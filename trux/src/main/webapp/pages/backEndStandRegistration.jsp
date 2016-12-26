<%@page import="com.trux.model.StandRegistration"%>
<%@page import="com.trux.model.HubRegistration"%>
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

<title>Register Organizational Client</title>

 <script type="text/javascript">
  
 function validateForm(){
 	 var flag;  //clusterId,standName
 	 var hubId=document.getElementById("hubId").value;
 	 var clusterId=document.getElementById("clusterId").value;
 	 var standName=document.getElementById("standName").value;  
 	 var message="";
 	if(hubId==""){
 	  document.getElementById('message').style.display = "block";
 	  document.getElementById('message').style.color="red";
 	  message+="<br/>";
 	  message+="Please select the Hub  !<br/>";
 	  document.getElementById("hubId").style.borderColor="red";
 	  document.getElementById('hubId').focus();
 	  flag= false;
 	  }else{
 	  document.getElementById("hubId").style.borderColor="green"; 
 	  }
 	 if(clusterId==""){
 	   document.getElementById('message').style.display = "block";
 	   document.getElementById('message').style.color="red"; 	   
 	   message+="Please select the Cluster  !<br/>";
 	   document.getElementById("clusterId").style.borderColor="red";
 	   document.getElementById('clusterId').focus();
 	   flag= false; 		 
 	   }else{
		 document.getElementById("clusterId").style.borderColor="green"; 
	   } 	 
 	if(standName==""){
 	document.getElementById('message').style.display = "block";
 	document.getElementById('message').style.color="red"; 	
 	message+="Please enter Stand details !<br/>";
 	document.getElementById("standName").style.borderColor="red";
 	document.getElementById('standName').focus();
 	flag= false; 		 
 	}else{
 	document.getElementById("standName").style.borderColor="green";
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
   autocomplete = new google.maps.places.Autocomplete(
       /** @type {HTMLInputElement} */(document.getElementById('standName')),
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

function fillStand()	
{
  var SelValue = document.getElementById("clusterId").value;	 
	 $.ajax({
	      type: "POST",
	      url: "/trux/reg/getClusterByHub",
	      data:{
	    	  hubId:SelValue
			  } ,
	      success: function(data) {
	    	  document.getElementById("standName").innerHTML = data;
	    	  document.getElementById("standName").value.innerHTML=data;
	      }
	    });
    return true;
}
	 
	</script>
	 
</head>
 <body onload="initialize();">
 <div class="container">  
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager">  
    	<legend class="borderManager">Stand Registration <% StandRegistration saveStandBack=(StandRegistration)request.getAttribute("saveStandBack"); if(saveStandBack!=null && saveStandBack.getStandId()!=null){ %><span style="color: green;">Stand Saved successfully </span> <%} %></legend>
					<form  action="standRegister" method="post" class="form-inline" onsubmit="return validateForm();">				    
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Hub<span style="color: red;">*</span></div>
							   <select name="hubId" id="hubId" onchange="fillCluster()" class="input-sm" style="width:100%;">
                                <option value="">--Select Hub--</option>
                                 <%List<HubRegistration> hubList = (List<HubRegistration>)session.getAttribute("hublist");
									if(hubList != null && !hubList.isEmpty()){
									for(int i=0; i<hubList.size();i++){ 
									%>	
								 <option value="<%=hubList.get(i).getHubId() %>"><%=hubList.get(i).getHubName() %></option>
                                 <%	}}%>                        
                             </select>
						 </div>
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Cluster<span style="color: red;">*</span></div>
							<select name="clusterId" id="clusterId" onchange="fillStand();" class="input-sm" style="width:100%;">
							<option value="">--Select Cluster--</option>
							 </select> 
						</div>
					 	
						 	<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Stand ( DC ) Name<span style="color: red;">*</span></div>
							<input type="text" name="standName" id="standName"  class="form-control input-sm" style="width:100%;" placeholder="Cluster Name"/>   
						</div>
						 
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
						<div class="col-lg-3 col-md-3 col-sm-12">     
							<div style="margin-top:6px; font-size:14px; text-align:left; margin-left:0px;margin-top:8px;">
								<input type="submit" class="btn btn-danger btn-sm btn_nav1" value="Register Now"> 
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
  </body>
</html>
 
	 