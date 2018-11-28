package de.htw.ai.os.bean;

import java.sql.Timestamp;

public class LocationEntry {
	
	private String name;
	private Timestamp lastTimestamp;
	private Timestamp secondlastTimestamp;
	private double lastLognitude;
	private double secondlastLognitude;
	private double lastLattitude;
	private double secondlastLattitude;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getLastTimestamp() {
		return lastTimestamp;
	}

	public void setLastTimestamp(Timestamp lastTimestamp) {
		this.lastTimestamp = lastTimestamp;
	}

	public Timestamp getSecondlastTimestamp() {
		return secondlastTimestamp;
	}

	public void setSecondlastTimestamp(Timestamp secondlastTimestamp) {
		this.secondlastTimestamp = secondlastTimestamp;
	}

	public double getLastLognitude() {
		return lastLognitude;
	}

	public void setLastLognitude(double lastLognitude) {
		this.lastLognitude = lastLognitude;
	}

	public double getSecondlastLognitude() {
		return secondlastLognitude;
	}

	public void setSecondlastLognitude(double secondlastLognitude) {
		this.secondlastLognitude = secondlastLognitude;
	}

	public double getLastLattitude() {
		return lastLattitude;
	}

	public void setLastLattitude(double lastLattitude) {
		this.lastLattitude = lastLattitude;
	}

	public double getSecondlastLattitude() {
		return secondlastLattitude;
	}

	public void setSecondlastLattitude(double secondlastLattitude) {
		this.secondlastLattitude = secondlastLattitude;
	}


	
	
	public LocationEntry(){
		
	}
	
	public LocationEntry(String name, Timestamp lastTimestamp){
		this.name = name;
		this.lastTimestamp = lastTimestamp;
	}

}
