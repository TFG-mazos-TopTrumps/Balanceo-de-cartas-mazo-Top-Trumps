package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Card;

public interface CardsDao extends JpaRepository<Card, Integer> {

}
