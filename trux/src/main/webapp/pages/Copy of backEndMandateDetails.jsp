<%@page import="com.trux.model.ClientMandateDetail"%>
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
<link href="/trux/resources/jtable/css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery.jtable.js" type="text/javascript"></script> 
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script> 
 

 <script src="/trux/resources/js/mask/jquery.masked-input.js"></script>

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
 	 var flag=false;   
 	// clientId clientSubId mandateType mandateStartDate mandateEndDate
 	 var clientId=document.getElementById("clientId").value; 
     var clientSubId = document.getElementById("clientSubId").value;
 	 var mandateType=document.getElementById("mandateType").value; 
 	 var mandateStartDate=document.getElementById("mandateStartDate").value; 
 	 var mandateEndDate=document.getElementById("mandateEndDate").value; 
 	  
	var message="";
	
 	if(clientId==""){
 	 	  document.getElementById('message').style.display = "block";
 	 	  document.getElementById('message').style.color="red";
 	 	  message+="<br/>";
 	 	  message+="Please select the Client name  !<br/>";
 	 	  document.getElementById("clientId").style.borderColor="red";
 	 	  document.getElementById('clientId').focus();
 	 	  flag= false;
 	 	  }else{
 	 	  document.getElementById("clientId").style.borderColor="green"; 
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
 	 	 if(mandateType==""){
 	 	   document.getElementById('message').style.display = "block";
 	 	   document.getElementById('message').style.color="red";
 	 	   message+="Please select the Mandate Type !<br/>";
 	 	   document.getElementById("mandateType").style.borderColor="red";
 	 	   document.getElementById('mandateType').focus();
 	 	   flag= false; 		 
 	 	   }else{
 			 document.getElementById("mandateType").style.borderColor="green"; 
 		   }
 	 	 if(mandateStartDate==""){
 	 	 	   document.getElementById('message').style.display = "block";
 	 	 	   document.getElementById('message').style.color="red";
 	 	 	   message+="Please select the Mandate Start Date !<br/>";
 	 	 	   document.getElementById("mandateStartDate").style.borderColor="red";
 	 	 	   document.getElementById('mandateStartDate').focus();
 	 	 	   flag= false; 		 
 	 	 	   }else{
 	 			 document.getElementById("mandateStartDate").style.borderColor="green"; 
 	 		   }
 	 	 if(mandateEndDate==""){
 	 	 	   document.getElementById('message').style.display = "block";
 	 	 	   document.getElementById('message').style.color="red";
 	 	 	   message+="Please select the Mandate End Date !<br/>";
 	 	 	   document.getElementById("mandateEndDate").style.borderColor="red";
 	 	 	   document.getElementById('mandateEndDate').focus();
 	 	 	   flag= false; 		 
 	 	 	   }else{
 	 			 document.getElementById("mandateEndDate").style.borderColor="green"; 
 	 		   }
 	 	if(message==""){flag=true;}
  return flag;
  }
   
//------------------------------------------//
 
	
 

function fillSubOrg()	
{   var flag=false; 
    var SelOrgValue = document.getElementById("clientId").value;
    var message="";
    
   if(SelOrgValue==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("assosiatedBy").style.borderColor="red";
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
	    	  document.getElementById("clientSubId").innerHTML = data;
	    	  document.getElementById("clientSubId").value.innerHTML=data;
	      }
	    });
    return true;
   }
   return false;
}

function showMandateType(){
	var mandateType=$("#mandateType").val();
	if(mandateType=="Fix"){
		$('#fix').show();
		 $('#boxs').hide();
		 $('#trips').hide();
		 $('#weights').hide();
		 
	}else if(mandateType=="Adhoc"){
		$('#fix').show();
		 $('#boxs').hide();
		 $('#trips').hide();
		 $('#weights').hide();
		 
	}else if(mandateType=="Box"){
		$('#fix').hide();
	    $('#boxs').show();
	    $('#trips').hide();
	    $('#weights').hide();
	}
	else if(mandateType=="Weight"){
		$('#fix').hide();
	    $('#boxs').hide();
	    $('#trips').hide();
	    $('#weights').show();
	    
	} else if(mandateType=="Trip"){
		$('#fix').hide();
		 $('#boxs').hide();
		 $('#trips').show();
		 $('#weights').hide();
	}else{
		$('#fix').hide();
		 $('#boxs').hide();
	}
	 	
}


