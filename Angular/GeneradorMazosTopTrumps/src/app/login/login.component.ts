import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
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

  notNullUsername: boolean = true;
  notNullPassword: boolean = true;
  notNullConfirmPassword: boolean = true;
  notNullName: boolean = true;
  duplicatedName: boolean = true;
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
    
      let user = new User();
      user.username = this.usuario;
      user.password = this.password;
      user.name = this.name;

      this.notNullUsername=true;
      this.notNullPassword=true;
      this.notNullConfirmPassword=true;
      this.errorPasswordRegister = true;
      this.notNullName=true;
      this.duplicatedName=true;
      let anyError = false;
 
      if(this.usuario==null || this.usuario=="") {
        this.notNullUsername=false;
        anyError=true;
      }

      if(this.password==null || this.password=="") {
        this.notNullPassword=false;
        anyError=true;
      }

      if(this.confirmPassword==null || this.confirmPassword=="") {
        this.notNullConfirmPassword=false;
        anyError=true;
      }

      if(this.password!=this.confirmPassword) {
        this.errorPasswordRegister = false;
        anyError=true;

      }
      if(this.name==null || this.name=="") {
        this.notNullName=false;
        anyError=true;
      }

      

      if(!anyError) {
          this.service.countUserByUsername(this.usuario).subscribe({next: usuario => {
            if(usuario==0) {
              this.service.register(user).subscribe({
                next : respuesta => {
                  console.log(`Registrado, ${JSON.stringify(respuesta)}`) 
                  this.condicionRegistro=true;
                  
                },
                error: e => {
                  console.log(`insertar -> No se ha podido registrar, ${e}`)
                  alert(e)
                }
              })   
            } else {
                  this.duplicatedName=false;
            }
          }})
           
            
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
    
    let usuario = this.cookies.get("usuario");
    let password = this.cookies.get("password");
    if( usuario != "" && password != "") {
        this.service.login(usuario, password).subscribe({
          next: user => {
            
              this.route.navigate([`home`]);
          }
        })
    }

  }
  
}

