package com.trux.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.internal.SessionImpl;
import org.springframework.web.bind.annotation.RequestParam;

import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.DeviceRegistration;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.DriverRegistration;
import com.trux.model.ForgetPassword;
import com.trux.model.UserDetail;
import com.trux.model.UserLogin;
import com.trux.model.VehicleRegistration;

public class RegistrationDAOImpl implements RegistrationDAO {

	public static Map<Integer, String> countryMap = new HashMap<Integer, String>();
	public static Map<Integer, String> cityMap = new HashMap<Integer, String>();
	public static Map<Integer, String> stateMap = new HashMap<Integer, String>();
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	public Map<Integer, String> getAllCountriesMap() {
		if (countryMap.isEmpty()) {
			Session session = sessionFactory.openSession();
			try {
				SessionImpl sessionImpl = (SessionImpl) session;
				java.sql.Connection connection = sessionImpl.connection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM countries;");
				while (rs.next()) {
					countryMap.put(rs.getInt("id"), rs.getString("value"));
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

	public Map<Integer, String> getAllCitiesMap() {
		if (cityMap.isEmpty()) {
			Session session = sessionFactory.openSession();
			try {
				SessionImpl sessionImpl = (SessionImpl) session;
				java.sql.Connection connection = sessionImpl.connection();
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM cities;");
				while (rs.next()) {
					cityMap.put(rs.getInt("city_id"), rs.getString("city"));
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

	public Map<Integer, String> getAllStatesMap() {
		if (stateMap.isEmpty()) {
			Session session = sessionFactory.openSession();
			try {
				SessionImpl sessionImpl = (SessionImpl) session;
				java.sql.Connection connection = sessionImpl.connection();

				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("select * from states;");
				while (rs.next()) {
					stateMap.put(rs.getInt("state_id"), rs.getString("state_name"));
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

	public void saveOrUpdate(Object object) {
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(object);
			tx.commit();
			session.flush();
			session.clear();
			session.close();
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<VehicleRegistration> getRegisteredVehicleList() {
		Session session = sessionFactory.openSession();
		try {
			List<VehicleRegistration> registeredVehicleList = session.createQuery("from VehicleRegistration").list();
			session.flush();
			session.clear();
			session.close();
			return registeredVehicleList;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DriverRegistration> getRegisteredDriverList() {
		Session session = sessionFactory.openSession();
		try {
			List<DriverRegistration> registeredDriverList = session.createQuery("from DriverRegistration").list();
			session.flush();
			session.clear();
			session.close();
			return registeredDriverList;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<VehicleRegistration> getFreeRegisteredVehicleList() {
		Session session = sessionFactory.openSession();
		try {
			List<VehicleRegistration> registeredVehicleList = session
					.createQuery("from VehicleRegistration v where v.vehicleStatus='free'").list();
			session.flush();
			session.clear();
			session.close();
			return registeredVehicleList;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DriverRegistration> getFreeRegisteredDriverList() {
		Session session = sessionFactory.openSession();
		try {
			List<DriverRegistration> registeredDriverList = session
					.createQuery("from DriverRegistration d where d.driverStatus='free'").list();
			session.flush();
			session.clear();
			session.close();
			return registeredDriverList;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public UserDetail getUserDetail(String email) {
		Session session = sessionFactory.openSession();
		try {
			List<UserDetail> userDetail = session.createQuery("from UserDetail u where u.email='" + email + "'").list();
			session.flush();
			session.clear();
			session.close();

			if (userDetail != null && !userDetail.isEmpty()) {
				return userDetail.get(0);
			}
			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserDetail> getUserDetailList() {
		Session session = sessionFactory.openSession();
		try {
			List<UserDetail> userDetail = session.createQuery("from UserDetail order by firstname").list();
			session.flush();
			session.clear();
			session.close();
			if (userDetail != null && !userDetail.isEmpty()) {
				return userDetail;
			}
			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public UserDetail getUserDetails(UserLogin dto) {
		Session session = sessionFactory.openSession();
		try {
			List<UserDetail> userDetail = session
					.createQuery("FROM UserDetail as d where d.firstname='" + dto.getUserName() + "' or d.email='"
							+ dto.getUserName() + "' AND d.password='" + dto.getUserPassword() + "'")
					.list();
			session.flush();
			session.clear();
			session.close();
			if (userDetail != null && !userDetail.isEmpty()) {
				return userDetail.get(0);
			} else {
				return new UserDetail("", "", "", "", "", false);
			}
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public UserDetail getUserDetailsWithGcmId(UserLogin dto) {
		Session session = sessionFactory.openSession();
		try {
			List<UserDetail> userDetail = session.createQuery("FROM UserDetail as d where d.firstname='"
					+ dto.getUserName() + "' OR d.email='" + dto.getUserName() + "' AND d.password='"
					+ dto.getUserPassword() + "' and gcmId='" + dto.getGcmId() + "'").list();
			session.flush();
			session.clear();
			session.close();
			if (userDetail != null && !userDetail.isEmpty()) {
				return userDetail.get(0);
			} else {
				return new UserDetail(TruxErrorCodes.LEAD_AGENTID_LOGIN_EXCEPTION_MESSAGE.getCode(),
						TruxErrorCodes.LEAD_AGENTID_LOGIN_EXCEPTION_MESSAGE.getDescription());
			}
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return new UserDetail(TruxErrorCodes.LEAD_AGENTID_LOGIN_EXCEPTION_MESSAGE.getCode(),
					TruxErrorCodes.LEAD_AGENTID_LOGIN_EXCEPTION_MESSAGE.getDescription() + " becouse "
							+ er.getMessage().toString());
		}
	}

	@SuppressWarnings("unchecked")
	public DriverRegistration getDriverDetailsById(Integer driverId) {
		Session session = sessionFactory.openSession();
		try {
			List<DriverRegistration> driverDetailsList = session
					.createQuery("from DriverRegistration d where d.id=" + driverId).list();
			session.flush();
			session.clear();
			session.close();

			if (driverDetailsList != null && !driverDetailsList.isEmpty()) {
				return driverDetailsList.get(0);
			}
			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public VehicleRegistration getVehicleDetailsById(Integer vehicleId) {
		Session session = sessionFactory.openSession();
		try {
			List<VehicleRegistration> vehicleDetailsList = session
					.createQuery("from VehicleRegistration v where v.id=" + vehicleId).list();
			session.flush();
			session.clear();
			session.close();

			if (vehicleDetailsList != null && !vehicleDetailsList.isEmpty()) {
				return vehicleDetailsList.get(0);
			}
			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public VehicleRegistration getVehicleDetailsByVehicleNumber(String vehicleNumber) {
		Session session = sessionFactory.openSession();
		try {
			List<VehicleRegistration> vehicleDetailsList = session
					.createQuery("from VehicleRegistration v where v.vehicleNumber='" + vehicleNumber
							+ "' OR v.driverMobiles='" + vehicleNumber.trim() + "'")
					.list();
			session.flush();
			session.clear();
			session.close();
			if (vehicleDetailsList != null && !vehicleDetailsList.isEmpty()) {
				String[] monthYear = vehicleDetailsList.get(0).getVehicleModel().split(",");

				if (monthYear != null && monthYear.length > 0) {
					try {
						vehicleDetailsList.get(0).setMonth(monthYear[0]);
						vehicleDetailsList.get(0).setYear(monthYear[1]);
					} catch (Exception er) {
						vehicleDetailsList.get(0).setYear(vehicleDetailsList.get(0).getVehicleModel());
					}
				}

				Integer driverId = vehicleDetailsList.get(0).getDriverId();
				if (driverId != 0) {
					Session sessions = this.sessionFactory.openSession();
					List<DriverRegistration> driverRegList = sessions
							.createQuery("from DriverRegistration Where id=" + driverId).list();
					sessions.flush();
					sessions.clear();
					sessions.close();

					if (driverRegList != null && !driverRegList.isEmpty()) {
						vehicleDetailsList.get(0).setDriverMobile(driverRegList.get(0).getPhoneNumber());
					}
				}
				return vehicleDetailsList.get(0);
			}
			return null;

		} catch (Exception er) {
			System.out.println(er.getMessage().toString());

			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public VehicleRegistration getVehicleDetailsByDriverId(Integer vehicleId) {
		Session session = sessionFactory.openSession();
		try {
			List<VehicleRegistration> vehicleDetailsList = session
					.createQuery("from VehicleRegistration v where v.driverId=" + vehicleId).list();
			session.flush();
			session.clear();
			session.close();

			if (vehicleDetailsList != null && !vehicleDetailsList.isEmpty()) {
				return vehicleDetailsList.get(0);
			}
			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DeviceRegistration> getAllRegisteredDevice() {
		Session session = sessionFactory.openSession();
		try {
			List<DeviceRegistration> registeredDeviceList = session.createQuery("from DeviceRegistration").list();
			session.flush();
			session.clear();
			session.close();
			return registeredDeviceList;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DeviceRegistration> getFreeRegisteredDeviceList() {
		Session session = sessionFactory.openSession();
		try {
			List<DeviceRegistration> registeredDeviceList = session
					.createQuery("from DeviceRegistration d where d.deviceStatus='free'").list();
			session.flush();
			session.clear();
			session.close();
			return registeredDeviceList;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public DeviceRegistration getDeviceDetailsById(Integer deviceId) {
		Session session = sessionFactory.openSession();
		try {
			List<DeviceRegistration> deviceDetail = session
					.createQuery("from DeviceRegistration d where d.id=" + deviceId).list();
			session.flush();
			session.clear();
			session.close();

			if (deviceDetail != null && !deviceDetail.isEmpty()) {
				return deviceDetail.get(0);
			}
			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public DriverDeviceVehicleMapping driverValidation(String driverPhoneNumber, String deviceUniqueId) {
		Session session = sessionFactory.openSession();
		try {
			List<DriverDeviceVehicleMapping> driverDeviceVehicleDetails = session
					.createQuery("from DriverDeviceVehicleMapping d where d.driverPhoneNumber='" + driverPhoneNumber
							+ "' AND d.deviceUUID='" + deviceUniqueId + "'")
					.list();
			session.flush();
			session.clear();
			session.close();

			if (driverDeviceVehicleDetails != null && driverDeviceVehicleDetails.size() > 0) {
				return driverDeviceVehicleDetails.get(0);
			}
			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")

	public DriverDeviceVehicleMapping getDriverDeviceDetail(String driverPhoneNumber) {
		Session session = sessionFactory.openSession();
		try {
			List<DriverDeviceVehicleMapping> driverDeviceVehicleDetails = session
					.createQuery(
							"from DriverDeviceVehicleMapping d where d.driverPhoneNumber='" + driverPhoneNumber + "'")
					.list();
			session.flush();
			session.clear();
			session.close();

			if (driverDeviceVehicleDetails != null && !driverDeviceVehicleDetails.isEmpty()) {
				return driverDeviceVehicleDetails.get(0);
			}

			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")

	public List<DriverDeviceVehicleMapping> getAllAvailableDrivers() {
		Session session = sessionFactory.openSession();
		try {

			List<DriverDeviceVehicleMapping> driverDeviceVehicleDetails = session
					.createQuery("from DriverDeviceVehicleMapping d where d.loginStatus='1' AND d.driverStatus='0'")
					.list();
			session.flush();
			session.clear();
			session.close();

			if (driverDeviceVehicleDetails != null && !driverDeviceVehicleDetails.isEmpty()) {
				return driverDeviceVehicleDetails;
			}

			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")

	public ForgetPassword getEmailByPassKey(String passkey) {
		Session session = sessionFactory.openSession();
		try {

			List<ForgetPassword> forgetPassList = session
					.createQuery("from ForgetPassword f where f.passwordGenKey='" + passkey + "'").list();
			session.flush();
			session.clear();
			session.close();

			if (forgetPassList != null && !forgetPassList.isEmpty()) {
				return forgetPassList.get(0);
			}

			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")

	public List<DriverDeviceVehicleMapping> getAllAvailableDriversByVehicleType(String vehicleType) {
		try {
			Session session = this.sessionFactory.openSession();

			// loginStatus driverStatus

			List<DriverDeviceVehicleMapping> driverDeviceVehicleDetails = session.createQuery(
					"from DriverDeviceVehicleMapping d where d.loginStatus='1' AND d.driverStatus='0' and d.vehicleType='"
							+ vehicleType + "'")
					.list();
			session.flush();
			session.clear();
			session.close();

			if (driverDeviceVehicleDetails != null && !driverDeviceVehicleDetails.isEmpty()) {
				return driverDeviceVehicleDetails;
			}

			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			return null;
		}
	}

	@SuppressWarnings("unchecked")

	public List<DriverDeviceVehicleMapping> getAllAvailableVehicle() {
		try {
			List<DriverDeviceVehicleMapping> vehicleList = new ArrayList<DriverDeviceVehicleMapping>();

			Session session = this.sessionFactory.openSession();
			Query query = session.createQuery(
					"SELECT DISTINCT(d.vehicleType) FROM DriverDeviceVehicleMapping d where d.loginStatus=:loginStatus AND d.driverStatus=:driverStatus order by d.vehicleType desc");
			query.setParameter("loginStatus", 1);
			query.setParameter("driverStatus", 0);
			List<Object> objectList = query.list();
			session.flush();
			session.clear();
			session.close();
			if (objectList != null) {
				for (Object obj : objectList) {
					DriverDeviceVehicleMapping map = new DriverDeviceVehicleMapping();
					// System.out.println(obj.toString());
					map.setVehicleType(obj.toString());
					vehicleList.add(map);
				}
			}
			if (vehicleList != null && vehicleList.size() > 0) {
				return vehicleList;
			} else {
				return null;
			}
		} catch (Exception er) {
			System.out.println(er.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")

	public UserDetail getUserDetailByMobile(String mobile) {
		try {
			Session session = this.sessionFactory.openSession();
			List<UserDetail> userDetail = session
					.createQuery("FROM UserDetail as d where  d.phoneNumber='" + mobile + "'").list();
			session.flush();
			session.clear();
			session.close();
			if (userDetail != null && !userDetail.isEmpty()) {
				return userDetail.get(0);
			}
			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			return null;
		}
	}

	@SuppressWarnings("unchecked")

	public UserDetail getUserDetailByEmail(String email) {
		try {
			Session session = this.sessionFactory.openSession();
			List<UserDetail> userDetail = session.createQuery("FROM UserDetail as d where  d.email='" + email + "'")
					.list();
			session.flush();
			session.clear();
			session.close();
			if (userDetail != null && !userDetail.isEmpty()) {
				return userDetail.get(0);
			}
			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			return null;
		}
	}

	@SuppressWarnings("unchecked")

	public UserDetail saveUserDetail(UserDetail object) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.getTransaction();
			session.save(object);
			tx.begin();
			tx.commit();
			DetachedCriteria maxId = DetachedCriteria.forClass(UserDetail.class).setProjection(Projections.max("id"));
			List<UserDetail> idList = session.createCriteria(UserDetail.class).add(Property.forName("id").eq(maxId))
					.list();
			session.flush();
			session.clear();
			session.close();
			if (idList != null && !idList.isEmpty()) {
				return idList.get(0);
			} else {
				return new UserDetail(TruxErrorCodes.LEAD_AGENTID_CREATION_MESSAGE.getCode(),
						TruxErrorCodes.LEAD_AGENTID_CREATION_MESSAGE.getDescription());
			}
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			return new UserDetail(TruxErrorCodes.LEAD_AGENTID_CREATION_ERROR_MESSAGE.getCode(),
					TruxErrorCodes.LEAD_AGENTID_CREATION_ERROR_MESSAGE.getDescription() + " "
							+ er.getMessage().toString());
		}

	}

	@SuppressWarnings("unchecked")

	public DriverRegistration registrationDriverByAPI(DriverRegistration dto) {

		try {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			try {
				dto.setPhoneNumber(dto.getPhoneNumber());

				dto.setFirstName(dto.getDriverName());
				dto.setAddress(dto.getPermanentAddress());
				dto.setIsActive(1);
				session.save(dto);
				tx.commit();
			} catch (Exception e) {
				List<DriverRegistration> lists = new ArrayList<DriverRegistration>();
				DriverRegistration dtoa = new DriverRegistration();
				dtoa.setId(0);
				lists.add(dtoa);
				return lists.get(0);
			}

			DetachedCriteria maxId = DetachedCriteria.forClass(DriverRegistration.class)
					.setProjection(Projections.max("id"));
			List<DriverRegistration> idList = session.createCriteria(DriverRegistration.class)
					.add(Property.forName("id").eq(maxId)).list();
			session.flush();
			session.clear();
			session.close();
			if (idList != null && !idList.isEmpty()) {
				return idList.get(0);
			} else {
				return new DriverRegistration(TruxErrorCodes.DRIVER_REGISTRATION_ERROR_MESSAGE.getCode(),
						TruxErrorCodes.DRIVER_REGISTRATION_ERROR_MESSAGE.getDescription());
			}
		} catch (Exception er) {
			return new DriverRegistration(TruxErrorCodes.DRIVER_REGISTRATION_ERROR_MESSAGE.getCode(),
					TruxErrorCodes.DRIVER_REGISTRATION_ERROR_MESSAGE.getDescription() + " "
							+ er.getMessage().toString());
		}

	}

	public DriverRegistration updateRegistrationDriverById(DriverRegistration dto) {

		Session session = this.sessionFactory.openSession();
		try {
			DriverRegistration obj = (DriverRegistration) session.get(DriverRegistration.class, dto.getId());

			if (dto.getHubId() != null && !dto.getHubId().equals(""))
				obj.setHubId(dto.getHubId());
			if (dto.getCity() != null && !dto.getCity().equals(""))
				obj.setCity(dto.getCity());
			if (dto.getState() != null && !dto.getState().equals(""))
				obj.setState(dto.getState());
			if (dto.getCountry() != null && !dto.getCountry().equals(""))
				obj.setCountry(dto.getCountry());
			if (dto.getGender() != null && !dto.getGender().equals(""))
				obj.setGender(dto.getGender());
			if (dto.getDriverName() != null && !dto.getDriverName().equals(""))
				obj.setDriverName(dto.getDriverName());
			if (dto.getDrivingExperience() != null && !dto.getDrivingExperience().equals(""))
				obj.setDrivingExperience(dto.getDrivingExperience());
			if (dto.getLocalAddress() != null && !dto.getLocalAddress().equals(""))
				obj.setLocalAddress(dto.getLocalAddress());
			if (dto.getPermanentAddress() != null && !dto.getPermanentAddress().equals(""))
				obj.setPermanentAddress(dto.getPermanentAddress());
			if (dto.getStandDetails() != null && !dto.getStandDetails().equals(""))
				obj.setStandDetails(dto.getStandDetails());
			if (dto.getAssosiatedBy() != null && !dto.getAssosiatedBy().equals(""))
				obj.setAssosiatedBy(dto.getAssosiatedBy());
			if (dto.getCluster() != null && !dto.getCluster().equals(""))
				obj.setCluster(dto.getCluster());
			if (dto.getSubOrgClient() != null && !dto.getSubOrgClient().equals(""))
				obj.setSubOrgClient(dto.getSubOrgClient());
			if (dto.getDstatus() != null && !dto.getDstatus().equals(""))
				obj.setDstatus(dto.getDstatus());
			if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().equals(""))
				obj.setPhoneNumber(dto.getPhoneNumber());
			if (dto.getDriverName() != null && !dto.getDriverName().equals(""))
				obj.setFirstName(dto.getDriverName());
			if (dto.getPermanentAddress() != null && !dto.getPermanentAddress().equals(""))
				obj.setAddress(dto.getPermanentAddress());
			if (dto.getAgentId() != null && !dto.getAgentId().equals(""))
				obj.setAgentId(dto.getAgentId());
			if (dto.getDriverStatus() != null && !dto.getDriverStatus().equals(""))
				obj.setDriverStatus(dto.getDriverStatus());
			if (dto.getDriverReportingTime() != null && !dto.getDriverReportingTime().equals(""))
				obj.setDriverReportingTime(dto.getDriverReportingTime());

			if (dto.getImageOfDL() != null && !dto.getImageOfDL().equals(""))
				obj.setImageOfDL(dto.getImageOfDL());
			if (dto.getImageOfpanCard() != null && !dto.getImageOfpanCard().equals(""))
				obj.setImageOfpanCard(dto.getImageOfpanCard());
			if (dto.getImageOfpoliceVerificationReport() != null
					&& !dto.getImageOfpoliceVerificationReport().equals(""))
				obj.setImageOfpoliceVerificationReport(dto.getImageOfpoliceVerificationReport());
			if (dto.getDriverPhoto() != null && !dto.getDriverPhoto().equals(""))
				obj.setDriverPhoto(dto.getDriverPhoto());
			if (dto != null && dto.getIsActive() != 0)
				obj.setIsActive(dto.getIsActive());
			if (dto.getDriver_photo_thum() != null && !dto.getDriver_photo_thum().equals(""))
				obj.setDriver_photo_thum(dto.getDriver_photo_thum());

			Session session1 = this.sessionFactory.openSession();
			session1.get(DriverRegistration.class, dto.getId());
			Transaction tx = session1.beginTransaction();
			session1.merge(obj);
			tx.commit();
			session1.clear();
			session1.close();
			return obj;

		} catch (Exception e) {
			return new DriverRegistration(TruxErrorCodes.DRIVER_REGISTRATION_ERROR_MESSAGE.getCode(),
					TruxErrorCodes.DRIVER_REGISTRATION_ERROR_MESSAGE.getDescription() + " "
							+ e.getMessage().toString());
		}

	}

	@SuppressWarnings("unchecked")

	public VehicleRegistration registrationVehicleByAPI(VehicleRegistration dto) {

		try {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(dto);
			tx.commit();

			DetachedCriteria maxId = DetachedCriteria.forClass(VehicleRegistration.class)
					.setProjection(Projections.max("id"));
			List<VehicleRegistration> idList = session.createCriteria(VehicleRegistration.class)
					.add(Property.forName("id").eq(maxId)).list();
			session.flush();
			session.clear();
			session.close();
			if (idList != null && !idList.isEmpty()) {
				return idList.get(0);
			} else {
				return new VehicleRegistration(TruxErrorCodes.VEHCLE_REGISTRATION_ERROR_MESSAGE.getCode(),
						TruxErrorCodes.VEHCLE_REGISTRATION_ERROR_MESSAGE.getDescription());
			}
		} catch (Exception er) {
			return new VehicleRegistration(TruxErrorCodes.VEHCLE_REGISTRATION_ERROR_MESSAGE.getCode(),
					TruxErrorCodes.VEHCLE_REGISTRATION_ERROR_MESSAGE.getDescription());
		}

	}

	public VehicleRegistration updateVehicleByAPI(VehicleRegistration dto) {
		try {
			Session session = this.sessionFactory.openSession();

			VehicleRegistration obj = (VehicleRegistration) session.get(VehicleRegistration.class, dto.getId());
			if (dto.getAgentId() != null && !dto.getAgentId().equals("")) {
				obj.setAgentId(dto.getAgentId());
			}
			if (dto.getDriverId() != null && !dto.getDriverId().equals("")) {
				obj.setDriverId(dto.getDriverId());
			}
			if (dto.getFuelType() != null && !dto.getFuelType().equals("")) {
				obj.setFuelType(dto.getFuelType());
			}
			if (dto.getKmReading() != null && !dto.getKmReading().equals("")) {
				obj.setKmReading(dto.getKmReading());
			}
			if (dto.getOwnerName() != null && !dto.getOwnerName().equals("")) {
				obj.setOwnerName(dto.getOwnerName());
			}
			if (dto.getOwnerAddress() != null && !dto.getOwnerAddress().equals("")) {
				obj.setOwnerAddress(dto.getOwnerAddress());
			}
			if (dto.getOwnerName() != null && !dto.getOwnerName().equals("")) {
				obj.setOwnerName(dto.getOwnerName());
			}
			if (dto.getOwnerPhoneNumber() != null && !dto.getOwnerPhoneNumber().equals("")) {
				obj.setOwnerPhoneNumber(dto.getOwnerPhoneNumber());
			}
			if (dto.getVehicleModel() != null && !dto.getVehicleModel().equals("")) {
				obj.setVehicleModel(dto.getVehicleModel());
			}
			if (dto.getVehicleNumber() != null && !dto.getVehicleNumber().equals("")) {
				obj.setVehicleNumber(dto.getVehicleNumber());
			}
			if (dto.getVehicleStatus() != null && !dto.getVehicleStatus().equals("")) {
				obj.setVehicleStatus(dto.getVehicleStatus());
			}
			if (dto.getVehicleType() != null && !dto.getVehicleType().equals("")) {
				obj.setVehicleType(dto.getVehicleType());
			}
			if (dto.getVehicleBody() != null && !dto.getVehicleBody().equals("")) {
				obj.setVehicleBody(dto.getVehicleBody());
			}
			if (dto.getCountryId() != null && !dto.getCountryId().equals("")) {
				obj.setCountryId(dto.getCountryId());
			}
			if (dto.getStateId() != null && !dto.getStateId().equals("")) {
				obj.setStateId(dto.getStateId());
			}
			if (dto.getCityId() != null && !dto.getCityId().equals("")) {
				obj.setCityId(dto.getCityId());
			}
			if (dto.getClientId() != null && !dto.getClientId().equals("")) {
				obj.setClientId(dto.getClientId());
			}
			if (dto.getClusterId() != null && !dto.getClusterId().equals("")) {
				obj.setClusterId(dto.getClusterId());
			}
			if (dto.getHubId() != null && !dto.getHubId().equals("")) {
				obj.setHubId(dto.getHubId());
			}
			if (dto.getStandId() != null && !dto.getStandId().equals("")) {
				obj.setStandId(dto.getStandId());
			}
			if (dto.getSubclientId() != null && !dto.getSubclientId().equals("")) {
				if (dto.getSubclientId() != 0) {
					obj.setSubclientId(dto.getSubclientId());
				}
			}

			if (dto.getDriverMobiles() != null && !dto.getDriverMobiles().equals("")) {
				obj.setDriverMobiles(dto.getDriverMobiles());
			}
			if (dto.getAccountHolderName() != null && !dto.getAccountHolderName().equals("")) {
				obj.setAccountHolderName(dto.getAccountHolderName());
			}
			if (dto.getBankName() != null && !dto.getBankName().equals("")) {
				obj.setBankName(dto.getBankName());
			}

			if (dto.getPanNumber() != null && !dto.getPanNumber().equals("")) {
				obj.setPanNumber(dto.getPanNumber());
			}
			if (dto.getAccountNumber() != null && !dto.getAccountNumber().equals("")) {
				obj.setAccountNumber(dto.getAccountNumber());
			}

			if (dto.getIfscCode() != null && !dto.getIfscCode().equals("")) {
				obj.setIfscCode(dto.getIfscCode());
			}
			if (dto.getIsBarcodeIssued() != null && !dto.getIsBarcodeIssued().equals("")) {
				obj.setIsBarcodeIssued(dto.getIsBarcodeIssued());
			}
			if (dto != null && dto.getIsActive() != 0) {
				obj.setIsActive(dto.getIsActive());
			}

			if (dto.getImage_of_insurance() != null && !dto.getImage_of_insurance().equals("")) {
				obj.setImage_of_insurance(dto.getImage_of_insurance());
			}
			if (dto.getImage_of_permit_vehicle() != null && !dto.getImage_of_permit_vehicle().equals("")) {
				obj.setImage_of_permit_vehicle(dto.getImage_of_permit_vehicle());
			}
			if (dto.getImage_of_pollution() != null && !dto.getImage_of_pollution().equals("")) {
				obj.setImage_of_pollution(dto.getImage_of_pollution());
			}
			if (dto.getImage_of_rc() != null && !dto.getImage_of_rc().equals("")) {
				obj.setImage_of_rc(dto.getImage_of_rc());
			}
			if (dto.getInsuranceExpiryDate() != null && !dto.getInsuranceExpiryDate().equals("")) {
				obj.setInsuranceExpiryDate(dto.getInsuranceExpiryDate());
			}

			obj.setModifiedBy(dto.getModifiedBy());
			obj.setModifiedDate(new Date());

			Session session1 = this.sessionFactory.openSession();
			session1.get(VehicleRegistration.class, obj.getId());
			Transaction tx = session1.beginTransaction();
			VehicleRegistration ojvs = (VehicleRegistration) session1.merge(obj);
			tx.commit();
			session1.clear();
			session1.close();
			return ojvs;

		} catch (Exception e) {
			return new VehicleRegistration(TruxErrorCodes.DRIVER_REGISTRATION_ERROR_MESSAGE.getCode(),
					TruxErrorCodes.DRIVER_REGISTRATION_ERROR_MESSAGE.getDescription() + " "
							+ e.getMessage().toString());
		}

	}

	@SuppressWarnings("unchecked")

	public UserDetail getUserDetailsByAgentId(String dto) {

		try {
			Session session = this.sessionFactory.openSession();
			List<UserDetail> userDetail = session.createQuery("FROM UserDetail as d where d.id=" + dto.trim() + "")
					.list();
			session.flush();
			session.clear();
			session.close();
			if (userDetail != null && !userDetail.isEmpty()) {
				return userDetail.get(0);
			}
			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			return null;
		}
	}

	public String changePasswords(UserDetail dto) {
		int status = 0;
		try {
			Session session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			java.sql.Connection connection = sessionImpl.connection();
			PreparedStatement ps = connection.prepareStatement("UPDATE userDetails  SET PASSWORD='" + dto.getPassword()
					+ "' WHERE email='" + dto.getEmail() + "';");// AND
																	// phonenumber='"+dto.getPhoneNumber()+"';");
			status = ps.executeUpdate();
			connection.close();
			session.flush();
			session.clear();
			session.close();
			if (status == 0) {
				return " Cant change your passwords because your user name  might be wrong !";
			} else if (status >= 1) {
				return " Your password has been changed ! ";
			}

		} catch (Exception er) {
			er.printStackTrace();
			return " Cant change your passwords its " + er.getMessage().toString();
		}
		return " Cant change your passwords because your user name or mobile might be wrong !";
	}

	@SuppressWarnings("unchecked")

	public DriverRegistration getDriverDetail(String driverPhoneNumber) {
		Session session = this.sessionFactory.openSession();
		try {
			List<DriverRegistration> driverRegistrationList = session.createQuery(
					"from DriverRegistration d where d.phoneNumber='" + driverPhoneNumber + "' Order By firstName")
					.list();
			session.flush();
			session.clear();
			session.close();

			if (driverRegistrationList != null && !driverRegistrationList.isEmpty()) {
				return driverRegistrationList.get(0);
			}

			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")

	public List<DriverRegistration> getDriverDetailToEdit() {
		Session session = this.sessionFactory.openSession();
		try {
			List<DriverRegistration> driverRegistrationList = session
					.createQuery("from DriverRegistration Order By firstName").list();
			session.flush();
			session.clear();
			session.close();

			if (driverRegistrationList != null && !driverRegistrationList.isEmpty()) {
				return driverRegistrationList;
			}

			return null;
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

	@SuppressWarnings("unchecked")

	public List<DriverRegistration> getRegisteredDriverList(String bySubClient) {
		try {
			Session session = this.sessionFactory.openSession();
			List<DriverRegistration> driverRegistrationList = session
					.createQuery("Select d From DriverRegistration d where d.subOrgClient='" + bySubClient.trim()
							+ "' Order By firstName")
					.list();
			session.flush();
			session.clear();
			session.close();
			if (driverRegistrationList != null && driverRegistrationList.size() > 0) {
				return driverRegistrationList;
			} else {
				return null;
			}
		} catch (Exception er) {
			System.out.println(er.getMessage());
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<DriverRegistration> getRegisteredDriverListBySubOrg(String subOrgClient) {
		try {
			Session session = this.sessionFactory.openSession();
			List<DriverRegistration> driverRegistrationList = session
					.createQuery("Select d From DriverRegistration d where d.subOrgClient='" + subOrgClient.trim()
							+ "' Order By firstName")
					.list();
			session.flush();
			session.clear();
			session.close();
			if (driverRegistrationList != null && driverRegistrationList.size() > 0) {
				return driverRegistrationList;
			} else {
				return null;
			}
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			return null;
		}

	}

	public void saveOrUpdateDDVM(DriverDeviceVehicleMapping dto) {
		DriverDeviceVehicleMapping obj = null;

		try {
			if (dto.getId() != null) {
				Session session = this.sessionFactory.openSession();

				obj = (DriverDeviceVehicleMapping) session.get(DriverDeviceVehicleMapping.class, dto.getId());
				if (obj != null) {
					if (dto.getDeviceId() != null && !dto.getDeviceId().equals(""))
						obj.setDeviceId(dto.getDeviceId());
					if (dto.getDriverId() != null && !dto.getDriverId().equals(""))
						obj.setDriverId(dto.getDriverId());
					if (dto.getVehicleId() != null && !dto.getVehicleId().equals(""))
						obj.setVehicleId(dto.getVehicleId());
					if (dto.getVehicleNumber() != null && !dto.getVehicleNumber().equals(""))
						obj.setVehicleNumber(dto.getVehicleNumber());
					if (dto.getDeviceUUID() != null && !dto.getDeviceUUID().equals(""))
						obj.setDeviceUUID(dto.getDeviceUUID());
					if (dto.getDriver_apk_version() != null && !dto.getDriver_apk_version().equals(""))
						obj.setDriver_apk_version(dto.getDriver_apk_version());
					if (dto.getApkUrl() != null && !dto.getApkUrl().equals(""))
						obj.setApkUrl(dto.getApkUrl());
					if (dto.getDriverLoginDate() != null && !dto.getDriverLoginDate().equals(""))
						obj.setDriverLoginDate(dto.getDriverLoginDate());
					if (dto.getDriverLoginDurationTime() != null && !dto.getDriverLoginDurationTime().equals(""))
						obj.setDriverLoginDurationTime(dto.getDriverLoginDurationTime());

					if (dto.getVehicleType() != null && !dto.getVehicleType().equals(""))
						obj.setVehicleType(dto.getVehicleType());
					if (dto.getDriverLoginTime() != null && !dto.getDriverLoginTime().equals(""))
						obj.setDriverLoginTime(dto.getDriverLoginTime());
					if (dto.getDriverLogoutDate() != null && !dto.getDriverLogoutDate().equals("")) {
						obj.setDriverLogoutDate(dto.getDriverLogoutDate());
					}
					if (dto.getDriverLogoutTime() != null && !dto.getDriverLogoutTime().equals("")) {
						obj.setDriverLogoutTime(dto.getDriverLogoutTime());
					}
					if (dto.getDriverName() != null && !dto.getDriverName().equals("")) {
						obj.setDriverName(dto.getDriverName());
					}
					if (dto.getDriverPhoneNumber() != null && !dto.getDriverPhoneNumber().equals("")) {
						obj.setDriverPhoneNumber(dto.getDriverPhoneNumber());
					}
					if (dto.getDriverStatus() != null && !dto.getDriverStatus().equals("")) {
						obj.setDriverStatus(dto.getDriverStatus());
					}
					if (dto.getDstatus() != null && !dto.getDstatus().equals("")) {
						obj.setDstatus(dto.getDstatus());
					}
					if (dto.getLastLoginTime() != null && !dto.getLastLoginTime().equals("")) {
						obj.setLastLoginTime(dto.getLastLoginTime());
					}
					if (dto.getLoginStatus() != null && !dto.getLoginStatus().equals("")) {
						obj.setLoginStatus(dto.getLoginStatus());
					}

					if (dto.getSubClientId() != null && !dto.getSubClientId().equals("")) {
						if (dto.getSubClientId() != 0) {
							obj.setSubClientId(dto.getSubClientId());
						}
					}

					if (dto.getVehicleId() != null && !dto.getVehicleId().equals("")) {
						obj.setVehicleId(dto.getVehicleId());
					}

					Session session1 = this.sessionFactory.openSession();
					session1.get(DriverDeviceVehicleMapping.class, dto.getId());
					Transaction tx = session1.beginTransaction();
					DriverDeviceVehicleMapping ojvs = (DriverDeviceVehicleMapping) session1.merge(obj);
					tx.commit();
					session1.clear();
					session1.close();
				}
			} else if (obj == null) {
				Session sessions = sessionFactory.openSession();
				try {
					Transaction txs = sessions.beginTransaction();
					sessions.saveOrUpdate(dto);
					txs.commit();
					sessions.flush();
					sessions.clear();
					sessions.close();
				} catch (Exception er) {
					System.out.println(er.getMessage().toString());
					sessions.close();
				}
			}
		} catch (Exception er) {
			er.printStackTrace();
		}
	}
}
