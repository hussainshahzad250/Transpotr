<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link href="/trux/resources/jtable/css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery.jtable.js" type="text/javascript"></script>
<link rel="shortcut icon" type="image/ico" href="/trux/resources/images/favicon.ico" />


</head>
<body> 
<% String titles=""; int flag=Integer.parseInt(""+request.getAttribute("flag")); 
if(flag==1){titles="All Available Records ";}else if(flag==2){titles="All Logged In Available Records ";}else if(flag==3){titles="Logged In Drivers And Transit Available Records ";}else if(flag==4){titles="Not Logged In Available Records ";} %>
<div class="container">  
	<div class="row">
    	<div class="col-lg-12 col-md-12 col-sm-12">   	
    	<fieldset class="fieldset2 col-lg-12 borderManager table-responsive">  <legend class="borderManager"><%=titles%></legend>
			<form  action="loggedInReportsByVehicleType" method="post" class="form-inline"  >
				
					<div class="row" style="margin-top:-2%; ">  
					<div class="col-lg-3 col-md-3 col-sm-12">
						<div style="margin-bottom:6px;">Vehicle type</div>
							<select name="vehicleType" id="vehicleType" class="input-sm" style="width:100%;cursor: pointer;">
							<option value="">--Select Vehicle type</option>
							<option value="Tata Ace">Tata Ace</option>
							<option value="Tata 407">Tata 407</option>
							<option value="Tata 709">Tata 709</option>
							<option value="Bolero Pickup">Bolero Pickup</option>
							<option value="Mahindra Champion">Mahindra Champion</option>
							<option value="Maruti Eeco">Maruti Eeco</option>
							</select> 
						</div>	
						 	<script>  $(document).ready(function(){
       							 $("#vehicleType").val("<%=request.getAttribute("vehicleType")%>").attr('selected', 'selected');
    								 });
    							  </script>                                      									                                  
					 
						<div class="col-lg-3 col-md-3 col-sm-12"><div style="margin-bottom:6px;">City</div>
						<select name="city" id="city" class="input-sm" style="width:100%;cursor: pointer;">
						<option value="">--Select City --</option>
						<option value="Delhi">Delhi</option>
						 
						</select>
						</div>
					  					
						
						<div class="clearfix margin_05"></div>	
						<div class="col-lg-3 col-md-3 col-sm-12">     
						<input type="hidden" name="flag" id="flag">
							<div style="margin-top:6px; font-size:14px; text-align:left; margin-left:0px;margin-top:8px;">
								<button class="btn btn-danger btn-sm btn_nav2">Filter</button> 
								<input type="reset"  class="btn btn-danger btn-sm btn_nav1" value="Clear">
						                             
							</div>
						</div> 
					</div>											
					 <div class="clearfix margin_10"></div> 
				
			</form>   			 
			 
			</fieldset>
		</div>
	</div>		
</div>
<fieldset class="fieldset2 borderManager table-responsive">
<div style="overflow: auto; height: 500px;">
	<div style="width: 100%; margin-right: 0%; margin-left: 0%; text-align: left;">
		<div id="LoggedInTableContainer"></div>
	</div></div>
	
	</fieldset>
	<script type="text/javascript">
	$(document).ready(function() {
		var flag=""+<%=flag%>;		
		document.getElementById('flag').value=flag;
		var vehicleType=""+'<%=request.getAttribute("vehicleType")%>';		 
  		var city=""+'<%=request.getAttribute("city")%>';
  		var url;
		var titles;
		/* if(flag==1){
			titles="All Available Records "
		}else if(flag==2){
			titles="All Logged In Available Records "
		}
		else if(flag==3){
			titles="Logged In Drivers And Transit Available Records "
		}else if(flag==4){
			titles="Not Logged In Available Records "
		}  */
		if(vehicleType!="" && !vehicleType=='' && !vehicleType=='null'){
			url='/trux/reportsapi/loggedInDriverReports?action=list&flag='+flag+'&vehicleType='+vehicleType+'&city='+city;
		}else{
			url='/trux/reportsapi/loggedInDriverReports?action=list&flag='+flag;
		}
		$('#LoggedInTableContainer').jtable({
			title : titles,
		    paging: true, //Enable paging
            pageSize: 15, //Set page size (default: 10)           
            actions: {
            	 listAction:url,
                //listAction: '/trux/reportsapi/loggedInDriverReports?action=list&flag='+flag,
                //createAction: '/trux/reportsapi/loggedInDriverReports?action=create',
                //updateAction: '/trux/reportsapi/loggedInDriverReports?action=update',
                deleteAction: '/trux/reportsapi/loggedInDriverReports?action=delete'
            },
			fields : {
				id : {
					title : 'Id',
					sort :true,
					width : '10%',
					key : true,
					list : true,
					edit : false,
					create : true
				},
				driverName : {
					title : 'Name',
					width : '25%',
					edit : true
				},
				vehicleType : {
					title : 'Vehicle Type',
					width : '20%',
					edit : true
				},
				vehicleNumber : {
					title : 'Vehicle No',
					width : '15%',
					edit : true
				},
				driverPhoneNumber : {
					title : 'Phone Number',
					width : '15%',
					edit : true
				},
				driverMessage : {
					title : 'Status Of Vehicle',
					width : '15%',
					edit : true
				}
			}
		});
		$('#LoggedInTableContainer').jtable('load');
	});
</script>
	
</body>
</html>