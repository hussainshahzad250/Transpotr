<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>import</title>
</head>
<body>
	<form:form commandName="fileBean" method="post" enctype="multipart/form-data" action="saveExcel">
        <form:label for="fileData" path="fileData">Select file</form:label><br/><br/>
        <form:input path="fileData" type="file"/>
        <input type="submit" value="Upload File" />
    </form:form>
</body>
</html> --%>

<%@page import="com.trux.model.FileBean"%>
<%@page import="com.trux.model.ClientMandate"%>
<%@page import="java.util.Date"%>
<%@page import="com.trux.utils.DateFormaterUtils"%>
<%@page import="com.trux.model.DriverAttandance"%>
<%@page import="com.trux.model.OrganizationMasterRegistration"%>
<%@page import="java.util.List"%>
<%@page import="com.trux.model.OrderFilterReportsDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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


<style type="text/css">
#md-form input[type="radio"] {
	-webkit-appearance: radio;
}

input[type="checkbox"] {
	-webkit-appearance: checkbox;
}

input[type="text"] {
	-webkit-appearance: text;
}

tbody tr td:first-child, table#attandanceTable tbody tr td:last-child {
	padding: 3px 0px
}
</style>
<style type="text/css">
table.successTable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table.successTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

table.successTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
	border: 1px;
	font-size: 8px;
}
</style>


<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<style type="text/css">
table.successTable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
}

table.successTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

table.successTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
	color: green;
}

.oddrowcolor {
	background-color: #d4e3e5;
}

.evenrowcolor {
	background-color: #c3dde0;
}
</style>


<script src="/trux/resources/core/jquery.autocomplete.min.js"></script>
<link href="/trux/resources/core/main.css" rel="stylesheet">

<link type="text/css" rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>

<title>Sheet Upload</title>
<!-- <script type="text/javascript">

	function saveExcell(){
		var file = document.getElementById("fileData").value;		
		 $.ajax({
		      type: "POST",
		      url: "/trux/transport/saveExcel",
		      data:{
		    	  vehicleTypeId:file
				  } ,
		      success: function(data) {
		    	  if(data){
		    		  $("#message").empty();
		    		  window.location.reload();
		    		  
		    			  $("#message").append(data.errorMesaage);
		    	  }
		      }
		    });
	   return true;		
	}
</script> -->
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<fieldset class="fieldset2 col-lg-12 borderManager">
					<legend class="borderManager" style="width: 100%">Freight Master Upload Data</legend><a href="/trux/transport/downloadExcel" class="btn btn-danger btn-sm right-btn btn_nav1 right_btn_update" style="margin-right: 20px;"><b>Freight Master Download Data</b></a>
					
					<div class="row highlight" style="margin-top: -2%;">
					<form id="md-form" action="/trux/transport/saveExcel" method="POST"
						class="form-inline" onsubmit="return validateForm();"
						enctype="multipart/form-data">
						
						<div class="col-lg-6 col-md-6 col-sm-12">
							<div style="margin-bottom: 6px;">
								Upload Data<span style="color: red;">*</span>
							</div>
							<input type="file" name="fileData" id="fileData"
								class="form-control input-sm" style='width: 100%;'>
						</div>
						
						<div class="col-lg-6 col-md-6 col-sm-12">
							<input type="submit" value="Upload" onclick="saveExcell();" class="form-control input-sm">
						</div>
					</form>
					</div>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<div id="message" style="color: red;"></div>
				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>
