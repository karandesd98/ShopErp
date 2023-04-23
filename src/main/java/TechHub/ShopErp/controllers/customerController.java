package TechHub.ShopErp.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import TechHub.ShopErp.Managers.UserManager;
import TechHub.ShopErp.model.User;

@Controller
@RequestMapping("/customer")
public class customerController {
	
	@Autowired 
	public UserManager userManager;
	
	@GetMapping("/customerDashBoard")
	public String signin(Model model,Principal principal)
	{
		String userName= principal.getName();
		User user = userManager.getUserByUserName(userName);
		
		if (user.getROLE().equalsIgnoreCase("CUSTOMER")) {
			model.addAttribute("user", user);
			return "customerDashBoard";
		} else {
			model.addAttribute("title", "home page");
			return "home";
		}
		
	}

}
