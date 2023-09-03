package TechHub.ShopErp.Managers;

import java.util.List;
import java.util.Optional;

import TechHub.ShopErp.tables.CustomerOrder;

public interface CustomerOrderManager {
	
	CustomerOrder save(CustomerOrder obj);
	List<CustomerOrder> getAll();
	Optional<CustomerOrder> getById(Integer id);
	void update(CustomerOrder OldObj);
	void delete(Integer id);
}
