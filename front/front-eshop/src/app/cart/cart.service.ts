import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Cart } from './cart';
import { CartElement } from './cartElement';


@Injectable()
export class CartService {

  private url = 'http://10.226.160.85:9090/api/v1/carts';

  constructor(private http: Http) { }

  public retrieveCart(customerID: string): Observable<Response> {

    let empty = false;
    return this.http.get(this.url + "/" + customerID).map(

      response => response
    ).catch(error => Observable.throw("an error occured"));
  }


  public ajouterProduit(id: number, price: number) {
    let customer = JSON.parse(localStorage['customer']);
    let idCustomer: string = customer['id'];
    this.retrieveCartAndAdd(id, price, idCustomer);

  }

  public removeElement(id: number) {

    let customer = localStorage.getItem("customer");
    console.log(customer);
    console.log(JSON.parse(customer));
    let customerID = JSON.parse(customer).id;

    return this.http.delete(this.url + "/" + customerID + "/elements/" + id )
      .map(response => { response.json(); })
      .catch(error => Observable.throw("an error occured"));
  }

  private retrieveCartAndAdd(id: number, price: number, idCustomer: string) {
    let observable: Observable<any> = this.http.get(this.url + '/' + idCustomer)
      .map(this.extractData)
      .catch(this.handleError);
    observable.subscribe(
      cart => {
        this.addCart(cart, id, price);
      },
      error => {
        this.addCartEmpty(id, price, idCustomer);
      }
    );
  }

  /**
  * ajoute le produit à un panier existant, ou increment son nombre
  */
  private addCart(cart: any, id: number, price: number): void {
    let elements = cart.cartElements;
    let contains = false;
    let elementID = elements.length + 1;
    for (let element of elements) {
      if (element.productID == id) {
        element.quantity++;
        contains = true;
        break;
      }
    }
    if (!contains) {
      let product = {
        elementID: elementID,
        productID: id,
        quantity: 1,
        unitPrice: price
      }
      elements.push(product)
    }
    this.createCart(cart);
  }

  /**
  * créer le panier avec le nouveau produit
  */
  private addCartEmpty(id: number, price: number, idCustomer: string): void {
    let data = {
      id: idCustomer,
      cartElements: [{
        elementID: 1,
        productID: id,
        quantity: 1,
        unitPrice: price
      }],
      timeStamp: new Date().getTime() + '',
      customerID: idCustomer,
      totalPrice: 0.000000
    };
    this.createCart(data);
  }

  private createCart(data: any): void {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    let observable: Observable<any> = this.http.post(this.url, data, options)
      .map(this.extractData)
      .catch(this.handleError);
    observable.subscribe(
      resp => {
        console.log(resp);
      },
      error => {
        console.log("unexecpected error");
      }
    );
  }

  private extractData(res: Response) {
    return res.json() || null;
  }

  private handleError(error: Response | any) {
    return Observable.throw('an error occured');
  }

}
