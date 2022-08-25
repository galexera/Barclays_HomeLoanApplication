package BarclaysProject.HomeLoanApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BarclaysProject.HomeLoanApp.entity.LoanApplication;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication,Integer> {

}
