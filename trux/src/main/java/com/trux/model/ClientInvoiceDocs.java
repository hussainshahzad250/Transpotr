package com.trux.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client_invoice_docs")

public class ClientInvoiceDocs {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)

@Column(name="clientInvoicDocsId")
private Integer	clientInvoicDocsId;
@Column(name="bookingLeaseId")
private Integer	bookingLeaseId;
@Column(name="doc_type")
private String	docType;
@Column(name="document_url")
private String	documentUrl;
@Column(name="createddate")
private Date	createdDate;
@Column(name="createdby")
private Integer	createdBy;


public ClientInvoiceDocs() {
	super();
}


public ClientInvoiceDocs(Integer clientInvoicDocsId) {
	super();
	this.clientInvoicDocsId = clientInvoicDocsId;
}


public ClientInvoiceDocs(Integer clientInvoicDocsId, Integer bookingLeaseId,
		String docType, String documentUrl, Date createdDate, Integer createdBy) {
	super();
	this.clientInvoicDocsId = clientInvoicDocsId;
	this.bookingLeaseId = bookingLeaseId;
	this.docType = docType;
	this.documentUrl = documentUrl;
	this.createdDate = createdDate;
	this.createdBy = createdBy;
}


	
}
