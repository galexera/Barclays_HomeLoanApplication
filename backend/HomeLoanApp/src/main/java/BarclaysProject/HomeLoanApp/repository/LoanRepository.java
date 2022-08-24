package BarclaysProject.HomeLoanApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BarclaysProject.HomeLoanApp.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan,Integer> {
	
}
