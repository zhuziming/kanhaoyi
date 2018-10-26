<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>看好医后台</title>
    <link rel="icon" href="${imgpath}/favicon.ico" type="image/x-icon"/>
    <link href="${csspath}/boots/bootstrap.min.css" rel="stylesheet">
    <link href="${csspath}/dashboard.css" rel="stylesheet" >
    <script src="${jspath}/jquery.min.js"></script>
	<script src="${jspath}/popper.min.js"></script>
	<script src="${jspath}/boots/bootstrap.min.js"></script>
  </head>

  <body>
    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">后台管理系统</a>
      
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="#">退出</a>
        </li>
      </ul>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <#include "leftMenu.ftl">

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
         	<form>
         		
				<nav class=" float-right" aria-label="Page navigation example">
				  <ul class="pagination">
				    <li class="page-item"><a class="page-link" href="#">首页</a></li>
				    <li class="page-item"><a class="page-link" href="#">1</a></li>
				    <li class="page-item active"><a class="page-link" href="#">2</a></li>
				    <li class="page-item"><a class="page-link" href="#">3</a></li>
				    <li class="page-item"><a class="page-link" href="#">末页</a></li>
				  </ul>
				</nav>
				
         	</form>
			<iframe name="newPage" hidden></iframe>
         	<div class="table-responsive">
	            <table class="table table-striped table-sm">
	              <thead>
	                <tr>
	                  <th>课程id</th>
	                  <th>图片</th>
	                  <th>标题</th>
	                  <th>说明</th>
	                  <th>操作</th>
	                </tr>
	              </thead>
	              <tbody>
	              	<#if (indexNewslist?size=0)>
	              		没有数据了
	              	<#else>
	              		<#list indexNewslist as indexNews>
	              			<tr id="${indexNews.id}">
			                  <td>${indexNews.courseID}</td>
			                  <td>${indexNews.img}</td>
			                  <td>${indexNews.title}</td>
			                  <td>${indexNews.context}</td>
			                  <td>
							  	<button type="button" onclick="delIndexNews(${indexNews.id})" class="btn btn-info btn-sm">删除</button> 
							  </td>
			                </tr>
	              		</#list>
	              	</#if>

	                <tr>
		                <form target="newPage" action="${indexpath}/manage/addIndexNews.action" enctype="multipart/form-data" method="post">
		                	<div class="form-row">
			                  <td>
							  	<input type="number" name="courseID" class="form-control" required>
							  </td>
			                  <td>
								<input type="file" name="indexNewsImg" class="form-control-file" required>
							  </td>
			                  <td>
								<input type="text" name="title" class="form-control" required>
							  </td>
			                  <td>
								<input type="text" name="context" class="form-control" required>
							  </td>
			                  <td>
							  	<button type="submit" class="btn btn-info btn-sm">添加轮播</button> 
							  </td>
						  	</div>
						</form>
	                </tr>
	              </tbody>
	            </table>
	          </div>
        </main>
      </div>
    </div>
  </body>
</html>
<script>
	function ajaxFileUpload(msg){
		var data = eval("("+msg+")");
		alert(decodeURIComponent(data.msg));
	}
	
	function delIndexNews(indexNewsID){
		$.ajax({
		   	type: "POST",
		   	url: "${indexpath}/manage/delIndexNews.action",
		   	data: {"indexNewsID":indexNewsID},
		   	dataType: 'json',
		   	success: function(data){
		   		if(data.success==1){
		   			$("#"+indexNewsID).remove()
		   			alert(data.msg)
		   		}else{
		   			alert(data.msg)
		   		}
		   		
		   	}
		});
	}
</script>