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

import TechHub.ShopErp.helper.FileUploadHelper;
import TechHub.ShopErp.repository.FilePathRepository;
import TechHub.ShopErp.tables.PurchaseOrder;

@Controller
public class PurchaseOrderController {

	@Autowired
	FilePathRepository filePathRepository;

	@Autowired
	FileUploadHelper fileUploadHelper;

	String path = "";

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("purchase_order_name") String purchaseOrderName,@RequestParam("purchase_from") String purchaseFrom,
			@RequestParam("purchase_from_mobile_no") String purchaseFromMobileNo,@RequestParam("total_amount") int totalAmount) {
		try {
			// String originalFilename = file.getOriginalFilename();
			// ServletUriComponentsBuilder.fromCurrentContextPath()

			boolean result = fileUploadHelper.uploadFile(file);
			PurchaseOrder order = new PurchaseOrder();
			
			if (result) {
				path = Paths.get(fileUploadHelper.UPLOAD_DIR + "\\" + file.getOriginalFilename()).toString();
			} else {
				System.out.println("Fail");
			}
			order.setPurchaseOrderName(purchaseOrderName);
			order.setPurchaseFrom(purchaseFrom);
			order.setPurchaseFromMobileNo(purchaseFromMobileNo);
			order.setPurchaseOrderTotalAmount(totalAmount);
			order.setUploadedBillPath(path);
			filePathRepository.save(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/purchaseOrder";
		/*
		 * JsonObject jobj = new JsonObject(); jobj.addProperty("msg",
		 * "welcome sachin in software development business"); return new
		 * Gson().toJson(jobj);
		 */
	}

}
