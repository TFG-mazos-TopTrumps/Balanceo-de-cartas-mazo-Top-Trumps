package org.uma.jmetal.problem.multiobjective.entidades;


import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

public class Carta {
	
	private Map<Integer,Double> categorias = new HashedMap<>();
	
	public Carta() {
		
	}
	public Map<Integer,Double> getCategorias() {
		return categorias;
	}
	public void setCategorias(Map<Integer,Double> categorias) {
		this.categorias = categorias;
	}

}
