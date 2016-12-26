<%-- 
<%@page import="com.trux.model.UserDetail"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute></title>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="shortcut icon" type="image/ico" href="/trux/resources/images/favicon.ico" />

<!-- main css -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- All CSS Styles -->
<link type="text/css" href="/trux/resources/css/bootstrap.css" rel="stylesheet" media="screen" />
<link type="text/css" href="/trux/resources/css/animate.css" rel="stylesheet" media="screen" />
<!-- <link type="text/css" href="/trux/resources/css/swiper.css" rel="stylesheet" media="screen" /> -->
<link type="text/css" href="/trux/resources/css/owl.carousel.css" rel="stylesheet" media="screen" />
<link type="text/css" href="/trux/resources/css/owl.theme.css" rel="stylesheet" media="screen" /> 
<link type="text/css" href="/trux/resources/css/style.css" rel="stylesheet" media="screen" />  	
<link type="text/css" href="/trux/resources/css/font-awesome.min.css" rel="stylesheet" media="screen" /> 
 <!-- Different Styles End -->
<!-- Media Queries -->
<link type="text/css" href="/trux/resources/css/media.css" rel="stylesheet" media="screen" />
<!-- Modernizr -->
<script type="text/javascript" src="/trux/resources/js/modernizr.js"></script>
<style>
/*-------------------------------*/
/*           VARIABLES           */
/*-------------------------------*/


body {
  position: relative;
  overflow-x: hidden;
}

body, html {
  height: 100%;
 /*  background-color: #583e7e; */
}

.nav .open > a { background-color: transparent; }

.nav .open > a:hover { background-color: transparent; }

.nav .open > a:focus { background-color: transparent; }

/*-------------------------------*/
/*           Wrappers            */
/*-------------------------------*/

#wrapper {
  -moz-transition: all 0.5s ease;
  -o-transition: all 0.5s ease;
  -webkit-transition: all 0.5s ease;
  padding-left: 0;
  -webkit-transition: all 0.5s ease;
  transition: all 0.5s ease;
}

#wrapper.toggled { padding-left: 220px; }

#wrapper.toggled #sidebar-wrapper { width: 244px; }

#wrapper.toggled #page-content-wrapper {
  margin-right: -220px;
  position: absolute;
}

#sidebar-wrapper {
  -moz-transition: all 0.5s ease;
  -o-transition: all 0.5s ease;
  -webkit-transition: all 0.5s ease;
  background: #1a1a1a;
  height: 100%;
  left: 220px;
  margin-left: -220px;
  overflow-x: hidden;
  overflow-y: auto;
  -webkit-transition: all 0.5s ease;
  transition: all 0.5s ease;
  width: 0;
  z-index: 1000;
}
#sidebar-wrapper::-webkit-scrollbar {
 display: none;
}

#page-content-wrapper {
 /*  padding-top: 70px; */
  width: 100%;
}

/*-------------------------------*/
/*     Sidebar nav styles        */
/*-------------------------------*/

.sidebar-nav {
  list-style: none;
  margin: 0;
  padding: 0;
  position: absolute;
  top: 0;
  width: 244px;
}

.sidebar-nav li {
  display: inline-block;
  line-height: 20px;
  position: relative;
  width: 100%;
  text-align: left;
}

.sidebar-nav li:before {
  -moz-transition: width 0.2s ease-in;
  -ms-transition: width 0.2s ease-in;
  -webkit-transition: width 0.2s ease-in;
  background-color: #0AAAD2;
  content: '';
  height: 100%;
  left: 0;
  position: absolute;
  top: 0;
  -webkit-transition: width 0.2s ease-in;
  transition: width 0.2s ease-in;
  width: 3px;
  z-index: -1;
}

.sidebar-nav li:first-child a {
  background-color: #1a1a1a;
  color: #ffffff;
}

