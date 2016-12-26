<!DOCTYPE html>
<%@page import="com.trux.model.DriverVehicleComboDetails"%>
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
    		<h3>Driver Vehicle Mapping</h3>
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
							<li><a href="/trux/admin/truxDetail/registeredDriverList">Register Driver List</a></li>   
							<li><a href="/trux/admin/truxDetail/registeredVehicleList">Register Vehicle List</a></li>
							<li class="active"><a href="/trux/admin/truxDetail/vehicleDriverMappingView">Driver Vehicle Map List</a></li> 							
                        </ul>
						<div class="clearfix hs_margin_30"></div>						     			
                        <div id="myTabContent" class="tab-content">
						
						<div>
                            <fieldset class="feildsetarea table-responsive">
                                <div class="fieldset2">Register Driver List</div>
                                <div class="clearfix hs_margin_30"></div> 
                                <% List<DriverVehicleComboDetails> driverVehicleComboDetailList = (List<DriverVehicleComboDetails>)request.getAttribute("driverVehicleComboDetails"); 
                                	if(driverVehicleComboDetailList != null && !driverVehicleComboDetailList.isEmpty()){
                                		for(DriverVehicleComboDetails driverVehicleDetails:driverVehicleComboDetailList){%>
                                			 <div class="row">
                                    <div class="col-lg-4 col-md-4 col-sm-12">											                                  
                                        <div class="col-lg-12 col-md-12 col-sm-12">
                                            Driver First Name : <%= driverVehicleDetails.getDriverFirstName() %>
                                        </div>   
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Driver Last Name : <%=driverVehicleDetails.getDriverLastName() %>
                                        </div>	
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Driver Phone Number : <%=driverVehicleDetails.getDriverPhoneNumber() %>
                                        </div> 
                                    </div>  
                                    <div class="col-lg-4 col-md-4 col-sm-12">                                  
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Vehicle Number : <%=driverVehicleDetails.getVehicleNumber() %>
                                        </div>		
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12">
											Vehicle type : <%=driverVehicleDetails.getVehicleType() %>
                                        </div> 	
                                        <div class="clearfix margin_10"></div>
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
<div class="hs_copyright"> Copyright &#169; 2015 Trux All Rights Reserved.</div>

<!--main js file start--> 
<script type="text/javascript" src="/trux/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/trux/resources/js/jquery.scrollUp.min.js"></script>
<script type="text/javascript" src='/trux/resources/js/scripts-bottom.js'></script>
</body>
</html>
