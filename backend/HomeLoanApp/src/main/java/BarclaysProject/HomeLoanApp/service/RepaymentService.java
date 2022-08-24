package BarclaysProject.HomeLoanApp.service;

import java.util.List;

import BarclaysProject.HomeLoanApp.entity.Repayment;


public interface RepaymentService {
	
	public Repayment getEmiById(int id);
	public List<Repayment> getEmiByLoanId(int loan_id);
	public Repayment payEmi(int id);
	public String forPayEmi(int id);
	public String prePayEmi(int id, int months);
	
}
