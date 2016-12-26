 
 
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
          <fieldset class="fieldset2 borderManager table-responsive">
          <a href="/trux/consumer/consumerXlsReports" style="color: black;font-size: 10px;padding-left: -1"><img alt="" src="/trux/resources/images/xls.png" width="15px;" height="15px;">Export to Excel</a> 
          <table id="consumerList"></table>
		  <div id="pconsumerList"></div>         
          </fieldset>        
        </div>
  
    </div>
  
 <script type="text/javascript">
 
jQuery(document).ready(function() {
	    $.jgrid.nav.searchtext = "Search";
	    $.jgrid.nav.refreshtext = "Re-Fresh";	 
		$("#consumerList").jqGrid({
			    url:'/trux/consumer/consumerReportsGrid',
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
				colNames : ['CId','Name','E-Mail','Phone','User Type','Created Date','Updated Date'],
				colModel : [
						    {name : 'id',index : 'id',width : 140,hhidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'userFistLastName',index : 'userFistLastName',width :170,hhidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'email',index : 'email',width : 280,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'phoneNumber',index : 'phoneNumber',width : 100,hhidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } },
		                    {name : 'userTypes',index : 'userTypes',width : 110,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' } }, 
		                    {name : 'createConsumerDate',index : 'createConsumerDate',width : 155,hidden : false,editable:true,editrules:{required:true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"' }},
		                    {name : 'updatedConsumerDate',index : 'updatedConsumerDate',width : 155,hidden : false,editable : true,editrules : {edithidden : false}, editoptions:{readonly: true},cellattr: function (rowId, tv, rawObject, cm, rdata) { return 'style="white-space: normal;"'}},
		                   ],
				rowNum :10,
				rowList : [5,10,15,20,25,30, 50, 60, 120, 240, 480,1000 ],
				height : "100%",
				width : "100%",
				loadonce : true,
				fontsize : 11,
				shrinkToFit : true,
				rownumbers : true,
				caption : "Consumer Report",
				singleselect: true,
				pager : '#pconsumerList',
				viewrecords : true,
				emptyrecords : "No racords available",gridComplete: function()
				{
				    var rows = $("#orderList").getDataIDs(); 
				    
				} 
				 
		    });
   	$("#consumerList").jqGrid('navGrid', '#pconsumerList', {
			edit : false,
			add : false,
			del : false,
			exl : true,
			search : true},
			{width : 'auto',url : '#'}, 
			{width : 'auto',url : '#'}, 
			{width : 'auto', serializeDelData: function (postdata) {
				  var selr = jQuery('#consumerList').jqGrid('getGridParam','selrow'); 
			      var rowData = $("#consumerList").jqGrid('getRowData');
			     
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