import { CartElement } from './cartElement';

export class Cart {

  public ID: string;
  public cartElements: CartElement[];
  public timeStamp: Number;
  public customerID: string;
  public totalPrice: Number;

}
