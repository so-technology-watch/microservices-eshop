import { Component, OnInit, OnDestroy } from '@angular/core';
import { Http } from '@angular/http';

import { ProductService } from './product.service';
import { Products } from '../products/products';
import { NavigationEnd, Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'product',
  styleUrls: ['./product.component.css'],
  templateUrl: './product.component.html',
  providers: [ProductService]
})

export class ProductComponent implements OnInit, OnDestroy {
	private id : number;
	private sub : any;
	private product : Products = null

	constructor(private router : Router, private route : ActivatedRoute, private productService : ProductService){
	}

	goPreviousPage() : void {
		this.router.navigate(['products']);
	}

	ngOnInit() {
		this.sub = this.route.params.subscribe(params => {
       		this.id = +params['id'];
       		this.updateProductInformations();
       	});
	}

	updateProductInformations(){
		this.productService.getProduct(this.id)
       			.subscribe(
       				product =>  {
       					this.product = product;
       				},
       				error => {
       					this.product = null;
       				}
   				);
	}

	ngOnDestroy() {
		this.sub.unsubscribe();
	}
}