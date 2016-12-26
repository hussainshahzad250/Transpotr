 
<%@page import="java.util.*"%>
 
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%> 
<html lang="en">
<head>
<link href="/bonton/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/bonton/resources/css/style.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="/bonton/resources/css/font-awesome.min.css" rel="stylesheet">
<link href="/bonton/resources/css/responsive-tables.css"
	rel="stylesheet">

<link type="text/css" rel="stylesheet"
	href="/bonton/resources/css/shCoreEclipse.css" media="screen" />
<link type="text/css" rel="stylesheet"
	href="/bonton/resources/css/jquery-ui.min.css" media="screen" />
<link type="text/css" rel="stylesheet"
	href="/bonton/resources/css/jquery.ui.plupload.css" media="screen" />

<script src="/bonton/resources/js/jquery-1.11.1.min.js"></script>
<script src="/bonton/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/bonton/resources/js/jquery-ui.min.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="/bonton/resources/js/plupload.full.min.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="/bonton/resources/js/jquery.ui.plupload.min.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="/bonton/resources/js/customejs/AllhotelpageValidation.js"></script>
<script type="text/javascript"
	src="/bonton/resources/js/customejs/newroom.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<style type="text/css">
label.error {
	color: red;
	font-weight: normal;
}
</style>
<script type="text/javascript">



function checkFile(fieldObj) {
    var flag=false;
	$("#image_error").hide();
	var FileName = fieldObj.value;
	if (FileName == '') {
		return false;
	}
	var FileExt = FileName.substr(FileName.lastIndexOf('.') + 1);
	var FileSize = fieldObj.files[0].size;
	var FileSizeMB = (FileSize / 10485760).toFixed(2);
	 
	if ((FileExt != "jpg" && FileExt != "jpeg" && FileExt != "gif" && FileExt != "png") || FileSize > 2*1024*1024) {
		$("#image_error").show();
		flag= false;
	}else{
		flag= true;
	}
	 
    if(flag==false){
    	 alert("Too large Image. Only image smaller than 2MB can be uploaded & jpg,jpeg,gif,png extention file accepted.");
    }else{
	uploadJqueryForm();}
}

var indexGlobalVal=1;
function getId(idVal){
 indexGlobalVal=idVal;
    $("#imageId").val(idVal);
	$("#amenitiesID").val(idVal);
	$("#descriptionID").val(idVal);
	
} 
 
var amenitiesDetailsAllD = [];
var descriptionArrayD=[];
function amenitiesVal(id){
	 var amenitiesDetailsD = [];
	 var amenitiesv = $('#Amenities').find('input');
	 $.each($("input[name='roomAmenities"+id+"']:checked"), function(){ 
		 amenitiesDetailsD.push($(this).val());             
	 });
	 if(amenitiesv.is(':checked')){
		amenitiesDetailsD.push($("#Amenities").val()); 
	 } 
	 amenitiesDetailsAllD.push("{"+amenitiesDetailsD+"}")
	  $("#roomAmenitiesVal").val(amenitiesDetailsAllD);
	 var valIndex=id;//$("#amenitiesID").val();
		if(valIndex==''){$("#amenitiesVAL1").val(amenitiesDetailsD);}else{		
			$("#amenitiesVAL"+valIndex).val(amenitiesDetailsD);
		} 
	 
	  
}
var descriptionArray = [];
function descriptionDetails(id){
	var addressArea= $("#address"+id).val();
	
	if(addressArea==""){
		addressArea="-";
	}
	 var valIndex=id;
	if(valIndex==''){$("#descriptionVAL1").val(addressArea);}else{		
		$("#descriptionVAL"+valIndex).val(addressArea);
	} 
}
/*
function amenitiesD(){ 
	flag=formvalidationRoom();
	var rowCount = $('#mytbody').children().length;
	alert("JFJ " +rowCount);
var checker=false;
var flag=true;
 
 for( i=0;i<rowCount;i++){
	 checker=formvalidationRoom();
	 alert(checker);
	   if (checker==false){ 
		   flag=false;
		   break;
		 }
	   if (checker==true){ 
		   flag=true;	
		   
		} 
	} 
if(flag==false){
	return false;
}else{
	return true;
}
}
*/

 
</script>

<script type="text/javascript">
function hideImageUpload()
{var imageUrlVal=$("#imageuploadFile1").val();
if(imageUrlVal==""){
	imageUrlVal="*";
}else{
	$("#hotel_image").html("");
	$("#blah").attr("src","");
}
	
}

function addtextBoxType(eleID){
	
		var x = $("#ChildPolicy"+eleID).val();
		if(x=="otherType"){
			$('#divTypeOther'+eleID).show();
		}else{  
			$('#divTypeOther'+eleID).hide();
		}
	}
