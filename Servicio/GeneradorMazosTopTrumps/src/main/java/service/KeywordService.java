package service;

import java.sql.SQLException;
import java.util.List;

import javax.validation.ConstraintViolationException;

import model.Keyword;

public interface KeywordService {
	
	Keyword findKeyword(String word) throws Exception;
	List<Keyword> findAllKeyword();
	boolean createKeyword(String deck, Keyword word) throws ConstraintViolationException, SQLException;
	List<String> findAllWord();
	Integer countWords(String word);
	List<Keyword> findKeywordsByDeck(String deck) throws Exception;

}
