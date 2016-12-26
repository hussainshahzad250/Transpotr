package com.trux.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "FlashedOrderTracking")
public class TrackFlashedBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;


	@Column(name = "driverPhoneNumber")
	private String driverPhoneNumber;

	@Column(name = "bookingId")
	private long bookingId;

	

	@Column(name = "orderflashedtime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderflashedtime;

	@Column(name = "updateDateTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDateTime;

	public TrackFlashedBooking() {
	}

	public TrackFlashedBooking(String driverPhoneNumber, long bookingId,
			Date orderflashedtime) {
		super();
		this.driverPhoneNumber = driverPhoneNumber;
		this.bookingId = bookingId;
		this.orderflashedtime = orderflashedtime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDriverPhoneNumber() {
		return driverPhoneNumber;
	}

	public void setDriverPhoneNumber(String driverPhoneNumber) {
		this.driverPhoneNumber = driverPhoneNumber;
	}

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public Date getOrderflashedtime() {
		return orderflashedtime;
	}

	public void setOrderflashedtime(Date orderflashedtime) {
		this.orderflashedtime = orderflashedtime;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	 
	  
}
