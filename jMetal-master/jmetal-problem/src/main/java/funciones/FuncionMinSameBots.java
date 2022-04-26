package funciones;

import org.uma.jmetal.problem.multiobjective.entidades.Bot;
import org.uma.jmetal.problem.multiobjective.entidades.Simulacion;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class FuncionMinSameBots extends FuncionObjetivo{

	private int RG;
	private Bot b1;
	private Bot b2;
	
	public FuncionMinSameBots(int RG, Bot b1, Bot b2) {
		this.RG = RG;
		this.b1 = b1;
		this.b2 = b2;
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
		res = (-1.00) * (1.00/RG) * sum;
		return res;
	}
	
	

}
