package TechHub.ShopErp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import TechHub.ShopErp.tables.*;

import TechHub.ShopErp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;

	/*
	 * public User findCustomerById(Integer customerId) { User user = new User();
	 * Optional<User> optionalUser = userRepository.findById(customerId); if
	 * (optionalUser.isPresent()) { user = optionalUser.get(); } return user; }
	 */
	
	/*
	 * public ResponseEntity<String> deleteCustomer(String email) {
	 * userRepository.deleteCustomer(email); return
	 * ResponseEntity.ok("User deleted successfully"); }
	 */
}
