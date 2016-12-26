<!DOCTYPE html>
<%@page import="com.trux.model.CustomerBookingDetails"%>
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
<!-- Bootstrap core CSS -->
<link href="/trux/resources/css/main.css" rel="stylesheet">
<link href="/trux/resources/css/timepicki.css" rel="stylesheet">
<script src="/trux/resources/js/jquery-1.11.1.min.js"></script>
<script src="/trux/resources/js/jquery-ui.js"></script>
<script type="text/javascript">
$(document).ready(function(){	
	// "scrollTop" plugin
	$.scrollUp();
	
	
	 $("#travellerDetail").hide();
	 
	 $("#reviewDiv").hide();
	 
	 $('#emailValidationLabel').hide();
	 
	$("#continue").click(function(){
		
		var email = document.getElementById("Email").value;
		
		if(email != null && email != undefined && email.length > 0){
			 $("#travellerDetail").show();
		     $("#emaildiv").hide();
		     
		     $('#emailLabel').removeClass('active');
		     $('#travellerDetailsLabel').addClass('active');
		}else{
			$('#emailValidationLabel').show();
		}
    });
	
	$("#travellerContinue").click(function(){
		
		var travellerName = document.getElementById("username").value;
		
		var travellerNumbere = document.getElementById("phonenumber").value;
		
		var vehicleType = document.getElementById("vehicleType").value;
		
		var isValid = false;
		
		
		dropLocationValidationLabel

		pickupLocationValidationLabel


		vehicleTypeValidationLabel

		

		phoneValidationLabel
		if(travellerName != null && travellerName != undefined && travellerName.length > 0){
			isValid = true;
		}else{
			isValid = false;
			$("#nameValidationLabel").show();
		}
		
		if()
		
		document.getElementById("travellerName").innerHTML = travellerName;
		
		var fromLocation = document.getElementById("fromLocation").value;
		
		document.getElementById("fromLocationLbl").innerHTML = fromLocation;
		
		 $("#travellerDetail").hide();
	     $("#reviewDiv").show();
	     
	     $('#travellerDetailsLabel').removeClass('active');
	     $('#reviewLabel').addClass('active');
	
	});
});
</script>

</head>
<body>
<div style="width:100%; float:left; margin:auto;position:relative; z-index:999999;">
<header id="header" class="gradient4Color">	
	<div class="container">
        <div class="row">
          	<div class="col-lg-12 col-md-12 col-sm-12 clearfix">
            	<div class="col-lg-3 col-md-3 col-sm-12">
                	<div id="hs_logo"> <a> <img src="/trux/resources/images/logo2.png" alt=""> </a> </div>
              <!-- #logo --> 
            	</div>
            	<div class="col-lg-9 col-md-9 col-sm-12">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12">
                            <div class="top_right">
                                <ul>
                                    <li><span style="color:#FFFFFF;">User Name</span></li>  
                                    <li><a href="javascript:void(0);"><button class="btn btn-default">Change Password</button></a></li> 
                                    <li><a href="javascript:void(0);"><button class="btn btn-default">LogOut</button>      </a></li>            
                                </ul>
                            </div>
                        </div>  
						<div class="col-lg-9 col-md-9 col-sm-12">
              				<button type="button" class="hs_nav_toggle navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">Menu<i class="fa fa-bars"></i></button>
                            <nav>
                                <ul class="hs_menu collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                    <li class="active"><a href="javascript:void(0);" class="linkmenu">Home</a></li>
                                  <li><a href="javascript:void(0);">About Us</a></li>									
								  <li><a href="javascript:void(0);"> Articles</a></li>                          	
                                  <li><a href="javascript:void(0);">Blog</a></li>
                                  <li><a href="javascript:void(0);">Testimonials</a></li>
                                  <li><a href="javascript:void(0);">Careers</a></li>
                                  <li><a href="javascript:void(0);">Contact Us</a></li>
                              </ul>
                            </nav>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3">
                            <div class="hs_social">
                                <ul>
                                    <li><a href="javascript:void(0);" style="color:#ffffff; background-color:#314987"><i class="fa fa-facebook"></i></a></li>
                                    <li><a href="javascript:void(0);" style="color:#ffffff; background-color:#00b2e4"><i class="fa fa-twitter"></i></a></li>
                                    <li><a href="javascript:void(0);" style="color:#ffffff; background-color:#d01820"><i class="fa fa-google-plus"></i></a></li> 
                                    <li><a href="javascript:void(0);" style="color:#ffffff; background-color:#329edc"><i class="fa fa-linkedin"></i></a></li>             
                                </ul>
                            </div>         
                        </div>
					</div>
            	</div>            	
          	</div>
          <!-- .col-md-12 --> 
        </div>
        <!-- .row --> 
	</div>
  <!-- .container -->   
