package TechHub.ShopErp.Managers;

import java.util.List;
import java.util.Optional;

import TechHub.ShopErp.tables.CustomerOrder;
import TechHub.ShopErp.tables.CustomerOrderDetail;

public interface CustomerOrderDetailManager {
	
	CustomerOrderDetail save(CustomerOrderDetail obj);
	List<CustomerOrderDetail> getAll();
	Optional<CustomerOrderDetail> getById(Integer id);
	void update(CustomerOrderDetail OldObj);
	void delete(Integer id);
}
