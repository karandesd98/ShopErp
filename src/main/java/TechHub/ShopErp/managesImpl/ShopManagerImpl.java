package TechHub.ShopErp.managesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.Managers.ShopManager;
import TechHub.ShopErp.dao.ShopDao;
import TechHub.ShopErp.repository.ShopRepository;
import TechHub.ShopErp.tables.PurchaseOrderDetail;
import TechHub.ShopErp.tables.Shop;

@Service
public class ShopManagerImpl implements ShopManager{

	@Autowired
	public ShopDao shopDaoObj;
	
	@Autowired
	ShopRepository shopRepository;
	
	@Override
	public void saveNewShop(Object... userInfos) {
		shopDaoObj.saveNewShop(userInfos);
	}

	@Override
	public List<Object[]> getAllShops() {
		return shopDaoObj.getAllShops();
	}
	
	@Override
	public void mapUser(Integer shopid, Integer userId, String mappedStatus, boolean isActive, boolean isDeleted) {
		shopDaoObj.mapUser(shopid, userId,mappedStatus, isActive, isDeleted);
		
	}

	@Override
	public void unMapUser(Integer shopid, Integer userId, String mappedStatus, boolean isActive, boolean isDeleted) {
		shopDaoObj.unMapUser(shopid, userId,mappedStatus, isActive, isDeleted);
		
	}

	@Override
	public Shop getShopById(Integer id) {
		Optional<Shop> optionalProduct = shopRepository.findById(id);
        return optionalProduct.orElseGet(() -> new Shop());
	}
	

}
