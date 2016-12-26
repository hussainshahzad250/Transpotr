<%@page import="com.trux.model.States"%>
<%@page import="com.trux.model.VehicleType"%>
<%@page import="com.trux.model.CRFOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.trux.model.ControllerDAOTracker"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css"
	rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js"
	type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js"
	type="text/javascript"></script>
<!-- <link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script> -->
<link href="/trux/resources/jtable/css/metro/blue/jtable.css"
	rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery.jtable.js"
	type="text/javascript"></script>
<!-- Added By shahzad Hussain for date picker -->

<link rel="stylesheet" type="text/css"
	href="/trux/resources/jtable/jquery.datetimepicker.css" />

<style type="text/css">
.custom-date-style {
	background-color: red !important;
}

.input {
	
}

.input-wide {
	width: 500px;
}
</style>
<!-- Added By shahzad Hussain for date picker end -->

<script type="text/javascript">
	function fillFromCity() {
		var stateId = document.getElementById("fromStateId").value;
		$.ajax({
			type : "POST",
			url : "/trux/reg/getCitiesByState",
			data : {
				state : stateId
			},
			success : function(data) {
				document.getElementById("fromCityId").innerHTML = data;
				document.getElementById("fromCityId").value.innerHTML = data;
			}
		});
		return true;
	}

	function fillToCity() {
		var stateId = document.getElementById("toStateId").value;
		$.ajax({
			type : "POST",
			url : "/trux/reg/getCitiesByState",
			data : {
				state : stateId
			},
			success : function(data) {
				document.getElementById("toCityId").innerHTML = data;
				document.getElementById("toCityId").value.innerHTML = data;
			}
		});
		return true;
	}
	$(function() {
		$(".js__p_start").simplePopup();
	});
	function getOrderId(id, vehicleName, vehicle_body, from_city_id,
			to_city_id, deploy_date_time, price) {
		//  alert(vehicleName);

		$('#id').val(id);
		$('#vehicleTypeId').val(vehicleName);
		$('#vehicle_body').val(vehicle_body);
		$('#from_city_id').val(from_city_id);
		$('#to_city_id').val(to_city_id);
		// 	$('#deployDateTime').val(deploy_date_time);	
		var d = new Date(deploy_date_time);
		$('#deployDateTime').val(
				d.getDate() + '/' + (d.getMonth() + 1) + '/' + d.getFullYear()
						+ ' ' + d.getHours() + ':' + d.getMinutes());
		$('#price').val(price);
	}
	function updateOrder() {
		var Id = document.getElementById("id").value;
		var datetime = document.getElementById("deployDateTime").value;
		var amount = document.getElementById("price").value;
		$.ajax({
			type : "POST",
			url : "/trux/transport/UpdateOrder",
			data : {
				id : Id,
				deployDateTime : datetime,
				price : amount
			},
			success : function(data) {
				window.open("/trux/transport/updateTransporterOrder", "_self");
			}
		});
		return true;
	}

	/* function orderCancel(){
	 var SelorderId = document.getElementById("orderId").value;
	 var SelmapOrderId = document.getElementById("mapOrderId").value;
	 var Selstatus = 'Cancelled';	
	 $.ajax({
	 type: "POST",
	 url: "/trux/transport/changeClientIsActiveUpdate",
	 data:{
	 trsptrClientOrdersId:SelorderId,
	 trsptrClientOrdersMappingId:SelmapOrderId,
	 status:Selstatus
	 } ,
	 success: function(data) {
	 window.open("/trux/transport/approveTransporterOrder","_self");
	 }
	 });
	 return true;
	
	 //trsptrClientOrdersMappingId:SelmapOrderId,
	 }
	 */

	function function1() {
		alert("You don't have permission to delete the Order Now ??");
	}
	function getAllOrder() {
		var SelfromCityId = document.getElementById("from_city_id").value;
		var SeltoCityId = document.getElementById("to_city_id").value;
		$
				.ajax({
					type : "POST",
					url : "/trux/transport/getAllOrder",
					data : {
						fromCityId : SelfromCityId,
						toCityId : SeltoCityId
					},
					success : function(data) {

						if (data) {

							$("#html").empty();
							$("#error").empty();
							if (data.data == null) {
								//alert("No record found !!!");
								$("#error")
										.append(
												"No record found for selected date range !!!");
							}
							var len = data.data.length;
							var txt = "";
							txt += "<div class='res_tbl'>";
							txt += "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
							txt += "<tr>";
							txt += "<th width='8%'>Order Id</th>";
							txt += "<th width='17%'>Vehicle Type</th>";
							txt += "<th width='11%'>Body Type</th>";
							txt += "<th width='11%'>From</th>";
							txt += "<th width='11%'>To</th>";
							txt += "<th width='18%' colspan='2'>Date & Time</span></th>";
							txt += "<th width='8%'>Amount</th>";
							txt += "<th width='8%' colspan='2'>Actions</th>";
							txt += "</tr>";

							if (len > 0) {
								for (var i = 0; i < len; i++) {
									/*  var temp = new Array() ;
									temp[0] = data.data[i].fromCityId;
									temp[1] = data.data[i].toCityId;  */
									txt += "<tr>";
									txt += "<td>" + data.data[i].id + "</td>";
									txt += "<td>" + data.data[i].vehicleName
											+ "</td>";
									txt += "<td>" + data.data[i].vehicle_body
											+ "</td>";
									txt += "<td>" + data.data[i].from_city_id
											+ "</td>";
									txt += "<td>" + data.data[i].to_city_id
											+ "</td>";
									// 	txt += "<td>"+data.data[i].deploy_date_time+"</td>";
									var d = new Date(
											data.data[i].deploy_date_time);
									txt += "<td colspan='2'>" + d.getDate()
											+ '/' + (d.getMonth() + 1) + '/'
											+ d.getFullYear() + " "
											+ d.getHours() + ":"
											+ d.getMinutes() + "</td>";
									txt += "<td>" + data.data[i].price
											+ "</td>";
									txt += "<td align='center' valign='middle'><a href='#' onclick=\"getOrderId('"
											+ data.data[i].id
											+ "','"
											+ data.data[i].vehicleName
											+ "','"
											+ data.data[i].vehicle_body
											+ "','"
											+ data.data[i].from_city_id
											+ "','"
											+ data.data[i].to_city_id
											+ "',"
											+ data.data[i].deploy_date_time
											+ ",'"
											+ data.data[i].price
											+ "');\" class='js__p_start'><span class='glyphicon glyphicon-edit' style='color:#1d9bbb;'></span></a></td>";
									txt += "<td align='center' valign='middle'><a onclick=\"function1();\" href='#'><span class='glyphicon glyphicon-trash' style='color:#1d9bbb;'></span></a></td>";
									txt += "</tr>";
								}
							}
							txt += "</table>";
							txt += "</div>";
							if (txt != "") {
								$("#html").append(txt);
								$(".js__p_start").simplePopup();
							} else {
								$("#error").empty();
								$("#error")
										.append(
												"No record found for selected date range !!!");
							}
						}
					}
				});
		return true;
	}
