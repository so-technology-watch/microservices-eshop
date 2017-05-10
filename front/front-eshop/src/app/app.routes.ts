import { Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProductsComponent } from './products/products.component';
import { BillsComponent } from './bills/bills.component';
import { BuyComponent } from './buy/buy.component';
import { CartComponent } from './cart/cart.component';
import { DescriptionComponent } from './description/description.component';
import { AuthGuard } from './guards/auth.gard';
import { ProductComponent } from './product/product.component';




export const rootRouterConfig: Routes = [
  { path: '', redirectTo: 'products', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'products', component: ProductsComponent },
  { path: 'cart', component: CartComponent, canActivate: [AuthGuard] },
  { path: 'buy', component: BuyComponent, canActivate: [AuthGuard] },
  { path: 'description', component: DescriptionComponent },
  { path: 'bills', component: BillsComponent, canActivate: [AuthGuard] },
  { path: 'product/:id', component: ProductComponent }
];


export const gatewayUrl : string = 'http://localhost:9090/api/v1';
