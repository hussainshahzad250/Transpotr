<%@page import="com.trux.model.States"%>
<%@page import="com.trux.model.VehicleType"%>
<%@page import="java.util.List"%>
<%@page import="com.trux.model.ControllerDAOTracker"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Transporter Order</title>

<script>

function fillFromCity()
{
    var stateId = document.getElementById("fromStateId").value;
	 $.ajax({
	      type: "POST",
	      url: "/trux/reg/getCitiesByState",
	      data:{
			  state:stateId
			  } ,
	      success: function(data) {
	    	  document.getElementById("fromCityId").innerHTML = data;
	    	  document.getElementById("fromCityId").value.innerHTML=data;
	      }
	    });
    return true;
}

function fillToCity()
{
    var stateId = document.getElementById("toStateId").value;
	 $.ajax({
	      type: "POST",
	      url: "/trux/reg/getCitiesByState",
	      data:{
			  state:stateId
			  } ,
	      success: function(data) {
	    	  document.getElementById("toCityId").innerHTML = data;
	    	  document.getElementById("toCityId").value.innerHTML=data;
	      }
	    });
    return true;
}

function api()
{
    var SelfromCityId = document.getElementById("fromCityId").value;
    var SeltoCityId = document.getElementById("toCityId").value;
    var Selstatus = document.getElementById("status").value;
	 $.ajax({
	      type: "GET",
	      url: "trux/transport/approveAllCRFOrder",
	      data:{
	    	  fromCityId:SelfromCityId,
	    	  toCityId:SeltoCityId,
	    	  status:Selstatus
			  } ,
	      success: function(data) {
	    	  
	    	  
	    	  if(data){
					$("#html").empty();
					$("#error").empty();
					
					if (data.data == null){
						//alert("No record found !!!");
						$("#error").append("No record found !!!");
					}
					
		            var len = data.data.length;
		            var txt = "";
		            var txt2 = "";
		            
		            if(len > 0){
		            	alert(len);
		            	
		            	txt += "<tr>";
						txt += "<th width=\"8%\">Order Id</th>";
						txt += "<th width=\"18%\">Vehicle Type</th>";
						txt += "<th width=\"12%\">Body Type</th>";
						txt += "<th width=\"20%\" colspan=\"2\"><span class=\"date_lft\">Date</span><span class=\"time_rgt_hed\">Time</span></th>";
										<!--<th width="9%">Time</th> -->
						txt += "<th width=\"12%\">From</th>";
						txt += "<th width=\"12%\">To</th>";
						txt += "<th width=\"9%\">Amount</th>";
						txt += "<th width=\"9%\">Status</th>";
						txt += "</tr>";
		                for(var i=0;i<len;i++){
		                        
		                	txt += "<tr>";
							txt += "<td>#"+data.data[i].orderId+"</td>";
							txt += "<td>"+data.data[i].vehcleTypeName+"</td>";
							txt += "<td>"+data.data[i].vehicleBody+"</td>";
							txt += "<td colspan=\"2\"><span>"+data.data[i].dateConvert+"</span></td>";
								/* <span class="time_rgt">09:00 AM</span> */
							txt += "<td>"+data.data[i].fromCityName+"</td>";
							txt += "<td>"+data.data[i].toCityName+"</td>";
							txt += "<td>"+data.data[i].amount+"</td>";
							txt += "<td>"+data.data[i].status+"</td>";
							txt += "</tr>";
		                        
		                }
		                if(txt != ""){
		                	$("#html").append(txt);
		                	
		                } else {
		                	$("#error").empty();
		                	$("#error").append("No record found !!!");
		                	//alert("No record found !!!");
		                }
		            } else {
		            	//alert(len);
		            	//alert("No record found !!!");
		            	$("#error").empty();
		            	$("#error").append("No record found !!!");
		            }
		        }
	    	  
	    	  
	    	  
	    	  
	    	  document.getElementById("html").innerHTML = data;
	    	  document.getElementById("html").value.innerHTML=data;
	      }
	    });
    return true;
}

/* function dateConvert(input){
	Timestamp timestamp = new Timestamp(input);
    Date date = new Date(timestamp.getTime());

    // S is the millisecond
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm:ss");

    return simpleDateFormat.format(timestamp).toString();
} */

</script>

</head>
<body>

