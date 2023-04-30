package TechHub.ShopErp.Managers;

import java.util.List;

import TechHub.ShopErp.model.User;

public interface ShopManager {
//	void saveUser(Object... userInfo);
//    public User getUserByUserName(String userName);
	void saveNewShop(Object... userInfos);
	List<Object[]> getAllShops();
}
