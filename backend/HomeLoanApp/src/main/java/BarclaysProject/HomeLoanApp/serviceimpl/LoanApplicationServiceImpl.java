package BarclaysProject.HomeLoanApp.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BarclaysProject.HomeLoanApp.emailSender.EmailSenderService;
//import com.barclays.homeloan.emailsender.EmailSenderService;
import BarclaysProject.HomeLoanApp.entity.*;
import BarclaysProject.HomeLoanApp.repository.*;
import BarclaysProject.HomeLoanApp.service.*;
import BarclaysProject.HomeLoanApp.utils.EmiManager;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

	@Autowired
	private EmiManager emiManager;

	@Autowired
	private RepaymentRepository repaymentRepository;

	@Autowired
	private SavingRepository savingRep;

	@Autowired
	private LoanApplicationRepository loanAppRepository;

	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;

	@Override
	public LoanApplication addrequest(LoanApplication req) {
		LoanApplication newApp = new LoanApplication();
		newApp.setAddress(req.getAddress());
		newApp.setEmail(req.getEmail());
		newApp.setLoanAmount(req.getLoanAmount());
		newApp.setMonthlySalary(req.getMonthlySalary());
		newApp.setTenure(req.getTenure());

		return loanAppRepository.save(newApp);
	}

	@Override
	public String validate(int id) {
		
		Optional<LoanApplication> newApp = loanAppRepository.findById(id);
		if (newApp==null) {
			System.out.println("in");
			return null;
		}
		LoanApplication app = newApp.get();
		int monthlySalary = app.getMonthlySalary();
		if (monthlySalary * 50 < app.getLoanAmount()) {
			app.setStatus("Declined");
			loanAppRepository.save(app);
			String body = "Dear Account Holder, \n\t We regret to inform you that your application for a home loan of ₹ "+ app.getLoanAmount()+" has not been approved by the bank."
					+ "\nI hereby request you to please come by at our office to meet our Loan Officer, Mr. Abhishek Duklan, anytime during banking hours from Monday to Friday to complete all the formalities."
					+ "\n\nLooking forward to see you.\n"
					+ "\nThank you.\n"
					+ "\nRegards,"
					+ "\n Bank";
			emailSenderService.sendEmail(app.getEmail(), "Loan Approval", body);
			return "Declined: loan should be less than monthlyincome*50";
		}
		app.setStatus("approved");
		String body = "Dear Account Holder, \n\t We are highly pleased to inform you that your application for a home loan of ₹ "+ app.getLoanAmount()+" has been approved by the bank."
				+ "\nI hereby request you to please come by at our office to meet our Loan Officer anytime during banking hours from Monday to Friday to complete all the formalities so that the loan amount can be credited to your account."
				+ "\n\nLooking forward to see you.\n"
				+ "\nThank you.\n"
				+ "\nRegards,"
				+ "\n Bank";
		emailSenderService.sendEmail(app.getEmail(), "Loan Approval", body);
		createLoan(app);

		return "Loan Approved Successfully !!";
	}

	
	public void createLoan(LoanApplication app) {

		Loan newLoan = new Loan();
		newLoan.setInterest(0.07f);
		newLoan.setLoanAmount(app.getLoanAmount());
		newLoan.setStatus("Approved");
		newLoan.setTenure(app.getTenure());
		SavingAccount savAcc = savingRep.findByEmail(app.getEmail());
		newLoan.setSavingAccount(savAcc.getSeqId());
		Loan savedLoan = loanRepository.save(newLoan);
		app.setLoanId(savedLoan.getId());
		loanAppRepository.save(app);

		createRepaymentEmi(savedLoan);

	}

	private void createRepaymentEmi(Loan loan) {

		float amt = loan.getLoanAmount();
		int tenure = loan.getTenure();
		float emi = emiManager.CalculateEmi(amt, tenure);
		LocalDate date = LocalDate.now().plusMonths(1);
		float rate = EmiManager.INTEREST;
		for (int i = 0; i < tenure; i++) {
			float interest = amt * rate;
			float principal = emi - interest;
			Repayment rp = new Repayment();
			rp.setEmi(emi);
			rp.setInterestamount(interest);
			rp.setPrincipalamount(principal);
			rp.setOutstanding(amt);
			rp.setDate(date);
			rp.setStatus("Pending");
			rp.setLoanId(loan);
			repaymentRepository.save(rp);
			amt = amt - principal;
			date = date.plusMonths(1);

		}

	}
	
	@Override
	 public List<LoanApplication> getAllLoan() {
			
			return loanAppRepository.findAll();
		}
	
	@Override
	 public LoanApplication getLoanApplicationById(int id) {
		
		Optional<LoanApplication> loanApplication  = loanAppRepository.findById(id);

		return loanApplication.get();

		}

}
