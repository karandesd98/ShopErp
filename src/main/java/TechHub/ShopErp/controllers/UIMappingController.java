package TechHub.ShopErp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("")
public class UIMappingController {
	
	@GetMapping("/")
	public String home(Model m)
	{
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
	
	@GetMapping("/welcome")
	@ResponseBody
	public String home1()
	{
		System.out.println("welcome to spring boot web appp development man");
		return "home";
	}

}
