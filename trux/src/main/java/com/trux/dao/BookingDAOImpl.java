package com.trux.dao;

import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;

import com.trux.model.CustomerBookingDetails;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.OrderFilterReportsDto;
import com.trux.model.RescheduleTrip;
import com.trux.utils.DateFormaterUtils;
import com.trux.utils.JulianDayHoursMinuteSecondCount;

public class BookingDAOImpl implements BookingDAO {

	private SessionFactory sessionFactory;
	 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	
	 public void saveOrUpdate(Object object){
		 Session session = this.sessionFactory.openSession();
		try{
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(object);
        tx.commit();
       
        }catch(Exception er){System.out.println(er.getMessage().toString());
        }
		finally{ session.close();}
		
	}

	public CustomerBookingDetails getBookingDetailsById(Integer bookingId) {
		try{Session session = this.sessionFactory.openSession();
		 	@SuppressWarnings("unchecked")
		 	List<CustomerBookingDetails> bookingdetailsList = session.createQuery("from CustomerBookingDetails c where c.bookingId="+bookingId).list();
	        session.close();
	        
	        if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
	        	return bookingdetailsList.get(0);
	        }
	        
	        return null;}catch(Exception er){System.out.println(er.getMessage().toString());return null;}		
	}

	public CustomerBookingDetails getBookingDetailsByDriverCall(Integer bookingId) {
		Session session = this.sessionFactory.openSession();
		try{		
		CustomerBookingDetails dto1=null;
		Object obj = session.get(CustomerBookingDetails.class,bookingId );
		dto1 = (CustomerBookingDetails)obj;
		session.close();
		dto1.setDriverCallDatetime(new Date());
		Session session1 = this.sessionFactory.openSession();
		session1.get(CustomerBookingDetails.class, bookingId);		 
		Transaction tx=session1.beginTransaction();		 
		session1.merge(dto1);
		tx.commit();
		session1.clear(); 
		session1.close();
        
        return null;
        }catch(Exception er){
        	System.out.println(er.getMessage().toString());
         session.close();
        	return null;
        	
        }		
}
	
	public List<CustomerBookingDetails> getBookingHistory(String email) {
		Session session = this.sessionFactory.openSession();
		try{
	 	@SuppressWarnings("unchecked")
	 	List<CustomerBookingDetails> bookingdetailsList = session.createQuery("from CustomerBookingDetails c where c.customerEmail='"+email+"' ORDER BY c.createdDate DESC").list();
        session.close();
        
        if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
        	return bookingdetailsList;
        }
        
        return null;
        }catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();
        return null;
        }		
	}
	
	 
	public CustomerBookingDetails getBookingDetailsByEmail(String email, Integer bookingId){
		Session session = this.sessionFactory.openSession();
		try{
	 	@SuppressWarnings("unchecked")
	 	List<CustomerBookingDetails> bookingdetailsList = session.createQuery("from CustomerBookingDetails c where c.customerEmail='"+email+"' and c.bookingId="+bookingId).list();
        session.close();
        
        if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
        	return bookingdetailsList.get(0);
        }
        
        return null;}catch(Exception er){
        	
        	System.out.println(er.getMessage().toString());
        	session.close();
        	return null;}
	}
	
	
	public List<CustomerBookingDetails> getPendingBookings() {
		Session session = this.sessionFactory.openSession();
		try{
		@SuppressWarnings("unchecked")
	 	List<CustomerBookingDetails> bookingdetailsList = session.createQuery("from CustomerBookingDetails c where c.bookingStatus='Pending' order by c.createdDate desc").list();
        session.close();
        
        if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
        	//System.out.println("BookingDAOImpl.getPendingBookings() pending booking list size **** " + bookingdetailsList.size());
        	return bookingdetailsList;
        }
        
        return null;}catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();
        return null;}	
}


	
	public List<CustomerBookingDetails> getAllCurrentBookingStatus() {
		Session session = this.sessionFactory.openSession();
		try{
	 	@SuppressWarnings("unchecked")
	 	List<CustomerBookingDetails> bookingdetailsList = session.createQuery("from CustomerBookingDetails c where c.bookingStatus NOT in ('Pending','Completed','Cancelled') order by c.createdDate desc").list();
        session.close();
        
        if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
        	//System.out.println("BookingDAOImpl.getPendingBookings() current booking list size **** " + bookingdetailsList.size());
        	return bookingdetailsList;
        }
        
        return null;}catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();
        return null;}	
}

	
	public List<CustomerBookingDetails> getAllConfirmedBooking() {
		Session session = this.sessionFactory.openSession();
		try{
	 	@SuppressWarnings("unchecked")  
	 	List<CustomerBookingDetails> bookingdetailsList = session.createQuery("from CustomerBookingDetails c where c.bookingStatus = 'Confirmed' order by c.createdDate desc").list();
        session.close();
        
        if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
        	for (int i=0;i<bookingdetailsList.size();i++){
        	if(bookingdetailsList.get(i).getExpectedRideStartTime()!=0){
        	Date pickUptime = new Date(bookingdetailsList.get(i).getExpectedRideStartTime());
    		String datepickup=	pickUptime.toString();
        	bookingdetailsList.get(i).setPickupDateTime(DateFormaterUtils.getDayDateTime(datepickup));
        		}        		
        	}
        	//System.out.println("BookingDAOImpl.getPendingBookings() confirmed booking list size **** " + bookingdetailsList.size());
        	return bookingdetailsList;
        }
        
        return null;}catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();
        return null;}	
}


	
	public List<CustomerBookingDetails> getAllCancelledBookings() {
		Session session = this.sessionFactory.openSession();
		try{
	 	@SuppressWarnings("unchecked")
	 	List<CustomerBookingDetails> bookingdetailsList = session.createQuery("from CustomerBookingDetails c where c.bookingStatus = 'Cancelled' order by c.createdDate desc").list();
        session.close();
        
        if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
        	for(int i=0;i<bookingdetailsList.size();i++){
        		if(bookingdetailsList.get(i).getUpdatedDate()!=null){
        		String updateDate=	bookingdetailsList.get(i).getUpdatedDate().toString();
        		bookingdetailsList.get(i).setUpdatedDateTime(DateFormaterUtils.getDateTime(updateDate));
        		}
        		if(bookingdetailsList.get(i).getPickUpTime()!=null){
            		String pickupDateTime=	bookingdetailsList.get(i).getPickUpTime().toString();
            		bookingdetailsList.get(i).setPickupDateTime(DateFormaterUtils.getDateTime(pickupDateTime));
            		}
        		
        		
        	}
        	//System.out.println("BookingDAOImpl.getPendingBookings() cancelled booking list size **** " + bookingdetailsList.size());
        	return bookingdetailsList;
        }
        
        return null;}catch(Exception er){System.out.println(er.getMessage().toString());
        session.clear();session.close();
        return null;}	
}


	
	public List<DriverDeviceVehicleMapping> getAllLogedInVehicles() {
		Session session = this.sessionFactory.openSession();
		try{
	 	@SuppressWarnings("unchecked")
	 	List<DriverDeviceVehicleMapping> driverDeviceMapList = session.createQuery("from DriverDeviceVehicleMapping d where d.loginStatus = 1").list();
        session.close();
        
        if(driverDeviceMapList != null && !driverDeviceMapList.isEmpty()){
        	//System.out.println("BookingDAOImpl.getPendingBookings() driverDeviceMapList list size **** " + driverDeviceMapList.size());
        	return driverDeviceMapList;
        }
        
        return null;}catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();	
        return null;}	
}


	
	public List<CustomerBookingDetails> getCustomerBookingReports() {
		Session session = this.sessionFactory.openSession();
		try{
	 	@SuppressWarnings("unchecked")
	 	List<CustomerBookingDetails> customerBookingDetailsList = session.createQuery("SELECT c from CustomerBookingDetails c where  c.bookingStatus = 'Pending' order by c.rideTime desc").list();
        session.close();        
        if(customerBookingDetailsList != null && customerBookingDetailsList.size()>0){
        	return customerBookingDetailsList;
        }else{
		return null;}}catch(Exception er){System.out.println(er.getMessage().toString());
		session.close();
		return null;}	
		
	}


	@SuppressWarnings("unchecked")
	
	public List<DriverDeviceVehicleMapping> getAllLogedInVehicles(String flags) {
		Session session = this.sessionFactory.openSession();
		try{
	   String flag=flags.trim();
	 	List<DriverDeviceVehicleMapping> driverDeviceMapList=null;
	 	if(flag.equals("1")){
	 	 driverDeviceMapList = session.createQuery("from DriverDeviceVehicleMapping d").list();
	 	 } else if(flag.equals("2")){
		 	 driverDeviceMapList = session.createQuery("SELECT d from DriverDeviceVehicleMapping d where d.loginStatus=1 and d.driverStatus=0").list();
	 	 } else if(flag.equals("3")){
		 	 driverDeviceMapList = session.createQuery("SELECT d from DriverDeviceVehicleMapping d where d.loginStatus=1 and d.driverStatus=1").list();
		 } else if(flag.equals("4")){
		 	 driverDeviceMapList = session.createQuery("SELECT d from DriverDeviceVehicleMapping d where d.loginStatus=0 and d.driverStatus=0").list();
		 } 
	 	 session.flush();session.clear();
		 session.close();
        
	  if(driverDeviceMapList != null && !driverDeviceMapList.isEmpty()){
        	//System.out.println("BookingDAOImpl.getPendingBookings() driverDeviceMapList list size **** " + driverDeviceMapList.size());
        	return driverDeviceMapList;
        }else
		return null;}catch(Exception er){System.out.println(er.getMessage().toString());
		
		session.close();
		return null;}	
	}

	@SuppressWarnings("unchecked")
	 
	public List<DriverDeviceVehicleMapping> getAllLogedInVehiclesByVehicle(String flags,String vehicleType,String city) {
		Session session = this.sessionFactory.openSession();
		try{
	   String flag=flags.trim();
	 	List<DriverDeviceVehicleMapping> driverDeviceMapList=null;
	 	if(flag.equals("1")){
	 	 driverDeviceMapList = session.createQuery("from DriverDeviceVehicleMapping d where d.vehicleType='"+vehicleType+"'").list();
	 	 } else if(flag.equals("2")){
		 	 driverDeviceMapList = session.createQuery("SELECT d from DriverDeviceVehicleMapping d where d.loginStatus=1 and d.vehicleType='"+vehicleType+"' and d.driverStatus=0").list();
		 } else if(flag.equals("3")){
		 	 driverDeviceMapList = session.createQuery("SELECT d from DriverDeviceVehicleMapping d where d.loginStatus=1 and d.vehicleType='"+vehicleType+"' and d.driverStatus=1").list();
		 } else if(flag.equals("4")){
		 	 driverDeviceMapList = session.createQuery("SELECT d from DriverDeviceVehicleMapping d where d.loginStatus=0 and d.vehicleType='"+vehicleType+"' and d.driverStatus=0").list();
		 } 
	 	
	 	session.flush();session.clear();
	 	session.close();
        
	  if(driverDeviceMapList != null && !driverDeviceMapList.isEmpty()){
        	//System.out.println("BookingDAOImpl.getPendingBookings() driverDeviceMapList list size **** " + driverDeviceMapList.size());
        	return driverDeviceMapList;
        }
		return null;}catch(Exception er){System.out.println(er.getMessage().toString());
		session.close();
		
		return null;}	
	}


	
	public int removeDriverDeviceVehicleMapping(String byId) {
		//System.out.println("ID:"+byId);
		Session session = this.sessionFactory.openSession();
		try{
		Query query = session.createQuery("DELETE DriverDeviceVehicleMapping d where d.id=:ID");
		query.setParameter("ID", new Integer(byId));
		int result = query.executeUpdate();
		 session.flush();session.clear();
		 session.close();
		return result;}catch(Exception er){System.out.println(er.getMessage().toString());
		session.close();
		return 0;}	
	}


	 
	@SuppressWarnings("unchecked")
	
	public List<DriverDeviceVehicleMapping> getLogedInVehiclesReportByPagination(int pageNumber,int pageSize,String flags) {
		Session session = this.sessionFactory.openSession();
		try{
		 String flag=flags.trim();
		 	List<DriverDeviceVehicleMapping> driverDeviceMapList=null; 
		 	if(flag.equals("1")){
		 	 driverDeviceMapList =session.createQuery("from DriverDeviceVehicleMapping d").
			 setFirstResult(pageNumber).
			 setMaxResults(pageSize).list();
		 	 session.flush();session.clear();
		 	 session.close();
		 	 } else if(flag.equals("2")){
			 driverDeviceMapList = session.createQuery("SELECT d from DriverDeviceVehicleMapping d where d.loginStatus=1 and d.driverStatus=0").setFirstResult(pageNumber).
			 setMaxResults(pageSize).list();
			 session.flush();session.clear();
			 session.close();
			 } else if(flag.equals("3")){
			 driverDeviceMapList = session.createQuery("SELECT d from DriverDeviceVehicleMapping d where d.loginStatus=1 and d.driverStatus=1").setFirstResult(pageNumber).
			 setMaxResults(pageSize).list(); 
			 session.flush();session.clear();
			 session.close();
			 } else if(flag.equals("4")){
			 driverDeviceMapList = session.createQuery("SELECT d from DriverDeviceVehicleMapping d where d.loginStatus=0 and d.driverStatus=0").setFirstResult(pageNumber).
			 setMaxResults(pageSize).list();
			 session.flush();session.clear();
			 session.close();
			 } 
		 	if(driverDeviceMapList!=null && driverDeviceMapList.size()>0){ 
		 		for(int id=0;id<driverDeviceMapList.size();id++){
		 			if(driverDeviceMapList.get(id).getDriverStatus()==0){
		 				driverDeviceMapList.get(id).setDriverMessage("Available");
		 			}else if(driverDeviceMapList.get(id).getDriverStatus()==1){
		 				driverDeviceMapList.get(id).setDriverMessage("Busy");
		 			}
		 		}
		 		return driverDeviceMapList;}else
		  return null;}catch(Exception er){System.out.println(er.getMessage().toString());
		  session.close();
		  return null;}	
	}
	@SuppressWarnings("unchecked")
	
	
	public List<DriverDeviceVehicleMapping> getLogedInVehiclesReportByPaginationByVehicle(int pageNumber,int pageSize,String flags,String vehicleTyle,String city) {
		Session session = this.sessionFactory.openSession();
		try{
		 String flag=flags.trim();
		 	List<DriverDeviceVehicleMapping> driverDeviceMapList=null; 
		 	if(flag.equals("1")){
		 	 driverDeviceMapList =session.createQuery("from DriverDeviceVehicleMapping d where d.vehicleType='"+vehicleTyle+"'").
			 setFirstResult(pageNumber).
			 setMaxResults(pageSize).list();
		 	 session.flush();session.clear();
		 	 session.close();
		 	 } else if(flag.equals("2")){
			 driverDeviceMapList = session.createQuery("SELECT d from DriverDeviceVehicleMapping d where d.loginStatus=1 and d.vehicleType='"+vehicleTyle+"' and d.driverStatus=0").setFirstResult(pageNumber).setMaxResults(pageSize).list();
			 session.flush();session.clear();
			 session.close();
			 } else if(flag.equals("3")){
			 driverDeviceMapList = session.createQuery("SELECT d from DriverDeviceVehicleMapping d where d.loginStatus=1 and d.vehicleType='"+vehicleTyle+"' and d.driverStatus=1").setFirstResult(pageNumber).
			 setMaxResults(pageSize).list(); 
			 session.flush();session.clear();
			 session.close();
			 } else if(flag.equals("4")){
			 driverDeviceMapList = session.createQuery("SELECT d from DriverDeviceVehicleMapping d where d.loginStatus=0 and d.vehicleType='"+vehicleTyle+"' and d.driverStatus=0").setFirstResult(pageNumber).
			 setMaxResults(pageSize).list();
			 session.flush();session.clear();
			 session.close();
			 } 
		 	if(driverDeviceMapList!=null && driverDeviceMapList.size()>0){ 
		 		for(int id=0;id<driverDeviceMapList.size();id++){
		 			if(driverDeviceMapList.get(id).getDriverStatus()==0){
		 				driverDeviceMapList.get(id).setDriverMessage("Available");
		 			}else if(driverDeviceMapList.get(id).getDriverStatus()==1){
		 				driverDeviceMapList.get(id).setDriverMessage("Busy");
		 			}
		 		}
		 		return driverDeviceMapList;}else
		  return null;}catch(Exception er){System.out.println(er.getMessage().toString());
		  session.close();
		  return null;}	
	}


	
	public List<CustomerBookingDetails> getPendingBookings(int pageNumber,	int pageSize) {
		Session session = this.sessionFactory.openSession();
		try{
	 	@SuppressWarnings("unchecked")
	 	List<CustomerBookingDetails> bookingdetailsList = session.createQuery("from CustomerBookingDetails c where c.bookingStatus='Pending' order by c.createdDate desc").setFirstResult(pageNumber).setMaxResults(pageSize).list();
	 	 session.flush();session.clear();
		 session.close();
        
        if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
        	//System.out.println("BookingDAOImpl.getPendingBookings() pending booking list size **** " + bookingdetailsList.size());
        	return bookingdetailsList;
        }
        
        return null;}catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();
        return null;}	
	}

   
	
	public List<CustomerBookingDetails> getBookingDetaisByFilter(int pageNumber, int pageSize, OrderFilterReportsDto dto) {
		Session session = this.sessionFactory.openSession();
		try{
		List<CustomerBookingDetails> bookingdetailsList=null;
		
		try{
			/*if(dto==null){
			Query query = session.createQuery("FROM CustomerBookingDetails C order by C.createdDate desc").setFirstResult(pageNumber).setMaxResults(pageSize);
			bookingdetailsList= new ArrayList<CustomerBookingDetails>();
			bookingdetailsList.add(new CustomerBookingDetails());
			bookingdetailsList = query.list();
			 session.flush();session.clear();
			 session.close();
			}else */
			if(dto!=null){
			OrderSearchDAOImpl impl=new OrderSearchDAOImpl();
			bookingdetailsList= new ArrayList<CustomerBookingDetails>();
			bookingdetailsList.add(new CustomerBookingDetails());
			bookingdetailsList=	impl.searchOrderWithPaging(pageNumber,pageSize,dto, sessionFactory);
			
		}
		
	}catch(Exception er){er.printStackTrace();}
	 	//double totalAmountOfRevenue=0;
        try{ if(bookingdetailsList!=null && bookingdetailsList.size()>0){
        	int ii=1;
	 	for(int i=0;i<bookingdetailsList.size();i++){
	 		int sn=ii++;
	 		if(bookingdetailsList.get(i).getBooking_time()!=null){
	 			bookingdetailsList.get(i).setBookingDateTime(DateFormaterUtils.getDateTime(bookingdetailsList.get(i).getBooking_time().toString()));
	 		}else{
	 			bookingdetailsList.get(i).setBookingDateTime("");	
	 		}
	 		
	 		String dateTime=bookingdetailsList.get(i).getRideTime().toString();
	 		if(bookingdetailsList.get(i).getJourneycompletetime()!=null){
	 		String tripDateTime=bookingdetailsList.get(i).getJourneycompletetime().toString();
	 		if(tripDateTime!=null&&tripDateTime!=""){
	 			bookingdetailsList.get(i).setTripEndTimes(DateFormaterUtils.splitTimeIntoDate(tripDateTime));
	 			bookingdetailsList.get(i).setTripEndDates(DateFormaterUtils.getDateTime(tripDateTime));
	 		}
	 		}else{
	 			bookingdetailsList.get(i).setTripEndTimes("");
	 			bookingdetailsList.get(i).setTripEndDates("");
	 		
	 		}
	 		//System.out.println("dateTime :" +dateTime);
	 		if(dateTime!=null){
	 			bookingdetailsList.get(i).setTimes(DateFormaterUtils.splitTimeIntoDate(dateTime));
	 			bookingdetailsList.get(i).setDates(DateFormaterUtils.getDateTime(dateTime));
	 		}else{
	 			bookingdetailsList.get(i).setTimes("");
	 			bookingdetailsList.get(i).setDates("");
	 		}
	 		if(bookingdetailsList.get(i).getDriverPhonenumber()==null){
	 			bookingdetailsList.get(i).setDriverPhonenumber("") ;
	 		 }
	 		 if(bookingdetailsList.get(i).getDriverPhonenumber()==null){
	 			bookingdetailsList.get(i).setDriverPhonenumber("") ;
	 		 }
	 		if(bookingdetailsList.get(i).getDriverName()==null){
	 			bookingdetailsList.get(i).setDriverName("") ;
	 		 }
	 		if(bookingdetailsList.get(i).getVehicleNumber()==null){
	 			bookingdetailsList.get(i).setVehicleNumber("") ;
	 		 }
	 		if(bookingdetailsList.get(i).getVehicleType()==null){
	 			bookingdetailsList.get(i).setVehicleType("") ;
	 		 }
	    /*if(bookingdetailsList.get(i).getTotalFare()!=null){
        double totalFare=	bookingdetailsList.get(i).getTotalFare();
        //System.out.println("Total Fare "+ totalFare +" Revenue "+totalFare*10/100);
        double revenuTotal= totalFare*10/100;
        DecimalFormat df = new DecimalFormat("###.##");
        df.setMaximumFractionDigits(2);
        double value= Double.parseDouble(df.format(revenuTotal));
        totalAmountOfRevenue=totalAmountOfRevenue+revenuTotal;
        bookingdetailsList.get(i).setRevenue(value);
         }else{bookingdetailsList.get(i).setTotalFare(0.00);}*/
	    bookingdetailsList.get(i).setSerialNo(sn);
        }
	 	 //DecimalFormat df = new DecimalFormat("###.##");
	     //df.setMaximumFractionDigits(2);
	     //double totalAmountOfRevenueValue= Double.parseDouble(""+df.format(totalAmountOfRevenue));
	    // bookingdetailsList.get(0).setTotalAmountOfRevenue(totalAmountOfRevenueValue);
        }
        }catch(Exception e){e.printStackTrace();
        session.close();
        }
	 	session.close();
        
        if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
        	//System.out.println("Booking revenue list size **** " + bookingdetailsList.size());
        	
        	return bookingdetailsList;
        }else{
		return null;
        }}catch(Exception er){System.out.println(er.getMessage().toString());return null;}	
	}

  
	@SuppressWarnings("unchecked")
	
	public List<CustomerBookingDetails> getBookingDetaisByFilter(OrderFilterReportsDto dto) {
	 
		Session session = this.sessionFactory.openSession();
		List<CustomerBookingDetails> bookingdetailsList=null;
		try{
			if(dto==null){
				Query query = session.createQuery("FROM CustomerBookingDetails C order by C.createdDate desc");
				bookingdetailsList= new ArrayList<CustomerBookingDetails>();
				bookingdetailsList.add(new CustomerBookingDetails());
				bookingdetailsList = query.list();
				 session.flush();session.clear();
				 session.close();
				
			}else{
				OrderSearchDAOImpl impl=new OrderSearchDAOImpl();
				bookingdetailsList= new ArrayList<CustomerBookingDetails>();
				bookingdetailsList.add(new CustomerBookingDetails());
				bookingdetailsList=	impl.searchOrder(dto, sessionFactory);
				
			}
		 
        if(bookingdetailsList != null && bookingdetailsList.size()>0){
        	//System.out.println("BookingDAOImpl.getPendingBookings() pending booking list size **** " + bookingdetailsList.size());
        	return bookingdetailsList;
        }else{
		return null;
        }}catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();
        return null;}	
	}

 
	@SuppressWarnings("unchecked")
	
	public List<CustomerBookingDetails> getBookingRevenueDetaisByFilters(int pageNumber, int pageSize, OrderFilterReportsDto dto) {
		try{
		
		List<CustomerBookingDetails> bookingdetailsLists=new ArrayList<CustomerBookingDetails>();
		List<Object[]> bookingdetailsList=null;
		
		if(dto==null){
			Session session = this.sessionFactory.openSession();
			Query query = session.createQuery("select date(C.createdDate),count(C) as TotalCount,SUM(C.totalFare) as totalFare FROM CustomerBookingDetails C where C.totalFare is not null group by date(C.createdDate) order by date(C.createdDate) desc").setFirstResult(pageNumber).setMaxResults(pageSize);
			bookingdetailsList = query.list();
			 session.flush();
			 session.clear();
			 session.close();
			  
		}else { 
			OrderSearchDAOImpl impl=new OrderSearchDAOImpl();
			bookingdetailsList=	impl.searchRevenueWithPaging(pageNumber,pageSize, dto, sessionFactory);
		}
		 try{ 
			 if(bookingdetailsList!=null && bookingdetailsList.size()>0){
	         double totalAmountOfRevenue=0;
	   	 	 double grassTotal=0;
		 	for(Object[] dt:bookingdetailsList){
		  		CustomerBookingDetails dtos=new CustomerBookingDetails();
		 		dtos.setCreatedDate((Date)dt[0]);
		  		dtos.setDates(dt[0].toString());
		 		dtos.setState(""+dt[1]);
		 		dtos.setTotalFare((Double)dt[2]);
		 		bookingdetailsLists.add(dtos);
		 	}
		 	 DecimalFormat df = new DecimalFormat("###.##");
		        df.setMaximumFractionDigits(2);
		 	for(int i=0;i<bookingdetailsLists.size();i++){
		    if(bookingdetailsLists.get(i).getTotalFare()!=null){
	        double totalFare=bookingdetailsLists.get(i).getTotalFare();
	        String totalCount=  bookingdetailsLists.get(i).getState();
	        Integer  total=new Integer(0);
	        if(totalCount!=null && !totalCount.equals("")){
	        	  total=Integer.parseInt(totalCount);
	        }
	        double averageFare=totalFare/total;
	        bookingdetailsLists.get(i).setTotalAmountOfRevenue(Double.parseDouble(df.format(averageFare)));
	        double revenuTotal= totalFare*10/100;
	        grassTotal=grassTotal+totalFare;
	        
	       
	        bookingdetailsLists.get(i).setTotalFares(Double.parseDouble(df.format(totalFare)));
	        double value= Double.parseDouble(df.format(revenuTotal));
	        totalAmountOfRevenue=totalAmountOfRevenue+revenuTotal;
	        bookingdetailsLists.get(i).setRevenue(value); 
		    }else{bookingdetailsLists.get(i).setTotalFare(0.00);}}
		    
	        //System.out.println("Total Revenue befor Formated :" +totalAmountOfRevenue);
	        String totalAmountOfRevenues=df.format(totalAmountOfRevenue);
	        if(totalAmountOfRevenues!=null){
	        //System.out.println("totalAmountOfRevenues after formated :"+totalAmountOfRevenues);
	        //double totalAmountOfRevenueValue= Double.parseDouble(totalAmountOfRevenues);	        
	 	   // bookingdetailsLists.get(0).setTotalAmountOfRevenue(totalAmountOfRevenueValue);
	        
		 	}		 	   
	        }
	        }catch(Exception e){e.printStackTrace();}
	        
        
        if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
        	//System.out.println("Booking Revenue list size **** " + bookingdetailsList.size());
        	return bookingdetailsLists;
        }
		return null; 
		}catch(Exception er){System.out.println(er.getMessage().toString());return null;}	
	}

 
	@SuppressWarnings("unchecked")
	
	public List<Object[]> getBookingRevenueDetaisByFilter(OrderFilterReportsDto dto) {
		try{
		Session session = this.sessionFactory.openSession();
		List<CustomerBookingDetails> bookingdetailsLists=null;
		List<Object[]> bookingdetailsList=null;
		
		if(dto==null){
			  Query query = session.createQuery("select date(C.createdDate),count(C) as TotalCount,SUM(C.totalFare) as totalFare FROM CustomerBookingDetails C where C.totalFare is not null group by date(C.createdDate) order by date(C.createdDate) desc");
			  bookingdetailsList = query.list();
			  session.flush();session.clear();
			session.close();
			  
		}else{
			OrderSearchDAOImpl impl=new OrderSearchDAOImpl();			
			bookingdetailsList=	impl.searchRevenue(dto, sessionFactory);
		}
		 		 	
	        try{ if(bookingdetailsList!=null && bookingdetailsList.size()>0){
	         double totalAmountOfRevenue=0;
	   	 	 double grassTotal=0;
	   	    bookingdetailsLists=new ArrayList<CustomerBookingDetails>();
		 	for(Object[] dt:bookingdetailsList){
		  		CustomerBookingDetails dtos=new CustomerBookingDetails();
		 		dtos.setCreatedDate((Date)dt[0]);
		 		dtos.setState(""+dt[1]);
		 		dtos.setTotalFare((Double)dt[2]);
		 		bookingdetailsLists.add(dtos);
		 	}
		 	for(int i=0;i<bookingdetailsLists.size();i++){
		    if(bookingdetailsLists.get(i).getTotalFare()!=null){
	        double totalFare=bookingdetailsLists.get(i).getTotalFare();
	        String totalCount=  bookingdetailsLists.get(i).getState();
	        Integer  total=new Integer(0);
	        if(totalCount!=null && !totalCount.equals("")){
	        	  total=Integer.parseInt(totalCount);
	        }
	        double totalFairs=totalFare/total;
	        bookingdetailsLists.get(i).setTotalAmountOfRevenue(totalFairs);
	        double revenuTotal= totalFare*10/100;
	        grassTotal=grassTotal+totalFare;
	        DecimalFormat df = new DecimalFormat("###.##");
	        df.setMaximumFractionDigits(2);
	        bookingdetailsLists.get(i).setTotalFares(Double.parseDouble(df.format(totalFare)));
	        double value= Double.parseDouble(df.format(revenuTotal));
	        totalAmountOfRevenue=totalAmountOfRevenue+revenuTotal;
	        bookingdetailsLists.get(i).setRevenue(value); 
		    }}
		    DecimalFormat df = new DecimalFormat("###.##");
	        df.setMaximumFractionDigits(2);
	        //System.out.println("Total Revenue befor Formated :" +totalAmountOfRevenue);
	        String totalAmountOfRevenues=df.format(totalAmountOfRevenue);
	        if(totalAmountOfRevenues!=null){
	        //System.out.println("totalAmountOfRevenues after formated :"+totalAmountOfRevenues);
	        double totalAmountOfRevenueValue= Double.parseDouble(totalAmountOfRevenues);	  
	         
	 	  //  bookingdetailsLists.get(0).setTotalAmountOfRevenue(totalAmountOfRevenueValue);
	        
		 	}		 	   
	        }
	        }catch(Exception e){e.printStackTrace();}
       
	 	 
        
        if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
        	//System.out.println("Booking Revenue list size **** " + bookingdetailsList.size());
        	return bookingdetailsList;
        }else{ 
        	return null;
        }}catch(Exception er){System.out.println(er.getMessage().toString());return null;}	
	}


	
	public CustomerBookingDetails updateBookedVehicle(long bookingId,String vehicleType) {
		
		Session session = this.sessionFactory.openSession();
		try{
		 String booingIds=""+bookingId;
		 
		Transaction tx=session.beginTransaction();		
		CustomerBookingDetails customerBookingDetails=(CustomerBookingDetails) session.load(CustomerBookingDetails.class, new Integer(booingIds));
		tx.commit();
		session.close();
		customerBookingDetails.setVehicleType(vehicleType);
		Session sessions = this.sessionFactory.openSession();
		
		Transaction tx1=sessions.beginTransaction();
		
		CustomerBookingDetails customerBookingDetail=(CustomerBookingDetails)sessions.merge(customerBookingDetails);
		customerBookingDetail.setVehicleType(vehicleType);
		tx1.commit();		 
	    sessions.close();
	   
		return customerBookingDetail;}catch(Exception er){System.out.println(er.getMessage().toString());
		session.close();
		return null;}	
	}

	@SuppressWarnings("unchecked")
	
	
	public List<DriverDeviceVehicleMapping> searchLoginLogoutDriver(int numbnerOfPage,int pageSize ,OrderFilterReportsDto dto) {
		Session session = this.sessionFactory.openSession();
		try{
			Map<String, Object> parameters = new HashMap<String, Object>();
	 	List<DriverDeviceVehicleMapping> driverDeviceMapList=null; 
		if(dto==null){	
		driverDeviceMapList = session.createQuery("SELECT d from DriverDeviceVehicleMapping d where d.loginStatus=1").setFirstResult(numbnerOfPage).setMaxResults(pageSize).list(); 
		session.flush();session.clear();
		session.close();
		if(driverDeviceMapList!=null && driverDeviceMapList.size()>0){ 
  
			
	 		for(int id=0;id<driverDeviceMapList.size();id++){
	 			if(driverDeviceMapList.get(id).getLastLoginTime()!=null){
		 			 String loginDateTime=driverDeviceMapList.get(id).getLastLoginTime().toString();
		 		 		if(loginDateTime!=null){
		 		 			driverDeviceMapList.get(id).setDriverLoginTime(DateFormaterUtils.splitTimeIntoDate(loginDateTime));
		 		 			driverDeviceMapList.get(id).setDriverLoginDate(DateFormaterUtils.splitDateIntoTime(loginDateTime));
		 		 		}}
		 			if(driverDeviceMapList.get(id).getLastLogoutTime()!=null){
		 		 		String logoutDateTime=driverDeviceMapList.get(id).getLastLogoutTime().toString();
		 		 		if(logoutDateTime!=null){
		 		 			driverDeviceMapList.get(id).setDriverLogoutTime(DateFormaterUtils.splitTimeIntoDate(logoutDateTime));
		 		 			driverDeviceMapList.get(id).setDriverLogoutDate(DateFormaterUtils.splitDateIntoTime(logoutDateTime));
		 		 		}}
		 			if(driverDeviceMapList.get(id).getLastLogoutTime()!=null && driverDeviceMapList.get(id).getLastLogoutTime()!=null){
		 			   driverDeviceMapList.get(id).setDriverLoginDurationTime(JulianDayHoursMinuteSecondCount.diffBetweenTwoDate( driverDeviceMapList.get(id).getLastLogoutTime().toString(),driverDeviceMapList.get(id).getLastLoginTime().toString()));
		 			}
	 			if(driverDeviceMapList.get(id).getDriverStatus()==0){
	 				driverDeviceMapList.get(id).setDriverMessage("Available");
	 			}else if(driverDeviceMapList.get(id).getDriverStatus()==1){
	 				driverDeviceMapList.get(id).setDriverMessage("Busy");
	 			}
	 		}
	 		return driverDeviceMapList;
	 		}else{
	     return null;
	  }
		}else{
		boolean firstClause = true;
		StringBuffer queryBuf = new StringBuffer("FROM DriverDeviceVehicleMapping  a ");
		if (dto.getStatus() != null && dto.getStatus() !="") {
			if(dto.getStatus().equals("1")){
				queryBuf.append(firstClause ? " where " : " and ");
				queryBuf.append("a.loginStatus = :loginStatus and a.driverStatus=:driverStatus");
				parameters.put("loginStatus", 1);
				parameters.put("driverStatus", 0);
				firstClause = false; 
				} else if(dto.getStatus().equals("2")){
		 		queryBuf.append(firstClause ? " where " : " and ");
				queryBuf.append("a.loginStatus = :loginStatus and a.driverStatus=:driverStatus");
				parameters.put("loginStatus", 1);
				parameters.put("driverStatus", 0);
				firstClause = false; 
				 
			 } else if(dto.getStatus().equals("3")){
				 queryBuf.append(firstClause ? " where " : " and ");
					queryBuf.append("a.loginStatus = :loginStatus and a.driverStatus=:driverStatus");
					parameters.put("loginStatus", 1);
					parameters.put("driverStatus", 1);
					firstClause = false; 
			 
			 } else if(dto.getStatus().equals("4")){
				 queryBuf.append(firstClause ? " where " : " and ");
					queryBuf.append("a.loginStatus = :loginStatus and a.driverStatus=:driverStatus");
					parameters.put("loginStatus", 0);
					parameters.put("driverStatus", 0);
					firstClause = false; 
			 } }
		 
			if (dto.getVehicleType() != null && dto.getVehicleType() != "") {
				queryBuf.append(firstClause ? " where " : " and ");
				queryBuf.append("a.vehicleType= :vehicleType");
				parameters.put("vehicleType", dto.getVehicleType());
				firstClause = false;
			}
		
		String hqlQuery = queryBuf.toString();
        //System.out.println("hqlQuery :"+hqlQuery);
		Query query = session.createQuery(hqlQuery);
		Iterator<String> iter = parameters.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			Object value = parameters.get(name);
			//System.out.println(value);            			
			query.setParameter(name, value);
			
		} 
		driverDeviceMapList=query.setFirstResult(numbnerOfPage).setMaxResults(pageSize).list();
		session.flush();session.clear();
		session.close();
		if(driverDeviceMapList!=null && driverDeviceMapList.size()>0){ 
			
	 		for(int id=0;id<driverDeviceMapList.size();id++){
	 			if(driverDeviceMapList.get(id).getLastLoginTime()!=null){
		 			 String loginDateTime=driverDeviceMapList.get(id).getLastLoginTime().toString();
		 		 		if(loginDateTime!=null){
		 		 			driverDeviceMapList.get(id).setDriverLoginTime(DateFormaterUtils.splitTimeIntoDate(loginDateTime));
		 		 			driverDeviceMapList.get(id).setDriverLoginDate(DateFormaterUtils.splitDateIntoTime(loginDateTime));
		 		 		}}
		 			if(driverDeviceMapList.get(id).getLastLogoutTime()!=null){
		 		 		String logoutDateTime=driverDeviceMapList.get(id).getLastLogoutTime().toString();
		 		 		if(logoutDateTime!=null){
		 		 			driverDeviceMapList.get(id).setDriverLogoutTime(DateFormaterUtils.splitTimeIntoDate(logoutDateTime));
		 		 			driverDeviceMapList.get(id).setDriverLogoutDate(DateFormaterUtils.splitDateIntoTime(logoutDateTime));
		 		 		}}
		 			if(driverDeviceMapList.get(id).getLastLogoutTime()!=null && driverDeviceMapList.get(id).getLastLogoutTime()!=null){
			 			   driverDeviceMapList.get(id).setDriverLoginDurationTime(JulianDayHoursMinuteSecondCount.diffBetweenTwoDate( driverDeviceMapList.get(id).getLastLogoutTime().toString(),driverDeviceMapList.get(id).getLastLoginTime().toString()));
			 			}
	 			if(driverDeviceMapList.get(id).getDriverStatus()==0){
	 				driverDeviceMapList.get(id).setDriverMessage("Available");
	 			}else if(driverDeviceMapList.get(id).getDriverStatus()==1){
	 				driverDeviceMapList.get(id).setDriverMessage("Busy");
	 			}
	 		}
	 		return driverDeviceMapList;
	 		}else{
	         return null;
	        }
		}}catch(Exception er){System.out.println(er.getMessage().toString());
		session.close();
		return null;}	
	}	 

	@SuppressWarnings("unchecked")
	
	public List<DriverDeviceVehicleMapping> searchLoginLogoutDriver(OrderFilterReportsDto dto) {
		Session session = sessionFactory.openSession();
		try{
		 Map<String, Object> parameters = new HashMap<String, Object>();
		List<DriverDeviceVehicleMapping> driverDeviceMapList=null; 
		if(dto==null){	
		driverDeviceMapList = session.createQuery("SELECT d from DriverDeviceVehicleMapping d where d.loginStatus=1").list(); 
		 session.flush();session.clear();
		 session.close();
		if(driverDeviceMapList!=null && driverDeviceMapList.size()>0){ 	 			 
 		return driverDeviceMapList;
		}else{
			return null;
		}
 		}else{
		boolean firstClause = true;
		StringBuffer queryBuf = new StringBuffer("FROM DriverDeviceVehicleMapping  a ");
		if (dto.getStatus() != null && dto.getStatus() !="") {
		if(dto.getStatus().equals("1")){
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.loginStatus = :loginStatus and a.driverStatus=:driverStatus");
			parameters.put("loginStatus", 1);
			parameters.put("driverStatus", 0);
			firstClause = false; 
	 	 } else if(dto.getStatus().equals("2")){
	 		queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.loginStatus = :loginStatus and a.driverStatus=:driverStatus");
			parameters.put("loginStatus", 1);
			parameters.put("driverStatus", 0);
			firstClause = false; 
			 
		 } else if(dto.getStatus().equals("3")){
			 queryBuf.append(firstClause ? " where " : " and ");
				queryBuf.append("a.loginStatus = :loginStatus and a.driverStatus=:driverStatus");
				parameters.put("loginStatus", 1);
				parameters.put("driverStatus", 1);
				firstClause = false; 
		 
		 } else if(dto.getStatus().equals("4")){
			 queryBuf.append(firstClause ? " where " : " and ");
				queryBuf.append("a.loginStatus = :loginStatus and a.driverStatus=:driverStatus");
				parameters.put("loginStatus", 0);
				parameters.put("driverStatus", 0);
				firstClause = false; 
		 } }
	 
		if (dto.getVehicleType() != null && dto.getVehicleType() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.vehicleType= :vehicleType");
			parameters.put("vehicleType", dto.getVehicleType());
			firstClause = false;
		}
	 
		
		String hqlQuery = queryBuf.toString();
        //System.out.println("hqlQuery :"+hqlQuery);
		Query query = session.createQuery(hqlQuery);
		Iterator<String> iter = parameters.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			Object value = parameters.get(name);
			//System.out.println(value);            			
			query.setParameter(name, value);
			
		} 
		driverDeviceMapList=query.list();
		session.flush();session.clear();
		session.close();
		if(driverDeviceMapList!=null && driverDeviceMapList.size()>0){ 
			 
	 		return driverDeviceMapList;
	 		} else 
	 		{
	 			return null;
	 		}
		}}catch(Exception er){System.out.println(er.getMessage().toString());
		session.close();
		return null;}	
	}


	
	public List<CustomerBookingDetails> getBookingDetaisByFilterToGrid(	OrderFilterReportsDto dto) {
		List<CustomerBookingDetails> bookingdetailsList=null;
		 
		try{ 
		 	if(dto!=null){
 			/*	if(dto.getDstatus().equals("On-DemandCAPP")){
 				bookingdetailsList= new ArrayList<CustomerBookingDetails>();
				 StatelessSession statelessSession = this.sessionFactory.openStatelessSession();
				     try {
				     ScrollableResults scrollableResults = statelessSession.createQuery("FROM CustomerBookingDetails C where C.bookingMode is null order by C.createdDate desc").scroll(ScrollMode.FORWARD_ONLY);
				     while(scrollableResults.next()) {
				    	 CustomerBookingDetails demoEntity = (CustomerBookingDetails) scrollableResults.get()[0];
				    	 bookingdetailsList.add(demoEntity);
				   } 
				   } finally {
					  statelessSession.close();
				     }
 				}else{*/
			OrderSearchDAOImpl impl=new OrderSearchDAOImpl();
			bookingdetailsList= new ArrayList<CustomerBookingDetails>();
			bookingdetailsList.add(new CustomerBookingDetails());
			bookingdetailsList=	impl.searchOrder(dto, sessionFactory);
 				/*}*/
		}
		
	}catch(Exception er){er.printStackTrace();}
	 	 
        try{ if(bookingdetailsList!=null && bookingdetailsList.size()>0){
        	int ii=1;
	 	for(int i=0;i<bookingdetailsList.size();i++){
	 		int sn=ii++;
	 		if(bookingdetailsList.get(i).getBooking_time()!=null){
	 			bookingdetailsList.get(i).setBookingDateTime(DateFormaterUtils.getDateTime(bookingdetailsList.get(i).getBooking_time().toString()));
	 		}else{
	 			bookingdetailsList.get(i).setBookingDateTime("");	
	 		}
	 		
	 		String dateTime=bookingdetailsList.get(i).getRideTime().toString();
	 		if(bookingdetailsList.get(i).getJourneycompletetime()!=null){
	 		String tripDateTime=bookingdetailsList.get(i).getJourneycompletetime().toString();
	 		if(tripDateTime!=null&&tripDateTime!=""){
	 			bookingdetailsList.get(i).setTripEndTimes(DateFormaterUtils.splitTimeIntoDate(tripDateTime));
	 			bookingdetailsList.get(i).setTripEndDates(DateFormaterUtils.getDateTime(tripDateTime));
	 		}
	 		}else{
	 			bookingdetailsList.get(i).setTripEndTimes("");
	 			bookingdetailsList.get(i).setTripEndDates("");
	 		
	 		}
	 		//System.out.println("dateTime :" +dateTime);
	 		if(dateTime!=null){
	 			bookingdetailsList.get(i).setTimes(DateFormaterUtils.splitTimeIntoDate(dateTime));
	 			
	 			bookingdetailsList.get(i).setDates(DateFormaterUtils.getDateTime(dateTime));
	 		}else{
	 			bookingdetailsList.get(i).setTimes("");
	 			bookingdetailsList.get(i).setDates("");
	 		}
	 		if(bookingdetailsList.get(i).getDriverPhonenumber()==null){
	 			bookingdetailsList.get(i).setDriverPhonenumber("") ;
	 		 }
	 		 if(bookingdetailsList.get(i).getDriverPhonenumber()==null){
	 			bookingdetailsList.get(i).setDriverPhonenumber("") ;
	 		 }
	 		if(bookingdetailsList.get(i).getDriverName()==null){
	 			bookingdetailsList.get(i).setDriverName("") ;
	 		 }
	 		if(bookingdetailsList.get(i).getVehicleNumber()==null){
	 			bookingdetailsList.get(i).setVehicleNumber("") ;
	 		 }
	 		if(bookingdetailsList.get(i).getVehicleType()==null){
	 			bookingdetailsList.get(i).setVehicleType("") ;
	 		 }
	 		if(bookingdetailsList.get(i).getTotalFare()==null){bookingdetailsList.get(i).setTotalFare(new Double("0"));}
	 		if(bookingdetailsList.get(i).getCreatedDate()!=null){
	 			String bookingDate=""+bookingdetailsList.get(i).getCreatedDate();
	 			bookingDate=bookingDate.replace(".0", "");
	 			bookingdetailsList.get(i).setBookingDurationOfTime(bookingDate);
	 		}
	 		if( bookingdetailsList.get(i).getRideTime()!=null &&  bookingdetailsList.get(i).getCreatedDate()!=null){
	 			String tripDate=""+bookingdetailsList.get(i).getRideTime();
	 			tripDate=tripDate.replace(".0", "");
	 			bookingdetailsList.get(i).setStatusChangeDurationOfTime(tripDate);
	 			bookingdetailsList.get(i).setStatusChangeDurationOfTimeDateFormate(JulianDayHoursMinuteSecondCount.diffBetweenTwoDate(bookingdetailsList.get(i).getRideTime().toString(),DateFormaterUtils.convertGMTToISTWithDates(new Date().toString())));
	 		 	
	 		  }  
	 		bookingdetailsList.get(i).setSerialNo(sn);
        }
	   }
         if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
        	//System.out.println("Booking revenue list size **** " + bookingdetailsList.size());
           return bookingdetailsList;
        }else{
		return null;
        }}catch(Exception er){System.out.println(er.getMessage().toString());return null;}	
	}
	 
	public List<CustomerBookingDetails> getBackEndBookingDetaisByFilterToGrid(	OrderFilterReportsDto dto) {
		//Session session = this.sessionFactory.openSession();
		List<CustomerBookingDetails> bookingdetailsList=null;
			try{
			/*if(dto==null){
				bookingdetailsList= new ArrayList<CustomerBookingDetails>();
				 StatelessSession statelessSession = this.sessionFactory.openStatelessSession();
				     try {
				    ScrollableResults scrollableResults = statelessSession.createQuery("FROM CustomerBookingDetails C where C.bookingMode='B' order by C.createdDate desc").scroll(ScrollMode.FORWARD_ONLY);
				     while(scrollableResults.next()) {
				    	 CustomerBookingDetails demoEntity = (CustomerBookingDetails) scrollableResults.get()[0];
				    	 bookingdetailsList.add(demoEntity);
				   } 
				   } finally {
					  statelessSession.close();
				     }
 			}else{*/
 			if(dto!=null){
			OrderSearchDAOImpl impl=new OrderSearchDAOImpl();
			bookingdetailsList= new ArrayList<CustomerBookingDetails>();
			bookingdetailsList.add(new CustomerBookingDetails());
			bookingdetailsList=	impl.backEndBooigRideSearchOrder(dto, sessionFactory);
				
		}
		
	}catch(Exception er){er.printStackTrace();}
	 	 
        try{ if(bookingdetailsList!=null && bookingdetailsList.size()>0){
        	int ii=1;
	 	for(int i=0;i<bookingdetailsList.size();i++){
	 		int sn=ii++;
	 		if(bookingdetailsList.get(i).getBooking_time()!=null){
	 			bookingdetailsList.get(i).setBookingDateTime(DateFormaterUtils.getDateTime(bookingdetailsList.get(i).getBooking_time().toString()));
	 		}else{
	 			bookingdetailsList.get(i).setBookingDateTime("");	
	 		}
	 		
	 		String dateTime=bookingdetailsList.get(i).getRideTime().toString();
	 		if(bookingdetailsList.get(i).getJourneycompletetime()!=null){
	 		String tripDateTime=bookingdetailsList.get(i).getJourneycompletetime().toString();
	 		if(tripDateTime!=null&&tripDateTime!=""){
	 			bookingdetailsList.get(i).setTripEndTimes(DateFormaterUtils.splitTimeIntoDate(tripDateTime));
	 			bookingdetailsList.get(i).setTripEndDates(DateFormaterUtils.getDateTime(tripDateTime));
	 		}
	 		}else{
	 			bookingdetailsList.get(i).setTripEndTimes("");
	 			bookingdetailsList.get(i).setTripEndDates("");
	 		
	 		}
	 		//System.out.println("dateTime :" +dateTime);
	 		if(dateTime!=null){
	 			bookingdetailsList.get(i).setTimes(DateFormaterUtils.splitTimeIntoDate(dateTime));
	 			bookingdetailsList.get(i).setDates(DateFormaterUtils.getDateTime(dateTime));
	 		}else{
	 			bookingdetailsList.get(i).setTimes("");
	 			bookingdetailsList.get(i).setDates("");
	 		}
	 		if(bookingdetailsList.get(i).getDriverPhonenumber()==null){
	 			bookingdetailsList.get(i).setDriverPhonenumber("") ;
	 		 }
	 		 if(bookingdetailsList.get(i).getDriverPhonenumber()==null){
	 			bookingdetailsList.get(i).setDriverPhonenumber("") ;
	 		 }
	 		if(bookingdetailsList.get(i).getDriverName()==null){
	 			bookingdetailsList.get(i).setDriverName("") ;
	 		 }
	 		if(bookingdetailsList.get(i).getVehicleNumber()==null){
	 			bookingdetailsList.get(i).setVehicleNumber("") ;
	 		 }
	 		if(bookingdetailsList.get(i).getVehicleType()==null){
	 			bookingdetailsList.get(i).setVehicleType("") ;
	 		 }
	 		if(bookingdetailsList.get(i).getTotalFare()==null){bookingdetailsList.get(i).setTotalFare(new Double("0"));}
	 		if(bookingdetailsList.get(i).getCreatedDate()!=null){
	 			String bookingDate=""+bookingdetailsList.get(i).getCreatedDate();
	 			bookingDate=bookingDate.replace(".0", "");
	 			bookingdetailsList.get(i).setBookingDurationOfTime(bookingDate);
	 		}
	 		if( bookingdetailsList.get(i).getRideTime()!=null &&  bookingdetailsList.get(i).getCreatedDate()!=null){
	 			String tripDate=""+bookingdetailsList.get(i).getRideTime();
	 			tripDate=tripDate.replace(".0", "");
	 			bookingdetailsList.get(i).setStatusChangeDurationOfTime(tripDate);
	 			bookingdetailsList.get(i).setStatusChangeDurationOfTimeDateFormate(JulianDayHoursMinuteSecondCount.diffBetweenTwoDate(bookingdetailsList.get(i).getRideTime().toString(),DateFormaterUtils.convertGMTToISTWithDates(new Date().toString())));
	 		 	
	 		  }  
	 		bookingdetailsList.get(i).setSerialNo(sn);
        }
	   } 
	 	   if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
        	//System.out.println("Booking revenue list size **** " + bookingdetailsList.size());
           return bookingdetailsList;
        }else{
		return null;
        }
         }catch(Exception er){System.out.println(er.getMessage().toString());return null;}	
	}

	
	public void editCustomerBookingDetails(CustomerBookingDetails dto) {
		try{Session session = this.sessionFactory.openSession();
		
		CustomerBookingDetails dto1=null;
		Object obj = session.get(CustomerBookingDetails.class, new Integer(dto.getBookingId()));
		dto1 = (CustomerBookingDetails)obj;
		session.close();
		dto1.setBookingStatus(dto.getBookingStatus()); 
		dto1.setFromLocation(dto.getFromLocation());
		dto1.setToLocation(dto.getToLocation());
		dto1.setCustmerPhonenumber(dto.getCustmerPhonenumber());
		dto1.setVehicleNumber(dto.getVehicleNumber());
		dto1.setVehicleType(dto.getVehicleType());
		dto1.setDriverName(dto.getDriverName());
		dto1.setDriverPhonenumber(dto.getDriverPhonenumber());
		dto1.setDstatus(dto.getDstatus());
		if(dto.getRideTime()!=null){
		dto1.setRideTime(dto.getRideTime());
		}
		if(dto.getBookingStatus().equals("Confirmed")){
			dto1.setBooking_acceptedtime(new Date());
			
		}
		
		if(dto.getBookingStatus().equals("Loading Start")){
			dto1.setLoading_starttime(new Date());
		}
		if(dto.getBookingStatus().equals("Loading Complete")){
			dto1.setLoading_completetime(new Date());
		}
		if(dto.getBookingStatus().equals("Ride Start")){
			dto1.setRide_starttime(new Date());
			
		}
		if(dto.getBookingStatus().equals("On Ride")){
			dto1.setLoading_completetime(new Date());
		}
		if(dto.getBookingStatus().equals("Unloading Start")){
			dto1.setUnloading_starttime(new Date());
		}
		if(dto.getBookingStatus().equals("Completed")){
			dto1.setJourneycompletetime(new Date());
			dto1.setTotalFare(dto.getTotalFare());
		}
		if(dto.getBookingStatus().equals("Cancelled")){
			dto1.setJourneycompletetime(new Date());
			dto1.setTotalFare(null);
		}
		Session session1 = this.sessionFactory.openSession();
	
		session1.get(CustomerBookingDetails.class, new Integer(dto.getBookingId()));
		Transaction tx=session1.beginTransaction();		 
		session1.merge(dto1);
		tx.commit();
		session1.clear(); 
		 session1.close();
	}catch(Exception er){System.out.println(er.getMessage().toString());}	
		
	}


	
	public List<CustomerBookingDetails> searchByMobile(String mobile) {
		try{Session session = this.sessionFactory.openSession();
	 	@SuppressWarnings("unchecked")
	 	List<CustomerBookingDetails> bookingdetailsList = session.createQuery("from CustomerBookingDetails c where c.custmerPhonenumber='"+mobile+"'").list();
	 	 session.flush();session.clear();
		 session.close();
        
        if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
        	return bookingdetailsList;
        }else        
        return null;
       }catch(Exception er){System.out.println(er.getMessage().toString());return null;}	
	}


	
	public List<CustomerBookingDetails> getAllCustomerBookingDetails() {
		try{Session session = this.sessionFactory.openSession();
	 	@SuppressWarnings("unchecked")
	 	List<CustomerBookingDetails> bookingdetailsList = session.createQuery("SELECT c from CustomerBookingDetails c").list();
	 	 session.flush();session.clear();
		 session.close();
        
        if(bookingdetailsList != null && !bookingdetailsList.isEmpty()){
        	return bookingdetailsList;
        }else        
        return null;}catch(Exception er){System.out.println(er.getMessage().toString());return null;}	
	}
	
	public RescheduleTrip rescheduleTirp(RescheduleTrip dto) {
	try {			
		Session session = sessionFactory.openSession();
		 SessionImpl sessionImpl = (SessionImpl) session;
	     java.sql.Connection connection=sessionImpl.connection();	   
		 PreparedStatement ps = connection.prepareStatement("update booking  set bookingStatus='Pending', rideTime='"+dto.getPickupTime()+"',vehicleType='"+dto.getVehicleType()+"' where bookingId="+dto.getBookingId()+";");
         boolean flag= ps.execute();
         connection.close();
		 session.flush();
	      session.clear();
	      session.close(); 
	      if(flag==false){
	    	  RescheduleTrip dtos=  new RescheduleTrip();
	     	   dtos.setStatus("Success");
	    	  return dtos;
	       } 
	      else{ RescheduleTrip dtos=  new RescheduleTrip();
    	   dtos.setStatus("Fail");
   	  return dtos;
	    	  }
	    }catch(Exception er){er.printStackTrace();
	    RescheduleTrip dtos=  new RescheduleTrip();
   	   dtos.setErrorCode("EU01");
   	   dtos.setErrorMessage(er.getMessage().toString());
   	   dtos.setStatus("Fail");
   	  return dtos;
   	  }
	}
	public RescheduleTrip cencelledTirp(RescheduleTrip dto) {
		try {			
			Session session = sessionFactory.openSession();
			 SessionImpl sessionImpl = (SessionImpl) session;
		     java.sql.Connection connection=sessionImpl.connection();	   
			 PreparedStatement ps = connection.prepareStatement("update booking  set bookingStatus='Cancelled' where bookingId="+dto.getBookingId()+";");
	         boolean flag= ps.execute();
	         connection.close();
			 session.flush();
		      session.clear();
		      session.close(); 
		      if(flag==false){
		    	  RescheduleTrip dtos=  new RescheduleTrip();
		     	   dtos.setStatus("Success");
		    	  return dtos;
		       } 
		      else{ RescheduleTrip dtos=  new RescheduleTrip();
	    	   dtos.setStatus("Fail");
	   	  return dtos;
		    	  }
		    }catch(Exception er){er.printStackTrace();
		    RescheduleTrip dtos=  new RescheduleTrip();
	   	   dtos.setErrorCode("EU01");
	   	   dtos.setErrorMessage(er.getMessage().toString());
	   	   dtos.setStatus("Fail");
	   	  return dtos;
	   	  }
		}
}
