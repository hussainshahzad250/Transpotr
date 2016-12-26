package com.trux.model;

import java.util.Date;
import java.util.Set;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "consumer_paymentmode", uniqueConstraints = {
		@UniqueConstraint(columnNames = "paymentType"),
		@UniqueConstraint(columnNames = "cpmcode") })
public class ConsumerPaymentMode {

	@Id
	@Column(name = "pId", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pId;

	@Column(name = "paymentType")
	private String paymentType;

	@Column(name = "cpmCode")
	private String cpmcode;

	@Column(name = "createdDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "updatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	public ConsumerPaymentMode() {
		super();
	}

	public long getpId() {
		return pId;
	}

	public void setpId(long pId) {
		this.pId = pId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCpmcode() {
		return cpmcode;
	}

	public void setCpmcode(String cpmcode) {
		this.cpmcode = cpmcode;
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
