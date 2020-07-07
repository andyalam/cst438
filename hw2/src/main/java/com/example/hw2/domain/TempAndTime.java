package com.example.hw2.domain;

public class TempAndTime {
	public double temp;
	public long time;
	public int timezone;
	
	public TempAndTime(double temp, long time, int timezone){
		this.temp = (temp - 273.15) * 9.0/5.0 + 32.0;
		this.time = time;
		this.timezone = timezone;
	}
 }
