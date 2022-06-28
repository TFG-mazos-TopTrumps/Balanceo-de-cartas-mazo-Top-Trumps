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

  getDecks(name: string) {
    return this.http.get<Deck[]>(this.url + 'Decks?name=' + name); 
  }

  getDecksByKeyword(word: string) {
    return this.http.get<Deck[]>(this.url + 'Decks?keyword=' + word);
  }

  createDeck(deck: Deck, idUser: number) {
    let headers = new HttpHeaders;
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<Deck>(this.url + "Deck?idUser=" + idUser,
    deck.toString(),
    {headers: headers}
   )
  }

 

}
