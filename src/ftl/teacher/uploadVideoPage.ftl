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
		
		<form class="mt-3">
			<div>
				<div class="form-row">
					<div class="col-md-6">
			      		<label for="validationTooltip03">视频分组</label>
				      	<div class="col-12">
				      		<select class="custom-select" required>
						      <option value="">Open this select menu</option>
						      <option value="1">One</option>
						      <option value="2">Two</option>
						      <option value="3">Three</option>
						    </select>
				      	</div>
			      	</div>
			      	<div class="col-md-6">
			      		<label for="validationTooltip03">新建分组</label>
			      		<div class="form-row">
			      			<div class="input-group mb-3">
							  <input type="text" class="form-control" placeholder="分组名称">
							  <div class="input-group-append">
							    <span class="input-group-text">新建</span>
							  </div>
							</div>
			      		</div>
			      	</div>
				</div>
				<div class="form-row">
				    <div class="col-md-8 mb-3">
				      <label for="validationDefault01">视频名称</label>
				      <input type="text" class="form-control" id="validationDefault01" placeholder="视频名称" required>
				    </div>
				    <div class="col-md-4 mb-3">
				      <label for="validationDefaultUsername">选择文件</label>
				      <div class="input-group">
				        <input type="file" class="custom-file-input" id="validatedCustomFile" required>
						<label class="custom-file-label" for="validatedCustomFile">点击选择文件</label>
				      </div>
				    </div>
				  </div>
			</div>
			
			<div>
				<div class="form-row">
				    <div class="col-md-8 mb-3">
				      <label for="validationDefault01">视频名称</label>
				      <input type="text" class="form-control" id="validationDefault01" placeholder="视频名称" required>
				    </div>
				    <div class="col-md-4 mb-3">
				      <label for="validationDefaultUsername">选择文件</label>
				      <div class="input-group">
				        <input type="file" onchange="$('#outline').html(this.value)" class="custom-file-input" id="validatedCustomFile" required>
						<label class="custom-file-label" for="validatedCustomFile" id="outline">点击选择文件</label>
				      </div>
				    </div>
				  </div>
			</div>
		  <button class="btn btn-primary" type="submit">添加</button>
		  <button class="btn btn-primary" type="submit">提交</button>
		</form>
		
</div>
    
  </body>
</html>