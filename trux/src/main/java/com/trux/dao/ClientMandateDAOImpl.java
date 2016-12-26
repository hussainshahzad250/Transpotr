package com.trux.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.ClientMandate;
import com.trux.model.ClientMandateDetail;
import com.trux.utils.DateFormaterUtils;

public class ClientMandateDAOImpl implements ClientMandateDAO{

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	
	public ClientMandate saveClientMandate(ClientMandate dto) {
		Session session=sessionFactory.openSession();
		try{
			Transaction tx=session.beginTransaction();
			session.save(dto);
			tx.commit();
		    DetachedCriteria maxId = DetachedCriteria.forClass(ClientMandate.class).setProjection(Projections.max("clientMandateId"));
			List<ClientMandate>  clusterList=session.createCriteria(ClientMandate.class).add(Property.forName("clientMandateId").eq(maxId)).list();
			session.clear();
			session.close();
			if(clusterList!=null && clusterList.size()>0){
			return clusterList.get(0);
			}else{
			return null;	
			}
		}catch(Exception er){
			
			er.printStackTrace();}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<ClientMandate> getClientMandate() {
		Session session=sessionFactory.openSession();
		try{
		 List<ClientMandate>  clusterList=session.createQuery("FROM ClientMandate").list();
			session.clear();
			session.close();
			if(clusterList!=null && clusterList.size()>0){
			return clusterList;
			}else{
			return null;	
			}
		}catch(Exception er){
			
			er.printStackTrace();}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public ClientMandate getClientMandate(Integer clientMandateId) {
		Session session=sessionFactory.openSession();
		try{
		 List<ClientMandate>  clusterList=session.createQuery("FROM ClientMandate WHERE clientMandateId="+clientMandateId).list();
			session.clear();
			session.close();
			if(clusterList!=null && clusterList.size()>0){
			return clusterList.get(0);
			}else{
			return null;	
			}
		}catch(Exception er){
			
			er.printStackTrace();}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ClientMandate> getMandateByClientAndSubClient(Integer clientId, Integer subClientId) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT clientmandateid,mandate_type FROM client_mandate cm INNER JOIN client_sub_master sm ON cm.clientsubid=sm.idClientSubMaster ");
		sql.append("WHERE clientsubid="+subClientId+" AND clientid="+clientId);
		System.out.println(sql.toString());
	 	Session session=sessionFactory.openSession();
		List<ClientMandate> cmdList=new ArrayList<ClientMandate>();
		try{ 
			List<Object[]>  idList=session.createSQLQuery(sql.toString()).list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		    	  for (Object[] obj:idList){ 
					ClientMandate dto=new ClientMandate();
					if(obj[0]!=null){
						dto.setClientMandateId(new Integer(obj[0].toString()));
					}
                   if(obj[1]!=null){
                 	dto.setMandateType(obj[1].toString());	
					}
					
					cmdList.add(dto);
				}
			return cmdList;
			}else{
				ClientMandate dto=new ClientMandate();
				dto.setErrorCode("402");
				dto.setErrorMessage("Records does not exist!");
				cmdList=new ArrayList<ClientMandate>();
				cmdList.add(dto);
			return cmdList;	
			}
		}catch(Exception er){
			
			er.printStackTrace();
		 	ClientMandate dto=new ClientMandate();
			dto.setErrorCode("402");
			dto.setErrorMessage("Records does not exist! "+er.getMessage());
			cmdList=new ArrayList<ClientMandate>();
			cmdList.add(dto);
			return cmdList;	}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ClientMandateDetail> getMandateDetailByClientAndSubClient(Integer clientId, Integer subClientId) {
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT sm.mandatedetailid, cm.clientmandateid, cm.mandate_type, sm.vehicle_type, sm.no_of_vehicles,");
		sql.append(" sm.fix_days, sm.fix_km, sm.fix_hour, sm.trip_km_start, sm.trip_km_end, sm.invoice_amount ");
		sql.append(" FROM client_mandate cm INNER JOIN client_mandate_detail sm ON cm.clientmandateid=sm.mandateid ");
		sql.append("WHERE clientsubid="+subClientId+" AND clientid="+clientId);
		System.out.println(sql.toString());
	 	Session session=sessionFactory.openSession();
		List<ClientMandateDetail> cmdList=new ArrayList<ClientMandateDetail>();
		try{ 
			List<Object[]>  idList=session.createSQLQuery(sql.toString()).list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		    	  for (Object[] obj:idList){ 
		    		  ClientMandateDetail dto=new ClientMandateDetail();
					if(obj[0]!=null){
						dto.setMandateDetailId(new Integer(obj[0].toString()));
					}
					if(obj[1]!=null){
						dto.setMandateId(new Integer(obj[1].toString()));
					}
                   if(obj[2]!=null){
                 	dto.setMandateType(obj[2].toString());	
					}
                   if(obj[3]!=null){
                    	dto.setVehicleType(obj[3].toString());	
   					}
                   if(obj[4]!=null){
                    	dto.setTotalVehicle(new Integer(obj[4].toString()));	
   					}
                   if(obj[5]!=null){
                    	dto.setFixDays(new Integer(obj[5].toString()));	
   					}
                   if(obj[6]!=null){
                    	dto.setFixKm(new Integer(obj[6].toString()));	
   					}
                   if(obj[7]!=null){
                    	dto.setFixHour(new Integer(obj[7].toString()));	
   					}
                   /*if(obj[8]!=null){
                    	dto.setTripKmStart(obj[8].toString());	
   					}
                   if(obj[9]!=null){
                    	dto.setTripKmEnd(obj[9].toString());	
   					}*/
                   /*if(obj[10]!=null){
                    	dto.setInvoiceAmount(new Double(obj[10].toString()));	
   					}*/
					
					cmdList.add(dto);
				}
			return cmdList;
			}else{
				ClientMandateDetail dto=new ClientMandateDetail();
				 cmdList=new ArrayList<ClientMandateDetail>();
				cmdList.add(dto);
			return cmdList;	
			}
		}catch(Exception er){
			
			er.printStackTrace();
			ClientMandateDetail dto=new ClientMandateDetail();
			 cmdList=new ArrayList<ClientMandateDetail>();
			cmdList.add(dto);
			return cmdList;	}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<ClientMandate> getMandateCSUBId(Integer clientId, Integer subClientId) {
		StringBuilder sql=new StringBuilder();
		
		sql.append("SELECT DISTINCT cm.clientmandateid,cms.name,sm.subName, mandate_type FROM client_mandate cm INNER JOIN client_sub_master sm ON cm.clientsubid=sm.idClientSubMaster INNER JOIN client_master cms ON cms.idClientMaster=cm.clientid ");
		sql.append("WHERE clientid="+clientId+" AND clientsubid="+subClientId);
	    Session session=sessionFactory.openSession();
		List<ClientMandate> cmdList=new ArrayList<ClientMandate>();
		try{ 
			List<Object[]>  idList=(List<Object[]>)session.createSQLQuery(sql.toString()).list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		    	  for (Object[] obj:idList){ 
					ClientMandate dto=new ClientMandate();
					if(obj[0]!=null){
					dto.setClientMandateId(new Integer(obj[0].toString()));
					}
					if(obj[1]!=null){
						dto.setClientName(obj[1].toString());
						}
					if(obj[2]!=null){
					dto.setSubClientName(obj[2].toString());
					}
					if(obj[3]!=null){
					dto.setMandateType(obj[3].toString());
					}
					cmdList.add(dto);
				}
			return cmdList;
			}else{
				ClientMandate dto=new ClientMandate();
				dto.setErrorCode("402");
				dto.setErrorMessage("Records does not exist!");
				cmdList=new ArrayList<ClientMandate>();
				cmdList.add(dto);
			return cmdList;	
			}
		}catch(Exception er){
			
			er.printStackTrace();
		 	ClientMandate dto=new ClientMandate();
			dto.setErrorCode("402");
			dto.setErrorMessage("Records does not exist! "+er.getMessage());
			cmdList=new ArrayList<ClientMandate>();
			cmdList.add(dto);
			return cmdList;	}
	}
	
	
	public List<ClientMandate> getMandateCSUBIdAndMandateType(Integer clientId, Integer subClientId,String mandateType) {
		StringBuilder sql=new StringBuilder();
	 
		sql.append("SELECT DISTINCT cm.clientmandateid,cms.name,sm.subName, mandate_type,mandate_start_date,mandate_end_date FROM client_mandate cm INNER JOIN client_sub_master sm ON cm.clientsubid=sm.idClientSubMaster INNER JOIN client_master cms ON cms.idClientMaster=cm.clientid ");
		sql.append("WHERE clientid="+clientId+" AND clientsubid="+subClientId+" AND mandate_type='"+mandateType+"';");
	    Session session=sessionFactory.openSession();
		List<ClientMandate> cmdList=new ArrayList<ClientMandate>();
		try{ 
			@SuppressWarnings("unchecked")
			List<Object[]>  idList=(List<Object[]>)session.createSQLQuery(sql.toString()).list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		    	  for (Object[] obj:idList){ 
					ClientMandate dto=new ClientMandate();
					if(obj[0]!=null){
					dto.setClientMandateId(new Integer(obj[0].toString()));
					}
					if(obj[1]!=null){
						dto.setClientName(obj[1].toString());
					}
					if(obj[2]!=null){
					dto.setSubClientName(obj[2].toString());
					}
					if(obj[3]!=null){
					dto.setMandateType(obj[3].toString());
					}
					if(obj[4]!=null){
						dto.setMandateStartDates(obj[4].toString());
						}
					if(obj[5]!=null){
						dto.setMandateEndDates(obj[5].toString());
						}
					cmdList.add(dto);
				}
			return cmdList;
			}else{
				ClientMandate dto=new ClientMandate();
				dto.setErrorCode("402");
				dto.setErrorMessage("Records does not exist!");
				cmdList=new ArrayList<ClientMandate>();
				cmdList.add(dto);
			return cmdList;	
			}
		}catch(Exception er){
			
			er.printStackTrace();
		 	ClientMandate dto=new ClientMandate();
			dto.setErrorCode("402");
			dto.setErrorMessage("Records does not exist! "+er.getMessage());
			cmdList=new ArrayList<ClientMandate>();
			cmdList.add(dto);
			return cmdList;	}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<ClientMandate> getMandateCSUBId(Integer clientId,int pageNumber,int pageSize) {
		StringBuilder sql=new StringBuilder();
		
		sql.append("SELECT DISTINCT cm.clientmandateid,cms.name,sm.subName, cm.mandate_type,md.mandatedetailid,md.vehicle_type,md.body_type,md.no_of_vehicles, cm.mandate_start_date,cm.mandate_end_date,cm.total_vehicles,cm.service_tax_abetted,cm.service_tax_full ");
		sql.append(" FROM client_mandate cm INNER JOIN client_sub_master sm ON cm.clientsubid=sm.idClientSubMaster "); 
		sql.append(" INNER JOIN client_master cms ON cms.idClientMaster=cm.clientid ");
		sql.append(" LEFT JOIN client_mandate_detail md ON md.mandateid=cm.clientmandateid");
		sql.append(" WHERE clientid="+clientId);
		
	/*	
		sql.append("SELECT DISTINCT cm.clientmandateid,cms.name,sm.subName, cm.mandate_type,cm.mandate_start_date,cm.mandate_end_date FROM client_mandate cm INNER JOIN client_sub_master sm ON cm.clientsubid=sm.idClientSubMaster INNER JOIN client_master cms ON cms.idClientMaster=cm.clientid ");
		sql.append("WHERE clientid="+clientId);*/
		System.out.println("SQL " +sql.toString());
	    Session session=sessionFactory.openSession();
		List<ClientMandate> cmdList=null;
		try{ 
			List<Object[]>  idList=(List<Object[]>)session.createSQLQuery(sql.toString()).setFirstResult(pageNumber).setMaxResults(pageSize).list();;
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		    	  cmdList=new ArrayList<ClientMandate>();
		    	  for (Object[] obj:idList){ 
					ClientMandate dto=new ClientMandate();
					if(obj[0]!=null){
						if(obj[0]!="")
					dto.setClientMandateId(new Integer(obj[0].toString()));
					}
					if(obj[1]!=null){
						dto.setClientName(obj[1].toString());
						}
					if(obj[2]!=null){
					dto.setSubClientName(obj[2].toString());
					}
					if(obj[3]!=null){
					dto.setMandateType(obj[3].toString());
					}
					
					//
					if(obj[4]!=null){
						if(obj[4]!="")
						dto.setMandateDetailsId(new Integer(obj[4].toString()));
						}
					if(obj[5]!=null){
						dto.setVehicleType(obj[5].toString());
						}
					if(obj[6]!=null){
						dto.setBodyType(obj[6].toString());
						}
					if(obj[7]!=null){
						if(obj[7]!="")
						dto.setTotalNoOfVehicle(new Integer(obj[7].toString()));
						}
					//
					
					if(obj[8]!=null){
						dto.setMandateStartDates(DateFormaterUtils.dateFormateInEdits(obj[8].toString()));
						}
					if(obj[9]!=null){
						dto.setMandateEndDates(DateFormaterUtils.dateFormateInEdits(obj[9].toString()));
						}
					if(obj[10]!=null){
						if(obj[10]!="")
						dto.setTotalVehicles(new Integer(obj[10].toString()));
						}
					
					if(obj[11]!=null){
						if(obj[11]!="")
						dto.setAbettedTax(new Double(obj[11].toString()));
						}
					if(obj[12]!=null){
						if(obj[12]!="")
						dto.setFullTax(new Double(obj[12].toString()));
						}
					cmdList.add(dto);
				}
			return cmdList;
			}else{
				 
			return null;	
			}
		}catch(Exception er){
			
			er.printStackTrace();
		 	ClientMandate dto=new ClientMandate();
			dto.setErrorCode("402");
			dto.setErrorMessage("Records does not exist! "+er.getMessage());
			cmdList=new ArrayList<ClientMandate>();
			cmdList.add(dto);
			return cmdList;	}
	}
	
