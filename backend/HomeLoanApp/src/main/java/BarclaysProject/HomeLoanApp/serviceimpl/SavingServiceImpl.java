package BarclaysProject.HomeLoanApp.serviceimpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BarclaysProject.HomeLoanApp.entity.SavingAccount;
import BarclaysProject.HomeLoanApp.repository.SavingRepository;
import BarclaysProject.HomeLoanApp.service.*;

@Service
public class SavingServiceImpl implements SavingService {

	@Autowired
	private SavingRepository savingRepository;
	
	@Override
	public List<SavingAccount> getAllAccounts() {
		return savingRepository.findAll();
	}

	@Override
	public SavingAccount addAccount(SavingAccount acc) {
		SavingAccount newAcc = new SavingAccount();
		newAcc.setAccountNumber(acc.getAccountNumber());
		newAcc.setBalance(acc.getBalance());
		newAcc.setEmail(acc.getEmail());
		newAcc.setName(acc.getName());
		return savingRepository.save(newAcc);
	}

}
