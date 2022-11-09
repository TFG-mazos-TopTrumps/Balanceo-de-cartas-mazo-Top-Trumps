package service;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.validation.ConstraintViolationException;






import model.Deck;



public interface DeckService {

	Deck getDeckById(int id);
	List<Deck> getDecks();
	Deck getDeckByName(String name);
	List<Deck> getDecksByKeywords(String k);
	void createDeck(Deck d) throws SQLException, ConstraintViolationException; 
	Integer findDeckId(String name);
	List<Double> generateDeckValues(Integer nCards, Integer nCategories, Double lowerLimit, Double upperLimit);
	Integer countDecksWithName(String name);
	void balanceDeck(Integer nCards, Integer nCategories, Double lowerLimit, Double upperLimit, String deck) throws ConstraintViolationException, SQLException;
	void pdfMazo(String deck) throws IOException, Exception;
	boolean checkKeyword(String deck);
	void publishDeck(String deck);
	void noPublishDeck(String deck);
	 
}
