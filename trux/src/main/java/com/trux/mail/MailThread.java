package com.trux.mail;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.trux.constants.SMSTemplates;
import com.trux.model.CustomerBookingDetails;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.FareRates;
import com.trux.service.FareService;
import com.trux.service.RegistrationService;
import com.trux.service.TruxStartUpService;
import com.trux.service.VehicleLocationService;
import com.trux.utils.JasperUtils;
import com.trux.utils.PusherUtil;
import com.trux.utils.TruxUtils;

public class MailThread extends Thread{

	private static MailThread mailThread;
	
	@Autowired
	private TruxStartUpService truxStartUpService;
	
	@Autowired
	private FareService fareService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private VehicleLocationService vehicleLocationService;
	
	
	public static MailThread getInstance(){
		
		if(mailThread == null){
			try {
				mainThreadRestart();
			} catch (InvocationTargetException e) {				
				e.printStackTrace();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			return new MailThread();
		}else {
			return mailThread;
		}
		
	}
	
	public static void mainThreadRestart() throws InvocationTargetException, InterruptedException {
	    try {
	      EventQueue.invokeAndWait(new Runnable() {
	        
	        public void run() {
	          throw new RuntimeException("exception");
	        }
	      });
	    } catch (Exception e) {
	      //ignore
	    }
	    EventQueue.invokeAndWait(
	        new Runnable() {
	          
	          public void run() {
	            System.out.println("EDT restarted");
	            
	          }
	        }
	    ); 
	  }
	
	
	public void run() {
	try{
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
		while(true){
			try {
				

			//	System.out.println("MailThread.run() ****************");
				
				processLoadLaterOrders();
				
				if(truxStartUpService != null && truxStartUpService.mailingQueue != null && !truxStartUpService.mailingQueue.isEmpty()){
					System.out.println("MailThread.run() starting size **** " + truxStartUpService.mailingQueue.size());
					for (CustomerBookingDetails bookingDetils : truxStartUpService.mailingQueue) {
						
						if(!bookingDetils.isProcessed()){
							if(bookingDetils.getPayment_mode() == 3)
								try {
									SMSTemplates.senMessageToConsigerForTripEndingAndPaymentDone(bookingDetils);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							String vehicleType = bookingDetils.getVehicleType();
							 FareRates fareRates = fareService.getFareByVehicleType(vehicleType);

										     
						    System.out.println("generating report");
							
						     try {
								JasperUtils.generateInvoiceReport(bookingDetils, fareRates);
								System.out.println("Sending email");
								TruxUtils.sendMail(bookingDetils.getCustomerEmail(), bookingDetils.getDocumentuploadurl(), 1, bookingDetils, fareRates);
								System.out.println("MailThread.run() starting end **** " + truxStartUpService.mailingQueue.size());
								//wait(2*1000);
						     } catch (Exception e) {
								e.printStackTrace();
							}
						}
						
						bookingDetils.setProcessed(true);
					}
					truxStartUpService.mailingQueue.clear();
					try {
						System.out.println("MailThread.run() sleep for 2 sec *************");
						sleep(2*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{
					try {
						//System.out.println("MailThread.run() sleep for 10 sec *************");
						sleep(10*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}catch(Exception er){er.printStackTrace();}
	}

	private void processLoadLaterOrders() throws InterruptedException {
		
		if(truxStartUpService != null && truxStartUpService.orderPushingQueue != null && !truxStartUpService.orderPushingQueue.isEmpty()){
			  PusherUtil pusherUtil = new PusherUtil();

			System.out.println("Order Pushing Thread run() starting size **** " + truxStartUpService.orderPushingQueue.size());
			for (CustomerBookingDetails customerBookingDetail : truxStartUpService.orderPushingQueue) {
			
				List<DriverDeviceVehicleMapping> allRegisteredDeviceList = registrationService.getAllAvailableDrivers();
				
				System.out.println("Pushing order for order id "+customerBookingDetail.getBookingId()+ " and customer Name "+customerBookingDetail.getCustomerName());

				pusherUtil.notifyTruxs(allRegisteredDeviceList, customerBookingDetail, vehicleLocationService);
				sleep(70*1000);
				
				
			}
			
			truxStartUpService.orderPushingQueue.clear();
			
		}
}
}
