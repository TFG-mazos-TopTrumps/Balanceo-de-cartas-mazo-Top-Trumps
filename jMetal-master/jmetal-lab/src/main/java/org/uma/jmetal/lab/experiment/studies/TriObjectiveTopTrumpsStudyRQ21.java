package org.uma.jmetal.lab.experiment.studies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.lab.experiment.Experiment;
import org.uma.jmetal.lab.experiment.ExperimentBuilder;
import org.uma.jmetal.lab.experiment.component.impl.ComputeQualityIndicators;
import org.uma.jmetal.lab.experiment.component.impl.ExecuteAlgorithms;
import org.uma.jmetal.lab.experiment.component.impl.GenerateBoxplotsWithR;
import org.uma.jmetal.lab.experiment.component.impl.GenerateFriedmanHolmTestTables;
import org.uma.jmetal.lab.experiment.component.impl.GenerateHtmlPages;
import org.uma.jmetal.lab.experiment.component.impl.GenerateLatexTablesWithStatistics;
import org.uma.jmetal.lab.experiment.component.impl.GenerateReferenceParetoSetAndFrontFromDoubleSolutions;
import org.uma.jmetal.lab.experiment.component.impl.GenerateWilcoxonTestTablesWithR;
import org.uma.jmetal.lab.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.lab.experiment.util.ExperimentProblem;
import org.uma.jmetal.lab.visualization.StudyVisualizer;
import org.uma.jmetal.operator.crossover.impl.SBXCrossover;
import org.uma.jmetal.operator.mutation.impl.PolynomialMutation;
import org.uma.jmetal.problem.toptrumps.TopTrumpsDeckGenerationProblem;
import org.uma.jmetal.problem.toptrumps.MazoTopTrumpsFB;
import org.uma.jmetal.problem.singleobjective.MazoTopTrumpsSO;
import org.uma.jmetal.qualityindicator.impl.Epsilon;
import org.uma.jmetal.qualityindicator.impl.GenerationalDistance;
import org.uma.jmetal.qualityindicator.impl.InvertedGenerationalDistance;
import org.uma.jmetal.qualityindicator.impl.InvertedGenerationalDistancePlus;
import org.uma.jmetal.qualityindicator.impl.NormalizedHypervolume;
import org.uma.jmetal.qualityindicator.impl.Spread;
import org.uma.jmetal.qualityindicator.impl.hypervolume.impl.PISAHypervolume;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.util.errorchecking.JMetalException;

public class TriObjectiveTopTrumpsStudyRQ21 extends TriObjectiveTopTrumpsStudy {

	
	private int categories = 6;	
	private int players = 2;
	private int games = 30; // Número de simulaciones que se efectúan.
	private int nCardsToChooseFrom = 3; // Número de cartas de entre las que se escoge
	
	public TriObjectiveTopTrumpsStudyRQ21(){
		super(10,TriObjectiveTopTrumpsStudy.DEFAULT_BASE_DIRECTORY,"RQ21-ImpactOfTheNumberOfCardsPerDeck");
	}
	
	@Override
	protected List<ExperimentProblem<DoubleSolution>> generateProblemList() {
		List<ExperimentProblem<DoubleSolution>> problems=new ArrayList<ExperimentProblem<DoubleSolution>>(6);
		ExperimentProblem expProblem=null;
		TopTrumpsDeckGenerationProblem problem=null;
		int ncards=0;
		int nrounds=0;
		for(int i=2;i<8;i++) {
			ncards=(int) Math.pow(players, i);
			nrounds=ncards/players;
			problem=new TopTrumpsDeckGenerationProblem(ncards,categories,games,nrounds,players,nCardsToChooseFrom);
			problem.setProblemName(problem.getName()+"-"+String.valueOf(ncards));
			expProblem=new ExperimentProblem<>(
						problem,
						String.valueOf(ncards)
					);
			
			problems.add(expProblem);			
		}
		return problems;	    
	}
	
	public static void main(String[] args) throws IOException {
		TriObjectiveTopTrumpsStudy study=new TriObjectiveTopTrumpsStudyRQ21();
		study.run(args);
	}
							
}