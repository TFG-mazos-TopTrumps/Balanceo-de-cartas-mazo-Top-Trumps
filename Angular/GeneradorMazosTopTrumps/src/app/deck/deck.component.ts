import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Deck } from '../model/Deck';
import { Keyword } from '../model/Keyword';
import { DeckService } from '../service/deck.service';
import { KeywordService } from '../service/keyword.service';
import { LoginService } from '../service/login.service';


@Component({
  selector: 'app-deck',
  templateUrl: './deck.component.html',
  styleUrls: ['./deck.component.css']
})
export class DeckComponent implements OnInit {
  
  form;
  idDeck:number;
  keyword: string;
  username: string;
  password: string;
  name: string;
  description: string;
  image: string;
  cards: number;
  categories: number;
  valueMin: number;
  valueMax: number;
  idUser: number;
  keywords: Array<string> = []; 

  condicionKeyword: boolean = false;
  confirmKeyword: boolean = true;
  notNullName: boolean = true;
  duplicatedName: boolean = true;
  notNullAndNegativeNCards: boolean = true;
  notNullAndNegativeNCategories: boolean = true;
  notNullAndNegativeValueMin: boolean = true;
  notNullAndNegativeValueMax: boolean = true;
  valueMinBiggerOrEqualThanValueMax: boolean = true;
  minKeywords: boolean = true;

  notNullKeyword: boolean = true;
  anyError:boolean = false;
  alertaExito: boolean = false;
  
  constructor(private userService: LoginService, private deckService: DeckService, private keywordService: KeywordService, private route:Router,  private cookies: CookieService) {
     
   }

  ngOnInit() {

    let usuario = this.cookies.get("usuario");
    let password = this.cookies.get("password");
0
    if( usuario == "" && password == "") {
      this.userService.login(usuario, password).subscribe({
        next: user => {
            this.route.navigate([``]);
        }
      })
  }
  }

  
  createDeck() {

    this.anyError=false;
    let deck = new Deck();
    deck.name = this.name;
    deck.description = this.description;
    deck.image = this.image;
    deck.ncards = this.cards;
    deck.ncategories = this.categories;

    this.cookies.set("deck", deck.name);
    
    this.username = this.cookies.get("usuario");
    this.password= this.cookies.get("password");
   
    this.deckService.countDecksName(this.name).subscribe({
      next: respuesta => {
        let numberOfDecks: number;
        numberOfDecks=respuesta;
     

    if(deck.name == null) {
        this.notNullName=false;
        this.anyError=true;
    }
    
    if(numberOfDecks > 0) {
      this.duplicatedName=false;
      this.anyError=true;
    } 
    if(deck.ncards == null || deck.ncards < 0) {
      this.notNullAndNegativeNCards=false;
      this.anyError=true;
    } 
    if(deck.ncategories == null || deck.ncategories < 0) {
      this.notNullAndNegativeNCategories=false;
      this.anyError=true;
    }
    
    if(this.valueMin == null || this.valueMin < 0 ) {
      this.notNullAndNegativeValueMin=false;
      this.anyError=true;

    }

    if(this.valueMax == null || this.valueMax < 0 ) {
      this.notNullAndNegativeValueMax=false;
      this.anyError=true;

    }

    if(this.valueMin >= this.valueMax) {
      this.valueMinBiggerOrEqualThanValueMax=false;
      this.anyError=true;
    }

    
  if(!this.anyError) {
    this.deckService.createDeck(deck, this.username, this.password).subscribe({
      next: d => {
        
        console.log(`Registrado, ${JSON.stringify(d)}`) 
        this.condicionKeyword=true;
      
        
      },
      error: e => {
        console.log(`insertar -> No se ha podido registrar, ${e}`)
        alert(e)
        
        
        
        
      }
    })
    
  }
}
});
  
  }


  deckKeyword() {
    this.notNullKeyword=true;

    this.alertaExito=false;
    let anyError = false;
    let keyword = new Keyword();
    keyword.word = this.keyword;
   
    
    let name = this.cookies.get("deck");

    if(keyword.word==null || keyword.word=="") {
      this.notNullKeyword=false;
      anyError=true;
    }
    if(!anyError) {
    this.deckService.getDeckByName(name).subscribe({
      next: respuesta => {
        this.idDeck = respuesta.idDeck;
      },
      error: e => {
        console.log(`consulta -> No se ha podido obtener, ${e}`)
        alert(e)
      }

    });
    this.keywordService.addKeyword(keyword, name).subscribe({
      next: respuesta => {
        console.log(`Registrado, ${JSON.stringify(respuesta)}`) 
        this.confirmKeyword=true;
        this.alertaExito=true;
        this.minKeywords=true;

      },
      error: e => {
        console.log(`insertar -> No se ha podido registrar, ${e}`)
        alert(e)
      }
    });
    this.keyword="";
    
      this.confirmKeyword=true;
    
  }
  }
  volver() {
    this.route.navigate([`home`]);
  }

  nextCategories() {

    let deck = this.cookies.get("deck");
    this.deckService.checkKeywords(deck).subscribe({
      next: ck => {

        if(ck) {
          this.minKeywords=false;
        } else {
          this.route.navigate(['/card', this.cards, this.categories, this.valueMin, this.valueMax]);
        }
    }
  });
    

  }
}
