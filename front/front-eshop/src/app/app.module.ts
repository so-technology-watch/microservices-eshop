import { NgModule } from '@angular/core'
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpModule } from '@angular/http';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

import { AppComponent } from './app.component';
import { rootRouterConfig } from './app.routes';

import { LoginComponent } from './login/login.component';
import { ProductsComponent } from './products/products.component';
import { BillsComponent} from './bills/bills.component';
import { BuyComponent} from './buy/buy.component';
import { CartComponent} from './cart/cart.component';
import { DescriptionComponent} from './description/description.component';
import { ProductComponent } from './product/product.component';
import { SuccessfullAddComponent } from './product/successfulladd.component';
import {AuthGuard} from './auth.gard';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProductsComponent,
    BillsComponent,
    BuyComponent,
    CartComponent,
    DescriptionComponent,
    SuccessfullAddComponent,
    ProductComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(rootRouterConfig, { useHash: true })
  ],
  providers: [AuthGuard],
  bootstrap: [ AppComponent ]
})
export class AppModule {

}
