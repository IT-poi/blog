$(function(){
	console.log("response:");//TODO
    init();
    //右上角提示消息
//    $.bootstrapGrowl("欢迎来到我的博客", {
//	  ele: 'body', // which element to append to
//	  type: 'info', // (null, 'info', 'error', 'success')
//	  offset: {from: 'top', amount: 60}, // 'top', or 'bottom'
//	  align: 'right', // ('left', 'right', or 'center')
//	  width: 250, // (integer, or 'auto')
//	  delay: 4000,
//	  allow_dismiss: true,
//	  stackup_spacing: 10 // spacing between consecutively stacked growls.
//	});
 });

function init(){
//	设置导航栏的上滑下滑效果
	$("#top").headroom({
      "tolerance": 5,
      "offset": 200,
      "classes": {
    	"initial": "animated",
    	"pinned": "fadeInDown",
    	"unpinned": "fadeOutUp"
  		}
	});
};

function message(messages){
  $.bootstrapGrowl(messages, {
	  ele: 'body', // which element to append to
	  type: 'warning', // (null, 'warning', 'info', 'danger', 'success')
	  offset: {from: 'top', amount: 60}, // 'top', or 'bottom'
	  align: 'right', // ('left', 'right', or 'center')
	  width: 250, // (integer, or 'auto')
	  delay: 4000,
	  allow_dismiss: true,
	  stackup_spacing: 10 // spacing between consecutively stacked growls.
	});
};
