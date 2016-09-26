app.controller('demoEditCtrl', function($scope, $location, $http) {
	//document.getElementById("firstName").focus();
	angular.element(document.getElementById("firstName"))[0].focus();
	

	$scope.params = $location.search();
	if (typeof($scope.response) == 'undefined' || $scope.response == null) {		
		$http.get($scope.params.uri)
	    .then(function(response) {
	       $scope.firstName = response.data.firstName;
	       $scope.lastName = response.data.lastName;
	       $scope.email = response.data.email;
	       $scope.comment = response.data.comment;
	       
			if (typeof($scope.comment) == 'undefined' || $scope.comment == null) {
				$scope.comment = "";
			}
			
			$scope.response = response;
			angular.element(document.getElementById('submit'))[0].disabled = false;
	    }, function(response) {
		       alert("Error getting data for URI: " + uri);	    	
	    });
	}
	
	$scope.submit = function() {
		var patchData = $scope.response.data;
		
		if (patchData.firstName != $scope.firstName)
			patchData.firstName = $scope.firstName.replace("[^A-Za-z ]+", "");
		
		if (patchData.lastName != $scope.lastName)
			patchData.lastName = $scope.lastName.replace("[^A-Za-z ]+", "");
		
		if (patchData.email != $scope.email)
			patchData.email = $scope.email;
		
		if (patchData.comment = $scope.comment)
			patchData.comment = $scope.comment.replace("[^A-Za-z 1-9]+", "");
				
		$http.patch($scope.response.config.url, patchData)
		.then(function(response) {
			console.log("SUCCESS code: " + response.status);        
			console.log("SUCCESS status: " + response.statusText);   
			console.log("PATCH " + JSON.stringify(response.config.data));
			console.log("RESPONSE " + JSON.stringify(response));
		},function(response) {
			console.log("ERROR code: " + response.status);        
			console.log("ERROR status: " + response.statusText);
			console.log(JSON.stringify(response));
		});
		
		$location.path("/repo");
		$location.search('uri', null);
	};
	
	$scope.cancelUpdate = function () {
		$location.path("/repo");
		$location.search('uri', null);
	};

});