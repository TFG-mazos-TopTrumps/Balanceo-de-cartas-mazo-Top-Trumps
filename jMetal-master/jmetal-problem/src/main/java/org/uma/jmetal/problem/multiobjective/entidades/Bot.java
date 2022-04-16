package org.uma.jmetal.problem.multiobjective.entidades;

import java.util.List;

public interface Bot {
	
	/**
	 * Método empleado para que, si es turno, elija la categoria más conveniente para jugar con base a las cartas de la que dispone en su
	 * baza actual.
	 * @return indice de la categoria con valor más alto.
	 */
	
	int elegirCategoria();

	/**
	 * Método usado para que el bot, si gana la ronda, sume a su mazo las cartas jugadas.
	 * @param cartasObtenidas representa las cartas que obtiene cuando gana una ronda.
	 */
	
	void recogerCartas(List<Carta> cartasObtenidas);
	
	/**
	 * Método empleado para que el agente elija una carta con la que jugar la ronda 
	 * según la categoría seleccionada.
	 * @return el valor de la carta para la categoría que se juega en la ronda.
	 */
	Carta jugar(int categoria);
}
