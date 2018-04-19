import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { MatSnackBar } from '@angular/material/snack-bar';

import { TdLoadingService } from '@covalent/core/loading';
import { TdDialogService } from '@covalent/core/dialogs';
import { TdMediaService } from '@covalent/core/media';

import { UserService, IUser } from './services/user.service';
import { Room } from '../../models/room.model';
import { InventoryService } from '../../services/inventory.service';
import { Observable } from 'rxjs/Observable';

import {} from ''
import { of } from 'rxjs';

@Component({
  selector: 'qs-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
})
export class UsersComponent implements OnInit {

  users: IUser[];
  filteredUsers: IUser[];
  rooms: Room[];

  constructor(private _titleService: Title,
              private _loadingService: TdLoadingService,
              private _dialogService: TdDialogService,
              private _snackBarService: MatSnackBar,
              private _userService: UserService,
              private _changeDetectorRef: ChangeDetectorRef,
              private _inventoryService: InventoryService,
              public media: TdMediaService) {
  }

  ngOnInit(): void {
    this._titleService.setTitle('Covalent Users');
    this.load();
    this._inventoryService.getRooms().subscribe(rooms => this.rooms = rooms);
  }

  filterUsers(displayName: string = ''): void {
    this.filteredUsers = this.users.filter((user: IUser) => {
      return user.displayName.toLowerCase().indexOf(displayName.toLowerCase()) > -1;
    });
  }

  async load(): Promise<void> {
    try {
      this._loadingService.register('users.list');
      this.users = await this._userService.query().toPromise();
    } catch (error) {
      this.users = await this._userService.staticQuery().toPromise();
    } finally {
      this.filteredUsers = Object.assign([], this.users);
      this._loadingService.resolve('users.list');
    }
  }

  delete(id: string): void {
    this._dialogService
      .openConfirm({message: 'Are you sure you want to delete this user?'})
      .afterClosed().toPromise().then((confirm: boolean) => {
        if (confirm) {
          this._delete(id);
        }
      });
  }

  private async _delete(id: string): Promise<void> {
    try {
      this._loadingService.register('users.list');
      await this._userService.delete(id).toPromise();
      this.users = this.users.filter((user: IUser) => {
        return user.id !== id;
      });
      this.filteredUsers = this.filteredUsers.filter((user: IUser) => {
        return user.id !== id;
      });
      this._snackBarService.open('User Deleted', 'Ok');
    } catch (error) {
      this._dialogService.openAlert({message: 'There was an error trying to delete the user'});
    } finally {
      this._loadingService.resolve('users.list');
    }
  }

}
