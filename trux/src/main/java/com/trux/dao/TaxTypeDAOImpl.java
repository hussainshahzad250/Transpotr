package com.trux.dao;

import com.trux.model.TaxType;

public class TaxTypeDAOImpl implements TaxTypeDAO {

	private TaxType tax = new TaxType();

	public TaxType getTaxType() {

		return tax;
	}

	public TaxType getTax() {
		return tax;
	}

	public void setTax(TaxType tax) {
		this.tax = tax;
	}

}
