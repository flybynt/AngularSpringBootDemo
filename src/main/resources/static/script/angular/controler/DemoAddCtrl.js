app.controller('demoAddCtrl', function($scope, $location, $http) {
	//document.getElementById("firstName").focus();
	angular.element(document.getElementById("firstName"))[0].focus();

	$scope.addRow = function() {
		if (!$scope.comment) {
			$scope.comment = "";
		}
		
		var postData = {
				'firstName': $scope.firstName.replace("[^A-Za-z]+", ""),
				'lastName': $scope.lastName.replace("[^A-Za-z ]+", ""),
		        'email': $scope.email,
		        'comment': $scope.comment.replace("[^A-Za-z 1-9]+", "")};
		var addRepoUri = '/repo';		
		
		$http.post(addRepoUri, postData)
		.then(function(response) {
			console.log("SUCCESS code: " + response.status);        
			console.log("SUCCESS status: " + response.statusText);   
			console.log("POST " + JSON.stringify(response.config.data));
			console.log("RESPONSE " + JSON.stringify(response));
			$scope.resetFields();
		},function(response) {
			console.log("ERROR code: " + response.status);        
			console.log("ERROR status: " + response.statusText);
			console.log(JSON.stringify(response));
		});
		
		$location.path("/repo");
		$location.search('uri', null);
	};
	
	$scope.resetFields = function(){
		$scope.firstName = "";
		$scope.lastName = "";
		$scope.email = "";
		$scope.comment = "";
		
	    var error = $scope.demoForm.$error;
	    angular.forEach(error.showstatus, function(field){
	    	field.$setValidity('showstatus', true);
	    });
		
		$scope.demoForm.$setValidity();
		document.getElementById("firstName").focus();
	}

});

app.directive('statusMsg', function() {
	  return {
		    require: 'ngModel',
		    link: function (scope, element, attrs, controller) {
		    	var maxlength = Number(attrs.statusMsg);
		    	function fromUser(entry) {
		    		if (entry.length > maxlength) {
		    			var transformedInput = entry.substring(0, maxlength);
		    			controller.$setViewValue(transformedInput);
		    			controller.$render();
		    			controller.$setValidity('showstatus', false);
		    			return transformedInput;
		    		}
		    		else {
		    			controller.$setValidity('showstatus', true);
		    		}
		    		return entry;
		    	}
		    	controller.$parsers.push(fromUser);
		    }
	  }; 
});