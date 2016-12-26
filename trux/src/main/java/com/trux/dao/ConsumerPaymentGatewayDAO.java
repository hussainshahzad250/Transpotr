package com.trux.dao;

import com.trux.model.ConsumerPaymentGateway;
import com.trux.model.ConsumerPaymentStatus;
import com.trux.model.ConsumerWallet;

public interface ConsumerPaymentGatewayDAO {

	public void saveCP(ConsumerPaymentGateway dto);
	public void saveCW(ConsumerWallet dto);
	public void saveCPS(ConsumerPaymentStatus dto);
	
}
