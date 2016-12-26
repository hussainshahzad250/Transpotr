package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.ClientInvoiceDocsDAO;
import com.trux.model.ClientInvoiceDocs;

@Service
public class ClientInvoiceDocsService {
	@Autowired
	private ClientInvoiceDocsDAO clientInvoiceDocsDAO;

	public ClientInvoiceDocs saveClientInvoiceDocs(ClientInvoiceDocs dto) {

		return clientInvoiceDocsDAO.saveClientInvoiceDocs(dto);
	}

	public List<ClientInvoiceDocs> getClientInvoiceDocs() {

		return clientInvoiceDocsDAO.getClientInvoiceDocs();
	}

	public List<ClientInvoiceDocs> getClientInvoiceDocsBybookingLeaseId(
			Integer bookingLeaseId) {

		return clientInvoiceDocsDAO
				.getClientInvoiceDocsBybookingLeaseId(bookingLeaseId);
	}

	public ClientInvoiceDocs getClientInvoiceDocs(Integer clientInvoicDocsId) {

		return clientInvoiceDocsDAO.getClientInvoiceDocs(clientInvoicDocsId);
	}

}
