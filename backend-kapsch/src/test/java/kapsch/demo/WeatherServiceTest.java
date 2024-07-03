package kapsch.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import kapsch.demo.Entities.WeatherRequest;
import kapsch.demo.Repositories.WeatherRequestRepository;
import kapsch.demo.Services.WeatherService;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class WeatherServiceTest {

	  @Autowired
	    private WeatherService weatherService;

	    @MockBean
	    private RestTemplate restTemplate;

	    @MockBean
	    private WeatherRequestRepository weatherRequestRepository;

	    @Test
	    public void testGetWeatherForecastSuccess() {

	        String expectedResponse = "{\"weather\":\"sunny\"}";
	        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(expectedResponse);

	        String result = weatherService.getWeatherForecast(10.0, 20.0);

	        verify(restTemplate, times(1)).getForObject(anyString(), eq(String.class));

	        assertEquals(expectedResponse, result);

	        //Verificar que el método saveWeatherRequest fue llamado una vez
	        ArgumentCaptor<WeatherRequest> weatherRequestCaptor = ArgumentCaptor.forClass(WeatherRequest.class);
	        verify(weatherRequestRepository, times(1)).save(weatherRequestCaptor.capture());

	        WeatherRequest savedRequest = weatherRequestCaptor.getValue();
	        assertEquals(10.0, savedRequest.getLatitude());
	        assertEquals(20.0, savedRequest.getLongitude());
	        assertNotNull(savedRequest.getRequestTime());
	    }

	    @Test
	    public void testGetWeatherForecastFailure() {
	   
	        when(restTemplate.getForObject(anyString(), eq(String.class))).thenThrow(new RestClientException("API error"));

	   
	        String result = weatherService.getWeatherForecast(10.0, 20.0);

	        // Verificar que el resultado sea el mensaje de error
	        assertEquals("Error retrieving weather data", result);

	        // Verificar que el método saveWeatherRequest no fue llamado
	        verify(weatherRequestRepository, times(0)).save(any());
	    }

	    @Test
	    public void testGetAllWeatherRequests() {

	        WeatherRequest request1 = new WeatherRequest(10.0, 20.0, LocalDateTime.now());
	        WeatherRequest request2 = new WeatherRequest(30.0, 40.0, LocalDateTime.now());
	        when(weatherRequestRepository.findAll()).thenReturn(List.of(request1, request2));


	        List<WeatherRequest> result = weatherService.getAllWeatherRequests();


	        assertNotNull(result);
	        assertEquals(2, result.size());
	        assertTrue(result.contains(request1));
	        assertTrue(result.contains(request2));
	    }

    
    
}