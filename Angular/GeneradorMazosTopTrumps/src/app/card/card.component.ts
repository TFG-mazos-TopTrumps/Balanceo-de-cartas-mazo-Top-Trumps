import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute,  Router } from '@angular/router';
import { LoadComponent } from '../load/load.component';
import { Card } from '../model/Card';
import { CardService } from '../service/card.service';
import { DeckService } from '../service/deck.service';
import { LoginService } from '../service/login.service';


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  i:number=0;
  n:number=0;
  category: string;
  cards: number;
  nCategories: number;
  categories: Array<string> = [];
  idCard: number;
  name: string;
  image: string;
  description: string;
  deck: string;
  valueMin: number;
  valueMax: number;
  cardCategories: boolean = false;
  generateValues: boolean = false;
  notNullCategory: boolean = true;
  duplicatedCategory: boolean = true;
  notNullCard: boolean = true;
  errorMaxLengthCategories: boolean = true;
  errorMaxLengthName: boolean = true;
  errorMaxLengthDescription: boolean = true;
  errorMaxLengthImage: boolean = true; 
  errorPatternURL: boolean = true;
  errorIncorrectFormatImage: boolean = true;
  unexpectedError: boolean = true;
  pdf: boolean = false;
  publication: boolean = true;
  values: number[];
  alertPublish: boolean = false;
  pantallaExito: boolean = false;
  

 cardsOfDeck: Card[];
  
 

  constructor(private loginService: LoginService, private route : ActivatedRoute, private router : Router, private cardService: CardService,  private deckService: DeckService,  public dialog: MatDialog) {
    
    this.deckService.getDeckByName(sessionStorage.getItem("deck")).subscribe({
      next: deck => {
        this.nCategories = deck.ncategories;
        this.cards = deck.ncards;
        this.valueMin = parseFloat(sessionStorage.getItem("valueMin"));
        this.valueMax = parseFloat(sessionStorage.getItem("valueMax"));
      }
    });
    
    
    

   }

  ngOnInit() {

    this.deck = sessionStorage.getItem("deck");
    let usuario = sessionStorage.getItem("usuario");
    let password = sessionStorage.getItem("password");
    let categoriesCompleted = sessionStorage.getItem("categoriesCompleted");
    let cardsCompleted = sessionStorage.getItem("cardsCompleted");
    let balanceCompleted = sessionStorage.getItem("balanceCompleted");

  
    
  if(usuario==undefined && password==undefined) {
    this.loginService.login(usuario, password).subscribe({
      next: user => {
          this.router.navigate([``]);
          sessionStorage.removeItem("categoriesCompleted");
      sessionStorage.removeItem("cardsCompleted");
      sessionStorage.removeItem("balanceCompleted");
      sessionStorage.removeItem("deck");
      sessionStorage.removeItem("valueMin");
      sessionStorage.removeItem("valueMax");

      let indice = 0;
      while(true) {
        let c = sessionStorage.getItem("category " + indice);
        if(c != undefined) {
        
       sessionStorage.removeItem("category " + indice)
        indice++;

      }
        if(c == undefined) {
          break;
        }

      }    
      }
    })
  }

  this.deckService.getDeckByName(sessionStorage.getItem("deck")).subscribe({
    next: deck => {
      this.nCategories = deck.ncategories;
      this.cards = deck.ncards;
      this.valueMin = parseFloat(sessionStorage.getItem("valueMin"));
      this.valueMax = parseFloat(sessionStorage.getItem("valueMax"));
    }
  });

    if(categoriesCompleted == "true" && cardsCompleted == undefined && balanceCompleted == undefined) {
        this.cardCategories = true;
        
        let indice = 0;
        while(true) {
          let c = sessionStorage.getItem("category " + indice);
          if(c != undefined) {
          
          this.categories.push(c);
          indice++;

        }
          if(c == undefined) {
            break;
          }

        }        

        this.cardService.cardsOfDeck(this.deck).subscribe({
          next: cards => {
            if(cards.length > 0) {
              this.n = cards.length;
            }
          }
        })
    }
    if(categoriesCompleted == "true" && cardsCompleted == "true" && balanceCompleted == undefined) {
      this.cardCategories = true;
      this.generateValues = true;

      } 
    if(categoriesCompleted == "true" && cardsCompleted == "true" && balanceCompleted == "true") {
      this.cardCategories = true;
      this.generateValues = true;
      this.pdf = true;
    } 



  }

  generateCategories() {

    this.notNullCategory=true;
    this.errorMaxLengthCategories=true;
    this.duplicatedCategory=true;
    this.unexpectedError=true;
    let anyError=false;
    if(this.category==null || this.category=="") {
      this.notNullCategory=false;
      anyError=true;
    }
    if(this.category.length > 15) {
      this.errorMaxLengthCategories=false;
      anyError=true;
    }
    if(this.categories.length != 0) {
      for(const c of this.categories) {
        if(c == this.category) {
          this.duplicatedCategory=false;
          anyError=true;
        }
      }
    }
    if(!anyError) {
      if(this.i<=this.nCategories) {
        
        
        this.cardService.validateCategory(this.category).subscribe({
          next: respuesta => {
            if(!respuesta) {
            this.unexpectedError=false;
          } else {
            sessionStorage.setItem("category " + this.i, this.category);
            this.categories.push(this.category);
            
            this.category="";
            this.i++;
            console.log(this.categories);
          
          if(this.i==this.nCategories) {
      
            this.cardCategories=true;
            sessionStorage.setItem("categoriesCompleted", "true");
            
          }
        }
          
          },
          error: e => {
            console.log(`insertar -> No se ha podido registrar, ${e}`)
            alert(e)
          }
        });
            
          }
       
        
      }
      
    
  }
  


  generateCard() {

    this.notNullCard=true;
    this.errorMaxLengthName=true;
    this.errorMaxLengthDescription=true;
    this.errorMaxLengthImage=true;
    this.errorPatternURL=true;
    this.errorIncorrectFormatImage=true;
    this.unexpectedError=true;

    let card = new Card();
    card.name = this.name;
    card.image = this.image;
    card.description = this.description;
   
    let anyError=false;

    if(this.name==null || this.name=="") {
      this.notNullCard=false;
      anyError=true;
    }

    if(this.name.length > 25) {
      this.errorMaxLengthName=false;
      anyError=true;
    }
    if(this.image != undefined) {

      
      if(this.image.length > 4000) {
        this.errorMaxLengthImage=false;
        anyError=true;
      }
      if(this.image.length >= 1) {
       
        if(!(this.image.startsWith("http://") || this.image.startsWith("https://"))) {
          
          this.errorPatternURL=false;
          anyError=true;
        }
        
        if(!(this.image.includes(".jpg") || this.image.includes(".png") || this.image.includes(".jpeg"))) {
          this.errorIncorrectFormatImage=false;
          anyError=true;
        }
      }
    }
    if(this.description!=undefined) {
      if(this.description.length > 500) {
          this.errorMaxLengthDescription=false;
          anyError=true;
      }
    }
    
    
    if(!anyError) {
      if(this.n<=this.cards) {
     
        this.cardService.createCard(card, this.deck).subscribe({
          next: respuesta => {

            if(!respuesta) {
              this.unexpectedError=false;
            } else {
            console.log(`Registrado, ${JSON.stringify(respuesta)}`)
           
            this.idCard=respuesta.idCard;
            this.notNullCard=true;
            
            
            for(const category of this.categories) {
              
              this.cardService.addCategory(this.idCard, category).subscribe({next: categoria => {
                console.log(`Registrado, ${JSON.stringify(categoria)}`)
              },
              error: e => {
                console.log(`insertar -> No se ha podido registrar, ${e}`)
                alert(e)
              }
      
              })
      
            }
            this.n++;
  
            if(this.n==this.cards) {
              this.generateValues=true;
              sessionStorage.setItem("cardsCompleted", "true");
            
  
            }
          }
          },
          error: e => {
            console.log(`insertar -> No se ha podido registrar, ${e}`)
            alert(e)
          }
        });
          
        
        this.name="";
        this.image="";
        this.description="";
        
        
      }
  }
  }

  generateDeck() {
    const load = this.dialog.open((LoadComponent), {
      data: `Puede tardar unos segundos`
    });
    this.deckService.balanceDeck(this.cards, this.nCategories, this.valueMin, this.valueMax, this.deck).subscribe({
      next: bd => {
        
        console.log("Balanceado el mazo " + this.deck);
        this.pdf=true;
        load.close();
        sessionStorage.setItem("balanceCompleted", "true");
        
      }
    });
    

    
}

  pdfMazo() { 
   this.unexpectedError=true;
    const load = this.dialog.open((LoadComponent), {
      data: `Puede tardar unos segundos`
    });
    
    this.deckService.deckPdf(this.deck).subscribe({
      next: pdf => {

        if(!pdf) {
          load.close();
          this.unexpectedError=false;
        }
        console.log("Generado PDF del mazo " + pdf);
        sessionStorage.removeItem("categoriesCompleted");
        sessionStorage.removeItem("cardsCompleted");
        sessionStorage.removeItem("balanceCompleted");
        sessionStorage.removeItem("deck");
        sessionStorage.removeItem("valueMin");
        sessionStorage.removeItem("valueMax");
        let indice = 0;
        while(true) {
          let c = sessionStorage.getItem("category " + indice);
          if(c != undefined) {
          
         sessionStorage.removeItem("category " + indice)
          indice++;

        }
          if(c == undefined) {
            break;
          }

        }    
        load.close()
        this.pantallaExito=true;
        this.router.navigate([`success`]);
        
      
      }
  });
        
  }

  publishDeck() {
    this.deckService.publishDeck(this.deck).subscribe({ next: publish => {
      console.log(`El mazo se ha publicado con éxito.`)
      this.alertPublish = true;
      this.publication=false;
    }});
  }

  noPublishDeck() {
    this.deckService.noPublishDeck(this.deck).subscribe({ next: publish => {
      console.log(`El mazo no se ha publicado`)
      this.alertPublish = false;
      this.publication=false;
    }});
  }

  }

