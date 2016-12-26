package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.BookingDAO;
import com.trux.model.CustomerBookingDetails;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.OrderFilterReportsDto;
import com.trux.model.RescheduleTrip;

@Service
public class BookingService {

	@Autowired
	private BookingDAO bookingDAO;
	
	public void savoOrUpdate(Object o){
		bookingDAO.saveOrUpdate(o);
	}
	
	
	public CustomerBookingDetails getBookingDetailsById(Integer bookingId){
		return bookingDAO.getBookingDetailsById(bookingId);
	}
	public CustomerBookingDetails getBookingDetailsByDriverCall(Integer bookingId) {
		return bookingDAO.getBookingDetailsByDriverCall(bookingId);
	}
	public List<CustomerBookingDetails> getBookingHistory(String email){
		return bookingDAO.getBookingHistory(email);
	}
	
	public CustomerBookingDetails getBookingDetailsByEmail(String email, Integer bookingId){
		return bookingDAO.getBookingDetailsByEmail(email, bookingId);
	}
	
	public List<CustomerBookingDetails> getPendingBookings(){
		return bookingDAO.getPendingBookings();
	}
	
	public List<CustomerBookingDetails> getAllCurrentBookingStatus(){
		return bookingDAO.getAllCurrentBookingStatus();
	}
	
	public List<CustomerBookingDetails> getAllConfirmedBooking(){
		return bookingDAO.getAllConfirmedBooking();
	}
	
	public List<CustomerBookingDetails> getAllCancelledBookings(){
		return bookingDAO.getAllCancelledBookings();
	}

	public List<DriverDeviceVehicleMapping> getAllLogedInVehicles(){
		return bookingDAO.getAllLogedInVehicles();
	}
	
	public List<CustomerBookingDetails> getCustomerBookingReports() {
		return bookingDAO.getCustomerBookingReports();
	}
	
	public List<DriverDeviceVehicleMapping> getAllLogedInVehicles(String flag){
		return bookingDAO.getAllLogedInVehicles(flag);
	}
	
	public int removeDriverDeviceVehicleMapping(String byId){
		return bookingDAO.removeDriverDeviceVehicleMapping(byId);
	}
	
	public List<DriverDeviceVehicleMapping> getLogedInVehiclesReportByPagination(int pageNumber,int pageSize,String flags){
		return bookingDAO.getLogedInVehiclesReportByPagination(pageNumber, pageSize, flags);
	}
	public List<CustomerBookingDetails> getPendingBookings(int pageNumber,	int pageSize){
		return bookingDAO.getPendingBookings(pageNumber, pageSize);
	}
	public List<CustomerBookingDetails> getBookingDetaisByFilter(int pageNumber, int pageSize, OrderFilterReportsDto dto) {
		return bookingDAO.getBookingDetaisByFilter(pageNumber, pageSize, dto);
	}
	public List<CustomerBookingDetails> getBookingDetaisByFilterToGrid(OrderFilterReportsDto dto) {
		return bookingDAO.getBookingDetaisByFilterToGrid(dto);
	}
	public List<CustomerBookingDetails> getBackEndBookingDetaisByFilterToGrid(	OrderFilterReportsDto dto){
		return bookingDAO.getBackEndBookingDetaisByFilterToGrid(dto);
	}
	public List<CustomerBookingDetails> getBookingDetaisByFilter(OrderFilterReportsDto dto){
		return bookingDAO.getBookingDetaisByFilter(dto);
	}
	
	public List<CustomerBookingDetails> getBookingRevenueDetaisByFilters(int pageNumber, int pageSize, OrderFilterReportsDto dto) {
	
		return bookingDAO.getBookingRevenueDetaisByFilters(pageNumber,pageSize,dto);
	}
	public List<Object[]> getBookingRevenueDetaisByFilter(OrderFilterReportsDto dto) {
		return bookingDAO.getBookingRevenueDetaisByFilter(dto);
	}
	
	public CustomerBookingDetails updateBookedVehicle(long bookingId,String vehicleType){
		return bookingDAO.updateBookedVehicle(bookingId, vehicleType);
	}
	public List<DriverDeviceVehicleMapping> searchLoginLogoutDriver(int numbnerOfPage,int pageSize ,OrderFilterReportsDto dto) {
		return bookingDAO.searchLoginLogoutDriver(numbnerOfPage, pageSize, dto);
	}
	public List<DriverDeviceVehicleMapping> searchLoginLogoutDriver(OrderFilterReportsDto dto) {
		return bookingDAO.searchLoginLogoutDriver(dto);
	}
	
	public void editCustomerBookingDetails(CustomerBookingDetails dto){
		bookingDAO.editCustomerBookingDetails(dto);
	}
	
	public List<CustomerBookingDetails> getAllCustomerBookingDetails() {
		return bookingDAO.getAllCustomerBookingDetails();
	}
	
	public List<DriverDeviceVehicleMapping> getAllLogedInVehiclesByVehicle(String flags,String vehicleType,String city) {
			return bookingDAO.getAllLogedInVehiclesByVehicle(flags, vehicleType, city);
			}
	
	public List<DriverDeviceVehicleMapping> getLogedInVehiclesReportByPaginationByVehicle(int pageNumber,int pageSize,String flags,String vehicleTyle,String city) {
		return bookingDAO.getLogedInVehiclesReportByPaginationByVehicle(pageNumber, pageSize, flags, vehicleTyle, city);
		}
	
	public RescheduleTrip rescheduleTirp(RescheduleTrip dto) {
		return bookingDAO.rescheduleTirp(dto);
	}
	public RescheduleTrip cencelledTirp(RescheduleTrip dto){
		return bookingDAO.cencelledTirp(dto);
	}
}
