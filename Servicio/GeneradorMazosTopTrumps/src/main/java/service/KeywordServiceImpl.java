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
	
	public Keyword findKeyword(String word) {
		
		return keywordsDao.findKeywordByWord(word);
	}

	
	public boolean createKeyword(Integer idDeck, Keyword word) throws ConstraintViolationException {
		
		// Condiciones de validación
		boolean errorNotNullWord = word.getWord() == null || word.getWord().isBlank() || word.getWord().isEmpty() ? true:false;
		
		try {
			
			if(errorNotNullWord) {
				throw new ConstraintViolationException("La palabra clave no puede ser nula.",null);
			}
		
			Optional<Deck> optionalDeck = decksDao.findById(idDeck);
			
			if(optionalDeck.isPresent()) {
				Deck d = optionalDeck.get();
				word.addKeyword(d);
				if(this.keywordsDao.findKeywordByWord(word.getWord()) == null) {
					keywordsDao.save(word);
				} 			
				return true;
			}
			
			return false;
		} catch(ConstraintViolationException e) {
			
			if(errorNotNullWord) {
				System.out.println("La palabra clave no puede ser nula.");

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

	

	
	
	
	
}
