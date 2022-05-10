package org.uma.jmetal.problem.toptrumps.objectivefunctions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.uma.jmetal.solution.doublesolution.impl.DefaultDoubleSolution;
import org.uma.jmetal.util.bounds.Bounds;

class FunctionMaxNotSameBotsTest {

	@Test
	void test() {
		
			Random random=new Random();
			FunctionMaxNotSameBots sut=new  FunctionMaxNotSameBots(10,	 // Number of games to be played
															 2,	 // Maximum number of rounds per game
															 random, // Random number generator used
															 2,		 // Number of players
															 2);	 // Number of topmost cards among which players choose
			
			List<Double> cardsValuesList=Arrays.asList(
					1.0,2.0,3.0,4.0,
					4.0,3.0,2.0,1.0,
					2.0,3.0,4.0,1.0,
					3.0,4.0,1.0,2.0		);
			
			List<Bounds<Double>> bounds=new ArrayList<Bounds<Double>>();					
			bounds.add(Bounds.create(1.0, 4.0));
			bounds.add(Bounds.create(1.0, 4.0));
			bounds.add(Bounds.create(1.0, 4.0));
			bounds.add(Bounds.create(1.0, 4.0));
			
			DefaultDoubleSolution solution=new DefaultDoubleSolution(1,0,bounds);
			solution.variables().clear();
			solution.variables().addAll(cardsValuesList);
			solution.attributes().put("categories", 4);
			Double actualValue=sut.evaluate(solution);
			Double expectedMaxBound=1.0;
			Double expectedMinBound=-1.0;
			assertTrue(actualValue < expectedMaxBound);
			assertTrue(actualValue >= expectedMinBound);
	}
	
	
	@Test
	void test2() {
		
			Random random=new Random();
			FunctionMaxNotSameBots sut=new  FunctionMaxNotSameBots(100,	 // Number of games to be played
															 2,	 // Maximum number of rounds per game
															 random, // Random number generator used
															 2,		 // Number of players
															 2);	 // Number of topmost cards among which players choose
			
			List<Double> cardsValuesList=Arrays.asList(
					1.0,2.0,3.0,4.0,
					4.0,3.0,2.0,1.0,
					2.0,3.0,4.0,1.0,
					3.0,4.0,1.0,2.0		);
			
			List<Bounds<Double>> bounds=new ArrayList<Bounds<Double>>();					
			bounds.add(Bounds.create(1.0, 4.0));
			bounds.add(Bounds.create(1.0, 4.0));
			bounds.add(Bounds.create(1.0, 4.0));
			bounds.add(Bounds.create(1.0, 4.0));
			
			DefaultDoubleSolution solution=new DefaultDoubleSolution(1,0,bounds);
			solution.variables().clear();
			solution.variables().addAll(cardsValuesList);
			solution.attributes().put("categories", 4);
			Double actualValue=sut.evaluate(solution);
			Double expectedMaxBound=1.0;
			Double expectedMinBound=-1.0;
			assertTrue(actualValue < expectedMaxBound);
			assertTrue(actualValue >= expectedMinBound);
			// Since we run 100 games... greedy bots should win random ones:
			assertTrue(actualValue < -0.0);
	}

}
