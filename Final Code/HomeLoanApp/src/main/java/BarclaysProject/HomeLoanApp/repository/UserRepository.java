package BarclaysProject.HomeLoanApp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import BarclaysProject.HomeLoanApp.entity.*;
public interface UserRepository extends JpaRepository<User,Integer> {

	 @Query("SELECT u FROM User u WHERE u.email = ?1")
	    public User findByEmail(String email);
//		Optional<User> findByEmail(String email);

}

