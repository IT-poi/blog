$(function(){
  $('#re_top').click(function(){$('html,body').animate({scrollTop: '0px'}, 800);});
  $.ajax({
    url: '/manager/easyinfo',
    type:'POST',
    success: function (data) {
      // console.log(data);
      $('#username').text(data['data']['name']);
        // alert(data);
    }
  })
});

//查找url中的参数(传入参数名)
function getUrlParameter(pname){
  var urlinfo=window.location.href;     //获取当前页面的url 
  var len=urlinfo.length;         //获取url的长度 
  var offset=urlinfo.indexOf("?");    //设置参数字符串开始的位置 
  var infoStr=urlinfo.substr(offset,len);//取出参数字符串 这里会获得类似“id=1”这样的字符串 
  var info=infoStr.split(/=|&&|\?/);;   //对获得的参数字符串按照“=”进行分割 
  var value = '';         //得到参数值 
  for (var i = info.length - 1; i >= 0; i--) {
    
    if(info[i] == pname && i % 2 != 0){
      value = info[i+1];
    }
    
  }

  return value;
}
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