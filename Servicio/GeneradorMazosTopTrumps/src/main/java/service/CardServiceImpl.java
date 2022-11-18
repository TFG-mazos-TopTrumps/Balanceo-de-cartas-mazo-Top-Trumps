package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map.Entry;

import javax.validation.ConstraintViolationException;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CardsDao;
import model.Card;


@Service
public class CardServiceImpl implements CardService {
	
	
	CardsDao cardsDao;

	public CardServiceImpl(@Autowired CardsDao cardsDao) {
		super();
		this.cardsDao = cardsDao;
	}
	
	@Override
	@Transactional
	public Card saveCard(Card c) throws SQLException, ConstraintViolationException {
		
		
		boolean errorNotNullName = c.getName() == null || c.getName().isBlank() || c.getName().isEmpty() ? true:false;
		boolean errorMaxLengthName = c.getName().length() > 25 ? true:false;
		boolean errorMaxLengthDescription = (c.getDescription() != null && c.getDescription().length() > 500) ? true:false;
		boolean errorMaxLengthImage = (c.getImage() != null && c.getImage().length() > 4000) ? true:false;
		boolean errorPatternURL = (c.getImage() != null && c.getImage().length() >= 1 && !(c.getImage().startsWith("http://") || c.getImage().startsWith("https://"))) ? true:false;
		boolean errorIncorrectFormatImage = c.getImage() != null  && !(c.getImage().contains(".jpg") || c.getImage().contains(".png") || c.getImage().contains(".jpeg")) ? true:false;
		boolean errorNotNullDeck = c.getDeck() == null ? true:false;
		boolean anyError = false;
		try {
		
		
			if(!anyError && errorNotNullName) {
				anyError=true;
				throw new ConstraintViolationException("El nombre de la carta no puede quedar vacío.",null);
				
			}
			if(!anyError && errorNotNullDeck) {
				anyError=true;
				throw new ConstraintViolationException("Debe estar asociada a un mazo",null);
				
			}
			if(!anyError && errorMaxLengthName) {
				anyError=true;
				throw new ConstraintViolationException("El nombre de la carta no puede tener más de 25 caracteres.",null);
				
			}
			if(!anyError && errorMaxLengthDescription) {
				anyError=true;
				throw new ConstraintViolationException("La descripción no puede tener más de 500 caracteres.",null);
				
			}
			if(!anyError && errorMaxLengthImage) {
				anyError=true;
				throw new ConstraintViolationException("La URL de la imagen no puede tener más de 4000 caracteres.",null);
				
			}
			if(!anyError && errorPatternURL) {
				anyError=true;
				throw new ConstraintViolationException("El campo imagen ha de ser una URL.",null);
				
			}
			
			
			if(!anyError) {
			
			return this.cardsDao.save(c);
			}
		} catch(ConstraintViolationException e) {
			if(errorNotNullName) {
				System.out.println("El campo nombre de la carta no puede ser nulo.");
				
			}
			if(errorNotNullDeck) {
				System.out.println("Debe estar asociada a un mazo.");
				
			}
			if(errorMaxLengthName) {
				System.out.println("El nombre de la carta no puede tener más de 25 caracteres.");
				
			}
			if(errorMaxLengthDescription) {
				System.out.println("La descripción de la carta no puede tener más de 500 caracrteres.");
				
			}
			if(errorMaxLengthImage) {
				System.out.println("La URL de la imagen no puede tener más de 4000 caracteres .");
				
			}
			if(errorPatternURL) {
				System.out.println("El campo imagen ha de ser una URL.");
				
			}
			if(!anyError && errorIncorrectFormatImage) {
				anyError=true;
				throw new ConstraintViolationException("La imagen ha tener formato .jpg, .png o .jpeg .",null);
				
			}
			if(errorIncorrectFormatImage) {
				System.out.println("La imagen ha tener formato .jpg, .png o .jpeg .");
			}
			return null;
		}
		
		
			return null;
		
	}

	@Override
	public Card findCardById(Integer id) throws Exception {
	try {
		Optional<Card> optionalCard = this.cardsDao.findById(id);
		
		if(optionalCard.isPresent()) {
			return optionalCard.get();
		} else {
			throw new Exception("El id introducido no corresponde con ninguna carta registrada en el sistema.");
			
		}
		
	} catch(Exception e) {
		System.out.println("El id introducido no corresponde con ninguna carta registrada en el sistema.");
		return null;
	}
	}

	@Override
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

	@Override
	public Card categorieCard(int card, String category) throws Exception, ConstraintViolationException, SQLException {
		boolean errorNotNullCategory = category == null || category.isBlank() || category.isEmpty() ? true:false;
		
		try {
			if(errorNotNullCategory) {
				throw new Exception("La categoria no puede ser nula.");
			}
			Card c = this.findCardById(card);
			c.getCategories().put(category, 0.0);
			return this.saveCard(c);
		} catch(Exception e) {
			System.out.println("La categoria no puede ser nula.");
			return null;
		}
	}

	@Override
	public boolean validateCategory(String category) throws Exception {
		boolean errorNotNullCategory = category == null || category.isBlank() || category.isEmpty() ? true:false;
		boolean errorMaxLengthCategory = category.length() > 15 ? true:false;
		boolean anyError = false;
		try {
			if(errorNotNullCategory) {
				anyError=true;
				throw new Exception("La categoría no puede ser nula.");
			}
			if(!anyError && errorMaxLengthCategory) {
				anyError=true;
				throw new Exception("La categoría no puede tener más de 15 caracteres.");
			}
			
			if(!anyError) {
				return true;
			}
			
			
		} catch(Exception e) {
			if(errorNotNullCategory) {
				System.out.println("La categoría no puede ser nula.");
			}
			if(errorMaxLengthCategory) {
				System.out.println("La categoría no puede ser nula.");
			}
			
			
		}
		return false;
	}
}
