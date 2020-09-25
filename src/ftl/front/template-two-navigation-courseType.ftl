<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>${courseType.name}</title>
    <link rel="icon" href="${imgpath}/favicon.ico" type="image/x-icon"/>
    <meta http-equiv="keywords" content="看好医,${courseType.name}">
	<meta http-equiv="description" content="">
    <link rel="stylesheet" href="${csspath}/boots/bootstrap.min.css">
    <link rel="stylesheet" href="${csspath}/boots/blog.css">
    <link rel="stylesheet" href="${csspath}/kanhaoyi-index.css">
    <script src="${jspath}/jquery.min.js"></script>
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
	  
	  <nav class="navbar navbar-expand-sm navbar-light breadcrumb mb-3">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="${indexpath}">分类</a>
		
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <#list courseTypeList as courseType_f>
		      	<#if courseType_f.id=courseType.id>
		      		<li class="nav-item active">
		      	<#else>
		      		<li class="nav-item">
				</#if>
		        	<a class="nav-link" href="${indexpath}/${courseType_f.nameSpace}.html">${courseType_f.name}</a>
		      	</li>
			  </#list>
		    </ul>
		  </div>
		</nav>

	  	<#if (courseList?size=0)>
		  	<div class="jumbotron">
			  <h1 class="display-4">Hello, world!</h1>
			  <p class="lead">暂时没有课程，去别的地方看看吧</p>
			  <hr class="my-4">
			  
			</div>
	  	<#else>
	  		<div class="row">
		  		<#list courseList as course>
				    <div class="col-xl-2 col-lg-3 col-md-3 col-sm-4 col-6">
				    	<a href="${indexpath}${course.coursePath}" target="_blank">
				    		<img width="100%;" src="${imgpath}/courseImg${course.picturePath}"></img>
				      		<p class="k-course-sort">${course.courseName}</p>
				    	</a>
				    </div>
			    </#list>
			</div>
		</#if>

	</div>

	<#include "../footer.ftl">
	
  </body>
</html>
<script src="${jspath}/kanhaoyi.js"></script>