import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { JwtHelper } from 'angular2-jwt';
import { Customer } from './customer';
import { Credentials } from './credentials';
import { gatewayUrl } from '../app.routes';

@Injectable()
export class CustomerService {
  private url = gatewayUrl+'/customers';
  private jwtHelper : JwtHelper;

  constructor(private http : Http){
    this.jwtHelper = new JwtHelper();
  }


  public retrieveCustomer(token : string) : Observable<Response> {
    let id = this.jwtHelper.decodeToken(token).customerID;

    return this.http.get(this.url + "/" + id)
      .map(response => response)
      .catch (error => Observable.throw("an error occured"));

  }

}
