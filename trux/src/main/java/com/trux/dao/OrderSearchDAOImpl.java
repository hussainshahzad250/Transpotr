package com.trux.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

import com.trux.model.CustomerBookingDetails;
import com.trux.model.OrderFilterReportsDto;
import com.trux.utils.DateFormaterUtils;

public class OrderSearchDAOImpl {
	
	@SuppressWarnings("deprecation")
	public List<CustomerBookingDetails> searchOrder(OrderFilterReportsDto dto ,SessionFactory sessionFactory ) {
		StatelessSession session = sessionFactory.openStatelessSession();
       
		try{
			
			Map<String, Object> parameters = new HashMap<String, Object>();
	  
		if(dto==null){			
			 StatelessSession statelessSession = sessionFactory.openStatelessSession();
			List<CustomerBookingDetails> list= new ArrayList<CustomerBookingDetails>();
			     try {
			    ScrollableResults scrollableResults = statelessSession.createQuery("FROM CustomerBookingDetails C order by C.createdDate desc").scroll(ScrollMode.FORWARD_ONLY);
			     while(scrollableResults.next()) {
			    	 CustomerBookingDetails demoEntity = (CustomerBookingDetails) scrollableResults.get()[0];
			    	 list.add(demoEntity);
			   } 
			   } finally {
				  statelessSession.close();
			     }
			     return list;
		}else{
			if(dto.getDstatus().equals("On-DemandCAPP")){
				boolean firstClause = true;
				StringBuffer queryBuf = new StringBuffer("FROM CustomerBookingDetails  a ");
				
				if (dto.getFrom() != null && dto.getFrom()!="" && dto.getTo() != null && dto.getTo() != "" ) {
					queryBuf.append(firstClause ? " where " : " and ");
					queryBuf.append("a.createdDate BETWEEN :startDate and :endDate");
					parameters.put("startDate", DateFormaterUtils.dateFormate(dto.getFrom()));
					parameters.put("endDate", dto.getTo());
					firstClause = false;
				}else{		
					if(dto.getFrom()!=null && dto.getFrom()!="") {
					queryBuf.append(firstClause ? " where " : " and ");
					queryBuf.append("a.createdDate >= :startDate");
					parameters.put("startDate",DateFormaterUtils.dateFormate(dto.getFrom()) );
					firstClause = false;
				}
				if (dto.getTo() != null && dto.getTo() !="") {
					queryBuf.append(firstClause ? " where " : " and ");
					queryBuf.append("a.createdDate <= :endDate");
					parameters.put("endDate", dto.getTo());
					firstClause = false;
				}}
				if (dto.getCity() != null && dto.getCity() !="") {
					queryBuf.append(firstClause ? " where " : " and ");
					queryBuf.append("a.city = :city");
					parameters.put("city", dto.getCity());
					firstClause = false;
				}
				if (dto.getStatus() != null && dto.getStatus() !="") {
					queryBuf.append(firstClause ? " where " : " and ");
					queryBuf.append("a.bookingStatus = :bookingStatus");
					parameters.put("bookingStatus", dto.getStatus());
					firstClause = false;
				}
				if (dto.getCustMobile() != null && dto.getCustMobile() != "") {
					queryBuf.append(firstClause ? " where " : " and ");
					queryBuf.append("a.custmerPhonenumber= :custmerPhonenumber");
					parameters.put("custmerPhonenumber", dto.getCustMobile());
					firstClause = false;
				}
				if (dto.getVehicleNo() != null && dto.getVehicleNo() != "") {
					queryBuf.append(firstClause ? " where " : " and ");
					queryBuf.append("a.vehicleNumber= :vehicleNumber");
					parameters.put("vehicleNumber", dto.getVehicleNo());
					firstClause = false;
				}
				if (dto.getVehicleType() != null && dto.getVehicleType() != "") {
					queryBuf.append(firstClause ? " where " : " and ");
					queryBuf.append("a.vehicleType= :vehicleType");
					parameters.put("vehicleType", dto.getVehicleType());
					firstClause = false;
				}
				if (dto.getDriverMobile() != null && dto.getDriverMobile() != "") {
					queryBuf.append(firstClause ? " where " : " and ");
					queryBuf.append("a.driverPhonenumber= :driverPhonenumber");
					parameters.put("driverPhonenumber", dto.getDriverMobile());
					firstClause = false;
				}
				 if (dto.getDstatus() != null && dto.getDstatus() != "") {
					queryBuf.append(firstClause ? " where " : " and ");
					queryBuf.append("( a.bookingMode = :bookingMode OR ( a.bookingMode IS NULL AND :bookingMode IS NULL ) )");
					//queryBuf.append("a.bookingMode is null");
				    parameters.put("bookingMode", "is null");
					firstClause = false;
				} 
			 	queryBuf.append(" order by a.createdDate desc");
				
				String hqlQuery = queryBuf.toString();
		        System.out.println("hqlQuery :"+hqlQuery);
				Query query = session.createQuery(hqlQuery);
				Iterator<String> iter = parameters.keySet().iterator();
				while (iter.hasNext()) {
					String name = (String) iter.next();
					Object value = parameters.get(name);
					System.out.println(value);
		            if(name=="startDate" || name=="endDate"){
					query.setParameter(name,new Date(value.toString()));
					}else
		            if(name=="bookingMode"){
						query.setParameter(name,null);
					} else{			
						query.setParameter(name, value);
						}
				} 
			System.out.println(query.toString());
				 ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);
				 List<CustomerBookingDetails> list= new ArrayList<CustomerBookingDetails>();
			     while(scrollableResults.next()) {
			    	 CustomerBookingDetails demoEntity = (CustomerBookingDetails) scrollableResults.get()[0];
			    	 list.add(demoEntity);
			   }  
			    session.close();
				return list;
			}else{
		boolean firstClause = true;
		StringBuffer queryBuf = new StringBuffer("FROM CustomerBookingDetails  a ");

		if (dto.getFrom() != null && dto.getFrom()!="" && dto.getTo() != null && dto.getTo() != "" ) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.createdDate BETWEEN :startDate and :endDate");
			parameters.put("startDate", DateFormaterUtils.dateFormate(dto.getFrom()));
			parameters.put("endDate", dto.getTo());
			firstClause = false;
		}else{		
			if(dto.getFrom()!=null && dto.getFrom()!="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.createdDate >= :startDate");
			parameters.put("startDate",DateFormaterUtils.dateFormate(dto.getFrom()) );
			firstClause = false;
		}
		if (dto.getTo() != null && dto.getTo() !="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.createdDate <= :endDate");
			parameters.put("endDate", dto.getTo());
			firstClause = false;
		}}
		if (dto.getCity() != null && dto.getCity() !="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.city = :city");
			parameters.put("city", dto.getCity());
			firstClause = false;
		}
		if (dto.getStatus() != null && dto.getStatus() !="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.bookingStatus = :bookingStatus");
			parameters.put("bookingStatus", dto.getStatus());
			firstClause = false;
		}
		if (dto.getCustMobile() != null && dto.getCustMobile() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.custmerPhonenumber= :custmerPhonenumber");
			parameters.put("custmerPhonenumber", dto.getCustMobile());
			firstClause = false;
		}
		if (dto.getVehicleNo() != null && dto.getVehicleNo() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.vehicleNumber= :vehicleNumber");
			parameters.put("vehicleNumber", dto.getVehicleNo());
			firstClause = false;
		}
		if (dto.getVehicleType() != null && dto.getVehicleType() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.vehicleType= :vehicleType");
			parameters.put("vehicleType", dto.getVehicleType());
			firstClause = false;
		}
		if (dto.getDriverMobile() != null && dto.getDriverMobile() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.driverPhonenumber= :driverPhonenumber");
			parameters.put("driverPhonenumber", dto.getDriverMobile());
			firstClause = false;
		}
		if (dto.getDstatus() != null && dto.getDstatus() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.dstatus= :dstatus");
			parameters.put("dstatus", dto.getDstatus());
			firstClause = false;
		}
		
		queryBuf.append(" order by a.createdDate desc");
		
		String hqlQuery = queryBuf.toString();
        //System.out.println("hqlQuery :"+hqlQuery);
		Query query = session.createQuery(hqlQuery);
		Iterator<String> iter = parameters.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			Object value = parameters.get(name);
			//System.out.println(value);
            if(name=="startDate" || name=="endDate"){
			query.setParameter(name,new Date(value.toString()));
			}else{			
			query.setParameter(name, value);
			}
		} 
		 ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);
		 List<CustomerBookingDetails> list= new ArrayList<CustomerBookingDetails>();
	     while(scrollableResults.next()) {
	    	 CustomerBookingDetails demoEntity = (CustomerBookingDetails) scrollableResults.get()[0];
	    	 list.add(demoEntity);
	   }  
	    session.close();
		return list; 
		}}}catch(Exception er){System.out.println(er.getMessage().toString());
		session.close();
		return null;}	
	}

	
	@SuppressWarnings("deprecation")
	public List<CustomerBookingDetails> backEndBooigRideSearchOrder(OrderFilterReportsDto dto ,SessionFactory sessionFactory ) {
		try{
			
			Map<String, Object> parameters = new HashMap<String, Object>();
	  
		if(dto==null){			
			 
			List<CustomerBookingDetails> list= new ArrayList<CustomerBookingDetails>();
			 StatelessSession statelessSession = sessionFactory.openStatelessSession();
			     try {
			    ScrollableResults scrollableResults = statelessSession.createQuery("FROM CustomerBookingDetails C where C.bookingMode='B' order by C.createdDate desc").scroll(ScrollMode.FORWARD_ONLY);
			     while(scrollableResults.next()) {
			    	 CustomerBookingDetails demoEntity = (CustomerBookingDetails) scrollableResults.get()[0];
			    	 list.add(demoEntity);
			   } 
			   } finally {
				  statelessSession.close();
			     }
			     return list;
		}else{
		StatelessSession session = sessionFactory.openStatelessSession();
		boolean firstClause = true;
		StringBuffer queryBuf = new StringBuffer("FROM CustomerBookingDetails  a ");

		if (dto.getFrom() != null && dto.getFrom()!="" && dto.getTo() != null && dto.getTo() != "" ) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.createdDate BETWEEN :startDate and :endDate");
			parameters.put("startDate", DateFormaterUtils.dateFormate(dto.getFrom()));
			parameters.put("endDate", dto.getTo());
			firstClause = false;
		}else{		
			if(dto.getFrom()!=null && dto.getFrom()!="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.createdDate >= :startDate  and  a.bookingMode='B'");
			parameters.put("startDate",DateFormaterUtils.dateFormate(dto.getFrom()) );
			firstClause = false;
		}
		if (dto.getTo() != null && dto.getTo() !="") {
			queryBuf.append(firstClause ? " where " : " and  ");
			queryBuf.append("a.createdDate <= :endDate");
			parameters.put("endDate", dto.getTo());
			firstClause = false;
		}}
		if (dto.getCity() != null && dto.getCity() !="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.city = :city");
			parameters.put("city", dto.getCity());
			firstClause = false;
		}
		if (dto.getStatus() != null && dto.getStatus() !="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.bookingStatus = :bookingStatus");
			parameters.put("bookingStatus", dto.getStatus());
			firstClause = false;
		}
		if (dto.getCustMobile() != null && dto.getCustMobile() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.custmerPhonenumber= :custmerPhonenumber");
			parameters.put("custmerPhonenumber", dto.getCustMobile());
			firstClause = false;
		}
		if (dto.getVehicleNo() != null && dto.getVehicleNo() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.vehicleNumber= :vehicleNumber");
			parameters.put("vehicleNumber", dto.getVehicleNo());
			firstClause = false;
		}
		if (dto.getVehicleType() != null && dto.getVehicleType() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.vehicleType= :vehicleType");
			parameters.put("vehicleType", dto.getVehicleType());
			firstClause = false;
		}
		if (dto.getDriverMobile() != null && dto.getDriverMobile() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.driverPhonenumber= :driverPhonenumber");
			parameters.put("driverPhonenumber", dto.getDriverMobile());
			firstClause = false;
		}
		if (dto.getBookingMode() != null && dto.getBookingMode() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.bookingMode= :bookingMode");
			parameters.put("bookingMode", dto.getBookingMode());
			firstClause = false;
		}
		if (dto.getDstatus() != null && dto.getDstatus() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.dstatus= :dstatus");
			parameters.put("dstatus", dto.getDstatus());
			firstClause = false;
		}
		
		queryBuf.append(" order by a.createdDate desc");
		
		String hqlQuery = queryBuf.toString();
        System.out.println("hqlQuery :"+hqlQuery);
		Query query = session.createQuery(hqlQuery);
		Iterator<String> iter = parameters.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			Object value = parameters.get(name);
			//System.out.println(value);
            if(name=="startDate" || name=="endDate"){
			query.setParameter(name,new Date(value.toString()));
			}else{			
			query.setParameter(name, value);
			}
		} 
		 ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);
		 List<CustomerBookingDetails> list= new ArrayList<CustomerBookingDetails>();
	     while(scrollableResults.next()) {
	    	 CustomerBookingDetails demoEntity = (CustomerBookingDetails) scrollableResults.get()[0];
	    	 list.add(demoEntity);
	   }  
	    session.close();
		return list; 
		}}catch(Exception er){System.out.println(er.getMessage().toString());return null;}	
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<CustomerBookingDetails> searchOrderWithPaging(int numberofpage,int pagenumber,OrderFilterReportsDto dto ,SessionFactory sessionFactory ) {
		try{Map<String, Object> parameters = new HashMap<String, Object>();		
		if(dto==null){
			Session session = sessionFactory.openSession();
			StringBuffer queryBuf = new StringBuffer("FROM CustomerBookingDetails  a ");
			queryBuf.append(" order by a.createdDate desc");
			//System.out.println(queryBuf.toString());
		    Query query = session.createQuery(queryBuf.toString()).setFirstResult(numberofpage).setMaxResults(pagenumber);
		    List<CustomerBookingDetails> list=query.list();
			session.flush();session.clear();
			session.close();
			return list; 
		}else{
		Session session = sessionFactory.openSession();
		boolean firstClause = true;
		StringBuffer queryBuf = new StringBuffer("FROM CustomerBookingDetails  a ");

		if (dto.getFrom() != null && dto.getFrom()!="" && dto.getTo() != null && dto.getTo() != "" ) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.createdDate BETWEEN :startDate and :endDate");
			parameters.put("startDate", DateFormaterUtils.dateFormate(dto.getFrom()));
			parameters.put("endDate", dto.getTo());
			firstClause = false;
		}else{		
			if(dto.getFrom()!=null && dto.getFrom()!="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.createdDate >= :startDate");
			parameters.put("startDate",DateFormaterUtils.dateFormate(dto.getFrom()));
			firstClause = false;
		}
		if (dto.getTo() != null && dto.getTo() !="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.createdDate <= :endDate");
			parameters.put("endDate", dto.getTo());
			firstClause = false;
			}}
			if (dto.getCity() != null && dto.getCity() !="") {
				queryBuf.append(firstClause ? " where " : " and ");
				queryBuf.append("a.city = :city");
				parameters.put("city", dto.getCity());
				firstClause = false;
			}
			if (dto.getStatus() != null && dto.getStatus() !="") {
				queryBuf.append(firstClause ? " where " : " and ");
				queryBuf.append("a.bookingStatus = :bookingStatus");
				parameters.put("bookingStatus", dto.getStatus());
				firstClause = false;
			}
			if (dto.getCustMobile() != null && dto.getCustMobile() != "") {
				queryBuf.append(firstClause ? " where " : " and ");
				queryBuf.append("a.custmerPhonenumber= :custmerPhonenumber");
				parameters.put("custmerPhonenumber", dto.getCustMobile());
				firstClause = false;
			}
			if (dto.getVehicleNo() != null && dto.getVehicleNo() != "") {
				queryBuf.append(firstClause ? " where " : " and ");
				queryBuf.append("a.vehicleNumber= :vehicleNumber");
				parameters.put("vehicleNumber", dto.getVehicleNo());
				firstClause = false;
			}
			if (dto.getVehicleType() != null && dto.getVehicleType() != "") {
				queryBuf.append(firstClause ? " where " : " and ");
				queryBuf.append("a.vehicleType= :vehicleType");
				parameters.put("vehicleType", dto.getVehicleType());
				firstClause = false;
			}
			if (dto.getDriverMobile() != null && dto.getDriverMobile() != "") {
				queryBuf.append(firstClause ? " where " : " and ");
				queryBuf.append("a.driverPhonenumber= :driverPhonenumber");
				parameters.put("driverPhonenumber", dto.getDriverMobile());
				firstClause = false;
			}
			if (dto.getDstatus() != null && dto.getDstatus() != "") {
				queryBuf.append(firstClause ? " where " : " and ");
				queryBuf.append("a.dstatus= :dstatus");
				parameters.put("dstatus", dto.getDstatus());
				firstClause = false;
			}
			
		queryBuf.append(" order by a.createdDate desc");
		
		String hqlQuery = queryBuf.toString();
        //System.out.println("hqlQuery :"+hqlQuery);
		Query query = session.createQuery(hqlQuery).setFirstResult(numberofpage).setMaxResults(pagenumber);
		Iterator<String> iter = parameters.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			Object value = parameters.get(name);
			//System.out.println(value);
            if(name=="startDate" || name=="endDate"){
				query.setParameter(name,new Date(value.toString()));
			}else{			
			query.setParameter(name, value);
			}
		} 
		 List<CustomerBookingDetails> list=query.list();
			session.flush();session.clear();
			session.close();
			return list; 
		}}catch(Exception er){System.out.println(er.getMessage().toString());return null;}	
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Object[]> searchRevenue(OrderFilterReportsDto dto,SessionFactory sessionFactory) {
		try{Map<String, Object> parameters = new HashMap<String, Object>();
		if(dto==null){
			Session session = sessionFactory.openSession();
			StringBuffer queryBuf = new StringBuffer("select date(a.createdDate),count(a) as TotalCount,SUM(a.totalFare) as totalFare FROM CustomerBookingDetails  a ");
			queryBuf.append(" where a.totalFare is not null group by date(a.createdDate) order by date(a.createdDate) desc");
			//System.out.println(queryBuf.toString());
		    Query query = session.createQuery(queryBuf.toString());
		    List<Object[]> list=query.list();
			session.flush();session.clear();
			session.close();
			return list; 
		}else if(dto.getCity()==""  && dto.getFrom()==""  && dto.getTo()==""  && dto.getVehicleType()==""){
			Session session = sessionFactory.openSession();
			StringBuffer queryBufs = new StringBuffer("select date(a.createdDate),count(a) as TotalCount,SUM(a.totalFare) as totalFare FROM CustomerBookingDetails  a ");
			queryBufs.append(" where a.totalFare is not null group by date(a.createdDate) order by date(a.createdDate) desc");
			//System.out.println(queryBufs.toString());
		    Query query = session.createQuery(queryBufs.toString());
		    List<Object[]> list=query.list();
			session.flush();session.clear();
			session.close();
			return list;
	 	}else{
	    Session session = sessionFactory.openSession();
		boolean firstClause = true;
		StringBuffer queryBuf = new StringBuffer("select date(a.createdDate),count(a) as TotalCount,SUM(a.totalFare) as totalFare FROM CustomerBookingDetails  a ");
		if (dto.getFrom() != null && dto.getFrom()!="" && dto.getTo() != null && dto.getTo() != "" ) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.createdDate BETWEEN :startDate and :endDate");
			parameters.put("startDate", DateFormaterUtils.dateFormate(dto.getFrom()));
			parameters.put("endDate", dto.getTo());
			firstClause = false;
		}else{		
			if(dto.getFrom()!=null && dto.getFrom()!="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.createdDate >= :startDate");
			parameters.put("startDate", DateFormaterUtils.dateFormate(dto.getFrom()));
			firstClause = false;
		}
		if (dto.getTo() != null && dto.getTo() !="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.createdDate <= :endDate");
			parameters.put("endDate", dto.getTo());
			firstClause = false;
		}}
		if (dto.getCity() != null && dto.getCity() !="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.city = :city");
			parameters.put("city", dto.getCity());
			firstClause = false;
		}
		if (dto.getStatus() != null && dto.getStatus() !="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.bookingStatus = :bookingStatus");
			parameters.put("bookingStatus", dto.getStatus());
			firstClause = false;
		}
		if (dto.getCustMobile() != null && dto.getCustMobile() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.custmerPhonenumber= :custmerPhonenumber");
			parameters.put("custmerPhonenumber", dto.getCustMobile());
			firstClause = false;
		}
		if (dto.getVehicleNo() != null && dto.getVehicleNo() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.vehicleNumber= :vehicleNumber");
			parameters.put("vehicleNumber", dto.getVehicleNo());
			firstClause = false;
		}
		if (dto.getVehicleType() != null && dto.getVehicleType() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.vehicleType= :vehicleType");
			parameters.put("vehicleType", dto.getVehicleType());
			firstClause = false;
		}
		if (dto.getDriverMobile() != null && dto.getDriverMobile() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.driverPhonenumber= :driverPhonenumber");
			parameters.put("driverPhonenumber", dto.getDriverMobile());
			firstClause = false;
		}
		if (dto.getDstatus() != null && dto.getDstatus() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.dstatus= :dstatus");
			parameters.put("dstatus", dto.getDstatus());
			firstClause = false;
		}
		
		queryBuf.append(" and a.totalFare is not null group by date(a.createdDate) order by date(a.createdDate) desc");
		String hqlQuery = queryBuf.toString();
		//System.out.println("hqlQuery : "+hqlQuery);
		Query query = session.createQuery(hqlQuery);
		Iterator<String> iter = parameters.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			Object value = parameters.get(name);
			//System.out.println(value);
            if(name=="startDate" || name=="endDate"){
		    query.setParameter(name,new Date(value.toString()));
			}else{			
			query.setParameter(name, value);
			}
		}
		List<Object[]> list=query.list();
		session.flush();session.clear();
		session.close();
		return list; 
		}}catch(Exception er){System.out.println(er.getMessage().toString());return null;}	
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Object[]> searchRevenueWithPaging(int numberofpage,int pagenumber,OrderFilterReportsDto dto,SessionFactory sessionFactory) {
		try{Map<String, Object> parameters = new HashMap<String, Object>();
		if(dto==null){
			Session session = sessionFactory.openSession();
			StringBuffer queryBuf = new StringBuffer("select date(a.createdDate),count(a) as TotalCount,SUM(a.totalFare) as totalFare FROM CustomerBookingDetails  a ");
			queryBuf.append(" where a.totalFare is not null group by date(a.createdDate) order by date(a.createdDate) desc");
			//System.out.println(queryBuf.toString());
		    Query query = session.createQuery(queryBuf.toString());
		    List<Object[]> list=query.setFirstResult(numberofpage).setMaxResults(pagenumber).list();
			session.flush();
			session.clear();
			session.close();
			return list; 
		}else if(dto.getDstatus()==""  && dto.getFrom()==""  && dto.getTo()==""  && dto.getVehicleType()==""){
			Session session = sessionFactory.openSession();
			StringBuffer queryBufs = new StringBuffer("select date(a.createdDate),count(a) as TotalCount,SUM(a.totalFare) as totalFare FROM CustomerBookingDetails  a ");
			queryBufs.append(" where a.totalFare is not null group by date(a.createdDate) order by date(a.createdDate) desc");
			//System.out.println(queryBufs.toString());
		    Query query = session.createQuery(queryBufs.toString());
		    List<Object[]> list=query.setFirstResult(numberofpage).setMaxResults(pagenumber).list();
			session.flush();
			session.clear();
			session.close();
			return list;
	 	}else{
	 	Session session = sessionFactory.openSession();
		boolean firstClause = true;
		StringBuffer queryBuf = new StringBuffer("select date(a.createdDate),count(a) as TotalCount,SUM(a.totalFare) as totalFare FROM CustomerBookingDetails  a ");
		if (dto.getFrom() != null && dto.getFrom()!="" && dto.getTo() != null && dto.getTo() != "" ) {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.createdDate BETWEEN :startDate and :endDate");
			parameters.put("startDate", DateFormaterUtils.dateFormate(dto.getFrom()));
			parameters.put("endDate", dto.getTo());
			firstClause = false;
		}else{		
			if(dto.getFrom()!=null && dto.getFrom()!="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.createdDate >= :startDate");
			parameters.put("startDate", DateFormaterUtils.dateFormate(dto.getFrom()));
			firstClause = false;
		}
		if (dto.getTo() != null && dto.getTo() !="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.createdDate <= :endDate");
			parameters.put("endDate", dto.getTo());
			firstClause = false;
		}}
		if (dto.getCity() != null && dto.getCity() !="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.city = :city");
			parameters.put("city", dto.getCity());
			firstClause = false;
		}
		if (dto.getStatus() != null && dto.getStatus() !="") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.bookingStatus = :bookingStatus");
			parameters.put("bookingStatus", dto.getStatus());
			firstClause = false;
		}
		if (dto.getCustMobile() != null && dto.getCustMobile() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.custmerPhonenumber= :custmerPhonenumber");
			parameters.put("custmerPhonenumber", dto.getCustMobile());
			firstClause = false;
		}
		if (dto.getVehicleNo() != null && dto.getVehicleNo() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.vehicleNumber= :vehicleNumber");
			parameters.put("vehicleNumber", dto.getVehicleNo());
			firstClause = false;
		}
		if (dto.getVehicleType() != null && dto.getVehicleType() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.vehicleType= :vehicleType");
			parameters.put("vehicleType", dto.getVehicleType());
			firstClause = false;
		}
		if (dto.getDriverMobile() != null && dto.getDriverMobile() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.driverPhonenumber= :driverPhonenumber");
			parameters.put("driverPhonenumber", dto.getDriverMobile());
			firstClause = false;
		}
		if (dto.getDstatus() != null && dto.getDstatus() != "") {
			queryBuf.append(firstClause ? " where " : " and ");
			queryBuf.append("a.dstatus= :dstatus");
			parameters.put("dstatus", dto.getDriverMobile());
			firstClause = false;
		}
		queryBuf.append(" and a.totalFare is not null group by date(a.createdDate) order by date(a.createdDate) desc");
		String hqlQuery = queryBuf.toString();
		System.out.println("hqlQuery :"+hqlQuery);
		Query query = session.createQuery(hqlQuery);
		Iterator<String> iter = parameters.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			Object value = parameters.get(name);
			//System.out.println(value);
            if(name=="startDate" || name=="endDate"){
			query.setParameter(name,new Date(value.toString()));
			}else{			
			query.setParameter(name, value);
			}
		}
		   List<Object[]> list= query.setFirstResult(numberofpage).setMaxResults(pagenumber).list();
		    session.flush();
		    session.clear();
			session.close();
			return list;
		}}catch(Exception er){System.out.println(er.getMessage().toString());return null;}	
	}
	
	public static void main(String[] args) {
		OrderSearchDAOImpl dao=new OrderSearchDAOImpl();
		OrderFilterReportsDto dto=null;//new OrderFilterReportsDto();
		SessionFactory sessionFactory =null;
	/*	dto.setFrom("2015-05-04 00:00");
		dto.setTo("2015-05-12");
		dto.setCity("Delhi");*/
		/*dto.setCustMobile("98989889992");
		dto.setDriverMobile("8882397104");
		dto.setVehicleNo("UP-XU0034");
		dto.setStatus("Pending");*/
		//dao.searchOrder(dto,sessionFactory);
		dao.searchRevenue(dto,sessionFactory);
	}

}
