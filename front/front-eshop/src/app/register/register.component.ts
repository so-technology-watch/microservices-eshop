import { Component } from '@angular/core';
import { Informations } from './informations';
import { RegisterService } from './register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'register',
  styleUrls: ['./register.component.css'],
  templateUrl: './register.component.html',
  providers: [ RegisterService ]
})
export class RegisterComponent {
	private informations = new Informations();

	constructor(private registerService : RegisterService, private router : Router) {
	}

	submit() : void {
		if(this.valid()){
			this.registerService.register(this.informations)
				.subscribe(
					res => {
						localStorage['id'] = res['id'];
						this.router.navigate(['login'])
					},
					error => {
						console.log('an error occured');
					}
				);
		}
	}

	valid() : boolean {
		this.informations.setEmail(this.informations.getEmail());
		return this.informations.areValid();
	}
}