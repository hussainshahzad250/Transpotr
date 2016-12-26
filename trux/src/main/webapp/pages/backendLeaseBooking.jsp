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
tbody tr td:first-child, table#attandanceTable tbody tr td:last-child { padding: 3px 0px }
</style>
 
<script src="/trux/resources/core/jquery.autocomplete.min.js"></script>
<link href="/trux/resources/core/main.css" rel="stylesheet">

 <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
 <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>

<title>Booking Ride By Organization</title>

 <script type="text/javascript">
 
 

 //
 
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
 	 var flag=false;  
 	 var innerflag=false;  
 	 var orgName=document.getElementById("orgName").value; 
 	 var driver=document.getElementById("driver").value; 
 	var tbl = document.getElementById('attandanceTable');
	var lastRow = tbl.rows.length;
	var iteration = lastRow; 
 var message="";
 if(orgName==""){
 	 	  document.getElementById('message').style.display = "block";
 	 	  document.getElementById('message').style.color="red";
 	 	  message+="<br/>";
 	 	  message+="Please select the Organization name  !<br/>";
 	 	  document.getElementById("orgName").style.borderColor="red";
 	 	  document.getElementById('orgName').focus();
 	 	  flag= false;
 	 	  }else{
 	 	  document.getElementById("orgName").style.borderColor="green"; 
 	 	  }
 	 	 if(driver==""){
 	 	   document.getElementById('message').style.display = "block";
 	 	   document.getElementById('message').style.color="red";
 	 	   
 	 	   message+="Please select the Driver Name !<br/>";
 	 	   document.getElementById("driver").style.borderColor="red";
 	 	   document.getElementById('driver').focus();
 	 	   flag= false; 		 
 	 	   }else{
 			 document.getElementById("driver").style.borderColor="green"; 
 		   }
 	 	if(message==""){flag=true;}
 	 	 if(innerflag!=false){ 	 
 	 		flag=true;
 	 	 	document.getElementById("save").disabled = true; 		
 	 	 }else{
 	 		flag=false;
 	 	 }
 	 	var idName = new Array("","attandanceDate","checkin", "checkout","openingkm","closingkm","tolltax","noofbox");
		
 	for ( var i = 0; i < iteration; i++) {		 
		for ( var id = 1; id < idName.length; id++) {
			var attandanceInner = "";
			var checkinInner = "";
			var checkOutInner = "";
			var openingInner = "";
			var closingInner = "";
			var tolltaxInner = "";
			var noofboxInner = "";
			attandanceInner = document.getElementById(idName[id] + i).value;
			if(attandanceInner==""){ 
				 	  document.getElementById('message').style.display = "block";
				 	  document.getElementById('message').style.color="red";
				 	  message+="<br/>";
				 	  message+="Please Enter the Attandance Date !<br/>";
				 	 document.getElementById(idName[id] + i).style.borderColor="red";
				 	 
				 	 flag= false;
				 	 innerflag=false;
				 	  }else{
				 	 document.getElementById(idName[id] + i).style.borderColor="green"; 
			}
			
			if(id=2){
			checkinInner = document.getElementById(idName[id] + i).value; 
			if(checkinInner==""){ 
			 	  document.getElementById('message').style.display = "block";
			 	  document.getElementById('message').style.color="red";
			 	  message+="<br/>";
			 	  message+="Please Enter the CheckIn time  !<br/>";
			 	 document.getElementById(idName[id] + i).style.borderColor="red";
			 	// document.getElementById(idName[id] + i).focus();
			 	flag= false;
			 	 innerflag=false;
			 	  }else{
			 	 document.getElementById(idName[id] + i).style.borderColor="green"; 
			}
			if(checkinInner!=""){
			 var checkinTime=true;
			 var parts = checkinInner.split(':');
			 if (parts[0] > 23 || parts[1] > 59 || parts[2] > 59) {
				 checkinTime= false;
			 } 
			if(checkinTime==false){ 
			 	  document.getElementById('message').style.display = "block";
			 	  document.getElementById('message').style.color="red";
			 	  message+="<br/>";
			 	  message+="Please Enter the valide CheckIn time.Enter checkin time is invalide  !<br/>";
			 	 document.getElementById(idName[id] + i).style.borderColor="red";
			   	flag= false;
			 	 innerflag=false;
			 	  }else{
			 	 document.getElementById(idName[id] + i).style.borderColor="green"; 
			}
			}
			}
			if(id=3){
			checkOutInner = document.getElementById(idName[id] + i).value;
			 
			if(checkOutInner==""){ 
			 	  document.getElementById('message').style.display = "block";
			 	  document.getElementById('message').style.color="red";
			 	  message+="<br/>";
			 	  message+="Please Enter the CheckOut time  !<br/>";
			 	 document.getElementById(idName[id] + i).style.borderColor="red";
			 	   flag= false;
			 	  innerflag=false;
			 	  }else{
			 	 document.getElementById(idName[id] + i).style.borderColor="green"; 
			}
			if(checkOutInner!=""){
			 var checkoutTime=true;
			 var parts = checkOutInner.split(':');
			 if (parts[0] > 23 || parts[1] > 59 || parts[2] > 59) {
			    checkoutTime= false;
			 } 
			if(checkoutTime==false){ 
			 	  document.getElementById('message').style.display = "block";
			 	  document.getElementById('message').style.color="red";
			 	  message+="<br/>";
			 	  message+="Please Enter the valide  CheckOut time.Enter value is Invalid time format !<br/>";
			 	 document.getElementById(idName[id] + i).style.borderColor="red";
			 	   flag= false;
			 	  innerflag=false;
			 	  }else{
			 	 document.getElementById(idName[id] + i).style.borderColor="green"; 
			}
			}
			}
			
			if(id=4){
				openingInner = document.getElementById(idName[id] + i).value;
				if(openingInner==""){ 
				 	  document.getElementById('message').style.display = "block";
				 	  document.getElementById('message').style.color="red";
				 	  message+="<br/>";
				 	  message+="Please Enter the opening kilometer   !<br/>";
				 	 document.getElementById(idName[id] + i).style.borderColor="red";
				 	  flag= false;
				 	  innerflag=false;
				 	  }else{
				 	 document.getElementById(idName[id] + i).style.borderColor="green"; 
				}
				}
			
			if(id=5){
				closingInner = document.getElementById(idName[id] + i).value;
				if(closingInner==""){ 
				 	  document.getElementById('message').style.display = "block";
				 	  document.getElementById('message').style.color="red";
				 	  message+="<br/>";
				 	  message+="Please Enter the closing kilometer   !<br/>";
				 	 document.getElementById(idName[id] + i).style.borderColor="red";
				 	  flag= false;
				 	  innerflag=false;
				 	  }else{
				 	 document.getElementById(idName[id] + i).style.borderColor="green"; 
				}
				}
			if(id=6){
				tolltaxInner = document.getElementById(idName[id] + i).value;
				if(tolltaxInner==""){ 
				 	  document.getElementById('message').style.display = "block";
				 	  document.getElementById('message').style.color="red";
				 	  message+="<br/>";
				 	  message+="Please Enter the toll tax amount   !<br/>";
				 	 document.getElementById(idName[id] + i).style.borderColor="red";
				 	  flag= false;
				 	  innerflag=false;
				 	  }else{
				 	 document.getElementById(idName[id] + i).style.borderColor="green"; 
				}
				}
			if(id=7){
				noofboxInner = document.getElementById(idName[id] + i).value;
				if(noofboxInner==""){ 
				 	  document.getElementById('message').style.display = "block";
				 	  document.getElementById('message').style.color="red";
				 	  message+="<br/>";
				 	  message+="Please Enter the No Of Box at most total no. of count or zero    !<br/>";
				 	 document.getElementById(idName[id] + i).style.borderColor="red";
				 	  flag= false;
				 	  innerflag=false;
				 	  }else{
				 	 document.getElementById(idName[id] + i).style.borderColor="green"; 
				}
				}
		}		  
	} 
 	if(message==""){  flag= true;
	  innerflag=true;}
  	document.getElementById('message').innerHTML=message;
 	if(flag==true && innerflag==true){
 	    var toAddress= collectToAttandanceValue();
 	 	}
 	return flag;
  }
   
