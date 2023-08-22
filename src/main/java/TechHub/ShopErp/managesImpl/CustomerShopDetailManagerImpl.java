package TechHub.ShopErp.managesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.Managers.CustomerShopDetailManager;
import TechHub.ShopErp.repository.CustomerShopDetailRepository;
import TechHub.ShopErp.tables.CustomerShopDetail;

@Service
public class CustomerShopDetailManagerImpl implements CustomerShopDetailManager {

	@Autowired
	CustomerShopDetailRepository customerShopDetailRepository;
	
	@Override
	public CustomerShopDetail sava(CustomerShopDetail customerShopDetail) {
		return customerShopDetailRepository.save(customerShopDetail);
	}

}
