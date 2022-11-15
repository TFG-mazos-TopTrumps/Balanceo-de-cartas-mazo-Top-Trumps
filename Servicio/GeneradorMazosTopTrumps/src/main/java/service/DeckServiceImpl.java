package service;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
		boolean errorMaxLengthName = d.getName().length() > 30 ? true:false;
		boolean errorMaxLengthDescription = (d.getDescription() != null && d.getDescription().length() > 500) ? true:false;
		boolean errorMaxLengthImage = (d.getImage() != null  && d.getImage().length() > 4000) ? true:false;
		boolean errorIncorrectFormatImage = d.getImage() != null  && !(d.getImage().contains(".jpg") || d.getImage().contains(".png") || d.getImage().contains(".jpeg")) ? true:false;
		boolean errorPatternURL = (d.getImage() != null && d.getImage().length() >= 1 && !(d.getImage().startsWith("http://") || d.getImage().startsWith("https://"))) ? true:false;
		boolean errorNotNullAndIncorrectNCards = d.getNCards() < 2 ? true:false;
		boolean errorNotNullAndIncorrectNCategories = d.getNCategories() < 2 ? true:false;
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
				throw new ConstraintViolationException("El nombre del mazo no puede tener más de 30 caracteres.",null);
				
			}
			
			if(!anyError && errorMaxLengthDescription) {
				anyError=true;
				throw new ConstraintViolationException("La descripción no puede tener más de 500 caracteres",null);
				
			}
			
			if(!anyError && errorMaxLengthImage) {
				anyError=true;
				throw new ConstraintViolationException("La URL de la imagen no puede tener más de 4000 caracteres.",null);
				
			}
			
			if(!anyError && errorPatternURL) {
				anyError=true;
				throw new ConstraintViolationException("El campo imagen ha de ser una URL.",null);
				
			}
			
			if(!anyError && errorIncorrectFormatImage) {
				anyError=true;
				throw new ConstraintViolationException("La imagen ha tener formato .jpg, .png o .jpeg .",null);
				
			}
			
			if(!anyError && errorNotNullAndIncorrectNCards) {
				anyError=true;
				throw new ConstraintViolationException("El número de cartas no puede quedar vacío y ha de ser positivo: mayor que uno y menor o igual que treinta.", null);
				
			}
			if(!anyError && errorNotNullAndIncorrectNCategories) {
				anyError=true;
				throw new ConstraintViolationException("El número de categorias no puede quedar vacío y ha de ser positivo: mayor que uno y menor o igual que seis.",null);
			
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
			
			if(errorNotNullAndIncorrectNCards) {
				System.out.println("El número de cartas no puede quedar vacío y ha de ser positivo: mayor que uno y menor o igual que treinta.");
			
			}
			if(errorNotNullAndIncorrectNCategories) {
				System.out.println("El número de cartas no puede quedar vacío y ha de ser positivo: mayor que uno y menor o igual que seis.");
				
			}
			if(errorMaxLengthName) {
				System.out.println("El nombre del mazo no puede tener más de 30 caracteres.");
			}
			if(errorMaxLengthDescription) {
				System.out.println("La descripción no puede tener más de 500 caracteres.");
			}
			if(errorMaxLengthImage) {
				System.out.println("La URL de la imagen no puede tener más de 4000 caracteres.");
			}
			if(errorPatternURL) {
				System.out.println("El campo imagen ha de ser una URL.");
			}
			if(errorIncorrectFormatImage) {
				System.out.println("La imagen ha tener formato .jpg, .png o .jpeg .");
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
	
	
	    public static void download(String urlString, String filename,String savePath) throws Exception, UnknownHostException {  
	        // Construir URL  
	    	
	    	try {
	        URL url = new URL(urlString);  
	        
	                 // conexión abierta  
	        URLConnection con = url.openConnection();
	       
	                 // Establece el tiempo de espera de la solicitud en 5 s  
	        con.setConnectTimeout(5*1000);  
	                 // flujo de entrada 
	       
	        InputStream is = con.getInputStream();  
	      
	                 // Búfer de datos de 1K  
	        byte[] bs = new byte[2048];  
	                 // La longitud de los datos leídos  
	        int len;  
	                 // flujo de archivo de salida  
	       File sf=new File(savePath);  
	       if(!sf.exists()){  
	           sf.mkdirs();  
	       }  
	       OutputStream os = new FileOutputStream(sf.getPath()+"/"+filename);  
	                 // empieza a leer  
	        while ((len = is.read(bs)) != -1) {  
	          os.write(bs, 0, len);  
	        }  
	                 // Finalizar, cerrar todos los enlaces  
	        os.close();  
	        is.close();  
	        
	    	} catch(UnknownHostException u) {
	    		System.out.println("La URL indicada no corresponde con ninguna imagen existente.");
	    	}
	    } 
	    
	  


	public void pdfMazo(String deck) throws IOException, Exception {
		
		Deck d = this.decksDao.findDeckByName(deck).get();
		List<Card> cards = this.cardService.findCardsOfDeck(deck);
		
		// Atributos:
		String name = d.getName();
		String description = d.getDescription();
		
		
		try (PDDocument document = new PDDocument()) {
            PDPage firstPage = new PDPage(PDRectangle.A4);
           
            
            document.addPage(firstPage);

            PDPageContentStream contentStream = new PDPageContentStream(document, firstPage);
          
            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 32);
            contentStream.newLineAtOffset(25, firstPage.getTrimBox().getHeight()-25);
            contentStream.showText(name);
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 20);
            contentStream.newLineAtOffset(25, firstPage.getTrimBox().getHeight()-25*2);          
            contentStream.showText("Creador: ");
            contentStream.showText(d.getUser().getName());
            contentStream.endText();

           if(description != null) {
        	   
        	   contentStream.beginText();
               contentStream.setFont(PDType1Font.TIMES_BOLD, 20);
               contentStream.newLineAtOffset(25, firstPage.getTrimBox().getHeight()-25*3);           
               contentStream.showText("Descripción: ");
               contentStream.endText();
               
               List<String> lines = new ArrayList<String>();
               
               int begin = 0;
               
               String line;
               for(int end = 80; end <= description.length() + 80; end += 80) {
            	   
            	   if(end==80) {
	            	   if(description.length() < 80) {
	            		   line = description.substring(begin);
	            		   lines.add(line);
	            		   
	            	   } else {
	            		   line = description.substring(begin, end);
	            		   lines.add(line);
	            	   }
            	   } else {
            	   begin += 80;
            	   if(end > 80) {
            		  
            		   if(end < description.length()-1) {
            			   line = description.substring(begin, end);
            			   lines.add(line);
            		   }
            		   if(end > description.length()-1) {
            			   line = description.substring(begin);
            			   lines.add(line);
            		

            			   
            	   }   
            	   
            	   
            	 }
            	   }
               } 
               int c = 4;
              
               for(String l : lines) {
            	   contentStream.beginText();
                   contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
                   contentStream.newLineAtOffset(25, firstPage.getTrimBox().getHeight()-25*c);        
                   contentStream.showText(l);
                   contentStream.endText();
                   c++;
               }
               
        	            
           
           
           if(d.getImage() != null) {
        	
        	  download(d.getImage(), "imagen" + d.getName() + ".jpg", "C:/PDFMazo");
          	  File img = new File("C:\\PDFMazo\\" + "imagen" + d.getName() + ".jpg");
          	  if(img.exists()) {
	          	  PDImageXObject image = PDImageXObject.createFromFileByContent(img, document);          
	             contentStream.drawImage(image, 20, 20, 500, 500);
          	  } else {
          		  contentStream.beginText();
          		  contentStream.setFont(PDType1Font.TIMES_BOLD, 14);
	          	  contentStream.newLineAtOffset(20,20);
	          	  contentStream.showText("No tiene imagen.");
	          	  contentStream.endText();
          		  
          	  }
        	  
           }
            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 14);
            contentStream.newLineAtOffset(25, firstPage.getTrimBox().getHeight()-25*11);
            contentStream.showText("Número de cartas: ");
            contentStream.showText(String.valueOf(d.getNCards()));
            contentStream.endText();
            
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 14);
            contentStream.newLineAtOffset(25, firstPage.getTrimBox().getHeight()-25*12);
            contentStream.showText("Número de categorias: ");
            contentStream.showText(String.valueOf(d.getNCategories()));
            contentStream.endText();
            
            
            contentStream.close();
            
            //======================================Cartas===========================================
            
            
            for(Card card : cards) {
            	 PDPage pageCard = new PDPage(PDRectangle.A4);
            	 document.addPage(pageCard);
	            	 
               // Tamaño de la carta.
	            PDPageContentStream contentStreamCards = new PDPageContentStream(document, pageCard);
                contentStreamCards.setNonStrokingColor(new Color(0,0,0));
                contentStreamCards.addRect(125+75, 225+25, 190, 310);
                contentStreamCards.fill();
                
               // Fondo de la carta
                
                contentStreamCards.setNonStrokingColor(new Color(245,245,245));
				contentStreamCards.addRect(130 + 75, 230+25, 180, 300);
                contentStreamCards.fill();
                
               // Nombre de las cartas.	
                
                contentStreamCards.setNonStrokingColor(new Color(135,206,250));
                contentStreamCards.addRect(130 + 75, 510+25, 180, 20);
                contentStreamCards.fill();
                
	           // Panel descripción.
	            contentStreamCards.setNonStrokingColor(new Color(135,206,250));
	            contentStreamCards.addRect(130 + 75, 230+25, 75, 130);
	            contentStreamCards.fill();
                
             
                
                for(int i=1; i<=d.getNCategories(); i++) {
                
                // Paneles nombres de las categorías.
                    
	            contentStreamCards.setNonStrokingColor(new Color(135,206,250));
	            contentStreamCards.addRect(211 + 75, 368 -(23*i) +25, 95, 15);
	            contentStreamCards.fill();
    
                
            }
                // Color de la fuente.
                
                contentStreamCards.setNonStrokingColor(new Color(0,0,0));
                contentStreamCards.fill();
              
                if(card.getImage() != null) {
               
               
                  download(card.getImage(), "imagen" + card.getName() + ".jpg", "C:/PDFMazo");
               	  File img = new File("C:\\PDFMazo\\" + "imagen" + card.getName() + ".jpg");
               	  if(img.exists()) {
               	  PDImageXObject image = PDImageXObject.createFromFileByContent(img, document);          
               	  contentStreamCards.drawImage(image, 132 + 75, 362+25, 176, 144);
               	  }  else {
                 		contentStreamCards.beginText();
                 		contentStreamCards.setFont(PDType1Font.TIMES_BOLD, 14);
    	          	  	contentStreamCards.newLineAtOffset(132+50, 362+25);
    	          	  	contentStreamCards.showText("No tiene imagen.");
    	          	  	contentStreamCards.endText();
                 	}
               	  }
                
                
                
                
                contentStreamCards.beginText();
                contentStreamCards.setFont(PDType1Font.TIMES_BOLD, 12);
                contentStreamCards.newLineAtOffset(132 + 75, 513+25);
                contentStreamCards.showText(card.getName());
                contentStreamCards.endText();
                
                
                if(card.getDescription() != null) {
             	   
                	
                    List<String> linesCard = new ArrayList<String>();
                    
                    int beginCard = 0;
                    String lineCard;
                    for(int end = 20; end <= card.getDescription().length() + 20; end += 20) {
                 	   
                 	   if(end==20) {
     	            	   if(card.getDescription().length() < 20) {
     	            		   lineCard = card.getDescription().substring(beginCard);
     	            		   linesCard.add(lineCard);
     	            	   } else {
     	            		  lineCard = card.getDescription().substring(beginCard,end);
     	            		  linesCard.add(lineCard);
     	            	   }
     	            	   
                 	   } else {
                 		  beginCard += 20;
                 	   if(end > 20) {
                 		  
	                 		   if(end < card.getDescription().length()-1) {
	                 			   lineCard = card.getDescription().substring(beginCard, end);
	                 			   linesCard.add(lineCard);
	                 		   }
	                 		   if(end > card.getDescription().length()-1) {
	                 			   lineCard = card.getDescription().substring(beginCard);
	                 			   linesCard.add(lineCard);
	                 			   
	                 	   }   
                 	   
                 	   
                 	 }
                 	   }
                    } 
                    int contador = 0;
                    
                    for(String l : linesCard) {
                    	
                    	contentStreamCards.beginText();
                    	contentStreamCards.setFont(PDType1Font.TIMES_BOLD, 6);
                    	contentStreamCards.newLineAtOffset(132 + 75, 352 - 5*contador +25);        
                    	contentStreamCards.showText(l);
                    	contentStreamCards.endText();
                        contador++;
                    }
                   
                
             	            
                }
                
               
                int n = 1;
                for(var cv : card.getCategories().entrySet()) {
                	
                	contentStreamCards.beginText();
                	contentStreamCards.setFont(PDType1Font.TIMES_BOLD, 7);
                	contentStreamCards.newLineAtOffset(214 + 75, 371 - 23*n +25);        
                	contentStreamCards.showText(cv.getKey() + " " + String.valueOf(cv.getValue()));
                	contentStreamCards.endText();
                	n++;
                }
                contentStreamCards.close();
	            }
      
            	
            }
	       
            document.save("C:\\PDFMazo\\" + d.getName() + ".pdf");
        
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
