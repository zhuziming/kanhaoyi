/* 得到用户名，并显示 */
getusernmae();
// 网站的域名
var webname = "http://localhost:8080/kanhaoyi";
function getusernmae(){
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/kanhaoyi/getusername.action",
		success: function(msg){
			var info = eval("("+msg+")");
			if(info.success==1){
				var date = eval("("+info.msg+")");
				$("#people").html(date.nickName); /* 放入昵称 */
				$("#people").attr("href","http://localhost:8080/kanhaoyi/back/index.action"); /* 更换后台链接 */
				$("#infoNum").html(date.infoNum); /* 放入消息数 */
				$("#signUp").hide();
				$("#logout").show();
			}
		}
	});
}

/* 留言
 * courseID 课程的id
 *  */
function say(courseID,say){
	if(say.length>255){
		alert("已超出最大字符数");
	}else{
		$.ajax({
			type: "POST",
			url: "http://localhost:8080/kanhaoyi/front/courseSay.action",
			data:{courseID:courseID,say:say},
			success: function(msg){
				var info = eval("("+msg+")");
				if(info.success==1){
					$("#comment").val("");
					$("#newComment").after(info.param.courseCommentHtml);
					alert(info.msg);
				}else{
					alert(info.msg);
				}
			}
		});
	}
}