import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { LoginService } from '../login/login.service';
import { AuthStatus } from '../login/authStatus';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';




@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private http: Http) { }

  loginService = new LoginService(this.http);

  public canActivate() : Observable<boolean> | boolean{
    let authStatus = new AuthStatus();

    if (localStorage.getItem("token") && localStorage.getItem("token") !== "" && localStorage.getItem("token") !== null) {

    return  this.loginService.retrieveAuthStatus(localStorage.getItem("token")).map(

        response => {
          authStatus.code = response.json().code;
          authStatus.message = response.json().message;

          console.log(authStatus);

          if (authStatus != null && authStatus.code == 0) {
              return true;
          } else {

            console.log("not connected, must login token");
            this.router.navigate(['login']);
            return false;
          }
        }

      );


    } else {
      console.log("not connected, must login");
      this.router.navigate(['login']);
      return false;

    }


  }



}
