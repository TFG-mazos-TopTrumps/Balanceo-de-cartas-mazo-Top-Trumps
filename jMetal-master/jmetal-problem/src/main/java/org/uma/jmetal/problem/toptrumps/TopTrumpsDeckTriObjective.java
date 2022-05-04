package org.uma.jmetal.problem.toptrumps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.problem.toptrumps.objectivefunctions.FuncionMaxNotSameBots;
import org.uma.jmetal.problem.toptrumps.objectivefunctions.FuncionMinSameBots;
import org.uma.jmetal.problem.toptrumps.objectivefunctions.ObjectiveFunction;
import org.uma.jmetal.problem.toptrumps.objectivefunctions.FuncionfD;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.solution.doublesolution.impl.DefaultDoubleSolution;

@SuppressWarnings("serial")
public class TopTrumpsDeckTriObjective extends AbstractDoubleProblem {

	private final int cards = 4;
	private final int categories = 4;
	private final int maxRounds = 10;
	private final int RG = 3; // Número de simulaciones que se efectúan.
	private final int objectives = 3;
	private final double lowerLimit=0.0;
	private final double upperLimit=10.0;
	Random random=new Random(1979L);

	private List<ObjectiveFunction> functions = new ArrayList<>();
	
	public TopTrumpsDeckTriObjective() {
	    // Definimos aquí las funciones que queremos usar.
	    ObjectiveFunction f1 = new FuncionMinSameBots(RG,maxRounds,random);
	    ObjectiveFunction f2 = new FuncionMaxNotSameBots(RG,maxRounds,random);
	    ObjectiveFunction f3 = new FuncionfD(random,cards,categories);
	    

		// Se añaden a la lista de funciones.
	    this.functions.add(f1);
	    this.functions.add(f2);
	    this.functions.add(f3);

		setNumberOfVariables(cards * categories);
	    setNumberOfObjectives(functions.size());
	    setName("TopTrumpsDeckTriObjective");
	    
	    
	    
	    
	    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(this.lowerLimit);
	      upperLimit.add(this.upperLimit);
	    }

	    setVariableBounds(lowerLimit, upperLimit);
	}
	
	public DoubleSolution evaluate(DoubleSolution solution) {
		double[] f = new double[solution.objectives().length];

		
		// Añadimos las funciones para cada objetivo:		
		for(int i=0;i<functions.size();i++) {
			f[i] = functions.get(i).evaluate(solution);
			solution.objectives()[i] = f[i];
		}
	 
	    return solution ;
	}
	
	@Override
	public DoubleSolution createSolution() {
	    DoubleSolution result=super.createSolution();
	    result.attributes().put("categories", categories);
	    return result;
	}
	

		
	}
	




