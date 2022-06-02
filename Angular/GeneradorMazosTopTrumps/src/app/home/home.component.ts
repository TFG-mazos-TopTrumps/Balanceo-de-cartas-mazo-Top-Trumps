import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private route:Router, private cookies: CookieService) { }

  ngOnInit() {
  }

  public logout() {
    this.cookies.delete("usuario");
    this.route.navigate([``]);
  }

  public buscar() {
    this.route.navigate([`buscar`]);
  }
}
