import { Component, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  name: string;
  username: string;
  password: string;
  constructor(private loginService: LoginService, private route:Router) {
       this.loginService.getUserByUsername(sessionStorage.getItem("usuario")).subscribe({
        next: res => {
          this.name=res.name;
        }
       })
   }

  public logout() {
    sessionStorage.removeItem("usuario");
    sessionStorage.removeItem("password");
   
    this.route.navigate([``]);
  }

  public buscar() {
    this.route.navigate([`buscar`]);
  }

  public design() {
    this.route.navigate(['deck']);
  }

  ngOnInit() {
    
   
    let usuario = sessionStorage.getItem("usuario");
    let password = sessionStorage.getItem("password");

   

    if( usuario == undefined && password == undefined) {
      this.loginService.login(usuario, password).subscribe({
        next: user => {
            this.route.navigate([``]);
        }
      })
  }
  }
}
