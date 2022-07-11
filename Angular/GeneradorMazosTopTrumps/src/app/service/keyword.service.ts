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
}