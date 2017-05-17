import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { gatewayUrl } from '../app.routes';
import { Bill } from './bill';

@Injectable()
export class BuyService {
  private buyUrl: string = gatewayUrl + '/pay';
  constructor(private http: Http) { }

	buy(callbackOnError : any) : Observable<Bill> {
		let customer = JSON.parse(localStorage['customer']);
    	let id: string = customer['id'];
		let query = this.buyUrl+'/'+id;
		let headers = new Headers();
    	let options = new RequestOptions({ headers: headers });
		return this.http.post(query, headers)
			.map(this.extractData)
			.catch((error) => {
				callbackOnError();
				return Observable.throw(error);
			});
	}

	private extractData(res : Response) {
		return res.json() || {}
	}
}
