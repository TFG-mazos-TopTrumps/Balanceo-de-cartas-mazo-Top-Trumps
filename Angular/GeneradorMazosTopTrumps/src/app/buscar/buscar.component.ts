import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Deck } from '../model/Deck';
import { DeckService } from '../service/deck.service';

@Component({
  selector: 'app-buscar',
  templateUrl: './buscar.component.html',
  styleUrls: ['./buscar.component.css']
})
export class BuscarComponent implements OnInit {

  decks: Deck[] = [];
  buscar:string="";
  buscador:boolean=true;

  constructor(private service: DeckService, private route:Router) { }

  ngOnInit() {
  }

  searchDecks() {
    this.buscador = false;  
    this.service.getDecksByKeyword(this.buscar).subscribe({next: response => {
      this.decks = response;
      
    }});
      
  }



}
