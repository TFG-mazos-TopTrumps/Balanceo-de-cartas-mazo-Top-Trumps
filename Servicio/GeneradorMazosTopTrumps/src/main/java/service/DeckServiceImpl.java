package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


	public List<Deck> getDecksByKeywords(String k) {
		
		return decksDao.findDecksByKeywords(k);
	}

	@Transactional
	public void createDeck(Deck d) {
		
		this.decksDao.save(d);
		
	}

	
	public Deck getDeckById(int id) {
		Optional<Deck> oDeck = decksDao.findById(id);
		Deck deck = new Deck();
		if(oDeck.isPresent()) {
			deck=oDeck.get();
			return deck;
		}
		return null;
	}

}