//------------------------------------------//

function addExpancesRow(){
 var i= $('#expanceTable').children().length;
 i; 
    var data="<tr><td>";
    data +="<div class='col-lg-12 col-md-12 col-sm-12'> <div style='margin-bottom:6px;'>Trip Expances<span style='color: red;'>*</span></div> <div class='col-lg-12 col-md-12 col-sm-12' style='border:1px solid #999; border-radius:4px;0px 12px 0 0'>  <select name='tolltax"+i+"' id='tolltax"+i+"'  class='col-lg-6 col-md-6 col-sm-6' style='height:30px;border:none; padding:0px'><option value=''>--Select Trip Expences--</option>							<option value='Toll Tax'>Toll Tax</option><option value='Bilty'>Bilty</option><option value='Parking'>Parking</option><option value='Chalan'>Chalan</option><option value='Other'>Other</option></select>  <input type='text' name='amount"+i+"' id='amount"+i+"' onkeyup='\"isNumberKey(event,'amount"+i+"');\" class='form-control input-sm col-lg-6 col-md-6 col-sm-6' maxlength='10'  style='width:50%;padding:0px; border:none;' placeholder='Expences Amount'></div>";
 
     $('#expsTable').append(data);
   // datePickeInitialize();
}


function addTripRow(){
	 var ii= $('#tripTable').children().length-1;
	 ii++; 
	    var data="<tr><td>";
	    data +="<div style='margin-bottom:6px;'>Trip Stops<span style='color: red;'>*</span></div><input type='text' name='tripAddress"+ii+"' id='tripAddress"+ii+"' class='form-control input-sm' maxlength='10'  style='width:100%;border: 1px solid#A2A2A2;' placeholder='Trip Stops'>";
	    $('#tableTrip').append(data);
	   if(ii==1){
	    autocomplete = new google.maps.places.Autocomplete(
	    		(document.getElementById('tripAddress'+ii)),
	    		{ types: ['geocode'] });   
	    		google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    		fillInAddress();
	    		});
	   }
	    if(ii==1){
	    				autocompleteTo0= new google.maps.places.Autocomplete(
	    				(document.getElementById('tripAddress'+ii)),
	    				{ types: ['geocode'] }); 
	    				google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    				fillInAddress();
	    				});
}if(ii==2){
	    				autocompleteTo1 = new google.maps.places.Autocomplete(
	    				(document.getElementById('tripAddress'+ii)),
	    				{ types: ['geocode'] }); 
	    				google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    				fillInAddress();
	    				}); 
}if(ii==3){
	    				autocompleteTo2= new google.maps.places.Autocomplete(
	    				(document.getElementById('tripAddress'+ii)),
	    				{ types: ['geocode'] }); 
	    				google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    				fillInAddress();
	    				});
}if(ii==4){	    
	    				autocompleteTo3 = new google.maps.places.Autocomplete(
	    				(document.getElementById('tripAddress'+ii)),
	    				{ types: ['geocode'] }); 
	    				google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    				fillInAddress();
	    				}); 
}if(ii==5){
	    				autocompleteTo4 = new google.maps.places.Autocomplete(
	    				(document.getElementById('tripAddress'+ii)),
	    				{ types: ['geocode'] }); 
	    				google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    				fillInAddress();
	    				});
}if(ii==6){
	    				autocompleteTo5 = new google.maps.places.Autocomplete(
	    				(document.getElementById('tripAddress'+ii)),
	    				{ types: ['geocode'] }); 
	    				google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    				fillInAddress();
	    				}); 
}if(ii==7){
	    				autocompleteTo6 = new google.maps.places.Autocomplete(
	    				(document.getElementById('tripAddress'+ii)),
	    				{ types: ['geocode'] }); 
	    				google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    				fillInAddress();
	    				}); 
}if(ii==8){
	    				autocompleteTo7 = new google.maps.places.Autocomplete(
	    				(document.getElementById('tripAddress'+ii)),
	    				{ types: ['geocode'] }); 
	    				google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    				fillInAddress();
	    				}); 
}if(ii==9){
	    				autocompleteTo8 = new google.maps.places.Autocomplete(
	    				(document.getElementById('tripAddress'+ii)),
	    				{ types: ['geocode'] }); 
	    				google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    				fillInAddress();
	    				}); 
}if(ii==10){
	    				autocompleteTo9 = new google.maps.places.Autocomplete(
	    				(document.getElementById('tripAddress'+ii)),
	    				{ types: ['geocode'] }); 
	    				google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    				fillInAddress();
	    				});
}if(ii==11){
	    				autocompleteTo10 = new google.maps.places.Autocomplete(
	    				(document.getElementById('tripAddress'+ii)),
	    				{ types: ['geocode'] }); 
	    				google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    				fillInAddress();
	    				});
}if(ii==12){
	    				autocompleteTo11 = new google.maps.places.Autocomplete(
	    				(document.getElementById('tripAddress'+ii)),
	    				{ types: ['geocode'] }); 
	    				google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    				fillInAddress();
	    				});
}if(ii==13){
	    				autocompleteTo12 = new google.maps.places.Autocomplete(
	    						(document.getElementById('tripAddress'+ii)),
	    						{ types: ['geocode'] }); 
	    						google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    						 fillInAddress();
	    						 }); 
}if(ii==14){
	    								    
	    						autocompleteTo14 = new google.maps.places.Autocomplete(
	    						(document.getElementById('tripAddress'+ii)),
	    						{ types: ['geocode'] }); 
	    						google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    						fillInAddress();
	    						 }); 
}if(ii==15){
	    						autocompleteTo15 = new google.maps.places.Autocomplete(
	    						(document.getElementById('tripAddress'+ii)),
	    						{ types: ['geocode'] }); 
	    						google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    						fillInAddress();
	    						});
}if(ii==16){
	    						autocompleteTo16 = new google.maps.places.Autocomplete(
	    						(document.getElementById('tripAddress'+ii)),
	    						{ types: ['geocode'] }); 
	    						google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    						fillInAddress();
	    						}); 
}if(ii==17){
	    						autocompleteTo17 = new google.maps.places.Autocomplete(
	    						(document.getElementById('tripAddress'+ii)),
	    						{ types: ['geocode'] }); 
	    						google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    						fillInAddress();
	    						}); 
}if(ii==18){
	    						autocompleteTo18 = new google.maps.places.Autocomplete(
	    						(document.getElementById('tripAddress'+ii)),
	    						{ types: ['geocode'] }); 
	    						google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    						fillInAddress();
	    						}); 
}if(ii==19){
	    						autocompleteTo19 = new google.maps.places.Autocomplete(
	    						(document.getElementById('tripAddress'+ii)),
	    						{ types: ['geocode'] }); 
	    						google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    						fillInAddress();
	    						});
}if(ii==20){
	    						autocompleteTo20 = new google.maps.places.Autocomplete(
	    						(document.getElementById('tripAddress'+ii)),
	    						{ types: ['geocode'] }); 
	    						google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    						fillInAddress();
	    						});
}if(ii==21){
	    						autocompleteTo21 = new google.maps.places.Autocomplete(
	    						(document.getElementById('tripAddress'+ii)),
	    						{ types: ['geocode'] }); 
	    						google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    						fillInAddress();
	    						});
                           }if(ii==22){
	    						autocompleteTo22 = new google.maps.places.Autocomplete(
	    						(document.getElementById('tripAddress'+ii)),
	    						{ types: ['geocode'] }); 
	    						google.maps.event.addListener(autocomplete, 'place_changed', function() {
	    						fillInAddress();
	    						});
                                 }
	    //datePickeInitialize();
	    //initialize();
	}
 //---------------------------------------//
	 
	
	
