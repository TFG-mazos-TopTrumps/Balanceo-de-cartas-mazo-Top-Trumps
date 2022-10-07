package service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import model.Keyword;

public interface KeywordService {
	
	Keyword findKeyword(String word) throws Exception;
	List<Keyword> findAllKeyword();
	boolean createKeyword(Integer idDeck, Keyword word) throws ConstraintViolationException;
	List<String> findAllWord();
	Integer countWords(String word);
	List<Keyword> findKeywordsByDeck(String deck);

}
