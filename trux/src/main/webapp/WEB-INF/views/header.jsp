 <%@page import="com.trux.model.UserDetail"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="f"%>
<div class="container container_crm">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 clearfix">
            <div class="row">
            <div class="col-lg-2 col-md-2 col-sm-12">
                <div id="hs_logo">
                    <a href="index.html">
                     <img src="/trux/resources/images/trux_logo.png" alt="">
                    </a>
                   
                </div> 
             
            </div>
             <% UserDetail userDetail=null;
                if(session.getAttribute("userDetail")!=null){
                	userDetail =(UserDetail)session.getAttribute("userDetail");
                	}
                if(userDetail !=null &&  userDetail.getUserRole()!=null && !userDetail.getUserRole().equals("") ){
                %>
            <div class="col-lg-10 col-md-10 col-sm-12">
                <div class="row">
                     <div class="col-lg-10 col-md-10 col-sm-10 nav_crm">  
 <div class="top_right">   
   <nav id="nav1" role="navigation">
    <a href="#nav1" title="Show navigation"><span style="color: #FFFFFF;">Welcome &nbsp; <b style="text-transform:capitalize;padding: 5px 14px 5px 8px;    border-radius: 4px;    box-shadow: 1px 1px 2px #000;    background: url(/trux/resources/images/drop_ar.png);    background-repeat: no-repeat; background-position: 61px 9px;    background-size: 12px;}"><%=userDetail.getFirstname() %> &nbsp;&nbsp;</b> &nbsp; &nbsp; &nbsp; </span></a>
    <a href="#" title="Hide navigation"><span style="color: #FFFFFF;">Welcome &nbsp; <b style="text-transform:capitalize;background: #ccc;
       background: url(/trux/resources/images/drop_ar1.png);padding: 5px 14px 5px 8px;  border-radius: 4px;  background-repeat: no-repeat; background-position: 61px 9px;    background-size: 12px; box-shadow: 1px 1px 2px #000;"><%=userDetail.getFirstname() %> &nbsp;&nbsp;</b> &nbsp; &nbsp; &nbsp; </span></a>
   <ul id="bs-example-navbar-collapse-1">
                                <li><span style="color: #FFFFFF;" class="welcome_resp">Welcome &nbsp; <b style="text-transform:capitalize"><%=userDetail.getFirstname() %> &nbsp;&nbsp;</b> &nbsp; &nbsp; &nbsp; </span>
                                  <%if(userDetail.getUserRole().trim().equals("Admin")) {%>
                                <a href="/trux/admin/register/creater" class="welcome_resp"><button class="btn btn-default btn_nav">Create New User </button></a>
                               
                                <%} %>
                                </li>
                                 <li> <%if(userDetail.getUserRole().trim().equals("Admin")) {%><a href="/trux/attandance/clientMapping"><button class="btn btn-default btn_nav">Assign Client</button></a><%} %></li>
                                 <li><a href="/trux/admin/register/changePass"><button class="btn btn-default btn_nav">Change Password</button></a></li>
                                <li><a href="/trux/admin/register/logout"><button class="btn btn-default btn_nav" style="background-color:#333">LogOut</button> </a></li>
                            </ul>
</nav>             
  </div>
  </div>
  <div class="col-lg-12 col-md-12 col-sm-12 nav_crm">
                     
  <nav id="nav" role="navigation">
    <a href="#nav" title="Show navigation"><i class="fa fa-bars"></i></a>
   <a href="#" title="Hide navigation"><i class="fa fa-bars"></i>  </a> 
  
    <%if(userDetail.getUserRole().trim().equals("Admin")){%>
    <ul class="hs_menu " id="bs-example-navbar-collapse-1">
          <li><a href="/trux/reports/liveReports">Home</a></li>
                                <li><a href="javascript:void(0); " class="drop-arrow">Exclusive reports</a>
                                    <ul>
                                        <li><a href="/trux/reports/liveReports">Live Orders</a></li>
                                        
                                        <li><a href="/trux/reportsapi/orderReports" class="drop-arrow">Order Reports</a>
                                        <ul>
                                         <li><a href="/trux/reportsapi/orderReportOnDemandCApp">On Demand (Consumer App)</a>
                                          <li><a href="/trux/reportsapi/orderReportOnDemand">On Demand</a>
                                          <li><a href="/trux/reportsapi/leasedOrderReport">Leased</a>
                                        </ul>
                                        </li> 
                                        <li><a href="/trux/reportsapi/revenueReports">Revenue Reports</a></li>                                        
                                        <li><a href="/trux/reports/bookingCurrentStatus">Current Status</a></li>  
                                        <li><a href="/trux/reports/acceptedBookings">Accepted orders</a></li>  
                                        <li><a href="/trux/reports/rejectedOrders">Rejected orders</a></li>  
                                                           
                                    </ul>
                                </li>
                                <li><a href="javascript:void(0);" class="drop-arrow">Consumer Reports </a>
                                    <ul>  
                                    
                                    <li><a href="/trux/booking/consumerBookrideV2V">Consumer Book Ride </a></li>
                                    <li><a href="/trux/reportsapi/backendBookingRideReports">Book Ride Reports</a></li>
                                    <li><a href="/trux/subclient/leasedbooking">Leased Booking</a></li>  
                                    <li><a href="/trux/reports/bookingReports">Load Later Reports</a></li>
                                    <li><a href="/trux/reports/bookingVehicleChangeReports">Load Later Vehicle Change</a></li>
                                    <li><a href="/trux/consumer/reports">Consumer Report</a></li>
                                    </ul>
                                </li>
                                <li><a href="javascript:void(0);" class="drop-arrow">All logged in vehicles</a>
                                    <ul>
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=1" id="openNewDownloadFormApplyReport">All </a></li>
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=2">Logged In And Available</a></li>
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=3">Drivers In Transit </a></li>	
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=4">Not Logged In </a></li>                                      
                                       
                                    </ul>
                                </li>
                           
                                <li><a href="javascript:void(0);"  class="drop-arrow">Driver Management</a>
                                <ul>  
                                <%if(userDetail.getUserRole().trim().equals("Admin")) {%>
                                <li><a href="/trux/app/zoneModule">Zone Registration</a></li>                              
                                <li><a href="/trux/reg/hubRegister">Hub Registration</a></li>
                                <li><a href="/trux/reg/clusterModule">Cluster Registration</a></li>
                                <li><a href="/trux/reg/standModule">Stand ( DC ) Registration</a></li>
                                <li><a href="/trux/reg/driver">Driver Registration</a></li>                                
                                <!-- <li><a href="/trux/reg/uploadDriverimage">Upload driver documents</a></li> -->
                                                                             
                                <li><a href="/trux/reg/registerVehicle">Vehicle Registration</a></li>
                                <%} %>
                                <li><a href="/trux/attandance/modules">Attendance (Leased) </a></li>  
                                <li><a href="/trux/reportsapi/driverloginLogoutReports">Driver Login/Logout Report</a></li>
                                 </ul>
                                </li>
                                <%if( userDetail.getUserRole().equals("Admin")) {%>
                                <li><a href="javascript:void(0);"  class="drop-arrow">Client Management</a>
                                <ul>
                                <li><a href="/trux/app/orgRegistration">Client Registration</a></li> 
                                <li><a href="/trux/app/subsidiaryOrgModule">Subsidiarly Registration</a></li>
                               <li><a href="/trux/app/organizationUserModule">Create Client User</a></li>
                               <li><a href="/trux/app/organizationalBooking">Client Ride Booking </a></li>
                                   
                              
                                 </ul>
                                </li>
                                <li><a href="javascript:void(0);"  class="drop-arrow">Mandate Management</a>
                                <ul>
                                <li><a href="/trux/clients/mandate">Mandate Registration</a></li> 
                                <li><a href="/trux/clients/mandateDetails">Mandate details type </a></li>
                               <!-- <li><a href="/trux/app/organizationUserModule">Create Client User</a></li>
                               <li><a href="/trux/app/organizationalBooking">Client Ride Booking </a></li> -->
                                   
                              
                                 </ul>
                                </li>
                                <% }%>
                            </ul>
                        
 
                                
<!--   -------------------------------------------END NEW MENU- ATUL--------------------------  -->      
         <%}else if(userDetail.getUserRole().trim().equals("Agent")) {%>
                      
                            <ul class="hs_menu " id="bs-example-navbar-collapse-1">
                                <li><a href="/trux/reports/liveReports">Home</a></li>
                                <li><a href="javascript:void(0);" class="drop-arrow">Exclusive reports</a>
                                    <ul>
                                        <li><a href="/trux/reports/liveReports">Live Orders</a></li>
                                         <li><a href="/trux/reportsapi/orderReports" class="drop-arrow">Order Reports</a>
                                        <ul>
                                        <li><a href="/trux/reportsapi/orderReportOnDemandCApp">On Demand (Consumer App)</a>
                                         <li><a href="/trux/reportsapi/orderReportOnDemand">On Demand</a>
                                         <li><a href="/trux/reportsapi/leasedOrderReport">Leased</a>
                                        </ul>
                                        </li> 
                                       <li><a href="/trux/reportsapi/revenueReports">Revenue Reports</a></li>                                        
                                        <li><a href="/trux/reports/bookingCurrentStatus">Current Status</a></li>  
                                        <li><a href="/trux/reports/acceptedBookings">Accepted orders</a></li>  
                                        <li><a href="/trux/reports/rejectedOrders">Rejected orders</a></li>  
                                       <!--  <li><a href="#style2Tab5">Lapsed order</a></li>  -->                             
                                    </ul>
                                </li>
                                <li><a href="javascript:void(0);" class="drop-arrow">Consumer Reports </a>
                                    <ul>
                                    
                                    <li><a href="/trux/booking/consumerBookrideV2V">Consumer Book Ride </a></li>
                                    <li><a href="/trux/reportsapi/backendBookingRideReports">Book Ride Reports</a></li>
                                    <li><a href="/trux/booking/consumerBookrideV2V">Lease Book Ride </a></li>
                                   <li><a href="/trux/subclient/leasedbooking">Leased Booking</a></li>  
                                        <li><a href="/trux/reports/bookingReports">Load Later Reports</a></li>
                                         <li><a href="/trux/reports/bookingVehicleChangeReports">Load Later Vehicle Change</a></li>
                                        <li><a href="/trux/consumer/reports">Consumer Report</a></li>
                                    </ul>
                                </li>
                                <li><a href="javascript:void(0);" class="drop-arrow">All logged in vehicles</a>
                                    <ul>
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=1" id="openNewDownloadFormApplyReport">All </a></li>
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=2">Logged In And Available</a></li>
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=3">Drivers In Transit </a></li>	
                                        <li><a href="/trux/reportsapi/loggedInReports?flag=4">Not Logged In </a></li>                                      
                                       
                                    </ul>
                                </li>
                           
                                <li><a href="javascript:void(0);" class="drop-arrow">Driver Management</a>
                                <ul>  
                                
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
                                <li><a href="/trux/reportsapi/driverloginLogoutReports">Driver Login/Logout Report</a></li>
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
                     	 
                               <ul class="hs_menu" id="bs-example-navbar-collapse-1">
                                <li><a href="/trux/reports/liveReports">Home</a></li>
                                 <li><a href="javascript:void(0);"  class="drop-arrow">Mandate Management</a>
                                <ul>
                                <li><a href="/trux/clients/mandate">Client Mandate Registration</a></li> <!-- 
                                <li><a href="/trux/app/subsidiaryOrgModule">Client Mandate Details Registration</a></li>
                               <li><a href="/trux/app/organizationUserModule">Create Client User</a></li>
                               <li><a href="/trux/app/organizationalBooking">Client Ride Booking </a></li> -->
                                 </ul>
                                </li>
                                 </ul>
                         <%}else if(userDetail.getUserRole().trim().equals("Field")) {%>
                     	 
                      <ul class="hs_menu" id="bs-example-navbar-collapse-1">
                                <li><a href="/trux/reports/liveReports">Home</a></li>
                                <li><a href="javascript:void(0);"  class="drop-arrow">Driver Management</a>
                                 <ul> 
                                <li><a href="/trux/attandance/modules">Attendance (Leased) </a></li> 
                                <li><a href="/trux/reg/standModule">Stand ( DC ) Registration</a></li>
                                <li><a href="/trux/reg/driver">Driver Registration</a></li>                                                       
                                <li><a href="/trux/reg/registerVehicle">Vehicle Registration</a></li>
                                 
                               </ul>
                                </li>
                                </ul>  
                                
                         <%}}%>
                          </nav>
                    </div> 
                
                </div>
                </div>
              
            </div>
        </div>
        <!-- .col-md-12 -->
    </div>
    <!-- .row -->
</div>
			<!-- .container -->
	