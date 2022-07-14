package org.uma.jmetal.lab.experiment.studies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



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
	                    .setExperimentBaseDirectory(experimentBaseDirectory)
	                    .setOutputParetoFrontFileName("FUN")
	                    .setOutputParetoSetFileName("VAR")
	                    .setReferenceFrontDirectory(experimentBaseDirectory + "/MazoMinimoTopTrumpsSOStudy/referenceFronts")
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
	    new GenerateReferenceParetoSetAndFrontFromDoubleSolutions(experiment).run();
	    new ComputeQualityIndicators<>(experiment).run();
	    new GenerateLatexTablesWithStatistics(experiment).run();
	    //new GenerateFriedmanHolmTestTables<>(experiment).run();
	    new GenerateWilcoxonTestTablesWithR<>(experiment).run();
	    new GenerateBoxplotsWithR<>(experiment).setRows(2).setColumns(3).run();
	    //new GenerateHtmlPages<>(experiment, StudyVisualizer.TYPE_OF_FRONT_TO_SHOW.MEDIAN).run() ;
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
