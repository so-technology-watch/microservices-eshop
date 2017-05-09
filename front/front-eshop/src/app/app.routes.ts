import { Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { ProductsComponent } from './products/products.component';
import { BillsComponent} from './bills/bills.component';
import { BuyComponent} from './buy/buy.component';
import { CartComponent} from './cart/cart.component';
import { DescriptionComponent} from './description/description.component';


export const rootRouterConfig: Routes = [
  { path: '', redirectTo: 'products', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'products', component: ProductsComponent },
  {path: 'cart', component: CartComponent},
  {path: 'buy', component: BuyComponent},
  {path: 'description', component: DescriptionComponent},
  {path: 'bills', component: BillsComponent}
];
