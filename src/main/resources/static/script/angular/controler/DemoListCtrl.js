app.controller('demoListCtrl', function($scope, $location, $http) {		 
	var listCtlrUri = '/ctlrdemo/all';

	$http.get(listCtlrUri)
    .then(function(response) {
        $scope.listCtlr = response.data;
        $scope.listCtlrStatus = response.statusText;
        $scope.listCtlrStatusCode = response.status;
    	console.log("SUCCESS code: " + response.status);        
    	console.log("SUCCESS status: " + response.statusText);        
		console.log(JSON.stringify(response));
    },function(response) {
        $scope.listCtlr = "[]";
        $scope.listCtlr = response.data;
        $scope.listCtlrStatus = response.statusText;
        console.log("ERROR code: " + response.status);        
    	console.log("ERROR status: " + response.statusText);
		console.log(JSON.stringify(response));
    });
});