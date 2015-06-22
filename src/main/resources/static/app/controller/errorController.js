angular.module('myApp.controllers').controller('ErrorController', function($scope, $modalInstance, error) {
	$scope.error = error;
	
	$scope.exit = function () {
    	$modalInstance.dismiss('exit');
    };
    
})