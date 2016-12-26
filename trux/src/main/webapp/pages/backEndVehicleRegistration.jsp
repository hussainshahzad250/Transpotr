<%@page import="com.trux.model.VehicleRegistration"%>
<%@page import="com.trux.model.OrganizationMasterRegistration"%>
<%@page import="com.trux.model.DriverRegistration"%>
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

 
		 var dateToday = new Date(); 
		 		 
		/*  $(function() {
		   $('#disticCode').keyup(function(){
				    this.value = this.value.toUpperCase();
				});
		  $('#disMCode').keyup(function(){
			    this.value = this.value.toUpperCase();
			});
	     $('#vehicleNumber').keyup(function(){
			    this.value = this.value.toUpperCase();
			});
		 }); */
		

		 
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
		    }else{
		        document.getElementById('message').innerHTML="";
		        return true;	
		    } 
  }
 
		 
 function lettersOnly(evt,id) {
     evt = (evt) ? evt : event;
     var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode :
        ((evt.which) ? evt.which : 0));
     if (charCode > 31 && (charCode < 65 || charCode > 90) &&
        (charCode < 97 || charCode > 122)) {
    	 $("input#"+id).val(""); 
		    $("input#"+id).focus(); 
		     document.getElementById(id).value="";
		     document.getElementById(id).focus();
    	 document.getElementById('message').innerHTML="Enter letters only.";
        
        return false;
     }
     return true;
   }
 
 function fetchDriversMobiles()
 {        
 var mobile=$("#driverMobile").val();
 
 var xmlhttp;
 if (window.XMLHttpRequest)
   {// code for IE7+, Firefox, Chrome, Opera, Safari
   xmlhttp=new XMLHttpRequest();
   }
 
 else
   {// code for IE6, IE5
   xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
   }
 xmlhttp.onreadystatechange=function()
   {
   if (xmlhttp.readyState==4 && xmlhttp.status==200)
     {
 	var mobileValue=xmlhttp.responseText;
 	var mobileValues=mobileValue.replace("\"", "");
      $("#validNumber").val(mobileValues.replace("\"", ""));
     }
   }
 xmlhttp.open("GET","/trux/reg/validateDriverMobile?mobile="+mobile,true);
 xmlhttp.send();
 }
 
 function validateForm(){
	 fetchDriversMobiles();
 	 var flag;
 	 var ownerName=document.getElementById("ownerName").value;
 	 var ownerAddress=document.getElementById("ownerAddress").value;
 	 var ownerPhoneNumber=document.getElementById("ownerPhoneNumber").value;
 	 var vehicleNumber=document.getElementById("vehicleNumber").value;
     var vehicleModel=document.getElementById("vehicleModel").value;
     var month=document.getElementById("month").value;
     var year=document.getElementById("year").value;
   	 var disticCode=document.getElementById("disticCode").value;
  	 var disMCode=document.getElementById("disMCode").value; 
   	 var disNumber=document.getElementById("disNumber").value;
   	 var vehicleBody=document.getElementById("vehicleBody").value;
   	
 	 var vehicleStatus=document.getElementById("vehicleStatus").value;
 	 var vehicleType=document.getElementById("vehicleType").value; 
 	 var kmReading=document.getElementById("kmReading").value; /* 
 	 var ioRC=document.getElementById("ioRC").files.length>0;
 	 var ioPermitVehicle=document.getElementById("ioPermitVehicle").files.length>0; */
 	 var fuelType=document.getElementById("fuelType").value;
 	 var driverMobile=document.getElementById("driverMobile").value; 
     var validNumber=document.getElementById("validNumber").value;
     if(month!="" && year!=""){
         $('#vehicleModel').val(month+","+year);
		  }
 	 var message="";
 	if(ownerName==""){
 	  document.getElementById('message').style.display = "block";
 	  document.getElementById('message').style.color="red";
 	  message+="<br/>";
 	  message+="Please enter the owner name  !<br/>";
 	  document.getElementById("ownerName").style.borderColor="red";
 	  document.getElementById('ownerName').focus();
 	  flag= false;
 	  }else{
 	  document.getElementById("ownerName").style.borderColor="green"; 
 	  }
 	 if(ownerAddress==""){
 	   document.getElementById('message').style.display = "block";
 	   document.getElementById('message').style.color="red"; 	   
 	   message+="Please enter the owner address !<br/>";
 	   document.getElementById("ownerAddress").style.borderColor="red";
 	   document.getElementById('ownerAddress').focus();
 	   flag= false; 		 
 	   }else{
		 document.getElementById("ownerAddress").style.borderColor="green"; 
	   }
 	 
 	 if(ownerPhoneNumber==""){
 		document.getElementById("message").style.color="red";
 		document.getElementById('message').style.display = "block";
 		document.getElementById("ownerPhoneNumber").style.borderColor="red"; 		
 		message+="Please enter the Owner Phone Number !<br/>";
 	    document.getElementById('ownerPhoneNumber').focus();
 		flag= false;
 	   }else{
 		  document.getElementById("ownerPhoneNumber").style.borderColor="green";
 	   }
   
/*  if(ownerPhoneNumber!=""){
  if((ownerPhoneNumber.length != 10 || ownerPhoneNumber.length != 11)) {
 	document.getElementById('message').style.display = "block";
 	document.getElementById('message').style.color="red"; 	
 	message+="Phone Number Should be 11 digit !<br/> ";
 	document.getElementById("ownerPhoneNumber").style.borderColor="red";
 	document.getElementById('ownerPhoneNumber').focus(); 
 	flag= false; 
 	}else{
 	document.getElementById("ownerPhoneNumber").style.borderColor="green";
 	} 
 	} */
 	
 	
 	  
 	 if(disticCode==""){
 	 	document.getElementById("message").style.color="red";
 	 	document.getElementById('message').style.display = "block";
 	 	document.getElementById("disticCode").style.borderColor="red"; 	
 	 	message+="Please enter vehicle first alphabetice Number !<br/>";
 	 	document.getElementById('disticCode').focus();
 	 	flag= false;
 	 	}else{
 	 	document.getElementById("disticCode").style.borderColor="green";
 	 	}
 
 	 if(disMCode==""){
 	 	document.getElementById("message").style.color="red";
 	 	document.getElementById('message').style.display = "block";
 	 	document.getElementById("disMCode").style.borderColor="red"; 	
 	 	message+="Please enter vehicle  midil alpha-numeric number!<br/>";
 	 	document.getElementById('disMCode').focus();
 	 	flag= false;
 	 	}else{
 	 	document.getElementById("disMCode").style.borderColor="green";
 	 	}  
 	 if(disNumber==""){
 	 	document.getElementById("message").style.color="red";
 	 	document.getElementById('message').style.display = "block";
 	 	document.getElementById("disNumber").style.borderColor="red"; 	
 	 	message+="Please enter Vehicle last four digit number !<br/>";
 	 	document.getElementById('disNumber').focus();
 	 	flag= false;
 	 	}else{
 	 	document.getElementById("disNumber").style.borderColor="green";
 	 	}
	 
 	 if(disticCode!="" && disMCode!="" && disNumber!=""){
 		$('#vehicleNumber').val(disticCode.trim()+""+disMCode.trim()+""+disNumber.trim());
		}
 	 
 	$('#vehicleNumber').val(disticCode+""+disMCode+""+disNumber);
 	vehicleNumber=$('#vehicleNumber').val();
   if(vehicleNumber==""){
 	document.getElementById("message").style.color="red";
 	document.getElementById('message').style.display = "block";
 	document.getElementById("vehicleNumber").style.borderColor="red"; 	
 	message+="Please enter Vehicle Number !<br/>";
 	document.getElementById('vehcileNumberCantails').focus();
 	flag= false;
 	}else{
 	document.getElementById("vehcileNumberCantails").style.borderColor="green";
 	}
   if(vehicleBody==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("vehicleBody").style.borderColor="red"; 	
	 	message+="Please enter Vehicle Body !<br/>";
	 	document.getElementById('vehicleBody').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("vehicleBody").style.borderColor="green";
	 	}
   
  if(vehicleStatus==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("vehicleStatus").style.borderColor="red"; 	
	 	message+="Please select Vehicle Status  !<br/>";
	 	document.getElementById('vehicleStatus').focus();
	 	flag= false;
	 	}else {
	 	document.getElementById("vehicleStatus").style.borderColor="green";
	 	}
  if(vehicleType==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("vehicleType").style.borderColor="red"; 	
	 	message+="Please select Vehicle Type  !<br/>";
	 	document.getElementById('vehicleType').focus();
	 	flag= false;
	 	}else {
	 	document.getElementById("vehicleType").style.borderColor="green";
	 	}
    if(kmReading==""){
 	document.getElementById("message").style.color="red";
 	document.getElementById('message').style.display = "block";
 	document.getElementById("kmReading").style.borderColor="red"; 	
 	message+="Please enter KM Reading !<br/>";
 	document.getElementById('kmReading').focus();
 	flag= false;
 	}else{
 	document.getElementById("kmReading").style.borderColor="green";
 	}
    
   
  /* if(ioRC==false){
  	document.getElementById('message').style.display = "block";
  	document.getElementById('message').style.color="red";  	
    message+="Please upload Vehicle RC !<br/>";
  	document.getElementById("ioRC").style.borderColor="red";
  	document.getElementById('ioRC').focus();
  	flag= false; 		 
  	}else{
  	document.getElementById("ioRC").style.borderColor="green";
  	}
  if(ioPermitVehicle==false){ 
  	     document.getElementById('message').style.display = "block";
  	     document.getElementById('message').style.color="red";  	     
  	   message+="Please upload Permit Of Vehicle !<br/>";
  	     document.getElementById("ioPermitVehicle").style.borderColor="red";
  	 	 document.getElementById('ioPermitVehicle').focus(); 
  	 	 flag= false;
  	 	 } else{
  	 	 document.getElementById("ioPermitVehicle").style.borderColor="green"; 
  	} */
  if(fuelType==""){ 
	     document.getElementById('message').style.display = "block";
	     document.getElementById('message').style.color="red";  	     
	   message+="Please select Fuel Type Of Vehicle !<br/>";
	     document.getElementById("fuelType").style.borderColor="red";
	 	 document.getElementById('fuelType').focus(); 
	 	 flag= false;
	 	 } else{
	 	 document.getElementById("fuelType").style.borderColor="green"; 
	}
  if(driverMobile==""){
		document.getElementById("message").style.color="red";
		document.getElementById('message').style.display = "block";
		document.getElementById("driverMobile").style.borderColor="red";		
		message+="Please enter the Driver Phone Number !<br/>";
	    document.getElementById('driverMobile').focus();
		flag= false;
	   }else{
		  document.getElementById("driverMobile").style.borderColor="green";
	   }

  
/*  if(driverMobile!=""){
  if((driverMobile.length != 10 || driverMobile.length != 11)) {
	document.getElementById('message').style.display = "block";
	document.getElementById('message').style.color="red"; 	
	message+="Phone Number Should be 11 digit !<br/> ";
	document.getElementById("driverMobile").style.borderColor="red";
	document.getElementById('driverMobile').focus(); 
	flag= false; 
	}else{
	document.getElementById("driverMobile").style.borderColor="green";
	} 
	} */
	if(driverMobile!=""){
	if(driverMobile==validNumber){
		document.getElementById("driverMobile").style.borderColor="green"; 
    	}else{
    		document.getElementById('message').style.display = "block";
        	document.getElementById('message').style.color="red";            	
        	message+="Please enter register driver phone number. Enter phone is not exist Or not validate please enter again. !<br/>";
        	document.getElementById("driverMobile").style.borderColor="red";
        	document.getElementById('driverMobile').focus();
        	flag= false; 
    	}
	}
	 
		  if(month==""){
			 	document.getElementById("message").style.color="red";
			 	document.getElementById('message').style.display = "block";
			 	document.getElementById("month").style.borderColor="red"; 	
			 	message+="Please select month !<br/>";
			 	document.getElementById('month').focus();
			 	flag= false;
			 	}else{
			 	document.getElementById("month").style.borderColor="green";
			 	} 	
		  if(year==""){
			 	document.getElementById("message").style.color="red";
			 	document.getElementById('message').style.display = "block";
			 	document.getElementById("year").style.borderColor="red"; 	
			 	message+="Please select year !<br/>";
			 	document.getElementById('year').focus();
			 	flag= false;
			 	}else{
			 	document.getElementById("year").style.borderColor="green";
			 	} 	
		  if(vehicleModel==""){
			 	document.getElementById("message").style.color="red";
			 	document.getElementById('message').style.display = "block";
			 	document.getElementById("model").style.borderColor="red"; 	
			 	message+="Please enter Vehicle Model !<br/>";
			 	document.getElementById('model').focus();
			 	flag= false;
			 	}else{
			 	document.getElementById("model").style.borderColor="green";
			 	$('#vehicleModel').val(month+","+year);
			 	} 	
		  var ownerPhoneNumbers = isNaN(ownerPhoneNumber);
		   if(ownerPhoneNumbers==true){
			   document.getElementById('message').style.display = "block";
			 	document.getElementById('message').style.color="red"; 	
			 	message+="Phone Number Should be 11 digit Numerice !<br/> ";
			 	document.getElementById("ownerPhoneNumber").style.borderColor="red";
			 	document.getElementById('ownerPhoneNumber').focus(); 
			 	flag= false; 
		  }
		  var disNumbers = isNaN(disNumber);
	 	   if(disNumbers==true){
	 		document.getElementById("message").style.color="red";
	  	 	document.getElementById('message').style.display = "block";
	  	 	document.getElementById("disNumber").style.borderColor="red"; 	
	  	 	message+="Please enter Vehicle last four digit number !<br/>";
	  	 	document.getElementById('disNumber').focus();
	  	 	flag= false;
	 	  }
		  var driverMobiles = isNaN(driverMobile);
		  
		  if(driverMobiles==true){
			  document.getElementById("message").style.color="red";
			 document.getElementById('message').style.display = "block";
			document.getElementById("driverMobile").style.borderColor="red";		
			 message+="Please enter the Driver Phone Number !<br/>";
			    document.getElementById('driverMobile').focus();
				flag= false; 
		  }
		 
		  var kmReadings = isNaN(kmReading);
		   if(kmReadings==true){
			   document.getElementById("message").style.color="red";
			 	document.getElementById('message').style.display = "block";
			 	document.getElementById("kmReading").style.borderColor="red"; 	
			 	message+="Please enter KM Reading !<br/>";
			 	document.getElementById('kmReading').focus();
			 	flag= false;
		  }
		   
  if(message==""){flag=true;}
 	document.getElementById('message').innerHTML=message;
 	return flag;
 }
 
 //goople api
 
 var placeSearch, autocomplete,bankautocomplete;
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
       /** @type {HTMLInputElement} */(document.getElementById('ownerAddress')),
       { types: ['geocode'] });
   // When the user selects an address from the dropdown,
   // populate the address fields in the form.
   google.maps.event.addListener(autocomplete, 'place_changed', function() {
     fillInAddress();
   });
    
   bankautocomplete = new google.maps.places.Autocomplete(
	       /** @type {HTMLInputElement} */(document.getElementById('bankName')),
	       { types: ['bank'] });
	   // When the user selects an address from the dropdown,
	   // populate the address fields in the form.
	   google.maps.event.addListener(bankautocomplete, 'place_changed', function() {
	     fillInAddress();
	   });
   
 }

 // [START region_fillform]
 function fillInAddress() {
   // Get the place details from the autocomplete object.
   var place = autocomplete.getPlace();

   /* for (var component in componentForm) {
     document.getElementById(component).value = '';
     document.getElementById(component).disabled = false;
   } */

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
 
 
 function colletMonthYear(){
	 var month=$('#month').val()
	 var year=$('#year').val()
	 var vehicleModel=	$('#vehicleModel').val();
	  if(month==""){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("month").style.borderColor="red"; 	
		 	message+="Please select month !<br/>";
		 	document.getElementById('month').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("month").style.borderColor="green";
		 	} 	
	  if(year==""){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("year").style.borderColor="red"; 	
		 	message+="Please select year !<br/>";
		 	document.getElementById('year').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("year").style.borderColor="green";
		 	} 	
	  	
		$('#vehicleModel').val(month+"/"+year);
 }
 
   
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
{   var flag=false;
 var state = document.getElementById("state").value;
 var country = document.getElementById("country").value;
 var city = document.getElementById("city").value;
 var message="";
 if(country==""){
  	document.getElementById("message").style.color="red";
  	document.getElementById('message').style.display = "block";
  	document.getElementById("country").style.borderColor="red";
  	
  	message+="Please select the country !<br/>";
  	document.getElementById('country').focus();
  	flag= false;
  	}else{
  	document.getElementById("country").style.borderColor="green";
  	} 
    if(state==""){
 	 	document.getElementById("message").style.color="red";
 	 	document.getElementById('message').style.display = "block";
 	 	document.getElementById("state").style.borderColor="red";	 	
 	 	message+="Please select the state !<br/>";
 	 	document.getElementById('state').focus();
 	 	flag= false;
 	 	}else{
 	 	document.getElementById("state").style.borderColor="green";
 	 	}
    
    if(city==""){
 	 	document.getElementById("message").style.color="red";
 	 	document.getElementById('message').style.display = "block";
 	 	document.getElementById("city").style.borderColor="red";
 	 	
 	 	message+="Please select the city !<br/>";
 	 	document.getElementById('city').focus();
 	 	flag= false;
 	 	}else{
 	 	document.getElementById("city").style.borderColor="green";
 	 	} 
 
    if(message==""){flag=true;}
    if(flag==true){
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
    return false;
}
	</script>
	
	<script>	
function fillCluster()	
	{
	    var SelValue = document.getElementById("hubId").value;	 
		 $.ajax({
		      type: "POST",
		      url: "/trux/reg/getClusterByHub",
		      data:{
		    	  hubId:SelValue
				  } ,
		      success: function(data) {
		    	  document.getElementById("clusterId").innerHTML = data;
		    	  document.getElementById("clusterId").value.innerHTML=data;
		      }
		    });
	    return true;
	}
	
function fillUserList(){
	  
	  //var SelValue = document.getElementById("country").value;
		 
		 $.ajax({
		      type: "GET",
		      url: "/trux/reg/getUserList",
		       
		      success: function(data) {
		    	  document.getElementById("createdBy").innerHTML = data;
		    	  document.getElementById("createdBy").value.innerHTML=data;
		      }
		    });
	    return true;
	  
}

function fillStand()	
{  
 var SelValue = document.getElementById("clusterId").value;	 
	 $.ajax({
	      type: "POST",
	      url: "/trux/reg/getStandByCluster",
	      data:{
	    	  clusterId:SelValue
			  } ,
	      success: function(data) {
	    	  document.getElementById("standDetails").innerHTML = data;
	    	  document.getElementById("standDetails").value.innerHTML=data;
	      }
	    });
 return true;
}

function fillSubOrg()	
{   var flag=false;
 var SelValue = document.getElementById("hubId").value;	
 var SelOrgValue = document.getElementById("assosiatedBy").value;
 var message="";
 if(SelValue==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("hubId").style.borderColor="red";	 	
	 	message+="Please select the hub !<br/>";
	 	document.getElementById('hubId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("hubId").style.borderColor="green";
	 	}
if(SelOrgValue==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("assosiatedBy").style.borderColor="red";
	    message+="Please enter the Driver Assosiated By Organization name !<br/>";
	 	document.getElementById('assosiatedBy').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("assosiatedBy").style.borderColor="green";
	 	}
if(message==""){flag= true;}
if(flag==true){
	 $.ajax({
	      type: "POST",
	      url: "/trux/reg/getSubsidiaryOrg",
	      data:{
	    	  hubId:SelValue,
	    	  idClientMaster:SelOrgValue
			  } ,
	      success: function(data) {
	    	  document.getElementById("subOrgClient").innerHTML = data;
	    	  document.getElementById("subOrgClient").value.innerHTML=data;
	      }
	    });
 return true;
}
return false;
}

function addshowHide(){
	
	var x = $("#vehicleStatus").val();
	if(x=="Open Market"){
		$('#mapped').hide();
	}else{  
		$('#mapped').show();
	}
}
function pickTime(){
 $('#driverReportingTime').datetimepicker({
	 datepicker:false,
	  format:'H:i',
	  step:5 
	 });
 }
 
 
//Validate Decimal Value
function validateDecimal(id) {
   $(id).val($(id).val().replace(/[^0-9\.]/g,''));
           if ((event.which != 46 || $(id).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
               event.preventDefault();
           }
       
}
 </script>
  
</head>
 <body onload="initialize(); ">
 <div class="container">  
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager table-responsive">
		<legend class="borderManager" style="width: 100%;">Vehicle Registration  <% VehicleRegistration saveVehicaleBack=(VehicleRegistration) request.getAttribute("saveVehicaleBack"); if(saveVehicaleBack!=null && saveVehicaleBack.getId()!=null){%> <b style="color: green;">Registration ID :<%=saveVehicaleBack.getId() %></b><% }else if( saveVehicaleBack!=null && saveVehicaleBack.getId()==null) {%> <b style="color: red;">Vehicle Not Register</b><%} %>
    	  <a href="/trux/reg/updateVehicle" class="btn btn-danger btn-sm right-btn btn_nav1 right_btn_update" style="color: black;padding-right: 20px;padding-top: -2px;/* float: right; */"><b>Upload image</b></a>
		  <a href="/trux/reg/updateVehicle" class="btn btn-danger btn-sm right-btn btn_nav1 right_btn_update" style="color: black;padding-right: 20px;padding-top: -2px; margin-right:5px;/* float: right; */"><b>Edit</b></a>
    	</legend>
		  	<form  action="backEndvehicleRegistrations" method="post" class="form-inline" onsubmit="return validateForm();"  enctype="multipart/form-data">
				 <span style="float: left;margin-bottom: 5px; font-weight: bold; font-size: 16px; color: #525353;">Driver</span>
				   <div class="clearfix margin_5"></div>
				  <div class="clearfix margin_10"></div>
					<div class="row" style="margin-top:-2%;box-shadow: 1px 1px 7px #6A6A6A;padding: 10px;border-radius: 6px; ">
					<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Driver Mobile<span style="color: red;">*</span></div>
						<input type="text" name="driverMobile" id="driverMobile" maxlength="11"  onchange="fetchDriversMobiles();" onfocus="fillUserList()"  onkeyup="fetchDriversMobiles();"  onselect="fetchDriversMobiles();" onkeypress="validateDecimal(driverMobile);" class="form-control input-sm" style="width:100%;" placeholder="Driver Mobile (AS numeric value only)" required/>
						<input type="hidden" name="validNumber" id="validNumber" value=""> 
						</div>
					</div></br>
					
				  
						<span style="float: left;margin-bottom: 5px; font-weight: bold; font-size: 16px; color: #525353;">Vehicle</span>
				   <div class="clearfix margin_5"></div>
				  <div class="clearfix margin_10"></div>
					<div class="row" style="margin-top:-2%;box-shadow: 1px 1px 7px #6A6A6A;padding: 10px;border-radius: 6px; ">
						<div class="col-lg-3 col-md-3 col-sm-12"> 
						<div style="margin-bottom:6px;">Vehicle Number<span style="color: red;">*</span></div>
						 <div class="col-lg-12 col-md-12 col-sm-12" id="vehcileNumberCantails" style="border:1px solid #CCC9C9; border-radius: 4px;  padding: 4px;">
                            <input class="col-lg-4 col-md-4 col-sm-12" type="text" name="disticCode" id="disticCode"   class="form-control input-sm vehicle_width" maxlength="2" style=" text-align:center; padding: 0px;background:url(../resources/images/minus.png);background-repeat: no-repeat; background-position:right; " placeholder="DL" required>
							<input  class="col-lg-4 col-md-4 col-sm-12" type="text" name="disMCode" id="disMCode" class="form-control input-sm vehicle_width" maxlength="4" style="  text-align:center; padding: 0px;background:url(../resources/images/minus.png);background-repeat: no-repeat; background-position:right;" placeholder="11LV" required>
							<input class="col-lg-4 col-md-4 col-sm-12"  type="text" name="disNumber" id="disNumber"  class="form-control input-sm vehicle_width" maxlength="4" style=" text-align:center; padding: 0px;"  placeholder="8899" required>  
							</div>
						</div>
						<!-- <div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Vehicle Number<span style="color: red;">*</span></div>
						 -->	<input type="hidden" name="vehicleNumber" id="vehicleNumber"  class="form-control input-sm" style="width:100%;" placeholder="Vehicle Number"/>   
						<!-- </div>	 -->						
						 
						  		
						<div class="col-lg-3 col-md-3 col-sm-12">
						<div style="margin-bottom:6px;">Vehicle type<span style="color: red;">*</span></div>
							<select name="vehicleType" id="vehicleType" class="input-sm" style="width:100%;" required>
							<option value="">--Select Vehicle type</option>
							<option value="Mahindra Champion">Champion</option>
							<option value="Maruti Eeco">Eeco</option>
							<option value="Tata Ace">Tata Ace</option>
							
							<option value="Mahindra Champion">Champion</option>
							<option value="Maruti Eeco">Eeco</option>
							<option value="Tata Ace">Tata Ace</option>
							<option value="Tata 407">Tata 407 (10 Ft)</option>
							<option value="Tata 709">Tata 709 (14 Ft)</option>
							<option value="Bolero Pickup">Bolero Pickup</option>
		 					<option value="17 Feet Single Axle">17 Feet - Single Axle</option>
							<option value="17 Feet Double Axle">17 Feet - Double Axle</option>
							<option value="19 Feet Single Axle">19 Feet - Single Axle</option>
							<option value="19 Feet Double Axle">19 Feet - Double Axle</option>
							<option value="19 Feet Multi-Axle">19 Feet - Multi-Axle</option>
							<option value="22 Feet Single Axle">22 Feet - Single Axle</option>
							<option value="22 Feet Double Axle">22 Feet - Double Axle</option>			
							<option value="22 Feet Multi-Axle">22 Feet - Multi-Axle</option>
							<option value="24 Feet Single Axle">24 Feet - Single Axle</option>
							<option value="24 Feet Double Axle">24 Feet - Double Axle</option>	
							<option value="24 Feet Multi-Axle">24 Feet - Multi-Axle</option>
							<option value="28 Feet Single Axle">28 Feet - Single Axle</option>
							<option value="28 Feet Double Axle">28 Feet - Double Axle</option>	
							<option value="28 Feet Multi-Axle">28 Feet - Multi-Axle</option>
							<option value="32 Feet Single Axle">32 Feet - Single Axle</option>	
							<option value="32 Feet Double Axle">32 Feet - Double Axle</option>	
							<option value="32 Feet Multi-Axle">32 Feet - Multi-Axle</option>
							<option value="40 Feet Double Axle">40 Feet - Double Axle</option>	
							<option value="40 Feet Multi-Axle">40 Feet - Multi-Axle</option>
							</select> 
						</div>	
						 
						<div class="col-lg-3 col-md-3 col-sm-12">
						<div style="margin-bottom:6px;">Vehicle Body<span style="color: red;">*</span></div>
							<select name="vehicleBody" id="vehicleBody" class="input-sm" style="width:100%;" required>
							<option value="">--Select Vehicle type</option>
							<option value="Containerized">Containerized</option>
							<option value="Open body">Open body</option>
							</select> 
						</div>	
						 <div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">KM. Reading<span style="color: red;">*</span></div>
						<input type="text" name="kmReading" id="kmReading" class="form-control input-sm" onkeypress="validateDecimal(kmReading);" maxlength="11"  style="width:100%;" placeholder="KM. Reading" required/>
						</div>
						 
						
						<!-- <div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Vehicle RC<span style="color: red;">*</span></div>
						<input type="file" name="ioRC" id="ioRC"  maxlength="12" class="form-control input-sm" style="width:100%;" placeholder="Vehicle RC"/>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Vehicle Permit<span style="color: red;">*</span></div>
						<input type="file" name="ioPermitVehicle" id="ioPermitVehicle"  maxlength="12" class="form-control input-sm" style="width:100%;" placeholder="Vehicle Permi"/>
						</div> -->
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;margin-top: 10px;">Vehicle Fuel type<span style="color: red;">*</span></div>
							<select name="fuelType" id="fuelType" class="input-sm" style="width:100%;" required>
							<option value="">--Select Vehicle Fuel type</option>
							<option value="Petrol">Petrol</option>
							<option value="CNG">CNG</option>
							<option value="Diesel">Diesel </option>
							</select>
						</div>
					 	<%List<Integer> yearList= (List<Integer>)request.getAttribute("yearList");
						List<String> monthList= (List<String>)request.getAttribute("monthList");%>
						  <div class="col-lg-3 col-md-3 col-sm-12">
										<div style="margin-bottom: 6px;margin-top: 10px;">Vehicle Model Year<span style="color: red;">*</span>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12"
											style="border: 1px solid #999; border-radius: 4px; 0 px 12px 0 0; padding: 0;" id="model">
                                             	<select name="month" id="month" onchange="colletMonthYear();" class="col-lg-6 col-md-3 col-sm-12" style="height: 30px; border: none;border-radius:4px; padding: 1px;" required>
												<option value="">--Month--</option>
												<% for(int i=0;i<monthList.size();i++) {%>
												 <option value=<%=monthList.get(i) %>><%=monthList.get(i) %></option>
												 <%} %>
											    </select>  
											    <select name="year" id="year"  onchange="colletMonthYear();" class="col-lg-6 col-md-3 col-sm-12" style="height: 30px; border: none;border-radius:4px; padding: 1px;" required>
												<option value="">--Year</option>
												<% for(int i=0;i<yearList.size();i++) {%>
												 <option value=<%=yearList.get(i) %>><%=yearList.get(i) %></option>
												 <%} %>
											    </select>    
										</div>
									</div>
									
									<div class="col-lg-3 col-md-3 col-sm-12">
										<div style="margin-bottom: 6px;margin-top: 10px;">Vehicle Insurance Expiry
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12"
											style="border: 1px solid #999; border-radius: 4px; 0 px 12px 0 0; padding: 0;" id="model">
											<input type="text" name="insuranceExpiryDate" id="insuranceExpiryDate" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #AEAEAE;border: 1px solid #AEAEAE; background:url(../resources/images/calender2.png);background-repeat: no-repeat; background-position:right;  ' placeholder="Select Insurance Expiry Date">    
										</div>
									</div>
								</div></br>
								<span style="float: left;margin-bottom: 5px; font-weight: bold; font-size: 16px; color: #525353;">Owner</span>
				   <div class="clearfix margin_5"></div>
				  <div class="clearfix margin_10"></div>
					<div class="row" style="margin-top:-2%;box-shadow: 1px 1px 7px #6A6A6A;padding: 10px;border-radius: 6px; ">                                        									                                  
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Owner Name<span style="color: red;">*</span></div>
							<input type="text" name="ownerName" id="ownerName" class="form-control input-sm" style="width:100%;"  placeholder="Owner Name" required/>  
						</div>                                               
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Owner Addresse<span style="color: red;">*</span></div>
							<input type="text" name="ownerAddress" id="ownerAddress"   onFocus="geolocate()" class="form-control input-sm" style="width:100%;" placeholder="Owner Addresse" required/>   
						</div>	
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Owner Phone Number<span style="color: red;">*</span></div>
							<input type="text" name="ownerPhoneNumber" id="ownerPhoneNumber" maxlength="11"  onkeypress="validateDecimal(ownerPhoneNumber);" class="form-control input-sm" style="width:100%;" placeholder="Owner Phone Number (AS numeric value only)" required/>   
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12">    
							<div style="margin-bottom:6px;">PAN Number<!-- <span style="color: red;">*</span> --></div>
							<input type="text" name="panNumber" id="panNumber" maxlength="13"   class="form-control input-sm" style="width:100%;"  placeholder="Pan Card Number" />  
						</div> 
						
					 
					 <div class="clearfix margin_5"></div>
				  <div class="clearfix margin_10"></div>
					 	    <div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Bank Acount Holder Name<!-- <span style="color: red;">*</span> --></div>
							<input type="text" name="accountHolderName" id="accountHolderName" maxlength="255"  class="form-control input-sm" style="width:100%;" placeholder="Account Holder Name" />   
						</div> 
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Bank Name<!-- <span style="color: red;">*</span> --></div>
						<!-- 	<input type="text" name="bankName" id="bankName" maxlength="255"   />   --> 
						<select name="bankName" id="bankName"  class="form-control input-sm" style="width:100%;" placeholder="Bank Name">
						<option value="">--Select Bank--</option>
						<option value="Allahabad Bank">Allahabad Bank</option>
						<option value="Andhra Bank">Andhra Bank</option>
						<option value="Axis Bank">Axis Bank</option>
						<option value="Bank of Bahrain and Kuwait">Bank of Bahrain and Kuwait</option>
						<option value="Bank of Baroda - Corporate Banking">Bank of Baroda - Corporate Banking</option>
						<option value="Bank of Baroda - Retail Banking">Bank of Baroda - Retail Banking</option>
						<option value="Bank of India">Bank of India</option>
						<option value="Bank of Maharashtra">Bank of Maharashtra</option>
						<option value="Canara Bank">Canara Bank</option>
						<option value="Central Bank of India">Central Bank of India</option>
						<option value="City Union Bank">City Union Bank</option>
						<option value="Corporation Bank">Corporation Bank</option>
						<option value="Deutsche Bank">Deutsche Bank</option>
						<option value="Development Credit Bank">Development Credit Bank</option>
						<option value="Dhanlaxmi Bank">Dhanlaxmi Bank</option>
						<option value="Federal Bank">Federal Bank</option>
						<option value="HDFC Bank">HDFC Bank</option>
						<option value="ICICI Bank">ICICI Bank</option>
						<option value="IDBI Bank">IDBI Bank</option>
						<option value="Indian Bank">Indian Bank</option>
						<option value="Indian Overseas Bank">Indian Overseas Bank</option>
						<option value="IndusInd Bank">IndusInd Bank</option>
						<option value="ING Vysya Bank">ING Vysya Bank</option>
						<option value="Jammu and Kashmir Bank">Jammu and Kashmir Bank</option>
						<option value="Karnataka Bank Ltd">Karnataka Bank Ltd</option>
						<option value="Karur Vysya Bank">Karur Vysya Bank</option>
						<option value="Kotak Bank">Kotak Bank</option>
						<option value="Laxmi Vilas Bank">Laxmi Vilas Bank</option>
						<option value="Oriental Bank of Commerce">Oriental Bank of Commerce</option>
						<option value="Punjab National Bank - Corporate Banking">Punjab National Bank - Corporate Banking</option>
						<option value="Punjab National Bank - Retail Banking">Punjab National Bank - Retail Banking</option>
						<option value="Punjab & Sind Bank">Punjab & Sind Bank</option>
						<option value="Shamrao Vitthal Co-operative Bank">Shamrao Vitthal Co-operative Bank</option>
						<option value="South Indian Bank">South Indian Bank</option>
						<option value="State Bank of Bikaner & Jaipur">State Bank of Bikaner & Jaipur</option>
						<option value="State Bank of Hyderabad">State Bank of Hyderabad</option>
						<option value="State Bank of India">State Bank of India</option>
						<option value="State Bank of Mysore">State Bank of Mysore</option>
						<option value="State Bank of Patiala">State Bank of Patiala</option>
						<option value="State Bank of Travancore">tate Bank of Travancore</option>
						<option value="Syndicate Bank">Syndicate Bank</option>
						<option value="Tamilnad Mercantile Bank Ltd.">Tamilnad Mercantile Bank Ltd.</option>
						<option value="UCO Bank">UCO Bank</option>
						<option value="Union Bank of India">Union Bank of India</option>
						<option value="United Bank of India">United Bank of India</option>
						<option value="Vijaya Bank">Vijaya Bank</option>
						<option value="Yes Bank Ltd">Yes Bank Ltd</option>
						
						</select>
						</div>                                          
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Account Number<!-- <span style="color: red;">*</span> --></div>
							<input type="text" name="accountNumber" id="accountNumber"  maxlength="13"   onkeypress="validateDecimal(accountNumber);" class="form-control input-sm" style="width:100%;" placeholder="Account Number" />   
						</div>	
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">IFSC Code <!-- <span style="color: red;">*</span> --></div>
							<input type="text" name="ifscCode" id="ifscCode" maxlength="11"  class="form-control input-sm" style="width:100%;" placeholder="IFSC Code"  />   
						</div>
					</div></br>
                        <!-- onkeyup="isNumberKey(event,'ownerPhoneNumber');"
						onkeyup="isNumberKey(event,'disNumber');"
						onkeyup="lettersOnly(event,'disticCode');" 
						isNumberKey(event,'driverMobile'), fetchDriversMobiles();
						-->
				 <span style="float: left;margin-bottom: 5px; font-weight: bold; font-size: 16px; color: #525353;">Client Association</span>
				   <div class="clearfix margin_5"></div>
				  <div class="clearfix margin_10"></div>
					<div class="row" style="margin-top:-2%;box-shadow: 1px 1px 7px #6A6A6A;padding: 10px;border-radius: 6px; ">							
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Country<span style="color: red;">*</span></div>
							   <select name="country" id="country" onchange="fillState()" class="input-sm" style="width:100%;" required>
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
									
									$('#insuranceExpiryDate').datetimepicker({
									    timepicker:false,
									    format:'Y/m/d'
										 });
    						}); 
    				        </script>
						 </div>
						  
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">State<span style="color: red;">*</span></div>
							<select name="state" id="state" onchange="fillCity();" class="input-sm" style="width:100%;" required>
							<option value="">--Select state--</option>
							 </select> 
						</div>
						
						
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">City<span style="color: red;">*</span></div>
						<select name="city" id="city" onchange="fillClusterByCSC();"  class="input-sm" style="width:100%;">
							<option value="">--Select city--</option>
						</select>    
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Hub<span style="color: red;">*</span></div>
						<select name="hubId" id="hubId" onchange="fillCluster()" class="input-sm" style="width:100%;">
							<option value="">--Select Hub--</option>
							 
							</select> 
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;margin-top: 10px;">Cluster<span style="color: red;">*</span></div>
						<select name="clusterId" id="clusterId" onchange="fillStand()" class="input-sm" style="width:100%;">
							<option value="">--Select Cluster--</option>
						</select> 
						</div>
						
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;margin-top: 10px;">Stand ( DC )<span style="color: red;">*</span></div>
						<select name="standDetails" id="standDetails" class="input-sm" style="width:100%;">
							<option value="">--Select Stand--</option>
						</select> 
						</div>
					  	
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;margin-top: 10px;">Vehicle Association <span style="color: red;">*</span></div>
						<select name="vehicleStatus" id="vehicleStatus"  class="input-sm" style="width:100%;" onchange="addshowHide();">
                                 <option value="">--Select Association--</option>
                                  <option value="Mapped">Leased</option>
                                  <option value="Free">On-Demand</option>
                                  <option value="Open Market">Open Market</option>                        
                             </select>
						 
						</div>	 			 
						<div id="mapped" style="display:none;">
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;margin-top: 10px;">Associated By Client <span style="color: red;">*</span></div>
						<select name="assosiatedBy" id="assosiatedBy" onchange="fillSubOrg();"  class="input-sm" style="width:100%;">
                                 <option value="">--Select Client--</option>
                                 <% List<OrganizationMasterRegistration>  list= (List<OrganizationMasterRegistration>)session.getAttribute("orgList");
									if(list != null && !list.isEmpty()){
									for(int i=0; i<list.size();i++){ 
									%>	
								 <option value="<%=list.get(i).getIdClientMaster() %>"><%=list.get(i).getName() %></option>
                                 <%	}}%>                        
                             </select>
						 
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;margin-top: 10px;"> Client Subsidiary <span style="color: red;">*</span></div>
						<select name="subOrgClient" id="subOrgClient"  class="input-sm" style="width:100%;">
                                 <option value="">--Select Client Subsidiary--</option>
                              </select>
					 	</div>
						<!-- <div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Driver reporting time <span style="color: red;">*</span></div>
						<input type="text" name="driverReportingTime" id="driverReportingTime" onclick="pickTime();" class="form-control input-sm"  style="width:100%; color: black; background-color: white;" readonly="readonly">
                        </div>
                        </div -->
						 
						 <input type="hidden" name="vehicleModel" id="vehicleModel"  value="" />
						
						</div> 
				 </div></br>
				 
				 <span style="float: left;margin-bottom: 5px; font-weight: bold; font-size: 16px; color: #525353;">Registered By</span>
				   <div class="clearfix margin_5"></div>
				  <div class="clearfix margin_10"></div>
					<div class="row" style="margin-top:-2%;box-shadow: 1px 1px 7px #6A6A6A;padding: 10px;border-radius: 6px; ">							
						  
						  <div class="col-lg-3 col-md-3 col-sm-12">
						 <div style="margin-bottom:6px;">Registered By<!-- <span style="color: red;">*</span> --></div>
							 <select name="createdBy" id="createdBy" class="input-sm" style="width:100%;">
                                 <option value="0">--Select User--</option>
                                                       
                             </select>
							   
						</div>
						 
				 </div>
				 
						<div class="clearfix margin_05"></div> 
						<div class="col-lg-3 col-md-3 col-sm-12">     
							<div style="margin-top:6px; font-size:14px; text-align:left; margin-left:0px;margin-top:8px;">
								<input type="submit" class="btn btn-danger btn-sm btn_nav1" value="Register Now">
								<input type="reset" class="btn btn-danger btn-sm btn_nav1" value="clear"> 
						                             
							</div>
						</div> 
					</div><!--to delete-->											
					<!-- <div class="clearfix margin_10"></div> -->
				
			</form>
			 
			 <div id="message" style="color: red;"></div>  
		
			</fieldset>
		</div>
	</div>		
</div>

 
  </body>
</html>
 
	 
