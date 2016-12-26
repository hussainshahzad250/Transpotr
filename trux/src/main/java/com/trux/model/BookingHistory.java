package com.trux.model;

import java.util.List;

public class BookingHistory {
	
	
	private String status;

	private List<BookingDetails> all;
	
	private List<BookingDetails> upcoming;
	
	private List<BookingDetails> completed;


	
	public BookingHistory() {
		super();
	}

	public BookingHistory(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public List<BookingDetails> getAll() {
		return all;
	}

	public void setAll(List<BookingDetails> all) {
		this.all = all;
	}

	public List<BookingDetails> getUpcoming() {
		return upcoming;
	}

	public void setUpcoming(List<BookingDetails> upcoming) {
		this.upcoming = upcoming;
	}

	public List<BookingDetails> getCompleted() {
		return completed;
	}

	public void setCompleted(List<BookingDetails> completed) {
		this.completed = completed;
	}





	public class BookingDetails{
		
		private Integer bookingId;
		
		private String status;
		
		private Long pickup_time;
		
		private String pickup_address;
		
		private String service_city;
		
		private String truck_category;

		
		
		public BookingDetails() {
			super();
		}
		
		

		public BookingDetails(Integer bookingId, String status,
				Long pickup_time, String pickup_address, String service_city,
				String truck_category) {
			super();
			this.bookingId = bookingId;
			this.status = status;
			this.pickup_time = pickup_time;
			this.pickup_address = pickup_address;
			this.service_city = service_city;
			this.truck_category = truck_category;
		}



	

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Integer getBookingId() {
			return bookingId;
		}



		public void setBookingId(Integer bookingId) {
			this.bookingId = bookingId;
		}



		public Long getPickup_time() {
			return pickup_time;
		}



		public void setPickup_time(Long pickup_time) {
			this.pickup_time = pickup_time;
		}



		public String getPickup_address() {
			return pickup_address;
		}

		public void setPickup_address(String pickup_address) {
			this.pickup_address = pickup_address;
		}

		public String getService_city() {
			return service_city;
		}

		public void setService_city(String service_city) {
			this.service_city = service_city;
		}

		public String getTruck_category() {
			return truck_category;
		}

		public void setTruck_category(String truck_category) {
			this.truck_category = truck_category;
		}
		
	}

}
