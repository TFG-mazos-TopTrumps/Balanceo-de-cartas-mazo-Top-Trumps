package controller;



import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Convert;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;


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
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

import com.itextpdf.text.DocumentException;

import model.Card;
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
	
	@GetMapping(value = "CheckKeywords", produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean checkKeywords(@RequestParam("name") String name) {

		return this.deckService.checkKeyword(name);

	}
	
	@GetMapping(value = "CountDeckName")
	public Integer countDecksByName(@RequestParam("name") String name) {

		return deckService.countDecksWithName(name);

	}

	@GetMapping(value="DecksKeyword", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Deck> getDecksByKeyword(@RequestParam("keyword") String keyword) {
		
		return deckService.getDecksByKeywords(keyword);
	}
	
	@PostMapping(value="Deck", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void createDeck(@RequestBody Deck d, @RequestParam("username") String username, @RequestParam("password") String password) throws ConstraintViolationException, SQLException {
		
		User u = userService.login(username, password);
		d.setUser(u);
		deckService.createDeck(d);
	
	}
	
	@PostMapping(value="DeckGenerateValues", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Double> valuesDeck(@RequestParam("cards") Integer cards, @RequestParam("categories") Integer categories,
			@RequestParam("lowerLimit") Double lowerLimit, @RequestParam("upperLimit") Double upperLimit) {
		
		return this.deckService.generateDeckValues(cards, categories, lowerLimit, upperLimit);

	}
	
	@PostMapping(value="DeckPDF", produces=MediaType.APPLICATION_PDF_VALUE)
	public void pdfMazo(@RequestParam("deck") String deck) throws IOException {
		
		this.deckService.pdfMazo(deck);

	}
	
	@PutMapping(value="DeckBalance", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void balanceDeck(@RequestParam("cards") Integer cards, @RequestParam("categories") Integer categories,
			@RequestParam("lowerLimit") Double lowerLimit, @RequestParam("upperLimit") Double upperLimit,
			@RequestParam("deck") String deck) throws ConstraintViolationException, SQLException {
		this.deckService.balanceDeck(cards, categories, lowerLimit, upperLimit, deck);
		
	}
	
	@PutMapping(value="DeckPublish")
	public void publishDeck(@RequestParam("deck") String deck) {
		
		this.deckService.publishDeck(deck);
	
	}
	
	@PutMapping(value="DeckNoPublish")
	public void noPublishDeck(@RequestParam("deck") String deck) {
		
		this.deckService.noPublishDeck(deck);
	
	}
	
	
	
	

	
	

	
	
}
