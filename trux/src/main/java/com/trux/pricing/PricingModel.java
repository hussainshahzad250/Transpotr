package com.trux.pricing;

import java.text.DecimalFormat;
import java.util.List;

import com.trux.controller.FareController;
import com.trux.model.FareRates;
import com.trux.model.GDistanceMatrix;
import com.trux.service.FareService;

public class PricingModel {

	private String vehicleType;
	private double distance;
	private double timeDurationofJourney;
	private double loadingTime;
	private double unLoadingTime;
	private double ratePerKilometer;
	private double waitingChargesPerMinute;
	private boolean isActual;
	private double baseFare;
	private double distaceRebateOnBaseFare;
	private GDistanceMatrix distanceMatrix;
	private FareService fareService;
    DecimalFormat df2 = new DecimalFormat("#.##");

	
	public PricingModel(double distance, double timeDurationofJourney,
			String vehicleType, GDistanceMatrix distanceMatrix, boolean isActual, FareService fareService) {
		this.distance = distance;
		this.vehicleType = vehicleType;
		this.isActual = isActual;
		this.timeDurationofJourney = timeDurationofJourney;
		this.distanceMatrix = distanceMatrix;
		this.fareService = fareService;
	}
	public PricingModel() {
		// TODO Auto-generated constructor stub
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getTimeDurationofJourney() {
		return timeDurationofJourney;
	}
	public void setTimeDurationofJourney(double timeDurationofJourney) {
		this.timeDurationofJourney = timeDurationofJourney;
	}
	public double getLoadingTime() {
		return loadingTime;
	}
	public void setLoadingTime(double loadingTime) {
		this.loadingTime = loadingTime;
	}
	public double getUnLoadingTime() {
		return unLoadingTime;
	}
	public void setUnLoadingTime(double unLoadingTime) {
		this.unLoadingTime = unLoadingTime;
	}
	public double getRatePerKilometer() {
		return ratePerKilometer;
	}
	public void setRatePerKilometer(double ratePerKilometer) {
		this.ratePerKilometer = ratePerKilometer;
	}
	public double getWaitingChargesPerMinute() {
		return waitingChargesPerMinute;
	}
	public void setWaitingChargesPerMinute(double waitingChargesPerMinute) {
		this.waitingChargesPerMinute = waitingChargesPerMinute;
	}
	
	
	
	

	public GDistanceMatrix getDistanceMatrix() {
		return distanceMatrix;
	}
	public void setDistanceMatrix(GDistanceMatrix distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}
	public boolean isActual() {
		return isActual;
	}
	public void setActual(boolean isActual) {
		this.isActual = isActual;
	}
	public double computeFare() {
		
		
		if(!isActual) {
			//this.timeDurationofJourney = this.distance*5/3 + 10; 
			this.timeDurationofJourney = distanceMatrix.getDurationValue()/60;
		}
		
		
		FareRates curreFareRates = this.fareService.getFareByVehicleType(this.vehicleType);
		
		
		
			if(curreFareRates != null )
			{
				this.baseFare = curreFareRates.getBase_fare();
				this.ratePerKilometer =curreFareRates.getRate_per_km();
				this.waitingChargesPerMinute = curreFareRates.getRate_per_trip_minute();
				this.distaceRebateOnBaseFare = curreFareRates.getInclusive_of_km();
				
				System.out.println("Setting price from DB");
				
			}
			
			else 
			{
				

				this.baseFare = 300;
				this.ratePerKilometer =25;
				this.waitingChargesPerMinute = 2;
				this.distaceRebateOnBaseFare = 2;
				System.out.println("Setting Hard coded Price");

			}
			
			double actualdistanceForcomputation  = distance - this.distaceRebateOnBaseFare;
			if(actualdistanceForcomputation<=0)
				actualdistanceForcomputation = 0;
		
			System.out.println("Vehicle Type"+this.vehicleType+"Base Fare:"+baseFare+" Rate Per Km:"+ratePerKilometer+" waitingChargesPerMinute:"+waitingChargesPerMinute+" distaceRebateOnBaseFare "+distaceRebateOnBaseFare+" duration"+timeDurationofJourney+" distance: "+distance);

			
			double costDueTobaseFare  = Double.valueOf(df2.format(this.baseFare));
			double costDueToextaTravel = Double.valueOf(df2.format((actualdistanceForcomputation)*this.ratePerKilometer));
			double costDueToTravelTime  = Double.valueOf(df2.format(this.timeDurationofJourney*this.waitingChargesPerMinute));
			
			double costOfJourney = costDueTobaseFare+costDueToextaTravel+costDueToTravelTime;
			
		    return Double.valueOf(df2.format(costOfJourney));
			
						
			
	}
	
   
	
	
	public double computeActualFare(String vehicleType, double distance, double duration, FareService fareService) {
		

		this.fareService = fareService;
		this.vehicleType = vehicleType;
		
		
		FareRates curreFareRates = this.fareService.getFareByVehicleType(this.vehicleType);
		
			if(curreFareRates != null )
			{
				this.baseFare = curreFareRates.getBase_fare();
				this.ratePerKilometer =curreFareRates.getRate_per_km();
				this.waitingChargesPerMinute = curreFareRates.getRate_per_trip_minute();
				this.distaceRebateOnBaseFare = curreFareRates.getInclusive_of_km();
				
				System.out.println("Setting price from DB");
				
			}
			
			else 
			{
				

				this.baseFare = 300;
				this.ratePerKilometer =25;
				this.waitingChargesPerMinute = 2;
				this.distaceRebateOnBaseFare = 2;
				System.out.println("Setting Hard coded Price");

			}
		
		

			//convert to minutes
			
			duration = duration/60;
			distance = distance/1000;
			double actualdistanceForcomputation  = distance - this.distaceRebateOnBaseFare;
			if(actualdistanceForcomputation<=0)
				actualdistanceForcomputation = 0;
			
			
			System.out.println("Vehicle Type"+this.vehicleType+"Base Fare:"+baseFare+" Rate Per Km:"+ratePerKilometer+" waitingChargesPerMinute:"+waitingChargesPerMinute+" distaceRebateOnBaseFare "+distaceRebateOnBaseFare+" duration"+duration+" distance: "+distance);
			
			
			double costDueTobaseFare  = Double.valueOf(df2.format(this.baseFare));
			double costDueToextaTravel = Double.valueOf(df2.format((actualdistanceForcomputation)*this.ratePerKilometer));
			double costDueToTravelTime  = Double.valueOf(df2.format(duration*this.waitingChargesPerMinute));
			
			double costOfJourney = costDueTobaseFare+costDueToextaTravel+costDueToTravelTime;
			
		    return Double.valueOf(df2.format(costOfJourney));
			
			
	
	}
	
	public static void main(String[] args) throws Exception {
	
	    DecimalFormat df2 = new DecimalFormat("#.##");

		System.out.println(Double.valueOf(df2.format(100.1234555)));
		
	}
	
	
}
