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
    return this.http.get<Deck[]>(this.url + 'Decks?keyword=' + word);
  }

  getDeckId() {
    return this.http.get<number>(this.url + 'DeckId'); 
  }
  
  createDeck(deck: Deck, username: string, password: string) {
    let headers = new HttpHeaders;
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<Deck>(this.url + "Deck?username=" + username + "&password=" + password,
    deck.toString(),
    {headers: headers}
   )
  }

 

}
