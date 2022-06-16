package service;

import java.util.List;

import model.Card;

public interface CardService {
	
	void saveCard(Card c, List<String> categories);

}
