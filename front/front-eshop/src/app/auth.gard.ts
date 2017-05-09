import  {Injectable } from  '@angular/core';
import { Router, CanActivate } from '@angular/router';


@Injectable()
export class AuthGuard implements CanActivate  {

constructor(private router : Router){}

canActivate( ){

alert("t'as pas le droit ah ah!");
  return false ;

}



}
