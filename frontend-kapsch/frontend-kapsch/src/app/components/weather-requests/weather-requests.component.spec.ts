import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeatherRequestsComponent } from './weather-requests.component';

describe('WeatherRequestsComponent', () => {
  let component: WeatherRequestsComponent;
  let fixture: ComponentFixture<WeatherRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeatherRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeatherRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
