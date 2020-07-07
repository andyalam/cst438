package com.example.hw2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.hw2.domain.*;
import com.example.hw2.service.CityService;

@RestController
public class CityRestController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/api/cities/{city}")
	public ResponseEntity<CityInfo> getWeather(@PathVariable("city") String cityName) {
		CityInfo cityInfo = cityService.getCityInfo(cityName);
		return cityInfo == null
				? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(cityInfo, HttpStatus.OK);
	}
	
}
