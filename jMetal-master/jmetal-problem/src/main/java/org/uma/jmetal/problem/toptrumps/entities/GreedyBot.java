package org.uma.jmetal.problem.toptrumps.entities;

import java.util.List;
import java.util.Random;

public class GreedyBot extends Bot {
	
	
	public GreedyBot() {
		this(new Random(),1);
	}
	
	public GreedyBot(Random random,int nCardsToChoose) {
		super(random,nCardsToChoose);
	}

	public String chooseCategory() {

		String bestCategory = null;
		Double bestValue = Double.MIN_VALUE;

		for (Card card : trump) {
			for (String category : card.getCategories()) {
				if (card.getValue(category) > bestValue) {
					bestCategory = category;
					bestValue = card.getValue(category);
				}
			}
		}
		return bestCategory;

	}	

	@Override
	public void collectCards(List<Card> cards) {
		trump.addAll(cards);

	}

	@Override
	public Card play(String category) {
		Card bestCard = null;
		Double bestValue = Double.MIN_VALUE;
		Card card;
		for (int i=0;i< Math.min(nCardsToChoose, trump.size()) ;i++) {
			card=this.trump.get(i);
			if (card.getValue(category) > bestValue) {
				bestCard = card;
				bestValue = card.getValue(category);
			}
		}
		return bestCard;
	}

}
