package tribe.controller.dto;

import tribe.domain.socialNetwork.Location;

public class LocationDto {

	protected String name;
	
	protected String address;
	
	protected String city;
	
	protected Float lat;
	
	protected Float lng;
	
	public LocationDto() {}
	
	public LocationDto(Location location) {
		this.name = location.getName();
		this.address = location.getAddress();
		this.city = location.getCity();
		this.lat = location.getLat();
		this.lng = location.getLng();
	}

	public LocationDto(String name, String address, String city, Float lat, Float lng) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.lat = lat;
		this.lng = lng;
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

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}
}
