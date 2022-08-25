package BarclaysProject.HomeLoanApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import BarclaysProject.HomeLoanApp.constants.SystemConstants;
import BarclaysProject.HomeLoanApp.repository.LoanRepository;
import BarclaysProject.HomeLoanApp.service.LoanService;

@RestController
public class LoanController {

	
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	LoanService loanService;
	
	@GetMapping(value = SystemConstants.LOAN_BY_ID)
	public ResponseEntity<?> findLoanById(@PathVariable int id){
		try {
			System.out.print("api running !!");
			return new ResponseEntity<>(loanService.getLoanById(id), HttpStatus.OK);
		}
		catch(Exception e){
			System.out.print("Error occurred while fetching all Loan data: " + e.getMessage());
            return new ResponseEntity<>("Error occurred while fetching all Loan data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

