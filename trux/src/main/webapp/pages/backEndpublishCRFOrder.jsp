<%@page import="com.trux.model.States"%>
<%@page import="com.trux.model.VehicleType"%>
<%@page import="com.trux.model.CRFOrder"%>
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
<title>Insert title here</title>

<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script>
<link href="/trux/resources/jtable/css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery.jtable.js" type="text/javascript"></script>

<script type="text/javascript">

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
$(function() {
    $(".js__p_start").simplePopup();
});
function getOrderId(orderId,crfSource,crfDestination,crfVehicleType){
	//alert(orderId);
	$("#depOrderId").val(orderId);
	$("#crfSource").val(crfSource);
	$("#crfDestination").val(crfDestination);
	$("#crfVehicleType").val(crfVehicleType);
	
}


function approveCRFOrder(){
	
	
	var stateId = document.getElementById("toStateId").value;
	 $.ajax({
	      type: "POST",
	      url: "/trux/transport/approveCRFOrder",
	      data:{
	    	  orderId : document.getElementById("depOrderId").value,
	    	  vehicleTypeId : document.getElementById("vehicleTypeId").value,
	    	  sourceCityId : document.getElementById("fromCityId").value,
	    	  destinationCityId : document.getElementById("toCityId").value,
	    	  deployTime : document.getElementById("deploymentDateTime").value,
	    	  freightRate : document.getElementById("freightRate").value
			  } ,
	      success: function(data) {
	    	  //window.location.reload();
	    	  //$("#approve").fadeOut();

	    	  window.open("/trux/transport/publishCRFOrder","_self");
	    	  
	    	  //alert(data.errorMesaage);
	    	  
	    	  
	      }
	    });
   return true;
	
}

function allOrderByDate(){
	 $.ajax({
	      type: "POST",
	      url: "/trux/transport/allOrderByDate",
	      data:{
	    	  date : document.getElementById("searchStartDate").value
			  } ,
	      success: function(data) {
	    	  
	    	  if (data) {
					$("#html").empty();
					
					if (data.data == null) {
						//alert("No record found !!!");
						$("#error").append("No record found for selected date range !!!");
					}

					var status = "";

					var len = data.data.length;
					var txt = "";
					
					txt += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
					txt += "<tr>";
						txt += "<th width='8%'>Order Id</th>";
						txt += "<th width='17%'>CRF Deployment Id</th>";
						txt += "<th width='11%'>CRF Source</th>";
						txt += "<th width='18%'>CRF Destination</th>";
						txt += "<th width='11%'>Vehicle Type</th>";
						txt += "<th width='11%'>Body Type</th>";
						txt += "<th width='8%'>Reporting Time</th>";
						txt += "<th width='8%'>Actions</th>";
					txt += "</tr>";
		              
		            if (len > 0) {
						
						for (var i = 0; i < len; i++) {
							if(data.data[i].bodyType != null){
							
							txt += "<tr>";
								txt += "<td>"+data.data[i].id+"</td>";
								txt += "<td>"+data.data[i].crfDeploymentId+"</td>";
								txt += "<td>"+data.data[i].crfSource+"</td>";
								txt += "<td>"+data.data[i].crfDestination+"</td>";
								txt += "<td>"+data.data[i].crfSourceVehicleType+"</td>";
								txt += "<td>"+data.data[i].bodyType+"</td>";
								//alert(data.data[i].reportingTime);
								//var d = new Date(data.data[i].reportingTime);
								//txt += "<td>"+d.getHours() +":"+ d.getMinutes()+"</td>";
								txt += "<td>"+data.data[i].reportingTime+"</td>";
								txt += "<td align='center' valign='middle'><a href='#' onclick=\"getOrderId('"+data.data[i].id+"','"+data.data[i].crfSource+"','"+data.data[i].crfDestination+"','"+data.data[i].crfSourceVehicleType+"')\" class='js__p_start'><span class='glyphicon glyphicon-edit' style='color:#1d9bbb;'></span></a></td>";
							txt += "</tr>";
							}
						}
					}
					
					txt += "</table>";
					
					if (txt != "") {
						$("#html").append(txt);
						$(".js__p_start").simplePopup();

					} else {
						$("#error").empty();
						$("#error").append("No record found for selected date !!!");
					}
					
	    	  }
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
	      }
	    });
  return true;
}


