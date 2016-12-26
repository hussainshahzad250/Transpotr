<!DOCTYPE html>
<%@page import="com.trux.model.DriverRegistration"%>
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
<div class="hs_page_title">
	<div class="container">
		<div class="row" style="margin-left:0px; margin-right:0; margin-top:5px;">
    		<h3>Registered Driver List</h3>
    		
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
                          <li><a href="/trux/admin/register/driverRegistration">Driver Registraion</a></li>
                          <li><a href="/trux/admin/register/vehicleRegistration">Vehicle Registraion</a></li>
                          <li><a href="/trux/admin/register/deviceRegistration">Device Registraion</a></li>  
							<li class="active"><a href="/trux/admin/truxDetail/registeredDriverList">Register Driver List</a></li>   
							<li><a href="/trux/admin/truxDetail/registeredVehicleList">Register Vehicle List</a></li>
							<li><a href="/trux/admin/truxDetail/vehicleDriverMappingView">Driver Vehicle Map List</a></li> 							
							<li><a href="/trux/admin/truxDetail/mapDriverVehicle">Driver Vehicle Map</a></li>                      
                        </ul>
						<div class="clearfix hs_margin_30"></div>						     			
                        <div id="myTabContent" class="tab-content">
						
						<div>
                            <fieldset class="feildsetarea">
                                <div class="fieldset2">Register Driver List</div>
                                <div class="clearfix hs_margin_30"></div> 
                                <% List<DriverRegistration> registeredDriverList = (List<DriverRegistration>)request.getAttribute("registeredDriverlist"); 
                                	if(registeredDriverList != null && !registeredDriverList.isEmpty()){
                                		for(DriverRegistration driver:registeredDriverList){%>
                                			 <div class="row">
                                    <div class="col-lg-4 col-md-4 col-sm-12">											                                  
                                        <div class="col-lg-12 col-md-12 col-sm-12">
                                            First Name : <%= driver.getFirstName() %>
                                        </div>   
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Address : <%=driver.getAddress() %>
                                        </div>	
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Country : <%=driver.getCountry() %>
                                        </div> 
                                    </div>  
                                    <div class="col-lg-4 col-md-4 col-sm-12">                                  
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Middle Name : <%=driver.getMiddleName() %>
                                        </div>		
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											City : <%=driver.getCity() %>
                                        </div> 	
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Gender : <%=driver.getGender() %>                                          
                                        </div> 						
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12">
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Last name : <%=driver.getLastName() %>
                                        </div>
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											State: <%=driver.getState() %>
                                        </div>
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Phone Number : <%=driver.getPhoneNumber() %>
                                        </div>
                                    </div>
                                </div><div class="clearfix margin_10">--------------------------------------------------------------------------------------------------</div>
                                		<%}
                                	%>
                               			 		
                                	<%}else{%>
                                	<div class="clearfix margin_10">No result found</div>
                                	<%}
                                
                                %>
                        	</fieldset>
						</div>
                    </div>											
                 </div>   
             </div>
       </div>		
  	</div>   
</div>
<div class="clearfix margin_10"></div>	
</body>
</html>