function  getMandateType()	
{  var flag=false;  
	var sellOrgId = document.getElementById("clientId").value;
   var sellSubOrgId = document.getElementById("clientSubId").value;
   var message="";
   if(sellOrgId==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("clientId").style.borderColor="red";
	    message+="Please select client !<br/>";
	 	document.getElementById('clientId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("clientId").style.borderColor="green";
	 	}
   if(sellSubOrgId==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("clientSubId").style.borderColor="red";
	    message+="Please select sub-clients !<br/>";
	 	document.getElementById('clientSubId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("clientSubId").style.borderColor="green";
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
    $("#clientMandateId").val(data.clientMandateId);
    $("#mandateType").val(mandateType).attr('selected', 'selected');
   	if(mandateType=="Fix"){
		$('#fix').show();
		 $('#boxs').hide();
		 $('#trips').hide();
		 $('#weights').hide();
      }else if(mandateType=="Adhoc"){
		$('#fix').show();
		 $('#boxs').hide();
		 $('#trips').hide();
		 $('#weights').hide();
		 
	}else if(mandateType=="Box"){
		$('#fix').hide();
	    $('#boxs').show();
	    $('#trips').hide();
	    $('#weights').hide();
	}
	else if(mandateType=="Weight"){
		$('#fix').hide();
	    $('#boxs').hide();
	    $('#trips').hide();
	    $('#weights').show();
	    
	} else if(mandateType=="Trip"){
		$('#fix').hide();
		 $('#boxs').hide();
		 $('#trips').show();
		 $('#weights').hide();
	}else{
		$('#fix').hide();
		 $('#boxs').hide();
		 $('#trips').hide();
		 $('#weights').hide();
	}
   	}
    });
  }
return flag;
}
 </script>
