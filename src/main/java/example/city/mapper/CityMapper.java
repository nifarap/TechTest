package example.city.mapper;

import example.city.domain.dto.CityDTO;
import example.city.domain.entity.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    City map(CityDTO dto);

    CityDTO map(City entity);
}
