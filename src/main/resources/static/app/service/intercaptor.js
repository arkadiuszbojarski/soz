angular.module('myApp').factory('Interceptor', ['$q', '$injector', function($q, $injector, TokenStore){
    return {
    	request: function(config) {
    		var authToken = TokenStore.retrieve();
    		if(authToken) {
    			config.headers['X-AUTH-TOKEN'] = authToken;
    		}
    		return config;
    	},
        response: function(response){
            return response || $q.when(response);
        },
        responseError: function(rejection) {
            if (rejection.status === 401) {
            	$injector.get('$state').go('login', null, {reload: true});
            } else if(rejection.status === 403) {
                $injector.get('$state').go('authenticated.forbidden', null, {reload: true});
            }
            
            return $q.reject(rejection);
        }
    }
}])