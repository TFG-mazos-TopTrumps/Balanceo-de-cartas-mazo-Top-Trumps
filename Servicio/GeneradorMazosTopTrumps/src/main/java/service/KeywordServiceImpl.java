package service;

import java.util.Optional;

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

	
	public boolean createKeyword(Integer idDeck, Keyword word) {
		
		Optional<Deck> optionalDeck = decksDao.findById(idDeck);
		
		if(optionalDeck.isPresent()) {
			Deck d = optionalDeck.get();
			word.addKeyword(d);
			keywordsDao.save(word);
			return true;
		}
		
		return false;
	}

	

	
	
	
	
}
