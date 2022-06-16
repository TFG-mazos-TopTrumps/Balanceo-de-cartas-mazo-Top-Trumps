package service;

import java.util.List;

import model.Deck;
import model.Keyword;

public interface DeckService {

	Deck getDeckById(int id);
	List<Deck> getDecks(String name);
	List<Deck> getDecksByKeywords(String k);
	void createDeck(Deck d);
}
