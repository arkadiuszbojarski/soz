angular.module('myApp.controllers').controller('AccountsListController', function($scope, $modal, user, accounts, Account) {
    
	$scope.itemsPerPage = accounts.size;
	$scope.currentPage = accounts.number + 1;
	$scope.maxSize = 5;
	$scope.totalItems = accounts.totalElements;
	$scope.sort = accounts.sort[0] ? accounts.sort[0].property : null;
	$scope.reverse = accounts.sort[0] ? !accounts.sort[0].ascending : false;
	$scope.accounts = accounts.content;
	
	$scope.user = user;
    $scope.query = null;
    
    $scope.search = function(query, page) {
    	Account.page({
    		query: query ? query : undefined,
    		page: page - 1,
    		size: $scope.itemsPerPage,
    		direction: $scope.reverse ? "DESC" : "ASC",
    		property: $scope.sort
    	}).$promise.then(function(success) {
    		$scope.itemsPerPage = success.size;
    		$scope.currentPage = success.number + 1;
    		$scope.totalItems = success.totalElements;
    		$scope.sort = success.sort[0] ? success.sort[0].property : null;
    		$scope.reverse = success.sort[0] ? !success.sort[0].ascending : false;
    		$scope.accounts = success.content;
    	});
    }
    
    $scope.clear_search = function() {
    	$scope.query = null;
    	$scope.search($scope.query, 1);
    }
    
    $scope.reverse_order = function() {
    	$scope.reverse = !$scope.reverse;
    	$scope.search($scope.query, $scope.currentPage);
    }
    
    $scope.open_create_account_form = function() {
    	var create_account_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/account/newAccount.html',
    		controller: 'AccountAddController',
    	});
    	
    	create_account_form_instance.result.then(function () {
    		
    	}, function () {
    		$scope.search($scope.query, $scope.currentPage);
    	});
    }
    
    $scope.open_edit_account_form = function(an_account) {
    	var edit_account_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/account/editAccount.html',
    		controller: 'AccountEditController',
    		resolve: {
    			edited: function() {
    				return an_account;
    			},
    			roles: function() {
    				return Account.roles().$promise;
    			}
    		}
    	});
    	
    	edit_account_form_instance.result.then(function () {
    		
    	}, function () {
    		$scope.search($scope.query, $scope.currentPage);
    	});
    }
       
}).controller('AccountAddController', function($scope, $modalInstance, $timeout, focus, Account) {
	
	$scope.account = {};
	$scope.errors = [];
	$scope.alert = null;
	var timer = null;
	
	$scope.action_account = function() {
        new Account({
        	username: $scope.account.username,
        	email: $scope.account.email,
        	password: $scope.account.password,
        	passwordRepeated: $scope.account.passwordRepeated
        }).$save().then(function(success) {
            $scope.account = {};
            $scope.errors = [];            
            focus('email');
            $scope.alert = {type: "success", msg: "new.account.created"};
            $timeout.cancel(timer);
            timer = $timeout(function(){
            	$scope.alert = null;
            }, 3000);
        }, function(fail) {
        	if(fail.status !== 401 && fail.status !== 403) {
        		$scope.errors = fail.data;
        	} else {
        		$timeout.cancel(timer);
        		$modalInstance.dismiss('exit');
        	}
        });
    };

    $scope.exit = function () {
    	$timeout.cancel(timer);
    	$modalInstance.dismiss('exit');
    };
      
}).controller('AccountEditController', function($scope, $modalInstance, $timeout, focus, edited, roles, Account) {
	
	$scope.account = edited;
	$scope.errors = [];
	$scope.alert = null;
	$scope.roles = roles;
	$scope.options = [{value: true}, {value: false}];
	var timer = null;
	
	$scope.action_account = function() {
        Account.update({id: $scope.account.id}, $scope.account, function(success) {
            $scope.account = success;
            $scope.errors = [];
            focus('username');
            $scope.alert = {type: "success", msg: "account.edited"};
            $timeout.cancel(timer);
            timer = $timeout(function(){
            	$scope.alert = null;
            }, 3000);
        }, function(fail) {
            if(fail.status !== 401 && fail.status !== 403) {
            	$scope.errors = fail.data;
            } else {
            	$timeout.cancel(timer);
        		$modalInstance.dismiss('exit');
        	}
        });
    };

    $scope.exit = function () {
    	$timeout.cancel(timer);
    	$modalInstance.dismiss('exit');
    };
      
}).controller('PasswordChangeController', function($scope, $modalInstance, $timeout, focus, Account) {

	$scope.passwordChange = {};
	$scope.errors = [];
	$scope.alert = null;
	var timer = null;
	
	$scope.password_change = function() {
        Account.changeMyPassword($scope.passwordChange, function(success) {
            $scope.passwordChange = {};
            $scope.errors = [];
            focus('passwordOld');
            $scope.alert = {type: "success", msg: "password.edited"};
            $timeout.cancel(timer);
            timer = $timeout(function(){
            	$scope.alert = null;
            }, 3000);
        }, function(fail) {
        	if(fail.status !== 401 && fail.status !== 403) {
        		$scope.errors = fail.data;
        	} else {
        		$timeout.cancel(timer);
        		$modalInstance.dismiss('exit');
        	}
        });
    };

    $scope.exit = function () {
    	$timeout.cancel(timer);
    	$modalInstance.dismiss('exit');
    };
      
})