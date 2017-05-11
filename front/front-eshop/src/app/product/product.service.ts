import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { Products } from '../products/products';
import { gatewayUrl } from '../app.routes'

@Injectable()
export class ProductService {
	private productsUrl = gatewayUrl+'/products';
	private cartUrl = gatewayUrl+'/carts';

	constructor(private http : Http){}

	getProduct(id : number) : Observable<Products> {
		let query = this.productsUrl+'/'+id
		return this.http.get(query)
			.map(this.extractData)
			.catch(this.handleError);
	}

	ajouterProduit(id : number, price : number) {
    	let customer = JSON.parse(localStorage['customer']);
    	let idCustomer: string = customer['id'];
    	this.retrieveCartAndAdd(id, price, idCustomer);
    	
	}

	private retrieveCartAndAdd(id : number, price : number, idCustomer : string){
		let observable : Observable<any> = this.http.get(this.cartUrl+'/'+idCustomer)
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
	private addCart(cart : any, id : number, price : number) : void {
		let elements = cart.cartElements;
		let contains = false;
		let elementID = elements.length+1;
		for(let element of elements){
			if(element.productID == id){
				element.quantity ++;
				contains = true;
				break;
			}
		}
		if(!contains){
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
	private addCartEmpty(id : number, price : number, idCustomer : string) : void {
		let data = {
    		id: idCustomer,
    		cartElements:  [{
    			elementID: 1,
    			productID: id,
    			quantity: 1,
    			unitPrice: price
    		}],
			timeStamp: new Date().getTime()+'',
			customerID: idCustomer,
			totalPrice: 0.000000
    	};
    	this.createCart(data);
	}

	private createCart(data : any) : void{
		let headers = new Headers({ 'Content-Type': 'application/json' });
    	let options = new RequestOptions({ headers: headers });
		let observable : Observable<any> = this.http.post(this.cartUrl, data, options)
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

	private extractData(res : Response) {
		return res.json() || null;
	}

	private handleError(error : Response | any) {
		return Observable.throw('an error occured');
	}
}