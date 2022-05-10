package org.uma.jmetal.problem.toptrumps.objectivefunctions;

import java.util.Random;

import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class DominatedCards extends ObjectiveFunction {

	public DominatedCards(int cards, int categories) {
		this(new Random(),cards,categories); 		
	}



	public DominatedCards(Random random, int cards,int categories) {
		super(random);
		this.cards=cards;
		this.categories=categories;
	}



	private int cards;
	private int categories;
	
	
	
	public double evaluate(DoubleSolution solution) {
		
	    
	    double sum = 0.0;
	    for(int k=0;k<cards;k++) {	    		    		    	
	    	for(int i=0;i<cards;i++) {	    		
	    		if(i != k && dominates(k,i,solution)) {
	    			sum ++;
	    		}	    		
	    	}
	    }
	    return sum;
	}
	
	private boolean dominates(int card1,int card2, DoubleSolution solution) {		
		double catCard1=0.0;
		double catCard2=0.0;
		for(int j=0;j<categories;j++) {
			catCard1=solution.variables().get(card1 * categories+j);
			catCard2=solution.variables().get(card2 * categories+j);
			if(catCard1<=catCard2)			
				return false;						
				
		}
		return true;
	}
	
	

}
