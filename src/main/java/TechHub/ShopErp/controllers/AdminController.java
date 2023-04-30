package TechHub.ShopErp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import TechHub.ShopErp.Managers.ShopManager;
import TechHub.ShopErp.Managers.UserManager;
import TechHub.ShopErp.model.User;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired 
	public UserManager userManager;
	
	@Autowired 
	public ShopManager shopManager;
	
	@GetMapping("/adminDashBoard")
	public String signin(Model model,Principal principal)
	{
		String userName= principal.getName();
		User user = userManager.getUserByUserName(userName);
		
		if (user.getROLE().equalsIgnoreCase("ADMIN")) {
			model.addAttribute("user", user);
			return "adminDashBoard";
		} else {
			model.addAttribute("title", "home page");
			return "home";
		}
		
	}
	
	
	@GetMapping("/addOwner")
	public String addOwner(Model model,Principal principal)
	{
		String userName= principal.getName();
		User user = userManager.getUserByUserName(userName);
		model.addAttribute("user", user);
		model.addAttribute("title", "home page");
	return "addOwner";
	}
	
	
	//=============================================handeler method started from here==========================//
	
	@GetMapping("/saveNewOwner.json")
	@ResponseBody
	public String saveNewOwner(HttpServletRequest req)
	{
		
	String name=req.getParameter("name")!=null?req.getParameter("name"):"";
	String password=req.getParameter("password")!=null?req.getParameter("password"):"";
	String email=req.getParameter("email")!=null?req.getParameter("email"):"";
	String 	about=req.getParameter("about")!=null?req.getParameter("about"):"";

	userManager.saveNewOwner(name,password,email,about);
	
	System.out.println(name);
	JsonObject jobj=new JsonObject();
	 jobj.addProperty("msg", "welcome sachin in software development business");
	 return new Gson().toJson(jobj);
	}
	
	
	
	@GetMapping("/getAllOwners.json")
	@ResponseBody
	public String getAllOwners(HttpServletRequest req)
	{
		
	List<Object[]> allOwners=userManager.getAllOwners();
	JsonArray jMainArray=new JsonArray();
	for( Object[] ownerArr : allOwners)
	{
		JsonObject jobj=new JsonObject();
		String ownerName=ownerArr[1]!=null?ownerArr[1].toString():"";
		jobj.addProperty("ownerName", ownerName);
		
		String ownerEmail=ownerArr[2]!=null?ownerArr[2].toString():"";
		jobj.addProperty("ownerEmail", ownerEmail);
		
		String ownerDesc=ownerArr[7]!=null?ownerArr[7].toString():"";
		jobj.addProperty("ownerDesc", ownerDesc);
		
		jMainArray.add(jobj);
	}
	
	
	 return new Gson().toJson(jMainArray);
	}
	
	// Add Shop related code started from here...!!!
	
	@GetMapping("/addShop")
	public String addShop(Model model,Principal principal)
	{
		String userName= principal.getName();
		User user = userManager.getUserByUserName(userName);
		model.addAttribute("user", user);
		model.addAttribute("title", "home page");
	return "addShop";
	}
	
	//=============================================handler method started from here==========================//
	
		@GetMapping("/saveNewShop.json")
		@ResponseBody
		public String saveNewShop(HttpServletRequest req)
		{
		System.out.println("Insert into save new SHOP...!!");	
		String shopName=req.getParameter("shopName")!=null?req.getParameter("shopName"):"";
		String shopAddress=req.getParameter("shopAddress")!=null?req.getParameter("shopAddress"):"";
		String shopType=req.getParameter("shopType")!=null?req.getParameter("shopType"):"";
		String 	about=req.getParameter("about")!=null?req.getParameter("about"):"";

		shopManager.saveNewShop(shopName,shopAddress,shopType,about);
		
		System.out.println(shopName);
		JsonObject jobj=new JsonObject();
		 jobj.addProperty("msg", "welcome sachin in software development business");
		 return new Gson().toJson(jobj);
		}
		
		
		
		@GetMapping("/getAllShops.json")
		@ResponseBody
		public String getAllShops(HttpServletRequest req)
		{
			
		List<Object[]> allOwners=userManager.getAllOwners();
		JsonArray jMainArray=new JsonArray();
		for( Object[] ownerArr : allOwners)
		{
			JsonObject jobj=new JsonObject();
			String ownerName=ownerArr[1]!=null?ownerArr[1].toString():"";
			jobj.addProperty("ownerName", ownerName);
			
			String ownerEmail=ownerArr[2]!=null?ownerArr[2].toString():"";
			jobj.addProperty("ownerEmail", ownerEmail);
			
			String ownerDesc=ownerArr[7]!=null?ownerArr[7].toString():"";
			jobj.addProperty("ownerDesc", ownerDesc);
			
			jMainArray.add(jobj);
		}
		
		List<Object[]> allShops=shopManager.getAllShops();
		JsonArray jMainArrayForShop=new JsonArray();
		for( Object[] shopArr : allShops)
		{
			JsonObject jobj=new JsonObject();
			String shopName=shopArr[1]!=null?shopArr[1].toString():"";
			jobj.addProperty("shopName", shopName);
			
			String shopAddress=shopArr[2]!=null?shopArr[2].toString():"";
			jobj.addProperty("shopAddress", shopAddress);
			
			String shopType=shopArr[3]!=null?shopArr[3].toString():"";
			jobj.addProperty("shopType", shopType);
			
			jMainArray.add(jobj);
		}
		
		
		 return new Gson().toJson(jMainArray);
		}

}