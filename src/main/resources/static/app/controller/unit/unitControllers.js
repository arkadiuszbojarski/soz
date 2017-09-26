angular.module('myApp.controllers').controller('UnitsListController', function($scope, $modal, user, units, Unit) {
	
	$scope.itemsPerPage = units.size;
	$scope.currentPage = units.number + 1;
	$scope.maxSize = 5;
	$scope.totalItems = units.totalElements;
	$scope.sort = units.sort[0] ? units.sort[0].property : null;
	$scope.reverse = units.sort[0] ? !units.sort[0].ascending : false;
	$scope.units = units.content;
	
	$scope.user = user;
    $scope.query = null;
    
    $scope.search = function(query, page) {
    	Unit.page({
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
    		$scope.units = success.content;
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
    
    $scope.open_create_unit_form = function() {
    	var create_unit_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/unit/unit.html',
    		controller: 'UnitAddController',
    	});
    	
    	create_unit_form_instance.result.then(function () {
    		
    	}, function () {
    		$scope.search($scope.query, $scope.currentPage);
    	});
    }
    
    $scope.open_edit_unit_form = function(an_unit) {
    	var edit_unit_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/unit/unit.html',
    		controller: 'UnitEditController',
    		resolve: {
    			edited: function() {
    				return an_unit;
    			}
    		}
    	});
    	
    	edit_unit_form_instance.result.then(function () {
    		
    	}, function () {
    		$scope.search($scope.query, $scope.currentPage);
    	});
    }
    
    $scope.delete_unit = function(an_unit) {
        Unit.remove({id: an_unit.id}).$promise.then(function(success) {
        	$scope.search($scope.query, $scope.currentPage);
        }, function(fail) {
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
    
}).controller('UnitAddController', function($scope, $modalInstance, $timeout, focus, Unit) {
	
	$scope.unit = {};
	$scope.errors = [];
	$scope.alert = null;
	var timer = null;
	
	$scope.action_unit = function() {
        new Unit({
        	name: $scope.unit.name
        }).$save().then(function(success) {
            $scope.unit = {};
            $scope.errors = [];
            focus('name');
            $scope.alert = {type: "success", msg: "new.unit.created"};
            $timeout.cancel(timer);
            timer = $timeout(function() {
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
      
}).controller('UnitEditController', function($scope, $modalInstance, $timeout, focus, edited, Unit) {
	
	$scope.unit = edited;
	$scope.errors = [];
	$scope.alert = null;
	var timer = null;
	
	$scope.action_unit = function() {
        Unit.update({id: $scope.unit.id}, $scope.unit).$promise.then(function(success) {
        	$scope.unit = success;
            $scope.errors = [];
            focus('name');
            $scope.alert = {type: "success", msg: "unit.edited"};
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