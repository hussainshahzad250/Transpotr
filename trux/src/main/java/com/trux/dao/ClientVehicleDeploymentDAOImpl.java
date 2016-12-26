package com.trux.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.ClientVehicleDeployment;

public class ClientVehicleDeploymentDAOImpl implements ClientVehicleDeploymentDAO {
SessionFactory sessionFactory;
	
	public ClientVehicleDeployment saveClientVehicleDeployment(ClientVehicleDeployment dto) {
		try{
            Session session=sessionFactory.openSession();
		    Transaction tx=session.beginTransaction();
		    session.save(dto);
		    tx.commit();
		    DetachedCriteria maxId = DetachedCriteria.forClass(ClientVehicleDeployment.class).setProjection(Projections.max("id"));
		    @SuppressWarnings("unchecked")
			List<ClientVehicleDeployment>  vdeployList=session.createCriteria(ClientVehicleDeployment.class).add(Property.forName("id").eq(maxId)).list();
		    session.close();
		    if(vdeployList!=null && vdeployList.size()>0){
		    	return	vdeployList.get(0);
		    }
		     }catch(Exception er){}
		return null; 
	}
	
	 
	public void saveClientVehicleDeployments(StringBuilder dto) {
		try{
             
		    Session session=sessionFactory.openSession();
		    Transaction tx=session.beginTransaction();
		    int tota= session.createSQLQuery(dto.toString()).executeUpdate();
		    System.out.println(tota);
		    tx.commit();
		    session.close();
		   }catch(Exception er){
			   er.printStackTrace();
		   }
		 
	}
	
	@SuppressWarnings("unchecked")
	public int totalClientVehicleDeployments(ClientVehicleDeployment dto) {
		 int tota=0;
		try{
             
		    Session session=sessionFactory.openSession();
		    String sql="SELECT COUNT(mandate_detail_id) as total FROM client_vehicle_deployment WHERE  mandate_detail_id="+dto.getMandateDetailId()+" AND vehicle_type='"+dto.getVehicleType()+"' AND mandate_type='"+dto.getMandateType()+"' AND body_type='"+dto.getBodyType()+"';";
		    List<BigInteger>  idList=(List<BigInteger>)session.createSQLQuery(sql).list();
		      System.out.println(idList.toString());
		    
		    session.close();
		    if(idList!=null && idList.size()>0){
		    	for(BigInteger obj:idList){
		    		String to=obj.toString();
				     if(to!=null && !to.equals("")){
				    	 try{
				    	 if(to!="0"){
				    		 tota=	Integer.parseInt(to) ;
				    	 }
				    	 }catch(Exception er){er.printStackTrace();}
				     }
		    	}
		    }
		     
		    return tota;
		   }catch(Exception er){
			   er.printStackTrace();
		   }
		return tota;
		 
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
