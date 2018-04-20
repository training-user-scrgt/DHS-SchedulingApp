import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from '@angular/common/http';

import {User} from '../models/user.model';
import {Observable} from 'rxjs/Observable';

import {environment} from '../environments/environment';

@Injectable()
export class UserService {

    public baseUrl = environment.userApi;
    private readonly _usersUrl: string = "/user";

    constructor(protected http: HttpClient) {
    }

    getUsers() {
        let endpointUrl = this.baseUrl + this._usersUrl;

        return this.http.get<User[]>(endpointUrl, this.getRequestHeaders())
            .catch(error => {
                return Observable.throw(error);//this.handleError(error, () => this.getUsersEndpoint());
            });
    }

    protected getRequestHeaders(): { headers: HttpHeaders } {
        let headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Accept': 'application/json, text/plain, */*'
        });

        return {headers: headers};
    }
}