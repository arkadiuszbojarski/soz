angular.module('myApp.controllers').controller('PartsListController', function($scope, $modal, user, parts, Part) {
    
	$scope.itemsPerPage = parts.size;
	$scope.currentPage = parts.number + 1;
	$scope.maxSize = 5;
	$scope.totalItems = parts.totalElements;
	$scope.sort = parts.sort[0] ? parts.sort[0].property : null;
	$scope.reverse = parts.sort[0] ? !parts.sort[0].ascending : false;
	$scope.parts = parts.content;
	
	$scope.user = user;
    $scope.query = null;
    $scope.conditions = null;
    
    $scope.search = function(query, conditions, page) {
    	Part.page({
    		query: query ? query : undefined,
    		number: conditions && conditions.number ? conditions.number : undefined,
    		description: conditions && conditions.description ? conditions.description : undefined,
    		category: conditions && conditions.category ? conditions.category : undefined,
    		meterial: conditions && conditions.material ? conditions.material : undefined,
    		supplier: conditions && conditions.supplier ? conditions.supplier : undefined,
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
    		$scope.parts = success.content;
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
    
    $scope.open_create_part_form = function() {
    	var create_part_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/part/part.html',
    		controller: 'PartAddController',
    	});
    	
    	create_part_form_instance.result.then(function () {
    		
    	}, function () {
    		$scope.search($scope.query, $scope.conditions, $scope.currentPage);
    	});
    }
    
    $scope.open_edit_part_form = function(a_part) {
    	var edit_part_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/part/part.html',
    		controller: 'PartEditController',
    		resolve: {
    			edited: function() {
    				return a_part;
    			}
    		}
    	});
    	
    	edit_part_form_instance.result.then(function () {
    		
    	}, function () {
    		$scope.search($scope.query, $scope.conditions, $scope.currentPage);
    	});
    }
    
    $scope.open_search_part_form = function() {
    	var search_part_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/part/searchPart.html',
    		controller: 'PartSearchController',
    		resolve: {
    			conditions: function() {
    				return $scope.conditions;
    			}
    		}
    	});
    	
    	search_part_form_instance.result.then(function(conditions) {
    		$scope.conditions = conditions;
    		$scope.query = null;
    		$scope.search($scope.query, $scope.conditions, 1);
    	});
    }
    
    $scope.delete_part = function(a_part) {
        Part.remove({id: a_part.id}).$promise.then(function () {
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
    
}).controller('PartAddController', function($scope, $modalInstance, $timeout, focus, Part, Category, Supplier, Unit) {
	
	$scope.categories = [];
	$scope.suppliers = [];
	$scope.units = [];
	$scope.part = {
			category: {},
			supplier: {contact: {address: {}}},
			dimensions: []
	};
	$scope.errors = [];
	$scope.alert = null;
	var timer = null;
	
	$scope.hint_categories = function(query) {
		Category.page({
			query: query
		}).$promise.then(function(success) {
			$scope.categories = success.content;
		});
	}
	
	$scope.hint_units = function(query) {
		Unit.page({
			query: query
		}).$promise.then(function(success) {
			$scope.units = success.content;
		});
	}

	$scope.hint_suppliers = function(query) {
		Supplier.page({
			query: query
		}).$promise.then(function(success) {
			$scope.suppliers = success.content;
		})
	}
	
	$scope.categorySelect = function($item, $model, $label) {
		$scope.part.category = {id: $item.id, name: $item.name};
	}
	
	$scope.unitSelect = function($item, $model, $lable, a_dimension) {
		a_dimension.unit = {id: $item.id, name: $item.name};
	}
	
	$scope.supplierSelect = function($item, $model, $label) {
		$scope.part.supplier = {
				id: $item.id,
				name: $item.name,
				contact: $item.contact ? {
					email: $item.contact.email,
					web_site: $item.contact.web_site,
					phone: $item.contact.phone,
					address: $item.contact.address ? {
						country: $item.contact.address.country,
						city: $item.contact.address.city,
						postal_code: $item.contact.address.postal_code,
						street: $item.contact.address.street
					} : {}
				} : {address: {}}
			};
	}
	
	$scope.add_new_dimension = function () {
		$scope.part.dimensions.push({name: '', value: '', unit: {name: ''}});
	}
	
	$scope.remove_dimension = function(a_dimension) {
		$scope.part.dimensions.splice($scope.part.dimensions.indexOf(a_dimension), 1);
	}
	
	$scope.clear_unit_id = function(unit) {
		unit.id = undefined;
	}
	
	var isNewSupplierSet = function() {
		return !$scope.part.supplier.id &&
		($scope.part.supplier.name ||
		$scope.part.supplier.contact.email ||
		$scope.part.supplier.contact.web_site ||
		$scope.part.supplier.contact.phone ||
		$scope.part.supplier.contact.address.country ||
		$scope.part.supplier.contact.address.city ||
		$scope.part.supplier.contact.address.postal_code ||
		$scope.part.supplier.contact.address.street);
	}
	
	$scope.action_part = function() {
		var new_part = new Part();
		
		new_part.number = $scope.part.number;
		new_part.description = $scope.part.description;
		new_part.category = $scope.part.category.id ?
				$scope.part.category : $scope.part.category.name ?
						{name: $scope.part.category.name} : undefined;
		
		new_part.material = $scope.part.material;
		new_part.dimensions = $scope.part.dimensions;
		new_part.supplier = $scope.part.supplier.id ?
				$scope.part.supplier : isNewSupplierSet() ?
						{name: $scope.part.supplier.name, contact: $scope.part.supplier.contact} : undefined;

		new_part.$save().then(function(success) {
        	$scope.part = {
        			category: {},
        			supplier: {contact: {address: {}}},
        			dimensions: []
        	};
            $scope.errors = [];
            focus('number');
            $scope.alert = {type: "success", msg: "new.part.created"};
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
	}

    $scope.exit = function () {
    	$timeout.cancel(timer);
    	$modalInstance.dismiss('exit');
    };
      
}).controller('PartEditController', function($scope, $modalInstance, $timeout, focus, edited, Part, Category, Supplier, Unit) {
	
	$scope.categories = [];
	$scope.units = [];
	$scope.suppliers = [];
	$scope.part = edited;
	$scope.errors = [];
	$scope.alert = null;
	var timer = null;
	
	$scope.hint_categories = function(query) {
		Category.page({
			query: query
		}).$promise.then(function(success) {
			$scope.categories = success.content;
		});
	}
	
	$scope.hint_units = function(query) {
		Unit.page({
			query: query
		}).$promise.then(function(success) {
			$scope.units = success.content;
		});
	}

	$scope.hint_suppliers = function(query) {
		Supplier.page({
			query: query
		}).$promise.then(function(success) {
			$scope.suppliers = success.content;
		})
	}
	
	$scope.categorySelect = function($item, $model, $label) {
		$scope.part.category = {id: $item.id, name: $item.name};
	}
	
	$scope.unitSelect = function($item, $model, $lable, a_dimension) {
		a_dimension.unit = {id: $item.id, name: $item.name};
	}
	
	$scope.supplierSelect = function($item, $model, $label) {
		$scope.part.supplier = {
				id: $item.id,
				name: $item.name,
				contact: $item.contact ? {
					email: $item.contact.email,
					web_site: $item.contact.web_site,
					phone: $item.contact.phone,
					address: $item.contact.address ? {
						country: $item.contact.address.country,
						city: $item.contact.address.city,
						postal_code: $item.contact.address.postal_code,
						street: $item.contact.address.street
					} : {}
				} : {address: {}}
			};
	}
	
	$scope.add_new_dimension = function () {
		$scope.part.dimensions.push({name: '', value: '', unit: {name: ''}});
	}
	
	$scope.remove_dimension = function(a_dimension) {
		$scope.part.dimensions.splice($scope.part.dimensions.indexOf(a_dimension), 1);
	}
	
	$scope.closeAlert = function(index) {
		$scope.alerts.splice(index, 1);
	}
	
	$scope.clear_unit_id = function(unit) {
		unit.id = undefined;
	}
	
	$scope.action_part = function() {
        Part.update({id: $scope.part.id}, $scope.part, function(success) {
            $scope.part = success;
            $scope.errors = [];
            focus('name');
            $scope.alert = {type: "success", msg: "part.edited"};
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
      
}).controller('PartSearchController', function($scope, $modalInstance, conditions, Supplier, Category, Part) {
	
	$scope.categories = [];
	$scope.suppliers = [];
	$scope.parts = [];
	$scope.conditions = conditions;
	
	$scope.hint_categories = function(query) {
		Category.page({
			query: query
		}).$promise.then(function(success) {
			$scope.categories = success.content;
		});
	}

	$scope.hint_suppliers = function(query) {
		Supplier.page({
			query: query
		}).$promise.then(function(success) {
			$scope.suppliers = success.content;
		})
	}
	
	$scope.hint = function(query) {
		Part.page({
			query: query ? query : undefined,
		    number: conditions && conditions.number ? conditions.number : undefined,
		    description: conditions && conditions.description ? conditions.description : undefined,
		    category: conditions && conditions.category ? conditions.category : undefined,
		    meterial: conditions && conditions.material ? conditions.material : undefined,
		    supplier: conditions && conditions.supplier ? conditions.supplier : undefined
		}).$promise.then(function(success) {
			$scope.parts = success.content;
		})
	}
	
	$scope.ok = function() {
		$modalInstance.close($scope.conditions);
	}

    $scope.exit = function () {
    	$modalInstance.dismiss('exit');
    };
      
})