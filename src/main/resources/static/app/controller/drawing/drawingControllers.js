angular.module('myApp.controllers').controller('DrawingsListController', function($scope, $modal, user, drawings, Drawing) {
    
	$scope.itemsPerPage = drawings.size;
	$scope.currentPage = drawings.number + 1;
	$scope.maxSize = 5;
	$scope.totalItems = drawings.totalElements;
	$scope.sort = drawings.sort[0] ? drawings.sort[0].property : null;
	$scope.reverse = drawings.sort[0] ? !drawings.sort[0].ascending : false;
	$scope.drawings = drawings.content;
	
	$scope.user = user;
    $scope.query = null;
    
    $scope.search = function(query, page) {
    	Drawing.page({
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
    		$scope.drawings = success.content;
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
    
    $scope.open_create_drawing_form = function() {

    	var create_drawing_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/drawing/drawing.html',
    		controller: 'DrawingAddController',
    	});
    	
    	create_drawing_form_instance.result.then(function () {
    		
    	}, function () {
    		$scope.search($scope.query, $scope.currentPage);
    	});
    }
    
    $scope.open_edit_drawing_form = function(a_drawing) {

    	var edit_drawing_form_instance = $modal.open({
    		animation: true,
    		templateUrl: 'partials/drawing/drawing.html',
    		controller: 'DrawingEditController',
    		resolve: {
    			edited: function() {
    				return a_drawing;
    			}
    		}
    	});
    	
    	edit_drawing_form_instance.result.then(function () {
    		
    	}, function () {
    		$scope.search($scope.query, $scope.currentPage);
    	});
    }
    
    $scope.delete_drawing = function(a_drawing) {
        Drawing.remove({id: a_drawing.id}).then(function () {
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
    
}).controller('DrawingAddController', function($scope, $modalInstance, $timeout, focus, Drawing) {
	
	$scope.drawing = {};
	$scope.errors = [];
	$scope.alert = null;
	var timer = null;
	
	$scope.action_drawing = function() {
        new Drawing({
            number: $scope.drawing.number
        }).$save().then(function(success) {
            $scope.drawing = {};
            $scope.errors = [];
            focus('number');
            $scope.alert = {type: "success", msg: "new.drawing.created"};
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
      
}).controller('DrawingEditController', function($scope, $modalInstance, $timeout, focus, edited, Drawing) {
	
	$scope.drawing = edited;
	$scope.errors = [];
	$scope.alert = null;
	var timer = null;
	
	$scope.action_drawing = function() {
        Drawing.update({id: $scope.drawing.id}, $scope.drawing, function(success) {
            $scope.drawing = success;
            $scope.errors = [];
            focus('name');
            $scope.alert = {type: "success", msg: "drawing.edited"};
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