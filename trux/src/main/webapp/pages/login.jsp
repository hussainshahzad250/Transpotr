<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="com.trux.model.DesboardInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
<title>Trux - Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- favicon links --> 
<!-- main css -->
<link type="text/css" rel="stylesheet" href="/trux/resources/css/main.css" media="screen" />
<!--js-->
<link rel="shortcut icon" type="image/ico" href="/trux/resources/images/favicon.ico" />
<script src="/trux/resources/js/jquery-1.11.1.min.js"></script>
<style>
body{background:#fff}
</style>
<head>
<script type="text/javascript">
function passval()
{   
var passVal=document.getElementById("passVal").value;
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    { 
    document.getElementById("passVal").value=xmlhttp.responseText;
    }
  }
xmlhttp.open("POST","/trux/admin/register/getsalt?passwordVal="+passVal,true);
xmlhttp.send();
}
</script>
<script type="text/javascript">
     /*   $(function() {
            $(this).bind("contextmenu", function(e) {
                e.preventDefault();
                alert("Due to security region right click is not allowed  !");
            });
        }); */
    </script>
</head>
<!--<body oncontextmenu="return false">-->
<body background="#f5f5f5">

<!-------------------------------------------------------------------start header section----------------------------------------->

<header style="margin-bottom: 20px;padding: 0 0px 15px 15px;background-color:#0aaad2;">
  <div class="container-fluid">
    <div class="row">
      <div class="col-lg-3 col-xs-3 col-sm-3 col-md-3 "> <a href="javascript:void(0);" class="navbar-brand"> <img src="/trux/resources/images/trux_logo.png"></a> </div>
   </div>
  </div>
</header>
<!-----------------------------------------end header------------------------------------------------------------------->
<div style='width:100%; float:left; margin:auto; margin-top:3%;'>
    <div class="container"> 
        <div class="row">          	
            <div class="col-lg-12 col-md-12 col-sm-12">				
				<div class="row">
					 <div class="col-lg-6 col-md-6 col-sm-6 resp-3">	
						<div style="margin-left:0px">						
                            <div class="col-lg-12 col-md-12 col-sm-12 center">	
                                <div style="text-align:center; margin-top:-11%;"><img src="/trux/resources/images/map-cnct.png" style=" width:100%"></div>
                                
                                 
                            </div>                   
                            
						</div>
            		</div>
                    
   <!-----------------------------------------------colum right----------------------------------------------------------->
                    
                    <div class="col-lg-6 col-md-6 col-sm-6">	
						<div class="lognarea">						
                            <div class="col-lg-12 col-md-12 col-sm-12 center">	
                                
                                <h1 style="font-size: 25px;color: #676767;font-family: 'Open Sans', sans-serif;font-weight: 400;text-align: center;">Welcome to Trux</h1>
                                <div class="clearfix margin_10" style="color:red;">
                                <% if(request.getAttribute("error") != null){%>
                                <%=request.getAttribute("error") %>
                                <% }%>
								</div> 
                            </div>                   
                            <div class="col-lg-12 col-md-12 col-sm-12">	                
                            	<form class="form-horizontal" action="/trux/admin/register/home" method="post">
                                <fieldset>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
                                        <input type="text" name="userName" class="form-control" placeholder="Username">
                                    </div>
                                    <div class="clearfix"></div><br>
                
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                                        <input type="password" name="userPassword"  id="passVal" class="form-control" placeholder="Password">
                                    </div>
                                    <div class="clearfix"></div>                
                                    <!--<div class="input-prepend">
                                       <input type="checkbox" id="remember">  <label class="remember" for="remember">Remember me</label>
                                    </div>-->
                                    <div class="clearfix margin_10"></div>              
                                    <div class="col-lg-12">
                                        <button type="submit" class="btn btn-danger col-sm-12 dflt" id="linkLogin">Login</button>
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
    <footer class="text-center footer-end" id="footer" >
         <footer class="text-center footer-end" id="footer-1" >
<div class="container-fluid"><br>
<div class="row">
<% if(request.getAttribute("desboardInfo")!=null) {
List<DesboardInfo> dtotList=(List<DesboardInfo>)request.getAttribute("desboardInfo"); 
%>
<h3>
<%if(dtotList!=null){
	DesboardInfo dtos=	dtotList.get(0);
	if(dtos.getDesboardInfo()!=null){ %>
    <%=dtotList.get(0).getDesboardInfo() %>
<%}}} %></h3>

</div>
</div>
</footer> 
<div class="clearfix margin_10"></div><!-- background-image:url(/trux/resources/images/banner2.jpg); -->
<div class="hs_copyright" style="background-color:#0aaad2;"> Copyright &#169; 2016 Trux All Rights Reserved.</div>	  
</footer>
</div> 
<script type="text/javascript">
$("#passVal").keyup(function(event){
    if(event.keyCode == 13){
    	 
        $("#linkLogin").click();
    }
 });
 </script>
<!--main js file start--> 
<script type="text/javascript" src="/trux/resources/js/bootstrap.min.js"></script>
</body>
</html>
