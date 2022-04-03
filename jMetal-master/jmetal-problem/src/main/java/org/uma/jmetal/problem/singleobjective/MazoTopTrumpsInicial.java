package org.uma.jmetal.problem.singleobjective;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.integerproblem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.integersolution.IntegerSolution;

public class MazoTopTrumpsInicial extends AbstractIntegerProblem {

	private Integer vi;
	private Integer vk;
	public MazoTopTrumpsInicial() {
	    setNumberOfVariables(4);
	    setNumberOfObjectives(1);
	    setName("MazoTopTrumpsInicial");
	    
	    List<Integer> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Integer> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(0);
	      upperLimit.add(10);
	    }

	    setVariableBounds(lowerLimit, upperLimit);
	}
	
	public IntegerSolution evaluate(IntegerSolution solution) {
		
		int[] f = new int[solution.objectives().length];

	    f[0] = solution.variables().get(0);
	    
	    
	    // Función fitness fD:
	    
	    int k = 32; // Número de cartas que componen el mazo (32).
	    int i = 1;
	    for(k=0;k<32;k++) {
	    	for(i=0;i<32;i++) {
	    		
	    		
	    	}
	    }

	    solution.objectives()[0] = f[0];
	   

	    return solution ;
	}
	
	
	

}
