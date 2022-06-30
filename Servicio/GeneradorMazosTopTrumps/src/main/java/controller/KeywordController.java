package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Deck;
import model.Keyword;
import service.DeckService;
import service.KeywordService;

@RestController
@CrossOrigin("*")
public class KeywordController {
	
	@Autowired
	DeckService deckService;
	
	@Autowired
	KeywordService keywordService;
	
	@GetMapping(value="Keyword", produces=MediaType.APPLICATION_JSON_VALUE)
	public Keyword getKeyword(@RequestParam("word") String word) {
		
		return keywordService.findKeyword(word);
		
	}
	
	
	@PostMapping(value="Keyword", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void createKeyword(@RequestBody Keyword word, @RequestParam("name") String name) {
		keywordService.createKeyword(name, word);
		
		
		
		
	}

	
}
