$(document).ready(function() {
  showAllShops();
});


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
                         <li><button class="dropdown-item" type="button">Action</button></li>
                         <li><button class="dropdown-item" type="button">Another action</button></li>
                         <li><button class="dropdown-item" type="button">Something else here</button></li>
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