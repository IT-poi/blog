var app = angular.module("myApp",[]);
app.controller("indexController", function($scope,$http) {
	
	//消息
	var message = function(messages){
	    //右上角提示消息
	    $.bootstrapGrowl(messages, {
		  ele: 'body', // which element to append to
		  type: 'success', // (null, 'info', 'error', 'success')
		  offset: {from: 'top', amount: 60}, // 'top', or 'bottom'
		  align: 'right', // ('left', 'right', or 'center')
		  width: 250, // (integer, or 'auto')
		  delay: 4000,
		  allow_dismiss: true,
		  stackup_spacing: 10 // spacing between consecutively stacked growls.
		});
	 };
	
	//初始化
	$scope.recentPages = {};
//	$scope.blogger = {};
	
//	$(function(){
//		console.log("------------" + $scope.recentPages.currPage);
//		if($scope.recentPages.currPage == null){
//			message("啥子哦！");
//			$http.get("/article/list")
//			.success(function (response) {
//				console.log(response);//TODO
//				if(response.status === "OK"){
//					$scope.recentPages = response.data;
//				}else{
//					console.log(response.messages);
//				}
//			});
//		}else{
//			message("耍一哈");
//			console.log($scope.recentPages.currPage);
//			$scope.refreshPage($scope.recentPages);
//		}
//	 });
	$(function(){
		$scope.getManager();
	});

	$http.get("/article/list")
	.success(function (response) {
		console.log(response);//TODO
		if(response.status === "OK"){
			$scope.recentPages = response.data;
		}else{
			console.log(response.messages);
		}
	});
	
	
	//请求页面
	var queryArticlePage = function(pageBean){
		var params = {
			currPage : pageBean.currPage,
			pageSize : pageBean.pageSize,
			order : pageBean.order,
			orderBy : pageBean.orderBy,
			totalCount : pageBean.totalCount,
			totalPage : pageBean.totalPage
		};
		console.log(params);
		$http.post('/article/pagelist',
		            params).
		        success(function(response){
		        	$scope.recentPages = response.data;
		        });
//		$http({
//				method: 'POST',
//	            data: params,
//	            ignoreErrors: true,
//	            url:"/article/pagelist"
//            })
//            .success(function (result) {
//            	$scope.recentPages = result.data;
//            });
	};
	
	$scope.getManager = function(){
		$http.get('/manager/easyinfo').
	        success(function(response){
	        	console.log(response);
	        	$scope.blogger = response.data;
	        	console.log($scope.blogger);
	        });
	};
	
	$scope.refreshPage = function(){
		queryArticlePage($scope.recentPages);
	};
	//下一页
	$scope.nextPage = function(){
		console.log("nextPage()");
		if($scope.recentPages.currPage>=$scope.recentPages.totalPage){
			message("已经到最后一页啦！");
		}else if($scope.recentPages.currPage >= 1){
			//
			$scope.recentPages.currPage++;
			queryArticlePage($scope.recentPages);
		}else{
			message("抱歉，发生了未知错误！");
		}
	};
	//上一页
	$scope.previousPage = function(){
		console.log("previousPage()");
		if($scope.recentPages.currPage<=1){
			message("前面没有了！");
		}else if($scope.recentPages.currPage <= $scope.recentPages.totalPage){
			//
			$scope.recentPages.currPage--;
			queryArticlePage($scope.recentPages);
		}else{
			console.log("抱歉，发生了未知错误！");
		}
	};
	//首页
	$scope.firstPage = function(){
		console.log("firstPage()");// TODO
		if($scope.recentPages.currPage==1){
			message("你在卖萌么！");
		}else{
			$scope.recentPages.currPage = 1;
			queryArticlePage($scope.recentPages);
		}
	};
	//尾页
	$scope.lastPage = function(){
		if($scope.recentPages.currPage == $scope.recentPages.totalPage){
			message("你在卖萌么！");
		}else{
			$scope.recentPages.currPage = $scope.recentPages.totalPage;
			queryArticlePage($scope.recentPages);
		}
	};
	
});