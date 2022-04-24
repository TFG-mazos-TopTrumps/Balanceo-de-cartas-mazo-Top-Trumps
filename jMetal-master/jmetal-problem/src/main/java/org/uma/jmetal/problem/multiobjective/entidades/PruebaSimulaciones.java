package org.uma.jmetal.problem.multiobjective.entidades;

import java.util.ArrayList;
import java.util.List;

public class PruebaSimulaciones {
	
	public static void main(String[] args) {
		
		List<Double> valoresCartas = new ArrayList<>();
		valoresCartas.add(8.0);
		valoresCartas.add(4.0);
		valoresCartas.add(6.0);
		valoresCartas.add(2.0);
		valoresCartas.add(9.0);
		valoresCartas.add(3.0);
		valoresCartas.add(5.0);
		valoresCartas.add(8.0);
		
		int cartas = 2;
		int categorias = 4;
		Bot b1 = new Agente();
		Bot b2 = new DummyBot();
		
		Simulacion s = new Simulacion();
		s.setP0(b2);
		s.setP4(b1);
		s.partida(valoresCartas);
		
		System.out.println("Número de rondas jugadas: " + s.getNumeroRondasJugadas());
		System.out.println("Número de cartas por jugar: " + s.getCartasPorJugar());
		System.out.println("Número de cartas en el mazo: + " + s.getMazo().size());
	}

}
