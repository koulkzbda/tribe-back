package tribe.domain.socialNetwork;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import tribe.controller.dto.LocationDto;
import tribe.domain.habitTracking.HabitVersion;

@Entity
public class Location {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
	protected String name;
	
	protected String address;
	
	protected String city;
	
	protected String postalCode;
	
	protected BigDecimal lat;
	
	protected BigDecimal lng;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    protected HabitVersion habitVersion;

	public Location() {}
	
	public Location(LocationDto l) {
		name = l.getName();
		address = l.getAddress();
		city = l.getCity();
		postalCode = l.getPostalCode();
		lat = l.getLat();
		lng = l.getLng();
	}

	public Location(String name, String address, String city, String postalCode, BigDecimal lat, BigDecimal lng,
			HabitVersion habitVersion) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.lat = lat;
		this.lng = lng;
		this.habitVersion = habitVersion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public HabitVersion getHabitVersion() {
		return habitVersion;
	}

	public void setHabitVersion(HabitVersion habitVersion) {
		this.habitVersion = habitVersion;
	}
	
}
