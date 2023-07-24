package TechHub.ShopErp.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import TechHub.ShopErp.Managers.PurchaseOrderManager;
import TechHub.ShopErp.Managers.ShopManager;
import TechHub.ShopErp.Managers.UserManager;
import TechHub.ShopErp.model.User;
import TechHub.ShopErp.tables.PurchaseOrder;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired 
	public UserManager userManager;
	
	@Autowired 
	public ShopManager shopManager;
	
	@Autowired
	public ProductTypeMasterManager productTypeMasterManager;
	
	@Autowired 
	public PurchaseOrderManager purchaseOrderManager;
	
	
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
		
		@GetMapping("/getAllMyShopToAddPurchaseOrder.json")
		@ResponseBody
		public String getAllMyShopToAddPurchaseOrder(HttpServletRequest req,Principal principal)
		{
			
		List<Object[]> myAllShop=shopManager.getAllShops();
		JsonArray jMainArray=new JsonArray();
	
        Map<Integer,Object[]> myMap=new HashMap<>();
		for( Object[] shopArr : myAllShop)
		{
		   Integer shopId=shopArr[0]!=null?Integer.parseInt(shopArr[0].toString()) :0;
		   String shopName=shopArr[3]!=null?(shopArr[3].toString()) :"";

		   if(!myMap.containsKey(shopId))
		   {
			myMap.put(shopId, shopArr);
			
			JsonObject jobj=new JsonObject();
			jobj.addProperty("shopId", shopId);
			jobj.addProperty("shopName", shopName);

			jMainArray.add(jobj);
			
			
		   }
		}
		
		
		 return new Gson().toJson(jMainArray);
		}
		
		
		@GetMapping("/getAllPurchaseOrderOfShop.json")
		@ResponseBody
		public String getAllPurchaseOrderOfShop(HttpServletRequest req,Principal principal)
		{
		Integer shopId=req.getParameter("shopId")!=null?Integer.parseInt(req.getParameter("shopId")) :0;
		List<Object[]> appPurchaseOrder=purchaseOrderManager.getAllPurchaseOrderOfShop(shopId);
		JsonArray jMainArray=new JsonArray();
	
       
		for( Object[] purchaseOrd : appPurchaseOrder)
		{
		   Integer purchaseOrderId=purchaseOrd[0]!=null?Integer.parseInt(purchaseOrd[0].toString()) :0;
		   String purchaseOrderName=purchaseOrd[8]!=null?(purchaseOrd[8].toString()) :"";
		   Integer purchaseOrderTotalAmount=purchaseOrd[9]!=null?Integer.parseInt(purchaseOrd[9].toString()) :0;
		   String PurchaseBy=purchaseOrd[12]!=null?(purchaseOrd[12].toString()) :"";
           String date=purchaseOrd[13]!=null?(purchaseOrd[13].toString()) :"";
           String goodsAmount=purchaseOrd[15]!=null?(purchaseOrd[15].toString()) :"";
           String otherAmount=purchaseOrd[16]!=null?(purchaseOrd[16].toString()) :"";
           String billUploadPath=purchaseOrd[10]!=null?(purchaseOrd[10].toString()) :"";
		   
		   
         JsonObject jObje=new JsonObject();
         jObje.addProperty("purchaseOrderId", purchaseOrderId.toString());
         jObje.addProperty("purchaseOrderName", purchaseOrderName.toString());
         jObje.addProperty("purchaseOrderTotalAmount", purchaseOrderTotalAmount.toString());
         jObje.addProperty("PurchaseBy", PurchaseBy.toString());
         jObje.addProperty("date", date.toString());
         jObje.addProperty("goodsAmount", goodsAmount.toString());
         jObje.addProperty("otherAmount", otherAmount.toString());
         jObje.addProperty("billUploadPath", billUploadPath.toString());
 
         
         
         jMainArray.add(jObje);
		} 
		
		
		 return new Gson().toJson(jMainArray);
		}
		

		
		
		@GetMapping("/saveNewPurchaseOrder.json")
		@ResponseBody
		public String saveNewPurchaseOrder(HttpServletRequest req)
		{
			
		String pName=req.getParameter("pName")!=null?req.getParameter("pName"):"";
		String PurchaseBy=req.getParameter("PurchaseBy")!=null?req.getParameter("PurchaseBy"):"";
		String DateOfPurchaseOrder=req.getParameter("DateOfPurchaseOrder")!=null?req.getParameter("DateOfPurchaseOrder"):"";
		String 	GoodsAmount=req.getParameter("GoodsAmount")!=null?req.getParameter("GoodsAmount"):"";
		String otherAmount=req.getParameter("otherAmount")!=null?req.getParameter("otherAmount"):"";
		String totalAmount=req.getParameter("totalAmount")!=null?req.getParameter("totalAmount"):"";
		String 	notForPurchaseOrder=req.getParameter("notForPurchaseOrder")!=null?req.getParameter("notForPurchaseOrder"):"";
        Integer shopId=req.getParameter("shopId")!=null?Integer.parseInt(req.getParameter("shopId")) :0;
        Date currentDate = new Date();
        
        PurchaseOrder purchaseOrder=new PurchaseOrder();
        purchaseOrder.setPurchaseOrderName(pName);
        purchaseOrder.setPurchaseBy(PurchaseBy);
        purchaseOrder.setPurchaseAt(currentDate);
        purchaseOrder.setPurchaseOrderAmount(Integer.parseInt(GoodsAmount));
        purchaseOrder.setPurchaseOrderOtherAmount(Integer.parseInt(otherAmount));
        purchaseOrder.setPurchaseOrderTotalAmount(Integer.parseInt(totalAmount));
        purchaseOrder.setNote(notForPurchaseOrder);
        purchaseOrder.setShop_id(shopId);
        
        purchaseOrderManager.save(purchaseOrder);
        
	//	purchaseOrderManager.saveNewPurchaseOrder(pName,PurchaseBy,DateOfPurchaseOrder,GoodsAmount,otherAmount,totalAmount,notForPurchaseOrder,shopId);
		
	// 	System.out.println(name);
		JsonObject jobj=new JsonObject();
		 jobj.addProperty("msg", "welcome sachin in software development business");
		 return new Gson().toJson(jobj);
		}
		
		
		@GetMapping("/deletePurchaseOrder.json")
		@ResponseBody
		public String deletePurchaseOrder(HttpServletRequest req)
		{
			
		Integer purchaseOrderId=req.getParameter("purchaseOrderId")!=null?Integer.parseInt(req.getParameter("purchaseOrderId")):0;
	
		purchaseOrderManager.deletePurchaseOrder(purchaseOrderId);
		
	// 	System.out.println(name);
		JsonObject jobj=new JsonObject();
		 jobj.addProperty("msg", "welcome sachin in software development business");
		 return new Gson().toJson(jobj);
		}
		
		Map<Integer,Object[]> productIdWiseProductInfo=null;
		Map<Integer,List<Integer>> parentProductTypeWiseDirectChild=null;
		Integer colSpan=0;
		
		@GetMapping("/getAllProductMasterToAddProductToPurchaseOrder.json")
		@ResponseBody
		public String getAllProductMasterToAddProductToPurchaseOrder(HttpServletRequest req)
		{
			
	
		List<Object[]> allProductTypeMaster=	productTypeMasterManager.getAllProductMasterType();
		
		// to store only parent
		Set<Integer> onlyParentProductTypeSet=new HashSet<Integer>();
		parentProductTypeWiseDirectChild=new HashMap<Integer,List<Integer>>();
		
		for(Object[] objArr : allProductTypeMaster)
		{
			Integer productTypeMasterId=objArr[0]!=null?Integer.parseInt(objArr[0].toString()):0;
			Integer productTypeMasterParentId=objArr[2]!=null?Integer.parseInt(objArr[2].toString()):0;

			if(productTypeMasterParentId!=0)
			{
				parentProductTypeWiseDirectChild.put(productTypeMasterParentId, new ArrayList<Integer>());
			}
			
			if(productTypeMasterParentId==0)
			{
				onlyParentProductTypeSet.add(productTypeMasterId);
			}
			
		}
		
		
		//code to store overAll info
		 productIdWiseProductInfo=new HashMap<Integer,Object[]>();

		for(Object[] objArr : allProductTypeMaster)
		{
			Integer productTypeMasterId=objArr[0]!=null?Integer.parseInt(objArr[0].toString()):0;
			Integer productTypeMasterParentId=objArr[2]!=null?Integer.parseInt(objArr[2].toString()):0;

			if(!productIdWiseProductInfo.containsKey(productTypeMasterId))
				productIdWiseProductInfo.put(productTypeMasterId, objArr);
			
			if(parentProductTypeWiseDirectChild.containsKey(productTypeMasterParentId))
			{
				parentProductTypeWiseDirectChild.get(productTypeMasterParentId).add(productTypeMasterId);
			}
			
		}
		
		JsonArray jArray=new JsonArray();
		for(Integer baseTypeId : onlyParentProductTypeSet)
		{
			JsonObject jobj=getSubTypeJsonObject(baseTypeId);
			jArray.add(jobj);
			colSpan=0;
		}
		
		
	     
		 return new Gson().toJson(jArray);
		}
		
		
		JsonObject getSubTypeJsonObject(Integer baseTypeId)
		{
		Object[] productInfo=	productIdWiseProductInfo.get(baseTypeId);
		// parentProductTypeWiseDirectChild=
			JsonObject productTypeJbj=new JsonObject();
			
			String productTypename=productInfo[1]!=null?productInfo[1].toString():"";
			productTypeJbj.addProperty("productTypeName", productTypename);
			productTypeJbj.addProperty("productTypeId", baseTypeId);
			
			if(parentProductTypeWiseDirectChild.containsKey(baseTypeId))
			{
			JsonArray subTypeArr=new JsonArray();
			List<Integer> subTypeIds=	parentProductTypeWiseDirectChild.get(baseTypeId);
			for(Integer subTypePId : subTypeIds)
			{
				JsonObject subTypeJobj=getSubTypeJsonObject(subTypePId);
				subTypeArr.add(subTypeJobj);
			}
			productTypeJbj.add("subTypeArr", subTypeArr);
			
			// to take colspan
			colSpan = colSpan + subTypeArr.size();
			productTypeJbj.addProperty("colspan", colSpan);
			
			return productTypeJbj;
			}
			else
			{
				JsonArray subTypeArr=new JsonArray();
				productTypeJbj.add("subTypeArr", subTypeArr);
				productTypeJbj.addProperty("colspan", 0);
				return productTypeJbj;
			}
			
			
			//return productTypeJbj;	
		}
		
		
		
}
