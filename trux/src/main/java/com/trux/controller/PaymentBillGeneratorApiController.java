package com.trux.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/billGenerator")
public class PaymentBillGeneratorApiController {
	@ResponseBody
	@RequestMapping(value = "/sandbox", method = RequestMethod.POST)
	public ModelAndView checkout(HttpServletRequest request,
			HttpServletResponse response, @RequestParam int amount,
			@RequestParam String mobile) throws Exception {
/*		response.setContentType("application/json");
		String accessKey = "NBUP5B5BQXASB5M9NTJH";
		String secretKey = "ba03d93365d9d156d990966b4e309b073f1f498d";
	//	String returnUrl = "http://truxapp.com/trux/billGenerator/returnresponse";
		String returnUrl = "http://truxapp.com/trux/pages/test.jsp";

		String txnID = String.valueOf(System.currentTimeMillis());
		String dataString = "merchantAccessKey=" + accessKey
				+ "&transactionId=" + txnID + "&amount=" + amount;
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(),"HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(secretKeySpec);
		byte[] hmacArr = mac.doFinal(dataString.getBytes());
		StringBuilder build = new StringBuilder();
		for (byte b : hmacArr) {
			build.append(String.format("%02x", b));
		}
		String hmac = build.toString();
		StringBuilder amountBuilder = new StringBuilder();
		amountBuilder.append("\"value\":\"").append(amount).append("\"")
				.append(",\"currency\":\"INR\"");
		StringBuilder resBuilder = new StringBuilder("{");
		resBuilder.append("\"merchantTxnId\"").append(":").append("\"")
				.append(txnID).append("\"").append(",")
				.append("\"requestSignature\"").append(":").append("\"")
				.append(hmac).append("\"").append(",")
				.append("\"merchantAccessKey\"").append(":").append("\"")
				.append(accessKey).append("\"").append(",")
				.append("\"returnUrl\"").append(":").append("\"")
				.append(returnUrl).append("\"").append(",")
				.append("\"amount\"").append(":").append("{")
				.append(amountBuilder).append("}").append("}");
		response.getWriter().print(resBuilder);*/

		 return new ModelAndView("CitrusFiles/citrusbillgenerator");
	}

	@SuppressWarnings("serial")
	@ResponseBody
	@RequestMapping(value = "/returnresponse", method = RequestMethod.POST)
	public ModelAndView retrunresponse(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	/*	String secretKey = "ba03d93365d9d156d990966b4e309b073f1f498d";
		Hashtable<String, String> reqValMap = new Hashtable<String, String>() {
			public synchronized String toString() {
				Enumeration<String> keys = keys();
				StringBuffer buff = new StringBuffer("{");
				while (keys.hasMoreElements()) {
					String key = keys.nextElement();
					String value = get(key);

					buff.append("'").append(key).append("'").append(":")
							.append("'").append(value).append("'").append(',');
				}
				buff = new StringBuffer(buff.toString().substring(0,
						buff.toString().length() - 1));
				buff.append("}");
				return buff.toString();
			}
		};
		Enumeration<String> parameterList = request.getParameterNames();
		while (parameterList.hasMoreElements()) {
			String paramName = parameterList.nextElement();
			String paramValue = request.getParameter(paramName);
			reqValMap.put(paramName, paramValue);
		}
		String dataString = new StringBuilder()
				.append(request.getParameter("TxId"))
				.append(request.getParameter("TxStatus"))
				.append(request.getParameter("amount"))
				.append(request.getParameter("pgTxnNo"))
				.append(request.getParameter("issuerRefNo"))
				.append(request.getParameter("authIdCode"))
				.append(request.getParameter("firstName"))
				.append(request.getParameter("lastName"))
				.append(request.getParameter("pgRespCode"))
				.append(request.getParameter("addressZip")).toString();

		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(),
				"HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(secretKeySpec);
		byte[] hmacArr = mac.doFinal(dataString.getBytes());
		StringBuilder build = new StringBuilder();
		for (byte b : hmacArr) {
			build.append(String.format("%02x", b));
		}
		String hmac = build.toString();
		String reqSignature = request.getParameter("signature");
		System.out.println("txn ID : " + request.getParameter("TxId"));
		System.out.println("RESPONSE " + reqValMap.toString());
		System.out.println("RESPONSE " + "THIS IS TEST");
		return reqValMap.toString();*/
		return new ModelAndView("CitrusFiles/returndata");
	}
}