	@SuppressWarnings("unchecked")
	public ClientMandate updateMandate(ClientMandate dto){
		
		Session session=sessionFactory.openSession(); 
    	List<ClientMandate> objList=session.createQuery("FROM ClientMandate where clientMandateId="+dto.getClientMandateId()).list();
    	 session.close();
    	 ClientMandate dts=null;
    	if(objList!=null && objList.size()>0){
    	for(ClientMandate at:objList){
    		if(dto!=null && dto.getMandateStartDate()!=null){
    			if(!dto.getMandateStartDate().equals(""))
    		 	at.setMandateStartDate(dto.getMandateStartDate());
    		}
    		if(dto!=null && dto.getMandateEndDate()!=null){
    			if(!dto.getMandateEndDate().equals(""))
    		  at.setMandateEndDate(dto.getMandateEndDate());
    		}
    		if(dto!=null && dto.getMandateType()!=null){	    			
    		if(!dto.getMandateType().equals(""))
		   	at.setMandateType(dto.getMandateType());
    		}
    	 
    		if(dto!=null && dto.getModifiedBy()!=null){
    			if(!dto.getModifiedBy().equals(0))
 			 at.setModifiedBy(dto.getModifiedBy());
    		}
    		 
    		at.setModifiedDate(new Date());
    		Session session1 = this.sessionFactory.openSession();
      		session1.get(ClientMandate.class, at.getClientMandateId());		 
      		Transaction txs=session1.beginTransaction();		 
      	    dts=(ClientMandate)session1.merge(at);
      		txs.commit(); 
      		session1.close(); 
      		return dts;
      			}
        	}
    	return dts;
	}
	