function removToExpancesRow() {
	var tbl = document.getElementById('expanceTable');
	var lastRow = tbl.rows.length;
	if (lastRow > 1)
		tbl.deleteRow(lastRow - 1);
}
 
function removToTripRow() {
	var tbl = document.getElementById('tableTrip');
	var lastRow = tbl.rows.length;
	if (lastRow > 1)
		tbl.deleteRow(lastRow - 1);
}
 
 function collectToTripBookingValue() {
		var tbl = document.getElementById('tableTrip');
		var lastRow = tbl.rows.length;
		var iteration = lastRow;
		var tripStoVal = new Array(); 
	 
		var idName = new Array("","tripAddress");
		var tripStop = ""; 
	 	for ( var i = 0; i < iteration; i++) {		
		 	for ( var id = 1; id < idName.length; id++) {
				var tripStopInner = "";
			 	 
				tripStopInner = document.getElementById(idName[id] + i).value;
				tripStopInner=tripStopInner.replace(/,/g, " ");
				if (id == (idName.length)-1) {
					tripStop =  tripStopInner ;
			     } else {
					tripStop =tripStopInner;
				}
			}
			 
		 	tripStoVal.push(tripStop);
		 
			 
		} 
	   document.getElementById("tripStop").value=tripStoVal;
		 
	// alert(tripStoVal);
		
        tripStoVal = new Array();
		return  tripStoVal;
		
	}
 
 
 function collectToExpValue() {
		var tbl = document.getElementById('expanceTable');
		var lastRow = tbl.rows.length;
		var iteration = lastRow;
		var tripExpVal = new Array(); 
		var tripAmountVal = new Array(); 
		var idName = new Array("","tolltax","amount");
		var tripExp = ""; 
		var tripAmount = ""; 
	 	for ( var i = 0; i < iteration; i++) {	
	 		var tripExpInner = "";
			var tripAmountInner = "";
		 	for ( var id = 1; id < idName.length; id++) {
				
			    if(id==1){ 
				tripExpInner = document.getElementById(idName[id] + i).value;
		 	     }
			     if(id==2){
					  tripAmountInner = document.getElementById(idName[id] + i).value;
				     }
			 	if (id == (idName.length)-1) {
					tripExp = tripExpInner;
					tripAmount=tripAmountInner;
			 	} else {
					tripExp = tripExpInner;
					tripAmount=tripAmountInner;
				}
			}
			 
		 	tripExpVal.push(tripExp);
		 	tripAmountVal.push(tripAmount);
	 	} 
	 	 
	   document.getElementById("tripExpType").value=tripExpVal;
	   document.getElementById("tripAmount").value=tripAmountVal;
		 
	// alert(tripStoVal);
		
     tripExpVal = new Array();
     tripAmountVal = new Array();
	 return  tripExpVal;
		
	}
 
 
 function  getSubsidiaryMandate()	
 {  var flag=false;  
	var sellOrgId = document.getElementById("orgName").value;
    var sellSubOrgId = document.getElementById("subOrgClient").value;
    var message="";
    if(sellOrgId==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("orgName").style.borderColor="red";
	    message+="Please select client !<br/>";
	 	document.getElementById('orgName').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("orgName").style.borderColor="green";
	 	}
    if(sellSubOrgId==""){
 	 	document.getElementById("message").style.color="red";
 	 	document.getElementById('message').style.display = "block";
 	 	document.getElementById("subOrgClient").style.borderColor="red";
 	    message+="Please select client subsidiary !<br/>";
 	 	document.getElementById('subOrgClient').focus();
 	 	flag= false;
 	 	}else{
 	 	document.getElementById("subOrgClient").style.borderColor="green";
 	 	}
   if(message==""){ 
   $.ajax({
       type: "POST",
       url: "/trux/subclient/getClientMandateType",
       data:{
    	  clientId:sellOrgId,
    	  subClientId:sellSubOrgId
 		  } ,
       success: function(data) {
    	   var mandateType="";
    	   mandateType= data.mandateType;
    	 //  alert(mandateType)
     	 $("#mandateType").val(mandateType); 
       }
     });
   }
 return flag;
 }


