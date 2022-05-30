import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  falloNombre = true;
  falloPassword = true;

  usuario: string = "";
  password: string = "";
  confirmPassword: string = "";
  name: string = "";

  condicionRegistro: boolean = true;

  constructor(private service: LoginService, private route:Router) {
    this.service = service;
   }
 

  public login() {
    if(this.service.login(this.usuario, this.password)) {
        this.route.navigate([`home`]);
    } else {
      this.falloNombre = false;
      this.falloPassword = false;
    }
  }
  
  public register() {

    let user = new User();
    if(this.password==this.confirmPassword) {
      
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
