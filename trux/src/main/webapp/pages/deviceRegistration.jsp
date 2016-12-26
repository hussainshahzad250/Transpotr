<!DOCTYPE html>
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
    		<h3>Device Registration</h3>
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
                          <li class="active"><a href="/trux/admin/register/deviceRegistration">Device Registraion</a></li>    
							<li><a href="/trux/admin/truxDetail/registeredDriverList">Registered Driver List</a></li>   
							<li><a href="/trux/admin/truxDetail/registeredVehicleList">Registered Vehicle List</a></li> 
							<li><a href="/trux/admin/truxDetail/vehicleDriverMappingView">Registered Device List</a></li>
							<li><a href="/trux/admin/truxDetail/vehicleDriverMappingView">Registered Device List</a></li>							 							
                        </ul>
						<div class="clearfix hs_margin_30"></div>						     			
                        <div id="myTabContent" class="tab-content">

                        <div>
                        <form action="/trux/admin/register/registerDevice" method="post">
							<fieldset class="feildsetarea">
                                <div class="fieldset2">Device Registraion</div>
                                <%if(request.getAttribute("errorMessage") != null){ %>
                                
                                <div id="cityValidationLabel" style="width:100%; float:left; margin:auto; color:#FF0000;">
                                
                                <%=request.getAttribute("errorMessage") %>
                                
                                </div>
                                <%} %>
                                <div class="clearfix hs_margin_30"></div>  
                                <div class="row">
                                    <div class="col-lg-4 col-md-4 col-sm-12">											                                  
                                        <div class="col-lg-12 col-md-12 col-sm-12"><div style="margin-bottom:6px;">Device Unique Id</div>
                                            <input type='text' id="deviceId" name="deviceId"  class="form-control input-lg" style="width:90%;" placeholder="Device Unique Id"/>
                                        </div>   
                                       	
                                    </div>  
                                </div>
								<div class="clearfix margin_10"></div>    
                                <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12">     
                                        <div style="margin-top:6px; font-size:14px; text-align:left; margin-left:18px;">
                                            <button class="btn btn-danger">Register</button>                               
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
</body>
</html>
