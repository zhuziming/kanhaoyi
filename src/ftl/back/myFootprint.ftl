<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>看好医-个人中心-我的足迹</title>
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
		        <a class="nav-link btn btn-outline-primary" href="${indexpath}/back/myFootprint.action">我的足迹</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="${indexpath}/back/collect.action">收藏</a>
		      </li>
		    </ul>
		    
		  </div>
		</nav>
		
		<nav class="navbar navbar-light justify-content-between mt-2">
		  <a class="navbar-brand"></a>
		  <form class="form-inline">
		    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">编辑</button>
		    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">全部删除</button>
		    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">删除</button>
		  </form>
		</nav>
		
		<table class="table table-striped table-hover">
		  <tbody>
		    <tr>
		      <td>2018-04-12</td>
		      <td>心率不齐的发生因素</td>
		      <td>内科</td>
		      <td><input type="checkbox" aria-label="Checkbox for following text input"></td>
		    </tr>
		    <tr>
		      <td>2018-03-25</td>
		      <td>冬天的保养方法</td>
		      <td>养生</td>
		      <td><input class="d-none" type="checkbox" aria-label="Checkbox for following text input"></td>
		    </tr>
		    <tr>
		      <td>2018-02-11</td>
		      <td>夏日如何防晒</td>
		      <td>美容</td>
		      <td><input class="d-none" type="checkbox" aria-label="Checkbox for following text input"></td>
		    </tr>
		  </tbody>
		</table>
		
		<button type="button" class="btn btn-info btn-lg btn-block">加载更多...</button>
		
	</div>
	
	<#include "../footer.ftl">
	
  </body>
</html>
<script src="${indexpath}/js/kanhaoyi.js"></script>