package com.trux.dao;

import java.util.List;

import com.trux.model.ClientInvoiceDocs;

public interface ClientInvoiceDocsDAO {

	public ClientInvoiceDocs saveClientInvoiceDocs(ClientInvoiceDocs dto);
	public List<ClientInvoiceDocs> getClientInvoiceDocs();
	public List<ClientInvoiceDocs> getClientInvoiceDocsBybookingLeaseId(Integer bookingLeaseId);
	public ClientInvoiceDocs getClientInvoiceDocs(Integer clientInvoicDocsId);
	
}
