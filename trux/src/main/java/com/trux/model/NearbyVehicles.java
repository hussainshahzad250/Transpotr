package com.trux.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class NearbyVehicles {

	public String distanceTata709 = "no trucks";
	public String distanceTataAce = "no trucks";
	public String distanceTata407 = "no trucks";
	private String distanceBoleroPickup ="no trucks";
    private String distanceMarutiEeco = "no trucks";
    private String distanceMahindraChampion = "no trucks";

	
	
	
	public String getDistanceMahindraChampion() {
		return distanceMahindraChampion;
	}


	public void setDistanceMahindraChampion(String distanceMahindraChampion) {
		this.distanceMahindraChampion = distanceMahindraChampion;
	}


	public String getDistanceTata709() {
		return distanceTata709;
	}


	public void setDistanceTata709(String distanceTata709) {
		this.distanceTata709 = distanceTata709;
	}


	public String getDistanceTataAce() {
		return distanceTataAce;
	}


	public void setDistanceTataAce(String distanceTataAce) {
		this.distanceTataAce = distanceTataAce;
	}





	public String getDistanceBoleroPickup() {
		return distanceBoleroPickup;
	}


	public void setDistanceBoleroPickup(String distanceBoleroPickup) {
		this.distanceBoleroPickup = distanceBoleroPickup;
	}


	public String getDistanceMarutiEeco() {
		return distanceMarutiEeco;
	}


	public void setDistanceMarutiEeco(String distanceMarutiEeco) {
		this.distanceMarutiEeco = distanceMarutiEeco;
	}


	public String getDistanceTata407() {
		return distanceTata407;
	}







	public void setDistanceTata407(String distanceTata407) {
		this.distanceTata407 = distanceTata407;
	}







	public List<TruckLocation> getTruxLocations() {
		return truxLocations;
	}







	public void setTruxLocations(List<TruckLocation> truxLocations) {
		this.truxLocations = truxLocations;
	}







	public List<TruckLocation> truxLocations = new ArrayList<TruckLocation>();
	
	
	

 
	
	
   public static void main(String[] args) {
	   NearbyVehicles fetchNearByTrux = new NearbyVehicles();

	   for(int i = 0 ; i < 10 ; i ++) {
		   for(int j = 0 ; j < 2 ; j ++) {
		   TruckLocation location = new TruckLocation();
		   if(j ==0 ) {
		   location.setLatitude(Double.parseDouble("28.5"+i+"68983"));
		   location.setLongitude(Double.parseDouble("77.3"+i+"69436"));
		   }
		   
		   if (j ==1) {
			   location.setLatitude(Double.parseDouble("28.4"+i+"68983"));
			   location.setLongitude(Double.parseDouble("77.2"+i+"69436"));
		   }
		   
		   if(i%4 == 0 )
			   location.setTruxType("Tata709");
		   
		   if(i%4 == 1 )
			   location.setTruxType("TataAce");
		   
		   if(i%4 == 2 )
			   location.setTruxType("MaximaPickup");
		   
		   if(i%4 == 3 )
			   location.setTruxType("Tata407");
		   
		   fetchNearByTrux.getTruxLocations().add(location);
		   
	   }
	  }
	   
	     Gson gson = new Gson();
		String jsonPayload = gson.toJson(fetchNearByTrux);
		System.out.println(jsonPayload);
}
	
	
}


	
	