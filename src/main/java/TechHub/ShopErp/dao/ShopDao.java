package TechHub.ShopErp.dao;

import java.util.List;

import TechHub.ShopErp.model.User;

public interface ShopDao {
//	void saveUser(Object... userInfo);
//	User getUserByUserName(String userName);
	void saveNewShop(Object... userInfos);
	List<Object[]> getAllShops();
	
	void mapUser(Integer shopid, Integer userId, String mappedStatus, boolean isActive, boolean isDeleted);
	void unMapUser(Integer shopid, Integer userId, String mappedStatus, boolean isActive, boolean isDeleted);


}