<div class="container">
  <div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
      <fieldset class="fieldset2 col-lg-12 borderManager table-responsive">
        <legend class="borderManager" style="width: 100%"> Search Order</legend>
        <!-- <form id="" action="" method="post" class="form-inline" onsubmit="return validateForm();"> -->

          <div class="row highlight" style="margin-top: -2%;">
            <div class="col-lg-3 col-md-3 col-sm-6"> <span style="float: left; margin:15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">From</span>
              <div class="clearfix margin_05"></div>
              <div style="margin-bottom: 6px;"> State<span style="color: red;">*</span> </div>
              <select name="fromStateId" id="fromStateId" onchange="fillFromCity();" class="input-sm" style="width:100%;" required="">
					  <option value="">--Select State-- </option>
					  <% List<States>  stateList= (List<States>) session.getAttribute("statesList"); 
                                 if(stateList != null && !stateList.isEmpty()){
									for(int i=0; i<stateList.size();i++){ %>	
								 <option value="<%=stateList.get(i).getStateId() %>"><%=stateList.get(i).getStateName() %></option>
                                 <%	}}%>
				</select>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6"> <span style="float: left; margin:15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">&nbsp;</span>
              <div class="clearfix margin_05"></div>
              <div style="margin-bottom: 6px;"> City<span style="color: red;">*</span> </div>
              <select name="fromCityId" id="fromCityId" onchange="" class="input-sm" style="width:100%;">
				  <option value="">--Select city--</option>
				</select>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6"> <span style="float: left; margin:15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">To</span>
              <div class="clearfix margin_05"></div>
              <div style="margin-bottom: 6px;"> State<span style="color: red;">*</span> </div>
              <select name="toStateId" id="toStateId" onchange="fillToCity();" class="input-sm" style="width:100%;" required="">
					  <option value="">-- Select State-- </option>
					  <% List<States>  stateList2= (List<States>) session.getAttribute("statesList"); 
                                 if(stateList2 != null && !stateList2.isEmpty()){
									for(int i=0; i<stateList2.size();i++){ %>	
								 <option value="<%=stateList2.get(i).getStateId() %>"><%=stateList2.get(i).getStateName() %></option>
                                 <%	}}%>
				</select>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6"> <span style="float: left; margin:15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">&nbsp;</span>
              <div class="clearfix margin_05"></div>
              <div style="margin-bottom: 6px;"> City<span style="color: red;">*</span> </div>
              <select name="toCityId" id="toCityId" onchange="" class="input-sm" style="width:100%;">
				  <option value="">--Select city--</option>
				</select>
            </div>
          </div>
          <!-- end 1st row -->
          
          <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6">
              <div style="margin-bottom: 6px;"> Status<span style="color: red;">*</span> </div>
              <select name="status" id="status" class="input-sm" style="width: 100%;" required>
                <option value="">--Select Status--</option>
                <option value=All>All</option>
                <option value="Pending">Pending</option>
                <option value="Confirmed">Confirmed</option>
                <option value="Cancelled">Cancelled</option>
              </select>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6"> <span style="float: left; margin:0 0 2px 0px; font-weight: bold; font-size: 16px; color: #525353;">&nbsp;</span>
              <div class="clearfix margin_05"></div>
              <input type="submit" class="btn btn-danger btn-sm btn_nav1" id="save" value="Submit" onclick="api();">
            </div>
          </div>
          <!-- end 1st row -->
          
          <div class="clearfix margin_10"></div>
          <div class="clearfix margin_10"></div>


						<div class="row">
							<div class="res_tbl">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" id="html">
									<tr>
										<th width="8%">Order Id</th>
										<th width="18%">Vehicle Type</th>
										<th width="12%">Body Type</th>
										<th width="20%" colspan="2"><span class="date_lft">Date</span><span
											class="time_rgt_hed">Time</span></th>
										<th width="9%">Time</th>
										<th width="12%">From</th>
										<th width="12%">To</th>
										<th width="9%">Amount</th>
										<th width="9%">Status</th>
									</tr>
									<tr>
										<td>001</td>
										<td>Eeco</td>
										<td>Containerized</td>
										<td colspan="2"><span class="date_lft">20-04-2016</span>
											<span class="time_rgt">09:00 AM</span></td>
										<td>Delhi</td>
										<td>Jaipur</td>
										<td>25000</td>
										<td>Pending</td>
									</tr>
									<tr>
										<td>002</td>
										<td>Champion</td>
										<td>Open</td>
										<td colspan="2"><span class="date_lft">20-04-2016</span>
											<span class="time_rgt">09:00 AM</span></td>
										<td>Delhi</td>
										<td>Bangalore</td>
										<td>35000</td>
										<td>Pending</td>
									</tr>
									<tr>
										<td>001</td>
										<td>Eeco</td>
										<td>Containerized</td>
										<td colspan="2"><span class="date_lft">20-04-2016</span>
											<span class="time_rgt">09:00 AM</span></td>
										<td>Delhi</td>
										<td>Jaipur</td>
										<td>25000</td>
										<td>Pending</td>
									</tr>
									<tr>
										<td>002</td>
										<td>Champion</td>
										<td>Open</td>
										<td colspan="2"><span class="date_lft">20-04-2016</span>
											<span class="time_rgt">09:00 AM</span></td>
										<td>Delhi</td>
										<td>Bangalore</td>
										<td>35000</td>
										<td>Pending</td>
									</tr>
								</table>
							</div>
						</div>


					<!-- </form> -->
        <div id="message" style="color: red;"></div>
      </fieldset>
    </div>
  </div>
  <div class="error-client-search" id="error"> </div>
</div>

</body>
</html>