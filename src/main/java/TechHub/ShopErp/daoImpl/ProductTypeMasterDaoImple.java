package TechHub.ShopErp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Service;

import TechHub.ShopErp.dao.ProductTypeMasterDao;
import TechHub.ShopErp.utilityAndSecurity.DataBaseConnectionUtility;

@Service
public class ProductTypeMasterDaoImple implements ProductTypeMasterDao {

	@Override
	public void saveNewProductType(Object... userInfo) {

		String productTypeMasterName=userInfo[0]!=null?userInfo[0].toString():"";
		Integer uniqueNo=userInfo[1]!=null?Integer.parseInt(userInfo[1].toString()) :0;
		
		try {
		Connection con=	DataBaseConnectionUtility.getDataSource().getConnection();
		if(con!=null)
		{
			String query = " insert into product_type_master (PRODUCT_TYPE_MASTER_NAME,UNIQUE_NO)"
			        + " values (?, ?)";
			
			// create the mysql insert prepared statement
		      PreparedStatement preparedStmt = con.prepareStatement(query);
		      preparedStmt.setString (1, productTypeMasterName);
		      preparedStmt.setInt(2, uniqueNo);
		 
		   // execute the prepared statement
		      preparedStmt.execute();
		      
		      preparedStmt.close();
		      con.close();
			
		}
		else
		{
			System.out.println("can not connect to database");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	
		
	}

}
