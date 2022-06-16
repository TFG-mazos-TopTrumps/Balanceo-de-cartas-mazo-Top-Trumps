package service;

import model.Keyword;

public interface KeywordService {
	
	Keyword findKeyword(String word);
	
	void createKeyword(Keyword word);

}
