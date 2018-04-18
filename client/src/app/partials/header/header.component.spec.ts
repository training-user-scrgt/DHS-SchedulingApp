import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';

import { HeaderComponent } from './header.component';
import { ActivatedRoute, Router } from '@angular/router';
//  import { Router, ActivatedRoute, ParamMap } from '@angular/router';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  //  it(`should create`, async(inject([HttpTestingController, ApiService],   (httpClient: HttpTestingController, apiService: ApiService)
   it(`should create`, async(inject([Router], (Router: Router) => {
  // it('should create', () => {
    expect(component).toBeTruthy();
  })));
  // });
});
