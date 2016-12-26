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
	var iteration = lastRow - 1; 
	
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
   
	//---------------------------------------//
	
function addToAttandanceRow() {	
	var tbl = document.getElementById('attandanceTable');
	var lastRow = tbl.rows.length;
	var iteration = lastRow - 1;
	var row = tbl.insertRow(lastRow); 
	var cellRight0 = row.insertCell(0);
	var el1 = document.createElement('input');
	el1.type = 'text';
	el1.name = 'attandanceDate' + iteration;
	el1.id = 'attandanceDate' + iteration; 
	el1.onFocus = "geolocate()";
	el1.className = "form-control input-sm";
	el1.size = 22;
	cellRight0.appendChild(el1);
	var cellRightImg1 = row.insertCell(1);
	var img1 = document.createElement('img');
    img1.src = "/trux/resources/images/calendar.png";
    img1.id = 'imgs0' + iteration; 
    img1.title="Please click on text field area after that  show the calander";
    cellRightImg1.appendChild(img1);   
	
	var cellRight1 = row.insertCell(2);
	var el2= document.createElement('input');
	el2.type = 'text';
	el2.name = 'checkout' + iteration;
	el2.id = 'checkout' + iteration;
	el2.className = "form-control input-sm";
	el2.onkeyup="isNumberKey(event,'checkout"+ iteration+"');";
	el2.maxLength="5";
	el2.size = 10;
	cellRight1.appendChild(el2);
	var cellRightImg2 = row.insertCell(3);
	var img2 = document.createElement('img');
    img2.src = "/trux/resources/images/calendar.png";
    img2.id = 'imgs1' + iteration; 
    img2.title="Please click on text field area after that  show the calander";
    cellRightImg2.appendChild(img2);
    
	var cellRight2 = row.insertCell(4);
	var el3= document.createElement('input');
	el3.type = 'text';
	el3.name = 'checkin' + iteration;
	el3.id = 'checkin' + iteration;
	el3.className = "form-control input-sm";
	el3.onkeyup="isNumberKey(event,'checkin"+ iteration+"');";
	el3.maxLength="5";
	el3.size = 10;
	cellRight2.appendChild(el3);
	var cellRightImg3 = row.insertCell(5);
	var img3 = document.createElement('img');
    img3.src = "/trux/resources/images/calendar.png";
    img3.id = 'imgs2' + iteration; 
    img3.title="Please click on text field area after that  show the calander";
    cellRightImg3.appendChild(img3);
    
    
    //
    var cellRight4 = row.insertCell(6);
	var el4= document.createElement('input');
	el4.type = 'text';
	el4.name = 'openingkm' + iteration;
	el4.id = 'openingkm' + iteration;
	el4.className = "form-control input-sm";
	el4.onkeyup="isNumberKey(event,'openingkm"+ iteration+"');";
	el4.maxLength="10";
	el4.size = 10;
	cellRight4.appendChild(el4);
	var cellRightImg4 = row.insertCell(7);
	var img4 = document.createElement('img');
    img4.src = "/trux/resources/images/speedometer.jpg";
    img4.id = 'imgs3' + iteration; 
    img4.width=30 ;
    img4.height=30;
    img4.title="Please click on text field area after that  show the calander";
    cellRightImg4.appendChild(img4); 
    
    
    
    var cellRight5 = row.insertCell(8);
	var el5= document.createElement('input');
	el5.type = 'text';
	el5.name = 'closingkm' + iteration;
	el5.id = 'closingkm' + iteration;
	el5.className = "form-control input-sm";
	el5.onkeyup="isNumberKey(event,'closingkm"+ iteration+"');";
	el5.maxLength="10";
	el5.size = 10;
	cellRight5.appendChild(el5);
	var cellRightImg5 = row.insertCell(9);
    var img5 = document.createElement('img');
    img5.src = "/trux/resources/images/speedometer.jpg";
    img5.id = 'imgs4' + iteration; 
    img5.width=30 ;
    img5.height=30;
    img5.title="Please click on text field area after that  show the calander";
    cellRightImg5.appendChild(img5);  
    
    
    
    var cellRight6 = row.insertCell(10);
	var el6= document.createElement('input');
	el6.type = 'text';
	el6.name = 'tolltax' + iteration;
	el6.id = 'tolltax' + iteration;
	el6.className = "form-control input-sm";
	el6.onkeyup="isNumberKey(event,'tolltax"+ iteration+"');";
	el6.maxLength="10";
	el6.size = 10;
	cellRight6.appendChild(el6);
	
	var cellRight7 = row.insertCell(11);
	var el7= document.createElement('input');
	el7.type = 'text';
	el7.name = 'noofbox' + iteration;
	el7.id = 'noofbox' + iteration;
	el7.className = "form-control input-sm";
	el7.onkeyup="isNumberKey(event,'noofbox"+ iteration+"');";
	el7.maxLength="10";
	el7.size = 10;
	cellRight7.appendChild(el7);
	
	datePickeInitialize();
	 
 }
	
	
