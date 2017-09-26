angular.module('myApp.services').factory("Category", ['$resource', function($resource){
	return $resource("/api/categories/:id", {id: "@id"}, {
        "update": {method: "PUT"},
        "page": {method: "GET", isArray: false}
	});
}])