package org.uma.jmetal.problem.multiobjective.entidades;

import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public abstract class FuncionObjetivo {
	
	/**
	 * Método abstracto que define que hace una función objetivo.
	 * @param solution que se evalúa.
	 * @return el valor resultante del cáculo de la función.
	 */
	public abstract double funcion(DoubleSolution solution);

}
