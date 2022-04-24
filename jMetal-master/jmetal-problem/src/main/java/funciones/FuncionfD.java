package funciones;

import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class FuncionfD extends FuncionObjetivo {

	private int cartas;
	private int categorias;
	
	public double funcion(DoubleSolution solution) {
		double fD = 0;
		// Función fitness fD:
	    
	    double sum = 0.0;
	    for(int k=0;k<cartas;k++) {
	    	double lk1 = solution.variables().get(k * categorias);
	    	double lk2 = solution.variables().get((k * categorias) +1);
	    	double lk3 = solution.variables().get((k * categorias) +2);
	    	double lk4 = solution.variables().get((k * categorias) +3);
	    	
	    	
	    	for(int i=0;i<cartas;i++) {
	    		
	    		if(i != k) {
	    		double li1 = solution.variables().get(i * categorias);
		    	double li2 = solution.variables().get((i * categorias) +1);
		    	double li3 = solution.variables().get((i * categorias)+2);
		    	double li4 = solution.variables().get((i * categorias)+3);
		    	
		    	boolean comparacion = (lk1 > li1) && (lk2 > li2) && (lk3 > li3) && (lk4 > li4);
	    		double condicionResta = (comparacion) ? 1.00:0.00;
	    		sum += 1 - condicionResta;
	    		}
	    		
	    		}
	    	}
	    fD = (-1.00)*(1/cartas) * sum;
	    return fD;
	}
	
	

}
