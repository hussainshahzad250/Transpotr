<%@page import="com.trux.model.ClientMandate"%>
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
<link href="/trux/resources/jtable/css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery.jtable.js" type="text/javascript"></script> 
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script> 
  
<style type="text/css">
tbody tr td:first-child, table#attandanceTable tbody tr td:last-child { padding: 3px 0px }

input[type="text"] { -webkit-appearance: text;
border-bottom-color: black;
 }
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

<title>Client Mandate</title>

 <script type="text/javascript">
 
 function isNumberKey(evt)
 {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
    {  document.getElementById('message').innerHTML="Please enter numeric value only !";
       return false;
    }else{
        document.getElementById('message').innerHTML="";
        return true;	
    } 
 }
 
 
 function validateForm(){
 	 var flag=false;   
 	// clientId clientSubId mandateType mandateStartDate mandateEndDate
 	 var clientId=document.getElementById("clientId").value; 
     var clientSubId = document.getElementById("clientSubId").value;
 	 var mandateType=document.getElementById("mandateType").value; 
 	 var mandateStartDate=document.getElementById("mandateStartDate").value; 
 	 var mandateEndDate=document.getElementById("mandateEndDate").value; 
 	  
	var message="";
	
 	if(clientId==""){
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
	 	  document.getElementById("clientSubId").style.borderColor="red";
	 	  document.getElementById('clientSubId').focus();
	 	  flag= false;
	 	  }else{
	 	  document.getElementById("clientSubId").style.borderColor="green"; 
	 	  }
 	 	 if(mandateType==""){
 	 	   document.getElementById('message').style.display = "block";
 	 	   document.getElementById('message').style.color="red";
 	 	   message+="Please select the Mandate Type !<br/>";
 	 	   document.getElementById("mandateType").style.borderColor="red";
 	 	   document.getElementById('mandateType').focus();
 	 	   flag= false; 		 
 	 	   }else{
 			 document.getElementById("mandateType").style.borderColor="green"; 
 		   }
 	 	 if(mandateStartDate==""){
 	 	 	   document.getElementById('message').style.display = "block";
 	 	 	   document.getElementById('message').style.color="red";
 	 	 	   message+="Please select the Mandate Start Date !<br/>";
 	 	 	   document.getElementById("mandateStartDate").style.borderColor="red";
 	 	 	   document.getElementById('mandateStartDate').focus();
 	 	 	   flag= false; 		 
 	 	 	   }else{
 	 			 document.getElementById("mandateStartDate").style.borderColor="green"; 
 	 		   }
 	 	 if(mandateEndDate==""){
 	 	 	   document.getElementById('message').style.display = "block";
 	 	 	   document.getElementById('message').style.color="red";
 	 	 	   message+="Please select the Mandate End Date !<br/>";
 	 	 	   document.getElementById("mandateEndDate").style.borderColor="red";
 	 	 	   document.getElementById('mandateEndDate').focus();
 	 	 	   flag= false; 		 
 	 	 	   }else{
 	 			 document.getElementById("mandateEndDate").style.borderColor="green"; 
 	 		   }
 	 	if(message==""){flag=true;}
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
	    message+="Please select the client !<br/>";
	 	document.getElementById('clientId').focus();
	 	flag= false;
	 	}else{
	 	document.getElementById("clientId").style.borderColor="green";
	 	}
   if(message==""){flag= true;}
   if(flag==true){
	 $.ajax({
		 type: "POST",
	      url: "/trux/reg/getSubsidiaryOrgByMasterId",
	      data:{ 
	    	  idClientMaster:SelOrgValue
			  } ,
	      success: function(data) {
	    	  document.getElementById("clientSubId").innerHTML = data;
	    	  document.getElementById("clientSubId").value.innerHTML=data;
	      }
	    });
    return true;
   }
   return false;
}


 </script>
