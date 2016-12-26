<%@page import="com.trux.model.DriverRegistration"%>
<%@page import="com.trux.model.OrganizationMasterRegistration"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Driver File</title>
<script src="/trux/resources/jtable/js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script> 
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script> 

<script type="text/javascript">
function driverToUpdateList()	
{    var orgId = document.getElementById("orgName").value;
 
	 $.ajax({
	      type: "POST",
	      url: "/trux/attandance/getDriverDetails",
	      data:{
	    	  organizationId:orgId
			  } ,
	      success: function(data) {
	    	  document.getElementById("driver").innerHTML = data;
	    	  document.getElementById("driver").value.innerHTML=data;
	      }
	    });
    return true;
}
</script>
</head>
<body>
	<fieldset class="fieldset2 col-lg-12 borderManager table-responsive">
		<legend class="borderManager">
			Upload Driver Documents
			<%
			DriverRegistration rs = (DriverRegistration) request.getAttribute("reg");
			if (rs != null) {
		    if (rs.getId() == 0) {
			%><b style="color: red;">Driver Not Update.
				Might be sume error <%=rs.getErrorMesaage()%>
			</b>
			<%
				} else {
			%><b style="color: green;">Driver Id <%=rs.getId()%>
				upload Successfully !
			</b>
			<%
				}
				}
			%>
			</legend>
 <div class="clearfix margin_10"></div>	
<div class="clearfix margin_10"></div>	

		<form action="/trux/reg/searchEditDrivers" method="post"	class="form-inline" onsubmit="return validateForm();" enctype="multipart/form-data">
			<div class="row" style="margin-top: -2%;">
				<div class="col-lg-3 col-md-3 col-sm-12">
					<div style="margin-bottom: 6px;">
						Organization Name<span style="color: red;">*</span>
					</div>

					<select name="orgName" id="orgName" onchange="driverToUpdateList();" class="input-sm" style="width: 100%;">
						<option value="">--Select Client--</option>
						<%
							List<OrganizationMasterRegistration> list = (List<OrganizationMasterRegistration>) request.getAttribute("orgList");
							if (list != null && !list.isEmpty()) {
								for (int i = 0; i < list.size(); i++) {
						%>
						<option value="<%=list.get(i).getIdClientMaster()%>"><%=list.get(i).getName()%></option>
						<%
							}
							}
						%>
					</select>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-12">
					<div style="margin-bottom: 6px;">
						Driver Name<span style="color: red;">*</span>
					</div>
					<select name="driverName" id="driver" class="input-sm"
						style="width: 100%;">
						<option value="">--Select Driver--</option>
					</select>
				</div>
				 <div class="clearfix margin_10"></div>	
			  <div class="clearfix margin_10"></div>	
				<div class="col-lg-3 col-md-3 col-sm-12">
					<div style="margin-bottom: 6px;">
						Driver Photo
						<!-- <span style="color: red;">*</span> -->
					</div>
					<input type="file" name="imagesOfDH" id="imagesOfDH"
						accept=".jpg, .png, .jpeg, .gif, .bmp, .pdf, .tiff|images/*"
						class="form-control input-sm" style="width: 100%;"
						placeholder="Driver Photo" />
				</div>
				<div class="col-lg-3 col-md-3 col-sm-12">
					<div style="margin-bottom: 6px;">
						Driver DL<span style="color: red;">*</span>
					</div>
					<input type="file" name="imagesOfDL" id="imagesOfDL"
						accept=".jpg, .png, .jpeg, .gif, .bmp, .pdf, .tiff|images/*"
						class="form-control input-sm" style="width: 100%;"
						placeholder="Driver DL" />
				</div>
				<div class="col-lg-3 col-md-3 col-sm-12">
					<div style="margin-bottom: 6px;">
						Driver PAN Card
						<!-- <span style="color: red;">*</span> -->
					</div>
					<input type="file" name="ioPanCard" id="ioPanCard"
						accept=".jpg, .png, .jpeg, .gif, .bmp, .pdf, .tiff|images/*"
						maxlength="12" onFocus="geolocate()" class="form-control input-sm"
						style="width: 100%;" placeholder="Driver PAN Card" />
				</div>

				<div class="col-lg-3 col-md-3 col-sm-12">
					<div style="margin-bottom: 6px;">
						Driver Police Verification Report
						<!-- <span style="color: red;">*</span> -->
					</div>
					<input type="file" name="iOPVReports" id="iOPVReports"
						accept=".jpg, .png, .jpeg, .gif, .bmp, .pdf .tiff|images/*"
						class="form-control input-sm" style="width: 100%;"
						placeholder="Driver Police Verification Report" />
				</div>
			</div>
		</form>
	</fieldset>
</body>
</html>