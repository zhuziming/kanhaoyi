<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>看好医-个人中心-首页</title>
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
      <div class="bg-dark collapse show " id="navbarHeader" >
        <nav class="navbar navbar-expand-md absolute-top k-ma-nav-back">
        	<div class="container text-white" >
	            <div class="btn-group" role="group" aria-label="Basic example">
				   <a href="${indexpath}" class="btn btn-link text-white">www.kanhaoyi.com</a>
				</div>
				<div class="btn-group" role="group" aria-label="Basic example">
				  <a id="people" href="${indexpath}/back/index.action" class="btn btn-link text-white">
				  	${nickname}
				  </a>
				</div>
        	</div>
	    </nav>
	  </div>
	  <div class="bg-info text-white">
	  	<div class="container">
	  		<div class="row pt-5">
	  			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12">
		 			<div class="media">
					  <img width="150px;" src="${imgpath}/${picture}" class="figure-img img-fluid rounded-circle mr-3">
					  <div class="media-body">
					    <h5 class="mt-0">${account}</h5>
					   	<span> ${nickname}</span>
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
		      <li class="nav-item active">
		        <a class="nav-link" href="${indexpath}/back/myFootprint.action">我的足迹</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="${indexpath}/back/collect.action">收藏</a>
		      </li>
		    </ul>
		    
		  </div>
		</nav>
		
		<div class="container mt-2 mb-2">
			<a class="btn btn-success" href="${indexpath}/teacher/uploadVideoPage.action" role="button">上传视频</a>
			<a class="btn btn-success" href="${indexpath}/teacher/publishCoursePage.action" role="button">发布课程</a>
			<a class="btn btn-success" href="${indexpath}/teacher/myCoursePage.action" role="button">我的课程</a>
		</div>
		
		<div class="jumbotron">
		  <h1 class="display-4">Hello, ${nickname}</h1>
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
