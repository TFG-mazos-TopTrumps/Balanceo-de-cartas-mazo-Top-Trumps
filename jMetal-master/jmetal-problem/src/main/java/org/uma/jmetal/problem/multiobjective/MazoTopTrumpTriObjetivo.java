package org.uma.jmetal.problem.multiobjective;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.problem.multiobjective.entidades.Agente;
import org.uma.jmetal.problem.multiobjective.entidades.DummyBot;
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
	
	private DummyBot db1 = new DummyBot();
	private Agente a1 = new Agente();
	private Agente a2 = new Agente();
	private List<FuncionObjetivo> funciones = new ArrayList<>();
	public MazoTopTrumpTriObjetivo() {
	    setNumberOfVariables(cartas * categorias);
	    setNumberOfObjectives(objetivos);
	    setName("MazoTopTrumpsTriObjetivo");
	    
	    // Definimos aquí las funciones que queremos usar.
	    FuncionObjetivo f1 = new FuncionMinSameBots(RG, a1, a2);
	    FuncionObjetivo f2 = new FuncionMaxNotSameBots(RG, db1 ,a1);
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
		
		f[0] = funciones.get(0).funcion(solution);
		f[1] = funciones.get(1).funcion(solution);
		f[2] = funciones.get(2).funcion(solution);
	    

	    solution.objectives()[0] = f[0];
		solution.objectives()[1] = f[1];
		solution.objectives()[2] = f[2];
	 
	    return solution ;
	}
	

		
	}
	




