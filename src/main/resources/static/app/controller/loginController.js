angular.module('myApp.controllers').controller('LoginController', function($scope, $rootScope, $http, $state, $stateParams, TokenStore, Account) {

	$scope.error = $stateParams.error ? $stateParams.error : false;
	
	var authenticate = function(credentials) {
		$http.post('/api/login', credentials).success(function(result, status, headers) {
			$scope.error = false;
			TokenStore.store(headers('X-AUTH-TOKEN'));
			$state.go('authenticated.requisitions');
		}).error(function() {
			$scope.error = true;
			TokenStore.clear();
			$state.go('login');
		});
	}
	
	$scope.login = function() {
		authenticate($scope.credentials);
	}
	
})