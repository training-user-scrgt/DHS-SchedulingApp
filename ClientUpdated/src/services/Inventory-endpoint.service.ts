import { Injectable, Injector } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { EndpointFactory } from './endpoint-factory.service';
import { ConfigurationService } from './configuration.service';
import { Room } from '../models/room.model';

@Injectable()
export class InventoryEndpoint extends EndpointFactory {

    private readonly _roomsUrl: string = "/rooms";
    
    get roomsUrl() { return this.configurations.baseUrl + this._roomsUrl; }
    
    constructor(http: HttpClient, configurations: ConfigurationService, injector: Injector) {

        super(http, configurations, injector);
    }

    getRoomsEndpoint(): Observable<Room[]> {
        let endpointUrl = this.roomsUrl;

        return this.http.get<Room[]>(endpointUrl, this.getRequestHeaders())
            .catch(error => {
                return this.handleError(error, () => this.getRoomsEndpoint());
            });
    }
}