</header>
</div>
<div style="width:100%; float:left; margin:auto;"> 
<div class="hs_page_title">
	<div class="container">
		<div class="row" style="margin-left:0px; margin-right:0; margin-top:5px;">
    		<h3>Easiest way to book a Vehicle</h3>
    		<ul>
      			<li>
					<select name="" id="" class="input-lg" style="width:100%;">
                        <option value="">Select City</option>
                        <option value="1">Delhi</option>
                        <option value="12">Lucknow</option>
                        <option value="13">Mumbai</option>
                        <option value="14">Noida</option>                               
                	</select>
				</li>      		
    		</ul>
		</div>
  	</div>
</div>
</div>
<div class="clearfix hs_margin_30"></div>
<div class="container">  
	<div class="row">
    	<div class="col-lg-12 col-md-12 col-sm-12"> 
            <form action="/trux/booking/book" method="POST">
                <div class="hs_tab">
					<div class="breadcrumb2 flat">
                        <span class="active" id="emailLabel">1. Email</span>
                        <span id="travellerDetailsLabel">2. Traveller Details</span>
                        <span id="reviewLabel">3. Review Details</span>    
						<span></span>                    </div>                    
								     			
  <!--////////////////Email Address start div/////////////////////////-->					
                    <fieldset class="fieldset2 col-lg-12" id="emaildiv"> 						
                        <div class="row">
                            <div class="col-lg-4 col-md-4 col-sm-12">											                                  
                                <div style="margin-bottom:6px;">Email Address</div>
                                <input type='text' id="Email" name="email"  class="form-control input-lg" style="width:100%;" placeholder="Email Address"/><br/>
                                <div id="emailValidationLabel" style="width:100%; float:left; margin:auto; color:#FF0000;">Please enter a valid email address</div>
                                <div style="margin-top:4px; text-align:left;"><input name="" class="" type="checkbox" value="">&nbsp;I have an Trux account</div>
                            </div>  
                            <div class="col-lg-4 col-md-4 col-sm-12">     
                                <div style="margin-top:27px; font-size:14px; text-align:left; margin-left:0px;">
                                    <div class="btn btn-danger btn-lg" id="continue" style="background: #e2e2e2;">Continue</div>                               
                                </div>
                            </div>
                        </div>
                        <div class="clearfix margin_10"></div>   
                    </fieldset>

                    <!--////////////////Email Address end div/////////////////////////-->	
					<!--////////////////Traveller's details start div/////////////////////////-->	
  							
                    <fieldset class="fieldset2 col-lg-12" id="travellerDetail">                                    
                        <div class="col-lg-12 col-md-12 col-sm-12" style="background-color:#f1f1f1;">
                            <h3>Traveller's details</h3> 
                        </div>  
                        <div class="clearfix margin_10"></div>
                        <div class="row">
                            <div class="col-lg-4 col-md-4 col-sm-12">											                                  
                                <div style="margin-bottom:6px;">Name</div>
                                <input type='text' id="username" name="username"  class="form-control input-lg" style="width:100%;" placeholder="Name"/>
                                <div id="nameValidationLabel" style="width: 100%; float: left; margin: auto; color: rgb(255, 0, 0);">Please enter a valid username</div>										
                            </div>  
                            <div class="col-lg-4 col-md-4 col-sm-12">											                                  
                                <div style="margin-bottom:6px;">Phone</div>
                                <input type='text' id="phonenumber"  name="phonenumber" class="form-control input-lg" style="width:100%;" placeholder="Phone"/>
                                 <div id="phoneValidationLabel" style="width: 100%; float: left; margin: auto; color: rgb(255, 0, 0);">Please enter a valid phone number</div>										
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-12">											                                  
                                <div style="margin-bottom:6px;">Vehicle Category</div>
                                <select name="vehicleType" id="" class="input-lg" style="width:100%;">
                                   <option value="Mahindra Champion">Mahindra Champion</option>
                                   <option value="Tata Ace">Tata Ace</option>   
                                   <option value="Maximo Pickup">Maximo Pickup</option>   
                                   <option value="Tata 407">Tata 407</option>   
                                   <option value="Tata 709">Tata 709</option>                                                                            
                                </select>
                                <div id="vehicleTypeValidationLabel" style="width: 100%; float: left; margin: auto; color: rgb(255, 0, 0);">Please select a valid vehicle type.</div>							
                            </div>
                        </div>
                        <div class="clearfix margin_10"></div>  
                        
                        <div class="col-lg-12 col-md-12 col-sm-12" style="background-color:#f1f1f1;">
                            <h3>Pickup details</h3> 
                        </div>  
                        <div class="row">
                            <div class="clearfix margin_10"></div>                                       
                            <div class="col-lg-4 col-md-4 col-sm-12">											                                  
                                <div style="margin-bottom:6px;">Pickup Locality</div>
                                <input type='text' id=""   class="form-control input-lg" style="width:100%;" placeholder="Pickup Locality"/>											
                            </div>  
                            <div class="col-lg-8 col-md-8 col-sm-12">											                                  
                                <div style="margin-bottom:6px; margin-left:4px;">Pickup Address</div>
                                <textarea name="fromLocation" id="fromLocation" class="form-control input-lg" placeholder="Pickup Address" style="margin-left:4px;height:45px;width:100%;" ></textarea>
                                <div id="pickupLocationValidationLabel" style="width: 100%; float: left; margin: auto; color: rgb(255, 0, 0);">Please enter a valid pickup address</div>								
                            </div>
                        </div>                                        
                        <div class="clearfix margin_10"></div>  


                        <div class="col-lg-12 col-md-12 col-sm-12" style="background-color:#f1f1f1;">
                            <h3>Drop details</h3> 
                        </div>  
                        <div class="row">
                            <div class="clearfix margin_10"></div>                                   
                            <div class="col-lg-4 col-md-4 col-sm-12">											                                  
                                <div style="margin-bottom:6px;">Drop Locality</div>
                                <input type='text' id=""  class="form-control input-lg" style="width:100%;" placeholder="Drop Locality"/>											
                            </div>  
                            <div class="col-lg-8 col-md-8 col-sm-12">											                                  
                                <div style="margin-bottom:6px; margin-left:4px;">Drop Address</div>
                                <textarea name="toLocation" class="form-control input-lg" placeholder="Drop Address" style="margin-left:4px;height:45px;width:100%;" ></textarea>
                                <div id="dropLocationValidationLabel" style="width: 100%; float: left; margin: auto; color: rgb(255, 0, 0);">Please enter a valid drop address</div>								
                            </div>                                            
                            <div class="clearfix margin_10"></div>   
                            <div class="col-lg-4 col-md-4 col-sm-12">     
                                <div style="margin-top:25px; font-size:14px; text-align:left; margin-left:0px;">
                                    <div class="btn btn-danger btn-lg" id="travellerContinue">Continue</div>                               
                                </div>
                            </div>
                        </div>
                    </fieldset>

					<!--////////////////Traveller's details end div//////////////////////-->
					<!--////////////////Review Details start div/////////////////////////-->
                       							
                    <fieldset class="fieldset2 col-lg-12" id="reviewDiv"> 
                        <div class="col-lg-12 col-md-12 col-sm-12" style="background-color:#f1f1f1;">
                            <h3>Review Details</h3> 
                        </div>
                        <div class="clearfix margin_10"></div>
                        <div class="row">
                            <div class="col-lg-2 col-md-2 col-sm-12">											                                  
                                Traveller
                            </div> 
                            <div class="col-lg-10 col-md-11 col-sm-12">											                                  
                                <label id="travellerName"></label> 
                            </div> 
                            <div class="clearfix margin_10"></div>
                            <div class="col-lg-2 col-md-2 col-sm-12">											                                  
                                Pickup From
                            </div> 
                            <div class="col-lg-10 col-md-11 col-sm-12">											                                  
                                <label id="fromLocationLbl"></label>
                            </div>
                            <div class="clearfix margin_10"></div>
                            <div class="col-lg-2 col-md-2 col-sm-12">											                                  
                                Pickup On
                            </div> 
                            <div class="col-lg-10 col-md-11 col-sm-12">											                                  
                                <label id="rideTime">
								<%CustomerBookingDetails customerBookingDetails = (CustomerBookingDetails)request.getSession().getAttribute("customerBookingDetails");%>
		
							<%=customerBookingDetails.getRideTime()%>
							</label>
                            </div>
                            <div class="clearfix margin_10"></div>
                            <div class="col-lg-2 col-md-2 col-sm-12">											                                  
                                Car Category
                            </div> 
                            <div class="col-lg-10 col-md-11 col-sm-12">											                                  
                                <label id="vehicleCat"></label>
                            </div>
                            <div class="clearfix margin_10"></div>
                            <div class="col-lg-2 col-md-2 col-sm-12">											                                  
                                <div style="margin-top:10px;">Coupon Code:</div>
                            </div> 
                            <div class="col-lg-10 col-md-11 col-sm-12">
                                <div class="row" style="margin-left:-12px;">
                                    <div class="col-lg-3 col-md-3 col-sm-12">	
                                        <input type='text' id=""  class="form-control input-lg" style="width:100%;" placeholder="Coupon code"/>													
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-12">	
                                        <button class="btn btn-danger btn-lg">Verify</button> 
                                    </div>		
                                    <div class="col-lg-12 col-md-12 col-sm-12">	
                                        <div style="margin-top:4px; text-align:left;"><input name="" class="" type="checkbox" value="">
                                            &nbsp;I've read and I accept the <a href="javascript:void(0);">terms and conditions.</a>
                                        </div>	
                                    </div>										
                                </div>	
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-12">     
                                <div style="margin-top:25px; font-size:14px; text-align:left; margin-left:0px;">
                                    <button type="submit" class="btn btn-danger btn-lg">Book</button>                               
                                </div>
                            </div>
                        </div>
                        <div class="clearfix margin_10"></div>   
                    </fieldset>
                    <!--////////////////Review Details end div/////////////////////////-->									
             	</div>  
            </form> 
       </div>		
  	</div>   