</head>
 <body>
 <div class="container">  
	<div class="row">   
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager table-responsive">  <legend class="borderManager">Mandate </legend>
		 <form  action="searchMandate" method="post">  
		   <div class="row" style="margin-top:-2%; ">                                        									                                  
						<div class="col-lg-4 col-md-4 col-sm-12">
							<div style="margin-bottom:6px;">Clients<span style="color: red;">*</span></div>
							<!-- onchange="fillSubOrg()" -->
							  <select name="clientId" id="clientId"  class="input-sm" style="width:100%;">
                                 <option value="">--Select Client (Alphabetical)--'
                                 <% List<OrganizationMasterRegistration>  list= (List<OrganizationMasterRegistration>)request.getAttribute("orgList");
									if(list != null && !list.isEmpty()){
									for(int i=0; i<list.size();i++){ 
									%>	
								 <option value="<%=list.get(i).getIdClientMaster() %>"><%=list.get(i).getName() %>
                                 <%	}}%>                        
                             </select>
                             <script type="text/javascript">
						   $(document).ready(function() {
						  	var selClientId="<%=request.getAttribute("clientId")%>";					  		 
					  	    $("#clientId option[value="+selClientId+"]").attr("selected", true); 
   						}); 
   				        </script> 
                     	</div>             
                     	<!-- <div class="col-lg-4 col-md-4 col-sm-12">
                     	<div style="margin-bottom:6px;"> Sub-Clients <span style="color: red;">*</span></div>
						<select name="clientSubId" id="clientSubId"    class="input-sm" style="width:100%;">
                            <option value="">--Select Sub-Client (Alphabetical)--'
                        </select>
					 	</div> -->
					 	<div class="col-lg-2 col-md-2 col-sm-12" style="width: 10%">
					<div style="margin-bottom:6px;">  &nbsp; &nbsp; &nbsp; <span style="color: red; "> &nbsp; &nbsp;</span></div>
					 <input type="submit" class="btn btn-danger btn-sm btn_nav1" id="save"  value="Search Now">  
					</div>    
					</form>
					 <div class="col-lg-2 col-md-2 col-sm-12" >
					<div style="margin-bottom:6px;">  &nbsp; &nbsp; &nbsp; <span style="color: red;"> &nbsp; &nbsp;</span></div>
					 <input type="button" class="btn btn-danger btn-sm btn_nav1" id="addnewMandate"   value="Add New Mandate">  
					</div>
					</fieldset>
				 </div> 
<div class="clearfix margin_05"></div>
<div class="clearfix margin_10"></div>
  	<div class="container">  
        <div class="row">
            <fieldset class="fieldset2 borderManager table-responsive"> 
              <div	style="width: 100%; margin-right: 0%; margin-left: 0%; text-align: left; ">       
            <div id="mandateInTableContainer"></div>
        </div><!-- </div> -->
        </fieldset>
    </div>
   </div> 
	 
			 <div id="message" style="color: red;"></div>  
			
		</div>
	</div>		
          

