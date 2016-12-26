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
<!-- Bootstrap core CSS -->
<link href="/trux/resources/css/main.css" rel="stylesheet">
<link href="/trux/resources/css/timepicki.css" rel="stylesheet">
<script src="/trux/resources/js/jquery-1.11.1.min.js"></script>
<script src="/trux/resources/js/jquery-ui.js"></script>
<script type="text/javascript">
$(document).ready(function(){	
	// "scrollTop" plugin
	$.scrollUp();
	
	
	$('#cityValidationLabel').hide();
	$("#timeValidationLabel").hide();
	$("#dateValidationLabel").hide();
	
	
	
	$( "#submitform" ).submit(function( event ) {
		var city = document.getElementById("city").value;
		
		var startdate = document.getElementById("txtStartDate").value;
		
		var ridetime = document.getElementById("timepicker1").value;
		
		if(!(city != null && city.length > 0)){
			$("#cityValidationLabel").show();
			event.preventDefault();
		}
		if(!(startdate != null && startdate.length > 0)){
			$("#dateValidationLabel").show();
			event.preventDefault();
		}
		if(!(ridetime != null && ridetime.length > 0)){
			$("#timeValidationLabel").show();
			event.preventDefault();
		}
		return true;
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
                                    <li><span style="color:#FFFFFF;"><!-- User Name --></span></li>  
                                    <li><a href="javascript:void(0);"><!-- <button class="btn btn-default">Change Password </button>--> &nbsp;</a></li> 
                                    <li><a href="javascript:void(0);"><!-- <button class="btn btn-default"><!-- LogOut </button>-->     &nbsp; </a></li>            
                                </ul>
                            </div>
                        </div>  
                        	<div class="col-lg-9 col-md-9 col-sm-12">
              				<button type="button" class="hs_nav_toggle navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">Menu<i class="fa fa-bars"></i></button>
                            <nav>
                                <ul class="hs_menu collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                    <li class="active"><a href="http://www.truxapp.com/index.html;" class="linkmenu">Home</a></li>
                                  <!--   <li><a href="javascript:void(0);">About Us</a></li>									
									<li><a href="javascript:void(0);"> Articles</a></li>                          	
                                    <li><a href="javascript:void(0);">Blog</a></li> -->
                                    <li><a href="http://www.truxapp.com/terms_conditions.html">Terms & Conditions</a></li>
                                    <li><a href="http://www.truxapp.com/privacy_policy.html">Privacy Policy</a></li>
                                    <li><a href="http://www.truxapp.com/contact_us.html">Contact Us</a></li>
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
<div style="width:100%; float:left; margin:auto; margin-top:-2%; ">
<div id="carousel-example-generic" class="carousel" data-ride="carousel"> 
  <!-- Indicators -->
  	<ol class="carousel-indicators">
		<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    	<li data-target="#carousel-example-generic" data-slide-to="1"></li>
  	</ol>  
  	<!-- Wrapper for slides -->
  	<div class="carousel-inner">
    	<div class="item active"> <img class="animated" src="/trux/resources/images/slider/slider1.png" alt="...">
      		<div class="carousel-caption">
        		<h1 class="hs_slider_title animated bounceInDown">EASIEST WAY TO BOOK A VEHICLE</h1>
        		<p class="lead animated pulse">EASIEST WAY TO BOOK A VEHICLE EASIEST WAY TO BOOK A VEHICLE</p>
        	</div>
    	</div>
    	<div class="item"> <img class="animated" src="/trux/resources/images/slider/slider2.png" alt="...">
      		<div class="carousel-caption">
        		<h1 class="hs_slider_title animated bounceInDown">EASIEST WAY TO BOOK A VEHICLE</h1>
        		<p class="lead animated pulse">EASIEST WAY TO BOOK A VEHICLE EASIEST WAY TO BOOK A VEHICLE</p>
       		</div>
    	</div>
  	</div>
</div>
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
                        <!--option value="12">Lucknow</option>
                        <option value="13">Mumbai</option>
                        <option value="14">Noida</option-->                               
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
                <div class="row-fluid">	                       
                    <fieldset class="fieldset2">
						<div class="col-lg-12 col-md-12 col-sm-12" style="background-color:#f1f1f1;">
                            <h3> Book a Vehicle</h3> 
                        </div> 
                       
                        <div class="clearfix hs_margin_30"></div>
                        <form id="submitform" action="/trux/booking/ridebooking" method="POST">  
                        <div class="row">
                            <div class="col-lg-4 col-md-4 col-sm-12">											                                  
                                <div style="margin-bottom:6px;">From</div>
                                <select name="city" id="city" class="input-lg" style="width:100%;">
                                    <option value="">--Select Business Type--</option>
                                    <option value="Delhi">Delhi</option>
                                    <!--option value="12">Lucknow</option>
                                    <option value="13">Mumbai</option>
                                    <option value="14">Noida</option-->                               
                                </select> 
							<div id="cityValidationLabel" style="width:100%; float:left; margin:auto; color:#FF0000;">Please enter a valid email address</div>								
							</div>
							<div class="col-lg-4 col-md-4 col-sm-12">                               
                                <div style="margin-bottom:6px;">Pickup Date</div>
                                <input type='text' id="txtStartDate" name="bookingDate"  class="form-control input-lg" placeholder="YY-MM-DD"/>                               
								<div id="dateValidationLabel" style="width:100%; float:left; margin:auto; color:#FF0000;">Please enter a valid date</div>								
							</div>
							<div class="col-lg-4 col-md-4 col-sm-12">                               
                                <div style="margin-bottom:6px;">Pickup Time</div>
	    							<input id="timepicker1" type="text" name="bookingTime" class="form-control input-lg" style="width:100%;"placeholder="00/00/00" />
								<div id="timeValidationLabel" style="width:100%; float:left; margin:auto; color:#FF0000;">Please enter a valid time</div>								
                            </div>                       
                        </div>
                        <div class="clearfix margin_10"></div>    
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12">     
                                <div style="margin-top:6px; font-size:14px; text-align:left; margin-left:0px;">
                                    <button type="submit" class="btn btn-danger btn-lg">Search</button>                               
                                </div>
                            </div>
                            <div class="clearfix margin_10"></div>
                        </div> 
                      </form>
                  </fieldset>
			</div>
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
          buttonImage:"/trux/resources/images/calendar.png", 
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
