package com.example.hw2.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.example.hw2.domain.*;
import com.example.hw2.service.CityService;
import com.example.hw2.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityService cityService;

    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private WeatherService weatherService;

    private JacksonTester<CityInfo> cityInfoJson;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void testFindCity() throws Exception {
        String cityName = "Test Francisco";

        Country country = new Country("USTEST", "Some country name");
        City city = new City(11, cityName, "USTEST", "test district", 4000);
        List<City> cities = new ArrayList<City>();
        cities.add(city);
        TempAndTime tempAndTime = new TempAndTime(310.63, 1593994942, 25200);

        CityInfo mockedInfo = new CityInfo(
                11, cityName, "USTEST", "Some country name",
                "test district", 4000, 99.46400000000003, "1593994942"
        );
        given(cityService.getCityInfo(cityName)).willReturn(mockedInfo);

        MockHttpServletResponse res = mvc.perform(get("/api/cities/" + cityName))
                .andReturn().getResponse();

        assertThat(res.getStatus()).isEqualTo(HttpStatus.OK.value());

        CityInfo result = cityInfoJson.parseObject(res.getContentAsString());
        assertThat(result).isEqualTo(mockedInfo);
    }

    @Test
    public void testFindCityFail() throws Exception {
        String cityName = "Test Francisco";
        List<City> cities = new ArrayList<City>();
        given(cityService.getCityInfo(cityName)).willReturn(null);

        MockHttpServletResponse res = mvc.perform(get("/api/cities/" + cityName))
                .andReturn().getResponse();

        assertThat(res.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testFindCities() throws Exception {
        String cityName = "Los Angeles";

        Country country = new Country("USTEST", "Some country name");
        City city = new City(11, cityName, "USTEST", "test district", 4000);
        List<City> cities = new ArrayList<City>();
        cities.add(city);
        TempAndTime tempAndTime = new TempAndTime(310.63, 1593994942, 25200);

        CityInfo mockedInfo = new CityInfo(
                11, cityName, "USTEST", "Some country name",
                "test district", 4000, 99.46400000000003, "1593994942"
        );
        given(cityService.getCityInfo(cityName)).willReturn(mockedInfo);

        MockHttpServletResponse res = mvc.perform(get("/api/cities/" + cityName))
                .andReturn().getResponse();

        assertThat(res.getStatus()).isEqualTo(HttpStatus.OK.value());

        CityInfo result = cityInfoJson.parseObject(res.getContentAsString());
        assertThat(result).isEqualTo(mockedInfo);
    }
}
