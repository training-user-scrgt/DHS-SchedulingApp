import { Injectable } from '@angular/core';

import { environment } from '../environments/environment';



type UserConfiguration = {
};

@Injectable()
export class ConfigurationService {

    public baseUrl = environment.apiUrl;
    
}
