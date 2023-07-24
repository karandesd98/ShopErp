package TechHub.ShopErp.controllers;

import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import TechHub.ShopErp.Managers.PurchaseOrderManager;
import TechHub.ShopErp.helper.FileUploadHelper;
import TechHub.ShopErp.repository.FilePathRepository;
import TechHub.ShopErp.tables.PurchaseOrder;

@Controller
public class PurchaseOrderController {

	@Autowired
	FilePathRepository filePathRepository;

	@Autowired
	FileUploadHelper fileUploadHelper;
	
	@Autowired
	PurchaseOrderManager purchaseOrderManager;



	@PostMapping("/upload")
	@ResponseBody
	public String uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("purchaseOrderId") Integer purchaseOrderId) {
		try {
			// String originalFilename = file.getOriginalFilename();
			// ServletUriComponentsBuilder.fromCurrentContextPath()

			boolean result = fileUploadHelper.uploadFile(file);
			PurchaseOrder order = purchaseOrderManager.findById(purchaseOrderId);
			
			String fileName = "";
			if (result) {
				fileName=file.getOriginalFilename();
			} else {
				System.out.println("Fail");
			}

			order.setUploadedBillPath(fileName);
			purchaseOrderManager.save(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		
		  JsonObject jobj = new JsonObject(); jobj.addProperty("msg","File Uploaded Successfully...sa!"); 
		  return new Gson().toJson(jobj);
		
	}
	

}
