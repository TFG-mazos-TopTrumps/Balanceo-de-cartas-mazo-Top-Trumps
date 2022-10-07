import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Keyword } from '../model/Keyword';

@Injectable({
  providedIn: 'root'
})
export class KeywordService {

  url: string="http://localhost:9000/";
  constructor(private http: HttpClient) { }

  addKeyword(keyword: Keyword, name: string) {
      let headers = new HttpHeaders;
      headers = headers.set('Content-Type', 'application/json; charset=utf-8');
      return this.http.post(this.url + "Keyword?deck=" + name,
      keyword.toString(),
      {headers: headers}
     )
}

  getKeywords() {
    return this.http.get<Array<Keyword>>(this.url + "Keywords");
  }
  getKeywordsByDeck(deck: string) {
    return this.http.get<Keyword[]>(this.url + "KeywordsDeck?deck=" + deck);
  }

  countWords(word: string) {
    return this.http.get<number>(this.url + 'CountKeyword?word=' + word); 
  }

  getWords() {
    return this.http.get<string[]>(this.url + "KeywordsWords");
  }
}