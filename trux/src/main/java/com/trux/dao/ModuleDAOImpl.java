package com.trux.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.internal.SessionImpl;
import org.hibernate.transform.Transformers;

import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.CRFOrder;
import com.trux.model.Cities;
import com.trux.model.ControllerDAOTracker;
import com.trux.model.Countries;
import com.trux.model.Module;
import com.trux.model.States;
import com.trux.model.UserDetail;
import com.trux.model.VehicleType;

public class ModuleDAOImpl implements ModuleDAO {
	public static Map<String, Integer> countryMap = new HashMap<String, Integer>();
	public static Map<String, Integer> cityMap = new HashMap<String, Integer>();
	public static Map<String, Integer> stateMap = new HashMap<String, Integer>();
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	public Map<String, Integer> getAllCountriesMap() {
		if (countryMap.isEmpty()) {
			Session session = sessionFactory.openSession();
			try {

				SessionImpl sessionImpl = (SessionImpl) session;
				java.sql.Connection connection = sessionImpl.connection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM countries;");
				while (rs.next()) {
					countryMap.put(rs.getString("value"), rs.getInt("id"));
				}
				connection.close();
				session.clear();
				session.close();
			} catch (SQLException e) {
				e.printStackTrace();
				session.close();
			}
		}
		return countryMap;
	}

	public Map<String, Integer> getAllCitiesMap() {
		if (cityMap.isEmpty()) {
			Session session = sessionFactory.openSession();
			try {
				SessionImpl sessionImpl = (SessionImpl) session;
				java.sql.Connection connection = sessionImpl.connection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM cities;");
				while (rs.next()) {
					cityMap.put(rs.getString("city"), rs.getInt("city_id"));
				}
				connection.close();
				session.clear();
				session.close();
			} catch (SQLException e) {
				e.printStackTrace();
				session.close();
			}
		}
		return cityMap;

	}

	public Map<String, Integer> getAllStatesMap() {
		if (stateMap.isEmpty()) {
			Session session = sessionFactory.openSession();
			try {
				SessionImpl sessionImpl = (SessionImpl) session;
				java.sql.Connection connection = sessionImpl.connection();

				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("select * from states;");
				while (rs.next()) {
					stateMap.put(rs.getString("state_name"), rs.getInt("state_id"));
				}
				connection.close();
				session.clear();
				session.close();
			} catch (Exception er) {
				er.printStackTrace();
				session.close();
			}
		}
		return stateMap;
	}