</script>

</head>
<body>

<div class="container">
  <div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
      <fieldset class="fieldset2 col-lg-12 borderManager table-responsive">
        <legend class="borderManager" style="width: 100%"> Publish CRF Orders</legend>
        
        <div id="message" style="color: red;"></div>
        
        <div class="row">
        
        	<div class="col-lg-3 col-md-3 col-sm-12">
              <div style="margin-bottom: 6px;"> Select Date</div>
              <input type="text" name="searchStartDate" id="searchStartDate" class="form-control input-sm" style='width: 100%; box-shadow: 1px 1px #AEAEAE; border: 1px solid #AEAEAE; background: url(../resources/images/calender2.png); background-repeat: no-repeat; background-position: right;' placeholder="Select Start Date (Alpha-numeric)" >
              
              <input type="submit" class="btn btn-danger btn-sm btn_nav1" id="save" value="Submit" onclick="allOrderByDate();"  style="
    margin-left: 285px;
    margin-top: -30px;
">
            </div>
            <div class="clearfix margin_10"></div>
        	
        
            <div class="res_tbl" id="html">
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <th width="8%">Order Id</th>
                  <th width="17%">CRF Deployment Id</th>
                  <th width="11%">CRF Source</th>
                  <th width="18%">CRF Destination</th>
                  <th width="11%">Vehicle Type</th>
                  <th width="11%">Body Type</th>
                  <th width="8%">Reporting Time</th>
                  <th width="8%">Actions</th>
                </tr>
                
                <% List<CRFOrder>  orders= (List<CRFOrder>) session.getAttribute("allOrder"); 
                                 if(orders != null && !orders.isEmpty()){
									for(int i=0; i<orders.size();i++){ %>
									<% if(orders.get(i).getBodyType() != null) {%>
									<tr>
										<td><%= orders.get(i).getId() %></td>
										<td><%= orders.get(i).getCrfDeploymentId() %></td>
										<td><%= orders.get(i).getCrfSource() %></td>
										<td><%= orders.get(i).getCrfDestination() %></td>
										<td><%= orders.get(i).getCrfSourceVehicleType() %></td>
										<td><%= orders.get(i).getBodyType() %></td>
										<td><%= orders.get(i).getReportingTime() %></td>
										<td align="center" valign="middle"><a href="#" onclick="getOrderId(<%=orders.get(i).getId() %>,'<%=orders.get(i).getCrfSource() %>','<%=orders.get(i).getCrfDestination() %>','<%=orders.get(i).getCrfSourceVehicleType() %>')" class="js__p_start"><span class="glyphicon glyphicon-edit" style="color:#1d9bbb;"></span></a></td>
									</tr>
									<%
									}
										}
										}
									%>
                
              </table>
            </div>
          </div>
        
      </fieldset>
    </div>
  </div>
  <div class="row ad-hoc-request" id="html"> </div>
  <div class="error-client-search" id="error"> </div>
</div>

<!-- pop-up-section here -->
<div class="popup js__popup js__slide_top" id="approve"> <a href="#" class="p_close js__p_close" title="Close"> <span></span><span></span> </a>
<div class="p_content">
<h2>Approval</h2>

<div class="p_content_row" style="display:none">
<input type="text" name="depOrderId" id="depOrderId">
</div>

<div class="p_content_row">
<label>CRF Source:</label> <input type="text" name="crfSource" id="crfSource" class="form-control input-sm" style="width:100%;background: #fff;" readonly>
              
</div>

<div class="p_content_row">
<label>CRF Destination:</label> <input type="text" name="crfDestination" id="crfDestination" class="form-control input-sm" style="width:100%;background: #fff;" readonly>
              
</div>

