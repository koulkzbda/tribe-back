package tribe.controller.dto;

import java.math.BigDecimal;

import tribe.domain.socialNetwork.Location;

public class LocationDto {

	protected String name;
	
	protected String address;
	
	protected String city;
	
	protected String postalCode;
	
	protected BigDecimal lat;
	
	protected BigDecimal lng;
	
	public LocationDto() {}
	
	public LocationDto(Location location) {
		this.name = location.getName();
		this.address = location.getAddress();
		this.city = location.getCity();
		this.postalCode = location.getPostalCode();
		this.lat = location.getLat();
		this.lng = location.getLng();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
}
