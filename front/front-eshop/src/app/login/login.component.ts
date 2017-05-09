import { Component } from '@angular/core';
import { Headers, RequestOptions, Http } from '@angular/http';
import { Credentials } from './credentials';
import { AuthResponse } from './authResponse';
import { LoginService } from './login.service';


@Component({
  selector: 'login',
  templateUrl: './login.component.html',
})
export class LoginComponent {

  constructor(private http: Http) { }


  submitted = false
  credentials = new Credentials("mail2@mail.fr", "passijjfeij");
  authResponse = new AuthResponse();
  loginService = new LoginService(this.http);

  auth() {

    this.authResponse = this.loginService.authenticate(this.credentials);

    if (this.authResponse != null ){
      
    }
  }
  get json() { return JSON.stringify(this.credentials); }
  get authResponseJSON() { return JSON.stringify(this.authResponse); }

}
