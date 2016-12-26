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
 	 var flag; //clientCode  orgName address countryId stateId cityId copyOfAgreement
 	 var orgName=document.getElementById("orgName").value;
 	 var clientCode=document.getElementById("clientCode").value; 	 
 	 var address=document.getElementById("address").value;
 	 var countryId=document.getElementById("countryId").value;
 	 var stateId=document.getElementById("stateId").value;  
 	 var cityId=document.getElementById("cityId").value;//  usersName,passwords
 	 var copyOfAgreement=document.getElementById("copyOfAgreement").value;
 	 var message="";
 	if(orgName==""){
 	  document.getElementById('message').style.display = "block";
 	  document.getElementById('message').style.color="red";
 	  message+="<br/>";
 	  message+="Please enter the Organization name  !<br/>";
 	  document.getElementById("orgName").style.borderColor="red";
 	  document.getElementById('orgName').focus();
 	  flag= false;
 	  }else{
 	  document.getElementById("orgName").style.borderColor="green"; 
 	  }
 	 if(clientCode==""){
 	   document.getElementById('message').style.display = "block";
 	   document.getElementById('message').style.color="red";
 	   
 	   message+="Please enter the Client Code!<br/>";
 	   document.getElementById("clientCode").style.borderColor="red";
 	   document.getElementById('clientCode').focus();
 	   flag= false; 		 
 	   }else{
		 document.getElementById("clientCode").style.borderColor="green"; 
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
 	 
   if(stateId==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("stateId").style.borderColor="red";
	 	
	 	message+="Please select state name !<br/>";
	 	document.getElementById('stateId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("stateId").style.borderColor="green";
	 	} 
   if(cityId==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("cityId").style.borderColor="red";
	 	
	 	message+="Please select city name !<br/>";
	 	document.getElementById('cityId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("cityId").style.borderColor="green";
	 	} 
   if(copyOfAgreement==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("copyOfAgreement").style.borderColor="red";
	 	
	 	message+="Please add copy of contract/Agreement !<br/>";
	 	document.getElementById('copyOfAgreement').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("copyOfAgreement").style.borderColor="green";
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
	
	function isNumberKey(evt,id)
	 { 
	    var charCode = (evt.which) ? evt.which : event.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57) && (charCode < 96 || charCode > 105))
	    {  document.getElementById('message').innerHTML="Please enter numeric value only !";
	    $("input#"+id).val(""); 
	    $("input#"+id).focus(); 
	     document.getElementById(id).value="";
	     document.getElementById(id).focus();
	      return false;
	    } else {
	        document.getElementById('message').innerHTML="";
	        return true;	
	    } 
	 }
	
	

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
    	<fieldset class="fieldset2 col-lg-12 borderManager">  <legend class="borderManager">Client Registration <% OrganizationMasterRegistration rs=(OrganizationMasterRegistration) request.getAttribute("reg"); if(rs!=null){%> <b style="color: green;">Registration ID :<%=rs.getIdClientMaster() %>,Register As Branch Code :<%=rs.getClientCode() %></b><% }%></legend>
		 	<form  action="orgRegistration" method="post" class="form-inline" onsubmit="return validateForm();" enctype="multipart/form-data">
				<div class="row" style="margin-top:-2%; ">   
				       <div class="col-lg-3 col-md-3 col-sm-12">
			   				<div style="margin-bottom:6px;">Code<span style="color: red;">*</span></div>
							<input type="text" name="clientCode" id="clientCode"    class="form-control input-sm" style="width:100%;" placeholder="Contact person"/>   
						</div>	                                     									                                  
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Client Organization Name<span style="color: red;">*</span></div>
							<input type="text" name="orgName" id="orgName" class="form-control input-sm" style="width:100%;"  placeholder="Organization Name"/>  
						</div>                                               
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Address<span style="color: red;">*</span></div>
							<input type="text" name="address" id="address" maxlength="255" onFocus="geolocate()" class="form-control input-sm" style="width:100%;" placeholder="ORG Address"/>   
						</div> 
											
						
						
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
						 <div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">State<span style="color: red;">*</span></div>
							<select name="stateId" id="stateId" onchange="fillCity();" class="input-sm" style="width:100%;">
							<option value="">--Select state--</option>
							 </select> 
						</div>
						 
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">City<span style="color: red;">*</span></div>
						<select name="cityId" id="cityId" onchange="fillClusterByCSC();"  class="input-sm" style="width:100%;">
							<option value="">--Select city--</option>
						</select>    
						</div>
						<!-- 
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Country<span style="color: red;">*</span></div>
							<input type="text" name="email" id="email"  class="form-control input-sm" style="width:100%;" placeholder="E-mail"/>   
						</div>	
								
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">State<span style="color: red;">*</span></div>
						 <input type="text" name="contactPersonPhone" id="contactPersonPhone" onkeyup="isNumberKey(event,'contactPersonPhone');" maxlength="12" class="form-control input-sm" style="width:100%;" placeholder="Phone Number" />
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">City<span style="color: red;"></span></div>
						 <input type="text" name="parentOrgId" id="parentOrgId"  class="form-control input-sm" style="width:100%;" placeholder="Brach (As Head Brach Code)" />
						</div>	
						 -->
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Contract/Agrement Copy<span style="color: red;"></span></div>
						 <input type="file" name="copyOfAgreement" id="copyOfAgreement"  class="form-control input-sm" style="width:100%;" placeholder="User Name" />
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
 
	 