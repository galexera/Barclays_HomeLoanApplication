package BarclaysProject.HomeLoanApp.service;

import java.util.List;

//import org.springframework.stereotype.Service;

import BarclaysProject.HomeLoanApp.entity.LoanApplication;


public interface LoanApplicationService {
	
	LoanApplication addrequest(LoanApplication req);
	
	String validate(int id);
	List<LoanApplication> getAllLoan(); 
	
    LoanApplication getLoanApplicationById(int id);

}
