import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import {map,startWith, debounceTime} from 'rxjs/operators';
import { Router } from '@angular/router';
import { Deck } from '../model/Deck';
import { DeckService } from '../service/deck.service';
import { Observable } from 'rxjs';
import { Keyword } from '../model/Keyword';
import { KeywordService } from '../service/keyword.service';
import { LoginService } from '../service/login.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-buscar',
  templateUrl: './buscar.component.html',
  styleUrls: ['./buscar.component.css']
})
export class BuscarComponent implements OnInit {

  decks: Deck[] = [];
  buscar:string="";
  buscador:boolean=true;
  keywords: string[] = [];
  keyword: string;

  detalle: boolean = false;
  nombre: string = "";
  nCards: number = 0;
  nCategories: number = 0;
  description: string = "";
  image: string = "";
  user: string = "";
  showForm: boolean = true;
  control = new FormControl;
  filKeywords: Observable<string[]>;
  constructor(private service: KeywordService, private deckService: DeckService, private loginService: LoginService, private route:Router, private cookies: CookieService) {
    this.service.getWords().subscribe({next: response => {
      this.keywords = response;
    }});
   }

  ngOnInit() {

    let usuario = this.cookies.get("usuario");
    let password = this.cookies.get("password");

    if( usuario == "" && password == "") {
      this.loginService.login(usuario, password).subscribe({
        next: user => {
            this.route.navigate([``]);
        }
      })
  }
    this.filKeywords = this.control.valueChanges.pipe(
      debounceTime(500),
      startWith(''),
      map(val => this._filter(val))
    );
  }

  private _filter(val: string): string[] {
    const formatVal = val.toLocaleLowerCase();
    return this.keywords.filter(keyword => keyword.toLocaleLowerCase().indexOf(formatVal) === 0);
  }

  buscarPorPalabraClave() {
    this.showForm=false;
    this.deckService.getDecksByKeyword(this.keyword).subscribe({next: response => {
      this.decks=response;
    }});
  }

  seleccionar(deck: Deck): void {
    this.showForm=true;
    this.detalle=true;
    this.loginService.getUserByUsername(this.cookies.get("usuario")).subscribe({
      next: u => {
        this.user=u.name;
        this.nombre=deck.name;
        this.nCards=deck.ncards;
        this.nCategories=deck.ncategories;
        this.description=deck.description;
        this.image=deck.image;    
      }
    })
   }

   pdfMazo() { 
    this.deckService.deckPdf(this.nombre).subscribe({
      next: pdf => {
        console.log("Generado PDF del mazo " + pdf);
      }
  });
        
  }
  volverHome() {
    this.route.navigate([`home`]);
  }

  volver() {
    this.showForm=true;
  }
  volverListaResultados() {
    this.detalle = false;
    this.showForm = false;
  }
}