</script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<fieldset class="fieldset2 col-lg-12 borderManager table-responsive">
					<legend class="borderManager" style="width: 100%"> Update
						Order</legend>
					<!-- <form id="" action="" method="post" class="form-inline" onsubmit="return validateForm();"> -->
					<div class="row highlight" style="margin-top: -2%;">
						<div class="col-lg-3 col-md-3 col-sm-6">
							<span
								style="float: left; margin: 15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">From</span>
							<div class="clearfix margin_05"></div>
							<div style="margin-bottom: 6px;">State</div>
							<select name="fromStateId" id="fromStateId"
								onchange="fillFromCity();" class="input-sm" style="width: 100%;"
								required="">
								<option value="">--Select State--</option>
								<%
									List<States> stateList = (List<States>) session.getAttribute("statesList");
									if (stateList != null && !stateList.isEmpty()) {
										for (int i = 0; i < stateList.size(); i++) {
								%>
								<option value="<%=stateList.get(i).getStateId()%>"><%=stateList.get(i).getStateName()%></option>
								<%
									}
									}
								%>
							</select>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6">
							<span
								style="float: left; margin: 15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">&nbsp;</span>
							<div class="clearfix margin_05"></div>
							<div style="margin-bottom: 6px;">City</div>
							<select name="fromCityId" id="fromCityId" onchange=""
								class="input-sm" style="width: 100%;">
								<option value="">--Select city--</option>
							</select>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6">
							<span
								style="float: left; margin: 15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">To</span>
							<div class="clearfix margin_05"></div>
							<div style="margin-bottom: 6px;">State</div>
							<select name="toStateId" id="toStateId" onchange="fillToCity();"
								class="input-sm" style="width: 100%;" required="">
								<option value="">-- Select State--</option>
								<%
									List<States> stateList2 = (List<States>) session.getAttribute("statesList");
									if (stateList2 != null && !stateList2.isEmpty()) {
										for (int i = 0; i < stateList2.size(); i++) {
								%>
								<option value="<%=stateList2.get(i).getStateId()%>"><%=stateList2.get(i).getStateName()%></option>
								<%
									}
									}
								%>
							</select>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6">
							<span
								style="float: left; margin: 15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">&nbsp;</span>
							<div class="clearfix margin_05"></div>
							<div style="margin-bottom: 6px;">City</div>
							<select name="toCityId" id="toCityId" onchange=""
								class="input-sm" style="width: 100%;">
								<option value="">--Select city--</option>
							</select>
						</div>
					</div>
					<!-- end 1st row -->

					<div class="row">
						<div class="col-lg-3 col-md-3 col-sm-6">
							<div style="margin-bottom: 6px;">
								Status<span style="color: red;">*</span>
							</div>
							<select name="status" id="status" class="input-sm"
								style="width: 100%;" required>
								<option value="All">All</option>
								<option value="Pending">Pending</option>
								<option value="Confirmed">Confirmed</option>
								<option value="Cancelled">Cancelled</option>
							</select>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6">
							<span
								style="float: left; margin: 0 0 2px 0px; font-weight: bold; font-size: 16px; color: #525353;">&nbsp;</span>
							<div class="clearfix margin_05"></div>
							<input type="submit" class="btn btn-danger btn-sm btn_nav1"
								id="save" value="List All Orders" onclick="getAllOrder();">
						</div>
					</div>
					<!-- end 1st row -->

					<div class="clearfix margin_10"></div>
					<div class="clearfix margin_10"></div>
					<div class="row" id="html"></div>
					<!-- </form> -->
					<div id="message" style="color: red;"></div>
				</fieldset>
			</div>
		</div>
		<div class="row ad-hoc-request" id="html"></div>
		<div class="error-client-search" id="error"></div>
	</div>

	<!-- pop-up-section here -->

	<div class="popup js__popup js__slide_top">
		<a href="#" class="p_close js__p_close" title="Close"> <span></span><span></span>
		</a>
		<div class="p_content">
			<h2>Update Order</h2>

			<div class="p_content_row">
				<label>Order Id:</label><input type="text" name="id" id="id"
					class="form-control input-sm"
					style="width: 100%; background: #fff;" placeholder="Order ID"
					readonly>
			</div>

			<div class="p_content_row">
				<label>Vehicle Type:</label><input type="text" name="vehicleTypeId"
					id="vehicleTypeId" class="form-control input-sm"
					style="width: 100%; background: #fff;" placeholder="Vehicle Type"
					readonly>
			</div>

			<div class="p_content_row">
				<label>Body Type:</label> <input type="text" name="vehicleBody"
					id="vehicle_body" class="form-control input-sm"
					style="width: 100%; background: #fff;" placeholder="Body Type"
					readonly>
			</div>

			<div class="p_content_row">
				<label>From:</label> <input type="text" name="fromCityId"
					id="from_city_id" class="form-control input-sm"
					style="width: 100%; background: #fff;" placeholder="From" readonly>
			</div>

			<div class="p_content_row">
				<label>To:</label> <input type="text" name="toCityId"
					id="to_city_id" class="form-control input-sm"
					style="width: 100%; background: #fff;" placeholder="To" readonly>
			</div>

			<div class="p_content_row">
				<label>Date & Time:</label> <input type="text" name="deployDateTime"
					id="deployDateTime" class="form-control input-sm"
					style="width: 100%; background: #fff;"
					placeholder="YYYY/MM/DD HH:MM">
			</div>

			<div class="p_content_row">
				<label>Amount:</label> <input type="text" name="price" id="price"
					class="form-control input-sm"
					style="width: 100%; background: #fff;" placeholder="Amount">
			</div>

			<div class="p_content_row">
				<!-- <input type="button" class="btn btn-danger btn-sm btn_nav1" id="" value="Cancel Request" onclick="orderCancel();"> -->
				<input type="button" class="btn btn-danger btn-sm btn_nav1 f_right"
					id="" value="Update Order" onclick="updateOrder();">
			</div>


		</div>

	</div>
	<div class="p_body js__p_body js__fadeout"></div>
	<!-- pop-up-section here -->

	<!-- Added  For Date picker -->
	<script src="/trux/resources/jtable/js/jquery.js"></script>
	<script src="/trux/resources/jtable/js/jquery.datetimepicker.full.js"></script>
	<script>
		$.datetimepicker.setLocale('en');
		$('#deployDateTime').datetimepicker();
	</script>


	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script
		src="http://www.jqueryscript.net/demo/Minimalist-jQuery-Modal-Popup-Box-Plugin-Simple-Popup/demo/js/jquery.popup.js"></script>

	<!-- <script type="text/javascript">
    $(function() {
      $(".js__p_start, .js__p_another_start").simplePopup();
    });
  </script> -->

</body>
</html>