function driverListBySubsidiary()	
{    var sellSubOrgId = document.getElementById("subOrgClient").value;

 $.ajax({
      type: "POST",
      url: "/trux/subclient/getDriverDetailsBySubClientId",
      data:{
    	  bySubClient:sellSubOrgId
		  } ,
      success: function(data) {
    	  document.getElementById("driver").innerHTML = data;
    	  document.getElementById("driver").value.innerHTML=data;
      }
    });
return true;
}



function fillSubOrg()	
{   var flag=false; 
    var SelOrgValue = document.getElementById("orgName").value;
    var message="";
    
   if(SelOrgValue==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("assosiatedBy").style.borderColor="red";
	    message+="Please enter the Driver Assosiated By subsidiary !<br/>";
	 	document.getElementById('orgName').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("orgName").style.borderColor="green";
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
	    	  document.getElementById("subOrgClient").innerHTML = data;
	    	  document.getElementById("subOrgClient").value.innerHTML=data;
	      }
	    });
    return true;
   }
   return false;
}
 

var dateToday = new Date();
var dd = dateToday.getDate();
var mm = dateToday.getMonth()+1; 
var yyyy = dateToday.getFullYear();
var toYears=parseInt(yyyy);
$(function() {
$('#bookingDate').datetimepicker({
	 showOn: 'button',
	 buttonImage: "/trux/resources/images/calendar.png",
    buttonImageOnly: true, 
    timepicker:false,
    format:'Y/m/d',
	 dayOfWeekStart : 1,
	 lang:'en',
	 yearRange: '1800:' + toYears + '',
	 startDate:	dateToday  
	 });
});
 

 
 
 function searcgBookingsubmit(){
         getSubsidiaryMandate();
 
         var sellOrgName=$("#orgName").val();
	  	 var sellSubOrgClient=$("#subOrgClient").val();
	  	 var sellDriver=$("#driver").val();
	  	 var sellBookingDate=$("#bookingDate").val();
	     var dataString='orgName='+sellOrgName+'&subOrgClient='+sellSubOrgClient+'&driver='+sellDriver+'&bookingDate='+sellBookingDate;
	   	  $.ajax({
	  			  type: "GET",
	  			  url: "/trux/subclient/searchLeasedBookingAtNow",
	  			 data: dataString,
	  			  cache: false,
	  			  success: function(result){
	  				  if(result=='' || !result)
	  				  {
	  					 $("#booking").hide();
	                     $("#bookingOrderReports").hide();
	  				  } 
	  				 var data="";
	  				 $.each(result, function( index, value ) {
	  				  $("#booking").show();
                     $("#bookingOrderReports").show();
                     if(value.bookingId!=null){
                         data +='<tr><td>'+value.bookingId+'</td><td>'+value.clientName+'</td><td>'+value.driverName+'</td></tr>';
                     } }); 
	  	$('#bookedReportsTable').append(data); 
	  		 
	        
	    	
	    /* 	$("#driverId").val(data.id);
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
	  	    $("#subOrgClientM").html("( "+data.subOrgClient+" )");  */
	  	  
	  		 
	      }
	    });

	 return true;
		}
 
  
 function collectAndSubmit(){
	 collectToExpValue();
	 collectToTripBookingValue();
	 
	  var sellorgName=$("#orgName").val();
	  
     var sellTripStop=$("#tripStop").val();
  	 var sellTripExpType=$("#tripExpType").val();
  	 var sellTripAmount=$("#tripAmount").val();
  	 var sellDriver=$("#driver").val();
  	 var sellBookingDate=$("#bookingDate").val();
   	 var sellNoofboxes=$("#noofboxes").val();
   	 var sellSubOrgClient=$("#subOrgClient").val(); 
	 var sellStartKm="9"//$("#subOrgClient").val(); 
	 var sellEndKm="10"//$("#subOrgClient").val(); 
	 
   	 
 	 $.ajax({
 	      type: "POST",
 	      url: "/trux/subclient/leasedBookingAtNow ",
 	      data:{ 
 	    	orgName:sellorgName,
 	    	subOrgClient:sellSubOrgClient, 
 	    	driver:sellDriver, 
 	    	bookingDate:sellBookingDate,
 	    	noofboxes:sellNoofboxes,
 	    	tripStop:sellTripStop,
 	    	tripExpType:sellTripExpType,
 	    	tripAmount:sellTripAmount,
 	    	tripStartKm:sellStartKm,
 	    	tripEndKm:sellEndKm
 	      } ,
 	      success: function(data) {
 	    	//  document.getElementById("subOrgClient").innerHTML = data;
 	    	//  document.getElementById("subOrgClient").value.innerHTML=data;
 	      }
 	    });
     return true;
 }  