function myFunction(sel,elementId){
			var dat='';
			var val=sel.value;
			var vale=parseInt(val);
			 
		    for(var l=0;l<sel.value;l++){
		    //dat+='<div class="col-lg-12 col-md-12 col-sm-12" style="margin-left:-20px;"><div style="margin-bottom:6px; width:127px;">Under(In Age)</div><select name="childpolicyunderage" id="childpolicyunderage_'+elementId+"_"+l+'"  onChange="childPolicyUnderAge("'+vale+'","'+elementId+"_"+l+'")" class="form-control input-lg"  style="width:73px;"><option value="">Select</option><option value="01">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option><option value="05">05</option><option value="06">06</option><option value="07">07</option><option value="08">08</option><option value="09">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option></select><br/><span id="childpolicyunderage_error_'+elementId+"_"+l+'" style="color:red;display:none; text-align:left;">Under(In Age)</span></div><div class="clearfix margin_10"></div>';
			dat+='<div class="col-lg-12 col-md-12 col-sm-12" style="margin-left:-20px;"><div style="margin-bottom:6px; width:127px;">Under Age 2</div><input type="text" name="childpolicyunderage'+elementId+'" id="childpolicyunderage_'+elementId+"_"+l+'"  onChange="childPolicyUnderAge("'+vale+'","'+elementId+"_"+l+'")" class="form-control input-lg"  style="width:73px;"><br/><span id="childpolicyunderage_error_'+elementId+"_"+l+'" style="color:red;display:none; text-align:left;">Under Age 2</span></div><div class="clearfix margin_10"></div>';
		     }
			$("#underage"+elementId).html(dat);
    }
    /********remove Image****************/
function removeImage(imageurl){
    var uploadedImg=$("#imageuploadFile1").val();
    var res = uploadedImg.replace(imageurl, "");
    var firstchar = res.charAt(0);
    var lastchar = res.charAt(res.length-1);
    if(firstchar=="#"){        
        res = res.replace(firstchar, "");
    }
    if(lastchar=="#"){        
        res = res.replace(lastchar, "");
    }
    var last12char = imageurl.substr(imageurl.length - 12); 
    $("#imageuploadFile").val(res);
    $("#"+last12char).hide();
    
    
}
function removeImageAfterUpload(imageurl,id){
	 var valIndex=id; 
	var uploadedImg= $("#imageUrlPath"+valIndex).val();		
    var res = uploadedImg.replace(imageurl, "");
    var firstchar = res.charAt(0);
    var lastchar = res.charAt(res.length-1);
    /* if(firstchar=="#"){        
        res = res.replace(firstchar, "");
    }
    if(lastchar=="#"){        
        res = res.replace(lastchar, "");
    } */
    var last12char = imageurl.substr(imageurl.length - 12); 
    if(res!=""){
    $("#imageUrlPath"+valIndex).val(res); }
    $("#imageuploadFile").val(res);
    $("#"+last12char).hide();
    
    
}
var immageArray =[];

var uploadImageVal=[];
function imageUploadVAl(){ 
	var imageUrlVal=$("#imageuploadFile1").val();
	var fileval=$("#file").val();	
	var valIndex=indexGlobalVal;//$("#imageId").val();
	if(imageUrlVal==""){
		imageUrlVal="{}";
	}else{
	if(fileval!=""){
	var repviousVal=$("#imageUrlPath"+valIndex).val();
	if(repviousVal!=""){
		immageArray.push(repviousVal);
	}
	var imageUrlValArray=[];	 
	imageUrlValArray.push(imageUrlVal);	
	uploadImageVal.push("["+immageArray+"]");
	if(valIndex==''){$("#imageUrlPath1").val(immageArray);
	}else{		
		$("#imageUrlPath"+valIndex).val(immageArray);
		$("#imageuploadFile").val(uploadImageVal);
		immageArray=[];
	}
	}	
	}
	}

function uploadJqueryForm(){
	indexGlobalVal;
var immageArrayInner =[];
$("#black_overlay").fadeIn();
$("#loader_img").fadeIn();

$("#imageuploadform1").ajaxForm({ 	
data: $("#imageuploadform1").serialize(),
success: function(data) { 
  
var last12char = data.substr(data.length - 12); 
$("#hotel_image").append('<div class="col-lg-2 col-md-2 col-sm-12"  id="'+last12char+'"><div style="margin-bottom:6px;"><img src="'+data+'" style="width:100%;height:50px;"><div><a href="javascript:void(0)" onclick="return removeImage(\''+data+'\')">Remove</a></div></div></div>');
var fileName=$("#imageuploadFileHolder").val();
if(fileName!=''){
fileName=fileName+"#"+data;
}else{
fileName=data;
}
immageArray.push(fileName);
$("#imageuploadFileHolder").val(fileName);
//alert(immageArrayInner);
$("#imageuploadFile1").val($("#imageuploadFileHolder").val());
$("#imageuploadFileHolder").val("");
//alert($("#imageuploadFile").val());
$("#loader_img").fadeOut("slow");
  $("#black_overlay").fadeOut("slow");
  $("#loader_img").html('<div style="position:absolute;">Please wait <img src="/bonton/resources/img/loader.gif" style="padding-left: 10px;"/></div>');
} 
}).submit();
immageArray.push("{"+immageArrayInner+"}");
$("#imageuploadFile1").val(immageArray);
 
}