	public List<Countries> getAllCountriesList() {

		Session session = this.sessionFactory.openSession();
		try {

			List<Countries> countriesList = new ArrayList<Countries>();
			try {

				SessionImpl sessionImpl = (SessionImpl) session;
				java.sql.Connection connection = sessionImpl.connection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM countries;");
				while (rs.next()) {
					Countries dto = new Countries();
					dto.setId(rs.getInt("id"));
					dto.setValue(rs.getString("value"));
					dto.setCode(rs.getString("code"));
					countriesList.add(dto);
				}
				connection.close();
				session.clear();
				session.close();
			} catch (SQLException e) {
				e.printStackTrace();
				session.close();
			}

			if (countriesList != null && !countriesList.isEmpty()) {
				return countriesList;
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;
	}

	public List<States> getAllStates() {
		Session session = this.sessionFactory.openSession();
		List<States> statesList = new ArrayList<States>();
		try {
			SessionImpl sessionImpl = (SessionImpl) session;
			java.sql.Connection connection = sessionImpl.connection();

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from states;");
			while (rs.next()) {
				States dto = new States();
				dto.setCountryId(rs.getInt("country_id"));
				dto.setStateId(rs.getInt("state_id"));
				dto.setStateName(rs.getString("state_name"));
				statesList.add(dto);
			}
			connection.close();
			session.clear();
			session.close();
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		try {

			if (statesList != null && !statesList.isEmpty()) {
				return statesList;
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;
	}

	public List<States> getStatesListForACountry(Integer countryId) {

		Session session = this.sessionFactory.openSession();
		List<States> statesList = new ArrayList<States>();
		try {
			SessionImpl sessionImpl = (SessionImpl) session;
			java.sql.Connection connection = sessionImpl.connection();

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from states  where country_id=" + countryId + ";");
			while (rs.next()) {
				States dto = new States();
				dto.setCountryId(rs.getInt("country_id"));
				dto.setStateId(rs.getInt("state_id"));
				dto.setStateName(rs.getString("state_name"));
				statesList.add(dto);
			}
			connection.close();
			session.clear();
			session.close();
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		try {

			if (statesList != null && !statesList.isEmpty()) {
				return statesList;
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;
		/*
		 * Session session = this.sessionFactory.openSession(); try{
		 * 
		 * List<States> statesList = session.createQuery(
		 * "from States s where s.countryId="+countryId).list();
		 * session.close(); if(statesList != null && !statesList.isEmpty()){
		 * return statesList; } }catch(Exception er){er.printStackTrace();
		 * session.close();} return null;
		 */
	}

	public List<Cities> getAllCities() {
		Session session = this.sessionFactory.openSession();
		List<Cities> citiesList = new ArrayList<Cities>();
		try {
			SessionImpl sessionImpl = (SessionImpl) session;
			java.sql.Connection connection = sessionImpl.connection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cities;");
			while (rs.next()) {
				Cities dto = new Cities();
				dto.setCityId(rs.getInt("city_id"));
				dto.setCityName(rs.getString("city"));
				dto.setStateId(rs.getInt("state_id"));
				citiesList.add(dto);
			}
			connection.close();
			session.clear();
			session.close();
		} catch (SQLException e) {
			e.printStackTrace();
			session.close();
		}
		try {

			if (citiesList != null && !citiesList.isEmpty()) {
				return citiesList;
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;
	}

	public List<Cities> getAllCitiesForAState(Integer stateId) {

		Session session = this.sessionFactory.openSession();
		List<Cities> citiesList = new ArrayList<Cities>();
		try {
			SessionImpl sessionImpl = (SessionImpl) session;
			java.sql.Connection connection = sessionImpl.connection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cities where state_id=" + stateId + ";");
			while (rs.next()) {
				Cities dto = new Cities();
				dto.setCityId(rs.getInt("city_id"));
				dto.setCityName(rs.getString("city"));
				dto.setStateId(rs.getInt("state_id"));
				citiesList.add(dto);
			}
			connection.close();
			session.clear();
			session.close();
		} catch (SQLException e) {
			e.printStackTrace();
			session.close();
		}
		try {

			if (citiesList != null && !citiesList.isEmpty()) {
				return citiesList;
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;
		/*
		 * Session session = this.sessionFactory.openSession(); try{
		 * List<Cities> citiesList = session.createQuery(
		 * "from Cities c where c.stateId="+stateId).list(); session.close();
		 * if(citiesList != null && !citiesList.isEmpty()){ return citiesList; }
		 * }catch(Exception er){ er.printStackTrace(); session.close(); } return
		 * null;
		 */
	}

	@SuppressWarnings("unchecked")
	public List<Cities> getCityCodeByName(String userInput) {

		Session session = this.sessionFactory.openSession();
		try {
			List<Cities> cityCode = session
					.createQuery("select c from Cities c  where c.cityName like '%" + userInput + "%'").list();
			session.close();
			if (cityCode != null) {
				return cityCode;
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public Cities getAllCityById(Integer cityId) {
		Session session = sessionFactory.openSession();
		try {
			List<Cities> citiesList = session.createQuery("from Cities c where c.cityId=" + cityId).list();
			session.clear();
			session.close();
			if (citiesList != null && !citiesList.isEmpty()) {
				return citiesList.get(0);
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public States getAllStateById(Integer stateId) {
		Session session = sessionFactory.openSession();
		try {
			List<States> stateList = session.createQuery("from States c where c.stateId=" + stateId).list();
			session.close();
			if (stateList != null && !stateList.isEmpty()) {
				return stateList.get(0);
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")

	public Countries getAllCountryById(Integer countryID) {
		Session session = sessionFactory.openSession();
		try {
			List<Countries> countriesList = session.createQuery("from Countries c where c.id=" + countryID).list();
			session.close();
			if (countriesList != null && !countriesList.isEmpty()) {
				return countriesList.get(0);
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;
	}

	public Module save(Module dto) {
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.getTransaction();
			session.save(dto);
			tx.begin();
			tx.commit();
			DetachedCriteria maxId = DetachedCriteria.forClass(Module.class).setProjection(Projections.max("id"));
			@SuppressWarnings("unchecked")
			List<Module> idList = session.createCriteria(Module.class).add(Property.forName("id").eq(maxId)).list();
			session.flush();
			session.clear();
			session.close();
			if (idList != null && !idList.isEmpty()) {
				return idList.get(0);
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return new Module(TruxErrorCodes.VEHICLE_TYPE_MESSAGE.getCode(),
				TruxErrorCodes.VEHICLE_TYPE_MESSAGE.getDescription());

	}

	@SuppressWarnings("unchecked")

	public List<Module> getModule(String accessRightsRole) {
		Session session = sessionFactory.openSession();
		try {
			String[] role = accessRightsRole.split(",");
			String accessRightsRole11, accessRightsRole2;
			if (role.length == 2) {
				accessRightsRole11 = role[0];
				accessRightsRole2 = role[1];
			} else {
				accessRightsRole11 = accessRightsRole;
				accessRightsRole2 = accessRightsRole;
			}
			Query query = session.createQuery("from Module m where m.accessRightsRole BETWEEN '" + accessRightsRole11
					+ "' AND '" + accessRightsRole2 + "'");
			List<Module> idList = query.list();
			session.flush();
			session.clear();
			session.close();
			if (idList != null && !idList.isEmpty()) {
				return idList;
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		List<Module> list = new ArrayList<Module>();
		list.add(new Module(TruxErrorCodes.MODULE_RIGHT_TO_ACCESS_MESSAGE.getCode(),
				TruxErrorCodes.MODULE_RIGHT_TO_ACCESS_MESSAGE.getDescription()));
		return list;

	}

	public List<UserDetail> getUserList() {
		// Session session2 = sessionFactory.openSession();
		// List<UserDetail> as = null;
		//
		// String query = "SELECT ud.`id`, CONCAT(ud.`firstname`,'
		// ',ud.`lastname`) uname, ud.`email` uemail FROM `userDetails` AS ud
		// WHERE ud.`isActive`=1 ORDER BY uname ASC";
		// as =
		// session2.createSQLQuery(query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		// session2.close();
		//
		// if (as!=null){
		// return as;
		// }
		// else{
		// return as;
		// }

		Session session = this.sessionFactory.openSession();
		List<UserDetail> dList = session.createQuery("FROM UserDetail WHERE isActive=1 ORDER BY firstname ASC").list();

		if (dList.size() > 0) {
			return dList;
		} else {
			return null;
		}
	}

	public List<VehicleType> getVehicleType() {
		Session session = this.sessionFactory.openSession();
		List<VehicleType> statesList = new ArrayList<VehicleType>();
		try {
			SessionImpl sessionImpl = (SessionImpl) session;
			java.sql.Connection connection = sessionImpl.connection();

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from vehicle_type;");
			while (rs.next()) {
				VehicleType dto = new VehicleType();
				dto.setId(rs.getInt("id"));
				dto.setVehicleName(rs.getString("vehicleName"));
				statesList.add(dto);
			}
			connection.close();
			session.clear();
			session.close();
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		try {

			if (statesList != null && !statesList.isEmpty()) {
				return statesList;
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;

	}

	public List<CRFOrder> getAllOrders() {
		Session session = this.sessionFactory.openSession();
		List<CRFOrder> statesList = new ArrayList<CRFOrder>();
		try {
			Session session2 = sessionFactory.openSession();
			List<?> as = null;

			String query = "";

			query += "SELECT ";
			query += "tco.`id`, tco.`crf_deployment_id` crfDeploymentId, tco.`crf_source` crfSource, tco.`crf_destination` crfDestination, tco.`crf_source_vehicle_type` crfSourceVehicleType, cvd.`reporting_time` reportingTime, cvd.`body_type` bodyType ";
			query += "FROM ";
			query += "`trsptr_client_orders` AS tco ";
			query += "LEFT JOIN `client_vehicle_deployment` AS cvd ON tco.`crf_deployment_id`=cvd.`id` ";
			query += "WHERE ";
			query += "tco.`is_published`=0 AND tco.`is_active`=0 AND tco.`crf_deployment_id` IS NOT NULL ";
			query += "ORDER BY tco.`id` DESC;";

			as = session2.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(CRFOrder.class)).list();
			session2.close();

			statesList = (List<CRFOrder>) as;

		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		try {

			if (statesList != null && !statesList.isEmpty()) {
				return statesList;
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;
	}

	public ControllerDAOTracker getFilterOrder(String date) {
		ControllerDAOTracker cdt = new ControllerDAOTracker();
		try {

			Session session = this.sessionFactory.openSession();
			List<?> dList = null;

			String query = "";
			query += "SELECT ";
			query += "tco.`id`, tco.`crf_deployment_id` crfDeploymentId, tco.`crf_source` crfSource, tco.`crf_destination` crfDestination, tco.`crf_source_vehicle_type` crfSourceVehicleType, cvd.`reporting_time` reportingTime, cvd.`body_type` bodyType ";
			query += "FROM ";
			query += "`trsptr_client_orders` AS tco ";
			query += "LEFT JOIN `client_vehicle_deployment` AS cvd ON tco.`crf_deployment_id`=cvd.`id` ";
			query += "WHERE ";
			query += "tco.`is_published`=0 AND tco.`is_active`=0 AND tco.`crf_deployment_id` IS NOT NULL AND DATE(tco.`created_on`)='"
					+ date + "' ";
			query += "ORDER BY tco.`id` DESC;";
			dList = session.createSQLQuery(query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			session.close();

			if (dList != null) {
				cdt.setSuccess(true);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Success");
				cdt.setData(dList);
			} else {
				cdt.setSuccess(false);
				cdt.setErrorCode("100");
				cdt.setErrorMessage("Failure");
			}

			return cdt;

		} catch (Exception er) {
			ControllerDAOTracker cdte = new ControllerDAOTracker();
			cdte.setSuccess(false);
			cdte.setErrorCode("101");
			cdte.setErrorMessage("Failure");
			return cdte;
		}
	}

	public List<VehicleType> getAllVehicle(int parseInt) {

		Session session = this.sessionFactory.openSession();
		List<VehicleType> vehicleList = new ArrayList<VehicleType>();
		try {
			SessionImpl sessionImpl = (SessionImpl) session;
			java.sql.Connection connection = sessionImpl.connection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM vehicle_type where vehicleName=" + parseInt + ";");
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

	public List<VehicleType> getVehicle(String vehicleId) {

		Session session = this.sessionFactory.openSession();
		List<VehicleType> vehicleList = new ArrayList<VehicleType>();
		try {
			SessionImpl sessionImpl = (SessionImpl) session;
			java.sql.Connection connection = sessionImpl.connection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM vehicle_type where vehicleName='" + vehicleId+"'");
			while (rs.next()) {
				VehicleType dto = new VehicleType();
				dto.setId(rs.getInt("id"));
				dto.setVehicleName(rs.getString("vehicleName"));
				vehicleList.add(dto);
				System.out.println("ID="+dto.getId());
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


}
