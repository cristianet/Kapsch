package kapsch.demo.Repositories;

import kapsch.demo.Entities.WeatherRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRequestRepository extends JpaRepository<WeatherRequest, Long> {
  
}