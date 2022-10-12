package org.uma.jmetal.problem.toptrumps.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Bot {
		
	
	protected boolean turn;
	protected String name;
	protected List<Card> trump;
	protected List<Card> playedCards;
	protected Random random;
	protected int nCardsToChoose;
	
	
	
	public Bot(Random random, int nCardsToChoose) {
		this.random=random;
		this.turn=false;
		this.name="";
		this.trump=new LinkedList<Card>();
		this.playedCards=new LinkedList<Card>();
		this.nCardsToChoose=nCardsToChoose;
	}
	
	public void reset() {
		if(this.trump!=null)
			this.trump.clear();
		if(this.playedCards!=null)
			this.playedCards.clear();
		this.turn=false;		
			
	}
	
	/**
	 * Método empleado para que, si es turno, elija la categoría más conveniente para jugar con base a las cartas de la que dispone en su
	 * baza actual.
	 * @return categoría elegida.
	 */
	public abstract String chooseCategory();

	/**
	 * Método usado para que el bot, si gana la ronda, sume a su mazo las cartas jugadas.
	 * @param cartasObtenidas representa las cartas que obtiene cuando gana una ronda.
	 */
	public abstract void collectCards(List<Card> cards);
	
	/**
	 * Método empleado para que el agente elija una carta con la que jugar la ronda 
	 * según la categoría seleccionada.
	 * @return card carta elegida.
	 */
	public abstract Card play(String category);
	

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turno) {
		this.turn = turno;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public List<Card> getTrump() {
		return trump;
	}

	public void setTrump(List<Card> trump) {
		this.trump = trump;
	}

	public List<Card> getPlayedCards() {
		return playedCards;
	}

	public void setPlayedCard(List<Card> playedCards) {
		this.playedCards = playedCards;
	}

}
