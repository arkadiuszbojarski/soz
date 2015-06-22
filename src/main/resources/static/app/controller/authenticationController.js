angular.module('myApp.controllers').controller('AuthenticationController', function($scope, $rootScope, $state, $modal, user, TokenStore) {
	
	$scope.user = user;
	
	$scope.logout = function() {
		TokenStore.clear();
		$rootScope.user = null;
		$state.transitionTo('login');
	}
	
	$scope.open_change_password_form = function() {
		
		var change_password_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/account/passwordChange.html',
    		controller: 'PasswordChangeController'
    	});
	}
	
	$rootScope.isAdmin = function() {
		return $scope.user && $scope.user.role === 'ADMIN';
	}
	
})