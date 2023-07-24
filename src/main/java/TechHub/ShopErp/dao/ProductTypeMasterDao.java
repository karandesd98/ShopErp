package TechHub.ShopErp.dao;

import java.util.List;
import java.util.Map;

public interface ProductTypeMasterDao {
	void saveNewProductType(Object...  objects);
	List<Object[]> getAllProductTypeMasterParent(Object... objects);
	void saveSubProductType(Object... objects);
	List<Object[]> getAllSubProductTypeMaster(Integer producttypemasterid);
	List<Object[]> getAllProductMasterType();
	
}
