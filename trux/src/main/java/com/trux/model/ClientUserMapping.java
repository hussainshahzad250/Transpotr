package com.trux.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client_truxuser_mapping")
public class ClientUserMapping {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_mappingId")
	private Integer userMappingId;
	@Column(name="clientsubid")
	private Integer clientSubid;
	@Column(name="userid")
	private Integer userid;
	@Column(name="createddatetime")
	private Integer createdDatetime; 
	@Column(name="createdby")
 	private Integer createdBy;
	@Column(name="modifieddatetime")
 	private Date modifiedDateTime;
	@Column(name="modifiedby")
 	private Integer modifiedBy;
	@Column(name="is_active")
 	private Integer isActive;
	
	
	public ClientUserMapping() {
		super();
	}


	public ClientUserMapping(Integer userMappingId) {
		super();
		this.userMappingId = userMappingId;
	}


	public ClientUserMapping(Integer userMappingId, Integer clientSubid,
			Integer userid, Integer createdDatetime, Integer createdBy,
			Date modifiedDateTime, Integer modifiedBy, Integer isActive) {
		super();
		this.userMappingId = userMappingId;
		this.clientSubid = clientSubid;
		this.userid = userid;
		this.createdDatetime = createdDatetime;
		this.createdBy = createdBy;
		this.modifiedDateTime = modifiedDateTime;
		this.modifiedBy = modifiedBy;
		this.isActive = isActive;
	}
	
	
}
