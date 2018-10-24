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
		    	<li class="breadcrumb-item active" aria-current="page">我的课程</li>
		  	</ol>
		</nav>
    	
     	<#if (courseListMap?size=0)>
     		您还没有课程，快去发布吧
		<#else>
			<table class="table table-striped">
			  <thead>
			    <tr>
			      <th scope="col">课程名称</th>
			      <th scope="col">所属科室</th>
			      <th scope="col">创建时间</th>
			      <th scope="col">点击量</th>
			      <th scope="col">有几集</th>
			      <th scope="col">操作</th>
			    </tr>
			  </thead>
			  <tbody id="contentList">
			  	<#list courseListMap as courseMap>
			  		<tr>
				      <td scope="row">${courseMap.course_name}</td>
				      <td>${courseMap.name}</td>
				      <td>${courseMap.time?string("yyyy-MM-dd HH:mm:ss")}</td>
				      <td>${courseMap.click_volume}</td>
				      <td>${courseMap.quantity}</td>
				      <td>
				      	<a href="${indexpath}/teacher/compileCoursePage.action?courseID=${courseMap.id}" class="btn btn-info btn-sm">编辑</a>
				      </td>
				    </tr>
				</#list>
			  </tbody>
			</table>
			
			<div id="padingHTML">
				${padingHTML}
			</div>
		</#if>
    	
    </div>
    
    <#include "../footer.ftl">
    
  </body>
</html>
<script>
	function ajaxPaging(pageIndex){
		console.log(pageIndex);
		
		$.ajax({
		   	type: "POST",
		   	url: "${indexpath}/teacher/ajaxGetCourseList.action",
		   	data: {"pageIndex":pageIndex},
		   	success: function(msg){
		   		var data = eval("("+msg+")");
		   		if(data.success==1){
		   			var result = eval("("+data.msg+")");
		   			$("#contentList").html(result.dataList);
		   			$("#padingHTML").html(result.padingHTML);
		   		}else if(data.success==2){
		   			alert(data.msg);
		   		}else{
		   			alert("网络异常，请稍后在试");
		   		}
		   	}
		});
		
	}

</script>