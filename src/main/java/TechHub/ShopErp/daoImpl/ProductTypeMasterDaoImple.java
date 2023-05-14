package TechHub.ShopErp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.dao.ProductTypeMasterDao;
import TechHub.ShopErp.utilityAndSecurity.DataBaseConnectionUtility;
import TechHub.ShopErp.utilityAndSecurity.HibernateUtility;

@Service
public class ProductTypeMasterDaoImple implements ProductTypeMasterDao {

	@Override
	public void saveNewProductType(Object... userInfo) {

		String productTypeMasterName=userInfo[0]!=null?userInfo[0].toString():"";
		String uniqueNo=userInfo[1]!=null?(userInfo[1].toString()) :"";
		
		try {
		Connection con=	DataBaseConnectionUtility.getDataSource().getConnection();
		if(con!=null)
		{
			String query = " insert into product_type_master (PRODUCT_TYPE_MASTER_NAME,UNIQUE_NO)"
			        + " values (?, ?)";
			
			// create the mysql insert prepared statement
		      PreparedStatement preparedStmt = con.prepareStatement(query);
		      preparedStmt.setString (1, productTypeMasterName);
		      preparedStmt.setString(2, uniqueNo);
		 
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
	public List<Object[]> getAllProductTypeMasterParent(Object... objects) {
		
		Session session = HibernateUtility.getSessionFactory().openSession();
		String sqlQuery = "select * from product_type_master where PRODUCT_TYPE_MASTER_PARENT_ID is null;";
		Query query = session.createSQLQuery(sqlQuery);
		List<Object[]> results = query.list();

		return results;
	}

	@Override
	public void saveSubProductType(Object... objects) {

		String SubProductName=objects[0]!=null?objects[0].toString():"";
		 String subUniqueNo=objects[1]!=null?(objects[1].toString()) :"";
		 Integer productTypeMasterId=objects[2]!=null?Integer.parseInt(objects[2].toString()) :0;

		
		try {
		Connection con=	DataBaseConnectionUtility.getDataSource().getConnection();
		if(con!=null)
		{
			String query = " insert into product_type_master (PRODUCT_TYPE_MASTER_NAME,UNIQUE_NO,PRODUCT_TYPE_MASTER_PARENT_ID)"
			        + " values (?, ?, ?)";
			
			// create the mysql insert prepared statement
		      PreparedStatement preparedStmt = con.prepareStatement(query);
		      preparedStmt.setString (1, SubProductName);
		      preparedStmt.setString(2, subUniqueNo);
		      preparedStmt.setInt(3, productTypeMasterId);
		 
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
	public List<Object[]> getAllSubProductTypeMaster(Integer producttypemasterid) {
		
		Session session = HibernateUtility.getSessionFactory().openSession();
		String sqlQuery = "select * from product_type_master where PRODUCT_TYPE_MASTER_PARENT_ID= :producttypemasterid";
		Query query = session.createSQLQuery(sqlQuery);
		query.setParameter("producttypemasterid", producttypemasterid);
		List<Object[]> results = query.list();

		return results;
	}

}
