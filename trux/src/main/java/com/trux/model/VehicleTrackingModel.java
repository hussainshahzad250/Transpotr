package com.trux.model;

public class VehicleTrackingModel
{
    private String state_id;

    private Booking booking;

    private String request_type;

    private String status;
    
    private String finished_at;
    
    private String start_at;
    
    public Booking createBookingInstance() {
    	this.booking = new Booking();
    	return booking;
    }

    public String getState_id ()
    {
        return state_id;
    }

    public void setState_id (String state_id)
    {
        this.state_id = state_id;
    }

    public Booking getBooking ()
    {
        return booking;
    }

    public void setBooking (Booking booking)
    {
        this.booking = booking;
    }

    public String getRequest_type ()
    {
        return request_type;
    }

    public void setRequest_type (String request_type)
    {
        this.request_type = request_type;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
    
    

    public String getFinished_at() {
		return finished_at;
	}

	public void setFinished_at(String finished_at) {
		this.finished_at = finished_at;
	}

	public String getStart_at() {
		return start_at;
	}

	public void setStart_at(String start_at) {
		this.start_at = start_at;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [state_id = "+state_id+", booking = "+booking+", request_type = "+request_type+", status = "+status+"]";
    }
    
    public class Booking
    {
        private String pickup_lng;

        private String booking_id;

        private String pickup_lat;

        private String crn;

        private Alloted_cab_info alloted_cab_info;

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

        public Alloted_cab_info getAlloted_cab_info ()
        {
            return alloted_cab_info;
        }

        public void setAlloted_cab_info (Alloted_cab_info alloted_cab_info)
        {
            this.alloted_cab_info = alloted_cab_info;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [pickup_lng = "+pickup_lng+", booking_id = "+booking_id+", pickup_lat = "+pickup_lat+", crn = "+crn+", alloted_cab_info = "+alloted_cab_info+"]";
        }

		public Alloted_cab_info createInnerClasses() {
			
			this.alloted_cab_info = new Alloted_cab_info();
			Duration duration = new Duration();
			Distance distance = new Distance();
			this.alloted_cab_info.setDuration(duration);
			this.alloted_cab_info.setDistance(distance);
			
			return this.alloted_cab_info;
			
		}
    }
    
    public class Alloted_cab_info
     {
         private String id;

         private Distance distance;

         private Duration duration;

         private String pickup_address;

         private String driver_mobile;

         private String driver_name;

         private String color;

         private String license_number;

         private String car_model;

         private String lng;

         private String lat;

         public String getId ()
         {
             return id;
         }

         public void setId (String id)
         {
             this.id = id;
         }

         public Distance getDistance ()
         {
             return distance;
         }

         public void setDistance (Distance distance)
         {
             this.distance = distance;
         }

         public Duration getDuration ()
         {
             return duration;
         }

         public void setDuration (Duration duration)
         {
             this.duration = duration;
         }

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

         public String getColor ()
         {
             return color;
         }

         public void setColor (String color)
         {
             this.color = color;
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

         @Override
         public String toString()
         {
             return "ClassPojo [id = "+id+", distance = "+distance+", duration = "+duration+", pickup_address = "+pickup_address+", driver_mobile = "+driver_mobile+", driver_name = "+driver_name+", color = "+color+", license_number = "+license_number+", car_model = "+car_model+", lng = "+lng+", lat = "+lat+"]";
         }
     }
     public class Distance
     {
         private String unit;

         private String value;

         public String getUnit ()
         {
             return unit;
         }

         public void setUnit (String unit)
         {
             this.unit = unit;
         }

         public String getValue ()
         {
             return value;
         }

         public void setValue (String value)
         {
             this.value = value;
         }

         @Override
         public String toString()
         {
             return "ClassPojo [unit = "+unit+", value = "+value+"]";
         }
     }
     
     public class Duration
     {
         private String unit;

         private String value;

         public String getUnit ()
         {
             return unit;
         }

         public void setUnit (String unit)
         {
             this.unit = unit;
         }

         public String getValue ()
         {
             return value;
         }

         public void setValue (String value)
         {
             this.value = value;
         }

         @Override
         public String toString()
         {
             return "ClassPojo [unit = "+unit+", value = "+value+"]";
         }
     }
}
