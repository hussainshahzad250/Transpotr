 <%@page import="com.trux.model.Countries"%>
<%@page import="com.trux.model.OrganizationMasterRegistration"%>
<%@page import="java.util.List"%>
<%@page import="com.trux.model.VehicleRegistration"%>
<%@page import="com.trux.model.OrderFilterReportsDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
* {

font-family: Arial, sans-serif;
font-size: 12px;
font-weight: normal;
}
#config {
overflow: auto;
margin-bottom: 10px;
}
.config {
float: left;
width: 200px;
height: 250px;
border: 1px solid #000;
margin-left: 10px;
}
.config .title {
font-weight: bold;
text-align: center;
}
.config .barcode2D,  #miscCanvas {
display: none;
}
#submit {
clear: both;
}
#barcodeTarget,  #canvasTarget {
margin-top: 20px;
}
</style>
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script> 
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script> 
 <style type="text/css">
 input[type="radio"] { -webkit-appearance: radio; }
tbody tr td:first-child, table#attandanceTable tbody tr td:last-child { padding: 3px 0px }
</style>

<!-- 
<script src="/trux/resources/core/jquery.1.10.2.min.js"></script> -->
<script src="/trux/resources/core/jquery.autocomplete.min.js"></script>
<link href="/trux/resources/core/main.css" rel="stylesheet">

 <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
 <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>

<title>Consumer Booking Vehicle</title>

<script src="/trux/resources/js/jquery-barcode.js"></script>
<script type="text/javascript">

