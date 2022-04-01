package example.city.controller;

import example.city.domain.dto.CityDTO;
import example.city.domain.entity.City;
import example.city.exception.RecordNotFoundException;
import example.city.mapper.CityMapper;
import example.city.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {
    private final CityService service;

    private final JdbcTemplate jdbcTemplate;

    private final CityMapper mapper;

    @GetMapping("/all")
    public @ResponseBody
    List<City> getAllCity() {
        return service.getAllCity();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    CityDTO getCityById(@PathVariable("id") Long id) throws RecordNotFoundException {
        City city = service.getCityById(id);
        return mapper.map(city);
    }

    @PostMapping("/create")
    public void createCity(CityDTO city) {
        service.createOrUpdateCity(mapper.map(city));
    }

    @PostMapping("/update")
    public void updateCity(CityDTO city) {
        service.createOrUpdateCity(mapper.map(city));
    }

    @DeleteMapping("/{id}")
    public void deleteCityById(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteCityById(id);
    }

    @PostMapping("/query")
    public @ResponseBody
    String createCity(@RequestBody String queryString) {
        if (queryString != null && queryString.contains("select")) {
            List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString);
            return list.stream().map(map -> map.keySet().stream().map(key -> key + map.get(key)).collect(Collectors.joining(", "))).collect(Collectors.joining(System.lineSeparator()));
        } else {
            assert queryString != null;
            jdbcTemplate.execute(queryString);
            return "";
        }
    }

}
