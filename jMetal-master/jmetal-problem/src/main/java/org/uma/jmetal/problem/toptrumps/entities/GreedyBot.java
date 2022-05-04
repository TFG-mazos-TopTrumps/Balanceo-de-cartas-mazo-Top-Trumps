package org.uma.jmetal.problem.toptrumps.entities;

import java.util.List;
import java.util.Random;

public class GreedyBot extends Bot {

	public GreedyBot() {
		this(new Random());
	}
	
	public GreedyBot(Random random) {
		super(random);
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

		for (Card card : trump) {
			if (card.getValue(category) > bestValue) {
				bestCard = card;
				bestValue = card.getValue(category);
			}
		}
		return bestCard;
	}

}
