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
@Table(name = "walletEntry")
public class WalletEntryModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "weId")
	private long walleId;

	@Column(name = "driverName")
	private String driverName;

	@Column(name = "driverNumber")
	private String driverNumber;

	@Column(name = "orderDateTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDateTime;

	@Column(name = "freightAmount")
	private double freightAmount;

	@Column(name = "serviceTax")
	private double serviceTax;

	@Column(name = "marchant")
	private Integer marchant;

	@Column(name = "truxServiceCommition")
	private double truxServiceCommision;

	@Column(name = "serviceChargeEntries")
	private double serviceChargeEntries;

	@Column(name = "orderCancellationCharge")
	private double orderCancellationCharge;

	@Column(name = "giftVoucherOrPromotionCode")
	private String giftVoucherOrPromotionCode;

	@Column(name = "total")
	private double total;

	public WalletEntryModel() {
		super();
	}

	public WalletEntryModel(long weId, String driverName, String driverNumber, Date orderDateTime, double freightAmount,
			double serviceTax, Integer marchant, double truxServiceCommition, double serviceChargeEntries,
			double orderCancellationCharge, String giftVoucherOrPromotionCode, double total) {
		super();
		this.walleId = weId;
		this.driverName = driverName;
		this.driverNumber = driverNumber;
		this.orderDateTime = orderDateTime;
		this.freightAmount = freightAmount;
		this.serviceTax = serviceTax;
		this.marchant = marchant;
		this.truxServiceCommision = truxServiceCommition;
		this.serviceChargeEntries = serviceChargeEntries;
		this.orderCancellationCharge = orderCancellationCharge;
		this.giftVoucherOrPromotionCode = giftVoucherOrPromotionCode;
		this.total = total;
	}

	public WalletEntryModel(String driverName, String driverNumber, Date orderDateTime, double freightAmount,
			double serviceTax, Integer marchant, double truxServiceCommition, double serviceChargeEntries,
			double orderCancellationCharge, String giftVoucherOrPromotionCode, double total) {
		super();
		this.driverName = driverName;
		this.driverNumber = driverNumber;
		this.orderDateTime = orderDateTime;
		this.freightAmount = freightAmount;
		this.serviceTax = serviceTax;
		this.marchant = marchant;
		this.truxServiceCommision = truxServiceCommition;
		this.serviceChargeEntries = serviceChargeEntries;
		this.orderCancellationCharge = orderCancellationCharge;
		this.giftVoucherOrPromotionCode = giftVoucherOrPromotionCode;
		this.total = total;
	}

	public long getWalleId() {
		return walleId;
	}

	public void setWalleId(long walleId) {
		this.walleId = walleId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverNumber() {
		return driverNumber;
	}

	public void setDriverNumber(String driverNumber) {
		this.driverNumber = driverNumber;
	}

	public Date getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(Date orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public double getFreightAmount() {
		return freightAmount;
	}

	public void setFreightAmount(double freightAmount) {
		this.freightAmount = freightAmount;
	}

	public double getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(double serviceTax) {
		this.serviceTax = serviceTax;
	}

	public Integer getMarchant() {
		return marchant;
	}

	public void setMarchant(Integer marchant) {
		this.marchant = marchant;
	}

	public double getTruxServiceCommision() {
		return truxServiceCommision;
	}

	public void setTruxServiceCommision(double truxServiceCommision) {
		this.truxServiceCommision = truxServiceCommision;
	}

	public double getServiceChargeEntries() {
		return serviceChargeEntries;
	}

	public void setServiceChargeEntries(double serviceChargeEntries) {
		this.serviceChargeEntries = serviceChargeEntries;
	}

	public double getOrderCancellationCharge() {
		return orderCancellationCharge;
	}

	public void setOrderCancellationCharge(double orderCancellationCharge) {
		this.orderCancellationCharge = orderCancellationCharge;
	}

	public String getGiftVoucherOrPromotionCode() {
		return giftVoucherOrPromotionCode;
	}

	public void setGiftVoucherOrPromotionCode(String giftVoucherOrPromotionCode) {
		this.giftVoucherOrPromotionCode = giftVoucherOrPromotionCode;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
