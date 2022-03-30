package example.city.controller;

import example.city.domain.entity.City;
import example.city.exception.RecordNotFoundException;
import example.city.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {
    private final CityService service;

    @GetMapping
    public ResponseEntity<List<City>> getAllCity() {
        List<City> list = service.getAllCity();
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") Long id) throws RecordNotFoundException {
        City city = service.getCityById(id);
        return new ResponseEntity<>(city, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> createOrUpdateCity(City city) throws RecordNotFoundException {
        City updated = service.createOrUpdateCity(city);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteCityById(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteCityById(id);
        return HttpStatus.FORBIDDEN;
    }

}
