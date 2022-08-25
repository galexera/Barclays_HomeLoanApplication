package BarclaysProject.HomeLoanApp.service;

import java.util.List;

import BarclaysProject.HomeLoanApp.entity.User;


public interface UserService {
	
	public List<User> getAllUsers();
	public User addAccount(User user);
	public User getUserById(int id);

}
