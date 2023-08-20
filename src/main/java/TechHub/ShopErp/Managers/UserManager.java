package TechHub.ShopErp.Managers;

import java.util.List;
import java.util.Map;

import TechHub.ShopErp.model.User;


public interface UserManager {

	TechHub.ShopErp.tables.User save(TechHub.ShopErp.tables.User u);
	void saveUser(Object... userInfo);
    public User getUserByUserName(String userName);
	void saveNewOwner(Object... userInfos);
	List<Object[]> getAllOwners();
	List<Object[]> getAllOwnersToMapShop(Integer shopid);

}
