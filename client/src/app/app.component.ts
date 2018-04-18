import { Component } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'DHS Scheduling Application';

  constructor(private router: Router){
		this.router = router;
  }
  
  isLoggedIn(){
		return this.router.url !== '/';
	}

  onLogout(_isLoggedIn: boolean) {
		this.router.navigate(['login']);
	}
}
