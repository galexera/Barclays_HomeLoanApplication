package BarclaysProject.HomeLoanApp.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BarclaysProject.HomeLoanApp.entity.Loan;
import BarclaysProject.HomeLoanApp.entity.Repayment;
import BarclaysProject.HomeLoanApp.entity.SavingAccount;
import BarclaysProject.HomeLoanApp.repository.LoanRepository;
import BarclaysProject.HomeLoanApp.repository.RepaymentRepository;
import BarclaysProject.HomeLoanApp.repository.SavingRepository;
import BarclaysProject.HomeLoanApp.service.RepaymentService;
import BarclaysProject.HomeLoanApp.utils.EmiManager;

@Service
public class RepaymentServiceImpl implements RepaymentService {

	@Autowired
	private EmiManager emiManager;
	
	@Autowired
	private SavingRepository savingRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private RepaymentRepository repayRepository;
	
	@Override
	public Repayment getEmiById(int id) {
		Optional<Repayment> repayment = repayRepository.findById(id);

		return repayment.get();
	}

	@Override
	public List<Repayment> getEmiByLoanId(int loan_id) {
		Loan loan = loanRepository.findById(loan_id).get();
		List<Repayment> repayment = repayRepository.findByLoanId(loan);
		return repayment;
	}
	
	@Override
	public Repayment payEmi(int id) {
		Loan loan = loanRepository.findById(id).get();
		int savingAccountId = loan.getSavingAccount();
		List<Repayment> lst = repayRepository.findByLoanIdAndStatus(loan, "Pending");
		Repayment repay = lst.get(0);

		SavingAccount acc = savingRepository.findById(savingAccountId).get();
		float balance = acc.getBalance();
		float deductAmount = repay.getEmi();
		acc.setBalance(balance - deductAmount);
		repay.setStatus("Paid");
		repayRepository.save(repay);
		savingRepository.save(acc);

		return repay;
	}

	@Override
	public String forPayEmi(int id) {
		Loan loan = loanRepository.findById(id).get();

		int savingAccountId = loan.getSavingAccount();
		List<Repayment> paidList = repayRepository.findByLoanIdAndStatus(loan, "Paid");
		if (paidList.size() < 3) {
			return "Loan foreclosure forbidden !";
		}
		List<Repayment> unPaidEmiList = repayRepository.findByLoanIdAndStatus(loan, "Pending");
		Repayment unPaidEmi = unPaidEmiList.get(0);
		unPaidEmiList.remove(0);
		int outstanding = (int) unPaidEmi.getOutstanding();
		unPaidEmi.setInterestamount(0);
		unPaidEmi.setPrincipalamount(0);
		unPaidEmi.setEmi(outstanding);
		unPaidEmi.setDate(LocalDate.now());
		unPaidEmi.setStatus("Paid");
		repayRepository.save(unPaidEmi);
		for (Repayment r : unPaidEmiList) {
			r.setStatus("Cancelled");
			repayRepository.save(r);
		}
		SavingAccount acc = savingRepository.findById(savingAccountId).get();
		float currBalance = acc.getBalance();
		acc.setBalance(currBalance - outstanding);
		savingRepository.save(acc);
		loan.setStatus("Closed");
		loanRepository.save(loan);

		return "Foreclosed Successfully";
	}

	@Override
	public String prePayEmi(int id, int months) {
		Loan loan = loanRepository.findById(id).get();
		int savingAccountId = loan.getSavingAccount();
		SavingAccount savingAccount = savingRepository.findById(savingAccountId).get();
		List<Repayment> lst = repayRepository.findByLoanIdAndStatus(loan, "Pending");
		Repayment repay = lst.get(0);
		if(lst.size()<months) {
			return "please enter correct tenure in months ";
		}
		if(lst.size()==months) {
			return "Please enter tenure between 3 and "+String.valueOf(loan.getTenure()-months);
		}	
		
		
		float totalEmi = repay.getEmi()*months;
		float interest = repay.getInterestamount();
		float newOutstanding = repay.getOutstanding()+interest-totalEmi;
		
		repay.setPrincipalamount(totalEmi-interest);
		repay.setEmi(totalEmi);
		repay.setStatus("Paid");
		repayRepository.save(repay);	
		float balance = savingAccount.getBalance();
		savingAccount.setBalance(balance-totalEmi);
		savingRepository.save(savingAccount);
		
		lst.remove(0);
		float newEmi = emiManager.CalculateEmi(newOutstanding,lst.size() );
		float rate = EmiManager.INTEREST;
		for(Repayment rp: lst) {
			interest = newOutstanding * rate;
			float principal = newEmi - interest;
			rp.setEmi(newEmi);
			rp.setInterestamount(interest);
			rp.setPrincipalamount(principal);
			rp.setOutstanding(newOutstanding);
			repayRepository.save(rp);
			newOutstanding = newOutstanding-principal;
		
		}

		return "Emi Paid succesfully for "+String.valueOf(months)+" months";
	}

}