.sidebar-nav li:nth-child(2):before { background-color: #0AAAD2; }

.sidebar-nav li:nth-child(3):before { background-color: #0AAAD2; }

.sidebar-nav li:nth-child(4):before { background-color: #0AAAD2; }

.sidebar-nav li:nth-child(5):before { background-color: #0AAAD2; }

.sidebar-nav li:nth-child(6):before { background-color: #0AAAD2; }

.sidebar-nav li:nth-child(7):before { background-color: #0AAAD2; }

.sidebar-nav li:nth-child(8):before { background-color: #0AAAD2; }

.sidebar-nav li:nth-child(9):before { background-color: #0AAAD2; }

.sidebar-nav li:hover:before {
  -webkit-transition: width 0.2s ease-in;
  transition: width 0.2s ease-in;
  width: 100%;
}

.sidebar-nav li a {
  color: #dddddd;
  display: block;
  padding: 6px 5px 6px 14px;
  text-decoration: none;
}

.sidebar-nav li.open:hover before {
  -webkit-transition: width 0.2s ease-in;
  transition: width 0.2s ease-in;
  width: 100%;
}

.sidebar-nav .dropdown-menu {
  background-color: #095062;
  border-radius: 0;
  border: none;
  box-shadow: none;
  margin: 0;
  padding: 0;
  position: relative;
  width: 100%;
}

.sidebar-nav li a:hover, .sidebar-nav li a:active, .sidebar-nav li a:focus, .sidebar-nav li.open a:hover, .sidebar-nav li.open a:active, .sidebar-nav li.open a:focus {
  background-color: transparent;
  color: #ffffff;
  text-decoration: none;
}

.sidebar-nav > .sidebar-brand {
  /* font-size: 20px;
  height: 65px; */
  line-height: 44px;
  background: #0AAAD2;
}

/*-------------------------------*/
/*       Hamburger-Cross         */
/*-------------------------------*/



.hamburger:hover { outline: none; }

.hamburger:focus { outline: none; }

.hamburger:active { outline: none; }

.hamburger.is-closed:before {
  -webkit-transform: translate3d(0, 0, 0);
  -webkit-transition: all 0.35s ease-in-out;
  color: #ffffff;
  content: '';
  display: block;
  font-size: 14px;
  line-height: 32px;
  opacity: 0;
  text-align: center;
  width: 100px;
}

.hamburger.is-closed:hover before {
  -webkit-transform: translate3d(-100px, 0, 0);
  -webkit-transition: all 0.35s ease-in-out;
  display: block;
  opacity: 1;
}

.hamburger.is-closed:hover .hamb-top {
  -webkit-transition: all 0.35s ease-in-out;
  top: 0;
}

.hamburger.is-closed:hover .hamb-bottom {
  -webkit-transition: all 0.35s ease-in-out;
  bottom: 0;
}

.hamburger.is-closed .hamb-top {
  -webkit-transition: all 0.35s ease-in-out;
  background-color: #000;
  top: 5px;
}

.hamburger.is-closed .hamb-middle {
  background-color: #000;
  margin-top: -2px;
  top: 50%;
}

.hamburger.is-closed .hamb-bottom {
  -webkit-transition: all 0.35s ease-in-out;
  background-color: #000;
  bottom: 5px;
}

.hamburger.is-closed .hamb-top, .hamburger.is-closed .hamb-middle, .hamburger.is-closed .hamb-bottom, .hamburger.is-open .hamb-top, .hamburger.is-open .hamb-middle, .hamburger.is-open .hamb-bottom {
  height: 4px;
  left: 0;
  position: absolute;
  width: 100%;
}

.hamburger.is-open .hamb-top {
  -webkit-transform: rotate(45deg);
  -webkit-transition: -webkit-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
  background-color: #ffffff;
  margin-top: -2px;
  top: 50%;
}

.hamburger.is-open .hamb-middle {
  background-color: #ffffff;
  display: none;
}

.hamburger.is-open .hamb-bottom {
  -webkit-transform: rotate(-45deg);
  -webkit-transition: -webkit-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
  background-color: #ffffff;
  margin-top: -2px;
  top: 50%;
}

.hamburger.is-open:before {
  -webkit-transform: translate3d(0, 0, 0);
  -webkit-transition: all 0.35s ease-in-out;
  color: #ffffff;
  content: '';
  display: block;
  font-size: 14px;
  line-height: 32px;
  opacity: 0;
  text-align: center;
  width: 100px;
}

.hamburger.is-open:hover before {
  -webkit-transform: translate3d(-100px, 0, 0);
  -webkit-transition: all 0.35s ease-in-out;
  display: block;
  opacity: 1;
}

/*-------------------------------*/
/*          Dark Overlay         */
/*-------------------------------*/

.overlay {
  position: fixed;
  display: none;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 1;
}

/* SOME DEMO STYLES - NOT REQUIRED */

/* body, html { background-color: #583e7e; } */

body h1, body h2, body h3, body h4 { color: rgba(255, 255, 255, 0.9); }

body p, body blockquote { color: rgba(255, 255, 255, 0.7); }

body a {
  color: rgba(255, 255, 255, 0.8);
  text-decoration: underline;
}

body a:hover { color: #ffffff; }
</style>
</head>
<body style="background-color:#f8f8f8;">
<div style="width:100%; float:left; margin:auto;">
<!--  style="background-image:url(/trux/resources/images/banner2.jpg); -->
<header id="header" class="gradient4Color header"   role="banner">	
<script>
function call(){
$("#form").submit();
}
</script>
<div class="container">  
	<div class="row">
	<% UserDetail userDetail=null;
                if(session.getAttribute("userDetail")!=null){
                	userDetail =(UserDetail)session.getAttribute("userDetail");
                	}
                if(userDetail !=null &&  userDetail.getUserRole()!=null && !userDetail.getUserRole().equals("") ){
                %>
	<div class="col-lg-10 col-md-10 col-sm-10 nav_crm"> 
	
	<div class="col-lg-12 col-md-12 col-sm-12" >
                <div id="hs_logo" style="width: 70%;margin-left: 30%;" >
                     	
                          <img src="/trux/resources/images/trux_logo.png" alt="" onclick="call();">
                        
          
                      <a href="/trux/admin/register/logout" style="right: 1px;float: right;"><button class="btn btn-default btn_nav" style="background-color:#333">LogOut</button> </a>
    
                    <a href="#" class="resp-add" style="  float: right;text-decoration: none;width: 100px;">
             
                    
                     	<img src="/trux/resources/images/profile.png" alt="" style=" width: 42px; margin: 0 auto; margin-left: 24px">
                     	</br>
                     	
                     	 <b style="color: #fff;font-size: 17px;float: left;margin-top: 0px;z-index: 9999; position: absolute; opacity: 0.8; text-align: center;    margin-top: -6px; width: 100px;"><%=userDetail.getFirstname() %> &nbsp;&nbsp;  </b> 
                    </a>
                   
                    </div> 
                <form action="/trux/admin/register/home" method="post" id="form">
                     	        <input type="hidden" name="userName" value="<%=userDetail.getFirstname()%>">
                                <input type="hidden" name="userPassword" value="<%=userDetail.getPassword()%>">
                                </form>
 <div class="top_right">   
  
                      
  </div>
  </div>
  
             
            </div>
    	<div class="col-lg-12 col-md-12 col-sm-12">  
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
		</div>
		 
		<div class="col-lg-12 col-md-12 col-sm-12" style="margin-buttom:20px;" >  			
			<tiles:insertAttribute name="body"></tiles:insertAttribute>
		</div>	
		<%}else{ %>
		<div class="col-lg-12 col-md-12 col-sm-12" style="margin-buttom:20px;" >  			
			<tiles:insertAttribute name="message"></tiles:insertAttribute>
		</div>	
		<%} %>
	</div>
</div>
<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</header>
</div>
</body>
</html> --%>




<%@page import="com.trux.model.UserDetail"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute></title>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="shortcut icon" type="image/ico" href="/trux/resources/images/favicon.ico" />

<!-- main css -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- All CSS Styles -->
<link type="text/css" href="/trux/resources/css/bootstrap.css" rel="stylesheet" media="screen" />
<link type="text/css" href="/trux/resources/css/animate.css" rel="stylesheet" media="screen" />
<!-- <link type="text/css" href="/trux/resources/css/swiper.css" rel="stylesheet" media="screen" /> -->
<link type="text/css" href="/trux/resources/css/owl.carousel.css" rel="stylesheet" media="screen" />
<link type="text/css" href="/trux/resources/css/owl.theme.css" rel="stylesheet" media="screen" /> 
<link type="text/css" href="/trux/resources/css/style.css" rel="stylesheet" media="screen" />  	
<link type="text/css" href="/trux/resources/css/font-awesome.min.css" rel="stylesheet" media="screen" /> 
 <!-- Different Styles End -->
<!-- Media Queries -->
<link type="text/css" href="/trux/resources/css/media.css" rel="stylesheet" media="screen" />
<!-- Modernizr -->
<script type="text/javascript" src="/trux/resources/js/modernizr.js"></script>
<style>
/*-------------------------------*/
/*           VARIABLES           */
/*-------------------------------*/


body {
  position: relative;
  overflow-x: hidden;
}

body, html {
  height: 100%;
 /*  background-color: #583e7e; */
}

.nav .open > a { background-color: transparent; }

.nav .open > a:hover { background-color: transparent; }

.nav .open > a:focus { background-color: transparent; }

/*-------------------------------*/
/*           Wrappers            */
/*-------------------------------*/

#wrapper {
  -moz-transition: all 0.5s ease;
  -o-transition: all 0.5s ease;
  -webkit-transition: all 0.5s ease;
  padding-left: 0;
  -webkit-transition: all 0.5s ease;
  transition: all 0.5s ease;
}

#wrapper.toggled { padding-left: 220px; }

#wrapper.toggled #sidebar-wrapper { width: 244px; }

#wrapper.toggled #page-content-wrapper {
  margin-right: -220px;
  position: absolute;
}

#sidebar-wrapper {
  -moz-transition: all 0.5s ease;
  -o-transition: all 0.5s ease;
  -webkit-transition: all 0.5s ease;
  background: #1a1a1a;
  height: 100%;
  left: 220px;
  margin-left: -220px;
  overflow-x: hidden;
  overflow-y: auto;
  -webkit-transition: all 0.5s ease;
  transition: all 0.5s ease;
  width: 0;
  z-index: 1000;
}
#sidebar-wrapper::-webkit-scrollbar {
 display: none;
}

#page-content-wrapper {
 /*  padding-top: 70px; */
  width: 100%;
}

/*-------------------------------*/
/*     Sidebar nav styles        */
/*-------------------------------*/

.sidebar-nav {
  list-style: none;
  margin: 0;
  padding: 0;
  position: absolute;
  top: 0;
  width: 244px;
}

.sidebar-nav li {
  display: inline-block;
  line-height: 20px;
  position: relative;
  width: 100%;
  text-align: left;
}

.sidebar-nav li:before {
  -moz-transition: width 0.2s ease-in;
  -ms-transition: width 0.2s ease-in;
  -webkit-transition: width 0.2s ease-in;
  background-color: #0AAAD2;
  content: '';
  height: 100%;
  left: 0;
  position: absolute;
  top: 0;
  -webkit-transition: width 0.2s ease-in;
  transition: width 0.2s ease-in;
  width: 3px;
  z-index: -1;
}

.sidebar-nav li:first-child a {
  background-color: #1a1a1a;
  color: #ffffff;
}

.sidebar-nav li:nth-child(2):before { background-color: #0AAAD2; }

.sidebar-nav li:nth-child(3):before { background-color: #0AAAD2; }

.sidebar-nav li:nth-child(4):before { background-color: #0AAAD2; }

.sidebar-nav li:nth-child(5):before { background-color: #0AAAD2; }

.sidebar-nav li:nth-child(6):before { background-color: #0AAAD2; }

.sidebar-nav li:nth-child(7):before { background-color: #0AAAD2; }

.sidebar-nav li:nth-child(8):before { background-color: #0AAAD2; }

.sidebar-nav li:nth-child(9):before { background-color: #0AAAD2; }

.sidebar-nav li:hover:before {
  -webkit-transition: width 0.2s ease-in;
  transition: width 0.2s ease-in;
  width: 100%;
}

.sidebar-nav li a {
  color: #fff;
  display: block;
  padding: 6px 5px 6px 14px;
  text-decoration: none;
  font-size: 15px;
  width: 100%;
  text-align: left;
}

.sidebar-nav li.open:hover before {
  -webkit-transition: width 0.2s ease-in;
  transition: width 0.2s ease-in;
  width: 100%;
}

.sidebar-nav .dropdown-menu {
  background-color: #095062;
  border-radius: 0;
  border: none;
  box-shadow: none;
  margin: 0;
  padding: 0;
  position: relative;
  width: 100%;
}

.sidebar-nav li a:hover, .sidebar-nav li a:active, .sidebar-nav li a:focus, .sidebar-nav li.open a:hover, .sidebar-nav li.open a:active, .sidebar-nav li.open a:focus {
  background-color: transparent;
  color: #ffffff;
  text-decoration: none;
}

.sidebar-nav > .sidebar-brand {
  /* font-size: 20px;
  height: 65px; */
  line-height: 44px;
  background: #0AAAD2;
}

/*-------------------------------*/
/*       Hamburger-Cross         */
/*-------------------------------*/



.hamburger:hover { outline: none; }

.hamburger:focus { outline: none; }

.hamburger:active { outline: none; }

.hamburger.is-closed:before {
  -webkit-transform: translate3d(0, 0, 0);
  -webkit-transition: all 0.35s ease-in-out;
  color: #ffffff;
  content: '';
  display: block;
  font-size: 14px;
  line-height: 32px;
  opacity: 0;
  text-align: center;
  width: 100px;
}

.hamburger.is-closed:hover before {
  -webkit-transform: translate3d(-100px, 0, 0);
  -webkit-transition: all 0.35s ease-in-out;
  display: block;
  opacity: 1;
}

.hamburger.is-closed:hover .hamb-top {
  -webkit-transition: all 0.35s ease-in-out;
  top: 0;
}

.hamburger.is-closed:hover .hamb-bottom {
  -webkit-transition: all 0.35s ease-in-out;
  bottom: 0;
}

.hamburger.is-closed .hamb-top {
  -webkit-transition: all 0.35s ease-in-out;
  background-color: #000;
  top: 5px;
}

.hamburger.is-closed .hamb-middle {
  background-color: #000;
  margin-top: -2px;
  top: 50%;
}

.hamburger.is-closed .hamb-bottom {
  -webkit-transition: all 0.35s ease-in-out;
  background-color: #000;
  bottom: 5px;
}

.hamburger.is-closed .hamb-top, .hamburger.is-closed .hamb-middle, .hamburger.is-closed .hamb-bottom, .hamburger.is-open .hamb-top, .hamburger.is-open .hamb-middle, .hamburger.is-open .hamb-bottom {
  height: 4px;
  left: 0;
  position: absolute;
  width: 100%;
}

.hamburger.is-open .hamb-top {
  -webkit-transform: rotate(45deg);
  -webkit-transition: -webkit-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
  background-color: #ffffff;
  margin-top: -2px;
  top: 50%;
}

.hamburger.is-open .hamb-middle {
  background-color: #ffffff;
  display: none;
}

.hamburger.is-open .hamb-bottom {
  -webkit-transform: rotate(-45deg);
  -webkit-transition: -webkit-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
  background-color: #ffffff;
  margin-top: -2px;
  top: 50%;
}

.hamburger.is-open:before {
  -webkit-transform: translate3d(0, 0, 0);
  -webkit-transition: all 0.35s ease-in-out;
  color: #ffffff;
  content: '';
  display: block;
  font-size: 14px;
  line-height: 32px;
  opacity: 0;
  text-align: center;
  width: 100px;
}

.hamburger.is-open:hover before {
  -webkit-transform: translate3d(-100px, 0, 0);
  -webkit-transition: all 0.35s ease-in-out;
  display: block;
  opacity: 1;
}

/*-------------------------------*/
/*          Dark Overlay         */
/*-------------------------------*/

.overlay {
  position: fixed;
  display: none;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 1;
}

/* SOME DEMO STYLES - NOT REQUIRED */

/* body, html { background-color: #583e7e; } */

body h1, body h2, body h3, body h4 { color: rgba(255, 255, 255, 0.9); }

body p, body blockquote { color: rgba(255, 255, 255, 0.7); }

body a {
  color: rgba(255, 255, 255, 0.8);
  text-decoration: underline;
}

body a:hover { color: #ffffff; }
* {font-family:arial; margin:0; padding:0;}
 
 
.menu {background:#0AAAD2; color:#fff; height:70px; line-height:70px;letter-spacing:1px; width:100%;}
 
</style>
</head>
<body style="background-color:#f8f8f8;height: 100%">
<div style="width:100%; float:left; margin:auto;">
 
<header id="header" class="gradient4Color header" role="banner">
 <script>
function call(){
$("#form").submit();
}
</script>	
<div class="menu">
<% UserDetail userDetail=null;
                if(session.getAttribute("userDetail")!=null){
                	userDetail =(UserDetail)session.getAttribute("userDetail");
                	}
                if(userDetail !=null &&  userDetail.getUserRole()!=null && !userDetail.getUserRole().equals("") ){
                %>
<div class="col-lg-10 col-md-10 col-sm-10 nav_crm"> 
	
	<div class="col-lg-12 col-md-12 col-sm-12" >
                <div id="hs_logo" style="width: 70%;margin-left: 30%;">
                     	<img src="/trux/resources/images/trux_logo.png" alt="" onclick="call();">
                        

<a href="/trux/admin/register/logout" style=" float: right; margin: -15px 0 23px;;  margin-left: -27px;">
<span class="glyphicon glyphicon glyphicon-off log_out1" aria-hidden="true" style="    font-size: 24px;    color: #fff;  background: #095E73;    margin-top: 20px;    padding: 4px 3px;box-shadow: 3px 2px 2px #212222;"></span>
<a href="javascript:void(0);" class="resp-add" style="  float: right;text-decoration: none;width: 130px;">
             
                    
                     	<img src="/trux/resources/images/profile.png" alt="" style=" width: 42px; margin: 0 auto; margin-left: 24px">
                     	<br>
                     	 
        
                     	 <b style="color: #fff;font-size: 17px;float: left;margin-top: 0px;z-index: 9999; position: absolute; opacity: 0.8; text-align: center;    margin-top: -6px; width: 100px;"><%=userDetail.getFirstname() %>  &nbsp;&nbsp;  </b> 
                    </a>
                   
                </div>
                 <form action="/trux/admin/register/home" method="post" id="form">
                     	        <input type="hidden" name="userName" value="<%=userDetail.getFirstname()%>">
                                <input type="hidden" name="userPassword" value="<%=userDetail.getPassword()%>">
                                </form>
                
 <div class="top_right">   
  
            
  </div>
  </div>
  
             
            </div>
</div>
<div class="container">  
	<div class="row">
	
	
    	<div class="col-lg-12 col-md-12 col-sm-12">  
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
		</div>
		 
		<div class="col-lg-12 col-md-12 col-sm-12" style="margin-buttom:20px;" >  			
			<tiles:insertAttribute name="body"></tiles:insertAttribute>
		</div>	
		<%}else{ %>
		<div class="col-lg-12 col-md-12 col-sm-12" style="margin-buttom:20px;" >  			
			<tiles:insertAttribute name="message"></tiles:insertAttribute>
		</div>	
		<%} %>
	</div>
</div>
<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</header>
<script type="text/javascript">
//Create a clone of the menu, right next to original.
$('.menu').addClass('original').clone().insertAfter('.menu').addClass('cloned').css('position','fixed').css('top','0').css('margin-top','0').css('z-index','500').removeClass('original').hide();

scrollIntervalID = setInterval(stickIt, 10);


function stickIt() {

  var orgElementPos = $('.original').offset();
  orgElementTop = orgElementPos.top;               

  if ($(window).scrollTop() >= (orgElementTop)) {
    // scrolled past the original position; now only show the cloned, sticky element.

    // Cloned element should always have same left position and width as original element.     
    orgElement = $('.original');
    coordsOrgElement = orgElement.offset();
    leftOrgElement = coordsOrgElement.left;  
    widthOrgElement = orgElement.css('width');
    $('.cloned').css('left',leftOrgElement+'px').css('top',0).css('width',widthOrgElement).show();
    $('.original').css('visibility','hidden');
  } else {
    // not scrolled past the menu; only show the original menu.
    $('.cloned').hide();
    $('.original').css('visibility','visible');
  }
}
</script>
</div>
</body>
</html>