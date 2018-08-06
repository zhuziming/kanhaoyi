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
    	
     	<#if (videoList?size=0)>
     		没有数据了
     	<#else>
			<table class="table table-striped">
				<thead>
				    <tr>
				      <th scope="col">标题</th>
				      <th scope="col">创建时间</th>
				      <th scope="col">所属分组</th>
				    </tr>
			  	</thead>
			  	<tbody id="contentList">
					<#list videoList as video>
						<tr>
							<td>${video.name}</td>
					    	<td>${video.createTime}</td>
					    	<td>${video.groupName}</td>
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
		   	url: "${indexpath}/teacher/ajaxGetVideoList.action",
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