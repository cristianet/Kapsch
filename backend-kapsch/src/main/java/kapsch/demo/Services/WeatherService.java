package kapsch.demo.Services;

import kapsch.demo.Entities.WeatherDetail;
import kapsch.demo.Entities.WeatherRequest;
import kapsch.demo.Repositories.WeatherDetailRepository;
import kapsch.demo.Repositories.WeatherRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final RestTemplate restTemplate;
    private final WeatherRequestRepository weatherRequestRepository;
    private final WeatherDetailRepository weatherDetailRepository;
    private final String weatherApiBaseUrl;

    @Autowired 
    public WeatherService(RestTemplate restTemplate, WeatherRequestRepository weatherRequestRepository, WeatherDetailRepository weatherDetailRepository,
                          @Value("${weather.api.base-url}") String weatherApiBaseUrl) {
        this.restTemplate = restTemplate;
        this.weatherRequestRepository = weatherRequestRepository;
        this.weatherDetailRepository = weatherDetailRepository;
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

            saveWeatherRequest(latitude, longitude, weatherData);

            return weatherData;
        } catch (RestClientException e) {
            logger.error("Error while calling weather API", e);
            return "Error retrieving weather data";
        }
    }

    private void saveWeatherRequest(double latitude, double longitude, String responseJson) {
        WeatherRequest weatherRequest = new WeatherRequest(latitude, longitude, LocalDateTime.now());
        weatherRequestRepository.save(weatherRequest);
         
        try {
        JsonNode jsonNode = new ObjectMapper().readTree(responseJson);
        
        if (jsonNode.has("current_weather")) {
            JsonNode currentWeather = jsonNode.get("current_weather");
            WeatherDetail weatherDetail = new WeatherDetail();
            weatherDetail.setTemperature(currentWeather.path("temperature").asDouble());
            weatherDetail.setWindSpeed(currentWeather.path("windspeed").asDouble());
            weatherDetail.setWindDirection(currentWeather.path("winddirection").asDouble());
            weatherDetail.setWeatherRequest(weatherRequest);

            weatherDetailRepository.save(weatherDetail);
            

        } 
        } catch (JsonMappingException e) {
         
        	logger.error("Error de E/S al mappear JSON: {}", e.getMessage());             
        } catch (JsonProcessingException e) {
        	logger.error("Error de E/S al procesar JSON: {}", e.getMessage()); 
		}
        

        
        logger.info("Weather request saved successfully: Latitude={}, Longitude={}", latitude, longitude);
    }
    
    public List<WeatherRequest> getAllWeatherRequests() {
        return weatherRequestRepository.findAll();
    }
    
    public List<WeatherDetail> getAllWeatherDetails() {
        return weatherDetailRepository.findAll();
    }
    
    public Optional<WeatherDetail> getWeatherDetailsById(Long id) {
     return weatherDetailRepository.findById(id);
    }
}