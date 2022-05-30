package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Card;
import model.Deck;

public interface DecksDao extends JpaRepository<Deck, Integer> {

}
