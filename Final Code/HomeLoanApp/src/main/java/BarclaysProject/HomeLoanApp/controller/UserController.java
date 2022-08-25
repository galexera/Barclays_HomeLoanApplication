package BarclaysProject.HomeLoanApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import BarclaysProject.HomeLoanApp.constants.SystemConstants;
import BarclaysProject.HomeLoanApp.entity.User;
import BarclaysProject.HomeLoanApp.service.UserService;


@RestController
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = SystemConstants.GET_ALL_USER)
	public ResponseEntity<?> findAll(){
		try {
			System.out.print("api running !!");
			return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
		}
		catch(Exception e){
			System.out.print("Error occurred while fetching all users data: " + e.getMessage());
            return new ResponseEntity<>("Error occurred while fetching all users data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = SystemConstants.GET_USER_BY_ID)
	public ResponseEntity<?> findUserById(@PathVariable int id){
		try {
			logger.info("api running !!");
			return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
		}
		catch(Exception e){
			logger.error("Error occurred while fetching all User data: " + e.getMessage());
            return new ResponseEntity<>("Error occurred while fetching all Loan data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = SystemConstants.ADD_USER)
	public ResponseEntity<?> addAccount(@RequestBody User user) {
		try {
			return new ResponseEntity<>(userService.addAccount(user), HttpStatus.CREATED);
		}
		catch(Exception e){
			logger.error("Error occurred while adding Account: " + e.getMessage());
            return new ResponseEntity<>("Error occurred while adding Account", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

}
