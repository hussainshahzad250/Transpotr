 <%@page import="com.trux.model.Zones"%>
<%@page import="com.trux.model.Countries"%>
<%@page import="java.util.List"%>
<%@page import="com.trux.model.OrganizationMasterRegistration"%>
<%@page import="com.trux.model.OrderFilterReportsDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script> 
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script> 

<!-- 
<script src="/trux/resources/core/jquery.1.10.2.min.js"></script> -->
<script src="/trux/resources/core/jquery.autocomplete.min.js"></script>
<link href="/trux/resources/core/main.css" rel="stylesheet">

 <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
 <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>

<title>Zone Registration </title>

 <script type="text/javascript">		

 function validateForm(){
 	 var flag;  
 	 var zoneName=document.getElementById("zoneName").value;
 	 var countryId=document.getElementById("countryId").value;
 	 
 	 var message="";
 	if(zoneName==""){
 	  document.getElementById('message').style.display = "block";
 	  document.getElementById('message').style.color="red";
 	  message+="<br/>";
 	  message+="Please enter the Zone name  !<br/>";
 	  document.getElementById("zoneName").style.borderColor="red";
 	  document.getElementById('zoneName').focus();
 	  flag= false;
 	  }else{
 	  document.getElementById("zoneName").style.borderColor="green"; 
 	  }
 	 
 	if(countryId==""){
 	document.getElementById('message').style.display = "block";
 	document.getElementById('message').style.color="red"; 	
 	message+="Please select coutry name !<br/>";
 	document.getElementById("countryId").style.borderColor="red";
 	document.getElementById('countryId').focus();
 	flag= false; 		 
 	}else{
 	document.getElementById("countryId").style.borderColor="green";
 	}
 	  
   
  if(message==""){flag=true;}
 	document.getElementById('message').innerHTML=message;
 	return flag;
 }
 
 //goople api
 
 var placeSearch, autocomplete,autocompleteTo;
 var componentForm = {
   street_number: 'short_name',
   route: 'long_name',
   locality: 'long_name',
   administrative_area_level_1: 'short_name',
   country: 'long_name',
   postal_code: 'short_name'
 };

 function initialize() { 
   autocomplete = new google.maps.places.Autocomplete(
     (document.getElementById('address')),
       { types: ['geocode'] });
   google.maps.event.addListener(autocomplete, 'place_changed', function() {
     fillInAddress();
   }); 
   
 }

 // [START region_fillform]
 function fillInAddress() {
   // Get the place details from the autocomplete object.
   var place = autocomplete.getPlace();

   for (var component in componentForm) {
     document.getElementById(component).value = '';
     document.getElementById(component).disabled = false;
   }

   // Get each component of the address from the place details
   // and fill the corresponding field on the form.
   for (var i = 0; i < place.address_components.length; i++) {
     var addressType = place.address_components[i].types[0];
     if (componentForm[addressType]) {
       var val = place.address_components[i][componentForm[addressType]];
       document.getElementById(addressType).value = val;
     }
   }
 }
 // [END region_fillform]

 // [START region_geolocation]
 // Bias the autocomplete object to the user's geographical location,
 // as supplied by the browser's 'navigator.geolocation' object.
 function geolocate() {
   if (navigator.geolocation) {
     navigator.geolocation.getCurrentPosition(function(position) {
       var geolocation = new google.maps.LatLng(
           position.coords.latitude, position.coords.longitude);
       var circle = new google.maps.Circle({
         center: geolocation,
         radius: position.coords.accuracy
       });
       autocomplete.setBounds(circle.getBounds());
     });
   }
 }
 // [END region_geolocation]
 </script>
 
 <script type="text/javascript">
  
	function fillState()	
		{    var SelValue = document.getElementById("countryId").value;
		 
			 $.ajax({
			      type: "POST",
			      url: "/trux/reg/getCountryStates",
			      data:{
					  country:SelValue
					  } ,
			      success: function(data) {
			    	  document.getElementById("stateId").innerHTML = data;
			    	  document.getElementById("stateId").value.innerHTML=data;
			      }
			    });
		    return true;
		}


		function fillCity()
		{
		    var stateId = document.getElementById("stateId").value;
			 $.ajax({
			      type: "POST",
			      url: "/trux/reg/getCitiesByState",
			      data:{
					  state:stateId
					  } ,
			      success: function(data) {
			    	  document.getElementById("cityId").innerHTML = data;
			    	  document.getElementById("cityId").value.innerHTML=data;
			      }
			    });
		    return true;
		}
	function fillClusterByCSC()
	{ 
	    var state = document.getElementById("stateId").value;
	    var country = document.getElementById("countryId").value;
	    var city = document.getElementById("cityId").value;
	    
		 $.ajax({
		      type: "POST",
		      url: "/trux/reg/getClusterByCSC",
		      data:{
		    	  stateId:state,
				  countryId:country,
				  cityId:city
				  } ,
		      success: function(data) {
		    	  document.getElementById("hubId").innerHTML = data;
		    	  document.getElementById("hubId").value.innerHTML=data;
		      }
		    });
	    return true;
	}
	</script>
</head>
 <body onload="initialize();">
 <div class="container">  
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager">  <legend class="borderManager">Zone Registration <% Zones rs=(Zones) request.getAttribute("saveBack"); if(rs!=null){%> <b style="color: green;">Registration ID :<%=rs.getZoneId() %></b><% }%></legend>
		 	<form  action="zoneRegistration" method="post" class="form-inline" onsubmit="return validateForm();" enctype="multipart/form-data">
				<div class="row" style="margin-top:-2%; ">   
				       <div class="col-lg-3 col-md-3 col-sm-12">
			   				<div style="margin-bottom:6px;">Zone Name<span style="color: red;">*</span></div>
							<input type="text" name="zoneName" id="zoneName"    class="form-control input-sm" style="width:100%;" placeholder="Zone Name"/>   
						</div>	                                     									                                  
						  <div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Country<span style="color: red;">*</span></div>
							   <select name="countryId" id="countryId" class="input-sm" style="width:100%;">
                                 <option value="">--Select Country--</option>
                                 <%List<Countries> countriesList = (List<Countries>)session.getAttribute("countriesList");
									if(countriesList != null && !countriesList.isEmpty()){
									for(int i=0; i<countriesList.size();i++){ 										
									%>	
								<%if(countriesList.get(i).getValue().equals("India")){ %>
									 <option value="<%=countriesList.get(i).getId() %>" selected="selected"><%=countriesList.get(i).getValue() %></option>
								<%}else{ %>
								 <option value="<%=countriesList.get(i).getId() %>"><%=countriesList.get(i).getValue() %></option>
                                 <%	}}}%>                        
                             </select>
                             
						 </div>
				 						 
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
						<div class="col-lg-3 col-md-3 col-sm-12">     
							<div style="margin-top:6px; font-size:14px; text-align:left; margin-left:0px;margin-top:8px;">
								<input type="submit" class="btn btn-danger btn-sm btn_nav1" value="Register Now"> 
									<input type="reset" class="btn btn-danger btn-sm btn_nav1" value="Reset"> 					                             
							</div>
						</div> 
						 
					</div>											
					<!-- <div class="clearfix margin_10"></div> -->
				
			</form>
			 <div id="message" style="color: red;"></div>  
			</fieldset>
		</div>
	</div>		
</div>
  </body>
</html>
 
	 