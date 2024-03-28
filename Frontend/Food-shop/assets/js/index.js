/*----------------Carousel-------------*/
$('.owl-carousel').owlCarousel({
    loop:true,
    margin:10,
    nav:true,
    dots:false,
    navText:["<i class='far fa-arrow-left'></i>","<i class='far fa-arrow-right'></i>"],
    responsive:{
        0:{
            items:1
        },
        768:{
            items:2
        },
        1000:{
            items:4
        }
    }
})
/*----------------Carousel-------------*/

/*----------------Banner Swiper-------------*/
var swiper = new Swiper(".mySwiper", {
  slidesPerView: 1,
  spaceBetween: 30,
  loop: true,
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
});
/*----------------Banner Swiper-------------*/

/*----------------Thêm SP vào giỏ hàng--------------*/


/*----------------Thêm SP vào giỏ hàng--------------*/



/*----------------Tabs SP Mới và SP Hot--------------*/
function opentab(evt, cityName) {
// var i, tabcontent, tablinks;
          
tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

document.getElementById(cityName).style.display = "block";
evt.currentTarget.className += " active";
}

document.querySelector('.tablinks').click();
/*----------------Tabs SP Mới và SP Hot--------------*/


/*----------------Thông báo thêm SP vào trang DS SP yêu thích--------------*/
function themvaoyeuthich(x) {
  alert("Đã thêm vào danh sách yêu thích!");
}
/*----------------Thông báo thêm SP vào trang DS SP yêu thích--------------*/

/*----------------Đồng hồ đếm ngược-----------------------*/


/*----------------Đồng hồ đếm ngược-----------------------*/



/*----------------Popup-----------------------*/

setTimeout(function() {
  document.getElementById('popup').style.display = 'block';
  document.getElementById('overlay').style.display = 'block';
  
  // Đóng popup sau 5 giây
  setTimeout(function() {
      closePopup();
  }, 5000);
}, 1000);

// Hàm đóng popup
function closePopup() {
  document.getElementById('popup').style.display = 'none';
  document.getElementById('overlay').style.display = 'none';
}
/*----------------Popup-----------------------*/

/*----------------Live Chat-----------------------*/

const popup = document.querySelector('.chat-popup');
const chatBtn = document.querySelector('.chat-btn');
const submitBtn = document.querySelector('.submit');
const chatArea = document.querySelector('.chat-area');
const inputElm = document.querySelector('input');
const emojiBtn = document.querySelector('#emoji-btn');
const picker = new EmojiButton();

// Chọn emoji  
window.addEventListener('DOMContentLoaded', () => {

    picker.on('emoji', emoji => {
      document.querySelector('input').value += emoji;
    });
  
    emojiBtn.addEventListener('click', () => {
      picker.togglePicker(emojiBtn);
    });
  });        

// bật tắt live chat 

chatBtn.addEventListener('click', ()=>{
    popup.classList.toggle('show');
})

// send msg 
submitBtn.addEventListener('click', ()=>{
    let userInput = inputElm.value;

    let temp = `<div class="out-msg">
    <span class="my-msg">${userInput}</span>
    <img src="images/logo/kiyeu.jpg" class="avatar">
    </div>`;

    chatArea.insertAdjacentHTML("beforeend", temp);
    inputElm.value = '';

})

/*----------------Live Chat-----------------------*/

/*----------------Nút quay lại đầu trang--------------*/
$(document).ready(function(){
  $(window).scroll(function(){
    if($(this).scrollTop()){
      $('#backtop').fadeIn();
      }
    else{
      $('#backtop').fadeOut();
    }
  });
  $('#backtop').click(function(){
    $('html,body').animate({scrollTop:0},1000);
  });
})
/*----------------Nút quay lại đầu trang--------------*/

