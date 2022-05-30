import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url: string="http://localhost:9000/";

  constructor(private http: HttpClient) { }
  
  login(user: string, password: string) {

    return this.http.post<boolean>(this.url + "Login/" + user + "/" + password,null);

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
