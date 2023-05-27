package TechHub.ShopErp.controllers;

import java.io.FileOutputStream;
import java.security.Principal;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import TechHub.ShopErp.Managers.InvoiceService;
import TechHub.ShopErp.Managers.UserManager;
import TechHub.ShopErp.exception.InvoiceNotFoundException;
import TechHub.ShopErp.model.User;
import TechHub.ShopErp.pdf.InvoiceDataPdfExport;
import TechHub.ShopErp.tables.Invoice;

import com.twilio.twiml.voice.*;

@Controller
public class GenerateBillController {
	
	 // Twilio Account SID and Auth Token
    private static final String TWILIO_ACCOUNT_SID = "ACe07dc864999575b70dfeb8d3130af1a9";
    private static final String TWILIO_AUTH_TOKEN = "9a3c8678477bce7ce996a07d2c15ec2d";

    // Twilio WhatsApp phone number
    private static final String TWILIO_WHATSAPP_NUMBER = "+14155238886";

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
		
		//String userName= principal.getName();
				String userName= "pratik@satarkar";
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
		
		//String userName= principal.getName();
				String userName= "pratik@satarkar";
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
		
		//String userName= principal.getName();
				String userName= "pratik@satarkar";
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
	public String updateInvoice(@ModelAttribute Invoice invoice, RedirectAttributes attributes, Model model, Principal principal) {
		
		//String userName= principal.getName();
				String userName= "pratik@satarkar";
				User user = userManager.getUserByUserName(userName);
				model.addAttribute("user", user);
				model.addAttribute("title", "home page");	
		
		service.updateInvoice(invoice);
		Long id = invoice.getId();
		attributes.addAttribute("message", "Invoice with id: '" + id + "' is updated successfully !");
		return "redirect:getAllInvoices";
	}

	@GetMapping("/deleteInvoice")
	public String deleteInvoice(@RequestParam Long id, RedirectAttributes attributes, Model model, Principal principal) {
		
		//String userName= principal.getName();
				String userName= "pratik@satarkar";
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
	 * Export data to pdf file
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
	
	
	@GetMapping("/shareInvoice")
	public void shareinvoice() {
		 try {
	            // Generate the PDF
	            Document document = new Document();
	            FileOutputStream outputStream = new FileOutputStream("sample.pdf");
	           // PdfWriter.getInstance(document, outputStream);

	            document.open();
	           // document.add(new Paragraph("Hello, World!"));
	            document.close();
	            outputStream.close();

	            // Share the PDF via WhatsApp
	            String pdfPath = "sample.pdf";
	            String recipientNumber = "7378938893";

	            sendPdfViaWhatsApp(pdfPath, recipientNumber);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
		 
		 public static void sendPdfViaWhatsApp(String pdfPath, String recipientNumber) {
		        try {
		            // Initialize Twilio client
		            Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);

		            // Read the PDF file
		           // byte[] pdfBytes = readPdfFile(pdfPath);

		            // Encode PDF file to Base64
		           // String base64EncodedPDF = Base64.getEncoder().encodeToString(pdfBytes);

		            // Send the PDF via WhatsApp
		            Message.creator(
		                    new PhoneNumber("whatsapp:+" + recipientNumber),
		                    new PhoneNumber(TWILIO_WHATSAPP_NUMBER),
		                    "").create();
		                    //.setMediaUrl(new URL("data:application/pdf;base64," + base64EncodedPDF))
		                   /* .create();*/
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        
		    

				/*
				 * public static byte[] readPdfFile(String filePath) throws Exception {
				 * ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				 * byte[] buffer = new byte[1024]; int bytesRead; try (InputStream inputStream =
				 * new FileInputStream(filePath)) { while ((bytesRead =
				 * inputStream.read(buffer)) != -1) { byteArrayOutputStream.write(buffer, 0,
				 * bytesRead); } } return byteArrayOutputStream.toByteArray(); }
				 */
	}
}
