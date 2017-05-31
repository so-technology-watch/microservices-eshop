import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { Products } from './products';
import { gatewayUrl } from '../app.routes'

@Injectable()
export class ProductsService {
	private productsUrl : string = gatewayUrl+'/products';
	constructor(private http : Http){}

	getProducts(page : number, productsPerPage : number) : Observable<Products[]> {
		let begin = ( (page - 1) * productsPerPage );
		let end = begin + productsPerPage;
		let query = this.productsUrl+'?range='+begin+'-'+end;

		return this.http.get(query)
			.map(this.extractData)
			.catch(this.handleError);
	}

	search(criteria : string) : Observable<Products[]> {
		let query = this.productsUrl+'?criterias='+criteria
		return this.http.get(query)
			.map(this.extractData)
			.catch(this.handleError);
	}

	getCount() : Observable<number> {
		let query = this.productsUrl+'?count=true';
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
