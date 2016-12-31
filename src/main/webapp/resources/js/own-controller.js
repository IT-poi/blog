var app = angular.module("own",[]);
app.controller("ownController", function($scope,$http) {
	
	$scope.blogger = {};
	
	$(function(){
		$scope.getManager();
	});
	

	$scope.getManager = function(){
		$http.get('/manager/easyinfo').
	        success(function(response){
	        	if(response.status === "ok"){
	        		$scope.blogger = response.data;
	        	}else{
	        		message("请求发生错误");
	        	}
	        });
	};
	
});