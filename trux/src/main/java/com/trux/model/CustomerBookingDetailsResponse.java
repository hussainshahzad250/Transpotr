package com.trux.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class CustomerBookingDetailsResponse implements Serializable{
	private List<CustomerBookingDetails> rows;
	private String total;
	private String records;
	private String page;
	private String status;

	public List<CustomerBookingDetails> getRows() {
		return rows;
	}

	public void setRows(List<CustomerBookingDetails> rows) {
		this.rows = rows;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRecords() {
		return records;
	}

	public void setRecords(String records) {
		this.records = records;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}