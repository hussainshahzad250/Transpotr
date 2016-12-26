 <%@page import="com.trux.model.OrganizationMasterRegistration"%>
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

<!-- 
<script src="/trux/resources/core/jquery.1.10.2.min.js"></script> -->
<script src="/trux/resources/core/jquery.autocomplete.min.js"></script>
<link href="/trux/resources/core/main.css" rel="stylesheet">

 <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
 <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>

<title>Booking Ride By Organization</title>

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
 	 var orgName=document.getElementById("orgName").value;
 	 var contactPerson=document.getElementById("contactPerson").value;
 	 var email=document.getElementById("email").value; 
    var toAddress= collectToAddressValue();
    alert(toAddress);
 	 var address=document.getElementById("fromAddress").value; 
 	document.getElementById("toAddress").value=toAddress;
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
   if(address==""){
 	document.getElementById("message").style.color="red";
 	document.getElementById('message').style.display = "block";
 	document.getElementById("fromAddress").style.borderColor="red";
 	
 	message+="Please enter address !<br/>";
 	document.getElementById('fromAddress').focus();
 	flag= false;
 	}else{
 	document.getElementById("fromAddress").style.borderColor="green";
 	} 
  if(message==""){flag=true;}
 	document.getElementById('message').innerHTML=message;
 	return flag;
 }
 
 //goople api
 
 var placeSearch, autocomplete,autocompleteTo0,autocompleteTo1,autocompleteTo2,autocompleteTo3,autocompleteTo4,autocompleteTo5,autocompleteTo6,autocompleteTo7,autocompleteTo8,autocompleteTo9,autocompleteTo10,autocompleteTo11,autocompleteTo12,autocompleteTo13,autocompleteTo14,autocompleteTo15,autocompleteTo16,autocompleteTo17,autocompleteTo18,autocompleteTo19,autocompleteTo20,autocompleteTo21,autocompleteTo22;
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
(document.getElementById('fromAddress')),
{ types: ['geocode'] });   
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
});    
autocompleteTo0= new google.maps.places.Autocomplete(
(document.getElementById('toAddress0')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
});
    
autocompleteTo1 = new google.maps.places.Autocomplete(
(document.getElementById('toAddress1')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
 fillInAddress();
 }); 
 
autocompleteTo2= new google.maps.places.Autocomplete(
(document.getElementById('toAddress2')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
});
		    
autocompleteTo3 = new google.maps.places.Autocomplete(
(document.getElementById('toAddress3')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
 }); 
autocompleteTo4 = new google.maps.places.Autocomplete(
(document.getElementById('toAddress4')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
});
autocompleteTo5 = new google.maps.places.Autocomplete(
(document.getElementById('toAddress5')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
}); 
autocompleteTo6 = new google.maps.places.Autocomplete(
(document.getElementById('toAddress6')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
 }); 
