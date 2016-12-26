<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2//EN">
 <html>
     <head>
         <meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
     </head>
     <body>
         <%@ page language="java" import="java.lang.*,com.citruspay.pg.net.*" session="false" isErrorPage="false"%>
         <%
             String formPostUrl = "https://sandbox.citruspay.com/truxapp";	//Need to replace the last part of URL("your-vanityUrlPart") with your Testing/Live URL
             String secret_key = "ba03d93365d9d156d990966b4e309b073f1f498d";	//Need to change with your Secret Key
             String vanityUrl = "truxapp";	//Need to change with your Vanity URL Key from the citrus panel
             String orderAmount = "1.00";
             String merchantTxnId = String.valueOf(System.currentTimeMillis());
             String currency = "INR";
             String data=vanityUrl+orderAmount+merchantTxnId+currency;
             String securitySignature = RequestSignature.generateHMAC(data, secret_key);
         %>
        <center>
         <form method="post" action="<%=formPostUrl%>">
             <input type="hidden" id="merchantTxnId" name="merchantTxnId" value="<%=merchantTxnId%>" />
             <input type="hidden" id="orderAmount" name="orderAmount" value="<%=orderAmount%>" />
             <input type="hidden" id="currency" name="currency" value="<%=currency%>" />
             <input type="hidden" name="returnUrl" value="http://truxapp.com/trux/paymentgateway/response" />
             <input type="hidden" id="secSignature" name="secSignature" value="<%=securitySignature%>" />
             <input type="Submit" value="Pay Now"/>
         </form>
         </center>
     </body>
 </html>	