<%@page import="com.trux.model.HubRegistration"%>
<%@page import="com.trux.model.Countries"%>
<%@page import="java.util.List"%>
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

<script src="/trux/resources/core/jquery.autocomplete.min.js"></script>
<link href="/trux/resources/core/main.css" rel="stylesheet">

 <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
 <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>

<title>Register Organizational Client</title>

 <script type="text/javascript">
  
 function validateForm(){
 	 var flag; 
 	 var countryId=document.getElementById("countryId").value;
 	 var stateId=document.getElementById("stateId").value;
 	 var cityId=document.getElementById("cityId").value;
 	 var hubName=document.getElementById("hubName").value; 
 	 var message="";
 	if(countryId==""){
 	  document.getElementById('message').style.display = "block";
 	  document.getElementById('message').style.color="red";
 	  message+="<br/>";
 	  message+="Please select the country  !<br/>";
 	  document.getElementById("countryId").style.borderColor="red";
 	  document.getElementById('countryId').focus();
 	  flag= false;
 	  }else{
 	  document.getElementById("countryId").style.borderColor="green"; 
 	  }
 	 if(stateId==""){
 	   document.getElementById('message').style.display = "block";
 	   document.getElementById('message').style.color="red";
 	   
 	   message+="Please select the state  !<br/>";
 	   document.getElementById("stateId").style.borderColor="red";
 	   document.getElementById('stateId').focus();
 	   flag= false; 		 
 	   }else{
		 document.getElementById("stateId").style.borderColor="green"; 
	   }
 	 if(cityId==""){
 		document.getElementById("message").style.color="red";
 		document.getElementById('message').style.display = "block";
 		document.getElementById("cityId").style.borderColor="red";
 		
 		message+="Please select the city !<br/>";
 	    document.getElementById('cityId').focus();
 		flag= false;
 	   }else{
 		  document.getElementById("cityId").style.borderColor="green";
 	   }
 
 	if(hubName==""){
 	document.getElementById('message').style.display = "block";
 	document.getElementById('message').style.color="red"; 	
 	message+="Please enter Hub details !<br/>";
 	document.getElementById("hubName").style.borderColor="red";
 	document.getElementById('hubName').focus();
 	flag= false; 		 
 	}else{
 	document.getElementById("hubName").style.borderColor="green";
 	}
  
    
  if(message==""){flag=true;}
 	document.getElementById('message').innerHTML=message;
 	return flag;
 }
 
 //goople api
 
 var placeSearch, autocomplete;
 var componentForm = {
   street_number: 'short_name',
   route: 'long_name',
   locality: 'long_name',
   administrative_area_level_1: 'short_name',
   country: 'long_name',
   postal_code: 'short_name'
 };

 function initialize() {
   // Create the autocomplete object, restricting the search
   // to geographical location types.
   autocomplete = new google.maps.places.Autocomplete(
       /** @type {HTMLInputElement} */(document.getElementById('hubName')),
       { types: ['geocode'] });
   // When the user selects an address from the dropdown,
   // populate the address fields in the form.
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
 
  
	
<script>
	 
function fillState()
	
	{
	 
	    var SelValue = document.getElementById("countryId").value;
	 
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
	</script> 
</head>
 <body onload="initialize();">
 <div class="container">  
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager">  
    	<legend class="borderManager">Hub Registration <%	HubRegistration saveHubBack=(HubRegistration)request.getAttribute("saveHubBack"); if(saveHubBack!=null && saveHubBack.getHubId()!=null){ %><span style="color: green;">Hub saved succesfully </span><%} %></legend>
					<form  action="hubRegister" method="post" class="form-inline" onsubmit="return validateForm();">				    
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Country<span style="color: red;">*</span></div>
							   <select name="countryId" id="countryId" onchange="fillState()" class="input-sm" style="width:100%;">
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
                             <script>  
                             $(document).ready(function(){    																		 
									fillState();
    						}); 
    				        </script>
						 </div>
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">State<span style="color: red;">*</span></div>
							<select name="stateId" id="stateId" onchange="fillCity();" class="input-sm" style="width:100%;">
							<option value="">--Select state--</option>
							 </select> 
						</div>
						 	
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">City<span style="color: red;">*</span></div>
						<select name="cityId" id="cityId" class="input-sm" style="width:100%;">
							<option value="">--Select city --</option>
						</select>    
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Hub Name<span style="color: red;">*</span></div>
							<input type="text" name="hubName" id="hubName"  class="form-control input-sm"  style="width:100%;" placeholder="Hub Name"/>   
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
			</form>
			 <div id="message" style="color: red;"></div>  
			</fieldset>
		</div>
	</div>		
</div>
  </body>
</html>
 
	 