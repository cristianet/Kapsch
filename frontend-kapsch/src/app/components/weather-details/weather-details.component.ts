import { AfterViewInit, Component, Input, OnChanges, SimpleChanges   } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WeatherService } from '../../services/weather.service';
import { WeatherDetail, WeatherRequest } from '../../models/index';

@Component({
  selector: 'app-weather-details',
  templateUrl: './weather-details.component.html',
  styleUrls: ['./weather-details.component.scss']
})
export class WeatherDetailsComponent implements OnChanges    {

  @Input() request!: number; 
  weatherDetail: WeatherDetail | undefined;
  

  constructor(private weatherService: WeatherService) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['request']) {

    console.log("ENTRE2", this.request);
 
      const id = this.request; 
      this.weatherService.getWeatherDetailById(id).subscribe(
        data => this.weatherDetail = data,
        error => console.error('There was an error!', error)
      );
    } else {
      console.error('Input request is null or undefined.');
    }
  }

}