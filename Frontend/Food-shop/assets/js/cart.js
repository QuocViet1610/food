$(document).ready(function(){
    var token = localStorage.getItem("token");
    var Cart = localStorage.getItem("products");
    console.log(JSON.parse(Cart));
      
    var linkImage = "http://localhost:8080/restaurant";
    $.ajax({
        method: "GET",
        url: linkImage,
        headers: { 'Authorization':'Bearer '+ token }
      })
        .done(function( msg ) {
          var giohang = JSON.parse(Cart);
            $.each(giohang, function(index, value) {
                var totalPrice = value.quantity * value.price;
                var priceproduce = parseInt(value.price);
                var ttgh = '<tr class="product_food" data-id=' + value.id + '>' +
                    '<td id="deleteProDuct"><i class="far fa-times-circle"></i></td>' +
                    '<td><img src="'+linkImage+"/files/"+ value.img +'" alt=""></td>' +
                    '<td>' + value.title + '</td>' +
                    '<td class="dongia">' + formatCurrency(priceproduce) + '</td>' +
                    '<td class="soluong"><input type="number" value="' + value.quantity + '" min="1"></td>' +
                  
                    '<td class="tongtien">' + formatCurrency(totalPrice) + '</td>' +
                    '</tr>';
            
                $("#mycart").append(ttgh);
            });            
            tinhTongTienDH();
        });
      function formatCurrency(price) {
    return price.toLocaleString('vi', { style: 'currency', currency: 'VND' });
}

$(document).on("click", "#deleteProDuct", function() {
  var confirmation = confirm("you want to delete this?");
  if(confirmation){
    $( this ).closest('tr').remove();
    var productId = $(this).closest('tr').attr('data-id');
    console.log(productId)
    var cartItems = JSON.parse(localStorage.getItem("products")) || [];
    var index = cartItems.findIndex(function(item) {
      return item.id === productId;
  });
  if (index !== -1) {
    cartItems.splice(index, 1);
}

// Cập nhật danh sách giỏ hàng trong localStorage
localStorage.setItem("products", JSON.stringify(cartItems));
    tinhTongTienDH();
  }
});


$(document).on("click", ".soluong input", function() {
  var quantity = $(this).val(); // Get the new quantity from the input field
  var price = $(this).closest('tr').find('.dongia').text().replace(/\D/g, '');
  var productId = $(this).closest('tr').attr('data-id');
  console.log(productId)  


  var cartItems = JSON.parse(localStorage.getItem("products")) || [];
  var index = cartItems.findIndex(function(item) { // vong lap for 
    return item.id === productId;
  });

  if (index !== -1) {

    cartItems[index].quantity = quantity;
  }



  var totalPrice = parseInt(quantity) * parseInt(price); 

  $(this).closest('tr').find('.tongtien').text(formatCurrency(totalPrice)); 
  localStorage.setItem("products", JSON.stringify(cartItems));
  tinhTongTienDH()

});


function tinhTongTienDH() {
  var totalPrices = 0;
  $('td.tongtien').each(function() { // Corrected selector
    totalPrices += parseInt($(this).text().replace(/\D/g, '')); // 
  });
  $("#tong-tien-don-hang").text(formatCurrency(totalPrices)); // Update total price of the order
}
//******************************Thanh Toan ***************************************************/

$(".payment").on("click", function(){
  var giohang = JSON.parse(Cart);

  var userString = localStorage.getItem("user");
  var User = JSON.parse(userString);

  var tongTien = $('#tong-tien-don-hang').text()
  var tongTienNumber = tongTien.replace(/\D/g, '');



  var orderRequest = {
    restaurantID: giohang[0].idRes, 
    userID: User.id,
    price:tongTienNumber,
    food: [] 
};

// Duyệt qua từng món ăn trong giỏ hàng và thêm vào mảng food của orderRequest
$.each(giohang, function(index, value) {
  console.log(value)
    var foodRequest = {
        id: value.id,
        quanity: value.quantity
    };
    orderRequest.food.push(foodRequest);
});

console.log(JSON.stringify(orderRequest))



$.ajax({
  method: "POST",
  url: "http://localhost:8080/Order",
  contentType: "application/json",
  data: JSON.stringify(orderRequest),
  headers: { 'Authorization':'Bearer '+ token }
})
  .done(function( msg ) {

    if(msg.data){
      alert("Payment success")
      localStorage.removeItem('products');
      window.location.href="../../cart.html"
    }else{
    }
  });

});


});