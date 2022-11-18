package dao;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Deck;


public interface DecksDao extends JpaRepository<Deck, Integer> {
	
	@Query("select d from Deck d where d.name=?1")
	Optional<Deck> findDeckByName(String name);
	
	@Query("select d from Deck d join d.keywords k where k.word=?1 and d.published=true")
	List<Deck> findDecksByKeywords(String k);
	
	@Query("select d.idDeck from Deck d where d.name=?1")
	Integer findDeckId(String name);

	@Query("select count(*) from Deck d where d.name=?1")
	Integer countDecksWithName(String name);
}
