import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DeckService } from '../service/deck.service';

@Component({
  selector: 'app-buscar',
  templateUrl: './buscar.component.html',
  styleUrls: ['./buscar.component.css']
})
export class BuscarComponent implements OnInit {

  
  buscar:string="";

  constructor(private service: DeckService, private route:Router) { }

  ngOnInit() {
  }

  searchDecks() {
    this.service.getDecks(this.buscar);

  }



}
