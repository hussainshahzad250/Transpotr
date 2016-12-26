<!-- <!DOCTYPE html>
<html lang="en">
<head>
<title>Driver Registration</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="shortcut icon" type="image/ico" href="favicon.ico" />
<link rel="icon" type="image/ico" href="favicon.ico" />

<link rel="stylesheet" href="/trux/resources/css/main.css" media="screen"/>
<script src="/trux/resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){	
	// "scrollTop" plugin
	$.scrollUp();
});
function validate() {
	
	if(($("#driverName" ).val()).length > 0){
		if(($("#phonenumber" ).val()).length > 0){
			if(($("#vehicleNumber" ).val()).length > 0){
				if(($("#vehicleType" ).val()).length > 0){
					return true;
				}else{
					alert('Please enter vehicle type.')
					return false;
				}
			}else{
				alert('Please enter vehicle number.')
				return false;
			}
		}else{
			alert('Please enter Driver phonenumber.')
			return false;
		}
			
	}else{
		alert('Please enter Driver name.');
		return false;
	}
	
}

</script>


</head>
<body> 
<div class="clearfix hs_margin_30"></div>
<div class="hs_page_title">
	<div class="container">
		<div class="row" style="margin-left:0px; margin-right:0; margin-top:5px;">
    		<h3>Driver Registraion</h3>
    		
		</div>
  	</div>
</div>
<div class="container">  
	<div class="row">
    	<div class="col-lg-12 col-md-12 col-sm-12">            
                <div class="row-fluid">		
                    <div class="hs_tab">
                        <ul id="myTab" class="nav nav-tabs">
                           <li class="active"><a href="/trux/admin/register/driverRegistration">Driver Registraion</a></li>
                          <li><a href="/trux/admin/register/vehicleRegistration">Vehicle Registraion</a></li>
                          <li><a href="/trux/admin/register/deviceRegistration">Device Registraion</a></li>
							<li><a href="/trux/admin/truxDetail/registeredDriverList">Register Driver List</a></li>   
							<li><a href="/trux/admin/truxDetail/registeredVehicleList">Register Vehicle List</a></li>
							<li><a href="/trux/admin/truxDetail/vehicleDriverMappingView">Driver Vehicle Map List</a></li> 							
                        </ul>
						<div class="clearfix hs_margin_30"></div>						     			
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade in active" id="services1">
                            <form action="/trux/admin/register/registerDriver" method="post" onsubmit="return validate();" enctype="multipart/form-data">

                            <fieldset class="feildsetarea">
                                <div class="fieldset2">Driver Registraion</div>
                                <div class="clearfix hs_margin_30"></div>  
                                <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12">											                                  
                                        <div class="col-lg-4 col-md-4 col-sm-12"><div style="margin-bottom:6px;">Driver Name</div>
                                            <input type='text' id="driverName" name="driverName"  class="form-control input-lg" style="width:90%;" placeholder="Driver Name"/>
                                        </div>   
										<div class="col-lg-4 col-md-4 col-sm-12"><div style="margin-bottom:6px;">Driver Phonenumber</div>
                                            <input type='text' id="phonenumber" name="phonenumber"  class="form-control input-lg" style="width:90%;" placeholder="Driver Phonenumber"/>
                                        </div>	  
										<div class="col-lg-4 col-md-4 col-sm-12"><div style="margin-bottom:6px;">Vehicle Number</div>
                                            <textarea name="vehicleNumber" id="vehicleNumber" cols="" rows="" class="form-control input-lg" style="width:90%;height:45px;" placeholder="Vehicle Number"></textarea>                                              
                                        </div>       
										<div class="clearfix margin_10"></div>  
										                                            
                                      	<div class="col-lg-4 col-md-4 col-sm-12"><div style="margin-bottom:6px;">Vehicle  Type</div>
                                            <select name="vehicleType" id="vehicleType" class="form-control2 input-lg" style="width: 317px;">
                                                <option value="">---Select---</option>
                                                <option value="Mahindra Champion">Mahindra Champion</option>
                                                <option value="Tata Ace">Tata Ace</option>   
                                                <option value="Bolero Pickup">Bolero Pickup</option>   
                                                <option value="Tata 407">Tata 407</option>   
                                                <option value="Tata 709">Tata 709</option>
                                                <option value="Maruti Eeco">Maruti Eeco</option>                                                       
                                            </select>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-12"><div style="margin-bottom:6px;">Cluster/Hub</div>
                                            <select name="cluster" id="cluster" class="form-control2 input-lg" style="width: 317px;">
                                                <option value="">---Select---</option>
                                                <option value="Kirti Nagar West Delhi">Kirti Nagar West Delhi</option>
                                                <option value="Ghitorini  South Delhi">Ghitorini  South Delhi</option>   
                                                                                                     
                                            </select>
                                        </div>
                                        
                                          
                                        <div class="col-lg-4 col-md-4 col-sm-12"><div style="margin-bottom:6px;">Upload Driver Photo</div>
                                            <input type="file" name="files" id="files" class="form-control2 input-lg" style="width: 317px;">
                                        </div>  
                          
                                        <div class="clearfix margin_10"></div>                                         						
                                    </div>
                                </div>
								<div class="clearfix margin_10"></div>    
                                <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12">     
                                        <div style="margin-top:6px; font-size:14px; text-align:left; margin-left:18px;">
                                            <button type="submit" class="btn btn-danger">Submit</button>                               
                                        </div>
                                    </div>
                                    <div class="clearfix margin_10"></div>
                                </div>                 
                        	</fieldset>

                        	</form>
						</div>
                    </div>											
                 </div>   
             </div>
       </div>		
  	</div>   
</div> 
</body>
</html>
  -->
  <div style="text-align: center;   padding: 15% 11%; height:300%;    width: 100%;">
 <center> <b style="width:100%; height:100%; font-size: 34px;">WELCOME TO TRUX APP</b></center>
 </div>