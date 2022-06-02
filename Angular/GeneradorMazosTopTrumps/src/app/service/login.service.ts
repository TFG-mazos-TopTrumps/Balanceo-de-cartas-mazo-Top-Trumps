import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { User } from '../model/User';
import { CookieService } from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url: string="http://localhost:9000/";

  constructor(private http: HttpClient, private cookies: CookieService) { }
  
  login(user: string, password: string) {

    let headers= new HttpHeaders()
      .set('content-type', 'application/json')
      .set('Access-Control-Allow-Origin', '*');
      return this.http.post<boolean>(this.url + 'Login?username=' + user + '&password=' + password, Boolean, {headers: headers});

  }

  register(user: User) {
    let headers = new HttpHeaders;
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post(this.url + "Register",
    user.toString(),
    {headers: headers}
   )
  }

  

  
}
