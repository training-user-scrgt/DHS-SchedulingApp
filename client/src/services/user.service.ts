import { Injectable, Injector } from '@angular/core';
import { Router, NavigationExtras } from "@angular/router";

import { HttpClient, HttpHeaders } from '@angular/common/http';

import { User } from '../models/user.model';
import { Observable } from 'rxjs/Observable';

import { environment } from '../environments/environment';

@Injectable()
export class UserService {

    public baseUrl = environment.apiUrl; 
    private readonly _usersUrl: string = "/user";
    private readonly _userByIdUrl: string = "/user";

    constructor(protected http: HttpClient) {
 
    }

    getUsers() {
        let endpointUrl = this.baseUrl + this._usersUrl;

        return this.http.get<User[]>(endpointUrl, this.getRequestHeaders())
            .catch(error => {
                return Observable.throw(error);//this.handleError(error, () => this.getUsersEndpoint());
            });
    }

    getUserById(id: string) {
        let endpointUrl = this.baseUrl + "/" + id;

        return this.http.get<User>(endpointUrl, this.getRequestHeaders())
            .catch(error => {
                return Observable.throw(error);//this.handleError(error, () => this.getUsersEndpoint());
            });
    }
	
	updateUserById(id: string, User: User) {
        let endpointUrl = this.baseUrl + "/" + id;

        return this.http.put<>(endpointUrl, User, this.getRequestHeaders())
            .catch(error => {
                return Observable.throw(error);//this.handleError(error, () => this.getUsesrsEndpoint());
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
