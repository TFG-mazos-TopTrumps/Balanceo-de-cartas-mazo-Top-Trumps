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

   

    if(usuario==undefined && password==undefined) {
      this.loginService.login(usuario, password).subscribe({
        next: user => {
            this.router.navigate([``]);
            sessionStorage.removeItem("categoriesCompleted");
        sessionStorage.removeItem("cardsCompleted");
        sessionStorage.removeItem("balanceCompleted");
        sessionStorage.removeItem("deck");
        sessionStorage.removeItem("valueMin");
        sessionStorage.removeItem("valueMax");
  
        let indice = 0;
        while(true) {
          let c = sessionStorage.getItem("category " + indice);
          if(c != undefined) {
          
         sessionStorage.removeItem("category " + indice)
          indice++;
  
        }
          if(c == undefined) {
            break;
          }
  
        }    
        }
      })
    }
  }

  volver() {
        

    this.router.navigate([`home`]);
  }

}
