var app = angular.module("detial",[]);
app.controller("detialController", function($scope,$http) {
	function GetQueryString(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	};
	
	//初始化
//	$scope.recentPages = {};
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
		var articleId = GetQueryString("id");
		$scope.getArtile(articleId);
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
		$.post('/article/pagelist',
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
	
	$scope.getArtile = function(articleId){
		$.get('/ArticleAction/getArticle?articleId='+articleId).
	        success(function(response){
	        	console.log(response);
	        	$scope.article = response.data;
	        	console.log($scope.article);
	        });
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
	
});