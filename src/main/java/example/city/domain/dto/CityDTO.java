package example.city.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityDTO implements Serializable {
    private Long id;

    private String name;

    private String countryCode;

    private String district;

    private Long population;
}