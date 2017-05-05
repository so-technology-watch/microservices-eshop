import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { Products } from './products';

@Injectable()
export class ProductsService {
	private productsUrl : string = 'http://localhost:9090/api/v1/products';
	private productsPerPage : number = 5;
	constructor(private http : Http){}

	getProducts(page : number) : Observable<Products[]> {
		let begin = (page * this.productsPerPage) - 1;
		let end = begin + this.productsPerPage;
		let query = this.productsUrl+'?range='+begin+'-'+end;

		return this.http.get(query)
			.map(this.extractData)
			.catch(this.handleError);
	}

	private extractData(res : Response) {
		return res.json() || []
	}

	private handleError(error : Response | any) {
		return Observable.throw('an error occured');
	}
}