$("#propertyimages").change(function(){
readURL(this);
})
function readURL(input) {

if (input.files && input.files[0]) {
var reader = new FileReader();
reader.onload = function (e) {
$('#blah').attr('src', e.target.result);
$("#imageUrl").val(e.target.result);
//alert(e.target.result);
}

reader.readAsDataURL(input.files[0]);
}
}



</script>
</head>
<body>
	<div class="wrapper">
		<!-- sidebar-right -->
		<header class="top-nav">
			<input type="hidden" name="hotelId"
				value="<%=request.getAttribute("hotelId")%>">
			<div class="top-nav-inner">
				<div class="nav-header">
					<button type="button"
						class="navbar-toggle pull-left sidebar-toggle"
						id="sidebarToggleSM">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<ul class="nav-notification pull-right">
						<li><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><i class="fa fa-cog fa-lg"></i></a> <span
							class="badge badge-danger bounceIn">1</span>
							<ul class="dropdown-menu dropdown-sm pull-right">
								<li class="user-avatar"><img src="images/profile1.jpg"
									alt="" class="img-circle">
									<div class="user-content">
										<h5 class="no-m-bottom">User</h5>
										<div class="m-top-xs">
											<a href="javascript:void(0);" class="m-right-sm">Profile</a>
											<a href="/bonton/logout">Log out</a>
										</div>
									</div></li>
							</ul></li>
					</ul>
					<a href="/bonton/home" class="brand"><img
						src="/bonton/resources/images/logo.png" style="width: 50%;"></a>
				</div>

			</div>
			<!-- ./top-nav-inner -->
		</header>
		<aside class="sidebar-menu fixed">
			<div class="sidebar-inner scrollable-sidebar">
				<div class="main-menu">
					<ul class="accordion">
						<li class="menu-header">Main Menu</li>
						<li class="bg-palette1"><a href="/bonton/home"> <span
								class="menu-content block"> <span class="menu-icon"><i
										class="block fa fa-home fa-lg"></i></span> <span
									class="text m-left-sm">Dashboard</span>
							</span> <span class="menu-content-hover block"> Home </span>
						</a></li>
						<li class="bg-palette2 active"><a href="#"> <span
								class="menu-content block"> <span class="menu-icon"><i
										class="block fa fa-users fa-lg"></i></span> <span
									class="text m-left-sm">Property Managment</span>
							</span> <span class="menu-content-hover block"> Property
									Managment </span>
						</a></li>

						<li class="openable bg-palette4"><a
							href="54.169.73.105/bontonMaster/"> <span
								class="menu-content block"> <span class="menu-icon"><i
										class="block fa fa-tags fa-lg"></i></span> <span
									class="text m-left-sm">Manage Master</span> <span
									class="submenu-icon"></span>
							</span>
						</a></li>
						<!-- <li class="bg-palette4">
                        <a href="forgot_password.html">
                            <span class="menu-content block">
                                <span class="menu-icon"><i class="block fa fa-list fa-l fa-lg"></i></span> <span class="text m-left-sm">Forgot Password</span>
                            </span>
                            <span class="menu-content-hover block">
                                Forgot Password
                            </span>
                        </a>
                    </li> -->
						<li class="bg-palette2"><a href="/bonton/logout"> <span
								class="menu-content block"> <span class="menu-icon"><i
										class="block fa fa-power-off fa-l fa-lg"></i></span> <span
									class="text m-left-sm">Sign out</span>
							</span> <span class="menu-content-hover block"> Sign out </span>
						</a></li>
					</ul>
				</div>
				<div class="sidebar-fix-bottom clearfix">
					<div class="user-dropdown dropup pull-left">
						<a href="#" class="dropdwon-toggle font-18" data-toggle="dropdown"><i
							class="ion-person-add"></i> </a>
						<ul class="dropdown-menu">
							<li><a href="inbox.html">Inbox<span
									class="badge badge-danger bounceIn animation-delay2 pull-right">1</span></a></li>
							<li><a href="#">Notification<span
									class="badge badge-purple bounceIn animation-delay3 pull-right">2</span></a></li>
							<li><a href="#" class="sidebarRight-toggle">Message<span
									class="badge badge-success bounceIn animation-delay4 pull-right">7</span></a></li>
							<li class="divider"></li>
							<li><a href="#">Setting</a></li>
						</ul>
					</div>
					<a href="signin.html" class="pull-right font-18"><i
						class="ion-log-out"></i></a>
				</div>
			</div>
		</aside>

		<div class="main-container">
			<div class="padding-md">
				<h3 class="header-text m-bottom-md">Property Managment</h3>
				<p style="color: red;"><%=request.getAttribute("error") == null ? "" :"Enter valid value"%>
				</p>

				<div class="row user-profile-wrapper">
					<!-- ./col -->
					<div class="col-md-12">
						<div class="smart-widget">
							<div class="smart-widget-inner">
								<ul class="nav nav-tabs tab-style2 tab-left bg-grey">
									<li><a href="#"> <span class="icon-wrapper"><i
												class="fa fa-home"></i></span> <span class="text-wrapper"><strong>Hotels</strong></span>
									</a></li>
									<li class="active"><a href="#"> <span
											class="icon-wrapper"><i class="fa fa-home"></i></span> <span
											class="text-wrapper"><strong>Rooms</strong></span>
									</a></li>
									<li><a href="#"> <span class="icon-wrapper"><i
												class="fa fa-rupee"></i></span> <span class="text-wrapper"><strong>Rates
													& Plan</strong></span>
									</a></li>

								</ul>
								<div class="smart-widget-body">
									<div class="tab-content">
										<div class="tab-pane fade in active"> 
											 

											<form class="frmRoom" id="basic-constraint"
												action="/bonton/upload/uploadRoom" method="POST"
												enctype="multipart/form-data">
												<div class="clearfix margin_10"></div>
												<div class="con">
													<div class="row">
														<div class="col-lg-12 col-md-12 col-sm-12">
															<div
																style="width: 1024px; overflow-y: hidden; overflow-x: scroll;">
																<table id="POITable"
																	class="table table-striped table-bordered bootstrap-datatable datatable responsive"
																	style="font-size: 12px;">
																	<thead>
																		<tr style="background-color: #cccccc;">
																			<th><div style="width: 20px;">Sr. No.</div></th>
																			<th><div style="width: 100px;">
																					Room Category<span style="color: #FF0000;">*</span>
																				</div></th>
																			<th><div style="width: 100px;">Rooms Name</div></th>
																			<th><div style="width: 100px;">
																					No. of Rooms<span style="color: #FF0000;"></span>
																				</div></th>
																			<th><div style="width: 100px;">
																					Allocation<span style="color: #FF0000;"></span>
																				</div></th>
																			<th><div style="width: 200px;">Child
																					Policy</div></th>
																			<th><div style="width: 80px;">
																					Max packs<span style="color: #FF0000;">*</span>
																				</div></th>
																			<th><div>
																					Occupancy<span style="color: #FF0000;">*</span>
																				</div></th>
																			<th><div style="width: 150px;">
																					Bed Config<span style="color: #FF0000;">*</span>
																				</div></th>
																			<th><div>Amenities</div></th>
																			<th><div>Description</div></th>
																			<th><div>Room images</div></th>
																			<th colspan="2"><div>Action</div></th>
																		</tr>
																	</thead>
																	<tbody id="mytbody">
																		<tr>
																			<td class="center" style="width: 11%;">1</td>
																			<td class="center" style="width: 17%;"><select
																				name="roomCat1" id="RoomCat1" onchange="checkDuplicasyOfRoom('1');"
																				class="selectcheck form-control input-lg"
																				style="width: 100%;">
																					<option value="">Select Room Category</option>
																				 
																			</select> <span id="RoomCat_error1"
																				style="color: red; display: none; text-align: left;">Select
																					Room Category</span></td>
																			<td class="center" style="width: 11%;"><input
																				type='text' id="RoomsName1" name="roomsName"
																				class="stringType form-control input-lg"
																				style="width: 100%;" placeholder="Rooms Name">
																				<span id="RoomsName_error1"
																				style="color: red; display: none; text-align: left;">only
																					alphabet</span></td>
																			<td class="center"><input type='text'
																				id="NoRooms1" name="noRooms1" maxlength=2
																				class="numberOnly form-control input-lg"
																				onkeyup="isNumberKey(event,'NoRooms1');"
																				style="width: 100%;" placeholder="No. of Rooms">
																				<span id="NoRooms_error1"
																				style="color: red; display: none; text-align: left;">only
																					numeric </span></td>
																			<td class="center"><input type='text'
																				id="Allocation1" name="allocation1" maxlength=3
																				class="numberOnly form-control input-lg"
																				onkeyup="isNumberKey(event,'Allocation1');"
																				style="width: 100%;" placeholder="Allocation">
																				<span id="Allocation_error1"
																				style="color: red; display: none; text-align: left;">only
																					numeric </span></td>
																			<td><select id="ChildPolicy1"
																				name="ChildPolicy1" onChange="addtextBoxType(1)"
																				class="form-control input-lg" style="width: 100%;">
																					<option value="">Select</option>
																					<option value="otherType">Allowed in Room</option>
																			</select>
																				<div class="clearfix margin_10"></div>
																				<div id="divTypeOther1" style="display: none;">
																					<div class="col-md-6">
																						<div style="margin-bottom: 6px;">Under Age 1</div>
																						<input type="text" name="childpolicycat"
																							id="childpolicycat1"
																							onkeyup="isNumberKey(event,'childpolicycat1');"
																							maxlength=2 class="form-control input-lg"
																							style="width: 100%;"> <span
																							id="ChildPolicy_error1"
																							style="color: red; display: none;; text-align: left;">Enter
																							under age 1</span>
																					</div>
																					<div class="col-md-6">
																						<div style="margin-bottom: 6px;">Under Age 2</div>
																						<input type="text" name="childpolicyunderage"
																							onkeyup="isNumberKey(event,'childpolicyunderage_1_1');"
																							id="childpolicyunderage_1_1" maxlength=2
																							class="form-control input-lg"
																							style="width: 100%;"> <span
																							id="childpolicyunderage_error_1_1"
																							style="color: red; display: none; text-align: left;">Enter
																							under age 2</span>
																					</div>
																					<div id="underage1"
																						class="col-lg-6 col-md-6 col-sm-12 pull-right"></div>
																				</div></td>

																			<td class="center"><input type='text'
																				onkeyup="isNumberKey(event,'MaxPack1');"
																				id="MaxPack1" name="maxPack1" maxlength=4
																				class="numberOnly form-control input-lg"
																				style="width: 100%;" placeholder="Max packs">
																				<span id="MaxAdults_error1"
																				style="color: red; display: none; text-align: left;">only
																					numeric</span></td>

																			<td class="center"><select name="occupancy"
																				id="occupancy1"
																				class="selectcheck form-control input-lg"
																				onchange="occupancyVAl(1);" style="width: 100%;">
																					<option value="1">Single</option>
																					<option value="2">Double</option>
																					<option value="3">Triple</option>
																					<option value="4">Quard</option>
																			</select> <span id="occupancy_error1"
																				style="color: red; display: none; text-align: left;">Select
																					Occupancy</span></td>

																			<td class="center" style="width: 14%;"><select
																				name="roomBedding1" id="roomBedding1"
																				class="selectcheck form-control input-lg"
																				style="width: 100%;">
																					<option value="">Select Configuration</option>

																					 
																			</select> <span id="roomBedding_error1"
																				style="color: red; display: none; text-align: left;">Select
																					Bed Config</span></td>
																			<td class="center"><div
																					style="margin-top: 10px;">
																					<a href="" data-toggle="modal"
																						data-target="#smallModalFr1"
																						class="1 btn btn-info" onclick="getId(1);">Amenities<input
																						type="hidden" name="amenitiesVAL"
																						id="amenitiesVAL1"><input type="hidden"
																						name="amenitiesID" id="amenitiesID" value="1"></a>
																				</div>
																				<span id="Amenities_error1"
																				style="color: red; display: none; text-align: left;">required</span></td>
																			<td class="center"><a href=""
																				data-toggle="modal" data-target="#smallModalDs1"
																				onclick="getId(1);"><i class="fa fa-edit"
																					style="font-size: 30px; margin-top: 10px;"><input
																						type="hidden" name="descriptionVAL"
																						id="descriptionVAL1"><input type="hidden"
																						name="descriptionID" id="descriptionID" value="1"></i></a></td>
																			<td class="center"><a href=""
																				data-toggle="modal" data-target="#smallModal2"
																				onclick="getId(1); addImage(1);"><input
																					type="hidden" name="imageUrlPath"
																					id="imageUrlPath1"><input type="hidden"
																					name="imageId" id="imageId" value="1"><i
																					class="fa fa-picture-o"
																					style="font-size: 30px; margin-top: 10px;"></i></a></td>

																			<td>&nbsp;</td>
																		</tr>
																	</tbody>
																</table>
																<div>
																	<div style="color: red;" id="message"></div>
																	<div style="margin-bottom: 4px;">
																		<button type="button" class='delete btn2 btn-danger'>
																			<i class=" fa fa-times-circle"
																				style="font-size: 18px;"></i>
																		</button>
																		<button type="button" class='addmore btn2 btn-danger'
																			id="amenitiesButtonCall">
																			<i class="fa fa-plus" style="font-size: 18px;"></i>
																		</button>
																	</div>
																</div>
															</div>
														</div>
													</div>

												</div>
												<div class="clearfix margin_10"></div>

												<!--///////////////////Room Amenities popup start//////////////////////////////-->
												<%
													for( int ii=1;ii<40;ii++){
												%>
												<div class="modal fade" id="smallModalFr<%=ii%>">
													<div class="modal-dialog modal-content">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal">
																	<span aria-hidden="true">&times;</span><span
																		class="sr-only">Close</span>
																</button>
																<h4 class="modal-title">Room Amenities</h4>
															</div>
															< 
															<div class="modal-footer">
																<button type="button" id="btnSaveAmenities"
																	class="btn btn-primary" data-dismiss="modal"
																	onclick="amenitiesVal(<%=ii%>);">Save</button>
															</div>
														</div>
													</div>
												</div>
												<%
													}
												%>
												<!--///////////////////////////number1//end////////////////////////////////////-->
												<%
													for(int ds=1;ds<40;ds++) {
												%>
												<div class="modal fade" id="smallModalDs<%=ds%>">
													<div class="modal-dialog modal-content">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal">
																	<span aria-hidden="true">&times;</span><span
																		class="sr-only">Close</span>
																</button>
																<h4 class="modal-title">Description</h4>
															</div>
															<div class="modal-body">
																<textarea name="address<%=ds%>" id="address<%=ds%>"
																	cols="" rows="5" class="form-control input-lg"
																	style="width: 100%;"></textarea>
															</div>
															<div class="modal-footer">
																<button type="button" id="btnSaveDescription"
																	class="btn btn-primary" data-dismiss="modal"
																	onclick="descriptionDetails(<%=ds%>)">Save
																	changes</button>
															</div>
														</div>
													</div>
												</div>
												<%
													}
												%>
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12">
														<div
															style="margin-top: 25px; font-size: 14px; text-align: left; margin-left: 0px;"
															class=" navbar-right">

															<input type="hidden" name="descriptionVal"
																id="descriptionVal"> <input type="hidden"
																name="roomAmenitiesVal" id="roomAmenitiesVal"> <input
																type="hidden" name="occupancyVal" id="occupancyVal">
															<input type="hidden" name="childpolicyunderageVal"
																id="childpolicyunderageVal"> <input
																type="hidden" name="imageuploadFile"
																id="imageuploadFile">

															<button type="submit" id="" name="" class="btn btn-info"
																onClick="return formvalidationRoom();">Save &
																Next</button>
														</div>
													</div>
													<div class="clearfix margin_10"></div>
												</div>

												<div class="row">
													<div class="col-lg-12"></div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<footer class="footer">
			<p class="no-margin">
				&copy; 2015 <strong>Bonton Admin</strong>. ALL Rights Reserved.
			</p>
		</footer>
	</div>
	<a href="#" class="scroll-to-top hidden-print"><i
		class="fa fa-chevron-up fa-lg"></i></a>

	<script>
