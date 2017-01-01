var app = angular.module("own",[]);
app.controller("ownController", function($scope,$http) {
	
	$scope.search = function(){
		var search = document.getElementById("search").value;
		if(search === ""){
			message("搜索关键字不能为空！");
			return;
		}else{
			window.location.href='/boke/search.html?keywords='+search;
		}
	};
	
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