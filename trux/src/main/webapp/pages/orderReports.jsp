 <%@page import="com.trux.model.OrderFilterReportsDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>C
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/trux/resources/css/style.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery.jtable.js" type="text/javascript"></script>
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script> 

<title>Order Search Reports</title>
 
</head>
<body>

<div class="container">  
	<div class="row">
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager">  <legend class="borderManager">Order Search Form</legend>
			<f:form commandName="orderFilter" action="orderFilterReports" method="post" cssClass="form-inline" ><!-- onsubmit="return appliedFromToDate();" -->
				
					<div class="row" style="margin-top:-2%; ">                                        									                                  
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">From</div>
							<f:input path="from" id="txtStartDate" cssClass="form-control input-sm" cssStyle="width:80%;cursor: pointer;"  placeholder="yyyy/mm/dd hh:mm"/> <img alt="" style="cursor: pointer;"src="/trux/resources/images/calendar.png" id="fromImg" title="Please click on text field area after that  show the calander"/> 
						</div>                                               
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div style="margin-bottom:6px;">To</div>
							<f:input path="to" id="txtEndDate"  cssClass="form-control input-sm" cssStyle="width:80%;cursor: pointer;" placeholder="yyyy/mm/dd hh:mm"/> <img alt="" style="cursor: pointer;"src="/trux/resources/images/calendar.png" id="toImg" title="Please click on text field area after that  show the calander"/>  
						</div>	
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">City</div>
						<f:select path="city" id="city" cssClass="input-sm" cssStyle="width:100%;cursor: pointer;">
						<f:option value="">--Select City --</f:option>
						<f:option value="Delhi">Delhi</f:option>
						<%-- <f:option value="Lucknow">Lucknow</f:option>
						<f:option value="Mumbai">Mumbai</f:option>
						<f:option value="Noida">Noida</f:option> --%>
						</f:select>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Status</div>
						<f:select path="status" id="status" cssClass="input-sm" cssStyle="width:100%;cursor: pointer;">
						  <f:option value="">--Select Status--</f:option> 
						   <f:option value="Pending">Pending</f:option>
									<f:option value="Confirmed">Confirmed</f:option>
									<f:option value="Loading Start">Loading started</f:option>
									<f:option value="Loading Complete">Loading complete</f:option>
									<f:option value="Ride Start">Trip started</f:option>
									<f:option value="Unloading Start">Unloading Start</f:option>
									<f:option value="Completed">Completed</f:option>
									<f:option value="On Ride">On Ride</f:option>
									<f:option value="Cancelled">Cancelled</f:option>
						</f:select> 
						</div>
						<div class="clearfix margin_05"></div>						
						<div class="col-lg-3 col-md-3 col-sm-12">
						<div style="margin-bottom:6px;">Vehicle type</div>
							<f:select path="vehicleType" id="vehicleType" cssClass="input-sm" cssStyle="width:100%;cursor: pointer;">
							<f:option value="">--Select Vehicle type</f:option>
							<f:option value="Tata Ace">Tata Ace</f:option>
							<f:option value="Tata 407">Tata 407</f:option>
							<f:option value="Tata 709">Tata 709</f:option>
							<f:option value="Bolero Pickup">Bolero Pickup</f:option>
							<f:option value="Mahindra Champion">Mahindra Champion</f:option>
							<f:option value="Maruti Eeco">Maruti Eeco</f:option>
							</f:select> 
						</div>	
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Vehicle No</div>
						<f:input path="vehicleNo" id="vehicleNo" cssClass="form-control input-sm" cssStyle="width:100%;cursor: pointer;" placeholder="Vehicle No"/>
						</div> 					
						 <div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Cust. Mobile No</div>
						<f:input path="custMobile" id="custMobile" cssClass="form-control input-sm" cssStyle="width:100%;cursor: pointer;" placeholder="Cust. Mobile No" />
						</div>  
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Driver Mobile</div>
						<f:input path="driverMobile" id="driverMobile" cssClass="form-control input-sm" cssStyle="width:100%;cursor: pointer;" placeholder="Driver Mobile"/>
						</div>
						
						<div class="col-lg-3 col-md-3 col-sm-12">     
							<div style="margin-top:6px; font-size:14px; text-align:left; margin-left:0px;margin-top:8px;">
								<button class="btn btn-danger btn-sm">Filter</button> 
						                             
							</div>
						</div> 
					</div>											
					<!-- <div class="clearfix margin_10"></div> -->
				
			</f:form>
			<f:form action="/trux/reportsapi/clear" method="get">
			<div class="col-lg-3 col-md-3 col-sm-12">  
		 
			<div style="text-align: left; font-size: 14px; margin-top: -31px; margin-left: 52px;">
			<button class="btn btn-danger btn-sm">Clear</button>                              
			</div>
			</div></f:form> 
			</fieldset>
		</div>
	</div>		
