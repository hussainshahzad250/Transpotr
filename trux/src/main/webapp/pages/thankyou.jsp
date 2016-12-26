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
            <div class="row" style="margin-left:0px; margin-right:0; margin-top:5px; height:40px;">
                &nbsp;
            </div>
        </div>
    </div>
</div>
<div class="clearfix hs_margin_30"></div>
<div class="container">  
	<div class="row">
    	<div class="col-lg-12 col-md-12 col-sm-12"> 
            <div style="text-align:center; margin-top:5%; height:400px;">				
				<h1>Thank you for using Trux</h1> <br>
				We have sent you an email and a SMS confirming your booking details. Hope you have a pleasant drive. <br><br>
				<strong>Your Reference No. :</strong> <%=request.getAttribute("bookingId") %><br><br>
				Kindly use this your Reference Number in all further correspondence with Trux. Thank you. 
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
          buttonImage:"images/calendar.png", 
          buttonImageOnly: true,
          dateFormat: "mm/dd/yy", 
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
