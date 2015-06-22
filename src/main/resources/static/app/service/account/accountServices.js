angular.module('myApp.services').factory("Account", ['$resource', function($resource){
	return $resource("/api/accounts/:id", {id: "@id"}, {
		"page": {method: "GET", isArray: false},
        "update": {method: "PUT"},
        "my": {url: "/api/accounts/my", method: "GET"},
        "changeMyPassword": {url: "/api/accounts/my/password", method: "PUT"},
        "roles": {url: "api/accounts/roles", method: "GET", isArray: true}
	});
}])