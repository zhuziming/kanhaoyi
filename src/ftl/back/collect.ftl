<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>看好医-个人中心-收藏</title>
    <link rel="icon" href="${imgpath}/favicon.ico" type="image/x-icon"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="${csspath}/boots/bootstrap.min.css">
    <link rel="stylesheet" href="${csspath}/boots/blog.css">
    <link rel="stylesheet" href="${csspath}/kanhaoyi-index.css">    
    <script src="${jspath}/jquery.min.js"></script>
	<script src="${jspath}/boots/bootstrap.min.js"></script>
  </head>
  
  <body>
  	<header>
      <#include "../head.ftl">
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
		        <a class="nav-link" href="${indexpath}/back/index.action">个人中心</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="${indexpath}/back/myFootprint.action">我的足迹</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link btn btn-outline-primary" href="${indexpath}/back/collect.action">收藏</a>
		      </li>
		    </ul>
		    
		  </div>
		</nav>
		
		<div class="row mt-5">
		    <div class="col-xl-2 col-lg-3 col-md-3 col-sm-4 col-6">
		    	<a href="http://www.baidu.com">
		    		<img width="100%;" src="${indexpath}/image/190-100.png"></img>
		      		<p style="word-wrap:keep-all;white-space:nowrap;overflow: hidden;">col-xl-2 col-xl-2 </p>
		    	</a>
		    </div>
		    <div class="col-xl-2 col-lg-3 col-md-3 col-sm-4 col-6">
		    	<a href="http://www.baidu.com">
		    		<img width="100%;" src="${indexpath}/image/190-100.png"></img>
		      		<p style="word-wrap:keep-all;white-space:nowrap;overflow: hidden;">col-xl-2 col-xl-2 </p>
		    	</a>
		    </div>
		    <div class="col-xl-2 col-lg-3 col-md-3 col-sm-4 col-6">
		    	<a href="http://www.baidu.com">
		    		<img width="100%;" src="${indexpath}/image/190-100.png"></img>
		      		<p style="word-wrap:keep-all;white-space:nowrap;overflow: hidden;">col-xl-2 col-xl-2 </p>
		    	</a>
		    </div>
		    <div class="col-xl-2 col-lg-3 col-md-3 col-sm-4 col-6">
		    	<a href="http://www.baidu.com">
		    		<img width="100%;" src="${indexpath}/image/190-100.png"></img>
		      		<p style="word-wrap:keep-all;white-space:nowrap;overflow: hidden;">col-xl-2 col-xl-2 </p>
		    	</a>
		    </div>
		    <div class="col-xl-2 col-lg-3 col-md-3 col-sm-4 col-6">
		    	<a href="http://www.baidu.com">
		    		<img width="100%;" src="${indexpath}/image/190-100.png"></img>
		      		<p style="word-wrap:keep-all;white-space:nowrap;overflow: hidden;">col-xl-2 col-xl-2 </p>
		    	</a>
		    </div>
		    <div class="col-xl-2 col-lg-3 col-md-3 col-sm-4 col-6">
		    	<a href="http://www.baidu.com">
		    		<img width="100%;" src="${indexpath}/image/190-100.png"></img>
		      		<p style="word-wrap:keep-all;white-space:nowrap;overflow: hidden;">col-xl-2 col-xl-2 </p>
		    	</a>
		    </div>
		 </div>

	</div>
	
	<#include "../footer.ftl">
	
  </body>
</html>