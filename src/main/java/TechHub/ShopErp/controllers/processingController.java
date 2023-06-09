package TechHub.ShopErp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import TechHub.ShopErp.Managers.UserManager;

@RestController
public class processingController {
	
	@Autowired 
	public UserManager userManager;
	
	@GetMapping("/doRegister.json")
	public String doRegister(HttpServletRequest req)
	{
		
	String name=req.getParameter("name")!=null?req.getParameter("name"):"";
	String password=req.getParameter("password")!=null?req.getParameter("password"):"";
	String email=req.getParameter("email")!=null?req.getParameter("email"):"";
	String 	about=req.getParameter("about")!=null?req.getParameter("about"):"";

	userManager.saveUser(name,password,email,about);
	
	System.out.println(name);
	JsonObject jobj=new JsonObject();
	 jobj.addProperty("msg", "welcome sachin in software development business");
	
		return new Gson().toJson(jobj);
	}
	

}
