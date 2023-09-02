package TechHub.ShopErp.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import TechHub.ShopErp.dao.CustomerDao;
import TechHub.ShopErp.utilityAndSecurity.HibernateUtility;

@Service
public class CustomerDaoIml implements CustomerDao{

	@Override
	public List<Object[]> getAllCustomerOfShop(Integer shopId) {
		
		Session session = HibernateUtility.getSessionFactory().openSession();
		//String sqlQuery = "select * from my_user where shop_id= :shopId and IS_ENABLED=1;";
		String sqlQuery = "SELECT\r\n"
				+ "		c.user_id AS c_user_id,\r\n"
				+ "		c.customer_id AS c_customer_id,\r\n"
				+ "       c.address AS c_address,\r\n"
				+ "       c.customer_name AS c_customer_name,\r\n"
				+ "       c.email AS c_email,\r\n"
				+ "       c.is_deleted AS c_is_deleted,\r\n"
				+ "       c.mobile_no AS c_mobile_no,\r\n"
				+ "       mu.role AS mu_role,\r\n"
				+ "       mu.image AS mu_image,\r\n"
				+ "       mu.user_id AS mu_user_id\r\n"
				+ "FROM customer_shop_detail AS csd\r\n"
				+ "INNER JOIN customer AS c ON csd.customer_id = c.customer_id\r\n"
				+ "INNER JOIN my_user AS mu ON mu.user_id = c.user_id\r\n"
				+ "WHERE csd.shop_id = :shopId AND c.is_deleted = 1;";
		Query query = session.createSQLQuery(sqlQuery);
		query.setParameter("shopId", shopId);
		List<Object[]> results = query.list();

		return results;
	}

}
