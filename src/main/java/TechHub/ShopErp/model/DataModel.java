package TechHub.ShopErp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataModel {
	 private Map<Integer, List<String>> data;
	 
	 public DataModel() {
	        // Initialize the map if needed
		 data= new HashMap<>();
	    }

	public Map<Integer, List<String>> getData() {
		return data;
	}

	public void setData(Map<Integer, List<String>> data) {
		this.data = data;
	}
	 
	 

}
