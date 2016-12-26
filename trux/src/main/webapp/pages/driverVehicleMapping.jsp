<!DOCTYPE html>
<%@page import="com.trux.model.DriverRegistration"%>
<%@page import="com.trux.model.VehicleRegistration"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<title>Trux</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- favicon links -->
<link rel="shortcut icon" type="image/ico" href="favicon.ico" />
<link rel="icon" type="image/ico" href="favicon.ico" />
<!-- main css -->
<link rel="stylesheet" href="/trux/resources/css/main.css" media="screen"/>
<!--js-->
<script src="/trux/resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){	
	// "scrollTop" plugin
	$.scrollUp();
});
</script>

</head>
<body>
<header id="header">	
	<div class="container">
        <div class="row">          	
            <div class="col-lg-6 col-md-6 col-sm-12">
                <div id="hs_logo" > <a href="javascript:void(0);"> <img src="/trux/resources/images/logo2.png" alt=""> </a> </div>
            </div>            	
            <div class="col-lg-6 col-md-6 col-sm-12">
                <div class="top_right">
                    <ul>
                        <li><span style="color:#FFFFFF;"></span></li>  
                    </ul>
                </div>
            </div>          
        </div>
	</div>
<header>
<div class="hs_page_title">
	<div class="container">
		<div class="row" style="margin-left:0px; margin-right:0; margin-top:5px;">
    		<h3>Map Driver & Vehicle</h3>
    		
		</div>
  	</div>
</div>
<div class="clearfix hs_margin_30"></div>
<div class="container">  
	<div class="row">
    	<div class="col-lg-12 col-md-12 col-sm-12">            
                <div class="row-fluid">		
                    <div class="hs_tab">
                        <ul id="myTab" class="nav nav-tabs">
                         <li><a href="/trux/admin/register/driverRegistration">Driver Registration</a></li>
                          <li><a href="/trux/admin/register/vehicleRegistration">Vehicle Registration</a></li> 
                          <li><a href="/trux/admin/register/deviceRegistration">Device Registration</a></li>   
							<li><a href="/trux/admin/truxDetail/registeredDriverList">Register Driver List</a></li>   
							<li><a href="/trux/admin/truxDetail/registeredVehicleList">Register Vehicle List</a></li> 
							<li><a href="/trux/admin/truxDetail/vehicleDriverMappingView">Driver Vehicle Map List</a></li> 							
                        </ul>
						<div class="clearfix hs_margin_30"></div>						     			
                        <div id="myTabContent" class="tab-content">

                        <div>
                        <form action="/trux/admin/truxDetail/vehicleDriverMapping" method="post">
							<fieldset class="feildsetarea">
                                <div class="fieldset2">Vehicle Driver Mapping</div>
                                <div class="clearfix hs_margin_30"></div>  
                                <div class="row">
                                    <div class="col-lg-4 col-md-4 col-sm-12">                                  
                                        <div class="col-lg-12 col-md-12 col-sm-12"><div style="margin-bottom:6px;">Driver List</div>
                                            <select name="driverId" id="driverId" class="form-control2 input-lg" style="width:90%;">
                                                <option value="">---Select---</option>
                                                <% List<DriverRegistration> registeredDriverList = (List<DriverRegistration>)request.getAttribute("registeredDriverlist"); 
                                            	if(registeredDriverList != null && !registeredDriverList.isEmpty()){
                                	            	for(DriverRegistration driver:registeredDriverList){%>
                                				<option value="<%=driver.getId()%>">
                                				<%if(driver.getFirstName() != null){%>
                                				<%=driver.getFirstName()%><%} %>&nbsp;
                                				<%if(driver.getLastName() != null){%> 
                                				<%=driver.getLastName() %> 
                                				<%} %>/<%if(driver.getPhoneNumber() != null){ %>
                                					<%=driver.getPhoneNumber() %>
                                					<%} %> </option>
                                			<%}} %>
                                                                                                       
                                            </select>
                                        </div>			
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12"><div style="margin-bottom:6px;">Vehicle List</div>
                                            <select name="vehicleId" id="vehicleId" class="form-control2 input-lg" style="width:90%;">
                                                <option value="">---Select---</option>
                                                <% List<VehicleRegistration> registeredVehicleList = (List<VehicleRegistration>)request.getAttribute("registeredVehicleList"); 
                                	if(registeredVehicleList != null && !registeredVehicleList.isEmpty()){
                                		for(VehicleRegistration vehicle:registeredVehicleList){
                                		%>
                               					<option value="<%=vehicle.getId()%>">
                               					<%if(vehicle.getVehicleNumber() != null){ %>
                               						<%=vehicle.getVehicleNumber() %>
                               					<%} %>/
                               					&nbsp;
                               					<%if(vehicle.getVehicleType() != null){ %>
                               						<%=vehicle.getVehicleType()%>
                               					<%} %>
                               					</option>
                               			<%}}%>
                                            </select>
                                        </div> 				
                                    </div>	
                                </div>
								<div class="clearfix margin_10"></div>    
                                <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12">     
                                        <div style="margin-top:6px; font-size:14px; text-align:left; margin-left:18px;">
                                            <button class="btn btn-danger">Map</button>                               
                                        </div>
                                    </div>
                                    <div class="clearfix margin_10"></div>
                                </div>  
							</fieldset>
							</form>
                            <div class="clearfix margin_10"></div>
                        </div>
                    </div>											
                 </div>   
             </div>
       </div>		
  	</div>   
</div>
<div class="clearfix margin_10"></div>	
<div class="hs_copyright"> Copyright &#169; 2015 Trux All Rights Reserved.</div>

<!--main js file start--> 
<script type="text/javascript" src="/trux/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/trux/resources/js/jquery.scrollUp.min.js"></script>
<script type="text/javascript" src='/trux/resources/js/scripts-bottom.js'></script>
</body>
</html>
