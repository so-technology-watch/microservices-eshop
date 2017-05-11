import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Cart } from './cart';
import { CartElement } from './cartElement';


@Injectable()
export class CartService {

  private url = 'http://10.226.160.85:9090/api/v1/carts';

  constructor(private http: Http) { }

  public retrieveCart(customerID: string): Observable<Response>{

    let empty = false;
    return this.http.get(this.url + "/" + customerID).map(

      response => response
    ).catch(error => error);
  }

}
