package com.trux.controller;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.trux.model.ConsumerPaymentStatus;
import com.trux.model.ConsumerWallet;
import com.trux.service.ConsumerPaymentGatewayService;
 
@Controller
@RequestMapping("/paymentgateway")
public class ConsumerWalletController {
	@Autowired
	private ConsumerPaymentGatewayService consumerPaymentGatewayService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView gateway() {
		return new ModelAndView("consumerwallet");
	}

	@RequestMapping(value = "/consumerwallet", method = RequestMethod.POST)
	public ModelAndView consumerwalle(
			@ModelAttribute("cwallet") ConsumerWallet cwallet,
			BindingResult bind) {
		System.out.println("Email : " + cwallet.getEmail() + " phone : "
				+ cwallet.getPhone() + " Amount :" + cwallet.getAmount()
				+ " cdl :" + cwallet.getCardDateLine() + " PTYPE :"
				+ cwallet.getPeymentType());
		consumerPaymentGatewayService.saveCW(cwallet);
		return new ModelAndView("consumerwallet");
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView checkoutRequest() {
		return new ModelAndView("/CitrusFiles/samplerequest");
	}

	@RequestMapping(value = "/response", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView checkoutResponse(HttpServletRequest request,
			HttpServletResponse response) {

		String secretKeys = "ba03d93365d9d156d990966b4e309b073f1f498d";
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
		System.out.println(reqValMap);
		BigDecimal amount = null;
		String amountv = reqValMap.get("amount");
		if (amountv != null) {
			amount = new BigDecimal(Double.parseDouble(amountv.trim()));
		} else {
			amount = new BigDecimal(0);
		}
		String value = reqValMap.get("dccAmount");
		BigDecimal dccamount = null;

		dccamount = new BigDecimal(0);

		String dataString = new StringBuilder().append(" TxId : ")
				.append(request.getParameter("TxId")).append(" TxStatus :")
				.append(request.getParameter("TxStatus")).append(" amount : ")
				.append(request.getParameter("amount")).append(" pgTxnNo :")
				.append(request.getParameter("pgTxnNo"))
				.append(" issuerRefNo :")
				.append(request.getParameter("issuerRefNo"))
				.append(" authIdCode :")
				.append(request.getParameter("authIdCode"))
				.append(" firstName :")
				.append(request.getParameter("firstName"))
				.append(" lastName : ")
				.append(request.getParameter("lastName"))
				.append(" pgRespCode: ")
				.append(request.getParameter("pgRespCode"))
				.append(" addressZip :")
				.append(request.getParameter("addressZip")).toString();
		//System.out.println(dataString);
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeys.getBytes(),"HmacSHA1");
		Mac mac;
		try {
			mac = Mac.getInstance("HmacSHA1");
			mac.init(secretKeySpec);
			byte[] hmacArr = mac.doFinal(dataString.getBytes());
			StringBuilder build = new StringBuilder();
			for (byte b : hmacArr) {
				build.append(String.format("%02x", b));
			}
			String hmac = build.toString();
			String reqSignature = request.getParameter("signature");
			//System.out.println("txn ID : " + request.getParameter("TxId"));

			//System.out.println(" hmac " + hmac);
			//System.out.println("reqSignature :" + reqSignature);
		} catch (Exception e) {

			e.printStackTrace();
		}

		 
		ConsumerPaymentStatus cps = new ConsumerPaymentStatus();
		cps.setFirstName(reqValMap.get("firstName"));
		cps.setLastName(reqValMap.get("lastName"));
		cps.setMobileNo(reqValMap.get("mobileNo"));
		cps.setEmail(reqValMap.get("email"));
		cps.setAddressStreet1(reqValMap.get("addressStreet1"));
		cps.setAddressStreet2(reqValMap.get("addressStreet2"));
		cps.setAddressCity(reqValMap.get("addressCity"));
		cps.setAddressState(reqValMap.get("addressState"));
		cps.setAddressCountry(reqValMap.get("addressCountry"));
		cps.setAddressZip(reqValMap.get("addressZip"));
		cps.setAmount(amount);
		cps.setDccAmount(dccamount);
		cps.setCurrency(reqValMap.get("currency"));
		cps.setMaskedCardNumber(reqValMap.get("maskedCardNumber"));
		cps.setTransactionId(reqValMap.get("transactionId"));
		cps.setIsCOD(reqValMap.get("isCOD"));
		cps.setEncryptedCardNumber(reqValMap.get("encryptedCardNumber"));
		cps.setImpsMobileNumber(reqValMap.get("impsMobileNumber"));
		cps.setCardType(reqValMap.get("cardType"));
		cps.setCardHolderName(reqValMap.get("cardHolderName"));
		cps.setPgTxnNo(reqValMap.get("pgTxnNo"));
		cps.setTxGateway(reqValMap.get("TxGateway"));
		cps.setPgRespCode(reqValMap.get("pgRespCode"));
		cps.setImpsMmid(reqValMap.get("impsMmid"));
		cps.setPaymentMode(reqValMap.get("paymentMode"));
		cps.setSignature(reqValMap.get("signature"));
		cps.setExpiryMonth(reqValMap.get("expiryMonth"));
		cps.setIssuerRefNo(reqValMap.get("issuerRefNo"));
		cps.setAuthIdCode(reqValMap.get("authIdCode"));
		cps.setExchangeRate(reqValMap.get("exchangeRate"));
		cps.setTxn3DSecure(reqValMap.get("txn3DSecure"));
		cps.setRequestedCurrenc(reqValMap.get("requestedCurrency"));
		cps.setTxId(reqValMap.get("TxId"));
		cps.setDccCurrency(reqValMap.get("dccCurrency"));
		cps.setTxRefNo(reqValMap.get("TxRefNo"));
		cps.setTxMsg(reqValMap.get("TxMsg"));
		cps.setRequestedAmount(reqValMap.get("requestedAmount"));
		cps.setExpiryYear(reqValMap.get("expiryYear"));
		cps.setEci(reqValMap.get("eci"));
		cps.setTxnDateTime(reqValMap.get("txnDateTime"));
		cps.setTxStatus(reqValMap.get("TxStatus"));
		cps.setTxnType(reqValMap.get("txnType"));
		cps.setDccOfferId(reqValMap.get("dccOfferId"));
		//System.out.println(reqValMap);
		consumerPaymentGatewayService.saveCps(cps);

		return new ModelAndView("/CitrusFiles/returnresponse");
	}
 

}