<script type="text/javascript">
	    $(document).ready(function () {
	       /*  $("#mondateData").dialog({ autoOpen: false });
	 
	        $("#mondateData").click(
	            function () {
	                $("#mondateData").dialog('open');
	                return false;
	            }
	        ); */
	        
	      
	       
	       
	        $("#addnewMandate").click(
	        		 function () {
	        		window.location.href="/trux/clients/mandate";
	        	    return false;
	 	      });
	    });
	    
	    
	    
	</script>      
		<script type="text/javascript">
		var dateToday = new Date();
		var dd = dateToday.getDate();
		var mm = dateToday.getMonth()+1; 
		var yyyy = dateToday.getFullYear();
		var toYears=parseInt(yyyy);
		$(function() {
		$('#mandateStartDate').datetimepicker({
			 timeFormat: 'HH:mm z',
			 dayOfWeekStart : 1,
			 lang:'en',
			 yearRange: '1800:' + toYears + '',
			 startDate:	dateToday 
			 });
		 
		$('#mandateEndDate').datetimepicker({
			 /* datepicker:false,
			  format:'H:i',
			  step:5  */
			 timeFormat: 'HH:mm z',
			 dayOfWeekStart : 1,
			 lang:'en',
			 yearRange: '1800:' + toYears + '',
			 startDate:	dateToday
			 });
		});
		
		
		
	 	
		function mandateOpenToEdit(){
			//$("#mondateData").show();
			$("#mondateData").dialog('open');
		}
		
		
		
		
		function searchMandateSubmit(){
	       
			var data="";
			$('#mandateRecords').append(data); 
			var ii=1;
			 var sellOrgName=$("#clientId").val();
		  	 var sellSubOrgClient=$("#clientSubId").val();
		  	 var dataString='clientId='+sellOrgName+'&clientSubId='+sellSubOrgClient;
		   	  $.ajax({
		  			  type: "GET",
		  			  url: "/trux/clients/searchmandate",
		  			 data: dataString,
		  			  cache: false,
		  			  success: function(result){
		  				  if(result=='' || !result)
		  				  {
		  			    $("#mandateDetailsReports").hide();
		  				  } 
		  				
		  				 $.each(result, function( index, value ) {
		  					ii=ii++;
	                     $("#mandateDetailsReports").show();
	                     if(value.clientName!=null){
	                         data +='<tr><td>'+ii+'</td><td>'+value.clientName+'</td><td>'+value.subClientName+'</td><td>'+value.mandateType+'</td><td  class="fa fa-pencil-square-o"><a href="#" onclick="mandateOpenToEdit()">'+value.clientMandateId+'" edit </a></td></tr>';
	                     } }); 
		  	$('#mandateRecords').append(data); 
		  	 }
		    });  

		 return true;
			}
		</script>	
		
		
		<script type="text/javascript">
	$(document).ready(function() {
		 $('.ui-button-text').click(function() {
			$('#mandateInTableContainer').hide();
		 });
	
	/*  $(".ui-button").click(
	            function () {
	            	$('#mandateInTableContainer').hide();
	                return false;
	            }
	        ); */
		
		var selClientId=""+'<%=request.getAttribute("clientId")%>';		 
  		var selClientSubId=""+'<%=request.getAttribute("clientSubId")%>';
  		
  		if(selClientId=="null"){
  			$('#mandateInTableContainer').hide();
  		}
  		
  		var url;
		var titles="All Mandate Details "
		 
		if(selClientId!=""){
			url='/trux/clients/searchMandateReports?action=list&clientId='+selClientId;
			
			//url='/trux/clients/searchMandateReports?action=list&clientId='+selClientId+'&clientSubId='+selClientSubId;
		}  
		$('#mandateInTableContainer').jtable({
			/* title : titles, */
		    paging: true, //Enable paging
            pageSize: 15, //Set page size (default: 10)    
            columnResizable: true, //Actually, no need to set true since it's default
            columnSelectable: true, //Actually, no need to set true since it's default
            saveUserPreferences: true,
            actions: {
            	 listAction:url,
                //listAction: '/trux/reportsapi/loggedInDriverReports?action=list&flag='+flag,
                //createAction: '/trux/reportsapi/loggedInDriverReports?action=create',
                updateAction: '/trux/clients/searchMandateReports?action=update',
             //   deleteAction: '/trux/clients/searchMandateReports?action=delete'
            },   
			fields : {
				clientMandateId : { 
					 key: true,
	                    create: false,
	                    edit: false,
	                    list: false,
	                    input: function (data) {
	                    var val=data.clientMandateId;
	                    var even=val/2;
					        if (even) {
					            return  $( "tr:even" ).css( "background-color", "#b9ffb9" );
					        }  
					    } 
	                   
				},
				mandateDetailsId : {  
					 key: true,
	                    create: false,
	                    edit: false,
	                    list: false
				},
				clientName : {
					title : 'Client',
					width : '15%',
					edit : false
				},
				subClientName : {
					title : 'Sub-Client',
					width : '15%',
					edit : false
				},mandateType : {
					title : 'Mandate Type',
					width : '10%',
					create: true,
				    edit: true,
				    list: true,
				    input: function (data) {
				        if (data.value) {
				            return '<input type="text" readonly class="jtable-input-readonly" name="mandateType" value="' + data.value + '"/>';
				        }  
				    } 
				    /* visibility: 'hidden' */
					//options: { 'Leased':'Leased','Box':'Box','Weight':'Weight','Per Trip':'Per Trip','Per Km':'Per Km','Per Day':'Per Day','Per Hour':'Per Hour' },
					
				},mandateStartDates : {
					title : 'Mandate Start Date', 
					 width: '14%',
	                 type: 'date',
	                 displayFormat: 'yy-mm-dd',
	                 edit: true,
	                 sorting: false,
	                 input: function (data) {
					        if (data.value) {
					            return '<input type="text" readonly class="jtable-input-readonly" name="mandateStartDates" value="' + data.value + '"/>';
					        }  
					    } 
				},
				mandateEndDates : {
					title : 'Mandate End Date',
				    width: '14%',
	                type: 'date',
	                displayFormat: 'yy-mm-dd',
	                edit: true,
	                sorting: false 
	            }/* ,
				totalVehicles : {
					title : 'Total Vehicles within mandate',
					width : '14%',
					edit : false
				} */,abettedTax : {
					title : 'Abetted Tax',
					width : '10%',
					edit : false
				},
				fullTax : {
					title : 'Full Tax',
					width : '10%',
					edit : false
				},
				vehicleType : {  
					title : 'Vehicle Type',
				    width: '10%',
				    edit : true,  
					options: { 'Mahindra Champion':'Champion','Maruti Eeco':'Eeco','Tata Ace':'Tata Ace','Tata 407':'Tata 407 (10 Ft)','Tata 709':'Tata 709 (14 Ft)','Bolero Pickup':'Bolero Pickup','17 Feet Single Axle':'17 Feet - Single Axle','17 Feet Double Axle':'17 Feet - Double Axle','19 Feet Single Axle':'19 Feet - Single Axle','19 Feet Double Axle':'19 Feet - Double Axle','19 Feet Multi-Axle':'19 Feet - Multi-Axle','22 Feet Single Axle':'22 Feet - Single Axle','22 Feet Double Axle':'22 Feet - Double Axle','22 Feet Multi-Axle':'22 Feet - Multi-Axle','24 Feet Single Axle':'24 Feet - Single Axle','24 Feet Double Axle':'24 Feet - Double Axle','24 Feet Multi-Axle':'24 Feet - Multi-Axle','28 Feet Single Axle':'28 Feet - Single Axle','28 Feet Double Axle':'28 Feet - Double Axle','28 Feet Multi-Axle':'28 Feet - Multi-Axle','32 Feet Single Axle':'32 Feet - Single Axle','32 Feet Double Axle':'32 Feet - Double Axle','32 Feet Multi-Axle':'32 Feet - Multi-Axle','40 Feet Double Axle':'40 Feet - Double Axle','40 Feet Multi-Axle':'40 Feet - Multi-Axle' },
					list: true 
				},bodyType : {
					title : 'Vehicle Body Type',
				    width: '10%',
				    edit : true,  
					options: {  'Containerized':'Containerized','Open body':'Open body'},
					list: true 
				},totalNoOfVehicle : {
					title : 'No Of Vehicle',
				    width: '10%',
	                 edit: true 
				}
				
			 }
		});
		//$("#Edit-vehicleType").addClass("input-sm");
		//$("#Edit-bodyType").addClass("input-sm");
		 
		$('#mandateInTableContainer').jtable('load');
		/* $('select').addClass("input-sm"); */
		//$('#Edit-bodyType').prop('disabled',true);
	  /*  $("#ui-id-3").css("width: 420px; min-height: 28px; max-height: none; height: 352px;"); */
		
	});
