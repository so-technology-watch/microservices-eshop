import { Component } from '@angular/core';
import { AuthGuard } from './guards/auth.gard';
import { Observable } from 'rxjs/Observable';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ AuthGuard ]
})
export class AppComponent {
	private logged : boolean;
	constructor(private authService: AuthGuard, private router: Router){
		this.logged = false;
		this.checkIsConnected();
        this.router.events.subscribe( (event)=>{
        	if(event instanceof NavigationEnd){
        		this.checkIsConnected();
        	}
        });
	}

	private checkIsConnected() : void {
		let activate = this.authService.canActivate();
		let isOsb = activate instanceof Observable;

		if(isOsb){
			(<Observable<boolean>> activate).subscribe(
				resp => {
					this.logged = resp;
				},
				error => {
					this.logged = false;
				}
			)
		}
	}

	public isLogged(){
		return this.logged;
	}

	public disconnect(){
		localStorage.clear();
		this.logged = false;
	}
}
