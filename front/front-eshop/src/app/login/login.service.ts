import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';
import {AuthResponse} from './authResponse';
import {Credentials} from './credentials';


@Injectable()
export class LoginService {

  private url = 'http://10.226.160.79:9090/api/v1/auth';


  constructor(private http: Http) { }

  public authenticate(credentials : Credentials) {

    let body = JSON.stringify(credentials)
    let headers = new Headers({ 'content-type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    let authResponse = new AuthResponse();

    this.http.post(this.url, body, options).subscribe(
      response => {
        authResponse.code = response.json().code;
        authResponse.content = response.json().content;

      },
      err => {
         console.log();
       authResponse = null; });

      return authResponse;
  }

}
