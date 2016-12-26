package com.trux.dao;

import java.util.List;

import com.trux.model.CustomerBookingDetails;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.OrderFilterReportsDto;
import com.trux.model.RescheduleTrip;



public interface BookingDAO {

	public void saveOrUpdate(Object o);

	public void editCustomerBookingDetails(CustomerBookingDetails dto);
	public CustomerBookingDetails getBookingDetailsById(Integer bookingId);
	public CustomerBookingDetails getBookingDetailsByDriverCall(Integer bookingId);
	public List<CustomerBookingDetails> getBookingHistory(String email);
	public RescheduleTrip rescheduleTirp(RescheduleTrip dto) ;
	public RescheduleTrip cencelledTirp(RescheduleTrip dto);
	public CustomerBookingDetails getBookingDetailsByEmail(String email, Integer bookingId);
	
	public List<CustomerBookingDetails> getPendingBookings();
	public List<CustomerBookingDetails> getPendingBookings(int pageNumber,int pageSize);
	
	public List<CustomerBookingDetails> getAllCurrentBookingStatus();
	
	public List<CustomerBookingDetails> getAllConfirmedBooking();
	
	public List<CustomerBookingDetails> getAllCancelledBookings();
	
	public List<DriverDeviceVehicleMapping> getAllLogedInVehicles();
	
	public List<DriverDeviceVehicleMapping> getAllLogedInVehicles(String flag);
	
	public int removeDriverDeviceVehicleMapping(String byId);
	
	public List<CustomerBookingDetails> getCustomerBookingReports();
	
	public List<DriverDeviceVehicleMapping> getLogedInVehiclesReportByPagination(int startingPage,int endpage,String flag);
	
	public List<CustomerBookingDetails> getBookingDetaisByFilter(int pageNumber, int pageSize, OrderFilterReportsDto dto);
	public List<CustomerBookingDetails> getBookingDetaisByFilterToGrid(OrderFilterReportsDto dto);
	
	public List<CustomerBookingDetails> getBookingDetaisByFilter(OrderFilterReportsDto dto);
	
	public List<CustomerBookingDetails> getBookingRevenueDetaisByFilters(int pageNumber, int pageSize, OrderFilterReportsDto dto);
	
	public List<Object[]> getBookingRevenueDetaisByFilter(OrderFilterReportsDto dto);
	
	public CustomerBookingDetails updateBookedVehicle(long bookingId, String vehicleType);
	public List<DriverDeviceVehicleMapping> searchLoginLogoutDriver(int startingPage,int endpage, OrderFilterReportsDto dto);
	public List<DriverDeviceVehicleMapping> searchLoginLogoutDriver( OrderFilterReportsDto dto);
	public List<CustomerBookingDetails> searchByMobile(String mobile);
	public List<CustomerBookingDetails> getAllCustomerBookingDetails() ;
	public List<DriverDeviceVehicleMapping> getAllLogedInVehiclesByVehicle(String flags,String vehicleType,String city) ;
	public List<DriverDeviceVehicleMapping> getLogedInVehiclesReportByPaginationByVehicle(int pageNumber,int pageSize,String flags,String vehicleTyle,String city);
	public List<CustomerBookingDetails> getBackEndBookingDetaisByFilterToGrid(	OrderFilterReportsDto dto);
}
