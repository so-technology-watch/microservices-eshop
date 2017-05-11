import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { gatewayUrl } from '../app.routes';
import { Customer } from '../login/customer';

@Injectable()
export class AccountService {
	private accountUrl : string = gatewayUrl+'/customers';
	constructor(private http : Http) {}

	getInformations() : Observable<Customer> {
		let customer = JSON.parse(localStorage['customer']);
    	let id: string = customer['id'];
		let query = this.accountUrl+'/'+id;
		return this.http.get(query)
			.map(this.extractData)
			.catch(this.handleError);
	}

	saveInformations(informations : Customer) {
		let headers = new Headers({ 'Content-Type': 'application/json' });
    	let options = new RequestOptions({ headers: headers });
		let observable : Observable<any> = this.http.post(this.accountUrl, informations, options)
			.map(this.extractData)
			.catch(this.handleError)
		observable.subscribe(
			resp => {
				console.log(resp);
			},
			error => {
				console.log('unexpected error');
			}
		);
	}

	private extractData(res : Response) {
		return res.json() || []
	}

	private handleError(error : Response | any) {
		return Observable.throw('an error occured');
	}
}