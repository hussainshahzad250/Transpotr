<%@page import="com.trux.model.ClientMandate"%>
<%@page import="java.util.Date"%>
<%@page import="com.trux.utils.DateFormaterUtils"%>
<%@page import="com.trux.model.DriverAttandance"%>
<%@page import="com.trux.model.OrganizationMasterRegistration"%>
<%@page import="java.util.List"%>
<%@page import="com.trux.model.OrderFilterReportsDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css"
	rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js"
	type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js"
	type="text/javascript"></script>
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css"
	rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js"
	type="text/javascript"></script>


<style type="text/css">
#md-form input[type="radio"] {
	-webkit-appearance: radio;
}

input[type="checkbox"] {
	-webkit-appearance: checkbox;
}

input[type="text"] {
	-webkit-appearance: text;
}

tbody tr td:first-child, table#attandanceTable tbody tr td:last-child {
	padding: 3px 0px
}
</style>
<style type="text/css">
table.successTable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
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
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
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

.oddrowcolor {
	background-color: #d4e3e5;
}

.evenrowcolor {
	background-color: #c3dde0;
}
</style>


<script src="/trux/resources/core/jquery.autocomplete.min.js"></script>
<link href="/trux/resources/core/main.css" rel="stylesheet">

<link type="text/css" rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>

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
 
 
 
   
//------------------------------------------//
 
	
 function tt(){ alert("jj");}


