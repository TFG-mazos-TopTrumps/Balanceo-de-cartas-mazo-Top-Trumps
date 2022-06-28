import { Component, OnInit } from '@angular/core';;
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Deck } from '../model/Deck';
import { Keyword } from '../model/Keyword';
import { User } from '../model/User';
import { DeckService } from '../service/deck.service';
import { KeywordService } from '../service/keyword.service';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-deck',
  templateUrl: './deck.component.html',
  styleUrls: ['./deck.component.css']
})
export class DeckComponent implements OnInit {
  
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
  keywords: string[];

  condicionKeyword: boolean = false;
  constructor(private userService: LoginService, private deckService: DeckService, private keywordService: KeywordService, private route:Router, private cookies: CookieService) {
   
   }

  ngOnInit() {
  }

  
  createDeck() {

    let deck = new Deck();
    deck.name = this.name;
    deck.description = this.description;
    deck.image = this.image;
    deck.ncards = this.cards;
    deck.ncategories = this.categories;

    
    this.username = this.cookies.get("usuario");
    this.password= this.cookies.get("password");

    this.userService.getIdUser(this.username, this.password).subscribe({
        next: respuesta => { 
        this.idUser=respuesta;
        console.log(this.idUser)

      }
    });
 
 
    this.deckService.createDeck(deck, this.idUser).subscribe({
      next: respuesta => {
        console.log(`Registrado, ${JSON.stringify(respuesta)}`) 
        this.condicionKeyword=true;
      },
      error: e => {
        console.log(`insertar -> No se ha podido registrar, ${e}`)
        alert(e)
      }
    })
  }


  deckKeyword() {
    let keyword = new Keyword();
    keyword.word = this.keyword;
    //this.keywords.push(this.keyword);
    
    
    this.keywordService.addKeyword(keyword, this.name).subscribe({
      next: respuesta => {
        console.log(`Registrado, ${JSON.stringify(respuesta)}`) 
        
      },
      error: e => {
        console.log(`insertar -> No se ha podido registrar, ${e}`)
        alert(e)
      }
    });
  }
  volver() {
    this.route.navigate([`home`]);
  }

}
