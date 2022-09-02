package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.lab.experiment.util.ExperimentProblem;
import org.uma.jmetal.operator.crossover.impl.SBXCrossover;
import org.uma.jmetal.operator.mutation.impl.PolynomialMutation;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

import dao.CardsDao;
import dao.DecksDao;
import model.Card;
import model.Deck;
import toptrumps.TopTrumpsDeckGenerationProblem;

@Service
public class DeckServiceImpl implements DeckService {

	DecksDao decksDao;
	CardsDao cardsDao;

	public DeckServiceImpl(@Autowired DecksDao decksDao, @Autowired CardsDao cardsDao) {
		super();
		this.decksDao = decksDao;
		this.cardsDao = cardsDao;
	}

	public List<Deck> getDecks() {

		return decksDao.findAll();

	}
	public List<Deck> getDecksByKeywords(String k) {
		
		return decksDao.findDecksByKeywords(k);
	}

	@Transactional
	public void createDeck(Deck d) throws SQLException, ConstraintViolationException {
		
		// Condiciones de validación
		boolean errorDuplicatedName = this.decksDao.findDeckByName(d.getName()).isPresent() ? true : false;
		boolean errorNotNullName = d.getName() == null || d.getName().isBlank() || d.getName().isEmpty() ? true:false;
		boolean errorNotNullAndNegativeNCards = d.getNCards() < 0 ? true:false;
		boolean errorNotNullAndNegativeNCategories = d.getNCategories() < 0 ? true:false;
		boolean anyError = false;
		
		try {
			if(errorDuplicatedName) {
				anyError=true;
				throw new SQLException("El nombre indicado ya se encuentra registrado en el sistema.");
				
			}
			
			if(!anyError && errorNotNullName) {
				anyError=true;
				throw new ConstraintViolationException("El campo nombre del mazo no puede ser nulo.",null);
				
			}
			
			if(!anyError && errorNotNullAndNegativeNCards) {
				anyError=true;
				throw new ConstraintViolationException("El número de cartas no puede ser menor que cero.",null);
				
			}
			if(!anyError && errorNotNullAndNegativeNCategories) {
				anyError=true;
				throw new ConstraintViolationException("El número de categorías no puede ser menor que cero.",null);
			
			}
			
			
			if(!anyError) {
				this.decksDao.save(d);
			}
				
		
			
		} catch(SQLException e) {
			System.out.println("El nombre indicado ya se encuentra registrado en el sistema.");
			
		} catch(ConstraintViolationException e) {
			if(errorNotNullName) {
				System.out.println("El campo nombre del mazo no puede ser nulo.");
				
			}
			
			if(errorNotNullAndNegativeNCards) {
				System.out.println("El número de cartas no puede ser menor que cero.");
			
			}
			if(errorNotNullAndNegativeNCategories) {
				System.out.println("El número de categorías no puede ser menor que cero.");
				
			}
		}
		
		
		
	}

	
	public Deck getDeckById(int id) {
		Optional<Deck> oDeck = decksDao.findById(id);
		Deck deck = new Deck();
		if(oDeck.isPresent()) {
			deck=oDeck.get();
			return deck;
		}
		return null;
	}

	@Override
	public Deck getDeckByName(String name) {
		
		Optional<Deck> optionalDeck = decksDao.findDeckByName(name);
		
		if(optionalDeck.isPresent()) {
			return optionalDeck.get();
		} else {
			return optionalDeck.orElse(new Deck());
		}
	}

	@Override
	public Integer findDeckId(String name) {
		
		return decksDao.findDeckId(name);
	}

	public List<Double> generateDeckValues(Integer nCards, Integer nCategories, Double lowerLimit, Double upperLimit) {
		ExperimentProblem<DoubleSolution> problem = 
				new ExperimentProblem<>(
						new TopTrumpsDeckGenerationProblem(nCards, nCategories, 30, 15, 2, 3, lowerLimit, upperLimit)
					);
				
		
		
		 Algorithm<List<DoubleSolution>> algorithm = new NSGAIIBuilder<DoubleSolution>(
                 problem.getProblem(),
                 new SBXCrossover(1.0, 20.0),
                 new PolynomialMutation(1.0 / problem.getProblem().getNumberOfVariables(),
                         20.0),
                 100)
                 .build();
		 
		 algorithm.run();
		 List<DoubleSolution> solutions = algorithm.getResult();
		 
		 return solutions.get(0).variables();
		
	
	}

	@Override
	public Integer countDecksWithName(String name) {
		
		return this.decksDao.countDecksWithName(name);
	}

	@Override
	public void balanceDeck(Integer nCards, Integer nCategories, Double lowerLimit, Double upperLimit, String deck) {
		List<Card> cards = this.cardsDao.findCardsOfDeck(deck);
		List<Double> values = this.generateDeckValues(nCards, nCategories, lowerLimit, upperLimit);
		
		int i = 0;
		
		for(Card c : cards) {
			
			for (Map.Entry<String, Double> entry : c.getCategories().entrySet()) {
			    
				Double value = values.get(i);
			    entry.setValue(value);
			    i++;
			}
			this.cardsDao.save(c);
			
		}
		
		
		
	}

	
	public PDDocument pdfMazo(String deck) throws IOException {
		
		List<Card> cards = this.cardsDao.findCardsOfDeck(deck);
		 try (PDDocument document = new PDDocument()) {
			 PDPage page = new PDPage(PDRectangle.A6);
			 for(Card c : cards) {
	           
	            document.addPage(page);

	            PDPageContentStream contentStream = new PDPageContentStream(document, page);
	            
	            
		            contentStream.beginText();
		            contentStream.setFont(PDType1Font.COURIER, 32);
		            contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 52);
		            contentStream.showText(c.getName());
		            contentStream.newLine();
		            contentStream.showText(c.getDescription());
		            contentStream.endText();
		            
//		            PDImageXObject image = PDImageXObject.createFromFile(c.getImage(), document);
//		            contentStream.drawImage(image, 150, 250);
		            contentStream.close();
	            }
	         
	            document.save(deck + ".pdf");
	            return document;
	        }
	    }
		
		public boolean checkKeyword(String deck) {
			
			Deck d = this.getDeckByName(deck);
			
			return d.getKeywords().isEmpty() ? true:false;
		}
	
	
	
	

}
