$(document).ready(function() {
  showAllOwners();
});


function showAllOwners()
{
	
	$.ajax({
		url:'getAllOwners.json',
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
      <th scope="col">Owner Name</th>
      <th scope="col">Owner Email</th>
      <th scope="col">Owner Desc</th>
       <th scope="col">Action</th>
    </tr>
  </thead><tbody>`;
  
  data.forEach(function(ownerobj,index){
	  const{ownerName='',ownerEmail='',ownerDesc=''}=ownerobj;
	  boiler +=`<tr>
	             <td scope="row">${++index}</td>
	             <td scope="row">${ownerName}</td>
	             <td scope="row">${ownerEmail}</td>
	             <td scope="row">${ownerDesc}</td>
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

function saveNewOwner()
{
	
	var name=$("#name").val();
    var password=$("#password").val();
    var email=$("#email").val();
     var about=$("#about").val();

	$.ajax({
		url:'saveNewOwner.json',
		type: 'GET',
		data: {
			name: name,
			password: password,
			email:email,
			about:about
		},
		dataType: 'json',
		success: function(data) {
		//	var boiler=`<h1>${data.msg}</h1>`
			swal("Good job!", "Your New Owner Created!", "success");
		},
		error: function(request, error) {
			 alert("Request 1: " + JSON.stringify(request));
		}
});
	
}