var app = angular.module("myApp",[]);
app.controller("userCtrl", function($scope,$http) {
	console.log("response:");//TODO
	$http.get("/article")
	.success(function (response) {
		console.log(response);//TODO
		$scope.recentPages = response.recentPages;
	});
});