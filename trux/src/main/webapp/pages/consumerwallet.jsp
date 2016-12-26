<!DOCTYPE html>
<html lang="en">
<head>
<title>Trux</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- favicon links -->
<link rel="shortcut icon" type="image/ico" href="favicon.ico" />
<link rel="icon" type="image/ico" href="favicon.ico" />
<!-- main css -->
<link rel="stylesheet" href="/trux/resources/css/main.css" media="screen"/>
<!--js-->
<script src="/trux/resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){	
	// "scrollTop" plugin
	$.scrollUp();
});
</script>

</head>
<body>
 
<div class="hs_page_title">
	<div class="container">
		<div class="row" style="margin-left:0px; margin-right:0; margin-top:5px;">
    		<h3>Consumer Wallet</h3>
		</div>
  	</div>
</div>
<div class="clearfix hs_margin_30"></div>
<div class="container">  
	<div class="row">
    	<div class="col-lg-12 col-md-12 col-sm-12">            
                <div class="row-fluid">		
                    <div class="hs_tab">
                       
						<div class="clearfix hs_margin_30"></div>						     			
                        <div id="myTabContent" class="tab-content">

                        <div> 
                              
                        <form action="./consumerwallet" method="post">
                                <div class="row">                                    										                                  
                                        <div class="col-lg-4 col-md-4 col-sm-12"><div style="margin-bottom:6px;">E-Mail</div>
                                            <input type='text' id="email" name="email"  class="form-control input-lg" style="width:90%;" placeholder="E-Mail"/>
                                        </div> 
										<div class="col-lg-4 col-md-4 col-sm-12"><div style="margin-bottom:6px;">Mobile</div>
                                           <input type='text' id="phone" name="phone"  class="form-control input-lg" style="width:90%;" placeholder="Mobile"/>
                                        </div>  
										<div class="col-lg-4 col-md-4 col-sm-12"><div style="margin-bottom:6px;">Amount</div>
                                            <input type='text' id="amount" name="amount"  class="form-control input-lg" style="width:90%;" placeholder="Amount"/>
                                        </div>	
                                        <div class="clearfix margin_10"></div>
                                        <div class="col-lg-4 col-md-4 col-sm-12"><div style="margin-bottom:6px;">Card Date Line</div>
                                            <input type='text' id="cardDateLine" name="cardDateLine"  class="form-control input-lg" style="width:90%;" placeholder="Card Date Line"/>
                                        </div>	
                                        <div class="col-lg-4 col-md-4 col-sm-12"><div style="margin-bottom:6px;">Payment Type</div>
                                           <select name="peymentType" id="peymentType" class="form-control2 input-lg" style="width:90%;">
                                                <option value="">---Select---</option>
                                                <option value="1">Credit Card</option>
                                                <option value="2">Debit Card</option>   
                                                <option value="3">Cash</option>   
                                                <option value="4">Net Banking</option>   
                                                <option value="5">Citrus Gatway</option>                                                       
                                            </select> 
                                        </div> 	
                                        <div class="col-lg-4 col-md-4 col-sm-12"><div style="margin-bottom:6px;">Payment Sub Type</div>
                                               <input type='text' id="paymentSubType" name="paymentSubType"  class="form-control input-lg" style="width:90%;" placeholder="Payment sub type"/>
                                        </div> 				
                                    </div>									
                                </div>
								<div class="clearfix margin_10"></div>    
                                <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12">     
                                        <div style="margin-top:6px; font-size:14px; text-align:left; margin-left:18px;">
                                            <button class="btn btn-danger">Submit</button>                               
                                        </div>
                                    </div>
                                    <div class="clearfix margin_10"></div>
                                </div> 
                                </form>  


							
                            <div class="clearfix margin_10"></div>
                        </div>
                    </div>											
                 </div>   
             </div>
       </div>		
  	</div>    
</body>
</html> 