import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Informations } from './informations';
import { gatewayUrl } from '../app.routes'

@Injectable()
export class RegisterService {

  private url = gatewayUrl+'/customers';


  constructor(private http: Http) { }

  public register(informations : Informations): Observable<Response> {

    let body = JSON.stringify(informations);
    let headers = new Headers({ 'content-type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.url, body, options)
                    .map(this.extractData)
                    .catch(this.handleError);
  }
  
  private extractData(res : Response) {
    return res.json() || null;
  }

  private handleError(error : Response | any) {
    return Observable.throw('an error occured');
  }
}