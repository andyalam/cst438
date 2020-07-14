package com.example.hw2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.example.hw2.domain.TempAndTime;

@Service
public class WeatherService {
	
	private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
	private RestTemplate restTemplate;
	private String weatherUrl;
	private String apiKey;
	
	public WeatherService(
			@Value("${weather.url}") final String weatherUrl, 
			@Value("${weather.apikey}") final String apiKey ) {
		this.restTemplate = new RestTemplate();
		this.weatherUrl = weatherUrl;
		this.apiKey = apiKey; 
	}
	
	public TempAndTime getTempAndTime(String cityName) {
		ResponseEntity<JsonNode> response = restTemplate.getForEntity(
				weatherUrl + "?q=" + cityName + "&appid=" + apiKey,
				JsonNode.class);
		JsonNode json = response.getBody();
		log.info("Status code from weather server:" + response.getStatusCodeValue());
		log.info("JSON response from weather service:" + json.toString());

		/*
		{
		  "coord":{
			"lon":-119.83,
			"lat":36.67
		  },
		  "weather":[
			{
			  "id":801,
			  "main":"Clouds",
			  "description":"few clouds",
			  "icon":"02d"
			}
		  ],
		  "base":"stations",
		  "main":{
			"temp":310.63,
			"feels_like":305.25,
			"temp_min":310.37,
			"temp_max":311.15,
			"pressure":1011,
			"humidity":14
		  },
		  "visibility":16093,
		  "wind":{
			"speed":6.2,
			"deg":310
		  },
		  "clouds":{
			"all":20
		  },
		  "dt":1593994942,
		  "sys":{
			"type":1,
			"id":4056,
			"country":"US",
			"sunrise":1593953188,
			"sunset":1594005672
		  },
		  "timezone":-25200,
		  "id":5350964,
		  "name":"Fresno",
		  "cod":200
		}
		*/

		double temp = json.get("main").get("temp").asDouble();
		long time = json.get("dt").asLong();
		int timezone = json.get("timezone").asInt();
		return new TempAndTime(temp, time, timezone);
	}
}
