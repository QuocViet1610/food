$(document).ready(function(){
    var token = localStorage.getItem("token");
    var id = parseInt(GetParameterValues('id'))
    var linkFile ='http://localhost:8080/restaurant/files/';


//*******************************phan loai **********************************
    function myFunction() {
        // Loại bỏ class "active" từ tất cả các nút tab
        $(".menu-tab").removeClass("active");
        // Thêm class "active" cho nút được click
        $(this).addClass("active");
        var tabId = $(this).data("tab");
        // Ẩn tất cả các tab content
         $(".tabcontent").hide();   
    if(tabId === 'All'){
        $(".tabcontent").show();
    }else{           
        // Hiển thị tab content tương ứng với nút được click
        $("#" + tabId).show();
    }   
    }
    $(".menu-tabs").on("click", ".menu-tab", myFunction);

    $.ajax({
        method: "GET",
        url: "http://localhost:8080/restaurant/getrestaurantDetial?id="+ id,
        headers: { 'Authorization':'Bearer '+ token }
      })
        .done(function( msg ) {
            var itemsByCategory = [];

            $.each(msg.data.food, function (index, value) {
                if ($.inArray(value.categoryDTO, itemsByCategory) === -1) {
                    itemsByCategory.push(value.categoryDTO)
                  }
                               
            });


// ************************************** add info restaurant****************************************      

            var htmlProduct = `  <div class="information_header">
            <div class="restaurent_img" style="width: 300px;">
              <img src="${linkFile}${msg.data.image}" alt="" >
            </div>
        <div class="infor">
            <h1>${msg.data.title}</h1>
            <h3><i class="fa-solid fa-utensils" style="color: black;"></i>${msg.data.descriptions}</h3>
            <div class="restaurent_inf">
              <div class="rating">
                <i class="fa-solid fa-star"></i> ${msg.data.rating}
              </div>
              <div class="address"><i class="fa-solid fa-location-dot"></i>${msg.data.address}</div>
              <div class="subtitel"><i class="fa-solid fa-subtitles"></i>${msg.data.subtitle}</div>
            </div>
            
            <div class="buy">
              <div class="openHour"><i class="fa-solid fa-hourglass-start"></i> Open:${moment(msg.data.open_date).format("YYYY-MM-DD HH:mm:ss")}</div>
              <div class="ship"><i class="fa-solid fa-truck-fast"></i>${msg.data._freeship}</div>
            </div>
            
            <div class="menu-container">
              <div class="menu-tabs">
              <button class="menu-tab active" data-tab="All"  onclick="myFunction_all()" >All</button>
                <!-- Thêm các tab khác tại đây -->
              </div>
        </div>  
   
          </div>  
          <div class ="plus">
          <div class="inconplus" > <a href="fooddetail.html?id=${id}" ><i class="fa-solid fa-pen-to-square"></i>
               
          </a></div>
          </div>
          </div>
    
            `;

 //*************************************** Add Category ******************************************* */ 

            $(".section_header").append(htmlProduct);
            for (let i = 0; i < itemsByCategory.length; i++) {
                console.log(itemsByCategory[i])
                var category =`<button class="menu-tab" data-tab="${itemsByCategory[i]}" onclick="myFunction()" >${itemsByCategory[i]}</button>`
                $(".menu-tabs").append(category);
                var testimonials__grid = `  <div class="testimonials__grid tabcontent" id=${itemsByCategory[i]}> </div>`
          
                $(".section__container").append(testimonials__grid);
              }
         

 //*************************************** Add Food ******************************************* */ 
            var idRestarant = msg.data.id;
            $.each(msg.data.food, function (index, value) {

                var htmlProduct = `                
                 <div class="pro" data-id= ${value.id} data-idRes=${idRestarant} >
                <img class="img-product" src="${linkFile}${value.image}" data_img="${value.image}" alt="">
                <div class="des">
                    <h4 class ="title">${value.tittle}</h4>
                    <div class="star">
                        <i class="fas fa-star"></i>
                        <span style="font-size: 15px; font-weight: bold;">${value.rating}</span>
                    </div>
                    <div class="price">
                    <span style="display:block; margin-top: 10px; font-size: 15px;">${formatMoney(value.price)}</span>
                    <div class="price-icon">
                    <i class="fa-solid fa-cart-plus"></i>
                    </div>
                    </div>
                </div>
           
        </div>
            
                `;
                $(`#${value.categoryDTO}`).append(htmlProduct);
          
          });
            $(".menu-tabs").on("click", ".menu-tab", myFunction);
       

        });
        $(document).on('click', '.price-icon', function() {
          var item = $(this).closest('.pro');

     

          var title = item.find('.title').text(); 
          var price = item.find('.price span').text();
          var img = item.find('.img-product').attr('data_img');
          var idProduct = item.attr('data-id');
    

          console.log(title);
          console.log(price);
          console.log(img);
          console.log(idProduct);
      });
    

 //*************************************** format Money ******************************************* */ 

        function formatMoney(x) {
            VND = x.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
            return VND
        }
  

 //*************************************** Get URL******************************************* */ 
    function GetParameterValues(param) {
        var url = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for (var i = 0; i < url.length; i++) {
        var urlparam = url[i].split('='); 
        if (urlparam[0] == param) {
        return urlparam[1];
        }
        }
        }
    });

// *******************************ADD CART *********************************
    $(document).on('click', '.price-icon', function() {
      var item = $(this).closest('.pro');
      var title = item.find('.title').text(); 
      var price = item.find('.price span').text().replace(/\D/g, '');;
      var img = item.find('.img-product').attr('data_img');
      var idProduct = item.attr('data-id');
      var idRes = item.attr('data-idRes');
      var product = {
          title: title,
          price: price,
          img: img, 
          id: idProduct,
          idRes: idRes,
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
      alert("successfully added to cart");
  });
