import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

import { AccountService } from './account.service';
import { Customer } from '../login/customer';

@Component({
  selector: 'account',
  styleUrls: ['./account.component.css'],
  templateUrl: './account.component.html',
  providers: [AccountService]
})

export class AccountComponent implements OnInit {
	private informations : Customer;
	private progress : number;
	private fields : number = 5;

	constructor(private accountService : AccountService) {
		this.progress = 0;
	}

	save() : void {
		this.accountService.saveInformations(this.informations);
	}

	onChange() : void {
		let valid = this.validQuantity() * 10 * 2;
		this.progress = valid;
	}

	ngOnInit() {
		this.accountService.getInformations()
			.subscribe(
				informations => {
					this.informations = informations;
					this.onChange();
				},
				error => {
					console.log(error)
				}
			);
	}

	private validQuantity() : number {
		let quantity : number = 0;
		let info = this.informations;
		if(info == null) return 0;
		let fields = [info.lastname, info.firstname, info.email, info.address, info.phoneNumber];
		for(let field of fields){
			if(this.isValid(field)){
				quantity++;
			}
		}
		return quantity;
	}

	private isValid(field : string) {
		return field != null && field.trim().length > 0
	}
}