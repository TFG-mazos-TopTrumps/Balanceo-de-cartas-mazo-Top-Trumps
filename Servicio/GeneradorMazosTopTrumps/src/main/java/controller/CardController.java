package controller;

import java.sql.SQLException;
import java.util.List;


import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Card;
import model.Deck;
import service.CardService;
import service.DeckService;

@RestController
@CrossOrigin("*")
public class CardController {

	@Autowired
	CardService cardService;
	
	@Autowired
	DeckService deckService;
	
	@GetMapping(value="Card")
	public Card getCard(@RequestParam("card") Integer card) throws Exception {
		return this.cardService.findCardById(card);
		
	}
	
	@PostMapping(value="Card", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Card createCard(@RequestBody Card c, @RequestParam("deck") String deck) throws Exception, SQLException, ConstraintViolationException {
		Deck d = this.deckService.getDeckByName(deck);
		c.setDeck(d);
		return this.cardService.saveCard(c);
	}
	@PostMapping(value="ValidateCategory", produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean validateCategory(@RequestParam("category") String category) throws Exception {
		return this.cardService.validateCategory(category);
	}
	
	@PostMapping(value="CardCategory")
	public Card categorieCard(@RequestParam("card") int card, @RequestParam("category") String category) throws Exception, ConstraintViolationException, SQLException {
		
		return this.cardService.categorieCard(card, category);
	}
	
	@GetMapping(value="CardsDeck")
	public List<Card> cardsOfDeck(@RequestParam("deck") String deck) {
		return this.cardService.findCardsOfDeck(deck);
	}
	
	
}
