package kapsch.demo.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    
    private final RestTemplate restTemplate;
    private final String weatherApiBaseUrl;

    public WeatherService(RestTemplate restTemplate, @Value("${weather.api.base-url}") String weatherApiBaseUrl) {
        this.restTemplate = restTemplate;
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
            return restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
            logger.error("Error while calling weather API", e);
            return "Error retrieving weather data";
        }
    }
}