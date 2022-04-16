package org.uma.jmetal.problem.multiobjective.entidades;

import java.util.List;

public class Carta {
	
	boolean jugada;
	private List<Double> valores;
	
	public boolean isJugada() {
		return jugada;
	}
	public void setJugada(boolean jugada) {
		this.jugada = jugada;
	}
	public List<Double> getValores() {
		return valores;
	}
	public void setValores(List<Double> valores) {
		this.valores = valores;
	}

}
