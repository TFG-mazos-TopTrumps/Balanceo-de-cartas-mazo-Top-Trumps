package objectivefunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uma.jmetal.solution.doublesolution.DoubleSolution;

import entities.Bot;
import entities.DummyBot;
import entities.Simulation;

public class FunctionMinSameBots extends ObjectiveFunction{

	private int numberOfGamesToSimulate;
	private int maxRounds;
	private int nplayers;
	private int nCardsToChoose;
	
	
	public FunctionMinSameBots(int numberOfGamesToSimulate,int maxRounds,Random random, int nplayers,int nCardsToChoose) {
		super(random);
		this.maxRounds=maxRounds;
		this.numberOfGamesToSimulate = numberOfGamesToSimulate;
		this.nplayers=nplayers;
		this.nCardsToChoose=nCardsToChoose;
	}
	
	public double evaluate(DoubleSolution solution) {
		double res = 0.0;
		double sum = 0.0;
		
		List<Bot> bots=new ArrayList<Bot>(nplayers);
		Bot b1;
		Bot b2;
		for(int i=0;i<nplayers;i++)
			bots.add(new DummyBot(random,nCardsToChoose));
		Integer nCategories=(Integer)solution.attributes().get("categories");
		for(int i=0;i<numberOfGamesToSimulate;i++) {			
			Simulation game = new Simulation(random); 			
			game.play(solution.variables(),nCategories,bots,maxRounds);
			for(int j=0;j<nplayers;j++) {
				b1=bots.get(j);
				for(int k=j+1;k<nplayers;k++) {
					b2=bots.get(k);
					sum += Math.abs(b1.getTrump().size() - b2.getTrump().size());
				}
			}
															
		}
		Integer nCardsInDeck=solution.variables().size()/nCategories;
		res = (2.00) * (1.00 / (numberOfGamesToSimulate*nCardsInDeck*nplayers*(nplayers - 1)) ) * sum;
		return res;
	}	

}
