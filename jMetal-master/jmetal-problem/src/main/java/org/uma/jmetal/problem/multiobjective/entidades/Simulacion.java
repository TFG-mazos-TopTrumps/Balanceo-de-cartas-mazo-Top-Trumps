package org.uma.jmetal.problem.multiobjective.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulacion implements Partida {
	private Bot p4;
	private Bot p0;
	private List<Carta> mazo = new ArrayList<>();
	private List<Carta> cartasPorJugar = new ArrayList<>();
	private List<Integer> diferenciaRondas = new ArrayList<>();
	private int numeroRondasJugadas = 0;
	private int categoriaEnJuego;
	
	
	public Simulacion() {
		
		
	}
	
	public void repartirCartas(List<Double> valoresCartas) {
		
		// Completamos las cartas del mazo con las variables.
		
		for(int i=0; i < valoresCartas.size()/4; i++) {
			
			Double c1 = valoresCartas.get(i * 4);
	    	Double c2 = valoresCartas.get((i * 4) + 1);
	    	Double c3 = valoresCartas.get((i * 4) + 2);
	    	Double c4 = valoresCartas.get((i * 4) + 3);
	    	
	    	Carta c = new Carta();
	    	c.getCategorias().put(0, c1);
	    	c.getCategorias().put(1,c2);
	    	c.getCategorias().put(2,c3);
	    	c.getCategorias().put(3,c4);
	    	
	    	mazo.add(c);
	    	cartasPorJugar.add(c);
		}
		
		Collections.shuffle(mazo); // Se barajan las cartas.
		int i = 0;
		for(Carta c : mazo) {
			if(i % 2 == 0) {
				p4.getBaza().add(c);
				
			} else {
				p0.getBaza().add(c);
			}
			
			i++;
			
		}
		
	}
	
	
	public void ronda() {
		
		if(numeroRondasJugadas==0) {
			double turnoInicial = Math.random();
			
			if(turnoInicial <= 0.5) {
				p0.setTurno(true);
			} else {
				p4.setTurno(true);
			}
			
		}
		
	
		if(p4.isTurno()) {
			categoriaEnJuego = p4.elegirCategoria();
		
		} else {
			categoriaEnJuego = p0.elegirCategoria();
			
		}
		
		Carta cartaP4 = p4.jugar(categoriaEnJuego);
		Carta cartaP0 = p0.jugar(categoriaEnJuego);
		
		cartasPorJugar.remove(cartaP4);
		cartasPorJugar.remove(cartaP0);
		
		
		Double valorP4 = cartaP4.getCategorias().get(categoriaEnJuego);
		Double valorP0 = cartaP0.getCategorias().get(categoriaEnJuego);
		List<Carta> truco = new ArrayList<>();
		
		
		if(valorP4 > valorP0) {
			
			truco.add(cartaP0);
			p4.getCartasJugadas().add(cartaP0);
			p4.setTurno(true);
			p4.recogerCartas(truco);
			
		} else if(valorP0 > valorP4) {
			truco.add(cartaP4);
			p0.getCartasJugadas().add(cartaP4);
			p0.setTurno(true);
			p0.recogerCartas(truco);
			
		} 
		
		Integer diferenciaRonda = Math.abs(p4.getBaza().size() - p0.getBaza().size());
		diferenciaRondas.add(diferenciaRonda);
		numeroRondasJugadas++;
		
		// Duda: qué ocurre en caso de empate de los valores.
		
		
	}
	
	public boolean finaliza() { 
		
		boolean res = (cartasPorJugar.isEmpty()) ? true : false;
		return res;
	}

	public void partida(List<Double> valoresCartas) {
		
		
		while(true) {
		
		if(numeroRondasJugadas==0) {
			
			repartirCartas(valoresCartas);
			
		}
			
		ronda();
		
		
		if(finaliza()) {
			break;
		}
		
		}
	}
	public Bot getP4() {
		return p4;
	}

	public void setP4(Bot p4) {
		this.p4 = p4;
	}

	public Bot getP0() {
		return p0;
	}

	public void setP0(Bot p0) {
		this.p0 = p0;
	}

	public List<Carta> getMazo() {
		return mazo;
	}

	public void setMazo(List<Carta> mazo) {
		this.mazo = mazo;
	}


	public int getCategoriaEnJuego() {
		return categoriaEnJuego;
	}

	public void setCategoriaEnJuego(int categoriaEnJuego) {
		this.categoriaEnJuego = categoriaEnJuego;
	}
	
	public List<Carta> getCartasPorJugar() {
		return cartasPorJugar;
	}

	public void setCartasPorJugar(List<Carta> cartasPorJugar) {
		this.cartasPorJugar = cartasPorJugar;
	}

	public int getNumeroRondasJugadas() {
		return numeroRondasJugadas;
	}

	public void setNumeroRondasJugadas(int numeroRondasJugadas) {
		this.numeroRondasJugadas = numeroRondasJugadas;
	}

	public List<Integer> getDiferenciaRondas() {
		return diferenciaRondas;
	}

	public void setDiferenciaRondas(List<Integer> diferenciaRondas) {
		this.diferenciaRondas = diferenciaRondas;
	}



	
	

}
