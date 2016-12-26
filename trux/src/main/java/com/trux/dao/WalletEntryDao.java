package com.trux.dao;

import java.util.List;

import com.trux.model.WalletEntryModel;

public interface WalletEntryDao {
public int saveWalletEntry(WalletEntryModel dto);
public List<WalletEntryModel> getWalletEntry();
public List<WalletEntryModel> getWalletEntry(String mobile);
public WalletEntryModel getWalletEntry(int weId);
}
