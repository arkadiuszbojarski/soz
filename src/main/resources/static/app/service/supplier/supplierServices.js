angular.module('myApp.services').factory("Supplier", ['$resource', function($resource){
	return $resource("/api/suppliers/:id", {id: "@id"}, {
        "update": {method: "PUT"},
        "page": {method: "GET", isArray: false}
	});
}])