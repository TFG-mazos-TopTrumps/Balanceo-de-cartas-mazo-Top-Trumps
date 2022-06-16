package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CardsDao;
import model.Card;

@Service
public class CardServiceImpl implements CardService {

	CardsDao cardsDao;

	public CardServiceImpl(@Autowired CardsDao cardsDao) {
		super();
		this.cardsDao = cardsDao;
	}
	
	public void saveCard(Card c, List<String> categories) {
		
		int i = 0;
		
		for(String categorie: categories) {
			c.getCategories().put(categorie, null);			
		}
		this.cardsDao.save(c);
		
	}

}
