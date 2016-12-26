<%@page import="com.trux.model.UserDetail"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
	<%UserDetail userDetail=null;
                if(session.getAttribute("userDetail")!=null){
                	userDetail =(UserDetail)session.getAttribute("userDetail");
                	}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/trux/resources/css/style.css" rel="stylesheet"	type="text/css" />
<link href="/trux/resources/jtable/jquery.jqGrid-4.4.1/css/ui.jqgrid.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css"	rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js"	type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/jquery.jqGrid-4.4.1/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="/trux/resources/jtable/jquery.jqGrid-4.4.1/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="/trux/resources/jtable/jquery.jqGrid-4.4.1/js/jquery.jqGrid.src.js"></script>

<script type="text/javascript">
        $(function() {
            $(this).bind("contextmenu", function(e) {
                e.preventDefault();
                alert("Due to security region right click is not allowed  !");
            });
        }); 

        function fetchMobile()
        {        
        var mobile=document.getElementById("phoneNumber").value;
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
        	var mobileValue=xmlhttp.responseText;
        	var mobileValues=mobileValue.replace("\"", "");
             document.getElementById("validNumber").value=mobileValues.replace("\"", "");
            }
          }
        xmlhttp.open("GET","/trux/admin/register/validateMobile?mobile="+mobile,true);
        xmlhttp.send();
        }
        
     function validateNewUserForm(){
    	
        	 var flag;
        	 var firstName=document.getElementById("firstname").value;
        	 var lastName=document.getElementById("lastname").value;
        	 var mobile=document.getElementById("phoneNumber").value;
        	 var validNumber=document.getElementById("validNumber").value;
        	 var password=document.getElementById("password").value;
        	 var message="";
        	if(firstName==""){
        	  document.getElementById('message').style.display = "block";
        	  document.getElementById('message').style.color="red";
        	  message+="<br/>";
        	  message+="Please enter the first name  !<br/>";
        	  document.getElementById("firstname").style.borderColor="red";
        	  document.getElementById('firstname').focus();
        	  flag= false;
        	  }else{
        	  document.getElementById("firstname").style.borderColor="green"; 
        	  }
        /* 	 if(lastName==""){
        	   document.getElementById('message').style.display = "block";
        	   document.getElementById('message').style.color="red";
        	   
        	   message+="Please enter the Last Name !<br/>";
        	   document.getElementById("lastname").style.borderColor="red";
        	   document.getElementById('lastname').focus();
        	   flag= false; 		 
        	   }else{
       		 document.getElementById("lastname").style.borderColor="green"; 
       	   } */
        	 
        	/* if(mobile==""){
        	document.getElementById('message').style.display = "block";
        	document.getElementById('message').style.color="red";        	
        	message+="Please enter Phone Number !<br/>";
        	document.getElementById("phoneNumber").style.borderColor="red";
        	document.getElementById('phoneNumber').focus();
        	flag= false; 		 
        	}else{
        	document.getElementById("phoneNumber").style.borderColor="green";
        	}
        	if(mobile!=""){
        	if((mobile.length != 10)) {
        	document.getElementById('message').style.display = "block";
        	document.getElementById('message').style.color="red";
        	
        	message+="Phone Number Should be 10 digit !<br/> ";
        	document.getElementById("phoneNumber").style.borderColor="red";
        	document.getElementById('phoneNumber').focus(); 
        	flag= false; 
        	}else{
        	document.getElementById("phoneNumber").style.borderColor="green";
        	} 
        	}
        	
        	if(mobile==validNumber){
            	document.getElementById('message').style.display = "block";
            	document.getElementById('message').style.color="red";            	
            	message+="Please enter other phone number. Enter phone already exist !<br/>";
            	document.getElementById("phoneNumber").style.borderColor="red";
            	document.getElementById('phoneNumber').focus();
            	flag= false; 		 
            	}else{
            	document.getElementById("phoneNumber").style.borderColor="green";
            	} */
        	if(password==""){
            	document.getElementById('message').style.display = "block";
            	document.getElementById('message').style.color="red";
            	
            	message+="Please enter password !<br/>";
            	document.getElementById("password").style.borderColor="red";
            	document.getElementById('password').focus();
            	flag= false; 		 
            	}else{
            	document.getElementById("password").style.borderColor="green";
            	}
         if(document.getElementById("userRole").selectedIndex == 0){
        	document.getElementById('message').style.display = "block";
        	
           message+="Please select Role Type at !<br/>";
        	document.getElementById("userRole").style.borderColor="red";
        	document.getElementById('userRole').focus();
        	flag= false;
        	}else{
        	document.getElementById("userRole").style.borderColor="green";
        	}	 
         
         if(message==""){flag=true;}
        	document.getElementById('message').innerHTML=message;
        	return flag;
        }
        
    </script> 
