$(function(){
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