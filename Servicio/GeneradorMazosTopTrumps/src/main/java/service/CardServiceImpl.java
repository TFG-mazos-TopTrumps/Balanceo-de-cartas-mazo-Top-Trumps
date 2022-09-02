package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.ConstraintViolationException;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CardsDao;
import model.Card;

import toptrumps.TopTrumpsDeckGenerationProblem;

@Service
public class CardServiceImpl implements CardService {

	CardsDao cardsDao;

	public CardServiceImpl(@Autowired CardsDao cardsDao) {
		super();
		this.cardsDao = cardsDao;
	}
	
	@Transactional
	public Card saveCard(Card c) throws SQLException, ConstraintViolationException {
		boolean errorNotNullName = c.getName() == null || c.getName().isBlank() || c.getName().isEmpty() ? true:false;
		boolean anyError = false;
		
		try {
			if(errorNotNullName) {
				anyError=true;
				throw new SQLException("El nombre de la carta no puede quedar vacío.");
				
			}
			
			if(!anyError) {
			
			return this.cardsDao.save(c);
			}
		} catch(ConstraintViolationException e) {
			if(errorNotNullName) {
				System.out.println("El campo nombre de la carta no puede ser nulo.");
				
			}
			return null;
		}
		return new Card();
		
	}

	
	public Card findCardById(Integer id) {
		
		Optional<Card> optionalCard = this.cardsDao.findById(id);
		
		if(optionalCard.isPresent()) {
			return optionalCard.get();
		} else {
			System.out.println("El id introducido no corresponde con ninguna carta registrada en el sistema.");
			return optionalCard.orElse(new Card());
		}
		
	}

	
	public List<Card> findCardsOfDeck(String deck) {
		
		return cardsDao.findCardsOfDeck(deck);
	}

	@Override
	public Card balanceDeck(Card c, List<Double> value) throws ConstraintViolationException, SQLException {
		Card card = new Card();
		
		for (Entry<String, Double> entry : c.getCategories().entrySet()) {
		   
		    c.getCategories().put(entry.getKey(), null);
		}
		
		card = this.saveCard(c);
		return card;

}
}
