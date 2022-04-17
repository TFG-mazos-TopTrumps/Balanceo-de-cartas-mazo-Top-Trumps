package org.uma.jmetal.problem.multiobjective.entidades;

import java.util.List;

public interface Partida {
	
	void repartirCartas(List<Double> valoresCartas);
	boolean finaliza();
	void partida(List<Double> valoresCartas);
	
	void ronda();

}
