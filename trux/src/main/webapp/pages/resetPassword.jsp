<!DOCTYPE html>
<html lang="en">
<head>
<title>Trux - Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- favicon links -->
<link rel="shortcut icon" type="image/ico" href="/trux/resources/images/favicon.ico" />
<link rel="icon" type="image/ico" href="/trux/resources/images/favicon.ico" />
<!-- main css -->
<link type="text/css" rel="stylesheet" href="/trux/resources/css/main.css" media="screen" />
<!--js-->
<script src="/trux/resources/js/jquery-1.11.1.min.js"></script>
<style>
body{background:#f4f4f4 url(images/bg.gif) repeat top left;}
</style>
<script type="text/javascript">
$( document ).ready(function() {
	$("#newpassword").hide();
	$("#confirmpassword").hide();
	$("#passwordMatch").hide();
});
function showPreview() {
	$("#newpassword").hide();
	$("#confirmpassword").hide();
	$("#passwordMatch").hide();
	if(($("#newPass" ).val()).length > 0){
		if(($("#confirmPass" ).val()).length > 0){
			if(($("#confirmPass" ).val()) == ($("#newPass" ).val())){
				return true;
				/* $.ajax({
				      type: "POST",
				      url: "/trux/forgetPassword/email/setNewPassword",
				      data:{
				    	  newPassword:($("#newPass").val());
						  passwordKey:($("#passwordKey").val());
						  } ,
				      success: function(data) {
						alert(data);
				      }
				    }); */
			}else{
				$("#passwordMatch").show();	
				return false;
			}
		}else{
			$("#confirmpassword").show();
			return false;
		}
			
	}else{
		$("#newpassword").show();
		return false;
	}
	
}
</script>
</head>
<body>
<div style="width:100%; float:left; margin:auto; margin-top:8%;">
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
								<h1 style="text-align:center;">Change Password</h1>
							</div>   
							<div class="clearfix margin_10"></div>               
                            <div class="col-lg-12 col-md-12 col-sm-12">	                
                            	<form class="form-horizontal" action="/trux/forgetPassword/setNewPassword" method="POST" onsubmit="return showPreview();">
                                <fieldset>
									<div class="input-group input-group-lg">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                                        <input type="password" id="newPass" name="newPassword" class="form-control" placeholder="New Password">
                                        <input type="hidden" name="passwordKey" id="passwordKey" value="<%= request.getAttribute("passwordKey")%>">	
                                    </div> 
									<p id="newpassword" class="center js-forgot-subtitle island" style="margin-top: 20px; text-align: center; color: rgb(255, 255, 255);color: red;">
        								Please enter new password
    								</p> 
									<div class="clearfix"></div><br> 
									<div class="input-group input-group-lg">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                                        <input type="password" id="confirmPass" class="form-control" placeholder="Confirm Password">
                                    </div>  
									<p id="confirmpassword" class="center js-forgot-subtitle island" style="margin-top: 20px; text-align: center; color: rgb(255, 255, 255);color: red;">
        								Please enter confirm password
    								</p> 
									<div style="display:none;" class="star">This Field is Required </div>                                   
                                    <div class="clearfix margin_10"></div>
                                    <p id="passwordMatch" class="center js-forgot-subtitle island" style="margin-top: 20px; text-align: center; color: rgb(255, 255, 255);color: red;">
        								Not match
    								</p>              
                                    <div class="col-lg-12" style="margin-left:-12px;">
                                        <button type="submit" class="btn btn-danger btn-lg">Submit</button>
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
