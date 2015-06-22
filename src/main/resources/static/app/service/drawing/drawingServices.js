angular.module('myApp.services').factory("Drawing", ['$resource', function($resource){
	return $resource("/api/drawings/:id", {id: "@id"}, {
		"page": {method: "GET", isArray: false},
        "update": {method: "PUT"}
	});
}])