$(function() {
$("input:radio[name=tax]").click(function() {
    var value = $(this).val();
    var options="<option value=''>--Select Tax Type--</option><option value='Full'>Full</option><option value='Abated'>Abated</option>";
     if(value=="No"){
    	$("#taxType").prop('disabled', 'disabled');
    	$("#taxType").html(options);
    	
     }else{
    	 $("#taxType").removeAttr("disabled");
     }
});
 });

 


	 //---------------------------------------//
		 
			 //Validate Decimal Value
 function validateDecimal(id) {
    $(id).val($(id).val().replace(/[^0-9\.]/g,''));
            if ((event.which != 46 || $(id).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
                event.preventDefault();
            }
        
} 
		
	
	 
	 

	 
	 
	 
	 
	 function validateVehicleType(ids,bids,index) {
		 
		 var options="<option value=''>--Select Vehicle type</option><option value='Mahindra Champion'>Champion</option><option value='Maruti Eeco'>Eeco</option><option value='Tata Ace'>Tata Ace</option><option value='Tata 407'>Tata 407 (10 Ft)</option><option value='Tata 709'>Tata 709 (14 Ft)</option><option value='Bolero Pickup'>Bolero Pickup</option><option value='17 Feet Single Axle'>17 Feet - Single Axle</option><option value='17 Feet Double Axle'>17 Feet - Double Axle</option><option value='19 Feet Single Axle'>19 Feet - Single Axle</option><option value='19 Feet Double Axle'>19 Feet - Double Axle</option><option value='19 Feet Multi-Axle'>19 Feet - Multi-Axle</option><option value='22 Feet Single Axle'>22 Feet - Single Axle</option><option value='22 Feet Double Axle'>22 Feet - Double Axle</option><option value='22 Feet Multi-Axle'>22 Feet - Multi-Axle</option><option value='24 Feet Single Axle'>24 Feet - Single Axle</option><option value='24 Feet Double Axle'>24 Feet - Double Axle</option><option value='24 Feet Multi-Axle'>24 Feet - Multi-Axle</option><option value='28 Feet Single Axle'>28 Feet - Single Axle</option><option value='28 Feet Double Axle'>28 Feet - Double Axle</option><option value='28 Feet Multi-Axle'>28 Feet - Multi-Axle</option><option value='32 Feet Single Axle'>32 Feet - Single Axle</option><option value='32 Feet Double Axle'>32 Feet - Double Axle</option><option value='32 Feet Multi-Axle'>32 Feet - Multi-Axle</option><option value='40 Feet Double Axle'>40 Feet - Double Axle</option><option value='40 Feet Multi-Axle'>40 Feet - Multi-Axle</option>";
		 
			var tbl = document.getElementById('mandateTable');
			var lastRow = tbl.rows.length;
			var iteration = lastRow;
			var idName = new Array("","vehicleType","vehicleBody"); 
			var vehicleTypeValue = $("#"+ids).val(); 
			var vehicleBodyValue = $("#"+bids).val(); 
			
			//alert("Selected - "+vehicleTypeValue + " " + vehicleBodyValue + " " + index);
			
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
				var vehicleBodyInnerValue = ""; 
		 	 	for ( var id = 1; id < idName.length; id++) {
		 	 	    if(id==1){ 
				    	vehicleTypeInnerValue =$("#vehicleType"+i).val();
			 	     }
		 	 	  if(id==2){ 
		 	 		    vehicleBodyInnerValue =$("#vehicleBody"+i).val();
			 	     }
		 	 	  
		 	 	//alert("Iterated - "+vehicleTypeInnerValue + " " + vehicleBodyInnerValue + " " + index);
		 	 	  
		 	 	  if(index!=i){
		 	 		 if($.trim(vehicleTypeValue)==$.trim(vehicleTypeInnerValue) ){
		 	 			 if($.trim(vehicleBodyValue)==$.trim(vehicleBodyInnerValue)){
		 	 				 $("#"+bids).css({ borderColor: "red" });
						 	 $("#"+bids).focus();
						 	 $("#"+ids).css({ borderColor: "red" });
						 	 $("#"+ids).focus();
						 	alert(" Already exist " +vehicleTypeValue);
						 	//$("#"+ids).html(options);
						 	alert(" Already exist " +vehicleBodyValue);
		 	 			 }
		 	 			 else{
		 	 				 $("#"+ids).css({ borderColor: "green" });
		 	 				 $("#"+ids).html(options);
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
	 }
	 
	 
	 
	 function validateTwoDifferentYearDate(){
		 var objToDate=$("#mandateStartDate").val();
		 var objFromDate =$("#mandateEndDate").val();
		 validateAlreadyAssingDate();
		 if(dateCheck(objFromDate ,objToDate)){
			 $("#mandateStartDate").css({ borderColor: "green" });
			 $("#mandateEndDate").css({ borderColor: "green" });
		   } else {
			    alert("From date should be greater than to date !");
			    $("#mandateStartDate").css({ borderColor: "red" });
			    $("#mandateEndDate").css({ borderColor: "red" });
			   // $("#mandateStartDate").val('');
				$("#mandateEndDate").val('');
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
	 var startDate=new Array();
	 var endDate=new Array();
	 
	 function searchMandateTypeIsExist(){
		 startDate=new Array();
		 endDate=new Array();
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
		       url: "/trux/clients/searchByMandateType",
		       data:{
		    	   clientId:SelOrgValue,
		    	   clientSubId:SelClientSubId,
		    	   mandateType:SelMandateType
		 		  } ,
		       success: function(data) {
		    	   if(data=='' || !data)
	  				  {
	  					 
	  				  } else{
		    	   $.each(data, function( index, value ) {
                        
		    		   //alert("Entered");
		    		   var mandateType=value.mandateType;
		                var clientNams=value.clientName;
				    	var subClientName=value.subClientName;
				    	var startDates=value.mandateStartDates;
				    	var endDates=value.mandateEndDates;
				        var endDats=endDates.substring(0,10).replace("-", "/").replace("-", "/").replace("-", "/");
				    	var startDated = startDates.substring(0,10).replace("-", "/").replace("-", "/").replace("-", "/");
				    	startDate.push(startDated);
				    	endDate.push(endDats);
				       });
		    	/*   alert(startDate +"  "+endDate) */
	  				  }
		       }
		     });
		   }
	 }
	 
	 function validateAlreadyAssingDate(){
		 var objToDate=$("#mandateStartDate").val();
		 var objFromDate =$("#mandateEndDate").val();
		 if(objFromDate!='' && objFromDate!=''){
		 var fDate,lDate;
		  fDate = Date.parse(objFromDate);
		  lDate = Date.parse(objToDate);
		  var checkEqual=fDate+lDate;
		  if(startDate!=''){
	      var arrayLength = startDate.length;
	      var messages="";
	     var flags=false;
  	      for (var i = 0; i < arrayLength; i++) {
  		  var availablefDate = Date.parse(startDate[i]);
  		  var availableTDate = Date.parse(endDate[i]); 
  		  var totalAvailableDate=(availablefDate+availableTDate);
  	//alert(objToDate);
  	//alert(endDate[i]);
  			if(lDate<availableTDate || lDate==availableTDate){
  	  			 messages="Mandate already exist for selected date range. ";  
  	  			flags=false; 
  	  		}  		  
  			else if(totalAvailableDate>=checkEqual){
  			 messages="Mandate already exist for selected date range. ";  
  			flags=false; 
  		   }else{
  			// messages="Are you valide to assing start & end date.Please assing befor date ." 
  				flags=true;
  		   }
  		  }
  	    if(flags==false){
  	    	 $("#message").html(messages);
  	    	$("#message").css({color: "red" });
             $("#mandateStartDate").val('');
  			 $("#mandateEndDate").val('');
  	    }else{
  	    	 $("#message").html(messages);
  	    	// $("#message").css({color: "green" });
  	    }
	}
	 }
	 }
	 
	 
	  
	 
	 
  	    
	 
 </script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<fieldset class="fieldset2 col-lg-12 borderManager table-responsive">
					<legend class="borderManager" style="width: 100%">
						Add New Mandate
						<!--  <a href="/trux/clients/editmandate" class="btn btn-danger btn-sm right-btn btn_nav1 right_btn_update" style="color: black;padding-right: 20px;padding-top: -2px;/* float: right; */"><b>Update Mandate</b></a>
    -->
					</legend>
					
					<script type="text/javascript">function validateForm(){
						 //alert("validate entered");
						 
						 /* var mandateType=document.getElementById("mandateType").value;
						 
						 if(mandateType=="Box" || mandateType=="Weight"){
						 
							 //alert("in");
							 collectVehicleDetailsValue();
						 } */
						 collectVehicleDetailsValue();
					 	 var flag=false;   
					 	// clientId clientSubId mandateType mandateStartDate mandateEndDate
					 	 var clientId=document.getElementById("clientId").value; 
					     var clientSubId = document.getElementById("clientSubId").value;
					 	  
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
					 	 	  validateTwoDifferentYearDate();
					 	 	 
					 	 	 //
					 	 	 if(mandateType=="Box" || mandateType=="Weight")
					 	 	 {
							  var tbl = document.getElementById('mandateTable');
							  var lastRow = tbl.rows.length;
							  var iteration = lastRow;
							  var idName = new Array("","vehicleType","vehicleBody","count");
					 	 	 for ( var i = 0; i < iteration; i++) {	
							 		var countInnerValue = 0; 
									var vehicleInnerBodyValue = ""; 
									var vehicleTypeInnerValue = ""; 
							 	    for ( var id = 1; id < idName.length; id++) {
									  if(id==1){ 
										  if($("#"+idName[id]+i).val()==""){
											  document.getElementById('message').style.display = "block";
								 	 	 	   document.getElementById('message').style.color="red";
								 	 	 	   message+="Please select the Vehicle Type !<br/>";
									    	   document.getElementById(idName[id]+i).style.borderColor="red";
											 	document.getElementById(idName[id]+i).focus();
											 	 flag= false; 
											    }else{
										 	 	 document.getElementById(idName[id]+i).style.borderColor="green";
										 	  }
								 	     }
									     if(id==2){
									    	if($("#"+idName[id]+i).val()==""){
									    		 document.getElementById('message').style.display = "block";
									 	 	 	   document.getElementById('message').style.color="red";
									 	 	 	   message+="Please select the body Type !<br/>";
									    	   document.getElementById(idName[id]+i).style.borderColor="red";
											 	document.getElementById(idName[id]+i).focus();
											 	 flag= false; 
											    }else{
										 	 	 document.getElementById(idName[id]+i).style.borderColor="green";
										 	  }
									    	}
										   if(id==3){
									    	 if($("#"+idName[id]+i).val()==""){
									    		 document.getElementById('message').style.display = "block";
									 	 	 	   document.getElementById('message').style.color="red";
									 	 	 	   message+="Please enter the required total vehicle  !<br/>";
										    	   document.getElementById(idName[id]+i).style.borderColor="red";
												 	document.getElementById(idName[id]+i).focus();
												 	 flag= false; 
												    }else{
											 	  document.getElementById(idName[id]+i).style.borderColor="green";
										      }
									        }
									 }
								  } 
					}
							 	 
					 	 	 //
					 	 	if(message==""){flag=true;}
					  return flag;
					  }
					
					
					function collectVehicleDetailsValue() {
						 
						var tbl = document.getElementById('mandateTable');
						var lastRow = tbl.rows.length;
						var iteration = lastRow;
						var count = new Array(); 
						var vehicleBody = new Array(); 
						var vehicleType = new Array(); 
						var idName = new Array("","vehicleType","vehicleBody","count");
						var countValue = 0; 
						var vehicleBodyValue = ""; 
						var vehicleTypeValue = ""; 
						var totalCount=0;
						  
					 	for ( var i = 0; i < iteration; i++) {	
					 		var countInnerValue = 0; 
							var vehicleInnerBodyValue = ""; 
							var vehicleTypeInnerValue = ""; 
					 		 
						 	for ( var id = 1; id < idName.length; id++) {
								
							    if(id==1){ 
							    	vehicleTypeInnerValue =$("#"+idName[id]+i).val();
						 	     }
							     if(id==2){
							    	 vehicleInnerBodyValue = $("#"+idName[id]+i).val();
								     }
							     if(id==3){
							    	 countInnerValue = $("#"+idName[id]+i).val();
							    	 //alert(countInnerValue);
								     }
							 	if (id == (idName.length)-1) {
							 	 countValue  = countInnerValue; 
								 vehicleBodyValue =vehicleInnerBodyValue; 
								 vehicleTypeValue =vehicleTypeInnerValue; 
							 	} else {
							 		 countValue  = countInnerValue; 
									 vehicleBodyValue =vehicleInnerBodyValue; 
									 vehicleTypeValue =vehicleTypeInnerValue; 
							  }
							}
						 	
						 	count.push(countValue); 
							vehicleBody.push(vehicleBodyValue); 
							vehicleType.push(vehicleTypeValue); 
							totalCount=totalCount+countValue;
					     } 
					 	 

						$("#vehicleTypeVal").val(vehicleType);
						$("#vehicleBodyVal").val(vehicleBody);   
						$("#countVal").val(count);			
						$("#totalVehicle").val(totalCount); 
						  
					 count = new Array(); 
					 vehicleBody = new Array(); 
					 vehicleType = new Array(); 
					 return  vehicleType;
						
					}
					
					</script>
					
					<form id="md-form" action="/trux/clients/savemandate" method="post"
						class="form-inline" onsubmit="return validateForm();">
						<div class="row" style="margin-top: -2%;">
							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">
									Clients<span style="color: red;">*</span>
								</div>
<script>function fillSubOrg()	
{   var flag=false; 
var SelOrgValue = document.getElementById("clientId").value;
var message="";
//alert(SelOrgValue);
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
    	  //alert(data);
    	  document.getElementById("clientSubId").innerHTML = data;
    	  document.getElementById("clientSubId").value.innerHTML=data;
      }
    });
return true;
}
return false;
} 


