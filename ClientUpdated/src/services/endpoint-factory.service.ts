import { Injectable, Injector } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/operator/catch';

import { ConfigurationService } from './configuration.service';


@Injectable()
export class EndpointFactory {

    constructor(protected http: HttpClient, protected configurations: ConfigurationService, private injector: Injector) {

    }

    protected getRequestHeaders(): { headers: HttpHeaders } {
        let headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Accept': `application/json, text/plain, */*`
        });

        return { headers: headers };
    }

    protected handleError(error, continuation: () => Observable<any>) {

        return Observable.throw(error);

    }
}
