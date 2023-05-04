package TechHub.ShopErp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;


import TechHub.ShopErp.dao.ShopDao;
import TechHub.ShopErp.utilityAndSecurity.DataBaseConnectionUtility;
import TechHub.ShopErp.utilityAndSecurity.HibernateUtility;
import TechHub.ShopErp.utilityAndSecurity.MultipleResultQuery;

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
			
			String query = " insert into shop (shop_name,shop_address,shop_type,about)"
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
	
       Map<Integer, List<Object[]>> mapObj=null;
		try {
			Connection con=	DataBaseConnectionUtility.getDataSource().getConnection();
			if(con!=null)
			{
				MultipleResultQuery	mrq =new	MultipleResultQuery(con,"CALL sp_getAllShops()");
				mapObj= mrq.execute();
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
			
		
		return mapObj.get(0);
		
	}
	
	@Override
	public void mapUser(Integer shopid, Integer userId, String mappedStatus, boolean isActive, boolean isDeleted) {

		try {
			Connection con = DataBaseConnectionUtility.getDataSource().getConnection();
			if (con != null) {

				String query = " insert into owner_shop_detail (shop_id, user_id, is_deleted, is_active)"
						+ " values (?, ?, ?, ?)";

				// create the mysql insert prepared statement
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, shopid);
				preparedStmt.setInt(2, userId);
				preparedStmt.setBoolean(3, isDeleted);
				preparedStmt.setBoolean(4, isActive);

				// execute the prepared statement
				preparedStmt.execute();

				preparedStmt.close();
				con.close();

			} else {
				System.out.println("can not connect to database");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void unMapUser(Integer shopid, Integer userId, String mappedStatus, boolean isActive, boolean isDeleted) {
		try {
			Connection con = DataBaseConnectionUtility.getDataSource().getConnection();
			if (con != null) {
				String query = " delete from owner_shop_detail WHERE user_id=? and shop_id=? ";

				// create the mysql insert prepared statement
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, userId);
				preparedStmt.setInt(2, shopid);

				// execute the prepared statement
				preparedStmt.execute();

				preparedStmt.close();
				con.close();

			} else {
				System.out.println("can not connect to database");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