</head>
 <body>
 <div class="container">  
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager table-responsive">  <legend class="borderManager" style="width: 100%">Mandate Details
    	  <a href="/trux/clients/updateMandateDetails" class="btn btn-danger btn-sm right-btn btn_nav1 right_btn_update" style="color: black;padding-right: 20px;padding-top: -2px;/* float: right; */"><b>Update Mandate</b></a>
    	  </legend>
		 <f:form commandName="clientMandateDetails" action="saveMandateDetails" method="post">  
		   <div class="row" style="margin-top:-2%; ">                                        									                                  
						<div class="col-lg-4 col-md-4 col-sm-12">
						<input type="hidden" name="clientMandateId" id="clientMandateId" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2;background-repeat: no-repeat; background-position:right; border-color: black;' placeholder="Enter No of Vehicles (Numeric)" >
						
							<div style="margin-bottom:6px;">Clients<span style="color: red;">*</span></div>
							
							  <f:select path="clientId" id="clientId" onchange="fillSubOrg()" cssClass="input-sm" cssStyle="width:100%;">
                                 <option value="">--Select Client (Alphabetical)--</option>
                                 <% List<OrganizationMasterRegistration>  list= (List<OrganizationMasterRegistration>)request.getAttribute("orgList");
									if(list != null && !list.isEmpty()){
									for(int i=0; i<list.size();i++){ 
									%>	
								 <option value="<%=list.get(i).getIdClientMaster() %>"><%=list.get(i).getName() %></option>
                                 <%	}}%>                        
                             </f:select>
                     	</div>             
                     	<div class="col-lg-4 col-md-4 col-sm-12">
                     	<div style="margin-bottom:6px;"> Sub-Clients <span style="color: red;">*</span></div>
						<select name="clientSubId" id="clientSubId" onchange="getMandateType();"   class="input-sm" style="width:100%;">
                            <option value="">--Select Sub-Client (Alphabetical)--</option>
                        </select>
					 	</div>
					 	<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Mandate Type<span style="color: red;">*</span></div>
							<select name="mandateType" id="mandateType" onchange="showMandateType()" class="input-sm" style="width:100%;">
							<option value="">--Select Mandat Type--</option>
							<option value="Fix">Fix</option>
							<option value="Box">Box</option>
							<option value="Weight">Weight</option>
							<option value="Trip">Trip</option>
							<option value="Adhoc">Adhoc</option>
							 </select>    
						</div>	
						<div class="col-lg-12 col-md-12 col-sm-12">
						<hr/></div>
					   <div id="fix" style="display: none;">
					    <div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
						<div class="col-lg-4 col-md-4 col-sm-12">
						<div style="margin-bottom:6px;">Vehicle type<span style="color: red;">*</span></div>
							<select name="fixVehicleType" id="fixVehicleType" class="input-sm" style="width:100%;border-color: black;">
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
						
						<div class="col-lg-4 col-md-4 col-sm-12">
							<div style="margin-bottom:6px;">Days/month<span style="color: red;">*</span></div>
							<input type="text" name="fixMandateDayDate" id="fixMandateDayDate" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2;background-repeat: no-repeat; background-position:right; border-color: black;' placeholder="No. Of Days (Numeric)" ="No. Of Days (Numeric)">
							
						</div>        
						
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
						<div class="col-lg-4 col-md-4 col-sm-12">
						<div style="margin-bottom:6px;">Number Of Vehicles<span style="color: red;">*</span></div>
						<input type="text" name="fixVehicleNumber" id="fixVehicleNumber" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2;background-repeat: no-repeat; background-position:right; border-color: black;' placeholder="Enter No of Vehicles (Numeric)" >
						</div>	
						
						<div class="col-lg-4 col-md-4 col-sm-12">
							<div style="margin-bottom:6px;">Km/month<span style="color: red;">*</span></div>
							<input type="text" name="fixMkPerMonth" id="fixMkPerMonth" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2;background-repeat: no-repeat; background-position:right; border-color: black;' placeholder="No. Of Km (Numeric)" >
					   </div> 
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
						<div class="col-lg-4 col-md-4 col-sm-12">
						<div style="margin-bottom:6px;">Invoice Amount<span style="color: red;">*</span></div>
						<input type="text" name="fixInvoiceAmount" id="fixInvoiceAmount" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2;background-repeat: no-repeat; background-position:right; border-color: black;' placeholder="Invoice Amount (Numeric)" >
						 </div>	
					 	<div class="col-lg-4 col-md-4 col-sm-12">
							<div style="margin-bottom:6px;">Hrs/day<span style="color: red;">*</span></div>
							<input type="text" name="fixHrsPerDay" id="fixHrsPerDay" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2; background-repeat: no-repeat; background-position:right; border-color: black;' placeholder="No. Of Hours (Numeric)" >
					 </div> 
						</div>
						<div id="boxs" style="display: none;">
						 
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
						<div class="col-lg-4 col-md-4 col-sm-12"> 
						<div style="margin-bottom:6px;">Invoice Amount<span style="color: red;">*</span></div>
						<input type="text" name="boxInvoiceAmount" id="boxInvoiceAmount" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2;background-repeat: no-repeat; background-position:right; border-color: black;' placeholder="Invoice Amount (Numeric)">
						 </div>
					  </div>       
					 <div id="weights" style="display: none;">
					    <div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
						<div class="col-lg-4 col-md-4 col-sm-12">
						<div style="margin-bottom:6px;">Vehicle type<span style="color: red;">*</span></div>
							<select name="weightVehicleType" id="weightVehicleType" class="input-sm" style="width:100%;border-color: black;">
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
						
					 <div class="col-lg-4 col-md-4 col-sm-12">
						<div style="margin-bottom:6px;">Invoice Amount<span style="color: red;">*</span></div>
						<input type="text" name="weightInvoiceAmount" id="weightInvoiceAmount" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2;background-repeat: no-repeat; background-position:right; border-color: black;' placeholder="Invoice Amount (Numeric)">
						 </div>	
						
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
						<div class="col-lg-4 col-md-4 col-sm-12">
						<div style="margin-bottom:6px;">Number Of Vehicles<span style="color: red;">*</span></div>
						<input type="text" name="weightVehicleNumber" id="weightVehicleNumber" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2;background-repeat: no-repeat; background-position:right; border-color: black;' placeholder="Enter No of Vehicles (Numeric)">
						</div>	
					 </div>
					 <div id="trips" style="display: none;">
						
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
						<div class="col-lg-4 col-md-4 col-sm-12">
						<div style="margin-bottom:6px;">Trip Start Km/day<span style="color: red;">*</span></div>
						<input type="text" name="tripStartKmPerday" id="tripStartKmPerday" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2;background-repeat: no-repeat; background-position:right; border-color: black;' placeholder="Trip Start No. Of Km (Numeric)" >
						 </div>	
						 <div class="col-lg-4 col-md-4 col-sm-12">
						<div style="margin-bottom:6px;">Invoice Amount<span style="color: red;">*</span></div>
						<input type="text" name="tripInvoiceAmount" id="tripInvoiceAmount" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2;background-repeat: no-repeat; background-position:right; border-color: black;' placeholder="Invoice Amount (Numeric)" >
						 </div>
						 <div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
					    <div class="col-lg-4 col-md-4 col-sm-12">
							<div style="margin-bottom:6px;">Trip End Km/day<span style="color: red;">*</span></div>
							<input type="text" name="tripEndKmPerday" id="tripEndKmPerday" class="form-control input-sm"style='width:100%;box-shadow: 1px 1px #898787;border: 1px solid #A2A2A2; background-repeat: no-repeat; background-position:right; border-color: black;' placeholder="Trip End No. Of Km (Numeric)" >
					 	</div> 
					 </div>                       									                                  
					 	<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
				 	
					<div class="col-lg-2 col-md-2 col-sm-12" style="width: 10%">
					<div style="margin-bottom:6px;">  &nbsp; &nbsp; &nbsp; <span style="color: red;"> &nbsp; &nbsp;</span></div>
					 <input type="submit" class="btn btn-danger btn-sm btn_nav1" id="save"  value="Save Now">  
					</div> 
					<div class="col-lg-2 col-md-2 col-sm-12" >
					<div style="margin-bottom:6px;">  &nbsp; &nbsp; &nbsp; <span style="color: red;"> &nbsp; &nbsp;</span></div>
					 <input type="reset" class="btn btn-danger btn-sm btn_nav1" id="addnewMandate"   value="Reset">  
					</div>   
					</div>
					</f:form>
					<%ClientMandateDetail saveBack=(ClientMandateDetail) request.getAttribute("saveBack"); 
					if(saveBack!=null){%>
	                 <div style="margin-bottom:6px;">  &nbsp; &nbsp; &nbsp; <span style="color:green;"><%=saveBack.getMandateDetailId() %> Saved successfully !</span></div>
					 	</div>
					 	<%} %>
				 </fieldset>
				 </div> 
<div class="clearfix margin_05"></div>
<div class="clearfix margin_10"></div>
 
</div>          
  </div>
				 
  </body>
</html>
 
	 