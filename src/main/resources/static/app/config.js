angular.module('myApp').config(function($stateProvider, $httpProvider, $translateProvider) {
	
	$translateProvider.useStaticFilesLoader({
		prefix: '/languages/locale-',
		suffix: '.json'
	});
	$translateProvider.preferredLanguage('pl_PL');
	$translateProvider.useSanitizeValueStrategy('escape');
	$translateProvider.useMissingTranslationHandlerLog();
	$translateProvider.useLocalStorage();
	
	$httpProvider.interceptors.push('Interceptor');
	
	$stateProvider.state('login', {
		url: '/login',
		views: {
			'content@': {
				templateUrl: '/partials/login.html',
				controller: 'LoginController'
			}
		}
	}).state('authenticated', {
		url: '',
		abstract: true,
		resolve: {
			Account: 'Account',
			user: function(Account) {
				return Account.my().$promise;
			}
		},
		views: {
			'navbar@': {
				templateUrl: '/partials/navbar.html',
				controller: 'AuthenticationController'
			}
		}
	}).state('authenticated.forbidden', {
		url: '/forbidden',
		resolve: {
            user: function(user) {
            	return user;
            }
        },
		views: {
			'content@': {
				templateUrl: '/partials/forbidden.html',
				controller: 'ForbiddenController'
			}
		}
	}).state('authenticated.units', {
		url: '/units',
		resolve: {
			Unit: 'Unit',
            units: function(Unit) {
                return Unit.page().$promise;
            },
            user: function(user) {
            	return user;
            }
        },
        views: {
			'content@': {
				templateUrl: '/partials/unit/listUnits.html',
				controller: 'UnitsListController'
			}
		}
	}).state('authenticated.categories', {
		url: '/categories',
		resolve: {
			Category: 'Category',
			categories: function(Category) {
				return Category.page().$promise;
			},
            user: function(user) {
            	return user;
            }
		},
		views: {
			'content@': {
				templateUrl: '/partials/category/listCategories.html',
				controller: 'CategoriesListController'
			}
		}
	}).state('authenticated.suppliers', {
		url: '/suppliers',
		resolve: {
			Supplier: 'Supplier',
			suppliers: function(Supplier) {
				return Supplier.page().$promise;
			},
            user: function(user) {
            	return user;
            }
		},
		views: {
			'content@': {
				templateUrl: 'partials/supplier/listSuppliers.html',
				controller: 'SuppliersListController'
			}
		}
	}).state('authenticated.drawings', {
		url: '/drawings',
		resolve: {
			Drawing: 'Drawing',
			drawings: function(Drawing) {
				return Drawing.page().$promise;
			},
            user: function(user) {
            	return user;
            }
		},
		views: {
			'content@': {
				templateUrl: '/partials/drawing/listDrawings.html',
				controller: 'DrawingsListController'
			}
		}
	}).state('authenticated.parts', {
		url: '/parts',
		resolve: {
			Part: 'Part',
			parts: function(Part) {
				return Part.page().$promise;
			},
            user: function(user) {
            	return user;
            }
		},
		views: {
			'content@': {
				templateUrl: '/partials/part/listParts.html',
				controller: 'PartsListController'
			}
		}
	}).state('authenticated.requisitions', {
		url: '/requisitions',
		resolve: {
			Requisition: 'Requisition',
			requisitions: function(Requisition) {
				return  Requisition.page().$promise;
			},
            user: function(user) {
            	return user;
            }
		},
		views: {
			'content@': {
				templateUrl: '/partials/requisition/listRequisitions.html',
				controller: 'RequisitionsListController'
			}
		}
	}).state('authenticated.accounts', {
		url: '/accounts',
		resolve: {
			Account: 'Account',
			accounts: function(Account) {
				return Account.page().$promise;
			},
            user: function(user) {
            	return user;
            }
		},
		views: {
			'content@': {
				templateUrl: '/partials/account/listAccounts.html',
				controller: 'AccountsListController'
			}
		}
	});
	
}).run(function($state) {
	$state.go('login');
})