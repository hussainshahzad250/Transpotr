package com.trux.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trux.model.WalletEntryModel;
import com.trux.service.WalletEntryService;

@Controller
@RequestMapping(value = "/mywallet")
public class WalletEntryController {

	@Autowired
	private WalletEntryService service;

	@ResponseBody
	@RequestMapping(value = "/entry", method = RequestMethod.GET)
	protected String walletEntry(@RequestParam String driverName, @RequestParam String driverNumber,
			@RequestParam Double freightAmount, @RequestParam Double serviceTax, @RequestParam Integer marchant,
			@RequestParam Double truxServiceCommition, @RequestParam Double serviceChargeEntries,
			@RequestParam Double orderCancellationCharge, @RequestParam String giftVoucherOrPromotionCode,
			@RequestParam Double total) {
		Date orderDateTime = new Date();
		WalletEntryModel walletEntry = new WalletEntryModel(driverName, driverNumber, orderDateTime, freightAmount,
				serviceTax, marchant, truxServiceCommition, serviceChargeEntries, orderCancellationCharge,
				giftVoucherOrPromotionCode, total);
		service.saveWalletEntry(walletEntry);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/viewMyWallet", method = RequestMethod.GET)
	protected List<WalletEntryModel> walletEntry(@RequestParam String driverNumber) {
		List<WalletEntryModel> walletEntryModel = service.getWalletEntry(driverNumber);
		return walletEntryModel;

	}

}
