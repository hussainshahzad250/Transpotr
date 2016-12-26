package com.trux.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.TransporterRegistrationDAO;
import com.trux.model.Cities;
import com.trux.model.CommunicationSMS;
import com.trux.model.ControllerDAOTracker;
import com.trux.model.ExcelFile;
import com.trux.model.States;
import com.trux.model.TransporterClientOrderMapping;
import com.trux.model.TransporterClientOrders;
import com.trux.model.TransporterFreightChart;
import com.trux.model.TransporterLoginHistory;
import com.trux.model.TransporterOrderFollowUp;
import com.trux.model.TransporterRegistration;
import com.trux.model.TransporterVehicleRegistration;
import com.trux.model.VehicleType;

@Service
public class TransporterRegistrationService {
	@Autowired
	private TransporterRegistrationDAO transporterRegistrationDAO;
	
	

	public ControllerDAOTracker saveTransporterRegistration(TransporterRegistration tr) {

		return transporterRegistrationDAO.saveTransporterRegistration(tr);
	}

	public ControllerDAOTracker getTransporterByMobile(String mobileNumber) {
		return transporterRegistrationDAO.getTransporterByMobile(mobileNumber);
	}
	
	public ControllerDAOTracker getTransporterDetails(String mobileNumber) {
		return transporterRegistrationDAO.getTransporterDetails(mobileNumber);
	}

	public ControllerDAOTracker updateTransporterRegistration(TransporterRegistration tr) {
		return transporterRegistrationDAO.updateTransporterRegistration(tr);
	}

	public ControllerDAOTracker getUserDetailsWithGcmId(TransporterRegistration tr) {
		return transporterRegistrationDAO.getUserDetailsWithGcmId(tr);
	}

	public ControllerDAOTracker saveTransporterLoginHistory(TransporterLoginHistory tlh) {
		return transporterRegistrationDAO.saveTransporterLoginHistory(tlh);
	}

	public ControllerDAOTracker getVehicleNumber(String vehicleNumber) {
		return transporterRegistrationDAO.getVehicleNumber(vehicleNumber);
	}

	public ControllerDAOTracker saveTransporterVehicle(TransporterVehicleRegistration tvr) {
		return transporterRegistrationDAO.saveTransporterVehicle(tvr);
	}

	public ControllerDAOTracker getTransporterVehicle(Integer trsptrRegistrationId) {
		return transporterRegistrationDAO.getTransporterVehicle(trsptrRegistrationId);
	}

	public ControllerDAOTracker updateTransporterVehicle(TransporterVehicleRegistration tvr) {
		return transporterRegistrationDAO.updateTransporterVehicle(tvr);
	}

	public ControllerDAOTracker getClientOrders(Integer cityId, String vehicleCategory) {
		return transporterRegistrationDAO.getClientOrders(cityId,vehicleCategory);
	}

	public ControllerDAOTracker clientOrderConfirmation(TransporterClientOrderMapping tcom) {
		return transporterRegistrationDAO.clientOrderConfirmation(tcom);
	}

	public ControllerDAOTracker orderFollowUp(TransporterOrderFollowUp tofu) {
		return transporterRegistrationDAO.orderFollowUp(tofu);
	}

	public ControllerDAOTracker getFollowUpOrders(Integer trsptrRegistrationId) {
		return transporterRegistrationDAO.getFollowUpOrders(trsptrRegistrationId);
	}

	public ControllerDAOTracker transporterClientIsActiveUpdate(TransporterClientOrders tco) {
		return transporterRegistrationDAO.transporterClientIsActiveUpdate(tco);
	}

	public ControllerDAOTracker getClientOrdersHistory(Integer trsptrRegistrationId, String status,
			String vehicleCategory) {
		return transporterRegistrationDAO.getClientOrdersHistory(trsptrRegistrationId, status, vehicleCategory);
	}

	public ControllerDAOTracker changeTransporterStatus(TransporterClientOrderMapping tcom) {
		return transporterRegistrationDAO.changeTransporterStatus(tcom);
	}

	public ControllerDAOTracker transporterFollowUpActiveUpdate(TransporterOrderFollowUp tofu) {
		return transporterRegistrationDAO.changeTransporterStatus(tofu);
	}

