<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="/trux/resources/css/style.css" rel="stylesheet"
	type="text/css" />
<link
	href="/trux/resources/jtable/jquery.jqGrid-4.4.1/css/ui.jqgrid.css"
	rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css"
	rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js"	type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/jquery.jqGrid-4.4.1/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="/trux/resources/jtable/jquery.jqGrid-4.4.1/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="/trux/resources/jtable/jquery.jqGrid-4.4.1/js/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="/trux/resources/core/countdown.js"></script>
<script type="text/javascript" src="/trux/resources/core/countdown.min.js"></script>

<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>
<style type="text/css" media="screen">
th.ui-th-column div {
	white-space: normal !important;
	height: auto !important;
	padding: 2px;
}
</style>
<script type="text/javascript">

var placeSearch, autocomplete,autocompleteTo;
var componentForm = {
  street_number: 'short_name',
  route: 'long_name',
  locality: 'long_name',
  administrative_area_level_1: 'short_name',
  country: 'long_name',
  postal_code: 'short_name'
};

function initialize() {
  // Create the autocomplete object, restricting the search
  // to geographical location types.
  autocomplete = new google.maps.places.Autocomplete(
      /** @type {HTMLInputElement} */(document.getElementById('fromLocation')),
      { types: ['geocode'] });
  // When the user selects an address from the dropdown,
  // populate the address fields in the form.
  google.maps.event.addListener(autocomplete, 'place_changed', function() {
    fillInAddress();
  });
  
  autocompleteTo = new google.maps.places.Autocomplete(
	       /** @type {HTMLInputElement} */(document.getElementById('toLocation')),
	       { types: ['geocode'] });
	   // When the user selects an address from the dropdown,
	   // populate the address fields in the form.
	   google.maps.event.addListener(autocompleteTo, 'place_changed', function() {
	     fillInAddress();
	   });
  
}

// [START region_fillform]
function fillInAddress() {
  // Get the place details from the autocomplete object.
  var place = autocomplete.getPlace();

  for (var component in componentForm) {
    document.getElementById(component).value = "";
    document.getElementById(component).disabled = false;
  }

  // Get each component of the address from the place details
  // and fill the corresponding field on the form.
  for (var i = 0; i < place.address_components.length; i++) {
    var addressType = place.address_components[i].types[0];
    if (componentForm[addressType]) {
      var val = place.address_components[i][componentForm[addressType]];
      document.getElementById(addressType).value = val;
    }
  }
}
// [END region_fillform]

// [START region_geolocation]
// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
function geolocate() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      var geolocation = new google.maps.LatLng(
          position.coords.latitude, position.coords.longitude);
      var circle = new google.maps.Circle({
        center: geolocation,
        radius: position.coords.accuracy
      });
      autocomplete.setBounds(circle.getBounds());
    });
  }
}
// [END region_geolocation]
</script>
</head>
<body onload="initialize();">

	<script type="text/javascript">

function  driverStatus()
{ 
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
    document.getElementById("driversStatusReport").innerHTML=xmlhttp.responseText;
    }
  }
  xmlhttp.open("GET","/trux/reportsapi/loggedInReports?flag="+flag,true); 
