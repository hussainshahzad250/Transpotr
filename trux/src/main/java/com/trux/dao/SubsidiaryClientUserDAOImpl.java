package com.trux.dao;

import java.util.ArrayList;
import java.util.List;
 

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.OrganizationMasterRegistration;
import com.trux.model.SubsidiaryClientOfOrg;
import com.trux.model.SubsidiaryClientUser;

public class SubsidiaryClientUserDAOImpl implements SubsidiaryClientUserDAO {

private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public SubsidiaryClientUser saveSubsidiaryClientUser(SubsidiaryClientUser dto) {
		Session session=sessionFactory.openSession();
		SubsidiaryClientUser fechdto=null;
		try{  
		List<SubsidiaryClientUser> list=null;
	    String sqlSelect="SELECT user_mappingId,clientid,clientsubid,userid FROM client_truxuser_mapping  WHERE userid="+dto.getUserId()+" AND clientid="+dto.getClientId()+" AND clientsubid="+dto.getClientSubId()+";";
		 	@SuppressWarnings("unchecked")
			List<Object[]>  idList=session.createSQLQuery(sqlSelect).list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		    	  list=new ArrayList<SubsidiaryClientUser>();
		    	  for (Object[] obj:idList){
		    		  SubsidiaryClientUser dt=new SubsidiaryClientUser();
		    		  Integer setUserMappingId=new Integer(obj[0].toString());
		    		  dt.setUserMappingId(setUserMappingId);
		    		  dt.setClientId(new Integer(obj[1].toString()));
		    		  dt.setClientSubId(new Integer(obj[2].toString()));
		    		  dt.setUserId(new Integer(obj[3].toString()));
		    		  list.add(dt);
		    	  }
		      }
		    
		      if(list!=null && list.size()>0){
		    	  fechdto=list.get(0);
		    	  if(fechdto.getClientId().equals(dto.getClientId())){
		    		if((fechdto.getUserId().equals(dto.getUserId()))){
		    		  if((fechdto.getClientSubId().equals(dto.getClientSubId()))){
		    		  Session sessions=sessionFactory.openSession();
		    		  Transaction tx1=sessions.beginTransaction();
		    		  String sqls="UPDATE client_truxuser_mapping SET is_active=1 WHERE user_mappingId="+fechdto.getUserMappingId()+" and  userid="+fechdto.getUserId()+" AND clientid="+fechdto.getClientId()+" AND clientsubid="+fechdto.getClientSubId()+";";
		    		    System.out.println(sqls);
		    		    sessions.createSQLQuery(sqls).executeUpdate();
		    		    tx1.commit();
		    		    sessions.clear();
		    		    sessions.close();
		    	  }
		    	 }
		    	}
		      }else{
		 Session sessionm=sessionFactory.openSession();
    	Transaction tx2=sessionm.beginTransaction();
    	sessionm.saveOrUpdate(dto);
    	tx2.commit();
		DetachedCriteria maxId = DetachedCriteria.forClass(SubsidiaryClientUser.class).setProjection(Projections.max("userMappingId"));
		@SuppressWarnings("unchecked")
		List<SubsidiaryClientUser>  saveBack=sessionm.createCriteria(SubsidiaryClientUser.class).add(Property.forName("userMappingId").eq(maxId)).list();
		sessionm.close();
		if(saveBack!=null &&  saveBack.size()>0){
			fechdto=saveBack.get(0);
	    	 return fechdto;
	     }
		}
		 }catch(Exception er){
			 er.printStackTrace();
			  
		 }
		return fechdto; 
	}

	
	public List<SubsidiaryClientUser> getSubsidiaryClientUserList() {

		Session session=sessionFactory.openSession();
		try{ 
		 Query query=session.createQuery("From SubsidiaryClientUser where isActive=1");
			@SuppressWarnings("unchecked")
			List<SubsidiaryClientUser>  idList=query.list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		      return idList;
		      }
		     }catch(Exception er){
		    	 er.printStackTrace();		    	 
		    	 session.close();
		     }
		      return null;
	}

	
	public List<SubsidiaryClientUser> getSubsidiaryClientUserListByUserId(Integer userId) {

		Session session=sessionFactory.openSession();
		try{ 
		 Query query=session.createQuery("From SubsidiaryClientUser where userId="+userId+" and isActive=1");
			@SuppressWarnings("unchecked")
			List<SubsidiaryClientUser>  idList=query.list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		      return idList;
		      }
		     }catch(Exception er){
		    	 er.printStackTrace();		    	 
		    	 session.close();
		     }
		      return null;
	}

 
	public List<SubsidiaryClientOfOrg> getSubsidiaryClientOfOrgListByUserId(Integer userId) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT DISTINCT s.idClientSubMaster,s.subName,s.address ");
		sql.append("FROM client_sub_master s INNER JOIN client_truxuser_mapping m ON m.clientsubid=s.idClientSubMaster ");
		sql.append("WHERE m.userid="+userId +" and m.is_active=1");
		 List<SubsidiaryClientOfOrg> list=new ArrayList<SubsidiaryClientOfOrg>();
		Session session=sessionFactory.openSession();
		try{ 
		 	@SuppressWarnings("unchecked")
			List<Object[]>  idList=session.createSQLQuery(sql.toString()).list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		    	  for (Object[] obj:idList){
		    		  SubsidiaryClientOfOrg dt=new SubsidiaryClientOfOrg();
		    		  Integer subclientId=new Integer(obj[0].toString());
		    		  dt.setIdClientSubMaster(subclientId);
		    		  dt.setSubName(obj[1].toString());
		    		  dt.setAddress(obj[2].toString());
		    		  list.add(dt);
		    		  
		    	  }
		      return list;
		      }
		     }catch(Exception er){
		    	 er.printStackTrace();	 
		     }
		      return null;
	  
	}

	
	public List<SubsidiaryClientOfOrg> getSubsidiaryClientOfOrgListByUserId(Integer userId,Integer clientId) {
		StringBuilder sql=new StringBuilder();
		
		sql.append("SELECT DISTINCT m.idClientSubMaster,m.subName,m.address FROM client_truxuser_mapping s ");
		sql.append(" INNER JOIN client_sub_master m ON s.clientsubid=m.idClientSubMaster WHERE s.userid="+userId+" AND s.clientid="+clientId  +" AND s.is_active=1");
		 
		List<SubsidiaryClientOfOrg> list=new ArrayList<SubsidiaryClientOfOrg>();
		Session session=sessionFactory.openSession();
		try{ 
		 	@SuppressWarnings("unchecked")
			List<Object[]>  idList=session.createSQLQuery(sql.toString()).list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		    	  for (Object[] obj:idList){
		    		  SubsidiaryClientOfOrg dt=new SubsidiaryClientOfOrg();
		    		  Integer subclientId=new Integer(obj[0].toString());
		    		  dt.setIdClientSubMaster(subclientId);
		    		  dt.setSubName(obj[1].toString());
		    		  dt.setAddress(obj[2].toString());
		    		  list.add(dt);
		    		  
		    	  }
		      return list;
		      }
		     }catch(Exception er){
		    	 er.printStackTrace();	 
		     }
		      return null;
	  
	}
	
	
	public List<SubsidiaryClientOfOrg> searchSubsidiaryClient(Integer userId,Integer clientId) {
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT ctm.user_mappingId, cm.name,csm.subName,csm.address,ctm.is_active,ctm.clientsubid FROM  client_truxuser_mapping  ctm ");
		sql.append(" INNER JOIN client_sub_master csm ON ctm.clientsubid=csm.idClientSubMaster ");
		sql.append(" INNER JOIN client_master cm ON cm.idClientMaster=ctm.clientid ");
		sql.append(" WHERE ctm.userid="+userId+" AND  ctm.clientid="+clientId+"  AND ctm.is_active=1 ORDER BY cm.name;");
		List<SubsidiaryClientOfOrg> list=new ArrayList<SubsidiaryClientOfOrg>();
		Session session=sessionFactory.openSession();
		try{ 
		 	@SuppressWarnings("unchecked")
			List<Object[]>  idList=session.createSQLQuery(sql.toString()).list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      String clientName="";
		      if(idList!=null && idList.size()>0){
		    	  for (Object[] obj:idList){
		    		  SubsidiaryClientOfOrg dt=new SubsidiaryClientOfOrg();
		    		  if(obj[0]!=null){
		    		 dt.setIdClientSubMaster(new Integer(obj[0].toString()));
		    		  }
		    		  dt.setClientName("");
		    		if(!clientName.equals(obj[1].toString()));{
		    		 dt.setClientName(obj[1].toString());
		    		 clientName=obj[1].toString();
		    		}
		    		 dt.setSubName(obj[2].toString());
		    		  dt.setAddress(obj[3].toString());
		    		  dt.setIsActive(new Integer(obj[4].toString()));
		    		  if(obj[5]!=null){ 
				    		 dt.setClientSudIds(new Integer(obj[5].toString()));
				    	}
		    		  list.add(dt);
		    		  
		    	  }
		      return list;
		      }
		     }catch(Exception er){
		    	 er.printStackTrace();	 
		     }
		      return null;
	  
	}
	
	
	public List<SubsidiaryClientOfOrg> searchSubsidiaryAndActiveDeactive(Integer userId,Integer clientId,Integer subClientId,Integer actionFlag) {
		StringBuilder sql=new StringBuilder();
		 
		 
		sql.append(" SELECT ctm.user_mappingId, cm.name,csm.subName,csm.address,ctm.is_active,ctm.clientsubid FROM  client_truxuser_mapping  ctm ");
		sql.append(" INNER JOIN client_sub_master csm ON ctm.clientsubid=csm.idClientSubMaster ");
		sql.append(" INNER JOIN client_master cm ON cm.idClientMaster=ctm.clientid ");
		sql.append(" WHERE ctm.userid="+userId+" AND ctm.clientid="+clientId+" AND ctm.is_active=1 ORDER BY cm.name;");
		
		System.out.println(sql.toString());
		List<SubsidiaryClientOfOrg> list=new ArrayList<SubsidiaryClientOfOrg>();
		Session session=sessionFactory.openSession();
		 Transaction tx=session.beginTransaction();
		try{ 
			String sqls="UPDATE client_truxuser_mapping SET is_active="+actionFlag+" WHERE userid="+userId+" AND clientid="+clientId+" AND clientsubid="+subClientId+";";
		    System.out.println(sqls);
			session.createSQLQuery(sqls).executeUpdate();
		    tx.commit();
		 	@SuppressWarnings("unchecked")
			List<Object[]>  idList=session.createSQLQuery(sql.toString()).list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      String clientName="";
		      if(idList!=null && idList.size()>0){
		    	  for (Object[] obj:idList){
		    		  SubsidiaryClientOfOrg dt=new SubsidiaryClientOfOrg();
		    		  if(obj[0]!=null){
		    		 dt.setIdClientSubMaster(new Integer(obj[0].toString()));
		    		  }
		    		  dt.setClientName("");
		    		if(!clientName.equals(obj[1].toString()));{
		    		 dt.setClientName(obj[1].toString());
		    		 clientName=obj[1].toString();
		    		}
		    		 dt.setSubName(obj[2].toString());
		    		  dt.setAddress(obj[3].toString());
		    		  dt.setIsActive(new Integer(obj[4].toString()));
		    		  if(obj[5]!=null){ 
				    		 dt.setClientSudIds(new Integer(obj[5].toString()));
				    	}
		    		  list.add(dt);
		    		  
		    	  }
		      return list;
		      }
		     }catch(Exception er){
		    	 er.printStackTrace();	 
		     }
		      return null;
	  
	}

	
	public List<SubsidiaryClientOfOrg> getSubsidiaryClientOfOrgListByHubId(Integer userId,Integer clientId,Integer hubId) {
		StringBuilder sql=new StringBuilder();
		
		/*sql.append("SELECT m.idClientSubMaster,m.subName,m.address FROM client_truxuser_mapping s ");
		sql.append("INNER JOIN client_sub_master m ON s.clientsubid=m.idClientSubMaster ");
		sql.append("WHERE s.userid=10 AND s.clientid=3 AND s.is_active=1 AND m.idHub=2;");
		*/
		
		sql.append("SELECT DISTINCT m.idClientSubMaster,m.subName,m.address FROM client_truxuser_mapping s "); 
		sql.append("INNER JOIN client_sub_master m ON s.clientid=m.idClientMaster ");
		  sql.append("WHERE s.userid="+userId+" AND s.clientid="+clientId+" AND s.is_active=1 AND m.idHub="+hubId);
		 
		List<SubsidiaryClientOfOrg> list=new ArrayList<SubsidiaryClientOfOrg>();
		Session session=sessionFactory.openSession();
		try{ 
		 	@SuppressWarnings("unchecked")
			List<Object[]>  idList=session.createSQLQuery(sql.toString()).list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		    	  for (Object[] obj:idList){
		    		  SubsidiaryClientOfOrg dt=new SubsidiaryClientOfOrg();
		    		  Integer subclientId=new Integer(obj[0].toString());
		    		  dt.setIdClientSubMaster(subclientId);
		    		  dt.setSubName(obj[1].toString());
		    		  dt.setAddress(obj[2].toString());
		    		  list.add(dt);
		    		  
		    	  }
		      return list;
		      }
		     }catch(Exception er){
		    	 er.printStackTrace();	 
		     }
		      return null;
	  
	}

	
	public List<DriverDeviceVehicleMapping> getDriverModileVehicle(Integer subClientId) {
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT dmc.driver_id,dmc.vehicle_id,dmc.driverName,dmc.vehicle_number, dmc.driver_phone_no FROM driver_device_vehicle_mapping dmc ");
		sql.append("WHERE dmc.subClientId="+subClientId);
		/*sql.append("SELECT dmc.driver_id,dmc.vehicle_id,d.driverName,dmc.vehicle_number, dmc.driver_phone_no ");
		sql.append("FROM  driver_registration d INNER JOIN driver_device_vehicle_mapping dmc ON d.phone_number=dmc.driver_phone_no ");
		sql.append("WHERE d.sub_org_client="+subClientId);*/

		 System.out.println(sql.toString());
		 List<DriverDeviceVehicleMapping> list=new ArrayList<DriverDeviceVehicleMapping>();
		Session session=sessionFactory.openSession();
		try{ 
		 	@SuppressWarnings("unchecked")
			List<Object[]>  idList=session.createSQLQuery(sql.toString()).list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		    	  for (Object[] obj:idList){
		    		  DriverDeviceVehicleMapping dt=new DriverDeviceVehicleMapping();
		    		  if(obj[0]!=null){
		    		  Integer driverId=new Integer(obj[0].toString());
		    		  dt.setDriverId(driverId);
		    		  }
		    		  if(obj[1]!=null){
			    		  Integer vehicleId=new Integer(obj[1].toString());
			    		  dt.setVehicleId(vehicleId);
			    		  }
		    		  if(obj[2]!=null){
		    		  dt.setDriverName(obj[2].toString());
		    		  }
		    		  if(obj[3]!=null){
		    		  dt.setVehicleNumber(obj[3].toString());
		    		  }
		    		  if(obj[4]!=null){
		    		  dt.setDriverPhoneNumber(obj[4].toString());
		    		  }
		    		  list.add(dt);
		    		  
		    	  }
		      return list;
		      }
		     }catch(Exception er){
		    	 er.printStackTrace();	 
		     }
		      return null;
	  
	}
	
	
	public List<OrganizationMasterRegistration> getOrgClient(Integer userId) {
		StringBuilder sql=new StringBuilder();
		
		sql.append("SELECT DISTINCT m.idClientMaster,m.name,m.address FROM client_truxuser_mapping s ");
		sql.append("INNER JOIN client_master m ON s.clientid=m.idClientMaster WHERE s.userid="+userId );

		 System.out.println(sql.toString());
		 List<OrganizationMasterRegistration> list=new ArrayList<OrganizationMasterRegistration>();
		Session session=sessionFactory.openSession();
		try{ 
		 	@SuppressWarnings("unchecked")
			List<Object[]>  idList=session.createSQLQuery(sql.toString()).list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		    	  for (Object[] obj:idList){
		    		  OrganizationMasterRegistration dt=new OrganizationMasterRegistration();
		    		  if(obj[0]!=null){
		    		  Integer idClientMaster=new Integer(obj[0].toString());
		    		  dt.setIdClientMaster(idClientMaster);
		    		  }
		    		  if(obj[1]!=null){
			    		   dt.setName(obj[1].toString());
			    		  }
		    		  if(obj[2]!=null){
		    		  dt.setAddress(obj[2].toString());
		    		  }
		    		  
		    		  list.add(dt);
		    		  
		    	  }
		      return list;
		      }
		     }catch(Exception er){
		    	 er.printStackTrace();	 
		     }
		      return null;
	  
	}
	
	 

}
