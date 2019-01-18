<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>看好医-个人中心-首页</title>
    <link rel="icon" href="${imgpath}/favicon.ico" type="image/x-icon"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="${indexpath}/css/boots/bootstrap.min.css">
    <link rel="stylesheet" href="${indexpath}/css/boots/blog.css">
    <link rel="stylesheet" href="${indexpath}/css/kanhaoyi-index.css">    
    <script src="${indexpath}/js/jquery.min.js"></script>
	<script src="${indexpath}/js/boots/bootstrap.min.js"></script>

  </head>
  
  <body>
  	<header>
      <#include "../head.ftl">
	  <div class="bg-info text-white">
	  	<div class="container">
	  		<div class="row pt-5">
	  			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12">
		 			<div class="media">
					  <img width="150px;" src="${imgpath}/${user.picture}" class="figure-img img-fluid rounded-circle mr-3">
					  <div class="media-body">
					    <h5 class="mt-0">${user.account}</h5>
					   	<span> ${user.nickname}</span><br/>
					   	<span>金币：${user.money}</span>
					  </div>
					</div>
				</div>
				<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12">
		 			
		 		</div>
	  		</div>
	  		
      	</div>
	  </div> 
	    
    </header>
    
	<div class="container">
	 	
	 	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarText">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item active">
		        <a class="nav-link btn btn-outline-primary" href="${indexpath}/back/index.action">个人中心</a>
		      </li>
		     <#-- 注释
			      <li class="nav-item active">
			        <a class="nav-link" href="${indexpath}/back/myFootprint.action">我的足迹</a>
			      </li>
			      <li class="nav-item active">
			        <a class="nav-link" href="${indexpath}/back/collect.action">收藏</a>
			      </li>
		      -->
		    </ul>
		    
		  </div>
		</nav>
		
		
		<div class="container mt-2">
			<#-- 老师 -->
			<#if (teacher!"")=="teacher"> 
				<a class="btn btn-success mb-2" href="${indexpath}/teacher/uploadVideoPage.action" role="button">上传视频</a>
				<a class="btn btn-success mb-2" href="${indexpath}/teacher/myVideoPage.action" role="button">我的视频</a>
				<a class="btn btn-success mb-2" href="${indexpath}/teacher/publishCoursePage.action" role="button">发布课程</a>
				<a class="btn btn-success mb-2" href="${indexpath}/teacher/myCoursePage.action" role="button">我的课程</a>
			</#if>
			<#-- 超级管理员 -->
			<#if (admin!"")=="admin"> 
				<a class="btn btn-danger  mb-2" href="${indexpath}/manage/indexPage.action" role="button">后台管理页</a>
			</#if>
			<#-- 客服 -->
			<#if (customerService!"")=="customerService">
				<a class="btn btn-info mb-2" href="${indexpath}/customer/indexPage.action" role="button">呼叫中心</a>
			</#if>
		</div>
		

		
		<div class="jumbotron">
		  <h1 class="display-4">Hello, ${user.nickname}</h1>
		  <p class="lead"></p>
		  <hr class="my-4">
		  <p>
		  	${essay}
		  </p>
		  <p class="lead">
		    <a class="btn btn-primary btn-lg" href="${indexpath}" role="button">去首页看看吧!</a>
		  </p>
		</div>

	</div>
	
	
	<#include "../footer.ftl">
	
  </body>
</html>
