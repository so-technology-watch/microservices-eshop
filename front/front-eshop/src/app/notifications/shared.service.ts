import { Injectable } from '@angular/core'
import { AppComponent } from '../app.component';
import { Tabs } from '../app.tabs';
import { gatewayUrl } from '../app.routes';
import { RequestOptions, Headers } from '@angular/http';

@Injectable()
export class SharedService {
	private rootComponent : AppComponent;

	constructor() {
	}

	displayNotification(message : string, success : boolean) : void {
		this.rootComponent.setMessage(message, success, true);
	}

	changerOnglet(num : Tabs) : void {
		this.rootComponent.changeOnglet(num);
	}

	public setRootComponent(rootComponent : AppComponent) : void {
		this.rootComponent = rootComponent;
	}

	public getAuthorizationHeader(token : string) : RequestOptions {
		let headers = new Headers(
			{ 
				'Content-Type': 'application/json',
				'Authorization': 'Bearer '+token
			});
    	let options = new RequestOptions({ headers: headers });
    	return options;
	}
}