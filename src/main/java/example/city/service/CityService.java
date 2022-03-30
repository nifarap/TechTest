package example.city.service;

import example.city.domain.entity.City;
import example.city.exception.RecordNotFoundException;
import example.city.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Repository
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> getAllCity() {
        List<City> cityList = (List<City>) cityRepository.findAll();

        if (cityList.size() > 0) {
            return cityList;
        } else {
            return new ArrayList<City>();
        }
    }

    public City getCityById(Long id) throws RecordNotFoundException {
        Optional<City> city = cityRepository.findById(id);

        if (city.isPresent()) {
            return city.get();
        } else {
            throw new RecordNotFoundException("No city record exist for given id");
        }
    }

    public City createOrUpdateCity(City city_entity) {
        Optional<City> city = cityRepository.findById(city_entity.getId());

        if (city.isPresent()) {
            City newCity = city.get();
            newCity.setName(city_entity.getName());
            newCity.setCountryCode(city_entity.getCountryCode());
            newCity.setDistrict(city_entity.getDistrict());
            newCity.setPopulation(city_entity.getPopulation());

            newCity = cityRepository.save(city_entity);

            return newCity;
        } else {
            city_entity = cityRepository.save(city_entity);

            return city_entity;
        }
    }

    public void deleteCityById(Long id) throws RecordNotFoundException {
        Optional<City> city = cityRepository.findById(id);

        if (city.isPresent()) {
            cityRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No city record exist for given id");
        }
    }
}
