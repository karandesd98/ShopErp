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

}
