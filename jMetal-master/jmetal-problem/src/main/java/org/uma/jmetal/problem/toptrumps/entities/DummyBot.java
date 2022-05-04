package org.uma.jmetal.problem.toptrumps.entities;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;

public class DummyBot extends Bot{

	
	public DummyBot(){
		super(new Random());
	}
	
	public DummyBot(Random random) {
		super(random);
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
		Card result=trump.get(0);
		trump.remove(0);
		return result;
	}



}
