package controller;



import java.util.List;

import javax.persistence.Convert;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Deck;
import model.Keyword;
import model.User;
import service.DeckService;
import service.KeywordService;
import service.UserService;

@RestController
@CrossOrigin("*")
public class DeckController {

	@Autowired
	DeckService deckService;

	@Autowired
	KeywordService keywordService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "Decks", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Deck> getDecks(@RequestParam("name") String name) {

		return deckService.getDecks(name);

	}

	@GetMapping(value="DecksKeyword", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Deck> getDecksByKeyword(@RequestParam("keyword") String keyword) {
		
		return deckService.getDecksByKeywords(keyword);
	}
	
	@PostMapping(value="Deck")
	public void createDeck(@RequestBody Deck d, @RequestParam("idUser") Integer idUser) {
		User u = userService.findUserById(idUser);
		d.setUser(u);
		deckService.createDeck(d);
		
	}
	
	@PutMapping(value="DeckKeyword")
	public void addKeywordDeck() {
		
	}
	
	

	
	
}
