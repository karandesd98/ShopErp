package TechHub.ShopErp.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import TechHub.ShopErp.Managers.ProductTypeMasterManager;
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
	
	@Autowired
	public ProductTypeMasterManager productTypeMasterManager;
	
	
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
			
		// to get data form db 	
		List<Object[]> allShops=shopManager.getAllShops();
		
		//processing
		Map<Integer,Object[]> shopInfoMap=new HashMap<Integer,Object[]>();
		Map<Integer,List<Object[]>> shopWiseOwnersMap=new HashMap<Integer,List<Object[]>>();
		
 		for( Object[] shopArr : allShops)
		{
			Integer shopid=shopArr[0]!=null?Integer.parseInt(shopArr[0].toString()):0;
			
			if(!shopInfoMap.containsKey(shopid))
				shopInfoMap.put(shopid, shopArr);
			
			if(!shopWiseOwnersMap.containsKey(shopid))
				shopWiseOwnersMap.put(shopid, new ArrayList<Object[]>());
			
			shopWiseOwnersMap.get(shopid).add(shopArr);
		}
 		
 		
 		JsonArray jMainArray=new JsonArray();
 		for(Map.Entry<Integer,Object[]> en: shopInfoMap.entrySet())
 		{
 			Integer shopid=en.getKey();
 			Object[] shopInfo=en.getValue();
 			
 			String shopname=shopInfo[3]!=null?shopInfo[3].toString():"";
 			String shopAdd=shopInfo[2]!=null?shopInfo[2].toString():"";
 			String shopType=shopInfo[4]!=null?shopInfo[4].toString():"";
 			
 			JsonObject jSopObj=new JsonObject();
 			jSopObj.addProperty("shopid", shopid.toString());
 			jSopObj.addProperty("shopName", shopname.toString());
 			jSopObj.addProperty("shopAdd", shopAdd.toString());
 			jSopObj.addProperty("shopType", shopType.toString());
 			
 			JsonArray sopWonerJArr=new JsonArray();
 			for(Object[] ownerArr  : shopWiseOwnersMap.get(shopid))
			{
				Integer ownerId = ownerArr[5] != null ? Integer.parseInt(ownerArr[5].toString()) : 0;
				String ownerName = ownerArr[6] != null ? (ownerArr[6].toString()) : "";
				String ownerEmail = ownerArr[7] != null ? (ownerArr[7].toString()) : "";

				if (ownerId != 0) {
					JsonObject ownerJobj = new JsonObject();
					ownerJobj.addProperty("ownerId", ownerId.toString());
					ownerJobj.addProperty("ownerName", ownerName.toString());
					ownerJobj.addProperty("ownerEmail", ownerEmail.toString());
					sopWonerJArr.add(ownerJobj);
				}
			}
 			
 			jSopObj.add("shopOwners", sopWonerJArr);
 			
 			jMainArray.add(jSopObj);
 			
 		}
 		
		 return new Gson().toJson(jMainArray);
		}
		
		
		@GetMapping("/getAllOwnersToMapShop.json")
		@ResponseBody
		public String getAllOwnersToMapShop(HttpServletRequest req)
		{
		Integer shopid=req.getParameter("shopid")!=null?Integer.parseInt(req.getParameter("shopid")) :0;

		List<Object[]> allOwners=userManager.getAllOwnersToMapShop(shopid);
		
		JsonArray jMainArray=new JsonArray();
		for( Object[] ownerArr : allOwners)
		{
			
			Integer ownerId=ownerArr[0]!=null?Integer.parseInt(ownerArr[0].toString()):0;
			String ownerName=ownerArr[1]!=null?ownerArr[1].toString():"";
			String ownerEmail=ownerArr[2]!=null?ownerArr[2].toString():"";
			String mappedStatus=ownerArr[3]!=null?ownerArr[3].toString():"";

			JsonObject jobj=new JsonObject();
			jobj.addProperty("ownerId", ownerId);
			jobj.addProperty("ownerName", ownerName);
			jobj.addProperty("ownerEmail", ownerEmail);
			jobj.addProperty("mappedStatus", mappedStatus);
			
			jMainArray.add(jobj);
		}
		
		
		 return new Gson().toJson(jMainArray);
		}
		
		
		@GetMapping("/saveNewProductTypeMaster.json")
		@ResponseBody
		public String saveNewProductTypeMaster(HttpServletRequest req)
		{
			
		String ProductMasterName=req.getParameter("ProductMasterName")!=null?req.getParameter("ProductMasterName"):"";
		String uniqueNo=req.getParameter("uniqueNo")!=null?(req.getParameter("uniqueNo")) :"";
		
		productTypeMasterManager.saveNewProductType(ProductMasterName,uniqueNo);
		
		JsonObject jobj=new JsonObject();
		 jobj.addProperty("msg", "welcome in software development business");
		 return new Gson().toJson(jobj);
		}

		
		@GetMapping("/checkBoxValidation.json")
		@ResponseBody
		public String checkBoxValidation(HttpServletRequest req)
		{
   
		Integer shopid=req.getParameter("shopid")!=null?Integer.parseInt(req.getParameter("shopid")) :0;
		Integer userId=req.getParameter("userId")!=null?Integer.parseInt(req.getParameter("userId")) :0;
		String mappedStatus=req.getParameter("checkedValue")!=null?req.getParameter("checkedValue"):"";
		
		boolean isActive=true;
		boolean isDeleted=false;

		System.out.println("enter into controo.."+shopid+" "+userId+ " " +mappedStatus);
		
		if(mappedStatus.equals("checked")) {
		//delete row
//			isDeleted=true;
			shopManager.unMapUser(shopid, userId, mappedStatus, isActive, isDeleted);
		}else {
		//	insert row
			
			shopManager.mapUser(shopid, userId, mappedStatus, isActive, isDeleted);
		}
		
		JsonObject jobj=new JsonObject();
		 jobj.addProperty("msg", "welcome sachin in software development business");
		 return new Gson().toJson(jobj);

		}
		
		@GetMapping("/getAllProductTypeMasterParent.json")
		@ResponseBody
		public String getAllProductTypeMasterParent(HttpServletRequest req)
		{
			
		List<Object[]> productTypeParents=productTypeMasterManager.getAllProductTypeMasterParent();
		
		JsonArray jMainArray=new JsonArray();
		for( Object[] productTypeArr : productTypeParents)
		{
		   Integer productTypeMasterId=productTypeArr[0]!=null?Integer.parseInt(productTypeArr[0].toString()) :0;
		   String productTypeMasterName=productTypeArr[6]!=null?(productTypeArr[6].toString()) :"";
		   String unique_no=productTypeArr[8]!=null?(productTypeArr[8].toString()) :"";

			JsonObject jobj=new JsonObject();
			jobj.addProperty("productTypeMasterId", productTypeMasterId);
			jobj.addProperty("productTypeMasterName", productTypeMasterName);
			jobj.addProperty("unique_no", unique_no);

			jMainArray.add(jobj);
		}
		
		
		 return new Gson().toJson(jMainArray);
		}
		
		@GetMapping("/saveSubProductTypeMaster.json")
		@ResponseBody
		public String saveSubProductTypeMaster(HttpServletRequest req)
		{
			
		String SubProductName=req.getParameter("SubProductName")!=null?req.getParameter("SubProductName"):"";
		 String subUniqueNo=req.getParameter("subUniqueNo")!=null?(req.getParameter("subUniqueNo")) :"";
		 Integer productTypeMasterId=req.getParameter("productTypeMasterId")!=null?Integer.parseInt(req.getParameter("productTypeMasterId").toString()) :0;

		
		productTypeMasterManager.saveSubProductType(SubProductName,subUniqueNo,productTypeMasterId);
		
		JsonObject jobj=new JsonObject();
		 jobj.addProperty("msg", "welcome in software development business");
		 return new Gson().toJson(jobj);
		}
		
		@GetMapping("/getAllSubProductTypeMaster.json")
		@ResponseBody
		public String getAllSubProductTypeMaster(HttpServletRequest req)
		{
	
		Integer producttypemasterid=req.getParameter("producttypemasterid")!=null?Integer.parseInt(req.getParameter("producttypemasterid")) :0;
	
		List<Object[]> productTypeParents=productTypeMasterManager.getAllSubProductTypeMaster(producttypemasterid);
		
		JsonArray jMainArray=new JsonArray();
		for( Object[] productTypeArr : productTypeParents)
		{
		   Integer productTypeMasterId=productTypeArr[0]!=null?Integer.parseInt(productTypeArr[0].toString()) :0;
		   String productTypeMasterName=productTypeArr[6]!=null?(productTypeArr[6].toString()) :"";
		   String unique_no=productTypeArr[8]!=null?(productTypeArr[8].toString()) :"";
		   Integer productTypeMasterID=productTypeArr[7]!=null?Integer.parseInt(productTypeArr[7].toString()) :0;

		   
			JsonObject jobj=new JsonObject();
			jobj.addProperty("productTypeMasterId", productTypeMasterId);
			jobj.addProperty("productTypeMasterName", productTypeMasterName);
			jobj.addProperty("unique_no", unique_no);

			jMainArray.add(jobj); 
		}
		  return new Gson().toJson(jMainArray);
		
		}
		
}
