<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>看好医</title>
    <link rel="icon" href="${imgpath}/favicon.ico" type="image/x-icon"/>
    <meta http-equiv="keywords" content="看好医,医学讲解,医学视频">
	<meta http-equiv="description" content="看好医从事疾病的讲解，让用户了解病情的发生发展过程，从而帮助疾病的预防">
    <link rel="stylesheet" href="${csspath}/boots/bootstrap.min.css">
    <link rel="stylesheet" href="${csspath}/boots/blog.css">
    <link rel="stylesheet" href="${csspath}/kanhaoyi-index.css">
    <script src="${jspath}/jquery.min.js"></script>
	<script src="${jspath}/popper.min.js"></script>
	<script src="${jspath}/boots/bootstrap.min.js"></script>
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
		

		<#if courseList?? && (courseList?size > 0) >
			<#list courseList as course>
			 	<a href="${indexpath}${course.coursePath}" target="_blank">
		      		<div class="media">
					  <img class="mr-3" src="${imgpath}/courseImg${course.picturePath}">
					  <div class="media-body">
					    <h5 class="mt-0">${course.courseName}</h5>
					    <p style="display: -webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;overflow: hidden;">
					    	${course.intro}
					    </p>
					  </div>
					</div>
		    	</a>
	    	</#list>
		<#else>
			<div class="alert alert-success" role="alert">
			  <h4 class="alert-heading">未查询到结果</h4>
			  <hr>
			  <p class="mb-0">您可以给我们留言，说明您的问题，我们会尽快请专家为您做视频讲解</p>
			</div>
		</#if>

	</div>

	<#include "../footer.ftl">
	
  </body>
</html>
<script src="${jspath}/kanhaoyi.js"></script>