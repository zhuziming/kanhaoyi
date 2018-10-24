<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>看好医-个人中心-编缉课程</title>
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
		    	<li class="breadcrumb-item"><a href="${indexpath}/teacher/myCoursePage.action">我的课程</a></li>
		    	<li class="breadcrumb-item active" aria-current="page">编缉课程</li>
		  	</ol>
		</nav>
    	
     	<table class="table table-striped">
			<thead>
				<tr>
			      <th scope="col">课程名称</th>
			      <th scope="col">创建时间</th>
			      <th scope="col">点击量</th>
			      <th scope="col">有几集</th>
			      <th scope="col">操作</th>
			    </tr>
			</thead>
    		<tbody>
				<tr>
			      <td scope="row">${course.courseName}</td>
			      <td>${course.time}</td>
			      <td>${course.clickVolume}</td>
			      <td>${course.quantity}</td>
			      <td>
			      	<a href="javascript:compileCourse()" class="btn btn-info btn-sm">保存</a>
			      </td>
				</tr>  	
			</tbody>
		</table>
		<input class="d-none" id="courseID" value="${course.id}"/>
		<table class="table">
			<thead>
				<tr>
			      <th scope="col">课程介绍</th>
			    </tr>
			</thead>
    		<tbody>
				<tr>
			      <td>
			      	<textarea id="courseIntro" name="intro" class="form-control" style="height:300px;">${(course.intro)!""}</textarea>
			      </td>
				</tr>  	
			</tbody>
		</table>
    </div>
    
    <#include "../footer.ftl">
    
  </body>
</html>
<script>
	function compileCourse(){
		$.ajax({
		   	type: "POST",
		   	url: "${indexpath}/teacher/compileCourse.action",
		   	data: {"id":$("#courseID").val(),"intro":$("#courseIntro").val()},
		   	success: function(msg){
		   		var data = eval("("+msg+")");
		   		if(data.success==1){
		   			alert(data.msg);
		   		}else if(data.success==2){
		   			alert(data.msg);
		   		}else{
		   			alert("网络异常，请稍后在试");
		   		}
		   		
		   	}
		});
	}
</script>