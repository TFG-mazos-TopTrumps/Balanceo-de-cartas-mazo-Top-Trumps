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

  condicionRegistro: boolean = false;

  constructor(private service: LoginService, private route:Router) {
    this.service = service;
   }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
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

    if(this.password==this.confirmPassword) {
      let user = new User();
      user.usuario = this.usuario;
      user.password = this.password;
      user.name = this.name;

      this.service.register(user);
    }
  }

  public cambiarRegistro() {
    if(this.condicionRegistro) {
    this.condicionRegistro=false;
  } else {
    this.condicionRegistro=true;
  }
  }
 

  
}
