app.controller('demoListRepoCtrl', function($scope, $location, $http) {
	var listRepoUri = '/repodemo';
	
	$http.get(listRepoUri)	
    .then(function(response) {
        $scope.listRepo = response.data._embedded.dataEntities;
        $scope.listRepoStatus = response.statusText;
        $scope.listRepoStatusCode = response.status;
    	console.log("SUCCESS code: " + response.status);        
    	console.log("SUCCESS status: " + response.statusText); 
		console.log(JSON.stringify(response));
    },function(response) {
        $scope.listRepo = "[]";
        $scope.listRepoStatus = response.statusText;
        $scope.listRepoStatusCode = response.status;
        console.log("ERROR code: " + response.status);        
    	console.log("ERROR status: " + response.statusText);
		console.log(JSON.stringify(response));
    });
	
	$scope.editRow = function(uri) {
		$location.path("edit").search("uri", uri);
	};
 });