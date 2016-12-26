package com.trux.dao;

import java.util.Date;
import java.util.List;

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

public interface TransporterRegistrationDAO {

	ControllerDAOTracker saveTransporterRegistration(TransporterRegistration dto);

	ControllerDAOTracker getTransporterByMobile(String mobileNumber);

	ControllerDAOTracker getTransporterDetails(String mobileNumber);

	ControllerDAOTracker updateTransporterRegistration(TransporterRegistration tr);

	ControllerDAOTracker getUserDetailsWithGcmId(TransporterRegistration tr);

	ControllerDAOTracker saveTransporterLoginHistory(TransporterLoginHistory tlh);

	ControllerDAOTracker getVehicleNumber(String vehicleNumber);

	ControllerDAOTracker saveTransporterVehicle(TransporterVehicleRegistration tvr);

	ControllerDAOTracker getTransporterVehicle(Integer trsptrRegistrationId);

	ControllerDAOTracker updateTransporterVehicle(TransporterVehicleRegistration tvr);

	ControllerDAOTracker getClientOrders(Integer cityId, String vehicleCategory);

	ControllerDAOTracker clientOrderConfirmation(TransporterClientOrderMapping tcom);

	ControllerDAOTracker orderFollowUp(TransporterOrderFollowUp tofu);

	ControllerDAOTracker getFollowUpOrders(Integer trsptrRegistrationId);

	ControllerDAOTracker transporterClientIsActiveUpdate(TransporterClientOrders tco);

	ControllerDAOTracker getClientOrdersHistory(Integer trsptrRegistrationId, String status, String vehicleCategory);

	ControllerDAOTracker changeTransporterStatus(TransporterClientOrderMapping tcom);

	ControllerDAOTracker changeTransporterStatus(TransporterOrderFollowUp tofu);

	ControllerDAOTracker getTransporterFreightChart(TransporterFreightChart tfc);

	ControllerDAOTracker saveTransporterFreightRate(TransporterFreightChart tr);

	ControllerDAOTracker getOrderCityList();

	ControllerDAOTracker createClientOrder(TransporterClientOrders tco);

	ControllerDAOTracker searchOrder(Integer fromCityId, Integer toCityId, String status);

	void saveSMSRecord(CommunicationSMS transporterSMS);

	ControllerDAOTracker approveCRFOrder(TransporterClientOrders tco);

	ControllerDAOTracker trsptrVehicleStatusChange(Integer trsptrRegistrationId, String vehicleNumber);

	List<?> getServerApi(String string);

	List<String> getGCM();

	List<TransporterClientOrders> getAllOrder();

	/*ControllerDAOTracker getOrder(Integer fromCityId, Integer toCityId, String status);*/

	ControllerDAOTracker getOrder(Integer fromCityId, Integer toCityId);


	ControllerDAOTracker getByOrderId(Integer id, Integer price, Date deployDateTime);

	ControllerDAOTracker updateClientOrder(TransporterClientOrders tco);

	ControllerDAOTracker saveFile(ExcelFile excelFile);

	List<States> getStateName(States states);

	List<Cities> getCityName(Cities cities);

	List<VehicleType> getVehicleType(VehicleType vehicleType);

	ControllerDAOTracker getRecoredBy(Integer cityId, Integer cityId2, Integer id);

	ControllerDAOTracker updateFile(ExcelFile excelFile);

	List<?> saveToExcell(Integer source_state, Integer source_city);

	List<ExcelFile> getExcel();

	List<?> savToExcel(Integer source_city_id, Integer destination_city_id, List<VehicleType> v1);

	List<VehicleType> getVehicle(String string);

	List<VehicleType> getVehicle(Integer vehicle);

	
	

	
	

}
