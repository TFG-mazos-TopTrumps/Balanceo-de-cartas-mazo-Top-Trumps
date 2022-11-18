package service;

import java.sql.SQLException;
import java.util.List;

import javax.validation.ConstraintViolationException;

import model.Card;

public interface CardService  {
	
	Card saveCard(Card c) throws SQLException, ConstraintViolationException;
	Card categorieCard(int card, String category) throws Exception, ConstraintViolationException, SQLException;
	Card findCardById(Integer id) throws Exception;
	List<Card> findCardsOfDeck(String deck);
	Card balanceDeck(Card c, List<Double> value) throws ConstraintViolationException, SQLException;
	boolean validateCategory(String category) throws Exception;
}
