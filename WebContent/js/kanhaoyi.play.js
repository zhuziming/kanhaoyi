/* 课程播放页引入的js */
/** 当页面被打开，增加课程点击量 */
function clickVolume(){
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/kanhaoyi/frontCourse/addClickVolume.action",
		data:{courseID:$("#courseID").html()},
		success: function(msg){
			
		}
	});
}

clickVolume();