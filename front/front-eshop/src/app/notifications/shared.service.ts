import { Injectable } from '@angular/core'
import { AppComponent } from '../app.component';

@Injectable()
export class SharedService {
	private rootComponent : AppComponent;

	constructor() {
	}

	displayNotification(message : string, success : boolean) : void {
		this.rootComponent.setMessage(message, success, true);
	}

	public setRootComponent(rootComponent : AppComponent) : void {
		this.rootComponent = rootComponent;
	}
}