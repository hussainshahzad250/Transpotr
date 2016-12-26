<%@page import="com.trux.model.SubsidiaryClientUser"%>
<%@page import="com.trux.model.UserDetail"%>
<%@page import="java.util.Date"%>
<%@page import="com.trux.utils.DateFormaterUtils"%>
<%@page import="com.trux.model.DriverAttandance"%>
<%@page import="com.trux.model.OrganizationMasterRegistration"%>
<%@page import="java.util.List"%>
<%@page import="com.trux.model.OrderFilterReportsDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script> 
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script> 
<!-- <script src="/trux/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>  -->

<style type="text/css"> 
/* 
.dropdown {
  position: absolute;
  top:50%;
  transform: translateY(-50%);
}

a {
  color: #fff;
}

.dropdown dd,
.dropdown dt {
  margin: 0px;
  padding: 0px;
}

.dropdown ul {
  margin: -1px 0 0 0;
}

.dropdown dd {
  position: relative;
}

.dropdown a,
.dropdown a:visited {
  color: #fff;
  text-decoration: none;
  outline: none;
  font-size: 12px;
}

.dropdown dt a {
  background-color: #4F6877;
  display: block;
  padding: 8px 20px 5px 10px;
  min-height: 25px;
  line-height: 24px;
  overflow: auto;
  border: 1;
  width: 272px;
}

.dropdown dt a span,
.multiSel span {
  cursor: pointer;
  display: inline-block;
  padding: 0 3px 2px 0;
}

.dropdown dd ul {
  overflow: auto;
  background-color: white;
  border: 0;
  color: black;
  display: none;
  left: 0px;
  padding: 2px 15px 2px 5px;
  position: absolute;
  top: 2px;
  width: 280px;
  list-style: none;
  height: 100px;
}

.dropdown span.value {
  display: none;
}

.dropdown dd ul li a {
  padding: 5px;
  display: block;
}

.dropdown dd ul li a:hover {
  background-color: #fff;
}

button {
  background-color: #6BBE92;
  width: 302px;
  border: 0;
  padding: 10px 0;
  margin: 5px 0;
  text-align: center;
  color: #fff;
  font-weight: bold;
} */
 
input[type="radio"] { -webkit-appearance: radio; }
input[type="checkbox"] { -webkit-appearance: checkbox; }
 
tbody tr td:first-child, table#attandanceTable tbody tr td:last-child { padding: 3px 0px }
</style>
 <style type="text/css">
table.successTable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.successTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.successTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
	border: 1px;
	font-size: 8px;
}
</style>

 

<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<style type="text/css">
table.successTable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
}
table.successTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.successTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
	color: green;
}
.oddrowcolor{
	background-color:#d4e3e5;
}
.evenrowcolor{
	background-color:#c3dde0;
}
</style>

  
<script src="/trux/resources/core/jquery.autocomplete.min.js"></script>
<link href="/trux/resources/core/main.css" rel="stylesheet">

 <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
 <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>

<title>User Client Mapping</title>

 <script type="text/javascript">
 

 function validateForm(){
 	 var flag=false;  
 	 var innerflag=false;  
 	 var orgName=document.getElementById("clientId").value; 
     var clientSubId = document.getElementById("clientSubId").value;
 	 var userId=document.getElementById("userId").value; 
 	 
	var message="";
	 if(userId==""){
	 	   document.getElementById('message').style.display = "block";
	 	   document.getElementById('message').style.color="red";
	 	   
	 	   message+="Please select the User Name !<br/>";
	 	   document.getElementById("userId").style.borderColor="red";
	 	   document.getElementById('userId').focus();
	 	   flag= false; 		 
	 	   }else{
			 document.getElementById("userId").style.borderColor="green"; 
		   }
 	if(orgName==""){
 	 	  document.getElementById('message').style.display = "block";
 	 	  document.getElementById('message').style.color="red";
 	 	  message+="<br/>";
 	 	  message+="Please select the Client name  !<br/>";
 	 	  document.getElementById("clientId").style.borderColor="red";
 	 	  document.getElementById('clientId').focus();
 	 	  flag= false;
 	 	  }else{
 	 	  document.getElementById("clientId").style.borderColor="green"; 
 	 	  }
 	if(clientSubId==""){
	 	  document.getElementById('message').style.display = "block";
	 	  document.getElementById('message').style.color="red";
	 	  message+="<br/>";
	 	  message+="Please select the Sub Client name  !<br/>";
	 	  document.getElementById("clientSubIds").style.borderColor="red";
	 	  document.getElementById('clientSubIds').focus();
	 	  flag= false;
	 	  }else{
	 	  document.getElementById("clientSubIds").style.borderColor="green"; 
	 	  }
 	var val = [];
    $(':checkbox:checked').each(function(i){
      val[i] = $(this).val();
    });
    
    $("#clientSubId").val(val);
 	clientSubIdVal = new Array();
 	if(message==""){
 		flag= true;
	  }
   
 	return flag;
  }
   
