package org.uma.jmetal.problem.toptrumps.objectivefunctions;

import java.util.Random;

import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class FuncionfD extends ObjectiveFunction {

	public FuncionfD(int cards, int categories) {
		this(new Random(),cards,categories); 		
	}



	public FuncionfD(Random random, int cards,int categories) {
		super(random);
		this.cards=cards;
		this.categories=categories;
	}



	private int cards;
	private int categories;
	
	
	
	public double evaluate(DoubleSolution solution) {
		double fD = 0;
		// Función fitness fD:
	    
	    double sum = 0.0;
	    for(int k=0;k<cards;k++) {
	    	double lk1 = solution.variables().get(k * categories);
	    	double lk2 = solution.variables().get((k * categories) +1);
	    	double lk3 = solution.variables().get((k * categories) +2);
	    	double lk4 = solution.variables().get((k * categories) +3);
	    	
	    	
	    	for(int i=0;i<cards;i++) {
	    		
	    		if(i != k) {
	    		double li1 = solution.variables().get(i * categories);
		    	double li2 = solution.variables().get((i * categories) +1);
		    	double li3 = solution.variables().get((i * categories)+2);
		    	double li4 = solution.variables().get((i * categories)+3);
		    	
		    	boolean comparacion = (lk1 > li1) && (lk2 > li2) && (lk3 > li3) && (lk4 > li4);
	    		double condicionResta = (comparacion) ? 1.00:0.00;
	    		sum += 1 - condicionResta;
	    		}
	    		
	    		}
	    	}
	    fD = (-1.00)*(1/cards) * sum;
	    return fD;
	}
	
	

}
