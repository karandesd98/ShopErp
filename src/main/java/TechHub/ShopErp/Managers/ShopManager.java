package TechHub.ShopErp.Managers;

import java.util.List;

import TechHub.ShopErp.model.User;
import TechHub.ShopErp.tables.Shop;

public interface ShopManager {
//	void saveUser(Object... userInfo);
//    public User getUserByUserName(String userName);
	
	Shop getShopById(Integer id);
	
	
	void saveNewShop(Object... userInfos);
	List<Object[]> getAllShops();
	
	void mapUser(Integer shopid, Integer userId, String mappedStatus, boolean isActive, boolean isDeleted);
	void unMapUser(Integer shopid, Integer userId, String mappedStatus, boolean isActive, boolean isDeleted);

}
