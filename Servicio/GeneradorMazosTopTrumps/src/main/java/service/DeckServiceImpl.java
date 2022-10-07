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



import dao.DecksDao;
import model.Card;
import model.Deck;
import toptrumps.TopTrumpsDeckGenerationProblem;

@Service
public class DeckServiceImpl implements DeckService {

	DecksDao decksDao;
	CardService cardService;

	public DeckServiceImpl(@Autowired DecksDao decksDao, @Autowired CardService cardService) {
		super();
		this.decksDao = decksDao;
		this.cardService = cardService;
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
		boolean errorMaxLengthName = d.getName().length() >= 45 ? true:false;
		boolean errorMaxLengthDescription = (d.getDescription() != null && d.getDescription().length() >= 500) ? true:false;
		boolean errorMaxLengthImage = (d.getImage() != null  && d.getImage().length() >= 1000) ? true:false;
		boolean errorPatternURL = (d.getImage() != null && d.getImage().length() >= 1 && !(d.getImage().startsWith("http://") || d.getImage().startsWith("https://"))) ? true:false;
		boolean errorNotNullAndNegativeNCards = d.getNCards() < 2 ? true:false;
		boolean errorNotNullAndNegativeNCategories = d.getNCategories() < 2 ? true:false;
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
			
			if(!anyError && errorMaxLengthName) {
				anyError=true;
				throw new ConstraintViolationException("El nombre del mazo no puede tener 45 o más caracteres.",null);
				
			}
			
			if(!anyError && errorMaxLengthDescription) {
				anyError=true;
				throw new ConstraintViolationException("La descripción no puede tener 500 o más caracteres.",null);
				
			}
			
			if(!anyError && errorMaxLengthImage) {
				anyError=true;
				throw new ConstraintViolationException("La URL de la imagen no puede tener 1000 o más caracteres.",null);
				
			}
			
			if(!anyError && errorPatternURL) {
				anyError=true;
				throw new ConstraintViolationException("El campo imagen ha de ser una URL.",null);
				
			}
			
			
			if(!anyError && errorNotNullAndNegativeNCards) {
				anyError=true;
				throw new ConstraintViolationException("El número de cartas no puede ser menor que uno.",null);
				
			}
			if(!anyError && errorNotNullAndNegativeNCategories) {
				anyError=true;
				throw new ConstraintViolationException("El número de categorías no puede ser menor que uno.",null);
			
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
			if(errorMaxLengthName) {
				System.out.println("El nombre del mazo no puede tener 45 o más caracteres.");
			}
			if(errorMaxLengthDescription) {
				System.out.println("La descripción no puede tener 500 o más caracteres.");
			}
			if(errorMaxLengthImage) {
				System.out.println("La URL de la imagen no puede tener 1000 o más caracteres.");
			}
			if(errorPatternURL) {
				System.out.println("El campo imagen ha de ser una URL.");
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
	public void balanceDeck(Integer nCards, Integer nCategories, Double lowerLimit, Double upperLimit, String deck) throws ConstraintViolationException, SQLException {
		List<Card> cards = this.cardService.findCardsOfDeck(deck);
		List<Double> values = this.generateDeckValues(nCards, nCategories, lowerLimit, upperLimit);
		
		int i = 0;
		
		for(Card c : cards) {
			
			for (Map.Entry<String, Double> entry : c.getCategories().entrySet()) {
			    
				Double value = values.get(i);
			    entry.setValue(value);
			    i++;
			}
			
				c = this.cardService.saveCard(c);
		
			
		}
		
		
		
	}

//@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            //produces = MediaType.APPLICATION_PDF_VALUE)
	public void pdfMazo(String deck) throws IOException {
		
		Deck d = this.decksDao.findDeckByName(deck).get();
		List<Card> cards = this.cardService.findCardsOfDeck(deck);
		
		try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A6);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 32);
            contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 52);
            contentStream.showText(d.getName());
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 20);
            contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 52*2);
            contentStream.showText("Creador: ");
            contentStream.showText(d.getUser().getName());
            contentStream.endText();


//           if(d.getImage() != null) {
//            PDImageXObject image = PDImageXObject.createFromByteArray(document, Main.class.getResourceAsStream(d.getImage()).readAllBytes(), "Imagen del mazo");
//            contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);
//           }
           
           if(d.getDescription() != null) {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.COURIER, 14);
            contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 52*3);
            contentStream.showText(d.getDescription());
            contentStream.endText();
           }
            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.COURIER, 14);
            contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 52*4);
            contentStream.showText("Número de cartas: ");
            contentStream.showText(String.valueOf(d.getNCards()));
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.COURIER, 14);
            contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 52*5);
            contentStream.showText("Número de categorias: ");
            contentStream.showText(String.valueOf(d.getNCategories()));
            contentStream.endText();
            
            
            contentStream.close();

            document.save(d.getName() + ".pdf");
        }
	}
    
		 
	    
		
		public boolean checkKeyword(String deck) {
			
			Deck d = this.getDeckByName(deck);
			
			return d.getKeywords().isEmpty() ? true:false;
		}

		
		public void publishDeck(String deck) {
			Deck d = this.getDeckByName(deck);
			d.setPublished(true);
			this.decksDao.save(d);
			
		}

	
		public void noPublishDeck(String deck) {
			Deck d = this.getDeckByName(deck);
			d.setPublished(false);
			this.decksDao.save(d);
			
			
		}
	
	
	
	

}
