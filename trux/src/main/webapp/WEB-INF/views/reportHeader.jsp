<%@page import="com.trux.model.UserDetail"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="f"%>
<div id="wrapper">
  <div class="overlay" id="overlay_div"></div>
    <% UserDetail userDetail=null;
                if(session.getAttribute("userDetail")!=null){
                	userDetail =(UserDetail)session.getAttribute("userDetail");
                	}
                if(userDetail !=null &&  userDetail.getUserRole()!=null && !userDetail.getUserRole().equals("") ){
                %>
  <!-- Sidebar #095062  -->
  <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation" style="background-color:#003948;">
    <%if(userDetail.getUserRole().trim().equals("Admin")){%>  
    <ul class="nav sidebar-nav"> 
       <li class="sidebar-brand resp_info" style=" height: 63px;border:none;"><img src="/trux/resources/images/profile.png" style="width:47px;float:left; margin: 15px 105px 0 105px;"></li>
      <li class="dropdown sidebar-brand resp_info" style=" width:100%; text-align: center;"><a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown" style=" font-size: 16px"><b style="text-transform:capitalize"><%=userDetail.getFirstname() %>  </b></a>  
      <li><a href="/trux/reports/liveReports"><i class="fa fa-home"></i>&nbsp;Home</a></li>  
      
      <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"> <i class="fa fa-file-text"></i>&nbsp;&nbsp;Exclusive Reports <span class="caret"></span></a>
           <ul class="dropdown-menu" role="menu" >
             <!-- <li class="dropdown-header">Dropdown heading</li> -->
              
            					 <li class="dropdown-header"> </li>
            					  <li><a href="/trux/reports/liveReports">Live Orders</a></li>  
                                 <li> <a href="/trux/reportsapi/orderReports"> Order Reports</a>
                                 <li><a href="/trux/reportsapi/orderReportOnDemandCApp">On Demand (Consumer App)</a>
                                  <li><a href="/trux/reportsapi/orderReportOnDemand">On Demand</a>
                                   <li><a href="/trux/reportsapi/leasedOrderReport">Leased</a>
                                        
                                  </li> 
                                   <li><a href="/trux/reportsapi/revenueReports">Revenue Reports</a></li>                                        
                                   <li><a href="/trux/reports/bookingCurrentStatus">Current Status</a></li>  
                                   <li><a href="/trux/reports/acceptedBookings">Accepted Orders</a></li>  
                                   <li><a href="/trux/reports/rejectedOrders">Rejected Orders</a></li>
          </ul>
      </li>    
        
       <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"><i class="fa fa-file-pdf-o"></i>&nbsp;&nbsp;Consumer Reports <span class="caret"></span></a>
       
                                    <ul class="dropdown-menu" role="menu">  
                                     <li class="dropdown-header"> </li>
                                    <li><a href="/trux/booking/consumerBookrideV2V">Consumer Book Ride </a></li>
                                    <li><a href="/trux/reportsapi/backendBookingRideReports">Book Ride Reports</a></li>
                                    <li><a href="/trux/subclient/leasedbooking">Leased Booking</a></li>  
                                    <li><a href="/trux/reports/bookingReports">Load Later Reports</a></li>
                                    <li><a href="/trux/reports/bookingVehicleChangeReports">Load Later Vehicle Change</a></li>
                                    <li><a href="/trux/consumer/reports">Consumer Report</a></li>
                                   
                                    </ul>
                                </li>
                                 <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown">
                                 <i class="fa fa-truck"></i>&nbsp;&nbsp;All logged In Vehicles <span class="caret"></span></a>  
        
                                    <ul  class="dropdown-menu" role="menu">
                                     <li class="dropdown-header"> </li>
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=1" id="openNewDownloadFormApplyReport">All </a></li>
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=2">Logged In And Available</a></li>
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=3">Drivers In Transit </a></li>	
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=4">Not Logged In </a></li>                                      
                                       
                                    </ul>
                                </li>                      
       <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"><i class="fa fa-random"></i>&nbsp;&nbsp;Vehicle Management <span class="caret"></span></a>
       
                                <ul class="dropdown-menu" role="menu">
                                  <li class="dropdown-header"> </li>  
                             <%if(userDetail.getUserRole().trim().equals("Admin")) {%>   
                                <li><a href="/trux/app/zoneModule">Zone Registration</a></li>                              
                                <li><a href="/trux/reg/hubRegister">Hub Registration</a></li>
                                <li><a href="/trux/reg/clusterModule">Cluster Registration</a></li>
                                <li><a href="/trux/reg/standModule">Stand ( DC ) Registration</a></li>
                                <li><a href="/trux/reg/driver">Driver Registration</a></li>                                
                                <!-- <li><a href="/trux/reg/uploadDriverimage">Upload driver documents</a></li> -->
                                                                             
                                <li><a href="/trux/reg/registerVehicle">Vehicle Registration</a></li>
                                <%} %>   
                                 <li><a href="/trux/trip/tripDetails">Trip Sheet</a></li>
                                <li><a href="/trux/attandance/modules">Attendance (Leased) </a></li>  
                               <!--  <li><a href="/trux/reportsapi/driverloginLogoutReports">Driver Login/Logout Report</a></li> -->
                                 </ul>
                                </li>
       <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"><i class="fa fa-users"></i>&nbsp;&nbsp;Client Management <span class="caret"></span></a>
           <%if( userDetail.getUserRole().equals("Admin")) {%>  
                                <ul  class="dropdown-menu" role="menu">
                                 <li class="dropdown-header"> </li>  
                                <li><a href="/trux/app/orgRegistration">Client Registration</a></li> 
                                <li><a href="/trux/app/subsidiaryOrgModule">Subsidiarly Registration</a></li>
                               <li><a href="/trux/app/organizationUserModule">Create Client User</a></li>
                               <li><a href="/trux/app/organizationalBooking">Client Ride Booking </a></li>
                               <li><a href="/trux/app/clientAdhocRequest">Client Ad-Hoc Request</a></li>
                                   
                              
                                 </ul>
                                </li>
	   <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"><i class="fa fa-file-code-o"></i>&nbsp;&nbsp;Mandate Management <span class="caret"></span></a>
       
                                <ul  class="dropdown-menu" role="menu">
                                 <li class="dropdown-header"> </li>  

                                   <li><a href="/trux/clients/openSearchMandate">Search Mandate</a></li>

                                <li><a href="/trux/clients/mandate">Mandate Registration</a></li> 
                                <li><a href="/trux/clients/mandateDetails">Mandate details type </a></li>
                                 
                              
                                 </ul>
                                </li>
                                
                                <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"><i class="fa fa-automobile"></i>&nbsp;&nbsp;Transporter <span class="caret"></span></a>
           							<ul class="dropdown-menu" role="menu">
              
            					 <li class="dropdown-header"> </li>
            					  <li><a href="/trux/transport/createTransporterOrder">Create Order</a></li>
            					  <li><a href="/trux/transport/updateTransporterOrder">Update Order</a></li>
        						  <!-- <li><a href="/trux/transport/searchTransporterOrder">Search Order</a></li> -->
        						  <li><a href="/trux/transport/publishCRFOrder">Publish CRF Order</a></li>
        						  <li><a href="/trux/transport/approveTransporterOrder">Approve Order</a></li>
          							</ul>
      							</li>  
                                
                                <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"><i class="fa fa-automobile"></i>&nbsp;&nbsp;Masters <span class="caret"></span></a>
           							<ul class="dropdown-menu" role="menu">              
            							 <li class="dropdown-header"> </li>            					  
        						 		<li><a href="/trux/transport/importExcel">Freight Master (Intercity)</a></li>
          							</ul>
           							<ul class="dropdown-menu" role="menu">              
            							 <li class="dropdown-header"> </li>            					  
        						 		<li><a href="/trux/transport/downloadExcel">Download Excel</a></li>
          							</ul>
      							</li>  
                                
        <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"><i class="fa fa-user"></i>&nbsp;&nbsp;Manage User <span class="caret"></span></a>
           							<ul class="dropdown-menu" role="menu">
             <!-- <li class="dropdown-header">Dropdown heading</li> -->
              
            					 <li class="dropdown-header"> </li>
            					  <li><a href="/trux/admin/register/changePass"><button class="btn btn-default btn_nav">Change Password</button></a></li>
            					  <li>
                                  <%if(userDetail.getUserRole().trim().equals("Admin")) {%>
                                <a href="/trux/admin/register/creater" class="welcome_resp"><button class="btn btn-default btn_nav">Create New User </button></a>
                               
                                <%} %>
                                </li>
                            <li> <%if(userDetail.getUserRole().trim().equals("Admin")) {%><a href="/trux/attandance/clientMapping"><button class="btn btn-default btn_nav">Assign Client</button></a><%} %></li>      
        					
          </ul>
      </li>
      
      
     
                                
                                
                                 <% }%> 
                            
    </ul>
    		 <%}else if(userDetail.getUserRole().trim().equals("Agent")) {%>
    		 
    		 
    <ul class="nav sidebar-nav ">
    <li class="sidebar-brand "><img src="/trux/resources/images/prf.png" style="width:47px;float:left; margin: 15px 85px 0;"> </li>
       <!-- <li class="dropdown sidebar-brand"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"> Welcome Manish  <span class="caret"></span></a></li> -->
       
       <li class="dropdown sidebar-brand"><a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown" style=" font-size: 16px">Welcome &nbsp; <b style="text-transform:capitalize"><%=userDetail.getFirstname() %>  </b>  <span class="caret"></span></a>
                               
                                <li><a href="/trux/reports/liveReports"><i class="fa fa-home"></i>&nbsp;Home</a></li>
                             
								<li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"> <i class="fa fa-file-text"></i>&nbsp;&nbsp;Exclusive Reports <span class="caret"></span></a>
           						<ul class="dropdown-menu" role="menu">
             <!-- <li class="dropdown-header">Dropdown heading</li> -->
              
            					 <li class="dropdown-header"> </li>
            					  <li><a href="/trux/reports/liveReports">Live Orders</a></li>  
                                 <li> <a href="/trux/reportsapi/orderReports"> Order Reports</a>
                                 <li><a href="/trux/reportsapi/orderReportOnDemandCApp">On Demand (Consumer App)</a>
                                  <li><a href="/trux/reportsapi/orderReportOnDemand">On Demand</a>
                                   <li><a href="/trux/reportsapi/leasedOrderReport">Leased</a>
                                        
                                  </li> 
                                   <li><a href="/trux/reportsapi/revenueReports">Revenue Reports</a></li>                                        
                                   <li><a href="/trux/reports/bookingCurrentStatus">Current Status</a></li>  
                                   <li><a href="/trux/reports/acceptedBookings">Accepted Orders</a></li>  
                                   <li><a href="/trux/reports/rejectedOrders">Rejected Orders</a></li>
          </ul>
      </li>    
        
                                <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"><i class="fa fa-file-pdf-o"></i>&nbsp;&nbsp;Consumer Reports <span class="caret"></span></a>
       
                                    <ul class="dropdown-menu" role="menu">  
                                     <li class="dropdown-header"> </li>
                                    <li><a href="/trux/booking/consumerBookrideV2V">Consumer Book Ride </a></li>
                                    <li><a href="/trux/reportsapi/backendBookingRideReports">Book Ride Reports</a></li>
                                    <li><a href="/trux/booking/consumerBookrideV2V">Lease Book Ride </a></li>
                                    <li><a href="/trux/subclient/leasedbooking">Leased Booking</a></li>  
                                    <li><a href="/trux/reports/bookingReports">Load Later Reports</a></li>
                                    <li><a href="/trux/reports/bookingVehicleChangeReports">Load Later Vehicle Change</a></li>
                                    <li><a href="/trux/consumer/reports">Consumer Report</a></li>
                                     
                                    </ul>
                                </li>
                                <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"><i class="fa fa-truck"></i>&nbsp;&nbsp;All logged in vehicles <span class="caret"></span></a>  
        
                                    <ul  class="dropdown-menu" role="menu">
                                     <li class="dropdown-header"> </li>
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=1" id="openNewDownloadFormApplyReport">All </a></li>
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=2">Logged In And Available</a></li>
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=3">Drivers In Transit </a></li>	
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=4">Not Logged In </a></li>                                      
                                       
                                    </ul>
                                </li>    
                           
                                <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"><i class="fa fa-random"></i>&nbsp;&nbsp;Vehicle Management <span class="caret"></span></a>
       
                                <ul class="dropdown-menu" role="menu">
                                  <li class="dropdown-header"> </li> 
                                
                                 <%if(userDetail.getUserRole().trim().equals("Admin")) {%>
                                <li><a href="/trux/app/zoneModule">Zone Registration</a></li>                              
                                <li><a href="/trux/reg/hubRegister">Hub Registration</a></li>
                                <li><a href="/trux/reg/clusterModule">Cluster Registration</a></li>
                                <li><a href="/trux/reg/standModule">Stand ( DC ) Registration</a></li>
                                <%} %>
                                <li><a href="/trux/reg/driver">Driver Registration</a></li> 
                                <!-- <li><a href="/trux/reg/editDriver">Update Driver</a></li>       -->                                                        
                                <li><a href="/trux/reg/registerVehicle">Vehicle Registration</a></li>
                                <li><a href="/trux/attandance/modules">Attendance (Leased) </a></li> 
                                <li><a href="/trux/trip/tripDetails">Add Trip Sheet</a></li> 
                                <!-- <li><a href="/trux/reportsapi/driverloginLogoutReports">Driver Login/Logout Report</a></li> -->
                                 </ul>
                                </li>
                                <%if(userDetail.getUserRole().trim().equals("Admin")) {%>
                                <li><a href="javascript:void(0);">Client Management</a>
                                <ul>
                                <li><a href="/trux/app/orgRegistration">Client Registration</a></li> 
                                <li><a href="/trux/app/subsidiaryOrgModule">Subsidiarly Registration</a></li>
                               <li><a href="/trux/app/organizationUserModule">Create Client User</a></li> 
                               <li><a href="/trux/app/organizationalBooking">Client Ride Booking </a></li>
                               <li><a href="/trux/app/clientAdhocRequest">Client Ad-Hoc Request</a></li>
                                </ul>
                                </li>
                                
                           </ul>
                           <%} } else if(userDetail.getUserRole().trim().equals("Sales")) {%>
                           
                             <ul class="nav sidebar-nav ">
                             <li class="sidebar-brand "><img src="/trux/resources/images/prf.png" style="width:47px;float:left; margin: 15px 85px 0;"> </a> </li>
       <!-- <li class="dropdown sidebar-brand"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"> Welcome Manish  <span class="caret"></span></a></li> -->
       
       <li class="dropdown sidebar-brand"><a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown" style=" font-size: 16px">Welcome &nbsp; <b style="text-transform:capitalize"><%=userDetail.getFirstname() %>  </b>  <span class="caret"></span></a>
                               
                                <li><a href="/trux/reports/liveReports"><i class="fa fa-home"></i>&nbsp;Home</a></li>
                                <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"><i class="fa fa-file-code-o"></i>&nbsp;&nbsp;Mandate Management <span class="caret"></span></a>
       
                                  
                                  <ul  class="dropdown-menu" role="menu">
                                 <li class="dropdown-header"> </li>  

                                   <li><a href="/trux/clients/openSearchMandate">Search Mandate</a></li>

                                <li><a href="/trux/clients/mandate">Mandate Registration</a></li> 
                                <li><a href="/trux/clients/mandateDetails">Mandate details Type </a></li>
                                 
                              
                                 </ul>

                               <!--  <li><a href="/trux/clients/mandate">Mandate Registration</a></li> 
                                <li><a href="/trux/clients/mandateDetails">Mandate details type </a></li> -->
                                 
                              
                                 </ul>
                                </li>
                              </ul>
                               <%}else if(userDetail.getUserRole().trim().equals("Field")) {%>
                             <ul class="nav sidebar-nav ">
                             <li class="sidebar-brand "><img src="/trux/resources/images/prf.png" style="width:47px;float:left; margin: 15px 85px 0;">  </li>
       <!-- <li class="dropdown sidebar-brand"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"> Welcome Manish  <span class="caret"></span></a></li> -->
       
       <li class="dropdown sidebar-brand"><a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown" style=" font-size: 16px">Welcome &nbsp; <b style="text-transform:capitalize"><%=userDetail.getFirstname() %>  </b>  <span class="caret"></span></a>
                               
                                <li><a href="/trux/reports/liveReports"><i class="fa fa-home"></i>&nbsp;Home</a></li>  
                             <li class="dropdown"> <a href="javascript:void(0);" class="dropdown-toggle " data-toggle="dropdown"><i class="fa fa-random"></i>&nbsp;&nbsp;Vehicle Management <span class="caret"></span></a>
       
                                <ul class="dropdown-menu" role="menu">
                                  <li class="dropdown-header"> </li>  
                             
                                <li><a href="/trux/reg/standModule">Stand ( DC ) Registration</a></li>
                                <li><a href="/trux/reg/driver">Driver Registration</a></li>                                
                                <!-- <li><a href="/trux/reg/uploadDriverimage">Upload driver documents</a></li> -->
                                                                             
                                <li><a href="/trux/reg/registerVehicle">Vehicle Registration</a></li>
                                
                                <li><a href="/trux/attandance/modules">Attendance (Leased) </a></li>  
                               
                                 </ul>
                                </li>
                                </ul>
                             		 
        <%}}%>
  </nav>
  <!-- /#sidebar-wrapper --> 
  
  <!-- Page Content -->
  <div id="page-content-wrapper">
    <button type="button" class="hamburger is-closed animated fadeInLeft" data-toggle="offcanvas"> <span class="hamb-top"></span> <span class="hamb-middle"></span> <span class="hamb-bottom"></span> </button>
    <div class="container">
      
    </div>
  </div>
  <!-- /#page-content-wrapper --> 
  
</div>
<!-- /#wrapper --> 
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> 
<script>
$(document).ready(function () {
  var trigger = $('.hamburger'),
      overlay = $('.overlay'),
     isClosed = false;

    trigger.click(function () {
      hamburger_cross();      
    });

    function hamburger_cross() {

      if (isClosed == true) {          
        overlay.hide();
        trigger.removeClass('is-open');
        trigger.addClass('is-closed');
        isClosed = false;
        
        $('.hamb-top').show();  
        $('.hamb-middle').show();
        $('.hamb-bottom').show();
      } else {   
        overlay.show();
        trigger.removeClass('is-closed');
        trigger.addClass('is-open');
        isClosed = true;

        $('.hamb-top').hide();  
        $('.hamb-middle').hide();
        $('.hamb-bottom').hide();
      }
  }
  
  $('[data-toggle="offcanvas"]').click(function () {
        $('#wrapper').toggleClass('toggled');
  }); 
  $('#overlay_div').click(function () {
      $('#wrapper').toggleClass('toggled');
      hamburger_cross();
  });
});
</script>
			<!-- .container -->
	
 