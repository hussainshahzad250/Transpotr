<!doctype html>
<%@page import="java.util.List"%>
<%@page import="com.trux.model.DriverDeviceVehicleMapping"%>
 
<script type="text/javascript">
function statusDrivers()
{
 
var flag=document.getElementById("LoggedInDriver").value;
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
    document.getElementById("driversStatusReport").innerHTML=xmlhttp.responseText;
    }
  }
  xmlhttp.open("GET","/trux/reportsapi/loggedInReports?flag="+flag,true);
//xmlhttp.open("GET","/trux/reports/statusOfDriverVehicles?flag="+flag,true);
xmlhttp.send();
}


function statusDriversDevice(flag)
{
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
    document.getElementById("driversStatusReport").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","/trux/reports/statusOfDriverVehicles?flag="+flag,true);
xmlhttp.send();
}

function removeDriverById(byId,name)
{
var x;
if (confirm("Are you sure to delete "+name+" Driver !") == true) {

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
    document.getElementById("message"+byId).innerHTML=xmlhttp.responseText;
    var flag=document.getElementById("LoggedInDriver").value;
    statusDriversDevice(flag);
     }
  }
xmlhttp.open("GET","/trux/reports/removeDriverAndVehicles?byId="+byId+"&name="+name,true);
xmlhttp.send();
	    } else {
	        x = "Not suere";
	    }
}

 </script>
<div class="container">
		<div class="row" style="margin-bottom:20%;">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="padding-md">
					<div class="smart-widget">
						<div class="smart-widget-inner">
							<div class="clearfix margin_10"></div>
                            <div class="col-lg-6 col-md-6 col-sm-12 row">
                                 <div style="margin-bottom:6px;"><h4>Logged in Vehicles </h4>
                                 <select id="LoggedInDriver" onchange="return statusDrivers();">
                                  <option value="0">--Select--</option>
                                 <option value="1">All</option>
                                 <option value="2">Logged In and Available</option>                                 
                                 <option value="3">Drivers In Transit</option>
                                 <option value="4">Not Logged In</option>
                                 </select>
                                 </div>                 
                            </div>
                            <div id="driversStatusReport" class="table table-striped table-bordered bootstrap-datatable datatable responsive"></div>
                           <%--  <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">								
                            <tbody>
                                <thead>
                                  <tr style="background-color: #cccccc; ">
                                    <th><div class="center">Name</div></th>
                                    <th><div class="center">Vehicle Type</div></th>   
                                    <th><div class="center">Vehicle No</div></th>
                                    <th><div class="center">Phone Number</div></th>   
                                    <th><div class="center">Current Location</div></th>
                                    <th><div class="center">Status of the Vehicle </div></th>                 
                                  </tr>
                                </thead>
                                 <%if(request.getAttribute("driverDeviceMapList") != null){
                                           	List<DriverDeviceVehicleMapping> driverDeviceMapList = (List<DriverDeviceVehicleMapping>)request.getAttribute("driverDeviceMapList");
                                           	if(driverDeviceMapList != null && !driverDeviceMapList.isEmpty()){
                                           	for(DriverDeviceVehicleMapping driverDeviceMap : driverDeviceMapList){%>
                       							<tr>
                                 <td><%=driverDeviceMap.getDriverName() %></td>
                                 <td><%=driverDeviceMap.getVehicleType() %></td> 
                                  <td><%=driverDeviceMap.getVehicleNumber() %></td> 
                                  <td><%=driverDeviceMap.getDriverPhoneNumber() %></td> 
                                  <td></td> 
                                  <td>
                                  <%if(driverDeviceMap.getDriverStatus() == 0){ %>
                                  	Available
                                  <%}else if(driverDeviceMap.getDriverStatus() == 1){ %>
                                  	Busy
                                  <%} %>
                                  </td>                                                                          
                              </tr>                    	
                                  <%
                                  }
                                       }else{%>
                                    	         				<tr>
									                                 <td>No records found</td>
									                                 <td></td> 
									                                  <td></td> 
									                                  <td></td> 
									                                  <td></td> 
									                                  <td></td>                                                                          
									                              </tr>  
                                       <%}
                                           	}else{ %>
                                           				<tr>
                                 <td>No records found</td>
                                 <td></td> 
                                  <td></td> 
                                  <td></td> 
                                  <td></td> 
                                  <td></td>                                                                          
                              </tr>
                                           	<% }%>
                                
                            </tbody>
                            </table>  --%>
							<div class="clearfix margin_10"></div>
						</div>
					</div>
				</div>
</div>
					</div>
				</div>
<!-- Content End -->
<!-- Footer -->
<footer style="">
   <div class="container">
      <div class="row">         
             
				<div class="col-md-4">                  
                     <!-- ScrollToTop -->         
                     <a class="scrollup" href="#" title=""><i class="fa fa-angle-up"></i></a>               </div>    
				<div class="col-md-4" >   
                   <p style="font-size:12px; text-align:right;"><a href="refund_policy.html">Refund Policy</a> &nbsp;&nbsp; <a href="cancellation.html">Cancellation Policy</a>      </p>            
               </div>      
      </div><!-- .row End -->
   </div><!-- .container End -->
</footer>
<!-- Footer -->
 
