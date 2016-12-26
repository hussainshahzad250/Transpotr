<!DOCTYPE html>
<html>
<head>
<title></title>
<link href="/trux/resources/jtable/css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="/trux/resources/jtable/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<script src="/trux/resources/jtable/js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="/trux/resources/jtable/js/jquery.jtable.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#pendingTableContainer').jtable({
			title : 'Live Order',
            paging: true, //Enable paging
            pageSize: 5, //Set page size (//default: 10)           
            actions: {
                listAction: '/trux/reportsapi/liveOrderReports?action=list' 
            },
			fields : {
				/* bookingId : {
					title : 'Booking Id',
					sort :true,
					width : '6%',
					key : true,
					list : true,
					edit : false,
					create : true
				}, */
				customerName : {
					title : 'Name',
					sort :true,
					width : '15%',
					key : true,
					list : true,
					edit : true
				},
				custmerPhonenumber : {
					title : 'Phone Number',
					width : '10%',
					edit : true
				},
				fromLocation : {
					title : 'From Address',
					width : '20%',
					edit : true
				},
				toLocation : {
					title : 'To Address',
					width : '20%',
					edit : true
				},
				createdDate : {
					title : 'Time of Order',
					width : '15%',
					edit : true
				},
				booking_time : {
					title : 'Timer',
					width : '10%',
					edit : true
				}
				
			}
		});
		$('#pendingTableContainer').jtable('load');
	});
</script>

</head>
<body><fieldset class="fieldset2 borderManager table-responsive">
<div style="overflow: auto; height: 500px;">
	<div style="width: 100%; margin-right: 0%; margin-left: 0%; text-align: left;">
		<div id="pendingTableContainer"></div>
	</div></div>
	</fieldset>
</body>
</html> 