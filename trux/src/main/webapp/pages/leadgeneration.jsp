 <%@page import="com.trux.model.OrderFilterReportsDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">   
<link rel="shortcut icon" type="image/ico" href="/trux/resources/images/favicon.ico" /> 

<!-- main css -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- All CSS Styles -->
<link type="text/css" href="/trux/resources/css/bootstrap.css" rel="stylesheet" media="screen" />
<link type="text/css" href="/trux/resources/css/animate.css" rel="stylesheet" media="screen" /> 
<link type="text/css" href="/trux/resources/css/owl.carousel.css" rel="stylesheet" media="screen" />
<link type="text/css" href="/trux/resources/css/owl.theme.css" rel="stylesheet" media="screen" /> 
<link type="text/css" href="/trux/resources/css/style.css" rel="stylesheet" media="screen" />  	
<link type="text/css" href="/trux/resources/css/font-awesome.min.css" rel="stylesheet" media="screen" /> 
 
<link href="/trux/resources/core/main.css" rel="stylesheet">
<script src="/trux/resources/js/jquery-1.11.1.min.js"></script>
<script src="/trux/resources/jtable/js/jquery.form.js"></script>

<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" /> 
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script>
<title>Lead Generation</title> 
 <script type="text/javascript">
   
    function uploadVisitigCard(){ 
    	
    $("#imageuploadform1").ajaxForm({ 	
    data: $("#imageuploadform1").serialize(),
    success: function(data) {       
    var last12char = data.substr(data.length - 12); 
    $("#hotel_image").append('<div class="col-lg-2 col-md-2 col-sm-12"  id="'+last12char+'"><div style="margin-bottom:6px;"><img src="'+data+'" style="width:100%;height:50px;"><div><a href="javascript:void(0)" onclick="return removeImage(\''+data+'\')">Remove</a></div></div></div>');
    var fileName=$("#visitingCardUrl").val();
    if(fileName!=''){
    fileName=fileName+"#"+data;
    }else{
    fileName=data;
    } 
    $("#visitingCardUrl").val(fileName);
      $("#loader_img").html('<div style="position:absolute;">Please wait <img src="/trux/resources/images/loader.gif" style="padding-left: 10px;"/></div>');
    } 
    }).submit();
     
    }
   
    function removeImage(imageurl){
    var uploadedImg=$("#visitingCardUrl").val();
    var res = uploadedImg.replace(imageurl, "");
    var firstchar = res.charAt(0);
    var lastchar = res.charAt(res.length-1);
    if(firstchar=="#"){        
        res = res.replace(firstchar, "");
    }
    if(lastchar=="#"){        
        res = res.replace(lastchar, "");
    }
    var last12char = imageurl.substr(imageurl.length - 12); 
    $("#visitingCardUrl").val(res);
    $("#"+last12char).hide();
}
</script>    
<script type="text/javascript"> 
var dateToday = new Date();
var dd = dateToday.getDate();
var mm = dateToday.getMonth()+1; 
var yyyy = dateToday.getFullYear();
var toYears=parseInt(yyyy);
$(function() {
	 $('#scheduledMeetingDate, #scheduledMeetingTime').datetimepicker({
		 timeFormat: 'HH:mm z',
		 dayOfWeekStart : 1,
		 lang:'en',
		 yearRange: '1800:' + toYears + '',
		 startDate:	dateToday //'1986/01/05'
		 });
	 $('#fromImg').click(function(){
			$('#txtStartDate').datetimepicker('show');
		});
	 $('#toImg').click(function(){
			$('#txtEndDate').datetimepicker('show');
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
 <div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div style="margin-bottom: 6px;">
					Image of Visiting card<span style="color: red;">*</span>
				</div>
				<form id="imageuploadform1" name="imageuploadform1" method="post"
					action="/trux/lgapi/uploadImage"
					enctype="multipart/form-data">
					<input type="file" name="file" id="file"
						onchange="uploadVisitigCard();" style="width: 100%;"
						class="form-control input-lg" id="propertyimages" accept="image/*" />
					<span id="image_error" style="display: none; color: red">Please
						upload only jpg jpeg, gif, png</span> <span id="hotel_image"
						style="height: 50px; width: 50px;"></span>
					<div id="loader_img"
						style="width: 270px; height: 50px; border: 4px solid #999; border-radius: 8px 8px 8px 8px; z-index: 999999; position: relative; left: 50%; right: 0px; bottom: 0px; top: 10px; display: none; background: #fff; padding: 13px;"></div>
				</form>

				<form action="addAgentLG" method="post">

					<div class="clearfix margin_10"></div>
					<div class="col-lg-3 col-md-3 col-sm-12">
						<div style="margin-bottom: 6px;">Scheduled Meeting Date
							(Optional)</div>
						<input type="text" name="scheduledMeetingDate"
							id="scheduledMeetingDate" class="form-control input-sm"
							style="width: 100%;"
							placeholder="Scheduled Meeting Date (Optional)" /> <input
							type="hidden" name="imageOfVisitingCard" id="visitingCardUrl" />
					</div>
					<div class="col-lg-3 col-md-3 col-sm-12">
						<div style="margin-bottom: 6px;">Scheduled Meeting Time
							(Optional)</div>
						<input type="text" name="scheduledMeetingTime"
							id="scheduledMeetingTime" maxlength="12"
							class="form-control input-sm" style="width: 100%;"
							placeholder="Scheduled Meeting Time (Optional)" />
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div style="margin-bottom: 6px;">Send Proposal (0 or 1)
							(Optional)</div>
						<input type="text" name="sendProposal" id="sendProposal"
							class="form-control input-sm" style="width: 100%;"
							placeholder="Send Proposal (0 or 1) (Optional)" />
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div style="margin-bottom: 6px;">Scheduled Meeting Title
							(Optional)</div>
						<input type="text" name="scheduledMeetingTitle"
							id="scheduledMeetingTitle" class="form-control input-sm"
							style="width: 100%;"
							placeholder="Scheduled Meeting Title (Optional)" />
					</div>

					<div class="col-lg-12 col-md-12 col-sm-12">
						<div style="margin-bottom: 6px;">Comments (Optional)</div>
						<textarea rows="5" cols="1000" name="comments" id="comments"
							class="form-control input-sm" style="width: 100%;"
							placeholder="comments"></textarea>
					</div>
					<br />
					<div class="col-lg-3 col-md-3 col-sm-12">
						<div
							style="margin-top: 6px; font-size: 14px; text-align: left; margin-left: 0px; margin-top: 8px;">
							<button class="btn btn-danger btn-sm">Generate Lead</button>
							<input class="btn btn-danger btn-sm" type="reset">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
  </body>
</html>
 
	 