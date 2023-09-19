package TechHub.ShopErp.managesImpl;

import java.util.List;
import java.util.Map;

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

	@Override
	public List<Object[]> getAllProductTypeMasterParent(Object... objects) {
		return productTypeMasterDao.getAllProductTypeMasterParent(objects);
	}

	@Override
	public void saveSubProductType(Object... objects) {
		productTypeMasterDao.saveSubProductType(objects);
		
	}

	@Override
	public List<Object[]> getAllSubProductTypeMaster(Integer producttypemasterid) {
		return	productTypeMasterDao.getAllSubProductTypeMaster(producttypemasterid);
	}

	@Override
	public List<Object[]> getAllProductMasterType() {
		// TODO Auto-generated method stub
		return productTypeMasterDao.getAllProductMasterType();
	}

	@Override
	public List<Object[]> getAllProductofShop(Integer shopId,Integer purchaseOrderId) {
		// TODO Auto-generated method stub
		return productTypeMasterDao.getAllProductofShop(shopId,purchaseOrderId);
	}

}
