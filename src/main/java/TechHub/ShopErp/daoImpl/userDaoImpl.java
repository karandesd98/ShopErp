package TechHub.ShopErp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.dao.userDao;
import TechHub.ShopErp.model.User;
import TechHub.ShopErp.utilityAndSecurity.DataBaseConnectionUtility;
import TechHub.ShopErp.utilityAndSecurity.HibernateUtility;


@Service
public class userDaoImpl implements userDao {

	@Autowired
	private BCryptPasswordEncoder passWordEncoder;
	
	@Override
	public void saveUser(Object... userInfo) {
		
		String name=userInfo[0]!=null?userInfo[0].toString():"";
		String password=userInfo[1]!=null?userInfo[1].toString():"";
		String email=userInfo[2]!=null?userInfo[2].toString():"";
		String about=userInfo[3]!=null?userInfo[3].toString():"";
		password=passWordEncoder.encode(password);
		
		try {
		Connection con=	DataBaseConnectionUtility.getDataSource().getConnection();
		if(con!=null)
		{
			
			String query = " insert into my_user (USER_NAME,EMAIL,PASSWORD,ROLE,ABOUT)"
			        + " values (?, ?, ?, ?, ?)";
			
			// create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = con.prepareStatement(query);
		      preparedStmt.setString (1, name);
		      preparedStmt.setString (2, email);
		      preparedStmt.setString   (3, password);
		      preparedStmt.setString(4, "USER");
		      preparedStmt.setString(5, about);
		 
		   // execute the preparedstatement
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
	public User getUserByUserName(String userName) {

        User user=null;

		try {
			Connection con=	DataBaseConnectionUtility.getDataSource().getConnection();
			if(con!=null)
			{
				String query = "select USER_ID, USER_NAME, EMAIL, PASSWORD, ROLE, IS_ENABLED, IMAGE, ABOUT from my_user where EMAIL=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, userName);

				ResultSet myRs = preparedStmt.executeQuery();

				while (myRs.next()) {
					Integer USER_ID = myRs.getInt(1);
					String USER_NAME = myRs.getString(2);
					String EMAIL = myRs.getString(3);
					String PASSWORD = myRs.getString(4);
					String ROLE = myRs.getString(5);
					Boolean enabled = myRs.getBoolean(6);
					String IMAGE = myRs.getString(7);
					String ABOUT = myRs.getString(8);
					
					System.out.println(EMAIL + "     " + EMAIL);
					
					user=new User(USER_ID,USER_NAME,EMAIL,PASSWORD,ROLE,enabled,IMAGE,ABOUT);
				}

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
		
		
		return user;
	
		
		
	}

	@Override
	public void saveNewOwner(Object... userInfo) {
		
		String name=userInfo[0]!=null?userInfo[0].toString():"";
		String password=userInfo[1]!=null?userInfo[1].toString():"";
		String email=userInfo[2]!=null?userInfo[2].toString():"";
		String about=userInfo[3]!=null?userInfo[3].toString():"";
		password=passWordEncoder.encode(password);
		
		try {
		Connection con=	DataBaseConnectionUtility.getDataSource().getConnection();
		if(con!=null)
		{
			
			String query = " insert into my_user (USER_NAME,EMAIL,PASSWORD,ROLE,ABOUT)"
			        + " values (?, ?, ?, ?, ?)";
			
			// create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = con.prepareStatement(query);
		      preparedStmt.setString (1, name);
		      preparedStmt.setString (2, email);
		      preparedStmt.setString   (3, password);
		      preparedStmt.setString(4, "ADMIN");
		      preparedStmt.setString(5, about);
		 
		   // execute the preparedstatement
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
	public List<Object[]> getAllOwners() {
		
		Session session = HibernateUtility.getSessionFactory().openSession();
		String sqlQuery = "select * from my_user where ROLE='ADMIN'";
		Query query = session.createSQLQuery(sqlQuery);
		List<Object[]> results = query.list();

		return results;
	}

}
