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
    
    	<div id="formContainer" class="container mt-3">
    
	    	<nav aria-label="breadcrumb">
			  	<ol class="breadcrumb">
			    	<li class="breadcrumb-item"><a href="${indexpath}/back/index.action">个人中心</a></li>
			    	<li class="breadcrumb-item active" aria-current="page">上传视频</li>
			  	</ol>
			</nav>
			<iframe name="newPage" hidden></iframe>
			<form target="newPage" action="${indexpath}/teacher/videoFileUpload.action" class="mt-3" enctype="multipart/form-data" method="post">
				<input name="formIndex" value="0" hidden/>
				<div>
					<div class="form-row">
						<div class="col-md-6">
				      		<label for="validationTooltip03">视频分组</label>
					      	<div class="col-12">
					      		<select id="groupName" class="custom-select" required>
							    	<#list videoGroupList as videoGroup>
							    		<option value="${videoGroup.id}">${(videoGroup.groupName)!''}</option>
							    	</#list>
							    </select>
					      	</div>
				      	</div>
				      	<div class="col-md-6">
				      		<label for="validationTooltip03">新建分组</label>
				      		<div class="form-row">
				      			<div class="input-group mb-3">
								  <input type="text" id="createGroupName" class="form-control" placeholder="分组名称">
								  <div class="input-group-append">
								    <label onclick="createVideoGroup()" class="input-group-text">新建</label>
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
					        <input type="file" name="file" onchange="$(this).next().html(this.value)" class="custom-file-input" id="validatedCustomFile" required>
							<label class="custom-file-label" for="validatedCustomFile">点击选择文件</label>
					      </div>
					    </div>
					  </div>
				</div>
			</form>
		
		</div>
		<div class="container mt-3">
			<a class="btn btn-primary" href="javascript:createVideo()" role="button">添加一个新的视频</a>
			<button class="btn btn-primary" onclick="fileSubmit()" type="submit">上传</button>
		</div>
    	
	</body>
</html>
<script>
	// 新建视频组
	function createVideoGroup(){
		var createGroupName = $("#createGroupName").val();
		$.ajax({
			type: "POST",
			url: "${indexpath}/teacher/createVideoGroup.action",
			data:{"createGroupName":createGroupName},
			success: function(data){
				var info = eval("("+data+")");
				if(info.success==1){
					var msg = eval("("+info.msg+")")
					var opt = "<option selected='selected' value='"+msg.id+"'>"+msg.groupName+"</option>";
					$("#groupName").prepend(opt);
					console.log(msg);
				}else if(info.success==2){
					alert(info.msg);
				}
			}
		});
	}
	
	// 上传控件的id
	var videoNum = 1;
	// 创建视频上传控件
	function createVideo(){
		videoNum++;
		var elem = '';
			elem+='<form id='+videoNum+' class="mt-3">';
			elem+='<input name="formIndex" value="'+videoNum+'" hidden/>';
			elem+='<div>';
			elem+='	<div class="form-row">';
			elem+='	    <div class="col-md-8 mb-3">';
			elem+='	      <label for="validationDefault01">视频名称 &nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:removeEle('+videoNum+')" role="button">删除</a></label>';
			elem+='	      <input type="text" class="form-control" id="validationDefault01" placeholder="视频名称" required>';
			elem+='	    </div>';
			elem+='	    <div class="col-md-4 mb-3">';
			elem+='	      <label for="validationDefaultUsername">选择文件</label>';
			elem+='	      <div class="input-group">';
			elem+='	        <input type="file" name="file" onchange="$(this).next().html(this.value)" class="custom-file-input" id="validatedCustomFile" required>';
			elem+='			<label class="custom-file-label" for="validatedCustomFile">点击选择文件</label>';
			elem+='	      </div>';
			elem+='	    </div>';
			elem+='	  </div>';
			elem+='</div>';
			elem+='</form>';
		$("#formContainer").append(elem);	
			
	}
	// 删除表单元素
	function removeEle(idNum){
		$("#"+idNum).remove();
	}
	
	// 文件上传
	function fileSubmit(){
		
		$("form").submit();
	}
	
	function ajaxFileUpload(msg){
		console.log(msg);
	}

</script>

