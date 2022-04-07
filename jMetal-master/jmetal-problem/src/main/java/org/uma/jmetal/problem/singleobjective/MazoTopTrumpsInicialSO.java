package org.uma.jmetal.problem.singleobjective;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class MazoTopTrumpsInicialSO extends AbstractDoubleProblem {

	private int cartas = 32;
	private int categorias = 4;
	public MazoTopTrumpsInicialSO() {
	    setNumberOfVariables(cartas * categorias);
	    setNumberOfObjectives(1);
	    setName("MazoTopTrumpsInicial");
	    
	    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(1.00);
	      upperLimit.add(10.00);
	    }

	    setVariableBounds(lowerLimit, upperLimit);
	}
	
	public DoubleSolution evaluate(DoubleSolution solution) {
		
		double[] f = new double[solution.objectives().length];

	    f[0] = fD(solution);
	    

	    solution.objectives()[0] = f[0];
	   

	    return solution ;
	}
	
	
	
	public double fD(DoubleSolution solution) {
		
		double fD = 0;
		// Función fitness fD:
	    
	    double sum = 0.0;
	    for(int k=0;k<32;k++) {
	    	double lk1 = solution.variables().get(k * categorias);
	    	double lk2 = solution.variables().get((k * categorias) +1);
	    	double lk3 = solution.variables().get((k * categorias) +2);
	    	double lk4 = solution.variables().get((k * categorias) +3);
	    	
	    	
	    	for(int i=0;i<32;i++) {
	    		
	    		if(i != k) {
	    		double li1 = solution.variables().get(i * categorias);
		    	double li2 = solution.variables().get((i * categorias) +1);
		    	double li3 = solution.variables().get((i * categorias)+2);
		    	double li4 = solution.variables().get((i * categorias)+3);
		    	
		    	boolean comparacion = (lk1 > li1) && (lk2 > li2) && (lk3 > li3) && (lk4 > li4);
	    		double condicionResta = (comparacion) ? 1.00:0.00;
	    		sum += 1 - condicionResta;
	    		}
	    		
	    		}
	    	}
	    fD = (-1.00)*(1/32.00) * sum;
	    return fD;
	}
	

}
