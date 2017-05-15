import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { gatewayUrl } from '../app.routes';

@Injectable()
export class BuyService {
	private buyUrl : string = gatewayUrl+'/pay';
	constructor(private http : Http) {}

	buy(callbackOnError : any) : Observable<any> {
		let customer = JSON.parse(localStorage['customer']);
    	let id: string = customer['id'];
		let query = this.buyUrl+'/'+id;
		let headers = new Headers({ 'Content-Type': 'application/json' });
    	let options = new RequestOptions({ headers: headers });
		return this.http.post(query, headers)
			.map(this.extractData)
			.catch((error) => {
				callbackOnError();
				return Observable.throw('an error occured');
			});
	}

	private extractData(res : Response) {
		return res.json() || {}
	}
}