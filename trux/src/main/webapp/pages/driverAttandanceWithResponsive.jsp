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


<!-- Javascript goes in the document HEAD -->
<script type="text/javascript">
function altRows(id){
	if(document.getElementsByTagName){  
		
		var table = document.getElementById(id);  
		var rows = table.getElementsByTagName("tr"); 
		 
		for(i = 0; i < rows.length; i++){          
			if(i % 2 == 0){
				rows[i].className = "evenrowcolor";
			}else{
				rows[i].className = "oddrowcolor";
			}      
		}
	}
}
window.onload=function(){
	altRows('alternatecolor');
}
</script>

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

<title>Driver Attandance</title>

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
     var clientSubId = document.getElementById("clientSubId").value;
 	 var driver=document.getElementById("driver").value; 
 	var tbl = document.getElementById('attandanceTable');
	var lastRow = tbl.rows.length;
	var iteration = lastRow; 
	
	var message="";
	
 	if(orgName==""){
 	 	  document.getElementById('message').style.display = "block";
 	 	  document.getElementById('message').style.color="red";
 	 	  message+="<br/>";
 	 	  message+="Please select the Client name  !<br/>";
 	 	  document.getElementById("orgName").style.borderColor="red";
 	 	  document.getElementById('orgName').focus();
 	 	  flag= false;
 	 	  }else{
 	 	  document.getElementById("orgName").style.borderColor="green"; 
 	 	  }
 	if(clientSubId==""){
	 	  document.getElementById('message').style.display = "block";
	 	  document.getElementById('message').style.color="red";
	 	  message+="<br/>";
	 	  message+="Please select the Sub Client name  !<br/>";
	 	  document.getElementById("clientSubId").style.borderColor="red";
	 	  document.getElementById('clientSubId').focus();
	 	  flag= false;
	 	  }else{
	 	  document.getElementById("clientSubId").style.borderColor="green"; 
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
			/* if(checkinInner==""){ 
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
			} */
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
			 
		/* 	if(checkOutInner==""){ 
			 	  document.getElementById('message').style.display = "block";
			 	  document.getElementById('message').style.color="red";
			 	  message+="<br/>";
			 	  message+="Please Enter the CheckOut time  !<br/>";
			 	 document.getElementById(idName[id] + i).style.borderColor="red";
			 	   flag= false;
			 	  innerflag=false;
			 	  }else{
			 	 document.getElementById(idName[id] + i).style.borderColor="green"; 
			} */
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
			/* 	openingInner = document.getElementById(idName[id] + i).value;
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
				} */
				}
			
			if(id=5){
			  closingInner = document.getElementById(idName[id] + i).value;
				/* if(closingInner==""){ 
				 	  document.getElementById('message').style.display = "block";
				 	  document.getElementById('message').style.color="red";
				 	  message+="<br/>";
				 	  message+="Please Enter the closing kilometer   !<br/>";
				 	 document.getElementById(idName[id] + i).style.borderColor="red";
				 	  flag= false;
				 	  innerflag=false;
				 	  }else{
				 	 document.getElementById(idName[id] + i).style.borderColor="green"; 
				} */ 
				}
			if(id=6){
				tolltaxInner = document.getElementById(idName[id] + i).value;
				/* if(tolltaxInner==""){ 
				 	  document.getElementById('message').style.display = "block";
				 	  document.getElementById('message').style.color="red";
				 	  message+="<br/>";
				 	  message+="Please Enter the toll tax amount   !<br/>";
				 	 document.getElementById(idName[id] + i).style.borderColor="red";
				 	  flag= false;
				 	  innerflag=false;
				 	  }else{
				 	 document.getElementById(idName[id] + i).style.borderColor="green"; 
				} */
				}
			if(id=7){
				noofboxInner = document.getElementById(idName[id] + i).value;
				/* if(noofboxInner==""){ 
				 	  document.getElementById('message').style.display = "block";
				 	  document.getElementById('message').style.color="red";
				 	  message+="<br/>";
				 	  message+="Please Enter the No Of Box at most total no. of count or zero    !<br/>";
				 	 document.getElementById(idName[id] + i).style.borderColor="red";
				 	  flag= false;
				 	  innerflag=false;
				 	  }else{
				 	 document.getElementById(idName[id] + i).style.borderColor="green"; 
				} */
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

function addRow(){
 var i= $('#attandanceTable').children().length;
 i; 
    var data="<tr><td>";
    data +="<div class='col-lg-2 col-md-3 col-sm-12 drv-atnd'><input type='text' name='attandanceDate"+i+"' id='attandanceDate"+i+"' style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2; background:url(../resources/images/calender2.png);background-repeat: no-repeat; background-position:right' class='form-control input-sm'   placeholder'Attandance Date'></div><div class='col-lg-2 col-md-3 col-sm-12 drv-atnd'> <input type='text' name='checkin"+i+"' id='checkin"+i+"' class='form-control input-sm' style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2;background:url(../resources/images/time.png);background-repeat: no-repeat; background-position:right' maxlength='25'   placeholder='Check In date with time'></div><div class='col-lg-2 col-md-3 col-sm-12 drv-atnd'><input type='text' name='checkout"+i+"' id='checkout"+i+"' class='form-control input-sm' maxlength='25'   style='width:100%;box-shadow: 1px 1px #898787;background:url(../resources/images/time.png);background-repeat: no-repeat;border: 1px solid #A2A2A2; background-position:right' placeholder='Check Out date with time'></div><div class='col-lg-2 col-md-3 col-sm-12 drv-atnd'><input type='text' name='openingkm"+i+"' id='openingkm"+i+"'  onkeyup=\"isNumberKey(event,'openingkm"+i+"');\" class='form-control input-sm' maxlength='10' style='width:100%;box-shadow: 1px 1px #898787;background:url(../resources/images/meter.png);background-repeat: no-repeat;border: 1px solid #A2A2A2; background-position:right' placeholder='Opening kilometer'></div> <div class='col-lg-2 col-md-4 col-sm-12 drv-atnd'><input type='text' name='closingkm"+i+"' id='closingkm"+i+"' onkeyup=\"isNumberKey(event,'closingkm"+i+"');\" class='form-control input-sm' maxlength='10'  style='width:100%;box-shadow: 1px 1px #898787;background:url(../resources/images/meter.png);background-repeat: no-repeat;border: 1px solid #A2A2A2; background-position:right' placeholder='Closing kilometer'> </div> <div class='col-lg-2 col-md-3 col-sm-12 drv-atnd'><input type='text' name='tolltax"+i+"' id='tolltax"+i+"'  onkeyup=\"isNumberKey(event,'tolltax"+i+"');\" class='form-control input-sm' maxlength='10'  style='width:100%;box-shadow: 1px 1px #898787;background:url(../resources/images/rs.png);background-repeat: no-repeat;border: 1px solid #A2A2A2; background-position:right' placeholder='Toll Tax Amount'></div><div class='col-lg-2 col-md-4 col-sm-12 drv-atnd'><input type='text' name='noofbox"+i+"' id='noofbox"+i+"'  onkeyup=\"isNumberKey(event,'noofbox"+i+"');\" class='form-control input-sm' maxlength='10'  style='width:100%;box-shadow: 1px 1px #898787;background:url(../resources/images/counter.png);background-repeat: no-repeat;border: 1px solid #A2A2A2; background-position:right' placeholder'No Of Boxes'></div></td></tr>";
    $('#attandaceAddTable').append(data);
    datePickeInitialize();
}
 //---------------------------------------//
	 
	
	
function removToAttandanceRow() {
	var tbl = document.getElementById('attandanceTable');
	var lastRow = tbl.rows.length;
	if (lastRow > 1)
		tbl.deleteRow(lastRow - 1);
}
 
 function collectToAttandanceValue() {
		var tbl = document.getElementById('attandanceTable');
		var lastRow = tbl.rows.length;
		var iteration = lastRow;
		var attandanceVal = new Array();
		var checInVal = new Array();
		var checkOutVal = new Array();
		var openingOutVal = new Array();
		var closingOutVal = new Array();
		var tolltaxOutVal = new Array();
		var noofboxesOutVal = new Array();
		var idName = new Array("","attandanceDate","checkin", "checkout","openingkm","closingkm","tolltax","noofbox");
		var attandance = "";
		var checkin = "";
		var checkOut = "";
		var openingOut = "";
		var closingOut = "";
		var tolltaxOut = "";
		var noofboxesOut="";
			
		for ( var i = 0; i < iteration; i++) {		
			 
			for ( var id = 1; id < idName.length; id++) {
				var attandanceInner = "";
				var checkinInner = "";
				var checkOutInner = "";
				var openingOutInner = "";
				var closingOutInner = "";
				var tolltaxOutInner = "";
				var noofboxesOutInner ="";
				attandanceInner = document.getElementById(idName[id] + i).value;
				
				if(id=2){
				checkinInner = document.getElementById(idName[id] + i).value;
               
				}
				if(id=3){
				checkOutInner = document.getElementById(idName[id] + i).value;
				}
				
				if(id=4){
					openingOutInner = document.getElementById(idName[id] + i).value;
					}
				if(id=5){
					closingOutInner = document.getElementById(idName[id] + i).value;
					}
				if(id=6){
					tolltaxOutInner = document.getElementById(idName[id] + i).value;
					}
				
				if(id=7){
					noofboxesOutInner = document.getElementById(idName[id] + i).value;
					}
				
				if (id == (idName.length)-1) {
					attandance = attandanceInner;
					checkin=checkinInner;
					checkOut=checkOutInner;
				  	openingOut = openingOutInner;
					closingOut=closingOutInner;
					tolltaxOut=tolltaxOutInner;
					noofboxesOut=noofboxesOutInner;
				} else {
	 				attandance = attandanceInner;
					checkin=checkinInner;
					checkOut=checkOutInner;
					openingOut = openingOutInner;
					closingOut=closingOutInner;
					tolltaxOut=tolltaxOutInner;
					noofboxesOut=noofboxesOutInner;
				}
			}
			 
			attandanceVal.push(attandance);
			checInVal.push(checkin);
			checkOutVal.push(checkOut);
			
			openingOutVal.push(openingOut);
			closingOutVal.push(closingOut);
			tolltaxOutVal.push(tolltaxOut);
			noofboxesOutVal.push(noofboxesOut);
			 
		} 
		
		document.getElementById("attandanceDate").value=attandanceVal;
		document.getElementById("checkin").value=checInVal;
		document.getElementById("checkout").value=checkOutVal;
		
	 	document.getElementById("opening").value=openingOutVal;
		document.getElementById("closing").value=closingOutVal;
		document.getElementById("tolltax").value=tolltaxOutVal;
		document.getElementById("noofboxes").value=noofboxesOutVal;
	// alert(attandanceVal+" --- "+checInVal +"---  "+checkOutVal +" -- "+openingOutVal+" -- "+closingOutVal+" --  "+tolltaxOutVal);
		
        attandanceVal = new Array();
		  checInVal = new Array();
		  checkOutVal = new Array();
		  
		  openingOutVal = new Array();
		  closingOutVal = new Array();
		  tolltaxOutVal = new Array();
		  noofboxesOutVal=new Array();
		return  attandanceVal;
		
	}

var dateToday = new Date();
var dd = dateToday.getDate();
var mm = dateToday.getMonth()+1; 
var yyyy = dateToday.getFullYear();
var toYears=parseInt(yyyy);
$(function() {
$('#attandanceDate0').datetimepicker({
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
$('#checkin0').datetimepicker({
	 /* datepicker:false,
	  format:'H:i',
	  step:5  */ 
	 timeFormat: 'HH:mm z',
	 dayOfWeekStart : 1,
	 lang:'en',
	 yearRange: '1800:' + toYears + '',
	 startDate:	dateToday
	 });
$('#checkout0').datetimepicker({
	 /* datepicker:false,
	  format:'H:i',
	  step:5  */
	 timeFormat: 'HH:mm z',
	 dayOfWeekStart : 1,
	 lang:'en',
	 yearRange: '1800:' + toYears + '',
	 startDate:	dateToday
	 });
});
function datePickeInitialize() {
	var tbl = document.getElementById('attandanceTable');
	var lastRow = tbl.rows.length;
	for(i=0;i<lastRow;i++){
	 $('#attandanceDate'+i).datetimepicker({
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
	 $('#checkin'+i).datetimepicker({
		 /* datepicker:false,
		  format:'H:i',
		  step:5  */ 
		 timeFormat: 'HH:mm z',
		 dayOfWeekStart : 1,
		 lang:'en',
		 yearRange: '1800:' + toYears + '',
		 startDate:	dateToday
		 });
	 $('#checkout'+i).datetimepicker({
		 /* datepicker:false,
		  format:'H:i',
		  step:5  */
		 timeFormat: 'HH:mm z',
		 dayOfWeekStart : 1,
		 lang:'en',
		 yearRange: '1800:' + toYears + '',
		 startDate:	dateToday
		 });
	  $('#igms0'+i).click(function(){
		  	$('#attandanceDate'+i).datetimepicker('show');
		});
	 $('#igms1'+i).click(function(){
			$('#checkin'+i).datetimepicker('show');
		});
	 $('#igms2'+i).click(function(){
			$('#checkout'+i).datetimepicker('show');
		}); 
	}
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
	      url: "/trux/reg/getSubsidiaryOrgByMasterIdWithUser",
	      data:{ 
	    	  idClientMaster:SelOrgValue
			  } ,
	      success: function(data) {
	    	  document.getElementById("clientSubId").innerHTML = data;
	    	  document.getElementById("clientSubId").value.innerHTML=data;
	      }
	    });
    return true;
   }
   return false;
}


function driverList()	
{    var sellSubOrgId = document.getElementById("clientSubId").value;
 
	 $.ajax({
	      type: "POST",
	      url: "/trux/attandance/getDriverDetails",
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

 
	</script>
</head>
 <body>
 <div class="container">  
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager">  <legend class="borderManager">Driver Attendance <%List<DriverAttandance> savelists=(List<DriverAttandance>)request.getAttribute("savelists"); if(savelists!=null && savelists.size()>0){ %>  <b style="color: green;"><%=savelists.get(0).getStatusMessage() %></b><%} %></legend>
		 
			<form  action="atDrivers" method="post" class="form-inline" onsubmit="return validateForm();">
			 <div class="row" style="margin-top:-2%; ">                                        									                                  
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Client Name<span style="color: red;">*</span></div>
							
							  <select name="orgName" id="orgName" onchange="fillSubOrg()" class="input-sm" style="width:100%;">
                                 <option value="">--Select Client--</option>
                                 <% List<OrganizationMasterRegistration>  list= (List<OrganizationMasterRegistration>)request.getAttribute("orgList");
									if(list != null && !list.isEmpty()){
									for(int i=0; i<list.size();i++){ 
									%>	
								 <option value="<%=list.get(i).getIdClientMaster() %>"><%=list.get(i).getName() %></option>
                                 <%	}}%>                        
                             </select>
                     	</div>             
                     	<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;"> Client Subsidiary <span style="color: red;">*</span></div>
						<select name="clientSubId" id="clientSubId"  onchange="driverList()" class="input-sm" style="width:100%;">
                                 <option value="">--Select Client Subsidiary--</option>
                              </select>
					 	</div>
						                            
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Driver Name<span style="color: red;">*</span></div>
							<select name="driverName" id="driver"  class="input-sm" style="width:100%;">
							<option value="">--Select Driver--</option>
							 </select>    
						</div>	
						 
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
						 
					 <table id="attandaceAddTable" style="width: 100%; margin-bottom:10px;">
					 <thead>
				   </thead>
						 <tbody id="attandanceTable" >
						  <tr>
							<td>
							<div class="col-lg-2 col-md-3 col-sm-12 drv-atnd ">
                            
							 <input type="text"  name="attandanceDate0" id="attandanceDate0" class="form-control input-sm "  style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2; background:url(../resources/images/calender2.png);background-repeat: no-repeat; background-position:right' placeholder="Attandance Date"; >
                               
						     </div>	
                        	
                           <div class="col-lg-2 col-md-2 col-sm-12 drv-atnd">
                           <input type="text" name="checkin0" id="checkin0" class="form-control input-sm" maxlength="25"  style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2;background:url(../resources/images/time.png);background-repeat: no-repeat; background-position:right'placeholder="Check In date with time " autocomplete="off"> 
						 	  </div>	
                         
                           <div class="col-lg-2 col-md-2 col-sm-12 drv-atnd"><input type="text" name="checkout0" id="checkout0" class="form-control input-sm" maxlength="25"  style='width:100%;box-shadow: 1px 1px #898787;background:url(../resources/images/time.png);background-repeat: no-repeat;border: 1px solid #A2A2A2; background-position:right' placeholder="Check Out date with time" autocomplete="off"> 
							   </div>	
                           
                           <div class="col-lg-2 col-md-2 col-sm-12 drv-atnd"><input type="text" autocomplete="off" name="openingkm0" id="openingkm0" onkeyup='isNumberKey(event,"openingkm0");' class="form-control input-sm" maxlength="10"  style='width:100%;box-shadow: 1px 1px #898787;background:url(../resources/images/meter.png);background-repeat: no-repeat;border: 1px solid #A2A2A2; background-position:right' autocomplete="off" placeholder="Opening kilometer"> 
						   </div>	
                         
                           <div class="col-lg-2 col-md-2 col-sm-12 drv-atnd"><input type="text" autocomplete="off" name="closingkm0" id="closingkm0" onkeyup='isNumberKey(event,"closingkm0");' class="form-control input-sm" maxlength="10"  style='width:100%;box-shadow: 1px 1px #898787;background:url(../resources/images/meter.png);background-repeat: no-repeat;border: 1px solid #A2A2A2; background-position:right' placeholder="Closing kilometer "> 
						    </div>	
                           
                           <div class="col-lg-2 col-md-2 col-sm-12 drv-atnd">
                           <input type="text" name="tolltax0" id="tolltax0" autocomplete="off" onkeyup='isNumberKey(event,"tolltax0");' class="form-control input-sm" maxlength="10"  style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2;background:url(../resources/images/rs.png);background-repeat: no-repeat; background-position:right' placeholder="Toll Tax Amount"> 
						   </div>
						  <div class="col-lg-2 col-md-2 col-sm-12 drv-atnd">
                           <input type="text" name="noofbox0" id="noofbox0" autocomplete="off" onkeyup='isNumberKey(event,"noofbox0");' class="form-control input-sm" maxlength="10"  style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2;background:url(../resources/images/counter.png);background-repeat: no-repeat; background-position:right' placeholder="No Of Boxes"> 
						  </div>	
                         </td> 
						</tr>
					 </tbody>
					</table>
				</div> 
 
							<input type="hidden" name="attandanceDate" id="attandanceDate">
							<input type="hidden" name="checkin" id="checkin">	
							<input type="hidden" name="checkout" id="checkout">	
							<input type="hidden" name="opening" id="opening">
							<input type="hidden" name="closing" id="closing">	
							<input type="hidden" name="tolltax" id="tolltax">
							<input type="hidden" name="noofboxes" id="noofboxes"> 
							 					
							<input type="button" value="+" class="btn btn-danger btn-sm btn_nav1" onclick="addRow();" title="Add the to address and phone number!"/>
							<input type="button" value="-" onclick="removToAttandanceRow();" class="btn btn-danger btn-sm btn_nav1" title="Remove the to address and phone number! "/>
						    <input type="submit" class="btn btn-danger btn-sm btn_nav1" id="save"  value="Save Now"> 
							<input type="reset" class="btn btn-danger btn-sm btn_nav1" value="Reset"> 
							 		 				
			</form>
			 <div id="message" style="color: red;"></div>  
			</fieldset>
		</div>
	</div>		
</div>                  <%!int i = 1;%>
						<%   List<DriverAttandance> successList=(List<DriverAttandance>)request.getAttribute("savelists");
							if(successList!=null && successList.size()>0){	 
							%>
                    	<div class="table-responsive">
							<table class="table table-bordered  "id="successTable"  style="color: #727272; overflow-x:scroll;">
    <thead>
      <tr style="background-color: #0AAAD2;color: white;border: 0px; height: 30px; ">
        <th style="width:10%; text-align: center;">Sr.N.</th>
        <th style="width:10%; text-align: center;">Attandace</th>
        <th style="width:10%; text-align: center;">Punchin Date Time</th>
        <th style="width:10%; text-align: center;">Punchout Date Time</th>		
		<th style="width:10%; text-align: center;">Starting Kilometer</th>
		<th style="width:10%; text-align: center;">End Kilometer</th>
        <th style="width:10%; text-align: center;">Toll Tax</th>
        <th style="width:10%; text-align: center;">No Of Boxes</th>
		<th style="width:10%; text-align: center;">Created Date</th>
        <th style="width:10%; text-align: center;">Modified Date</th>
        <th style="width:10%; text-align: center;">Status</th>
      </tr>
    </thead>
    <tbody id="successResponse" style="text-align: center;" >
						 
		<%try{
			for (DriverAttandance attandance : successList)
					{  if(attandance!=null){
						%>
						  <tr> <td style="width: auto;"><%=i++ %></td>
							<%if(attandance.getAttendanceDate()!=null){ %>
							<td style="width: auto;">&nbsp;&nbsp;<%=attandance.getAttendanceDate()%></td>
						 	<%}else{%>
						 	<td>&nbsp;</td>
							<%}if(attandance.getPunchIn()!=null){ %>
						 	<td style="width: auto;">&nbsp;&nbsp;<%=attandance.getPunchIn()%></td>
							<%}else{%>
						 	<td>&nbsp;</td>
							<%}if(attandance.getPunchOut()!=null){ %>
							<td style="width: auto;">&nbsp;&nbsp;<%=attandance.getPunchOut()%></td>
							<%}else{%>
						 	<td>&nbsp;</td>
							<%}if(attandance.getOpeningKilometer()!=null){ %>
							<td style="width: auto;">&nbsp;&nbsp;<%=attandance.getOpeningKilometer()%></td>
							<%}else{%>
						 	<td>&nbsp;</td>
							<%}if(attandance.getClosingKilometer()!=null){ %>
							<td style="width: auto;">&nbsp;&nbsp;<%=attandance.getClosingKilometer()%></td>
							<%}else{%>
						 	<td>&nbsp;</td>
							<%}if(attandance.getTolltax()!=null){ %>
							<td style="width: auto;">&nbsp;&nbsp;<%=attandance.getTolltax()%></td> 
							<%}else{%>
						 	<td>&nbsp;</td>
							<%}if(attandance.getNoofboxes()!=null){ %>
							<td style="width: auto;">&nbsp;&nbsp;<%=attandance.getNoofboxes()%></td>  
							<%}else{%>
						 	<td>&nbsp;</td>
						 	<%}if(attandance.getCreatedDate()!=null){ %>
							<td style="width: auto;">&nbsp;&nbsp;<%=attandance.getCreatedDate().toString()%></td>
							<%}else{%>
						 	<td>&nbsp;</td>
							<%}if(attandance.getModifiedDate()!=null){ %>
							<td style="width: auto;">&nbsp;&nbsp;<%=attandance.getModifiedDate().toString()%></td>
							 
							<%}else{%>
						 	<td>&nbsp;</td>
						 	<%}if(attandance.getStatusMessage()!=null){ %>
							<td style="width: auto;">&nbsp;&nbsp;&nbsp;&nbsp;<%=attandance.getStatusMessage()%></td>
							<%}else{%>
						 	<td>&nbsp;</td><%} %>
						 	</tr>
						<%
						 } }
							 i = 1;
						}catch(Exception er ){out.print(er.getMessage());}}%>
						
						 
					 </tbody>
					</table>
					</div>
  </body>
</html>
 
	 