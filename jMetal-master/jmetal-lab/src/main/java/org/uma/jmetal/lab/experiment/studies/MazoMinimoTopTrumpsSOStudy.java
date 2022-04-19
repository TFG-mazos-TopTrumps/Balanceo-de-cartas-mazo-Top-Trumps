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
import org.uma.jmetal.lab.experiment.component.impl.GenerateWilcoxonTestTablesWithR;
import org.uma.jmetal.lab.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.lab.experiment.util.ExperimentProblem;
import org.uma.jmetal.lab.visualization.StudyVisualizer;
import org.uma.jmetal.operator.crossover.impl.SBXCrossover;
import org.uma.jmetal.operator.mutation.impl.PolynomialMutation;
import org.uma.jmetal.problem.multiobjective.MazoTopTrumpsFB;
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

public class MazoMinimoTopTrumpsSOStudy {

	private static final int INDEPENDENT_RUNS = 10;

	public static void main(String[] args) throws IOException {
	    if (args.length != 1) {
	      throw new JMetalException("Missing argument: experimentBaseDirectory");
	    }
	    String experimentBaseDirectory = args[0];
	    
	    List<ExperimentProblem<DoubleSolution>> problemList = List.of(
	    		
	            new ExperimentProblem<>(new MazoTopTrumpsSO())); // fD
	    
	    List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithmList =
	            configureAlgorithmList(problemList);
	    
	    Experiment<DoubleSolution, List<DoubleSolution>> experiment =
	            new ExperimentBuilder<DoubleSolution, List<DoubleSolution>>("MazoMinimoTopTrumpsSOStudy")
	                    .setAlgorithmList(algorithmList)
	                    .setProblemList(problemList)
	                    .setReferenceFrontDirectory("resources/referenceFrontsCSV")
	                    .setExperimentBaseDirectory(experimentBaseDirectory)
	                    .setOutputParetoFrontFileName("FUN")
	                    .setOutputParetoSetFileName("VAR")
	                    .setIndicatorList(List.of(
	                    	
	                            new Epsilon(),
	                            new Spread(),
	                            new GenerationalDistance(),
	                            new PISAHypervolume(),
	                            new NormalizedHypervolume(),
	                            new InvertedGenerationalDistance(),
	                            new InvertedGenerationalDistancePlus()))
	                    .setIndependentRuns(INDEPENDENT_RUNS)
	                    .setNumberOfCores(4)
	                    .build();

	    new ExecuteAlgorithms<>(experiment).run();
	    new ComputeQualityIndicators<>(experiment).run();
	    new GenerateLatexTablesWithStatistics(experiment).run();
	    new GenerateFriedmanHolmTestTables<>(experiment).run();
	    new GenerateWilcoxonTestTablesWithR<>(experiment).run();
	    new GenerateBoxplotsWithR<>(experiment).setRows(2).setColumns(3).run();
	    new GenerateHtmlPages<>(experiment, StudyVisualizer.TYPE_OF_FRONT_TO_SHOW.MEDIAN).run() ;
	  }
	
	static List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> configureAlgorithmList(
	          List<ExperimentProblem<DoubleSolution>> problemList) {
	    List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithms = new ArrayList<>();
	    for (int run = 0; run < INDEPENDENT_RUNS; run++) {
	      for (var experimentProblem : problemList) {
	        
	    	  
	    	  Algorithm<List<DoubleSolution>> algorithm = new NSGAIIBuilder<DoubleSolution>(
	                  experimentProblem.getProblem(),
	                  new SBXCrossover(1.0, 20.0),
	                  new PolynomialMutation(1.0 / experimentProblem.getProblem().getNumberOfVariables(),
	                          20.0),
	                  100)
	                  .build();
	       algorithms.add(new ExperimentAlgorithm<>(algorithm, experimentProblem, run));
	        
	      }
	      
	      
	      }
	    return algorithms;
	  }


	    
	}
