package org.uma.jmetal.problem.multiobjective.entidades;

import java.util.List;

public class DummyBot implements Bot{

	private boolean turno;
	private String nombre;
	private List<Carta> baza;
	private List<Carta> cartasJugadas;
	


	public int elegirCategoria() {
		int res = (int) Math.round(Math.random() * 4) * 1;
		 
		return res;
	}

	public void recogerCartas(List<Carta> cartasObtenidas) {
		for(Carta c : cartasObtenidas) {
			baza.add(c);
		}
	}

	public Carta jugar(int categoria) {
		
		Carta c = new Carta();
		c = baza.get(categoria); 
		c.setJugada(true);
		cartasJugadas.add(c);
		return c;
		
	}


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
