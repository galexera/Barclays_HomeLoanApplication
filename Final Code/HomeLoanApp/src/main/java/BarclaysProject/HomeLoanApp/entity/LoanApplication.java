package BarclaysProject.HomeLoanApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class LoanApplication {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String address;
	
	@Column
	private int loanAmount;
	
	@Column
	private int tenure;
	
	@Column
	private int monthlySalary;
	
	@Column
	private String email;
	
	@Column
	private String status;
	
	@Column
	private int loanId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public LoanApplication(int id, String address, int loanAmount, int tenure, int monthlySalary, String email,
			String status, int loanId) {
		super();
		this.id = id;
		this.address = address;
		this.loanAmount = loanAmount;
		this.tenure = tenure;
		this.monthlySalary = monthlySalary;
		this.email = email;
		this.status = status;
		this.loanId = loanId;
	}

	public LoanApplication() {};

}
