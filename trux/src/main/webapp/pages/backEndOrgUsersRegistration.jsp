 <%@page import="com.trux.model.OrganizationalUser"%>
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

<title>Register Organizational Client</title>

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
 	var Pflag;//idClientMaster		 subName	contactPerson email	contactPersonPhone usersName passwords
 	 var idClientMaster=document.getElementById("idClientMaster").value;
 	 var subName=document.getElementById("subName").value;
 	 var contactPerson=document.getElementById("contactPerson").value;
 	 var email=document.getElementById("email").value;
 	 var phoneNumber=document.getElementById("contactPersonPhone").value;
 	 var address=document.getElementById("address").value;  
 	 var usersName=document.getElementById("usersName").value;  
 	 var passwords=document.getElementById("passwords").value;//  usersName,passwords
 	
 	 var message="";
 	if(idClientMaster==""){
 	  document.getElementById('message').style.display = "block";
 	  document.getElementById('message').style.color="red";
 	  message+="<br/>";
 	  message+="Please enter the Organization name  !<br/>";
 	  document.getElementById("idClientMaster").style.borderColor="red";
 	  document.getElementById('idClientMaster').focus();
 	  flag= false;
 	  }else{
 	  document.getElementById("idClientMaster").style.borderColor="green"; 
 	  }
 	if(subName==""){
  	   document.getElementById('message').style.display = "block";
  	   document.getElementById('message').style.color="red";
  	   
  	   message+="Please select the Sub Organization Name !<br/>";
  	   document.getElementById("subName").style.borderColor="red";
  	   document.getElementById('subName').focus();
  	   flag= false; 		 
  	   }else{
 		 document.getElementById("subName").style.borderColor="green"; 
 	   }
 	 if(contactPerson==""){
 	   document.getElementById('message').style.display = "block";
 	   document.getElementById('message').style.color="red";
 	   
 	   message+="Please enter the Contact Person Name !<br/>";
 	   document.getElementById("contactPerson").style.borderColor="red";
 	   document.getElementById('contactPerson').focus();
 	   flag= false; 		 
 	   }else{
		 document.getElementById("contactPerson").style.borderColor="green"; 
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
 	if(phoneNumber==""){
 	document.getElementById('message').style.display = "block";
 	document.getElementById('message').style.color="red";
 	
 	message+="Please enter Phone Number !<br/>";
 	document.getElementById("contactPersonPhone").style.borderColor="red";
 	document.getElementById('contactPersonPhone').focus();
 	flag= false; 		 
 	}else{
 	document.getElementById("contactPersonPhone").style.borderColor="red";
 	}
 	if(phoneNumber!=""){
 	if((phoneNumber.length != 10)) {
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
   if(address==""){
 	document.getElementById("message").style.color="red";
 	document.getElementById('message').style.display = "block";
 	document.getElementById("address").style.borderColor="red"; 	
 	message+="Please enter address !<br/>";
 	document.getElementById('address').focus();
 	flag= false;
 	}else{
 	document.getElementById("address").style.borderColor="green";
 	} 
   if(usersName==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("usersName").style.borderColor="red";	 	
	 	message+="Please enter user name !<br/>";
	 	document.getElementById('usersName').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("usersName").style.borderColor="green";
	 	} 
   if(passwords==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("passwords").style.borderColor="red"; 	
	 	message+="Please enter password !<br/>";
	 	document.getElementById('passwords').focus();
	 	flag= false;
	 	Pflag=false;
	 	}else{
	 	document.getElementById("passwords").style.borderColor="green";
	 	Pflag=false;
	 	} 
   if(message!=""){
	   flag==false;
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
    
 function fillSubOrg()	
	{
	    var SelValue = document.getElementById("idClientMaster").value;	 
		 $.ajax({
		      type: "POST",
		      url: "/trux/app/getSubOrgList",
		      data:{
		    	  clientMasterId:SelValue
				  } ,
		      success: function(data) {
		    	  document.getElementById("subName").innerHTML = data;
		    	  document.getElementById("subName").value.innerHTML=data;
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
    	<fieldset class="fieldset2 col-lg-12 borderManager">  <legend class="borderManager">Organizational User Registration <% OrganizationalUser saveBack=(OrganizationalUser)request.getAttribute("subOrgSaveBack"); if(saveBack!=null){%> <b style="color: green;">Registration ID :<%=saveBack.getIdClientContactMaster() %></b><% }%></legend>
		 	<form  action="organizationUserRegistration" method="post" class="form-inline" onsubmit="return validateForm();">
				<div class="row" style="margin-top:-2%; ">                                        									                                  
						<div class="col-lg-3 col-md-3 col-sm-12">
			   				<div style="margin-bottom:6px;">Organization Name<span style="color: red;">*</span></div>
							<select name="idClientMaster" id="idClientMaster" onchange="fillSubOrg();"  class="input-sm" style="width:100%;">
                                 <option value="">--Select Organization --</option>
                                 <% List<OrganizationMasterRegistration>  list= (List<OrganizationMasterRegistration>)session.getAttribute("orgList");
									if(list != null && !list.isEmpty()){
									for(int i=0; i<list.size();i++){ 
									%>	
								 <option value="<%=list.get(i).getIdClientMaster() %>"><%=list.get(i).getName() %></option>
                                 <%	}}%>                        
                             </select>	   
						</div>	                           									                                  
						
						<div class="col-lg-3 col-md-3 col-sm-12">
			   				<div style="margin-bottom:6px;">Sub Orgnization<span style="color: red;">*</span></div>
							<select name="subName" id="subName" onchange="fillLevel();"  class="input-sm" style="width:100%;">
                                 <option value="">--Select Sub Organization --</option>
                            </select>
						</div>	                                             
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Contact person<span style="color: red;">*</span></div>
							<input type="text" name="contactPerson" id="contactPerson"    class="form-control input-sm" style="width:100%;" placeholder="Contact person"/>   
						</div>	
						
							
						 
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">E-mail<span style="color: red;">*</span></div>
							<input type="text" name="email" id="email"  class="form-control input-sm" style="width:100%;" placeholder="E-mail"/>   
						</div>	
							<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Phone Number<span style="color: red;">*</span></div>
						 <input type="text" name="contactPersonPhone" id="contactPersonPhone" onkeyup="isNumberKey(event,'contactPersonPhone');" maxlength="12" class="form-control input-sm" style="width:100%;" placeholder="Phone Number" />
						</div>
							
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Address<span style="color: red;">*</span></div>
							<input type="text" name="address" id="address" maxlength="255" onFocus="geolocate()" class="form-control input-sm" style="width:100%;" placeholder="ORG Address"/>   
						</div>
						
						 
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">User Name(as Mobile)<span style="color: red;"></span></div>
						 <input type="text" name="usersName" id="usersName"  class="form-control input-sm" style="width:100%;" placeholder="User Name" />
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Password<span style="color: red;"></span></div>
						 <input type="password" name="passwords" id="passwords"  class="form-control input-sm" style="width:100%;" placeholder="Passwords" />
						</div>				
						  <script>  
                             $(document).ready(function(){  
                            	 $('#usersName').val("");
                            	 $('#passwords').val("");
                             }); 
    				        </script>
						
							 
						 
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
 
	 