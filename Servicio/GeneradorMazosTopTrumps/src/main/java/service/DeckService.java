package service;

import java.util.List;

import model.Deck;
import model.Keyword;


public interface DeckService {

	Deck getDeckById(int id);
	List<Deck> getDecks();
	Deck getDeckByName(String name);
	List<Deck> getDecksByKeywords(String k);
	Deck createDeck(Deck d);
	Integer findDeckId(String name);

}
