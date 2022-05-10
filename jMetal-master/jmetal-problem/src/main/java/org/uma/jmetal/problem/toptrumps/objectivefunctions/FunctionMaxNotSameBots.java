package org.uma.jmetal.problem.toptrumps.objectivefunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import org.uma.jmetal.problem.toptrumps.entities.Bot;
import org.uma.jmetal.problem.toptrumps.entities.DummyBot;
import org.uma.jmetal.problem.toptrumps.entities.GreedyBot;
import org.uma.jmetal.problem.toptrumps.entities.Simulation;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class FunctionMaxNotSameBots extends ObjectiveFunction{	
	
	private int maxRounds;
	private int gamesToSimulate;
	private int nplayers;
	private int nCardsToChooseFrom;
	
	public FunctionMaxNotSameBots(int gamesToSimulate, int maxRounds, Random random, int nplayers, int nCardsToChooseFrom) {
		super(random);
		this.gamesToSimulate = gamesToSimulate;
		this.maxRounds=maxRounds;
		this.nplayers = nplayers;
		this.nCardsToChooseFrom=nCardsToChooseFrom;
		
	}

	public double evaluate(DoubleSolution solution) {
		
		List<Bot> dummyBots=new ArrayList<Bot>((int)nplayers/2);
		for(int i=0;i<(int)nplayers/2;i++)
			dummyBots.add(new DummyBot(random,nCardsToChooseFrom));
		List<Bot> smartBots=new ArrayList<Bot>((int)nplayers/2);
		for(int i=0;i<(int)nplayers/2;i++)
			smartBots.add(new GreedyBot(random,nCardsToChooseFrom));
		List<Bot> bots=new ArrayList<Bot>(nplayers);
		bots.addAll(dummyBots);
		bots.addAll(smartBots);
		
		double res = 0.0;
		double sum = 0.0;		
			
		
		int nCategories= (Integer) solution.attributes().get("categories");
		for(int i=0;i<gamesToSimulate;i++) {
			for(Bot dummyBot:dummyBots) {
				for(Bot smartBot:smartBots) {
					Simulation partida = new Simulation(random);			
					partida.play(solution.variables(), nCategories, bots, maxRounds);							
					sum += (smartBot.getTrump().size() - dummyBot.getTrump().size());
				}
			}
													
		}
		int nCardsInDeck=solution.variables().size()/nCategories;
		res = (-1.00)* (1.0/nCardsInDeck) * (1.0/gamesToSimulate) * sum;
		return res;
	}
	
	

}
