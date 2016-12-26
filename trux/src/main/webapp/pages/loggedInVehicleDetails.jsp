<%@page import="com.trux.model.DriverDeviceVehicleMapping"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.trux.model.CustomerBookingDetails"%>
<%@page import="java.util.List"%>
<fieldset class="borderManager">
<table
	class="table table-striped table-bordered bootstrap-datatable datatable responsive">
	<tbody>
	<thead>
		<tr style="background-color: #cccccc;">
			<th><div class="center">Name</div></th>
			<th><div class="center">Vehicle Type</div></th>
			<th><div class="center">Vehicle No</div></th>
			<th><div class="center">Phone Number</div></th>
			<th><div class="center">Current Location</div></th>
			<th><div class="center">Status of the Vehicle</div></th>
			<th><div class="center">Delete Action</div></th>
		</tr>
	</thead>
	<%
		if (request.getAttribute("driverDeviceMapList") != null) {
			List<DriverDeviceVehicleMapping> driverDeviceMapList = (List<DriverDeviceVehicleMapping>) request
					.getAttribute("driverDeviceMapList");
			if (driverDeviceMapList != null
					&& !driverDeviceMapList.isEmpty()) {
				for (DriverDeviceVehicleMapping driverDeviceMap : driverDeviceMapList) {
	%>
	<tr>
		<td><%=driverDeviceMap.getDriverName()%></td>
		<td><%=driverDeviceMap.getVehicleType()%></td>
		<td><%=driverDeviceMap.getVehicleNumber()%></td>
		<td><%=driverDeviceMap.getDriverPhoneNumber()%></td>
		<td></td>

		<td>
			<%
				if (driverDeviceMap.getDriverStatus() == 0) {
			%> Available <%
				} else if (driverDeviceMap.getDriverStatus() == 1) {
			%>
			Busy <%
				}
			%>
		</td>
		<td><a href="#"
			onclick="return removeDriverById(<%=driverDeviceMap.getId()%>,'<%=driverDeviceMap.getDriverName()%>');">
			<img alt="" src="/trux/resources/images/delete.png"	style="width: 20px; height: 20px;" title="If want to removed then click. Driver is <%=driverDeviceMap.getDriverName()%> and its Id is  <%=driverDeviceMap.getId()%>."></a>
		<div id="message<%=driverDeviceMap.getId()%>"></div></td>

	</tr>
	<%
		}
			} else {
	%>
	<tr>
		<td>No records found</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<%
		}
		} else {
	%>
	<tr>
		<td>No records found</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<%
		}
	%>

	</tbody>
	</tbody>

</table>
</fieldset>