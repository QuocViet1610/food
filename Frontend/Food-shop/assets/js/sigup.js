$(document).ready(function() {
    var token = localStorage.getItem("token");
    $(".register").click(function() {
      var emailInput = $('.email').val();
      var passwordInput = $('.password').val();
      var FullName = $('.fullname').val();
      $.ajax({
        method: "POST",
        url: "http://localhost:8080/login/signup",
        data: {
          email: emailInput,
          FullName: FullName,
          Password: passwordInput,
        }
      })
      .done(function(msg) {
        if (msg.data) {
            alert("Sign Up Success");

          window.location.href ='./login.html'
        } else {
          alert("the account was existed ");
        }
      });
    });
  });
  