<div class="p_content_row">
<label>CRF Vehicle Type:</label> <input type="text" name="crfVehicleType" id="crfVehicleType" class="form-control input-sm" style="width:100%;background: #fff;" readonly>
              
</div>

<div class="p_content_row">
<label>Vehicle Type:</label> <select name="vehicleTypeId" id="vehicleTypeId" onchange="" class="input-sm" style="width:100%;" required>
              <option value="">--Select Vehicle Type--</option>
              <% List<VehicleType>  vList= (List<VehicleType>) session.getAttribute("allVehicleType"); 
                                 if(vList != null && !vList.isEmpty()){
									for(int i=0; i<vList.size();i++){ %>	
								 <option value="<%=vList.get(i).getId() %>"><%=vList.get(i).getVehicleName() %></option>
                                 <%	}}%>
              </select>
</div>

<div class="p_content_row">
<label>Deployment Date:</label> <input type="text" name="deploymentDateTime" id="deploymentDateTime" class="form-control input-sm form_datetime" style="width:100%;" placeholder="Select date & time">


<script type="text/javascript">
    $(".form_datetime").datetimepicker({
      //format: "dd MM yyyy - hh:ii",
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left"
    });
</script>



</div>

<div class="p_content_row">


<div class="p_content_row_col_left">


<label>Source State</label>
<select name="fromStateId" id="fromStateId" onchange="fillFromCity();" class="input-sm" style="width:100%;" required="">
					  <option value="">--Select State-- </option>
					  <% List<States>  stateList= (List<States>) session.getAttribute("statesList"); 
                                 if(stateList != null && !stateList.isEmpty()){
									for(int i=0; i<stateList.size();i++){ %>	
								 <option value="<%=stateList.get(i).getStateId() %>"><%=stateList.get(i).getStateName() %></option>
                                 <%	}}%>
</select>

<label>Source City</label>
<select name="fromCityId" id="fromCityId" onchange="" class="input-sm" style="width:100%;">
				  <option value="">--Select city--</option>
</select>

</div>




<div class="p_content_row_col_right">
<label>Destination State</label> <select name="toStateId" id="toStateId" onchange="fillToCity();" class="input-sm" style="width:100%;" required="">
					  <option value="">-- Select State-- </option>
					  <% List<States>  stateList2= (List<States>) session.getAttribute("statesList"); 
                                 if(stateList2 != null && !stateList2.isEmpty()){
									for(int i=0; i<stateList2.size();i++){ %>	
								 <option value="<%=stateList2.get(i).getStateId() %>"><%=stateList2.get(i).getStateName() %></option>
                                 <%	}}%>
				</select>
				
<label>Destination City</label> <select name="toCityId" id="toCityId" onchange="" class="input-sm" style="width:100%;">
				  <option value="">--Select city--</option>
</select>
</div>


</div>




<div class="p_content_row">
<label>Freight Rate:</label> <input type="text" name="freightRate" id="freightRate" class="form-control input-sm" style="width:100%;" placeholder="Enter Freight Rate">
</div>

<div class="p_content_row">
<!-- <input type="button" class="btn btn-danger btn-sm btn_nav1" id="" value="Close"> -->
<input type="button" class="btn btn-danger btn-sm btn_nav1 f_right" value="Approve" onclick="approveCRFOrder();">
</div>

</div>
</div> 
<div class="p_body js__p_body js__fadeout"></div>
 <!-- pop-up-section here -->
 
   

<script type="text/javascript">
		var dateToday = new Date();
		var dd = dateToday.getDate();
		var mm = dateToday.getMonth()+1; 
		var yyyy = dateToday.getFullYear();
		var toYears=parseInt(yyyy);
		
    $("#searchStartDate").datetimepicker({
      //format: "dd MM yyyy - hh:ii",
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left",
        timepicker:false,
	    format:'Y/m/d'
    });
</script>




<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://www.jqueryscript.net/demo/Minimalist-jQuery-Modal-Popup-Box-Plugin-Simple-Popup/demo/js/jquery.popup.js"></script>

</body>
</html>