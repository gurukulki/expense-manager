var app = angular.module("expenseManager", []);

var server = "/expense-manager";

app.controller("expManagerController", function($scope, $http) {

	/*
	 * $scope.customers = [ { id : "6", nodes : [ { id : "11", name : "VMS
	 * Journey Time", port : "4005", url : "https://localhost:8443/vms" } ] }, {
	 * id : "7", nodes : [ { id : "13", name : "Merseytravel Journey Time
	 * System", port : "4008", url : "https://localhost:8443/vms" } ] }, { id :
	 * "1", nodes : [ { id : "7", name : "T2E Journey Time", port : "4010", url :
	 * "https://localhost:8443/vms" }, { id : "8", name : "Weston Bluetooth",
	 * port : "4006", url : "https://localhost:8443/vms" }, { id : "18", name :
	 * "TDC Test", port : "4014", url : "https://localhost:8443/vms" }, { id :
	 * "19", name : "Queue Time Test", port : "4012", url :
	 * "https://localhost:8443/vms" } ] }, ];
	 */
	$scope.init = function() {
		$scope.users = users;
		$scope.expenses = expenses;
	};

	$scope.formData = {};

	$scope.sendForm = function() {
		$http({
			method : 'POST',
			url : server + '/saveexpense',
			data : JSON.stringify($scope.formData), // pass in data as strings
			headers : {
				'Content-Type' : 'application/json'
			}
		}).success(function(data) {
			console.log(data);
			if (data.responseCode = "SUCCESS") {
				$scope.message = "Success";
				$scope.expenses = data.items;
			} else {
				// if successful, bind success message to message
				$scope.message = "Failed";
			}
		});
	};
	
	 
});
