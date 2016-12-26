package com.trux.model;

import java.util.Comparator;

public class VehicleComparator implements Comparator<VehicleLocation> {

	public int compare(VehicleLocation v1, VehicleLocation v2) {
		 if(v1.getDistanceFromUser() > v2.getDistanceFromUser()){
	            return 1;
	        } else {
	            return -1;
	        }
	}


}
