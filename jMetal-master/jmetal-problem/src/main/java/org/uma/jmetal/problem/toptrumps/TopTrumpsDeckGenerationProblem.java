package org.uma.jmetal.problem.toptrumps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.problem.toptrumps.objectivefunctions.FunctionMaxNotSameBots;
import org.uma.jmetal.problem.toptrumps.objectivefunctions.FunctionMinSameBots;
import org.uma.jmetal.problem.toptrumps.objectivefunctions.ObjectiveFunction;
import org.uma.jmetal.problem.toptrumps.objectivefunctions.DominatedCards;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.solution.doublesolution.impl.DefaultDoubleSolution;

@SuppressWarnings("serial")
public class TopTrumpsDeckGenerationProblem extends AbstractDoubleProblem {

	private int cards = 30;
	private int categories = 6;
	private int maxRounds = 15;
	private int players = 2;
	private int games = 30; // Número de simulaciones que se efectúan.
	private int nCardsToChooseFrom = 3; // Número de cartas de entre las que se escoge 
	private final int objectives = 3;
	private final double lowerLimit = 0.0;
	
	private final double upperLimit = 100.0;
	Random random = new Random(1979L);

	private List<ObjectiveFunction> functions = new ArrayList<>();

	public TopTrumpsDeckGenerationProblem(int cards, int categories, int games, int maxRounds, int players, int nCardsToChooseFrom) {		
		this.games = games;
		this.cards = cards;
		this.categories = categories;
		this.maxRounds = maxRounds;
		this.players = players;
		this.nCardsToChooseFrom=nCardsToChooseFrom;
		initialize();		
	}
	
	private void initialize() {
		// Definimos aquí las funciones que queremos usar.
		ObjectiveFunction f1 = new FunctionMinSameBots(games, maxRounds, random, players,nCardsToChooseFrom);
		ObjectiveFunction f2 = new FunctionMaxNotSameBots(games, maxRounds, random,players,nCardsToChooseFrom);
		ObjectiveFunction f3 = new DominatedCards(random, cards, categories);

		// Se añaden a la lista de funciones.
		this.functions.add(f1);
		this.functions.add(f2);
		this.functions.add(f3);

		setNumberOfVariables(cards * categories);
		setNumberOfObjectives(functions.size());
		setName("TopTrumpsDeckTriObjective");

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());

		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(this.lowerLimit);
			upperLimit.add(this.upperLimit);
		}

		setVariableBounds(lowerLimit, upperLimit);
	}

	public TopTrumpsDeckGenerationProblem() {
		initialize();
	}

	public DoubleSolution evaluate(DoubleSolution solution) {
		double[] f = new double[solution.objectives().length];

		// Añadimos las funciones para cada objetivo:
		for (int i = 0; i < functions.size(); i++) {
			f[i] = functions.get(i).evaluate(solution);
			solution.objectives()[i] = f[i];
		}

		return solution;
	}

	@Override
	public DoubleSolution createSolution() {
		DoubleSolution result = super.createSolution();
		result.attributes().put("categories", categories);
		return result;
	}
	
	public void setProblemName(String name) {
		setName(name);
	}

}
