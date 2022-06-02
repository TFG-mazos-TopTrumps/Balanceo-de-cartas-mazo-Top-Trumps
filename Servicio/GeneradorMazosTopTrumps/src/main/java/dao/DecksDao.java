package dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import model.Deck;

public interface DecksDao extends JpaRepository<Deck, Integer> {
	
	List<Deck> findDeckByName(String name);
}
