import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
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
  errorMaxLengthUsername : boolean = true;
  errorMaxLengthPassword : boolean = true;
  errorMaxLengthName: boolean = true;
  duplicatedName: boolean = true;
  condicionRegistro: boolean = true;
  alertRegistred: boolean = false;
  

  constructor(private service: LoginService, private route:Router) {
    this.service = service;
   }
 

  public login() {

    this.service.login(this.usuario, this.password)
                .subscribe((respuesta) => {
                  if(respuesta) {

                    sessionStorage.setItem("usuario",this.usuario);
                    sessionStorage.setItem("password",this.password);
                    this.alertRegistred=false;
                    this.route.navigate(['home']);
                  } else {
                    this.falloUsuario = false;
                    this.alertRegistred = false;
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
      this.errorMaxLengthUsername=true;
      this.errorMaxLengthPassword=true;
      this.errorMaxLengthName=true;
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
      if(this.usuario.length >= 45) {
        this.errorMaxLengthUsername=false;
        anyError=true;
      }
      if(this.password.length >= 45) {
        this.errorMaxLengthPassword=false;
        anyError=true;
      }
      if(this.name.length >= 50) {
        this.errorMaxLengthName=false;
        anyError=true;
      }


      

      if(!anyError) {
          this.service.countUserByUsername(this.usuario).subscribe({next: usuario => {
            if(usuario==0) {
              this.service.register(user).subscribe({
                next : respuesta => {
                  console.log(`Registrado, ${JSON.stringify(respuesta)}`) 
                  this.condicionRegistro=true;
                  this.alertRegistred=true;
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
    
    let usuario = sessionStorage.getItem("usuario");
    let password = sessionStorage.getItem("password");
   
    if(usuario != undefined && password != undefined) {
        this.service.login(usuario, password).subscribe({
          next: user => {
            
              this.route.navigate([`home`]);
          }
        })
    }

  }
  
}

