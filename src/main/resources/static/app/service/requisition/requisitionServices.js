angular.module('myApp.services').factory("Requisition", ['$resource', function($resource){
	return $resource("/api/requisitions/:number", {number: "@number"}, {
		"page": {method: "GET", isArray: false},
        "update": {method: "PUT"},
        "statuses": {url: "/api/requisitions/statuses", method: "GET", isArray: true},
        "history": {url: "/api/requisitions/:number/history", method: "GET", isArray: false}
	});
}])