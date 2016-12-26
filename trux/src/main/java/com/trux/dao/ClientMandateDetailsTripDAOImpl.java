package com.trux.dao;
 
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.ClientMandateDetailsTrip;  

public class ClientMandateDetailsTripDAOImpl implements ClientMandateDetailsTripDAO{

private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	 
	
	
	
	public ClientMandateDetailsTrip saveClientMandateDetailsTrip(ClientMandateDetailsTrip dto) {
		Session sessions=sessionFactory.openSession();
	    String sqlSelect="SELECT mandatetripid,mandatedetailid,point_a,point_b,billing_rate,night_holding_charges FROM client_mandate_detail_trip WHERE mandatedetailid="+dto.getMandateDetailId()+" AND point_a='"+dto.getPointA()+"' AND point_b='"+dto.getPointB()+"';";
		try{   
				List<ClientMandateDetailsTrip> list=null;
			     	@SuppressWarnings("unchecked")
					List<Object[]>  idList=sessions.createSQLQuery(sqlSelect).list();
					sessions.flush();
					sessions.clear();
					sessions.close(); 
				      if(idList!=null && idList.size()>0){
				    	  list=new ArrayList<ClientMandateDetailsTrip>();
				    	  for (Object[] obj:idList){
				    		  ClientMandateDetailsTrip dt=new ClientMandateDetailsTrip();
				    		  
				    		  if(obj[0]!=null){
				    		  dt.setMandateTripId(new Integer(obj[0].toString()));
				    		  }
				    		  if(obj[1]!=null){
				    			  dt.setMandateDetailId(new Integer(obj[1].toString()));
				    		  }
				    		  if(obj[2]!=null){
				    		  dt.setPointA(obj[2].toString());
				    		  }
				    		  if(obj[3]!=null){
				    		  dt.setPointB(obj[3].toString());
				    		  }
				    		  if(obj[4]!=null){
				    		  dt.setBillingRate(new Double(obj[4].toString()));
				    		  }
				    		  if(obj[5]!=null){
				    		  dt.setNightHoldingCharges(new Double(obj[5].toString()));
				    		  }
				    		  
				    		  list.add(dt);
				    	  }
				      }
				    
				      if(list!=null && list.size()>0){
				    	  ClientMandateDetailsTrip  fechdto=list.get(0);
				    	  if(fechdto.getMandateDetailId().equals(dto.getMandateDetailId())){
				    		 if(fechdto.getMandateTripId()!=null){
				    		  Session sessionss=sessionFactory.openSession();
				    		  Transaction tx1=sessionss.beginTransaction();
				    		  String sqls="UPDATE client_mandate_detail_trip SET point_a='"+dto.getPointA()+"' ,point_b='"+dto.getPointB()+"',billing_rate="+dto.getBillingRate()+",night_holding_charges="+dto.getNightHoldingCharges()+" ,modified_date=NOW(),modified_by="+dto.getCreatedBy()+" WHERE mandatedetailid="+fechdto.getMandateDetailId()+" AND mandatetripid="+fechdto.getMandateTripId()+";";
				    		    System.out.println(sqls);
				    		    sessionss.createSQLQuery(sqls).executeUpdate();
				    		    tx1.commit();
				    		    sessionss.clear();
				    		    sessionss.close();
				    	  }
				    	 }
				    	
				      }else{
		Session session=sessionFactory.openSession();
	    Transaction tx2=session.beginTransaction();
      	session.saveOrUpdate(dto);
    	tx2.commit();
		DetachedCriteria maxId = DetachedCriteria.forClass(ClientMandateDetailsTrip.class).setProjection(Projections.max("mandateTripId"));
		@SuppressWarnings("unchecked")
		List<ClientMandateDetailsTrip>  saveBack=session.createCriteria(ClientMandateDetailsTrip.class).add(Property.forName("mandateTripId").eq(maxId)).list();
		session.close();
		if(saveBack!=null &&  saveBack.size()>0){
		 return saveBack.get(0);
	     } 
		}
		 }catch(Exception er){
			 er.printStackTrace();
			 return null; 
		 }
		return null; 
		 
	}

	
	public ClientMandateDetailsTrip updateClientMandateDetailsTrip(
			ClientMandateDetailsTrip dto) {
	 
		return null;
	}

}