$(".delete").on('click', function() {
$('.case:checkbox:checked').parents("tr").remove();

});
var i=1;
$(".addmore").on('click',function(){
i= $('#mytbody').children().length;
i++; 	
var data="<tr><td>"+i+"</td>";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                //childpolicyunderage_error_'+elementId+"_"+l+'"
data +="<td class='center' style='width:120px;'><select name='roomCat1' id='RoomCat"+i+"' onchange='checkDuplicasyOfRoom("+i+");' class='selectcheck form-control input-lg'  style='width:100%;' ></select><span id='RoomCat_error"+i+"' style='color:red;display:none; text-align:left;'>Select Room Category</span></td><td><input type='text'  id='RoomsName"+i+"' name='roomsName'  class='stringType form-control input-lg' style='width:100%;' placeholder='Rooms Name' ><span id='RoomsName_error"+i+"' style='color:red; display:none;text-align:left;'>only alphabet</span></td><td><input type='text' maxlength=2 id='NoRooms"+i+"'  onkeyup=\"isNumberKey(event,'NoRooms"+i+"');\" name='noRooms1'  class='stringType form-control input-lg' style='width:100%;' placeholder='No. of Rooms' ><span id='NoRooms_error"+i+"' style='color:red;display:none; text-align:left;'>only numeric</span></td><td><input type='text' maxlength=3 id='Allocation"+i+"'  onkeyup=\"isNumberKey(event,'Allocation"+i+"');\" name='allocation1'  class='stringType form-control input-lg' style='width:100%;' placeholder='Allocation' ><span id='Allocation_error"+i+"' style='color:red;display:none; text-align:left;'>only numeric</span></td><td><select  id='ChildPolicy"+i+"' name='ChildPolicy1'  onChange='addtextBoxType("+i+")'class='form-control input-lg' style='width:100%;'><option value=''>Select</option><option value='otherType'>Allowed in Room</option></select><div class='clearfix margin_10'></div><div id='divTypeOther"+i+"' style='display:none;'><div class='col-md-6'><div style='margin-bottom:6px;'>Under Age 1</div><input type='text' name='childpolicycat' id='childpolicycat"+i+"' onkeyup=\"isNumberKey(event,'childpolicycat"+i+"');\" maxlength=2  class='form-control input-lg'  style='width:100%;'> </div><div class='col-md-6'><div id='underage"+i+"'></div></div></br><span id='ChildPolicy_error"+i+"' style='color:red;display:none; text-align:left;'>Please enter under age 1</span><div class='col-md-6'> <div style='margin-bottom:6px;'>Under Age 2</div> <input type='text'  onkeyup=\"isNumberKey(event,'childpolicyunderage_"+i+"_1');\" name='childpolicyunderage' id='childpolicyunderage_"+i+"_1' maxlength=2 class='form-control input-lg'  style='width:100%;'> <span id='childpolicyunderage_error_"+i+"_1' style='color:red;display:none; text-align:left;'>Enter under age 2</span> </div></td><td><input type='text' maxlength=4 id='MaxPack"+i+"'  onkeyup=\"isNumberKey(event,'MaxPack"+i+"');\" name='maxPack1'  class='stringType form-control input-lg' style='width:100%;' placeholder='Max Pack' ><span id='MaxAdults_error"+i+"' style='color:red;display:none; text-align:left;'>only numeric</span></td><td><select name='occupancy' id='occupancy"+i+"' onchange='occupancyVAl("+i+");' class='form-control input-lg'  style='width:100%;'><option value='1'>Single</option><option value='2'>Double</option><option value='3'>Triple</option><option value='4'>Quard</option></select><span id='occupancy_error"+i+"' style='color:red;display:none; text-align:left;'>required</span></td><td><select name='roomBedding1' id='roomBedding"+i+"' class='selectcheck form-control input-lg'  style='width:100%;'></select><span id='roomBedding_error"+i+"' style='color:red;display:none; text-align:left;'>Select Bed Config</span></td><td class='center'><div style='margin-top:10px;'><a href='' data-toggle='modal' data-target='#smallModalFr"+i+"' class='1 btn btn-info' onClick='getId("+i+");'>Amenities<input type='hidden' name='amenitiesVAL' id='amenitiesVAL"+i+"'><input type='hidden' name='amenitiesID' id='amenitiesID"+i+"' value="+i+"> </a></div></td><td class='center'><a href='' data-toggle='modal' data-target='#smallModalDs"+i+"' onClick='getId("+i+");'><input type='hidden' name='descriptionVAL' id='descriptionVAL"+i+"'><input type='hidden' name='descriptionID' id='descriptionID"+i+"' value="+i+"> <i class='fa fa-edit' style='font-size:30px; margin-top:10px;'></i></a></td><td class='center'><a href='' data-toggle='modal' data-target='#smallModal2' onClick='getId("+i+"); addImage("+i+");'><input type='hidden' name='imageUrlPath' id='imageUrlPath"+i+"'><input type='hidden' name='imageId' id='imageId' value="+i+"><i class='fa fa-picture-o' style='font-size:30px; margin-top:10px;'></i></a></td><td colspan='2'><input type='checkbox' name=ch[] class='case form-control'/></td></tr>";
$('table').append(data);
$("#RoomCat"+i+"").append($("#RoomCat1").html());
$("#roomBedding"+i+"").append($("#roomBedding1").html());
$("#noofboxes").val(i);
 
});


