import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { MatSnackBar } from '@angular/material/snack-bar';

import { TdLoadingService } from '@covalent/core/loading';
import { TdDialogService } from '@covalent/core/dialogs';
import { InventoryService } from '../../../services/inventory.service';
import { Room } from '../../../models/room.model';

@Component({
  selector: 'qs-room-form',
  templateUrl: './roomForm.component.html',
  styleUrls: ['./roomForm.component.scss'],
})
export class RoomFormComponent implements OnInit {

  room : Room;
  id: string;
  
  constructor(private _router: Router, 
    private _route: ActivatedRoute,
    private _inventoryService: InventoryService) {}

  goBack(): void {
    this._router.navigate(['/rooms']);
  }

  ngOnInit(): void {
    this._route.params.subscribe((params: {id: string}) => {
      this.id = params.id;
      if (this.id) {
        this.load();
      }
    });
  }

  load(){
    try {
      this._inventoryService.getRoomById(this.id).subscribe(room => this.room = room);
    } catch (error) {
      
    } 
  }

  save(){
    } 
  }

}
