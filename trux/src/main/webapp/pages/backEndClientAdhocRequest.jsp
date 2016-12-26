<%@page import="com.trux.model.ClientMandate"%>
<%@page import="java.util.Date"%>
<%@page import="com.trux.utils.DateFormaterUtils"%>
<%@page import="com.trux.model.DriverAttandance"%>
<%@page import="com.trux.model.OrganizationMasterRegistration"%>
<%@page import="java.util.List"%>
<%@page import="com.trux.model.OrderFilterReportsDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Client Ad-Hoc Request</title>

<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script>
<link href="/trux/resources/jtable/css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery.jtable.js" type="text/javascript"></script>
</head>
<body>

	<script type="text/javascript">
	
	function validateTwoDifferentYearDate(){
		 var objToDate=$("#searchStartDate").val();
		 var objFromDate =$("#searchEndDate").val();
		 if(dateCheck(objFromDate ,objToDate)){
			 $("#searchStartDate").css({ borderColor: "green" });
			 $("#searchEndDate").css({ borderColor: "green" });
		   } else {
			    alert("From date should be greater than to date !");
			    $("#searchStartDate").css({ borderColor: "red" });
			    $("#searchEndDate").css({ borderColor: "red" });
			   // $("#mandateStartDate").val('');
				$("#searchEndDate").val('');
		    }
			function dateCheck(from,to) {

			    var fDate,lDate,cDate;
			    fDate = Date.parse(from);
			    lDate = Date.parse(to); 
			    if((fDate < lDate)) {
			        return false;
			    }
			   return true;
			}
	 }
	
	
		function fillSubOrg() {
			var flag = false;
			var SelOrgValue = document.getElementById("clientId").value;
			var message = "";

			if (SelOrgValue == "") {
				document.getElementById("message").style.color = "red";
				document.getElementById('message').style.display = "block";
				document.getElementById("clientId").style.borderColor = "red";
				message += "Please select the client !<br/>";
				document.getElementById('clientId').focus();
				flag = false;
			} else {
				document.getElementById("clientId").style.borderColor = "green";
			}
			if (message == "") {
				flag = true;
			}
			if (flag == true) {
				$
						.ajax({
							type : "POST",
							url : "/trux/reg/getSubsidiaryOrgByMasterId",
							data : {
								idClientMaster : SelOrgValue
							},
							success : function(data) {
								document.getElementById("clientSubId").innerHTML = data;
								document.getElementById("clientSubId").value.innerHTML = data;
							}
						});
				return true;
			}
			return false;
		}
		
		function clientAdhocRequestDriverPayment(index) {
			var flag = false;
			var idName = new Array("","dId","ctd","ad","prn","rtc","remarks","boxes","bp","brn","tp","trn","lp","lrn"); 
			var SelId =$("#"+idName[1]+index).val();
			var Selctd =$("#"+idName[2]+index).val();
			var Selad =$("#"+idName[3]+index).val();
			var Selprn =$("#"+idName[4]+index).val();
			var Selrtc =$("#"+idName[5]+index).val();
			var Selremarks =$("#"+idName[6]+index).val();
			var Selboxes =$("#"+idName[7]+index).val();
			var Selbp =$("#"+idName[8]+index).val();
			var Selbrn =$("#"+idName[9]+index).val();
			var Seltp =$("#"+idName[10]+index).val();
			var Seltrn =$("#"+idName[11]+index).val();
			var Sellp =$("#"+idName[12]+index).val();
			var Sellrn =$("#"+idName[13]+index).val();
				$
						.ajax({
							type : "POST",
							url : "/trux/app/clientAdhocDriverPayment",
							data : {
								id : SelId,
								costToDriver : Selctd,
								advancePayment : Selad,
								revenueToCompany : Selrtc,
								paymentReferenceNumber : Selprn,
								remarks : Selremarks,
								boxes : Selboxes,
								balancePayment : Selbp,
								balanceReferenceNumber : Selbrn,
								tollPayment : Seltp,
								tollReferenceNumber : Seltrn,
								labourPayment : Sellp,
								labourReferenceNumber :Sellrn
						
							},
							success : function(data) {
								alert(data.errorMesaage);
							}
						});
				return true;
		}
		
		
		function CreateExcelSheet()
		{
			var x=myTable.rows;
		    var Excel = new ActiveXObject("Excel.Application");
		    Excel.visible = true;
		    var Book = Excel.Workbooks.Add();
		    for (i = 0; i < x.length; i++)
		    {
		        var y = x[i].cells;
		        for (j = 0; j < y.length; j++)
		        {
		            Book.ActiveSheet.Cells( i+1, j+1).Value = y[j].innerText;
		        }
		    }
		}

		function callClientSearch() {
			var clientID = document.getElementById("clientId").value;
			var clientSubID = document.getElementById("clientSubId").value;
			var SelstartDate = document.getElementById("searchStartDate").value;
			var SelendDate = document.getElementById("searchEndDate").value;
			var SelorderId = document.getElementById("orderId").value;
			var Selhaul = document.getElementById("haul").value;
			
			$
					.ajax({
						type : "POST",
						url : "/trux/app/clientAdhocRequestSearch",
						data : {
							clientSubId : clientSubID,
							startDate : SelstartDate,
							endDate : SelendDate,
							orderId : SelorderId,
							haul : Selhaul
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
													"No record found for selected date range !!!");
									
									$("#excelshow").css('display' , 'none');
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
								txt += "<div class=\"p_num\"><strong>Order Id</strong></div>";
								/* txt += "<div class=\"p_num\"><strong>Client Name</strong></div>"; */
								txt += "<h4 class=\"panel-title collapsed\">";
								txt += "<small>Destination <strong style=\"float:right; padding-right:15px;\">Order Date</strong></small>";
								txt += "<span>Vehicle Type</span>";
								txt += "<span class=\"p_status\" style=\"font-weight: bold;\">Status</span>";
								txt += "</h4>";
								txt += "</div>";
								txt += "</div>";
								txt += "</div>";
								txt += "</div>";
								
								
								
								
								txt2 += "<table id=\"testTable\">";
								txt2 += "<tbody>";
									txt2 += "<tr>";
										txt2 += "<td><strong>Order Id</strong></td>";
										txt2 += "<td><strong>Order Date</strong></td>";
										txt2 += "<td><strong>Client Name</strong></td>";
										txt2 += "<td><strong>From</strong></td>";
										txt2 += "<td><strong>To</strong></td>";
										txt2 += "<td><strong>Vehicle Number</strong></td>";
										txt2 += "<td><strong>Vehicle Type</strong></td>";
										txt2 += "<td><strong>Body Type</strong></td>";
										txt2 += "<td><strong>Status</strong></td>";
										txt2 += "<td><strong>No. Of Boxes</strong></td>";
										txt2 += "<td><strong>Driver Contact Number</strong></td>";
										txt2 += "<td><strong>Trux Inspector Name</strong></td>";
										txt2 += "<td><strong>Trux Inspector Number</strong></td>";
										txt2 += "<td><strong>Revenue To Company</strong></td>";
										txt2 += "<td><strong>Cost To Driver</strong></td>";
										txt2 += "<td><strong>Advance</strong></td>";
										txt2 += "<td><strong>Advance Reference Number</strong></td>";
										txt2 += "<td><strong>Balance Payable To Driver</strong></td>";
										txt2 += "<td><strong>Balance Reference No.</strong></td>";
										txt2 += "<td><strong>Toll Payment</strong></td>";
										txt2 += "<td><strong>Toll Reference No.</strong></td>";
										txt2 += "<td><strong>Labour Payment</strong></td>";
										txt2 += "<td><strong>Labour Reference No.</strong></td>";										
										txt2 += "<td><strong>Account Holder Name</strong></td>";
										txt2 += "<td><strong>Bank Name</strong></td>";
										txt2 += "<td><strong>Account Number</strong></td>";
										txt2 += "<td><strong>IFSC Code</strong></td>";
										txt2 += "<td><strong>PAN Number</strong></td>";
										txt2 += "<td><strong>Remarks</strong></td>";
									txt2 += "</tr>";
								
								
								

								if (len > 0) {
									
									txt3 = "<strong>Number of Records: </strong>"+len;
									//alert(len);

									txt += "<div class=\"panel-group\" id=\"accordion\">";
									for (var i = 0; i < len; i++) {
										txt += "<div class=\"panel panel-default\">";

										///////////////  Panel Title  ///////////////////////////			                	    
										txt += "<div class=\"panel-heading ad-hoc-heading\">";
										txt += "<div class=\"p_num\">#"
												+ data.data[i].id + "</div>";
										txt += "<h4 class=\"panel-title collapsed\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse"
												+ (i + 1)
												+ "\" aria-expanded=\"false\">";
										// txt += "<a>"+data.data[i].vehicleType+" <strong>("+data.data[i].bodyType+")</strong> "+" for "+data.data[i].cTo+"</a>";
										// txt += "<a class=\"p_status\">Status</a>";
										// txt += "</h4>";

										var d = new Date(data.data[i].orderDate);
										//alert(d.getDate() + '/' + (d.getMonth()+1) + '/' + d.getFullYear());

										txt += "<small>"
												+ data.data[i].cTo
												+ "<strong style=\"float:right; padding-right:15px;\">"
												+ d.getDate() + '/'
												+ (d.getMonth() + 1) + '/'
												+ d.getFullYear() + " " +d.getHours() +":"+ d.getMinutes()
												+ "</strong></small>";
										txt += "<span>"
												+ data.data[i].vehicleType
												+ " (" + data.data[i].bodyType
												+ ")</span>";

										//alert(data.data[i].vehicleNumber);
										if (data.data[i].vehicleNumber == "-") {
											status = "Awaited";
										}
										if (data.data[i].vehicleNumber != "-") {
											status = "On the way";
										}
										if (data.data[i].is_arrived != 0) {
											status = "Arrived";
										}										
										if (data.data[i].is_dispatched != 0) {
											status = "Dispatched";
										}
										if (data.data[i].is_delivered != 0) {
											status = "Delivered";
										}

										txt += "<span class=\"p_status\">"
												+ status + "</span>";
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
										txt += "<td><input type=\"text\" name=\"dId\" id=\"dId"
												+ (i + 1)
												+ "\" class=\"form-control input-sm\" style=\"width:100%;\" value=\""
												+ data.data[i].id + "\"></td>";
										txt += "</tr>";
										txt += "<tr>";
										txt += "<td><strong class=\"f13\">Client Name</strong></td>";
										txt += "<td colspan=\"3\">"
												+ data.data[i].clientName
												+ "</td>";
										txt += "</tr>";
										txt += "<tr>";
										txt += "<td colspan=\"2\"><strong class=\"f13\">"
												+ data.data[i].cFrom
												+ "</strong> to <strong class=\"f13\">"
												+ data.data[i].cTo
												+ "</strong></td>";
										txt += "<td><strong>Request Date and Time</strong></td>";
										txt += "<td>" + d.getDate() + '/'
												+ (d.getMonth() + 1) + '/'
												+ d.getFullYear()  + " " +d.getHours() +":"+ d.getMinutes() + "</td>";
										txt += "</tr>";
										txt += "<tr>";
										txt += "<td colspan=\"2\">"
												+ data.data[i].vehicleType
												+ " <strong>("
												+ data.data[i].bodyType
												+ ")</strong></td>";
										txt += "<td><strong>Number of Boxes</strong></td>";
										/* txt += "<td>" + data.data[i].boxes
												+ "</td>"; */
												
										if (data.data[i].boxes != null) {
											txt += "<td><input type=\"text\" name=\"boxes\" id=\"boxes"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\" value=\""
													+ data.data[i].boxes
													+ "\"></td>";
										} else {
											txt += "<td><input type=\"text\" name=\"boxes\" id=\"boxes"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\"></td>";
										}
												
										txt += "</tr>";
										txt += "<tr>";
										txt += "<td align=\"left\"> <strong>Vehicle No.</strong></td>";
										txt += "<td> "
												+ data.data[i].vehicleNumber
												+ "</td>";
										txt += "<td> <strong>Account Holder Name</strong></td>";
										txt += "<td> "
												+ data.data[i].accountHolderName
												+ "</td>";
										txt += "</tr>";
										txt += "<tr>";
										txt += "<td> <strong>Driver Contact No.</strong></td>";
										txt += "<td> "
												+ data.data[i].driverMobile
												+ "</td>";
										txt += "<td> <strong>Bank Name</strong></td>";
										txt += "<td> " + data.data[i].bankName
												+ "</td>";
												//alert(data.data[i].bankName);
										txt += "</tr>";
										txt += "<tr>";
										txt += "<td> <strong>Trux Inspector Name</strong></td>";
										txt += "<td> " + data.data[i].tiName
												+ "</td>";
										txt += "<td> <strong>Account Number</strong></td>";
										txt += "<td> "
												+ data.data[i].accountNumber
												+ "</td>";
										txt += "</tr>";
										txt += "<tr>";
										txt += "<td> <strong>Trux Inspector Contact No.</strong></td>";
										txt += "<td> " + data.data[i].tiNumber
												+ "</td>";
										txt += "<td> <strong>IFSC Code</strong></td>";
										txt += "<td> " + data.data[i].ifscCode
												+ "</td>";
										txt += "</tr>";
										txt += "<tr>";
										txt += "<td> <strong>Balance Payable to Driver</strong></td>";
										txt += "<td> "
												+ (data.data[i].costToDriver - data.data[i].advancePayment)
												+ "</td>";
										txt += "<td> <strong>PAN Number</strong></td>";
										txt += "<td> " + data.data[i].panNumber
												+ "</td>";
										txt += "</tr>";
										txt += "<tr>";
										txt += "<td> <strong>Cost to Driver</strong></td>";
										if (data.data[i].costToDriver != null) {
											txt += "<td><input type=\"text\" name=\"ctd\" id=\"ctd"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\" value=\""
													+ data.data[i].costToDriver
													+ "\"></td>";
										} else {
											txt += "<td><input type=\"text\" name=\"ctd\" id=\"ctd"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\"></td>";
										}

										txt += "<td> <strong>Revenue to Company</strong></td>";
										if (data.data[i].revenueToCompany != null) {
											txt += "<td><input type=\"text\" name=\"rtc\" id=\"rtc"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\" value=\""
													+ data.data[i].revenueToCompany
													+ "\"></td>";
										} else {
											txt += "<td><input type=\"text\" name=\"rtc\" id=\"rtc"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\"></td>";
										}

										txt += "</tr>";
										txt += "<tr>";
										txt += "<td> <strong>Advance</strong></td>";
										if (data.data[i].advancePayment != null) {
											txt += "<td><input type=\"text\" name=\"ad\" id=\"ad"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\" value=\""
													+ data.data[i].advancePayment
													+ "\"></td>";
										} else {
											txt += "<td><input type=\"text\" name=\"ad\" id=\"ad"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\"></td>";
										}

										txt += "<td> <strong>Advance Reference No.</strong></td>";
										if (data.data[i].paymentReferenceNumber != null) {
											txt += "<td><input type=\"text\" name=\"prn\" id=\"prn"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\" value=\""
													+ data.data[i].paymentReferenceNumber
													+ "\"></td>";
										} else {
											txt += "<td><input type=\"text\" name=\"prn\" id=\"prn"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\"></td>";
										}

										txt += "</tr>";
										txt += "<tr>";
										txt += "<td> <strong>Balance</strong></td>";
											txt += "<td><input type=\"text\" name=\"bp\" id=\"bp"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\" value=\""
													+ (data.data[i].costToDriver - data.data[i].advancePayment)
													+ "\"></td>";

										txt += "<td> <strong>Balance Reference No.</strong></td>";
										if (data.data[i].balanceReferenceNumber != null) {
											txt += "<td><input type=\"text\" name=\"brn\" id=\"brn"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\" value=\""
													+ data.data[i].balanceReferenceNumber
													+ "\"></td>";
										} else {
											txt += "<td><input type=\"text\" name=\"brn\" id=\"brn"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\"></td>";
										}

										txt += "</tr>";
										txt += "<tr>";
										txt += "<td> <strong>Toll Payment</strong></td>";
										if (data.data[i].tollPayment != null) {
											txt += "<td><input type=\"text\" name=\"tp\" id=\"tp"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\" value=\""
													+ data.data[i].tollPayment
													+ "\"></td>";
										} else {
											txt += "<td><input type=\"text\" name=\"tp\" id=\"tp"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\"></td>";
										}

										txt += "<td> <strong>Toll Reference No.</strong></td>";
										if (data.data[i].tollReferenceNumber != null) {
											txt += "<td><input type=\"text\" name=\"trn\" id=\"trn"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\" value=\""
													+ data.data[i].tollReferenceNumber
													+ "\"></td>";
										} else {
											txt += "<td><input type=\"text\" name=\"trn\" id=\"trn"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\"></td>";
										}

										txt += "</tr>";
										txt += "<tr>";
										txt += "<td> <strong>Labour Payment</strong></td>";
										if (data.data[i].labourPayment != null) {
											txt += "<td><input type=\"text\" name=\"lp\" id=\"lp"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\" value=\""
													+ data.data[i].labourPayment
													+ "\"></td>";
										} else {
											txt += "<td><input type=\"text\" name=\"lp\" id=\"lp"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\"></td>";
										}

										txt += "<td> <strong>Labour Reference No.</strong></td>";
										if (data.data[i].labourReferenceNumber != null) {
											txt += "<td><input type=\"text\" name=\"lrn\" id=\"lrn"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\" value=\""
													+ data.data[i].labourReferenceNumber
													+ "\"></td>";
										} else {
											txt += "<td><input type=\"text\" name=\"lrn\" id=\"lrn"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\"></td>";
										}

										txt += "</tr>";
										txt += "<tr>";
										txt += "<td><strong>Remarks</strong></td>";
										/* document.getElementById("remarks"+(i+1)).innerHTML = data.data[i].remarks;
										alert(data.data[i].remarks);
										document.getElementById("remarks"+(i+1)).value.innerHTML = data; */
										//alert(data.data[i].remarks);
										/* $("#remarks" + (i + 1)).val(
												data.data[i].remarks); */

										
										if (data.data[i].remarks != null) {
											txt += "<td colspan=\"3\"><textarea name=\"remarks\" id=\"remarks"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\">"
													+ data.data[i].remarks
													+ "</textarea></td>";
										} else {
											txt += "<td colspan=\"3\"><textarea name=\"remarks\" id=\"remarks"
													+ (i + 1)
													+ "\" class=\"form-control input-sm\" style=\"width:100%;\"></textarea></td>";
										}
										txt += "<td><a href=\"#\" style=\"color:#666;\" onClick=\"clientAdhocRequestDriverPayment("
												+ (i + 1)
												+ ")\"><i class=\"glyphicon glyphicon-floppy-disk\" title=\"Save\"></i></a></td>";
										//txt += "<td colspan=\"5\"><a href=\"#\" style=\"color:#666;\" onclick=\"clientAdhocRequestDriverPayment("+(i+1)+")\"><i class=\"glyphicon glyphicon-floppy-save\" title=\"Save\"></i></a></td>";
										txt += "</tr>";

										txt += "</tbody>";
										txt += "</table>";

										txt += "</div>";
										txt += "</div>";

										///////////////  Panel Body  ///////////////////////////

										txt += "</div>";

										txt2 += "<tr>";
										txt2 += "<td>" + data.data[i].id
												+ "</td>";
										txt2 += "<td>" + d.getDate() + '/'
												+ (d.getMonth() + 1) + '/'
												+ d.getFullYear() + "</td>";
										txt2 += "<td>"
												+ data.data[i].clientName
												+ "</td>";
										txt2 += "<td>" + data.data[i].cFrom
												+ "</td>";
										txt2 += "<td>" + data.data[i].cTo
												+ "</td>";
										txt2 += "<td>"
												+ data.data[i].vehicleNumber
												+ "</td>";
										txt2 += "<td>"
												+ data.data[i].vehicleType
												+ "</td>";
										txt2 += "<td>" + data.data[i].bodyType
												+ "</td>";
										txt2 += "<td>" + status + "</td>";
										txt2 += "<td>" + data.data[i].boxes
												+ "</td>";
										txt2 += "<td>"
												+ data.data[i].driverMobile
												+ "</td>";
										txt2 += "<td>" + data.data[i].tiName
												+ "</td>";
										txt2 += "<td>" + data.data[i].tiNumber
												+ "</td>";
										txt2 += "<td>"
												+ data.data[i].revenueToCompany
												+ "</td>";
										txt2 += "<td>"
												+ data.data[i].costToDriver
												+ "</td>";
										txt2 += "<td>"
												+ data.data[i].advancePayment
												+ "</td>";
										txt2 += "<td>"
											+ data.data[i].paymentReferenceNumber
											+ "</td>";
										txt2 += "<td>"
												+ (data.data[i].costToDriver - data.data[i].advancePayment)
												+ "</td>";
										txt2 += "<td>"+data.data[i].balanceReferenceNumber+"</td>";
										txt2 += "<td>"+data.data[i].tollPayment+"</td>";
										txt2 += "<td>"+data.data[i].tollReferenceNumber+"</td>";
										txt2 += "<td>"+data.data[i].labourPayment+"</td>";
										txt2 += "<td>"+data.data[i].labourReferenceNumber+"</td>";
										txt2 += "<td>"
												+ data.data[i].accountHolderName
												+ "</td>";
										txt2 += "<td>" + data.data[i].bankName
												+ "</td>";
										txt2 += "<td>"
												+ data.data[i].accountNumber
												+ "</td>";
										txt2 += "<td>" + data.data[i].ifscCode
												+ "</td>";
										txt2 += "<td>" + data.data[i].panNumber
												+ "</td>";
										txt2 += "<td>" + data.data[i].remarks
												+ "</td>";
										txt2 += "</tr>";

									}
									txt += "</div>";

									txt2 += "</tbody>";
									txt2 += "</table>";

									//if(txt != ""){
									//  $(txt).appendTo("#requestBody tbody");
									//}
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
										//alert("No record found !!!");
									}
								} else {
									//alert(len);
									//alert("No record found !!!");
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

		function validateForm() {
			var flag = false;
			var clientId = document.getElementById("clientId").value;
			var clientSubId = document.getElementById("clientSubId").value;

			var searchStartDate = document.getElementById("searchStartDate").value;
			var searchEndDate = document.getElementById("searchEndDate").value;

			var message = "";

			if (clientId == "") {
				document.getElementById('message').style.display = "block";
				document.getElementById('message').style.color = "red";
				message += "<br/>";
				message += "Please select the Client name  !<br/>";
				document.getElementById("clientId").style.borderColor = "red";
				document.getElementById('clientId').focus();
				flag = false;
			} else {
				document.getElementById("clientId").style.borderColor = "green";
			}
			if (clientSubId == "") {
				document.getElementById('message').style.display = "block";
				document.getElementById('message').style.color = "red";
				message += "<br/>";
				message += "Please select the Sub Client name  !<br/>";
				document.getElementById("clientSubId").style.borderColor = "red";
				document.getElementById('clientSubId').focus();
				flag = false;
			} else {
				document.getElementById("clientSubId").style.borderColor = "green";
			}
			/* if (searchStartDate == "") {
				document.getElementById('message').style.display = "block";
				document.getElementById('message').style.color = "red";
				message += "Please select the Start Date !<br/>";
				document.getElementById("searchStartDate").style.borderColor = "red";
				document.getElementById('searchStartDate').focus();
				flag = false;
			} else {
				document.getElementById("searchStartDate").style.borderColor = "green";
			}
			if (searchEndDate == "") {
				document.getElementById('message').style.display = "block";
				document.getElementById('message').style.color = "red";
				message += "Please select the End Date !<br/>";
				document.getElementById("searchEndDate").style.borderColor = "red";
				document.getElementById('searchEndDate').focus();
				flag = false;
			} else {
				document.getElementById("searchEndDate").style.borderColor = "green";
			} */
			validateTwoDifferentYearDate();

			if (message == "") {
				flag = true;
				callClientSearch();
			}

			return flag;
		}
	</script>

	<div class="container">
  <div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12">
      <fieldset class="fieldset2 col-lg-12 borderManager table-responsive">
        <legend class="borderManager" style="width: 100%"> Client Ad-Hoc Request </legend>
        <!-- <form id="" action="" method="post" class="form-inline" onsubmit="return validateForm();"> -->
          <div class="row" style="margin-top: -2%;">
            
            <div class="col-lg-3 col-md-3 col-sm-12">
              <div style="margin-bottom: 6px;"> Start Date<span style="color: red;">*</span> </div>
              <input type="text" name="searchStartDate" id="searchStartDate" onchange="validateTwoDifferentYearDate();" class="form-control input-sm" style='width: 100%; box-shadow: 1px 1px #AEAEAE; border: 1px solid #AEAEAE; background: url(../resources/images/calender2.png); background-repeat: no-repeat; background-position: right;' placeholder="Select Start Date (Alpha-numeric)" >
            </div>
            <div class="col-lg-3 col-md-3 col-sm-12">
              <div style="margin-bottom: 6px;"> End Date<span style="color: red;">*</span> </div>
              <input type="text" name="searchEndDate" id="searchEndDate" onchange="validateTwoDifferentYearDate();" class="form-control input-sm" style='width: 100%; box-shadow: 1px 1px #AEAEAE; border: 1px solid #AEAEAE; background: url(../resources/images/calender2.png); background-repeat: no-repeat; background-position: right;' placeholder="Select Start Date (Alpha-numeric)" >
            </div>
            
            <div class="col-lg-3 col-md-3 col-sm-12">
              <div style="margin-bottom: 6px;"> Clients<span style="color: red;">*</span> </div>
              
              <select name="clientId" id="clientId" onchange="fillSubOrg()" class="input-sm" style="width:100%;">
                                 <option value="">--Select Client (Alphabetical)--</option>
                                 <% List<OrganizationMasterRegistration>  orgList= (List<OrganizationMasterRegistration>) session.getAttribute("orgList"); 
                                 if(orgList != null && !orgList.isEmpty()){
									for(int i=0; i<orgList.size();i++){ %>	
								 <option value="<%=orgList.get(i).getIdClientMaster() %>"><%=orgList.get(i).getName() %></option>
                                 <%	}}%>                        
                             </select>
              
              
            </div>
            <div class="col-lg-3 col-md-3 col-sm-12">
              <div style="margin-bottom: 6px;"> Sub-Clients <span style="color: red;">*</span> </div>
              <select name="clientSubId" id="clientSubId" class="input-sm"
style="width: 100%;" required>
                <option value="">--Select Sub-Client--</option>
              </select>
            </div>
            <div class="clearfix margin_05"></div>
          </div>

		  <div class="row" style="margin-top: -2%; margin-top: 15px;">
				
				<div class="col-lg-3 col-md-3 col-sm-12">
					<div style="margin-bottom: 6px;">Order ID<span style="color: red;"> (Comma separated)</span></div>
					<input type="text" name="orderId" id="orderId"
						class="form-control input-sm"
						style="width: 100%; box-shadow: rgb(174, 174, 174) 1px 1px; border: 1px solid green;"
						placeholder="eg. 1043,2156" autocomplete="off"> <i
						class="glyphicon glyphicon-list-alt"
						style="top: -25px; right: -231px; font-size: 20px;"></i>
				</div>
				
				
				<div class="col-lg-3 col-md-3 col-sm-12">
					<div style="margin-bottom: 6px;">Haul Type</div>
					<select name="haul" id="haul" class="input-sm" style="width:100%;">
                                 <option value="">--Select Haul Type--</option>
                                 <option value="0">0-50 Local Haul</option>
                                 <option value="50">50-350 Short Haul</option>
                                 <option value="350">350-800 Medium Haul</option>
                                 <option value="800">>800 Long Haul</option>
                    </select>
				</div>
				
				
		  </div>
			
			<div class="clearfix margin_10"></div>
          <!-- <div class="clearfix margin_10"></div> -->
          <input type="submit" class="btn btn-danger btn-sm btn_nav1" id="save" value="Submit" onclick="callClientSearch();">
        <!-- </form>-->
        <div id="message" style="color: red;"></div>
      </fieldset>
    </div>
  </div>
  
  
  <!-- <div class="row ad-hoc-request" id="html">
  
  </div> -->
  
  <div class="error-client-search" id="error">
  
  </div>
  
  
  <!-- <div id="dvData">
    <table>
        <tbody><tr>
            <th>Column One</th>
            <th>Column Two</th>
            <th>Column Three</th>
        </tr>
        <tr>
            <td>row1 Col1</td>
            <td>row1 Col2</td>
            <td>row1 Col3</td>
        </tr>
        <tr>
            <td>row2 Col1</td>
            <td>row2 Col2</td>
            <td>row2 Col3</td>
        </tr>
        <tr>
            <td>row3 Col1</td>
            <td>row3 Col2</td>
            <td><a href="http://www.jquery2dotnet.com/">http://www.jquery2dotnet.com/</a>
            </td>
        </tr>
    </tbody></table>
</div> -->

<div style="display:none; margin-bottom: 5px;margin-top: 5px;padding-left: 6px;" id="excelshow">
<!-- <input type="button" class="btn btn-danger btn-sm btn_nav1" id="btnExport" value=" Export Table data into Excel "> -->
<input type="button" class="btn btn-danger btn-sm btn_nav1" onclick="tableToExcel('testTable', 'W3C Example Table')" value="Export to Excel">

<div id="noOfRecords" style="float:right; padding-right: 25px;padding-top: 5px;"></div>
</div>

		<div style="display:none" id="excel">

			<!-- <table>
				<tbody>
					<tr>
						<td><strong>Order Id</strong></td>
						<td><strong>Client Name</strong></td>
						<td><strong>From</strong></td>
						<td><strong>To</strong></td>
						<td><strong>Order Date</strong></td>
						<td><strong>Vehicle Type</strong></td>
						<td><strong>Body Type</strong></td>
						<td><strong>Status</strong></td>
						<td><strong>No. of boxes</strong></td>
						<td><strong>Vehicle Number</strong></td>
						<td><strong>Driver Contact Number</strong></td>
						<td><strong>TI Name</strong></td>
						<td><strong>TI Number</strong></td>
						<td><strong>Account Holder Name</strong></td>
						<td><strong>Bank Name</strong></td>
						<td><strong>Account Number</strong></td>
						<td><strong>IFSC Code</strong></td>
						<td><strong>PAN Number</strong></td>
						<td><strong>Balance Payable to Driver</strong></td>
						<td><strong>Cost to Driver</strong></td>
						<td><strong>Revenue to Company</strong></td>
						<td><strong>Advance</strong></td>
						<td><strong>Payment Reference Number</strong></td>
						<td><strong>Remarks</strong></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table> -->

		</div>

<script type="text/javascript">
$("#btnExport").click(function (e) {
    window.open('data:application/vnd.ms-excel,' + $('#excel').html());
    e.preventDefault();
});

var tableToExcel = (function() {
	  var uri = 'data:application/vnd.ms-excel;base64,'
	    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
	    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
	    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	  return function(table, name) {
	    if (!table.nodeType) table = document.getElementById(table)
	    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	    window.location.href = uri + base64(format(template, ctx))
	  }
	})()

</script>
  
  
  
   <!-- heading bar -->
 
<!--   <div class="row ad-hoc-request">
  <div class="panel-group" id="accordion" style="margin-bottom:0;">
    <div class="panel panel-default">
      <div class="panel-heading ad-hoc-heading top_hd" style="background:#0d586b">
        <div class="p_num"><strong>Order Id</strong></div>
        <h4 class="panel-title collapsed">
        <small>Destination <strong style="float:right; padding-right:15px;">Order Date</strong></small>
        <span>Vehicle Type</span>
        <span class="p_status" style="font-weight: bold;">Status</span>
        </h4>
      </div>
    </div>
  </div>
</div>  -->


<!-- heading bar -->


  <!-- toggle panel -->
  
  
  	
  <div class="row ad-hoc-request" id="html" style="margin-top:5px;">
<!--<div class="panel-group" id="accordion">
<div class="panel panel-default">
  <div class="panel-heading ad-hoc-heading">
    <div class="p_num">1.</div>
	<h4 class="panel-title collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapse1" aria-expanded="false">
    <small>Agra, Uttar Pradesh, India <strong style="float:right; padding-right:15px;">15 April, 2016</strong></small>
    <span>Tata 709 (Open body)</span>
    <span class="p_status">Pending</span>
    </h4>
  </div>
  <div id="collapse1" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
    <div class="panel-body">
      <table border="0" style="width:100%" class="nex_tbl_bdr">
        <tbody>
          <tr style="display:none">
            <td><strong class="f13">ID</strong></td>
            <td><input type="text" name="dId" id="dId1" class="form-control input-sm" style="width:100%;" value="641"></td>
          </tr>
          <tr>
            <td><strong class="f13">Client Name</strong></td>
            <td colspan="3">Xalta</td>
          </tr>
          <tr>
            <td colspan="2"><strong class="f13">Hapur, Uttar Pradesh, India</strong> to <strong class="f13">Agra, Uttar Pradesh, India</strong></td>
            <td><strong>Request Date and Time</strong></td>
            <td>15 April, 2016 12:00</td>
          </tr>
          <tr>
            <td colspan="2">Tata 709 <strong>(Open body)</strong></td>
            <td><strong>Number of Boxes</strong></td>
            <td>15</td>
          </tr>
          <tr>
            <td align="left"><strong>Vehicle No.</strong></td>
            <td> -</td>
            <td><strong>Account Holder Name</strong></td>
            <td> -</td>
          </tr>
          <tr>
            <td><strong>Driver Contact No.</strong></td>
            <td> -</td>
            <td><strong>Bank Name</strong></td>
            <td> LQ==</td>
          </tr>
          <tr>
            <td><strong>Trux Inspector Name</strong></td>
            <td> -</td>
            <td><strong>Account Number</strong></td>
            <td> -</td>
          </tr>
          <tr>
            <td><strong>Trux Inspector Contact No.</strong></td>
            <td> -</td>
            <td><strong>IFSC Code</strong></td>
            <td> -</td>
          </tr>
          <tr>
            <td><strong>Balance Payable to Driver</strong></td>
            <td> 0</td>
            <td><strong>PAN Number</strong></td>
            <td> -</td>
          </tr>
          <tr>
            <td><strong>Cost to Driver</strong></td>
            <td><input type="text" name="ctd" id="ctd1" class="form-control input-sm" style="width:100%;"></td>
            <td><strong>Revenue to Company</strong></td>
            <td><input type="text" name="rtc" id="rtc1" class="form-control input-sm" style="width:100%;"></td>
          </tr>
          <tr>
            <td><strong>Advance</strong></td>
            <td><input type="text" name="ad" id="ad1" class="form-control input-sm" style="width:100%;"></td>
            <td><strong>Payment Reference No.</strong></td>
            <td><input type="text" name="prn" id="prn1" class="form-control input-sm" style="width:100%;"></td>
          </tr>
          <tr>
          	<td><strong>Remarks</strong></td>
          	<td colspan="3"><textarea name="remarks" id="remarks" class="form-control input-sm" style="width:100%;"></textarea></td>
          	<td><a href="#" style="color:#666;" onClick="clientAdhocRequestDriverPayment(1)"><i class="glyphicon glyphicon-floppy-disk" title="Save"></i></a></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</div>






</div>-->
</div> 

  	
  
  
  
</div>













<script type="text/javascript">
		var dateToday = new Date();
		var dd = dateToday.getDate();
		var mm = dateToday.getMonth()+1; 
		var yyyy = dateToday.getFullYear();
		var toYears=parseInt(yyyy);
		$(function() {
		$('#searchStartDate').datetimepicker({
			//timepicker:false,
			// timeFormat: 'HH:mm z',
			/*  dayOfWeekStart : 1,
			 lang:'en',
			 yearRange: '1800:' + toYears + '',
			 startDate:	dateToday  */
			timepicker:false,
		    format:'Y/m/d'
			 });
		 
		$('#searchEndDate').datetimepicker({
			 /* datepicker:false,
			  format:'H:i',
			  step:5  */
			 // timepicker:false,
			// timeFormat: 'HH:mm z',
			/*  dayOfWeekStart : 1,
			 lang:'en',
			 yearRange: '1800:' + toYears + '',
			 startDate:	dateToday */
			 timepicker:false,
			    format:'Y/m/d'
			 });
		});
	 
		</script>
</body>
</html>