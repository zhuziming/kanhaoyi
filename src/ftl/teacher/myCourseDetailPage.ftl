<!DOCTYPE HTML>
<html>
  	<head>
  	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>课程详情</title>
    <link rel="icon" href="${imgpath}/favicon.ico" type="image/x-icon"/>
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
			    	<li class="breadcrumb-item active" aria-current="page">课程详情</li>
			  	</ol>
			</nav>
		
			<table class="table table-striped">
				<thead>
					<tr>
				      <th scope="col">课程名称</th>
				      <th scope="col">子课程名称</th>
				      <th scope="col">操作</th>
				    </tr>
				</thead>
	    		<tbody>
					<tr>
				      <td scope="row">${course.courseName}</td>
				      <td>${courseDetail.courseDetailName}</td>
				      <td>
				      	<a href="javascript:saveCourseDetail()" class="btn btn-info btn-sm">保存</a>
				      </td>
					</tr>  	
				</tbody>
			</table>
			<input id="detailID" type="hidden" value="${courseDetail.id}"/>
			<div class="alert alert-primary" role="alert">
				编缉课程头标签meta
			</div>
			<textarea id="detailMeta" class="form-control mb-3" style="height:100px;">
				<#if (courseDetail.meta)??>
${(courseDetail.meta)!""}
				<#else>
<meta name="title" content="">
<meta name="keywords" content="">
<meta name="description" content="">
				</#if>
			</textarea>
			
			<div class="alert alert-danger" role="alert">
				编缉课程title
			</div>
			<textarea id="detailTitle" class="form-control mb-3" style="height:40px;">
				<#if (courseDetail.title)??>
${(courseDetail.title)!""}
				<#else>
<title></title>
				</#if>
			</textarea>
			
			<div class="alert alert-success" role="alert">
				编缉课程文本html格式
			</div>
			
			<textarea id="detailText" class="form-control mb-3" style="height:500px;">
				<#if (courseDetail.intro)??>
${(courseDetail.intro)!""}
				<#else>
<h1>标题</h1>
<br>
<p class="line-180"></p>
<div class="row justify-content-center">
	<div class="col-sm-12 col-lg-8">
		<img alt="描述"  class="img-fluid" src="http://www.kanhaoyi.com/image/courseDetailImg/67/125/20201026_1.jpg" />
	</div>
</div>
				</#if>
			</textarea>
			
		</div>
	    
  	</body>
</html>
<script>
	// 保存课程详情
	function saveCourseDetail(){
		$.ajax({
		   	type: "POST",
		   	url: "${indexpath}/teacher/modifyCourseDetail.action",
		   	data: {"detailID":$("#detailID").val(),"detailMeta":$("#detailMeta").val(),
		   		"detailTitle":$("#detailTitle").val(),"detailText":$("#detailText").val()},
		   	dataType: 'json',
		   	success: function(data){
		   		alert(decodeURIComponent(data.msg));
		   	}
		});
	}
</script>



