package entities;

import java.util.List;
import java.util.Random;

import model.Card;

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
			for (String category : card.getCategories().keySet()) {
				if (card.getCategories().get(category) > bestValue) { 
					bestCategory = category;
					bestValue = card.getCategories().get(category);
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
			if (card.getCategories().get(category) > bestValue) {
				bestCard = card;
				bestValue = card.getCategories().get(category);
			}
		}
		return bestCard;
	}

}