</div>
<div class="clearfix margin_10"></div>	
<footer class="footer">
        <div class="container">
            <div class="row" style="margin-top:2%; margin-bottom:2%;">
                <div class="col-lg-9 col-md-12 col-sm-12">   
                    <div class="row" style="margin-top:0%;">
                        <div class="col-lg-3 col-md-12 col-sm-12"> 
							<ul class="footerbullet">
								<li><a href="#" style="color:#ffffff;">About Us </a></li>
								<li><a href="#" style="color:#ffffff;">FAQs </a></li>
								<li><a href="#" style="color:#ffffff;">Articles </a></li>
							</ul>     	
                        </div>
                        <div class="col-lg-3 col-md-12 col-sm-12">                            
							<ul class="footerbullet">
								<li><a href="#" style="color:#ffffff;">Blog </a></li>
								<li><a href="#" style="color:#ffffff;">Testimonials </a></li>
								<li><a href="#" style="color:#ffffff;">Careers </a></li>
							</ul>       	
                        </div>
                        <div class="col-lg-3 col-md-12 col-sm-12">                           
							<ul class="footerbullet">
								<li><a href="#" style="color:#ffffff;">Contact Us</a></li>
								<li><a href="#" style="color:#ffffff;">Terms and Conditions </a></li>
								<li><a href="#" style="color:#ffffff;">Privacy Policy</a></li>
							</ul>          	
                        </div>
                        <div class="col-lg-3 col-md-12 col-sm-12">                             
							<ul class="footerbullet">
								<li><a href="#" style="color:#ffffff;">Offers </a></li>
								<li><a href="#" style="color:#ffffff;">Fares </a></li>
								<li><a href="#" style="color:#ffffff;">Invoice </a></li>
							</ul>           	
                        </div>
                    </div>       	
                </div>
                <div class="col-lg-3 col-md-12 col-sm-12">   
                    <div style="width:100%; float:left; margin:auto;font-size:18px; font-weight:bold;">Contact Us</div> <br/>
					<ul>
						<li style="list-style:none; margin-left:-18%;">
                            Lorem Ipsum is simply dummy text,<br>
                            Lorem Ipsum is simply dummy text<br>
                            Lorem Ipsum is simply dummy text<br>
						</li>
					</ul>
                </div>
          </div>
        </div>	
