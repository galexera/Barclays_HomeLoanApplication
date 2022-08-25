package BarclaysProject.HomeLoanApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import BarclaysProject.HomeLoanApp.entity.Loan;
import BarclaysProject.HomeLoanApp.entity.Repayment;

public interface RepaymentRepository extends JpaRepository<Repayment, Integer> {
	
	List<Repayment> findByLoanIdAndStatus(Loan loanId, String status);
	List<Repayment> findByLoanId(Loan loan_id);

}
