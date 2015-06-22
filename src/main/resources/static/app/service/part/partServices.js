angular.module('myApp.services').factory("Part", ['$resource', function($resource){
	return $resource("/api/parts/:id", {id: "@id"}, {
		"page": {method: "GET", isArray: false},
        "update": {method: "PUT"}
	});
}])