function addImage(id){
 var valIndex=id; 
 if(valIndex==''){}else{
	var imagePath= $("#imageUrlPath"+valIndex).val();
	$("#imageUrlPath"+valIndex).val(imagePath);
	var value=imagePath.replace('{},',''); 
	var value1=value.replace(',{},{},',',');
	var value2=value1.replace('{}',','); 
	var value3=value2.replace(',{},',','); 
	var arrayImage=value3.split(","); 
	 if(arrayImage.length!=0){
		for(i=0;i<arrayImage.length;i++){
			id++;
			if(arrayImage[i]!=""){
				if(arrayImage[i]!="{}"){
				var url=arrayImage[i];
				var last12char = url.substr(url.length - 12); 
				$("#hotel_image").append('<div class="col-lg-2 col-md-2 col-sm-12"  id="'+last12char+'"><div style="margin-bottom:6px;"><img src="'+url+'" style="width:100%;height:50px;"><div><a href="javascript:void(0)" onclick="return removeImageAfterUpload(\''+url+'\','+valIndex+')">Remove</a></div></div></div>');
			 	}
				}
		} 
	}
	}
 }
 
 
function checkDuplicasyOfRoom(id)
{
	
var selects= '<option value="">Select Room Category</option><option value="Cottage">Cottage</option><option value="Deluxe">Deluxe</option><option value="Diplomat Room">Diplomat Room</option><option value="Economy Room">Economy Room</option><option value="Executive">Executive</option><option value="Executive Suite">Executive Suite</option><option value="Family Room">Family Room</option><option value="Premium">Premium</option><option value="Presidential Suite">Presidential Suite</option><option value="Standard">Standard</option><option value="Suite">Suite</option><option value="Studio">Studio</option><option value="Super Deluxe">Super Deluxe</option><option value="Superior Room">Superior Room</option><option value="Tent">Tent</option><option value="Villa">Villa</option><option value="1 BHK">1 BHK</option><option value="2 BHK">2 BHK</option><option value="3 BHK">3 BHK</option><option value="4 BHK">4 BHK</option><option value="testrooms">testrooms</option><option value="5 bhk ">5 bhk </option><option value="Ocean View Suite">Ocean View Suite</option><option value="test bhk1">test bhk1</option><option value="test bhk2">test bhk2</option><option value="Garden Villa">Garden Villa</option><option value="Vintage Room">Vintage Room</option><option value="Royal Room">Royal Room</option><option value="Royal Suite">Royal Suite</option><option value="Imperial Suite">Imperial Suite</option><option value="Tree Top Mansion">Tree Top Mansion</option><option value="Home Stay">Home Stay</option><option value="Luxury Room">Luxury Room</option><option value="Club Room">Club Room</option><option value="Deluxre Suite">Deluxre Suite</option><option value="Comfort Room">Comfort Room</option><option value="Lake View Room">Lake View Room</option><option value="Mountain View Room">Mountain View Room</option><option value="Grace Suite">Grace Suite</option><option value="Valley View">Valley View</option><option value="Tree Top Cottage">Tree Top Cottage</option><option value="Chalet Room">Chalet Room</option><option value="Chalet Deluxe Room">Chalet Deluxe Room</option><option value="Jungle Lodge">Jungle Lodge</option><option value="Jungle Deluxe Lodge">Jungle Deluxe Lodge</option>';
            
var	idexs= $("#mytbody").children().length;
var innerflag=false;
for(var i=1;i<idexs;i++){
if(id!=$.trim(i)){
var existRoomTypeWithinIndex=$("#basic-constraint").find("#RoomCat"+i).val();
var roomType=$("#basic-constraint").find("#RoomCat"+id).val();
if($.trim(existRoomTypeWithinIndex)==$.trim(roomType)){		
	innerflag=false;		
	if(innerflag==false){ 
	alert("\""+roomType +"\" Room category is already exist !");
	$("#basic-constraint").find("#RoomCat"+id).html(selects);
	}  
	}
}}
for(var i=idexs;i>1;i--){
	if(id!=$.trim(i)){
	var existRoomTypeWithinIndex=$("#basic-constraint").find("#RoomCat"+i).val();
	var roomType=$("#basic-constraint").find("#RoomCat"+id).val();
	if($.trim(existRoomTypeWithinIndex)==$.trim(roomType)){		
		innerflag=false;		
		if(innerflag==false){ 
		alert("\""+roomType +"\" Room category is already exist !");
		$("#basic-constraint").find("#RoomCat"+id).html(selects);
		}  
		}
	}
}
}
</script>
	<input type="hidden" id="noofboxes" value="">
	 

	<div style="z-index: 1051; margin-top: 20%;"
		class="modal bs-modal-sm col-sm-12 col-xs-12" id="smallModal2"
		tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content"
				style="width: 60% !important; margin-left: 20%; margin-top: 5%;">
				<div class="modal-header"
					style="background-color: #428BCA; color: #ffffff; padding: 2px;">
					<div style="padding: 3px;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Upload Room Image Dailog</h4>
					</div>
				</div>
				<div class="modal-body" style="text-align: justify;">
					<div class="row">
						<form id="imageuploadform1" method="post"
							action="/bonton/upload/uploadImage" enctype="multipart/form-data">
							<input type="file" name="file" id="file" onchange="checkFile(this);" style="width: 100%;"
								class="form-control input-lg" id="propertyimages"
								accept="image/*" /> <span id="image_error"
								style="display: none; color: red">Please upload only jpg
								jpeg, gif, png</span>
							<!-- <img id="blah" src="#" alt="your image" id="imagBlah1" style="width:50px; height:50px;" /> -->
						</form>
						<span id="hotel_image" style="height: 50px; width: 50px;"></span>

						<div id="loader_img"
							style="width: 270px; height: 50px; border: 4px solid #999; border-radius: 8px 8px 8px 8px; z-index: 999999; position: relative; left: 50%; right: 0px; bottom: 0px; top: 10px; display: none; background: #fff; padding: 13px;">
							<div style="position: absolute;">
								Please wait <img src="/bonton/resources/img/loader.gif"
									style="padding-left: 10px;" />
							</div>
						</div>						
					</div>
					<div class="modal-footer">
							<input type="hidden" name="imageuploadFile1"
								id="imageuploadFile1"> <input type="hidden"
								name="imageuploadFileHolder" id="imageuploadFileHolder">
							<input onclick="hideImageUpload();imageUploadVAl();" type="button" class="btn btn-primary" data-dismiss="modal"	value="Save">
						</div>
				</div>
			</div>
		</div>
	</div> 
	<div id="black_overlay"
		style="width: 100%; height: 100%; background: #000; opacity: 0.6; z-index: 999; position: absolute; left: 0px; right: 0px; bottom: 0px; top: 0px; display: none;">

	</div>

	<div id="loader"
		style="width: 300px; height: 70px; border: 4px solid #999; border-radius: 8px 8px 8px 8px; z-index: 9999; position: absolute; left: 50%; right: 0px; bottom: 0px; top: 200px; display: none; background: #ffffff; padding: 13px;">
		Please wait <img src="/bonton/resources/img/loader.gif"
			style="padding-left: 10px;" />
	</div>

</body>
</html>
