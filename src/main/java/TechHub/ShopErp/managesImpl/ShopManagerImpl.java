package TechHub.ShopErp.managesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.Managers.ShopManager;
import TechHub.ShopErp.dao.ShopDao;

@Service
public class ShopManagerImpl implements ShopManager{

	@Autowired
	public ShopDao shopDaoObj;
	
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

}
