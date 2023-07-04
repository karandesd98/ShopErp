package TechHub.ShopErp.managesImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.Managers.PurchaseOrderManager;
import TechHub.ShopErp.dao.PurchaseOrderDao;

@Service
public class PurchaseOrderManagerImpl implements PurchaseOrderManager {

	@Autowired
	PurchaseOrderDao purchaseOrderDao;
	
	@Override
	public List<Object[]> getAllPurchaseOrderOfShop(Integer shopId) {
		
		return purchaseOrderDao.getAllPurchaseOrderOfShop(shopId);
	}

	@Override
	public void saveNewPurchaseOrder(Object... objects) {
		purchaseOrderDao.saveNewPurchaseOrder(objects);
	}

}