//------------------------------------------//
 


function fillSubOrg()	
{   var flag=false; 
    var SelOrgValue = document.getElementById("clientId").value;
    var message="";
    
   if(SelOrgValue==""){
	 	document.getElementById("message").style.color="red";
	 	document.getElementById('message').style.display = "block";
	 	document.getElementById("assosiatedBy").style.borderColor="red";
	    message+="Please enter the Driver Assosiated By subsidiary !<br/>";
	 	document.getElementById('clientId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("clientId").style.borderColor="green";
	 	}
   if(message==""){flag= true;}
   if(flag==true){
	 $.ajax({
	      type: "POST",
	      url: "/trux/reg/getSubsidiary",
	     /*  url: "/trux/reg/getSubsidiaryOrgByMasterId", */
	      data:{ 
	    	  idClientMaster:SelOrgValue
			  } ,
	      success: function(data) {
	    	  $("#clientSub").html(data);
	    	//  document.getElementById("clientSubIds").value.innerHTML=data;
	      }
	    });
    return true;
   }
   return false;
}


function activeDeactive(id,flag)	
{      
	  var selluserId=$("#userId").val();
 	  var sellclientId=$("#clientId").val(); 
 	  var data="";
      var dataString='userId='+selluserId+'&clientId='+sellclientId+'&subClientId='+id+'&isActive='+flag;
      $("#assingReportsTable tbody tr").remove(); 
      $.ajax({
 			  type: "GET",
 			  url: "/trux/attandance/ActiveDeactive",
 			 data: dataString,
 			  cache: false,
 			  success: function(result){
 				  if(result=='' || !result)
 				  {
 					 $("#assingUserReports").hide();
 					 
                  }else{
                	  $("#assingUserReports").show();
                  } 
 				 $("#assingReportsTable tr:gt(0)").remove();
 				 var ids=0;
 				var values=new Array(); 
 				 
 				 $.each(result, function( index, value ) {
 				  $("#assingUserReports").show();
 				 values.push(value.clientSudIds);
                  if(value.subName!=null){
                   var val1="";
                   var val2="";
                   var tds= "";
                   ids=ids++;
                   if(value.isActive==0){
                       val1="<b style=color:red;>Deactivate</b>";
                       val2="<b style=color:red;>Activate</b>";
                       tds= '<td style="background-color: gray;"><input type="radio" name="action'+ids+'" id="action'+ids+'"  value="0" onclick="activeDeactive('+value.idClientSubMaster+',0)" checked>'+val1+' / <input type="radio" name="action'+ids+'" id="action'+ids+'"  value="1" onclick="activeDeactive('+value.idClientSubMaster+',1)" >'+val2+'</td>';
                      }
                     if(value.isActive==1){
                  	 val1="<b style=color:green;>Deactivate</b>";
                       val2="<b style=color:green;>Activate</b>";
                       tds= '<td style="background-color: silver;"><input type="radio" name="action'+ids+'" id="action'+ids+'"  value="0" onclick="activeDeactive('+value.idClientSubMaster+',0)" >'+val1+' / <input type="radio" name="action'+ids+'" id="action'+ids+'"  value="1" onclick="activeDeactive('+value.idClientSubMaster+',1)" checked>'+val2+'</td>';
                    }
                     // data +='<tr><td>'+value.clientName+'</td><td>'+value.subName+'</td>'+tds+'</tr>';
                     data +='<tr><td>'+value.clientName+'</td><td>'+value.subName+'</td></tr>';  } 
               
 				 }); 
 				 
 	$("#assingReportsTable tr:gt(0)").remove();
 	$("#assingReportsTable tbody").empty();
 	$('#assingReportsTBody').append(data); 
 	var val=values;
    $.each(values, function(i,e){
    var checkval=$('#subsidiaryId'+e).val();
    	if(checkval==e){
    	$('#subsidiaryId'+e).prop('checked', true);
    	 }
    	 
		}); 
  }
   });

return true;
}  

 
 
 
function searcgUserAssing(){
	  var selluserId=$("#userId").val();
 	  var sellclientId=$("#clientId").val(); 
 	  var data="";
      var dataString='userId='+selluserId+'&clientId='+sellclientId;
      
      $("#assingReportsTable tbody tr").remove(); 
      var ids=0;
      $.ajax({
 			  type: "GET",
 			  url: "/trux/attandance/searchUserClientMapping",
 			 data: dataString,
 			  cache: false,
 			  success: function(result){
 				  if(result=='' || !result)
 				  {
 					 $("#assingUserReports").hide();
                    
 				  } else{
                	  $("#assingUserReports").show();
                  } 
 				 $("#assingReportsTable tr:gt(0)").remove();
 				ids=ids++;
 				var values=new Array(); 
 				 $.each(result, function( index, value ) {
 				  $("#assingUserReports").show();
 				  
 				 values.push(value.clientSudIds);
                  if(value.subName!=null){
                    var val1="";
                   var val2="";
                   var tds= "";
                   if(value.isActive==0){
                     val1="<b style=color:red;>Deactivate</b>";
                     val2="<b style=color:red;>Activate</b>";
                     tds= '<td style="background-color: gray;"><input type="radio" name="action'+ids+'" id="action'+ids+'"  value="0" onclick="activeDeactive('+value.idClientSubMaster+',0)" checked>'+val1+' / <input type="radio" name="action'+ids+'" id="action'+ids+'"  value="1" onclick="activeDeactive('+value.idClientSubMaster+',1)" >'+val2+'</td>';
                    }
                   if(value.isActive==1){
                	 val1="<b style=color:green;>Deactivate</b>";
                     val2="<b style=color:green;>Activate</b>";
                     tds= '<td style="background-color: silver;"><input type="radio" name="action'+ids+'" id="action'+ids+'"  value="0" onclick="activeDeactive('+value.idClientSubMaster+',0)" >'+val1+' / <input type="radio" name="action'+ids+'" id="action'+ids+'"  value="1" onclick="activeDeactive('+value.idClientSubMaster+',1)" checked>'+val2+'</td>';
                  }
                  // data +='<tr><td>'+value.clientName+'</td><td>'+value.subName+'</td>'+tds+'</tr>';
                   data +='<tr><td>'+value.clientName+'</td><td>'+value.subName+'</td></tr>';
                  }
             
 				 }); 
 				
 	$("#assingReportsTable tr:gt(0)").remove();
 	$("#assingReportsTable tbody").empty();
 	$('#assingReportsTBody').append(data); 
 	var val=values;
    $.each(values, function(i,e){
    var checkval=$('#subsidiaryId'+e).val();
    	if(checkval==e){
    		$('#subsidiaryId'+e).prop('checked', true);
    	 }
    	 
		}); 
  }
   });

return true;
	}
 
 
 
 
function collectCheckVal(id){
	
	if ($('#'+id).prop('checked')==false){ 
		activeDeactive($('#'+id).val(),0)
		 
		}
	 
	 var val = [];
     $(':checkbox:checked').each(function(i){
       val[i] = $(this).val();
     });
     
     $("#clientSubId").val(val);
	//alert(val);
 	
}
 
</script> 
	
	  
</head>
 <body>
 <div class="container">  
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager">  <legend class="borderManager"> User Subsidiary Mapping <%
    		     List<SubsidiaryClientUser> listOfMapping=(List<SubsidiaryClientUser>) request.getAttribute("saveDto"); if(listOfMapping!=null && listOfMapping.size()>0){ %>  <b style="color: green;"> User Mapped successfully</b><%} %></legend>
		  	<form  id="userMapping" action="saveUserClientMapping" method="post" class="form-inline" onsubmit="return validateForm();">
			 <div class="row" style="margin-top:-2%; ">    
		 	 <div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">User<span style="color: red;">*</span></div>
							  <select name="userId" id="userId"   class="input-sm" style="width:100%;">
                                 <option value="">--Select User--</option>
                                 <% List<UserDetail> userList=  ( List<UserDetail>) request.getAttribute("userList"); 
									if(userList != null && !userList.isEmpty()){
									for(int i=0; i<userList.size();i++){ 
									%>	
								 <option value="<%=userList.get(i).getId() %>"><%=userList.get(i).getFirstname() %> &nbsp; &nbsp; <%=userList.get(i).getEmail() %></option>
                                 <%	}}%>                        
                             </select>
                     	</div>                            									                                  
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">Client Name<span style="color: red;">*</span></div>
							 <select name="clientId" id="clientId" onchange="fillSubOrg(),searcgUserAssing();" class="input-sm" style="width:100%;">
                                 <option value="" selected="selected">--Select Client--</option>
                                 <% List<OrganizationMasterRegistration>  list= (List<OrganizationMasterRegistration>)request.getAttribute("orgList");
									if(list != null && !list.isEmpty()){
									for(int i=0; i<list.size();i++){ 
									%>	
								 <option value="<%=list.get(i).getIdClientMaster() %>"><%=list.get(i).getName() %></option>
                                 <%	}}%>                        
                             </select>
                     	</div>      
                     	
						<div class="clearfix margin_05"></div>
						<div class="clearfix margin_10"></div>	
			       <div class="col-lg-6 col-md-12 col-sm-12"  id="assingUserReportds" style=" margin-left:0px;" >
		 <h6 style="color: black;padding: 6px; margin: 0px;border-radius: 6px;">Subsidiary Client</h6>
        <div style="overflow: auto;height:300px;">
                     	 <div class="col-lg-6 col-md-12 col-sm-12" id="clientSub"  style="width:100%; height:200px;padding: 6px; margin: 0px;border-radius: 6px; color: #727272; border:1px solid #999;">--Select Subsidiary-- </div>
				     
					<!-- 	<select name="clientSubIds" id="clientSubIds"  class="input-sm" style="width:100%; height:200px; " multiple="multiple">
                               
                               
                                 <option value="">--Select Subsidiary Client --</option>
                                 
                                  
                          </select> -->
                          <input type="hidden" name="clientSubId" id="clientSubId">
                          <input type="hidden" name="clientSubIds" id="clientSubIds">
                          
					 	</div>
					 	</div>
		<div class="col-lg-6 col-md-12 col-sm-12 right_table"  id="assingUserReports" style="overflow: auto; margin-left:0px; display: none;" >
		 <h4 style="text-align: center;color: #fff;background-color: #0AAAD2;padding: 6px; margin: 0px;border-radius: 6px;">Assign History</h4>
        <div style="height:160px;">
        <table class="table table-bordered  col-lg-6 col-md-12 col-sm-12" id="assingReportsTable"  style="color: #727272;">
         <thead>
         <tr style="background-color: #0AAAD2;color: white;border: 0px; height: 30px; ">
	     <th>
		  <span style="width: 100%;float: left; padding: 5px; margin: 0px;  text-align: center;">Client</span>
        </th>
        <th>
         <span style="width: 100%;float: left; padding: 5px; margin: 0px;  text-align: center;">Sub Client</span> 
         </th>
       <!-- <th >
         <span style="width: 100%;float: left; padding: 5px; margin: 0px;  text-align: center;">Action</span> 
         </th> -->
		</tr>
		</thead>
		<tbody id="assingReportsTBody">
		</tbody>
		</table>
		</div>
		</div> <div class="clearfix margin_05"></div>
			<div class="clearfix margin_10"></div>	
			 <div class="col-lg-3 col-md-3 col-sm-12">
			 <input type="submit" class="btn btn-danger btn-sm btn_nav1" id="save"  value="Save Now"> 
			 <input type="reset" class="btn btn-danger btn-sm btn_nav1" value="Reset"> 
			 </div>
		</div>
		 <div id="message" style="color: red;"></div>
		</fieldset>
		</div>
		</div>
		</div>
						
						
			<!-- 			///
	<dl class="dropdown"> 
  
    <dt>
    <a href="#">
      <span class="hida">Select</span>    
      <p class="multiSel"></p>  
    </a>
    </dt>
  
    <dd>
        <div class="mutliSelect">
            <ul style="overflow: auto; display: block;">
                <li>
                    <input type="checkbox" value="Apple" />Apple</li>
                <li>
                    <input type="checkbox" value="Blackberry" />Blackberry</li>
                <li>
                    <input type="checkbox" value="HTC" />HTC</li>
                <li>
                    <input type="checkbox" value="Sony Ericson" />Sony Ericson</li>
                <li>
                    <input type="checkbox" value="Motorola" />Motorola</li>
                <li>
                    <input type="checkbox" value="Nokia" />Nokia</li>
            </ul>
        </div>
    </dd>
  <button>Filter</button>
</dl>
						///	
						<script type="text/javascript">
						$(".dropdown dt a").on('click', function() {
							  $(".dropdown dd ul").slideToggle('fast');
							});

							$(".dropdown dd ul li a").on('click', function() {
							  $(".dropdown dd ul").hide();
							});

							function getSelectedValue(id) {
							  return $("#" + id).find("dt a span.value").html();
							}

							$(document).bind('click', function(e) {
							  var $clicked = $(e.target);
							  if (!$clicked.parents().hasClass("dropdown")) $(".dropdown dd ul").hide();
							});

							$('.mutliSelect input[type="checkbox"]').on('click', function() {

							  var title = $(this).closest('.mutliSelect').find('input[type="checkbox"]').val(),
							    title = $(this).val() + ",";

							  if ($(this).is(':checked')) {
							    var html = '<span title="' + title + '">' + title + '</span>';
							    $('.multiSel').append(html);
							    $(".hida").hide();
							  } else {
							    $('span[title="' + title + '"]').remove();
							    var ret = $(".hida");
							    $('.dropdown dt a').append(ret);

							  }
							});
						</script> -->
  </body>
</html>
 
	 