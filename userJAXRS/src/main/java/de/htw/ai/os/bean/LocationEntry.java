package de.htw.ai.os.bean;

import java.awt.Point;
import java.sql.Timestamp;


public class LocationEntry {
	
	static private final byte[] DEFAULT_HASH = HashTools.sha256HashCode("");
	
	private long id;
	private String userId;
	private byte[] passwordHash;
	private Timestamp last_timestamp;
	private Timestamp secondlast_timestamp;
	private float lastLongitude;
	private float lastLatitiude;
	private float secondLastLongitude;
	private float secondLastLatitiude;
	private Point last_position;
	private Point secondlast_position;
	private float last_direction;
	private float secondlast_direction;
	
	
	public LocationEntry() {
		this.id = 0;
		this.userId = "newUser";
		this.passwordHash = DEFAULT_HASH;
	}
	
	public LocationEntry(long id, String name, Timestamp timestamp) {
		this.id = id;
		this.userId = name;
		this.last_timestamp = timestamp;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}
	
	public byte[] getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(final byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public Timestamp getLastTimestamp() {
		return last_timestamp;
	}

	public void setLastTimestamp(final Timestamp lastTimestamp) {
		this.last_timestamp = lastTimestamp;
	}
	
	public Timestamp getSecondlastTimestamp() {
		return secondlast_timestamp;
	}

	public void setSecondlastTimestamp(final Timestamp secondlastTimestamp) {
		this.secondlast_timestamp = secondlastTimestamp;
	}
	
	public Point getLast_position() {
		return last_position;
	}

	public void setLast_position(final Point last_position) {
		this.last_position = last_position;
	}
	
	public Point getSecondlast_position() {
		return secondlast_position;
	}
	
	public void setSecondlast_position(final Point secondlast_position) {
		this.secondlast_position = secondlast_position;
	}
	
	public float getLast_direction() {
		return last_direction;
	}

	public void setLast_direction(final float last_direction) {
		this.last_direction = last_direction;
	}

	public float getSecondlast_direction() {
		return secondlast_direction;
	}

	public void setSecondlast_direction(final float secondlast_direction) {
		this.secondlast_direction = secondlast_direction;
	}
}