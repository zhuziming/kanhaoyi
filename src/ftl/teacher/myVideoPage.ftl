<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>看好医-个人中心-收藏</title>
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
      <div class="bg-dark collapse show " id="navbarHeader" >
        <nav class="navbar navbar-expand-md absolute-top k-ma-nav-back">
        	<div class="container text-white" >
	            <div class="btn-group" role="group" aria-label="Basic example">
				   <a href="${indexpath}" class="btn btn-link text-white">www.kanhaoyi.com</a>
				</div>
				<div class="btn-group" role="group" aria-label="Basic example">
				  <a id="people" href="${indexpath}/back/index.action" class="btn btn-link text-white">
				  	${(user.nickname)!''}
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
					  <img width="150px;" src="${imgpath}/${(user.picture)!''}" class="figure-img img-fluid rounded-circle mr-3">
					  <div class="media-body">
					    <h5 class="mt-0">${(user.account)!''}</h5>
					   	<span> ${(user.nickname)!''}</span>
					  </div>
					</div>
				</div>
				<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12">
		 			
		 		</div>
	  		</div>
	  		
      	</div>
	  </div> 
	    
    </header>
    
    <div class="container mt-3">
    
    	<nav aria-label="breadcrumb">
		  	<ol class="breadcrumb">
		    	<li class="breadcrumb-item"><a href="${indexpath}/back/index.action">个人中心</a></li>
		    	<li class="breadcrumb-item active" aria-current="page">我的视频</li>
		  	</ol>
		</nav>
    	
     
    	<table class="table table-striped">
		  <thead>
		    <tr>
		      <th scope="col">标题</th>
		      <th scope="col">创建时间</th>
		    </tr>
		  </thead>
		  <tbody>
			<#list videoList as video>
				<tr>
					<td>${video.name}</td>
				    <td>${video.createTime}</td>
				</tr>
		  	</#list>
		   
		  </tbody>
		  
		</table>
		
		<nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-end">
		    <li class="page-item"><a class="page-link" href="#">第一页</a></li>
		    <li class="page-item"><a class="page-link" href="#">1</a></li>
		    <li class="page-item active"><a class="page-link" href="#">2</a></li>
		    <li class="page-item"><a class="page-link" href="#">3</a></li>
		    <li class="page-item"><a class="page-link" href="#">4</a></li>
		    <li class="page-item"><a class="page-link" href="#">5</a></li>
		    <li class="page-item"><a class="page-link" href="#">最后一页</a></li>
		    <li class="page-item"><a class="page-link" href="#">2/10，55</a></li>
		  </ul>
		</nav>
    </div>
    <#include "../footer.ftl">
  </body>
</html>
    