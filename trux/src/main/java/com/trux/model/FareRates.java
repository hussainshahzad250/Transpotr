package com.trux.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fare_rate")
public class FareRates
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="city_id")
    private Integer city_id;
	
	@Column(name="city_name")
    private String city_name;

	@Column(name="city_display_name")
    private String city_display_name;
	

	@Column(name="truck_category")
    private String truck_category;
	
	@Column(name="truck_display_name")
    private String truck_display_name;
	
	@Column(name="rate_type")
    private String rate_type;
	
	@Column(name="base_fare")
    private double base_fare;

	@Column(name="inclusive_of_km")
    private double inclusive_of_km;
	
	@Column(name="rate_per_km")
    private double rate_per_km;
	
	@Column(name="inclusive_of_wait_time")
    private Integer inclusive_of_wait_time;
	
	@Column(name="rate_per_wait_minute")
    private String rate_per_wait_minute;
	
	@Column(name="inclusive_of_trip_minutes")
    private double inclusive_of_trip_minutes;
	
	@Column(name="rate_per_trip_minute")
    private double rate_per_trip_minute;

	
	@Column(name="is_pickup_charge")
    private boolean is_pickup_charge;
	
	@Column(name="pickup_charge")
    private double pickup_charge;
	

	@Column(name="pickup_charge_text")
    private String pickup_charge_text;


	@Column(name="unit_rate_per_km_per_ton")
    private double unit_rate_per_km_per_ton;
	
	@Column(name="vehicle_capacity")
    private double vehicle_capacity;
	
	
	@Column(name="free_load_time")
    private double free_load_time;
	
	@Column(name="free_unload_time")
    private double free_unload_time;




	@Column(name="slab2_per_km_charges")
    private double slab2_per_km_charges;
	
	@Column(name="charges_for_hard_copy")
	private double charges_for_hard_copy ;

	@Column(name="vehicle_length")
    private double vehicle_length;
	
	@Column(name="vehicle_width")
    private double vehicle_width;
	
	@Column(name="vehicle_height")
    private double vehicle_height;
	
	@Column(name="vehicle_dimension_uom")
    private String vehicle_dimension_uom;
	
	
	
	

	public double getVehicle_length() {
		return vehicle_length;
	}

	public void setVehicle_length(double vehicle_length) {
		this.vehicle_length = vehicle_length;
	}

	public double getVehicle_width() {
		return vehicle_width;
	}

	public void setVehicle_width(double vehicle_width) {
		this.vehicle_width = vehicle_width;
	}

	public double getVehicle_height() {
		return vehicle_height;
	}

	public void setVehicle_height(double vehicle_height) {
		this.vehicle_height = vehicle_height;
	}

	public String getVehicle_dimension_uom() {
		return vehicle_dimension_uom;
	}

	public void setVehicle_dimension_uom(String vehicle_dimension_uom) {
		this.vehicle_dimension_uom = vehicle_dimension_uom;
	}

	public double getSlab2_per_km_charges() {
		return slab2_per_km_charges;
	}

	public void setSlab2_per_km_charges(double slab2_per_km_charges) {
		this.slab2_per_km_charges = slab2_per_km_charges;
	}

	public double getCharges_for_hard_copy() {
		return charges_for_hard_copy;
	}

	public void setCharges_for_hard_copy(double charges_for_hard_copy) {
		this.charges_for_hard_copy = charges_for_hard_copy;
	}

	public double getFree_load_time() {
		return free_load_time;
	}

	public void setFree_load_time(double free_load_time) {
		this.free_load_time = free_load_time;
	}

	public double getFree_unload_time() {
		return free_unload_time;
	}

	public void setFree_unload_time(double free_unload_time) {
		this.free_unload_time = free_unload_time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getRate_per_trip_minute() {
		return rate_per_trip_minute;
	}

	public void setRate_per_trip_minute(double rate_per_trip_minute) {
		this.rate_per_trip_minute = rate_per_trip_minute;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getCity_display_name() {
		return city_display_name;
	}

	public void setCity_display_name(String city_display_name) {
		this.city_display_name = city_display_name;
	}

	public String getRate_type() {
		return rate_type;
	}

	public void setRate_type(String rate_type) {
		this.rate_type = rate_type;
	}

	public boolean isIs_pickup_charge() {
		return is_pickup_charge;
	}

	public void setIs_pickup_charge(boolean is_pickup_charge) {
		this.is_pickup_charge = is_pickup_charge;
	}

	public String getRate_per_wait_minute() {
		return rate_per_wait_minute;
	}

	public void setRate_per_wait_minute(String rate_per_wait_minute) {
		this.rate_per_wait_minute = rate_per_wait_minute;
	}

	public String getPickup_charge_text() {
		return pickup_charge_text;
	}

	public void setPickup_charge_text(String pickup_charge_text) {
		this.pickup_charge_text = pickup_charge_text;
	}

	public double getInclusive_of_km() {
		return inclusive_of_km;
	}

	public void setInclusive_of_km(double inclusive_of_km) {
		this.inclusive_of_km = inclusive_of_km;
	}

	public double getPickup_charge() {
		return pickup_charge;
	}

	public void setPickup_charge(double pickup_charge) {
		this.pickup_charge = pickup_charge;
	}

	public String getTruck_display_name() {
		return truck_display_name;
	}

	public void setTruck_display_name(String truck_display_name) {
		this.truck_display_name = truck_display_name;
	}

	public Integer getInclusive_of_wait_time() {
		return inclusive_of_wait_time;
	}

	public void setInclusive_of_wait_time(Integer inclusive_of_wait_time) {
		this.inclusive_of_wait_time = inclusive_of_wait_time;
	}

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public String getTruck_category() {
		return truck_category;
	}

	public void setTruck_category(String truck_category) {
		this.truck_category = truck_category;
	}

	public double getBase_fare() {
		return base_fare;
	}

	public void setBase_fare(double base_fare) {
		this.base_fare = base_fare;
	}

	public double getRate_per_km() {
		return rate_per_km;
	}

	public void setRate_per_km(double rate_per_km) {
		this.rate_per_km = rate_per_km;
	}

	public double getInclusive_of_trip_minutes() {
		return inclusive_of_trip_minutes;
	}

	public void setInclusive_of_trip_minutes(double inclusive_of_trip_minutes) {
		this.inclusive_of_trip_minutes = inclusive_of_trip_minutes;
	}

	public double getUnit_rate_per_km_per_ton() {
		return unit_rate_per_km_per_ton;
	}

	public void setUnit_rate_per_km_per_ton(double unit_rate_per_km_per_ton) {
		this.unit_rate_per_km_per_ton = unit_rate_per_km_per_ton;
	}

	public double getVehicle_capacity() {
		return vehicle_capacity;
	}

	public void setVehicle_capacity(double vehicle_capacity) {
		this.vehicle_capacity = vehicle_capacity;
	}

	
  
	
}