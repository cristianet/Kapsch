package kapsch.demo.Services;

import kapsch.demo.Entities.WeatherRequest;
import kapsch.demo.Repositories.WeatherRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final RestTemplate restTemplate;
    private final WeatherRequestRepository weatherRequestRepository;
    private final String weatherApiBaseUrl;

    @Autowired 
    public WeatherService(RestTemplate restTemplate, WeatherRequestRepository weatherRequestRepository,
                          @Value("${weather.api.base-url}") String weatherApiBaseUrl) {
        this.restTemplate = restTemplate;
        this.weatherRequestRepository = weatherRequestRepository;
        this.weatherApiBaseUrl = weatherApiBaseUrl;
    }

    public String getWeatherForecast(double latitude, double longitude) {
        String url = UriComponentsBuilder.fromHttpUrl(weatherApiBaseUrl + "/forecast")
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("current_weather", true)
                .toUriString();

        try {
            logger.info("Requesting weather forecast from URL: {}", url);
            String weatherData = restTemplate.getForObject(url, String.class);

            saveWeatherRequest(latitude, longitude);

            return weatherData;
        } catch (RestClientException e) {
            logger.error("Error while calling weather API", e);
            return "Error retrieving weather data";
        }
    }

    private void saveWeatherRequest(double latitude, double longitude) {
        WeatherRequest weatherRequest = new WeatherRequest(latitude, longitude, LocalDateTime.now());
        weatherRequestRepository.save(weatherRequest);
        logger.info("Weather request saved successfully: Latitude={}, Longitude={}", latitude, longitude);
    }
    
    public List<WeatherRequest> getAllWeatherRequests() {
        return weatherRequestRepository.findAll();
    }
}