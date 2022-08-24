package BarclaysProject.HomeLoanApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="savingaccount")
public class SavingAccount {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int seqId;
	
	@Column
	private long accountNumber;
	
	@Column
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column
	private float balance;

	

	
	public SavingAccount(int seqId, long accountNumber, String name, String email, float balance) {
		super();
		this.seqId = seqId;
		this.accountNumber = accountNumber;
		this.name = name;
		this.email = email;
		this.balance = balance;
	}




	public int getSeqId() {
		return seqId;
	}




	public void setSeqId(int seqId) {
		this.seqId = seqId;
	}




	public long getAccountNumber() {
		return accountNumber;
	}




	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public float getBalance() {
		return balance;
	}




	public void setBalance(float balance) {
		this.balance = balance;
	}




	public SavingAccount() {};
	
	
	
	

}
