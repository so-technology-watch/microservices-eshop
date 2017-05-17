import { Products } from '../products/Products';
import { Supplier } from '../products/supplier';
export class Bill {
	public _id : Id
	public products : Products[];
	public supplier : Supplier;
}

export class Id {
	public $oid : string;
}