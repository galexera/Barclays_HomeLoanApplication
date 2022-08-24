package BarclaysProject.HomeLoanApp.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity
@Table
public class Repayment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "local_date", columnDefinition = "DATE")
	private LocalDate date;
	
	@Column
	private float emi;
	
	@Column
	private float principalamount;
	
	@Column
	private float interestamount;
	
	@Column
	private float outstanding;

	@Column
	private String status;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "loanId", nullable = false)
	private Loan loanId;
	
	public Repayment() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public float getEmi() {
		return emi;
	}

	public void setEmi(float emi) {
		this.emi = emi;
	}

	public float getPrincipalamount() {
		return principalamount;
	}

	public void setPrincipalamount(float principalamount) {
		this.principalamount = principalamount;
	}

	public float getInterestamount() {
		return interestamount;
	}

	public void setInterestamount(float interestamount) {
		this.interestamount = interestamount;
	}

	public float getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(float outstanding) {
		this.outstanding = outstanding;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Loan getLoanId() {
		return loanId;
	}

	public void setLoanId(Loan loanId) {
		this.loanId = loanId;
	}

	public Repayment(int id, LocalDate date, float emi, float principalamount, float interestamount, float outstanding,
			String status, Loan loanId) {
		super();
		this.id = id;
		this.date = date;
		this.emi = emi;
		this.principalamount = principalamount;
		this.interestamount = interestamount;
		this.outstanding = outstanding;
		this.status = status;
		this.loanId = loanId;
	}

	
}
