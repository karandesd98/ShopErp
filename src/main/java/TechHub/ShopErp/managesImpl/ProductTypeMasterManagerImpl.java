package TechHub.ShopErp.managesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.Managers.ProductTypeMasterManager;
import TechHub.ShopErp.dao.ProductTypeMasterDao;

@Service
public class ProductTypeMasterManagerImpl implements ProductTypeMasterManager {
	
	@Autowired
	ProductTypeMasterDao productTypeMasterDao;

	@Override
	public void saveNewProductType(Object... objects) {
		productTypeMasterDao.saveNewProductType(objects);
	}

}
