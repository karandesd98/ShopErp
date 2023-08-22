package TechHub.ShopErp.managesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.Managers.CustomerManager;
import TechHub.ShopErp.repository.CustomerRepository;
import TechHub.ShopErp.tables.Customer;

@Service
public class CustomerManagerImpl implements CustomerManager {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer save(Customer c) {
		return customerRepository.save(c);
	}

}
