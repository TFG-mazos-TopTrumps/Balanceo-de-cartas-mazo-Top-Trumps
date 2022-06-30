package service;

import model.Keyword;

public interface KeywordService {
	
	Keyword findKeyword(String word);
	
	Keyword createKeyword(String name, Keyword word);

}
