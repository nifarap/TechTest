package example.city.service;

import example.city.domain.entity.City;
import example.city.exception.RecordNotFoundException;
import example.city.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> getAllCity() {
        List<City> cityList = (List<City>) cityRepository.findAll();

        if (cityList.size() > 0) {
            return cityList;
        } else {
            return Collections.emptyList();
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

    public City createOrUpdateCity(City cityEntity) {
        Optional<City> city = cityRepository.findById(cityEntity.getId());

        if (city.isPresent()) {
            City newCity = city.get();
            newCity.setName(cityEntity.getName());
            newCity.setCountryCode(cityEntity.getCountryCode());
            newCity.setDistrict(cityEntity.getDistrict());
            newCity.setPopulation(cityEntity.getPopulation());

            newCity = cityRepository.save(cityEntity);

            return newCity;
        } else {
            cityEntity = cityRepository.save(cityEntity);

            return cityEntity;
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
