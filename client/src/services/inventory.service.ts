import { Injectable, Injector } from '@angular/core';
import { Router, NavigationExtras } from "@angular/router";

import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Room } from '../models/room.model';
import { Observable } from 'rxjs/Observable';

import { environment } from '../environments/environment';

@Injectable()
export class InventoryService {

    public baseUrl = environment.apiUrl; 
    private readonly _roomsUrl: string = "/room";
    private readonly _roomByIdUrl: string = "/room";

    constructor(protected http: HttpClient) {
 
    }

    getRooms() {
        let endpointUrl = this.baseUrl + this._roomsUrl;

        return this.http.get<Room[]>(endpointUrl, this.getRequestHeaders())
            .catch(error => {
                return Observable.throw(error);//this.handleError(error, () => this.getRoomsEndpoint());
            });
    }

    getRoomById(id: string) {
        let endpointUrl = this.baseUrl + this._roomByIdUrl + "/" + id;

        return this.http.get<Room>(endpointUrl, this.getRequestHeaders())
            .catch(error => {
                return Observable.throw(error);//this.handleError(error, () => this.getRoomsEndpoint());
            });
    }

    saveRoomEquip(id: string) {
        let endpointUrl = this.baseUrl + this._roomByIdUrl + "/" + id;

        return this.http.get<Room>(endpointUrl, this.getRequestHeaders())
            .catch(error => {
                return Observable.throw(error);//this.handleError(error, () => this.getRoomsEndpoint());
            });
    }

    protected getRequestHeaders(): { headers: HttpHeaders } {
        let headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Accept': `application/json, text/plain, */*`
        });

        return { headers: headers };
    }
}
