package TechHub.ShopErp.managesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.Managers.ShopCustomerManager;
import TechHub.ShopErp.repository.ShopCustomerRepository;
import TechHub.ShopErp.tables.ShopCustomer;


@Service
public class ShopCustomerManagerImpl implements ShopCustomerManager {

	@Autowired
	ShopCustomerRepository shopCustomerRepository;

	@Override
	public ShopCustomer save(ShopCustomer shopCustomer) {
		return shopCustomerRepository.save(shopCustomer);
	}
	
	
}
