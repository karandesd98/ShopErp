package TechHub.ShopErp.managesImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.Managers.UserManager;
import TechHub.ShopErp.dao.userDao;
import TechHub.ShopErp.model.User;



@Service
public class UserManagerImpl implements UserManager {

	@Autowired
	public userDao userDaoObj;
	
	@Override
	public void saveUser(Object... userInfo) {
		userDaoObj.saveUser(userInfo);
		
	}

	@Override
	public User getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDaoObj.getUserByUserName(userName);
	}

	@Override
	public void saveNewOwner(Object... userInfos) {
		// TODO Auto-generated method stub
		userDaoObj.saveNewOwner(userInfos);
	}

	@Override
	public List<Object[]> getAllOwners() {
		// TODO Auto-generated method stub
		return userDaoObj.getAllOwners();
	}

	@Override
	public List<Object[]> getAllOwnersToMapShop(Integer shopid) {
		// TODO Auto-generated method stub
		return userDaoObj.getAllOwnersToMapShop(shopid);
	}

}
