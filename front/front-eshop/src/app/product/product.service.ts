import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { Products } from '../products/products';
import { gatewayUrl } from '../app.routes'

@Injectable()
export class ProductService {
	private productsUrl = gatewayUrl+'/products'

	constructor(private http : Http){}

	getProduct(id : number) : Observable<Products> {
		let query = this.productsUrl+'/'+id
		return this.http.get(query)
			.map(this.extractData)
			.catch(this.handleError);
	}

	private extractData(res : Response) {
		return res.json() || null;
	}

	private handleError(error : Response | any) {
		return Observable.throw('an error occured');
	}
}