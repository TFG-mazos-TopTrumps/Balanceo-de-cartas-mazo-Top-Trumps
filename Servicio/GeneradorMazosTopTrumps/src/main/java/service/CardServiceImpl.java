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
	
	public void saveCard(Card c) {
		
		this.cardsDao.save(c);
		
	}

}
