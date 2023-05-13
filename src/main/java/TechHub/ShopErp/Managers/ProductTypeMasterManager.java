package TechHub.ShopErp.Managers;

import java.util.List;
import java.util.Map;

public interface ProductTypeMasterManager {
	void saveNewProductType(Object...  objects);
    List<Object[]> getAllProductTypeMasterParent(Object...  objects);
	void saveSubProductType(Object...  objects);
	List<Object[]> getAllSubProductTypeMaster(Integer producttypemasterid);
}
