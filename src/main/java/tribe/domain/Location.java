package tribe.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Location {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String id;
	
	protected String name;
	
	protected String address;
	
	protected String city;
	
	protected Integer postalCode;
	
	protected Float lat;
	
	protected Float lng;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    protected HabitVersion habitVersion;

	public Location() {}

	public Location(String name, String address, String city, Integer postalCode, Float lat, Float lng,
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

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
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

	public HabitVersion getHabitVersion() {
		return habitVersion;
	}

	public void setHabitVersion(HabitVersion habitVersion) {
		this.habitVersion = habitVersion;
	}
	
}
