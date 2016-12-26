<%@page import="com.trux.model.VehicleTripSheet"%>
<%@page import="com.trux.model.ClientMandate"%>
<%@page import="java.util.Date"%>
<%@page import="com.trux.utils.DateFormaterUtils"%>
<%@page import="com.trux.model.DriverAttandance"%>
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
 

<style type="text/css">
#md-form input[type="radio"] { -webkit-appearance: radio; }
input[type="checkbox"] { -webkit-appearance: checkbox; }
input[type="text"] { -webkit-appearance: text; }
tbody tr td:first-child, table#attandanceTable tbody tr td:last-child { padding: 3px 0px }
</style>
 <style type="text/css">
table.successTable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.successTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.successTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
	border: 1px;
	font-size: 8px;
}
</style>
 

<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<style type="text/css">
table.successTable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
}
table.successTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.successTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
	color: green;
}
.oddrowcolor{
	background-color:#d4e3e5;
}
.evenrowcolor{
	background-color:#c3dde0;
}
</style>

  
<script src="/trux/resources/core/jquery.autocomplete.min.js"></script>
<link href="/trux/resources/core/main.css" rel="stylesheet">

 <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
 <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>

<title>Client Mandate</title>


<script type="text/javascript">
			
			 //goople api
			 

         var componentForm = {
			   street_number: 'short_name',
			   route: 'long_name',
			   locality: 'long_name',
			   administrative_area_level_1: 'short_name',
			   country: 'long_name',
			   postal_code: 'short_name'
			 };

			function initialize(ids,no) { 
			var tbl = ids;
			var lastRow = tbl.rows.length;
			var iteration = lastRow;   
			 
		 	for ( var i = 1; i < iteration+1; i++) {
			google.maps.event.addListener(new google.maps.places.Autocomplete((document.getElementById('locationA'+no+i)),{ types: ['(geocode)'] }), 'place_changed', function() {
			fillInAddress(new google.maps.places.Autocomplete((document.getElementById('locationA'+no+i)),{ types: ['(geocode)'] }));
			});  
			google.maps.event.addListener(new google.maps.places.Autocomplete((document.getElementById('locationB'+no+i)),{ types: ['(geocode)'] }), 'place_changed', function() {
			fillInAddress(new google.maps.places.Autocomplete((document.getElementById('locationB'+no+i)),{ types: ['(geocode)'] }));
			});
		 	}  
		 }
			
			
			function initializes(ids,no) { 
			 
			   google.maps.event.addListener(new google.maps.places.Autocomplete((document.getElementById('locationA'+ids+no)),{ types: ['(geocode)'] }), 'place_changed', function() {
				fillInAddress(new google.maps.places.Autocomplete((document.getElementById('locationA'+ids+no)),{ types: ['(geocode)'] }));
				});  
				google.maps.event.addListener(new google.maps.places.Autocomplete((document.getElementById('locationB'+ids+no)),{ types: ['(geocode)'] }), 'place_changed', function() {
				fillInAddress(new google.maps.places.Autocomplete((document.getElementById('locationB'+ids+no)),{ types: ['(geocode)'] }));
				});
			 	 
			 }

			 // [START region_fillform]
			 function fillInAddress(autocomplete) {
			   // Get the place details from the autocomplete object.
			   var place = autocomplete.getPlace();

			  /*  for (var component in componentForm) {
			  //   document.getElementById(component).value = '';
			     document.getElementById(component).disabled = true;
			   } */
 			   for (var i = 0; i < place.address_components.length; i++) {
			     var addressType = place.address_components[i].types[0];
			     if (componentForm[addressType]) {
			       var val = place.address_components[i][componentForm[addressType]];
			       document.getElementById(addressType).value = val;
			     }
			   }
			 } 
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
 
 
 function validateForm(){
	 var SelFlag=document.getElementById("checkbox").checked;
	 /* alert(SelFlag); */
	 if(SelFlag==false){
	
		 
	 collectTreepSheetLocationDetails();
 	 var flag=false;    
 	var tFlag=false;    
 	   
	 var SelClientId = document.getElementById("clientId").value;
	    var SelSubClientId=document.getElementById("subClientId").value;
	    var SelVehicleNo=document.getElementById("vehicleNo").value;
	    var SelTripDate=document.getElementById("tripDate").value;
	    var SelTripSheetOrderNo=document.getElementById("tripSheetOrderNo").value;
	    
	    
	    var SelOpeningKm=document.getElementById("openingKm").value;
	    var SelClosingKm=document.getElementById("closingKm").value;
	    var SelOpeningDateTime=document.getElementById("openingDateTime").value;
	    var SelClosingDateTime=document.getElementById("closingDateTime").value;
	    
	    //var SelFlag=document.getElementById("flag").value;
	    
	        
	    var message="";
	    
	   if(SelClientId==""){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("clientId").style.borderColor="red";
		    message+="Please select the client !<br/>";
		 	document.getElementById('clientId').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("clientId").style.borderColor="green";
		 	}
	   if(SelSubClientId==""){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("subClientId").style.borderColor="red";
		    message+="Please select the sub-client !<br/>";
		 	document.getElementById('subClientId').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("subClientId").style.borderColor="green";
		 	}
	   if(SelVehicleNo==""){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("vehicleNo").style.borderColor="red";
		    message+="Please select the Vehicle Number !<br/>";
		 	document.getElementById('vehicleNo').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("vehicleNo").style.borderColor="green";
		 	}
	   if(SelTripDate==""){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("tripDate").style.borderColor="red";
		    message+="Please select the Trip Date !<br/>";
		 	document.getElementById('tripDate').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("tripDate").style.borderColor="green";
		 	}
	   
	   if(SelOpeningKm==""){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("openingKm").style.borderColor="red";
		    message+="Please Enter the Opening Km. !<br/>";
		 	document.getElementById('openingKm').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("openingKm").style.borderColor="green";
		 	}
	   
	   if(SelClosingKm==""){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("closingKm").style.borderColor="red";
		    message+="Please Enter the Closing Kilo Meter !<br/>";
		 	document.getElementById('closingKm').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("closingKm").style.borderColor="green";
		 	}
	   
	   if(SelOpeningDateTime==""){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("openingDateTime").style.borderColor="red";
		    message+="Please Enter The Opening Date Time!<br/>";
		 	document.getElementById('openingDateTime').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("openingDateTime").style.borderColor="green";
		 	}
	   
	   if(SelClosingDateTime==""){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("closingDateTime").style.borderColor="red";
		    message+="Please Enter Closing Date Time !<br/>";
		 	document.getElementById('closingDateTime').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("closingDateTime").style.borderColor="green";
		 	}
 
	   
	   if(SelClosingKm<SelOpeningKm){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("closingKm").style.borderColor="red";
		    message+="Please Enter the Closing Kilo Meter greater than opening km !<br/>";
		 	document.getElementById('closingKm').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("closingKm").style.borderColor="green";
		 	}
	   
	   /* if(SelFlag==""){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("flag").style.borderColor="red";
		    message+="Please Enter the field as 2 if image has some issue else 1 !<br/>";
		 	document.getElementById('flag').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("flag").style.borderColor="green";
		 	} */
	   
//	   validateFlag();
	     validateTwoDifferentYearDate();
 	 	 
 	 	 //
 	 	 
		  var tbl = document.getElementById('tripSheetTable');
		  var lastRow = tbl.rows.length;
		  var iteration = lastRow;
		  var idName = new Array("","locationA","locationB");
 	 	 for ( var i = 0; i < iteration; i++) {	
		 		var countInnerValue = 0; 
				var vehicleInnerBodyValue = ""; 
				var vehicleTypeInnerValue = ""; 
		 	    for ( var id = 1; id < idName.length; id++) {
				  if(id==1){ 
					  if($("#"+idName[id]+i).val()==""){
						  document.getElementById('message').style.display = "block";
			 	 	 	   document.getElementById('message').style.color="red";
			 	 	 	   message+="Please enter the  From Drop Location  !<br/>";
				    	   document.getElementById(idName[id]+i).style.borderColor="red";
						 	document.getElementById(idName[id]+i).focus();
						 	tFlag= false; 
						    }else{
					 	 	 document.getElementById(idName[id]+i).style.borderColor="green";
					 	 	tFlag= true; 
					 	  }
			 	     }
				     if(id==2){
				    	if($("#"+idName[id]+i).val()==""){
				    		 document.getElementById('message').style.display = "block";
				 	 	 	   document.getElementById('message').style.color="red";
				 	 	 	   message+="Please enter the To Drop Location !<br/>";
				    	   document.getElementById(idName[id]+i).style.borderColor="red";
						 	document.getElementById(idName[id]+i).focus();
						 	tFlag= false; 
						    }else{
					 	 	 document.getElementById(idName[id]+i).style.borderColor="green";
					 	 	tFlag= true; 
					 	  }
				    	}
					  
				 }
			  } 
		 	 
 	 	 //
 	 	 if(tFlag==true && flag==false ){
 	 		message=="  " ;
 	 	 }
 	 	if(message==""){flag=true;}
  return flag;
 }
	 else{
		 return true;
	 }
  }
   
