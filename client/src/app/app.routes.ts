import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MainComponent } from './main.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';

import { RoomsComponent } from './rooms/rooms.component'
import { ReservationsComponent } from './reservations/reservations.component'
import { ReportComponent } from './report/report.component'

const routes: Routes = [{
    path: 'login',
    component: LoginComponent,
  }, {
    path: '',
    component: MainComponent,
    children: [{
        component: DashboardComponent,
        path: '',
      }, {
        path: '',
        loadChildren: './users/users.module#UsersModule',
      }, 
    ],
  }, {
    path: 'rooms',
    component: RoomsComponent,
  }, {
    path: 'reservations',
    component: ReservationsComponent,
  }, {
    path: 'report',
    component: ReportComponent,
  },
];

export const appRoutes: any = RouterModule.forRoot(routes);