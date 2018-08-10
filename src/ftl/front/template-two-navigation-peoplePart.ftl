<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>看好医-${peoplePart.partName}</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
	            <div class="btn-group" role="group">
				  <a class="btn btn-link text-dark">首页</a>
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
	  
	  <nav class="navbar navbar-expand-sm navbar-light breadcrumb mb-3">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <a class="navbar-brand" href="#">分类</a>
		
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		      <#list peoplePartList as peoplePart_f>
		      	<#if peoplePart_f.id=peoplePart.id>
		      		<li class="nav-item active">
		      	<#else>
		      		<li class="nav-item">
				</#if>
		        	<a class="nav-link" href="${indexpath}/${peoplePart_f.nameSpace}.html">${peoplePart_f.partName}</a>
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
				    	<a href="${indexpath}${course.coursePath}">
				    		<img width="100%;" src="${imgpath}/courseImg${course.picturePath}"></img>
				      		<p style="word-wrap:keep-all;white-space:nowrap;overflow: hidden;">col-xl-2 col-xl-2 </p>
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