function removToAttandanceRow() {
	var tbl = document.getElementById('attandanceTable');
	var lastRow = tbl.rows.length;
	if (lastRow > 2)
		tbl.deleteRow(lastRow - 1);
}
 
 function collectToAttandanceValue() {
		var tbl = document.getElementById('attandanceTable');
		var lastRow = tbl.rows.length;
		var iteration = lastRow - 1;
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
	 datepicker:false,
	  format:'H:i',
	  step:5  
	 });
$('#checkout0').datetimepicker({
	 datepicker:false,
	  format:'H:i',
	  step:5 
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
		 datepicker:false,
		  format:'H:i',
		  step:5  
		 });
	 $('#checkout'+i).datetimepicker({
		 datepicker:false,
		  format:'H:i',
		  step:5 
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
 
	</script>
</head>
 <body>
 <div class="container">  
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager">  <legend class="borderManager">Driver Attendance  <%List<DriverAttandance> savelists=(List<DriverAttandance>)request.getAttribute("savelists"); if(savelists!=null && savelists.size()>0){ %>  <b style="color: green;"><%=savelists.get(0).getStatusMessage() %></b><%} %></legend>
		 
			<form  action="atDrivers" method="post" class="form-inline" onsubmit="return validateForm();">
			 <div class="row" style="margin-top:-2%; ">                                        									                                  
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Organization Name<span style="color: red;">*</span></div>
							
							  <select name="orgName" id="orgName" onchange="driverList()" class="input-sm" style="width:100%;">
                                 <option value="">--Select Country--</option>
                                 <% List<OrganizationMasterRegistration>  list= (List<OrganizationMasterRegistration>)request.getAttribute("orgList");
									if(list != null && !list.isEmpty()){
									for(int i=0; i<list.size();i++){ 
									%>	
								 <option value="<%=list.get(i).getIdClientMaster() %>"><%=list.get(i).getName() %></option>
                                 <%	}}%>                        
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
						<div class="col-lg-12 col-md-12 col-sm-12" style="overflow-x:scroll">
						<table id="attandanceTable" cellpadding="3" cellspacing="3" >
							<tr><td>Attandance Date<span style="color: red;">*</span></td><td></td>
							<td>Check In Time <span style="color: red;">*</span></td><td></td>
							<td>Check Out Time<span style="color: red;">*</span></td><td></td>
							<td>Opening kilometer<span style="color: red;">*</span></td><td></td>
							<td>Closing kilometer <span style="color: red;">*</span></td><td></td>
							<td>Toll Tax Amount <span style="color: red;">*</span></td>
							<td>No Of Boxes <span style="color: red;">*</span></td>
							</tr>
							<tr><td><input type="text" name="attandanceDate0" id="attandanceDate0" class="form-control input-sm" size="22"></td>
							<td><img src="/trux/resources/images/calendar.png" id="imgs20" title="Please click on text field area after that  show the calander"></td>
							<td><input type="text" name="checkin0" id="checkin0" class="form-control input-sm" maxlength="5" size="10"></td>
							<td><img src="/trux/resources/images/calendar.png" id="imgs10" title="Please click on text field area after that  show the calander"></td>
							<td><input type="text" name="checkout0" id="checkout0" class="form-control input-sm" maxlength="5" size="10"></td>
							<td><img src="/trux/resources/images/calendar.png" id="imgs00" title="Please click on text field area after that  show the calander"></td>
						    <td><input type="text" name="openingkm0" id="openingkm0" class="form-control input-sm" maxlength="10" size="10"></td>
							<td><img src="/trux/resources/images/speedometer.jpg" width="30" height="30" id="imgs00" title="Please click on text field area after that  show the calander"></td>
						 	<td><input type="text" name="closingkm0" id="closingkm0" class="form-control input-sm" maxlength="10" size="10"></td>
						    <td><img src="/trux/resources/images/speedometer.jpg" width="30" height="30" id="imgs00" title="Please click on text field area after that  show the calander"></td>
						    <td><input type="text" name="tolltax0" id="tolltax0" class="form-control input-sm" maxlength="10" size="10"></td>
						    <td><input type="text" name="noofbox0" id="noofbox0" class="form-control input-sm" maxlength="10" size="10"></td>
						
						  </table>
							<input type="hidden" name="attandanceDate" id="attandanceDate">
							<input type="hidden" name="checkin" id="checkin">	
							<input type="hidden" name="checkout" id="checkout">	
							<input type="hidden" name="opening" id="opening">
							<input type="hidden" name="closing" id="closing">	
							<input type="hidden" name="tolltax" id="tolltax">
							<input type="hidden" name="noofboxes" id="noofboxes">							
							<input type="button" value="+" onclick="addToAttandanceRow();" class="btn btn-danger btn-sm btn_nav1" title="Add the to address and phone number!"/>
							<input type="button" value="-" onclick="removToAttandanceRow();" class="btn btn-danger btn-sm btn_nav1" title="Remove the to address and phone number! "/>
						    <input type="submit" class="btn btn-danger btn-sm btn_nav1" id="save"  value="Save Now"> 
							<input type="reset" class="btn btn-danger btn-sm btn_nav1" value="Reset"> 
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
 
	 