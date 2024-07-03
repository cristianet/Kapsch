import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WeatherRequest, WeatherDetail } from '../models/index';


@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  private baseUrl = 'http://localhost:8080'; // Cambia esta URL si tu backend está en otra dirección

  constructor(private http: HttpClient) { }

  getWeather(latitude: number, longitude: number): Observable<string> {
    const params = new HttpParams()
      .set('latitude', latitude.toString())
      .set('longitude', longitude.toString());
    return this.http.get(`${this.baseUrl}/weather`, { params, responseType: 'text' });
  }

  getAllWeatherRequests(): Observable<WeatherRequest[]> {
    return this.http.get<WeatherRequest[]>(`${this.baseUrl}/weather-requests`);
  }

  getAllWeatherDetails(): Observable<WeatherDetail[]> {
    return this.http.get<WeatherDetail[]>(`${this.baseUrl}/weather-details`);
  }

  getWeatherDetailById(id: number): Observable<WeatherDetail> {
    return this.http.get<WeatherDetail>(`${this.baseUrl}/weather-details/${id}`);
  }
}