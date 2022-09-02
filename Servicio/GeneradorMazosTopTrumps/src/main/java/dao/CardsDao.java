package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Card;

public interface CardsDao extends JpaRepository<Card, Integer> {
	
	@Query("select c from Card c join c.deck d where d.name=?1")
	List<Card> findCardsOfDeck(String deck);

}
