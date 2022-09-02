import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Card } from '../model/Card';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  url: string="http://localhost:9000/";
constructor(private http: HttpClient, private cookies: CookieService) { }

createCard(card: Card, deck: string) {
  let headers = new HttpHeaders;
  headers = headers.set('Content-Type', 'application/json; charset=utf-8');
  return this.http.post<Card>(this.url + "Card?deck=" + deck,
  card.toString(),
  {headers: headers}
 )
}

addCategory(idCard: number, category: string) {
  let headers = new HttpHeaders;
  headers = headers.set('Content-Type', 'application/json; charset=utf-8');
  return this.http.post<any>(this.url + "CardCategory?card=" + idCard + "&category=" + category,
  {headers: headers}
 )

}

cardsOfDeck(deck: string) {
  return this.http.get<Card[]>(this.url + 'CardsDeck?deck=' + deck);
}

generateValues(card: Card, values: number[]) {

  let headers = new HttpHeaders;
  headers = headers.set('Content-Type', 'application/json; charset=utf-8');
  return this.http.put<Card>(this.url + "CardValues",
  card.toString(),
  {headers: headers}
 )
}
}
