package TechHub.ShopErp.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import TechHub.ShopErp.Managers.InvoiceService;
import TechHub.ShopErp.Managers.UserManager;
import TechHub.ShopErp.exception.InvoiceNotFoundException;
import TechHub.ShopErp.model.User;
import TechHub.ShopErp.pdf.InvoiceDataPdfExport;
import TechHub.ShopErp.tables.Invoice;

@Controller
public class GenerateBillController {

	@Autowired
	private InvoiceService service;

	@Autowired
	private UserManager userManager;

	@GetMapping("/GenerateInvoice")
	public String purchaseOrder(Model model, Principal principal) {

		// String userName= principal.getName();
		String userName = "pratik@satarkar";
		User user = userManager.getUserByUserName(userName);
		model.addAttribute("user", user);
		model.addAttribute("title", "home page");
		return "GenerateInvoice";
	}

//	    @GetMapping("/")
//	    public String showHomePage() {
//	       return "homePage";
//	    }

	@GetMapping("/registerInvoice")
	public String showRegistration(Model model, Principal principal) {
		// String userName= principal.getName();
		String userName = "pratik@satarkar";
		User user = userManager.getUserByUserName(userName);
		model.addAttribute("user", user);
		model.addAttribute("title", "home page");
		return "registerInvoicePage";
	}

	@PostMapping("/saveInvoice")
	public String saveInvoice(@ModelAttribute Invoice invoice, Model model, Principal principal) {

		// String userName= principal.getName();
		String userName = "pratik@satarkar";
		User user = userManager.getUserByUserName(userName);
		model.addAttribute("user", user);
		model.addAttribute("title", "home page");

		service.saveInvice(invoice);
		Long id = service.saveInvice(invoice).getId();
		String message = "Record with id : '" + id + "' is saved successfully !";
		model.addAttribute("message", message);
		return "registerInvoicePage";
	}

	@GetMapping("/getAllInvoices")
	public String getAllInvoices(@RequestParam(value = "message", required = false) String message, Model model) {

		// String userName= principal.getName();
		String userName = "pratik@satarkar";
		User user = userManager.getUserByUserName(userName);
		model.addAttribute("user", user);
		model.addAttribute("title", "home page");

		List<Invoice> invoices = service.getAllInvoices();
		model.addAttribute("list", invoices);
		model.addAttribute("message", message);
		return "allInvoicesPage";
	}

	@GetMapping("/editInvoice")
	public String getEditPage(Model model, Principal principal, RedirectAttributes attributes, @RequestParam Long id) {

		// String userName= principal.getName();
		String userName = "pratik@satarkar";
		User user = userManager.getUserByUserName(userName);
		model.addAttribute("user", user);
		model.addAttribute("title", "home page");

		String page = null;
		try {
			Invoice invoice = service.getInvoiceById(id);
			model.addAttribute("invoice", invoice);
			page = "editInvoicePage";
		} catch (InvoiceNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:getAllInvoices";
		}
		return page;
	}

	@PostMapping("/updateInvoice")
	public String updateInvoice(@ModelAttribute Invoice invoice, RedirectAttributes attributes, Model model,
			Principal principal) {

		// String userName= principal.getName();
		String userName = "pratik@satarkar";
		User user = userManager.getUserByUserName(userName);
		model.addAttribute("user", user);
		model.addAttribute("title", "home page");

		service.updateInvoice(invoice);
		Long id = invoice.getId();
		attributes.addAttribute("message", "Invoice with id: '" + id + "' is updated successfully !");
		return "redirect:getAllInvoices";
	}

	@GetMapping("/deleteInvoice")
	public String deleteInvoice(@RequestParam Long id, RedirectAttributes attributes, Model model,
			Principal principal) {

		// String userName= principal.getName();
		String userName = "pratik@satarkar";
		User user = userManager.getUserByUserName(userName);
		model.addAttribute("user", user);
		model.addAttribute("title", "home page");

		try {
			service.deleteInvoiceById(id);
			attributes.addAttribute("message", "Invoice with Id : '" + id + "' is removed successfully!");
		} catch (InvoiceNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:getAllInvoices";
	}

	/***
	 * Export All data to pdf file
	 */
	@GetMapping("/pdf")
	public ModelAndView exportToPdf() {
		ModelAndView mav = new ModelAndView();
		mav.setView(new InvoiceDataPdfExport());
		// read data from DB
		List<Invoice> list = service.getAllInvoices();
		// send to pdfImpl class
		mav.addObject("list", list);
		return mav;
	}
	
	/***
	 * Export Perticular user data to pdf file
	 */
	@GetMapping("/singleUserPdf")
	public ModelAndView exportSingleCustomerDataToPdf(@RequestParam Long id) {
		System.out.println("Enter into export into single cust method..");
		ModelAndView mav = new ModelAndView();
		mav.setView(new InvoiceDataPdfExport());
		// read data from DB
		List<Invoice> list =Arrays.asList(service.getInvoiceById(id)) ;
		//Invoice invoice = service.getInvoiceById(id);
		// send to pdfImpl class
		mav.addObject("list", list);
		return mav;
	}

	@GetMapping("/shareInvoice")
	public String shareBill() {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("https://graph.facebook.com/v13.0/105873702526534/messages"))
					.header("Authorization",
							"Bearer EAADVNqsfFN4BAHf9YZAkz5FeATN03HIj1ZB17W43ogdMzz41ZBimQMU5pdO6KZA4OjvkdRXAxvsiqzH4cAaPyZCac7orn2ZBpv9HhMFNFkbIBfm4DmzbDKUv0Bg4FqlP4nofQOGBcKfIJA5MEujzLf3iO2tzyacs3TYulCx3m1PV5G8ZBmKhObfwpDKlVFjF5P3hR1b6rawRAZDZD")
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(
							"{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"917378938893\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
					.build();
			HttpClient http = HttpClient.newHttpClient();
			HttpResponse<String> response = http.send(request, BodyHandlers.ofString());
			System.out.println(response.body());

		} catch (URISyntaxException | IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return "shareInvoice";
	}

}
