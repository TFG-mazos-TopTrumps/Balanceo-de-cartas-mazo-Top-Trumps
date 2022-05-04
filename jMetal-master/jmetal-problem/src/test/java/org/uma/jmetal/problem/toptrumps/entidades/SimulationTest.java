package org.uma.jmetal.problem.toptrumps.entidades;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.uma.jmetal.problem.toptrumps.entities.Simulation;
import org.uma.jmetal.problem.toptrumps.entities.Bot;
import org.uma.jmetal.problem.toptrumps.entities.Card;
import org.uma.jmetal.problem.toptrumps.entities.DummyBot;

class SimulationTest {

	
	@Test
	void testGenerateCategories() {
		int categories=4;
		List<String> catnames= Simulation.generateCategories(categories);
		assertEquals(4,catnames.size());
	}
	
	
	@Test
	void testBuildCards() {
		int categories=4;
				
		List<Double> cardsValuesList=Arrays.asList(
				1.0,2.0,3.0,4.0,
				4.0,3.0,2.0,1.0,
				2.0,3.0,4.0,1.0,
				3.0,4.0,1.0,2.0		);
		List<Card> result=Simulation.buildCards(cardsValuesList, Simulation.generateCategories(categories));
		assertEquals(4, result.size());
	}
	
	
	@Test
	void testPlay() {
		int nBots=2;
		int maxRounds=10;
		int categories=4;
		Double[] cardValues= {			};		
		List<Double> cardsValuesList=Arrays.asList(
				1.0,2.0,3.0,4.0,
				4.0,3.0,2.0,1.0,
				2.0,3.0,4.0,1.0,
				3.0,4.0,1.0,2.0
				);		
		List<Bot> bots=new ArrayList<Bot>(nBots);
		for(int i=0;i<nBots;i++)
			bots.add(new DummyBot());
		Simulation simulacion=new Simulation();
		simulacion.play(cardsValuesList,categories,bots,maxRounds);
		assertEquals(2,simulacion.getNRoundsPlayed());
	}

}
