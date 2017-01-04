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
/**
 * html工具方法
 * @type {Object}
 */
var HtmlUtil = {
    /*1.用浏览器内部转换器实现html转码*/
    htmlEncode:function (html){
        //1.首先动态创建一个容器标签元素，如DIV
        var temp = document.createElement ("div");
        //2.然后将要转换的字符串设置为这个元素的innerText(ie支持)或者textContent(火狐，google支持)
        (temp.textContent != undefined ) ? (temp.textContent = html) : (temp.innerText = html);
        //3.最后返回这个元素的innerHTML，即得到经过HTML编码转换的字符串了
        var output = temp.innerHTML;
        temp = null;
        return output;
    },
    /*2.用浏览器内部转换器实现html解码*/
    htmlDecode:function (text){
        //1.首先动态创建一个容器标签元素，如DIV
        var temp = document.createElement("div");
        //2.然后将要转换的字符串设置为这个元素的innerHTML(ie，火狐，google都支持)
        temp.innerHTML = text;
        //3.最后返回这个元素的innerText(ie支持)或者textContent(火狐，google支持)，即得到经过HTML解码的字符串了。
        var output = temp.innerText || temp.textContent;
        temp = null;
        return output;
    },
    /*3.用正则表达式实现html转码*/
    htmlEncodeByRegExp:function (str){  
         var s = "";
         if(str.length == 0) return "";
         s = str.replace(/&/g,"&amp;");
         s = s.replace(/</g,"&lt;");
         s = s.replace(/>/g,"&gt;");
         s = s.replace(/ /g,"&nbsp;");
         s = s.replace(/\'/g,"&#39;");
         s = s.replace(/\"/g,"&quot;");
         return s;  
   },
   /*4.用正则表达式实现html解码*/
   htmlDecodeByRegExp:function (str){  
         var s = "";
         if(str.length == 0) return "";
         s = str.replace(/&amp;/g,"&");
         s = s.replace(/&lt;/g,"<");
         s = s.replace(/&gt;/g,">");
         s = s.replace(/&nbsp;/g," ");
         s = s.replace(/&#39;/g,"\'");
         s = s.replace(/&quot;/g,"\"");
         return s;  
   }
};