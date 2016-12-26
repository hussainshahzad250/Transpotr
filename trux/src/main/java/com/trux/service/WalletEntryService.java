package com.trux.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.WalletEntryDao;
import com.trux.model.WalletEntryModel;

@Service
public class WalletEntryService  {
@Autowired
private WalletEntryDao walletEntryDao;


public int saveWalletEntry(WalletEntryModel dto) {
	
	return walletEntryDao.saveWalletEntry(dto);
}


public List<WalletEntryModel> getWalletEntry() {
	
	return walletEntryDao.getWalletEntry();
}


public List<WalletEntryModel> getWalletEntry(String mobile) {
	WalletEntryModel wallate1=new WalletEntryModel("Lakshay Thakur", mobile, new Date(),new Double(2000), new Double(90), new Integer(2090), new Double(200), new Double(150), new Double(200), "xxXxxx", new Double(9999)); 
	WalletEntryModel wallate2=new WalletEntryModel("Lakshay Thakur", mobile, new Date(),new Double(2000), new Double(90), new Integer(2090), new Double(200), new Double(150), new Double(200), "xxXxxx", new Double(9999));
	List<WalletEntryModel> list1=new ArrayList<WalletEntryModel>();
	list1.add(wallate1);
	list1.add(wallate2);
	
	return list1;
	//return walletEntryDao.getWalletEntry(mobile);
}


public WalletEntryModel getWalletEntry(int weId) {
	
	return walletEntryDao.getWalletEntry(weId);
}

}
