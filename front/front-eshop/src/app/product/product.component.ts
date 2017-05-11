import { Component, OnInit, OnDestroy, ViewContainerRef } from '@angular/core';
import { Http } from '@angular/http';

import { ProductService } from './product.service';
import { Products } from '../products/products';
import { NavigationEnd, Router, ActivatedRoute } from '@angular/router';
import { SuccessfullAddComponent } from './successfulladd.component';

@Component({
  selector: 'product',
  styleUrls: ['./product.component.css'],
  templateUrl: './product.component.html',
  providers: [ProductService]
})

export class ProductComponent implements OnInit, OnDestroy {
	private id : number;
	private sub : any;
	private product : Products = null;
	private show : boolean;

	constructor(private router : Router, private route : ActivatedRoute, private productService : ProductService){
		this.show = false;
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

	ngOnDestroy() {
		this.sub.unsubscribe();
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

	ajoutPanier(id : number, price : number) {
		this.show = true;
		this.productService.ajouterProduit(id, price);
	}

	private errorImage(event){
		let target = event.target;
		let baseURI = target.baseURI;
		target.src = baseURI+'assets/notfound.png';
	}
}