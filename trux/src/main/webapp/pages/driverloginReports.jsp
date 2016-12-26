
<%@page import="com.trux.model.OrderFilterReportsDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/trux/resources/jtable/css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js"	type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery.jtable.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script>

<title>Driver Log In/Log Out Reports</title>
</head>
<body>

	<div class="container" style="border-radius: 10px 10px 7px 7px;">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<fieldset class="fieldset2 col-lg-12 borderManager">
					<legend class="borderManager">Driver Log In/Log Out search
						Form</legend>
					<f:form commandName="loginLogout"
						action="searchDriverloginLogoutReports" method="post"
						cssClass="form-inline">
						<!-- onsubmit="return appliedFromToDate();" -->

						<div class="row" style="margin-top: -2%;">
							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">Vehicle type</div>
								<f:select path="vehicleType" id="vehicleType"
									cssClass="input-sm" cssStyle="width:100%;">
									<f:option value="">--Select Vehicle type</f:option>
									<f:option value="Tata Ace">Tata Ace</f:option>
									<f:option value="Tata 407">Tata 407</f:option>
									<f:option value="Tata 709">Tata 709</f:option>
									<f:option value="Bolero Pickup">Bolero Pickup</f:option>
									<f:option value="Mahindra Champion">Mahindra Champion</f:option>
									<f:option value="Maruti Eeco">Maruti Eeco</f:option>
								</f:select>
							</div>

							<div class="col-lg-3 col-md-3 col-sm-12">
								<div style="margin-bottom: 6px;">Status</div>
								<f:select path="status" id="status" cssClass="input-sm"
									cssStyle="width:100%;">
									<f:option value="">--Select Status--</f:option>
									<f:option value="1">All</f:option>
									<f:option value="2">Logged In And Available</f:option>
									<f:option value="3">Drivers In Transit </f:option>
									<f:option value="4">Not Logged In </f:option>
								</f:select>
							</div>


							<div class="col-lg-3 col-md-3 col-sm-12">
								<div
									style="margin-top: 6px; font-size: 14px; text-align: left; margin-left: 0px; margin-top: 25px;">
									<button class="btn btn-danger btn-sm btn_nav2">Filter</button>

								</div>
							</div>
						</div>
						<!-- <div class="clearfix margin_10"></div> -->

					</f:form>
					<f:form action="clearRevenueFilter"
						commandName="revenueOrderFilter" method="post">
						<div class="col-lg-3 col-md-3 col-sm-12">
							<div
								style="text-align: left; font-size: 14px; margin-top: -33px; margin-left: 617px;">
								<button class="btn btn-danger btn-sm btn_nav2">Clear</button>
							</div>
						</div>
					</f:form>
				</fieldset>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<fieldset class="fieldset2 borderManager table-responsive">
				
				<div
					style="width: 100%; margin-right: 0%; margin-left: 0%; text-align: left;">
					<div id="LogInLogoutTableContainer"></div>
				</div>
			 
			</fieldset>
		</div>
	</div>


	<script type="text/javascript">
		$(document).ready(
			function() {
              $('#LogInLogoutTableContainer').jtable(
											{
												title : "Driver Login/Logout Report",
												paging : true, //Enable paging
												pageSize : 10, //Set page size (default: 10) 
												toolbar: {
												    items: [{
												        Tooltip: 'Click here to export this table to excel',
												        icon: '/trux/resources/images/xls.png',
												        text: 'Export to Excel',
												        click: function () {
												         window.location = '/trux/reportsapi/LoggedInXLSReports?action=export-excel';
												         e.preventDefault();
												        }
												    }]
												},
												actions : {
													listAction : '/trux/reportsapi/loginLogoutDriverReports?action=list',
												},
												fields : {
													driverName : {
														title : 'Driver Name',
														sort : true,
														width : '15%',
														key : true,
														list : true,
														edit : false,
														create : true
													},
													driverPhoneNumber : {
														title : ' Mobile no',
														width : '7%',
														edit : true
													},
													vehicleType : {
														title : 'Vehicle type',
														width : '10%',
														edit : true
													},
													driverMessage : {
														title : 'Status',
														width : '10%',
														edit : true
													},
													driverLogoutDate : {
														title : 'Last login Date',
														width : '10%',
														edit : true
													},
													driverLogoutTime : {
														title : 'Last login Time ',
														width : '10%',
														edit : true
													},
													driverLoginDate: {
														title : 'logout Date',
														width : '10%',
														edit : true
													},
													driverLoginTime : {
														title : 'logout time',
														width : '10%',
														edit : true
													},
													driverLoginDurationTime : {
														title : 'Login Days/ Hours:Minuts:Seconds',
														width : '10%',
														edit : true
													}

												}
											});
							$('#LogInLogoutTableContainer').jtable('load');
						});
	</script> 
</body>
</html>
