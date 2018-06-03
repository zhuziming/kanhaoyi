/* 得到用户名，并显示 */
getusernmae();

function getusernmae(){
	$.ajax({
		type: "POST",
		url: "/kanhaoyi/getusername.action",
		success: function(msg){
			var info = eval("("+msg+")");
			if(info.success==1){
				var date = eval("("+info.msg+")");
				$("#people").html(date.nickName); /* 放入昵称 */
				$("#people").attr("href","/kanhaoyi/back/index.action"); /* 更换后台链接 */
				$("#infoNum").html(date.infoNum); /* 放入消息数 */
				$("#signUp").hide();
				$("#logout").show();
			}
		}
	});
}