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


//setTimeout("addweixinQr()",5000);

//这是play页面微信二维码的图片，因为百度不让有悬浮，现在隐藏，排名上去后在打开
function addweixinQr(){
	$("#WeiXinLogo").append("<div class=\"d-flex align-items-end flex-column\">");
	$("#WeiXinLogo").append("<img style=\"margin-bottom: 50px; width: 40px; height:40px;\" src=\"http://localhost:8080/kanhaoyi/image/weixinLogo.jpg\">");
	$("#WeiXinLogo").append("</div>");

	$("#WeiXinQR").append("<div class=\"d-flex align-items-end flex-column\">");
	$("#WeiXinQR").append("<svg id=\"WeiXinQR_X\" viewBox=\"0 0 24 24\" width=\"30\" height=\"30\" stroke=\"currentColor\" stroke-width=\"2\" fill=\"none\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"css-i6dzq1\"><polyline points=\"13 17 18 12 13 7\"></polyline><polyline points=\"6 17 11 12 6 7\"></polyline></svg>");
	$("#WeiXinQR").append("<img src=\"http://localhost:8080/kanhaoyi/image/WeiXin200x400.gif\">");
	$("#WeiXinQR").append("</div>");
	
	/* 点击微信logo时 显示二维码图片 */
	$("#WeiXinLogo").click(function(){
		var cssText = $("#WeiXinQR").attr("style") + ";display:block !important;";
	  	$("#WeiXinQR").css("cssText",cssText);
	}); 
	/* 点击二维码上的>> 符号时，隐藏二维码 */
	$("#WeiXinQR_X").click(function(){
		var cssText = $("#WeiXinQR").attr("style") + ";display:none !important;";
	  	$("#WeiXinQR").css("cssText",cssText);
	});
}

/* 这是play页面微信二维码的图片，因为百度不让有悬浮，现在隐藏，排名上去后在打开
 * 



<div class="d-flex align-items-end flex-column">
	<img style="margin-bottom: 50px; width: 40px; height:40px;" src="${indexpath}/image/weixinLogo.jpg">
</div>
		
<div class="d-flex align-items-end flex-column">
	<svg id="WeiXinQR_X" viewBox="0 0 24 24" width="30" height="30" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" class="css-i6dzq1"><polyline points="13 17 18 12 13 7"></polyline><polyline points="6 17 11 12 6 7"></polyline></svg>
	<img src="${indexpath}/image/WeiXin200x400.gif">
</div>	
	
 * */


/*
*/