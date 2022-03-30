package example.city.repository;

import example.city.domain.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface CityRepository
        extends CrudRepository<City, Long> {
}
