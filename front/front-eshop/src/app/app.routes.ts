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
import { AccountComponent } from './account/account.component';



export const rootRouterConfig: Routes = [
  { path: '', redirectTo: 'products', pathMatch: 'full' },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'products', component: ProductsComponent, canActivate: [AuthGuard] },
  { path: 'cart', component: CartComponent, canActivate: [AuthGuard] },
  { path: 'buy', component: BuyComponent, canActivate: [AuthGuard] },
  { path: 'description', component: DescriptionComponent, canActivate: [AuthGuard]},
  { path: 'bills', component: BillsComponent, canActivate: [AuthGuard] },
  { path: 'product/:id', component: ProductComponent , canActivate: [AuthGuard]},
  { path: 'account', component : AccountComponent,  canActivate: [AuthGuard]}
];


export const gatewayUrl : string = 'http://10.226.160.85:9090/api/v1';
