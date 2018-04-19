import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Title } from '@angular/platform-browser';

import { TdDigitsPipe } from '@covalent/core/common';
import { TdLoadingService } from '@covalent/core/loading';

@Component({
  selector: 'qs-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {

  constructor(private _router: Router) {
  }

  ngOnInit(): void {
    
  }

  login(type) {
    this._router.navigate(['/'+type]);
  }
}
