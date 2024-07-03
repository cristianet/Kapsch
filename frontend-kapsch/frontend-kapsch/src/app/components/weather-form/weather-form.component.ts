import { Component } from '@angular/core';
import { WeatherService } from '../../services/weather.service';

@Component({
  selector: 'app-weather-form',
  templateUrl: './weather-form.component.html',
  styleUrls: ['./weather-form.component.scss']
})
export class WeatherFormComponent {
  latitude: number  = 52.52;
  longitude: number  = 13.41;
  weather!: string;

  constructor(private weatherService: WeatherService) { }

  onSubmit() {
    this.weatherService.getWeather(this.latitude, this.longitude).subscribe(
      data => this.weather = data,
      error => console.error('There was an error!', error)
    );
  }
}