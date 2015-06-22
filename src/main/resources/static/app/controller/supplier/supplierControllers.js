angular.module('myApp.controllers').controller('SuppliersListController', function($scope, $modal, user, suppliers, Supplier) {
    
	$scope.itemsPerPage = suppliers.size;
	$scope.currentPage = suppliers.number + 1;
	$scope.maxSize = 5;
	$scope.totalItems = suppliers.totalElements;
	$scope.sort = suppliers.sort[0] ? suppliers.sort[0].property : null;
	$scope.reverse = suppliers.sort[0] ? !suppliers.sort[0].ascending : false;
	$scope.suppliers = suppliers.content;
	
	$scope.user = user;
    $scope.query = null;
    $scope.conditions = null;
    
    $scope.search = function(query, conditions, page) {
    	Supplier.page({
    		query: query ? query : undefined,
    		name: conditions && conditions.name ? conditions.name : undefined,
    		email: conditions && conditions.email ? conditions.email : undefined,
    		web_site: conditions && conditions.web_site ? conditions.web_site : undefined,
    		phone: conditions && conditions.phone ? conditions.phone : undefined,
    		country: conditions && conditions.country ? conditions.country : undefined,
    		city: conditions && conditions.city ? conditions.city : undefined,
    		street: conditions && conditions.street ? conditions.street : undefined,
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
    		$scope.suppliers = success.content;
    	});
    }
    
    $scope.clear_search = function() {
    	$scope.query = '';
    	$scope.search($scope.query, $scope.conditions, 1);
    }
    
    $scope.reverse_order = function() {
    	$scope.reverse = !$scope.reverse;
    	$scope.search($scope.query, $scope.conditions, $scope.currentPage);
    }
    
    $scope.change_sort = function() {
    	$scope.search($scope.query, $scope.conditions, $scope.currentPage);
    }
    
    $scope.basic_search = function() {
    	$scope.conditions = null;
    	$scope.search($scope.query, $scope.conditions, 1);
    }
    
    $scope.clear_condition = function(property) {
    	$scope.conditions[property] = null;
    	$scope.search($scope.query, $scope.conditions, 1);
    }
    
    $scope.open_create_supplier_form = function() {
    	var create_supplier_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/supplier/supplier.html',
    		controller: 'SupplierAddController',
    	});
    	
    	create_supplier_form_instance.result.then(function () {
    		
    	}, function () {
    		$scope.search($scope.query, $scope.conditions, $scope.currentPage);
    	});
    }
    
    $scope.open_edit_supplier_form = function(a_supplier) {
    	var edit_supplier_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/supplier/supplier.html',
    		controller: 'SupplierEditController',
    		resolve: {
    			edited: function() {
    				return a_supplier;
    			}
    		}
    	});
    	
    	edit_supplier_form_instance.result.then(function () {
    		
    	}, function () {
    		$scope.search($scope.query, $scope.conditions, $scope.currentPage);
    	});
    }
    
    $scope.open_search_supplier_form = function() {
    	var search_supplier_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/supplier/searchSupplier.html',
    		controller: 'SupplierSearchController',
    		resolve: {
    			conditions: function() {
    				return $scope.conditions;
    			}
    		}
    	});
    	
    	search_supplier_form_instance.result.then(function(conditions) {
    		$scope.conditions = conditions;
    		$scope.query = null;
    		$scope.search($scope.query, $scope.conditions, 1);
    	});
    }
    
    $scope.delete_supplier = function(a_supplier) {
        Supplier.remove({}, {id: a_supplier.id}, function() {
        	$scope.search($scope.query, $scope.conditions, $scope.currentPage);
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
    
}).controller('SupplierAddController', function($scope, $modalInstance, $timeout, focus, Supplier) {
	
	$scope.supplier = {contact: {address: {}}};
	$scope.errors = [];
	$scope.alert = null;
	var timer = null;

	$scope.action_supplier = function() {
    	new Supplier({
    		name: $scope.supplier.name,
    		contact: $scope.supplier.contact
        }).$save().then(function(success) {
        	$scope.supplier = {contact: {address: {}}};
            $scope.errors = [];
            focus('name');
            $scope.alert = {type: "success", msg: "new.supplier.created"};
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
      
}).controller('SupplierEditController', function($scope, $modalInstance, $timeout, focus, edited, Supplier) {
	
	$scope.supplier = edited;
	$scope.errors = [];
	$scope.alert = null;
	var timer = null;
	
	$scope.action_supplier = function() {
        Supplier.update({id: $scope.supplier.id}, $scope.supplier, function(success) {
            $scope.supplier = success;
            $scope.errors = [];
            focus('name');
            $scope.alert = {type: "success", msg: "supplier.edited"};
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
      
}).controller('SupplierSearchController', function($scope, $modalInstance, conditions, Supplier) {
	
	$scope.conditions = conditions;
	$scope.suppliers = [];
	
	$scope.hints = function(query) {
		Supplier.page({
			query: query,
			name: conditions && conditions.name ? conditions.name : undefined,
		    email: conditions && conditions.email ? conditions.email : undefined,
		    web_site: conditions && conditions.web_site ? conditions.web_site : undefined,
		    phone: conditions && conditions.phone ? conditions.phone : undefined,
		    country: conditions && conditions.country ? conditions.country : undefined,
		    city: conditions && conditions.city ? conditions.city : undefined,
		    street: conditions && conditions.street ? conditions.street : undefined,
		}).$promise.then(function(success) {
			$scope.suppliers = success.content;
		});
	}
	
	$scope.ok = function() {
		$modalInstance.close($scope.conditions);
	}

    $scope.exit = function () {
    	$modalInstance.dismiss('exit');
    };
      
})