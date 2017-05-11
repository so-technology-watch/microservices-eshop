import { Component, Input, SimpleChanges, animate, style, state, transition, trigger } from '@angular/core';
import { Http } from '@angular/http';
import { Message } from './message';

@Component({
  selector: 'notifications',
  styleUrls: ['./notifications.component.css'],
  templateUrl: './notifications.component.html',
  animations: [
	trigger("fadeInOut", [
  	  state("open", style({opacity: 1})),
  	  state("closed", style({opacity: 0})),
  	  transition("open <=> closed", animate( "2000ms" )),
    ])
  ]
})
export class NotificationsComponent {
	private interval : any;
	@Input()
	private message : Message;
	private state : string;

	constructor(){
		this.state = 'closed';
	}

	ngOnChanges(change : SimpleChanges) : void {
		clearInterval(this.interval);
		this.changeState();
	}

	private changeState() : void {
		this.state = this.message.isVisible() ? 'open' : 'closed';
		setTimeout( () => {
			this.message.setVisible(false);
			this.state = 'closed';
		}, 1000);
	}
	
	private close() : void {
		this.state = 'closed';
	}
}