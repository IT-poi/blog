var app = angular.module("detial",[]);
app.controller("detialController", function($scope,$http) {
	
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
	
	$scope.haveReviews = true;
	$scope.articles = {};
	$scope.reviewList = [];
	
	$(function(){
		var articleId = GetQueryString("id");
		$scope.getArtile(articleId);
		if($scope.reviewList.length <= 0){
			$scope.haveReviews = true;
		}
	});
	$userUrl = function(){
		
	};
	
	//提交评论
	$scope.sendReivews = function(reviews){
		var params = {
				name : reviews.name,
				url : reviews.url,
				email : reviews.email,
				reviewCount : reviews.reviewCount,
				level : reviews.level,
				articleId : reviews.articleId,
			},
			transFn = function(data) {
	            return $.param(data);
	        },
	        postCfg = {
	            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
	            transformRequest: transFn
	        };
			$http.post('/reviews/sendReviews',
		            params, postCfg).
		        success(function(response){
		        	console.log(response);
		        	if(response.status == "ok"){
		        		message("评论成功！");
		        		document.getElementById("review").value = "";
		        		$scope.getArtile($scope.articles.article.id);
		        	}else{
		        		message("发生错误，评论失败！");
		        	}
		        });
	};
	
	
	//评论
	$scope.reviews = function(){
		var userName = document.getElementById("userName").value;
		var userEmail = document.getElementById("userEmail").value;
		var userUrl = document.getElementById("userUrl").value;
		var reviewCount = document.getElementById("review").value;
		var articleId = $scope.articles.article.id;
		if(userName == ""){
			message("昵称为必填项！");
			return;
		}else{
			if(reviewCount == ""){
				message("请输入内容再评论！");
				return;
			}
			var reviews = {
				name : userName,
				email : userEmail,
				url : userUrl,
				reviewCount : reviewCount,
				level : 1,
				articleId : articleId
			};
			$scope.sendReivews(reviews);
		}
	};

	
	
	$scope.getArtile = function(articleId){
		$http.get('/ArticleAction/getArticle?articleId='+articleId).
	        success(function(response){
	        	$scope.articles = response.data;
	        	$scope.reviewList = response.data.reviews;

	        	if($scope.reviewList.length>=1){
	        		$scope.haveReviews = true;
	        	}else if($scope.reviewList.length<=0){
	        		$scope.haveReviews = false;
	        	}
				console.log($scope.articles);
		        $scope.articles.article.content = HtmlUtil.htmlDecodeByRegExp($scope.articles.article.content);
		        // console.log($scope.article.content);
	        	$('#article_content').html($scope.articles.article.content);
	        	console.log("-----------------------");
	        	console.log($scope.reviewList);

	        });
	};
	
});