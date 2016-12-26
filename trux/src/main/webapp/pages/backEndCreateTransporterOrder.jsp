<%@page import="com.trux.model.States"%>
<%@page import="com.trux.model.VehicleType"%>
<%@page import="java.util.List"%>
<%@page import="com.trux.model.ControllerDAOTracker"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Order</title>

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

function callSaveClientOrder(){
	var SelvehicleTypeId = document.getElementById("vehicleTypeId").value;
	var SelvehicleBody = document.getElementById("vehicleBody").value;
	var SeldeployDateTime = document.getElementById("deployDateTime").value;
	var SelfromCityId = document.getElementById("fromCityId").value;
	var SeltoCityId = document.getElementById("toCityId").value;
	var Selprice = document.getElementById("price").value;
	 $.ajax({
	      type: "POST",
	      url: "/trux/transport/saveClientOrder",
	      data:{
	    	  vehicleTypeId:SelvehicleTypeId,
	    	  vehicleBody:SelvehicleBody,
	    	  deployDateTime:SeldeployDateTime,
	    	  fromCityId:SelfromCityId,
	    	  toCityId:SeltoCityId,
	    	  price:Selprice
			  } ,
	      success: function(data) {
	    	  if(data){
	    		  $("#message").empty();
	    		  window.location.reload();
	    		  
	    			  $("#message").append(data.errorMesaage);
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
        <legend class="borderManager" style="width: 100%"> Create Client Order </legend> <a href="/trux/transport/searchTransporterOrder" class="btn btn-danger btn-sm right-btn btn_nav1 right_btn_update" style="color: black;padding-right: 20px;padding-top: -2px;/* float: right; */"><b>Search Order</b></a>
         <!-- <form id="" action="trux/transport/saveClientOrders" method="POST" class="form-inline" onsubmit="return validateForm();"> --> 
          
          <div class="row" style="margin-top: -2%;">
          
            <div class="col-lg-3 col-md-3 col-sm-12">
              <div style="margin-bottom: 6px;"> Vehicle Type<span style="color: red;">*</span> </div>
              <select name="vehicleTypeId" id="vehicleTypeId" onchange="" class="input-sm" style="width:100%;" required>
              <option value="">--Select Vehicle Type--</option>
              <% List<VehicleType>  vList= (List<VehicleType>) session.getAttribute("allVehicleType"); 
                                 if(vList != null && !vList.isEmpty()){
									for(int i=0; i<vList.size();i++){ %>	
								 <option value="<%=vList.get(i).getId() %>"><%=vList.get(i).getVehicleName() %></option>
                                 <%	}}%>
              </select>
            </div>
            
            <div class="col-lg-3 col-md-3 col-sm-12">
              <div style="margin-bottom: 6px;"> Body Type <span style="color: red;">*</span> </div>
              <select name="vehicleBody" id="vehicleBody" class="input-sm" style="width: 100%;" required>
                <option value="">--Select Body Type--</option>
                <option value="Open body">Open body</option>
                <option value="Containerized">Containerized</option>
              </select>
            </div>
            
            <div class="col-lg-3 col-md-3 col-sm-12">
              <div style="margin-bottom: 6px;"> Date <span style="color: red;">*</span> </div>
              <input type="text" name="deployDateTime" id="deployDateTime" class="form-control input-sm" style="width:100%;" placeholder="Opening Date Time" value="">
            </div>
            
            
            <div class="clearfix margin_05"></div>
          </div>
          
<!-- end 1st row -->          
          
          <div class="row highlight">
          
          
          	<div class="col-lg-3 col-md-3 col-sm-6">
          	<span style="float: left; margin:15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">From</span>
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
            
            <div class="col-lg-3 col-md-3 col-sm-6">
            <span style="float: left; margin:15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">&nbsp;</span>
            <div class="clearfix margin_05"></div>
              <div style="margin-bottom: 6px;"> City<span style="color: red;">*</span> </div>
                <select name="fromCityId" id="fromCityId" onchange="" class="input-sm" style="width:100%;">
				  <option value="">--Select city--</option>
				</select>
            </div>
            
            <div class="col-lg-3 col-md-3 col-sm-6">
            <span style="float: left; margin:15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">To</span>
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
            
            <div class="col-lg-3 col-md-3 col-sm-6">
            <span style="float: left; margin:15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">&nbsp;</span>
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
              <div style="margin-bottom: 6px;"> Amount<span style="color: red;">*</span> </div>
              <input type="text" name="price" id="price" class="form-control input-sm" style="width:100%;" placeholder="Enter Amount">
            </div>
            
            <div class="col-lg-3 col-md-3 col-sm-6">
            <span style="float: left; margin:0 0 2px 0px; font-weight: bold; font-size: 16px; color: #525353;">&nbsp;</span>
            <div class="clearfix margin_05"></div>
             <input type="submit" class="btn btn-danger btn-sm btn_reset" id="reset" value="Reset">
          <input type="submit" class="btn btn-danger btn-sm btn_nav1" id="save" value="Submit" onclick="callSaveClientOrder();">
            </div>
          </div>
<!-- end 1st row -->
          
          
          
          
          <div class="clearfix margin_10"></div>
          <div class="clearfix margin_10"></div>
          
        <!-- </form> -->
        <%-- <%
        ControllerDAOTracker savelists=(ControllerDAOTracker)request.getAttribute("saveBack"); if(savelists!=null){
					%>
					<b style="color: green; position: absolute;"> Order Successfully Added</b>
					<%
						}
					%> --%>
        <div id="message" style="color: green;"></div>
      </fieldset>
    </div>
  </div>
  
  
  <div class="row ad-hoc-request" id="html">
  
  </div>
  
  <div class="error-client-search" id="error">
  </div>
  
  
  
  
</div>
<script type="text/javascript">
		var dateToday = new Date();
		var dd = dateToday.getDate();
		var mm = dateToday.getMonth()+1; 
		var yyyy = dateToday.getFullYear();
		var toYears=parseInt(yyyy);
		 
		$(function() {
			 $('#deployDateTime, #closingDateTime').datetimepicker({
				 timeFormat: 'HH:mm z',
				 dayOfWeekStart : 1,
				 lang:'en',
				 yearRange: '1800:' + toYears + '',
				 startDate:	dateToday //'1986/01/05'
				 }); 
		$('#tripDate').datetimepicker({
		    timepicker:false,
		    format:'Y/m/d'
			 });
		
		}); 
		 </script>
</body>
</html>