package com.trux.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author TRIYASOFT
 *
 */
@Entity
@Table(name = "consumer_wallet")
public class ConsumerWallet {

	@Id
	@Column(name = "cw_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cwId;

	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "amount")
	private float amount;

	@Column(name = "card_date_line")
	private String cardDateLine;

	@Column(name = "peymentType")
	private int peymentType;

	@Column(name = "payment_sub_type")
	private String paymentSubType;

	@Column(name = "createdDate")
	private Date createdDate;

	@Column(name = "updatedDate")
	private Date updatedDate;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "consumer_wallet", joinColumns = @JoinColumn(name = "peymentType"), inverseJoinColumns = @JoinColumn(name = "pId"))
	private List<ConsumerPaymentMode> paymentMode;
 
	public ConsumerWallet() {

	}

	public ConsumerWallet(long cwId, String email, String phone, float amount,
			String cardDateLine, int peymentType, String paymentSubType,
			Date createdDate, Date updatedDate,
			List<ConsumerPaymentMode> paymentMode) {
		super();
		this.cwId = cwId;
		this.email = email;
		this.phone = phone;
		this.amount = amount;
		this.cardDateLine = cardDateLine;
		this.peymentType = peymentType;
		this.paymentSubType = paymentSubType;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.paymentMode = paymentMode;
	}

	public long getCwId() {
		return cwId;
	}

	public void setCwId(long cwId) {
		this.cwId = cwId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getCardDateLine() {
		return cardDateLine;
	}

	public void setCardDateLine(String cardDateLine) {
		this.cardDateLine = cardDateLine;
	}

	public int getPeymentType() {
		return peymentType;
	}

	public void setPeymentType(int peymentType) {
		this.peymentType = peymentType;
	}

	public String getPaymentSubType() {
		return paymentSubType;
	}

	public void setPaymentSubType(String paymentSubType) {
		this.paymentSubType = paymentSubType;
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

	public List<ConsumerPaymentMode> getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(List<ConsumerPaymentMode> paymentMode) {
		this.paymentMode = paymentMode;
	}
 
}
