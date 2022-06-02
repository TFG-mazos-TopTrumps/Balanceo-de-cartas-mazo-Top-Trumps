package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DecksDao;
import model.Deck;

@Service
public class DeckServiceImpl implements DeckService {

	DecksDao decksDao;

	public DeckServiceImpl(@Autowired DecksDao decksDao) {
		super();
		this.decksDao = decksDao;
	}

	public List<Deck> getDecks(String name) {

		return decksDao.findDeckByName(name);
	}

}