</script>
	
   

	
<script type="text/javascript">
$(function() {
	var dateToday = new Date();
	var dd = dateToday.getDate();
	var mm = dateToday.getMonth()+1; 
	var yyyy = dateToday.getFullYear();
	var toYears=parseInt(yyyy);
	/*  $('#txtStartDate, #txtEndDate').datepicker({
	      showOn: 'both',
	      buttonImage: "/trux/resources/images/calendar.png",
         buttonImageOnly: true,
     	  changeMonth: true,
         changeYear: true,
         dateFormat: 'dd/mm/yy',
          yearRange: '2000:' + toYears + '',
        //beforeShowDay: jQuery.datepicker.noWeekends,
         maxDate: dateToday
	
 }); */
	 
	 $('#txtStartDate, #txtEndDate').datetimepicker({
		 showOn: 'button',
		 buttonImage: "/trux/resources/images/calendar.png",
         buttonImageOnly: true,
		 timeFormat: 'HH:mm z',
		 dayOfWeekStart : 1,
		 lang:'en',
		 startDate:	dateToday //'1986/01/05'
		 });
	 
	 $('#fromImg').click(function(){
			$('#txtStartDate').datetimepicker('show');
		});
	 $('#toImg').click(function(){
			$('#txtEndDate').datetimepicker('show');
		});
	}); 
	
	
/* jQuery('#txtStartDate').click(function(){
	  jQuery('#txtStartDate').datetimepicker('show'); //support hide,show and destroy command
	});
jQuery('#txtEndDate').click(function(){
	  jQuery('#txtEndDate').datetimepicker('show'); //support hide,show and destroy command
	});
  */

function julianDayCalculater(dates)
{	 
	var dateToday = dates; 
	var dd = dateToday.substring(0,2);
	var mm = dateToday.substring(3,5); 
	var yyyy = dateToday.substring(6,10);	 
   	var D = eval(dd);
	var M = eval(mm);
	var Y = eval(yyyy);
	if(M<3)	{
		Y--;
		M += 12;
	}
 	var C=0; 
	var E = Math.floor(365.25*(Y + 4716)); 
	var F = Math.floor(30.6001*(M + 1)); 
	var julianday = C + eval(D) + E + F - 1524.5; 
 	NewJD = julianday; 
 	alert(NewJD);
return  NewJD;
}



function appliedFromToDate(){	
	 
		var objFromDate = document.getElementById("txtStartDate").value;
		var objToDate = document.getElementById("txtEndDate").value;
		if(objFromDate!=null && objToDate!=null){
	    var	fromDate=julianDayCalculater(objFromDate);
	    var	toDate=julianDayCalculater(objToDate);
	    
	    if(toDate<fromDate)
		 {
		 alert("\"Date of To  \" should be greater than \"From  date\" ");
		 document.getElementById("txtEndDate").value="";
		 return false;
		 }else{
			 return true;
		 }   
	    }else{
			 return true;
	}
}

</script>
				 
  </body>
</html>
 
	 