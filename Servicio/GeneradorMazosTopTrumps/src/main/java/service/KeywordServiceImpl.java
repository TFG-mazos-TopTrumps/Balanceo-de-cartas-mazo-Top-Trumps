package service;

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

	
	public Keyword createKeyword(String name, Keyword word) {
		
		Deck d = decksDao.findDeckByName(name);
		word.getDecks().add(d);
		return keywordsDao.save(word);
		
	}

	

	
	
	
	
}
