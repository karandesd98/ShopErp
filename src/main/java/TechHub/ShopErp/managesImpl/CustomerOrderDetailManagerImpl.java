package TechHub.ShopErp.managesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.Managers.CustomerOrderDetailManager;
import TechHub.ShopErp.repository.CustomerOrderDetailRepository;
import TechHub.ShopErp.tables.CustomerOrderDetail;

@Service
public class CustomerOrderDetailManagerImpl implements CustomerOrderDetailManager {
	
	@Autowired
	CustomerOrderDetailRepository customerOrderDetailRepository;

	public CustomerOrderDetail save(CustomerOrderDetail obj) {
		return customerOrderDetailRepository.save(obj);
	}

	public List<CustomerOrderDetail> getAll() {
		return customerOrderDetailRepository.findAll();
	}

	public Optional<CustomerOrderDetail> getById(Integer id) {
		return customerOrderDetailRepository.findById(id);
	}

	public void update(CustomerOrderDetail OldObj) {
		customerOrderDetailRepository.save(OldObj);
	}

	public void delete(Integer id) {
		customerOrderDetailRepository.deleteById(id);
	}

}
