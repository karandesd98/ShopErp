package TechHub.ShopErp.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import TechHub.ShopErp.model.User;

@Controller
public class GenerateBillController {

	@GetMapping("/GenerateInvoice")
	public String purchaseOrder(Model model,Principal principal)
	{
		/*
		 * String userName= principal.getName(); User user =
		 * userManager.getUserByUserName(userName); model.addAttribute("user", user);
		 * model.addAttribute("title", "home page");
		 */
	return "GenerateInvoice";
	}
}
