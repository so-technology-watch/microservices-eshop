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
	private maxpages : number = 10;
	private pages : number[];
	private actualPage : number;
	private loading : boolean;

	constructor(private productsService : ProductsService){
		this.pages = Array(this.maxpages).fill(1).map((x,i)=>i+1);
		this.loading = false;
	}

	ngOnInit() {
		this.updateProducts(1);
	}

	updateProducts(page : number){
		this.actualPage = page;
		this.loading = true;
		this.productsService.getProducts(page)
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