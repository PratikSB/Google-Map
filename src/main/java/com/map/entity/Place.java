package com.map.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Place" , catalog="map")
public class Place implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@Column(name ="place_Id", nullable = false)
	private String place_Id;
	
	
	@Column(name ="Place_Name",length = 512)
	private String place_Name;
	
	@Column(name ="Address",length = 512)
	private String address;
	
	@Column(name ="Phone_Number",length = 512)
	private String phone_Number;
	
	@Column(name ="Rating",length = 512)
	private String rating;
	
	public String getPlace_Name() {
		return place_Name;
	}

	public void setPlace_Name(String place_Name) {
		this.place_Name = place_Name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPlace_Id() {
		return place_Id;
	}

	public void setPlace_Id(String place_Id) {
		this.place_Id = place_Id;
	}

	public String getPhone_Number() {
		return phone_Number;
	}

	public void setPhone_Number(String phone_Number) {
		this.phone_Number = phone_Number;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

}