</script>
								<select name="clientId" id="clientId" onchange="fillSubOrg();"
									class="input-sm" style="width: 100%;" required>
									<option value="">--Select Client--</option>
									<%
										List<OrganizationMasterRegistration>  list= (List<OrganizationMasterRegistration>)request.getAttribute("orgList");
																if(list != null && !list.isEmpty()){
																for(int i=0; i<list.size();i++){
									%>
									<option value="<%=list.get(i).getIdClientMaster()%>"><%=list.get(i).getName()%></option>
									<%
										}}
									%>
								</select>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">
									Sub-Clients <span style="color: red;">*</span>
								</div>
								<select name="clientSubId" id="clientSubId" class="input-sm"
									style="width: 100%;" required>
									<option value="">--Select Sub-Client--</option>
								</select>
							</div>
							<script>  
                             $(document).ready(function(){    																		 
                            	 //fillSubOrg();
    						}); 
                             
                             function mandateTypeSelection() {
                     		    var manTypeValue = $("#mandateType").val();
                     		     if(manTypeValue=="Weight"){
                     		    	$("#wieghtId").show();
                     		     }else{
                     		    	 $("#wieghtId").hide();
                     		     }

                     		    if(manTypeValue=="Weight" || manTypeValue=="Box"){
                     		    	$("#mandateTableId").hide();
                     		    }
                     		    else{
                     		    	$("#mandateTableId").show();
                     		    }
                     		     
                     		     
                     		}
    				        </script>

							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">
									Mandate Type<span style="color: red;">*</span>
								</div>
								<select name="mandateType" id="mandateType" class="input-sm"
									style="width: 100%;"
									onchange="mandateTypeSelection(), searchMandateTypeIsExist();"
									required>
									<option value="">--Select Mandate Type--</option>
									<option value="Leased">Leased</option>
									<option value="Box">Box</option>
									<option value="Weight">Weight</option>
									<option value="Per Trip">Per Trip</option>
									<option value="Per Km">Per Km</option>
									<option value="Per Day">Per Day</option>
									<option value="Per Hour">Per Hour</option>

								</select>
							</div>
							<div class="clearfix margin_05"></div>
							<div class="clearfix margin_10"></div>


							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">
									Mandate Start Date<span style="color: red;">*</span>
								</div>
								<input type="text" name="mandateStartDate" id="mandateStartDate"
									onchange="validateAlreadyAssingDate(), validateTwoDifferentYearDate();"
									class="form-control input-sm"
									style='width: 100%; box-shadow: 1px 1px #AEAEAE; border: 1px solid #AEAEAE; background: url(../resources/images/calender2.png); background-repeat: no-repeat; background-position: right;'
									placeholder="Select Start Date (Alpha-numeric)" required>

							</div>
							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">
									Mandate End Date<span style="color: red;">*</span>
								</div>
								<input type="text" name="mandateEndDate" id="mandateEndDate"
									onchange="validateAlreadyAssingDate(), validateTwoDifferentYearDate();"
									class="form-control input-sm"
									style='width: 100%; box-shadow: 1px 1px #AEAEAE; border: 1px solid #AEAEAE; background: url(../resources/images/calender2.png); background-repeat: no-repeat; background-position: right;'
									placeholder="Select End Date (Alpha-numeric)" required>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">
									Apply Service Tax<span style="color: red;">* </span> <input
										type="radio" name="tax" id="tax" value="Yes" checked>Yes
									<input type="radio" name="tax" id="tax" value="No">No
								</div>
								<select name="taxType" id="taxType" class="input-sm"
									style="width: 100%;">
									<option value="">--Select Tax Type--</option>
									<option value="Full">Full</option>
									<option value="Abated">Abated</option>

								</select>
							</div>

							<div class="clearfix margin_05"></div>
							<div class="clearfix margin_10"></div>
							<div class="col-lg-6 col-md-12 col-sm-12" id="mandateTableId" style="border: 2px;">
								<div style="margin-bottom: 6px;">
									Vehicle Details<span style="color: red;">*</span>
								</div>
								<table style="width: 100%;" id="mandateTable"
									class="col-lg-12 col-md-6 col-sm-12">
									<tbody id="mandateTBody">
										<tr>
											<td>
												<div class='col-lg-4 col-md-4 col-sm-12'
													style='float: left; margin: 0px -13px;'>
													<select name='vehicleType0' id='vehicleType0'
														class='input-sm'>
														<option value=''>--Select Vehicle type</option>
														<option value='Mahindra Champion'>Champion</option>
														<option value='Maruti Eeco'>Eeco</option>
														<option value='Tata Ace'>Tata Ace</option>
														<option value='Tata 407'>Tata 407 (10 Ft)</option>
														<option value='Tata 709'>Tata 709 (14 Ft)</option>
														<option value='Bolero Pickup'>Bolero Pickup</option>
														<option value='17 Feet Single Axle'>17 Feet -
															Single Axle</option>
														<option value='17 Feet Double Axle'>17 Feet -
															Double Axle</option>
														<option value='19 Feet Single Axle'>19 Feet -
															Single Axle</option>
														<option value='19 Feet Double Axle'>19 Feet -
															Double Axle</option>
														<option value='19 Feet Multi-Axle'>19 Feet -
															Multi-Axle</option>
														<option value='22 Feet Single Axle'>22 Feet -
															Single Axle</option>
														<option value='22 Feet Double Axle'>22 Feet -
															Double Axle</option>
														<option value='22 Feet Multi-Axle'>22 Feet -
															Multi-Axle</option>
														<option value='24 Feet Single Axle'>24 Feet -
															Single Axle</option>
														<option value='24 Feet Double Axle'>24 Feet -
															Double Axle</option>
														<option value='24 Feet Multi-Axle'>24 Feet -
															Multi-Axle</option>
														<option value='28 Feet Single Axle'>28 Feet -
															Single Axle</option>
														<option value='28 Feet Double Axle'>28 Feet -
															Double Axle</option>
														<option value='28 Feet Multi-Axle'>28 Feet -
															Multi-Axle</option>
														<option value='32 Feet Single Axle'>32 Feet -
															Single Axle</option>
														<option value='32 Feet Double Axle'>32 Feet -
															Double Axle</option>
														<option value='32 Feet Multi-Axle'>32 Feet -
															Multi-Axle</option>
														<option value='40 Feet Double Axle'>40 Feet -
															Double Axle</option>
														<option value='40 Feet Multi-Axle'>40 Feet -
															Multi-Axle</option>
													</select>
												</div>

												<div class='col-lg-4 col-md-4 col-sm-12'>
													<select name='vehicleBody0' id='vehicleBody0'
														class='input-sm' onchange="validateVehicleType('vehicleType0','vehicleBody0',0);">
														<option value=''>--Select Body type</option>
														<option value='Containerized'>Containerized</option>
														<option value='Open body'>Open body</option>
													</select>
												</div>
												<div class='col-lg-4 col-md-4 col-sm-12'
													style='float: left; margin: 0px -13px;'>
													<input type='text' name='count0' id='count0'
														class='form-control input-sm'
														onkeypress='validateDecimal(count0);' maxlength='11'
														style='width: 100%;' placeholder='No Of Vehicle' />
												</div>
											</td>
										</tr>
									</tbody>
								</table>
								<script type="text/javascript">function addRow(){
									 var i= $('#mandateTBody').children().length;
									   var data="<tr><td>";																																							  
									    data +="<div class='col-lg-4 col-md-4 col-sm-f12' style='float: left;margin: 0px -13px;'><select name='vehicleType"+i+"' id='vehicleType"+i+"' onchange=\"validateVehicleType(\'vehicleType"+i+"\',\'vehicleBody"+i+"\',"+i+");\" class='input-sm' required><option value=''>--Select Vehicle type</option><option value='Mahindra Champion'>Champion</option><option value='Maruti Eeco'>Eeco</option><option value='Tata Ace'>Tata Ace</option><option value='Tata 407'>Tata 407 (10 Ft)</option><option value='Tata 709'>Tata 709 (14 Ft)</option><option value='Bolero Pickup'>Bolero Pickup</option><option value='17 Feet Single Axle'>17 Feet - Single Axle</option><option value='17 Feet Double Axle'>17 Feet - Double Axle</option><option value='19 Feet Single Axle'>19 Feet - Single Axle</option><option value='19 Feet Double Axle'>19 Feet - Double Axle</option><option value='19 Feet Multi-Axle'>19 Feet - Multi-Axle</option><option value='22 Feet Single Axle'>22 Feet - Single Axle</option><option value='22 Feet Double Axle'>22 Feet - Double Axle</option><option value='22 Feet Multi-Axle'>22 Feet - Multi-Axle</option><option value='24 Feet Single Axle'>24 Feet - Single Axle</option><option value='24 Feet Double Axle'>24 Feet - Double Axle</option><option value='24 Feet Multi-Axle'>24 Feet - Multi-Axle</option><option value='28 Feet Single Axle'>28 Feet - Single Axle</option><option value='28 Feet Double Axle'>28 Feet - Double Axle</option><option value='28 Feet Multi-Axle'>28 Feet - Multi-Axle</option><option value='32 Feet Single Axle'>32 Feet - Single Axle</option><option value='32 Feet Double Axle'>32 Feet - Double Axle</option><option value='32 Feet Multi-Axle'>32 Feet - Multi-Axle</option><option value='40 Feet Double Axle'>40 Feet - Double Axle</option><option value='40 Feet Multi-Axle'>40 Feet - Multi-Axle</option></select></div><div class='col-lg-4 col-md-4 col-sm-12' ><select name='vehicleBody"+i+"' id='vehicleBody"+i+"' class='input-sm'  required><option value=''>--Select Body type</option><option value='Containerized'>Containerized</option><option value='Open body'>Open body</option></select></div><div class='col-lg-4 col-md-4 col-sm-12' style='float: left;margin: 0px -13px;'><input type='text' name='count"+i+"' id='count"+i+"' class='form-control input-sm' onkeypress='validateDecimal(count"+i+");'  maxlength='11'  style='width:100%;' placeholder='No Of Vehicle' required/></div></td></tr>";
									    $('#mandateTBody').append(data);
									   
									}</script>
								<input type="button" onclick="addRow();" value="+"
									class="btn btn-danger btn-sm btn_nav1">&nbsp;&nbsp;&nbsp;
									<script type="text/javascript">function removToVehiceDetailsRow() {
										var tbl = document.getElementById('mandateTable');
										var lastRow = tbl.rows.length;
										if (lastRow > 1)
											tbl.deleteRow(lastRow - 1);
									}</script>
								<input type="button" class="btn btn-danger btn-sm btn_nav1"
									onclick="removToVehiceDetailsRow();" value="-">
								<!--  <input type="checkbox" class="btn btn-danger btn-sm btn_nav1" onclick="collectVehicleDetailsValue();" value="Active">Active
			  -->
							</div>
							<div class="col-lg-3 col-md-12 col-sm-12" id="wieghtId"
								style="display: none;">
								<div style="margin-bottom: 6px;">
									Weight Unit <span style="color: red;">* </span>
								</div>
								<select name="weightVal" id="weightVal" class="input-sm"
									style="width: 100%;">
									<option value="">--Select Weight Unit--</option>
									<option value="Per Kg">Per Kg</option>
									<option value="Per Ton">Per Ton</option>
									<option value="Per Litre">Per Litre</option>
									<option value="Per Quintal">Per Quintal</option>
								</select>
							</div>
						</div>
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>

						<input type="hidden" name=vehicleTypeVal id="vehicleTypeVal"
							value=""> <input type="hidden" name="vehicleBodyVal"
							id="vehicleBodyVal" value=""> <input type="hidden"
							name="countVal" id="countVal" value=""> <input type="hidden"
							name="totalVehicle" id="totalVehicle" value=""> 
							
							<input type="submit" class="btn btn-danger btn-sm btn_nav1"
							id="save" value="Save Now"> <input type="reset"
							class="btn btn-danger btn-sm btn_nav1" value="Reset">
					</form>
					<%
						ClientMandate savelists=(ClientMandate)request.getAttribute("saveBack"); if(savelists!=null){
					%>
					<b style="color: green; position: absolute;"> Mandate
						Successfully Added</b>
					<%
						}
					%>
					<div id="message" style="color: red;"></div>
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
		$('#mandateStartDate').datetimepicker({
			//timepicker:false,
			// timeFormat: 'HH:mm z',
			/*  dayOfWeekStart : 1,
			 lang:'en',
			 yearRange: '1800:' + toYears + '',
			 startDate:	dateToday  */
			timepicker:false,
		    format:'Y/m/d'
			 });
		 
		$('#mandateEndDate').datetimepicker({
			 /* datepicker:false,
			  format:'H:i',
			  step:5  */
			 // timepicker:false,
			// timeFormat: 'HH:mm z',
			/*  dayOfWeekStart : 1,
			 lang:'en',
			 yearRange: '1800:' + toYears + '',
			 startDate:	dateToday */
			 timepicker:false,
			    format:'Y/m/d'
			 });
		});
	 
		</script>
</body>
</html>

