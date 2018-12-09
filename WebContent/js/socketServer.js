var ws = new WebSocket("ws://192.168.0.107:9000/");   // 低版本的浏览器没有webSocket对象，会报错       
ws.onmessage = function (evt) {    // 当服务端向客户端发来一个削息时     
    console.log(evt.data);
	var msg = JSON.parse(evt.data);
	if(msg.role=="dialogue"){
		console.log(document.getElementById("li"+msg.clientID));
		if(document.getElementById("li"+msg.clientID)){
			updateClient(msg.clientID,msg.content);
		}else{
			createClient(msg.clientID,msg.content);
		}
	}else if(msg.role=="signUp"){
		if(msg.content=="success"){ // 注册成功
			contentPackage.clientID = msg.clientID; // 分配本次会话id
			contentPackage.role = "dialogue"; // 注册成功后，改注册为会话
		}else if(msg.content=="failure"){ // 注册失败
		
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

// 创建左边导航及右边聊天窗
function createClient(clientID,content){
	// 创建左边导航
	var liEle = "";
	liEle += "<li id='li"+clientID+"' class='nav-item' onclick='showMain("+clientID+")'>";
	liEle += "	<a class='nav-link' href='#'>";
	liEle += "		<svg class='octicon octicon-organization' viewBox='0 0 16 16' version='1.1' width='16' height='16' aria-hidden='true'><path fill-rule='evenodd' d='M16 12.999c0 .439-.45 1-1 1H7.995c-.539 0-.994-.447-.995-.999H1c-.54 0-1-.561-1-1 0-2.634 3-4 3-4s.229-.409 0-1c-.841-.621-1.058-.59-1-3 .058-2.419 1.367-3 2.5-3s2.442.58 2.5 3c.058 2.41-.159 2.379-1 3-.229.59 0 1 0 1s1.549.711 2.42 2.088C9.196 9.369 10 8.999 10 8.999s.229-.409 0-1c-.841-.62-1.058-.59-1-3 .058-2.419 1.367-3 2.5-3s2.437.581 2.495 3c.059 2.41-.158 2.38-1 3-.229.59 0 1 0 1s3.005 1.366 3.005 4z'></path></svg>";                
	liEle += "		游客 "+clientID;                  
	liEle += "		<span class='badge badge-warning float-right' id='liNum"+clientID+"'>1</span>";                 
	liEle += "	</a>";				  
	liEle += "</li>";                
	$("#ulContent").prepend(liEle);
	
	// 创建右边聊天窗体
	var mainEle = "";
	mainEle += "<main style='display:none;' id='main"+clientID+"' role='main' class='col-10 ml-auto pt-3 px-4'>";
	mainEle += "	<div id='sayContent"+clientID+"' class='jumbotron' style='height:500px; overflow-y: auto;'>";
	mainEle += "		<div class='alert'>";
	mainEle += "			<h6>游客"+clientID+"  "+new Date().Format("yyyy-MM-dd hh:mm:ss")+"</h6>";
	mainEle += "			<p>"+content+"</p>";
	mainEle += "		</div>";
	mainEle += "		<div id='sayBottom"+clientID+"'></div>";
	mainEle += "	</div>";
	mainEle += "	<div class='modal-footer'>";
	mainEle += "		<input class='form-control' type='text' id='say"+clientID+"'/>";
	mainEle += "		<button type='button' class='btn btn-primary' onclick='sendSay("+clientID+")'>发送</button>";
	mainEle += "	</div>";
	mainEle += "</main>";
	$("#contentBottom").before(mainEle);
}

// 更新对话窗，第二次发消息来，不创建对话窗，只添加新消息
function updateClient(clientID,content){
	var say="";
	say+="<div class='alert'>";
	say+="	<h6>游客 "+new Date().Format("yyyy-MM-dd hh:mm:ss")+"</h6>";
	say+="	<p>"+content+"</p>";
	say+="</div>";			
	
	if($("#main"+clientID).css("display")=="none"){
		var liNum = parseInt($("#liNum"+clientID).html());
		$("#liNum"+clientID).html(++liNum);
		$("#liNum"+clientID).addClass("badge-warning");
	}
	
	$("#sayBottom"+clientID).before(say); // 把消息添加到对话框最底部
	$("#sayContent"+clientID).scrollTop(document.getElementById("sayContent"+clientID).scrollHeight);// 滚动条拉到最底部
}

// 隐藏全部对话窗，显示当前选择的对话窗
function showMain(clientID){
	$("main").hide();
	$("#main"+clientID).show();
	$("#liNum"+clientID).html(0);
	$("#liNum"+clientID).removeClass("badge-warning");
}
function sendSay(clientID){
	var sayVal = $("#say"+clientID).val();
	var say="";
	say+="<div class='alert alert-primary'>";
	say+="<h6>看好医 "+new Date().Format("yyyy-MM-dd hh:mm:ss")+"</h6>";
	say+="<p>"+$("#say"+clientID).val()+"</p>";
	say+="</div>";			
			
	$("#sayBottom"+clientID).before(say); // 把消息添加到对话框最底部
	$("#sayContent"+clientID).scrollTop(document.getElementById("sayContent"+clientID).scrollHeight);// 滚动条拉到最底部
	$("#say"+clientID).val(""); // 清空输入框
	$("#say"+clientID).focus();
	ws.send(JSON.stringify(getContent("server",clientID,contentPackage.serverID,sayVal,contentPackage.role)));
}

/*
服务端第一次注册时，会得到自己的serverID

identity : [client][server]  身份
clientID : 客户端id
serverID : 课程id
content  : {"signUp":[success][failure][error],"dialogue":[]} 如果role字段为signUp，则返回注册成功或失败。如果role字段为dialogue，则返回交谈内容
role     : [signUp 注册][dialogue 交谈] 任务
*/
var contentPackage = {"identity":"","clientID":"","serverID":"","content":"","role":""};
function getContent(identity,clientID,serverID,content,role){
	contentPackage.identity = identity;
	contentPackage.clientID = clientID;
	contentPackage.serverID = serverID;
	contentPackage.content  = content;
	contentPackage.role     = role;
	return contentPackage;
}