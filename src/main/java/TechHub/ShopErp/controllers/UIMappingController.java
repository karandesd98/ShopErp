package TechHub.ShopErp.controllers;

import java.security.Principal;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import TechHub.ShopErp.Managers.UserManager;
import TechHub.ShopErp.model.User;
import TechHub.ShopErp.utilityAndSecurity.HibernateUtility;


@Controller
@RequestMapping("")
public class UIMappingController {
	
	@Autowired 
	public UserManager userManager;
	
	@GetMapping("/")
	public String home(Model m)
	{
	//	SessionFactory sessionFactory=HibernateUtility.getSessionFactory();  
		m.addAttribute("title","home page");
		return "home";
	}
	

	
	@GetMapping("/about")
	public String about(Model m)
	{
		m.addAttribute("title","about page");
		return "about";
	}
	
	@GetMapping("/signup")
	public String signup(Model m)
	{
		m.addAttribute("title","signup page");
	//	m.addAttribute("user",new User());
		return "signup";
	}
	
	@GetMapping("/signin")
	public String signin()
	{
		return "login";
	}
	
	
}
