package org.uma.jmetal.problem.toptrumps.entities;

import java.util.List;

public interface Game {
		
	boolean isFinalizada();	
	void play(List<Card> cards, List<Bot> players, int maxRounds);
	void play(List<Double> values,int nCategories, List<Bot> players, int maxRounds);	
	void playRound();
	int getNRoundsPlayed();	

}
