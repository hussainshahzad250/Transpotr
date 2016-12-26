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
 
 <!-- <script
src="http://maps.googleapis.com/maps/api/js">
</script> -->
 

 <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
 <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>
 

<style type="text/css">
tbody tr td:first-child, table#attandanceTable tbody tr td:last-child { padding: 3px 0px }
input[type="text"] { -webkit-appearance: text; }
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

<title>Client Mandate Details</title>

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
 
 function float_validation(event, value){
	    if(event.which < 45 || event.which > 58 || event.which == 47 ) {
	          return false;
	            event.preventDefault();
	        } // prevent if not number/dot

	        if(event.which == 46 && value.indexOf('.') != -1) {
	            return false;
	            event.preventDefault();
	        } // prevent if already dot

	            if(event.which == 45 && value.indexOf('-') != -1) {
	                return false;
	            event.preventDefault();
	        } // prevent if already dot

	        if(event.which == 45 && value.length>0) {
	            event.preventDefault();
	        } // prevent if already -

	    return true;

	};
	 
 function validateForm(){
 	 var flag=false;   
 	// clientId clientSubId mandateType mandateStartDate mandateEndDate
 	 var clientId=document.getElementById("clientId").value; 
     var clientSubId = document.getElementById("clientSubId").value;
 	 var mandateType=document.getElementById("mandateType").value;  
 	  
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
	 	document.getElementById("clientId").style.borderColor="red";
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

//////////////////Trip/////////////////////
 function mandateTypeSelection() {
    var manTypeValueVal = $("#mandateType").val();
     if(manTypeValueVal=="Per Trip"){
    	$("#tripCantainer").hide();
    	$("#SearchWithinLBW").hide();
    	$("#SearchWithinTrip").show();
        $("#mandateInTableContainer").hide();
     }else{
    	 $("#tripCantainer").hide();
    	 $("#mandateInTableContainer").show();
         $("#SearchWithinLBW").show();
     	 $("#SearchWithinTrip").hide();
    }
}
 // search by mondayChange
 
 function searchMandateTypeIsExist(){
	   var flag=false; 
	    var SelOrgValue = document.getElementById("clientId").value;
	    var SelClientSubId=document.getElementById("clientSubId").value;
	    var SelMandateType=document.getElementById("mandateType").value;
	    var message="";
	    
	   if(SelOrgValue==""){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("clientId").style.borderColor="red";
		    message+="Please select the client !<br/>";
		 	document.getElementById('clientId').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("clientId").style.borderColor="green";
		 	}
	   if(SelClientSubId==""){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("clientSubId").style.borderColor="red";
		    message+="Please select the sub-client !<br/>";
		 	document.getElementById('clientSubId').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("clientSubId").style.borderColor="green";
		 	}
	   if(SelMandateType==""){
		 	document.getElementById("message").style.color="red";
		 	document.getElementById('message').style.display = "block";
		 	document.getElementById("mandateType").style.borderColor="red";
		    message+="Please select the mandate type !<br/>";
		 	document.getElementById('mandateType').focus();
		 	flag= false;
		 	}else{
		 	document.getElementById("mandateType").style.borderColor="green";
		 	}
	   if(message==""){flag= true;}
	    
	   if(flag==true){
		   
	 $.ajax({
	       type: "GET",
	       url: "/trux/clients/searchDetailsByMandateType",
	       data:{
	    	   clientId:SelOrgValue,
	    	   clientSubId:SelClientSubId,
	    	   mandateType:SelMandateType
	 		  } ,
	       success: function(data) {
	    	   if(data=='' || !data)
  				  {
	    		   $("#tripCantainer").hide();
  				  } else{
  				var ii=0;
  				var i=1;
  				$("#tripCantainer").show();
  				$('#tripCantainer').html("");
	    	     $.each(data, function( index, value ) {
	    	    	ii++; 
	    	    	
	    	    var mandateDetailId=value.mandateDetailId;
	    	    var mandateId= value.mandateId;
	    	    var vehicleType= value.vehicleType ;
	    	    var bodyType=value.bodyType;
	    	    var totalVehicle=value.totalVehicle;
	    	    var fixDays=value.fixDays;
	    	    var fixKm=value.fixKm;
	    	    var fixHour=value.fixHour;
	    	    var moq=value.moq;
	    	    var nightHoldingCharge=value.nightHoldingCharge;;
	    	    var extraKmCharge=value.extraKmCharge;
	    	    var extraHourCharge=value.extraHourCharge;
	    	    var billingRate=value.billingRate ;
	    	    var message="<div id='message"+ii+"' style='color:green'></div>";
	  			   
	    	    var data="<div><table style='width: 100%;' id='mandateTable"+ii+"' class='col-lg-12 col-md-6 col-sm-12'><div class='col-lg-2 col-md-2 col-sm-12' style='float: left;margin: 0px -13px;'> <b class='input-sm'>"+vehicleType+"</b> </div>	 <div class='col-lg-2 col-md-2 col-sm-12' style='float: left;margin: 0px -13px;'><b class='input-sm'>"+bodyType+"</b> </div> <div class='clearfix margin_05'></div></div><tbody id='mandateTBody"+ii+"'><tr> <td> <div class='col-lg-2 col-md-2 col-sm-12' style='float: left;margin: 0px -13px;'><input type='text' name='locationA' id='locationA"+ii+i+"'  class='form-control input-sm' style='width:100%;' placeholder='Enter Point A' /> </div> <div class='col-lg-2 col-md-2 col-sm-12' style='float: left;margin: 0px -13px;'><input type='text' name='locationB' id='locationB"+ii+i+"' class='form-control input-sm' style='width:100%;' placeholder='Enter Point B' /> </div> <div class='col-lg-2 col-md-2 col-sm-12' style='float: left;margin: 0px -13px;'><input type='text' name='billingRate' id='billingRate"+ii+i+"' onkeypress='validateDecimal(billingRate"+ii+i+");' class='form-control input-sm allownumericwithdecimal' style='width:100%;' placeholder='Enter Billing Rate' /></div><div class='col-lg-2 col-md-2 col-sm-12' style='float: left;margin: 0px -13px;'><input type='text' name='nightHoldingCharge' id='nightHoldingCharge"+ii+i+"'  onkeypress='validateDecimal(nightHoldingCharge"+ii+i+");' class='form-control input-sm allownumericwithdecimal' style='width:100%;' placeholder='Night Holding Charges' /></div><div class='col-lg-2 col-md-2 col-sm-12'  style='float: left;margin: 0px -13px;' ><input type='button' onclick='addRow(mandateTBody"+ii+","+ii+"), initialize(mandateTBody"+ii+","+ii+");' value='+' class='btn btn-danger btn-sm btn_nav1'>&nbsp;<input type='button' class='btn btn-danger btn-sm btn_nav1' onclick='removToVehiceDetailsRow(mandateTBody"+ii+","+ii+");' value='-'>&nbsp;<input type='button' id='save"+ii+"' class='btn btn-danger btn-sm btn_nav1' onclick='collectMandateDetailsLocationValue(mandateTBody"+ii+","+ii+");' value='Save'></div>"+message+"</td></tr></tbody></table> <input type='hidden' name='locationA' id='locationA"+ii+"' ><input type='hidden' name='locationB' id='locationB"+ii+"' > <input type='hidden' name='billingRate' id='billingRate"+ii+"' > <input type='hidden' name='nightHoldingCharge' id='nightHoldingCharge"+ii+"' ><input type='hidden' name='mandateDetailId' id='mandateDetailId"+ii+"' value="+mandateDetailId+"></div>";
			     $('#tripCantainer').append(data);
			     $("#tripCantainer").css({ borderColor: "green" });
			     try{
			      initializes(ii,i);
			     }catch(Exception ){}
	    	   });
  				  }
	       }
	     });
	   }
 }
function addRow(ids,no){
	 var ii= $(ids).children().length;
	   var data="<tr><td>";
	   
	    ii++;
	    if(ii==2){
	    	data +="<div class='col-lg-2 col-md-2 col-sm-12' style='float: left;margin: 0px -13px;'><input type='text' name='locationA' id='locationA"+no+ii+"' class='form-control input-sm' style='width:100%;' placeholder='Enter Point A' /> </div> <div class='col-lg-2 col-md-2 col-sm-12' style='float: left;margin: 0px -13px;'><input type='text' name='locationB' id='locationB"+no+ii+"' class='form-control input-sm' style='width:100%;' placeholder='Enter Point B' /> </div> <div class='col-lg-2 col-md-2 col-sm-12' style='float: left;margin: 0px -13px;'><input type='text' name='billingRate' id='billingRate"+no+ii+"' onkeypress='validateDecimal(billingRate"+no+ii+");'  class='form-control input-sm allownumericwithdecimal' style='width:100%;' placeholder='Enter Billing Rate' /></div><div class='col-lg-2 col-md-2 col-sm-12' style='float: left;margin: 0px -13px;'><input type='text' name='nightHoldingCharge' id='nightHoldingCharge"+no+ii+"' onkeypress='validateDecimal(nightHoldingCharge"+no+ii+");' class='form-control input-sm' style='width:100%;' placeholder='Night Holding Charges' /></div></td></tr>";
	    }else
	    data +="<div class='col-lg-2 col-md-2 col-sm-12' style='float: left;margin: 0px -13px;'><input type='text' name='locationA' id='locationA"+no+ii+"' class='form-control input-sm' style='width:100%;' placeholder='Enter Point A' /> </div> <div class='col-lg-2 col-md-2 col-sm-12' style='float: left;margin: 0px -13px;'><input type='text' name='locationB' id='locationB"+no+ii+"' class='form-control input-sm' style='width:100%;' placeholder='Enter Point B' /> </div> <div class='col-lg-2 col-md-2 col-sm-12' style='float: left;margin: 0px -13px;'><input type='text' name='billingRate' id='billingRate"+no+ii+"' onkeypress='validateDecimal(billingRate"+no+ii+");'  class='form-control input-sm allownumericwithdecimal' style='width:100%;' placeholder='Enter Billing Rate' /></div><div class='col-lg-2 col-md-2 col-sm-12' style='float: left;margin: 0px -13px;'><input type='text' name='nightHoldingCharge' id='nightHoldingCharge"+no+ii+"' onkeypress='validateDecimal(nightHoldingCharge"+no+ii+");' class='form-control input-sm' style='width:100%;' placeholder='Night Holding Charges' /></div></td></tr>";
	    $(ids).append(data);
	    initializes(no,ii);
	}
	 //---------------------------------------//
		 
		
		
	function removToVehiceDetailsRow(ids) {
		var tbl = ids;
		var lastRow = tbl.rows.length;
		if (lastRow > 1)
			tbl.deleteRow(lastRow - 1);
	}
	 
	 /// validate
	  function validateMandateDetailsLocationValue(ids,no) {
		  var flag=false;
			var tbl = ids;
			var lastRow = tbl.rows.length;
			var iteration = lastRow;
		 
			var locationA = new Array(); 
			var locationB = new Array(); 
			var billingRate = new Array(); 
			var nightHoldingCharge = new Array(); 
			 
			var idName = new Array("","locationA"+no,"locationB"+no,"billingRate"+no,"nightHoldingCharge"+no);
			var locationAV="";
			var locationBV="";
			var billingRateV=0;
            var nightHoldingChargeV=0;
			  var ii=1;
		 	for ( var i = 0; i < iteration; i++) {	
		 		var locationAVInner="";
				var locationBVInner="";
				var billingRateVInner=0;
	            var nightHoldingChargeVInner=0;
			 	for ( var id = 1; id < idName.length; id++) {
					var idss=idName[id]+ii;
				    var val=$("#"+idss).val();
				    if(val==""){ 
				    	 $("#"+idss).css({ borderColor: "red" });
					 	 $("#"+idss).focus();
					 	flag=false;
			 	     }else{
			 	    	 $("#"+idss).css({ borderColor: "green" }); 
			 	    	flag=true;
			 	     }
				     
				}
			 	  ii++;
		     } 
		   
		 return  flag;
			
		}
	 
	 //

	 function collectMandateDetailsLocationValue(ids,no) {
	var flags= validateMandateDetailsLocationValue(ids,no);
	var flag=false;
	if(flags==true){
			var tbl = ids;
			var lastRow = tbl.rows.length;
			var iteration = lastRow;
		 
			var locationA = new Array(); 
			var locationB = new Array(); 
			var billingRate = new Array(); 
			var nightHoldingCharge = new Array(); 
			 
			var idName = new Array("","locationA"+no,"locationB"+no,"billingRate"+no,"nightHoldingCharge"+no);
			var locationAV="";
			var locationBV="";
			var billingRateV=0;
            var nightHoldingChargeV=0;
			  var ii=1;
		 	for ( var i = 0; i < iteration; i++) {	
		 		var locationAVInner="";
				var locationBVInner="";
				var billingRateVInner=0;
	            var nightHoldingChargeVInner=0;
			 	for ( var id = 1; id < idName.length; id++) {
					var idss=idName[id]+ii;
				    if(id==1){ 
				    	locationAVInner =$("#"+idss).val();
			 	     }
				     if(id==2){
				    	 locationBVInner = $("#"+idss).val();
					     }
				     if(id==3){
				    	 billingRateVInner = $("#"+idss).val();
					     }
				     if(id==4){
				    	 nightHoldingChargeVInner = $("#"+idss).val();
					     }
				 	if (id == (idName.length)-1) {
				 		  locationAV=locationAVInner;
						  locationBV=locationBVInner;
						  billingRateV=billingRateVInner;
			              nightHoldingChargeV=nightHoldingChargeVInner;
				 	 
				 	} else {
				 		 locationAV=locationAVInner;
						  locationBV=locationBVInner;
						  billingRateV=billingRateVInner;
			              nightHoldingChargeV=nightHoldingChargeVInner;
				  }
				}
			 	  locationA.push(locationAV.replace(",", "~").replace(",", "~").replace(",", "~")); 
				  locationB.push(locationBV.replace(",", "~").replace(",", "~").replace(",", "~"));  
				  billingRate.push(billingRateV);  
				  nightHoldingCharge.push(nightHoldingChargeV); 
				  ii++;
		     } 
		 	
		    var id=$("#mandateDetailId"+no).val();
			$("#locationA"+no).val(locationA);
			$("#locationB"+no).val(locationB);   
			$("#billingRate"+no).val(billingRate);			
			$("#nightHoldingCharge"+no).val(nightHoldingCharge); 
			//alert("Mandate Id "+id+"   "+locationA +"  "+locationB+"      "+billingRate +"       "+nightHoldingCharge );  
			 locationA = new Array(); 
			  locationB = new Array(); 
			  billingRate = new Array(); 
			  nightHoldingCharge = new Array(); 
			  
			  flag=true;
	    }	
	
	///
	if(flag==true){
	    var SelMandateDetailId=$("#mandateDetailId"+no).val();
		var SelLocationA=	$("#locationA"+no).val();
		var SellocationB=	$("#locationB"+no).val();   
		var SelBillingRate=	$("#billingRate"+no).val();			
		var SelNightHoldingCharge=	$("#nightHoldingCharge"+no).val(); 
	   var dataString='mandateDetailId='+SelMandateDetailId+'&locationA='+SelLocationA+'&locationB='+SellocationB+'&billingRate='+SelBillingRate+"&nightHoldingCharge="+SelNightHoldingCharge;
	   	  $.ajax({
	  			  type: "GET",
	  			  url: "/trux/clients/saveMadateTripDetails",
	  			 data: dataString,
	  			  cache: false,
	  			  success: function(result){
	  				  if(result=='' || !result)
	  				  { 
	  				  } 
	  				 var data="";
	  				 $.each(result, function( index, value ) {
	  					//$("#save"+no).hide();
	  					$("#message"+no).html("Save successfully");
	  					
	  				 /*  $("#booking").show();
                     $("#bookingOrderReports").show();
                     if(value.bookingId!=null){
                         data +='<tr><td>'+value.bookingId+'</td><td>'+value.clientName+'</td><td>'+value.driverName+'</td></tr>';
                     } */ }); 
	  	//$('#bookedReportsTable').append(data); 
	  	  }
	    });
	///
	}
	 return  "";
		}
	 
	 
	 
	 function validateVehicleType(ids,index) {
		 
		 var options="<option value=''>--Select Vehicle type</option><option value='Mahindra Champion'>Champion</option><option value='Maruti Eeco'>Eeco</option><option value='Tata Ace'>Tata Ace</option><option value='Tata 407'>Tata 407 (10 Ft)</option><option value='Tata 709'>Tata 709 (14 Ft)</option><option value='Bolero Pickup'>Bolero Pickup</option><option value='17 Feet Single Axle'>17 Feet - Single Axle</option><option value='17 Feet Double Axle'>17 Feet - Double Axle</option><option value='19 Feet Single Axle'>19 Feet - Single Axle</option><option value='19 Feet Double Axle'>19 Feet - Double Axle</option><option value='19 Feet Multi-Axle'>19 Feet - Multi-Axle</option><option value='22 Feet Single Axle'>22 Feet - Single Axle</option><option value='22 Feet Double Axle'>22 Feet - Double Axle</option><option value='22 Feet Multi-Axle'>22 Feet - Multi-Axle</option><option value='24 Feet Single Axle'>24 Feet - Single Axle</option><option value='24 Feet Double Axle'>24 Feet - Double Axle</option><option value='24 Feet Multi-Axle'>24 Feet - Multi-Axle</option><option value='28 Feet Single Axle'>28 Feet - Single Axle</option><option value='28 Feet Double Axle'>28 Feet - Double Axle</option><option value='28 Feet Multi-Axle'>28 Feet - Multi-Axle</option><option value='32 Feet Single Axle'>32 Feet - Single Axle</option><option value='32 Feet Double Axle'>32 Feet - Double Axle</option><option value='32 Feet Multi-Axle'>32 Feet - Multi-Axle</option><option value='40 Feet Double Axle'>40 Feet - Double Axle</option><option value='40 Feet Multi-Axle'>40 Feet - Multi-Axle</option>";
		 
			var tbl = document.getElementById('mandateTable');
			var lastRow = tbl.rows.length;
			var iteration = lastRow;
			var idName = new Array("","vehicleType"); 
			var vehicleTypeValue = $("#"+ids).val();;  
			var flag=false;
			var innerflag=false;
			var oppositfCheckflag=false;
			for(var i=1;i<iteration;i++){
				oppositfCheckflag=false;
			if(index!=$.trim(i)){
				oppositfCheckflag=true;
			}}
		 	for ( var i = 0; i < iteration; i++) {	 
				var vehicleTypeInnerValue = ""; 
		 	 	for ( var id = 1; id < idName.length; id++) {
		 	 	    if(id==1){ 
				    	vehicleTypeInnerValue =$("#"+idName[id]+i).val();
			 	     }
		 	 	  if(index!=i){
		 	 		 if($.trim(vehicleTypeValue)==$.trim(vehicleTypeInnerValue)){
		 	 			 $("#"+ids).css({ borderColor: "red" });
					 	 $("#"+ids).focus();
					 	alert(" Already exist " +vehicleTypeValue);
					 	$("#"+ids).html(options);
						    }else{
						    	 $("#"+ids).css({ borderColor: "green" });
						    }
			 	 	  } 
			 } 
		     } 
		 	for(var i=iteration;i>0;i--){ 	 
				var vehicleTypeInnerValue = ""; 
		 	 	for ( var id = 1; id < idName.length; id++) {
		 	 	    if(id==1){ 
				    	vehicleTypeInnerValue =$("#"+idName[id]+i).val();
			 	     }
		 	 	   if(index!=i){
		 	 		 if($.trim(vehicleTypeValue)==$.trim(vehicleTypeInnerValue)){
		 	 			 $("#"+ids).css({ borderColor: "red" });
					 	 $("#"+ids).focus();
					 //	alert(" Already exist " +vehicleTypeValue);
					 	$("#"+ids).html(options);
					    }else{
					    	 $("#"+ids).css({ borderColor: "green" }); 
				 	 	  }
		 	 	  }  
		 	 	  
				 } 
		     }
		  return  vehicleType;
			
	 }
	 
	 /////////////end trip/////////////////
	 
	 
	 //Validate Decimal Value
 function validateDecimal(id) {
    $(id).val($(id).val().replace(/[^0-9\.]/g,''));
            if ((event.which != 46 || $(id).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
                event.preventDefault();
            }
        
} 
 </script>
</head>

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
			google.maps.event.addListener(new google.maps.places.Autocomplete((document.getElementById('locationA'+no+i)),{ types: ['(cities)'] }), 'place_changed', function() {
			fillInAddress(new google.maps.places.Autocomplete((document.getElementById('locationA'+no+i)),{ types: ['(cities)'] }));
			});  
			google.maps.event.addListener(new google.maps.places.Autocomplete((document.getElementById('locationB'+no+i)),{ types: ['(cities)'] }), 'place_changed', function() {
			fillInAddress(new google.maps.places.Autocomplete((document.getElementById('locationB'+no+i)),{ types: ['(cities)'] }));
			});
		 	}  
		 }
			
			
			function initializes(ids,no) { 
			 
			   google.maps.event.addListener(new google.maps.places.Autocomplete((document.getElementById('locationA'+ids+no)),{ types: ['(cities)'] }), 'place_changed', function() {
				fillInAddress(new google.maps.places.Autocomplete((document.getElementById('locationA'+ids+no)),{ types: ['(cities)'] }));
				});  
				google.maps.event.addListener(new google.maps.places.Autocomplete((document.getElementById('locationB'+ids+no)),{ types: ['(cities)'] }), 'place_changed', function() {
				fillInAddress(new google.maps.places.Autocomplete((document.getElementById('locationB'+ids+no)),{ types: ['(cities)'] }));
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
 <body>
 <div class="container">  
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager table-responsive">  <legend class="borderManager">Mandate Details </legend>
		 <form  action="searchToUodateMandateDetails" method="post">  
		   <div class="row" style="margin-top:-2%; ">                                        									                                  
						<div class="col-lg-4 col-md-4 col-sm-12">
							<div style="margin-bottom:6px;">Clients<span style="color: red;">*</span></div>
							
							  <select name="clientId" id="clientId" onchange="fillSubOrg()" class="input-sm" style="width:100%;">
                                 <option value="">--Select Client (Alphabetical)--</option>
                                 <% List<OrganizationMasterRegistration>  list= (List<OrganizationMasterRegistration>)request.getAttribute("orgList");
									if(list != null && !list.isEmpty()){
									for(int i=0; i<list.size();i++){ 
									%>	
								 <option value="<%=list.get(i).getIdClientMaster() %>"><%=list.get(i).getName() %></option>
                                 <%	}}%>                        
                             </select>
                     	</div>             
                     	<div class="col-lg-4 col-md-4 col-sm-12">
                     	<div style="margin-bottom:6px;"> Sub-Clients <span style="color: red;">*</span></div>
						<select name="clientSubId" id="clientSubId"    class="input-sm" style="width:100%;">
                            <option value="">--Select Sub-Client (Alphabetical)--</option>
                        </select>
					 	</div>
					 	
					 	<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Mandate Type<span style="color: red;">*</span></div>
							<select name="mandateType" id="mandateType"  class="input-sm" style="width:100%;" onchange="mandateTypeSelection();" required>
							<option value="">--Select Mandat Type--</option> 
							<option value="Leased">Leased</option>
							<option value="Box">Box</option>
							<option value="Weight">Weight</option>
							<option value="Per Trip">Per Trip</option>
							<option value="Per Km">Per Km</option>
							<option value="Per Day">Per Day</option>
							<option value="Per Hour">Per Hour</option>
						 </select>   
						<script type="text/javascript">
						   $(document).ready(function() {
						  	var selClientId="<%=request.getAttribute("clientId")%>";		 
					  		var selClientSubId="<%=request.getAttribute("clientSubId")%>";
					  		var mandateType="<%=request.getAttribute("mandateType")%>";
					  	    $("#clientId option[value="+selClientId+"]").attr("selected", true);
					  		$("#clientSubId option[value="+selClientSubId+"]").attr("selected", true);
					  		$("#mandateType option[value='"+mandateType+"']").attr("selected", true);
					  		 
					  		fillSubOrg();
   						}); 
   				        </script> 
						</div>
					
					<div class="col-lg-2 col-md-2 col-sm-12" style="width: 10%">
					<div style="margin-bottom:6px;">  &nbsp; &nbsp; &nbsp; <span style="color: red;"> &nbsp; &nbsp;</span></div>
					 <input type="submit" id="SearchWithinLBW" class="btn btn-danger btn-sm btn_nav1"  value="Search Now">  
					 <input type="button" id="SearchWithinTrip"  class="btn btn-danger btn-sm btn_nav1" onclick="searchMandateTypeIsExist();" value="Search Now" style="display: none;">  
					
					</div>    
					</form>
			         
					</div>  
					</fieldset>
				 </div> 
			<div class="clearfix margin_05"></div>
        <div class="clearfix margin_10"></div>
 
		<div class="container">  
        <div class="row">
            <fieldset class="fieldset2 borderManager table-responsive"> 
              <div	style="width: 100%; margin-right: 0%; margin-left: 0%; text-align: left;">       
            <div id="mandateInTableContainer" ></div>
            <div class="row" id=tripCantainer style="display: none;margin-top:-2%;box-shadow: 1px 1px 7px #6A6A6A;padding: 10px;border-radius: 6px; ">  
			 </div> <br/>
             <!--  <div id="googleMap" style="width:auto;height:200px;"></div>
             </div> -->
             </fieldset>   
        </div>
       
        </div>
   </div> 
<div id="message" style="color: red;"></div>  
			
		        

<script type="text/javascript">  
		var mandateType=""+'<%=request.getAttribute("mandateType")%>';
  		
		if(mandateType=="Per Trip"){
			
		}else{
	$(document).ready(function() {
		 $('.ui-button-text').click(function() {
			$('#mandateInTableContainer').hide();
		 });
		 var mandateTypes=$("#mandateType").val();  
	 	var selClientId=""+'<%=request.getAttribute("clientId")%>';		 
  		var selClientSubId=""+'<%=request.getAttribute("clientSubId")%>';
  		
  		if(selClientId=="null"){
  			$('#mandateInTableContainer').hide();
  		}
  		
  		var url;
		var titles="All Mandate Details "
		 var updateUrl;
		if(selClientId!="" && !selClientSubId==''){
			url='/trux/clients/searchMandateDetailsReports?action=list&clientId='+selClientId+'&clientSubId='+selClientSubId+"&mandateType="+mandateType;
		}  
		updateUrl='/trux/clients/searchMandateDetailsReports?action=update&mandateType='+mandateType;
	 
		if(mandateTypes=="Leased"){
		$('#mandateInTableContainer').jtable({
			paging: true,  
            pageSize: 15,           
            actions: {
              listAction:url, 
              updateAction: updateUrl, 
            },  
			fields : {
			 mandateDetailId : {key: true,create: false,edit: false,list: false},
			 mandateId : {key: true,create: false,edit: false,list: false},
			 vehicleType : {title : 'Vehicle Type',width : '10%',edit : false},
			 bodyType : {title : 'Body Type',width : '10%',edit : false},
			 totalVehicle : {title : 'No Of Vehicle',width : '10%',edit : false},
			 fixHour : {title : 'No. Of Hours',width: '10%',edit: true},
			 fixDays : {title : 'Days Per Month', width: '10%',edit: true} ,
			 fixKm: {title: 'No. Of Km',width: '10%',edit: true}, 
			 billingRate: {title: 'Billing Rate ',width: '10%',edit: true},
			 extraKmCharge: {title: 'Extra Km Charge', width: '15%',edit: true},
			 extraHourCharge: {title: 'Extra Hour Charge', width: '15%',edit: true}
			 }
		});
		$('#mandateInTableContainer').jtable('load');
		}
		if(mandateTypes=="Per Km"){
			$('#mandateInTableContainer').jtable({
				paging: true,  
	            pageSize: 15,           
	            actions: {
	              listAction:url, 
	              updateAction: updateUrl, 
	            },  
				fields : {
				 mandateDetailId : {key: true,create: false,edit: false,list: false},
				 mandateId : {key: true,create: false,edit: false,list: false},
				 vehicleType : {title : 'Vehicle Type',width : '10%',edit : false},
				 bodyType : {title : 'Body Type',width : '10%',edit : false},
				 totalVehicle : {title : 'No Of Vehicle',width : '10%',edit : false},  
				 billingRate: {title: 'Per KM Rate ',width: '10%',edit: true}, 
				 nightHoldingCharge: {title: 'Night Holding	 Charges', width: '25%',edit: true}
				 }
			});
			$('#mandateInTableContainer').jtable('load');
			}
		if(mandateTypes=="Per Day"){
			$('#mandateInTableContainer').jtable({
				paging: true,  
	            pageSize: 15,           
	            actions: {
	              listAction:url, 
	              updateAction: updateUrl, 
	            },  
				fields : {
				 mandateDetailId : {key: true,create: false,edit: false,list: false},
				 mandateId : {key: true,create: false,edit: false,list: false},
				 vehicleType : {title : 'Vehicle Type',width : '10%',edit : false},
				 bodyType : {title : 'Body Type',width : '10%',edit : false},
				 totalVehicle : {title : 'No Of Vehicle',width : '10%',edit : false},  
				 billingRate: {title: 'Per KM Rate ',width: '10%',edit: true}, 
				 nightHoldingCharge: {title: 'Night Holding	 Charges', width: '25%',edit: true}
				 }
			});
			$('#mandateInTableContainer').jtable('load');
			}
		if(mandateTypes=="Per Hour"){
			$('#mandateInTableContainer').jtable({
				paging: true,  
	            pageSize: 15,           
	            actions: {
	              listAction:url, 
	              updateAction: updateUrl, 
	            },  
				fields : {
				 mandateDetailId : {key: true,create: false,edit: false,list: false},
				 mandateId : {key: true,create: false,edit: false,list: false},
				 vehicleType : {title : 'Vehicle Type',width : '10%',edit : false},
				 bodyType : {title : 'Body Type',width : '10%',edit : false},
				 totalVehicle : {title : 'No Of Vehicle',width : '10%',edit : false},  
				 billingRate: {title: 'Per KM Rate ',width: '10%',edit: true}, 
				 nightHoldingCharge: {title: 'Night Holding	 Charges', width: '25%',edit: true}
				 }
			});
			$('#mandateInTableContainer').jtable('load');
			}
		if(mandateTypes=="Box"){
			$('#mandateInTableContainer').jtable({
				paging: true,  
	            pageSize: 15,           
	            actions: {
	              listAction:url, 
	              updateAction: updateUrl, 
	           },   
				fields : {
				 mandateDetailId : {key: true,create: false,edit: false,list: false},
				 mandateId : {key: true,create: false,edit: false,list: false},
				 vehicleType : {title : 'Vehicle Type',width : '15%',edit : false},
				 bodyType : {title : 'Body Type',width : '15%',edit : false},
				 totalVehicle : {title : 'No Of Vehicle',width : '15%',edit : false},
				 moq: {title: 'MOQ',width: '10%',edit: true},
				 billingRate: {title: 'Billing Rate ',width: '15%',edit: true},
				 nightHoldingCharge: {title: 'Night Holding Charge', width: '25%',edit: true}
				 }
			});
			$('#mandateInTableContainer').jtable('load');
			}if(mandateTypes=="Weight"){
				$('#mandateInTableContainer').jtable({
					paging: true,  
		            pageSize: 15,           
		            actions: {
		              listAction:url, 
		              updateAction: updateUrl, 
		            },
					fields : {
					 mandateDetailId : {key: true,create: false,edit: false,list: false},
					 mandateId : {key: true,create: false,edit: false,list: false},
					 vehicleType : {title : 'Vehicle Type',width : '15%',edit : false},
					 bodyType : {title : 'Body Type',width : '15%',edit : false},
					 totalVehicle : {title : 'No Of Vehicle',width : '15%',edit : false}, 
					 moq: {title: 'MOQ',width: '10%',edit: true},
					 billingRate: {title: 'Billing Rate ',width: '15%',edit: true},
                     nightHoldingCharge: {title: 'Night Holding Charge', width: '15%',edit: true}
					 }
				});
				$('#mandateInTableContainer').jtable('load');
				}
	
		
	});
		}
</script>
	
   

	
<script type="text/javascript">
$(function() {
	var dateToday = new Date();
	var dd = dateToday.getDate();
	var mm = dateToday.getMonth()+1; 
	var yyyy = dateToday.getFullYear();
	var toYears=parseInt(yyyy);
	 
	 
	 $('#txtStartDate, #txtEndDate').datetimepicker({
		 showOn: 'button',
		 buttonImage: "/trux/resources/images/calendar.png",
         buttonImageOnly: true,
		 timeFormat: 'HH:mm z',
		 dayOfWeekStart : 1,
		 lang:'en',
		 startDate:	dateToday //'1986/01/05'
		 });
	 
	 $('#fromImg').click(function(){
			$('#txtStartDate').datetimepicker('show');
		});
	 $('#toImg').click(function(){
			$('#txtEndDate').datetimepicker('show');
		});
	}); 
	
 

function julianDayCalculater(dates)
{	 
	var dateToday = dates; 
	var dd = dateToday.substring(0,2);
	var mm = dateToday.substring(3,5); 
	var yyyy = dateToday.substring(6,10);	 
   	var D = eval(dd);
	var M = eval(mm);
	var Y = eval(yyyy);
	if(M<3)	{
		Y--;
		M += 12;
	}
 	var C=0; 
	var E = Math.floor(365.25*(Y + 4716)); 
	var F = Math.floor(30.6001*(M + 1)); 
	var julianday = C + eval(D) + E + F - 1524.5; 
 	NewJD = julianday; 
 	alert(NewJD);
return  NewJD;
}



function appliedFromToDate(){	
	 
		var objFromDate = document.getElementById("txtStartDate").value;
		var objToDate = document.getElementById("txtEndDate").value;
		if(objFromDate!=null && objToDate!=null){
	    var	fromDate=julianDayCalculater(objFromDate);
	    var	toDate=julianDayCalculater(objToDate);
	    
	    if(toDate<fromDate)
		 {
		 alert("\"Date of To  \" should be greater than \"From  date\" ");
		 document.getElementById("txtEndDate").value="";
		 return false;
		 }else{
			 return true;
		 }   
	    }else{
			 return true;
	}
}

</script>
	
<script type="text/javascript">
function initialize() {
  var mapProp = {
    center:new google.maps.LatLng(28.6139,77.2090),
    zoom:5,
    mapTypeId:google.maps.MapTypeId.ROADMAP
  };
  var map=new google.maps.Map(document.getElementById("googleMap"), mapProp);
}
$(document).ready(function() {
google.maps.event.addDomListener(window, 'load', initialize);
});
</script> 
 <!-- <div id="googleMap" style="width:auto;height:200px;"></div>
             </div> -->
  </body>
</html>
 
	 