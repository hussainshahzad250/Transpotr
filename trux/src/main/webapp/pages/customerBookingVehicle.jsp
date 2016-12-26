 <%@page import="com.trux.model.CustomerBookingVehicle"%>
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

<title>Consumer Booking Vehicle</title>

 <script type="text/javascript">
 
 function validateEmail()
 {
 var x=document.getElementById('email').value;
 var atpos=x.indexOf("@");
 var dotpos=x.lastIndexOf(".");
 if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
   {
	document.getElementById('message').innerHTML="Not a valid e-mail address !";
    return false;
   }else{
	   document.getElementById('message').innerHTML="";
	  }
 }

 function isNumberKey(evt)
 {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
    {  document.getElementById('message').innerHTML="Please enter numeric value only !";
       return false;
    }else{
        document.getElementById('message').innerHTML="";
        return true;	
    } 
 }
 
		 var dateToday = new Date(); 
		         
		 $(function() {
			 $('#rideTime').datetimepicker({
				 timeFormat: 'HH:mm z',
				 dayOfWeekStart : 1,
				 lang:'en',
				 startDate:	dateToday //'1986/01/05'
				 });
			 });
		

 function validateForm(){
 	 var flag;
 	 var firstName=document.getElementById("firstName").value;
 	 var lastName=document.getElementById("lastName").value;
 	 var email=document.getElementById("email").value;
 	 var mobile=document.getElementById("phoneNumber").value;
 	 var fromLocation=document.getElementById("fromLocation").value;
     var toLocation=document.getElementById("toLocation").value; 
 	 var vehicleType=document.getElementById("vehicleType").value; 
 	 var paymentMode=document.getElementById("paymentMode").value; 
 	 var rideTime=document.getElementById("rideTime").value;
 	 var consigneeName=document.getElementById("consigneeName").value;
 	 var ConsigneePhonenumber=document.getElementById("ConsigneePhonenumber").value;
 	 var message="";
 	if(firstName==""){
 	  document.getElementById('message').style.display = "block";
 	  document.getElementById('message').style.color="red";
 	  message+="<br/>";
 	  message+="Please enter the first name  !<br/>";
 	  document.getElementById("firstName").style.borderColor="red";
 	  document.getElementById('firstName').focus();
 	  flag= false;
 	  }else{
 	  document.getElementById("firstName").style.borderColor="green"; 
 	  }
 	 if(lastName==""){
 	   document.getElementById('message').style.display = "block";
 	   document.getElementById('message').style.color="red";
 	   
 	   message+="Please enter the Last Name !<br/>";
 	   document.getElementById("lastName").style.borderColor="red";
 	   document.getElementById('lastName').focus();
 	   flag= false; 		 
 	   }else{
		 document.getElementById("lastName").style.borderColor="green"; 
	   }
 	 if(email==""){
 		document.getElementById("message").style.color="red";
 		document.getElementById('message').style.display = "block";
 		document.getElementById("email").style.borderColor="red";
 		
 		message+="Please enter the e-mail id !<br/>";
 	    document.getElementById('email').focus();
 		flag= false;
 	   }else{
 		  document.getElementById("email").style.borderColor="green";
 	   }
 	var atpos=email.indexOf("@");
 	var dotpos=email.lastIndexOf(".");
 	if(atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length)
 	{
 	document.getElementById("message").style.color="red";
 	document.getElementById('message').style.display = "block";
 	document.getElementById("email").style.borderColor="red";
 	
 	message+="Not a valid e-mail address !<br/>";
 	document.getElementById('email').focus();
 	flag= false;
 	}else{
	document.getElementById("email").style.borderColor="green";
	}
 	if(mobile==""){
 	document.getElementById('message').style.display = "block";
 	document.getElementById('message').style.color="red";
 	
 	message+="Please enter Phone Number !<br/>";
 	document.getElementById("phoneNumber").style.borderColor="red";
 	document.getElementById('phoneNumber').focus();
 	flag= false; 		 
 	}else{
 	document.getElementById("phoneNumber").style.borderColor="red";
 	}
 	if(mobile!=""){
 	if((mobile.length != 10)) {
 	document.getElementById('message').style.display = "block";
 	document.getElementById('message').style.color="red";
 	
 	message+="Phone Number Should be 10 digit !<br/> ";
 	document.getElementById("phoneNumber").style.borderColor="red";
 	document.getElementById('phoneNumber').focus(); 
 	flag= false; 
 	}else{
 	document.getElementById("phoneNumber").style.borderColor="green";
 	} 
 	}
   if(fromLocation==""){
 	document.getElementById("message").style.color="red";
 	document.getElementById('message').style.display = "block";
 	document.getElementById("fromLocation").style.borderColor="red";
 	
 	message+="Please enter From Location !<br/>";
 	document.getElementById('fromLocation').focus();
 	flag= false;
 	}else{
 	document.getElementById("fromLocation").style.borderColor="green";
 	}
  if(toLocation==""){
 	document.getElementById("message").style.color="red";
 	document.getElementById('message').style.display = "block";
 	document.getElementById("toLocation").style.borderColor="red";
 	
 	message+="Please enter To Location !<br/>";
 	document.getElementById('toLocation').focus();
 	flag= false;
 	}else{
 	document.getElementById("toLocation").style.borderColor="green";
 	} 		
  if(document.getElementById("vehicleType").selectedIndex == 0){
 	document.getElementById('message').style.display = "block";
 	
    message+="Please select Vehicle Type at !<br/>";
 	document.getElementById("vehicleType").style.borderColor="red";
 	document.getElementById('vehicleType').focus();
 	flag= false;
 	}else{
 	document.getElementById("vehicleType").style.borderColor="green";
 	}	 
  if(rideTime.trim()==""){
 	document.getElementById("message").style.color="red";
 	document.getElementById('message').style.display = "block";
 	document.getElementById("rideTime").style.borderColor="red";
 	
 	message+="Please enter the Ride Date Time  !<br/>";
 	document.getElementById('rideTime').focus();
 	flag= false;
 	}else {
 	document.getElementById("rideTime").style.borderColor="green";
 	}	   
  
  if(document.getElementById("paymentMode").selectedIndex == 0){
 	document.getElementById('message').style.display = "block"; 	
    message+="Please select Payment Mode Type at !<br/>";
 	document.getElementById("paymentMode").style.borderColor="red";
 	document.getElementById('paymentMode').focus();
 	flag= false;
 	}else{
 	document.getElementById("paymentMode").style.borderColor="green";
 	}
  if(consigneeName==""){
 	document.getElementById("message").style.color="red";
 	document.getElementById('message').style.display = "block";
 	document.getElementById("consigneeName").style.borderColor="red";
 	
 	message+="Please enter Consignee Name !<br/>";
 	document.getElementById('consigneeName').focus();
 	flag= false;
 	}else{
 	document.getElementById("consigneeName").style.borderColor="green";
 	}
  if(ConsigneePhonenumber==""){
  	document.getElementById('message').style.display = "block";
  	document.getElementById('message').style.color="red";
  	
    message+="Please enter Consignee Phone Number !<br/>";
  	document.getElementById("ConsigneePhonenumber").style.borderColor="red";
  	document.getElementById('ConsigneePhonenumber').focus();
  	flag= false; 		 
  	}else{
  	document.getElementById("ConsigneePhonenumber").style.borderColor="green";
  	}
  if(ConsigneePhonenumber!=""){
  	 if((ConsigneePhonenumber.length != 10)) {
  	     document.getElementById('message').style.display = "block";
  	     document.getElementById('message').style.color="red";
  	     
  	     message+="Consignee Phone Number Should be 10 digit !<br/> ";
  	     document.getElementById("ConsigneePhonenumber").style.borderColor="red";
  	 	 document.getElementById('ConsigneePhonenumber').focus(); 
  	 	 flag= false;
  	 	 } else{
  	 	 document.getElementById("ConsigneePhonenumber").style.borderColor="green"; 
  	 	 }
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
   // Create the autocomplete object, restricting the search
   // to geographical location types.
   autocomplete = new google.maps.places.Autocomplete(
       /** @type {HTMLInputElement} */(document.getElementById('fromLocation')),
       { types: ['geocode'] });
   // When the user selects an address from the dropdown,
   // populate the address fields in the form.
   google.maps.event.addListener(autocomplete, 'place_changed', function() {
     fillInAddress();
   });
   
   autocompleteTo = new google.maps.places.Autocomplete(
	       /** @type {HTMLInputElement} */(document.getElementById('toLocation')),
	       { types: ['geocode'] });
	   // When the user selects an address from the dropdown,
	   // populate the address fields in the form.
	   google.maps.event.addListener(autocompleteTo, 'place_changed', function() {
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
	$(function() {
		$('#firstName').autocomplete({		 
			serviceUrl: '/trux/booking/consumerBookAutoSuggetion',
			paramName: "mobile",
			delimiter: ",",
		    transformResult: function(response) {		    	
		        return {
		        	   suggestions: $.map($.parseJSON(response), function(item) {
		        		$('#firstName').val(item.firstname);
		            	//$('#lastName').val(item.lastname);
		            	//$('#email').val(item.email);
		            	//$('#phoneNumber').val(item.phoneNumber);
		               // return { value: item.firstname, data: item.lastname, data: item.email, data: item.phoneNumber, data: item.id };
		            	  return { value: item.firstname, data: item.phoneNumber, data: item.id };
		        	   }) };
		            }  
		}); 
		});
	</script>
	
	 <script>
	$(document).ready(function() {
		$('#firstName').autocomplete({ 
			serviceUrl: '/trux/booking/consumerBookAutoSuggetion',
			paramName: "mobile",
			delimiter: ",",
		    transformResult: function(response) {		    	
		        return {		        	
		            suggestions: $.map($.parseJSON(response), function(item) {	
		                $('#lastName').val(item.firstname);
		            	//$('#email').val(item.email) */;
		            	//$('#phoneNumber').val(item.phoneNumber);
		                return { value: item.firstname, data: item.lastname, data: item.email, data: item.phoneNumber, data: item.id };
		                })		            
		        };		        
		    }		    
		});		
		$('#phoneNumber').autocomplete({ 
			serviceUrl: '/trux/booking/consumerBookAutoSuggetion',
			paramName: "mobile",
			delimiter: ",",
		    transformResult: function(response) {		    	
		        return {		        	
		            suggestions: $.map($.parseJSON(response), function(item) {	
		            	//$('#firstName').val(item.firstname);
		            	/* $('#lastName').val(item.lastname);
		            	$('#email').val(item.email); */
		            	return { value: item.phoneNumber, data: item.lastname, data: item.email, data: item.firstname, data: item.id };
		                })		            
		        };		        
		    }		    
		});	
	});
	</script>
</head>
 <body onload="initialize(); getConsumerBookingDetails();">
 <div class="container">  
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager table-responsive">  <legend class="borderManager">Consumer Booking Vehicle  <%CustomerBookingVehicle cbVehicle=(CustomerBookingVehicle)request.getAttribute("cbVehicle"); %>
		    <%try{
		    if(cbVehicle!=null && !cbVehicle.getSuccessFullMessage().equals("")){%>
		    <b style="color: green;">Booked succesfully !</b>
		    <%}}catch(Exception er){} %> </legend>
			<div>
			<%-- <c:if test="${not empty list}">  --%>
		    <ul>
		    
			<%-- <c:forEach var="listValue" items="${list}">
				<li><c:out value="${listValue.successFullMessage}"></c:out> </li>
			</c:forEach> --%>
		  </ul>
 	     <%--  </c:if> --%>
			 </div>
			<f:form commandName="cbVehicle" action="bookrideV2V" method="post" cssClass="form-inline" onsubmit="return validateForm();">
				 <f:label path="successFullMessage"></f:label>
				  
					<div class="row" style="margin-top:-2%; ">                                        									                                  
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">First Name<span style="color: red;">*</span></div>
							<f:input path="firstName" id="firstName" cssClass="form-control input-sm" cssStyle="width:100%;"  placeholder="First Name"/>  
						</div>                                               
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Last Name<span style="color: red;">*</span></div>
							<f:input path="lastName" id="lastName"  cssClass="form-control input-sm" cssStyle="width:100%;" placeholder="Last Name"/>   
						</div>	
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">E-mail<span style="color: red;">*</span></div>
							<f:input path="email" id="email"  onchange="validateEmail();" cssClass="form-control input-sm" cssStyle="width:100%;" placeholder="E-mail"/>   
						</div>	
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Phone Number<span style="color: red;">*</span></div>
							<f:input path="phoneNumber" id="phoneNumber"  onkeypress="isNumberKey(event);" maxlength="12" cssClass="form-control input-sm" cssStyle="width:100%;" placeholder="Phone Number"/>   
						</div>							
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">From Location<span style="color: red;">*</span></div>
						 <f:input path="fromLocation" id="fromLocation"  onFocus="geolocate()" cssClass="form-control input-sm" cssStyle="width:100%;" placeholder="From Location" />
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">To Location<span style="color: red;">*</span></div>
						 <f:input path="toLocation" id="toLocation" cssClass="form-control input-sm" cssStyle="width:100%;" placeholder="To Location" />
						</div>					
						<div class="col-lg-3 col-md-3 col-sm-12">
						<div style="margin-bottom:6px;">Vehicle type<span style="color: red;">*</span></div>
							<f:select path="vehicleType" id="vehicleType" cssClass="input-sm" cssStyle="width:100%;">
							<f:option value="">--Select Vehicle type</f:option>
							<f:option value="Tata Ace">Tata Ace</f:option>
							<f:option value="Tata 407">Tata 407</f:option>
							<f:option value="Tata 709">Tata 709</f:option>
							<f:option value="Bolero Pickup">Bolero Pickup</f:option>
							<f:option value="Mahindra Champion">Mahindra Champion</f:option>
							<f:option value="Maruti Eeco">Maruti Eeco</f:option>
							</f:select> 
						</div>	
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Ride Time<span style="color: red;">*</span></div>
						<f:input path="rideTime" id="rideTime" cssClass="form-control input-sm" cssStyle="width:100%;" placeholder="Ride Time"/>
						</div> 
						<div class="clearfix margin_10"></div>					
						 <div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Labour Count</div>
							<f:select path="labourCount" id="labourCount" cssClass="input-sm" cssStyle="width:100%;">
							<f:option value="0">--Select Labour --</f:option>
							<f:option value="1">1</f:option>
							<f:option value="2">2</f:option>
							<f:option value="3">3</f:option>
							<f:option value="4">4</f:option>
							</f:select>
						</div>  
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Payment Mode</div>
							<f:select path="paymentMode" id="paymentMode" cssClass="input-sm" cssStyle="width:100%;">
							<f:option value="0">--Select Payment Mode --</f:option>
							<f:option value="2">Pay at the time of loading </f:option>
							<f:option value="3">Pay at the time of unloading </f:option>				 
							</f:select>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Consignee Name<span style="color: red;">*</span></div>
						<f:input path="consigneeName" id="consigneeName" cssClass="form-control input-sm" cssStyle="width:100%;" placeholder="Consignee Name"/>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Consignee Phone Number<span style="color: red;">*</span></div>
						<f:input path="ConsigneePhonenumber" id="ConsigneePhonenumber" onkeypress="isNumberKey(event);" maxlength="12" cssClass="form-control input-sm" cssStyle="width:100%;" placeholder="Consignee Phone Number"/>
						</div>
						
						<div class="col-lg-3 col-md-3 col-sm-12">     
							<div style="margin-top:6px; font-size:14px; text-align:left; margin-left:0px;margin-top:8px;">
								<button class="btn btn-danger btn-sm btn_nav2">Book Now</button> 
								
						                             
							</div>
						</div> 
					</div>											
					<!-- <div class="clearfix margin_10"></div> -->
				
			</f:form>
			<f:form action="consumerBookrideClear" method="get">
			<div class="col-lg-3 col-md-3 col-sm-12">     
			<div style="text-align: left; font-size: 14px; margin-top: -31px; margin-left: 72px;">
			<button class="btn btn-danger btn-sm btn_nav2">Clear</button>  
			<br/> 
			</div>
			</div> 
			  <div id="message" style="color: red;"></div>  
			</f:form> 
			</fieldset>
		</div>
	</div>		
</div>
  </body>
</html>
 
	 