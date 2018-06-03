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
    
    <div class="container mt-3">
    	<nav aria-label="breadcrumb">
		  	<ol class="breadcrumb">
		    	<li class="breadcrumb-item"><a href="${indexpath}/back/index.action">个人中心</a></li>
		    	<li class="breadcrumb-item active" aria-current="page">发布课程</li>
		  	</ol>
		</nav>

		<form class="mt-3">
			<div class="form-row">
				<div class="col-md-6 mb-3">
			      <label for="validationTooltip01">课程标题</label>
			      <input type="text" class="form-control" id="validationTooltip01" placeholder="First name"  required>
			      <div class="valid-tooltip">
			        Looks good!
			      </div>
			    </div>
			    <div class="col-md-6 mb-3">
			    	<div class="form-row">
			    		<div class="col-md-6">
			    			<label for="validationTooltip01">课程图片</label>
						      	<div class="input-group">
							        <input type="file" onchange="$('#outline').html(this.value)" class="custom-file-input" id="validatedCustomFile" required>
									<label class="custom-file-label" for="validatedCustomFile" id="outline">点击选择文件</label>
						      	</div>
			    		</div>
			    		<div class="col-md-6">
			    			<label for="validationTooltip01">课程所属类别</label>
						      	<div class="input-group">
							        <select class="custom-select" required>
								      	<option value="">Open this select menu</option>
								      	<option value="1">One</option>
								      	<option value="2">Two</option>
								      	<option value="3">Three</option>
								    </select>
						      	</div>
			    		</div>
			    	</div>
			      
			    </div>
			</div>
			<div class="form-row">
				<div class="col-md-6 mb-3">
			      <label for="validationTooltip02">第一集课程名称</label> 
			      <button type="button" class="btn btn-outline-primary btn-sm">删除</button>
			      <input type="text" class="form-control" id="validationTooltip02" placeholder="First name"  required>
			      <div class="valid-tooltip">
			        Looks good!
			      </div>
			    </div>
			    <div class="col-md-6 mb-3">
			      	<label for="validationTooltip03">选择视频</label>
			      	<div class="form-row">
			      		<div class="col-6">
				      		<select class="custom-select" required>
						      	<option value="">Open this select menu</option>
						      	<option value="1">One</option>
						      	<option value="2">Two</option>
						      	<option value="3">Three</option>
						    </select>
				      	</div>
				      	<div class="col-6">
				      		<select class="custom-select" required>
						      	<option value="">Open this select menu</option>
						      	<option value="1">One</option>
						      	<option value="2">Two</option>
						      	<option value="3">Three</option>
						    </select>
				      	</div>
			      	</div>
			    </div>
			</div>
			
			<div class="form-row">
				<div class="col-md-6 mb-3">
			      <label for="validationTooltip02">第二集课程名称</label>
			      <button type="button" class="btn btn-outline-primary btn-sm">删除</button>
			      <input type="text" class="form-control" id="validationTooltip02" placeholder="First name"  required>
			      <div class="valid-tooltip">
			        Looks good!
			      </div>
			    </div>
			    <div class="col-md-6 mb-3">
			      	<label for="validationTooltip03">选择视频</label>
			      	<div class="form-row">
			      		<div class="col-6">
				      		<select class="custom-select" required>
						      	<option value="">Open this select menu</option>
						      	<option value="1">One</option>
						      	<option value="2">Two</option>
						      	<option value="3">Three</option>
						    </select>
				      	</div>
				      	<div class="col-6">
				      		<select class="custom-select" required>
						      	<option value="">Open this select menu</option>
						      	<option value="1">One</option>
						      	<option value="2">Two</option>
						      	<option value="3">Three</option>
						    </select>
				      	</div>
			      	</div>
			    </div>
			</div>
			<button class="btn btn-primary" type="submit">添加</button>
			<button class="btn btn-primary" type="submit">Submit form</button>
		</form>
		
	</div>
    
  </body>
</html>