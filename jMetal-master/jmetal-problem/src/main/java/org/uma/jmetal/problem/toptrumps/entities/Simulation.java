package org.uma.jmetal.problem.toptrumps.entities;


import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Simulation implements Game {
	private List<Bot> bots;
	private List<Card> deck;			
	private int maxRounds;
	
	
	private Set<Card> cardsToBePlayed;
	private int nPlayedRounds;
	private String currentCategory;
	private Integer currentWinnerIndex;
	private List<Integer> differencesPerRounds;
	
	Random random;
	
	public Simulation() {
		this(new Random());
	}
	
	public Simulation(Random random) {
		this.random=random;
		nPlayedRounds=0;
		currentCategory=null;
		maxRounds=0;			
	}
	
	
	public Simulation(List<Card> deck,List<Bot>bots,int maxRounds,Random random) {
		this(random);
		this.deck=new ArrayList<Card>(deck);
		this.bots=bots;
		this.maxRounds=maxRounds;
				
	}
	
	public Simulation(List<Double> cardValues,int categories,List<Bot>bots, int maxRounds,Random random) {
		this(buildCards(cardValues, generateCategories(categories)),bots,maxRounds,random);
	}				
	
	public boolean isFinalizada() { 
		boolean res = (cardsToBePlayed.isEmpty()) ? true : false;
		return res;
	}
	
		

	public void play(List<Double> cardsValues, int nCategories, List<Bot> players, int maxRounds) {
		List<String> categories=generateCategories(nCategories);
		List<Card> cards=buildCards(cardsValues,categories);
		play(cards,players,maxRounds);		
	}

	public static List<String> generateCategories(int categoriasPorCarta) {
		List<String> result=new ArrayList<String>(categoriasPorCarta);
		for(int i=0;i<categoriasPorCarta;i++)
			result.add("Cat. "+(i+1));
		return result;
	}

	public static List<Card> buildCards(List<Double> cardsValues, List<String> categories) {
		if(cardsValues.size() % categories.size()!=0)
			throw new InvalidParameterException("The number of card values ("+cardsValues.size()+") is not a multiple of the number of categories ("+categories.size()+")");
		
		List<Card> result=new ArrayList<Card>(cardsValues.size()/categories.size());
		int nCategoriesPorCard=categories.size();
		List<Double> valuesCurrentCard=new ArrayList<Double>(nCategoriesPorCard);
		int index=0;		

		while(cardsValues.size() >= (index + categories.size())) {
			for(int i=0;i<nCategoriesPorCard;i++) {
				valuesCurrentCard.add(cardsValues.get(index+i));
			}
			index+=nCategoriesPorCard;
			result.add(new Card(categories,valuesCurrentCard));
			valuesCurrentCard.clear();
		}
		
		return result;
	}


	@Override
	public void play(List<Card> cards, List<Bot> players, int maxRounds) {
		
		this.maxRounds=maxRounds;
		this.nPlayedRounds=0;
		this.cardsToBePlayed=new HashSet<Card>(cards);
		this.deck=new ArrayList<Card>(cards);
		Collections.shuffle(deck);		
		this.bots=players;
		dealCards();
		while(!cardsToBePlayed.isEmpty() && nPlayedRounds<maxRounds) {
			playRound();
			nPlayedRounds++;
		}				
	}
	
	
	
	public void dealCards() {
		int cardsPerPlayer=deck.size()/bots.size();
		int index=0;
		for(Bot b:bots)
			b.reset();
		for(Card c:deck) {
			bots.get(index%bots.size()).getTrump().add(c);
			index++;
		}
	}


	@Override
	public void playRound() {
		if(currentWinnerIndex==null) { // First round:
			currentWinnerIndex=random.nextInt(bots.size()-1);			
		}
		
		List<Card> cardsPlayed=new ArrayList<Card>(bots.size());
		Map<Card,Bot> draw=new HashMap<Card,Bot>();
		Bot winner=bots.get(currentWinnerIndex);
		winner.setTurn(true);
		// Each bot plays a card:
		currentCategory=winner.chooseCategory();
		Bot currentPlayer;
		for(int i=currentWinnerIndex;i<bots.size();i++)
		{
			currentPlayer=bots.get(i);
			if(!currentPlayer.getTrump().isEmpty()) {
				Card c=currentPlayer.play(currentCategory);
				currentPlayer.getTrump().remove(c);
				cardsPlayed.add(c);			
				draw.put(c, currentPlayer);
			}
		}
		for(int i=0;i<currentWinnerIndex;i++) {
			currentPlayer=bots.get(i);
			if(!currentPlayer.getTrump().isEmpty()) {
				Card c=currentPlayer.play(currentCategory);
				currentPlayer.getTrump().remove(c);
				cardsPlayed.add(c);
				draw.put(c, currentPlayer);
			}
		}
		
		// We choose a new winner and compute differences:		
		
		Bot nextWinner=chooseWinner(draw,currentCategory);
		currentWinnerIndex=bots.indexOf(nextWinner);
		nextWinner.collectCards(cardsPlayed);
		cardsToBePlayed.removeAll(cardsPlayed);
		winner.setTurn(false);
		
	}


	private Bot chooseWinner(Map<Card, Bot> draw, String currentCategory) {
		Card bestCard=null;
		for(Card c:draw.keySet()) {
			if(bestCard!=null) {
				if(c.getValue(currentCategory)>bestCard.getValue(currentCategory))
					bestCard=c;
			}else 
				bestCard=c;			
		}
		return draw.get(bestCard);
	}

	@Override
	public int getNRoundsPlayed() {
		return nPlayedRounds;
	}
		

}
