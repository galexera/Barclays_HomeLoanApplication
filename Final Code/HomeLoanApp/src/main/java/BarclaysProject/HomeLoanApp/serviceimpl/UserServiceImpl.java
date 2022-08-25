package BarclaysProject.HomeLoanApp.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import BarclaysProject.HomeLoanApp.entity.User;
import BarclaysProject.HomeLoanApp.repository.UserRepository;
import BarclaysProject.HomeLoanApp.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	
	
	@Override
	public User addAccount(User user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setName(user.getName());
		newUser.setPassword(user.getPassword());
		
		return userRepository.save(newUser);
	}
	
	@Override
	public User getUserById(int id) {

		Optional<User> user = userRepository.findById(id);

		return user.get();

	}

}
