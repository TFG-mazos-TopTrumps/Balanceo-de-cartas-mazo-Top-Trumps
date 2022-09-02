package objectivefunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uma.jmetal.solution.doublesolution.DoubleSolution;

import entities.Bot;
import entities.DummyBot;
import entities.GreedyBot;
import entities.Simulation;

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
		double sumDummies = 0.0;		
		double sumSmarties = 0.0;	
		
		int nCategories= (Integer) solution.attributes().get("categories");
		for(int i=0;i<gamesToSimulate;i++) {
			Simulation partida = new Simulation(random);
			partida.play(solution.variables(), nCategories, bots, maxRounds);
			for(Bot dummyBot:dummyBots) 
				sumDummies +=  dummyBot.getTrump().size();
			for(Bot smartBot:smartBots) 																				
				sumSmarties += smartBot.getTrump().size();																			
		}
		int nCardsInDeck=solution.variables().size()/nCategories;
		res = (-1.00)* (1.0/nCardsInDeck) * (1.0/gamesToSimulate) * (sumSmarties - sumDummies);
		return res;
	}
	
	

}
