import { Injectable } from '@angular/core'
import { AppComponent } from '../app.component';
import { Tabs } from '../app.tabs';

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
}