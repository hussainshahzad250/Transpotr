package com.trux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.TaxTypeDAO;
import com.trux.model.TaxType;
@Service
public class TaxTypeService  {
@Autowired
	TaxTypeDAO taxTypeDAO;
	 
	public TaxType getTaxType() {
	 return taxTypeDAO.getTaxType();
	}

}
