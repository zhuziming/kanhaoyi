var ws = new WebSocket("ws://192.168.0.107:9000/");   // 低版本的浏览器没有webSocket对象，会报错   
ws.onmessage = function (evt) {    // 当服务端向客户端发来一个削息时     
    console.log(evt.data);
	var msg = JSON.parse(evt.data);
	
	if(msg.role=="dialogue"){
		addBeforeSayServer(msg.content);
	}else if(msg.role=="signUp"){
		addBeforeSayServer("请问您有什么需要帮助的？");
		if(msg.content=="success"){ // 注册成功
			$("#sayButton").removeClass('disabled'); 
			$("#customerTitleStatus").html("在线");
			contentPackage.clientID = msg.clientID; // 分配本次会话id
			contentPackage.role = "dialogue"; // 注册成功后，改注册为会话
			showCustomer();
		}else if(msg.content=="failure"){ // 注册失败
			$("#customerTitleStatus").html("不在线");
			$("#sayButton").addClass('disabled'); 
		}
	}
	
};       
       
ws.onclose = function() {       // 当服务端关闭时
   alert("Closed");       
};       
       
ws.onerror = function(err) {       // 当服务端出错时
   alert("Error: " + err);       
};   

//var time1 = new Date().format("yyyy-MM-dd HH:mm:ss");              
// 对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.Format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} 

/* 显示隐藏客服元素 */
function showCustomer(){
	if($("#customer").css("display")=="none"){
		$("#customer").show();
	}else{
		$("#customer").hide();
	}
}
/* 发送要说的话 */
function sendSay(){
	var sayVal = $("#say").val();
	if(sayVal==null || sayVal==""){
		return;
	}
	addBeforeSayClient(); // 添加对话
}

// 添加客户端消息
function addBeforeSayClient(){
	var sayVal = $("#say").val();
	var say="";
	say+="<div>";
	say+="<p>您："+new Date().Format("yyyy-MM-dd hh:mm:ss")+"<br/>"+$("#say").val()+"</p>";
	say+="</div>";			
			
	$("#sayBottom").before(say); // 把消息添加到对话框最底部
	$("#sayContent").scrollTop(document.getElementById("sayContent").scrollHeight);// 滚动条拉到最底部
	$("#say").val(""); // 清空输入框
	$("#say").focus();
	contentPackage.content = sayVal;
	ws.send(JSON.stringify(contentPackage));
}
// 添加服务器端消息
function addBeforeSayServer(serverContent){
	var say="";
	say+="<div class='alert alert-primary'>";
	say+="看好医："+new Date().Format("yyyy-MM-dd hh:mm:ss")+"<br/>"+serverContent;
	say+="</div>";	
	$("#sayBottom").before(say); // 把消息添加到对话框最底部
	$("#sayContent").scrollTop(document.getElementById("sayContent").scrollHeight);// 滚动条拉到最底部
}


// 数据包
var contentPackage = {"identity":"","clientID":"","serverID":"","content":"","role":""};
function getContent(identity,clientID,serverID,content,role){
	contentPackage.identity = identity;
	contentPackage.clientID = clientID;
	contentPackage.serverID = serverID;
	contentPackage.content  = content;
	contentPackage.role     = role;
	return contentPackage;
}