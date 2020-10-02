var app = (function () {
        
    var tryLogin = function() {
        var email = $("#idEmail").val();
        var password = $("#idPassword").val();
        var userData = {"email": email, "password": password};
        apiclient.login(userData, redirectMessage);
    }

    var redirectMessage = function(error) {
        if (error != null) {
            alert("Usuario o contraseña incorrectos!");
            return;
        }
        location.href = "protected/messages.html";
    }


    return {
        login: function() {
            tryLogin();
        }       
    }
})();
