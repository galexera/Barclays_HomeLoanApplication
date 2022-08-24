package BarclaysProject.HomeLoanApp.service;

import java.util.List;

import BarclaysProject.HomeLoanApp.entity.SavingAccount;

public interface SavingService {
	
	public List<SavingAccount> getAllAccounts();
	public SavingAccount addAccount(SavingAccount acc);

}
