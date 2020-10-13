<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    ${(courseDetail.meta)!""}
    ${(courseDetail.title)!""}
    <link rel="icon" href="${imgpath}/favicon.ico" type="image/x-icon"/>
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
	            <div class="btn-group d-none d-sm-block d-md-block d-lg-block d-xl-block" role="group">
				  	<a href="${indexpath}" class="btn btn-link text-dark">看好医</a>
				</div>
				<div class="btn-group" role="group" aria-label="Basic example">
				  	<form id="formQueryCourse" action="${indexpath}/frontIndex/indexQueryCourse.action" target="_blank" method="post">
				    	<div class="input-group align-items-center">
					  		<input type="text" name="keyword" id="query" class="form-control" placeholder="输入要查询的内容">
					  		<div class="input-group-append">
					    		<a class="text-muted" href="javascript:$('#formQueryCourse').submit()">
					       			<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="mx-3"><circle cx="10.5" cy="10.5" r="7.5"></circle><line x1="21" y1="21" x2="15.8" y2="15.8"></line></svg>
					    		</a>
					  		</div>
				    	</div>
				  	</form>
				  	<a class="btn btn-link text-dark" href="${indexpath}/push/lottery.html" target="_blank">
				  		抽奖
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
	  		<#if (courseDetail.videoID)??>
	  			<video  id="my-video" class="video-js col-lg-9" controls preload="auto"  poster="${imgpath}/courseImg${course.picturePath}">
					<source src="${indexpath}/mp4/course/${video.accountID}/${video.letterName}" type="video/mp4">
					<p class="vjs-no-js">
					  To view this video please enable JavaScript, and consider upgrading to a web browser that
					  <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
					</p>
			  	</video>
			  	
			  	<#if (courseDetailList?size) gt 1 >
				  	<#list courseDetailList as courseDetail_>
		        		<#if courseDetail_.id = courseDetail.id>
		        			<#if courseDetail__index=0><#-- 如果是第一个 -->
		        				<a  href="${indexpath}${courseDetailList[courseDetail__index+1].coursePath}" class="btn btn-success">下一讲</a>
		        			<#elseif !courseDetail__has_next><#-- 如果是最后一个 -->
		        				<a  href="${indexpath}${courseDetailList[courseDetail__index-1].coursePath}" class="btn btn-primary">上一讲</a>
		        			<#else>
		        				<a  href="${indexpath}${courseDetailList[courseDetail__index-1].coursePath}" class="btn btn-primary">上一讲</a>
		        				<a  href="${indexpath}${courseDetailList[courseDetail__index+1].coursePath}" class="btn btn-success">下一讲</a>
		        			</#if>
		        		</#if>
		        	</#list>
			  	</#if>
	  		</#if>
	  		
		  	
		  	
			<!-- 只有大屏幕时显示，是右侧的集数展示 -->
		  	<div class="list-group d-lg-none pt-3">
            	<#list courseDetailList as courseDetail_>
            		<#if courseDetail_.id = courseDetail.id>
            			<a href="${indexpath}${courseDetail_.coursePath}" class="list-group-item list-group-item-action list-group-item-primary">${courseDetail_.courseDetailName}</a>
            		<#else>
            			<a href="${indexpath}${courseDetail_.coursePath}" class="list-group-item list-group-item-action">${courseDetail_.courseDetailName}</a>
            		</#if>
            	</#list>
			</div> 
		  	
		  	<!-- 课程详情文本展示 -->
		  	<div>${(courseDetail.intro)!""}</div>
		  	

			<div class=" pt-3 pb-2">
			    <textarea id="comment" class="form-control mb-3" style="height:100px;" placeholder="想表达一下心情"></textarea>
			    <div class="d-flex flex-row-reverse bd-highlight">
			    	<a class="btn btn-primary" href="javascript:say(${course.id},$('#comment').val())" role="button">留下你的故事...</a>
			    </div>
			</div>
		  	
		  	<div class="row pt-3 pb-3">
		  		<#if courseLinkList[0]??>
		  			<div class="col-6 col-sm-6 col-md-6 col-lg-3 col-xl-3 pb-3">
			  			<a target="_blank" href="${(courseLinkList[0].webLocation)!""}">
				    		<img width="100%;" src="${imgpath}/courseLinkImg/${course.userID}/${course.id}/${courseLinkList[0].picture}${courseLinkList[0].format}"></img>
				      		<h5 style="word-wrap:keep-all;white-space:nowrap;overflow: hidden;">${(courseLinkList[0].intro)!""}</h5>
				    	</a>
			  		</div>
			  	<#else>	
			  	<#-- 
			  		<div class="col-6 col-sm-6 col-md-6 col-lg-3 col-xl-3 pb-3">
			  			<a target="_blank" href="${indexpath}/about-server.html">
				    		<img width="100%;" src="${imgpath}/190-100-placeholder.jpg"></img>
				    	</a>
			  		</div>
			  	 -->
				</#if>
		  		<#if courseLinkList[1]??>
			  		<div class="col-6 col-sm-6 col-md-6 col-lg-3 col-xl-3 pb-3">
			  			<a target="_blank" href="${(courseLinkList[1].webLocation)!""}">
				    		<img width="100%;" src="${imgpath}/courseLinkImg/${course.userID}/${course.id}/${courseLinkList[1].picture}${courseLinkList[1].format}"></img>
				      		<h5 style="word-wrap:keep-all;white-space:nowrap;overflow: hidden;">${(courseLinkList[1].intro)!""}</h5>
				    	</a>
			  		</div>
			  	<#else>	
			  	<#-- 
			  		<div class="col-6 col-sm-6 col-md-6 col-lg-3 col-xl-3 pb-3">
			  			<a target="_blank" href="${indexpath}/about-server.html">
				    		<img width="100%;" src="${imgpath}/190-100-placeholder.jpg"></img>
				    	</a>
			  		</div>
			  	 -->
		  		</#if>
				<#if courseLinkList[2]??>
			  		<div class="col-6 col-sm-6 col-md-6 col-lg-3 col-xl-3 pb-3">
			  			<a target="_blank" href="${(courseLinkList[2].webLocation)!""}">
				    		<img width="100%;" src="${imgpath}/courseLinkImg/${course.userID}/${course.id}/${courseLinkList[2].picture}${courseLinkList[2].format}"></img>
				      		<h5 style="word-wrap:keep-all;white-space:nowrap;overflow: hidden;">${(courseLinkList[2].intro)!""}</h5>
				    	</a>
			  		</div>
			  	<#else>	
			  	<#-- 
			  		<div class="col-6 col-sm-6 col-md-6 col-lg-3 col-xl-3 pb-3">
			  			<a target="_blank" href="${indexpath}/about-server.html">
				    		<img width="100%;" src="${imgpath}/190-100-placeholder.jpg"></img>
				    	</a>
			  		</div>
			  	 -->
		  		</#if>
				<#if courseLinkList[3]??>
			  		<div class="col-6 col-sm-6 col-md-6 col-lg-3 col-xl-3 pb-3">
			  			<a target="_blank" href="${(courseLinkList[3].webLocation)!""}">
				    		<img width="100%;" src="${imgpath}/courseLinkImg/${course.userID}/${course.id}/${courseLinkList[3].picture}${courseLinkList[3].format}"></img>
				      		<h5 style="word-wrap:keep-all;white-space:nowrap;overflow: hidden;">${(courseLinkList[3].intro)!""}</h5>
				    	</a>
			  		</div>
			  	<#else>	
			  	<#-- 
			  		<div class="col-6 col-sm-6 col-md-6 col-lg-3 col-xl-3 pb-3">
			  			<a target="_blank" href="${indexpath}/about-server.html">
				    		<img width="100%;" src="${imgpath}/190-100-placeholder.jpg"></img>
				    	</a>
			  		</div>
			  	 -->
		  		</#if>
		  	</div>

			<!-- 展示点赞最多的5条评论 -->
		  	${GoodPraise}

		  	<div id="newComment" class="alert alert-success" role="alert">
			  	以下是最新评论
			</div>
			
			<button type="button" onclick="loadNewComment()" id="moreLoad" class="btn btn-primary btn-lg btn-block mb-4">
				加载更多...
			</button>
			
			
		  	
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
	
	<!-- 播放页微信的小图标 固定在低部 -->
	<div id="WeiXinLogo" class="weixinLogo_s">
		
	</div>
	<!-- 播放页的添加客服微信图片  固定在低部 右边-->
	<div id="WeiXinQR" class="weixinQR_s d-none d-sm-none d-md-none d-lg-block d-xl-block">
		
	</div>
	
	<#include "../footer.ftl">
	
  </body>
</html>
<#if (courseDetail.videoID)??>
<script type="text/javascript">
	var myPlayer = videojs('my-video',{ autoplay: true,fluid: true }, function () { });
</script>
</#if>
<script src="${jspath}/kanhaoyi.js"></script>
<script src="${jspath}/kanhaoyi.play.js"></script>
<script>
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
				}
			}
		});
	}
</script>
