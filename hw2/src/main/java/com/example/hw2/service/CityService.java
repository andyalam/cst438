package com.example.hw2.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.example.hw2.domain.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hw2.domain.*;

@Service
public class CityService {
	private static final Logger log = LoggerFactory.getLogger(CityService.class);
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	public CityInfo getCityInfo(String cityName) {
		City city = cityRepository.findByName(cityName).get(0);
		Country country = countryRepository.findByCode(city.getCountryCode());
		TempAndTime tempAndTime = weatherService.getTempAndTime(cityName);

		return new CityInfo(city, country.getName(), tempAndTime.temp, "time_here");
	}
	
}
