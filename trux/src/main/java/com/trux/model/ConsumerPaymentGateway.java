package com.trux.model;

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
@Table(name = "consumer_paymentgateway")
public class ConsumerPaymentGateway {

	@Id
	@Column(name = "cpid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cpId;

	@Column(name = "wallet")
	private String wallet;

	@Column(name = "paymentmode")
	private String paymentmode;

	@Column(name = "cash_recharge")
	private String cashRecharge;

	@Column(name = "citrus_paymentgateway")
	private String citrusPaymentGateway;

	@Column(name = "createdDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "updatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	public ConsumerPaymentGateway() {
	}

	public ConsumerPaymentGateway(long cpId, String wallet, String paymentmode,
			String cashRecharge, String citrusPaymentGateway, Date createdDate,
			Date updatedDate) {
		super();
		this.cpId = cpId;
		this.wallet = wallet;
		this.paymentmode = paymentmode;
		this.cashRecharge = cashRecharge;
		this.citrusPaymentGateway = citrusPaymentGateway;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public long getCpId() {
		return cpId;
	}

	public void setCpId(long cpId) {
		this.cpId = cpId;
	}

	public String getWallet() {
		return wallet;
	}

	public void setWallet(String wallet) {
		this.wallet = wallet;
	}

	public String getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	public String getCashRecharge() {
		return cashRecharge;
	}

	public void setCashRecharge(String cashRecharge) {
		this.cashRecharge = cashRecharge;
	}

	public String getCitrusPaymentGateway() {
		return citrusPaymentGateway;
	}

	public void setCitrusPaymentGateway(String citrusPaymentGateway) {
		this.citrusPaymentGateway = citrusPaymentGateway;
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
