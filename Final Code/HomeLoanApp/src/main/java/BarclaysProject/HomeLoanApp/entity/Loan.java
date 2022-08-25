package BarclaysProject.HomeLoanApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Loan {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int loanAmount;
	
	@Column
	private float interest;
	
	@Column
	private int tenure;
	
	
	@Column
	private String status;
	
	
	@Column
	private int savingAccount;

	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getLoanAmount() {
		return loanAmount;
	}


	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}


	public float getInterest() {
		return interest;
	}


	public void setInterest(float interest) {
		this.interest = interest;
	}


	public int getTenure() {
		return tenure;
	}


	public void setTenure(int tenure) {
		this.tenure = tenure;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getSavingAccount() {
		return savingAccount;
	}

	

	public void setSavingAccount(int savingAccount) {
		this.savingAccount = savingAccount;
	}


	public Loan(int id, int loanAmount, float interest, int tenure, String status, int savingAccount) {
		super();
		this.id = id;
		this.loanAmount = loanAmount;
		this.interest = interest;
		this.tenure = tenure;
		this.status = status;
		this.savingAccount = savingAccount;
	}

	public Loan() {}
	
	
	
}

