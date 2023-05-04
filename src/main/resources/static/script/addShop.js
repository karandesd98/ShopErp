$(document).ready(function() {
  showAllShops();
});

function refreshPage() {

    location.reload(true);
}

function showAllShops()
{
	$.ajax({
		url:'getAllShops.json',
		type: 'GET',
		data: {
			
		},
		dataType: 'json',
		success: function(data) {
			var boiler = `
 <table class="table table-bordered border-primary">
  <thead>
    <tr>
      <th scope="col">Sr. No</th>
      <th scope="col">Shop Name</th>
      <th scope="col">Shop Address</th>
      <th scope="col">Shop Type</th>
      <th scope="col">Owner Detail</th>
       <th scope="col">Action</th>
    </tr>
  </thead><tbody>`;
  
  data.forEach(function(shop,index){
	  const{shopid='', shopName='', shopAdd='', shopType='',  shopOwners=[]}= shop;
	  
	  var innerTable=`<table class="table table-bordered border-primary">
	                  <thead></thead><tbody>`;
	  shopOwners.forEach(function(ownerobj,index1){
		  const{ownerId,ownerName,ownerEmail}=ownerobj;
		  innerTable +=`<tr>
		                <td scope="row">${++index1}</td>
		                <td scope="row">${ownerName}</td>
		                <td scope="row">Action</td>
		                </tr>`
	  });
	  innerTable +=` </tbody>
                     </table>`;
	  
	  
	  boiler +=`<tr>
	             <td scope="row">${++index}</td>
	             <td scope="row">${shopName}</td>
	             <td scope="row">${shopAdd}</td>
	             <td scope="row">${shopType}</td>
	             <td scope="row">${innerTable}</td>
	             <td scope="row">
	             <div class="dropdown">
                      <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false"> Action</button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                         <li><button class="dropdown-item" type="button">Edit</button></li>
                         <li><button class="dropdown-item" type="button" onclick="mapOwnersToShop(${shopid})">Map Owners</button></li>
                         <li><button class="dropdown-item" type="button">Delete</button></li>
                       </ul>
                 </div>
	             </td>
	             </tr>`;
	  
  });
  
   boiler +=` </tbody>
              </table>`;
              
         $('#wonersTable').html(boiler);
  
		},
		error: function(request, error) {
			 alert("Request 1: " + JSON.stringify(request));
		}
	});
	
	
	
// swal("Good job!", "onload function working properly!", "success");
}


function addOwnerModel()
{
	$('#addNewOwner').modal('show');
}

function saveNewShop()
{
	
	var shopName=$("#shop_name").val();
    var shopAddress=$("#shop_address").val();
    var shopType=$("#shop_type").val();
     var about=$("#about").val();

	$.ajax({
		url:'saveNewShop.json',
		type: 'GET',
		data: {
			shopName: shopName,
			shopAddress: shopAddress,
			shopType:shopType,
			about:about
		},
		dataType: 'json',
		success: function(data) {
		//	var boiler=`<h1>${data.msg}</h1>`
			swal("Good job!", "Your New Shop is Created!", "success");
		},
		error: function(request, error) {
			 alert("Request 1: " + JSON.stringify(request));
		}
});

$(document).ready(function() {
  showAllShops();
});

}


function mapOwnersToShop(shopid)
{
//	let shopid=shopid;
	
	// swal("Good job!", "Owner Mapped!", "success");
	$('#mapOwnerToShop').modal('show');
	$.ajax({
		url:'getAllOwnersToMapShop.json',
		type: 'GET',
		data: {
			shopid:shopid
		},
		dataType: 'json',
		success: function(data) {
			var boiler = `
 <table class="table table-bordered border-primary">
  <thead>
    <tr>
      <th scope="col">Sr. No</th>
      <th scope="col">Owner Name</th>
      <th scope="col">Owner Email</th>
      <th scope="col">Mapped Status</th>
    </tr>
  </thead><tbody>`;
  
  data.forEach(function(ownerobj,index){
	  const{ownerId='', ownerName='',ownerEmail='', mappedStatus=''}=ownerobj;
	  
	  var checkedValue=mappedStatus=='mapped'?'checked':'';
	  var checkbox=`<div class="form-check">
                       <input class="form-check-input" type="checkbox" onclick="checkBoxValidation(${ownerId}, ${shopid}, '${mappedStatus}' )" value="" id="flexCheckDefault" ${checkedValue}>
                       <label class="form-check-label" for="flexCheckDefault"></label>
                       </div>`;
	  
	  boiler +=`<tr>
	             <td scope="row">${++index}</td>
	             <td scope="row">${ownerName}</td>
	             <td scope="row">${ownerEmail}</td>
	             <td scope="row">${checkbox}</td>
	             </tr>`;
	  
  });
  
   boiler +=` </tbody>
              </table>`;
      
      $('#mapOwnerToShopBody').html(boiler);
  
		},
		error: function(request, error) {
			 alert("Request 1: " + JSON.stringify(request));
		}
	});
	
	

	
	
}


// When we click on checkbox then owner shop detail table will be affected releted code here...!!!

function checkBoxValidation(userId,shopid,mappedStatus)
{
	
	
	console.log(userId+' '+ shopid+' '+ mappedStatus);
	var checkedValue=mappedStatus=='mapped'?'checked':'unChecked';
	
	
	$.ajax({
		url:'checkBoxValidation.json',
		type: 'GET',
		data: {
			userId: userId,
			checkedValue: checkedValue,
			shopid:shopid,
		},
		dataType: 'json',
		success: function(data) {
		//	var boiler=`<h1>${data.msg}</h1>`
//			swal("Good job!", "Your New Shop is Created!", "success");
		},
		error: function(request, error) {
			 alert("Request 1: " + JSON.stringify(request));
		}
});
	
}


	
