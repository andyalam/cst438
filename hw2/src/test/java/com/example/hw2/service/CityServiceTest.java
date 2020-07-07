package com.example.hw2.service;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.hw2.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CityServiceTest {

    @Autowired
    private CityService cityService;

    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private WeatherService weatherService;

    @Test
    public void testFindCity() {
        String cityName = "Test Francisco";

        Country country = new Country("USTEST", "Some country name");
        City city = new City(11, cityName, "USTEST", "test district", 4000);
        List<City> cities = new ArrayList<City>();
        cities.add(city);

        TempAndTime tempAndTime = new TempAndTime(310.63, 1593994942, 25200);

        given(weatherService.getTempAndTime(cityName)).willReturn(tempAndTime);
        given(cityRepository.findByName(cityName)).willReturn(cities);
        given(countryRepository.findByCode("USTEST")).willReturn(country);

        CityInfo result = cityService.getCityInfo(cityName);
        CityInfo expected = new CityInfo(
                11, cityName, "USTEST", "Some country name",
                "test district", 4000, 99.46400000000003, "1593994942"
        );

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testFindCityFail() {
        String cityName = "Non-existent City";
        List<City> cities = new ArrayList<City>();

        given(cityRepository.findByName(cityName)).willReturn(cities);

        CityInfo result = cityService.getCityInfo(cityName);
        assertThat(result).isEqualTo(null);
    }

    @Test
    public void testFindCities() {
        String cityName = "Los Angeles";

        Country country = new Country("USTEST", "Some country name");
        City city = new City(11, cityName, "USTEST", "test district", 4000);
        List<City> cities = new ArrayList<City>();
        cities.add(city);

        TempAndTime tempAndTime = new TempAndTime(310.63, 1593994942, 25200);

        given(weatherService.getTempAndTime(cityName)).willReturn(tempAndTime);
        given(cityRepository.findByName(cityName)).willReturn(cities);
        given(countryRepository.findByCode("USTEST")).willReturn(country);

        CityInfo result = cityService.getCityInfo(cityName);
        CityInfo expected = new CityInfo(
                11, cityName, "USTEST", "Some country name",
                "test district", 4000, 99.46400000000003, "1593994942"
        );

        assertThat(result).isEqualTo(expected);
    }
}
