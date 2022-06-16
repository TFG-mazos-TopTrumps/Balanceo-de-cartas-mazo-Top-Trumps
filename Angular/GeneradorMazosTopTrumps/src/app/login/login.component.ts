import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { User } from '../model/User';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  falloUsuario = true;
  errorPasswordRegister = true;

  usuario: string = "";
  password: string = "";
  confirmPassword: string = "";
  name: string = "";

  condicionRegistro: boolean = true;
  

  constructor(private service: LoginService, private route:Router, private cookies: CookieService) {
    this.service = service;
   }
 

  public login() {

    this.service.login(this.usuario, this.password)
                .subscribe((respuesta) => {
                  if(respuesta) {
                    this.cookies.set("usuario", this.usuario);
                    this.cookies.set("password", this.password);
                    this.route.navigate(['home']);
                  } else {
                    this.falloUsuario = false;
                  }
                });
 
  }
  
  
  public register() {

    
    if(this.password==this.confirmPassword) {
      let user = new User();
      user.username = this.usuario;
      user.password = this.password;
      user.name = this.name;

      this.service.register(user).subscribe({
        next : respuesta => {
          console.log(`Registrado, ${JSON.stringify(respuesta)}`) 
          
        },
        error: e => {
          console.log(`insertar -> No se ha podido registrar, ${e}`)
          alert(e)
        }
      })   
      
    } else {
     this.errorPasswordRegister = false;
    }
  
  }

  public cambiarRegistro() {
    if(this.condicionRegistro) {
    this.condicionRegistro=false;
  } else {
    this.condicionRegistro=true;
  }
  }
 
  
  ngOnInit() {

  }
  
}
