import { HttpClient } from '@angular/common/http';
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


}
