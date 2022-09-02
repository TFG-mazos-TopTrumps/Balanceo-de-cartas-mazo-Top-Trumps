package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

import model.Card;
import model.Deck;
import model.Keyword;


public interface DeckService {

	Deck getDeckById(int id);
	List<Deck> getDecks();
	Deck getDeckByName(String name);
	List<Deck> getDecksByKeywords(String k);
	void createDeck(Deck d) throws SQLException, ConstraintViolationException; 
	Integer findDeckId(String name);
	List<Double> generateDeckValues(Integer nCards, Integer nCategories, Double lowerLimit, Double upperLimit);
	Integer countDecksWithName(String name);
	void balanceDeck(Integer nCards, Integer nCategories, Double lowerLimit, Double upperLimit, String deck);
	PDDocument pdfMazo(String mazo) throws IOException;
	boolean checkKeyword(String deck);
	 
}
