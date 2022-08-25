package BarclaysProject.HomeLoanApp.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BarclaysProject.HomeLoanApp.entity.Loan;
import BarclaysProject.HomeLoanApp.repository.LoanRepository;
import BarclaysProject.HomeLoanApp.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanRepository loanRepository;
	
	@Override
	public Loan getLoanById(int id) {

		Optional<Loan> loan = loanRepository.findById(id);

		return loan.get();

	}
}
