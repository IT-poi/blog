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
	$scope.second = [];
	
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
	$scope.sendReivews = function(parentId, reviews){
		var params = {};
		if(parentId != null){
			console.log("父评论不为空");
			console.log(parentId);
			params = {
					name : reviews.name,
					url : reviews.url,
					email : reviews.email,
					reviewCount : reviews.reviewCount,
					level : reviews.level,
					articleId : reviews.articleId,
					parentId : parentId
				};
		}else{
			params = {
					name : reviews.name,
					url : reviews.url,
					email : reviews.email,
					reviewCount : reviews.reviewCount,
					level : reviews.level,
					articleId : reviews.articleId
				};
		}
		
			var transFn = function(data) {
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
		        		message("评论成功！",'success');
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
			message("昵称为必填项！",'danger');
			return;
		}else{
			if(reviewCount == ""){
				message("请输入内容再评论！", 'danger');
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
			$scope.sendReivews(null, reviews);
		}
	};
	
	//回复
	$scope.response = function(id){
		console.log(id);
		$('#myModal').modal({
			show : true,//显示弹出层
			backdrop : 'static',//禁止位置关闭
			keyboard : false//关闭键盘事件
		});
		$('#responseBtn').click(function(){
			var userName = document.getElementById("userName").value.trim();
			var userEmail = document.getElementById("userEmail").value.trim();
			var userUrl = document.getElementById("userUrl").value.trim();
			var articleId = $scope.articles.article.id;
			
			var reviewCount  = $('#response').val().trim();
			console.log(reviewCount);
			if(userName==""){
				message("请先填写昵称项！", "danger");
			}
			else if(reviewCount ==""){
				message("请输入回复内容", "danger");
			}else{
				var reviews = {
						name : userName,
						email : userEmail,
						url : userUrl,
						reviewCount : reviewCount,
						level : 1,
						articleId : articleId
					};
					$scope.sendReivews(id, reviews);
					document.getElementById("response").value = "";
					$('#myModal').modal("hide");
			}
//			$('#killPhoneMessage').hide().html('<label class="label label-danger">错误！</label>').show(300);
		});
	};

	
	
	$scope.getArtile = function(articleId){
		$http.get('/ArticleAction/getArticle?articleId='+articleId).
	        success(function(response){
	        	$scope.articles = response.data;
	        	console.log(response.data.reviews);
//	        	$scope.reviewList = response.data.reviews;
	        	$scope.reviewList = [];
	        	$scope.second = [];
	        	for ( var i = 0; i < response.data.reviews.length; i++) {
	        		console.log("-------======------");
	        		if(response.data.reviews[i].parentReview == null){
	        			$scope.reviewList.push(response.data.reviews[i]);
	        		}else{
	        			$scope.second.push(response.data.reviews[i]);
	        		}
				}

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