import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { MatSnackBar } from '@angular/material/snack-bar';

import { TdLoadingService } from '@covalent/core/loading';
import { TdDialogService } from '@covalent/core/dialogs';
import { TdMediaService } from '@covalent/core/media';

import { Room } from '../../models/room.model';
import { InventoryService } from '../../services/inventory.service';
import { Observable } from 'rxjs/Observable';
import { ITdDataTableColumn } from '@covalent/core/data-table';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.scss']
})
export class RoomsComponent implements OnInit {
  columns: ITdDataTableColumn[] = [
    { name: 'building',  label: 'Building Name' },
    { name: 'roomName', label: 'Room Name' },
    { name: 'capacity', label: 'Occupancy' },
  ];
  rooms: Room[];
  available: boolean;

  constructor( private _inventoryService: InventoryService) { }

  ngOnInit() {
    this._inventoryService.getRooms().subscribe(rooms => this.rooms = rooms);
  }

}
