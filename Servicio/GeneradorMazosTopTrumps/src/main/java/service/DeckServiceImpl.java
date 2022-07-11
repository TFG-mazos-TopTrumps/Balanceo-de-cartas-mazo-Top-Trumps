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

	public List<Deck> getDecks() {

		return decksDao.findAll();

	}
	public List<Deck> getDecksByKeywords(String k) {
		
		return decksDao.findDecksByKeywords(k);
	}

	@Transactional
	public Deck createDeck(Deck d) {
		
		return this.decksDao.save(d);
		
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

	@Override
	public Deck getDeckByName(String name) {
		
		return decksDao.findDeckByName(name);
	}

	@Override
	public Integer findDeckId(String name) {
		
		return decksDao.findDeckId(name);
	}

	

}
