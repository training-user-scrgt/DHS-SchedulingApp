import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  @Input()  isLoggedIn: boolean;
  @Output() onLogout = new EventEmitter<boolean>();
  
  logout() {
	  this.onLogout.emit(this.isLoggedIn);
  }
}
