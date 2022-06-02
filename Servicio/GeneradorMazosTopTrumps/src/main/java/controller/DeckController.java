package controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Deck;
import service.DeckService;

@RestController
@CrossOrigin("*")
public class DeckController {

	@Autowired
	DeckService deckService;

	@GetMapping(value = "Decks", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Deck> getDecks(@RequestParam("name") String name) {

		return deckService.getDecks(name);

	}

	
}