xmlhttp.send();
}




 
</script>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<fieldset class="fieldset2 col-lg-12 borderManager">
					<legend class="borderManager">Order Search Form</legend>
					<p>
						<input type="hidden" id="time" readonly="readonly" class="input-sm">
					</p>
					<f:form commandName="orderFilter" action="ordergridFilterReports" method="post" cssClass="form-inline">
						<div class="row" style="margin-top: -2%;">
							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">From</div>
								<f:input path="from" id="txtStartDate"
									cssClass="form-control input-sm"
									cssStyle="width:80%;cursor: pointer;"
									placeholder="yyyy/mm/dd hh:mm" />
								<img alt="" style="cursor: pointer;"
									src="/trux/resources/images/calendar.png" id="fromImg"
									title="Please click on text field area after that  show the calander" />
							</div>
							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">To</div>
								<f:input path="to" id="txtEndDate"
									cssClass="form-control input-sm"
									cssStyle="width:80%;cursor: pointer;"
									placeholder="yyyy/mm/dd hh:mm" />
								<img alt="" style="cursor: pointer;"
									src="/trux/resources/images/calendar.png" id="toImg"
									title="Please click on text field area after that  show the calander" />
							</div>
							<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">Vehicle Association</div>
						<f:select path="dstatus" id="dstatus" cssClass="input-sm" cssStyle="width:100%;cursor: pointer;">
						<f:option value="">--Select Vehicle Association--</f:option>
						<f:option value="Mapped">Leased</f:option>
						<f:option value="Free">On Demand</f:option>
					 
						</f:select>
						</div>
							<%-- <div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">City</div>
								<f:select path="city" id="city" cssClass="input-sm"
									cssStyle="width:100%;cursor: pointer;">
									<f:option value="">--Select City --</f:option>
									<f:option value="Delhi">Delhi</f:option>
									<f:option value="Lucknow">Lucknow</f:option>
						<f:option value="Mumbai">Mumbai</f:option>
						<f:option value="Noida">Noida</f:option>
								</f:select>
							</div> --%>
							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">Status</div>
								<f:select path="status" id="status" cssClass="input-sm"
									cssStyle="width:100%;cursor: pointer;">
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
							<div class="clearfix margin_10"></div>
							
							
							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">Vehicle type</div>
								<f:select path="vehicleType" id="vehicleType"
									cssClass="input-sm" cssStyle="width:100%;cursor: pointer;">
									<f:option value="">--Select Vehicle type</f:option>
									<%-- <f:option value="Tata Ace">Tata Ace</f:option>
									<f:option value="Tata 407">Tata 407</f:option>
									<f:option value="Tata 709">Tata 709</f:option>
									<f:option value="Bolero Pickup">Bolero Pickup</f:option>
									<f:option value="Mahindra Champion">Mahindra Champion</f:option>
									<f:option value="Maruti Eeco">Maruti Eeco</f:option> --%>
									
							<option value="Mahindra Champion">Champion</option>
							<option value="Maruti Eeco">Eeco</option>
							<option value="Tata Ace">Tata Ace</option>
							<option value="Tata 407">Tata 407 (10 Ft)</option>
							<option value="Tata 709">Tata 709 (14 Ft)</option>
							<option value="Bolero Pickup">Bolero Pickup</option>
		 					<option value="17 Feet Single Axle">17 Feet - Single Axle</option>
							<option value="17 Feet Double Axle">17 Feet - Double Axle</option>
							<option value="19 Feet Single Axle">19 Feet - Single Axle</option>
							<option value="19 Feet Double Axle">19 Feet - Double Axle</option>
							<option value="19 Feet Multi-Axle">19 Feet - Multi-Axle</option>
							<option value="22 Feet Single Axle">22 Feet - Single Axle</option>
							<option value="22 Feet Double Axle">22 Feet - Double Axle</option>			
							<option value="22 Feet Multi-Axle">22 Feet - Multi-Axle</option>
							<option value="24 Feet Single Axle">24 Feet - Single Axle</option>
							<option value="24 Feet Double Axle">24 Feet - Double Axle</option>	
							<option value="24 Feet Multi-Axle">24 Feet - Multi-Axle</option>
							<option value="28 Feet Single Axle">28 Feet - Single Axle</option>
							<option value="28 Feet Double Axle">28 Feet - Double Axle</option>	
							<option value="28 Feet Multi-Axle">28 Feet - Multi-Axle</option>
							<option value="32 Feet Single Axle">32 Feet - Single Axle</option>	
							<option value="32 Feet Double Axle">32 Feet - Double Axle</option>	
							<option value="32 Feet Multi-Axle">32 Feet - Multi-Axle</option>
							<option value="40 Feet Double Axle">40 Feet - Double Axle</option>	
							<option value="40 Feet Multi-Axle">40 Feet - Multi-Axle</option>
								</f:select>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">Vehicle No</div>
								<f:input path="vehicleNo" id="vehicleNo"
									cssClass="form-control input-sm"
									cssStyle="width:100%;cursor: pointer;" placeholder="Vehicle No" />
							</div>
							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">Cust. Mobile No</div>
								<f:input path="custMobile" id="custMobile"
									cssClass="form-control input-sm"
									cssStyle="width:100%;cursor: pointer;"
									placeholder="Cust. Mobile No" />
							</div>
							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">Driver Mobile</div>
								<f:input path="driverMobile" id="driverMobile"
									cssClass="form-control input-sm"
									cssStyle="width:100%;cursor: pointer;"
									placeholder="Driver Mobile" />
							</div>

							<div class="col-lg-3 col-md-3 col-sm-12">
								<div
									style="margin-top: 6px; font-size: 14px; text-align: left; margin-left: 0px; margin-top: 8px;">
									<button class="btn btn-danger btn-sm btn_nav2"
										onclick="return appliedFromToDate();">Filter</button>

								</div>
							</div>
						</div>
					</f:form>
					<f:form action="/trux/reportsapi/gridclear" method="get">
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div
								style="text-align: left; font-size: 14px; margin-top: -31px; margin-left: 52px;">
								<button class="btn btn-danger btn-sm btn_nav2">Clear</button>
							</div>
						</div>
					</f:form> 
					  
				</fieldset>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">  <!-- table-responsive -->
			<fieldset class="fieldset2 borderManager table-responsive">
				<a href="/trux/reportsapi/ordergridXLSReports"
					style="color: black; font-size: 10px; padding-left: -1"><img
					alt="" src="/trux/resources/images/xls.png" width="15px;"
					height="15px;">Export to Excel</a>
				<table id="orderList" class="table-responsive"></table>
				<div id="pOrderList"></div>
			</fieldset>
		</div>

	</div>

	<script type="text/javascript">
	  function diffTwoDates(){
			 var now = new Date();		 
			 var date2 =new Date("2015-06-27 12:40:42");		 
		     var hourDiff = now - date2;
		     var diffHrs = Math.round((hourDiff % 86400000) / 3600000);
		     var diffMins = Math.round(((hourDiff % 86400000) % 3600000) / 60000);
		    alert(diffMins);
		   return diffMins;
		} 
