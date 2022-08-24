package BarclaysProject.HomeLoanApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BarclaysProject.HomeLoanApp.entity.SavingAccount;

public interface SavingRepository extends JpaRepository<SavingAccount,Integer> {

//	@Query(value = "select * from savingaccount s where s.email = ?1", nativeQuery = true)
	SavingAccount findByEmail(String email);
	
}
