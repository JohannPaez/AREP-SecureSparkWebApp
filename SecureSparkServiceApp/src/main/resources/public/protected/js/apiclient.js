var apiclient = (function(){
	
    return {
        loadTableMessages: function(callback) {
            var promise = $.get({
        		url: "/protected/messages"
            });
        	promise.then(function(data){
        		callback(null, JSON.parse(data));
        	}, function(error) {
        		callback(error, null);
        	});
		}, 
		addMensaje: function(jsonMessage, callback) {
			var promise = $.post({
        		url: "/protected/messages",
        		data: JSON.stringify(jsonMessage),
            });
			//contentType:"application/json"
        	promise.then(function(data){				
        		callback(null, data);
        	}, function(error) {
        		callback(error);
			});			
		},		
		logout: function(callback) {
		    var promise = $.get({
                url: "/protected/logout"
            });
            promise.then((data) => callback(data))
		}
    }
})();