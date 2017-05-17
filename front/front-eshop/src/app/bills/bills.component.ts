import { Component, OnInit } from '@angular/core';
import { BillsService } from './bills.service';
import { Bill } from '../buy/bill';
import { SharedService } from '../notifications/shared.service';


@Component({
  selector: 'bills',
  templateUrl: './bills.component.html',
  providers: [BillsService],
  styleUrls: ['bills.component.css']
})

export class BillsComponent implements OnInit {
	private bills : Bill[];

	constructor(private billsService : BillsService, private sharedService : SharedService){
		this.bills = [];
	}
	
	ngOnInit(){
		this.billsService.getBills().subscribe(
			result => {
				this.bills = result;
				this.billsService.setImages(this.bills);
			},
			error => {
				this.sharedService.displayNotification('Impossible d\'afficher vos commandes', false);
			}
		);
	}

	private errorImage(event){
		let target = event.target;
		let baseURI = target.baseURI;
		target.src = baseURI+'assets/notfound.png';
	}
}
