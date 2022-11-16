import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import {map,startWith, debounceTime} from 'rxjs/operators';
import { Router } from '@angular/router';
import { Deck } from '../model/Deck';
import { DeckService } from '../service/deck.service';
import { Observable } from 'rxjs';
import { KeywordService } from '../service/keyword.service';
import { LoginService } from '../service/login.service';
import { LoadComponent } from '../load/load.component';
import { MatDialog } from '@angular/material/dialog';


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

  notExistWord: boolean = true;
  notNullBusqueda: boolean = true;
  detalle: boolean = false;
  nombre: string = "";
  nCards: number = 0;
  nCategories: number = 0;
  description: string = "";
  image: string = "";
  user: string = "";
  showForm: boolean = true;
  anyError: boolean = false;
  control = new FormControl;
  filKeywords: Observable<string[]>;
  colorBordes: string;
  colorFondo: string;
  colorPaneles: string;
  colorFuente: string;
  constructor(private service: KeywordService, private deckService: DeckService, private loginService: LoginService, private route:Router, public dialog: MatDialog) {
    this.service.getWords().subscribe({next: response => {
      this.keywords = response;
    }});
   }

  ngOnInit() {

    let usuario = sessionStorage.getItem("usuario");
    let password = sessionStorage.getItem("password");
   

    if( usuario == undefined && password == undefined) {
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

    this.notNullBusqueda=true;
    this.notExistWord=true;
    this.anyError=false;
   
    this.service.countWords(this.keyword).subscribe({
      next: conteo => {
         
    if(this.keyword == null || this.keyword=="") {
      this.notNullBusqueda=false;
      this.anyError=true;
  }

  if(conteo==0) {
    this.notExistWord=false;
    this.anyError=true;
  }
  if(!this.anyError) {
    this.deckService.getDecksByKeyword(this.keyword).subscribe({next: response => {
      this.decks=response;
      this.showForm=false;
    }});
  }

      }
    })
   
  }

  seleccionar(deck: Deck): void {
    this.showForm=true;
    this.detalle=true;
    this.loginService.getUserByUsername(sessionStorage.getItem("usuario")).subscribe({
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
    const load = this.dialog.open((LoadComponent), {
      data: `Puede tardar unos segundos`
    });
    this.deckService.deckPdf(this.nombre).subscribe({
      next: pdf => {
        console.log("Generado PDF del mazo " + pdf);
        load.close();
        this.route.navigate([`success`]);
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