</head>
<body oncontextmenu="return false">
				
			
               
                                
 	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12">
			<fieldset class="fieldset2 col-lg-12 borderManager">
				<legend class="borderManager">Change Login Form</legend>
				<f:form commandName="login" action="/trux/admin/register/changePasswords"  method="post" cssClass="form-inline" >
				<div class="row" style="margin-top: -2%;">
			 <%if(userDetail !=null &&  userDetail.getUserRole()!=null && !userDetail.getUserRole().equals("") ){ %>
             <%if(userDetail.getUserRole().trim().equals("Admin")) {%>
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom: 6px;">User Name (As Email)  </div>
							<f:input path="email" id="email"
								cssClass="form-control input-sm"
								cssStyle="width:110%;cursor: pointer;"
								placeholder="Email" />							 
						</div>
						<%} %>
          	 <%if(userDetail.getUserRole().trim().equals("Agent")) {%>
          	  
							<f:hidden path="email" id="email"
								cssClass="form-control input-sm"
								cssStyle="width:110%;cursor: pointer;"
								placeholder="Email" />							 
					 
						 <script>  
                            $(document).ready(function(){    																		 
                            	 document.getElementById("email").value='<%=userDetail.getEmail() %>';
    						}); 
    				        </script>
						<%}}%>
			 
						<%-- 
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom: 6px;">Last Name  </div>
							<f:input path="lastname" id="lastname"
								cssClass="form-control input-sm"
								cssStyle="width:110%;cursor: pointer;"
								placeholder="Last Name" /> 
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom: 6px;">E-Mail</div>
							<f:input path="email" id="email"
								cssClass="form-control input-sm"
								cssStyle="width:110%;cursor: pointer;"
								placeholder="E-Mail" /> 
						</div>--%>
												
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom: 6px;">password </div>
							<f:password path="password" id="password" cssClass="form-control input-sm"
								cssStyle="width:110%;cursor: pointer;" placeholder="Password"/>
								 
						</div>
						<div class="clearfix margin_05"></div>						
						<div class="col-lg-3 col-md-3 col-sm-12">
							<f:textarea path="errorMessage"  disabled="true"  rows="1" cols="37"/>
						</div>
						<%-- <div class="clearfix margin_05"></div> 
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom: 6px;">Phone</div>
							<f:input path="phoneNumber" id="phoneNumber" cssClass="form-control input-sm" cssStyle="width:110%;cursor: pointer;" placeholder="Phone" onchange="fetchMobile();"/> 
							<input type="hidden" name="validNumber" id="validNumber" value=""> 
						</div>  --%>
						 
					 
						</div> 
						<div class="col-lg-3 col-md-3 col-sm-12">     
							<div style="margin-top:6px; font-size:14px; text-align:left; margin-left:0px;margin-top:8px;">
								<button class="btn btn-danger btn-sm btn_nav2" onclick="return validateNewUserForm();"> Change Password</button> <input type="reset" class="btn btn-danger btn-sm btn_nav1" value="clear"> 
						    </div>
					</div>
					 
					
				</f:form>
				</br> 
				 <div id="message" style="color: red;"></div> 
			</fieldset>
		</div>
	</div>

</body>
</html>