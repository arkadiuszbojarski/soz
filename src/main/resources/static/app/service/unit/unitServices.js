angular.module('myApp.services').factory("Unit", ['$resource', function($resource){
	return $resource("/api/units/:id", {id: "@id"}, {
		"page": {method: "GET", isArray: false},
        "update": {method: "PUT"}
	});
}])