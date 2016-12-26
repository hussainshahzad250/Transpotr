package com.trux.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.internal.SessionImpl;

import com.trux.model.Cities;
import com.trux.model.CommunicationSMS;
import com.trux.model.ControllerDAOTracker;
import com.trux.model.ExcelFile;
import com.trux.model.OrganizationMasterRegistration;
import com.trux.model.States;
import com.trux.model.TransporterClientOrderMapping;
import com.trux.model.TransporterClientOrders;
import com.trux.model.TransporterFreightChart;
import com.trux.model.TransporterLoginHistory;
import com.trux.model.TransporterOrderFollowUp;
import com.trux.model.TransporterRegistration;
import com.trux.model.TransporterVehicleRegistration;
import com.trux.model.VehicleType;

public class TransporterRegistrationDAOImpl implements TransporterRegistrationDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ControllerDAOTracker saveTransporterRegistration(TransporterRegistration dto) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		Session session = this.sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<TransporterRegistration> objList = session.createQuery(
				"FROM TransporterRegistration WHERE mobileNumber='" + dto.getMobileNumber() + "' AND id=" + dto.getId())
				.list();
		// "FROM TransporterRegistration WHERE mobileNumber ='" + mobileNumber +
		// "'"
		session.close();
		if (objList != null && objList.size() > 0) {
			try {
				for (TransporterRegistration at : objList) {
					if (dto != null && dto.getTransporterCompanyName() != null) {
						if (!dto.getTransporterCompanyName().equals(""))
							at.setTransporterCompanyName(dto.getTransporterCompanyName());
					}
					if (dto != null && dto.getContactPersonName() != null) {
						if (!dto.getContactPersonName().equals(""))
							at.setContactPersonName(dto.getContactPersonName());
					}
					if (dto != null && dto.getMobileNumber() != null) {
						if (!dto.getMobileNumber().equals(""))
							at.setMobileNumber(dto.getMobileNumber());
					}
					if (dto != null && dto.getPassword() != null) {
						at.setPassword(dto.getPassword());
					}
					if (dto != null && dto.getEmail() != null) {
						if (!dto.getEmail().equals(""))
							at.setEmail(dto.getEmail());
					}
					if (dto != null && dto.getCity() != null) {
						at.setCity(dto.getCity());
					}
					if (dto != null && dto.getState() != null) {
						at.setState(dto.getState());
					}
					if (dto != null && dto.getPincode() != null) {
						at.setPincode(dto.getPincode());
					}
					if (dto != null && dto.getVehicleCategory() != null) {
						if (!dto.getVehicleCategory().equals(""))
							at.setVehicleCategory(dto.getVehicleCategory());
					}
					if (dto != null && dto.getCreatedBy() != null) {
						at.setCreatedBy(dto.getCreatedBy());
					}
					if (dto != null && dto.getCreatedOn() != null) {
						at.setCreatedOn(dto.getCreatedOn());
					}
					if (dto != null && dto.getModifiedBy() != null) {
						at.setModifiedBy(dto.getModifiedBy());
					}
					if (dto != null && dto.getModifiedOn() != null) {
						at.setModifiedOn(dto.getModifiedOn());
					}
					if (dto != null && dto.getIsActive() != null) {
						at.setIsActive(dto.getIsActive());
					}
					at.setModifiedOn(new Date());
					Session session1 = this.sessionFactory.openSession();
					session1.get(TransporterRegistration.class, at.getId());
					Transaction txs = session1.beginTransaction();
					TransporterRegistration dts = (TransporterRegistration) session1.merge(at);
					txs.commit();
					session1.close();

					cdt.setSuccess(true);
					cdt.setErrorCode("200");
					cdt.setErrorMessage("Transporter updated successfully");
					cdt.setTransporterRegistration(dts);
					return cdt;

				}
			} catch (Exception er) {
				er.printStackTrace();
			}
		} else {

			Session sessions = sessionFactory.openSession();
			try {
				Transaction tx = sessions.beginTransaction();
				sessions.saveOrUpdate(dto);
				tx.commit();
				DetachedCriteria maxId = DetachedCriteria.forClass(TransporterRegistration.class)
						.setProjection(Projections.max("id"));
				List<TransporterRegistration> cmdList = sessions.createCriteria(TransporterRegistration.class)
						.add(Property.forName("id").eq(maxId)).list();
				sessions.clear();
				sessions.close();
				if (cmdList != null && cmdList.size() > 0) {
					TransporterRegistration dts = cmdList.get(0);
					cdt.setSuccess(true);
					cdt.setErrorCode("100");
					cdt.setErrorMessage("Transporter saved successfully");
					cdt.setTransporterRegistration(dts);
					return cdt;
				} else {
					TransporterRegistration dts = cmdList.get(0);
					cdt.setSuccess(false);
					cdt.setErrorCode("101");
					cdt.setErrorMessage("Request does not processed !");
					return cdt;
				}
			} catch (Exception er) {

				ControllerDAOTracker cdte = new ControllerDAOTracker();
				cdte.setSuccess(false);
				cdte.setErrorCode("101");
				cdte.setErrorMessage(er.toString());

				return cdte;
			}
		}
		ControllerDAOTracker cdte = new ControllerDAOTracker();
		cdte.setErrorCode("101");
		cdte.setErrorMessage("Your request does not processed !");
		return cdte;
	}

	public ControllerDAOTracker getTransporterByMobile(String mobileNumber) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {
			Session session = this.sessionFactory.openSession();
			List<TransporterRegistration> dList = session
					.createQuery("FROM TransporterRegistration WHERE mobileNumber='" + mobileNumber + "'").list();

			if (dList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request successful.");
				cdt.setTransporterRegistration(dList.get(0));

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("Transporter not found/registered.");

				return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}

	}

	public ControllerDAOTracker getTransporterDetails(String mobileNumber) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {
			Session session = this.sessionFactory.openSession();
			List<TransporterRegistration> dList = session
					.createQuery("FROM TransporterRegistration WHERE mobileNumber='" + mobileNumber + "'").list();

			////////////////////// Fetching city, state and country
			////////////////////// ////////////////

			Session session2 = sessionFactory.openSession();
			List<?> as = null;

			String query = "SELECT c.`city_id`, c.`city`, s.`state_id`, s.`state_name`, cn.`id`, cn.`value` FROM countries AS cn LEFT JOIN `states` AS s ON cn.`id`=s.`country_id` LEFT JOIN `cities` AS c ON s.`state_id`=c.`state_id` WHERE s.`state_id`="
					+ dList.get(0).getState() + " AND c.`city_id`=" + dList.get(0).getCity();
			as = session2.createSQLQuery(query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			session2.close();

			// if(as != null && as.size() >0){
			// cdt.setCsc(as.get(0));
			// }

			////////////////////// Fetching city, state and country
			////////////////////// ////////////////

			if (dList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request successful.");
				if (as != null && as.size() > 0)
					dList.get(0).setCsc(as.get(0));
				cdt.setData(dList.get(0));

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("Transporter not found/registered.");
				return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker updateTransporterRegistration(TransporterRegistration dto) {

		ControllerDAOTracker cdt = new ControllerDAOTracker();
		Session session = this.sessionFactory.openSession();
		List<TransporterRegistration> objList = session.createQuery(
				"FROM TransporterRegistration WHERE mobileNumber='" + dto.getMobileNumber() + "' AND id=" + dto.getId())
				.list();
		// "FROM TransporterRegistration WHERE mobileNumber ='" + mobileNumber +
		// "'"
		session.close();
		if (objList != null && objList.size() > 0) {
			try {
				for (TransporterRegistration at : objList) {
					if (dto != null && dto.getTransporterCompanyName() != null) {
						if (!dto.getTransporterCompanyName().equals(""))
							at.setTransporterCompanyName(dto.getTransporterCompanyName());
					}
					if (dto != null && dto.getContactPersonName() != null) {
						if (!dto.getContactPersonName().equals(""))
							at.setContactPersonName(dto.getContactPersonName());
					}
					if (dto != null && dto.getMobileNumber() != null) {
						if (!dto.getMobileNumber().equals(""))
							at.setMobileNumber(dto.getMobileNumber());
					}
					if (dto != null && dto.getPassword() != null) {
						at.setPassword(dto.getPassword());
					}
					if (dto != null && dto.getEmail() != null) {
						if (!dto.getEmail().equals(""))
							at.setEmail(dto.getEmail());
					}
					if (dto != null && dto.getCity() != null) {
						at.setCity(dto.getCity());
					}
					if (dto != null && dto.getState() != null) {
						at.setState(dto.getState());
					}
					if (dto != null && dto.getPincode() != null) {
						at.setPincode(dto.getPincode());
					}
					if (dto != null && dto.getVehicleCategory() != null) {
						if (!dto.getVehicleCategory().equals(""))
							at.setVehicleCategory(dto.getVehicleCategory());
					}
					if (dto != null && dto.getCreatedBy() != null) {
						at.setCreatedBy(dto.getCreatedBy());
					}
					if (dto != null && dto.getCreatedOn() != null) {
						at.setCreatedOn(dto.getCreatedOn());
					}
					if (dto != null && dto.getModifiedBy() != null) {
						at.setModifiedBy(dto.getModifiedBy());
					}
					if (dto != null && dto.getModifiedOn() != null) {
						at.setModifiedOn(dto.getModifiedOn());
					}
					if (dto != null && dto.getIsActive() != null) {
						at.setIsActive(dto.getIsActive());
					}
					at.setModifiedOn(new Date());
					Session session1 = this.sessionFactory.openSession();
					session1.get(TransporterRegistration.class, at.getId());
					Transaction txs = session1.beginTransaction();
					TransporterRegistration dts = (TransporterRegistration) session1.merge(at);
					txs.commit();
					session1.close();

					cdt.setSuccess(true);
					cdt.setErrorCode("200");
					cdt.setErrorMessage("Transporter updated successfully");
					cdt.setTransporterRegistration(dts);
					return cdt;

				}
			} catch (Exception er) {
				ControllerDAOTracker cdte = new ControllerDAOTracker();
				cdte.setSuccess(false);
				cdte.setErrorCode("101");
				cdte.setErrorMessage(er.toString());

				return cdte;
			}
		}
		ControllerDAOTracker cdte = new ControllerDAOTracker();
		cdte.setErrorCode("101");
		cdte.setErrorMessage("Your request does not processed !");
		return cdte;

	}

	public ControllerDAOTracker getUserDetailsWithGcmId(TransporterRegistration tr) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {
			Session session = this.sessionFactory.openSession();
			List<TransporterRegistration> dList = session
					.createQuery("FROM TransporterRegistration WHERE mobileNumber='" + tr.getMobileNumber()
							+ "' AND password=" + tr.getPassword() + " AND gcmId='" + tr.getGcmId() + "'")
					.list();

			if (dList.size() > 0) {

				////////////////////// Fetching city, state and country
				////////////////////// ////////////////

				Session session2 = sessionFactory.openSession();
				List<?> as = null;

				String query = "SELECT c.`city_id` cityId, c.`city` cityName, s.`state_id` stateId, s.`state_name` stateName, cn.`id` countryId, cn.`value` countryName FROM countries AS cn LEFT JOIN `states` AS s ON cn.`id`=s.`country_id` LEFT JOIN `cities` AS c ON s.`state_id`=c.`state_id` WHERE s.`state_id`="
						+ dList.get(0).getState() + " AND c.`city_id`=" + dList.get(0).getCity();
				as = session2.createSQLQuery(query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
				session2.close();

				// if(as != null && as.size() >0){
				// cdt.setCsc(as.get(0));
				// }

				////////////////////// Fetching city, state and country
				////////////////////// ////////////////

				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request successful.");
				if (as != null && as.size() > 0)
					dList.get(0).setCsc(as.get(0));
				cdt.setTransporterRegistration(dList.get(0));
				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("Invalid credentials.");
				return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker saveTransporterLoginHistory(TransporterLoginHistory tlh) {
		try {
			ControllerDAOTracker cdt = new ControllerDAOTracker();

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(tlh);
			tx.commit();
			DetachedCriteria maxId = DetachedCriteria.forClass(TransporterLoginHistory.class)
					.setProjection(Projections.max("id"));
			@SuppressWarnings("unchecked")
			List<TransporterLoginHistory> clusterList = session.createCriteria(TransporterLoginHistory.class)
					.add(Property.forName("id").eq(maxId)).list();
			session.close();
			if (clusterList != null && clusterList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Record saved successfully.");
				cdt.setData(clusterList.get(0));

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("Record not saved.");

				return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker getVehicleNumber(String vehicleNumber) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {
			Session session = this.sessionFactory.openSession();
			List<TransporterVehicleRegistration> dList = session
					.createQuery("FROM TransporterVehicleRegistration WHERE vehicleNumber='" + vehicleNumber + "'")
					.list();

			if (dList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request successful.");
				cdt.setTransporterVehicleRegistration(dList.get(0));

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("Vehicle not found/registered.");

				return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker saveTransporterVehicle(TransporterVehicleRegistration tvr) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();

		Session sessions = sessionFactory.openSession();
		try {
			Transaction tx = sessions.beginTransaction();
			sessions.saveOrUpdate(tvr);
			tx.commit();
			DetachedCriteria maxId = DetachedCriteria.forClass(TransporterVehicleRegistration.class)
					.setProjection(Projections.max("id"));
			List<TransporterVehicleRegistration> cmdList = sessions.createCriteria(TransporterVehicleRegistration.class)
					.add(Property.forName("id").eq(maxId)).list();
			sessions.clear();
			sessions.close();
			if (cmdList != null && cmdList.size() > 0) {
				TransporterVehicleRegistration dts = cmdList.get(0);
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Transporter saved successfully");
				cdt.setTransporterVehicleRegistration(dts);
				return cdt;
			} else {
				TransporterVehicleRegistration dts = cmdList.get(0);
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("Request does not processed !");
				cdt.setData(dts);
				return cdt;
			}
		} catch (Exception er) {

			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker getTransporterVehicle(Integer trsptrRegistrationId) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {
			Session session = this.sessionFactory.openSession();
			List<?> dList = null;

			String query = "SELECT tv.`id` id, vt.`vehicleName` vehicleName, tv.`vehicle_number` vehicleNumber, tv.`vehicle_type_id` vehicleTypeId, tv.`vehicle_body` vehicleBody, tv.`model_year` modelYear, tv.`insurance_expiry` insuranceExpiry, tv.`status` FROM trsptr_vehicle AS tv LEFT JOIN vehicle_type AS vt ON tv.`vehicle_type_id`=vt.`id` WHERE tv.`trsptr_registration_id`="
					+ trsptrRegistrationId;
			dList = session.createSQLQuery(query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			session.close();

			if (dList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request successful.");
				cdt.setData(dList);

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("No vehicles registered for the corresponding transporter.");

				return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker updateTransporterVehicle(TransporterVehicleRegistration dto) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		Session session = this.sessionFactory.openSession();
		List<TransporterVehicleRegistration> objList = session
				.createQuery("FROM TransporterVehicleRegistration WHERE vehicleNumber='" + dto.getVehicleNumber()
						+ "' AND id=" + dto.getId())
				.list();
		session.close();
		if (objList != null && objList.size() > 0) {
			try {
				for (TransporterVehicleRegistration at : objList) {
					if (dto != null && dto.getVehicleTypeId() != null) {
						at.setVehicleTypeId(dto.getVehicleTypeId());
					}
					if (dto != null && dto.getVehicleBody() != null) {
						if (!dto.getVehicleBody().equals(""))
							at.setVehicleBody(dto.getVehicleBody());
					}
					if (dto != null && dto.getModelYear() != null) {
						if (!dto.getModelYear().equals(""))
							at.setModelYear(dto.getModelYear());
					}
					if (dto != null && dto.getInsuranceExpiry() != null) {
						at.setInsuranceExpiry(dto.getInsuranceExpiry());
					}
					if (dto != null && dto.getStatus() != null) {
						if (!dto.getStatus().equals(""))
							at.setStatus(dto.getStatus());
					}
					if (dto != null && dto.getCreatedBy() != null) {
						at.setCreatedBy(dto.getCreatedBy());
					}
					if (dto != null && dto.getCreatedOn() != null) {
						at.setCreatedOn(dto.getCreatedOn());
					}
					if (dto != null && dto.getModifiedBy() != null) {
						at.setModifiedBy(dto.getModifiedBy());
					}
					if (dto != null && dto.getModifiedOn() != null) {
						at.setModifiedOn(dto.getModifiedOn());
					}
					Session session1 = this.sessionFactory.openSession();
					session1.get(TransporterVehicleRegistration.class, at.getId());
					Transaction txs = session1.beginTransaction();
					TransporterVehicleRegistration dts = (TransporterVehicleRegistration) session1.merge(at);
					txs.commit();
					session1.close();

					cdt.setSuccess(true);
					cdt.setErrorCode("200");
					cdt.setErrorMessage("Vehicle updated successfully");
					cdt.setTransporterVehicleRegistration(dts);
					return cdt;

				}
			} catch (Exception er) {
				ControllerDAOTracker cdte = new ControllerDAOTracker();
				cdte.setSuccess(false);
				cdte.setErrorCode("101");
				cdte.setErrorMessage(er.toString());

				return cdte;
			}
		}
		ControllerDAOTracker cdte = new ControllerDAOTracker();
		cdte.setSuccess(false);
		cdte.setErrorCode("101");
		cdte.setErrorMessage("Your request does not processed !");
		return cdte;
	}

	public ControllerDAOTracker getClientOrders(Integer city, String vehicleCategory) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {
			Session session = this.sessionFactory.openSession();
			List<?> dList = null;

			String query;

			if (city == 0) {

				if (vehicleCategory.equals("MCV")) {
					query = "SELECT tco.`id` id, (SELECT GROUP_CONCAT(DISTINCT(`trsptr_registration_id`)) flagTransporterId FROM `trsptr_order_follow_up` WHERE `trsptr_client_orders_id`=tco.`id` GROUP BY `trsptr_client_orders_id`) AS flagTransporterId, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE vt.`capacity_category` IN ('SCV', 'MCV') AND tco.`is_active`=1";
				} else if (vehicleCategory.equals("LCV")) {
					query = "SELECT tco.`id` id, (SELECT GROUP_CONCAT(DISTINCT(`trsptr_registration_id`)) flagTransporterId FROM `trsptr_order_follow_up` WHERE `trsptr_client_orders_id`=tco.`id` GROUP BY `trsptr_client_orders_id`) AS flagTransporterId, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE vt.`capacity_category` IN ('LCV', 'HCV') AND tco.`is_active`=1";
				} else {
					query = "SELECT tco.`id` id, (SELECT GROUP_CONCAT(DISTINCT(`trsptr_registration_id`)) flagTransporterId FROM `trsptr_order_follow_up` WHERE `trsptr_client_orders_id`=tco.`id` GROUP BY `trsptr_client_orders_id`) AS flagTransporterId, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE vt.`capacity_category` IN ('SCV', 'MCV', 'LCV', 'HCV') AND tco.`is_active`=1";
				}

			} else {

				if (vehicleCategory.equals("MCV")) {
					query = "SELECT tco.`id` id, (SELECT GROUP_CONCAT(DISTINCT(`trsptr_registration_id`)) flagTransporterId FROM `trsptr_order_follow_up` WHERE `trsptr_client_orders_id`=tco.`id` GROUP BY `trsptr_client_orders_id`) AS flagTransporterId, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE tco.`from_city_id`="
							+ city + " AND vt.`capacity_category` IN ('SCV', 'MCV') AND tco.`is_active`=1";
				} else if (vehicleCategory.equals("LCV")) {
					query = "SELECT tco.`id` id, (SELECT GROUP_CONCAT(DISTINCT(`trsptr_registration_id`)) flagTransporterId FROM `trsptr_order_follow_up` WHERE `trsptr_client_orders_id`=tco.`id` GROUP BY `trsptr_client_orders_id`) AS flagTransporterId, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE tco.`from_city_id`="
							+ city + " AND vt.`capacity_category` IN ('LCV', 'HCV') AND tco.`is_active`=1";
				} else {
					query = "SELECT tco.`id` id, (SELECT GROUP_CONCAT(DISTINCT(`trsptr_registration_id`)) flagTransporterId FROM `trsptr_order_follow_up` WHERE `trsptr_client_orders_id`=tco.`id` GROUP BY `trsptr_client_orders_id`) AS flagTransporterId, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE tco.`from_city_id`="
							+ city
							+ " AND vt.`capacity_category` IN ('SCV', 'MCV', 'LCV', 'HCV') AND tco.`is_active`=1";
				}
			}
			dList = session.createSQLQuery(query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			session.close();

			if (dList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request successful.");
				cdt.setData(dList);

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("No client order for the corresponding transporter.");

				return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker clientOrderConfirmation(TransporterClientOrderMapping tcom) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();

		Session sessions = sessionFactory.openSession();
		try {
			Transaction tx = sessions.beginTransaction();
			sessions.saveOrUpdate(tcom);
			tx.commit();
			DetachedCriteria maxId = DetachedCriteria.forClass(TransporterClientOrderMapping.class)
					.setProjection(Projections.max("id"));
			List<TransporterClientOrderMapping> cmdList = sessions.createCriteria(TransporterClientOrderMapping.class)
					.add(Property.forName("id").eq(maxId)).list();
			sessions.clear();
			sessions.close();
			if (cmdList != null && cmdList.size() > 0) {
				TransporterClientOrderMapping dts = cmdList.get(0);
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request saved successfully");
				cdt.setTransporterClientOrderMapping(dts);
				return cdt;
			} else {
				TransporterClientOrderMapping dts = cmdList.get(0);
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("Request does not processed !");
				cdt.setTransporterClientOrderMapping(dts);
				return cdt;
			}
		} catch (Exception er) {

			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker orderFollowUp(TransporterOrderFollowUp tofu) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();

		Session session = this.sessionFactory.openSession();
		List<TransporterOrderFollowUp> objList = session.createQuery(
				"FROM TransporterOrderFollowUp WHERE trsptrClientOrdersId=" + tofu.getTrsptrClientOrdersId()
						+ " AND trsptrRegistrationId=" + tofu.getTrsptrRegistrationId())
				.list();
		session.close();

		if (objList.size() > 0) {
			cdt.setSuccess(false);
			cdt.setErrorCode("101");
			cdt.setErrorMessage("Order already followed by the transporter.");

			return cdt;
		} else {

			Session sessions = sessionFactory.openSession();
			try {
				Transaction tx = sessions.beginTransaction();
				sessions.saveOrUpdate(tofu);
				tx.commit();
				DetachedCriteria maxId = DetachedCriteria.forClass(TransporterOrderFollowUp.class)
						.setProjection(Projections.max("id"));
				List<TransporterOrderFollowUp> cmdList = sessions.createCriteria(TransporterOrderFollowUp.class)
						.add(Property.forName("id").eq(maxId)).list();
				sessions.clear();
				sessions.close();
				if (cmdList != null && cmdList.size() > 0) {
					TransporterOrderFollowUp dts = cmdList.get(0);
					cdt.setSuccess(true);
					cdt.setErrorCode("100");
					cdt.setErrorMessage("Request saved successfully");
					cdt.setTransporterOrderFollowUp(dts);
					return cdt;
				} else {
					TransporterOrderFollowUp dts = cmdList.get(0);
					cdt.setSuccess(false);
					cdt.setErrorCode("101");
					cdt.setErrorMessage("Request does not processed !");
					cdt.setTransporterOrderFollowUp(dts);
					return cdt;
				}
			} catch (Exception er) {

				ControllerDAOTracker cdte = new ControllerDAOTracker();
				cdte.setSuccess(false);
				cdte.setErrorCode("101");
				cdte.setErrorMessage(er.toString());

				return cdte;
			}
		}
	}

	public ControllerDAOTracker getFollowUpOrders(Integer trsptrRegistrationId) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {
			Session session = this.sessionFactory.openSession();
			List<TransporterOrderFollowUp> dList = session
					.createQuery("FROM TransporterOrderFollowUp WHERE trsptrRegistrationId=" + trsptrRegistrationId
							+ "AND isActive=1")
					.list();

			if (dList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request successful.");
				cdt.setData(dList);

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("Order not found for corresponding transporter.");

				return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker transporterClientIsActiveUpdate(TransporterClientOrders dto) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		Session session = this.sessionFactory.openSession();
		List<TransporterClientOrders> objList = session
				.createQuery("FROM TransporterClientOrders WHERE id=" + dto.getId()).list();
		session.close();
		if (objList != null && objList.size() > 0) {
			try {
				for (TransporterClientOrders at : objList) {
					if (dto != null && dto.getPrice() != null) {
						at.setPrice(dto.getPrice());
					}
					if (dto != null && dto.getDeployDateTime() != null) {
						at.setDeployDateTime(dto.getDeployDateTime());
					}
					if (dto != null && dto.getFromCityId() != null) {
						at.setFromCityId(dto.getFromCityId());
					}
					if (dto != null && dto.getToCityId() != null) {
						at.setToCityId(dto.getToCityId());
					}
					if (dto != null && dto.getId() != null) {
						at.setId(dto.getId());
					}
					if (dto != null && dto.getVehicleTypeId() != null) {
						at.setVehicleTypeId(dto.getVehicleTypeId());
					}
					if (dto != null && dto.getVehicleBody() != null) {
						at.setVehicleBody(dto.getVehicleBody());
					}
					if (dto != null && dto.getIsActive() != null) {
						at.setIsActive(dto.getIsActive());
					}
					if (dto != null && dto.getModifiedBy() != null) {
						at.setModifiedBy(dto.getModifiedBy());
					}
					if (dto != null && dto.getModifiedOn() != null) {
						at.setModifiedOn(dto.getModifiedOn());
					}

					Session session1 = this.sessionFactory.openSession();
					session1.get(TransporterClientOrders.class, at.getId());
					Transaction txs = session1.beginTransaction();
					TransporterClientOrders dts = (TransporterClientOrders) session1.merge(at);
					txs.commit();
					session1.close();

					cdt.setSuccess(true);
					cdt.setErrorCode("200");
					cdt.setErrorMessage("Record updated successfully");
					cdt.setTransporterClientOrders(dts);
					return cdt;

				}
			} catch (Exception er) {
				ControllerDAOTracker cdte = new ControllerDAOTracker();
				cdte.setSuccess(false);
				cdte.setErrorCode("101");
				cdte.setErrorMessage(er.toString());

				return cdte;
			}
		}
		ControllerDAOTracker cdte = new ControllerDAOTracker();
		cdte.setSuccess(false);
		cdte.setErrorCode("101");
		cdte.setErrorMessage("Your request does not processed !");
		return cdte;
	}

	public ControllerDAOTracker getClientOrdersHistory(Integer trsptrRegistrationId, String status,
			String vehicleCategory) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {
			Session session = this.sessionFactory.openSession();
			List<?> dList = null;

			String query;
			if (vehicleCategory.equals("MCV")) {
				if (status.equals("Pending")) {
					query = "SELECT tco.`id` id, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `trsptr_client_order_mapping` AS tcom ON tco.`id`=tcom.`trsptr_client_orders_id` LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE tcom.`trsptr_registration_id`="
							+ trsptrRegistrationId
							+ " AND tcom.`status`='Pending' AND vt.`capacity_category` IN ('SCV', 'MCV') AND tcom.`is_active`=1";
				} else if (status.equals("Confirmed")) {
					query = "SELECT tco.`id` id, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `trsptr_client_order_mapping` AS tcom ON tco.`id`=tcom.`trsptr_client_orders_id` LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE tcom.`trsptr_registration_id`="
							+ trsptrRegistrationId
							+ " AND tcom.`status`='Confirmed' AND vt.`capacity_category` IN ('SCV', 'MCV') AND tcom.`is_active`=1";
				} else {
					query = "SELECT tco.`id` id, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `trsptr_client_order_mapping` AS tcom ON tco.`id`=tcom.`trsptr_client_orders_id` LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE tcom.`trsptr_registration_id`="
							+ trsptrRegistrationId
							+ " AND tcom.`status`='Cancelled' AND vt.`capacity_category` IN ('SCV', 'MCV') AND tcom.`is_active`=1";
				}
			} else if (vehicleCategory.equals("LCV")) {
				if (status.equals("Pending")) {
					query = "SELECT tco.`id` id, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `trsptr_client_order_mapping` AS tcom ON tco.`id`=tcom.`trsptr_client_orders_id` LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE tcom.`trsptr_registration_id`="
							+ trsptrRegistrationId
							+ " AND tcom.`status`='Pending' AND vt.`capacity_category` IN ('LCV', 'HCV') AND tcom.`is_active`=1";
				} else if (status.equals("Confirmed")) {
					query = "SELECT tco.`id` id, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `trsptr_client_order_mapping` AS tcom ON tco.`id`=tcom.`trsptr_client_orders_id` LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE tcom.`trsptr_registration_id`="
							+ trsptrRegistrationId
							+ " AND tcom.`status`='Confirmed' AND vt.`capacity_category` IN ('LCV', 'HCV') AND tcom.`is_active`=1";
				} else {
					query = "SELECT tco.`id` id, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `trsptr_client_order_mapping` AS tcom ON tco.`id`=tcom.`trsptr_client_orders_id` LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE tcom.`trsptr_registration_id`="
							+ trsptrRegistrationId
							+ " AND tcom.`status`='Cancelled' AND vt.`capacity_category` IN ('LCV', 'HCV') AND tcom.`is_active`=1";
				}
			} else {
				if (status.equals("Pending")) {
					query = "SELECT tco.`id` id, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `trsptr_client_order_mapping` AS tcom ON tco.`id`=tcom.`trsptr_client_orders_id` LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE tcom.`trsptr_registration_id`="
							+ trsptrRegistrationId
							+ " AND tcom.`status`='Pending' AND vt.`capacity_category` IN ('SCV', 'MCV', 'LCV', 'HCV') AND tcom.`is_active`=1";
				} else if (status.equals("Confirmed")) {
					query = "SELECT tco.`id` id, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `trsptr_client_order_mapping` AS tcom ON tco.`id`=tcom.`trsptr_client_orders_id` LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE tcom.`trsptr_registration_id`="
							+ trsptrRegistrationId
							+ " AND tcom.`status`='Confirmed' AND vt.`capacity_category` IN ('SCV', 'MCV', 'LCV', 'HCV') AND tcom.`is_active`=1";
				} else {
					query = "SELECT tco.`id` id, tco.`from_city_id` fromCityId, c.`city` fromCityName, tco.`to_city_id` toCityId, c2.`city` toCityName, tco.`vehicle_type_id` vehicleTypeId, vt.`vehicleName` vehicleTypeName, tco.`vehicle_body` vehicleBody, tco.`price` amount, tco.`deploy_date_time` deployDateTime FROM `trsptr_client_orders` AS tco LEFT JOIN `trsptr_client_order_mapping` AS tcom ON tco.`id`=tcom.`trsptr_client_orders_id` LEFT JOIN `vehicle_type` AS vt ON tco.`vehicle_type_id`=vt.`id` LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` LEFT JOIN `cities` AS c2 ON tco.`to_city_id`=c2.`city_id` WHERE tcom.`trsptr_registration_id`="
							+ trsptrRegistrationId
							+ " AND tcom.`status`='Cancelled' AND vt.`capacity_category` IN ('SCV', 'MCV', 'LCV', 'HCV') AND tcom.`is_active`=1";
				}
			}
			dList = session.createSQLQuery(query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			session.close();

			if (dList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request successful.");
				cdt.setData(dList);

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("No client order for the corresponding transporter.");

				return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker changeTransporterStatus(TransporterClientOrderMapping dto) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		Session session = this.sessionFactory.openSession();
		List<TransporterClientOrderMapping> objList = session
				.createQuery("FROM TransporterClientOrderMapping WHERE id=" + dto.getId()).list();
		session.close();
		if (objList != null && objList.size() > 0) {
			try {
				for (TransporterClientOrderMapping at : objList) {
					if (dto != null && dto.getStatus() != null) {
						if (!dto.getStatus().equals(""))
							at.setStatus(dto.getStatus());
					}
					if (dto != null && dto.getIsActive() != null) {
						if (!dto.getIsActive().equals(""))
							at.setIsActive(dto.getIsActive());
					}
					if (dto != null && dto.getModifiedBy() != null) {
						at.setModifiedBy(dto.getModifiedBy());
					}
					if (dto != null && dto.getModifiedOn() != null) {
						at.setModifiedOn(dto.getModifiedOn());
					}
					Session session1 = this.sessionFactory.openSession();
					session1.get(TransporterClientOrderMapping.class, at.getId());
					Transaction txs = session1.beginTransaction();
					TransporterClientOrderMapping dts = (TransporterClientOrderMapping) session1.merge(at);
					txs.commit();
					session1.close();

					cdt.setSuccess(true);
					cdt.setErrorCode("200");
					cdt.setErrorMessage("Record updated successfully");
					cdt.setTransporterClientOrderMapping(dts);
					return cdt;

				}
			} catch (Exception er) {
				ControllerDAOTracker cdte = new ControllerDAOTracker();
				cdte.setSuccess(false);
				cdte.setErrorCode("101");
				cdte.setErrorMessage(er.toString());

				return cdte;
			}
		}
		ControllerDAOTracker cdte = new ControllerDAOTracker();
		cdte.setSuccess(false);
		cdte.setErrorCode("101");
		cdte.setErrorMessage("Your request does not processed !");
		return cdte;
	}

	public ControllerDAOTracker changeTransporterStatus(TransporterOrderFollowUp dto) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		Session session = this.sessionFactory.openSession();
		List<TransporterOrderFollowUp> objList = session
				.createQuery(
						"FROM TransporterOrderFollowUp WHERE trsptrClientOrdersId=" + dto.getTrsptrClientOrdersId())
				.list();
		session.close();
		if (objList != null && objList.size() > 0) {
			try {
				for (TransporterOrderFollowUp at : objList) {
					if (dto != null && dto.getIsActive() != null) {
						at.setIsActive(dto.getIsActive());
					}
					if (dto != null && dto.getModifiedBy() != null) {
						at.setModifiedBy(dto.getModifiedBy());
					}
					if (dto != null && dto.getModifiedOn() != null) {
						at.setModifiedOn(dto.getModifiedOn());
					}
					Session session1 = this.sessionFactory.openSession();
					session1.get(TransporterOrderFollowUp.class, at.getId());
					Transaction txs = session1.beginTransaction();
					TransporterOrderFollowUp dts = (TransporterOrderFollowUp) session1.merge(at);
					txs.commit();
					session1.close();

					cdt.setSuccess(true);
					cdt.setErrorCode("200");
					cdt.setErrorMessage("Record updated successfully");
					cdt.setTransporterOrderFollowUp(dts);
					return cdt;

				}
			} catch (Exception er) {
				ControllerDAOTracker cdte = new ControllerDAOTracker();
				cdte.setSuccess(false);
				cdte.setErrorCode("101");
				cdte.setErrorMessage(er.toString());

				return cdte;
			}
		}
		ControllerDAOTracker cdte = new ControllerDAOTracker();
		cdte.setSuccess(false);
		cdte.setErrorCode("101");
		cdte.setErrorMessage("Your request does not processed !");
		return cdte;
	}

	public ControllerDAOTracker getTransporterFreightChart(TransporterFreightChart tfc) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {
			Session session = this.sessionFactory.openSession();
			List<TransporterFreightChart> dList = session
					.createQuery("FROM TransporterFreightChart WHERE trsptrRegistrationId="
							+ tfc.getTrsptrRegistrationId() + " AND sourceCityId=" + tfc.getSourceCityId()
							+ " AND destinationCityId=" + tfc.getDestinationCityId() + " AND vehicleTypeId="
							+ tfc.getVehicleTypeId() + " AND bodyType='" + tfc.getBodyType() + "'")
					.list();

			if (dList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request successful.");
				cdt.setTransporterFreightChart(dList.get(0));

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("No freight rate for corresponding transporter or city exist.");
				return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker saveTransporterFreightRate(TransporterFreightChart tr) {
		try {
			ControllerDAOTracker cdt = new ControllerDAOTracker();

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(tr);
			tx.commit();
			DetachedCriteria maxId = DetachedCriteria.forClass(TransporterFreightChart.class)
					.setProjection(Projections.max("id"));
			@SuppressWarnings("unchecked")
			List<TransporterFreightChart> clusterList = session.createCriteria(TransporterFreightChart.class)
					.add(Property.forName("id").eq(maxId)).list();
			session.close();
			if (clusterList != null && clusterList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Freight rate saved successfully.");
				cdt.setData(clusterList.get(0));

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("Freight rate not saved.");

				return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker getOrderCityList() {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {
			Session session = this.sessionFactory.openSession();
			List<?> dList = null;

			String query;
			query = "SELECT DISTINCT(tco.`from_city_id`) fromCityId, c.`city` fromCityName FROM `trsptr_client_orders` AS tco LEFT JOIN `cities` AS c ON tco.`from_city_id`=c.`city_id` WHERE tco.`is_active`=1";
			dList = session.createSQLQuery(query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			session.close();

			if (dList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request successful.");
				cdt.setData(dList);

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("No orders available.");

				return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker createClientOrder(TransporterClientOrders tco) {
		try {
			ControllerDAOTracker cdt = new ControllerDAOTracker();

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(tco);
			tx.commit();
			DetachedCriteria maxId = DetachedCriteria.forClass(TransporterClientOrders.class)
					.setProjection(Projections.max("id"));
			@SuppressWarnings("unchecked")
			List<TransporterClientOrders> clusterList = session.createCriteria(TransporterClientOrders.class)
					.add(Property.forName("id").eq(maxId)).list();
			session.close();
			if (clusterList != null && clusterList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Order saved successfully.");
				cdt.setData(clusterList.get(0));

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("Order not saved.");

				return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker searchOrder(Integer fromCityId, Integer toCityId, String status) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {
			Session session = this.sessionFactory.openSession();
			List<?> dList = null;

			String query = "";

			query += "SELECT ";
			query += "tco.`id` orderId, tcom.`id` mapOrderId, tcom.`vehicle_number`vehicleNumber,tcom.`driver_name` driverName, tcom.`driver_mobile_number` driverMobileNumber, tr.`contact_person_name` trsptrPersonName, tr.`mobile_number` trsptrMobileNumber,tco.`price` freightRate, (SELECT city FROM cities AS c WHERE c.city_id=tco.`from_city_id`) AS sourceName, (SELECT city FROM cities AS c WHERE c.city_id=tco.`to_city_id`) AS destinationName,tco.`deploy_date_time` reportingTime,tco.`vehicle_body` vehicleBody, vt.`vehicleName`, tcom.`status` ";
			query += "FROM `trsptr_client_order_mapping` AS tcom ";
			query += "LEFT JOIN `trsptr_client_orders` AS tco ON tco.`id`=tcom.`trsptr_client_orders_id` ";
			query += "LEFT JOIN `vehicle_type` AS vt ON vt.`id`=tco.`vehicle_type_id` ";
			query += "LEFT JOIN `trsptr_registration` AS tr ON tr.`id`=tcom.`trsptr_registration_id` ";
			// query += "WHERE tcom.`is_active`=1 OR tcom.`is_active`=0 "; //

			if (!status.equals("All"))
				query += "WHERE tcom.`status`='" + status + "' ";

			if (fromCityId != null || toCityId != null)
				if (!fromCityId.equals("") && !toCityId.equals(""))
					query += "AND tco.`from_city_id`=" + fromCityId + " AND tco.`to_city_id`=" + toCityId;

			dList = session.createSQLQuery(query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			session.close();

			if (dList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request successful.");
				cdt.setData(dList);

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("No orders available.");

				return cdt;
			}
		} catch (Exception er) {
			er.printStackTrace();
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public void saveSMSRecord(CommunicationSMS cSms) {
		try {
			ControllerDAOTracker cdt = new ControllerDAOTracker();

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(cSms);
			tx.commit();
			DetachedCriteria maxId = DetachedCriteria.forClass(CommunicationSMS.class)
					.setProjection(Projections.max("id"));
			@SuppressWarnings("unchecked")
			List<CommunicationSMS> clusterList = session.createCriteria(CommunicationSMS.class)
					.add(Property.forName("id").eq(maxId)).list();
			session.close();
			if (clusterList != null && clusterList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Record saved successfully.");
				cdt.setData(clusterList.get(0));

				// return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("Record not saved.");

				// return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			// return cdte;
		}
	}

	public ControllerDAOTracker approveCRFOrder(TransporterClientOrders dto) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		Session session = this.sessionFactory.openSession();
		List<TransporterClientOrders> objList = session
				.createQuery("FROM TransporterClientOrders WHERE id=" + dto.getId()).list();
		session.close();
		if (objList != null && objList.size() > 0) {
			try {
				for (TransporterClientOrders at : objList) {
					if (dto != null && dto.getVehicleTypeId() != null) {
						if (!dto.getVehicleTypeId().equals(""))
							at.setVehicleTypeId(dto.getVehicleTypeId());
					}
					if (dto != null && dto.getFromCityId() != null) {
						if (!dto.getFromCityId().equals(""))
							at.setFromCityId(dto.getFromCityId());
					}
					if (dto != null && dto.getToCityId() != null) {
						if (!dto.getToCityId().equals(""))
							at.setToCityId(dto.getToCityId());
					}
					if (dto != null && dto.getDeployDateTime() != null) {
						at.setDeployDateTime(dto.getDeployDateTime());
					}
					if (dto != null && dto.getPrice() != null) {
						if (!dto.getPrice().equals(""))
							at.setPrice(dto.getPrice());
					}
					if (dto != null && dto.getIsActive() != null) {
						if (!dto.getIsActive().equals(""))
							at.setIsActive(dto.getIsActive());
					}
					if (dto != null && dto.getIsPublished() != null) {
						if (!dto.getIsPublished().equals(""))
							at.setIsPublished(dto.getIsPublished());
					}

					Session session1 = this.sessionFactory.openSession();
					session1.get(TransporterClientOrders.class, at.getId());
					Transaction txs = session1.beginTransaction();
					TransporterClientOrders dts = (TransporterClientOrders) session1.merge(at);
					txs.commit();
					session1.close();

					cdt.setSuccess(true);
					cdt.setErrorCode("200");
					cdt.setErrorMessage("Order published successfully");
					cdt.setTransporterClientOrders(dts);
					return cdt;

				}
			} catch (Exception er) {
				ControllerDAOTracker cdte = new ControllerDAOTracker();
				cdte.setSuccess(false);
				cdte.setErrorCode("101");
				cdte.setErrorMessage(er.toString());

				return cdte;
			}
		}
		ControllerDAOTracker cdte = new ControllerDAOTracker();
		cdte.setSuccess(false);
		cdte.setErrorCode("101");
		cdte.setErrorMessage("Your request does not processed !");
		return cdte;
	}

	public ControllerDAOTracker trsptrVehicleStatusChange(Integer trsptrRegistrationId, String vehicleNumber) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		Session session = this.sessionFactory.openSession();
		List<TransporterVehicleRegistration> objList = session
				.createQuery("FROM TransporterVehicleRegistration WHERE trsptrRegistrationId=" + trsptrRegistrationId
						+ " AND vehicleNumber='" + vehicleNumber + "'")
				.list();
		session.close();
		if (objList != null && objList.size() > 0) {
			try {
				for (TransporterVehicleRegistration at : objList) {
					at.setStatus("Active");

					Session session1 = this.sessionFactory.openSession();
					session1.get(TransporterVehicleRegistration.class, at.getId());
					Transaction txs = session1.beginTransaction();
					TransporterVehicleRegistration dts = (TransporterVehicleRegistration) session1.merge(at);
					txs.commit();
					session1.close();

					cdt.setSuccess(true);
					cdt.setErrorCode("200");
					cdt.setErrorMessage("Vehicle status changed successfully.");
					cdt.setTransporterVehicleRegistration(dts);
					return cdt;

				}
			} catch (Exception er) {
				ControllerDAOTracker cdte = new ControllerDAOTracker();
				cdte.setSuccess(false);
				cdte.setErrorCode("101");
				cdte.setErrorMessage(er.toString());

				return cdte;
			}
		}
		ControllerDAOTracker cdte = new ControllerDAOTracker();
		cdte.setSuccess(false);
		cdte.setErrorCode("101");
		cdte.setErrorMessage("Your request does not processed !");
		return cdte;
	}

	public List<?> getServerApi(String string) {
		try {
			Session session = this.sessionFactory.openSession();
			List<?> dList = null;

			String query;
			query = "SELECT server_api_key FROM app_versions WHERE app_name='" + string + "'";
			dList = session.createSQLQuery(query).setResultTransformer(Criteria.ROOT_ENTITY).list();
			session.close();

			if (dList.size() > 0) {
				return dList;
			} else {
				return dList;
			}
		} catch (Exception er) {

			return null;
		}
	}

	public List<String> getGCM() {
		try {
			Session session = this.sessionFactory.openSession();
			List<String> dList = null;

			String query = "";

			query += "SELECT gcm_id FROM `trsptr_push_notification` ";
			query += "WHERE id IN ";
			query += "(";
			query += "SELECT MAX(`id`) ";
			query += "FROM `trsptr_push_notification` ";
			query += "GROUP BY `trsptr_registration_id`, `mac_id` ";
			query += ")";

			dList = session.createSQLQuery(query).setResultTransformer(Criteria.ROOT_ENTITY).list();
			session.close();

			if (dList.size() > 0) {
				return dList;
			} else {
				return dList;
			}
		} catch (Exception er) {

			return null;
		}
	}

	@SuppressWarnings("unchecked")

	public List<TransporterClientOrders> getAllOrder() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(TransporterClientOrders.class);
		return (List<TransporterClientOrders>) criteria.list();
	}

	public ControllerDAOTracker getOrder(Integer fromCityId, Integer toCityId) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {
			Session session = this.sessionFactory.openSession();
			List<?> dList = null;

			String query = "";

			// query += "SELECT * FROM truxdev.trsptr_client_orders WHERE
			// is_active=1";
			// query += "SELECT tco.`id`, c.`city`,(SELECT cities.`city` FROM
			// cities WHERE cities.`city_id`=tco.`to_city_id`) AS to_city
			// ,vt.`vehicleName`, tco.`vehicle_body`, tco.`price`,
			// tco.`deploy_date_time` FROM `trsptr_client_orders` AS tco LEFT
			// JOIN cities AS c ON c.`city_id`=tco.`from_city_id` LEFT JOIN
			// `vehicle_type` AS vt ON vt.`id`=tco.`vehicle_type_id`";
			// query += "SELECT tco.`id`, c.`city`,(SELECT cities.`city` FROM
			// cities WHERE cities.`city_id`=tco.`to_city_id`) AS to_city
			// ,vt.`vehicleName`, tco.`vehicle_body`, tco.`price`,
			// tco.`deploy_date_time` FROM `trsptr_client_orders` AS tco LEFT
			// JOIN cities AS c ON c.`city_id`=tco.`from_city_id` LEFT JOIN
			// `vehicle_type` AS vt ON vt.`id`=tco.`vehicle_type_id` WHERE
			// tco.`is_active`=1;";
			query += "SELECT tco.`id`, c.`city` as from_city_id,(SELECT cities.`city` FROM cities WHERE cities.`city_id`=tco.`to_city_id`) AS to_city_id ,vt.`vehicleName`, tco.`vehicle_body`, tco.`price`, tco.`deploy_date_time`FROM `trsptr_client_orders` AS tco LEFT JOIN cities AS c ON c.`city_id`=tco.`from_city_id`LEFT JOIN `vehicle_type` AS vt ON vt.`id`=tco.`vehicle_type_id` WHERE tco.`is_active`=1";
			/*
			 * if(fromCityId != null || toCityId !=null )
			 * if(!fromCityId.equals("") && !toCityId.equals("")) query +=
			 * "AND tco.`from_city_id`="+fromCityId+" AND tco.`to_city_id`="
			 * +toCityId;
			 */

			/* WITH SEPARATE DATE AND TIME */
			// query += "SELECT tco.`id`, c.`city`,(SELECT cities.`city` FROM
			// cities WHERE cities.`city_id`=tco.`to_city_id`) AS to_city
			// ,vt.`vehicleName`, tco.`vehicle_body`, tco.`price`,
			// DATE(tco.`deploy_date_time`) AS DATE,
			// TIME(tco.`deploy_date_time`) AS TIME FROM `trsptr_client_orders`
			// AS tco LEFT JOIN cities AS c ON c.`city_id`=tco.`from_city_id`
			// LEFT JOIN `vehicle_type` AS vt ON vt.`id`=tco.`vehicle_type_id`
			// WHERE tco.`is_active`=1;";

			dList = session.createSQLQuery(query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			session.close();

			if (dList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request successful.");
				cdt.setData(dList);

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("No orders available.");

				return cdt;
			}
		} catch (Exception er) {
			er.printStackTrace();
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	public ControllerDAOTracker getByOrderId(Integer id, Integer price, Date deployDateTime) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {
			Session session = this.sessionFactory.openSession();
			List<?> dList = null;

			String query = "";

			query += "update truxdev.trsptr_client_orders set price=" + price + ",deploy_date_time=" + deployDateTime
					+ "where id=" + id;

			dList = session.createSQLQuery(query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			session.close();

			if (dList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request successful.");
				cdt.setData(dList);

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("No orders available.");

				return cdt;
			}
		} catch (Exception er) {
			er.printStackTrace();
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}
	}

	@SuppressWarnings("unchecked")

	public ControllerDAOTracker updateClientOrder(TransporterClientOrders dto) {

		ControllerDAOTracker cdt = new ControllerDAOTracker();
		Session session = this.sessionFactory.openSession();
		System.out.println(dto.getId());
		List<TransporterClientOrders> objList = session
				.createQuery("FROM TransporterClientOrders WHERE id=" + dto.getId()).list();

		session.close();
		if (objList != null && objList.size() > 0) {
			try {
				for (TransporterClientOrders at : objList) {
					if (dto != null && dto.getFromCityId() != null) {
						if (!dto.getFromCityId().equals(""))
							at.setFromCityId(dto.getFromCityId());
					}
					if (dto != null && dto.getToCityId() != null) {
						if (!dto.getToCityId().equals(""))
							at.setToCityId(dto.getToCityId());
					}
					if (dto != null && dto.getVehicleBody() != null) {
						if (!dto.getVehicleBody().equals(""))
							at.setVehicleBody(dto.getVehicleBody());
					}
					if (dto != null && dto.getCreatedOn() != null) {
						if (!dto.getCreatedOn().equals(""))
							at.setCreatedOn(dto.getCreatedOn());
					}
					if (dto != null && dto.getVehicleTypeId() != null) {
						at.setVehicleTypeId(dto.getVehicleTypeId());
					}
					if (dto != null && dto.getCreatedBy() != null) {
						at.setCreatedBy(dto.getCreatedBy());
					}
					if (dto != null && dto.getModifiedBy() != null) {
						at.setModifiedBy(dto.getModifiedBy());
					}
					if (dto != null && dto.getModifiedOn() != null) {
						at.setModifiedOn(dto.getModifiedOn());
					}
					if (dto != null && dto.getIsActive() != null) {
						at.setIsActive(dto.getIsActive());
					}
					System.out.println(dto.getPrice());
					at.setPrice(dto.getPrice());
					at.setDeployDateTime(dto.getDeployDateTime());
					at.setModifiedOn(new Date());
					Session session1 = this.sessionFactory.openSession();
					session1.get(TransporterClientOrders.class, at.getId());
					Transaction txs = session1.beginTransaction();
					TransporterClientOrders dts = (TransporterClientOrders) session1.merge(at);
					txs.commit();
					session1.close();

					cdt.setSuccess(true);
					cdt.setErrorCode("200");
					cdt.setErrorMessage("Order updated successfully");
					cdt.setTransporterClientOrders(dts);
					return cdt;

				}
			} catch (Exception er) {
				ControllerDAOTracker cdte = new ControllerDAOTracker();
				cdte.setSuccess(false);
				cdte.setErrorCode("101");
				cdte.setErrorMessage(er.toString());

				return cdte;
			}
		}
		ControllerDAOTracker cdte = new ControllerDAOTracker();
		cdte.setErrorCode("101");
		cdte.setErrorMessage("Your request does not processed !");
		return cdte;

	}

	public ControllerDAOTracker saveFile(ExcelFile excelFile) {

		try {
			ControllerDAOTracker cdt = new ControllerDAOTracker();

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(excelFile);
			Thread.sleep(1000);
			tx.commit();
			Thread.sleep(1000);
			DetachedCriteria maxId = DetachedCriteria.forClass(ExcelFile.class).setProjection(Projections.max("id"));
			@SuppressWarnings("unchecked")
			List<ExcelFile> clusterList = session.createCriteria(ExcelFile.class).add(Property.forName("id").eq(maxId))
					.list();
			session.close();
			if (clusterList != null && clusterList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Record saved successfully.");
				cdt.setData(clusterList.get(0));
				Thread.sleep(1000);
				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("Record not saved.");

				return cdt;
			}
		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}

	}

	@SuppressWarnings("unchecked")
	public List<States> getStateName(States state) {
		Session session = this.sessionFactory.openSession();
		List<States> objList = session.createQuery("FROM States WHERE stateName='" + state.getStateName() + "'").list();
		return objList;
	}

	public List<Cities> getCityName(Cities city) {
		Session session = this.sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Cities> objList = session.createQuery("FROM Cities WHERE cityName='" + city.getCityName() + "'").list();
		return objList;
	}

	public List<VehicleType> getVehicleType(VehicleType vehicleType) {
		Session session = this.sessionFactory.openSession();
		@SuppressWarnings("unchecked")

		List<VehicleType> objList = session
				.createQuery("FROM VehicleType WHERE vehicleName='" + vehicleType.getVehicleName() + "'").list();
		return objList;
	}

	@SuppressWarnings("unchecked")
	public ControllerDAOTracker getRecoredBy(Integer cityId, Integer cityId2, Integer id) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {
			Session session = this.sessionFactory.openSession();
			List<ExcelFile> dList = session.createQuery("FROM ExcelFile WHERE source_city_id=" + cityId
					+ " AND destination_city_id=" + cityId2 + " AND vehicle_type_id=" + id).list();

			if (dList.size() > 0) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Request successful.");
				cdt.setExcelFile(dList.get(0));

				return cdt;
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("101");
				cdt.setErrorMessage("New Record has to be insert");

				return cdt;
			}
		} catch (Exception er) {
			er.printStackTrace();
			System.out.println(er);
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage(er.toString());

			return cdte;
		}

	}

	@SuppressWarnings("unchecked")
	public ControllerDAOTracker updateFile(ExcelFile dto) {
	
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		Session session = this.sessionFactory.openSession();
		System.out.println(dto.getVehicle_type_id());
		List<ExcelFile> objList = session
				.createQuery(
						"FROM ExcelFile WHERE source_city_id=" + dto.getSource_city_id() + " AND destination_city_id="
								+ dto.getDestination_city_id() + " AND vehicle_type_id=" + dto.getVehicle_type_id())
				.list();
		// .createQuery("FROM ExcelFile WHERE vehicle_type_id=" +
		// dto.getVehicle_type_id() + " AND id=" + dto.getId()).list();

		session.close();
		if (objList != null && objList.size() > 0) {
			try {
				for (ExcelFile at : objList) {
					if (dto != null && dto.getSource_state_id() != null) {
						if (!dto.getSource_state_id().equals(""))
							at.setSource_state_id(dto.getSource_state_id());
					}
					if (dto != null && dto.getSource_city_id() != null) {
						if (!dto.getSource_city_id().equals(""))
							at.setSource_city_id(dto.getSource_city_id());
					}
					if (dto != null && dto.getDestination_state_id() != null) {
						if (!dto.getDestination_state_id().equals(""))
							at.setDestination_state_id(dto.getDestination_state_id());
					}
					if (dto != null && dto.getDestination_city_id() != null) {
						if (!dto.getDestination_city_id().equals(""))
							at.setDestination_city_id(dto.getDestination_city_id());
					}
					if (dto != null && dto.getCreatedOn() != null) {
						if (!dto.getCreatedOn().equals(""))
							at.setCreatedOn(dto.getCreatedOn());
					}
					if (dto != null && dto.getCreatedBy() != null) {
						at.setCreatedBy(dto.getCreatedBy());
					}
					if (dto != null && dto.getModifiedBy() != null) {
						at.setModifiedBy(dto.getModifiedBy());
					}
					if (dto != null && dto.getModifiedOn() != null) {
						at.setModifiedOn(dto.getModifiedOn());
					}
					if (dto != null && dto.getIsActive() != null) {
						at.setIsActive(dto.getIsActive());
					}
					at.setRate(dto.getRate());
					at.setModifiedBy(dto.getModifiedBy());
					at.setModifiedOn(new Date());
					Session session1 = this.sessionFactory.openSession();
					session1.get(ExcelFile.class, at.getId());
					Transaction txs = session1.beginTransaction();
					ExcelFile dts = (ExcelFile) session1.merge(at);
					Thread.sleep(1000);
					txs.commit();Thread.sleep(1000);
					session1.close();

					cdt.setSuccess(true);
					cdt.setErrorCode("200");
					cdt.setErrorMessage("Order updated successfully");
					cdt.setExcelFile(dts);
					return cdt;

				}
			} catch (Exception er) {
				ControllerDAOTracker cdte = new ControllerDAOTracker();
				cdte.setSuccess(false);
				cdte.setErrorCode("101");
				cdte.setErrorMessage(er.toString());

				return cdte;
			}
		}
		ControllerDAOTracker cdte = new ControllerDAOTracker();
		cdte.setErrorCode("101");
		cdte.setErrorMessage("Your request does not processed !");
		return cdte;
	}

	public List<?> saveToExcell(Integer fromCity, Integer toCity) {
		try {
			Session session = this.sessionFactory.openSession();
			List<?> dList = null;

			String query = "";

			query += "SELECT * from master_freight_rate";

			if (fromCity != null && !fromCity.equals("")) {
				if (toCity == null || toCity.equals("")) {
					query += "WHERE source_city_id=" + fromCity + " AND destination_city_id=" + toCity;
				} else {
				}

			}

			query += ";";

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

	public List<ExcelFile> getExcel() {
		Session session = sessionFactory.openSession();
		try {
			List<ExcelFile> omrList = session.createQuery("from ExcelFile").list();
			session.close();
			if (omrList != null && !omrList.isEmpty()) {
				return omrList;
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;
	}

	public List<?> savToExcel(Integer source_city_id, Integer destination_city_id,Integer vehicle_type_id) {
		try {
			Session session = this.sessionFactory.openSession();
			List<?> dList = null;

			String query = "";

			query += "SELECT mfr.`id`, s.`state_name` as source_state_id, c.`city` as source_city_id, (SELECT states.`state_name`  FROM `states` WHERE `states`.`state_id`=mfr.`destination_state_id`) AS  destination_state_id,(SELECT `cities`.`city` FROM `cities` WHERE `cities`.`city_id`=mfr.`destination_city_id`) AS destination_city_id, mfr.distance,mfr.freight_rate, vt.`vehicleName`as vehicle_type_id FROM `master_freight_rate` AS mfr LEFT JOIN states AS s ON s.`state_id`=mfr.`source_state_id` LEFT JOIN cities AS c ON c.`city_id`=mfr.`source_city_id` LEFT JOIN `vehicle_type` AS vt ON vt.`id`=mfr.`vehicle_type_id` ";

			if (source_city_id != null && !source_city_id.equals("")) {
				if (destination_city_id != null && !destination_city_id.equals("")) {
					query += "WHERE source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id;
					if(vehicle_type_id !=null && !vehicle_type_id.equals("")){
						query +=" AND vehicle_type_id=" +vehicle_type_id;
					}
				} else {
				}

			}
			

			query += ";";

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

	public List<VehicleType> getVehicle(String string) {

		Session session = this.sessionFactory.openSession();
		List<VehicleType> vehicleList = new ArrayList<VehicleType>();
		try {
			SessionImpl sessionImpl = (SessionImpl) session;
			java.sql.Connection connection = sessionImpl.connection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM vehicle_type where vehicleName='" + string+"'");
			while (rs.next()) {
				VehicleType dto = new VehicleType();
				dto.setId(rs.getInt("id"));
				dto.setVehicleName(rs.getString("vehicleName"));
				vehicleList.add(dto);
				
			}
			connection.close();
			session.clear();
			session.close();
		} catch (SQLException e) {
			e.printStackTrace();
			session.close();
		}
		try {

			if (vehicleList != null && !vehicleList.isEmpty()) {
				return vehicleList;
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;

	}

	public List<VehicleType> getVehicle(Integer vehicle) {

		Session session = this.sessionFactory.openSession();
		List<VehicleType> vehicleList = new ArrayList<VehicleType>();
		try {
			SessionImpl sessionImpl = (SessionImpl) session;
			java.sql.Connection connection = sessionImpl.connection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM vehicle_type where id='" + vehicle+"'");
			while (rs.next()) {
				VehicleType dto = new VehicleType();
				dto.setId(rs.getInt("id"));
				dto.setVehicleName(rs.getString("vehicleName"));
				vehicleList.add(dto);
				
			}
			connection.close();
			session.clear();
			session.close();
		} catch (SQLException e) {
			e.printStackTrace();
			session.close();
		}
		try {

			if (vehicleList != null && !vehicleList.isEmpty()) {
				return vehicleList;
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;

	}

	public List<?> savToExcel(Integer source_city_id, Integer destination_city_id, List<VehicleType> allVehicle) {
		try {
			Session session = this.sessionFactory.openSession();
			List<?> dList = null;

			String query = "";

			query += "SELECT mfr.`id`, s.`state_name` as source_state_id, c.`city` as source_city_id, (SELECT states.`state_name`  FROM `states` WHERE `states`.`state_id`=mfr.`destination_state_id`) AS  destination_state_id,(SELECT `cities`.`city` FROM `cities` WHERE `cities`.`city_id`=mfr.`destination_city_id`) AS destination_city_id, mfr.distance,mfr.freight_rate, vt.`vehicleName`as vehicle_type_id FROM `master_freight_rate` AS mfr LEFT JOIN states AS s ON s.`state_id`=mfr.`source_state_id` LEFT JOIN cities AS c ON c.`city_id`=mfr.`source_city_id` LEFT JOIN `vehicle_type` AS vt ON vt.`id`=mfr.`vehicle_type_id` ";

			if (source_city_id != null && !source_city_id.equals("")) {
				if (destination_city_id != null && !destination_city_id.equals("")) {
					
					if(allVehicle !=null && !allVehicle.equals("")){
						query +=" WHERE source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(0).getId();
						query +=" OR source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(1).getId();
						query +=" OR source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(2).getId();
						query +=" OR source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(3).getId();
						query +=" OR source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(4).getId();
						query +=" OR source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(5).getId();
						query +=" OR source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(6).getId();
						query +=" OR source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(7).getId();
						query +=" OR source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(8).getId();
						query +=" OR source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(9).getId();
						query +=" OR source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(10).getId();
						query +=" OR source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(11).getId();
						query +=" OR source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(12).getId();
						query +=" OR source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(13).getId();
						query +=" OR source_city_id=" + source_city_id + " AND destination_city_id=" + destination_city_id+ " AND vehicle_type_id=" +allVehicle.get(14).getId();
					}
//					
					
				} else {
				}

			}
			

			query += ";";

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
}
