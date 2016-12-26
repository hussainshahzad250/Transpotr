package com.trux.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="saltval")
public class SaltAuthantication {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sid")
	private Integer sid;
	@Column(name="authanticationVal")
	private String authanticationVal;
	
	public SaltAuthantication(Integer sid) {
		super();
		this.sid = sid;
	}
	public SaltAuthantication(String authanticationVal) {
		super();
		this.authanticationVal = authanticationVal;
	}
	public SaltAuthantication() {
		super();
	}
	public SaltAuthantication(Integer sid, String authanticationVal) {
		super();
		this.sid = sid;
		this.authanticationVal = authanticationVal;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getAuthanticationVal() {
		return authanticationVal;
	}
	public void setAuthanticationVal(String authanticationVal) {
		this.authanticationVal = authanticationVal;
	}
	
	
	
}
