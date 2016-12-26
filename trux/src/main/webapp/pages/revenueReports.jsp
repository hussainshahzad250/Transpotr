 <%@page import="com.trux.model.OrderFilterReportsDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
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
 

<title>Order Revenue Reports</title>
 
</head>
<body>

<div class="container"  style="border-radius:10px 10px 7px 7px;">  
	<div class="row">    
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager">  <legend class="borderManager">Order Revenue search Form</legend>
			<f:form commandName="revenueOrderFilter" action="revenueFilterReports" method="post" cssClass="form-inline" ><!-- onsubmit="return appliedFromToDate();" -->
				
					<div class="row" style="margin-top:-2%;">                                        									                                  
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">From</div>
							<f:input path="from" id="txtStartDate" cssClass="form-control input-sm fromToDateInputFields" cssStyle="width:80%;cursor: pointer;" readonly="readonly" placeholder="yyyy/mm/dd hh:mm"/><img alt="" src="/trux/resources/images/calendar.png" id="fromImg" title="Please click on text field area after that  show the calander" style="cursor: pointer;"/>  
						</div>                                               
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">To</div>
							<f:input path="to" id="txtEndDate"  readonly="readonly" cssClass="form-control input-sm" cssStyle="width:80%;cursor: pointer;" placeholder="yyyy/mm/dd hh:mm"/><img alt="" src="/trux/resources/images/calendar.png" id="toImg" title="Please click on text field area after that  show the calander" style="cursor: pointer;"/>   
						</div>	
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Vehicle Status</div>
						<f:select path="city" id="city" cssClass="input-sm" cssStyle="width:100%;cursor: pointer;">
						<f:option value="">--Select Vehicle Association--</f:option>
						<f:option value="Mapped">Leased</f:option>
						<f:option value="Free">On-Demand</f:option>
					 
						</f:select>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Vehicle type</div>
						<f:select path="vehicleType" id="vehicleType" cssClass="input-sm" cssStyle="width:100%;cursor: pointer;">
							<f:option value="">--Select Vehicle type--</f:option>
							<f:option value="Tata Ace">Tata Ace</f:option>
							<f:option value="Tata 407">Tata 407</f:option>
							<f:option value="Tata 709">Tata 709</f:option>
							<f:option value="Bolero Pickup">Bolero Pickup</f:option>
							<f:option value="Mahindra Champion">Mahindra Champion</f:option>
							<f:option value="Maruti Eeco">Maruti Eeco</f:option>
							</f:select> 
						</div>
						 
						<div class="col-lg-3 col-md-3 col-sm-12">     
							<div style="margin-top:6px; font-size:14px; text-align:left; margin-left:0px;margin-top:25px;">
								<button class="btn btn-danger btn-sm btn_nav2">Filter</button> 
						                             
							</div>
						</div> 
					</div>											
					<!-- <div class="clearfix margin_10"></div> -->
				
			</f:form>
			<f:form action="clearRevenueFilter" commandName="revenueOrderFilter" method="post">
			<div class="col-lg-3 col-md-3 col-sm-12">     
			<div style="text-align: left; font-size: 14px; margin-top: -30px; margin-left: 52px;">
			<button class="btn btn-danger btn-sm btn_nav2">Clear</button>                          
			</div>
			</div></f:form> 
			</fieldset>
		</div>
	</div>		
</div>
	
   <div class="container">  
        <div class="row">
            <fieldset class="fieldset2 borderManager table-responsive">
             <!-- <div style="overflow: auto; height: 500px;"> -->
              <div	style="width: 100%; margin-right: 0%; margin-left: 0%; text-align: left;">       
            <div id="LoggedInTableContainer"></div>
        </div><!-- </div> -->
        </fieldset>
    </div>
   </div> 
<script type="text/javascript">
    	$(document).ready(function() {
		
			$('#LoggedInTableContainer').jtable({
				title : " ",
				paging : true, //Enable paging
				pageSize : 10, //Set page size (default: 10) 
			
				toolbar: {
				    items: [{
				        Tooltip: 'Click here to export this table to excel',
				        /* icon: '/trux/resources/images/xls.png', */
				        text: 'Export to Excel',
				        click: function () {
				         window.location = '/trux/reportsapi/revenueXLSReports?action=export-excel';
				         e.preventDefault();
				        }
				    }]
				}, 
				CustomAction: {
                    title: 'Refresh',
                    width: '1%',
                    sorting: false,
                    create: false,
                    edit: false,
                    list: true,
                    display: function (data) {
                        if (data.record) {
                            return '<button title="Refresh '+data.record.table+'"class="jtable-command-button jtable-edit-command-button" onclick="jTableAjax("/trux/reportsapi/revenueRequest?action=list",' + data.record.tableID + '); return false;"><span>Refresh '+data.record.table+'</span></button>';
                        }
                    }
                },
				actions : {
					listAction : '/trux/reportsapi/revenueRequest?action=list',
				},
				fields : {
					dates : {
						title : 'Date',
						sort : true,
						width : '15%',
						key : true,
						list : true,
						edit : false,
						create : true
					},
					state : {
						title : ' Number of Order',
						width : '10%',
						edit : true
					},
					totalFares : {
						title : 'Gross freight',
						width : '10%',
						edit : true
					},
					totalAmountOfRevenue : {
						title : 'Average freight',
						width : '10%',
						edit : true
					} ,
					Revenue : {
						title : 'Commission Revenue',
						width : '10%',
						edit : true
					}					
		}
			});
			$('#LoggedInTableContainer').jtable('load');
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
	 