autocompleteTo7 = new google.maps.places.Autocomplete(
(document.getElementById('toAddress7')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
}); 
autocompleteTo8 = new google.maps.places.Autocomplete(
(document.getElementById('toAddress8')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
}); 
autocompleteTo9 = new google.maps.places.Autocomplete(
(document.getElementById('toAddress9')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
});
autocompleteTo10 = new google.maps.places.Autocomplete(
(document.getElementById('toAddress10')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
});
autocompleteTo11 = new google.maps.places.Autocomplete(
(document.getElementById('toAddress11')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
});
autocompleteTo12 = new google.maps.places.Autocomplete(
		(document.getElementById('toAddress12')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		 fillInAddress();
		 }); 
		 
		autocompleteTo13= new google.maps.places.Autocomplete(
		(document.getElementById('toAddress13')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		});
				    
		autocompleteTo14 = new google.maps.places.Autocomplete(
		(document.getElementById('toAddress14')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		 }); 
		autocompleteTo15 = new google.maps.places.Autocomplete(
		(document.getElementById('toAddress15')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		});
		autocompleteTo16 = new google.maps.places.Autocomplete(
		(document.getElementById('toAddress16')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		}); 
		 
		autocompleteTo17 = new google.maps.places.Autocomplete(
		(document.getElementById('toAddress17')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		}); 
		autocompleteTo18 = new google.maps.places.Autocomplete(
		(document.getElementById('toAddress18')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		}); 
		autocompleteTo19 = new google.maps.places.Autocomplete(
		(document.getElementById('toAddress19')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		});
		autocompleteTo20 = new google.maps.places.Autocomplete(
		(document.getElementById('toAddress20')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		});
		autocompleteTo21 = new google.maps.places.Autocomplete(
		(document.getElementById('toAddress21')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		});
		autocompleteTo22 = new google.maps.places.Autocomplete(
		(document.getElementById('toAddress22')),
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
	
	 
	//---------------------------------------//
	
function addToAddressRow() {	
	var tbl = document.getElementById('Address');
	var lastRow = tbl.rows.length;
	var iteration = lastRow - 1;
	var row = tbl.insertRow(lastRow); 
	var cellRight0 = row.insertCell(0);
	var el1 = document.createElement('input');
	el1.type = 'text';
	el1.name = 'toAddress' + iteration;
	el1.id = 'toAddress' + iteration; 
	el1.onFocus = "geolocate()";
	el1.className = "form-control input-sm";
	el1.size = 30;
	cellRight0.appendChild(el1);
	
	var cellRight1 = row.insertCell(1);
	var el2= document.createElement('input');
	el2.type = 'text';
	el2.name = 'contactPersonPhone' + iteration;
	el2.id = 'contactPersonPhone' + iteration;
	el2.className = "form-control input-sm";
	el2.onkeyup="isNumberKey(event,'contactPersonPhone"+ iteration+"');";
	el2.maxLength="10";
	el2.size = 20;
	cellRight1.appendChild(el2);
	initialize();
 }
	
	
function removToAddressRow() {
	var tbl = document.getElementById('Address');
	var lastRow = tbl.rows.length;
	if (lastRow > 2)
		tbl.deleteRow(lastRow - 1);
}
function collectToAddressValue() {

	var tbl = document.getElementById('Address');
	var lastRow = tbl.rows.length;
	var iteration = lastRow - 1;
	var toAddressVal = new Array();
	var idName = new Array("","toAddress", "contactPersonPhone");
		var mobiles="";
	for ( var i = 0; i < iteration; i++) {
		
		var value = "{";
		for ( var id = 1; id < idName.length; id++) {
			var valueID = "";
			var valueOfMobile = "";
			 valueID = document.getElementById(idName[id] + i).value;
			if(id=2){
				valueOfMobile = document.getElementById(idName[id] + i).value;
			}
			if (id == (idName.length)-1) {
				value += valueID;
				mobiles+=valueOfMobile+",";
			} else {
				value += valueID + ",";
				mobiles+=valueOfMobile+",";
			}
		}
		value += "}";
		toAddressVal.push(value);
		
	} 
	//alert(mobiles);
	document.getElementById("contactPersonPhone").value=mobiles;
	
	return  toAddressVal;
	
}
	
//---------------------------------	

function fillState()	
	{    var SelValue = document.getElementById("country").value;
	 
		 $.ajax({
		      type: "POST",
		      url: "/trux/reg/getCountryStates",
		      data:{
				  country:SelValue
				  } ,
		      success: function(data) {
		    	  document.getElementById("state").innerHTML = data;
		    	  document.getElementById("state").value.innerHTML=data;
		      }
		    });
	    return true;
	}


	function fillCity()
	{
	    var stateId = document.getElementById("state").value;
		 $.ajax({
		      type: "POST",
		      url: "/trux/reg/getCitiesByState",
		      data:{
				  state:stateId
				  } ,
		      success: function(data) {
		    	  document.getElementById("city").innerHTML = data;
		    	  document.getElementById("city").value.innerHTML=data;
		      }
		    });
	    return true;
	}
function fillClusterByCSC()
{ 
    var state = document.getElementById("state").value;
    var country = document.getElementById("country").value;
    var city = document.getElementById("city").value;
    
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
	
function fillSubOrg()	
{    
    var SelValue = document.getElementById("orgName").value;	 
	 $.ajax({
	      type: "POST",
	      url: "/trux/app/getSubOrgList",
	      data:{
	    	  clientMasterId:SelValue 
			  } ,
	      success: function(data) {
	    	  document.getElementById("parentOrgId").innerHTML = data;
	    	  document.getElementById("parentOrgId").value.innerHTML=data;
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
    	<fieldset class="fieldset2 col-lg-12 borderManager">  <legend class="borderManager">Booking Ride By Organizational Client</legend>
		 
			<form  action="backOrgBooking" method="post" class="form-inline" onsubmit="return validateForm();">
				  
					<div class="row" style="margin-top:-2%; ">                                        									                                  
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Organization Client Name<span style="color: red;">*</span></div>
							<!-- <input type="text" name="orgName" id="orgName" class="form-control input-sm" style="width:100%;"  placeholder="Organization Name"/> -->  
						     <select name="orgName" id="orgName" onchange="fillSubOrg();"  class="input-sm" style="width:100%;">
                                 <option value="">--Select Organizational Client--</option>
                                 <% List<OrganizationMasterRegistration>  list= (List<OrganizationMasterRegistration>)session.getAttribute("orgList");
									if(list != null && !list.isEmpty()){
									for(int i=0; i<list.size();i++){ 
									%>	
								 <option value="<%=list.get(i).getIdClientMaster() %>"><%=list.get(i).getName() %></option>
                                 <%	}}%>                        
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
						
						<div class="col-lg-3 col-md-2 col-sm-12">
							<div style="margin-bottom:6px;">From Address<span style="color: red;">*</span></div>
							<input type="text"  name="fromAddress" id="fromAddress" maxlength="255" onFocus="geolocate()" class="form-control input-sm" style="width:100%;" placeholder="From Address">  
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Sub Branch  (As Sub Branch Added)<span style="color: red;"></span></div>
						<!--  <input type="text" name="parentOrgId" id="parentOrgId" class="form-control input-sm" style="width:100%;" placeholder="Branch (As Head Branch Code)" /> -->
						<select name="parentOrgId" id="parentOrgId"   class="input-sm" style="width:100%;">
                                 <option value="">--Select Sub cleint--</option>
                                                         
                             </select>	
						</div>
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
						<div class="col-lg-3 col-md-3 col-sm-12">
						<table id="Address" cellpadding="8" cellspacing="5">
							<tr><td>To Address<span style="color: red;">*</span></td>
							<td>Phone Number<span style="color: red;">*</span></td> 
							</tr>
							</table>
							<input type="hidden" name="toAddress" id="toAddress">
							<input type="hidden" name="contactPersonPhone" id="contactPersonPhone">							
							<input type="button" value="+" onclick="addToAddressRow();" class="btn btn-danger btn-sm" title="Add the to address and phone number!"/>
							<input type="button" value="-" onclick="removToAddressRow();" class="btn btn-danger btn-sm" title="Remove the to address and phone number! "/>
						    <input type="submit" class="btn btn-danger btn-sm" onclick="" value="Book Now"> 
							<input type="reset" class="btn btn-danger btn-sm" value="Reset"> 
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
 
	 