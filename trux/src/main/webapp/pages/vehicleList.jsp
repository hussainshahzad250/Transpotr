<!DOCTYPE html>
<%@page import="com.trux.model.VehicleRegistration"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<title>Trux</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body> 
<fieldset class="borderManagerwithoutbackground">
<div class="hs_page_title">
	<div class="container">
		<div class="row" style="margin-left:0px; margin-right:0; margin-top:5px; border-radius:10px 10px 7px 7px;">
    		<h3>Registered Vehicle List</h3>
    		
		</div>
  	</div>
</div>
<div class="clearfix hs_margin_30"></div>
<div class="container">  
	<div class="row">
    	<div class="col-lg-12 col-md-12 col-sm-12"  style="border-radius:10px 10px 7px 7px;">            
                <div class="row-fluid">		
                    <div class="hs_tab">
                        <ul id="myTab" class="nav nav-tabs">
                         <li><a href="/trux/admin/register/driverRegistration">Driver Registraion</a></li>
                          <li><a href="/trux/admin/register/vehicleRegistration">Vehicle Registraion</a></li> 
                          <li><a href="/trux/admin/register/deviceRegistration">Device Registraion</a></li>   
							<li><a href="/trux/admin/truxDetail/registeredDriverList">Register Driver List</a></li>   
							<li  class="active"><a href="/trux/admin/truxDetail/registeredVehicleList">Register Vehicle List</a></li>                          
                        </ul>
						<div class="clearfix hs_margin_30"></div>						     			
                        <div id="myTabContent" class="tab-content">
                            
						<div>
							<fieldset class="feildsetarea table-responsive">
                                <div class="fieldset2">Register Vehicle List</div>
                                <div class="clearfix hs_margin_30"></div>
                                <% List<VehicleRegistration> registeredVehicleList = (List<VehicleRegistration>)request.getAttribute("registeredVehicleList"); 
                                	if(registeredVehicleList != null && !registeredVehicleList.isEmpty()){
                                		for(VehicleRegistration vehicle:registeredVehicleList){
                                		%>
                               				 <div class="row">
                                    <div class="col-lg-4 col-md-4 col-sm-12">											                                  
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Vehicle Registraion Number  : <%=vehicle.getVehicleNumber() %>                                           
                                        </div>   
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Owner Name : <%=vehicle.getOwnerName() %>
                                        </div>	
                                    </div>  
                                    <div class="col-lg-4 col-md-4 col-sm-12">                                  
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Vehicle  Type : <%=vehicle.getVehicle_type() %>
                                        </div>			
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Owner Phone Number : <%=vehicle.getOwnerPhoneNumber() %>
                                        </div> 				
                                    </div>	
                                    <div class="col-lg-4 col-md-4 col-sm-12">                                  
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Model : <%=vehicle.getVehicleModel() %>
                                        </div>		
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Owner Address : <%=vehicle.getOwnerAddress() %>
                                        </div> 				
                                    </div>									
                                </div>
								<div class="clearfix margin_10">----------------------------------------------------------------------------------------------------</div> 	
									
                                	<%}}else{%>
                                		
                                		No Result Found
                                	<%}
                                %>
                               
							</fieldset>
                            <div class="clearfix margin_10"></div>
                        </div>
                    </div>											
                 </div>   
             </div>
       </div>		
  	</div>   
</div>
<div class="clearfix margin_10"></div>	
</fieldset>
</body>
</html>
