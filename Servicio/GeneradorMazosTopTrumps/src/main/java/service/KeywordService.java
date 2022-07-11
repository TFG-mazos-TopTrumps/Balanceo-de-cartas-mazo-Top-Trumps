package service;

import model.Keyword;

public interface KeywordService {
	
	Keyword findKeyword(String word);
	
	boolean createKeyword(Integer idDeck, Keyword word);

}
