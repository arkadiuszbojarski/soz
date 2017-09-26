angular.module('myApp.controllers').controller('CategoriesListController', function($scope, $modal, user, categories, Category) {
    
    $scope.itemsPerPage = categories.size;
	$scope.currentPage = categories.number + 1;
	$scope.maxSize = 5;
	$scope.totalItems = categories.totalElements;
	$scope.sort = categories.sort[0] ? categories.sort[0].property : null;
	$scope.reverse = categories.sort[0] ? !categories.sort[0].ascending : false;
	$scope.categories = categories.content;
	
	$scope.user = user;
    $scope.query = null;
    
    $scope.search = function(query, page) {
    	Category.page({
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
    		$scope.categories = success.content;
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
    
    $scope.open_create_category_form = function() {
    	var create_category_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/category/category.html',
    		controller: 'CategoryAddController',
    	});
    	
    	create_category_form_instance.result.then(function () {
    		
    	}, function () {
    		$scope.search($scope.query, $scope.currentPage);
    	});
    }
    
    $scope.open_edit_category_form = function(a_category) {
    	var edit_category_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/category/category.html',
    		controller: 'CategoryEditController',
    		resolve: {
    			edited: function() {
    				return a_category;
    			}
    		}
    	});
    	
    	edit_category_form_instance.result.then(function () {
    		
    	}, function () {
    		$scope.search($scope.query, $scope.currentPage);
    	});
    }
    
    $scope.delete_category = function(a_category) {
        Category.remove({}, {id: a_category.id}, function () {
        	$scope.search($scope.query, $scope.currentPage);
        }, function(fail){
        	if(fail.status !== 401 && fail.status !== 403) {
	        	$modal.open({
	        		animation: true,
	        		templateUrl: 'partials/error.html',
	        		controller: 'ErrorController',
	        		resolve: {
	        			error: function() {
	        				return fail;
	        			}
	        		}
	        	});
        	}
        });
    };
    
}).controller('CategoryAddController', function($scope, $modalInstance, $timeout, focus, Category) {
	
	$scope.category = {};
	$scope.errors = [];
	$scope.alert = null;
	var timer = null;
	
	$scope.action_category = function() {
        new Category({
        	name: $scope.category.name
        }).$save().then(function(success) {
            $scope.category = {};
            $scope.errors = [];
            focus('name');
            $scope.alert = {type: "success", msg: "new.category.created"};
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
      
}).controller('CategoryEditController', function($scope, $modalInstance, $timeout, focus, edited, Category) {
	
	$scope.category = edited;
	$scope.errors = [];
	$scope.alert = null;
	var timer = null;
	
	$scope.action_category = function() {
        Category.update({id: $scope.category.id}, $scope.category, function(success) {
            $scope.category = success;
            $scope.errors = [];
            focus('name');
            $scope.alert = {type: "success", msg: "category.edited"};
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