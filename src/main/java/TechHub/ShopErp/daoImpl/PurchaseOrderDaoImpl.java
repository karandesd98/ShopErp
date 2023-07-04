package TechHub.ShopErp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.dao.PurchaseOrderDao;
import TechHub.ShopErp.utilityAndSecurity.DataBaseConnectionUtility;
import TechHub.ShopErp.utilityAndSecurity.HibernateUtility;

@Service
public class PurchaseOrderDaoImpl implements PurchaseOrderDao {

	@Override
	public List<Object[]> getAllPurchaseOrderOfShop(Integer shopId) {
		
		Session session = HibernateUtility.getSessionFactory().openSession();
		String sqlQuery = "select * from purchase_order where shop_id= :shopId and IS_DELETED is not true;";
		Query query = session.createSQLQuery(sqlQuery);
		query.setParameter("shopId", shopId);
		List<Object[]> results = query.list();

		return results;
	}

	@Override
	public void saveNewPurchaseOrder(Object... objects) {

		String pName = objects[0] != null ? objects[0].toString() : "";
		String PurchaseBy = objects[1] != null ? (objects[1].toString()) : "";
		String DateOfPurchaseOrder = objects[2] != null ? (objects[2].toString()) : "";
		String GoodsAmount = objects[3] != null ? (objects[3].toString()) : "";
		String otherAmount = objects[4] != null ? (objects[4].toString()) : "";
		String totalAmount = objects[5] != null ? (objects[5].toString()) : "";
		String notForPurchaseOrder = objects[6] != null ? (objects[6].toString()) : "";
        Integer shopId=objects[7]!=null?Integer.parseInt(objects[7].toString()) :0;

		
		
		try {
		Connection con=	DataBaseConnectionUtility.getDataSource().getConnection();
		if(con!=null)
		{
			String query = " insert into purchase_order (PURCHASE_ORDER_NAME,PURCHASE_ORDER_TOTAL_AMOUNT,shop_id)"
			        + " values (?, ?, ?)";
			
			// create the mysql insert prepared statement
		      PreparedStatement preparedStmt = con.prepareStatement(query);
		      preparedStmt.setString (1, pName);
		      preparedStmt.setString(2, totalAmount);
		      preparedStmt.setInt(3, shopId);
		 
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
