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

}
