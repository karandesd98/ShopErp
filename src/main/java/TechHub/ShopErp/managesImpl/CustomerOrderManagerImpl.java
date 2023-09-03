package TechHub.ShopErp.managesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.Managers.CustomerOrderManager;
import TechHub.ShopErp.helper.ResorcenotFoundException;
import TechHub.ShopErp.repository.CustomerOrderRepository;
import TechHub.ShopErp.tables.CustomerOrder;

@Service
public class CustomerOrderManagerImpl implements CustomerOrderManager {
	
	 @Autowired
	 CustomerOrderRepository customerOrderRepository;

	
	    public CustomerOrder save(CustomerOrder obj) {
	        return customerOrderRepository.save(obj);
	    }

	    public List<CustomerOrder> getAll() {
	        return customerOrderRepository.findAll();
	    }

	    public Optional<CustomerOrder> getById(Integer id) {
	    	return customerOrderRepository.findById(id);	    
	    	}

	    public void update(CustomerOrder OldObj) {
	    	customerOrderRepository.save(OldObj);
	    }

	    public void delete(Integer id) {
	    	customerOrderRepository.deleteById(id);
	    }
	    
	    
	}

	

