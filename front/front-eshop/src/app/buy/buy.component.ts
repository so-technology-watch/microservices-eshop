import { Component } from '@angular/core';
import { BuyService } from './buy.service';
import { SharedService } from '../notifications/shared.service';
import { Bill } from './bill';

@Component({
  selector: 'buy',
  templateUrl: './buy.component.html',
  styleUrls: ['buy.component.css'],
  providers: [BuyService]
})

export class BuyComponent{
	private valid : boolean;
	private cardNumber : string;
	private expirationDate : string;
	private cryptogramme : string;
	private progress : number;
	private bills : Bill;
	private paid : boolean;

	constructor(private buyService : BuyService, private sharedService : SharedService){
		this.valid = false;
		this.progress = 0;
		this.paid = false;
	};

  private isValid(): boolean {
    return this.isValidDate() && this.isValidCard() && this.isValidCrypto();
  }

	onChange() : void {
		let nbValid = 0;
		for(let isValid of [this.isValidCard, this.isValidCrypto, this.isValidDate]){
			if(isValid.bind(this)()) nbValid ++;
		}
		this.progress = (nbValid * 10 * 10/3);
	}

	private buy() : void {
		if(!this.isValid()) return;
		this.buyService.buy(() => {
			this.sharedService.displayNotification('Une erreur est servenue', false);
		}).subscribe(
			result => {
				this.bills = result;
				this.paid = true;
			}
		);
	}
  
  private isValidCard(): boolean {
    if (this.cardNumber == null) return false;
    let card: string = this.cardNumber.split('-').join('');
    let validCard: boolean = new RegExp("^[0-9]{16}$").test(card);
    return validCard;
  }

  private isValidCrypto(): boolean {
    let validCrypt: boolean = new RegExp("^[0-9]{3}$").test(this.cryptogramme);
    return validCrypt;
  }

  private isValidDate(): boolean {
    let date = new Date(this.expirationDate);
    let validDate: boolean = date.toString() != 'Invalid Date';
    return validDate;
  }

	private onChangeCard() : void {
		let card : string = this.cardNumber;
		card = card.split('-').join('');
		let newCard = '';
		for(let i = 0; i < Math.ceil( (card.length/4) ); i++){
			let sl : string = card.slice(i*4, (i*4)+4);
			newCard += sl;
			if(sl.length == 4 && newCard.length <= 16){
				newCard += '-';
			}
		}
		this.cardNumber = newCard;
	}
	private changeActive() : void {
		this.sharedService.changerOnglet(4);
	}

}
