package org.uma.jmetal.problem.toptrumps.objectivefunctions;

import java.util.List;
import java.util.Random;

import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public abstract class ObjectiveFunction {
	
	
	protected Random random;
	
	
	public ObjectiveFunction(Random random){
		this.random=random;
	}
	
	
	/**
	 * Método abstracto que define que hace una función objetivo.
	 * @param solution que se evalúa.
	 * @return el valor resultante del cáculo de la función.
	 */
	public abstract double evaluate(DoubleSolution solution);

}
