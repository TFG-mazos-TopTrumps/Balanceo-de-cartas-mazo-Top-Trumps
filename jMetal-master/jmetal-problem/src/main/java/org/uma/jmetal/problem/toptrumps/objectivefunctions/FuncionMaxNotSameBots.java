package org.uma.jmetal.problem.toptrumps.objectivefunctions;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


import org.uma.jmetal.problem.toptrumps.entities.Bot;
import org.uma.jmetal.problem.toptrumps.entities.DummyBot;
import org.uma.jmetal.problem.toptrumps.entities.GreedyBot;
import org.uma.jmetal.problem.toptrumps.entities.Simulation;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class FuncionMaxNotSameBots extends ObjectiveFunction{	
	
	private int maxRounds;
	

	public FuncionMaxNotSameBots(int RG, int maxRounds, Random random) {
		super(random);
		this.RG = RG;
		this.maxRounds=maxRounds;
		
	}

	public double evaluate(DoubleSolution solution) {
		double res = 0.0;
		double sum = 0.0;
		DummyBot dummyBot=new DummyBot(random);
		GreedyBot greedyBot=new GreedyBot(random);			
		List<Bot> bots=Arrays.asList(dummyBot,greedyBot);
		int nCategories=(Integer)solution.attributes().get("categories");
		for(int i=0;i<RG;i++) {			
			Simulation partida = new Simulation(random);			
			partida.play(solution.variables(), nCategories, bots, maxRounds);							
			sum += greedyBot.getTrump().size() - dummyBot.getTrump().size();										
		}
		res = (1.00/RG) * sum;
		return res;
	}
	
	

}
