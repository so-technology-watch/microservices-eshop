import { Supplier } from './supplier';

export class Products {
	public id : number;
	public reference : string;
	public designation : string;
	public price : number;
	public idSupplier : number;
	public image : string;
	public idCategory : number;
	public supplier : Supplier;
	public quantiy : number;
}