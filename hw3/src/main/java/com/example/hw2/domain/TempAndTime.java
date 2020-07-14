package com.example.hw2.domain;

public class TempAndTime {
	private double temp;
	private long time;
	private int timezone;
	
	public TempAndTime(double temp, long time, int timezone){
		this.temp = (temp - 273.15) * 9.0/5.0 + 32.0;
		this.time = time;
		this.timezone = timezone;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}
}
