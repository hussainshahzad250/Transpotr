package com.trux.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.ConsumerPaymentGatewayDAO;
import com.trux.model.ConsumerPaymentGateway;
import com.trux.model.ConsumerPaymentStatus;
import com.trux.model.ConsumerWallet;
@Service
public class ConsumerPaymentGatewayService  {

	@Autowired
	private ConsumerPaymentGatewayDAO consumerPaymentGatewayDAO;

 
	public void saveCP(ConsumerPaymentGateway dto) {

		consumerPaymentGatewayDAO.saveCP(dto);
	}

	 
	public void saveCW(ConsumerWallet dto) {

		consumerPaymentGatewayDAO.saveCW(dto);
	}
	
	 
		public void saveCps(ConsumerPaymentStatus dto) {
			dto.setCreatedDate(new Date());
			consumerPaymentGatewayDAO.saveCPS(dto);
		}


	public ConsumerPaymentGatewayDAO getConsumerPaymentGatewayDAO() {
		return consumerPaymentGatewayDAO;
	}


	public void setConsumerPaymentGatewayDAO(
			ConsumerPaymentGatewayDAO consumerPaymentGatewayDAO) {
		this.consumerPaymentGatewayDAO = consumerPaymentGatewayDAO;
	}


	 

}
