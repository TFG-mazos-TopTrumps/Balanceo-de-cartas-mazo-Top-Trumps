package controller;



import java.util.List;

import javax.persistence.Convert;
import javax.servlet.http.HttpServletRequest;
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
	public List<Deck> getDecks() {

		return deckService.getDecks();

	}
	
	@GetMapping(value = "DeckName", produces=MediaType.APPLICATION_JSON_VALUE)
	public Deck getDeckByName(@RequestParam("name") String name) {

		return deckService.getDeckByName(name);

	}

	@GetMapping(value="DecksKeyword", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Deck> getDecksByKeyword(@RequestParam("keyword") String keyword) {
		
		return deckService.getDecksByKeywords(keyword);
	}
	
	@PostMapping(value="Deck")
	public void createDeck(@RequestBody Deck d, @RequestParam("username") String username, @RequestParam("password") String password) {
		
		User u = userService.login(username, password);
		d.setUser(u);
		deckService.createDeck(d);
	
	}
	
//	@GetMapping(value = "DeckId")
//	public Integer getDeckId(HttpSession session) {
//	
//		String name = (String) session.getAttribute("deck");
//		return deckService.findDeckId(name);
//
//	}
	
	
	

	
	
}
