package org.uma.jmetal.problem.toptrumps.objectivefunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.apache.commons.lang3.tuple.Pair;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.solution.doublesolution.impl.DefaultDoubleSolution;
import org.uma.jmetal.util.bounds.Bounds;

public class DominatedCardsTest {
	
	@Test
	public void testNonDominatedCards(){
		int categories=4;
		int cards=4;
		List<Double> cardsValuesList=Arrays.asList(
			1.0,2.0,3.0,4.0,
			4.0,3.0,2.0,1.0,
			2.0,3.0,4.0,1.0,
			3.0,4.0,1.0,2.0		);
		
		DominatedCards dc=new DominatedCards(cards, categories);
		List<Bounds<Double>> bounds=new ArrayList<Bounds<Double>>();
		for(int i=0;i<cardsValuesList.size();i++)
			bounds.add(Bounds.create(1.0, 4.0));		
		DefaultDoubleSolution solution=new DefaultDoubleSolution(1,0,bounds);
		solution.variables().clear();		
		solution.variables().addAll(cardsValuesList);
		Double actualValue=dc.evaluate(solution);
		Double expectedValue=-0.0;
		assertEquals(expectedValue,actualValue);
	}
	
	@Test
	public void testDominatedCards(){
		int categories=4;
		int cards=4;
		List<Double> cardsValuesList=Arrays.asList(
			4.0,4.0,4.0,4.0,
			3.0,1.0,2.0,1.0,
			2.0,3.0,3.0,1.0,
			3.0,2.0,1.0,3.0		);
		
		DominatedCards dc=new DominatedCards(cards, categories);
		List<Bounds<Double>> bounds=new ArrayList<Bounds<Double>>();
		for(int i=0;i<cardsValuesList.size();i++)
			bounds.add(Bounds.create(1.0, 4.0));		
		DefaultDoubleSolution solution=new DefaultDoubleSolution(1,0,bounds);
		solution.variables().clear();
		solution.variables().addAll(cardsValuesList);
		Double actualValue=dc.evaluate(solution);
		Double expectedValue=-3.0;
		assertEquals(expectedValue,actualValue);
	}
}
