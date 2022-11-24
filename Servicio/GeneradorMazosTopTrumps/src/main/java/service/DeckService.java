package service;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.validation.ConstraintViolationException;






import model.Deck;



public interface DeckService {

	Deck getDeckById(int id) throws Exception;
	List<Deck> getDecks();
	Deck getDeckByName(String name) throws Exception;
	List<Deck> getDecksByKeywords(String k);
	boolean createDeck(Deck d) throws SQLException, ConstraintViolationException; 
	Integer findDeckId(String name);
	List<Double> generateDeckValues(Integer nCards, Integer nCategories, Double lowerLimit, Double upperLimit);
	Integer countDecksWithName(String name);
	void balanceDeck(Integer nCards, Integer nCategories, Double lowerLimit, Double upperLimit, String deck) throws ConstraintViolationException, SQLException;
	boolean pdfMazo(String deck) throws IOException, Exception;
	boolean checkKeyword(String deck) throws Exception;
	boolean publishDeck(String deck) throws Exception;
	boolean noPublishDeck(String deck) throws Exception;
	 
}
