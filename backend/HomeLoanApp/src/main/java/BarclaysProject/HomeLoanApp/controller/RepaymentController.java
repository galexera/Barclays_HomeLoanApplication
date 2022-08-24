package BarclaysProject.HomeLoanApp.controller;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BarclaysProject.HomeLoanApp.constants.SystemConstants;
import BarclaysProject.HomeLoanApp.entity.Repayment;
import BarclaysProject.HomeLoanApp.repository.RepaymentRepository;
import BarclaysProject.HomeLoanApp.service.RepaymentService;
import BarclaysProject.HomeLoanApp.utils.TenureReqUtil;

@RestController
//@RequestMapping("/repay")
public class RepaymentController {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	
	@Autowired
	RepaymentRepository repayRepository;
	
	@Autowired
	RepaymentService repayService;
	
	
	@GetMapping(value = SystemConstants.EMI_BY_ID)
	public ResponseEntity<?> findEmiById(@PathVariable int id){
		try {
			logger.info("api running !! -- > emi by id");
			return new ResponseEntity<>(repayService.getEmiById(id), HttpStatus.OK);
		}
		catch(Exception e){
			logger.error("Error occurred while fetching all EMIs data: " + e.getMessage());
            return new ResponseEntity<>("Error occurred while fetching all EMIs data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value=SystemConstants.EMI_TO_CSV)
    public void exportToCSV(HttpServletResponse response, @PathVariable int loan_id) throws IOException {
		Writer writer = response.getWriter();

        List<Repayment> repay_list = repayService.getEmiByLoanId(loan_id);

        response.setContentType("text/csv");
		response.addHeader("Content-Disposition","attachment; filename=\"Loan_Report.csv\"");
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
        	csvPrinter.printRecord("ID", "Date", "EMI", "Interest Amount", "Outstanding", "Principal Amount", "Status");
            for (Repayment repay : repay_list) {
                csvPrinter.printRecord(repay.getId(), repay.getDate(), repay.getEmi(), repay.getInterestamount(), repay.getOutstanding(), repay.getPrincipalamount(), repay.getStatus());
            }
        } catch (IOException e) {
            logger.error("Error While writing CSV ", e);
        }
    }
	
	@GetMapping(value = SystemConstants.EMI_PAY)
	public ResponseEntity<?> payEmi(@PathVariable int id){
		try {
			
			return new ResponseEntity<>(repayService.payEmi(id), HttpStatus.OK);
		}
		catch(Exception e){
			logger.error("Error occurred while fetching all EMIs data: " + e.getMessage());
            return new ResponseEntity<>("Error occurred while fetching all EMIs data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = SystemConstants.EMI_FOREPAY)
	public ResponseEntity<?> forPayEmi(@PathVariable int id){
		try {
			
			return new ResponseEntity<>(repayService.forPayEmi(id), HttpStatus.OK);
		}
		catch(Exception e){
			logger.error("Error occurred while fetching all EMIs data: " + e.getMessage());
            return new ResponseEntity<>("Error occurred while fetching all EMIs data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = SystemConstants.EMI_PREPAY)
	public ResponseEntity<?> prePayEmi(@PathVariable int id,@RequestBody TenureReqUtil req){
		try {
			return new ResponseEntity<>(repayService.prePayEmi(id,req.getMonths()), HttpStatus.OK);
		}
		catch(Exception e){
			logger.error("Error occurred while fetching all EMIs data: " + e.getMessage());
            return new ResponseEntity<>("Error occurred while fetching all EMIs data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
