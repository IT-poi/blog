var app = angular.module("search",[]);
app.controller("searchController", function($scope,$http) {
	
	$scope.search = function(){
		var search = document.getElementById("search").value;
		if(search === ""){
			message("搜索关键字不能为空！");
			return;
		}else{
			window.location.href='/boke/search.html?keywords='+search;
		}
	};
	
	function GetQueryString(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	};
	
	//初始化
	$scope.recentPages = {};
	$scope.pageList = [];
	var keywords = GetQueryString("keywords");
	console.log("---------")
	console.log(keywords);
	

	$(function(){
		$scope.refreshPage();
	});
	
	//请求页面
	$scope.queryArticlePage = function(keywords, pageBean){
		var params = {
			currPage : pageBean.currPage,
			pageSize : pageBean.pageSize,
			keywords : keywords
		},
		transFn = function(data) {
            return $.param(data);
        },
        postCfg = {
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
            transformRequest: transFn
        };
		$http.post('/article/keywordsPagelist',
		            params, postCfg).
		        success(function(response){
		        	$scope.recentPages = response.data;
		        	$scope.pageList = response.data.list;
		        	for(var i = 0; i<$scope.pageList.length; i++){
		        		$scope.pageList[i] = ($scope.pageList[i].createTime).pattern("yyyy-MM-dd");
		        	}
		        	console.log($scope.pageList);
		        });
	};
	

	$scope.refreshPage = function(){
		var pageBean = {
			currPage : 1,
			pageSize : 5
		};
		$scope.queryArticlePage(keywords, pageBean);
	};
	//下一页
	$scope.nextPage = function(){
		console.log("nextPage()");
		if($scope.recentPages.currPage>=$scope.recentPages.totalPage){
			message("已经到最后一页啦！");
		}else if($scope.recentPages.currPage >= 1){
			//
			$scope.recentPages.currPage++;
			$scope.queryArticlePage(keywords, $scope.recentPages);
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
			$scope.queryArticlePage(keywords, $scope.recentPages);
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
			$scope.queryArticlePage(keywords, $scope.recentPages);
		}
	};
	//尾页
	$scope.lastPage = function(){
		if($scope.recentPages.currPage == $scope.recentPages.totalPage){
			message("你在卖萌么！");
		}else{
			$scope.recentPages.currPage = $scope.recentPages.totalPage;
			$scope.queryArticlePage(keywords, $scope.recentPages);
		}
	};
	
});