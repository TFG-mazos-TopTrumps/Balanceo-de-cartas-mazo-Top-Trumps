package dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Deck;
import model.Keyword;

public interface DecksDao extends JpaRepository<Deck, Integer> {
	
	@Query("select d from Deck d where d.name=?1")
	Deck findDeckByName(String name);
	
	@Query("select d from Deck d join d.keywords k where k.word=?1")
	List<Deck> findDecksByKeywords(String k);
}
