package TechHub.ShopErp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages={
//		"TechHub.ShopErp.controllers.AdminController", "TechHub.ShopErp.Managers.UserManager"})

@SpringBootApplication
public class ShopErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopErpApplication.class, args);
	}

}
