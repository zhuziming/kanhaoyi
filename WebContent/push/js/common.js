var $maskRule = $("#mask-rule"),//规则遮罩层
	$maskLottery = $("#mask-lottery"),//
    $mask = $("#mask"),//红包遮罩层
    $winning = $(".winning"),//红包
    $card = $("#card"),
    $close = $("#close");
    //link = false;//判断是否在链接跳转中

//规则
$(".a1").click(function () {
    $maskRule.show();
});
$("#close-rule").click(function () {
    $maskRule.hide();
});
$(".a2").click(function () {
	$maskLottery.show();
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/kanhaoyi/pushPrize/getPrizeRecord.action",
		dataType: "json",
		success: function(msg){
			$("#lotteryRecord").html(msg.msg);
		}
	});
	
});
$("#close-lottery").click(function () {
	$maskLottery.hide();
});


/*中奖信息提示*/
function win(mark1) {
    //遮罩层显示
    $mask.show();
    var mark2=mark1;
	switch(mark2)
   {
    case 0:
       	$("#text1").html("<h3>恭喜您获得</h3>"+
              "<p>1</p>"+
              "<b>金币</b>");
        break;
    case 1:
        $("#text1").html("<h3>恭喜您获得</h3>"+
              "<p>2</p>"+
              "<b>金币</b>");
        break;
	case 2:
        $("#text1").html("<h3>恭喜您获得</h3>"+
              "<p>伟哥<span>（金戈版）</span></p>"+
              "<b>一盒</b>");
        break;
	case 3:
        $("#text1").html("<h3>恭喜您获得</h3>"+
              "<p>50<span>元</span></p>"+
              "<b>优惠券一张</b>");
        break;
	case 4:
        $("#text1").html("<h3>恭喜您获得</h3>"+
              "<p>拿心</p>");
        break;
    case 5:
        $("#text1").html("<h3>恭喜您获得</h3>"+
        		"<p>1<span>G</span></p>"+
		        "<b>流量一张</b>");
		break;
    case 6:
        $("#text1").html("<h3>恭喜您获得</h3>"+
              "<p>雅诗兰黛</p>"+
              "<b>口红一支</b>");
        break;
    default:
    	$("#text1").html("<h3>恭喜您获得</h3>"+
        	"<p>拿心</p>");
  }
    //关闭弹出层
    $("#close,.win,.btn").click(function () {
    //$close.click(function () {
        $mask.hide();
	    $("#mask2").hide();
        $winning.removeClass("reback");
        $card.removeClass("pull");
    });
    /*$(".win,.btn").click(function () {
        link = true;
    });*/
}

//此处可以在commonjs中合并
function queryString(name) {
    name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
    var regexS = "[\\?&]" + name + "=([^&#]*)";
    var regex = new RegExp(regexS);
    var results = regex.exec(window.location.search);
    if(results === null) {
        return "";
    }
    else {
        return decodeURIComponent(results[1].replace(/\+/g, " "));
    }
}



