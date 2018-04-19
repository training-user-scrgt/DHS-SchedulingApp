import { Injectable, Injector } from '@angular/core';
import { Router, NavigationExtras } from "@angular/router";

import { InventoryEndpoint } from './inventory-endpoint.service';
import { Room } from '../models/room.model';

@Injectable()
export class InventoryService {

    constructor(private inventoryService: InventoryService,
        private inventoryEndpoint: InventoryEndpoint) {

    }

    getRooms() {
        return this.inventoryEndpoint.getRoomsEndpoint();
    }
}
