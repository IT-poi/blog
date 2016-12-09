var app = angular.module("myApp",[]);
app.controller("userCtrl", function($scope,$http) {
	console.log("response:");//TODO
	$http.get("/article/list")
	.success(function (response) {
		console.log(response);//TODO
		$scope.recentPages = response.data;
	});
	
	$scope.nextPage = function(){
		var params = {
			currPage :1,
			pageSize : 5
		};
		$http({    
				method: 'POST',
	            data: params,
	            ignoreErrors: true,
	            headers: {'Accept': 'application/json',
	                      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
	            url:"/article/list2"
	            .success(function (result) {
	            	console.log(result);//TODO
	    			$scope.recentPages = result.data;
	            })
            });
	};
	
});