//------------------------------------------//
 
	
 

function fillSubOrg()	
{   var flag=false; 
    var SelOrgValue = document.getElementById("clientId").value;
    var message="";
    
   if(SelOrgValue==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("clientId").style.borderColor="green";
	    message+="Please select the client !<br/>";
	 	document.getElementById('clientId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("clientId").style.borderColor="green";
	 	}
   if(message==""){flag= true;}
   if(flag==true){
	 $.ajax({
		 type: "POST",
	      url: "/trux/reg/getSubsidiaryOrgByMasterId",
	      data:{ 
	    	  idClientMaster:SelOrgValue
			  } ,
	      success: function(data) {
	    	  document.getElementById("subClientId").innerHTML = data;
	    	  document.getElementById("subClientId").value.innerHTML=data;
	      }
	    });
    return true;
   }
   return false;
}

function getDriverVehicle()	
{   var flag=false; 
    var SelClientId = document.getElementById("clientId").value;
    var SelSubClientId = document.getElementById("subClientId").value;
    var message="";
     
   if(SelClientId==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("clientId").style.borderColor="red";
	    message+="Please select the client !<br/>";
	 	document.getElementById('clientId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("clientId").style.borderColor="green";
	 	}
   if(SelSubClientId==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("clientId").style.borderColor="red";
	    message+="Please select the sub-client !<br/>";
	 	document.getElementById('subClientId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("subClientId").style.borderColor="green";
	 	}
   if(message==""){flag= true;}
   if(flag==true){
	 $.ajax({
		 type: "POST",
	      url: "/trux/trip/getDriverVehicle",
	      data:{
	    	  clientId:SelClientId,
	    	  subClientId:SelSubClientId
			  } ,
	      success: function(data) {
	    	  document.getElementById("vehicleNo").innerHTML = data;
	    	  document.getElementById("vehicleNo").value.innerHTML=data;
	      }
	    });
    return true;
   }
   return false;
}
  

function addRow(){
	 var i= $('#tripSheetTBody').children().length;
	   var data="<tr><td>";
	    data +="<div class='col-lg-3 col-md-3 col-sm-12'> <input type='text' name='locationA"+i+"' id='locationA"+i+"'  placeholder='Drop From Location'  class='form-control input-sm' style='width:103%;margin: 0px 0 0 -12px;'  required></div>	 <div class='col-lg-3 col-md-3 col-sm-12'><input type='text' name='locationB"+i+"' id='locationB"+i+"' placeholder='Drop To Location'  class='form-control input-sm' style='width:103%;margin: 0px 0 0 -12px;'  required></div></td></tr>";
	    $('#tripSheetTBody').append(data);
	   
	}
	 //---------------------------------------//
		 
			 //Validate Decimal Value
 function validateDecimal(id) {
    $(id).val($(id).val().replace(/[^0-9\.]/g,''));
            if ((event.which != 46 || $(id).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
                event.preventDefault();
            }
        
} 
		
	function removToVehiceDetailsRow() {
		var tbl = document.getElementById('tripSheetTable');
		var lastRow = tbl.rows.length;
		if (lastRow > 1)
			tbl.deleteRow(lastRow - 1);
	}
	 
	 

	 function collectTreepSheetLocationDetails() {
		 
			var tbl = document.getElementById('tripSheetTable');
			var lastRow = tbl.rows.length;
			var iteration = lastRow;
			var count = new Array(); 
			var fromLocation = new Array(); 
			var toLocation = new Array(); 
			var idName = new Array("","locationA","locationB");
			var fromLocationValue = ""; 
			var toLocationValue = "";  
			  
		 	for ( var i = 0; i < iteration; i++) {	
		 		 
				var fromLocationrInneValue = ""; 
				var toLocationInnerValue = ""; 
		 		 
			 	for ( var id = 1; id < idName.length; id++) {
					
				    if(id==1){ 
				    	fromLocationrInneValue =$("#"+idName[id]+i).val();
			 	     }
				     if(id==2){
				    	 toLocationInnerValue = $("#"+idName[id]+i).val();
					     }
				    
				 	if (id == (idName.length)-1) { 
					 fromLocationValue =fromLocationrInneValue; 
					 toLocationValue =toLocationInnerValue; 
				 	} else { 
						 fromLocationValue =fromLocationrInneValue; 
						 toLocationValue =toLocationInnerValue; 
				  }
				}
			 	 
				fromLocation.push(fromLocationValue); 
				toLocation.push(toLocationValue); 
				 
		     } 
		  
	     	$("#fromDropLocation").val(fromLocation);
			$("#toDropLocation").val(toLocation);   
			 
			   
			fromLocation = new Array(); 
			toLocation = new Array(); 
		 return  toLocation;
			
		}
	 
	 
 
	 function validateTwoDifferentYearDate(){
         var objToDate=$("#openingDateTime").val();
		 var objFromDate =$("#closingDateTime").val();
		// validateAlreadyAssingDate();
		 if(dateCheck(objFromDate ,objToDate)){
			 $("#openingDateTime").css({ borderColor: "green" });
			 $("#closingDateTime").css({ borderColor: "green" });
		   } else {
			    alert("From date should be greater than to date !");
			    $("#openingDateTime").css({ borderColor: "red" });
			    $("#closingDateTime").css({ borderColor: "red" });
			    $("#closingDateTime").val('');
		    }
			function dateCheck(from,to) {

			    var fDate,lDate,cDate;
			    fDate = Date.parse(from);
			    lDate = Date.parse(to); 
			    if((fDate < lDate)) {
			        return false;
			    }
			   return true;
			}
	 }
	 
	 function reload(){
		 window.location.reload();
	 }
 
	 function searchTripSheetIsExist(){
		 
		 document.getElementById("signSecurityName").value="";
		 document.getElementById("tripSheetOrderNo").value="";
		 document.getElementById("scanTripSheetDocument").value="";
		 document.getElementById("openingKm").value="";
		 document.getElementById("closingKm").value="";
		 document.getElementById("openingDateTime").value="";
		 document.getElementById("closingDateTime").value="";
		 document.getElementById("image").setAttribute("src","");
		 //alert(document.getElementById('tripSheetTBody').innerHTML);
		 document.getElementById('tripSheetTBody').innerHTML="";

		 document.getElementById("toll").value="";
		 document.getElementById("parking").value="";
		 document.getElementById("challan").value="";
		 document.getElementById("ngt").value="";
		 document.getElementById("other").value="";
		 //document.getElementById("flag").value="";
		 /* if(document.getElementById("checkbox").checked) {
			 $("#checkbox").toggle(this.checked);
			} */
		 
		 //document.getElementById('tripSheetTable').clear;
		 //document.getElementById("tripSheetTBody").value="";
		 
		 
		 
		 /* for ( var i = 1; i < iteration+1; i++) {
				google.maps.event.addListener(new google.maps.places.Autocomplete((document.getElementById('locationA'+no+i)),{ types: ['(geocode)'] }), 'place_changed', function() {
				fillInAddress(new google.maps.places.Autocomplete((document.getElementById('locationA'+no+i)),{ types: ['(geocode)'] }));
				});  
				google.maps.event.addListener(new google.maps.places.Autocomplete((document.getElementById('locationB'+no+i)),{ types: ['(geocode)'] }), 'place_changed', function() {
				fillInAddress(new google.maps.places.Autocomplete((document.getElementById('locationB'+no+i)),{ types: ['(geocode)'] }));
				});
			 	} */
		 
		 
   			var flag=false;        
		    var SelClientId = document.getElementById("clientId").value;
		    var SelSubClientId=document.getElementById("subClientId").value;
		    var SelVehicleNo=document.getElementById("vehicleNo").value;
		    var SelTripDate=document.getElementById("tripDate").value;
		    var SelTripSheetOrderNo=document.getElementById("tripSheetOrderNo").value;
		    
		    var SelToll=document.getElementById("toll").value;
		    var SelParking=document.getElementById("parking").value;
		    var SelChallan=document.getElementById("challan").value;
		    var SelNGT=document.getElementById("ngt").value;
		    var SelOther=document.getElementById("other").value;
		    //var SelFlag=document.getElementById("flag").value;
		    
		    var message="";
		    
		   if(SelClientId==""){
			 	document.getElementById("message").style.color="red";
			 	document.getElementById('message').style.display = "block";
			 	document.getElementById("clientId").style.borderColor="red";
			    message+="Please select the client !<br/>";
			 	document.getElementById('clientId').focus();
			 	flag= false;
			 	}else{
			 	document.getElementById("clientId").style.borderColor="green";
			 	}
		   if(SelSubClientId==""){
			 	document.getElementById("message").style.color="red";
			 	document.getElementById('message').style.display = "block";
			 	document.getElementById("subClientId").style.borderColor="red";
			    message+="Please select the sub-client !<br/>";
			 	document.getElementById('subClientId').focus();
			 	flag= false;
			 	}else{
			 	document.getElementById("subClientId").style.borderColor="green";
			 	}
		   if(SelVehicleNo==""){
			 	document.getElementById("message").style.color="red";
			 	document.getElementById('message').style.display = "block";
			 	document.getElementById("vehicleNo").style.borderColor="red";
			    message+="Please select the Vehicle Number !<br/>";
			 	document.getElementById('vehicleNo').focus();
			 	flag= false;
			 	}else{
			 	document.getElementById("vehicleNo").style.borderColor="green";
			 	}
		   if(SelTripDate==""){
			 	document.getElementById("message").style.color="red";
			 	document.getElementById('message').style.display = "block";
			 	document.getElementById("tripDate").style.borderColor="red";
			    message+="Please select the Trip Date !<br/>";
			 	document.getElementById('tripDate').focus();
			 	flag= false;
			 	}else{
			 	document.getElementById("tripDate").style.borderColor="green";
			 	}
		   
		   if(message==""){flag= true;}
		    
		   if(flag==true){
			   
		 $.ajax({
		       type: "GET",
		       url: "/trux/trip/getTripSheetIsEixstApi",
		       data:{
		    	   clientId:SelClientId,  
		    	   subClientId:SelSubClientId,
		    	   vehicleNo:SelVehicleNo,
		    	   tripDate:SelTripDate,
		    	   tripSheetOrderNo:SelTripSheetOrderNo
		 		  } ,
		       success: function(data) {
		    	   if(data=='' || !data)
	  				  {
		    		   $('#successMessage').val('');
	  				  } else{
	  					$('#successMessage').val('');
		    	    $.each(data, function( index, value ) {
		    	    	var id=(value.id);
		    	    	//alert(id);
		    	    	$("#clientId").val(value.clientId);
		    	    	$("#subClientId").val(value.subClientId);
		    	    	//$("#vehicleNo").val(value.vehicleNo);
		    	    	var driverName=(value.driverName);
		    	    	//alert(value.tripDate);
		    	        $("#tripDate").val(showDateDetails(value.tripDate)); 
		    	    	$("#tripSheetOrderNo").val(value.clientOrderNo);
		    	    	$("#openingKm").val(value.openingKm);
		    	    	
		    	    	//alert(showDateDetails(value.openingDateTime));
		    	    	//var opendate=value.openingDateTime;
		    	    	//var reso = opendate.replace("-", "/");
		    	    	if(value.openingDateTime!=null)
		    	    	$("#openingDateTime").val(value.openingDateTime.replace(/-/g,'/'));
		    	    	
		    	    	$("#closingKm").val(value.closingKm);
		    	    	
		    	    	//alert(showDateDetails(value.closingDateTime));
		    	    	//var closingdate=value.openingDateTime;
		    	    	//var resc = closingdate.replace("-", "/");
		    	    	if(value.closingDateTime!=null)
		    	    	$("#closingDateTime").val(value.closingDateTime.replace(/-/g,'/'));
		    	    	
		    	    	$("#signSecurityName").val(value.signSecurityName);
		    	    	document.getElementById("image").setAttribute("src",value.scannedTripSheetS3Url);
		    	    	document.getElementById("imagehref").setAttribute("href",value.scannedTripSheetS3Url);
		    	    	
		    	    	$("#toll").val(value.toll);
		    	    	$("#parking").val(value.parking);
		    	    	$("#challan").val(value.challan);
		    	    	$("#ngt").val(value.ngt);
		    	    	$("#other").val(value.other);
		    	    	//alert(value.flag);
		    	    	if(value.flag == 2){
		    	    		$("#checkbox").attr('checked', true);
		    	    	}
		    	    	else{
		    	    		$("#checkbox").attr('checked', false);
		    	    	}
		    	    	//$("#flag").val(value.flag);
		    	    	
		    	    	//(value.scannedTripSheetS3Url);
		    	    	//(value.signSecurityName);
		    	    	//(value.createdDate)
		    	    	//(value.createdBy); 
		    	    	//id
						//tripId
						//fromLocation
						//toLocation
						$('#successMessage').html('');
						removToVehiceDetailsRow();
		    	    	 $.each(value.vehicleTripDropList, function( index, value ) {
		    	    		 
		    	    		 var i= $('#tripSheetTBody').children().length;
		    	    		   var data="<tr><td>";
		    	    		    data +="<div class='col-lg-3 col-md-3 col-sm-12'> <input type='text' name='locationA"+i+"' id='locationA"+i+"' value="+value.fromLocation+" placeholder='Drop From Location'  class='form-control input-sm' style='width:103%;margin: 0px 0 0 -12px;'  required></div>	 <div class='col-lg-3 col-md-3 col-sm-12'><input type='text' name='locationB"+i+"' id='locationB"+i+"' value="+value.toLocation+" placeholder='Drop To Location'  class='form-control input-sm' style='width:103%;margin: 0px 0 0 -12px;'  required></div></td></tr>";
		    	    		    $('#tripSheetTBody').append(data);
						});
				       });
		    	   }
		       }
		     });
		   }
	 }
	  
	 function showTripDetails(){
		 $("#tripDetails").show();
	 }
	 
	 function replaceAll(str, find, replace) {
		  return str.replace(new RegExp(escapeRegExp(find), 'g'), replace);
		}
	 
	 function showDateDetails(values){
		 //alert(values);
		 var dateString = values+'\/'.substr(6);
		 var currentTime = new Date(parseInt(dateString ));
		 var month = currentTime.getMonth() + 1;
		 var day = currentTime.getDate();
		 var year = currentTime.getFullYear();
		 //var hour = currentTime.getHours();
		 //var minutes = currentTime.getMinutes();
		 var days="0";
		 var months="0";
		 if(month<10){
			 months=months+month;
		 }else{
			 months=month;
		 }
		 if(day<10){
			 days=days+day ;
		 }else{
			 days=day ; 
		 }
		 //+ " " + hour + ":" + minutes
		 var date = year+"/" + months + "/"+days;
		 return date
		  
	 }
	
 </script>
</head>
 <body>
 <div class="container">  
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager" >  <legend class="borderManager" style="width:100%">Add Trip Sheet  
     	
     </legend> <form id="md-form" action="/trux/trip/addTripSheet" method="POST"  class="form-inline" onsubmit="return validateForm();" enctype="multipart/form-data">
			 <div class="row" style='margin-top:-2%;'>       
			                                  									                                  
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Clients<span style="color: red;">*</span></div>
							
							  <select name="clientId" id="clientId" onchange="fillSubOrg()" class="input-sm" style="width:100%;" required>
                                 <option value="">--Select Client--</option>
                                 <% List<OrganizationMasterRegistration>  list= (List<OrganizationMasterRegistration>)session.getAttribute("orgList");
									if(list != null && !list.isEmpty()){
									for(int i=0; i<list.size();i++){ 
									%>	
								 <option value="<%=list.get(i).getIdClientMaster() %>"><%=list.get(i).getName() %></option>
                                 <%	}}%>                        
                             </select>
                     	</div>             
                     	<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;"> Sub-Clients <span style="color: red;">*</span></div>
						<select name="subClientId" id="subClientId" class="input-sm" onchange="getDriverVehicle();" style="width:100%;" required>
                                 <option value="">--Select Sub-Client--</option>
                         </select>
					 	</div>   
					 	<script>  
                             $(document).ready(function(){    																		 
                            	 fillSubOrg();
                            	  $("#tripDetails").hide();
    						}); 
    				        </script>
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Vehicle Number <span style="color: red;">*</span></div>
							<select name="vehicleNo" id="vehicleNo"  class="input-sm" style="width:100%;" required>
							  <option value="">--Select Vehicle--</option>
							
						 </select>    
						</div>	
						
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
						 <div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Trip Date<span style="color: red;">*</span></div>
							<input type="text" name="tripDate" id="tripDate" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #AEAEAE;border: 1px solid #AEAEAE; background:url(../resources/images/calender2.png);background-repeat: no-repeat; background-position:right;  ' placeholder="Select Start Date (Alpha-numeric)" required>
							
						</div>
						
						<div class="col-lg-6 col-md-6 col-sm-12">
						
						<div style="margin-bottom:6px;">&nbsp;<span style="color: red;">&nbsp;</span></div>
						<input type="button" class="btn btn-danger btn-sm btn_nav1"  value="Submit" onclick="searchTripSheetIsExist(); showTripDetails();">
						<input type="button" class="btn btn-danger btn-sm btn_nav1"  value="Reload" onclick="reload();"> 	
						</div>
						
						
						 	
						
						
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
						<hr>
						
						<div id="tripDetails" style="display: none;">
						<div class="col-lg-6 col-md-6 col-sm-12">
						<div class="col-lg-6 col-md-6 col-sm-12">
							<div style="margin-bottom:6px;">Sign Security Name</div>
							<input type="text" name="signSecurityName" id="signSecurityName" class="form-control input-sm"style='width:100%;' placeholder="Sign Security Name" value="">
					      </div>
					      <div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
						<div class="col-lg-6 col-md-6 col-sm-12">
							<div style="margin-bottom:6px;">Client Trip Sheet Order No. <span style="color: red;">*</span></div>
							<input type="text" name="tripSheetOrderNo" id="tripSheetOrderNo"  class="form-control input-sm" style="width:100%;" placeholder="Client Order No" value="">
							 
						</div>	
						
						<div class="col-lg-6 col-md-6 col-sm-12">
							<div style="margin-bottom:6px;">Upload Scan Trip Sheet<span style="color: red;">*</span></div>
							<input type="file" name="scanTripSheetDocument" id="scanTripSheetDocument"   class="form-control input-sm"style='width:100%;' placeholder="Closing Date" value="">
							
						</div>
						
						 <div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
						<div class="col-lg-6 col-md-6 col-sm-12">
							<div style="margin-bottom:6px;">Opening Km.<span style="color: red;">*</span></div>
							<input type="text" name="openingKm" id="openingKm"  onkeypress='validateDecimal(openingKm);' class="form-control input-sm"style='width:100%;' placeholder="Opening Km." value="">
							
						</div>
						
						<div class="col-lg-6 col-md-6 col-sm-12">
							<div style="margin-bottom:6px;">Closing Km.<span style="color: red;">*</span></div>
							<input type="text" name="closingKm" id="closingKm"  onkeypress='validateDecimal(closingKm);'  class="form-control input-sm"style='width:100%;' placeholder="Closing Km." value="">
							
						</div>
						
						 <div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
						<div class="col-lg-6 col-md-6 col-sm-12">
							<div style="margin-bottom:6px;">Opening Date Time<span style="color: red;">*</span></div>
							<input type="text" name="openingDateTime" id="openingDateTime"   class="form-control input-sm"style='width:100%;' placeholder="Opening Date Time" value="">
							
						</div> 
						<div class="col-lg-6 col-md-6 col-sm-12">
							<div style="margin-bottom:6px;">Closing Date Time<span style="color: red;">*</span></div>
							<input type="text" name="closingDateTime" id="closingDateTime" class="form-control input-sm"style='width:100%;' placeholder="Closing Date Time" value="">
							
						</div>
						
					 	 
				   <div class="clearfix margin_05"></div>
				      <div class="clearfix margin_10"></div>	
					  <div  class="col-lg-12 col-md-6 col-sm-12" style="border: 2px;"> 
						<div style="margin-bottom:6px;">Drop Details<span style="color: red;">*</span></div>
						<table style="width: 100%;" id="tripSheetTable" class="col-lg-6 col-md-6 col-sm-12"> 
						<tbody id="tripSheetTBody">
						<!-- <tr>
						<td> 
						 <div class="col-lg-3 col-md-3 col-sm-12">
							<input type="text" name='locationA0' id='locationA0'  class="form-control input-sm" style='width:103%;margin: 0px 0 0 -12px;' placeholder='Drop From Location'  required>
							</div>	
						    <div class="col-lg-3 col-md-3 col-sm-12">
							<input type="text" name='locationB0' id='locationB0'  class="form-control input-sm" style='width:103%;margin: 0px 0 0 -12px;' placeholder='Drop To Location'   required>
						   </div>	
						</td>
                      </tr> -->
                     </tbody>
					</table>
					<div class="clearfix margin_5"></div>
					<div class="clearfix margin_10"></div>
				  <div  class="col-lg-3 col-md-3 col-sm-12" style="border: 2px;">
					<input type="button" onclick="addRow();" value="+" class="btn btn-danger btn-sm btn_nav1">&nbsp;&nbsp;
					<input type="button" class="btn btn-danger btn-sm btn_nav1" onclick="removToVehiceDetailsRow();" value="-"> 
				 </div>
				</div>
				
				<div class="clearfix margin_5"></div>
				<div class="clearfix margin_10"></div>
				<div class="col-lg-6 col-md-6 col-sm-12">
							<div style="margin-bottom:6px;">Toll</div>
							<input type="text" name="toll" id="toll" class="form-control input-sm"style='width:100%;' placeholder="Toll" value="">
							
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12">
							<div style="margin-bottom:6px;">Parking</div>
							<input type="text" name="parking" id="parking" class="form-control input-sm"style='width:100%;' placeholder="Parking" value="">
							
				</div>
				<div class="clearfix margin_5"></div>
				<div class="clearfix margin_10"></div>
				<div class="col-lg-6 col-md-6 col-sm-12">
							<div style="margin-bottom:6px;">Challan</div>
							<input type="text" name="challan" id="challan" class="form-control input-sm"style='width:100%;' placeholder="Challan" value="">
							
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12">
							<div style="margin-bottom:6px;">NGT</div>
							<input type="text" name="ngt" id="ngt" class="form-control input-sm"style='width:100%;' placeholder="NGT" value="">
							
				</div>  
				<div class="clearfix margin_5"></div>
				<div class="clearfix margin_10"></div>
				<div class="col-lg-6 col-md-6 col-sm-12">
							<div style="margin-bottom:6px;">Other</div>
							<input type="text" name="other" id="other" class="form-control input-sm"style='width:100%;' placeholder="Other" value="">
							
				</div>
				
				<input type="hidden" name="fromDropLocation" id="fromDropLocation" value="">
				<input type="hidden" name="toDropLocation" id="toDropLocation" value="">   
				<input type="hidden" name="countVal" id="countVal" value="">  
				<input type="hidden" name="totalVehicle" id="totalVehicle" value="">   
				  <div  class="col-lg-12 col-md-6 col-sm-12" style="border: 2px;">
				  <br/>
				  <div class="clearfix margin_5"></div>
				  <div class="clearfix margin_10"></div>
				  <input type="submit" class="btn btn-danger btn-sm btn_nav1" id="save"  value="Save Now"> 
				 <input type="reset" class="btn btn-danger btn-sm btn_nav1" value="Reset"> 
				 </div>
				 </div>
				 <div class="col-lg-6 col-md-6 col-sm-12">
				 <div class="col-lg-6 col-md-6 col-sm-12">
							<div style="margin-bottom:6px;">Mark as</div>
							<!-- <script type="text/javascript">function validateFlag(){
								 var SelFlag=$("#flag").val;
								 alert(SelFlag);
								 if(SelFlag == 1 || SelFlag == 2){
									 $("#flag").css({ borderColor: "green" });
								 }
								 else{
									 $("#flag").css({ borderColor: "red" });
								 }
								 /* switch(SelFlag){
								 case 1:
								 case 2:
									 $("#flag").css({ borderColor: "green" });
									 break;
								 default:
										//alert("Please Enter the field as 2 if image has some issue else 1 !");
								    $("#flag").css({ borderColor: "red" });
								 } */
							 }</script> -->
							<!-- <input type="text" name="flag" id="flag" class="form-control input-sm" style="width:100%;" required> -->
							
							<!-- <input type="radio" id="flag" name="flag" value="1"> 1
  							<input type="radio" id="flag" name="flag" value="2"> 2<br> -->
  							
  							<label><input type=checkbox id="checkbox" name="checkbox"> Re-upload image</label><br>
  							
					      </div>
					      <div class="clearfix margin_5"></div>
				  <div class="clearfix margin_10"></div>
				 <div class="col-lg-12 col-md-12 col-sm-12 table-responsive" style=" width: 450px; max-height: 500px">
							<div class="table-responsive" style="margin-bottom:6px;"><span style="color: red;"></span></div>
							<a id="imagehref" href="" target="_blank"><img id="image" src="" alt="can't display picture"  style="width:500px;" class="table-responsive"/></a>
                        </div>
				 </div>
				 
		       </form>
			  <%-- <% VehicleTripSheet saveBack= (VehicleTripSheet)request.getAttribute("saveBack"); if(saveBack!=null && saveBack.getId()!=null){ %>   <b style="color: green;position: absolute;" id="successMessage">  Trip Sheet Successfully Added</b><%} %> --%>
		
			 <div id="message" style="color: red;">
			 </div>  
			</fieldset>
		</div>
	</div>		
</div>                
		<script type="text/javascript">
		var dateToday = new Date();
		var dd = dateToday.getDate();
		var mm = dateToday.getMonth()+1; 
		var yyyy = dateToday.getFullYear();
		var toYears=parseInt(yyyy);
		 
		$(function() {
			 $('#openingDateTime, #closingDateTime').datetimepicker({
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
 
	 