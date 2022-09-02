import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  username: string;
  password: string;
  constructor(private loginService: LoginService, private route:Router, private cookies: CookieService) {

   }

  public logout() {
    this.cookies.delete("usuario");
    this.cookies.delete("password");
    this.route.navigate([``]);
  }

  public buscar() {
    this.route.navigate([`buscar`]);
  }

  public design() {
    this.route.navigate(['deck']);
  }

  ngOnInit() {
    let usuario = this.cookies.get("usuario");
    let password = this.cookies.get("password");

    if( usuario == "" && password == "") {
      this.loginService.login(usuario, password).subscribe({
        next: user => {
            this.route.navigate([``]);
        }
      })
  }
  }
}
