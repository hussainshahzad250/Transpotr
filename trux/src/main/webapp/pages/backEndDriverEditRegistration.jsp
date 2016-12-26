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

  
 
		 var dateToday = new Date(); 
		         
		 $(function() {
			 $('#rideTime').datetimepicker({
				 timeFormat: 'HH:mm z',
				 dayOfWeekStart : 1,
				 lang:'en',
				 startDate:	dateToday //'1986/01/05'
				 });
			 });
		 
		 
		 
		  function fetchDriverMobiles()
	        {        
	        var mobile=document.getElementById("driverContactNumber").value;
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
	             document.getElementById("validNumber").value=mobileValues.replace("\"", "");
	            }
	          }
	        xmlhttp.open("GET","/trux/reg/validateDriverMobile?mobile="+mobile,true);
	        xmlhttp.send();
	        }
	        
		  
		  
		  function isNumberKey(evt,id)
		  { 
		     var charCode = (evt.which) ? evt.which : event.keyCode;
		     if (charCode > 31 && (charCode < 48 || charCode > 57) && (charCode < 96 || charCode > 105))
		     { 
		     document.getElementById('message').innerHTML="Please enter numeric value only !";
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
	 
 function validateForm(){
 	 var flag; 
 	 var driverName=document.getElementById("driverName").value;
 	 var gender=document.getElementById("gender").value;
 	 var driverContactNumber=document.getElementById("driverContactNumber").value;
 	 var drivingExperience=document.getElementById("drivingExperience").value;/* 
 	 var localAddress=document.getElementById("localAddress").value;
 	 var permanentAddress=document.getElementById("permanentAddress").value
 	 var standDetails=document.getElementById("standDetails").value; 
 	 var assosiatedBy=document.getElementById("assosiatedBy").value;  
 	 var country=document.getElementById("country").value;
 	 var state=document.getElementById("state").value; 	 
 	 var city=document.getElementById("city").value;
 	 var hubId=document.getElementById("hubId").value;
 	 var clusterId=document.getElementById("clusterId").value; 	
 	 var validNumber=document.getElementById("validNumber").value;

 	 var driverReportingTime=document.getElementById("driverReportingTime").value;
 	var dstatus = $("#dstatus").val(); */
 	
 	
 	 /* var imagesOfDL=document.getElementById("imagesOfDL").files.length>0; *//* 
 	 var ioPanCard=document.getElementById("ioPanCard").files.length>0; 
 	var iOPVReports=document.getElementById("iOPVReports").files.length>0; 
 	var subOrgClient=document.getElementById("subOrgClient").value*/
 	 var message="";
 	if(driverName==""){
 	  document.getElementById('message').style.display = "block";
 	  document.getElementById('message').style.color="red";
 	  message+="<br/>";
 	  message+="Please enter the Driver name  !<br/>";
 	  document.getElementById("driverName").style.borderColor="red";
 	  document.getElementById('driverName').focus();
 	  flag= false;
 	  }else{
 	  document.getElementById("driverName").style.borderColor="green"; 
 	  }
 	 if(gender==""){
 	   document.getElementById('message').style.display = "block";
 	   document.getElementById('message').style.color="red";
 	   
 	   message+="Please select the gender !<br/>";
 	   document.getElementById("gender").style.borderColor="red";
 	   document.getElementById('gender').focus();
 	   flag= false; 		 
 	   }else{
		 document.getElementById("gender").style.borderColor="green"; 
	   }
 	if(driverContactNumber==""){
 	 	document.getElementById('message').style.display = "block";
 	 	document.getElementById('message').style.color="red";
 	    message+="Please enter the Driver Contact Number !<br/>";
 	 	document.getElementById("driverContactNumber").style.borderColor="red";
 	 	document.getElementById('driverContactNumber').focus();
 	 	flag= false; 		 
 	 	}else{
 	 	document.getElementById("driverContactNumber").style.borderColor="green";
 	 	}
 	if(driverContactNumber!=""){
 	if((driverContactNumber.length != 10)) {
 	document.getElementById('message').style.display = "block";
 	document.getElementById('message').style.color="red"; 	
 	message+="Phone Number Should be 10 digit !<br/> ";
 	document.getElementById("driverContactNumber").style.borderColor="red";
 	document.getElementById('driverContactNumber').focus(); 
 	flag= false; 
 	}else{
 	document.getElementById("driverContactNumber").style.borderColor="green";
 	} 
 	}
 	/* if(validNumber!=""){
 	if(driverContactNumber==validNumber){
    	document.getElementById('message').style.display = "block";
    	document.getElementById('message').style.color="red";            	
    	message+="Please enter other phone number. Enter phone already exist !<br/>";
    	document.getElementById("driverContactNumber").style.borderColor="red";
    	document.getElementById('driverContactNumber').focus();
    	flag= false; 		 
    	}else{
    	document.getElementById("driverContactNumber").style.borderColor="green";
    	}
 	} */
 	/*  if(drivingExperience==""){
 		document.getElementById("message").style.color="red";
 		document.getElementById('message').style.display = "block";
 		document.getElementById("drivingExperience").style.borderColor="red";
 		
 		message+="Please enter the Driving Experience  as year !<br/>";
 	    document.getElementById('drivingExperience').focus();
 		flag= false;
 	   }else{
 		  document.getElementById("drivingExperience").style.borderColor="green";
 	   }
   */
 	/* if(localAddress=="" && !localAddress.length>0){
 	document.getElementById('message').style.display = "block";
 	document.getElementById('message').style.color="red"; 	
 	message+="Please enter the local address !<br/>";
 	document.getElementById("localAddress").style.borderColor="red";
 	document.getElementById('localAddress').focus();
 	flag= false; 		 
 	}else{
 	document.getElementById("localAddress").style.borderColor="green";
 	} */
 /* 	if(permanentAddress=="" && !permanentAddress.length>0){
 	 	document.getElementById('message').style.display = "block";
 	 	document.getElementById('message').style.color="red";
 	 	
 	 	message+="Please enter the Permanent address !<br/>";
 	 	document.getElementById("permanentAddress").style.borderColor="red";
 	 	document.getElementById('permanentAddress').focus();
 	 	flag= false; 		 
 	 	}else{
 	 	document.getElementById("permanentAddress").style.borderColor="green";
 	 	} */
 /* 	if(standDetails==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("standDetails").style.borderColor="red";
	 	
	 	message+="Please select the standDetails !<br/>";
	 	document.getElementById('standDetails').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("standDetails").style.borderColor="green";
	 	}

 	
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
   if(hubId==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("hubId").style.borderColor="red";	 	
	 	message+="Please select the hub !<br/>";
	 	document.getElementById('hubId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("hubId").style.borderColor="green";
	 	}
   if(clusterId==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("clusterId").style.borderColor="red";	 	
	 	message+="Please select the cluster !<br/>";
	 	document.getElementById('clusterId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("clusterId").style.borderColor="green";
	 	} */
   
   /* if(imagesOfDH==false){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("imagesOfDH").style.borderColor="red";	 	
	 	message+="Please upload the driver photo !<br/>";
	 	document.getElementById('imagesOfDH').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("imagesOfDH").style.borderColor="green";
	 	} */
 /*   if(imagesOfDL==false){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("imagesOfDL").style.borderColor="red";	 	
	 	message+="Please upload the image of driving licence !<br/>";
	 	document.getElementById('imagesOfDL').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("imagesOfDL").style.borderColor="green";
	 	} */
/*    if(ioPanCard==false){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("ioPanCard").style.borderColor="red";	 	
	 	message+="Please upload the image of driver PAN card !<br/>";
	 	document.getElementById('ioPanCard').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("ioPanCard").style.borderColor="green";
	 	}
   if(iOPVReports==false){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("iOPVReports").style.borderColor="red";	 	
	 	message+="Please upload the image of driver police verification report !<br/>";
	 	document.getElementById('iOPVReports').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("iOPVReports").style.borderColor="green";
	 	} */
	 	/* if(dstatus=="Free" || dstatus==""){
	 		document.getElementById('subOrgClient').value="0";
	 		document.getElementById('assosiatedBy').value="0";
	 	}else{ */
	 	/* 	if(dstatus=="Open Market" || dstatus=="Free" || dstatus==""){
		 		document.getElementById('subOrgClient').value="0";
		 		document.getElementById('assosiatedBy').value="0";
		 		document.getElementById('driverReportingTime').value="00:00";
			 	
		 	}else{
       if(subOrgClient==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("subOrgClient").style.borderColor="red";	 	
	 	message+="Please select the Subsidiary Organization name !<br/>";
	 	document.getElementById('subOrgClient').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("subOrgClient").style.borderColor="green";
	 	}
       
    	if(assosiatedBy==""){
    	 	document.getElementById("message").style.color="red";
    	 	document.getElementById('message').style.display = "block";
    	 	document.getElementById("assosiatedBy").style.borderColor="red";
    	 	
    	 	message+="Please enter the Driver Assosiated By Organization name !<br/>";
    	 	document.getElementById('assosiatedBy').focus();
    	 	flag= false;
    	 	}else{
    	 	document.getElementById("assosiatedBy").style.borderColor="green";
    	 	}
    	
    	if(driverReportingTime==""){
    	 	document.getElementById("message").style.color="red";
    	 	document.getElementById('message').style.display = "block";
    	 	document.getElementById("driverReportingTime").style.borderColor="red";
    	 	
    	 	message+="Please enter the driver reporting time !<br/>";
    	 	document.getElementById('driverReportingTime').focus();
    	 	flag= false;
    	 	}else{
    	 	document.getElementById("driverReportingTime").style.borderColor="green";
    	 	} 
		 	}*/
    	
  if(message==""){flag=true;}
 	document.getElementById('message').innerHTML=message;
 	return flag;
 }
 
 //goople api
 
 var placeSearch, autocomplete,autocompleteTo,autocompleteStand;
 var componentForm = {
   street_number: 'short_name',
   route: 'long_name',
   locality: 'long_name',
   administrative_area_level_1: 'short_name',
   country: 'long_name',
   postal_code: 'short_name'
 };

 
 //
 function initialize() {
   // Create the autocomplete object, restricting the search
   // to geographical location types.
   autocomplete = new google.maps.places.Autocomplete(
       /** @type {HTMLInputElement} */(document.getElementById('localAddress')),
       { types: ['geocode'] });
   // When the user selects an address from the dropdown,
   // populate the address fields in the form.
   google.maps.event.addListener(autocomplete, 'place_changed', function() {
     fillInAddress();
   });
   
   autocompleteTo = new google.maps.places.Autocomplete(
	       /** @type {HTMLInputElement} */(document.getElementById('permanentAddress')),
	       { types: ['geocode'] });
	   // When the user selects an address from the dropdown,
	   // populate the address fields in the form.
	   google.maps.event.addListener(autocompleteTo, 'place_changed', function() {
	     fillInAddress();
	   });
	   autocompleteStand = new google.maps.places.Autocomplete(
		       /** @type {HTMLInputElement} */(document.getElementById('standDetails')),
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
 
 function searchDriver(){
 var mobile=$("#driverphone").val();
 $.ajax({
      type: "GET",
      url: "/trux/reg/searchExistDriver",
      data:{
    	  driverPhone:mobile
		  } ,
        success: function(data) {
    	////console.log(data);;
    	$("#driverId").val(data.id);
    	$("#driverName").val(data.firstName);
  		$("#gender").val(data.gender);
  		$("#driverContactNumbers").val(data.phoneNumber);
  		$("#driverContactNumber").val(data.phoneNumber);
  		$("#drivingExperience").val(data.drivingExperience);
  		$("#localAddress").val(data.localAddress);
  		$("#permanentAddress").val(data.address);
  		$("#standDetails").val(data.standDetails); 
  		$("#assosiatedBy").val(data.assosiatedBy); 
  		$("#subOrgClient").val(data.subOrgClient); 		        		
  		$("#country").val(data.country);
  		$("#state").val(data.state); 	 
  		$("#city").val(data.city);
  		$("#hubId").val(data.hubId);
  		$("#clusterId").val(data.cluster); 	
  		$("#validNumber").val(data.phoneNumber);
  	 	$("#dstatus").val(data.dstatus);
  	 	$("#subOrgClientMe").val(data.subOrgClient);
  		$("#driverReportingTime").val(data.driverReportingTime);
  	  	 
  	 	
  	 	var x = $("#dstatus").val();
  		if(x=="Mapped"){
  			$('#mapped').show();
  		}else{  
  			$('#mapped').hide();
  		}
  		$("#stateM").html("( "+data.state+" )"); 	 
  		$("#cityM").html("( "+data.city+" )");
  		$("#hubM").html("( "+data.hubId+" )");
  		$("#clusterM").html("( "+data.cluster+" )");
  		$("#assosiatedByM").html("( "+data.assosiatedBy+" )"); 
  	    $("#standDetailsM").html("( "+data.standDetails+" )"); 
  	    $("#subOrgClientM").html("( "+data.subOrgClient+" )"); 
  	  
  		 
      }
    });

 return true;
 
 var subOrgClientMe=$("#subOrgClient").val();
 if(subOrgClientMe!=""){
  searchSubOrgRegisterDriver();
 }
 var clusterId= $("#clusterId").val();
 if(clusterId!=""){
  searchClusterName(); 
 }
 var standDetails= $("#standDetails").val();
 if(standDetails!=""){
	 searchStandName(); 
 }
 var cityId= $("#city").val();
 if(cityId!=""){
	 searchCityName(); 
 }
}
 
 
 function searchSubOrgRegisterDriver(){
	 var subOrgClientMe=$("#subOrgClient").val();
	 $.ajax({
	      type: "GET",
	      url: "/trux/reg/getSubsidiaryOrgNameById",
	      data:{
	    	  idClientMaster:subOrgClientMe
			  } ,
	        success: function(data) {
	    	//console.log(data);;
	    	$("#subOrgClientM").html(data.subName);  
	      }
	    });
	 
	 return true;
	}
 
    
 function searchClusterName(){
   var clusterId= $("#clusterId").val();
	 $.ajax({
	      type: "GET",
	      url: "/trux/reg/getClusterById",
	      data:{
	    	  clusterId:clusterId
			  } ,
	        success: function(data) {
	    	//console.log(data);;
	    	$("#clusterM").html(data.clusterName);  
	      }
	    });
	 
	 return true;
	}
 
 
 function searchStandName(){
	    var standDetails= $("#standDetails").val();
		 $.ajax({
		      type: "GET",
		      url: "/trux/reg/getStandById",
		      data:{
		    	  standDetailsId:standDetails
				  } ,
		        success: function(data) {
		    	//console.log(data);;
		    	$("#standDetailsM").html(data.standName);  
		      }
		    });
		 
		 return true;
		}
 
    
 function searchCityName(){
	    var cityId= $("#city").val();
		 $.ajax({
		      type: "GET",
		      url: "/trux/reg/getCitiesById",
		      data:{
		    	  cityId:cityId
				  } ,
		        success: function(data) {
		    	//console.log(data);;
		    	$("#cityM").html(data.cityName);  
		      }
		    });
		 
		 return true;
		}

  /* 
	$(function() {
	 	$('#search').click(function() {
	  	$('#driverphone').autocomplete({		 
			serviceUrl: '/trux/reg/searchExistDriver',
			paramName: "driverPhone",
			delimiter: ",",
		    transformResult: function(response) {		    	
		        return {
		        	   suggestions: $.map($.parseJSON(response), function(item) {
		        		$("#driverName").val(item.firstName);
		        		$("#gender").val(item.gender);
		        		$("#driverContactNumber").val(item.phoneNumber);
		        		$("#drivingExperience").val(item.drivingExperience);
		        		$("#localAddress").val(item.localAddress);
		        		$("#permanentAddress").val(item.address);
		        		$("#standDetails").val(item.standDetails); 
		        		$("#assosiatedBy").val(item.assosiatedBy); 
		        		$("#subOrgClient").val(item.subOrgClient); 		        		
		        		$("#country").val(item.country);
		        		$("#state").val(item.state); 	 
		        		$("#city").val(item.firstname);
		        		$("#hubId").val(item.hubId);
		        		$("#clusterId").val(item.cluster); 	
		        		$("#validNumber").val(item.firstname);
		        	 	$("#dstatus").val(item.dstatus); 
		                return { value: item.hubId, data: item.firstName, data: item.gender, data: item.phoneNumber,data: item.drivingExperience,data: item.localAddress,data: item.address, data: item.standDetails, data: item.assosiatedBy , data: item.cluster , data: item.subOrgClient , data: item.country, data: item.state, data: item.city,data: item.id  };
		              }) };
		            }  
		}); 
		}); */
	</script>
	
	 <script>
 
	
function fillState()	
	{    var SelValue = document.getElementById("country").value;
	    $.ajax({
		      type: "POST",
		      url: "/trux/reg/getCountryStatesToEdit",
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
		      url: "/trux/reg/getCitiesByStateToEdit",
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
	      url: "/trux/reg/getClusterByCSCToEdit",
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
		      url: "/trux/reg/getClusterByHubToEdit",
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

function fillStand()	
{  
    var SelValue = document.getElementById("clusterId").value;	 
	 $.ajax({
	      type: "POST",
	      url: "/trux/reg/getStandByClusterToEdit",
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
	      url: "/trux/reg/getSubsidiaryOrgToEdit",
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
	
	var x = $("#dstatus").val();
	if(x=="Mapped"){
		$('#mapped').show();
	}else{  
		$('#mapped').hide();
	}
}

function driverList()	
{    var orgId = document.getElementById("orgName").value;
 
	 $.ajax({
	      type: "POST",
	      url: "/trux/attandance/getDriverDetails",
	      data:{
	    	  organizationId:orgId
			  } ,
	      success: function(data) {
	    	  document.getElementById("driver").innerHTML = data;
	    	  document.getElementById("driver").value.innerHTML=data;
	      }
	    });
    return true;
}
	</script>
	
	<script type="text/javascript">
	function fnOpenNormalDialog() {
	    //$("#dialog-confirm").html("Confirm Dialog Box");
	    $('#dialog-confirm').show();
	    //$("#dialog").dialog({ autoOpen: false });
	    
	    $("#dialog-confirm").dialog();
 
	 /*    // Define the Dialog and its properties.
	    $("#dialog-confirm").dialog({
	        resizable: false,
	        modal: true,
	        title: "Modal",
	        height: 250,
	        width: 400,
	        buttons: {
	            "Yes": function () {
	                $(this).dialog('close');
	                callback(true);
	            },
	                "No": function () {
	                $(this).dialog('close');
	                callback(false);
	            }
	        }
	    }); */
	}

	$('#btnOpenDialog').click(fnOpenNormalDialog);

	function callback(value) {
	    if (value) {
	        alert("Confirmed");
	    } else {
	        alert("Rejected");
	    }
	}
	
	
	function pickTime(){
	    $('#driverReportingTime').datetimepicker({
		 datepicker:false,
		  format:'H:i',
		  step:5 
		 });
	    }
	</script>
</head>
 <body onload="initialize();">
 <div class="container">  
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager table-responsive">  <legend class="borderManager" style="width:100%;">Driver Update  <% DriverRegistration rs=(DriverRegistration) request.getAttribute("reg"); if(rs!=null){%><%if(rs.getId()==0){%><b style="color: red;">Driver Not Update. Might be sume error <%=rs.getErrorMesaage() %> </b><%}else{%><b style="color: green;">Driver Id <%=rs.getId() %> Update Successfully !</b><% }}%><a href="/trux/reg/driver" class="btn btn-danger btn-sm right-btn btn_nav1 right_btn_update" style="color: black;padding-right: 20px;padding-top: -2px;/* float: right; */"><b>Add New Driver</b></a> &nbsp; <!-- <input type="button" id="btnOpenDialog" onclick="fnOpenNormalDialog();" value="Upload Driver Documents" class="btn btn-danger btn-sm right-btn btn_nav1 right_btn_update"/> --></legend>
	 		 	
	 			<form  action="/trux/reg/searchEditDrivers" method="post" class="form-inline" onsubmit="return validateForm();" enctype="multipart/form-data">
					<div class="row" style="margin-top:-2%; "> 
					<div class="col-lg-3 col-md-3 col-sm-12">
			<div style="margin-bottom:6px;">Search By Driver Mobile</div>
				 <input type="text" name="driverphone" id="driverphone" class="form-control input-sm" style="width:100%; border-color:balck;"  placeholder="Search Driver Mobile"/>  
			 </div>    
			 <div class="col-lg-3 col-md-3 col-sm-12"> 
			 <div style="margin-bottom:6px;">&nbsp; </div>
							<input type="button" name="search" id="search" onclick="searchDriver();" class="btn_nav1" style="width:30%;padding: 5px 10px;" value="Search">  
						</div>              
			 <div class="clearfix margin_10"></div>	                      									                                  
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Driver Name<span style="color: red;">*</span></div>
							<input type="text" name="driverName" id="driverName" class="form-control input-sm" style="width:100%;"  placeholder="Driver Name" required/>  
						<input type="hidden" name="driverId" id="driverId"/>  
						
						</div>       
						     <input type="hidden" name="gender" id="gender"  value="Male">
							                                  
						<!-- <div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Gender<span style="color: red;">*</span></div>
							<select name="gender" id="gender"   class="input-sm" style="width:100%;">
							<option value="">--Select Gender--</option>
							<option value="Male">Male</option>
							<option value="Female">Female</option>
							<option value="Transgender">Transgender</option>							
						</select>
						 </div>	 -->							
					 <div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Driver Phone Number<span style="color: red;">*</span> </div>
						  	<input type="text" name="driverContactNumbers" id="driverContactNumbers" maxlength="10"  onchange="fetchDriverMobiles(),isNumberKey(event,'driverContactNumber');" class="form-control input-sm" style="width:100%;background-color:white;" placeholder="Driver Phone Number (AS numeric value only)" disabled="disabled" required/> 
							<input type="hidden" name="driverContactNumber" id="driverContactNumber" value="">
							<input type="hidden" name="validNumber" id="validNumber" value="">   
						  </div>	 
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Driving Experience( As Year)<!-- <span style="color: red;">*</span --></div>
							 <select name="drivingExperience" id="drivingExperience" onchange="fillState()" class="input-sm" style="width:100%;">
                                 <option value="">--Select Year--</option>
                                 <% for(int i=1; i<101;i++){ 										
									%>									 
								 <option value="<%=i+" Year" %>"><%=i+" Year" %></option>
                                 <%	}%>                        
                             </select>
							   
						</div>				
						<!-- <div class="clearfix margin_05"></div> -->
						<div class="clearfix margin_10"></div>	
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Local Address<!-- <span style="color: red;">*</span> --></div>
							<textarea  name="localAddress" id="localAddress"  onFocus="geolocate()" class="form-control input-sm" style="width:100%;" placeholder="Local Address"></textarea>  
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Permanent Address<!-- <span style="color: red;">*</span> --></div>
							<textarea  name="permanentAddress" id=permanentAddress   onFocus="geolocate()" class="form-control input-sm" style="width:100%;" placeholder="Permanent Address"></textarea>  
						</div><%-- 
													
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Country<span style="color: red;">*</span></div>
							   <select name="country" id="country" onchange="fillState()" class="input-sm" style="width:100%;">
                                 <option value="">--Select Country--</option>
                                 <%List<Countries> countriesList = (List<Countries>)session.getAttribute("countriesList");
									if(countriesList != null && !countriesList.isEmpty()){
									for(int i=0; i<countriesList.size();i++){ 										
									%>	
								<%if(countriesList.get(i).getValue().equals("India")){ %>
									 <option value="<%=countriesList.get(i).getId() %>" selected="selected"> <%=countriesList.get(i).getValue() %></option>
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
							<div style="margin-bottom:6px;">State<span style="color: red;">*</span><!-- <b id="stateM" style="margin-bottom:6px;"></b> --></div>
							<select name="state" id="state" onchange="fillCity();" class="input-sm" style="width:100%;">
							<option value="">--Select state--</option>
							 </select> 
							 
						</div>
						
						 <div class="clearfix margin_10"></div>
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">City<span style="color: red;">*</span></div>
						<select name="city" id="city" onchange="fillClusterByCSC();"  class="input-sm" style="width:100%;">
							<option value="">--Select city--</option>
						</select>   
						 <b id="cityM" style="margin-bottom:6px;font-size:11px; "></b> 
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Hub<span style="color: red;">*</span></div>
						<select name="hubId" id="hubId" onchange="fillCluster()" class="input-sm" style="width:100%;">
							<option value="">--Select Hub--</option>
							 
							</select> 
							 <b id="hubM" style="margin-bottom:6px;font-size:11px; "></b>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Cluster<span style="color: red;">*</span></div>
						<select name="clusterId" id="clusterId" onchange="fillStand()" class="input-sm" style="width:100%;">
							<option value="">--Select Cluster--</option>
						</select> 
						 <b id="clusterM" style="margin-bottom:6px;font-size:11px; "></b>
						</div>
						
						
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Stand ( DC )<span style="color: red;">*</span></div>
						<select name="standDetails" id="standDetails" class="input-sm" style="width:100%;">
							<option value="">--Select Stand--</option>
						</select> 
					    <b id="standDetailsM" style="margin-bottom:6px;font-size:11px; "></b>
						</div>
						
						 <div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
					
						<div class="clearfix margin_10"></div>	
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Driver Association <span style="color: red;">*</span> </div>
						<select name="dstatus" id="dstatus"  class="input-sm" style="width:100%;" onchange="addshowHide();">
                                 <option value="">--Select Association--</option>
                                  <option value="Mapped">Leased</option>
                                  <option value="Free">On-Demand</option>  
                                  <option value="Open Market">Open Market</option>                       
                             </select>
						 
						</div>	 			 
						<div id="mapped" style="display:none;">  
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Associated By Client <span style="color: red;">*</span></div>
						<select name="assosiatedBy" id="assosiatedBy" onchange="fillSubOrg();"  class="input-sm" style="width:100%;">
                                 <option value="">--Select Client--</option>
                                 <% List<OrganizationMasterRegistration>  list= (List<OrganizationMasterRegistration>)session.getAttribute("orgList");
									if(list != null && !list.isEmpty()){
									for(int i=0; i<list.size();i++){ 
									%>	
								 <option value="<%=list.get(i).getIdClientMaster() %>"> <%=list.get(i).getName() %></option>
                                 <%	}}%>                        
                             </select>
                             <b id="assosiatedByM" style="margin-bottom:6px;font-size:11px; "></b>
						 
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;"> Client Subsidiary <span style="color: red;">*</span><input type="hidden" id="subOrgClientMe"></div>
						<select name="subOrgClient" id="subOrgClient"  class="input-sm" style="width:100%;">
                                 <option value="">--Select Client Subsidiary--</option>
                              </select>
                              <b id="subOrgClientM" style="margin-bottom:6px;font-size:11px; "></b>
					 	</div>
					 	<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Driver reporting time <span style="color: red;">*</span></div>
						<input type="text" name="driverReportingTime" id="driverReportingTime" onclick="pickTime();" class="form-control input-sm"  style="width:100%; color: black; background-color: white;" readonly="readonly">
                        </div>
						</div>
						 --%>
						 
						 
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Driver Photo<!-- <span style="color: red;">*</span> --></div>
							<input type="file" name="imagesOfDH" id="imagesOfDH"  accept=".jpg, .png, .jpeg, .gif, .bmp, .pdf, .tiff|images/*" class="form-control input-sm"  style="width:100%;" placeholder="Driver Photo"/>   
						</div> 
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Driver DL<!-- <span style="color: red;">*</span> --></div>
							<input type="file" name="imagesOfDL" id="imagesOfDL" accept=".jpg, .png, .jpeg, .gif, .bmp, .pdf, .tiff|images/*" class="form-control input-sm" style="width:100%;" placeholder="Driver DL"/>   
						</div>
						<!-- <div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Driver PAN Card<span style="color: red;">*</span></div>
							<input type="file" name="ioPanCard" id="ioPanCard" accept=".jpg, .png, .jpeg, .gif, .bmp, .pdf, .tiff|images/*" maxlength="12" onFocus="geolocate()" class="form-control input-sm" style="width:100%;" placeholder="Driver PAN Card"/>   
						</div> -->
						
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Driver Police Verification Report<!-- <span style="color: red;">*</span> --></div>
						 <input type="file" name="iOPVReports" id="iOPVReports" accept=".jpg, .png, .jpeg, .gif, .bmp, .pdf .tiff|images/*" class="form-control input-sm" style="width:100%;" placeholder="Driver Police Verification Report" />
						</div>			
						
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
						<div class="col-lg-3 col-md-3 col-sm-12">     
							<div style="margin-top:6px; font-size:14px; text-align:left; margin-left:0px;margin-top:8px;">
								<input type="submit" class="btn btn-danger btn-sm btn_nav1" value="Update Now"> 
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

<div id="dialog-confirm" style="display:none; border-color: black;border-width:1px;">
<fieldset class="fieldset2 col-lg-12 borderManager table-responsive" style="border-color: black;border-width:1px;">
		<legend class="borderManager">Upload Driver Documents</legend>
 <div class="clearfix margin_10"></div>	
<div class="clearfix margin_10"></div>	
<form action="/trux/reg/searchEditDrivers" method="post"	class="form-inline" onsubmit="return validateForm();" enctype="multipart/form-data">
			<div class="row" style="margin-top: -2%;">
				<div class="col-lg-3 col-md-3 col-sm-12">
					<div style="margin-bottom: 6px;">
						Organization Name<span style="color: red;">*</span>
					</div>

					<select name="orgName" id="orgName" onchange="driverToUpdateList();" class="input-sm" style="width: 100%;">
						<option value="">--Select Client--</option>
						<%
							List<OrganizationMasterRegistration> clientlist = (List<OrganizationMasterRegistration>) request.getAttribute("orgList");
							if (clientlist != null && !clientlist.isEmpty()) {
								for (int i = 0; i < clientlist.size(); i++) {
						%>
						<option value="<%=clientlist.get(i).getIdClientMaster()%>"><%=clientlist.get(i).getName()%></option>
						<%
							}
							}
						%>
					</select>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-12">
					<div style="margin-bottom: 6px;">
						Driver Name<span style="color: red;">*</span>
					</div>
					<select name="driverName" id="driver" class="input-sm"
						style="width: 100%;">
						<option value="">--Select Driver--</option>
					</select>
				</div>
				 <div class="clearfix margin_10"></div>	
			  <div class="clearfix margin_10"></div>	
				<div class="col-lg-3 col-md-3 col-sm-12">
					<div style="margin-bottom: 6px;">
						Driver Photo
						<!-- <span style="color: red;">*</span> -->
					</div>
					<input type="file" name="imagesOfDH" id="imagesOfDH"
						accept=".jpg, .png, .jpeg, .gif, .bmp, .pdf, .tiff|images/*"
						class="form-control input-sm" style="width: 100%;"
						placeholder="Driver Photo" />
				</div>
				<div class="col-lg-3 col-md-3 col-sm-12">
					<div style="margin-bottom: 6px;">
						Driver DL<span style="color: red;">*</span>
					</div>
					<input type="file" name="imagesOfDL" id="imagesOfDL"
						accept=".jpg, .png, .jpeg, .gif, .bmp, .pdf, .tiff|images/*"
						class="form-control input-sm" style="width: 100%;"
						placeholder="Driver DL" />
				</div>
				<div class="col-lg-3 col-md-3 col-sm-12">
					<div style="margin-bottom: 6px;">
						Driver PAN Card
						<!-- <span style="color: red;">*</span> -->
					</div>
					<input type="file" name="ioPanCard" id="ioPanCard"
						accept=".jpg, .png, .jpeg, .gif, .bmp, .pdf, .tiff|images/*"
						maxlength="12" onFocus="geolocate()" class="form-control input-sm"
						style="width: 100%;" placeholder="Driver PAN Card" />
				</div>

				<div class="col-lg-3 col-md-3 col-sm-12">
					<div style="margin-bottom: 6px;">
						Driver Police Verification Report
						<!-- <span style="color: red;">*</span> -->
					</div>
					<input type="file" name="iOPVReports" id="iOPVReports"
						accept=".jpg, .png, .jpeg, .gif, .bmp, .pdf .tiff|images/*"
						class="form-control input-sm" style="width: 100%;"
						placeholder="Driver Police Verification Report" />
				</div>
			</div>
		</form>
	</fieldset>

</div>

  </body>
</html>
 
	 