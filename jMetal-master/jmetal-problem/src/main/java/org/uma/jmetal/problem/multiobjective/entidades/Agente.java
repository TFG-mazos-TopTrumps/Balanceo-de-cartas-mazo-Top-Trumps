package org.uma.jmetal.problem.multiobjective.entidades;

import java.util.ArrayList;
import java.util.List;

public class Agente implements Bot {
	private boolean turno;
	private String nombre;
	private List<Carta> baza;
	private List<Carta> cartasJugadas;
	
	public int elegirCategoria() {
		
		int res = 0;
		List<Double> valores = new ArrayList<>();
		
		for (int n = 0; n < baza.size(); n++) {
			
			Carta c = baza.get(n);
			
			

			for(int i = 0; i<c.getValores().size(); i++) {
				
				valores.add(c.getValores().get(i));
				
				
			}
			
		}
		
		Double valorActual = valores.get(0);
		int indice = 0;
		for(int m = 1; m < valores.size(); m++) {
			Double valorNuevo =  valores.get(m);
			
			if(valorNuevo > valorActual) {
				valorActual = valorNuevo;
				indice = m;
				
			}
		}
		
		res = indice % 4;
		return res;
		
	}
	public void recogerCartas(List<Carta> cartas) {
		
		for(Carta c : cartas) {
			baza.add(c);
		}
		
	}
	
	public Carta jugar(int categoria) {
		
		Carta c = new Carta();
		c = baza.get(0); // Cogemos la primera para ir comparándola con el resto.
		// Comprobamos de las cartas disponibles en la baza cual es la que tiene un valor más alto para esa categoría.
		for(int i=1; i<baza.size(); i++) {
			Carta nueva = baza.get(i);
			double valorActual = c.getValores().get(categoria);
			double valorNuevo = nueva.getValores().get(categoria);
			if(valorNuevo > valorActual) {
				c = nueva;
			}
		}
		
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
