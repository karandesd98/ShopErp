package TechHub.ShopErp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public ResponseEntity<String> deleteCustomer(String email) {
		customerRepository.deleteCustomer(email);
		return ResponseEntity.ok("User deleted successfully");
	}
}
