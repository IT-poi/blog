var app = angular.module("myApp",[]);
app.controller("userCtrl", function($scope,$http) {
	console.log("response:");//TODO
	$http.get("/user")
	.success(function (response) {
		console.log(response);//TODO
		$scope.users = response.users;
	});
});