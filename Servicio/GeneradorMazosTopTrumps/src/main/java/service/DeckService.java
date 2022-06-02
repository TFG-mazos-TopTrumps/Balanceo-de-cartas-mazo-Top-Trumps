package service;

import java.util.List;

import model.Deck;

public interface DeckService {

	List<Deck> getDecks(String name);
}
