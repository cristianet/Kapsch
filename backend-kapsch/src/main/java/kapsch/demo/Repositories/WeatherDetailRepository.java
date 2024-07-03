package kapsch.demo.Repositories;

import kapsch.demo.Entities.WeatherDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDetailRepository extends JpaRepository<WeatherDetail, Long> {
  
}