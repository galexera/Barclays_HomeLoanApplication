package BarclaysProject.HomeLoanApp.constants;

public class SystemConstants {
	
	   // Saving Account Api ...
		public static final String  ADD_SAVINGS_ACCOUNT = "/account/add";
		public static final String  GET_ALL_ACCOUNT = "/account/all";
		
		// Users .....
		public static final String  ADD_USER = "/add";
		public static final String  GET_ALL_USER = "/users";
		public static final String GET_USER_BY_ID="/users/{id}";
		public static final String GET_TOKEN = "/token";
		
		// Repayment api...
		public static final String  EMI_BY_ID = "/emi/{id}";
		public static final String  EMI_TO_CSV = "/emi/export/{loan_id}";
		public static final String  EMI_PAY = "/emi/pay/{id}";
		public static final String  EMI_FOREPAY = "/emi/forepay/{id}";
		public static final String  EMI_PREPAY = "/emi/prepay/{id}";
		
		// Loan Application api...
		public static final String  APPLY_HOME_LOAN = "/apply";
		public static final String  VALIDATE_HOME_LOAN = "/validate/{id}";
		public static final String  GET_HOME_LOAN = "/getLoan";
		public static final String  GET_LOAN_APPLICATION_BY_ID = "/{id}";
		
		
		//Loan api.....
		public static final String LOAN_BY_ID="loanId/{id}";
		
}
