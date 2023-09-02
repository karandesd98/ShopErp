package TechHub.ShopErp.managesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.Managers.CustomerManager;
import TechHub.ShopErp.dao.CustomerDao;
import TechHub.ShopErp.repository.CustomerRepository;
import TechHub.ShopErp.tables.Customer;

@Service
public class CustomerManagerImpl implements CustomerManager {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerDao customerDao;
	
	@Override
	public Customer save(Customer c) {
		return customerRepository.save(c);
	}

	@Override
	public List<Object[]> getAllCustomerOfShop(Integer shopId) {
		
		return customerDao.getAllCustomerOfShop(shopId);
	}

}
