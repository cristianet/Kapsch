import { Component, OnInit } from '@angular/core';
import { WeatherService } from '../../services/weather.service';
import { WeatherRequest } from '../../models/index';
import { Router } from '@angular/router';

@Component({
  selector: 'app-weather-requests',
  templateUrl: './weather-requests.component.html',
  styleUrls: ['./weather-requests.component.scss']
})
export class WeatherRequestsComponent implements OnInit {
  weatherRequests!: WeatherRequest[];
  selectedRequest: number = 0;

  constructor(private weatherService: WeatherService) { }

  ngOnInit(): void {
    this.fetchWeatherRequests();
  }

  fetchWeatherRequests(): void {
    this.weatherService.getAllWeatherRequests().subscribe(
      data => this.weatherRequests = data,
      error => console.error('Error fetching weather requests', error)
    );
  }

  viewDetails(request: number) {
    this.selectedRequest = request;
    console.log("entre", this.selectedRequest);
  }

}