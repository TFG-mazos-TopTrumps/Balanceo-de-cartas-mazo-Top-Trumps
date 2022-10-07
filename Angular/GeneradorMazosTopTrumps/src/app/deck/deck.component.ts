import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Deck } from '../model/Deck';
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

 
  confirmKeyword: boolean = true;
  notNullName: boolean = true;
  errorMaxLengthName: boolean = true;
  errorMaxLengthDescription: boolean = true;
  errorMaxLengthImage: boolean = true;
  errorPatternURL: boolean = true;
  duplicatedName: boolean = true;
  notNullAndNegativeNCards: boolean = true;
  notNullAndNegativeNCategories: boolean = true;
  notNullAndNegativeValueMin: boolean = true;
  notNullAndNegativeValueMax: boolean = true;
  valueMinBiggerOrEqualThanValueMax: boolean = true;
  minKeywords: boolean = true;

  anyError:boolean = false;
  alertaExito: boolean = false;
  
  constructor(private userService: LoginService, private deckService: DeckService, private keywordService: KeywordService, private route:Router) {
     
   }

  ngOnInit() {


    let usuario = sessionStorage.getItem("usuario");
    let password = sessionStorage.getItem("password");
    

    if(usuario == undefined && password == undefined) {
      this.userService.login(usuario, password).subscribe({
        next: user => {
            this.route.navigate([``]);
        }
      })
  }
  }

  
  createDeck() {

    
    this.notNullName=true;
    this.notNullAndNegativeNCategories=true;
    this.notNullAndNegativeNCards=true;
    this.notNullAndNegativeValueMin=true;
    this.notNullAndNegativeValueMax=true;
    this.duplicatedName=true;
    this.errorMaxLengthName=true;
    this.errorMaxLengthDescription=true;
    this.errorMaxLengthImage=true;
    this.errorPatternURL=true;
    this.valueMinBiggerOrEqualThanValueMax=true;

    

    this.anyError=false;
    

    this.username = sessionStorage.getItem("usuario");
    this.password= sessionStorage.getItem("password");
   
    this.deckService.countDecksName(this.name).subscribe({
      next: respuesta => {
        let numberOfDecks: number;
        numberOfDecks=respuesta;
    
    if(numberOfDecks > 0) {
      this.duplicatedName=false;
      this.anyError=true;
    } 
    if(this.name == undefined || this.name.length < 1) {
      this.notNullName=false;
      this.anyError=true;
    } else {
    if(this.name.length >= 45) {
      this.errorMaxLengthName=false;
      this.anyError=true;
    }
  }

  
  
    if(this.cards == null || this.cards < 2) {
      this.notNullAndNegativeNCards=false;
      this.anyError=true;
    } else {
      this.cards = Math.round(this.cards);
      
    }
  
    if(this.categories == null || this.categories < 2) {
      this.notNullAndNegativeNCategories=false;
      this.anyError=true;
    } else {
      this.categories = Math.round(this.categories);
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
    if(this.description != undefined) {
      if(this.description.length >= 500) {
        this.errorMaxLengthDescription=false;
        this.anyError=true;
      }
  }
  if(this.image != undefined) {
    
      if(this.image.length >= 1000) {
        this.errorMaxLengthImage=false;
        this.anyError=true;
      }
      if(this.image.length >= 1) {
        if(!(this.image.startsWith("http://") || this.image.startsWith("https://"))) {
        this.errorPatternURL=false;
        this.anyError=true;
      } 
      }
    }
    

    
  if(!this.anyError) {
    let deck = new Deck();
    deck.name = this.name;
    deck.description = this.description;
    deck.image = this.image;
    deck.ncards = this.cards;
    deck.ncategories = this.categories;
    sessionStorage.setItem("deck", deck.name);
    this.deckService.createDeck(deck, this.username, this.password).subscribe({
      next: d => {
        
        console.log(`Registrado, ${JSON.stringify(d)}`);
        
        this.route.navigate(['/keyword', this.cards, this.categories, this.valueMin, this.valueMax]);
      
        
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


 
  volver() {
    this.route.navigate([`home`]);
  }

 
}
