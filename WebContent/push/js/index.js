$(function () {
    var $blin = $(".light p"),//所有彩灯
	    $blin2 = $(".light2 p"),//所有彩灯
        $prize = $(".play li").not("#btn"),//含谢谢参与的所有奖品
        $change = $("#change"),//显示剩余机会
        $btn = $("#btn"),//开始抽奖按钮
        length = $prize.length,//奖品总数
        data = {count: 1},//次数
        bool = true,//判断是否可点击,true为可点击
        mark = 0,//标记当前位置，区间为0-7
        timer;//定时器

    init();
    //默认动画效果
    function init() {
        timer = setInterval(function () {
            //不能调用animate函数，不然标记会有问题
            $blin.toggleClass("blin");//彩灯动画
			$blin2.toggleClass("blin");//彩灯动画
            //九宫格动画
            length++;
            length %= 8;
            $prize.eq(length - 1).removeClass("select");
            $prize.eq(length).addClass("select");

            //位置标记的改变
            mark++;
            mark %= 8;
        }, 1000);
    }

    //点击抽奖
    $btn.click(function () {
        if (bool) {//若未按下
            bool = false;
            
            $.ajax({
    			type: "POST",
    			url: "http://localhost:8080/kanhaoyi/pushPrize/lottery.action",
    			dataType: "json",
    			success: function(msg){
    				if(msg.success==1){
    					console.log("结果："+msg.param.lotteryResult);
    					console.log("mark："+mark);
    					var count_t = parseInt(msg.param.lotteryCount);
    					$("#change").html(count_t);
    					var random_p = parseInt(msg.param.lotteryResult)-mark;
    					if(count_t==0){
    						$(".main-h2").html("<a href='http://localhost:8080/kanhaoyi/push/addLottery.html'>点击可以获得更多抽奖机会</a>");
    					}
    	                clickFn(random_p);
    				}else{
    					alert(msg.msg);
    				}
    			}
    		});
        }
    });

    //点击旋转
    function clickFn(random_p) {
        clearInterval(timer);//点击抽奖时清除定时器
        var random = random_p ; 
        mark += random;
        mark %= 8;
        console.log("mark %= 8 : " + mark);
        //默认先转4圈
        random += 32;//圈数 * 奖品总数
        //调用旋转动画
        for (var i = 1; i <= random; i++) {
            setTimeout(animate(), 2 * i * i);//第二个值越大，慢速旋转时间越长
        }
        //停止旋转动画
        setTimeout(function () {
            setTimeout(function () {
                bool = true;
                win(mark);
            }, 1000);
        }, 2 * random * random);
    }

    //动画效果
    function animate() {
        return function () {
            $blin.toggleClass("blin");//彩灯动画
			$blin2.toggleClass("blin");//彩灯动画
            //九宫格动画
            length++;
            length %= 8;
            $prize.eq(length - 1).removeClass("select");
            $prize.eq(length).addClass("select");
        }
    }

    //中奖信息提示
    $("#close,.win,.btn").click(function () {
        clearInterval(timer);//关闭弹出时清除定时器
        init();
    });

});