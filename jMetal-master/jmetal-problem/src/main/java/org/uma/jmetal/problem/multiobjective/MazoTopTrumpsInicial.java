package org.uma.jmetal.problem.multiobjective;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class MazoTopTrumpsInicial extends AbstractDoubleProblem {

	private int cartas = 32;
	private int categorias = 4;
	public MazoTopTrumpsInicial() {
	    setNumberOfVariables(cartas * categorias);
	    setNumberOfObjectives(3);
	    setName("MazoTopTrumpsInicial");
	    
	    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(0.0);
	      upperLimit.add(10.0);
	    }

	    setVariableBounds(lowerLimit, upperLimit);
	}
	
	public DoubleSolution evaluate(DoubleSolution solution) {
		double[] f = new double[solution.objectives().length];

	    f[0] = solution.variables().get(0);
	    
	    // f[1] = null;

	    solution.objectives()[0] = f[0];
	    solution.objectives()[1] = f[1];
	    solution.objectives()[2] = f[2];

	    return solution ;
	}
	
//	public boolean simulacion() {
//		
//	}
//	
//	public double fB1(DoubleSolution solution) {
//		
//		for(int i=0;i<25000;i++) {
//			boolean t4 = ()
//		}
//		
//	}
//	
//	public double fB2(DoubleSolution solution) {
//		
//	}
//	
//	public double fB3(DoubleSolution solution) {
//		
//	}
	
	

}
