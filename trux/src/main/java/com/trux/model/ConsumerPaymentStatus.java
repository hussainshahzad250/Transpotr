package com.trux.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "consumer_paymentstatus")
public class ConsumerPaymentStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cpid")
	private long cpid;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "mobileNo")
	private String mobileNo;

	@Column(name = "email")
	private String email;

	@Column(name = "addressStreet1")
	private String addressStreet1;

	@Column(name = "addressStreet2")
	private String addressStreet2;

	@Column(name = "addressCity")
	private String addressCity;

	@Column(name = "addressState")
	private String addressState;

	@Column(name = "addressCountry")
	private String addressCountry;

	@Column(name = "addressZip")
	private String addressZip;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "dccAmount")
	private BigDecimal dccAmount;

	@Column(name = "currency")
	private String currency;

	@Column(name = "maskedCardNumber")
	private String maskedCardNumber;

	@Column(name = "transactionId")
	private String transactionId;

	@Column(name = "isCOD")
	private String isCOD;

	@Column(name = "encryptedCardNumber")
	private String encryptedCardNumber;

	@Column(name = "impsMobileNumber")
	private String impsMobileNumber;

	@Column(name = "cardType")
	private String cardType;

	@Column(name = "cardHolderName")
	private String cardHolderName;

	@Column(name = "pgTxnNo")
	private String pgTxnNo;

	@Column(name = "TxGateway")
	private String TxGateway;

	@Column(name = "pgRespCode")
	private String pgRespCode;

	@Column(name = "impsMmid")
	private String impsMmid;
	
	@Column(name = "paymentMode")
	private String paymentMode;
	
	@Column(name = "signature")
	private String signature;
	
	@Column(name = "expiryMonth")
	private String expiryMonth;
	
	@Column(name = "issuerRefNo")
	private String issuerRefNo;
	
	@Column(name = "authIdCode")
	private String authIdCode;
	
	@Column(name = "exchangeRate")
	private String exchangeRate;
	
	@Column(name = "txn3DSecure")
	private String txn3DSecure;
	
	@Column(name = "requestedCurrenc")
	private String requestedCurrenc;
	
	@Column(name = "txId")
	private String txId;
	
	@Column(name = "dccCurrency")
	private String dccCurrency;
	
	@Column(name = "txRefNo")
	private String txRefNo;
	
	@Column(name = "txMsg")
	private String txMsg;
	
	@Column(name = "requestedAmount")
	private String requestedAmount;
	
	@Column(name = "expiryYear")
	private String expiryYear;
	
	@Column(name = "eci")
	private String eci;
	
	@Column(name = "txnDateTime")
	private String txnDateTime;
	
	@Column(name = "TxStatus")
	private String TxStatus;
	
	@Column(name = "txnType")
	private String txnType;
	
	@Column(name = "dccOfferId")
	private String dccOfferId;
	
	@Column(name = "createDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "updateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	public ConsumerPaymentStatus() {
		super();
	}
	

	public ConsumerPaymentStatus(String firstName, String lastName,
			String mobileNo, String email, String addressStreet1,
			String addressStreet2, String addressCity, String addressState,
			String addressCountry, String addressZip, BigDecimal amount,
			BigDecimal dccAmount, String currency, String maskedCardNumber,
			String transactionId, String isCOD, String encryptedCardNumber,
			String impsMobileNumber, String cardType, String cardHolderName,
			String pgTxnNo, String txGateway, String pgRespCode,
			String impsMmid, String paymentMode, String signature,
			String expiryMonth, String issuerRefNo, String authIdCode,
			String exchangeRate, String txn3dSecure, String requestedCurrenc,
			String txId, String dccCurrency, String txRefNo, String txMsg,
			String requestedAmount, String expiryYear, String eci,
			String txnDateTime, String txStatus, String txnType,
			String dccOfferId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.addressStreet1 = addressStreet1;
		this.addressStreet2 = addressStreet2;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressCountry = addressCountry;
		this.addressZip = addressZip;
		this.amount = amount;
		this.dccAmount = dccAmount;
		this.currency = currency;
		this.maskedCardNumber = maskedCardNumber;
		this.transactionId = transactionId;
		this.isCOD = isCOD;
		this.encryptedCardNumber = encryptedCardNumber;
		this.impsMobileNumber = impsMobileNumber;
		this.cardType = cardType;
		this.cardHolderName = cardHolderName;
		this.pgTxnNo = pgTxnNo;
		TxGateway = txGateway;
		this.pgRespCode = pgRespCode;
		this.impsMmid = impsMmid;
		this.paymentMode = paymentMode;
		this.signature = signature;
		this.expiryMonth = expiryMonth;
		this.issuerRefNo = issuerRefNo;
		this.authIdCode = authIdCode;
		this.exchangeRate = exchangeRate;
		this.txn3DSecure = txn3dSecure;
		this.requestedCurrenc = requestedCurrenc;
		this.txId = txId;
		this.dccCurrency = dccCurrency;
		this.txRefNo = txRefNo;
		this.txMsg = txMsg;
		this.requestedAmount = requestedAmount;
		this.expiryYear = expiryYear;
		this.eci = eci;
		this.txnDateTime = txnDateTime;
		this.TxStatus = txStatus;
		this.txnType = txnType;
		this.dccOfferId = dccOfferId;
	}


	public ConsumerPaymentStatus( String firstName, String lastName,
			String mobileNo, String email, String addressStreet1,
			String addressStreet2, String addressCity, String addressState,
			String addressCountry, String addressZip, BigDecimal amount,
			BigDecimal dccAmount, String currency, String maskedCardNumber,
			String transactionId, String isCOD, String encryptedCardNumber,
			String impsMobileNumber, String cardType, String cardHolderName,
			String pgTxnNo, String txGateway, String pgRespCode,
			String impsMmid, String paymentMode, String signature,
			String expiryMonth, String issuerRefNo, String authIdCode,
			String exchangeRate, String txn3dSecure, String requestedCurrenc,
			String txId, String dccCurrency, String txRefNo, String txMsg,
			String requestedAmount, String expiryYear, String eci,
			String txnDateTime, String txStatus, String txnType,
			String dccOfferId, Date createdDate, Date updatedDate) {
		 
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.addressStreet1 = addressStreet1;
		this.addressStreet2 = addressStreet2;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressCountry = addressCountry;
		this.addressZip = addressZip;
		this.amount = amount;
		this.dccAmount = dccAmount;
		this.currency = currency;
		this.maskedCardNumber = maskedCardNumber;
		this.transactionId = transactionId;
		this.isCOD = isCOD;
		this.encryptedCardNumber = encryptedCardNumber;
		this.impsMobileNumber = impsMobileNumber;
		this.cardType = cardType;
		this.cardHolderName = cardHolderName;
		this.pgTxnNo = pgTxnNo;
		TxGateway = txGateway;
		this.pgRespCode = pgRespCode;
		this.impsMmid = impsMmid;
		this.paymentMode = paymentMode;
		this.signature = signature;
		this.expiryMonth = expiryMonth;
		this.issuerRefNo = issuerRefNo;
		this.authIdCode = authIdCode;
		this.exchangeRate = exchangeRate;
		txn3DSecure = txn3dSecure;
		this.requestedCurrenc = requestedCurrenc;
		this.txId = txId;
		this.dccCurrency = dccCurrency;
		this.txRefNo = txRefNo;
		this.txMsg = txMsg;
		this.requestedAmount = requestedAmount;
		this.expiryYear = expiryYear;
		this.eci = eci;
		this.txnDateTime = txnDateTime;
		TxStatus = txStatus;
		this.txnType = txnType;
		this.dccOfferId = dccOfferId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public long getCpid() {
		return cpid;
	}

	public void setCpid(long cpid) {
		this.cpid = cpid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressStreet1() {
		return addressStreet1;
	}

	public void setAddressStreet1(String addressStreet1) {
		this.addressStreet1 = addressStreet1;
	}

	public String getAddressStreet2() {
		return addressStreet2;
	}

	public void setAddressStreet2(String addressStreet2) {
		this.addressStreet2 = addressStreet2;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public String getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getAddressZip() {
		return addressZip;
	}

	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getDccAmount() {
		return dccAmount;
	}

	public void setDccAmount(BigDecimal dccAmount) {
		this.dccAmount = dccAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMaskedCardNumber() {
		return maskedCardNumber;
	}

	public void setMaskedCardNumber(String maskedCardNumber) {
		this.maskedCardNumber = maskedCardNumber;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getIsCOD() {
		return isCOD;
	}

	public void setIsCOD(String isCOD) {
		this.isCOD = isCOD;
	}

	public String getEncryptedCardNumber() {
		return encryptedCardNumber;
	}

	public void setEncryptedCardNumber(String encryptedCardNumber) {
		this.encryptedCardNumber = encryptedCardNumber;
	}

	public String getImpsMobileNumber() {
		return impsMobileNumber;
	}

	public void setImpsMobileNumber(String impsMobileNumber) {
		this.impsMobileNumber = impsMobileNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getPgTxnNo() {
		return pgTxnNo;
	}

	public void setPgTxnNo(String pgTxnNo) {
		this.pgTxnNo = pgTxnNo;
	}

	public String getTxGateway() {
		return TxGateway;
	}

	public void setTxGateway(String txGateway) {
		TxGateway = txGateway;
	}

	public String getPgRespCode() {
		return pgRespCode;
	}

	public void setPgRespCode(String pgRespCode) {
		this.pgRespCode = pgRespCode;
	}

	public String getImpsMmid() {
		return impsMmid;
	}

	public void setImpsMmid(String impsMmid) {
		this.impsMmid = impsMmid;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public String getIssuerRefNo() {
		return issuerRefNo;
	}

	public void setIssuerRefNo(String issuerRefNo) {
		this.issuerRefNo = issuerRefNo;
	}

	public String getAuthIdCode() {
		return authIdCode;
	}

	public void setAuthIdCode(String authIdCode) {
		this.authIdCode = authIdCode;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getTxn3DSecure() {
		return txn3DSecure;
	}

	public void setTxn3DSecure(String txn3dSecure) {
		txn3DSecure = txn3dSecure;
	}

	public String getRequestedCurrenc() {
		return requestedCurrenc;
	}

	public void setRequestedCurrenc(String requestedCurrenc) {
		this.requestedCurrenc = requestedCurrenc;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getDccCurrency() {
		return dccCurrency;
	}

	public void setDccCurrency(String dccCurrency) {
		this.dccCurrency = dccCurrency;
	}

	public String getTxRefNo() {
		return txRefNo;
	}

	public void setTxRefNo(String txRefNo) {
		this.txRefNo = txRefNo;
	}

	public String getTxMsg() {
		return txMsg;
	}

	public void setTxMsg(String txMsg) {
		this.txMsg = txMsg;
	}

	public String getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(String requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public String getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}

	public String getEci() {
		return eci;
	}

	public void setEci(String eci) {
		this.eci = eci;
	}

	public String getTxnDateTime() {
		return txnDateTime;
	}

	public void setTxnDateTime(String txnDateTime) {
		this.txnDateTime = txnDateTime;
	}

	public String getTxStatus() {
		return TxStatus;
	}

	public void setTxStatus(String txStatus) {
		TxStatus = txStatus;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getDccOfferId() {
		return dccOfferId;
	}

	public void setDccOfferId(String dccOfferId) {
		this.dccOfferId = dccOfferId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
