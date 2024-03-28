$(document).ready(function() {
    var isCucess = false;
    $("#submit1").click(function(){       
        var loginForm = $('#flogin');
        var emailInput = $('.email').val();
        var passwordInput = $('.password').val();
        loginForm.on('submit', function(event) {
            event.preventDefault(); 

        
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (emailInput === '' || passwordInput === '') {
              alert('Vui lòng điền đủ thông tin đăng nhập trước khi gửi.');
              return;
            } else if (!emailPattern.test(emailInput)) {
              alert('Email không hợp lệ. Vui lòng nhập địa chỉ email đúng.');
              return;
            } else {
                console.log(emailInput)
                console.log(passwordInput)
                $.ajax({
                    method: "POST",
                    url: "http://localhost:8080/login/signin",
                    data: { 
                      username: emailInput, 
                      password: passwordInput
                     }
                  })
                    .done(function( msg ) {
            
                      if(msg.success){
                        console.log(msg)
                        isCucess =true;
                        localStorage.setItem("token", msg.data);
                        localStorage.setItem("user",JSON.stringify(msg.descrip));
                        window.location.href ='./index.html'
                      }else{
                       
                        alert("Login failed")
                      }
                    });
            }
            if (!isSuccess) {
              alert("Login failed");
          }
          });
               
            })

//**********************************ADD USER****************************************** */




          });
        
