var apiclient = (function(){
	
    return {       
		login: function(userData, callback) {
            var promise = $.post({
                url: "/login",
                data: JSON.stringify(userData)
            });
            promise.then(() => callback(null),
                        (error) => callback(error));
		},
		isLogin: function(callback) {
		    var promise = $.get({
                url: "/isLogin"
            });
            promise.then((bool) => callback(bool))
		}		
    }
})();