	@SuppressWarnings("unchecked")
	public ClientMandateDetail updateMandateDetails(ClientMandateDetail dto){
		
		Session session=sessionFactory.openSession(); 
    	List<ClientMandateDetail> objList=session.createQuery("FROM ClientMandateDetail where mandateDetailId="+dto.getMandateDetailId()).list();
    	 session.close();
    	 ClientMandateDetail dts=null;
    	if(objList!=null && objList.size()>0){
    	for(ClientMandateDetail at:objList){
    		if(dto!=null && dto.getBillingRate()!=null){
    			if(!dto.getBillingRate().equals(""))
    		 	at.setBillingRate(dto.getBillingRate());
    		}
    		if(dto!=null && dto.getFixDays()!=0){
    		  at.setFixDays(dto.getFixDays());
    		}
    		if(dto!=null && dto.getFixHour()!=0){	    			
    		 	at.setFixHour(dto.getFixHour());
    		}
    		if(dto!=null && dto.getFixKm()!=0){
    		 at.setFixKm(dto.getFixKm());
    		}
    		if(dto!=null && dto.getMoq()!=0){
       		 at.setMoq(dto.getMoq());
       		}
    		if(dto!=null && dto.getNightHoldingCharge()!=null){
    		 if(!dto.getNightHoldingCharge().equals(""))
       		 at.setNightHoldingCharge(dto.getNightHoldingCharge());
       		}
    		if(dto!=null && dto.getExtraHourCharge()!=null){
    		 if(!dto.getExtraHourCharge().equals(""))
          		 at.setExtraHourCharge(dto.getExtraHourCharge());
          		}
    		if(dto!=null && dto.getExtraKmCharge()!=null){
    			 if(!dto.getExtraKmCharge().equals(""))
          		 at.setExtraKmCharge(dto.getExtraKmCharge());
          		}
    		 if(dto!=null &&dto.getCreatedBy()!=null){
             at.setModifiedBy(dto.getCreatedBy());
    		 }
    		at.setModifiedDate(new Date());
    		Session session1 = this.sessionFactory.openSession();
      		session1.get(ClientMandateDetail.class, at.getMandateDetailId());		 
      		Transaction txs=session1.beginTransaction();		 
      	    dts=(ClientMandateDetail)session1.merge(at);
      		txs.commit(); 
      		session1.close(); 
      		return dts;
      		}
        	}
    	return dts;
	}
	 
