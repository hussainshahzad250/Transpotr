<%@page import="com.trux.utils.DateFormaterUtils"%>
<%@page import="com.trux.model.DriverDeviceVehicleMapping"%>
<%@page import="java.util.Date"%>
<%@page import="com.trux.model.CustomerBookingDetails"%> 
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Trux</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 
<script type="text/javascript">
function assignDrivers(id)
{
var ids=id; 
var bookingId=document.getElementById("bookingId"+ids).value;
var assignDriverPhoneNumber=document.getElementById("assignDriverId"+ids).value;
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
    document.getElementById("assignId"+ids).innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","/trux/booking/matchOrderandDriver?bookingId="+bookingId+"&driverPhoneNumber="+assignDriverPhoneNumber,true);
xmlhttp.send();
}

function allVehicleDriversDetail(id)
{
var ids=id;  
var vehicalTypeId=document.getElementById("vehicalTypeId"+ids).value;
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
    document.getElementById("assignDriverId"+ids).innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","/trux/reports/driverDetailsByVehicleType?vehicleType="+vehicalTypeId,true);
xmlhttp.send();
}
</script>
<style type="text/css">
tr:nth-child(even) {
    background-color: #F9F9F9;
    color: black;
}
tr:nth-child(odd) {
    background-color: #FFFFFF;
    color: black;
}
th{
	white-space: normal !important;
	height: auto !important;
	padding: 2px;
}
</style>
</head>
<body>
<fieldset class="fieldset2 borderManager table-responsive">
<div style="overflow: auto; height: 500px;">
	<div
		style="width: 100%; float: left; margin: auto; position: relative; z-index: 999999;">
		 
	</div>
	<div style="width: 100%; float: left; margin: auto;">
		<div class="hs_page_title">
			<div  class="container" style="background-color: #0B67CD;color: white;border: 0px; ">
				<div class="row" style="margin-left: 0px; margin-right: 0; margin-top: 5px;">
					<h5 > <a href="bookingXLSReports" style="color: black; font-size: 12px; padding-left: -1;color: white;"><img
					alt="" src="/trux/resources/images/xls.png" width="15px;" height="15px;" border="0">Export to Excel Booking Report</a> </h5>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12" style="padding-left: 0px;">
				<div class="row-fluid" style="width: 1091px; height: auto;">
					<table   style="font-size: 11px; height: auto;color: black;"  >
						<tbody >
						<thead style="height: 30px;">
							<tr style="background-color: #2D89EF;color: white;border: 0px; ">
								<th style="width: 5%;">
									<div class="center">SR No.</div>
								</th>
								<th><div style="width: 10%; height: 50px;">Booking Id</div></th>
								<th><div style="width: auto;">Customer Name</div></th>
								<th><div style="width: auto;">Phone Number</div></th>
								<th><div style="width: auto;">From Location</div></th>
								<th><div style="width: auto;"> To Location</div></th>
								<th><div style="width: auto;">Fares</div></th> 
								<th><div style="width: auto;">Booking Status</div></th>								
								<th><div style="width: auto;">Loading Time</div></th>
								<th><div style="width: auto;">Vehicle Type</div></th> 
								<th><div title="Select the driver name and mobile to assign the booking vehicle">Available Driver Details</div></th>
								<th><div>Assign to</div></th>
								<th><div>Assign Status</div></th>
							</tr>
						</thead>
						<%!int i = 0;%>
						<% try{
						List<CustomerBookingDetails> customerBookingDetailsList = (List<CustomerBookingDetails>)request.getAttribute("customerBookingDetailsList");
 						 for (CustomerBookingDetails consumer : customerBookingDetailsList)
								{
									i++;
						%>
						<tr style="height: 5px;">
							<td><%=i%></td>
							<td style="width: auto;"><input type="hidden" id="bookingId<%=i%>" value="<%=consumer.getBookingId()%>"/> <%=consumer.getBookingId()%></td>
							<td style="width: auto;"><%=consumer.getCustomerName()%></td>
							<td style="width: auto;"><%=consumer.getCustmerPhonenumber()%></td>
							<td style="width: auto;"><%=consumer.getFromLocation()%></td>
							<td style="width: auto;"><%=consumer.getToLocation()%></td>
							<td style="width: auto;"><%=consumer.getExpectedFare()%></td>
							<td style="width: 100px;"><%=consumer.getBookingStatus()%></td>
							<td style="width: 100px;"><%=DateFormaterUtils.convertGMTToISTWithDate(new Date(consumer.getRideTime().getTime()+11*1800*1000).toString()) %></td> 
			        		<td><input type="hidden" id="vehicalTypeId<%=i%>" value="<%=consumer.getVehicleType()%>"/><div onclick="return allVehicleDriversDetail(<%=i%>);" title="Please click the vehicle content area after that populate the driver and vehicle details !" style="color: green;"><%=consumer.getVehicleType()%></div></td> 
							<td><select id="assignDriverId<%=i%>" style="width:100px;" title="Select the driver name and mobile to assign the booking vehicle">
							</select></td>
							<td><input type="button" onclick="return assignDrivers(<%=i%>);" value="Assign" title="Assign button"></td>
							<td><div id="assignId<%=i%>" style="overflow:auto;width: auto;height:45px;"></div></td> 
						</tr>
						<%
							}
							i = 0;}catch(Exception er ){%>
							
							<tr style="width: 100%;">
								<td colspan="13"><% out.print( "No any pending recourds !");} %></td></tr>
						 
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="clearfix margin_10"></div> 
	</div>
