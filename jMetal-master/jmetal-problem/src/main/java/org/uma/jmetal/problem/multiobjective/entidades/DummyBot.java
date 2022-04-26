package org.uma.jmetal.problem.multiobjective.entidades;

import java.util.List;

public class DummyBot extends Bot{

	public int elegirCategoria() {
		int res = (int) Math.round(Math.random() * 3) * 1;
		 
		return res;
	}

	public void recogerCartas(List<Carta> cartasObtenidas) {
		for(Carta c : cartasObtenidas) {
			baza.add(c);
		}
	}

	public Carta jugar(int categoria) {
		int i = (int) Math.round(Math.random() * (baza.size() - 1));
		Carta c = baza.get(i);
		cartasJugadas.add(c);
		return c;
		
	}



}
