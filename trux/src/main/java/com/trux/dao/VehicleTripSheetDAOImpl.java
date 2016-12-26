package com.trux.dao;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.AssignedStands;
import com.trux.model.VehicleTripSheet;
import com.trux.model.VehicleTripsheetDrops;
import com.trux.utils.AWSS3Uploader;

public class VehicleTripSheetDAOImpl implements VehicleTripSheetDAO {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	
	public VehicleTripSheet saveVehicleTripSheet(VehicleTripSheet dto) {

		Session session = sessionFactory.openSession();
		List<VehicleTripSheet> objList = session.createQuery(
				"FROM VehicleTripSheet WHERE clientId=" + dto.getClientId()
						+ " AND subClientId=" + dto.getSubClientId()
						+ " AND vehicleNo='" + dto.getVehicleNo()
						+ "' AND tripDate='" + dto.getTripDates() + "'").list();
		session.close();
		if (objList != null && objList.size() > 0) {
			try {
				for (VehicleTripSheet at : objList) {
					if (dto != null && dto.getClientId() != null) {
						if (!dto.getClientId().equals(""))
							at.setClientId(dto.getClientId());
					}
					if (dto != null && dto.getSubClientId() != null) {
						if (!dto.getSubClientId().equals(""))
							at.setSubClientId(dto.getSubClientId());
					}
					if (dto != null && dto.getVehicleNo() != null) {
						if (!dto.getVehicleNo().equals(""))
							at.setVehicleNo(dto.getVehicleNo());
					}
					if (dto != null && dto.getTripDate() != null) {
						if (!dto.getTripDate().equals(""))
							at.setTripDate(dto.getTripDate());
					}
					if (dto != null && dto.getModifiedBy() != null) {
						if (!dto.getModifiedBy().equals(0))
							at.setModifiedBy(dto.getModifiedBy());
					}
					if (dto != null && dto.getClientOrderNo() != null) {
						at.setClientOrderNo(dto.getClientOrderNo());
					}
					if (dto != null && dto.getOpeningKm() != null) {
						at.setOpeningKm(dto.getOpeningKm());
					}
					if (dto != null && dto.getOpeningDateTime() != null) {
						at.setOpeningDateTime(dto.getOpeningDateTime());
					}
					if (dto != null && dto.getClosingKm() != null) {
						at.setClosingKm(dto.getClosingKm());
					}
					if (dto != null && dto.getClosingDateTime() != null) {
						at.setClosingDateTime(dto.getClosingDateTime());
					}
					if (dto != null && dto.getScannedTripSheetS3Url() != null) {
						if (!dto.getScannedTripSheetS3Url().equals("")) {
							at.setScannedTripSheetS3Url(dto
									.getScannedTripSheetS3Url());
						}
					}
					if (dto != null && dto.getSignSecurityName() != null) {
						if (!dto.getSignSecurityName().equals(""))
							at.setSignSecurityName(dto.getSignSecurityName());
					}
					if (dto != null && dto.getToll() != null) {
						if (!dto.getToll().equals("")) {
							at.setToll(dto.getToll());
						}
					}
					if (dto != null && dto.getParking() != null) {
						if (!dto.getParking().equals("")) {
							at.setParking(dto.getParking());
						}
					}
					if (dto != null && dto.getChallan() != null) {
						if (!dto.getChallan().equals("")) {
							at.setChallan(dto.getChallan());
						}
					}
					if (dto != null && dto.getNgt() != null) {
						if (!dto.getNgt().equals("")) {
							at.setNgt(dto.getNgt());
						}
					}
					if (dto != null && dto.getOther() != null) {
						if (!dto.getOther().equals("")) {
							at.setOther(dto.getOther());
						}
					}
					if (dto != null && dto.getFlag() != null) {
						if (dto.getFlag() != 0) {
							at.setFlag(dto.getFlag());
						}
					}
					if (dto != null && dto.getModifiedBy() != null) {
						if (dto.getModifiedBy() != 0) {
							at.setModifiedBy(dto.getModifiedBy());
						}
					}
					at.setModifiedDate(new Date());
					Session session1 = this.sessionFactory.openSession();
					session1.get(VehicleTripSheet.class, at.getId());
					Transaction txs = session1.beginTransaction();
					VehicleTripSheet dts = (VehicleTripSheet) session1
							.merge(at);
					txs.commit();
					session1.close();
					dts.setErrorCode("200");
					dts.setErrorMessage("Updated successfully");
					return dts;

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
				DetachedCriteria maxId = DetachedCriteria.forClass(
						VehicleTripSheet.class).setProjection(
						Projections.max("id"));
				List<VehicleTripSheet> cmdList = sessions
						.createCriteria(VehicleTripSheet.class)
						.add(Property.forName("id").eq(maxId)).list();
				sessions.clear();
				sessions.close();
				if (cmdList != null && cmdList.size() > 0) {
					VehicleTripSheet dts = cmdList.get(0);
					dts.setErrorCode("200");
					dts.setErrorMessage("Save successfully");
					return dts;
				} else {
					VehicleTripSheet dts = cmdList.get(0);
					dts.setErrorCode("400");
					dts.setErrorMessage("Your request does not processed !");
					return dts;
				}
			} catch (Exception er) {

				er.printStackTrace();
				VehicleTripSheet dts = new VehicleTripSheet();
				dts.setErrorCode("400");
				dts.setErrorMessage("Your request does not processed !");
				return dts;
			}
		}
		VehicleTripSheet dts = new VehicleTripSheet();
		dts.setErrorCode("400");
		dts.setErrorMessage("Your request does not processed !");
		return dts;

	}
	
	
	public VehicleTripSheet uploadTripSheet(VehicleTripSheet dto) {

		Session session = sessionFactory.openSession();
		List<VehicleTripSheet> objList = session.createQuery(
				"FROM VehicleTripSheet WHERE clientId=" + dto.getClientId()
						+ " AND subClientId=" + dto.getSubClientId()
						+ " AND vehicleNo='" + dto.getVehicleNo()
						+ "' AND tripDate='" + dto.getTripDates() + "'").list();
		session.close();
		if (objList != null && objList.size() > 0) {
			try {
				for (VehicleTripSheet at : objList) {
					if (dto != null && dto.getClientId() != null) {
						if (!dto.getClientId().equals(""))
							at.setClientId(dto.getClientId());
					}
					if (dto != null && dto.getSubClientId() != null) {
						if (!dto.getSubClientId().equals(""))
							at.setSubClientId(dto.getSubClientId());
					}
					if (dto != null && dto.getVehicleNo() != null) {
						if (!dto.getVehicleNo().equals(""))
							at.setVehicleNo(dto.getVehicleNo());
					}
					if (dto != null && dto.getTripDate() != null) {
						if (!dto.getTripDate().equals(""))
							at.setTripDate(dto.getTripDate());
					}
					if (dto != null && dto.getModifiedBy() != null) {
						if (!dto.getModifiedBy().equals(0))
							at.setModifiedBy(dto.getModifiedBy());
					}
					if (dto != null && dto.getScannedTripSheetS3Url() != null) {
						if (!dto.getScannedTripSheetS3Url().equals("")) {
							at.setScannedTripSheetS3Url(dto
									.getScannedTripSheetS3Url());
						}
					}
					if (dto != null && dto.getModifiedBy() != null) {
						if (dto.getModifiedBy() != 0) {
							at.setModifiedBy(dto.getModifiedBy());
						}
					}
					if (dto != null && dto.getFlag() != null) {
						if (dto.getFlag() != 0) {
							at.setFlag(dto.getFlag());
						}
					}
					at.setModifiedDate(new Date());
					Session session1 = this.sessionFactory.openSession();
					session1.get(VehicleTripSheet.class, at.getId());
					Transaction txs = session1.beginTransaction();
					VehicleTripSheet dts = (VehicleTripSheet) session1.merge(at);
					txs.commit();
					session1.close();
					dts.setErrorCode("200");
					dts.setErrorMessage("Updated successfully");
					return dts;

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
				DetachedCriteria maxId = DetachedCriteria.forClass(
						VehicleTripSheet.class).setProjection(
						Projections.max("id"));
				List<VehicleTripSheet> cmdList = sessions
						.createCriteria(VehicleTripSheet.class)
						.add(Property.forName("id").eq(maxId)).list();
				sessions.clear();
				sessions.close();
				if (cmdList != null && cmdList.size() > 0) {
					VehicleTripSheet dts = cmdList.get(0);
					dts.setErrorCode("100");
					dts.setErrorMessage("Save successfully");
					return dts;
				} else {
					VehicleTripSheet dts = cmdList.get(0);
					dts.setErrorCode("400");
					dts.setErrorMessage("Your request does not processed !");
					return dts;
				}
			} catch (Exception er) {

				er.printStackTrace();
					VehicleTripSheet dts = new VehicleTripSheet();
				dts.setErrorCode("400");
				dts.setErrorMessage("Your request does not processed !");
				return dts;
			}
		}
		VehicleTripSheet dts = new VehicleTripSheet();
		dts.setErrorCode("400");
		dts.setErrorMessage("Your request does not processed !");
		return dts;

	}

	
	public VehicleTripSheet updateVehicleTripSheet(VehicleTripSheet dto) {

		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<VehicleTripSheet> getVehicleTripSheet(VehicleTripSheet dto) {

		Session session = sessionFactory.openSession();
		List<VehicleTripSheet> objList = null;
		if (dto.getClientOrderNo() == null) {
			objList = session.createQuery(
					"FROM VehicleTripSheet WHERE clientId=" + dto.getClientId()
							+ " AND subClientId='" + dto.getSubClientId()
							+ "' AND vehicleNo='" + dto.getVehicleNo()
							+ "' AND tripDate='" + dto.getTripDates() + "'")
					.list();
		} else if (dto.getClientOrderNo() != null) {
			if (!dto.getClientOrderNo().equals("")) {
				objList = session.createQuery(
						"FROM VehicleTripSheet WHERE clientOrderNo="
								+ dto.getClientOrderNo()).list();
			}
		}

		session.close();
		if (objList != null && objList.size() > 0) {
			for (int i = 0; i < objList.size(); i++) {
				VehicleTripSheet at = objList.get(i);
				URL url = AWSS3Uploader.s3UrGenerator(at
						.getScannedTripSheetS3Url());
				System.out.println("URL :" + url);
				objList.get(i).setScannedTripSheetS3Url("" + url);
				Session sessions = sessionFactory.openSession();
				List<VehicleTripsheetDrops> vtdList = sessions.createQuery(
						"FROM VehicleTripsheetDrops WHERE trip_id="
								+ at.getId()).list();
				sessions.close();
				if (vtdList != null && vtdList.size() > 0) {
					objList.get(i).setVehicleTripDropList(vtdList);
				}
			}
			return objList;
		}
		return objList;

	}
	
	
	public List getAssignedStand(Integer userId) {
		Session session = sessionFactory.openSession();
		List as = null;
		
		String query="SELECT CM.name standName, GROUP_CONCAT(CSM.subname) subStandName FROM client_truxuser_mapping AS CTM LEFT JOIN client_master AS CM ON CTM.clientid=CM.idClientMaster LEFT JOIN client_sub_master AS CSM ON CTM.clientsubid=CSM.idClientSubMaster WHERE CTM.userid=42 GROUP BY CM.name";
		as = session.createSQLQuery(query).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		session.close();
		if(as != null && as.size() >0){
//			for (int i = 0; i < as.size(); i++){
//				as.get(i).setErrorCode(100);
//				as.get(i).setErrorMessage("Success");
//				
//				
//			}
			
			return as;
		}
		
		return as;
	}
	
	
	public List<VehicleTripSheet> getVehicleTripSheetAPI(VehicleTripSheet dto) {

		Session session = sessionFactory.openSession();
		List<VehicleTripSheet> objList = null;
		if (dto.getClientOrderNo() == null) {
			objList = session.createQuery(
					"FROM VehicleTripSheet WHERE clientId=" + dto.getClientId()
							+ " AND subClientId='" + dto.getSubClientId()
							+ "' AND vehicleNo='" + dto.getVehicleNo()
							+ "' AND tripDate='" + dto.getTripDates() + "'")
					.list();
		} else if (dto.getClientOrderNo() != null) {
			if (!dto.getClientOrderNo().equals("")) {
				objList = session.createQuery(
						"FROM VehicleTripSheet WHERE clientOrderNo="
								+ dto.getClientOrderNo()).list();
			}
		}

		session.close();
		if (objList != null && objList.size() > 0) {
			for (int i = 0; i < objList.size(); i++) {
				VehicleTripSheet at = objList.get(i);
				/*URL url = AWSS3Uploader.getSignedURL(at
						.getScannedTripSheetS3Url());
				System.out.println("URL :" + url);
				objList.get(i).setScannedTripSheetS3Url("" + url);*/
				Session sessions = sessionFactory.openSession();
				List<VehicleTripsheetDrops> vtdList = sessions.createQuery(
						"FROM VehicleTripsheetDrops WHERE trip_id="
								+ at.getId()).list();
				sessions.close();
				if (vtdList != null && vtdList.size() > 0) {
					objList.get(i).setVehicleTripDropList(vtdList);
				}
			}
			return objList;
		} else {
			VehicleTripSheet vts = new VehicleTripSheet();
			vts.setErrorCode("101");
			vts.setErrorMessage("No record exist.");
			
			objList.add(vts);
			
			return objList;
		}

	}
	

	public List<VehicleTripSheet> getVehicleDetailList(int clientId,
			int subClientId) {
		Session sessions = sessionFactory.openSession();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT v.id,v.driver_id ,v.client_id,v.subclient_id,v.vehicle_number,d.driverName,d.phone_number ");
		sql.append("FROM vehicle_registration v INNER JOIN driver_registration d ON v.driver_id=d.id WHERE v.client_id="
				+ clientId
				+ " AND v.subclient_id="
				+ subClientId
				+ " AND v.is_active=1;");
		try {
			List<VehicleTripSheet> list = null;
			@SuppressWarnings("unchecked")
			List<Object[]> idList = sessions.createSQLQuery(sql.toString())
					.list();
			sessions.flush();
			sessions.clear();
			sessions.close();
			if (idList != null && idList.size() > 0) {
				list = new ArrayList<VehicleTripSheet>();
				for (Object[] obj : idList) {
					VehicleTripSheet dt = new VehicleTripSheet();

					if (obj[0] != null) {
						dt.setId(new Integer(obj[0].toString()));
					}
					if (obj[1] != null) {
						dt.setDriverId(new Integer(obj[1].toString()));
					}
					if (obj[2] != null) {
						dt.setClientId(new Integer(obj[2].toString()));
					}
					if (obj[3] != null) {
						dt.setSubClientId(new Integer(obj[3].toString()));
					}
					if (obj[4] != null) {
						dt.setVehicleNo(obj[4].toString());
					}
					if (obj[5] != null) {
						dt.setDriverName(obj[5].toString());
					}
					if (obj[6] != null) {
						dt.setDriverMobile(obj[6].toString());
					}
					list.add(dt);
				}
			}
			if (list != null && list.size() > 0) {
				return list;
			} else {
				list = new ArrayList<VehicleTripSheet>();
				return list;
			}

		} catch (Exception er) {
			er.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public VehicleTripsheetDrops saveVehicleTripsheetDrops(
			VehicleTripsheetDrops dto) {
		Session session = sessionFactory.openSession();
		List<VehicleTripsheetDrops> objList = session.createQuery(
				"FROM VehicleTripsheetDrops WHERE tripId=" + dto.getTripId()
						+ " AND fromLocation='" + dto.getFromLocation()
						+ "' AND toLocation='" + dto.getToLocation() + "'")
				.list();
		session.close();
		if (objList != null && objList.size() > 0) {
			for (VehicleTripsheetDrops at : objList) {
				if (dto != null && dto.getTripId() != null) {
					if (!dto.getTripId().equals(""))
						at.setTripId(dto.getTripId());
				}
				if (dto != null && dto.getFromLocation() != null) {
					if (!dto.getFromLocation().equals(""))
						at.setFromLocation(dto.getFromLocation());
				}
				if (dto != null && dto.getToLocation() != null) {
					if (!dto.getToLocation().equals(""))
						at.setToLocation(dto.getToLocation());
				}
				if (dto != null && dto.getModifiedBy() != null) {
					if (dto.getModifiedBy() != 0)
						at.setModifiedBy(dto.getModifiedBy());
				}

				at.setModifiedDate(new Date());

				Session session1 = this.sessionFactory.openSession();
				session1.get(VehicleTripsheetDrops.class, at.getId());
				Transaction txs = session1.beginTransaction();
				VehicleTripsheetDrops dts = (VehicleTripsheetDrops) session1
						.merge(at);
				txs.commit();
				session1.close();
				return dts;
			}
		} else {

			Session sessions = sessionFactory.openSession();
			try {
				Transaction tx = sessions.beginTransaction();
				sessions.saveOrUpdate(dto);
				tx.commit();
				DetachedCriteria maxId = DetachedCriteria.forClass(
						VehicleTripsheetDrops.class).setProjection(
						Projections.max("id"));
				List<VehicleTripsheetDrops> cmdList = sessions
						.createCriteria(VehicleTripsheetDrops.class)
						.add(Property.forName("id").eq(maxId)).list();
				sessions.clear();
				sessions.close();
				if (cmdList != null && cmdList.size() > 0) {
					return cmdList.get(0);
				} else {
					return null;
				}
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
		return null;
	}

	
	public List getNonClosedTripSheet(Integer userId) {
		
		Session session = sessionFactory.openSession();
		String queryString = "SELECT VT.`clientid`, CM.`name`, VT.`subclientid`, CSM.`subName`, VT.`vehicleno`, VT.`driver_name`, VT.`trip_date` FROM `vehicle_tripsheet` AS VT LEFT JOIN `client_master` AS CM ON VT.`clientid`=CM.`idClientMaster` LEFT JOIN `client_sub_master` AS CSM ON VT.`subclientid`=CSM.`idClientSubMaster` WHERE VT.`created_by`="+userId+" AND VT.`flag`=2";
		Query query = session.createSQLQuery(queryString).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List objList = query.list();
		session.close();
		
		
		
		return objList;
	}
}
