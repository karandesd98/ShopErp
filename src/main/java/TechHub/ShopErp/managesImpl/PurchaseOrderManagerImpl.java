package TechHub.ShopErp.managesImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.Managers.PurchaseOrderManager;
import TechHub.ShopErp.dao.PurchaseOrderDao;
import TechHub.ShopErp.repository.purchaseOrderRepo;
import TechHub.ShopErp.tables.PurchaseOrder;

@Service
public class PurchaseOrderManagerImpl implements PurchaseOrderManager {

	@Autowired
	PurchaseOrderDao purchaseOrderDao;
	
	@Autowired
	purchaseOrderRepo purchaseOrderRepoObj;
	
	@Override
	public List<Object[]> getAllPurchaseOrderOfShop(Integer shopId) {
		
		return purchaseOrderDao.getAllPurchaseOrderOfShop(shopId);
	}

	@Override
	public void saveNewPurchaseOrder(Object... objects) {
		purchaseOrderDao.saveNewPurchaseOrder(objects);
	}
	
	@Override
	public void deletePurchaseOrder(Integer purchaseOrderId) {
		purchaseOrderRepoObj.deleteById(purchaseOrderId);
	}

	@Override
	public void save(PurchaseOrder purchaseOrder) {
		purchaseOrderRepoObj.save(purchaseOrder);
		
	}

}