</script>
<script type="text/javascript">

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
(document.getElementById('tripAddress0')),
{ types: ['geocode'] });   
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
});    
autocompleteTo0= new google.maps.places.Autocomplete(
(document.getElementById('tripAddress1')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
});
   
autocompleteTo1 = new google.maps.places.Autocomplete(
(document.getElementById('tripAddress2')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
}); 

autocompleteTo2= new google.maps.places.Autocomplete(
(document.getElementById('tripAddress3')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
});
		    
autocompleteTo3 = new google.maps.places.Autocomplete(
(document.getElementById('tripAddress4')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
}); 
autocompleteTo4 = new google.maps.places.Autocomplete(
(document.getElementById('tripAddress5')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
});
autocompleteTo5 = new google.maps.places.Autocomplete(
(document.getElementById('tripAddress6')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
}); 
autocompleteTo6 = new google.maps.places.Autocomplete(
(document.getElementById('tripAddress7')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
}); 
autocompleteTo7 = new google.maps.places.Autocomplete(
(document.getElementById('tripAddress8')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
}); 
autocompleteTo8 = new google.maps.places.Autocomplete(
(document.getElementById('tripAddress9')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
}); 
autocompleteTo9 = new google.maps.places.Autocomplete(
(document.getElementById('tripAddress10')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
});
autocompleteTo10 = new google.maps.places.Autocomplete(
(document.getElementById('tripAddress11')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
});
autocompleteTo11 = new google.maps.places.Autocomplete(
(document.getElementById('tripAddress12')),
{ types: ['geocode'] }); 
google.maps.event.addListener(autocomplete, 'place_changed', function() {
fillInAddress();
});
autocompleteTo12 = new google.maps.places.Autocomplete(
		(document.getElementById('tripAddress13')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		 fillInAddress();
		 }); 
		 
				    
		autocompleteTo14 = new google.maps.places.Autocomplete(
		(document.getElementById('tripAddress14')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		 }); 
		autocompleteTo15 = new google.maps.places.Autocomplete(
		(document.getElementById('tripAddress15')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		});
		autocompleteTo16 = new google.maps.places.Autocomplete(
		(document.getElementById('tripAddress16')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		}); 
		 
		autocompleteTo17 = new google.maps.places.Autocomplete(
		(document.getElementById('tripAddress17')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		}); 
		autocompleteTo18 = new google.maps.places.Autocomplete(
		(document.getElementById('tripAddress18')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		}); 
		autocompleteTo19 = new google.maps.places.Autocomplete(
		(document.getElementById('tripAddress19')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		});
		autocompleteTo20 = new google.maps.places.Autocomplete(
		(document.getElementById('tripAddress20')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		});
		autocompleteTo21 = new google.maps.places.Autocomplete(
		(document.getElementById('tripAddress21')),
		{ types: ['geocode'] }); 
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
		fillInAddress();
		});
		autocompleteTo22 = new google.maps.places.Autocomplete(
		(document.getElementById('tripAddress22')),
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
</head>

<body onload="initialize();">
 <div class="container">  
	<div class="row">
			<div class="col-lg-8 col-md-8 col-sm-12"
				style="box-shadow: 2px -1px 1px rgba(0, 0, 0, 0.5)">
				<fieldset class="fieldset2 col-lg-12 borderManager table-responsive">
					<legend class="borderManager">New Order (LEASED) </legend>
                         <div class="row" style="margin-top: -2%;">
							<div class="col-lg-4 col-md-4 col-sm-12">
								<div style="margin-bottom: 6px;">Client<span style="color: red;">*</span></div>
 							  
 						 		<select name="orgName" id="orgName" onchange="fillSubOrg();" class="input-sm" style="width:100%;">
                                <option value="">--Select Client--</option>
                                 <% List<OrganizationMasterRegistration>  list= (List<OrganizationMasterRegistration>)request.getAttribute("orgList");
									if(list != null && !list.isEmpty()){
									for(int i=0; i<list.size();i++){ 
									%>	
								 <option value="<%=list.get(i).getIdClientMaster() %>"><%=list.get(i).getName() %></option>
                                 <%	}}%>  
                                  
                                  </select>
 						
 						 </div>
							<div class="col-lg-4 col-md-4 col-sm-12">
								<div style="margin-bottom: 6px;">Subsidiary<span style="color: red;">*</span>
								</div>
								<select name="subOrgClient" id="subOrgClient" onchange="driverListBySubsidiary();" class="input-sm"	cssStyle="width: 100%;">
									<option value="">--Select Subsidiary--</option>
								</select>
							</div>

							<div class="col-lg-4 col-md-4 col-sm-12">
								<div style="margin-bottom: 6px;">Driver<span style="color: red;">*</span>
								</div>
								<select name="driver" id="driver" class="input-sm"
									style="width: 100%;">
									<option value="">--Select Driver--</option>
								</select>
							</div> 
							<div class="clearfix margin_10"></div>

							<div class="col-lg-4 col-md-4 col-sm-12 ">
								<div style="margin-bottom: 6px;">Order Date<span style="color: red;">*</span>
								</div>
                                <input type="text" name="bookingDate" id="bookingDate"	class="form-control input-sm " style="width: 100%; box-shadow: 1px 1px #898787; border: 1px solid #A2A2A2; background: url(../resources/images/calender2.png); background-repeat: no-repeat; background-position: right" placeholder="Order Date" >

							</div>
							 	<div class="col-lg-5 col-md-5 col-sm-12">
								<div style="margin-bottom: 6px;">&nbsp;</div>
								<input type="button" class="btn btn-danger btn-sm btn_nav1"	id="save" onclick="searcgBookingsubmit()" value="search booking" style="float: left;" >
								<div class="col-lg-8 col-md-8 col-sm-12" style="float: left; padding: 5px 25px;">
									<label style="cursor: pointer; color: #0AAAD2;">
									<span class="glyphicon glyphicon-unchecked"></span> &nbsp;Mark Attendance</label>
								</div>
							</div>
						 <div id="message" style="color: red;"></div>
						</div>
					
					
				</fieldset>
				<div id="booking" style="display: none;">
				<div class="clearfix margin_10"
					style="border-bottom: 2px dotted #999; padding-bottom: 16px; margin-bottom: 5px;"></div>
				<div class="col-lg-12 col-md-12 col-sm-12 message_box">
					<lable>  Booking By Trux App </lable>
				</div>

				<div class="clearfix margin_10"></div>

				<div class="col-lg-4 col-md-4 col-sm-12 ">

					<table style="width: 100%;" id="tripsTable">
						<tbody id="tripNoTable">
							<tr>
								<td>

									<div class="col-lg-12 col-md-12 col-sm-12">
										<div style="margin-bottom: 6px">
											No. of Boxes <span style="color: red;">*</span>
										</div>
										<input type="text" name="noofbox0" id="noofbox0" onkeyup="isNumberKey(event,'noofbox0');" class="form-control input-sm" maxlength="10" style="width: 100%; border: 1px solid #A2A2A2;" placeholder="No Of Boxes">
									</div>

								</td>
							</tr>
						</tbody>
					</table>


				</div>
				<div class="col-lg-4 col-md-4 col-sm-12 ">
					<table style="width: 100%;" id="tripsTable">
						<tbody id="trsTable">
							<tr>
								<td>

									<div class="col-lg-12 col-md-12 col-sm-12">

										<table style="width: 100%;" id="tableTrip">
											<tbody id="tripTable">
												<tr>
													<td>
														<div style="margin-bottom: 6px;">
															Trip Stops<span style="color: red;">*</span>
														</div> <input type="text" name="tripAddress0" id="tripAddress0"
														class="form-control input-sm" maxlength="10"
														style="width: 100%; border: 1px solid #A2A2A2;"
														placeholder="Trip Stops">
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12">
										<input type="button" value="+"
											class="btn btn-danger btn-sm btn_nav1"
											onclick="addTripRow();"
											title="Add the to address and phone number!"
											style="margin-top: 5px;"> <input type="button"
											value="-" onclick="removToTripRow();"
											class="btn btn-danger btn-sm btn_nav1"
											title="Remove the to address and phone number! "
											style="margin-top: 5px;">
									</div>
								</td>

							</tr>

						</tbody>
					</table>


				</div>  
				<div class="col-lg-4 col-md-4 col-sm-12 ">
					<table style="width: 100%;" id="expsTable">
						<tbody id="expanceTable">
							<tr>
								<td> <div class="col-lg-12 col-md-12 col-sm-12">
										<div style="margin-bottom: 6px;">Trip Expances<span style="color: red;">*</span>
										</div>
										<div class="col-lg-12 col-md-12 col-sm-12"
											style="border: 1px solid #999; border-radius: 4px; 0 px 12px 0 0; padding: 0;">

											<select name="tolltax0" id="tolltax0"
												class="col-lg-6 col-md-6 col-sm-6"
												style="height: 30px; border: none; padding: 1px;">
												<option value="">--Select--</option>
												<option value="Toll Tax">Toll Tax</option>
												<option value="Bilty">Bilty</option>
												<option value="Parking">Parking</option>
												<option value="Chalan">Chalan</option>
												<option value="Other">Other</option>
												<option value="OtherDescription">Other Description</option>
											    </select> 
											   <input type="text" name="amount0" id="amount0"
												onkeyup="isNumberKey(event,'amount0');"
												class="form-control input-sm col-lg-6 col-md-6 col-sm-6"
												maxlength="10"
												style="width: 50%; padding: 0px; border: none;"
												placeholder="Expences Amount">
												<!--  <input type="text" name="amount0" id="amount0"
												 
												class="form-control input-sm col-lg-6 col-md-6 col-sm-6"
												maxlength="10"
												style="width: 50%; padding: 0px; border: none;"
												placeholder="Other Description"> -->
										</div>
									</div>
								</td>

							</tr>

						</tbody>
					</table>

					<div class="col-lg-12 col-md-12 col-sm-12">
						<input type="button" value="+" class="btn btn-danger btn-sm btn_nav1" 	onclick="addExpancesRow();" title="Add the Expances!"
							style="margin-top: 5px;"> 
			         <input type="button" value="-" onclick="removToExpancesRow();" class="btn btn-danger btn-sm btn_nav1" title="Remove the Expances! " style="margin-top: 5px;">
					</div>
				</div>
 				</div>	
 				<div class="clearfix margin_10"></div>
                <input type="hidden" name="mandateType" id="mandateType">
				<input type="hidden" name="tripStop" id="tripStop">
				<input type="hidden" name="tripExpType" id="tripExpType">
				 <input type="hidden" name="tripAmount" id="tripAmount"> 
				 <input type="hidden" name="subOrgClient" id="subOrgClient">
				  <input type="hidden" name="driver" id="driver">
				  <input type="hidden" name="bookingDate" id="bookingDate"> 
				  <input type="hidden" name="noofboxes" id="noofboxes">
			      <input type="button" class="btn btn-danger btn-sm btn_nav1" id="save" onclick="collectAndSubmit();"	value="Create Order" style="margin-left: 15px"> 
				   <input type="reset" class="btn btn-danger btn-sm btn_nav1" value="Reset">
				<div id="message" style="color: red;"></div>
				</fieldset>
		</div>
		
		<div class="col-lg-3 col-md-3 col-sm-12 right_table"  id="bookingOrderReports" style="display: none; ">
		 <h4 style="text-align: center;color: #fff;background-color: #0AAAD2;padding: 6px; margin: 0px;border-radius: 6px;">Order History</h4>
        <div style="overflow: auto;height: 300px;">
        <table class="table table-bordered  "id="successTable"  style="color: #727272; overflow-x:scroll;">
         <thead>
       <tr style="background-color: #0AAAD2;color: white;border: 0px; height: 30px; ">
	     <th>
		  <span style="width: 100%;float: left; padding: 5px; margin: 0px;  text-align: center;">Order Id</span>
        </th>
        <th>
         <span style="width: 100%;float: left; padding: 5px; margin: 0px;  text-align: center;">Client</span> 
         </th>
         <th>
          <span  style="width: 100%;float: left; padding: 5px; margin: 0px;   text-align: center;">Driver</span> 
         </th>
		</tr>
		</thead>
		<tbody id="bookedReportsTable">
		</tbody>
		</table>
		</div>
		</div>
		 
		
		
</div>
  </body>
</html>
 
	 