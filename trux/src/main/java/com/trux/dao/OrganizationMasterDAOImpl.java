package com.trux.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.ClientMandateRequest;
import com.trux.model.ClientVehicleDeployment;
import com.trux.model.ControllerDAOTracker;
import com.trux.model.OrganizationMasterRegistration;
import com.trux.model.TransporterRegistration;

public class OrganizationMasterDAOImpl  implements OrganizationMasterDAO{
private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

	
	public OrganizationMasterRegistration registerOrg(OrganizationMasterRegistration dto) {
		Session session=sessionFactory.openSession();
		try{
		Transaction tx= session.beginTransaction();
		session.save(dto);
		tx.commit();
		DetachedCriteria maxId = DetachedCriteria.forClass(OrganizationMasterRegistration.class).setProjection(Projections.max("idClientMaster"));
	    @SuppressWarnings("unchecked")
		List<OrganizationMasterRegistration>  idList=session.createCriteria(OrganizationMasterRegistration.class).add(Property.forName("idClientMaster").eq(maxId)).list();
	      session.flush();
	      session.clear();
	      session.close(); 
	      if(idList!=null && !idList.isEmpty()){ 
			return idList.get(0);
	      }else{
	    	  OrganizationMasterRegistration dtos=  new OrganizationMasterRegistration();
	    	   dtos.setErrorCode(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getCode());
	    	   dtos.setErrorMessage(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getDescription());
	    	  return dtos;
	      }
	      }catch(Exception er){
	    	  er.printStackTrace();
	    	  session.close();
	      }
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public OrganizationMasterRegistration getOrgMasterDetails(OrganizationMasterRegistration dto) {
		Session session=sessionFactory.openSession();
		try{
		List<OrganizationMasterRegistration> omrList = session.createQuery("from OrganizationMasterRegistration d where  d.name='"+dto.getName()+"'  order by name").list();
        session.close();
        if(omrList!=null && !omrList.isEmpty()){
        	return omrList.get(0);
        }
}catch(Exception er){
	er.printStackTrace();
	session.close();
}
		return null; 
	}

	@SuppressWarnings("unchecked")
	
	public List<OrganizationMasterRegistration> getOrganizationMasterRegistration() {
		Session session=sessionFactory.openSession();
		try{
		List<OrganizationMasterRegistration> omrList = session.createQuery("from OrganizationMasterRegistration d order by name").list();
        session.close();
        if(omrList!=null && !omrList.isEmpty()){
        	return omrList;
        }
         }catch(Exception er){
        	 er.printStackTrace();
        	 session.close();
         }
		return null; 
	}

	@SuppressWarnings("unchecked")
	
	public OrganizationMasterRegistration getClientNameById(Integer idClientMaster) {
		Session session=sessionFactory.openSession();
		try{
		List<OrganizationMasterRegistration> omrList = session.createQuery("from OrganizationMasterRegistration d where d.idClientMaster="+idClientMaster+" order by name").list();
        session.close();
        if(omrList!=null && !omrList.isEmpty()){
        	return omrList.get(0);
        }
         }catch(Exception er){
        	 er.printStackTrace();
        	 session.close();
         }
		return null;
	}

	
	public List<?> clientAdhocRequestSearch(Integer clientId, Integer clientSubId, String startDate, String endDate, String orderId, Integer haul) {
		try {
			Session session = this.sessionFactory.openSession();
			List<?> dList = null;
			
			String query="";
			//		SELECT CVD.id, IFNULL(CMR.`source`,'-') cFrom, IFNULL(CMR.`destination`,'-') cTo, IFNULL(CVD.`vehicle_type`,'-') vehicleType, IFNULL(CVD.`body_type`,'-') bodyType, IFNULL(CVD.`vehicle_no`,'-') vehicleNumber, IFNULL(VR.`driver_mobile`,'-') driverMobile, IFNULL(VR.`account_holder_name`,'-') accountHolderName, IFNULL(VR.`bank_name`,'-') bankName, IFNULL(VR.`pan_number`,'-') panNumber, IFNULL(VR.`account_Number`,'-') accountNumber, IFNULL(VR.`ifsc_code`,'-') ifscCode, IFNULL(CONCAT(UD.`firstname`,' ',UD.`lastname`),'-') tiName, IFNULL(UD.`phonenumber`,'-') tiNumber, CVD.`costToDriver`, CVD.`advancePayment`, CVD.`revenueToCompany`, CVD.`paymentReferenceNumber` FROM client_vehicle_deployment AS CVD LEFT JOIN vehicle_registration AS VR ON CVD.`vehicle_no`=VR.`vehicle_number` LEFT JOIN userDetails AS UD ON CVD.`modifiedby`=UD.`id` LEFT JOIN client_mandate_request AS CMR ON CMR.`request_id`=CVD.`client_request_id` LEFT JOIN `client_mandate_detail` AS CMD ON CVD.`mandate_detail_id`=CMD.`mandatedetailid` LEFT JOIN `client_mandate` AS CM ON CMD.`mandatedetailid`=CM.`clientmandateid` WHERE CMR.`client_sub_id`="+clientSubId+" AND DATE(CMR.`created_date`) BETWEEN '"+startDate+"' AND '"+endDate+"' ORDER BY CVD.`createddate` DESC
//					SELECT CVD.id, IFNULL(CMR.`source`,'-') cFrom, IFNULL(CMR.`destination`,'-') cTo, IFNULL(CONCAT(CVD.`vehicle_type`,'-',CVD.`body_type`),'-') vehicleType, IFNULL(CONCAT(CVD.`vehicle_no`,'-',VR.`owner_phone_number`),'-') vehicleDetail, IFNULL(VR.`account_holder_name`,'-') accountHolderName, IFNULL(VR.`bank_name`,'-') bankName, IFNULL(VR.`pan_number`,'-') panNumber, IFNULL(VR.`account_Number`,'-') accountNumber, IFNULL(VR.`ifsc_code`,'-') ifscCode, IFNULL(CONCAT(UD.`firstname`,' ',UD.`lastname`,'-',UD.`phonenumber`),'-') tiDetail, CVD.`costToDriver`, CVD.`advancePayment`, CVD.`revenueToCompany` FROM client_vehicle_deployment AS CVD LEFT JOIN vehicle_registration AS VR ON CVD.`vehicle_no`=VR.`vehicle_number` LEFT JOIN userDetails AS UD ON CVD.`modifiedby`=UD.`id` LEFT JOIN client_mandate_request AS CMR ON CMR.`request_id`=CVD.`client_request_id` LEFT JOIN `client_mandate_detail` AS CMD ON CVD.`mandate_detail_id`=CMD.`mandatedetailid` LEFT JOIN `client_mandate` AS CM ON CMD.`mandatedetailid`=CM.`clientmandateid` WHERE CM.`clientid`="+clientId+" AND CM.`clientsubid`="+clientSubId+" ORDER BY CVD.`createddate` DESC
			
			query += "SELECT ";
			query += "CVD.id, CONCAT(CM2.`name`,'-',CSM2.`subName`) clientName, IFNULL(CMR.`source`,'-') cFrom, IFNULL(CMR.`destination`,'-') cTo, IFNULL(CVD.`vehicle_type`,'-') vehicleType, IFNULL(CVD.`body_type`,'-') bodyType, IFNULL(CVD.`vehicle_no`,'-') vehicleNumber, IFNULL(VR.`driver_mobile`,'-') driverMobile, IFNULL(VR.`account_holder_name`,'-') accountHolderName, IFNULL(VR.`bank_name`,'-') bankName, IFNULL(VR.`pan_number`,'-') panNumber, IFNULL(VR.`account_Number`,'-') accountNumber, IFNULL(VR.`ifsc_code`,'-') ifscCode, IFNULL(CONCAT(UD.`firstname`,' ',UD.`lastname`),'-') tiName, IFNULL(UD.`phonenumber`,'-') tiNumber, CVD.`costToDriver`, CVD.`advancePayment`, CVD.`revenueToCompany`, CVD.`paymentReferenceNumber`, CVD.`remarks`, CVD.`createddate` orderDate, CMR.`box_weight` boxes, CVD.`balancePayment`, CVD.`balanceReferenceNumber`, CVD.`tollPayment`, CVD.`tollReferenceNumber`, CVD.`labourPayment`, CVD.`labourReferenceNumber`, ( SELECT COUNT(id) ";
			query += "FROM ";
			query += "`driver_attendance_leased_vehicles` AS DALV ";
			query += "WHERE ";
			query += "DALV.vehicle_number=CVD.`vehicle_no` AND DATE(DALV.punchIn) BETWEEN DATE(CVD.`createddate`) AND DATE(CVD.`createddate`)+1 ) AS is_arrived, ";
			query += "( SELECT ";
			query += "COUNT(bookingLeaseId) ";
			query += "FROM ";
			query += "`booking_lease` AS BL ";
			query += "LEFT JOIN `vehicle_registration` AS VR ON BL.vehicleId=VR.id ";
			query += "WHERE VR.vehicle_number=CVD.`vehicle_no` AND DATE(BL.journeyStartDate) BETWEEN DATE(CVD.`createddate`) AND DATE(CVD.`createddate`)+1 AND BL.journeyEndDate IS NULL ) AS is_dispatched, ";
			query += "( SELECT ";
			query += "COUNT(bookingLeaseId) ";
			query += "FROM ";
			query += "`booking_lease` AS BL ";
			query += "LEFT JOIN `vehicle_registration` AS VR ON BL.vehicleId=VR.id ";
			query += "WHERE VR.vehicle_number=CVD.`vehicle_no` AND DATE(BL.journeyStartDate) BETWEEN DATE(CVD.`createddate`) AND DATE(CVD.`createddate`)+1 AND BL.journeyEndDate IS NOT NULL ) AS is_delivered ";
			query += "FROM ";
			query += "client_vehicle_deployment AS CVD ";
			query += "LEFT JOIN vehicle_registration AS VR ON CVD.`vehicle_no`=VR.`vehicle_number` ";
			query += "LEFT JOIN userDetails AS UD ON CVD.`modifiedby`=UD.`id` ";
			query += "LEFT JOIN client_mandate_request AS CMR ON CMR.`request_id`=CVD.`client_request_id` ";
			query += "LEFT JOIN `client_mandate_detail` AS CMD ON CVD.`mandate_detail_id`=CMD.`mandatedetailid` ";
			query += "LEFT JOIN `client_mandate` AS CM ON CMD.`mandatedetailid`=CM.`clientmandateid` ";
			query += "LEFT JOIN `client_sub_master` AS CSM2 ON CMR.`client_sub_id`=CSM2.`idClientSubMaster` ";
			query += "LEFT JOIN `client_master` AS CM2 ON CSM2.`idClientMaster`=CM2.`idClientMaster` ";
			
			String append = "";
			if (startDate != null && !startDate.equals("")) {
				if (endDate == null || endDate.equals("")) {
					query += "WHERE DATE(CMR.`created_date`) < '" + startDate + "'";
				} else if (clientSubId == null || clientSubId.equals("")) {
					query += "WHERE DATE(CMR.`created_date`) BETWEEN '" + startDate + "' AND '" + endDate + "'";
				} else {
					query += "WHERE CMR.`client_sub_id`=" + clientSubId + " AND DATE(CMR.`created_date`) BETWEEN '"
							+ startDate + "' AND '" + endDate + "'";
				}
				
				if(haul !=null && !haul.equals("")){
					if(haul == 0 || haul.equals(0)){
						query += " AND CMR.`distance_between_s_d` BETWEEN 0.0 AND 50.0";
					} else if (haul == 50 || haul.equals(50)){
						query += " AND CMR.`distance_between_s_d` BETWEEN 50.0 AND 350.0";
					} else if (haul == 350 || haul.equals(350)){
						query += " AND CMR.`distance_between_s_d` BETWEEN 350.0 AND 800.0";
					} else {
						query += " AND CMR.`distance_between_s_d` >= 800.0";
					}
				}
			}
			
			else if(orderId != null && !orderId.equals("")){
				String temp[] = orderId.split(",");
				
				
				append += "CVD.`id`="+temp[0];
				
				for (int i = 1; i < temp.length; i++) {
					append += " || CVD.`id`="+temp[i];
				}
				
				query += "WHERE " + append;
			}
			
			else if(haul !=null && !haul.equals("")){
				if(haul == 0 || haul.equals(0)){
					query += "WHERE CMR.`distance_between_s_d` BETWEEN 0.0 AND 50.0";
				} else if (haul == 50 || haul.equals(50)){
					query += "WHERE CMR.`distance_between_s_d` BETWEEN 50.0 AND 350.0";
				} else if (haul == 350 || haul.equals(350)){
					query += "WHERE CMR.`distance_between_s_d` BETWEEN 350.0 AND 800.0";
				} else {
					query += "WHERE CMR.`distance_between_s_d` >= 800.0";
				}
			}

			query += " ORDER BY CVD.`createddate` DESC";
			
			
			
			dList = session.createSQLQuery(query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			session.close();
			
			if (dList.size() > 0) {
				return dList;
			} else {
				return null;
			}
		} catch (Exception er) {
			return null;
		}
	}

	
	public ControllerDAOTracker clientAdhocRequestDriverPayment(ClientVehicleDeployment dto) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();

		Session session = this.sessionFactory.openSession();
		List<ClientVehicleDeployment> objList = session
				.createQuery("FROM ClientVehicleDeployment WHERE id=" + dto.getId()).list();
		session.close();

		if (objList != null && objList.size() > 0) {
			try {
				for (ClientVehicleDeployment at : objList) {
					
					if (dto != null && dto.getMandateDetailId() != null) {
						if (!dto.getMandateDetailId().equals(""))
							at.setMandateDetailId(dto.getMandateDetailId());
					}
					if (dto != null && dto.getMandateType() != null) {
						if (!dto.getMandateType().equals(""))
							at.setMandateType(dto.getMandateType());
					}
					if (dto != null && dto.getVehicleType() != null) {
						if (!dto.getVehicleType().equals(""))
							at.setVehicleType(dto.getVehicleType());
					}
					if (dto != null && dto.getBodyType() != null) {
						if (!dto.getBodyType().equals(""))
							at.setBodyType(dto.getBodyType());
					}
					if (dto != null && dto.getVehicleNo() != null) {
						if (!dto.getVehicleNo().equals(""))
							at.setVehicleNo(dto.getVehicleNo());
					}
					if (dto != null && dto.getReportingTime() != null) {
						at.setReportingTime(dto.getReportingTime());
					}
					if (dto != null && dto.getCostToDriver() != null) {
						if (!dto.getCostToDriver().equals(""))
							at.setCostToDriver(dto.getCostToDriver());
					}
					if (dto != null && dto.getAdvancePayment() != null) {
						if (!dto.getAdvancePayment().equals(""))
							at.setAdvancePayment(dto.getAdvancePayment());
					}
					if (dto != null && dto.getRevenueToCompany() != null) {
						if (!dto.getRevenueToCompany().equals(""))
							at.setRevenueToCompany(dto.getRevenueToCompany());
					}
					if (dto != null && dto.getPaymentReferenceNumber() != null) {
						if (!dto.getPaymentReferenceNumber().equals(""))
							at.setPaymentReferenceNumber(dto.getPaymentReferenceNumber());
					}
					if (dto != null && dto.getRemarks() != null) {
						if (!dto.getRemarks().equals(""))
							at.setRemarks(dto.getRemarks());
					}
					if (dto != null && dto.getBalancePayment() != null) {
						if (!dto.getBalancePayment().equals(""))
							at.setBalancePayment(dto.getBalancePayment());
					}
					if (dto != null && dto.getBalanceReferenceNumber() != null) {
						if (!dto.getBalanceReferenceNumber().equals(""))
							at.setBalanceReferenceNumber(dto.getBalanceReferenceNumber());
					}
					if (dto != null && dto.getTollPayment() != null) {
						if (!dto.getTollPayment().equals(""))
							at.setTollPayment(dto.getTollPayment());
					}
					if (dto != null && dto.getTollReferenceNumber() != null) {
						if (!dto.getTollReferenceNumber().equals(""))
							at.setTollReferenceNumber(dto.getTollReferenceNumber());
					}
					if (dto != null && dto.getLabourPayment() != null) {
						if (!dto.getLabourPayment().equals(""))
							at.setLabourPayment(dto.getLabourPayment());
					}
					if (dto != null && dto.getLabourReferenceNumber() != null) {
						if (!dto.getLabourReferenceNumber().equals(""))
							at.setLabourReferenceNumber(dto.getLabourReferenceNumber());
					}
					at.setId(dto.getId());

					Session session1 = this.sessionFactory.openSession();
					session1.get(ClientVehicleDeployment.class, at.getId());
					Transaction txs = session1.beginTransaction();
					ClientVehicleDeployment dts = (ClientVehicleDeployment) session1.merge(at);
					txs.commit();
					session1.close();

					cdt.setSuccess(true);
					cdt.setErrorCode("200");
					cdt.setErrorMessage("Record saved successfully.");
					cdt.setClientVehicleDeployment(dts);
					return cdt;

				}

			} catch (Exception er) {
				ControllerDAOTracker cdte = new ControllerDAOTracker();
				cdte.setSuccess(false);
				cdte.setErrorCode("101");
				cdte.setErrorMessage("Something went wrong.");

				return cdte;
			}

		}
		ControllerDAOTracker cdte = new ControllerDAOTracker();
		cdte.setErrorCode("101");
		cdte.setErrorMessage("Your request does not processed !");
		return cdte;

	}

	
	public ControllerDAOTracker updateClientMandateRequestBoxes(ClientMandateRequest dto) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();

		Session session = this.sessionFactory.openSession();
		List<ClientMandateRequest> objList = session
				.createQuery("FROM ClientMandateRequest WHERE request_id=" + dto.getRequest_id()).list();
		session.close();

		if (objList != null && objList.size() > 0) {
			try {
				for (ClientMandateRequest at : objList) {
					
					if (dto != null && dto.getBoxWeight() != null) {
						if (!dto.getBoxWeight().equals(""))
							at.setBoxWeight(dto.getBoxWeight());
					}
					if (dto != null && dto.getModifiedBy() != null) {
						if (!dto.getModifiedBy().equals(""))
							at.setModifiedBy(dto.getModifiedBy());
					}
					if (dto != null && dto.getModifiedDate() != null) {
						at.setModifiedDate(dto.getModifiedDate());
					}
					at.setRequest_id(dto.getRequest_id());

					Session session1 = this.sessionFactory.openSession();
					session1.get(ClientMandateRequest.class, at.getRequest_id());
					Transaction txs = session1.beginTransaction();
					ClientMandateRequest dts = (ClientMandateRequest) session1.merge(at);
					txs.commit();
					session1.close();

					cdt.setSuccess(true);
					cdt.setErrorCode("200");
					cdt.setErrorMessage("Record saved successfully.");
					cdt.setData(dts);
					return cdt;

				}

			} catch (Exception er) {
				ControllerDAOTracker cdte = new ControllerDAOTracker();
				cdte.setSuccess(false);
				cdte.setErrorCode("101");
				cdte.setErrorMessage("Something went wrong.");

				return cdte;
			}

		}
		ControllerDAOTracker cdte = new ControllerDAOTracker();
		cdte.setErrorCode("101");
		cdte.setErrorMessage("Your request does not processed !");
		return cdte;
	}
}
