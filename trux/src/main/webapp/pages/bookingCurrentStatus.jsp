<%-- <!doctype html>
<%@page import="com.trux.model.CustomerBookingDetails"%>
<%@page import="java.util.List"%> 
<fieldset class="fieldset2 borderManager">
<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12" style="width: 1131px;">
				<div class="padding-md">
					<div class="smart-widget" style="width: 1093px;">
						<div class="smart-widget-inner">
							<ul class="nav nav-tabs tab-style2">
								<li>
							  		<a href="/trux/reports/liveReports">							  			
							  			<span class="text-wrapper">Live Orders</span>
							  		</a>
							  	</li>
							  	<li class="active">
							  		<a href="#">							  			
							  			<span class="text-wrapper">Current Status</span>
							  		</a>
							  	</li>
							  	<li>
							  		<a href="/trux/reports/acceptedBookings">						  			
							  			<span class="text-wrapper">Accepted orders</span>
							  		</a>
							  	</li>
							  	<li>
							  		<a href="/trux/reports/rejectedOrders">					  			
							  			<span class="text-wrapper">Rejected orders</span>
							  		</a>
							  	</li>
								<li>
							  		<a href="#style2Tab5">							  			
							  			<span class="text-wrapper">Lapsed order</span>
							  		</a>
							  	</li>								
							</ul>
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12" style="border:1px solid #e6e6e6; padding:0px;margin-bottom:100px; margin-top:-6px;"> 
                                    <div class="smart-widget-body">
                                        <div class="tab-content">
                                            <div>
												
												<table class="table table-striped table-bordered bootstrap-datatable datatable responsive">								
                                                <tbody>
													<thead>
                                                      <tr style="background-color: #cccccc; ">
														<th><div class="center">Customer Name</div></th>
														<th><div class="center">Customer Phone Number</div></th>
                                                        <th><div class="center">Vehicle Type</div></th>   
														<th><div class="center">Vehicle No</div></th>
														<th><div class="center">Driver Name</div></th>
                                                        <th><div class="center">Driver Phone Number</div></th>   
														<th><div class="center">Current Location</div></th>
                                                        <th width="5%"><div class="center">To</div></th>
														<th><div class="center">From</div></th>
														<th><div class="center">Estimated Time left (in mins.)</div></th>
                                                      </tr>
                                                    </thead>
                                                    <%if(request.getAttribute("allCurrentBookingList") != null){ 
                                                    	List<CustomerBookingDetails> allCurrentBookings = (List<CustomerBookingDetails>)request.getAttribute("allCurrentBookingList");
                                                    	if(allCurrentBookings != null && !allCurrentBookings.isEmpty()){
                                                    	for(CustomerBookingDetails bookings : allCurrentBookings){%>
		                                                    <tr>
		                                                       <td><%=bookings.getCustomerName() %></td>
		                                                       <td><%=bookings.getCustmerPhonenumber() %></td>
		                                                       <td><%=bookings.getVehicleType() %></td>
		                                                       <td><%=bookings.getVehicleNumber() %></td>
		                                                       <td><%=bookings.getDriverName() %></td> 
																<td><%=bookings.getDriverPhonenumber() %></td> 
																<td><a href="http://truxapp.com/tracking/tracking.jsp?bookingId=<%=bookings.getBookingId() %>" target="_blank" style="color:black;">Location</a></td>
		                                                       <td><%=bookings.getToLocation() %></td>
		                                                       <td><%=bookings.getFromLocation() %></td> 
																<td><%=bookings.getEstimatedTimeLeft() %></td> 														
		                                                    </tr>
                                                    	<%}
                                                    }else{%>
                                                    	 <tr>
		                                                       <td>No records found.</td>
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
                                                    <%
                                                    }}else{%>
                                                     <tr>
		                                                       <td>No records found.</td>
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
                                                    <%} %>
                                                  
                                                </tbody>
                                                </table> 
												<div class="clearfix margin_10"></div>
                                            </div><!-- ./tab-pane -->
                                        </div><!-- ./tab-content -->
                                    </div>
								</div>
							</div>
						</div>
					</div><!-- ./smart-widget -->
				</div><!-- ./padding-md -->
			</div>
