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
  
  getUserByUsername(username: string) {
    return this.http.get<User>(this.url + "UserByUsername?username=" + username);

  }
  getIdUser(username: string, password: string) {
    return this.http.get<number>(this.url + "UserId?username=" + username + "&password=" + password);
  }
  
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

  countUserByUsername(user: string) {
      return this.http.get<number>(this.url + 'CountUserByUsername?username=' + user);

  }

  

  
}
