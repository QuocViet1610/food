$(document).ready(function(){
    var token = localStorage.getItem("token")
    console.log("token"+ token)
    var linkImage = "http://localhost:8080/restaurant";

// ********************************ADD Restaurant*************************************************
    $.ajax({
    method: "GET",
    url:linkImage,
    headers: { 'Authorization':'Bearer '+ token }
  })
    .done(function( msg ) {
     console.log(msg.data)
   
     $.each(msg.data, function (index, value) {
        console.log(value)
        var htmlProduct = `<div class="pro">
        <a href="detail.html?id=${value.id}">
            <img class="img-product"  src="${linkImage}/files/${value.image}" alt="">
            <div class="des">
            <h3>${value.titel}</h3>
            <div class="star">
                <i class="fas fa-star"></i>
                <span style="font-size: 12px; font-weight: bold;">${value.rating}</span>
                <i class="fa-solid fa-utensils" style="color: black; padding-left: 10px;"></i>
                <span style="font-size: 12px; font-weight: bold;">${value.descriptions} </span>
            </div>
            <i class="fa-solid fa-location-dot" style="color: black; padding-left: 3px;"></i>
            <span style="font-size: 12px; font-weight: bold;">${value.address}</span>
            <span style="display:block ;margin-top: 10px;" class ="ship"  id = "ship_${value._freeship }"> <i class="fa-solid fa-truck-fast"></i> Free Ship</span>
        </div>
    </div>
        </a>
        </div>
        `;
        $(".newProduct").append(htmlProduct);
  
        ;});
    });


// ********************************ADD promotion *************************************************
$.ajax({
    method: "GET",
    url:'http://localhost:8080/menu/FoodPromotion',
    headers: { 'Authorization':'Bearer '+ token }
  })
    .done(function( msg ) {
     console.log(msg.data)
     $.each(msg.data, function (index, value) {
        console.log(value)
            var htmlProduct = `<div class="item" data-id="${value.id}">
            <div class="img">
                <span>50% off</span>
                <img class= "img-promotion"src="${linkImage}/files/${value.image}" alt="" data-img ="${value.image}" >
                <div class="view">
                    <a href="chitiet3.html">
                        <i class="fa-solid fa-eye"></i>
                    </a> <br>
                    <i class="fa-solid fa-heart" onclick="themvaoyeuthich(this)"></i>
                </div>
            </div>
            <div class="text">
                <h5 class ="title_promotion">${value.tittle}</h5>
                <div class="star flex">
                    <i class="fas fa-star"> </i> 
                    <i class="fas fa-star"> </i> 
                    <i class="fas fa-star"> </i> 
                    <i class="fas fa-star"> </i> 
                    <i class="fas fa-star"> </i> 
                </div>
                <div class="price flex1">
                    <p><span> ${formatMoney(value.price)}</span><label>200.000</label></p>
                    <i class="fal fa-shopping-cart cart-button"></i>
                </div>
            </div>
        </div>
            `;
    
            $(".promotion").append(htmlProduct);
            ;});
        ;});


//khi ấn vào nút giỏ hàng thì tăng thêm đơn vị

$(document).on('click', '.cart-button', function() {
    var item = $(this).closest('.item'); // Lấy ra phần tử cha có class là 'item'
    var title = item.find('.title_promotion').text(); // Lấy tiêu đề sản phẩm
    var price = item.find('.price span').text().replace(/\D/g,''); // Lấy giá sản phẩm
    var img = item.find('.img-promotion').attr('data-img');
    var idProduct = item.attr('data-id');
    var product = {
        title: title,
        price: price,
        img: img, 
        id: idProduct,
        quantity: 0
    };

    var products = JSON.parse(localStorage.getItem('products')) || [];
    var existingProductIndex = products.findIndex(function(item) {
        return item.title === product.title && item.price === product.price && item.img === product.img && item.id === product.id;
    });
    if (existingProductIndex !== -1) {
        products[existingProductIndex].quantity++;
    } else {
        product.quantity = 1; 
        products.push(product);
    }

    var productJSON = JSON.stringify(products);
    // Lưu chuỗi JSON vào localStorage
    localStorage.setItem('products', productJSON);
    // Hiển thị thông báo hoặc cập nhật giao diện người dùng
    alert("Thêm vào giỏ hàng thành công");
});


function formatMoney(x) {
    VND = x.toLocaleString('vi', {style : 'currency', currency : 'VND'});
    return VND
}

//*****************User**************************** */
var user = JSON.parse(localStorage.getItem("user"));


if (user !== null && user !== undefined) {

    $(".User").text(user.fullName);
    $(".login").click(function() {
     
        $(".login i.fa-right-from-bracket").removeClass("fa-right-from-bracket").addClass("fa-solid fa-right-from-bracket");
        var confirmation = confirm("Do you want to log out?");    
        if (confirmation) {
            localStorage.removeItem("user");
        } else {
            event.preventDefault();
        
        }
    });



} else {
    $(".login i.fa-right-from-bracket").removeClass("fa-right-from-bracket").addClass("fa-user");

}
    });
