<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>看好医-外科</title>
    <link rel="icon" href="${imgpath}/favicon.ico" type="image/x-icon"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="${csspath}/boots/bootstrap.min.css">
    <link rel="stylesheet" href="${csspath}/boots/blog.css">
	<link rel="stylesheet" href="${csspath}/video-js.css">
    <link rel="stylesheet" href="${csspath}/kanhaoyi-index.css">
    <script src="${jspath}/jquery.min.js"></script>
	<script src="${jspath}/boots/bootstrap.min.js"></script>
	<script src="${jspath}/video.min.js"></script>
  </head>
  
  <body>
  	<header class="pb-3">
      <div class="bg-dark collapse show img-fluid k-top-of-page" id="navbarHeader" >
        <nav class="navbar navbar-expand-md navbar-dark absolute-top k-nav-back">
        	<div class="container" >
	            <div class="btn-group" role="group">
				  <a href="${indexpath}" class="btn btn-link text-dark">首页</a>
				</div>
				<div class="btn-group" role="group" aria-label="Basic example">
				  <a id="people" href="${indexpath}/loginPage.action" class="btn btn-link text-dark">
				  	未登录
				  </a>
				  <a id="signUp" href="${indexpath}/signUpPage.action" class="btn btn-link text-dark">
				  	注册
				  </a>
				  <a id="logout" href="${indexpath}/logout.action" class="btn btn-link text-dark" style="display: none;">
				  	退出
				  </a>
				  <a class="btn btn-link text-dark" href="${indexpath}/info-list.html">
				  	消息<span class="text-danger" id="infoNum"></span>
				  </a>
				</div>
        	</div>
	    </nav>
      </div>
    </header>
    
	<div class="container">
	  
	  <nav  class="navbar navbar-expand-sm navbar-light breadcrumb mb-3 mt-3">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#">分类</a>
		
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <#list courseTypeList as courseType>
		      	<#if courseType.id=course.courseTypeID>
		      		<li class="nav-item active">
		      	<#else>
		      		<li class="nav-item">
				</#if>
		        	<a class="nav-link" href="${indexpath}/${courseType.nameSpace}.html">${courseType.name}</a>
		      	</li>
			  </#list>
		    </ul>
		  </div>
		</nav>
	  
	 	<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}">首页</a></li>
		    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}/${courseType.nameSpace}.html">${courseType.name}</a></li>
		    <li class="breadcrumb-item active">${course.courseName}</li>
		    <li class="d-none" id="courseID">${course.id}</li>
		  </ol>
		</nav>
	 
	 <div class="row ">
	 	<div class="col-lg-9">
	  		<video width="100%" id="my-video" class="video-js" controls preload="auto"  poster="${imgpath}/courseImg${course.picturePath}" data-setup="{}">
				<source src="${indexpath}/mp4/course/${video.accountID}/${video.letterName}" type="video/mp4">
				<p class="vjs-no-js">
				  To view this video please enable JavaScript, and consider upgrading to a web browser that
				  <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
				</p>
		  	</video>
		  	
			<div class="input-group pt-3 pb-2">
			  <input type="text" id="comment" class="form-control" placeholder="想表达一下心情">
			  <div class="input-group-append">
			    <button class="btn btn-outline-secondary" onclick="say(${course.id},$('#comment').val())" type="button">留言</button>
			  </div>
			</div>
		  	
		  	${GoodPraise}

		  	
		  	<div id="newComment" class="alert alert-success" role="alert">
			  	以下是最新评论
			</div>
			
			<button type="button" onclick="loadNewComment()" id="moreLoad" class="btn btn-primary btn-lg btn-block mb-4">
				加载更多...
			</button>
			
			<div>${(course.intro)!""}</div>
		  	
	 	</div>
	
	 	<div class="col-lg-3 ">
            
            <div class="list-group">
            	<#list courseDetailList as courseDetail_>
            		<#if courseDetail_.id = courseDetail.id>
            			<a href="${indexpath}${courseDetail_.coursePath}" class="list-group-item list-group-item-action list-group-item-primary">${courseDetail_.courseDetailName}</a>
            		<#else>
            			<a href="${indexpath}${courseDetail_.coursePath}" class="list-group-item list-group-item-action">${courseDetail_.courseDetailName}</a>
            		</#if>
            	</#list>
			</div> 
	 	</div>
	 </div>
	</div>

	<#include "../footer.ftl">
	
  </body>
</html>
<script type="text/javascript">
	var myPlayer = videojs('my-video',{ fluid: true }, function () { });
</script>
<script src="${jspath}/kanhaoyi.js"></script>
<script>
	window.onload=loadNewComment();

	/* 当前评论信息的id，每一次加载评论都更新这个值 */
	var commentID=0;
	/* 加载最新评论 */
	function loadNewComment(){
		$.ajax({
			type: "POST",
			url: "${indexpath}/front/loadNewComment.action",
			data:{courseID:${course.id},commentID:commentID},
			success: function(msg){
				var info = eval("("+msg+")");
				if(info.success==1){
					$("#moreLoad").before(info.msg);
					commentID = info.param.commentID;
				}else if(info.success==2){
					$("#moreLoad").addClass("disabled")
					$("#moreLoad").html(info.msg);
				}
			}
		});
	}
	/* 赞 */
	function praise(commentID){
		$.ajax({
			type: "POST",
			url: "${indexpath}/front/praise.action",
			data:{commentID:commentID},
			success: function(msg){
				var info = eval("("+msg+")");
				var pNum = $("#praiseNum"+commentID).html();
				if(info.success==1){
					pNum = parseInt(pNum)+1;
					$("span[name='praiseNum"+commentID+"']").html(pNum);
				}else if(info.success==2){
					pNum = parseInt(pNum)-1;
					$("span[name='praiseNum"+commentID+"']").html(pNum);
				}
			}
		});
	}
</script>