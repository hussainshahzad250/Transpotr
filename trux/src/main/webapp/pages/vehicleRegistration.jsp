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
    		<h3>Vehicle Registration</h3>
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
                      </ul>
						<div class="clearfix hs_margin_30"></div>						     			
                        <div id="myTabContent" class="tab-content">

                        <div>
                        <form action="/trux/admin/register/registerVehicle" method="post">
							<fieldset class="feildsetarea">
                                <div class="fieldset2">Vehicle Registraion</div>
                                <div class="clearfix hs_margin_30"></div>  
                                <div class="row">
                                    <div class="col-lg-4 col-md-4 col-sm-12">											                                  
                                        <div class="col-lg-12 col-md-12 col-sm-12"><div style="margin-bottom:6px;">Vehicle Registraion Number</div>
                                            <input type='text' id="vehicleNumber" name="vehicleNumber"  class="form-control input-lg" style="width:90%;" placeholder="Vehicle Registraion Number"/>
                                        </div>   
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12"><div style="margin-bottom:6px;">Owner Name</div>
                                            <input type='text' id="ownerName" name="ownerName"  class="form-control input-lg" style="width:90%;" placeholder="Owner Name"/>
                                        </div>	
                                    </div>  
                                    <div class="col-lg-4 col-md-4 col-sm-12">                                  
                                        <div class="col-lg-12 col-md-12 col-sm-12"><div style="margin-bottom:6px;">Vehicle  Type</div>
                                            <select name="vehicleType" id="vehicleType" class="form-control2 input-lg" style="width:90%;">
                                                <option value="">---Select---</option>
                                                <option value="Mahindra Champion">Mahindra Champion</option>
                                                <option value="Tata Ace">Tata Ace</option>   
                                                <option value="Maximo Pickup">Maximo Pickup</option>   
                                                <option value="Tata 407">Tata 407</option>   
                                                <option value="Tata 709">Tata 709</option>                                                       
                                            </select>
                                        </div>			
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12"><div style="margin-bottom:6px;">Owner Phone Number</div>
                                            <input type='text' id="ownerPhoneNumber" name="ownerPhoneNumber"  class="form-control input-lg" style="width:90%;" placeholder="Owner Phone Number"/>
                                        </div> 				
                                    </div>	
                                    <div class="col-lg-4 col-md-4 col-sm-12">                                  
                                        <div class="col-lg-12 col-md-12 col-sm-12"><div style="margin-bottom:6px;">Model</div>
                                            <input type='text' id="model" name="model"  class="form-control input-lg" style="width:90%;" placeholder="Model"/>
                                        </div>		
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-12 col-md-12 col-sm-12"><div style="margin-bottom:6px;">Owner Address</div>
                                            <textarea name="ownerAddress" id="ownerAddress" cols="" rows="" class="form-control input-lg" style="width:90%;height:45px;" placeholder="Address"></textarea>
                                        </div> 				
                                    </div>									
                                </div>
								<div class="clearfix margin_10"></div>    
                                <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12">     
                                        <div style="margin-top:6px; font-size:14px; text-align:left; margin-left:18px;">
                                            <button class="btn btn-danger">Submit</button>                               
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
