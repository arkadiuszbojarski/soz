angular.module('myApp.controllers').controller('RequisitionsListController', function($scope, $stateParams, $modal, user, requisitions, Requisition) {
    
	$scope.itemsPerPage = requisitions.size;
	$scope.currentPage = requisitions.number + 1;
	$scope.maxSize = 5;
	$scope.totalItems = requisitions.totalElements;
	$scope.sort = requisitions.sort[0] ? requisitions.sort[0].property : null;
	$scope.reverse = requisitions.sort[0] ? !requisitions.sort[0].ascending : false;
	$scope.requisitions = requisitions.content;
	
	$scope.user = user;
    $scope.query = null;
    $scope.conditions = null;
    
    $scope.search = function(query, conditions, page) {
    	Requisition.page({
    		query: query ? query : undefined,
    		withNumber: conditions && conditions.number ? conditions.number : undefined,
    		after: conditions && conditions.after ? conditions.after : undefined,
    		before: conditions && conditions.before ? conditions.before : undefined,
    		author: conditions && conditions.author ? conditions.author : undefined,
    		status: conditions && conditions.status ? conditions.status : undefined,
    		element: conditions && conditions.element ? conditions.element : undefined,
    		drawing: conditions && conditions.drawing ? conditions.drawing : undefined,
    		part: conditions && conditions.part ? conditions.part : undefined,
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
    		$scope.requisitions = success.content;
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
    
    $scope.open_create_requisition_form = function() {
    	var create_requisition_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/requisition/requisition.html',
    		controller: 'RequisitionAddController',
    	});
    	
    	create_requisition_form_instance.result.then(function () {
    		
    	}, function () {
    		$scope.search($scope.query, $scope.conditions, $scope.currentPage);
    	});
    }
    
    $scope.open_edit_requisition_form = function(a_requisition) {
    	var edit_requisition_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/requisition/requisition.html',
    		controller: 'RequisitionEditController',
    		resolve: {
    			edited: function() {
    				return a_requisition;
    			}
    		}
    	});
    	
    	edit_requisition_form_instance.result.then(function () {
    		
    	}, function () {
    		$scope.search($scope.query, $scope.conditions, $scope.currentPage);
    	});
    }
    
    $scope.open_search_requisition_form = function() {
    	var search_requisition_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/requisition/searchRequisition.html',
    		controller: 'RequisitionSearchController',
    		resolve: {
    			conditions: function() {
    				return $scope.conditions;
    			}
    		}
    	});
    	
    	search_requisition_form_instance.result.then(function(conditions) {
    		$scope.conditions = conditions;
    		$scope.query = null;    		
    		$scope.search($scope.query, $scope.conditions, 1);
    	});
    }
    
    $scope.history = function(a_requisition){
    	Requisition.history({number: a_requisition.number}, function(success){
    		$scope.itemsPerPage = success.size;
    		$scope.currentPage = success.number + 1;
    		$scope.totalItems = success.totalElements;
    		$scope.sort = success.sort[0] ? success.sort[0].property : null;
    		$scope.reverse = success.sort[0] ? !success.sort[0].ascending : false;
    		$scope.requisitions = success.content;
    	})
    }
    
    $scope.canEditRequisition = function(a_requisition) {
    	if(a_requisition !== undefined && a_requisition !== null) {
    		return $scope.user.id == a_requisition.author.id || $scope.user.role == 'ADMIN';
    	}
    	
    	return false;
    }
    
    $scope.delete_requisition = function(a_requisition) {
        Requisition.remove({number: a_requisition.number}).$promise.then(function () {
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
    
}).controller('RequisitionAddController', function($scope, $modalInstance, $timeout, focus, Requisition, Part, Category, Supplier, Unit, Drawing) {
	
	$scope.parts = [];
	$scope.categories = [];
	$scope.suppliers = [];
	$scope.units = [];
	$scope.drawings = [];
	$scope.requisition = {
			unit: {},
			drawing: {},
			part: {
				category: {},
				supplier: {contact: {address: {}}},
			dimensions: []}
		};
	$scope.errors = [];
	$scope.alert = null;
	var timer = null;
	
	$scope.hint_parts = function(query) {
		Part.page({
			query: query
		}).$promise.then(function(success) {
			$scope.parts = success.content;
		});
	}
	
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
		});
	}
	
	$scope.hint_drawings = function(query) {
		Drawing.page({
			query: query
		}).$promise.then(function(success) {
			$scope.drawings = success.content;
		});
	}
	
	$scope.hint = function(query) {
		Requisition.page({
    		query: query ? query : undefined,
    		number: conditions && conditions.number ? conditions.number : undefined,
    		after: conditions && conditions.after ? conditions.after : undefined,
    		before: conditions && conditions.before ? conditions.before : undefined,
    		author: conditions && conditions.author ? conditions.author : undefined,
    		status: conditions && conditions.status ? conditions.status : undefined,
    		element: conditions && conditions.element ? conditions.element : undefined,
    		drawing: conditions && conditions.drawing ? conditions.drawing : undefined,
    		part: conditions && conditions.part ? conditions.part : undefined,
    		description: conditions && conditions.description ? conditions.description : undefined,
    		category: conditions && conditions.category ? conditions.category : undefined,
    		meterial: conditions && conditions.material ? conditions.material : undefined,
    		supplier: conditions && conditions.supplier ? conditions.supplier : undefined,
    	}).$promise.then(function(success) {
    		$scope.requisitions = success.content;
    	});
	}
	
	$scope.partSelect = function($item, $model, $label) {
		$scope.requisition.part = {
				id: $item.id,
				number: $item.number,
				description: $item.description,
				category: $item.category ? {id: $item.category.id, name: $item.category.name} : undefined,
				dimensions: $item.dimensions,
				material: $item.material,
				supplier: $item.supplier ? {
					id: $item.supplier.id,
					name: $item.supplier.name,
					contact: $item.supplier.contact ? {
						email: $item.supplier.contact.email,
						web_site: $item.supplier.contact.web_site,
						phone: $item.supplier.contact.phone,
						address: $item.supplier.contact.address ? {
							country: $item.supplier.contact.address.country,
							city: $item.supplier.contact.address.city,
							postal_code: $item.supplier.contact.address.postal_code,
							street: $item.supplier.contact.address.street
						} : {}
					} : {address: {}}
				} : undefined,
			};
	}
	
	$scope.supplierSelect = function($item, $model, $label) {
		$scope.requisition.part.supplier = {
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
	
	$scope.categorySelect = function($item, $model, $label) {
		$scope.requisition.part.category = {id: $item.id, name: $item.name};
	}
	
	$scope.requisitionUnitSelect = function($item, $model, $label) {
		$scope.requisition.unit = {id: $item.id, name: $item.name};
	}
	
	$scope.dimensionUnitSelect = function($item, $model, $label, a_dimension) {
		a_dimension.unit = {id: $item.id, name: $item.name};
	}
    
    $scope.drawingSelect = function($item, $model, $label) {
        $scope.requisition.drawing = {id: $item.id, number: $item.number};
    }
	
	$scope.add_new_dimension = function () {
		$scope.requisition.part.dimensions.push({name: '', value: '', unit: {name: ''}});
	}
	
	$scope.remove_dimension = function(a_dimension) {
		$scope.requisition.part.dimensions.splice($scope.requisition.part.dimensions.indexOf(a_dimension), 1);
	}
	
	var isNewSupplierSet = function() {
		return !$scope.requisition.part.supplier.id &&
		($scope.requisition.part.supplier.name ||
		$scope.requisition.part.supplier.contact.email ||
		$scope.requisition.part.supplier.contact.web_site ||
		$scope.requisition.part.supplier.contact.phone ||
		$scope.requisition.part.supplier.contact.address.country ||
		$scope.requisition.part.supplier.contact.address.city ||
		$scope.requisition.part.supplier.contact.address.postal_code ||
		$scope.requisition.part.supplier.contact.address.street);
	}
	
	$scope.action_requisition = function() {
		var new_requisition = new Requisition();
		
		new_requisition.element = $scope.requisition.element;
		new_requisition.amount = $scope.requisition.amount;
		new_requisition.unit = $scope.requisition.unit;
		new_requisition.drawing = $scope.requisition.drawing;
		new_requisition.comment = $scope.requisition.comment;
		new_requisition.part = $scope.requisition.part.id ?
				{id: $scope.requisition.part.id} : {
					number: $scope.requisition.part.number,
					description: $scope.requisition.part.description,
					category: $scope.requisition.part.category.id ? 
							$scope.requisition.part.category : $scope.requisition.part.category.name ? 
									{name: $scope.requisition.part.category.name} : undefined,
					dimensions: $scope.requisition.part.dimensions,
					material: $scope.requisition.part.material,
					supplier: $scope.requisition.part.supplier.id ? 
							$scope.requisition.part.supplier : isNewSupplierSet() ?
									{name: $scope.requisition.part.supplier.name, contact: $scope.requisition.part.supplier.contact} : undefined
				}
		
		new_requisition.$save().then(function(success) {
        	$scope.requisition = {
        			unit: {},
        			drawing: {},
        			part: {
        				category: {},
        				supplier: {contact: {address: {}}},
        			dimensions: []}
        		}; 
            $scope.errors = [];
            focus('element');
            $scope.alert = {type: "success", msg: "new.requisition.created"};
            $timeout.cancel(timer);
            $timeout(function(){
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
      
}).controller('RequisitionEditController', function($scope, $modalInstance, $timeout, focus, edited, Requisition, Part, Category, Supplier, Unit, Drawing) {
	
	$scope.parts = [];
	$scope.categories = [];
	$scope.suppliers = [];
	$scope.units = [];
	$scope.drawings = [];
	$scope.statuses = [];
	$scope.requisition = edited;
	$scope.errors = [];
	$scope.alert = null;
	var timer = null;
	
	$scope.hint_parts = function(query) {
		Part.page({
			query: query
		}).$promise.then(function(success) {
			$scope.parts = success.content;
		});
	}
	
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
		});
	}
	
	$scope.hint_drawings = function(query) {
		Drawing.page({
			query: query
		}).$promise.then(function(success) {
			$scope.drawings = success.content;
		});
	}
	
	Requisition.statuses(function(response) {
		$scope.statuses = response ? response : null;
	})
	
	$scope.hint = function(query) {
		Requisition.page({
    		query: query ? query : undefined,
    		number: conditions && conditions.number ? conditions.number : undefined,
    		after: conditions && conditions.after ? conditions.after : undefined,
    		before: conditions && conditions.before ? conditions.before : undefined,
    		author: conditions && conditions.author ? conditions.author : undefined,
    		status: conditions && conditions.status ? conditions.status : undefined,
    		element: conditions && conditions.element ? conditions.element : undefined,
    		drawing: conditions && conditions.drawing ? conditions.drawing : undefined,
    		part: conditions && conditions.part ? conditions.part : undefined,
    		description: conditions && conditions.description ? conditions.description : undefined,
    		category: conditions && conditions.category ? conditions.category : undefined,
    		meterial: conditions && conditions.material ? conditions.material : undefined,
    		supplier: conditions && conditions.supplier ? conditions.supplier : undefined,
    	}).$promise.then(function(success) {
    		$scope.requisitions = success.content;
    	});
	}
	
	$scope.partSelect = function($item, $model, $label) {
		$scope.requisition.part = {
				id: $item.id,
				number: $item.number,
				description: $item.description,
				category: $item.category ? {id: $item.category.id, name: $item.category.name} : undefined,
				dimensions: $item.dimensions,
				material: $item.material,
				supplier: $item.supplier ? {
					id: $item.supplier.id,
					name: $item.supplier.name,
					contact: $item.supplier.contact ? {
						email: $item.supplier.contact.email,
						web_site: $item.supplier.contact.web_site,
						phone: $item.supplier.contact.phone,
						address: $item.supplier.contact.address ? {
							country: $item.supplier.contact.address.country,
							city: $item.supplier.contact.address.city,
							postal_code: $item.supplier.contact.address.postal_code,
							street: $item.supplier.contact.address.street
						} : {}
					} : {address: {}}
				} : undefined,
			};
	}
	
	$scope.supplierSelect = function($item, $model, $label) {
		$scope.requisition.part.supplier = {
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
	
	$scope.categorySelect = function($item, $model, $label) {
		$scope.requisition.part.category = {id: $item.id, name: $item.name};
	}
	
	$scope.requisitionUnitSelect = function($item, $model, $label) {
		$scope.requisition.unit = {id: $item.id, name: $item.name};
	}
	
	$scope.dimensionUnitSelect = function($item, $model, $label, a_dimension) {
		a_dimension.unit = {id: $item.id, name: $item.name};
	}
    
    $scope.drawingSelect = function($item, $model, $label) {
        $scope.requisition.drawing = {id: $item.id, number: $item.number};
    }
	
	$scope.add_new_dimension = function () {
		$scope.requisition.part.dimensions.push({name: '', value: '', unit: {name: ''}});
	}
	
	$scope.remove_dimension = function(a_dimension) {
		$scope.requisition.part.dimensions.splice($scope.requisition.part.dimensions.indexOf(a_dimension), 1);
	}
	
	var isNewSupplierSet = function() {
		return !$scope.requisition.part.supplier.id &&
		($scope.requisition.part.supplier.name ||
		$scope.requisition.part.supplier.contact.email ||
		$scope.requisition.part.supplier.contact.web_site ||
		$scope.requisition.part.supplier.contact.phone ||
		$scope.requisition.part.supplier.contact.address.country ||
		$scope.requisition.part.supplier.contact.address.city ||
		$scope.requisition.part.supplier.contact.address.postal_code ||
		$scope.requisition.part.supplier.contact.address.street);
	}

	$scope.action_requisition = function() {
		Requisition.update({number: $scope.requisition.number}, $scope.requisition, function(success) {
            $scope.requisition = success;
            $scope.errors = [];
            focus('element');
            $scope.alert = {type: "success", msg: "requisition.edited"};
            $timeout.cancel(timer);
            $timeout(function(){
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
      
}).controller('RequisitionSearchController', function($scope, $modalInstance, conditions, Part, Supplier, Category, Drawing, Requisition) {
	
	$scope.drawings = [];
	$scope.categories = [];
	$scope.suppliers = [];
	$scope.parts = [];
	$scope.statuses = [];
	$scope.conditions = conditions;
	$scope.after_opened = false;
	$scope.before_opened = false;
	$scope.after = "";
	$scope.before = "";
	
	$scope.hint_parts = function(query) {
		Part.page({
			query: query
		}).$promise.then(function(success) {
			$scope.parts = success.content;
		});
	}
	
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
		});
	}
	
	$scope.hint_drawings = function(query) {
		Drawing.page({
			query: query
		}).$promise.then(function(success) {
			$scope.drawings = success.content;
		});
	}
	
	Requisition.statuses(function(response) {
		$scope.statuses = response ? response : null;
	})
	
	$scope.hint = function(query) {
		Requisition.page({
    		query: query ? query : undefined,
    		number: conditions && conditions.number ? conditions.number : undefined,
    		after: conditions && conditions.after ? conditions.after : undefined,
    		before: conditions && conditions.before ? conditions.before : undefined,
    		author: conditions && conditions.author ? conditions.author : undefined,
    		status: conditions && conditions.status ? conditions.status : undefined,
    		element: conditions && conditions.element ? conditions.element : undefined,
    		drawing: conditions && conditions.drawing ? conditions.drawing : undefined,
    		part: conditions && conditions.part ? conditions.part : undefined,
    		description: conditions && conditions.description ? conditions.description : undefined,
    		category: conditions && conditions.category ? conditions.category : undefined,
    		meterial: conditions && conditions.material ? conditions.material : undefined,
    		supplier: conditions && conditions.supplier ? conditions.supplier : undefined,
    	}).$promise.then(function(success) {
    		$scope.requisitions = success.content;
    	});
	}
	
	$scope.open_after = function($event) {
		$event.preventDefault();
	    $event.stopPropagation();
	    
	    $scope.after_opened = true;
	}
	
	$scope.open_before = function($event) {
		$event.preventDefault();
	    $event.stopPropagation();

	    $scope.before_opened = true;
	}
	
	$scope.toTimestamp = function(date) {
		return date ? new Date(date).getTime() : null;
	}
	
	$scope.ok = function() {
		$scope.conditions.after = $scope.toTimestamp($scope.after);
		$scope.conditions.before = $scope.toTimestamp($scope.before);
		$modalInstance.close($scope.conditions);
	}

    $scope.exit = function () {
    	$modalInstance.dismiss('exit');
    };
      
})