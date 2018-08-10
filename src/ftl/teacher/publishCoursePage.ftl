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
		    	<li class="breadcrumb-item active" aria-current="page">发布课程</li>
		  	</ol>
		</nav>


		<iframe name="newPage" hidden></iframe>
		<form onsubmit="return beginForm()" target="newPage" action="${indexpath}/teacher/publishCourse.action" class="mt-3" enctype="multipart/form-data" method="post">
			<div class="form-row">
				<div class="col-md-6 mb-3">
			      <label for="validationTooltip01">课程标题</label>
			      <input type="text" name="courseTitle" class="form-control" id="validationTooltip01" placeholder="courseTitle"  required>
			      <div class="valid-tooltip">
			        Looks good!
			      </div>
			    </div>
			    <div class="col-md-6 mb-3">
			    	<div class="form-row">
			    		<div class="col-md-6">
			    			<label for="validationTooltip01">课程图片 <code>只支持jpg png格式 190x100</code></label>
						      	<div class="input-group">
							        <input type="file" name="courseImg" onchange="$('#outline').html(this.value)" class="custom-file-input" id="validatedCustomFile" required>
									<label class="custom-file-label" for="validatedCustomFile" id="outline">点击选择文件</label>
						      	</div>
			    		</div>
			    		<div class="col-md-6">
			    			<label for="validationTooltip01">课程所属类别</label>
						      	<div class="input-group">
							        <select name="courseTypeID" class="custom-select" required>
								      	<#list courseTypeList as courseType>
								    		<option value="${courseType.id}">${(courseType.name)!''}</option>
								    	</#list>
								    </select>
						      	</div>
			    		</div>
			    	</div>
			      
			    </div>
			</div>
			
			<div class="form-row" id="1">
				<div class="col-md-6 mb-3">
			      <label for="validationTooltip02">第一集课程名称</label>
			      <input type="text" name="courseName1" class="form-control" id="validationTooltip01" placeholder="course name"  required>
			      <div class="valid-tooltip">
			        Looks good!
			      </div>
			    </div>
			    <div class="col-md-6 mb-3">
			    	<div class="form-row">
			    		<div class="col-md-6">
			    			<label for="validationTooltip03">选择视频</label>
						      	<select id="1_video" name="videoName1" class="custom-select" required>
							      	
							    </select>
			    		</div>
			    		<div class="col-md-6">
			    			<label for="validationTooltip01">课程所属类别</label>
						      	<div class="input-group">
							        <select id="1_videoGroup" class="custom-select" onchange="getVideo(1)" required>
							        	<option></option>
								      	<#list videoGroupList as videoGroup>
								    		<option value="${videoGroup.id}">${(videoGroup.groupName)!''}</option>
								    	</#list>
								    </select>
						      	</div>
			    		</div>
			    	</div>
			    </div>
			</div>
			
			

			<button id="addGroupButton" class="btn btn-primary" type="button" onclick="addGroup()">添加一集</button>
			<button class="btn btn-primary" id="submit" type="submit">提交，发布课程</button>
			<img class="k-hid" id="uploadImg" src="${imgpath}/upload.gif" />
		</form>
		
	</div>
    
  </body>
</html>
<script>
	// 删除表单元素
	function removeEle(idNum){
		$("#"+idNum).remove();
	}
	
	var groupID=10;
	// 添加一个视频组件
	function addGroup(){
		groupID++;
		var eleHTML = '';
		eleHTML+='<div class="form-row" id="'+groupID+'">';
		eleHTML+='		<div class="col-md-6 mb-3">';
		eleHTML+='	      <label for="validationTooltip02">课程名称</label>';
		eleHTML+='	      <button type="button" class="btn btn-outline-primary btn-sm" onclick="removeEle('+groupID+')">删除</button>';
		eleHTML+='	      <input type="text" name="courseName'+groupID+'" class="form-control" id="validationTooltip01" placeholder="course name"  required>';
		eleHTML+='	      <div class="valid-tooltip">';
		eleHTML+='	        Looks good!';
		eleHTML+='	      </div>';
		eleHTML+='	    </div>';
		eleHTML+='	    <div class="col-md-6 mb-3">';
		eleHTML+='	    	<div class="form-row">';
		eleHTML+='	    		<div class="col-md-6">';
		eleHTML+='	    			<label for="validationTooltip03">选择视频</label>';
		eleHTML+='				      	<select name="videoName'+groupID+'" id="'+groupID+'_video" class="custom-select" required>';
		eleHTML+='					      	';
		eleHTML+='					    </select>';
		eleHTML+='	    		</div>';
		eleHTML+='	    		<div class="col-md-6">';
		eleHTML+='	    			<label for="validationTooltip01">课程所属类别</label>';
		eleHTML+='				      	<div class="input-group">';
		eleHTML+='					        <select  id="'+groupID+'_videoGroup" class="custom-select" onchange="getVideo('+groupID+')" required>';
		eleHTML+='						      	<option></option>';
												<#list videoGroupList as videoGroup>
		eleHTML+='						    		<option value="${videoGroup.id}">${(videoGroup.groupName)!''}</option>';
								    			</#list>
		eleHTML+='						    </select>';
		eleHTML+='				      	</div>';
		eleHTML+='	    		</div>';
		eleHTML+='	    	</div>';
		eleHTML+='	    </div>';
		eleHTML+='	</div>';
		$("#addGroupButton").before(eleHTML);
		
	}
	// ，加载用的当前视频组的视频
	function getVideo(eleID){
		var videoGroupID = $("#"+eleID+"_videoGroup").val();
	
		$.ajax({
			type: "POST",
			url: "${indexpath}/teacher/getVideo.action",
			data:{"videoGroupID":videoGroupID},
			success: function(data){
				var info = eval("("+data+")");
				if(info.success==1){
					var eleHTML = "";
					var msg = eval("("+info.msg+")");
					for(var i=0;i<msg.length;i++){
						eleHTML += "<option value='"+msg[i].id+"'>"+msg[i].name+"</option>";
					}
					$("#"+eleID+"_video").html(eleHTML);
				}else if(info.success==2){
					$("#"+eleID+"_video").html("");
				}else if(info.success==3){
					alert(info.msg);
				}
			}
		});
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
