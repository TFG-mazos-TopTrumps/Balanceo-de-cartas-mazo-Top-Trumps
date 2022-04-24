package org.uma.jmetal.problem.multiobjective.entidades;

import java.util.ArrayList;
import java.util.List;

public abstract class Bot {
	
	protected boolean turno;
	protected String nombre;
	protected List<Carta> baza = new ArrayList<>();
	protected List<Carta> cartasJugadas = new ArrayList<>();

	/**
	 * Método empleado para que, si es turno, elija la categoria más conveniente para jugar con base a las cartas de la que dispone en su
	 * baza actual.
	 * @return indice de la categoria con valor más alto.
	 */
	
	public abstract int elegirCategoria();

	/**
	 * Método usado para que el bot, si gana la ronda, sume a su mazo las cartas jugadas.
	 * @param cartasObtenidas representa las cartas que obtiene cuando gana una ronda.
	 */
	
	public abstract void recogerCartas(List<Carta> cartasObtenidas);
	
	/**
	 * Método empleado para que el agente elija una carta con la que jugar la ronda 
	 * según la categoría seleccionada.
	 * @return el valor de la carta para la categoría que se juega en la ronda.
	 */
	public abstract Carta jugar(int categoria);
	

	public boolean isTurno() {
		return turno;
	}

	public void setTurno(boolean turno) {
		this.turno = turno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Carta> getBaza() {
		return baza;
	}

	public void setBaza(List<Carta> baza) {
		this.baza = baza;
	}

	public List<Carta> getCartasJugadas() {
		return cartasJugadas;
	}

	public void setCartasJugadas(List<Carta> cartasJugadas) {
		this.cartasJugadas = cartasJugadas;
	}

}
