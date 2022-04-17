package org.uma.jmetal.problem.multiobjective;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.problem.multiobjective.entidades.Simulacion;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

@SuppressWarnings("serial")
public class MazoTopTrumpsFB extends AbstractDoubleProblem {

	private int cartas = 32;
	private int categorias = 4;
	private int RG = 25000; // Número de simulaciones que se efectúan.
	public MazoTopTrumpsFB() {
	    setNumberOfVariables(cartas * categorias);
	    setNumberOfObjectives(3);
	    setName("MazoTopTrumpsFB");
	    
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

	    f[0] = fB1(solution);
	    f[1] = fB2(solution);
	    f[2] = fB3(solution);
	    
	   

	    solution.objectives()[0] = f[0];
	    solution.objectives()[1] = f[1];
	    solution.objectives()[2] = f[2];

	    return solution ;
	}
	

public double fB1(DoubleSolution solution) {
	double res = 0.0;
	double sum = 0.0;
		for(int i=0;i<RG;i++) {
		
			Simulacion partida = new Simulacion(); 
			partida.partida(solution.variables());
			
			if(partida.getP4().getBaza().size() > cartas/2) {
				sum += 1;
			}
		}
		
		res = (-1.00) * (1.00/RG) * sum;
		return res;
		
	}
	public double fB2(DoubleSolution solution) {
		double res = 0.0;
		double sum = 0.0;
		
		for(int i=0;i<RG;i++) {
			
			Simulacion partida = new Simulacion(); 
			partida.partida(solution.variables());
			
			sum += (partida.getTc()) * 1.00;
			
		}
		
		res = (-1.00) * (1.00/RG) * sum;
		return res;

		
	}
	
	public double fB3(DoubleSolution solution) {
		double res = 0.0;
		double sum = 0.0;
		
		for(int i=0;i<RG;i++) {
			
			Simulacion partida = new Simulacion(); 
			partida.partida(solution.variables());
			
			sum += Math.abs((2 * (partida.getP4().getBaza().size()) - (cartas/2)));
			
		}
		
		res = (1.00/RG) * sum;
		return res;
		
	}
	
	

}
