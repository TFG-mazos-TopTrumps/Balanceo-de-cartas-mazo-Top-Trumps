package org.uma.jmetal.problem.multiobjective;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

import funciones.FuncionMaxNotSameBots;
import funciones.FuncionMinSameBots;
import funciones.FuncionObjetivo;
import funciones.FuncionfD;

@SuppressWarnings("serial")
public class MazoTopTrumpTriObjetivo extends AbstractDoubleProblem {

	private final int cartas = 4;
	private final int categorias = 4;
	private final int RG = 3; // Número de simulaciones que se efectúan.
	private final int objetivos = 3;
	private List<FuncionObjetivo> funciones = new ArrayList<>();
	public MazoTopTrumpTriObjetivo() {
	    setNumberOfVariables(cartas * categorias);
	    setNumberOfObjectives(objetivos);
	    setName("MazoTopTrumpsTriObjetivo");
	    
	    // Definimos aquí las funciones que queremos usar.
	    FuncionObjetivo f1 = new FuncionMinSameBots(RG);
	    FuncionObjetivo f2 = new FuncionMaxNotSameBots(RG);
	    FuncionObjetivo f3 = new FuncionfD();
	    
	    // Se añaden a la lista de funciones.
	    this.funciones.add(f1);
	    this.funciones.add(f2);
	    this.funciones.add(f3);
	    
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

		
		// Añadimos las funciones para cada objetivo:
		
		for(int i=0;i<funciones.size();i++) {
	    f[i] = funciones.get(i).funcion(solution);
	    solution.objectives()[i] = f[i];
		}
	 
	    return solution ;
	}
	

		
	}
	




