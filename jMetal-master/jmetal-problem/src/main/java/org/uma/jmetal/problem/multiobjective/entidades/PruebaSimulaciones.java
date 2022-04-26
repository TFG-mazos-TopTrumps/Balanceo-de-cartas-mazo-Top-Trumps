package org.uma.jmetal.problem.multiobjective.entidades;

import java.util.ArrayList;
import java.util.List;

import funciones.FuncionMinSameBots;

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
		valoresCartas.add(4.0);
		valoresCartas.add(7.0);
		valoresCartas.add(3.0);
		valoresCartas.add(9.0);
		valoresCartas.add(5.0);
		valoresCartas.add(1.0);
		valoresCartas.add(9.0);
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
		
		for(int i=0; i<categorias; i++) {
		System.out.println("Valor " + i + " de la carta " + s.getMazo().get(1).getCategorias().get(i));
		
		}
		
		System.out.println("Categoria elegida por el bot: " + b2.elegirCategoria());
		
//		FuncionMinSameBots f1 = new FuncionMinSameBots(3);
//		System.out.println("Resultado fMin: " + f1.funcion(null));
	}

}
