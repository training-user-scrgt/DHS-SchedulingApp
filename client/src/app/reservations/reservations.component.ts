import { Component, OnInit, ChangeDetectorRef, ViewContainerRef } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { MatSnackBar } from '@angular/material/snack-bar';

import { TdLoadingService } from '@covalent/core/loading';
import { TdDialogService } from '@covalent/core/dialogs';
import { TdMediaService } from '@covalent/core/media';

import { Room } from '../../models/room.model';
import { InventoryService } from '../../services/inventory.service';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.scss']
})
export class ReservationsComponent implements OnInit {

  constructor(
   private _dialogService: TdDialogService,
    private _viewContainerRef: ViewContainerRef) { }

  ngOnInit() {
  }

  openConfirm(): void {
    this._dialogService.openConfirm({
      message: 'Plase confirm reservation!',
      disableClose: true || false, 
      viewContainerRef: this._viewContainerRef,
      title: 'Confirm Room Reservation', 
      cancelButton: 'Cancel',
      acceptButton: 'Reserve', 
      width: '500px',
    }).afterClosed().subscribe((accept: boolean) => {
      if (accept) {
        // DO SOMETHING
      } else {
        // DO SOMETHING ELSE
      }
    });
  }

}
