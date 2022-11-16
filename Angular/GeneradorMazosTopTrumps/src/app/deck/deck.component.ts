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
  colorBordes: string = "#FFFFFF";
  colorFondo: string="#000000";
  colorPaneles: string="#FFFFFF";
  colorFuente: string="#000000";

 
  confirmKeyword: boolean = true;
  notNullName: boolean = true;
  errorMaxLengthName: boolean = true;
  errorMaxLengthDescription: boolean = true;
  errorMaxLengthImage: boolean = true;
  errorPatternURL: boolean = true;
  errorIncorrectFormatImage: boolean = true;
  duplicatedName: boolean = true;
  notNullAndIncorrectNCards: boolean = true;
  notNullAndIncorrectNCategories: boolean = true;
  notNullAndNegativeValueMin: boolean = true;
  notNullAndNegativeValueMax: boolean = true;
  valueMinBiggerOrEqualThanValueMax: boolean = true;
  minKeywords: boolean = true;
  notNullColorBorde: boolean = true;
  notNullColorFondo: boolean = true;
  notNullColorPaneles: boolean = true;
  notNullColorFuente: boolean = true;
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
    this.notNullAndIncorrectNCategories=true;
    this.notNullAndIncorrectNCards=true;
    this.notNullAndNegativeValueMin=true;
    this.notNullAndNegativeValueMax=true;
    this.duplicatedName=true;
    this.errorMaxLengthName=true;
    this.errorMaxLengthDescription=true;
    this.errorMaxLengthImage=true;
    this.errorPatternURL=true;
    this.errorIncorrectFormatImage=true;
    this.valueMinBiggerOrEqualThanValueMax=true;
    this.notNullColorBorde=true;
    this.notNullColorFondo=true;
    this.notNullColorPaneles=true;
    this.notNullColorFuente=true;

    

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
    if(this.name.length > 30) {
      this.errorMaxLengthName=false;
      this.anyError=true;
    }
  }
  
  if(this.colorBordes == undefined || this.colorBordes==null) {
    this.notNullColorBorde=false;
    this.anyError=true;
  } 
  if(this.colorFondo == undefined || this.colorFondo==null) {
    this.notNullColorFondo=false;
    this.anyError=true;
  } 
  if(this.colorPaneles == undefined || this.colorPaneles==null) {
    this.notNullColorPaneles=false;
    this.anyError=true;
  } 
  if(this.colorFuente == undefined || this.colorFuente==null) {
    this.notNullColorFuente=false;
    this.anyError=true;
  } 
    if(this.cards == null || this.cards < 2 || this.cards > 30) {
      this.notNullAndIncorrectNCards=false;
      this.anyError=true;
    } else {
      this.cards = Math.round(this.cards);
      if(this.cards > 30) {
        this.notNullAndIncorrectNCards=false;
        this.anyError=true;
      }
      
    }
  
    if(this.categories == null || this.categories < 2) {
      this.notNullAndIncorrectNCategories=false;
      this.anyError=true;
    } else {
      this.categories = Math.round(this.categories);
      if(this.categories > 6) {
        this.notNullAndIncorrectNCategories=false;
        this.anyError=true;
      }
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
      if(this.description.length > 500) {
        this.errorMaxLengthDescription=false;
        this.anyError=true;
      }
  }
  if(this.image != undefined) {
    
      if(this.image.length > 4000) {
        this.errorMaxLengthImage=false;
        this.anyError=true;
      }
      if(this.image.length >= 1) {
        if(!(this.image.startsWith("http://") || this.image.startsWith("https://"))) {
        this.errorPatternURL=false;
        this.anyError=true;
      } 
      if(!(this.image.match(".jpg") || this.image.match(".png") || this.image.match(".jpeg"))) {
        this.errorIncorrectFormatImage=false;
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
    deck.borde=this.colorBordes;
    deck.fondo=this.colorFondo;
    deck.panel=this.colorPaneles;
    deck.fuente=this.colorFuente;
    
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
