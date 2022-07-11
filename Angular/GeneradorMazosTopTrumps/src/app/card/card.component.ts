import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Card } from '../model/Card';
import { CardService } from '../service/card.service';
import { DeckService } from '../service/deck.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  i: number = 1;
  name: string;
  image: string;
  description: string;
  cards: number;
  deck: string;

  categories: Array<string> = [];

  constructor(private route : ActivatedRoute, private cardService: CardService, private deckService: DeckService,  private cookies: CookieService) {
    this.cards = route.snapshot.params["cards"];

   }

  ngOnInit() {
  }

  generateDeck() {
    if(this.i<this.cards) {

      let card = new Card();
      card.name = this.name;
      card.image = this.image;
      card.description = this.description;
      
      this.deck = this.cookies.get("deck");
      this.cardService.createCard(card, this.deck).subscribe({
        next: respuesta => {
          console.log(`Registrado, ${JSON.stringify(respuesta)}`) 
        },
        error: e => {
          console.log(`insertar -> No se ha podido registrar, ${e}`)
          alert(e)
        }
      })
      
      
      this.name="";
      this.image="";
      this.description="";
      
      this.i++;
    }
  }
}
