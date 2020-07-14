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
		List<City> cities = cityRepository.findByName(cityName);
		if (cities.size() < 1) {
			return null;
		}

		City city = cities.get(0);
		Country country = countryRepository.findByCode(city.getCountryCode());
		TempAndTime tempAndTime = weatherService.getTempAndTime(cityName);
		String time = Long.toString(tempAndTime.getTime());

		return new CityInfo(city, country.getName(), tempAndTime.getTemp(), time);
	}
	
}