</footer>
<div class="hs_copyright"> Copyright &#169; 2015 Trux All Rights Reserved.</div>

<script src="/trux/resources/js/timepicki.js"></script>
<script>
	$('#timepicker1').timepicki();
</script>
<script type="text/javascript">
$(function() {
  $('#txtStartDate, #txtEndDate').datepicker({
          showOn: 'both',
          buttonImage:"images/calendar.png", 
          buttonImageOnly: true,
          dateFormat: "yy-mm-dd", 
          beforeShow: customRange,
          mandatory: true
  });
});
function onchangeCheckInDate() {
	if ($('#txtEndDate').val() != "Check-in Date" && $("#txtStartDate").datepicker("getDate") > $("#txtEndtDate").datepicker("getDate")) {
		$('#txtStartDate').val($('#txtStartDate').val());
	}
}
function onchangeCheckOutDate() {
	if ($('#txtStartDate').val() != "Check-out Date" && $("#txtStartDate").datepicker("getDate") > $("#txtEndDate").datepicker("getDate")) {
		$('#txtStartDate').val($('#txtEndtDate').val());
	}
}
function customRange(input) { 
	var date  = new  Date();
	var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();           
	return { 
	minDate: (input.id == "#txtEndtDate" ?
		$("#txtStartDate").datepicker("getDate") : 
		new Date(y, m, d)), 
	maxDate: (input.id == "star.datepickerer" ? 
		$("#txtEndDate").datepicker("getDate") : 
		null)
	 };
}
function blank(a) {
	if (a.value == a.defaultValue) {
		a.value="";
	}
}
function unblank(a) {
	if (a.value === "") {
		a.value = a.defaultValue;
	}
}
</script>

<script type="text/javascript" src="/trux/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/trux/resources/js/jquery.scrollUp.min.js"></script>
<script type="text/javascript" src='/trux/resources/js/scripts-bottom.js'></script>

</body>
</html>