</fieldset>
</body>
</html>
  
 <%-- 
 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="/trux/resources/css/style.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/jquery.jqGrid-4.4.1/css/ui.jqgrid.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script> 
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/jquery.jqGrid-4.4.1/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script> 
<script type="text/javascript" src="/trux/resources/jtable/jquery.jqGrid-4.4.1/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="/trux/resources/jtable/jquery.jqGrid-4.4.1/js/jquery.jqGrid.src.js"></script>
 <style type="text/css" media="screen">
    th.ui-th-column div{
        white-space:normal !important;
        height:auto !important;
        padding:2px;
    }
</style>
</head>
<body> 
   <div class="container">  
        <div class="row">
          <fieldset class="fieldset2 borderManager">
         <!--  <a href="/trux/reportsapi/ordergridXLSReports" style="color: black;font-size: 10px;padding-left: -1"><img alt="" src="/trux/resources/images/xls.png" width="15px;" height="15px;">Export ot Excel</a> -->
          <table id="bookingList"></table>
		  <div id="pbookingList"></div>         
          </fieldset>        
        </div>
  
    </div>
  
 <script type="text/javascript">
 
jQuery(document).ready(function() {
	    $.jgrid.nav.searchtext = "Search";
	    $.jgrid.nav.refreshtext = "Re-Fresh";	 
		$("#bookingList").jqGrid({
			    url:'trux/reports/bookingGridReports',
			    datatype: 'json',
			    mtype: 'GET', 
			    jsonReader : {
					root: "rows",
					page: "page",
					total: "total",
					records: "records",
					repeatitems: false,
					cell: "cell",
					id: " "
				},
				colNames : ['S.No','Order Id','Customer Name','Order Date','Pickup Date','Pickup Time','Pickup Point','Drop Point' ,'Trip end date','Trip end time','Vehicle Type','Vehicle No','Driver Name','Driver Number','Current Status','Freight'],
				colModel : [
							{name : 'serialNo',index : 'serialNo',width : 30,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true}},
				            {name : 'bookingId',index : 'bookingId',width : 40,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true}},
		                    {name : 'customerName',index : 'customerName',width : 70,hhidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'bookingDateTime',index : 'bookingDateTime',width : 70,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } }, 
		                    {name : 'dates',index : 'dates',width : 70,hidden : false,editable:true,editrules:{required:true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'times',index : 'times',width : 50,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'fromLocation',index : 'fromLocation',width : 120,hidden : false,editable:true,editrules:{required:true} ,cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'toLocation',index : 'toLocation',width : 120,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'tripEndDates',index : 'tripEndDates',width : 70,hidden : false,editable:true,editrules:{required:true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'tripEndTimes',index : 'tripEndTimes',width : 70,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
				            {name : 'vehicleType',index : 'vehicleType',width : 75,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'vehicleNumber',index : 'vehicleNumber',width : 75,hhidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'driverName',index : 'driverName',width : 80,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } }, 
		                    {name : 'driverPhonenumber',index : 'driverPhonenumber',width : 65,hidden : false,editable:true,editrules:{required:true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' }},
		                    {name : 'bookingStatus',index : 'bookingStatus',width : 65,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"'},formatter: function (cellvalue, options, rowObject) {if(cellvalue == "Confirmed"){ return "<a target='_blank' href=\"http://truxapp.com/tracking/tracking.jsp?bookingId=" + rowObject.bookingId + "\">" + cellvalue + "</a>";}else if(cellvalue == "Pending"){ return cellvalue ;}else if(cellvalue == "Completed"){ return cellvalue ;}else if(cellvalue == "Cancelled"){ return cellvalue ;}else { return "<a target='_blank' href=\"http://truxapp.com/tracking/tracking.jsp?bookingId=" + rowObject.bookingId + "\">" + cellvalue + "</a>";}},unformat: function (cellvalue, options, cellobject) { return cellobject.bookingId;}},	
		                    {name : 'totalFare',index : 'totalFare',width : 60,hidden : false,editable:true,editrules:{required:true} },
		                    ],
				rowNum :5,
				rowList : [5,10,15,20,30, 50, 60, 120, 240, 480 ],
				height : "100%",
				width : "100%",
				loadonce : true,
				fontsize : 11,
				shrinkToFit : true,
				rownumbers : false,
				caption : "Order Reports",
				singleselect: true,
				pager : '#pbookingList',
				viewrecords : true,
				emptyrecords : "No racords available"
				});								
		/* 		gridComplete: function()
				{
				    var rows = $("#bookingList").getDataIDs(); 
				    for (var i = 0; i < rows.length; i++)
				    {
				        var status = $("#bookingList").getCell(rows[i],"bookingStatus"); 
				        
				        if(status == "Pending")
				        {
				            $("#bookingList").jqGrid('setRowData',rows[i],false, {  color:'white',weightfont:'bold',background:'#ff1300'});            
				       
				        }else if(status == "Cancelled")
				        {
				            $("#bookingList").jqGrid('setRowData',rows[i],false, {  color:'white',weightfont:'bold',background:'#f0a30a'});            
				       
				        }else if(status == "Confirmed")
				        {
				            $("#bookingList").jqGrid('setRowData',rows[i],false, {  color:'white',weightfont:'bold',background:'#005128'});            
				        }
				        else if(status == "Completed")
				        {
				            $("#bookingList").jqGrid('setRowData',rows[i],false, {  color:'black',weightfont:'bold'});            
				        }
				        else if(status == "Loading Start")
				        {
				            $("#bookingList").jqGrid('setRowData',rows[i],false, {  color:'white',weightfont:'bold',background:'#00a653'});            
				        }
				        else  
				        {
				            $("#bookingList").jqGrid('setRowData',rows[i],false, {  color:'white',weightfont:'bold',background:'#00a653'});            
				        }
				    }
				} 
				 
		    }); */
   	$("#bookingList").jqGrid('navGrid', '#pbookingList', {
			edit : false,
			add : false,
			del : false,
			exl : true,
			search : true},
			{width : 'auto',url : '#'}, 
			{width : 'auto',url : '#'}, 
			{width : 'auto', serializeDelData: function (postdata) {
				  var selr = jQuery('#bookingList').jqGrid('getGridParam','selrow'); 
			      var rowData = $("#bookingList").jqGrid('getRowData');
			     
				  return true; 
				    },
		        	onclickSubmit: function (rp_ge, postdata) { 
		          }
			},
			 
		    {sopt : [ 'cn', 'bw', 'ew' ],
			closeOnEscape : true,
			multipleSearch : false,
			closeAfterSearch : true,
			closeAfterAdd : true,
			closeAfterEdit : true
			});
  
		});     
	</script> 	
 
</body>
</html> --%>