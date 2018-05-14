/* 得到用户名，并显示 */
getusernmae();

function getusernmae(){
	$.ajax({
		type: "POST",
		url: "/webWork/getusername.action",
		success: function(msg){
			var info = eval("("+msg+")");
			if(info.success==1){
				$("#people").html(info.msg);
			}
		}
	});
}