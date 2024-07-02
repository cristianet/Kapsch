package kapsch.demo.Entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class WeatherDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weather_request_id")
    private WeatherRequest weatherRequest;

    private LocalDateTime observationTime;

    private double temperature;
    private double windSpeed;
    private int windDirection;


    public WeatherDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeatherRequest getWeatherRequest() {
        return weatherRequest;
    }

    public void setWeatherRequest(WeatherRequest weatherRequest) {
        this.weatherRequest = weatherRequest;
    }

    public LocalDateTime getObservationTime() {
        return observationTime;
    }

    public void setObservationTime(LocalDateTime observationTime) {
        this.observationTime = observationTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }
}