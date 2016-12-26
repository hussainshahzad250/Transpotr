package com.trux.model;

public class BookedTruxTrackingModel {
	
	private String errorCode;
	private String errorMessage;
	private Integer journeyStatus = 0;

	    public BookedTruxTrackingModel(String errorCode, String errorMessage) {
	    	
		this.errorCode =  errorCode;
		this.errorMessage = errorMessage;
	}

		public BookedTruxTrackingModel() {
			// TODO Auto-generated constructor stub
		}

		public String getState_id() {
		return state_id;
	}

	public void setState_id(String state_id) {
		this.state_id = state_id;
	}

		private Booking booking;

	    private String state_id;

	

	    public Booking getBooking ()
	    {
	        return booking;
	    }

	    public void setBooking (Booking booking)
	    {
	        this.booking = booking;
	    }

	    public class Booking
	    {
	        private String pickup_lng;

	        private String booking_id;

	        private String pickup_lat;

	        private String crn;

	        private Alloted_trux_info alloted_trux_info;

	        public String getPickup_lng ()
	        {
	            return pickup_lng;
	        }

	        public void setPickup_lng (String pickup_lng)
	        {
	            this.pickup_lng = pickup_lng;
	        }

	        public String getBooking_id ()
	        {
	            return booking_id;
	        }

	        public void setBooking_id (String booking_id)
	        {
	            this.booking_id = booking_id;
	        }

	        public String getPickup_lat ()
	        {
	            return pickup_lat;
	        }

	        public void setPickup_lat (String pickup_lat)
	        {
	            this.pickup_lat = pickup_lat;
	        }

	        public String getCrn ()
	        {
	            return crn;
	        }

	        public void setCrn (String crn)
	        {
	            this.crn = crn;
	        }

	        public Alloted_trux_info getAlloted_trux_info ()
	        {
	            return alloted_trux_info;
	        }

	        public void setAlloted_cab_info (Alloted_trux_info alloted_trux_info)
	        {
	            this.alloted_trux_info = alloted_trux_info;
	        }

	      
	    }
	    
	    public class Alloted_trux_info
	    {
	     

	        private String pickup_address;

	        private String driver_mobile;

	        private String driver_name;


	        private String license_number;

	        private String car_model;

	        private String lng;

	        private String lat;

	      

	        public String getPickup_address ()
	        {
	            return pickup_address;
	        }

	        public void setPickup_address (String pickup_address)
	        {
	            this.pickup_address = pickup_address;
	        }

	        public String getDriver_mobile ()
	        {
	            return driver_mobile;
	        }

	        public void setDriver_mobile (String driver_mobile)
	        {
	            this.driver_mobile = driver_mobile;
	        }

	        public String getDriver_name ()
	        {
	            return driver_name;
	        }

	        public void setDriver_name (String driver_name)
	        {
	            this.driver_name = driver_name;
	        }

	    

	        public String getLicense_number ()
	        {
	            return license_number;
	        }

	        public void setLicense_number (String license_number)
	        {
	            this.license_number = license_number;
	        }

	        public String getCar_model ()
	        {
	            return car_model;
	        }

	        public void setCar_model (String car_model)
	        {
	            this.car_model = car_model;
	        }

	        public String getLng ()
	        {
	            return lng;
	        }

	        public void setLng (String lng)
	        {
	            this.lng = lng;
	        }

	        public String getLat ()
	        {
	            return lat;
	        }

	        public void setLat (String lat)
	        {
	            this.lat = lat;
	        }

	       
	    }

		public String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public Integer getJourneyStatus() {
			return journeyStatus;
		}

		public void setJourneyStatus(Integer journeyStatus) {
			this.journeyStatus = journeyStatus;
		}
	    
	    
}
