package org.uma.jmetal.problem.toptrumps.objectivefunctions;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.uma.jmetal.problem.toptrumps.entities.Bot;
import org.uma.jmetal.problem.toptrumps.entities.GreedyBot;
import org.uma.jmetal.problem.toptrumps.entities.Simulation;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class FuncionMinSameBots extends ObjectiveFunction{

	private int RG;
	private int maxRounds;
	
	
	public FuncionMinSameBots(int RG,int maxRounds,Random random) {
		super(random);
		this.maxRounds=maxRounds;
		this.RG = RG;
		
	}
	
	public double evaluate(DoubleSolution solution) {
		double res = 0.0;
		double sum = 0.0;
		Bot b1=new GreedyBot(random);		
		Bot b2=new GreedyBot(random);
		List<Bot> bots=Arrays.asList(b1,b2);
		Integer nCategories=(Integer)solution.attributes().get("categories");
		for(int i=0;i<RG;i++) {			
			Simulation game = new Simulation(random); 			
			game.play(solution.variables(),nCategories,bots,maxRounds);									
			sum += Math.abs(b1.getTrump().size()- b2.getTrump().size());												
		}
		res = (-1.00) * (1.00/RG) * sum;
		return res;
	}	

}
