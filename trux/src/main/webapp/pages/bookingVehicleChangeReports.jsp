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
 <style type="text/css">
tr:nth-child(even) {
    background-color: #F9F9F9;
    color: black;
}
tr:nth-child(odd) {
    background-color: #FFFFFF;
    color: black;
}
</style>
<script type="text/javascript">
function assignVehicle(id)
{
	var x;
	if (confirm("Are you sure to change hevicle !") == true) {
var ids=id; 
var bookingId=document.getElementById("bookingId"+ids).value;
var assignVehicleType=document.getElementById("assignVehicleId"+ids).value;
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
    loadAfterVehicleUpdate();
    }
  }
xmlhttp.open("GET","/trux/booking/updateBookingVehicle?bookingId="+bookingId+"&vehicleType="+assignVehicleType,true);
xmlhttp.send();
	}else {
        x = "Not suere";
    }
}

function allVehicleDetail(id)
{
var ids=id;  
var vehicalTypeId=document.getElementById("vehicelTypeId"+ids).value;
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
    document.getElementById("assignVehicleId"+ids).innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","/trux/reports/allVehicleType",true);
xmlhttp.send();
}

function loadAfterVehicleUpdate(){
	window.location.href="/trux/reports/bookingVehicleChangeReports";
}

</script>
</head>
<body>

<fieldset class="fieldset2 borderManager table-responsive">
<div style="overflow: auto; height: 500px;">
	<div style="width: 100%; float: left; margin: auto; position: relative; z-index: 999999;">
		 
	</div>
	<div style="width: 100%; float: left; margin: auto;">
		<div class="hs_page_title">
			<div class="container"  style="background-color: #0B67CD; color: white;border: 0px;">
				<div class="row"
					style="margin-left: 0px; margin-right: 0; margin-top: 5px;">
					<h5 style="color: white;">Booking Vehicle change Report</h5>
				</div>
			</div>
		</div>
	</div> 
	<div class="container" style="background-color: #0B67CD; color: white;border: 0px; ">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12"  style="padding-left: 0px;">
				<div class="row-fluid" style="width: 1091px; height: auto;">
					 <table   style="font-size: 11px; height: auto;color: black;"  >
						<tbody >
						<thead style="height: 30px;">
							<tr style="background-color: #2D89EF;color: white;border: 0px; height: 30px; ">
								<th style="width: 5%; text-align: center;">
									<div style="width: auto;">SR N.</div>
								</th>
								<th><div style="width: auto;">Booking Id</div></th>
								<th><div style="width: auto;">Customer Name</div></th>
								<th><div style="width: auto;">Phone Number</div></th>
								<th><div style="width: auto;">From Location</div></th>
								<th><div style="width: auto;">To Location</div></th>
								<th><div style="width: auto;">Booking Status</div></th>								
								<th><div style="width: auto;">Loading Time</div></th>
								<th><div style="width: auto;">Vehicle Type</div></th> 
								<th><div style="width: auto;" title="Select the driver name and mobile to assign the booking vehicle">Available Vehicle Details</div></th>
								<th><div style="width: auto;">Assign to Vehicle</div></th>
								<th><div style="width: auto;" >Assign Status</div></th>
							</tr>
						</thead>
						<%!int i = 0;%>
						<%try{
						List<CustomerBookingDetails> customerBookingDetailsList = (List<CustomerBookingDetails>)request.getAttribute("customerBookingDetailsList");
							
							if (customerBookingDetailsList.size() > 0)
								for (CustomerBookingDetails consumer : customerBookingDetailsList)
								{
									i++;
						%>
						<tr style="width: 5%;">
							<td><%=i%></td>
							<td style="width: auto;"><input type="hidden" id="bookingId<%=i%>" value="<%=consumer.getBookingId()%>"/> <%=consumer.getBookingId()%></td>
							<td style="width: auto;"><%=consumer.getCustomerName()%></td>
							<td style="width: auto;"><%=consumer.getCustmerPhonenumber()%></td>
							<td style="width: auto;"><%=consumer.getFromLocation()%></td>
							<td style="width: auto;"><%=consumer.getToLocation()%></td>
							<td style="width: auto;"><%=consumer.getBookingStatus()%></td>
							<td style="width: auto;"><%=DateFormaterUtils.convertGMTToISTWithDate(new Date(consumer.getRideTime().getTime()+11*1800*1000).toString())%></td> 
							<td style="width: auto;"><input type="hidden" id="vehicelTypeId<%=i%>" value="<%=consumer.getVehicleType()%>"/><div onclick="return allVehicleDetail(<%=i%>);" title="Please click the vehicle content area after that populate the vehicle details !" style="color: green;"><%=consumer.getVehicleType()%></div></td> 
							<td><select id="assignVehicleId<%=i%>" style="width:100px;" title="Select the Vehicle to assign the other booked vehicle">
							</select></td>
							<td><input type="button" onclick="return assignVehicle(<%=i%>);" value="Assign Vehicle" title="Assign button"></td>
							<td><div id="assignId<%=i%>" style="overflow:auto;width: 100px;height:auto"></div></td> 
						</tr>
						<%
							}
							i = 0;
						}catch(Exception er ){%>
						
						<tr style="width: 100%;">
							<td colspan="13"><% out.print( "No any pending recourds !");} %></td></tr>
					 </tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="clearfix margin_10"></div></div>
</fieldset>
</body>
</html>
