import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Deck } from '../model/Deck';

@Injectable({
  providedIn: 'root'
})
export class DeckService {

  url: string="http://localhost:9000/";
  constructor(private http: HttpClient, private cookies: CookieService) { }

  getDeckByName(name: string) {
    return this.http.get<Deck>(this.url + 'DeckName?name=' + name); 
  }

  getDecksByKeyword(word: string) {
    return this.http.get<Deck[]>(this.url + 'DecksKeyword?keyword=' + word);
  }

  //getDeckId() {
    //return this.http.get<number>(this.url + 'DeckId'); 
  //}

  countDecksName(name: string) {
    return this.http.get<number>(this.url + 'CountDeckName?name=' + name); 
  }
  
  createDeck(deck: Deck, username: string, password: string) {
    let headers = new HttpHeaders;
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<boolean>(this.url + "Deck?username=" + username + "&password=" + password,
    deck.toString(),
    {headers: headers}
   )
  }

  generateValues(cards: number, categories: number, valueMin: number, valueMax: number) {
    let headers = new HttpHeaders;
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<any>(this.url + "DeckGenerateValues?cards=" + cards + "&categories=" + categories
        + "&lowerLimit=" + valueMin + "&upperLimit=" + valueMax,
    {headers: headers})

  }

  balanceDeck(cards: number, categories: number, valueMin: number, valueMax: number, deck: string) {
    let headers = new HttpHeaders;
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.http.put<any>(this.url + "DeckBalance?cards=" + cards + "&categories=" + categories
        + "&lowerLimit=" + valueMin + "&upperLimit=" + valueMax + "&deck=" + deck,
    {headers: headers})

  }

  deckPdf(deck: string) {

    let headers = new HttpHeaders;
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<any>(this.url + "DeckPDF?deck=" + deck,
    {headers: headers})

  }

  checkKeywords(deck:string) {
    return this.http.get<boolean>(this.url + 'CheckKeywords?name=' + deck); 
  }
 
  publishDeck(deck: string) {

    let headers = new HttpHeaders;
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.http.put<any>(this.url + "DeckPublish?deck=" + deck,
    {headers: headers})

  }

  noPublishDeck(deck: string) {

    let headers = new HttpHeaders;
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.http.put<any>(this.url + "DeckNoPublish?deck=" + deck,
    {headers: headers})

  }


}
