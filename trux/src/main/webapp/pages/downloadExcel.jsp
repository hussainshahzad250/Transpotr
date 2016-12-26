<%@page import="com.trux.model.States"%>
<%@page import="com.trux.model.VehicleType"%>
<%@page import="com.trux.model.CRFOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.trux.model.ControllerDAOTracker"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@page import="com.trux.model.ClientMandate"%>
<%@page import="com.trux.utils.DateFormaterUtils"%>
<%@page import="com.trux.model.DriverAttandance"%>
<%@page import="com.trux.model.ExcelFile"%>
<%@page import="com.trux.model.OrganizationMasterRegistration"%>
<%@page import="com.trux.model.OrderFilterReportsDto"%>

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
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css"
	rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js"
	type="text/javascript"></script>
<link href="/trux/resources/jtable/css/metro/blue/jtable.css"
	rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery.jtable.js"
	type="text/javascript"></script>
<script src="/trux/resources/jtable/js/checkbox.js"
	type="text/javascript"></script>
<!-- for checkbox -->
<!-- <link href="/trux/resources/jtable/css/checkbox.css" rel="stylesheet" type="text/css" /> -->


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

	/* for Vehicel */

	function fillVehicle() {
		
		if(document.getElementById('tataace').checked) {
		} else {}
		if(document.getElementById('pickup').checked) {
		} else {}
		if(document.getElementById('tata407').checked) {
		} else {}
		if(document.getElementById('tata709').checked) {
		} else {}
		if(document.getElementById('singleaxle12').checked) {
		} else {}
		if(document.getElementById('singleaxle17').checked) {
		} else {}
		if(document.getElementById('singleaxle19').checked) {
		} else {}
		if(document.getElementById('singleaxle22').checked) {
		} else {}
		
		
		var v1 = document.getElementById('tataace').checked;
		
		var v2 = document.getElementById('pickup').checked;
		var v3 = document.getElementById('tata407').checked;
		var v4 = document.getElementById('tata709').checked;
		var v5 = document.getElementById('singleaxle12').checked;
		var v6 = document.getElementById('singleaxle17').checked;
		var v7 = document.getElementById('singleaxle19').checked;
		var v8 = document.getElementById('singleaxle22').checked;
		
		$.ajax({
			type : "POST",
			url : "/trux/reg/getVehicleList",
			data : {
				vehicle : v1,
				vehicle1 : v2
			},
			success : function(data) {
				document.getElementById("tataace").innerHTML = data;
				document.getElementById("tataace").value.innerHTML = data;
			}
		});
		return true;
	}

	$(function() {
		$(".js__p_start").simplePopup();
	});

	function callClientSearch() {
		var clientID = document.getElementById("fromCityId").value;
		var clientSubID = document.getElementById("toCityId").value;
		var v1 = document.getElementById('tataace').checked;
		
		var v2 = document.getElementById('pickup').checked;
		
		var v3 = document.getElementById('tata407').checked;
		var v4 = document.getElementById('tata709').checked;
		var v5 = document.getElementById('singleaxle12').checked;
		var v6 = document.getElementById('singleaxle17').checked;
		var v7 = document.getElementById('singleaxle19').checked;
		var v8 = document.getElementById('singleaxle22').checked;
		var v9 = document.getElementById('multiaxle24').checked;
		var v10 = document.getElementById('singleaxle32').checked;
		var v11 = document.getElementById('multiaxle32').checked;
		var v12 = document.getElementById('multiaxle40').checked;
		var v13 = document.getElementById('mahindra').checked;
		var v14 = document.getElementById('maruti').checked;
		
		$
				.ajax({
					type : "POST",
					url : "/trux/transport/exportToExcel",
					data : {
						fromCity : clientID,
						toCity : clientSubID,
						vehicle : v1,
						vehicle1 : v2,
						vehicle2 : v3,
						vehicle3 : v4,
						vehicle4 : v5,
						vehicle5 : v6,
						vehicle6 : v7,
						vehicle7 : v8,
						vehicle8 : v9,
						vehicle9 : v10,
						vehicle10 : v11,
						vehicle11 : v12,
						vehicle12 : v13,
						vehicle13 : v14
						
					},
					success : function(data) {
						//alert(data);
						if (data) {
							$("#html").empty();
							$("#excel").empty();
							$("#error").empty();
							$('#noOfRecords').empty();

							if (data.data == null) {
								//alert("No record found !!!");
								$("#error")
										.append(
												"No Record Found for selected range!! Enter Vali Source And Destination");

								$("#excelshow").css('display', 'none');
							}

							var status = "";

							var len = data.data.length;
							var txt = "";
							var txt2 = "";
							var txt3 = "";

							// txt += "<div class=\"row ad-hoc-request\">";

							//txt += "<div style=\"margin-bottom:5px;\"><input type=\"button\" class=\"btn btn-danger btn-sm btn_nav1\" id=\"btnExport\" value=\" Export Table data into Excel \"></div>";

							txt += "<div class=\"panel-group\" id=\"accordion\" style=\"margin-bottom:5px;\">";
							txt += "<div class=\"panel panel-default\">";
							txt += "<div class=\"panel-heading ad-hoc-heading top_hd\" style=\"background:#0d586b\">";
							/* txt += "<div class=\"haul_tyle\"><strong>Haul Type</strong></div>"; */
							/* txt += "<div class=\"p_num_master_download_data col1\"><strong>Serial No</strong></div>"; */
							txt += "<div class=\"p_num_master_download_data\"><strong>From State</strong></div>";
							txt += "<div class=\"p_num_master_download_data\"><strong>From City</strong></div>";
							txt += "<div class=\"p_num_master_download_data\"><strong>To State</strong></div>";
							txt += "<div class=\"p_num_master_download_data\"><strong>To City</strong></div>";
							txt += "<div class=\"p_num_master_download_data col6\"><strong>Amount</strong></div>";
							txt += "<div class=\"p_num_master_download_data last col7\"><strong>Vehicle Type</strong></div>";
							// 						txt += "<h4 class=\"panel-title collapsed\">";
							// // 						txt += "<small>From <strong style=\"float:right; padding-right:15px;\">Order Date</strong></small>";
							// // 						txt += "<small>From </small>";
							// 						txt += "<span>To City</span>";
							// 						txt += "<span>To City</span>";
							// 						txt += "<span class=\"p_status\" style=\"font-weight: bold;\">Status</span>";
							// 						txt += "</h4>";
							txt += "</div>";
							txt += "</div>";
							txt += "</div>";
							txt += "</div>";

							txt2 += "<table id=\"testTable\">";
							txt2 += "<tbody>";
							txt2 += "<tr>";
							/* txt2 += "<td><strong>SN</strong></td>"; */
							txt2 += "<td><strong>Source State</strong></td>";
							txt2 += "<td><strong>Source City</strong></td>";
							txt2 += "<td><strong>Destination State</strong></td>";
							txt2 += "<td><strong>Destination City</strong></td>";
							txt2 += "<td><strong>KM</strong></td>";
							txt2 += "<td><strong>Amount</strong></td>";
							txt2 += "<td><strong>Vehicle Type</strong></td>";
// 								txt2 += "<td><strong>Tata Ace</strong></td>";
// 								txt2 += "<td><strong>Bolero Pickup</strong></td>";
// 								txt2 += "<td><strong>Tata 407</strong></td>";
// 								txt2 += "<td><strong>Tata 709</strong></td>";
// 								txt2 += "<td><strong>12 Feet - Single Axle (3.5T)</strong></td>";
// 								txt2 += "<td><strong>17 Feet - Single Axle (7T)</strong></td>";
// 								txt2 += "<td><strong>19 Feet - Single Axle (9T)</strong></td>";
// 								txt2 += "<td><strong>22 Feet - Single Axle (9T)</strong></td>";
// 								txt2 += "<td><strong>24 Feet - Multi-Axle (14T)</strong></td>";
// 								txt2 += "<td><strong>32 Feet - Single Axle (9T)</strong></td>";
// 								txt2 += "<td><strong>32 Feet - Multi-Axle (16T)</strong></td>";
// 								txt2 += "<td><strong>40 Feet - Multi-Axle (20T)</strong></td>";
// 								txt2 += "<td><strong>34 Feet High Cube (14 MT)</strong></td>";
// 								txt2 += "<td><strong>36 Feet High Cube (14 MT)</strong></td>";

							txt2 += "</tr>";

							if (len > 0) {

								txt3 = "<strong>Number of Records: </strong>"+len;
								//alert(len);

								txt += "<div class=\"panel-group\" id=\"accordion\">";
								for (var i = 0; i < len; i++) {
									txt += "<div class=\"panel panel-default\">";

									///////////////  Panel Title  ///////////////////////////

									txt += "<div class=\"panel-heading ad-hoc-heading\">";
									/* txt += "<div class=\"p_num_master_download_data col1\">#" + data.data[i].id + "</div>"; */
									txt += "<h4 class=\"panel-title collapsed download_data_wid\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse"
											+ (i + 1)
											+ "\" aria-expanded=\"false\">";
									txt += "<div class=\"p_num_master_download_data\">"
											+ data.data[i].source_state_id
											+ "</div>";
									txt += "<div class=\"p_num_master_download_data\">"
											+ data.data[i].source_city_id
											+ "</div>";
									txt += "<div class=\"p_num_master_download_data\">"
											+ data.data[i].destination_state_id
											+ "</div>";

									txt += "<div class=\"p_num_master_download_data\">"
											+ data.data[i].destination_city_id
											+ "</div>";
									txt += "<div class=\"p_num_master_download_data col6\">"
											+ data.data[i].freight_rate
											+ "</div>";
									txt += "<div class=\"p_num_master_download_data last col7\">"
											+ data.data[i].vehicle_type_id
											+ "</div>";
									txt += "<h4 class=\"panel-title collapsed\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse"
											+ (i + 1)
											+ "\" aria-expanded=\"false\">";

									var d = new Date(data.data[i].orderDate);

									txt += "</h4>";

									var d = new Date(data.data[i].orderDate);

									txt += "</h4>";

									txt += "</div>";

									///////////////  Panel Title  ///////////////////////////

									///////////////  Panel Body  ///////////////////////////
									txt += "<div id=\"collapse"
											+ (i + 1)
											+ "\" class=\"panel-collapse collapse\" aria-expanded=\"false\" style=\"height: 0px;\">";
									txt += "<div class=\"panel-body\">";

									txt += "<table border=\"0\" style=\"width:100%\" class=\"nex_tbl_bdr\">";
									txt += "<tbody>";

									txt += "<tr style=\"display:none\">";
									txt += "<td><strong class=\"f13\">ID</strong></td>";
// 									txt += "<td><input type=\"text\" name=\"dId\" id=\"dId"
// 											+ (i + 1)
// 											+ "\" class=\"form-control input-sm\" style=\"width:100%;\" value=\""
// 											+ data.data[i].id + "\"></td>";
									txt += "</tr>";

									txt += "<tr>";
									txt += "<td align=\"left\"> <strong>Source State</strong></td>";
									txt += "<td> "
											+ data.data[i].source_state_id
											+ "</td>";
									txt += "<td> <strong>Destination State</strong></td>";
									txt += "<td> "
											+ data.data[i].destination_state_id
											+ "</td>";
									txt += "</tr>";
									txt += "<tr>";
									txt += "<td> <strong>Source City</strong></td>";
									txt += "<td> "
											+ data.data[i].source_city_id
											+ "</td>";
									txt += "<td> <strong>Destination City</strong></td>";
									txt += "<td> "
											+ data.data[i].destination_city_id
											+ "</td>";
									txt += "</tr>";
									txt += "<tr>";
									txt += "<td> <strong>Vehicle Type</strong></td>";
									txt += "<td> "
											+ data.data[i].vehicle_type_id
											+ "</td>";
									txt += "<td> <strong>Amount</strong></td>";
									txt += "<td> " + data.data[i].freight_rate
											+ "</td>";
									txt += "</tr>";
									txt += "<tr>";
									txt += "<td> <strong>Distance(KM)</strong></td>";
									txt += "<td> " + data.data[i].distance
											+ "</td>";

									txt += "</tr>";

									txt += "</tbody>";
									txt += "</table>";

									txt += "</div>";
									txt += "</div>";

									///////////////  Panel Body  ///////////////////////////

									txt += "</div>";

									txt2 += "<tr>";

// 									txt2 += "<td>" + data.data[i].id + "</td>";

									txt2 += "<td>"
											+ data.data[i].source_state_id
											+ "</td>";
									txt2 += "<td>"
											+ data.data[i].source_city_id
											+ "</td>";
									txt2 += "<td>"
											+ data.data[i].destination_state_id
											+ "</td>";
									txt2 += "<td>"
											+ data.data[i].destination_city_id
											+ "</td>";
									txt2 += "<td>" + data.data[i].distance
											+ "</td>";
									txt2 += "<td>"+ data.data[i].vehicle_type_id + "</td>";
									txt2 += "<td>" + data.data[i].freight_rate + "</td>";
									

// 									for (var i = 0; i < len; i++) {
// 										if (data.data[i].vehicle_type_id != null){
// 											txt2 += "<td>" + data.data[i].vehicle_type_id + "</td>";
// 										}
// 									}

// 									for (var i = 0; i < len; i++) {
// 										if (data.data[i].freight_rate != null){
// 											txt2 += "<td>" + data.data[i].freight_rate + "</td>";
// 										}
// 									}
									txt2 += "</tr>";

								}
								txt += "</div>";

								txt2 += "</tbody>";
								txt2 += "</table>";

								if (txt2 != "") {
									$("#excel").append(txt2);

									$("#excelshow").show();
								}

								if (txt != "") {
									$("#html").append(txt);
									$("#noOfRecords").append(txt3);

								} else {
									$("#error").empty();
									$("#error")
											.append(
													"No record found for selected date range !!!");
								}
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
<script type="text/javascript">
	$("#btnExport").click(function(e) {
		window.open('data:application/vnd.ms-excel,' + $('#excel').html());
		e.preventDefault();
	});

	var tableToExcel = (function() {
		var uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>', base64 = function(
				s) {
			return window.btoa(unescape(encodeURIComponent(s)))
		}, format = function(s, c) {
			return s.replace(/{(\w+)}/g, function(m, p) {
				return c[p];
			})
		}
		return function(table, name) {
			if (!table.nodeType)
				table = document.getElementById(table)
			var ctx = {
				worksheet : name || 'Worksheet',
				table : table.innerHTML
			}
			window.location.href = uri + base64(format(template, ctx))
		}
	})()
</script>

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<fieldset class="fieldset2 col-lg-12 borderManager table-responsive">
					<legend class="borderManager" style="width: 100%"> Freight Master Download Data</legend><a href="/trux/transport/importExcel" class="btn btn-danger btn-sm right-btn btn_nav1 right_btn_update" style="margin-right: 20px;">Freight Master Upload Data</b></a>
					
					<!-- <form id="" action="" method="post" class="form-inline" onsubmit="return validateForm();"> -->
					<div class="row highlight" style="margin-top: -2%;">
					<div class="col-lg-12">
						<div class="col-lg-4 col-md-4 col-sm-6">
							<span style="float: left; margin: 15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">From</span>
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
							<div class="clearfix margin_05"></div>
							<div style="margin:15px 0 6px 0;">City</div>
							<select name="fromCityId" id="fromCityId" onchange="" class="input-sm" style="width: 100%;">
								<option value="">--Select city--</option>
							</select>
						</div>

						<div class="col-lg-4 col-md-4 col-sm-6">
						<span style="float: left; margin: 15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">To</span>
							<span style="float: left; margin: 15px 0 15px 0px; font-weight: bold; font-size: 16px; color: #525353;">&nbsp;</span>
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
							<div style="margin:15px 0 6px 0">City</div>
							<select name="toCityId" id="toCityId" onchange="" class="input-sm" style="width: 100%;">
								<option value="">--Select city--</option>
							</select>
						</div>
					</div>
					
					
					<div class="col-lg-12" style="margin-top:0px;"><div class="col-md-12" style="text-align:right"><input type="checkbox" id="checkAll"/> <b>Select All</b></div></div>
					
					<div class="col-lg-12 maste_download_data_list" style="margin-top:15px">
						<div class="col-md-3"><input type="checkbox" name="vehicle" value="tataace" id="tataace">Tata Ace</div>
						<div class="col-md-3"><input type="checkbox" name="vehicle" value="pickup" id="pickup">Pickup</div>
						<div class="col-md-3"><input type="checkbox" name="vehicle" value="tata407" id="tata407">Tata 407</div>
						<div class="col-md-3"><input type="checkbox" name="vehicle" value="tata709" id="tata709">Tata 709</div>
						<div class="col-md-3"><input type="checkbox" name="vehicle" value="singleaxle12" id="singleaxle12">12 feet Single Axle</div>
						<div class="col-md-3"><input type="checkbox" name="vehicle" value="singleaxle17" id="singleaxle17">17 feet Single Axle</div>
						<div class="col-md-3"><input type="checkbox" name="vehicle" value="singleaxle19" id="singleaxle19">19 feet Single Axle</div>
						<div class="col-md-3"><input type="checkbox" name="vehicle" value="singleaxle22" id="singleaxle22" />22 feet Single Axle</div>
						<div class="col-md-3"><input type="checkbox" name="vehicle" value="multiaxle24" id="multiaxle24" />24 Feet Multi-Axle</div>
						<div class="col-md-3"><input type="checkbox" name="vehicle" value="singleaxle32" id="singleaxle32" />32 feet Single Axle</div>
						<div class="col-md-3"><input type="checkbox" name="vehicle" value="multiaxle32" id="multiaxle32" />32 feet Multi Axle</div>
						<div class="col-md-3"><input type="checkbox" name="vehicle" value="multiaxle40" id="multiaxle40" />40 feet Multi Axle</div>
						<div class="col-md-3"><input type="checkbox" name="vehicle" value="mahindra" id="mahindra" />Mahindra Champion</div>
						<div class="col-md-3"><input type="checkbox" name="vehicle" value="maruti" id="maruti" />Maruti Eeco</div>
					</div>
					
					</div>
					<!-- end 1st row -->

					<div class="row">
<!-- 						<div class="col-lg-3 col-md-3 col-sm-6"> -->
<!-- 							<div style="margin-bottom: 6px;"> -->
<!-- 								Result<span style="color: red;">*</span> -->
<!-- 							</div> -->
<!-- 							<select name="status" id="status" class="input-sm" -->
<!-- 								style="width: 100%;" required> -->
<!-- 								<option value="All">All</option> -->
								
<!-- 							</select> -->
<!-- 						</div> -->
						<div class="col-lg-3 col-md-3 col-sm-6">
							<span
								style="float: left; margin: 0 0 2px 0px; font-weight: bold; font-size: 16px; color: #525353;">&nbsp;</span>
<!-- 							<div class="clearfix margin_05"></div> -->
							<input type="submit" class="btn btn-danger btn-lg btn_nav1"
								id="save" value="Submit" onclick="callClientSearch();">
						</div>

					</div>

					<!-- end 1st row -->

<!-- 					<div class="clearfix margin_10"></div> -->
					<!-- for Number of Records and Download Link -->
					<div
						style="display: none; margin-bottom: 5px; margin-top: 5px; padding-left: 6px;"
						id="excelshow">
						<!-- <input type="button" class="btn btn-danger btn-sm btn_nav1" id="btnExport" value=" Export Table data into Excel "> -->
						<input type="button" class="btn btn-danger btn-sm btn_nav1"
							onclick="tableToExcel('testTable', 'W3C Example Table')"
							value="Export to Excel">
						<div id="noOfRecords"
							style="float: right; padding-right: 25px; padding-top: 5px;"></div>
						<div style="display: none" id="excel"></div>
					</div>
					




					<script type="text/javascript">
						$("#btnExport")
								.click(
										function(e) {
											window
													.open('data:application/vnd.ms-excel,'
															+ $('#excel')
																	.html());
											e.preventDefault();
										});

						var tableToExcel = (function() {
							var uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>', base64 = function(
									s) {
								return window
										.btoa(unescape(encodeURIComponent(s)))
							}, format = function(s, c) {
								return s.replace(/{(\w+)}/g, function(m, p) {
									return c[p];
								})
							}
							return function(table, name) {
								if (!table.nodeType)
									table = document.getElementById(table)
								var ctx = {
									worksheet : name || 'Worksheet',
									table : table.innerHTML
								}
								window.location.href = uri
										+ base64(format(template, ctx))
							}
						})()
					</script>
					<!-- for Number of Records and Download Link -->
					<div class="clearfix margin_10"></div>
					<div class="row ad-hoc-request" id="html"></div>
					<!-- </form> -->
					<div id="message" style="color: red;"></div>
				</fieldset>
			</div>
		</div>
		<div class="row ad-hoc-request" id="html"></div>
		<div class="error-client-search" id="error"></div>
	</div>

<script type="text/javascript">
$("#checkAll").change(function () {
    $("input:checkbox").prop('checked', $(this).prop("checked"));
});
</script>


</body>
</html>