 <%@page import="com.trux.model.SubsidiaryClientOfOrg"%>
<%@page import="com.trux.model.Zones"%>
<%@page import="com.trux.model.Countries"%>
<%@page import="java.util.List"%>
<%@page import="com.trux.model.OrganizationMasterRegistration"%>
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

<title>Sub Organizational Client</title>

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
 	 var flag; //idClientMaster   subName     address   countryId stateId cityId hubId zoneId idClientLevelMaster
 	 var idClientMaster=document.getElementById("idClientMaster").value;
 	 var subName=document.getElementById("subName").value; 	 
 	 var address=document.getElementById("address").value;
 	 var countryId=document.getElementById("countryId").value;
 	 var stateId=document.getElementById("stateId").value;  
 	 var cityId=document.getElementById("cityId").value;//  usersName,passwords
 	 var hubId=document.getElementById("hubId").value;
 	 var zoneId=document.getElementById("zoneId").value;
 	 var idClientLevelMaster=document.getElementById("idClientLevelMaster").value;
 	 var message="";
 	if(idClientMaster==""){
 	  document.getElementById('message').style.display = "block";
 	  document.getElementById('message').style.color="red";
 	  message+="<br/>";
 	  message+="Please select the Organization name  !<br/>";
 	  document.getElementById("idClientMaster").style.borderColor="red";
 	  document.getElementById('idClientMaster').focus();
 	  flag= false;
 	  }else{
 	  document.getElementById("idClientMaster").style.borderColor="green"; 
 	  }
 	 if(subName==""){
 	   document.getElementById('message').style.display = "block";
 	   document.getElementById('message').style.color="red";
 	   
 	   message+="Please enter the subsididary Organization Name!<br/>";
 	   document.getElementById("subName").style.borderColor="red";
 	   document.getElementById('subName').focus();
 	   flag= false; 		 
 	   }else{
		 document.getElementById("subName").style.borderColor="green"; 
	   }
 	if(address==""){
 	 	document.getElementById("message").style.color="red";
 	 	document.getElementById('message').style.display = "block";
 	 	document.getElementById("address").style.borderColor="red"; 	 	
 	 	message+="Please enter address !<br/>";
 	 	document.getElementById('address').focus();
 	 	flag= false;
 	 	}else{
 	 	document.getElementById("address").style.borderColor="green";
 	 	} 
 	 
 	if(countryId==""){
 	document.getElementById('message').style.display = "block";
 	document.getElementById('message').style.color="red"; 	
 	message+="Please select coutry name !<br/>";
 	document.getElementById("countryId").style.borderColor="red";
 	document.getElementById('countryId').focus();
 	flag= false; 		 
 	}else{
 	document.getElementById("countryId").style.borderColor="green";
 	}
 	 
   if(stateId==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("stateId").style.borderColor="red";
	 	
	 	message+="Please select state name !<br/>";
	 	document.getElementById('stateId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("stateId").style.borderColor="green";
	 	} 
   if(cityId==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("cityId").style.borderColor="red";
	 	
	 	message+="Please select city name !<br/>";
	 	document.getElementById('cityId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("cityId").style.borderColor="green";
	 	} 
   if(hubId==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("hubId").style.borderColor="red";
	 	
	 	message+="Please select hub !<br/>";
	 	document.getElementById('hubId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("hubId").style.borderColor="green";
	 	} 
   if(zoneId==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("zoneId").style.borderColor="red";
	 	
	 	message+="Please select Zone !<br/>";
	 	document.getElementById('zoneId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("zoneId").style.borderColor="green";
	 	} 
   if(idClientLevelMaster==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("idClientLevelMaster").style.borderColor="red";
	 	
	 	message+="Please select Level !<br/>";
	 	document.getElementById('idClientLevelMaster').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("idClientLevelMaster").style.borderColor="green";
	 	} 
   
  if(message==""){flag=true;}
 	document.getElementById('message').innerHTML=message;
 	return flag;
 }
 
 //goople api
 
 var placeSearch, autocomplete,autocompleteTo;
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
     (document.getElementById('address')),
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
	 
 function fillState()	
		{    var SelValue = document.getElementById("countryId").value;
		 	 $.ajax({
			      type: "POST",
			      url: "/trux/reg/getCountryStates",
			      data:{
					  country:SelValue
					  } ,
			      success: function(data) {
			    	  document.getElementById("stateId").innerHTML = data;
			    	  document.getElementById("stateId").value.innerHTML=data;
			      }
			    });
		    return true;
		}


		function fillCity()
		{
		    var stateId = document.getElementById("stateId").value;
			 $.ajax({
			      type: "POST",
			      url: "/trux/reg/getCitiesByState",
			      data:{
					  state:stateId
					  } ,
			      success: function(data) {
			    	  document.getElementById("cityId").innerHTML = data;
			    	  document.getElementById("cityId").value.innerHTML=data;
			      }
			    });
		    return true;
		}
	function fillHubByCSC()
	{ 
	    var state = document.getElementById("stateId").value;
	    var country = document.getElementById("countryId").value;
	    var city = document.getElementById("cityId").value;
	    
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

function fillLevel()	
{
    var SelValue = document.getElementById("idClientMaster").value;	 
	 $.ajax({
	      type: "POST",
	      url: "/trux/app/getSubOrgLevelList",
	      data:{
	    	  clientMasterId:SelValue
			  } ,
	      success: function(data) {
	    	  document.getElementById("idClientLevelMaster").innerHTML = data;
	    	  document.getElementById("idClientLevelMaster").value.innerHTML=data;
	      }
	    });
    return true;
}

 //idClientMaster,idClientLevelMaster,subName,address, countryId, zoneId, stateId, cityId, hubId,isActive;
	</script>
</head>
 <body onload="initialize();">
 <div class="container">  
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager">  <legend class="borderManager">Subsidiary Registration <% SubsidiaryClientOfOrg rs=(SubsidiaryClientOfOrg) request.getAttribute("subOrgSaveBack"); if(rs!=null && rs.getIdClientSubMaster()!=0){%> <b style="color: green;">Registration ID :<%=rs.getIdClientSubMaster() %>,Register As Sub Org Name :<%=rs.getSubName()%></b><% }else if(rs!=null && rs.getIdClientSubMaster()==0){%>Your request is not register .<%} %></legend>
		 	
			<div class="clearfix margin_05"></div>
			<div class="clearfix margin_10"></div>	
		 	<form  action="subOrgRegistration" method="post" class="form-inline" onsubmit="return validateForm();" enctype="multipart/form-data">
				<div class="row" style="margin-top:-2%; ">   
				       <div class="col-lg-3 col-md-3 col-sm-12">
			   				<div style="margin-bottom:6px;">Client Organization Name<span style="color: red;">*</span></div>
							<select name="idClientMaster" id="idClientMaster" onchange="fillLevel();"  class="input-sm" style="width:100%;">
                                 <option value="">--Select Organization Level--</option>
                                 <% List<OrganizationMasterRegistration>  list= (List<OrganizationMasterRegistration>)session.getAttribute("orgList");
									if(list != null && !list.isEmpty()){
									for(int i=0; i<list.size();i++){ 
									%>	
								 <option value="<%=list.get(i).getIdClientMaster() %>"><%=list.get(i).getName() %></option>
                                 <%	}}%>                        
                             </select>	   
						</div>	                           									                                  
						
						<div class="col-lg-3 col-md-3 col-sm-12">
			   				<div style="margin-bottom:6px;">Client Sub Orgnization<span style="color: red;">*</span></div>
							<input type="text" name="subName" id="subName"    class="form-control input-sm" style="width:100%;" placeholder="Subsidiary Name"/>   
						</div>	
						
							<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Address<span style="color: red;">*</span></div>
							<textarea  name="address" id="address"  onFocus="geolocate()" class="form-control input-sm" style="width:100%;" placeholder="Local Address"></textarea>  
						</div>
						 			
								<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Country<span style="color: red;">*</span></div>
							   <select name="countryId" id="countryId" onchange="fillState()" class="input-sm" style="width:100%;">
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
    						}); 
    				        </script>
						 </div>
						 <div class="clearfix margin_05"></div>						
						
						 <div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Zones <span style="color: red;">*</span></div>
						<select name="zoneId" id="zoneId"  class="input-sm" style="width:100%;">
                                 <option value="">--Select Zone--</option>
                                 <% List<Zones>  listsd= (List<Zones>)session.getAttribute("zoneList");
									if(listsd != null && !listsd.isEmpty()){
									for(int i=0; i<listsd.size();i++){ 
									%>	
								 <option value="<%=listsd.get(i).getZoneId() %>"><%=listsd.get(i).getZone() %></option>
                                 <%	}}%>                        
                             </select>						 
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">State<span style="color: red;">*</span></div>
							<select name="stateId" id="stateId" onchange="fillCity();" class="input-sm" style="width:100%;">
							<option value="">--Select state--</option>
							 </select> 
						</div>
						 
						
						
					 
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">City<span style="color: red;">*</span></div>
						<select name="cityId" id="cityId" onchange="fillHubByCSC();"  class="input-sm" style="width:100%;">
							<option value="">--Select city--</option>
						</select>    
						</div>
					   <div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Hub<span style="color: red;">*</span></div>
						<select name="hubId" id="hubId" onchange="fillCluster()" class="input-sm" style="width:100%;">
							<option value="">--Select Hub--</option>
							 
							</select>   
						</div>
							<div class="clearfix margin_10"></div>
					   
						 <div class="col-lg-3 col-md-3 col-sm-12">
						 
						 <div style="margin-bottom:6px;"><div>Latitude</div>
						 <input type="text" name="clientLat" id="clientLat"  class="form-control input-sm" style="width:100%;" placeholder="Client Latitude" />
						 </div>
						 </div>
						 
						 <div class="col-lg-3 col-md-3 col-sm-12">
						<div style="margin-bottom:6px;"><div>Longitude</div>
							<div class="clearfix margin_5"></div>
						 
						 <input type="text" name="clientLong" id="clientLong"  class="form-control input-sm" style="width:100%;" placeholder="Client Longitude" />
						 </div>
					 </div>
						 					
						
						 <div class="col-lg-3 col-md-3 col-sm-12">
							<div>Level<span style="color: red;">*</span></div>
								<div class="clearfix margin_5"></div>
						 
							<select name="idClientLevelMaster" id="idClientLevelMaster"  class="input-sm" style="width:100%;">
                                 <option value="">--Select Level--</option>
                                                          
                             </select>	  
						</div>                                               
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div>&nbsp;</div>
							<input type="button" id="btn" class="btn btn-danger btn-sm btn_nav1" style="width: 120px;" value="Create Level">
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
			<div id="dialog" title="Organization Level">
			<div class="clearfix margin_10"></div>						 
						<div class="col-lg-3 col-md-3 col-sm-12" style="width: 100%;"><div >Organization Level <span style="color: red;">*</span></div>
						<select name="clientMasterId" id="clientMasterId" onchange="fillSubOrg();"  class="input-sm" style="width:100%;">
                                 <option value="">--Select Organization Level--</option>
                                 <% List<OrganizationMasterRegistration>  listLevel= (List<OrganizationMasterRegistration>)session.getAttribute("orgList");
									if(listLevel != null && !listLevel.isEmpty()){
									for(int i=0; i<listLevel.size();i++){ 
									%>	
								 <option value="<%=listLevel.get(i).getIdClientMaster() %>"><%=listLevel.get(i).getName() %></option>
                                 <%	}}%>                        
                             </select>						 
						</div>
						<div class="clearfix margin_10"></div>						 
						<div class="col-lg-3 col-md-3 col-sm-12" style="width: 100%;"><div>Level Title<span style="color: red;">*</span></div>
						 <input type="text" name="levelTitle" id="levelTitle"  class="form-control input-sm" style="width:100%;" placeholder="Level Title" />
						</div>
						
			            <div class="clearfix margin_10"></div>						 
						<div class="col-lg-3 col-md-3 col-sm-12" style="width: 100%;"><div >Precedence <span style="color: red;">*</span></div>
						<select name="precedence" id="precedence"  class="input-sm" style="width:100%;">
                          <option value="">--Select Precedence Level--</option>
                                 <%  
									for(int i=1; i<6;i++){ 
									%>	
						  <option value="<%=i %>"><%=i %></option>
                                 <%	}%>                        
                          </select>
						 </div>
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>
						<div class="col-lg-3 col-md-3 col-sm-12">     
							<div style="width: 100%;">
								<input type="button" class="btn btn-danger btn-sm" value="Register Now" onclick="saveOrganizationLevel();">  					                             
							</div>
						</div> 
						<div class="clearfix margin_10"></div>
						<div class="col-lg-3 col-md-3 col-sm-12" style="width: 100%;">     
							<div style="width: 100%; color: green;" id="messages"></div>
						</div> 
  </div> 
<script type="text/javascript">
    $(document).ready(function () {
        $("#dialog").dialog({ autoOpen: false }); 
        $("#btn").click(
            function () {
                $("#dialog").dialog('open');
                return false;
            }
        );
    });
    
    
    function saveOrganizationLevel()	
	{    var SelclientMasterIdValue = document.getElementById("clientMasterId").value;
	     var SelLevelTitleValue = document.getElementById("levelTitle").value;
	     var SelPrecedenceValue = document.getElementById("precedence").value;
	     var flag= false;
	    var message="";
	    if(SelclientMasterIdValue==""){
	   	  document.getElementById('message').style.display = "block";
	   	  document.getElementById('message').style.color="red";
	   	  message+="<br/>";
	   	  message+="Please select the Organization name  !<br/>";
	   	  document.getElementById("clientMasterId").style.borderColor="red";
	   	  document.getElementById('clientMasterId').focus();
	   	  flag= false;
	   	  }else{
	   	  document.getElementById("clientMasterId").style.borderColor="green"; 
	   	  }
	    if(SelLevelTitleValue==""){
		   	  document.getElementById('message').style.display = "block";
		   	  document.getElementById('message').style.color="red";
		   	  message+="<br/>";
		   	  message+="Please enter the Level Title !<br/>";
		   	  document.getElementById("levelTitle").style.borderColor="red";
		   	  document.getElementById('levelTitle').focus();
		   	  flag= false;
		   	  }else{
		   	  document.getElementById("levelTitle").style.borderColor="green"; 
		   	  }
	    if(SelPrecedenceValue==""){
		   	  document.getElementById('message').style.display = "block";
		   	  document.getElementById('message').style.color="red";
		   	  message+="<br/>";
		   	  message+="Please enter the precedence !<br/>";
		   	  document.getElementById("precedence").style.borderColor="red";
		   	  document.getElementById('precedence').focus();
		   	  flag= false;
		   	  }else{
		   	  document.getElementById("precedence").style.borderColor="green"; 
		   	  }
	    
	    if(message==""){flag=true;}
	    message="";
	 	 
	 if(flag==true){
	  $.ajax({
		      type: "POST",
		      url: "/trux/app/orgLevelRegistration",
		      data:{ 
				  clientMasterId:SelclientMasterIdValue,
				  levelTitle:SelLevelTitleValue, 
				  precedence:SelPrecedenceValue
				  } ,
		      success: function(data) {
		    	  document.getElementById("messages").innerHTML = data;
		    	  document.getElementById("messages").value.innerHTML=data;
		      }
		    });
	    return true;
	 } 
	  
 }
</script>
 <div id="message" style="color: red;"></div>  
</fieldset>
</div>
	</div>		
</div>
  </body>
</html>
 
	 