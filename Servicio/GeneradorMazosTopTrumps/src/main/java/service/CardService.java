package service;

import java.sql.SQLException;
import java.util.List;

import javax.validation.ConstraintViolationException;

import model.Card;

public interface CardService  {
	
	Card saveCard(Card c) throws SQLException, ConstraintViolationException;
	Card findCardById(Integer id);
	List<Card> findCardsOfDeck(String deck);
	Card balanceDeck(Card c, List<Double> value) throws ConstraintViolationException, SQLException;
}
