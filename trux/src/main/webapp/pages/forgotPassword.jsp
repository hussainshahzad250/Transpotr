<!DOCTYPE html>
<html lang="en">
<head>
<title>Trux - Forget Password</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- favicon links -->
<link rel="shortcut icon" type="image/ico" href="/trux/resources/images/favicon.ico" />
<!-- main css -->
<link type="text/css" rel="stylesheet" href="/trux/resources/css/main.css" media="screen" />
<!--js-->
<script src="/trux/resources/js/jquery-1.11.1.min.js"></script>
<style>
body{background:#f4f4f4 url(images/bg.gif) repeat top left;}
</style>
<script type="text/javascript">
$( document ).ready(function() {
	$("#emailconfirmation").hide();
	$("#emailValidation").hide();
});
function showPreview() {
	
	if(($( "#email" ).val()).length > 0){
		$.get("/trux/forgetPassword/email/submit?email="+$( "#email" ).val(),function(data){
			$("#forgetEmail").hide();
			$("#passBtn").hide();
			$("#emailconfirmation").show();
			$("#emailValidation").hide();
		});	
	}else{
		$("#emailValidation").show();
	}
	
}
</script>
</head>
<body>
<div style="width:100%; float:left; margin:auto; margin-top:10%;">
    <div class="container"> 
        <div class="row">          	
            <div class="col-lg-12 col-md-12 col-sm-12">				
				<div class="row">
					 <div class="col-lg-8 col-md-8 col-sm-12">	
						<div id="box" class="well lognarea">						
                            <div class="col-lg-12 col-md-12 col-sm-12 center">	
                                <div style="text-align:center; margin-top:-2%;"><img src="/trux/resources/images/logo2.png"></div>								
                            </div>  
							<div class="col-lg-12 col-md-12 col-sm-12 center">
								<h1 style="text-align:center;">Forgot Password</h1>
							</div>   
							<div class="clearfix margin_10"></div>               
                            <div class="col-lg-12 col-md-12 col-sm-12">	                
                            	<form class="form-horizontal">
                                <fieldset>
                                    <div class="input-group input-group-lg" id ="forgetEmail">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope red"></i></span>
                                        <input type="text" name ="email" id="email" class="form-control" placeholder="Email ID">
                                    </div>
                                    <p id="emailconfirmation" class="center js-forgot-subtitle island" style="margin-top: 20px; text-align: center; color: rgb(255, 255, 255);color: black;">
        									We've sent you an email and SMS with a link to reset your password.
    								</p>
    								 <p id="emailValidation" class="center js-forgot-subtitle island" style="margin-top: 20px; text-align: center; color: rgb(255, 255, 255);color: red;">
        									Please enter a valid email.
    								</p>
									<div style="display:none;" class="star">This Field is Required </div>                                   
                                    <div class="clearfix margin_10"></div>              
                                    <div class="col-lg-12" style="margin-left:-12px; margin-top:10px;">
                                        <div><button onclick="showPreview()" id="passBtn" type="button" class="submitbutton">Reset Password</button></div>
                                    </div>
									<div class="clearfix margin_10"></div> 
                                </fieldset>  			                          
                            	</form>
							</div>
						</div>
            		</div>
				</div>
			</div>
		</div>       
    </div>
</div>
<!--main js file start--> 
<script type="text/javascript" src="/trux/resources/js/bootstrap.min.js"></script>
</body>
</html>