	public ControllerDAOTracker getTransporterFreightChart(TransporterFreightChart tfc) {
		return transporterRegistrationDAO.getTransporterFreightChart(tfc);
	}

	public ControllerDAOTracker saveTransporterFreightRate(TransporterFreightChart tr) {
		return transporterRegistrationDAO.saveTransporterFreightRate(tr);
	}

	public ControllerDAOTracker getOrderCityList() {
		return transporterRegistrationDAO.getOrderCityList();
	}

	public ControllerDAOTracker createClientOrder(TransporterClientOrders tco) {
		return transporterRegistrationDAO.createClientOrder(tco);
	}

	public ControllerDAOTracker searchOrder(Integer fromCityId, Integer toCityId, String status) {
		return transporterRegistrationDAO.searchOrder(fromCityId, toCityId, status);
	}

	public void saveSMSRecord(CommunicationSMS transporterSMS) {
		transporterRegistrationDAO.saveSMSRecord(transporterSMS);
	}

	public ControllerDAOTracker approveCRFOrder(TransporterClientOrders tco) {
		return transporterRegistrationDAO.approveCRFOrder(tco);
	}

	public ControllerDAOTracker trsptrVehicleStatusChange(Integer trsptrRegistrationId, String vehicleNumber) {
		return transporterRegistrationDAO.trsptrVehicleStatusChange(trsptrRegistrationId,vehicleNumber);
	}

	public String getServerApi(String string) {
		List<?> dList = transporterRegistrationDAO.getServerApi(string);

		if (dList != null)
			return (String) dList.get(0);
		else
			return "";
	}

	public List<String> getGCM() {
		return transporterRegistrationDAO.getGCM();
	}

	public List<TransporterClientOrders> getAllOrder() {
		return transporterRegistrationDAO.getAllOrder();
	}

	public ControllerDAOTracker getOrder(Integer fromCityId, Integer toCityId) {
			return transporterRegistrationDAO.getOrder(fromCityId, toCityId);
	}

	

	public ControllerDAOTracker getByOrderId(Integer id, Integer price, Date deployDateTime) {
		
		return transporterRegistrationDAO.getByOrderId(id,price,deployDateTime);
	}

	public ControllerDAOTracker updateClientOrder(TransporterClientOrders tco) {
		return transporterRegistrationDAO.updateClientOrder(tco);
	}

	public ControllerDAOTracker saveFile(ExcelFile excelFile) {
		
			
			return transporterRegistrationDAO.saveFile(excelFile);
	}

	public List<States> getStateName(States states) {
		
		
		return transporterRegistrationDAO.getStateName(states);
	}

	public List<Cities> getCityName(Cities cities) {
		
		return transporterRegistrationDAO.getCityName(cities);	
	}

	public List<VehicleType> getVehicleType(VehicleType vehicleType) {
		
		return transporterRegistrationDAO.getVehicleType(vehicleType);
	}

	public ControllerDAOTracker getRecoredBy(Integer cityId, Integer cityId2, Integer id) {
		return transporterRegistrationDAO.getRecoredBy(cityId,cityId2,id);
	}

	public ControllerDAOTracker updateFile(ExcelFile excelFile) {
		return transporterRegistrationDAO.updateFile(excelFile);	
	}

	public List<?> saveToExcell(Integer fromCity, Integer toCity) {
		
		return transporterRegistrationDAO.saveToExcell(fromCity,toCity);
	}

	public List<ExcelFile> getExcel() {
		return transporterRegistrationDAO.getExcel();
	}

	public List<?> savToExcel(Integer source_city_id, Integer destination_city_id, List<VehicleType> allVehicle) {
		// TODO Auto-generated method stub
		return transporterRegistrationDAO.savToExcel(source_city_id,destination_city_id,allVehicle);
	}

	public List<VehicleType> getVehicle(String string) {
		return transporterRegistrationDAO.getVehicle(string);
	}


	public List<VehicleType> getVehicle(Integer vehicle) {
		// TODO Auto-generated method stub
		return transporterRegistrationDAO.getVehicle(vehicle);
	}
	
	
	
	
	
	
}
