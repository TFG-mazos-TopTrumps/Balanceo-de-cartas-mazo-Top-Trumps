package funciones;

import org.uma.jmetal.problem.multiobjective.entidades.Agente;
import org.uma.jmetal.problem.multiobjective.entidades.DummyBot;
import org.uma.jmetal.problem.multiobjective.entidades.Simulacion;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class FuncionMaxNotSameBots extends FuncionObjetivo{	
	
	private DummyBot b1;
	private Agente b2;
	

	public FuncionMaxNotSameBots(int RG) {
		this.RG = RG;
	}

	public double funcion(DoubleSolution solution) {
		double res = 0.0;
		double sum = 0.0;
		for(int i=0;i<RG;i++) {
			
			Simulacion partida = new Simulacion();
			partida.setP0(b1);
			partida.setP4(b2);
			partida.partida(solution.variables());
			
			for(int diferenciaPuntuaciones : partida.getDiferenciaRondas()) {
				
				sum += diferenciaPuntuaciones * 1.0;
				
			}
			
		}
		res = (1.00/RG) * sum;
		return res;
	}
	
	

}
