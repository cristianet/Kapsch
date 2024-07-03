export interface WeatherRequest {
    id: number;
    latitude: number;
    longitude: number;
    requestTime: string;
  }

  export interface WeatherDetail {
    id: number;
    temperature: number;
    windSpeed: number;
    windDirection: number;
  }