	public void deleteMandate(ClientMandate dto){
		
		Session session=sessionFactory.openSession(); 
		Transaction tx=session.beginTransaction();
		String sql="DELETE FROM client_mandate WHERE clientmandateid="+dto.getClientMandateId()+";";
		System.out.println(sql);
    	 session.createSQLQuery(sql).executeUpdate();
    	 tx.commit();
    	 session.flush();
    	 session.close();
    	  
	}

	@SuppressWarnings("unchecked")
	
	public List<ClientMandateDetail> getClientMandateDetail(Integer clientId,Integer subClientId, String mandateType,int pageNumber,int pageSize) {
		 
		StringBuilder sql=new StringBuilder();
		
		sql.append("SELECT cmd.mandatedetailid,cmd.mandateid, cmd.vehicle_type,cmd.body_type,cmd.no_of_vehicles,");
		sql.append("cmd.moq,cmd.billing_rate,cmd.night_holding_charge,cmd.fix_days,cmd.fix_km,cmd.fix_hour,cmd.extra_mk_charge,cmd.extra_hour_charge ");
		sql.append(" FROM client_mandate_detail cmd INNER JOIN client_mandate m ON cmd.mandateid=m.clientmandateid");
		sql.append(" WHERE m.clientid="+clientId+" AND m.clientsubid="+subClientId+" AND m.mandate_type='"+mandateType+"' ");
		 
		System.out.println(sql.toString());
	 	Session session=sessionFactory.openSession();
		List<ClientMandateDetail> cmdList=null;
		try{ 
			List<Object[]>  idList=session.createSQLQuery(sql.toString()).setFirstResult(pageNumber).setMaxResults(pageSize).list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		    	  cmdList=new ArrayList<ClientMandateDetail>();
		    	  for (Object[] obj:idList){ 
		    		  ClientMandateDetail dto=new ClientMandateDetail();
					if(obj[0]!=null){
						dto.setMandateDetailId(new Integer(obj[0].toString()));
					}
					if(obj[1]!=null){
						dto.setMandateId(new Integer(obj[1].toString()));
					}
                   if(obj[2]!=null){
                 	dto.setVehicleType(obj[2].toString());	
					}
                   if(obj[3]!=null){
                    	dto.setBodyType(obj[3].toString());	
   					}
                   if(obj[4]!=null){
                    	dto.setTotalVehicle(new Integer(obj[4].toString()));	
   					}
                   if(obj[5]!=null){
                    	dto.setMoq(new Integer(obj[5].toString()));	
   					}
                   if(obj[6]!=null){
                    	dto.setBillingRate(new Double(obj[6].toString()));	
   					}
                   if(obj[7]!=null){
                    	dto.setNightHoldingCharge(new Double(obj[7].toString()));	
   					}
                   if(obj[8]!=null){
                   	dto.setFixDays(new Integer(obj[8].toString()));	
  					}
                  if(obj[9]!=null){
                   	dto.setFixKm(new Integer(obj[9].toString()));	
  					}
                  if(obj[10]!=null){
                   	dto.setFixHour(new Integer(obj[10].toString()));	
  					}
                  if(obj[11]!=null){
                     	dto.setExtraKmCharge(new Double(obj[11].toString()));	
    					}
                  if(obj[12]!=null){
                     	dto.setExtraHourCharge(new Double(obj[12].toString()));	
    					}
                  cmdList.add(dto);
				}
			 } 
		      return cmdList;
		}catch(Exception er){
			
			er.printStackTrace();
			ClientMandateDetail dto=new ClientMandateDetail();
			 cmdList=new ArrayList<ClientMandateDetail>();
			cmdList.add(dto);
			return cmdList;	}
		 
	}
	
	@SuppressWarnings("unchecked")
	
	public List<ClientMandateDetail> getClientMandateDetail(Integer clientId,Integer subClientId, String mandateType) {
		 
		StringBuilder sql=new StringBuilder();
		
		sql.append("SELECT cmd.mandatedetailid,cmd.mandateid, cmd.vehicle_type,cmd.body_type,cmd.no_of_vehicles,");
		sql.append("cmd.moq,cmd.billing_rate,cmd.night_holding_charge,cmd.fix_days,cmd.fix_km,cmd.fix_hour,cmd.extra_mk_charge,cmd.extra_hour_charge ");
		sql.append(" FROM client_mandate_detail cmd INNER JOIN client_mandate m ON cmd.mandateid=m.clientmandateid");
		sql.append(" WHERE m.clientid="+clientId+" AND m.clientsubid="+subClientId+" AND m.mandate_type='"+mandateType+"' ");
		 
		System.out.println(sql.toString());
	 	Session session=sessionFactory.openSession();
		List<ClientMandateDetail> cmdList=null;
		try{ 
			List<Object[]>  idList=session.createSQLQuery(sql.toString()).list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		    	  cmdList=new ArrayList<ClientMandateDetail>();
		    	  for (Object[] obj:idList){ 
		    		  ClientMandateDetail dto=new ClientMandateDetail();
					if(obj[0]!=null){
						dto.setMandateDetailId(new Integer(obj[0].toString()));
					}
					if(obj[1]!=null){
						dto.setMandateId(new Integer(obj[1].toString()));
					}
                   if(obj[2]!=null){
                 	dto.setVehicleType(obj[2].toString());	
					}
                   if(obj[3]!=null){
                    	dto.setBodyType(obj[3].toString());	
   					}
                   if(obj[4]!=null){
                    	dto.setTotalVehicle(new Integer(obj[4].toString()));	
   					}
                   if(obj[5]!=null){
                    	dto.setMoq(new Integer(obj[5].toString()));	
   					}
                   if(obj[6]!=null){
                    	dto.setBillingRate(new Double(obj[6].toString()));	
   					}
                   if(obj[7]!=null){
                    	dto.setNightHoldingCharge(new Double(obj[7].toString()));	
   					}
                   if(obj[8]!=null){
                   	dto.setFixDays(new Integer(obj[8].toString()));	
  					}
                  if(obj[9]!=null){
                   	dto.setFixKm(new Integer(obj[9].toString()));	
  					}
                  if(obj[10]!=null){
                   	dto.setFixHour(new Integer(obj[10].toString()));	
  					}
                  if(obj[11]!=null){
                     	dto.setExtraKmCharge(new Double(obj[11].toString()));	
    					}
                  if(obj[12]!=null){
                     	dto.setExtraHourCharge(new Double(obj[12].toString()));	
    					}
                  cmdList.add(dto);
				}
			 } 
		      return cmdList;
		}catch(Exception er){
			
			er.printStackTrace();
			ClientMandateDetail dto=new ClientMandateDetail();
			 cmdList=new ArrayList<ClientMandateDetail>();
			cmdList.add(dto);
			return cmdList;	}
		 
	}

}
