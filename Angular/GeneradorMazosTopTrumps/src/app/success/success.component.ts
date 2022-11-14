import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.css']
})
export class SuccessComponent implements OnInit {

  constructor(private router : Router, private loginService: LoginService) { }

  ngOnInit() {
    let usuario = sessionStorage.getItem("usuario");
    let password = sessionStorage.getItem("password");

   

    if( usuario == undefined && password == undefined) {
      this.loginService.login(usuario, password).subscribe({
        next: user => {
            this.router.navigate([``]);
        }
      })
  }
  }

  volver() {
        

    this.router.navigate([`home`]);
  }

}
