package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PostMapping(value="Card", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void createCard(@RequestBody Card c, @RequestParam("deck") String deck) {
		
		
		Deck d = deckService.getDeckByName(deck); 
		c.setDeck(d);
		
		this.cardService.saveCard(c);
		
	}
}