jQuery(document).ready(function() {	
	
	function myTimer() {
	    var d = new Date();
	    var t = d.toLocaleTimeString(); 
	    document.getElementById("time").value = t;
	}
  var myVar = setInterval(function(){ myTimer() }, 1000);
  
  

  
  function diffTwoDate(data2){
		 var now = new Date();		 
		 var date2 =new Date(data2);		 
	     var hourDiff = now - date2;
	     var diffHrs = Math.round((hourDiff % 86400000) / 3600000);
	     var diffMins = Math.round(((hourDiff % 86400000) % 3600000) / 60000);
	    // alert(diffMins);
	   return diffMins;
	}
  function timeDuration(data){
	     var monthNamesTo = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"];
		 var now = new Date(); 
		 var tt= now.toLocaleTimeString(); 
		 var timeValue=tt.replace("AM","");
		 var timeValue1=timeValue.replace("PM","");
		 var todates=monthNamesTo[now.getMonth()] + "/" + now.getDate()+"/"+now.getFullYear() +" "+timeValue1.replace(" ","");
	     difference = new Date(new Date(data) - new Date(todates)).toUTCString().split(" ")[4];
		return difference;
	 }
	function julianDayFromDate(dates){
		var dateToday = dates; 
		var yyyy = dateToday.substring(7,12);
		var mmv = dateToday.substring(3,6); 
		var mm=01;
		if(mmv=="Jan"){mm=01;}if(mmv=="Feb"){mm=02;}if(mmv=="Mar"){mm=03;}if(mmv=="Apr"){mm=04;}
		if(mmv=="May"){mm=05;}if(mmv=="Jun"){mm=06;}if(mmv=="Jul"){mm=07;}if(mmv=="Aug"){mm=08;}
		if(mmv=="Sep"){mm=09;}if(mmv=="Oct"){mm=09;}if(mmv=="Nov"){mm=11;}if(mmv=="Dec"){mm=12;}
		var dd = dateToday.substring(0,2);	
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

	function julianDayToDate(dates){
		var dateToday = dates; 
		var yyyy = dateToday.substring(0,4); 
		var mm = dateToday.substring(5,7);  
		var dd = dateToday.substring(7,10); 
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

	function datatValidate(date){
		var flag=false;
		 var bookingDateTime=date; 
		 var monthNamesTo = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"];
		 var now = new Date();
		 var todates=now.getFullYear() + " " + monthNamesTo[now.getMonth()] + " " + now.getDate();
	     var fromDate=julianDayFromDate(bookingDateTime);
	     var toDate=julianDayToDate(todates); 
		 if(toDate<=fromDate)
			 { 	flag=true;
			  }else{ 
				flag=false;
			  }
		 return flag;
		 }
	
	function dateStatusValidate(date){
		 var flag=false;
		 var bookingDateTime=date; 
		 var monthNamesTo = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"];
		 var now = new Date();
		 var todates=now.getFullYear() + " " + monthNamesTo[now.getMonth()] + " " + now.getDate();
	     var fromDate=date;
	     var toDate=julianDayToDate(todates); 
	     var totalTime=(fromDate-toDate);
	   	 if(toDate<=fromDate)
			 { 	flag=true;
			  }else{ 
				flag=false;
			  }
		 return flag;
		 }
	 
	    $.jgrid.nav.edittext = "Edit-Order";
	    $.jgrid.nav.searchtext = "Search";
	    $.jgrid.nav.refreshtext = "Re-Fresh";	 
	  
		$("#orderList").jqGrid({
			    url:'/trux/reportsapi/orderGridRequest?action=list', 
			    datatype: 'json',
			    mtype: 'POST', 
			    contentType: "application/json; charset=utf-8",
			    jsonReader : {
					root: "rows",
					page: "page",
					total: "total",
					records: "records",
					repeatitems: false,
					cell: "cell",
					id: " "
				},
				colNames : ['S.No','Order Id','Customer Name','Customer phone no','Order Date','Pickup Date Time', 'Labour Count','Pickup Point','Drop Point' ,'Trip end date time',/* 'Trip end time', */'Vehicle Type','Vehicle No','Driver Name','Driver Number','Status Change Time ','statusChangeDurationOfTime','statusChangeDurationOfTimeDateFormate','Current Status','Freight','Expected Fareight'],
				colModel : [
							{name : 'serialNo',index : 'serialNo',width : 30,hidden : false,editable : false,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
				            {name : 'bookingId',index : 'bookingId',width : 40,hidden : false,editable:true,editrules : {edithidden : false}, editoptions:{readonly: true}},
		                    {name : 'customerName',index : 'customerName',width : 80,hhidden : false,editable:true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'custmerPhonenumber',index : 'custmerPhonenumber',width : 70,hhidden : false,editable:true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'bookingDateTime',index : 'bookingDateTime',width : 100,hidden : false,editable:true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } }, 
		                    {name : 'dates',index : 'dates',width : 80,hidden : false,editable:true,editrules:{required:true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' },editoptions: {size: 20, maxlengh: 20,dataInit: function(element) {$(element).datetimepicker({dateFormat: 'yy.mm.dd'})}} },
		                    {name : 'labourCount',index : 'labourCount',width : 50,hidden : false,editable : false,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'fromLocation',index : 'fromLocation',width : 120,hidden : false,editable:true,editrules:{required:true} ,cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'toLocation',index : 'toLocation',width : 120,hidden : false,editable:true,editrules:{required:true} ,cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'tripEndDates',index : 'tripEndDates',width : 70,hidden : false,editable:false,editrules:{edithidden : true}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    /* {name : 'tripEndTimes',index : 'tripEndTimes',width : 70,hidden : false,editable : false,editrules : {edithidden : true}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } }, */
				            {name : 'vehicleType',index : 'vehicleType',width : 60,hidden : false,editable:true,editrules:{required:true} ,cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'vehicleNumber',index : 'vehicleNumber',width : 60,hidden : false,editable:true,editrules:{required:true} ,cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'driverName',index : 'driverName',width : 60,hidden : false,editable:true,editrules:{required:true} ,cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } }, 
		                    {name : 'driverPhonenumber',index : 'driverPhonenumber',width : 62,hidden : false,editable:true,editrules:{required:true} ,cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'bookingDurationOfTime',index : 'bookingDurationOfTime',width : 60,hidden : true,editable:false,editrules:{edithidden : true}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'statusChangeDurationOfTime',index : 'statusChangeDurationOfTime',width : 60,hidden : true,editable:false,editrules:{edithidden : true}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'statusChangeDurationOfTimeDateFormate',index : 'statusChangeDurationOfTimeDateFormate',width : 60,hidden : true,editable:false,editrules:{edithidden : true}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'bookingStatus',index : 'bookingStatus',width : 72,hidden : false,editable:true,edittype:"select",editoptions:{ value:"Pending:Pending;Confirmed:Confirmed;Loading Start:Loading started;Loading Complete:Loading complete;Ride Start:Trip started;Unloading Start:Unloading Start;Completed:Completed;On Ride:On Ride;Cancelled:Cancelled;"},editrules:{required:true},cellattr: function (rowId, tv, rawObject, cm, rdata) { if(rdata.bookingStatus == "Confirmed"){return 'style="white-space: normal;color:#005128;"'}else return 'style="white-space: normal;"'},formatter: function (cellvalue, options, rowObject) {if(cellvalue == "Confirmed"){ return "<a target='_blank' style='color:#005128;' href=\"http://truxapp.com/tracking/tracking.jsp?bookingId=" + rowObject.bookingId + "\">" + cellvalue + "</a>";}else if(cellvalue == "Pending"){ return cellvalue ;}else if(cellvalue == "Completed"){ return cellvalue ;}else if(cellvalue == "Cancelled"){ return cellvalue ;}else { return "<a target='_blank' style='color:#00a653;' href=\"http://truxapp.com/tracking/tracking.jsp?bookingId=" + rowObject.bookingId + "\">" + cellvalue + "</a>";}},unformat: function (cellvalue, options, cellobject) { return cellobject.bookingId;}},	
		                    {name : 'totalFare',index : 'totalFare',width : 60,hidden : false,editable:true,editrules:{required:false},formatter: function (cellvalue, options, rowObject) {if(rowObject.bookingStatus == "Completed"){ return "<span style='color:black;'>" + rowObject.totalFare  + "</sapn>";}/* else if(rowObject.bookingStatus == "Pending"){ return  rowObject.expectedFare } */else { return rowObject.expectedFare;}},unformat: function (cellvalue, options, cellobject) { return cellobject.expectedFare;}},
		                    {name : 'expectedFare',index : 'expectedFare',width : 60,hidden : true,editable:false,editrules:{edithidden : true}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
			               ],
				rowNum :25,
				rowList : [5,10,15,20,25,30, 50, 60, 120, 240,480,1000 ],
				height : "100%",
				width : "100%",
				loadonce : true,
				fontsize : 11,
				shrinkToFit : true,
				rownumbers : false,
				/* caption : "Order Reports", */
				singleselect: true,
				pager : '#pOrderList',
				viewrecords : true,
				emptyrecords : "No records available",								
				gridComplete: function()
				{
				    var rows = $("#orderList").getDataIDs();				    
				    for (var i = 0; i < rows.length; i++)
				    {
				        var status = $("#orderList").getCell(rows[i],"bookingStatus"); 
				        var expectedFare = $("#orderList").getCell(rows[i],"expectedFare");				        
				        var bookingDateTime = $("#orderList").getCell(rows[i],"bookingDateTime");
				        var data1 = $("#orderList").getCell(rows[i],"bookingDurationOfTime");
				        var data2 = $("#orderList").getCell(rows[i],"statusChangeDurationOfTime");
				        var data3 = $("#orderList").getCell(rows[i],"statusChangeDurationOfTimeDateFormate");
				        
				        var valueofTimeDifferenceDuration = diffTwoDate(data1,data2);
				        var value = timeDuration(data2);			  	    	
				        
					   if(status == "Pending")
				        {   var rowData = $('#orderList').jqGrid('getRowData', rows[i]);
				        if(valueofTimeDifferenceDuration<=5){
				        	 rowData.bookingStatus ="<b style='color: red;font-size: 10px;'>"+status +"  "+valueofTimeDifferenceDuration+" Minutes</b><br>"+data3;
				        	
				        }else{
					  		 rowData.bookingStatus ="<b style='color: red;font-size: 10px;'>"+status +"  "+valueofTimeDifferenceDuration+" Minutes </b><br><b style='color: red;font-size: 10px;'>"+data3+"</b>";
					  	  }
				            rowData.totalFare=expectedFare;
						   $('#orderList').jqGrid('setRowData', rows[i], rowData);
						    $("#orderList").jqGrid('setRowData',rows[i],false, {  color:'#ff1300',weightfont:'bold'});            
				  	    	 
				        }else if(status == "Cancelled")
				        {   var flags= datatValidate(bookingDateTime);
				        	 if(flags==true)
					  		 {  $("#orderList").jqGrid('setRowData',rows[i],false, {  color:'#f0a30a',weightfont:'bold'});
		                        }else{ 
					  			$("#orderList").jqGrid('setRowData',rows[i],false, {  color:'#A7A7A7',weightfont:'bold'});
					  		  }  			       
				        }else if(status == "Confirmed") 
				        {   var rowData = $('#orderList').jqGrid('getRowData', rows[i]);
				              if(valueofTimeDifferenceDuration<=15){
				            	  rowData.bookingStatus ="<b style='color: green;font-size: 10px;'>"+status +"  "+valueofTimeDifferenceDuration+" Minutes</b><br>"+data3;
						  	 }else{
						  		 rowData.bookingStatus ="<b style='color: red;font-size: 10px;'>"+status +"  "+valueofTimeDifferenceDuration+" Minutes</b><br><b style='color: red;font-size: 10px;'>"+data3+"</b>";
						  	  }
				            $('#orderList').jqGrid('setRowData', rows[i], rowData);
				            $("#orderList").jqGrid('setRowData',rows[i],false, {  color:'#008000',weightfont:'bold'});            
				          }
				        else if(status == "Completed")
				        {
				            $("#orderList").jqGrid('setRowData',rows[i],false, {  color:'black',weightfont:'bold'});            
				        }
				        else if(status == "Loading Start")
				        {   
				        	var rowData = $('#orderList').jqGrid('getRowData', rows[i]);
				              if(valueofTimeDifferenceDuration<=45){
				            	  rowData.bookingStatus ="<b style='color: red;font-size: 10px;'>"+status +"  "+valueofTimeDifferenceDuration+" Minutes</b><br>"+data3;
						  	 }else{
						  		 rowData.bookingStatus ="<b style='color: red;font-size: 10px;'>"+status +"  "+valueofTimeDifferenceDuration+" Minutes</b><br><b style='color: red;font-size: 10px;'>"+data3+"</b>";
						  	  }
				            $('#orderList').jqGrid('setRowData', rows[i], rowData);
				        	 $("#orderList").jqGrid('setRowData',rows[i],false, {  color:'#00a653',weightfont:'bold'});// $("#orderList").jqGrid('setRowData',rows[i],false, {  color:'#00a653',weightfont:'bold',background:'#00a653'});            
				        	 
							 
				        }
				        else  
				        {    var rowData = $('#orderList').jqGrid('getRowData', rows[i]);
			              if(valueofTimeDifferenceDuration>15){
					        	 rowData.bookingStatus ="<b style='color: red;font-size: 10px;'>"+status +"  "+valueofTimeDifferenceDuration+" Minutes</b><br><b style='color: red;font-size: 10px;'>"+data3+"</b>";
						  	 }else{
					        	 rowData.bookingStatus ="<b style='color: red;font-size: 10px;'>"+status +"  "+valueofTimeDifferenceDuration+" Minutes</b><br>"+data3;
						  	  }
				            $('#orderList').jqGrid('setRowData', rows[i], rowData);
				             $("#orderList").jqGrid('setRowData',rows[i],false, {  color:'#00a653',weightfont:'bold'});            
				           
						 
				        }
				    }
				} 
				 
		    });
	//$("#orderList").jqGrid('navGrid','#pOrderList',{edit:true,add:true,del:true,search:false,refresh:false}, { beforeShowForm: function(form) {$('#tr_roll',form).hide();} }, { beforeShowForm: function(form) {$('#tr_roll',form).show();} }, { } ); });
   	$("#orderList").jqGrid('navGrid', '#pOrderList', {
			edit : true,
			add : false,
			del : false,
			exl : true,
			search : true},
			{width : 'auto',url : '/trux/reportsapi/orderGridRequest?action=edit'}, 
			{width : 'auto',url : '#'}, 
			{width : 'auto', serializeDelData: function (postdata) {
				  var selr = jQuery('#orderList').jqGrid('getGridParam','selrow'); 
			      var rowData = $("#orderList").jqGrid('getRowData');
			      return true; 
				    },
		        	onclickSubmit: function (rp_ge, postdata) { 
		        		rp_ge.url = '#?bookingId='+registrationNumber ;
			   	        
		          }
			},			 
		    {sopt : [ 'cn', 'bw', 'ew' ],
			closeOnEscape : true,
			multipleSearch : false,
			closeAfterSearch : true,
			closeAfterAdd : true,
			closeAfterEdit : true,
			reloadAfterSubmit:true
			});
  
		});
		$("#orderList").jqGrid('navGrid','#pOrderList',{edit:true,add:true,del:true,search:false,refresh:false},
				{ beforeShowForm: function(form)
			{$('#orderList',form).hide();} 
		}, { 
			beforeShowForm: function(form) {
				$('#orderList',form).show();}
		}, { } ); 
		$.jgrid.edit = {  editCaption: "Edit Order", bSubmit: "Submit", bCancel: "Cancel", bClose: "Close", bYes : "Yes", bNo : "No", bExit : "Cancel", closeAfterAdd:true, closeAfterEdit:true, reloadAfterSubmit:true, msg: { required: "is mandatory or required", number: "is a number field. Enter a valid number", minValue: "should not be less than ", maxValue: "should not be more than " }, errorTextFormat: function (response) { if (response.status != 200) { return "Error encountered while processing. Please check the accuracy of data entered."; } }, 
				afterSubmit: function (response) {
					var objectArr = response.responseText;
					 var message=objectArr.split(",");
				     var messages="";
				     var messages1="";
					 for (var i = 0; i < message.length; i++) { 
						 messages1=message[i];
						 messages= messages1.substr(0, messages1.length-1);
						 }
					 confirm(messages);
					 // alert(messages);
					
                   // alert('After Submit \n' +'statusText: '+ response.statusText);
                   /*  var myInfo = '<div class="ui-state-highlight ui-corner-all">'+
                                 '<span class="ui-icon ui-icon-info" ' +
                                     'style="float: left; margin-right: .3em;"></span>' +
                                 response.statusText + 'Inserted'+
                                 '</div>',
                         $infoTr = $("#orderList" + $.jgrid.jqID(this.id) + ">tbody>tr.tinfo"),
                        $infoTd = $infoTr.children("td.topinfo"); 
                    $infoTd.html(myInfo);
                    $infoTr.show(); */

                    // display status message to 3 sec only
                    setTimeout(function () {
                        $infoTr.slideUp("slow");
                    }, 5000);

                    return [true, "", ""]; // response should be interpreted as successful
                },
                errorTextFormat: function (response) {
                alert('Error Text Format: \n' +'statusText: '+ response.statusText);
                return '<span class="ui-icon ui-icon-alert" ' +
                                 'style="float:left; margin-right:.3em;"></span>' +
                                response.statusText;}, };   
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
 

function julianDayCalculater(dates)
{	 
	var dateToday = dates; 
	var yyyy = dateToday.substring(0,4);
	var mm = dateToday.substring(5,7); 
	var dd = dateToday.substring(8,10);	 
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
		var from = objFromDate.substring(0,10);
		var to = objToDate.substring(0,10);		
		if(!from=="" && !to==""){ 
			
	    var	fromDate=julianDayCalculater(from);
	    var	toDate=julianDayCalculater(to);
	    
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
	}}



</script>
</body>
</html>