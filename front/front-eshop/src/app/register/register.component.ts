import { Component } from '@angular/core';
import { Informations } from './informations';

@Component({
  selector: 'register',
  styleUrls: ['./register.component.css'],
  templateUrl: './register.component.html',
})
export class RegisterComponent {
	private informations = new Informations();

	constructor() {

	}

	valid() {
		this.informations.setEmail(this.informations.getEmail());
		return this.informations.areValid();
	}
}