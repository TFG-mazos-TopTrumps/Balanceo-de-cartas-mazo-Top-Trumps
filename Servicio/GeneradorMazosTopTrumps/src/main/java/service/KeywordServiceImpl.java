package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DecksDao;

import dao.KeywordsDao;
import model.Deck;
import model.Keyword;

@Service
public class KeywordServiceImpl implements KeywordService {

	KeywordsDao keywordsDao;
	DecksDao decksDao;

	public KeywordServiceImpl(@Autowired KeywordsDao keywordsDao, @Autowired DecksDao decksDao) {
		super();
		this.keywordsDao = keywordsDao;
		this.decksDao = decksDao;
	}
	
	public Keyword findKeyword(String word) throws Exception {
		
		// Condiciones de validación
		
		boolean errorNotNullWord = word == null || word.isBlank() || word.isEmpty() ? true:false;
		boolean errorWordNotExist = keywordsDao.countKeywordByWord(word) == 0 ? true:false;
		boolean anyError = false;
		try { 
			if(errorNotNullWord) {
				anyError=true;
				throw new Exception("Debe indicar una palabra clave para realizar la búsqueda.");
			}
			
			if(!anyError && errorWordNotExist) {
				anyError=true;
				throw new Exception("Debe indicar una palabra clave que se encuentre registrada en el sistema.");
			}
			
			if(!anyError) {
				return keywordsDao.findKeywordByWord(word).get();
			}
			
		} catch(Exception e) {
			if(errorNotNullWord) {
				System.out.println("Debe indicar una palabra clave para realizar la búsqueda.");
			} 
			if(errorWordNotExist) {
				System.out.println("Debe indicar una palabra clave que se encuentre registrada en el sistema.");
			}
			
		}
		return null;
		
		
	}

	
	public boolean createKeyword(String name, Keyword word) throws ConstraintViolationException, SQLException {
		
		Optional<Deck> optDeck = this.decksDao.findDeckByName(name);
		if(optDeck.isEmpty()) {
			return false;
		} else {
		
		Deck deck = optDeck.get();
		// Condiciones de validación
		boolean errorNotNullWord = word.getWord() == null || word.getWord().isBlank() || word.getWord().isEmpty() ? true:false;
		boolean errorMaxLengthWord = word.getWord().length() >= 45 ? true:false;
		boolean errorDuplicateWord = false;
		boolean anyError=false;
		
		try {
		for(Keyword k : deck.getKeywords()) {
			if(k.getWord().equals(word.getWord())) {
				errorDuplicateWord=true;
			}
		}
		
			
			if(errorNotNullWord) {
				anyError=true;
				throw new ConstraintViolationException("La palabra clave no puede ser nula.",null);
			}

			if(!anyError && errorDuplicateWord) {
				anyError=true;
				throw new SQLException("La palabra clave indicada ya se ha añadido al mazo.");
			}
			
			if(!anyError && errorMaxLengthWord) {
				anyError=true;
				throw new ConstraintViolationException("La palabra clave no puede estar formada por 45 o más caracteres.",null);
			}
		
			if(!anyError) { 
			
				
				if(this.keywordsDao.findKeywordByWord(word.getWord()).isEmpty()) {
					word.addKeyword(deck);
					keywordsDao.save(word);
				} else {
					Keyword kw = this.keywordsDao.findKeywordByWord(word.getWord()).get();
					kw.addKeyword(deck);
					keywordsDao.save(kw);
					
				}
				
				
				return true;
			
			}
			
			
		} catch(ConstraintViolationException e) {
			
			if(errorNotNullWord) {
				System.out.println("La palabra clave no puede ser nula.");

			}
			
			if(errorMaxLengthWord) {
				System.out.println("La palabra clave no puede estar formada por 45 o más caracteres.");

			}
			
			
		}
		catch(SQLException e) {
			if(errorDuplicateWord) {
				System.out.println("La palabra clave indicada ya se ha añadido al mazo.");

			}
			
		}
		return false;
		}
		
	}

	@Override
	public List<Keyword> findAllKeyword() {
		
		return this.keywordsDao.findAll();
	}

	@Override
	public List<String> findAllWord() {
		return this.keywordsDao.findAllWord();
	}

	@Override
	public Integer countWords(String word) {
		
		return keywordsDao.countKeywordByWord(word);
	}

	@Override
	public List<Keyword> findKeywordsByDeck(String deck) throws Exception{
	
		try {
		Optional<Deck> optDeck = this.decksDao.findDeckByName(deck);
		
		if(optDeck.isPresent()) {
			return optDeck.get().getKeywords();
		} else {
			throw new Exception("El mazo designado por el nombre no se encuentra registrado en el sistema.");
		}
		} catch(Exception e) {
			System.out.println("El mazo designado por el nombre no se encuentra registrado en el sistema.");
			return null;
		}
		
	}

	

	
	
	
	
}