</div></div>
<!-- Content End -->
<!-- Footer -->
<footer style="">
   <div class="container">
      <div class="row">         
               <div class="siocalicon col-md-4 pull-left"> 
                    <ul class="pull-left" style="margin-left:30px; margin-top:30px;">
                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                        <li><a href="#"><i class="fa fa-instagram"></i></a></li>
						<li><a href="#"><i class="fa fa-youtube"></i></a></li>
						<li><a href="#"><i class="fa fa-vimeo"></i></a></li>
                    </ul>
               </div>    
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
</fieldset>
 
 --%>
 
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="/trux/resources/css/style.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/jquery.jqGrid-4.4.1/css/ui.jqgrid.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script> 
<link href="/trux/resources/jtable/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/jquery.jqGrid-4.4.1/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery.datetimepicker.js" type="text/javascript"></script> 
<script type="text/javascript" src="/trux/resources/jtable/jquery.jqGrid-4.4.1/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="/trux/resources/jtable/jquery.jqGrid-4.4.1/js/jquery.jqGrid.src.js"></script>
 <style type="text/css" media="screen">
    th.ui-th-column div{
        white-space:normal !important;
        height:auto !important;
        padding:2px;
    }
</style>
</head>
<body>
 
   <div class="container">  
        <div class="row">
          <fieldset class="fieldset2 borderManager">
          <!-- <a href="/trux/reportsapi/ordergridXLSReports" style="color: black;font-size: 10px;padding-left: -1"><img alt="" src="/trux/resources/images/xls.png" width="15px;" height="15px;">Export ot Excel</a> -->
          <table id="bookingCurrentList"></table>
		  <div id="pbookingCurrentList"></div>         
          </fieldset>        
        </div>
  
    </div>
  
 <script type="text/javascript">
 
jQuery(document).ready(function() {
	    $.jgrid.nav.searchtext = "Search";
	    $.jgrid.nav.refreshtext = "Re-Fresh";	 
		$("#bookingCurrentList").jqGrid({
			    url:'/trux/reports/bookingCurrentStatuswithinGrid',
			    datatype: 'json',
			    mtype: 'GET', 
			    jsonReader : {
					root: "rows",
					page: "page",
					total: "total",
					records: "records",
					repeatitems: false,
					cell: "cell",
					id: " "
				},
				colNames : ['Customer Name','Customer Phone Number','Vehicle Type','Vehicle No','Driver Name','Driver Phone Number','Current Location','To','From','Estimated Time left (in mins.)'],
				colModel : [
						    {name : 'customerName',index : 'customerName',width : 140,hhidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'custmerPhonenumber',index : 'custmerPhonenumber',width :70,hhidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'vehicleType',index : 'vehicleType',width : 80,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'vehicleNumber',index : 'vehicleNumber',width : 80,hhidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'driverName',index : 'driverName',width : 100,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } }, 
		                    {name : 'driverPhonenumber',index : 'driverPhonenumber',width : 62,hidden : false,editable:true,editrules:{required:true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' }},
		                    {name : 'bookingStatus',index : 'bookingStatus',width : 80,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"'},formatter: function (cellvalue, options, rowObject) { return "<a target='_blank' href=\"http://truxapp.com/tracking/tracking.jsp?bookingId=" + rowObject.bookingId + "\">Location</a>";},unformat: function (cellvalue, options, cellobject) { return cellobject.bookingId;}},
		                    {name : 'fromLocation',index : 'fromLocation',width : 210,hidden : false,editable:true,editrules:{required:true} ,cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'toLocation',index : 'toLocation',width : 210,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'estimatedTimeLeft',index : 'estimatedTimeLeft',width : 100,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"'}},	
		                    ],
				rowNum :10,
				rowList : [5,10,15,20,25,30, 50, 60, 120, 240, 480,1000 ],
				height : "100%",
				width : "100%",
				loadonce : true,
				fontsize : 11,
				shrinkToFit : true,
				rownumbers : false,
				caption : "Current Status Reports",
				singleselect: true,
				pager : '#pbookingCurrentList',
				viewrecords : true,
				emptyrecords : "No racords available",gridComplete: function()
				{
				    var rows = $("#orderList").getDataIDs(); 
				    
				} 
				 
		    });
   	$("#bookingCurrentList").jqGrid('navGrid', '#pbookingCurrentList', {
			edit : false,
			add : false,
			del : false,
			exl : true,
			search : true},
			{width : 'auto',url : '#'}, 
			{width : 'auto',url : '#'}, 
			{width : 'auto', serializeDelData: function (postdata) {
				  var selr = jQuery('#orderList').jqGrid('getGridParam','selrow'); 
			      var rowData = $("#orderList").jqGrid('getRowData');
			     
				  return true; 
				    },
		        	onclickSubmit: function (rp_ge, postdata) { 
		          }
			},
			 
		    {sopt : [ 'cn', 'bw', 'ew' ],
			closeOnEscape : true,
			multipleSearch : false,
			closeAfterSearch : true,
			closeAfterAdd : true,
			closeAfterEdit : true
			});
  
		});    

	</script> 	
 
</body>
</html>