$( document ).ready(function() {
    generateBarcode();
});
    
      function generateBarcode(){
      
        var value = $("#vehicleNumbers").val().toUpperCase();
       
        if(getParameterByName("vno") != "" && getParameterByName("vno") != null)
        {
        	$("#vehicleNumbers").val(getParameterByName("vno").toUpperCase());
        	value = getParameterByName("vno").toUpperCase();
        	}
        
        var btype = $("input[name=btype]:checked").val();
        var renderer = $("input[name=renderer]:checked").val();
        
		var quietZone = false;
        if ($("#quietzone").is(':checked') || $("#quietzone").attr('checked')){
          quietZone = true;
        }
		
        var settings = {
          output:renderer,
          bgColor: $("#bgColor").val(),
          color: $("#color").val(),
          barWidth: $("#barWidth").val(),
          barHeight: $("#barHeight").val(),
          moduleSize: $("#moduleSize").val(),
          posX: $("#posX").val(),
          posY: $("#posY").val(),
          addQuietZone: $("#quietZoneSize").val()
        };
        if ($("#rectangular").is(':checked') || $("#rectangular").attr('checked')){
          value = {code:value, rect: true};
        }
        if (renderer == 'canvas'){
          clearCanvas();
          $("#barcodeTarget").hide();
          $("#canvasTarget").show().barcode(value, btype, settings);
        } else {
          $("#canvasTarget").hide();
          $("#barcodeTarget").html("").show().barcode(value, btype, settings);
        }
      }
          
      function showConfig1D(){
        $('.config .barcode1D').show();
        $('.config .barcode2D').hide();
      }
      
      function showConfig2D(){
        $('.config .barcode1D').hide();
        $('.config .barcode2D').show();
      }
      
      function clearCanvas(){
        var canvas = $('#canvasTarget').get(0);
        var ctx = canvas.getContext('2d');
        ctx.lineWidth = 1;
        ctx.lineCap = 'butt';
        ctx.fillStyle = '#FFFFFF';
        ctx.strokeStyle  = '#000000';
        ctx.clearRect (0, 0, canvas.width, canvas.height);
        ctx.strokeRect (0, 0, canvas.width, canvas.height);
      }
      
      $(function(){
        $('input[name=btype]').click(function(){
          if ($(this).attr('id') == 'datamatrix') showConfig2D(); else showConfig1D();
        });
        $('input[name=renderer]').click(function(){
          if ($(this).attr('id') == 'canvas') $('#miscCanvas').show(); else $('#miscCanvas').hide();
        });
        generateBarcode();
      });
      
      function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
  
    </script>
 <script type="text/javascript">
 
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
		/*          
		 $(function() {
		  
			 $('#vehicleNumber').keyup(function(){
				    this.value = this.value.toUpperCase();
				});
			 
			 $('#vehicleNumbers').keyup(function(){
				    this.value = this.value.toUpperCase();
				});
			 });
		 */

		 
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
 
 
 function fetchDriversMobiles()
 {        
 var mobile=document.getElementById("driverMobile").value;
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
 function validateForm(){
 	 var flag;
 	 var ownerName=document.getElementById("ownerName").value;
 	 var ownerAddress=document.getElementById("ownerAddress").value;
 	 var ownerPhoneNumber=document.getElementById("ownerPhoneNumber").value;
 	 var vehicleNumber=document.getElementById("vehicleNumber").value;
 	var vehicleModel=document.getElementById("vehicleModel").value;
    var month=document.getElementById("month").value;
    var year=document.getElementById("year").value;
    
     
 	 var vehicleStatus=document.getElementById("vehicleStatus").value;
 	 var vehicleType=document.getElementById("vehicleType").value; 
 	 var kmReading=document.getElementById("kmReading").value; /* 
 	 var ioRC=document.getElementById("ioRC").files.length>0;
 	 var ioPermitVehicle=document.getElementById("ioPermitVehicle").files.length>0; */
 	 var fuelType=document.getElementById("fuelType").value;
 	 var driverMobile=document.getElementById("driverMobile").value; 
     var validNumber=document.getElementById("validNumber").value;
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
   
 /* if(ownerPhoneNumber!=""){
 if((ownerPhoneNumber.length != 10 || ownerPhoneNumber.length != 11)) {
  	document.getElementById('message').style.display = "block";
 	document.getElementById('message').style.color="red"; 	
 	message+="Phone Number Should be 11 digit !<br/> ";
 	document.getElementById("ownerPhoneNumber").style.borderColor="green";
 	document.getElementById('ownerPhoneNumber').focus(); 
 	flag= false; 
 	}else{
 	document.getElementById("ownerPhoneNumber").style.borderColor="green";
 	} 
 	} */
   if(vehicleNumber==""){
 	document.getElementById("message").style.color="red";
 	document.getElementById('message').style.display = "block";
 	document.getElementById("vehicleNumber").style.borderColor="red"; 	
 	message+="Please enter Vehicle Number !<br/>";
 	document.getElementById('vehicleNumber').focus();
 	flag= false;
 	}else{
 	document.getElementById("vehicleNumber").style.borderColor="green";
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
 
 /* if(driverMobile!=""){
	if((driverMobile.length != 10 || driverMobile.length != 11)) {
    document.getElementById('message').style.display = "block";
	document.getElementById('message').style.color="red"; 	
	message+="Phone Number Should be 11 digit !<br/> ";
	document.getElementById("driverMobile").style.borderColor="green";
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
        	message+="Please enter register driver phone number. Enter phone is not exist !<br/>";
        	document.getElementById("driverMobile").style.borderColor="red";
        	document.getElementById('driverMobile').focus();
        	flag= false; 
    	}
	document.getElementById("driverMobile").style.borderColor="green"; 
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
		 	document.getElementById('vehicleModel').value=month+" "+year;
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
	  var driverMobiles = isNaN(driverMobile.trim());
	  
	  if(driverMobiles==true){
		  document.getElementById("message").style.color="red";
		 document.getElementById('message').style.display = "block";
		document.getElementById("driverMobile").style.borderColor="green";		
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
       /** @type {HTMLInputElement} */(document.getElementById('ownerAddress')),
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
 
 <script type="text/javascript">
 function searchVehicle(){
	 var vehicleNumbers=$("#vehicleNumbers").val();
	 $.ajax({
	      type: "GET",
	      url: "/trux/reg/validateVehicleNumber",
	      data:{
	    	  vehicleNumber:vehicleNumbers
			  } ,
	        success: function(data) {
	    	console.log(data);
	    	
	    	$("#vehicleId").val(data.id);
	    	$("#driverId").val(data.driverId); 
	  		$("#kmReading").val(data.kmReading);
	  		$("#ownerPhoneNumber").val(data.ownerPhoneNumber);
	  		$("#vehicleNumber").val(data.vehicleNumber);
	  		$("#ownerAddress").val(data.ownerAddress);
	  		$("#ownerName").val(data.ownerName);
	  		$("#vehicleModel").val(data.vehicleModel);
	  	 	$("#vehicleStatus").val(data.vehicleStatus); 
	  		$("#vehicleType").val(data.vehicleType); 
	  		$("#fuelType").val(data.fuelType); 	
	  		$("#driverMobile").val(data.driverMobile); 	
	  		$("#month").val(data.month); 	
	  		$("#year").val(data.year); 	
	  		$("#vehicleBody").val(data.vehicleBody); 	
	  		$("#accountHolderName").val(data.accountHolderName); 
	  		$("#panNumber").val(data.panNumber); 
	  		$("#accountNumber").val(data.accountNumber); 
	  		$("#ifscCode").val(data.ifscCode); 
	  		$("#bankName").val(data.bankName);
	  		if(data.insuranceExpiryDate != null){
	  		$("#insuranceExpiryDate").val(showDateDetails(data.insuranceExpiryDate));
	  		}
	  		var isbarcodestatus=data.isBarcodeIssued;
	  		if(isbarcodestatus==1){
	  		 	$("input[name=isBarcodeIssued][value=" + isbarcodestatus + "]").attr('checked', 'checked');
	  		 }
	  		if(isbarcodestatus==0){
	  			$("input[name=isBarcodeIssued][value=" + isbarcodestatus + "]").attr('checked', 'checked');
		   }
	  	
	  		var x = $("#vehicleStatus").val();
	  		if(x=="Mapped"){
	  			$('#mapped').show();
	  		}else{  
	  			$('#mapped').hide();
	  		}
	  		$("#state").val(data.stateId); 	 
	  		$("#cityM").html("( "+data.city+" )");
	  		$("#hubM").html("( "+data.hubName+" )");
	  		$("#clusterM").html("( "+data.cluster+" )");
	  		$("#assosiatedByM").html("( "+data.assosiatedBy+" )"); 
	  	    $("#standDetailsM").html("( "+data.standDetails+" )"); 
	  	    $("#subOrgClientM").html("( "+data.subOrgClient+" )"); 
	  	  var stateId=$("#state").val();
		  if(stateId!=""){
				 fillCity();
			 } 
	      } 
	    });
	 fetchDriversMobiles();
	
	 
	 
	 return true;
	 
	}
	 
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
	  	
		$('#vehicleModel').val(month+","+year);
 }
 
 
 function addshowHide(){
		
		var x = $("#vehicleStatus").val();
		if(x=="Open Market"){
			$('#mapped').hide();
		}else{  
			$('#mapped').show();
		}
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
	</script>
	
	<style>
	.black_overlay{
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index:1001;
	-moz-opacity: 0.8;
	opacity:.80;
	filter: alpha(opacity=80);
}

.white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 25%;
	width: 50%;
	height: auto;
	padding: 16px;
	border: 16px solid orange;
	background-color: white;
	z-index:1002;
	overflow: auto;
}
	</style> 
</head>
 <body onload="initialize(); ">
 <div class="container">
  
        <div id="light" class="white_content">
<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'" style="color:#000;float:right; font-size14px"><img src="/trux/resources/images/cross.png" style=" width:20px" ></a> 
        
<div id="generator"> <input type="text" id="barcodeValue" value="">
<div id="config" style="display:none;">
<div class="config">
<div class="title">Type</div>
<input type="radio" name="btype" id="ean8" value="ean8">
<label for="ean8">EAN 8</label>
<br />
<input type="radio" name="btype" id="ean13" value="ean13">
<label for="ean13">EAN 13</label>
<br />
<input type="radio" name="btype" id="upc" value="upc">
<label for="upc">UPC</label>
<br />
<input type="radio" name="btype" id="std25" value="std25">
<label for="std25">standard 2 of 5 (industrial)</label>
<br />
<input type="radio" name="btype" id="int25" value="int25">
<label for="int25">interleaved 2 of 5</label>
<br />
<input type="radio" name="btype" id="code11" value="code11">
<label for="code11">code 11</label>
<br />
<input type="radio" name="btype" id="code39" value="code39" checked="checked">
<label for="code39">code 39</label>
<br />
<input type="radio" name="btype" id="code93" value="code93">
<label for="code93">code 93</label>
<br />
<input type="radio" name="btype" id="code128" value="code128">
<label for="code128">code 128</label>
<br />
<input type="radio" name="btype" id="codabar" value="codabar">
<label for="codabar">codabar</label>
<br />
<input type="radio" name="btype" id="msi" value="msi">
<label for="msi">MSI</label>
<br />
<input type="radio" name="btype" id="datamatrix" value="datamatrix">
<label for="datamatrix">Data Matrix</label>
<br />
<br />
</div>
<div class="config">
<div class="title">Misc</div>
Background :
<input type="text" id="bgColor" value="#FFFFFF" size="7">
<br />
"1" Bars :
<input type="text" id="color" value="#000000" size="7">
<br />
<div class="barcode1D"> bar width:
<input type="text" id="barWidth" value="3" size="3">
<br />
bar height:
<input type="text" id="barHeight" value="75" size="3">
<br />
</div>
<div class="barcode2D"> Module Size:
<input type="text" id="moduleSize" value="5" size="3">
<br />
Quiet Zone Modules:
<input type="text" id="quietZoneSize" value="1" size="3">
<br />
Form:
<input type="checkbox" name="rectangular" id="rectangular">
<label for="rectangular">Rectangular</label>
<br />
</div>
<div id="miscCanvas"> x :
<input type="text" id="posX" value="10" size="3">
<br />
y :
<input type="text" id="posY" value="20" size="3">
<br />
</div>
</div>
<div class="config">
<div class="title">Format</div>
<input type="radio" id="css" name="renderer" value="css" checked="checked">
<label for="css">CSS</label>
<br />
<input type="radio" id="bmp" name="renderer" value="bmp">
<label for="bmp">BMP (not usable in IE)</label>
<br />
<input type="radio" id="svg" name="renderer" value="svg">
<label for="svg">SVG (not usable in IE)</label>
<br />
<input type="radio" id="canvas" name="renderer" value="canvas">
<label for="canvas">Canvas (not usable in IE)</label>
<br />
</div>
</div>

</div>
<div id="barcodeTarget" class="barcodeTarget"></div>
<canvas id="canvasTarget" width="150" height="150"></canvas>

		 </div>
        <div id="fade" class="black_overlay"></div>
		
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager table-responsive"> 
		<legend class="borderManager" style="width: 100%;">Update Vehicle Registration  <% VehicleRegistration saveVehicaleBack=(VehicleRegistration) request.getAttribute("saveVehicaleBack"); if(saveVehicaleBack!=null){%> <b style="color: green;">Updated ID :<%=saveVehicaleBack.getId() %></b><% }else if( saveVehicaleBack!=null && saveVehicaleBack.getId()==null) {%> <b style="color: red;">Vehicle Not Register</b><%} %>
    	 <a href="/trux/reg/registerVehicle" class="btn btn-danger btn-sm right-btn btn_nav1 right_btn_update" style="color: black;padding-right: 20px;padding-top: -2px;/* float: right; */"><b>Add New Vehicle</b></a> &nbsp; 
	</legend>
		 
			<form  action="/trux/reg/updateVehicleRegistrations" method="post" class="form-inline" onsubmit="return validateForm();"  enctype="multipart/form-data">
			<div class="clearfix margin_10"></div>	  
		<div class="row" style="margin-top:-2%;margin-top:-2%;box-shadow: 1px 1px 7px #6A6A6A;padding: 10px;border-radius: 6px; "> 
			<div class="col-lg-4 col-md-4 col-sm-12">
			<div style="margin-bottom:6px;">Search By Vehicle Number OR Driver Mobile</div>
		 <input type="text" name="vehicleNumbers" id="vehicleNumbers" class="form-control input-sm" style="width:100%; border-color:balck;"  placeholder="Search Vehicle Number Or Driver Mobile" required/>  
		 
			 </div>    
			 <div class="col-lg-4 col-md-4 col-sm-12"> 
			 <div style="margin-bottom:6px;">&nbsp; </div>
							<input type="button" name="search" id="search" onclick="searchVehicle();" class="btn btn-danger btn-sm btn_nav1" style="width:30%;" value="Search">  
							<a href = "javascript:void(0)" onclick = "show_barcode_box()" style="background: #24BFE6;padding: 10px;width: 40%;border-radius: 4px;font-size: 12px;text-align: center;">Generate Barcode</a> 

						</div>
					</div>	</br>
						
<script>
function show_barcode_box(){
if(document.getElementById('vehicleNumbers').value.trim()==""){
	alert("No Vehicle Number Found!!");
	return false;
}	
document.getElementById('light').style.display='block';
document.getElementById('fade').style.display='block'; 

generateBarcode(); 
}
</script>						
						<div class="clearfix margin_10"></div>
						<span style="float: left;margin-bottom: 5px; font-weight: bold; font-size: 16px; color: #525353;">Driver</span><div class="clearfix margin_10"></div>
			<div class="row" style="margin-top:-2%;box-shadow: 1px 1px 7px #6A6A6A;padding: 10px;border-radius: 6px; ">  
					
					<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Driver Mobile<span style="color: red;">*</span></div>
						<input type="text" name="driverMobile" id="driverMobile" maxlength="12"  onkeyup="fetchDriversMobiles();" onkeypress="validateDecimal(driverMobile);" class="form-control input-sm" style="width:100%;" placeholder="Driver Mobile (AS numeric value only)" required/>
						<input type="hidden" name="validNumber" id="validNumber" value=""> 
						</div></br>
						
						 
					</div>	</br>
					<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
					<span style="float: left;margin-bottom: 5px; font-weight: bold; font-size: 16px; color: #525353;">Vehicle</span>
						<div class="clearfix margin_5"></div>
						
						<div class="clearfix margin_10"></div>
						<div class="row" style="margin-top:-2%;box-shadow: 1px 1px 7px #6A6A6A;padding: 10px;border-radius: 6px; "> 
							<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Vehicle Number<span style="color: red;">*</span></div>
							<input type="text" name="vehicleNumber" id="vehicleNumber" class="form-control input-sm" style="width:100%; background-color:white;" placeholder="Vehicle Number">   
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12">
						<div style="margin-bottom:6px;">Vehicle type<span style="color: red;">*</span></div>
							<select name="vehicleType" id="vehicleType" class="input-sm" style="width:100%;" required="">
							<option value="">--Select Vehicle type</option>
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
							<select name="vehicleBody" id="vehicleBody" class="input-sm" style="width:100%;" required="">
							<option value="">--Select Vehicle type</option>
							<option value="Containerized">Containerized</option>
							<option value="Open body">Open body</option>
							</select> 
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">KM. Reading<span style="color: red;">*</span></div>
						<input type="text" name="kmReading" id="kmReading" onkeypress="validateDecimal(kmReading);" class="form-control input-sm" style="width:100%;" placeholder="KM. Reading" required="">
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px; margin-top: 10px;">Vehicle Fuel type<span style="color: red;">*</span></div>
							<select name="fuelType" id="fuelType" class="input-sm" style="width:100%;" required="">
							<option value="">--Select Vehicle Fuel type</option>
							<option value="Petrol">Petrol</option>
							<option value="CNG">CNG</option>
							<option value="Diesel">Diesel </option>
							</select>
						</div>
						<%List<Integer> yearList= (List<Integer>)request.getAttribute("yearList");
						List<String> monthList= (List<String>)request.getAttribute("monthList");%>
						<div class="col-lg-3 col-md-3 col-sm-12">
										<div style="margin-bottom: 6px; margin-top: 10px;">Vehicle Model Year<span style="color: red;">*</span>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12" style="border: 1px solid #999; border-radius: 4px; 0 px 12px 0 0; padding: 0;" id="model">
                                             	<select name="month" id="month" onchange="colletMonthYear();" class="col-lg-6 col-md-3 col-sm-12" style="height: 30px; border: none;border-radius:4px; padding: 1px;" required="">
												<option value="">--Month--</option>
												
												 <option value="Jan">Jan</option>
												 
												 <option value="Feb">Feb</option>
												 
												 <option value="Mar">Mar</option>
												 
												 <option value="Apr">Apr</option>
												 
												 <option value="May">May</option>
												 
												 <option value="June">June</option>
												 
												 <option value="July">July</option>
												 
												 <option value="Aug">Aug</option>
												 
												 <option value="Sept">Sept</option>
												 
												 <option value="Oct">Oct</option>
												 
												 <option value="Nov">Nov</option>
												 
												 <option value="Dec">Dec</option>
												 
											    </select>  
											    <select name="year" id="year" onchange="colletMonthYear();" class="col-lg-6 col-md-3 col-sm-12" style="height: 30px; border: none;border-radius:4px; padding: 1px;" required="">
												<option value="">--Year</option>
												
												 <option value="2016">2016</option>
												 
												 <option value="2015">2015</option>
												 
												 <option value="2014">2014</option>
												 
												 <option value="2013">2013</option>
												 
												 <option value="2012">2012</option>
												 
												 <option value="2011">2011</option>
												 
												 <option value="2010">2010</option>
												 
												 <option value="2009">2009</option>
												 
												 <option value="2008">2008</option>
												 
												 <option value="2007">2007</option>
												 
												 <option value="2006">2006</option>
												 
												 <option value="2005">2005</option>
												 
											    </select>    
										</div>
									</div><!-- isBarcodeIssueYes isBarcodeIssuedNo   -->
									
									<div class="col-lg-3 col-md-3 col-sm-12">
										<div style="margin-bottom: 6px;margin-top: 10px;">Vehicle Insurance Expiry
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12"
											style="border: 1px solid #999; border-radius: 4px; 0 px 12px 0 0; padding: 0;" id="model">
											<input type="text" name="insuranceExpiryDate" id="insuranceExpiryDate" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #AEAEAE;border: 1px solid #AEAEAE; background:url(../resources/images/calender2.png);background-repeat: no-repeat; background-position:right;  ' placeholder="Select Insurance Expiry Date">    
										</div>
									</div>
									
									
							<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom: -7px; margin-top: 27px;">Barcode Issue Status</div>
							<div class="col-lg-12 col-md-12 col-sm-12" style="border: 0px solid #999; border-radius: 4px; 0 px 12px 0 0; padding: 0; margin-bottom: 6px; " id="model">
                             <input type="radio" name="isBarcodeIssued" id="isBarcodeIssueYes" value="1" class="form-control input-sm" style="width: 5%;"/>Yes  
						     <input type="radio" name="isBarcodeIssued" id="isBarcodeIssuedNo" value="0" class="form-control input-sm" style="width: 5%;" checked="checked"/> No 
						  </div>
						    </div>
						   
						     <input type="hidden" name="vehicleId" id="vehicleId" value="">
							<input type="hidden" name="vehicleModel" id="vehicleModel"  value="" />
						  </div></br>
						  <div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
					<span style="float: left;margin-bottom: 5px; font-weight: bold; font-size: 16px; color: #525353;">Owner</span>
						<div class="clearfix margin_5"></div>
						
						<div class="clearfix margin_10"></div>
					<div class="row" style="margin-top:-2%;box-shadow: 1px 1px 7px #6A6A6A;padding: 10px;border-radius: 6px; "> 
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Owner Name<span style="color: red;">*</span></div>
							<input type="text" name="ownerName" id="ownerName" class="form-control input-sm" style="width:100%;"  placeholder="Owner Name" required/>  
						<input type="hidden" name="vehicleId" id="vehicleId" value="">
						</div>  
						                                             
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Owner Addresse<span style="color: red;">*</span></div>
							<input type="text" name="ownerAddress" id="ownerAddress"   onFocus="geolocate()" class="form-control input-sm" style="width:100%;" placeholder="Owner Addresse" required/>   
						</div>	
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Owner Phone Number<span style="color: red;">*</span></div>
							<input type="text" name="ownerPhoneNumber" id="ownerPhoneNumber" maxlength="12"  onkeypress="validateDecimal(ownerPhoneNumber);" onchange="isNumberKey(event,'ownerPhoneNumber');" class="form-control input-sm" style="width:100%;" placeholder="Owner Phone Number (AS numeric value only)" required/>   
						</div>		<div class="col-lg-3 col-md-3 col-sm-12">    
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
							<!-- <input type="text" name="bankName" id="bankName" maxlength="255"  class="form-control input-sm" style="width:100%;" placeholder="Bank Name" /> -->   
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
							<input type="text" name="accountNumber" id="accountNumber"  onkeypress="validateDecimal(accountNumber);" maxlength="13"    class="form-control input-sm" style="width:100%;" placeholder="Account Number" />   
						</div>	
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">IFSC Code <!-- <span style="color: red;">*</span> --></div>
							<input type="text" name="ifscCode" id="ifscCode" maxlength="11"  class="form-control input-sm" style="width:100%;" placeholder="IFSC Code"  />   
						</div>
					</div></br>				
						 <div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
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
									fillCity();
    						}); 
                             
                             $('#insuranceExpiryDate').datetimepicker({
								    timepicker:false,
								    format:'Y/m/d'
									 });
    				        </script>
						 </div>
						  
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">State<span style="color: red;">*</span></div>
							<select name="state" id="state" onchange="fillCity();" class="input-sm" style="width:100%;" required>
							<option value="">--Select state--</option>
							 </select> 
							 <b id="stateM" style="margin-bottom:6px;"></b>
						</div>
						 
						
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
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;margin-top: 10px;">Cluster<span style="color: red;">*</span></div>
						<select name="clusterId" id="clusterId" onchange="fillStand()" class="input-sm" style="width:100%;">
							<option value="">--Select Cluster--</option>
						</select> 
						<b id="clusterM" style="margin-bottom:6px;font-size:11px; "></b>
						</div>
						
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;margin-top: 10px;">Stand ( DC )<span style="color: red;">*</span></div>
						<select name="standDetails" id="standDetails" class="input-sm" style="width:100%;">
							<option value="">--Select Stand--</option>
						</select> 
						<b id="standDetailsM" style="margin-bottom:6px;font-size:11px; "></b>
						</div>
					   <div class="clearfix margin_05"></div> 
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
						 <b id="assosiatedByM" style="margin-bottom:6px;font-size:11px; "></b>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;margin-top: 10px;"> Client Subsidiary <span style="color: red;">*</span></div>
						<select name="subOrgClient" id="subOrgClient"  class="input-sm" style="width:100%;">
                                 <option value="">--Select Client Subsidiary--</option>
                              </select>
                              <b id="subOrgClientM" style="margin-bottom:6px;font-size:11px; "></b>
					 	</div>
						</div>
					</div></br>
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
						<div class="col-lg-3 col-md-3 col-sm-12">     
							<div style="margin-top:6px; font-size:14px; text-align:left; margin-left:0px;margin-top:8px;">
								<input type="submit" class="btn btn-danger btn-sm btn_nav1" value="Update Now"> <input type="reset" class="btn btn-danger btn-sm btn_nav1" value="clear"> 
						                             
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
 
	 
