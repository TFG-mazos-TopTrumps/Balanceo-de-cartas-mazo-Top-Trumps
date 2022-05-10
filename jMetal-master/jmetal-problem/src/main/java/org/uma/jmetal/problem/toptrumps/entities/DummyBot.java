package org.uma.jmetal.problem.toptrumps.entities;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;

public class DummyBot extends Bot{

	
	public DummyBot(){
		super(new Random(),1);
	}
	
	public DummyBot(Random random,int nCardsToChoose) {
		super(random,nCardsToChoose);
	}
	
		
	
	public String chooseCategory() {
		Card card=trump.get(0);
		int res = random.nextInt(card.getNCategories()-1);
		List<String> keysAsArray = new ArrayList<String>(card.getCategories());		
		return keysAsArray.get(res);
	}


	@Override
	public void collectCards(List<Card> cards) {
		for(Card c : cards) {
			trump.add(c);
		}
		
	}

	@Override
	public Card play(String category) {
		int index=random.nextInt(Math.min(nCardsToChoose,trump.size()));
		Card result=trump.get(index);		
		return result;
	}



}
