package TechHub.ShopErp.dao;

import java.util.List;
import java.util.Map;

import TechHub.ShopErp.model.User;

public interface userDao {
	void saveUser(Object... userInfo);
	User getUserByUserName(String userName);
}
