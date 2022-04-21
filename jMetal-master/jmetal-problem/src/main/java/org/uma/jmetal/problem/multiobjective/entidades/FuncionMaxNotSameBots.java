package org.uma.jmetal.problem.multiobjective.entidades;

import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class FuncionMaxNotSameBots extends FuncionObjetivo{
	
	private DummyBot b1;
	private Agente b2;
	


	public double funcion(DoubleSolution solution) {
		double res = 0.0;
		double sum = 0.0;
		for(int i=0;i<RG;i++) {
			
			Simulacion partida = new Simulacion();
			partida.setP0(b1);
			partida.setP4(b2);
			partida.partida(solution.variables());
			
			int cartasB1 = partida.getP0().getBaza().size();
			int cartasB2 = partida.getP4().getBaza().size();
			
			int diferenciaPuntuaciones = Math.abs(cartasB2 - cartasB1);
			sum += diferenciaPuntuaciones * 1.0;
		}
		res = (1.00/RG) * sum;
		return res;
	}
	
	

}
