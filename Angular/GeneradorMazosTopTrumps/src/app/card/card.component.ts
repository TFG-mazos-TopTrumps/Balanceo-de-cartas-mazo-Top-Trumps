import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute,  Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
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
  notNullCard: boolean = true;
  pdf: boolean = false;
  values: number[];

  cardsOfDeck: Card[];
  

  constructor(private loginService: LoginService, private route : ActivatedRoute,  private router : Router, private cardService: CardService,  private deckService: DeckService,  public dialog: MatDialog, private cookies: CookieService) {
    
    this.cards = route.snapshot.params["cards"];
    this.nCategories = route.snapshot.params["categories"];
    this.cards = route.snapshot.params["cards"];
    this.valueMin = route.snapshot.params["valueMin"];
    this.valueMax = route.snapshot.params["valueMax"];
    

   }

  ngOnInit() {

    let usuario = this.cookies.get("usuario");
    let password = this.cookies.get("password");

    if( usuario == "" && password == "") {
      this.loginService.login(usuario, password).subscribe({
        next: user => {
            this.router.navigate([``]);
        }
      })
  }
  }

  generateCategories() {

    let anyError=false;
    if(this.category==null || this.category=="") {
      this.notNullCategory=false;
      anyError=true;
    }

    if(!anyError) {
    if(this.i<=this.nCategories) {
      
      this.categories.push(this.category);
      
      this.category="";
      this.i++;
      console.log(this.categories);
    }
    if(this.i==this.nCategories) {
      
      this.cardCategories=true;
      
    }
  }
  }

  generateCard() {

    let anyError=false;
    if(this.name==null || this.name=="") {
      this.notNullCard=false;
      anyError=true;
    }
    if(!anyError) {
    if(this.n<=this.cards) {
     
      let card = new Card();
      card.name = this.name;
      card.image = this.image;
      card.description = this.description;
      
      
      this.deck = this.cookies.get("deck");
      this.cardService.createCard(card, this.deck).subscribe({
        next: respuesta => {
          console.log(`Registrado, ${JSON.stringify(respuesta)}`)
          this.idCard=respuesta.idCard;
          
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

          if(this.n==this.cards) {
            this.generateValues=true;

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
      
      this.n++;
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
      }
    });
    

    
}

  pdfMazo() { 
    this.deckService.deckPdf(this.deck).subscribe({
      next: pdf => {
        console.log("Generado PDF del mazo " + pdf);
      }
  });
        
  }
}

