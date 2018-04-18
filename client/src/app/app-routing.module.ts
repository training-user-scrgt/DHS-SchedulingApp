import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PanelComponent } from './panel/panel.component';

const appRoutes: Routes = [


  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
 
  {
		path: 'home',
		component: DashboardComponent
  },
  {
		path: 'login',
		component: LoginComponent
	},
  {
		path: 'panel',
		component: PanelComponent
  },
   {
		path: '**',
    redirectTo: 'home'
  },
 
];

@NgModule({
  imports: [RouterModule.forRoot(
    appRoutes,
    {
      enableTracing: false // <-- debugging purposes only
    }
  )],
  exports: [RouterModule]
})
export class AppRoutingModule { }
