import { Credentials } from '../login/credentials';

export class Informations {

	private firstname : string;
	private lastname : string;
	private email : string;
	private address : string;
	private phoneNumber : string;
	private credentials : Credentials = new Credentials('','');

	areValid() {
		let b =
			this.isValid(this.firstname) &&
			this.isValid(this.lastname) &&
			this.isValid(this.email) &&
			this.isValid(this.address) &&
			this.isValid(this.phoneNumber) &&
			this.isValidPhone(this.phoneNumber) &&
			this.isValid(this.credentials.getPassword()) &&
			this.isValid(this.credentials.getEmail());
		return b && this.credentials.getEmail() == this.email;
	}

	isValid(field) : boolean {
		return field != null && field.length != 0;
	}

	isValidPhone(phone) : boolean {
		return phone != null && phone.match(/^\d{10,14}$/) != null;
	}

	isValidMail(mail) : boolean {
		return mail != null && mail.match(/\S+@\S+\.\S+/) != null;
	}

	setEmail(email : string){
		this.email = email;
		this.credentials.setEmail(email);
	}

	getEmail() : string {
		return this.email;
	}

}