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
    
    	<div id="formContainer" class="container mt-3">
    
	    	<nav aria-label="breadcrumb">
			  	<ol class="breadcrumb">
			    	<li class="breadcrumb-item"><a href="${indexpath}/back/index.action">个人中心</a></li>
			    	<li class="breadcrumb-item active" aria-current="page">上传视频</li>
			  	</ol>
			</nav>
			
			<div class="alert alert-primary" role="alert">
				上传视频一次只能上传5个，如果不足5个请点上传框上部的“删除”按钮把多余的框删除
			</div>
			
			<iframe name="newPage" hidden></iframe>
			<form onsubmit="return beginForm()" target="newPage" action="${indexpath}/teacher/videoFileUpload.action" class="mt-3" enctype="multipart/form-data" method="post">
				<input name="formIndex" value="0" hidden/>
				<div>
					<div class="form-row">
						<div class="col-md-6">
				      		<label for="validationTooltip03">视频分组</label>
					      	<div class="col-12">
					      		<select id="videoGroupID" name="videoGroupID" class="custom-select" required>
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
					      <input type="text" name="videoName0" class="form-control" id="validationDefault01" placeholder="视频名称" required>
					    </div>
					    <div class="col-md-4 mb-3">
					      <label for="validationDefaultUsername">选择文件 <code>请上传MP4格式</code></label>
					      <div class="input-group">
					        <input type="file" name="videoFile0" onchange="$(this).next().html(this.value)" class="custom-file-input" id="validatedCustomFile" required>
							<label class="custom-file-label" for="validatedCustomFile">点击选择文件</label>
					      </div>
					    </div>
					</div>
					
					<div id="1" class="form-row">
					    <div class="col-md-8 mb-3">
					      <label for="validationDefault01">视频名称</label> <a href="javascript:removeEle(1)" role="button">删除</a></label>
					      <input type="text" name="videoName1" class="form-control" id="validationDefault01" placeholder="视频名称" required>
					    </div>
					    <div class="col-md-4 mb-3">
					      <label for="validationDefaultUsername">选择文件 <code>请上传MP4格式</code></label>
					      <div class="input-group">
					        <input type="file" name="videoFile1" onchange="$(this).next().html(this.value)" class="custom-file-input" id="validatedCustomFile" required>
							<label class="custom-file-label" for="validatedCustomFile">点击选择文件</label>
					      </div>
					    </div>
					</div>
					
					<div id="2" class="form-row">
					    <div class="col-md-8 mb-3">
					      <label for="validationDefault01">视频名称</label> <a href="javascript:removeEle(2)" role="button">删除</a></label>
					      <input type="text" name="videoName2" class="form-control" id="validationDefault01" placeholder="视频名称" required>
					    </div>
					    <div class="col-md-4 mb-3">
					      <label for="validationDefaultUsername">选择文件 <code>请上传MP4格式</code></label>
					      <div class="input-group">
					        <input type="file" name="videoFile2" onchange="$(this).next().html(this.value)" class="custom-file-input" id="validatedCustomFile" required>
							<label class="custom-file-label" for="validatedCustomFile">点击选择文件</label>
					      </div>
					    </div>
					</div>
					
					<div id="3" class="form-row">
					    <div class="col-md-8 mb-3">
					      <label for="validationDefault01">视频名称</label> <a href="javascript:removeEle(3)" role="button">删除</a></label>
					      <input type="text" name="videoName3" class="form-control" id="validationDefault01" placeholder="视频名称" required>
					    </div>
					    <div class="col-md-4 mb-3">
					      <label for="validationDefaultUsername">选择文件 <code>请上传MP4格式</code></label>
					      <div class="input-group">
					        <input type="file" name="videoFile3" onchange="$(this).next().html(this.value)" class="custom-file-input" id="validatedCustomFile" required>
							<label class="custom-file-label" for="validatedCustomFile">点击选择文件</label>
					      </div>
					    </div>
					</div>
					
					<div id="4" class="form-row">
					    <div class="col-md-8 mb-3">
					      <label for="validationDefault01">视频名称</label> <a href="javascript:removeEle(4)" role="button">删除</a></label>
					      <input type="text" name="videoName4" class="form-control" id="validationDefault01" placeholder="视频名称" required>
					    </div>
					    <div class="col-md-4 mb-3">
					      <label for="validationDefaultUsername">选择文件 <code>请上传MP4格式</code></label>
					      <div class="input-group">
					        <input type="file" name="videoFile4" onchange="$(this).next().html(this.value)" class="custom-file-input" id="validatedCustomFile" required>
							<label class="custom-file-label" for="validatedCustomFile">点击选择文件</label>
					      </div>
					    </div>
					</div>
					
				</div>
				
				<div class="container mt-3 mb-3">
					<button class="btn btn-primary" id="submit" type="submit">上传</button>
					<img class="k-hid" id="uploadImg" src="${imgpath}/upload.gif" />
				</div>
			</form>
		</div>
		
    	<#include "../footer.ftl">
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
					alert("分组添加成功，请刷新页面查看");
					window.location.reload();
				}else if(info.success==2){
					alert(info.msg);
				}
			}
		});
	}
	

	// 删除表单元素
	function removeEle(idNum){
		$("#"+idNum).remove();
	}
	

	
	function ajaxFileUpload(msg){
		$("#uploadImg").hide();
		var data = eval("("+msg+")");
		if(data.success==1){
			$("#submit").html(decodeURIComponent(data.msg));
		}else if(data.success==2){
			$("#submit").html(decodeURIComponent(data.msg));
		}else if(data.success==3){
			$("#submit").html(decodeURIComponent(data.msg));
		}else{
			$("#submit").html("未知的错误，请稍后在试");
		}
	}
	
	function beginForm(){
		$("#uploadImg").show();
		$("#submit").html("上传中...");
		$("#submit").attr("disabled",true); // 让提交按钮失效
		return true;
	}

</script>

