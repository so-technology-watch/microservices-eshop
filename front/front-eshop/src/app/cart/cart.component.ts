import { Component, OnInit } from '@angular/core';
import { Cart } from './cart';
import { CartElement } from './cartElement';
import { CartService } from './cart.service';
import { Http, Response } from '@angular/http';
import { Products } from '../products/products';
import { ProductService } from '../product/product.service';
import { SharedService } from '../notifications/shared.service';


@Component({
  selector: 'cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})

export class CartComponent implements OnInit {

  private cart: Cart = new Cart();
  private cartService: CartService;
  private empty: boolean = false;
  private products: any = {};
  private productService: ProductService;

  constructor(private http: Http) {
    this.cartService = new CartService(this.http);
    this.productService = new ProductService(this.http);
  }

  ngOnInit() {

    this.init();
  }

  public init() {

    let id = JSON.parse(localStorage.getItem("customer")).id;
    this.cartService.retrieveCart(id).subscribe(

      response => {
        this.cart = response.json();
        let tempMap = {};
        response.json().cartElements.forEach(element => {
          this.productService.getProduct(element.productID.valueOf()).subscribe(

            response => {
              tempMap[element.productID] = response
            }
          );

          this.products = tempMap;
        });

      },
      error => {
        if (error instanceof Response && error.status === 404) {
          this.empty = true;
        }
      }
    );
  }

  private updateCart(event, element: CartElement, price: number) {

    console.log(element);
    this.cartService.ajouterProduit(element.productID.valueOf(), price);
    element.quantity = element.quantity.valueOf() + 1;
    alert("product successfully added");

  }

  private removeElement(id : number){

    this.cartService.removeElement(id);
  }

  private keys() {
    console.log(Object.keys(this.products))
    return Object.keys(this.products);
  }

  private errorImage(event) {
    let target = event.target;
    let baseURI = target.baseURI;
    target.src = baseURI + 'assets/notfound.png';
  }

}
