package org.uma.jmetal.problem.multiobjective.entidades;

import java.util.ArrayList;
import java.util.List;

public class Simulacion implements Partida {
	private Agente p4;
	private Agente p0;
	private List<Carta> totalCartas;
	private List<Carta> mazo;
	private int tc;
	private int categoriaEnJuego;
	
	public Simulacion() {
		this.setTotalCartas(mazo);
	}
	public void ronda() {
		
	
		if(p4.isTurno()) {
			categoriaEnJuego = p4.elegirCategoria();
			tc++;
		} else {
			categoriaEnJuego = p0.elegirCategoria();
			tc++;
		}
		
		Carta cartaP4 = p4.jugar(categoriaEnJuego);
		Carta cartaP0 = p0.jugar(categoriaEnJuego);
		
		
		Double valorP4 = cartaP4.getValores().get(categoriaEnJuego);
		Double valorP0 = cartaP0.getValores().get(categoriaEnJuego);
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
		
		// Duda: qué ocurre en caso de empate de los valores.
		
		
		
	}
	
	public boolean finaliza() { 
		boolean res = true;
		
		for(Carta c : totalCartas) {
			
			if(!c.jugada) {
				res = false;
				break;
			} 
			
		}
		
		return res;
	}

	public void partida() {
		
		boolean condicionParada = finaliza();
		while(true) {
			
		ronda();
		
		
		if(condicionParada) {
			break;
		}
		
		}
		
		
	}
	public Agente getP4() {
		return p4;
	}

	public void setP4(Agente p4) {
		this.p4 = p4;
	}

	public Agente getP0() {
		return p0;
	}

	public void setP0(Agente p0) {
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

	public int getTc() {
		return tc;
	}

	public void setTc(int tc) {
		this.tc = tc;
	}
	public List<Carta> getTotalCartas() {
		return totalCartas;
	}
	public void setTotalCartas(List<Carta> totalCartas) {
		this.totalCartas = totalCartas;
	} 
	

}
