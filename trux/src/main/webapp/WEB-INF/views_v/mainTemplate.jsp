
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- favicon links -->
<link rel="shortcut icon" type="image/ico" href="/trux/resources/images/favicon.ico" />
<!-- main css -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- All CSS Styles -->
<link type="text/css" href="/trux/resources/css/bootstrap.css" rel="stylesheet" media="screen" />
<link type="text/css" href="/trux/resources/css/animate.css" rel="stylesheet" media="screen" />
<link type="text/css" href="/trux/resources/css/swiper.css" rel="stylesheet" media="screen" />
<link type="text/css" href="/trux/resources/css/owl.carousel.css" rel="stylesheet" media="screen" />
<link type="text/css" href="/trux/resources/css/owl.theme.css" rel="stylesheet" media="screen" /> 
<link type="text/css" href="/trux/resources/css/style.css" rel="stylesheet" media="screen" />  	
<link type="text/css" href="/trux/resources/css/font-awesome.min.css" rel="stylesheet" media="screen" />
<!--<link type="text/css" href="fonts/stylesheet.css" rel="stylesheet" media="screen" />-->
<!-- Different Styles --> 
<!-- Media Queries -->
<link type="text/css" href="/trux/resources/css/media.css" rel="stylesheet" media="screen" />
<!-- Modernizr -->
<script type="text/javascript" src="/trux/resources/js/modernizr.js"></script> 
<script type="text/javascript">
$(document).ready(function(){	
	// "scrollTop" plugin
	$.scrollUp();
});
</script>
<title>
	<tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute>
</title>
</head>
<body>
<div style="width:100%; float:left; margin:auto;">
<header id="header" class="gradient4Color">	
<div class="container">  
	<div class="row">
    	<div class="col-lg-12 col-md-12 col-sm-12">  
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12" style="margin-buttom:20px;" >  			
			<tiles:insertAttribute name="body"></tiles:insertAttribute>			
		</div>
	</div>
</div>
<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</header>
</div>
</body>
</html>