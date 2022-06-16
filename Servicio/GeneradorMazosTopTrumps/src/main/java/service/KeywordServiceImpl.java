package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.KeywordDao;
import model.Keyword;

@Service
public class KeywordServiceImpl implements KeywordService {

	KeywordDao keywordDao;

	public KeywordServiceImpl(@Autowired KeywordDao keywordDao) {
		super();
		this.keywordDao = keywordDao;
	}
	
	public Keyword findKeyword(String word) {
		
		return keywordDao.findKeywordByWord(word);
	}

	
	public void createKeyword(Keyword word) {
		
		keywordDao.save(word);
		
	}

	
	
	
	
}