</div>

   <div class="container">  
        <div class="row">
          <fieldset class="fieldset2 borderManager table-responsive">
            <!--  <div class="inner_content_w"> -->
          
           <div	style="width: 100%; margin-right: 0%; margin-left: 0%; text-align: left;" >       
            <div id="LoggedInTableContainer"></div>
          <!--   </div>   -->         
        </div>
        </fieldset>
    </div>
   </div> 
<script type="text/javascript">

function clearField(){
	document.getElementById('form').innerHTML="";
	document.getElementById('to').innerHTML="";
	document.getElementById('city').innerHTML="";
	document.getElementById('status').innerHTML="";
	document.getElementById('vehicleNo').innerHTML="";
	document.getElementById('vehicleType').innerHTML="";
	document.getElementById('driverMobile').innerHTML="";
	document.getElementById('custMobile').innerHTML="";
	}
    	$(document).ready(function() {
    		
    		var snIndexId=0;
    		var bookingIds;
    		var snIndexIds=0;
			$('#LoggedInTableContainer').jtable({
				title : "Order Report",
				paging : true, //Enable paging
				pageSize : 10, //Set page size (default: 10)  
				
				toolbar: {
				    items: [{
				        Tooltip: 'Click here to export this table to excel',
				        icon: '/trux/resources/images/xls.png',
				        text: 'Export to Excel',
				        click: function () {
				         window.location = '/trux/reportsapi/orderXLSReports?action=export-excel';
				         e.preventDefault();
				        }
				    }]
				},
				actions : {
				 	listAction : '/trux/reportsapi/orderRequest?action=list', 
				},
				fields : {
					serialNo : {
						title : 'S. No',
						sort : true,
						width : '5%',
						key : true,
						list : true,
						edit : false,
						create : true,
						display: function(data){
							snIndexId=data.record.serialNo;
							snIndexIds=(parseInt(snIndexId)-2);
							 return '<span style="color: black;">' +snIndexId +'</span>';
					   }
					},
					bookingId : {
						title : 'Order Id',
						sort : true,
						width : '5%',
						key : true,
						list : true,
						edit : false,
						create : true,
						display: function(data){
							 bookingIds=data.record.bookingId;
							 return '<span> <a style="color: black;" href="http://truxapp.com/tracking/tracking.jsp?bookingId=' + data.record.bookingId + '" target="_blank">' + data.record.bookingId +'</a></span>';
					   } 
					},
					customerName : {
						title : 'Customer Name',
						width : '10%',
						edit : true
					},
					booking_time : {
						title : 'Order date',
						width : '10%',
						edit : true
					},
					dates : {
						title : 'Pickup date',
						width : '10%',
						edit : true
					},
					times : {
						title : 'Pickup time',
						width : '10%',
						edit : true
					},
					fromLocation : {
						title : 'Pickup Point',
						width : '15%',
						edit : true
					},
					toLocation : {
						title : 'Drop Point',
						width : '15%',
						edit : true
					},
					tripEndDates : {
						title : 'Trip end date ',
						width : '10%',
						edit : true
					},tripEndTimes : {
						title : 'Trip end time',
						width : '10%',
						edit : true
					},

					vehicleType : {
						title : 'Vehicle Type',
						width : '10%',
						edit : true
					},
					vehicleNumber : {
						title : 'Vehicle No',
						width : '9%',
						edit : true
					},
					driverName : {
						title : 'Driver Name',
						width : '10%',
						edit : true
					},
					driverPhonenumber : {
						title : 'Driver	Number',
						width : '9%',
						edit : true
					},
					bookingStatus : {
						title : 'Current Status',
						width : '10%',
						edit : true,	
						display: function(data){
							 var row_id=0;
							 var rowindex=0;
							 
							$.each(data, function(index, item){				  
				                var obj = item;
				                rowindex=obj.serialNo;
				                
				            }); 
							rowindex=rowindex-1;
							 console.debug('rowindex', rowindex); 
							   //rowindex=(parseInt(0))
							if(data.record.bookingStatus === "Pending") {
							 $('#LoggedInTableContainer').find(".jtable tbody tr").eq(rowindex).css("background", "#ff1300");
							 return '<span style="color: black;">' + data.record.bookingStatus +'</span>'; 
                             }else if (data.record.bookingStatus === "Cancelled") {
                            	  $('#LoggedInTableContainer').find(".jtable tbody tr").eq(rowindex).css("background", "#f0a30a");
								 return '<span style="color: black;">' + data.record.bookingStatus +'</span>'; 
                             }else if (data.record.bookingStatus === "Confirmed") {
                            	  $('#LoggedInTableContainer').find(".jtable tbody tr").eq(rowindex).css("background", "#005128");
								 return '<span style="color: black;"><a style="color: black;" href="http://truxapp.com/tracking/tracking.jsp?bookingId='+ bookingIds +'" target="_blank">' + data.record.bookingStatus +'</a></span>'; 
                             }else if (data.record.bookingStatus === "Completed") {
                            	  return '<span style="color: black;">' + data.record.bookingStatus +'</span>'; 
                             }else if (data.record.bookingStatus === "Loading Start") {
                            	 $('#LoggedInTableContainer').find(".jtable tbody tr").eq(rowindex).css("background", "#00a653");
								 return '<span style="color: black;"><a style="color: black;" href="http://truxapp.com/tracking/tracking.jsp?bookingId='+ bookingIds +'" target="_blank">' + data.record.bookingStatus +'</a></span>'; 
                            }else{
                                 $('#LoggedInTableContainer').find(".jtable tbody tr").eq(rowindex).css("background", "#00a653");
                            	 return '<span style="color: black;"><a style="color: black;" href="http://truxapp.com/tracking/tracking.jsp?bookingId='+ bookingIds +'" target="_blank">' + data.record.bookingStatus +'</a></span>'; 
                            }							 
		               },
					},
					totalFare : {
						title : 'Freight',
						width : '10%',
						edit : true
					}/*,
					expectedFare : {
						title : 'expectedFare	Freight',
						width : '10%',
						edit : true
					},
					Revenue : {
						title : 'Revenue',
						width : '10%',
						edit : true
					} ,
					totalAmountOfRevenue : {
						title : 'total Revenue',
						width : '10%',
						edit : true
					} */
					

				}
			});
			$('#LoggedInTableContainer').jtable('load');
			 
    	});   	
    	
	</script>

	
<script type="text/javascript">

var dateToday = new Date();
var dd = dateToday.getDate();
var mm = dateToday.getMonth()+1; 
var yyyy = dateToday.getFullYear();
var toYears=parseInt(yyyy);
$(function() {
	 $('#txtStartDate, #txtEndDate').datetimepicker({
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
  /* $('#txtStartDate, #txtEndDate').datepicker({
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
	 