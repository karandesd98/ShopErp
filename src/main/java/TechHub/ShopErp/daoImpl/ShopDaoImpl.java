package TechHub.ShopErp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.dao.ShopDao;
import TechHub.ShopErp.utilityAndSecurity.DataBaseConnectionUtility;
import TechHub.ShopErp.utilityAndSecurity.HibernateUtility;

@Service
public class ShopDaoImpl implements ShopDao{
	
	
	@Override
	public void saveNewShop(Object... userInfo) {
		String shopName=userInfo[0]!=null?userInfo[0].toString():"";
		String shopAddress=userInfo[1]!=null?userInfo[1].toString():"";
		String shopType=userInfo[2]!=null?userInfo[2].toString():"";
		String about=userInfo[3]!=null?userInfo[3].toString():"";
		
		try {
		Connection con=	DataBaseConnectionUtility.getDataSource().getConnection();
		if(con!=null)
		{
			
			String query = " insert into shops (shop_name,shop_address,shop_type,about)"
			        + " values (?, ?, ?, ?)";
			
			// create the mysql insert prepared statement
		      PreparedStatement preparedStmt = con.prepareStatement(query);
		      preparedStmt.setString (1, shopName);
		      preparedStmt.setString (2, shopAddress);
		      preparedStmt.setString   (3, shopType);
//		      preparedStmt.setString(4, "ADMIN");
		      preparedStmt.setString(4, about);
		 
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

	@Override
	public List<Object[]> getAllShops() {
		Session session = HibernateUtility.getSessionFactory().openSession();
		String sqlQuery = "select * from shops";
		Query query = session.createSQLQuery(sqlQuery);
		List<Object[]> results = query.list();

		return results;
	}

}
