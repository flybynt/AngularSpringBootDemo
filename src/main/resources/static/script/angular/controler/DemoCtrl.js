app.config(function($routeProvider, $locationProvider) {
    $routeProvider
    	.when('/repo', {
            templateUrl : 'fragment/listRepo.html',
            controller  : 'demoListRepoCtrl'
        })
        .when('/list', {
            templateUrl : 'fragment/list.html',
            controller  : 'demoListCtrl'
        })
        .when('/edit', {
            templateUrl : 'fragment/edit.html',
            controller  : 'demoEditCtrl',	
        })
    	.when('/add', {
    		templateUrl : 'fragment/add.html',
    		controller  : 'demoAddCtrl'
    	});
    
});

app.controller('demoCtrl', function($scope, $location, $routeParams) {
	
});