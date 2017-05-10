import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Cart } from './cart';
import { CartElement } from './cartElement';


@Injectable()
export class LoginService {

  private url = 'http://10.226.160.85:9090/api/v1/auth';


  constructor(private http: Http) { }

public retrieveCart(customerID){


}

}
