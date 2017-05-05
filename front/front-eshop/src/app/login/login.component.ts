import { Component } from '@angular/core';
import { Headers, RequestOptions, Http } from '@angular/http';


@Component({
  selector: 'login',
  templateUrl: './login.component.html',
})
export class LoginComponent {

  constructor(private http: Http) { }

  submitted = false
  credentials = new Credentials("mail2@mail.fr", "passijjfeij");
  authResponse = new AuthResponse();

  auth() {
    let body = JSON.stringify(this.credentials)
    let url = 'http://10.226.160.79:9090/api/v1/auth';
    let headers = new Headers({ 'content-type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    this.http.post(url, body, options).subscribe(
      response => {

        this.authResponse.code = response.json().code;
        this.authResponse.content = response.json().content;
      },
      err => { console.log() });
  }

  get json() { return JSON.stringify(this.credentials); }

  get authResponseJSON() { return JSON.stringify(this.authResponse); }

}

class Credentials {

  constructor(
    public email: String,
    public password: String
  ) { }
}

class AuthResponse {

  code: Number;
  content: String;

}
