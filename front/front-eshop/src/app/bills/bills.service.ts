import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { gatewayUrl } from '../app.routes';
import { Bill } from '../buy/bill';
import { Products } from '../products/Products';
import { SharedService } from '../notifications/shared.service';

@Injectable()
export class BillsService {
	private billsUrl : string = gatewayUrl+'/bills/user';
	private productsUrl : string = gatewayUrl+'/products';
	constructor(private http : Http, private sharedService : SharedService) {}

	getBills() : Observable<Bill[]> {
		let customer = JSON.parse(localStorage['customer']);
		let token = localStorage['token'];
    	let id: string = customer['id'];
		let query = this.billsUrl+'/'+id;
		return this.http.get(query, this.sharedService.getAuthorizationHeader(token))
			.map(this.extractData)
			.catch((error) => {
				return Observable.throw(error);
			});
	}

	setImages(bills : Bill[]) : void {
		bills.forEach((bill) => {
			bill.products.forEach((product) => {
				if(!product.image){
					let query = this.productsUrl+'/'+product.id;
					this.getProductInformations(query)
						.subscribe(
							result => {
								product.image = result.image;
							},
							error => {
								this.sharedService.displayNotification('Une errreur est survenue lors du chargement d\'une image', false);
							}
						)
				}
			})
		})
	}

	private getProductInformations(query : string) : Observable<Products> {
		return this.http.get(query)
			.map(this.extractData)
			.catch((error)=> {
				return Observable.throw(error)
			})
	}

	private extractData(res : Response) {
		return res.json() || {}
	}

}