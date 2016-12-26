
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2//EN">
<html>
<head>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;CHARSET=iso-8859-1">
</head>
<body>
	<%@ page language="java" import="java.lang.*, com.citruspay.pg.net.RequestSignature" session="false" isErrorPage="false"%>
	<%
		String data = "";
		String txnId = request.getParameter("TxId");
		String txnStatus = request.getParameter("TxStatus");
		String amount = request.getParameter("amount");
		String pgTxnId = request.getParameter("pgTxnNo");
		String issuerRefNo = request.getParameter("issuerRefNo");
		String authIdCode = request.getParameter("authIdCode");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String pgRespCode = request.getParameter("pgRespCode");
		String zipCode = request.getParameter("addressZip");
		String reqSignature = request.getParameter("signature");

		String mobile = request.getParameter("mobileNo");
		String email = request.getParameter("email");
		String address = request.getParameter("addressStreet1");
		String city = request.getParameter("addressCity");
		String state = request.getParameter("addressState");
		String country = request.getParameter("addressCountry");

		String signature = "";
		boolean flag = true;
		//Binding all required parameters in one string (i.e. data)
		if (txnId != null) {
			data += txnId;
		}
		if (txnStatus != null) {
			data += txnStatus;
		}
		if (amount != null) {
			data += amount;
		}
		if (pgTxnId != null) {
			data += pgTxnId;
		}
		if (issuerRefNo != null) {
			data += issuerRefNo;
		}
		if (authIdCode != null) {
			data += authIdCode;
		}
		if (firstName != null) {
			data += firstName;
		}
		if (lastName != null) {
			data += lastName;
		}
		if (pgRespCode != null) {
			data += pgRespCode;
		}
		if (zipCode != null) {
			data += zipCode;
		}
		String key = "ba03d93365d9d156d990966b4e309b073f1f498d";//Replace this with your secret key from the citrus panel
		signature = RequestSignature.generateHMAC(data, key);
		if (reqSignature != null && !reqSignature.equalsIgnoreCase("")
				&& !signature.equalsIgnoreCase(reqSignature)) {
			flag = false;
		}
		if (flag) {
	%>
	<center>
	<table >

		<tr>
			<td>First Name :</td>
			<td><%=firstName%></td>

			<td>Last Name :</td>
			<td><%=lastName%></td>
		</tr>
		<tr>
			<td>Mobile :</td>
			<td><%=mobile%></td>

			<td>E-mail:</td>
			<td><%=email%></td>
		</tr>
		<tr>
			<td>Address:</td>
			<td><%=address%></td>

			<td>City:</td>
			<td><%=city%></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><%=state%></td>

			<td>country:</td>
			<td><%=country%> Zip Code :<%=zipCode%></td>
		 
		<tr>
			<td>Your Transaction/Order Id :</td>
			<td><%=txnId%></td>

			<td>Your Unique Transaction/Order Id :</td>
			<td>
				<%
					out.println(request.getParameter("TxRefNo") == null ? "": request.getParameter("TxRefNo"));
				%>
			</td>
		</tr>
		<tr>
			<td>Your Amount :</td>
			<td><%=amount%></td>

			<td>Your Transaction Status :</td>
			<td>
				<%=txnStatus%>
			</td>
		</tr>
		
	</table>
	</center>
	<%
		} else {
	%>
	<label width="125px;">CitrusGenerated Signature and Our
		(Merchant) Signature Mis-Mactch</label>
	<%
		}
	%>

</body>
</html>
