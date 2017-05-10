import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

import { ProductsService } from './products.service';
import { Products } from './products';

@Component({
  selector: 'products',
  styleUrls: ['./products.component.css'],
  templateUrl: './products.component.html',
  providers: [ProductsService]
})

export class ProductsComponent implements OnInit {
	private errorMessage : string;
	private products : Products[];
	private maxpages : number = 1;
	private productsPerPage : number = 9;
	private pages : number[];
	private actualPage : number;
	private loading : boolean;
	private criteria : string;

	constructor(private productsService : ProductsService) {
		this.pages = [];
		this.initPages();
		this.loading = false;
	}

	private initPages() : void {
		this.productsService
			.getCount()
			.subscribe(
				count => {
					this.maxpages = Math.floor(count / this.productsPerPage);
					console.log(this.productsPerPage, count)
					this.updatePages();
				},
				error => {
					this.updatePages();
					error => this.errorMessage = <any>error
				}
			);
	}

	private updatePages() : void {
		this.pages = Array(this.maxpages).fill(1).map((x,i)=>i+1);
	}

	ngOnInit() {
		this.updateProducts(1);
	}

	updateProducts(page : number){
		this.actualPage = page;
		this.loading = true;
		this.productsService
			.getProducts(page, this.productsPerPage)
			.subscribe(
				products => {
					this.products = products;
					this.loading = false;
				},
				error => this.errorMessage = <any>error
			);
	}

	private typingTimer = null;
	search(event) : void {
		if(event){
			let self : ProductsComponent = this;
			clearTimeout(this.typingTimer);
			this.typingTimer = setTimeout( () => {
				self.search(null);
			}, 1000);
			return;
		}

		if(!this.criteria || this.criteria == '' ){
			this.updateProducts(this.actualPage);
			return;
		}
		this.loading = true;
		this.productsService
			.search(this.criteria)
			.subscribe(
				products => {
					this.products = products;
					this.loading = false;
				},
				error => this.errorMessage = <any>error
			);
	}

	getProducts() : Products[] {
		return this.products;
	}

	getActualPage() {
		return this.actualPage;
	}

}