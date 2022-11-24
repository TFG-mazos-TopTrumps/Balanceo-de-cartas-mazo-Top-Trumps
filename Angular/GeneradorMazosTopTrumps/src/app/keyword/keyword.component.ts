import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Keyword } from '../model/Keyword';
import { DeckService } from '../service/deck.service';
import { KeywordService } from '../service/keyword.service';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-keyword',
  templateUrl: './keyword.component.html',
  styleUrls: ['./keyword.component.css']
})
export class KeywordComponent implements OnInit {

  notNullKeyword: boolean = true;
  errorMaxLengthKeyword: boolean = true;
  errorDuplicateKeyword: boolean = true;
  anyError:boolean = false;
  alertaExito: boolean = false;
  keyword: string;
  keywords: Array<string> = []; 
  idDeck: number;
  condicionKeyword: boolean = false;
  confirmKeyword: boolean = true;
  minKeywords: boolean = true;
  keywordsDeck: Keyword[]; 
  unexpectedError: boolean = true;
  emptyKeywords: boolean = true;
  constructor(private userService: LoginService, private route : ActivatedRoute, private deckService: DeckService, private keywordService: KeywordService, private router:Router) {
    
   }

  ngOnInit() {
    let usuario = sessionStorage.getItem("usuario");
    let password = sessionStorage.getItem("password");
 

    if(usuario==undefined && password==undefined) {
      this.userService.login(usuario, password).subscribe({
        next: user => {
            this.router.navigate([``]);
            sessionStorage.removeItem("categoriesCompleted");
        sessionStorage.removeItem("cardsCompleted");
        sessionStorage.removeItem("balanceCompleted");
        sessionStorage.removeItem("deck");
        sessionStorage.removeItem("valueMin");
        sessionStorage.removeItem("valueMax");
  
        let indice = 0;
        while(true) {
          let c = sessionStorage.getItem("category " + indice);
          if(c != undefined) {
          
         sessionStorage.removeItem("category " + indice)
          indice++;
  
        }
          if(c == undefined) {
            break;
          }
  
        }    
        }
      })
    } else {

  this.keywordService.getKeywordsByDeck(sessionStorage.getItem("deck")).subscribe({
    next: kws => {
      this.keywordsDeck=kws;
      if(this.keywordsDeck.length==0) {
        this.emptyKeywords=false;
      }
    }
  });
}
  
  }

  
  deckKeyword() {

    this.notNullKeyword=true;
    this.errorMaxLengthKeyword=true;
    this.errorDuplicateKeyword=true;
    this.unexpectedError=true;
    this.alertaExito=false;
    this.emptyKeywords
   
    let anyError = false;
    let keyword = new Keyword();
    keyword.word = this.keyword;
   
    let name = sessionStorage.getItem("deck");


    if(keyword.word==null || keyword.word=="") {
      this.notNullKeyword=false;
      anyError=true;
    }

    if(keyword.word.length >= 45) {
      this.errorMaxLengthKeyword=false;
      anyError=true;
    }
    for(const k of this.keywordsDeck) {
      if(this.keyword == k.word) {
          this.errorDuplicateKeyword=false;
          anyError=true;
  
      }
    }

    if(!anyError) {
        this.keywordService.addKeyword(keyword, name).subscribe({
          next: respuesta => {
            if(!respuesta) {
              this.unexpectedError=false;
              
            } else {
              console.log(`Registrado, ${JSON.stringify(respuesta)}`) 
              this.confirmKeyword=true;
              this.alertaExito=true;
              this.minKeywords=true;
              this.emptyKeywords=true;
  
              this.keywordService.getKeywordsByDeck(name).subscribe({
                next: kws => {
                  this.keywordsDeck=kws;
                }
              });
           
            
          }
            
          },
          error: e => {
            console.log(`insertar -> No se ha podido registrar, ${e}`)
            alert(e)
          }
        });
      }
    this.keyword="";
    
      this.confirmKeyword=true;
    
  }
  
  nextCategories() {
    let deck = sessionStorage.getItem("deck");

    this.deckService.checkKeywords(deck).subscribe({
      next: ck => {

        if(ck) {
          this.minKeywords=false;
        } else {
          this.router.navigate(['/card']);
        }
    }
  });
    

  }
}
