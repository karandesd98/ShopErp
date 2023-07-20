package TechHub.ShopErp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import TechHub.ShopErp.utilityAndSecurity.HibernateUtility;

//@SpringBootApplication(scanBasePackages={
//		"TechHub.ShopErp.controllers.AdminController", "TechHub.ShopErp.Managers.UserManager"})

@SpringBootApplication
// @Import(HibernateUtility.class)
public class ShopErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopErpApplication.class, args);
	}

}
