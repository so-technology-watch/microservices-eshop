//var jwtDecode = require('jwt-decode');
import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {JwtHelper } from 'angular2-jwt';
import {Customer} from './customer';
import {Credentials} from './credentials';



export class CustomerService {


  constructor(private http : Http){}

  private url = 'http://10.226.159.191:9090/api/v1/customers';
  JwtHelper = new JwtHelper();

  public retrieveCustomer(token : string) : Observable<Response>{


    let id = this.JwtHelper.decodeToken(token).customerID;

    return this.http.get(this.url + "/" + id)

    .map(
      response => response
    )
    .catch (error => Observable.throw("an error occured"));

  }

}
