package com.map.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Location" , catalog="map")
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "location_Id" , length = 512)
	private long location_Id;

	@Column(name = "Lognitude" , length = 512)
	private double lognitude;
	
	@Column(name = "Latitude" , length = 512)
	private double latitude;

	@Column(name = "type" , length = 512)
	private String type;
	
	@Column(name = "keyword" , length = 512)
	private String keyword;
	
	@Column(name = "place" , length = 512)
	private String place;
	
	public Location() {
		super();
		
		
}

	public long getLocation_Id() {
		return location_Id;
	}

	public void setLocation_Id(long location_Id) {
		this.location_Id = location_Id;
	}

	public double getLognitude() {
		return lognitude;
	}

	public void setLognitude(double lognitude) {
		this.lognitude = lognitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
}