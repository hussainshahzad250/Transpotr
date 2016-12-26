package com.trux.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ConsumerRegistrationResponse implements Serializable{
	private List<ConsumerRegistration> rows;
	private Integer total;
	private Integer records;
	private Integer page;
	public List<ConsumerRegistration> getRows() {
		return rows;
	}
	public void setRows(List<ConsumerRegistration> rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
}
