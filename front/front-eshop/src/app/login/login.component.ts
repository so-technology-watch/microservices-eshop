import { Component } from '@angular/core';
import { Headers, RequestOptions, Http } from '@angular/http';
import { Credentials } from './credentials';
import { AuthResponse } from './authResponse';
import { LoginService } from './login.service';
import { AuthStatus } from './authStatus';


@Component({
  selector: 'login',
  styleUrls: ['./login.component.css'],
  templateUrl: './login.component.html',
})
export class LoginComponent {

  constructor(private http: Http) { }


  submitted = false
  credentials = new Credentials("mail2@mail.fr", "passijjfeij");
  authResponse = new AuthResponse();
  authStatus = new AuthStatus();
  loginService = new LoginService(this.http);

  auth() {

    this.loginService.authenticate(this.credentials).subscribe(

      response => {
        this.authResponse.code = response.json().code;
        this.authResponse.content = response.json().token;


        if (this.authResponse != null) {

          localStorage.setItem("token", this.authResponse.content);

        }
      }
    );

  }
  get json() { return JSON.stringify(this.credentials); }
  get authResponseJSON() { return JSON.stringify(this.authResponse); }

}
