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
	<script src="${jspath}/laydate/laydate.js"></script>
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
			      <th scope="col">科室</th>
			      <th scope="col">创建时间</th>
			      <th scope="col">点击量</th>
			      <th scope="col">有几集</th>
			      <th scope="col">操作</th>
			    </tr>
			</thead>
    		<tbody>
				<tr>
			      <td scope="row">${course.courseName}</td>
			      <td>
			      	<select id="courseTypeID" name="courseTypeID">
			      		<#list courseTypeList as courseType>
			      			<#if course.courseTypeID==courseType.id>
			      				<option selected="selected" value="${courseType.id}">${courseType.name}</option>
			      			<#else>
			      				<option value="${courseType.id}">${courseType.name}</option>
							</#if>
						</#list>
			      	</select>
			      </td>
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
			      <th scope="col">子课程</th>
			    </tr>
			</thead>
    		<tbody>
    			<#list courseDetailList as courseDetail>
					<tr>
			      		<td>
			      			<a target="_Blank" href="${indexpath}/teacher/myCourseDetailPage.action?courseDetailID=${courseDetail.id}">
								${courseDetail.courseDetailName}
							</a>
			      		</td>
					</tr>
				</#list>  	
			</tbody>
		</table>
		
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
		<iframe name="newPage" hidden></iframe>
		<table class="table">
			<thead>
				<tr>
			      <th scope="col">第一个链接 200 x 200</th>
			      <th scope="col">第二个链接 200 x 200</th>
			    </tr>
			</thead>
    		<tbody>
				<tr>
			      <td>
			      	<form action="${indexpath}/teacher/addLink.action" enctype="multipart/form-data" method="post" target="newPage">
				      	<div class="form-group row">
						    <label class="col-sm-2 col-form-label">图片：</label>
						    <div class="col-sm-10">
						      <input type="file" name="pictureFile" />
						      <button type="submit" class="btn btn-info btn-sm">保存</button>
						      <input hidden type="number" name="picture" value="1" />
						      <input hidden class="d-none" name="courseID" value="${course.id}"/>
						      <a href="javascript:delLink(${(courseLink1.id)!""})" class="btn btn-warning btn-sm">删除</a>
						    </div>
						</div>
				      	<div class="form-group row">
						    <label class="col-sm-2 col-form-label">描述：</label>
						    <div class="col-sm-10">
						      <input type="text" name="intro" value="${(courseLink1.intro)!""}" class="form-control" placeholder="链接描述" required>
						    </div>
						</div>
						<div class="form-group row">
						    <label class="col-sm-2 col-form-label">地址：</label>
						    <div class="col-sm-10">
						      <input type="text" name="webLocation" value="${(courseLink1.webLocation)!""}" class="form-control" placeholder="链接地址" required>
						    </div>
						</div>
						<div class="form-group row">
						    <label class="col-sm-3 col-form-label">过期时间：</label>
						    <div class="col-sm-9">
						      <input type="text" name="endTime" value="${(courseLink1.endTime)!''}" readonly class="form-control" placeholder="请选择日期" id="endTime1" required>
						    </div>
						</div>
					</form>
			      </td>
			      <td>
			      	<form action="${indexpath}/teacher/addLink.action" enctype="multipart/form-data" method="post" target="newPage">
				      	<div class="form-group row">
						    <label class="col-sm-2 col-form-label">图片：</label>
						    <div class="col-sm-10">
						      <input type="file" name="pictureFile" />
						      <button type="submit" class="btn btn-info btn-sm">保存</button>
						      <input hidden type="number" name="picture" value="2" />
						      <input hidden class="d-none" name="courseID" value="${course.id}"/>
						      <a href="javascript:delLink(${(courseLink2.id)!""})" class="btn btn-warning btn-sm">删除</a>
						    </div>
						</div>
				      	<div class="form-group row">
						    <label class="col-sm-2 col-form-label">描述：</label>
						    <div class="col-sm-10">
						      <input type="text" name="intro" value="${(courseLink2.intro)!""}" class="form-control" placeholder="链接描述" required>
						    </div>
						</div>
						<div class="form-group row">
						    <label class="col-sm-2 col-form-label">地址：</label>
						    <div class="col-sm-10">
						      <input type="text" name="webLocation" value="${(courseLink2.webLocation)!""}" class="form-control" placeholder="链接地址" required>
						    </div>
						</div>
						<div class="form-group row">
						    <label class="col-sm-3 col-form-label">过期时间：</label>
						    <div class="col-sm-9">
						      <input type="text" name="endTime" value="${(courseLink2.endTime)!''}" readonly class="form-control" placeholder="请选择日期" id="endTime2" required>
						    </div>
						</div>
					</form>
			      </td>
				</tr>  	
			</tbody>
		</table>
		
		<table class="table">
			<thead>
				<tr>
			      <th scope="col">第三个链接 200 x 200</th>
			      <th scope="col">第四个链接 200 x 200</th>
			    </tr>
			</thead>
    		<tbody>
				<tr>
			      <td>
			      	<form action="${indexpath}/teacher/addLink.action" enctype="multipart/form-data" method="post" target="newPage">
				      	<div class="form-group row">
						    <label class="col-sm-2 col-form-label">图片：</label>
						    <div class="col-sm-10">
						      <input type="file" name="pictureFile" />
						      <button type="submit" class="btn btn-info btn-sm">保存</button>
						      <input hidden type="number" name="picture" value="3" />
						      <input hidden class="d-none" name="courseID" value="${course.id}"/>
						      <a href="javascript:delLink(${(courseLink3.id)!""})" class="btn btn-warning btn-sm">删除</a>
						    </div>
						</div>
				      	<div class="form-group row">
						    <label class="col-sm-2 col-form-label">描述：</label>
						    <div class="col-sm-10">
						      <input type="text" name="intro" value="${(courseLink3.intro)!""}" class="form-control" placeholder="链接描述" required>
						    </div>
						</div>
						<div class="form-group row">
						    <label class="col-sm-2 col-form-label">地址：</label>
						    <div class="col-sm-10">
						      <input type="text" name="webLocation" value="${(courseLink3.webLocation)!""}" class="form-control" placeholder="链接地址" required>
						    </div>
						</div>
						<div class="form-group row">
						    <label class="col-sm-3 col-form-label">过期时间：</label>
						    <div class="col-sm-9">
						      <input type="text" name="endTime" value="${(courseLink3.endTime)!''}" readonly class="form-control" placeholder="请选择日期" id="endTime3" required>
						    </div>
						</div>
					</form>
			      </td>
			      <td>
			      	<form action="${indexpath}/teacher/addLink.action" enctype="multipart/form-data" method="post" target="newPage">
				      	<div class="form-group row">
						    <label class="col-sm-2 col-form-label">图片：</label>
						    <div class="col-sm-10">
						      <input type="file" name="pictureFile" />
						      <button type="submit" class="btn btn-info btn-sm">保存</button>
						      <input hidden type="number" name="picture" value="4" />
						      <input hidden class="d-none" name="courseID" value="${course.id}"/>
						      <a href="javascript:delLink(${(courseLink4.id)!""})" class="btn btn-warning btn-sm">删除</a>
						    </div>
						</div>
				      	<div class="form-group row">
						    <label class="col-sm-2 col-form-label">描述：</label>
						    <div class="col-sm-10">
						      <input type="text" name="intro" value="${(courseLink4.intro)!""}" class="form-control" placeholder="链接描述" required>
						    </div>
						</div>
						<div class="form-group row">
						    <label class="col-sm-2 col-form-label">地址：</label>
						    <div class="col-sm-10">
						      <input type="text" name="webLocation" value="${(courseLink4.webLocation)!""}" class="form-control" placeholder="链接地址" required>
						    </div>
						</div>
						<div class="form-group row">
						    <label class="col-sm-3 col-form-label">过期时间：</label>
						    <div class="col-sm-9">
						      <input type="text" name="endTime" value="${(courseLink4.endTime)!''}" readonly class="form-control" placeholder="请选择日期" id="endTime4" required>
						    </div>
						</div>
					</form>
			      </td>
				</tr>  	
			</tbody>
		</table>
		
		<table class="table">
			<thead>
				<tr>
			      <th scope="col">补充课程，每次只能补充5集</th>
			    </tr>
			</thead>
			<tbody>
				<tr>
			      <td>
			      	<div class="form-row" id="1">
						<div class="col-md-6 mb-3">
					      <label for="validationTooltip02">第一集课程名称</label>
					      <input type="text" name="courseName1" class="form-control" id="courseName1" placeholder="course name"  required>
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
			      </td>
			    </tr>
			    <tr>
			      <td>
			      	<div class="form-row" id="2">
						<div class="col-md-6 mb-3">
					      <label for="validationTooltip02">第二集课程名称</label>
					      <input type="text" name="courseName2" class="form-control" id="courseName2" placeholder="course name"  required>
					      <div class="valid-tooltip">
					        Looks good!
					      </div>
					    </div>
					    <div class="col-md-6 mb-3">
					    	<div class="form-row">
					    		<div class="col-md-6">
					    			<label for="validationTooltip03">选择视频</label>
								      	<select id="2_video" name="videoName2" class="custom-select" required>
									      	
									    </select>
					    		</div>
					    		<div class="col-md-6">
					    			<label for="validationTooltip01">课程所属类别</label>
								      	<div class="input-group">
									        <select id="2_videoGroup" class="custom-select" onchange="getVideo(2)" required>
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
			      </td>
			    </tr>
			    <tr>
			      <td>
			      	<div class="form-row" id="3">
						<div class="col-md-6 mb-3">
					      <label for="validationTooltip02">第三集课程名称</label>
					      <input type="text" name="courseName3" class="form-control" id="courseName3" placeholder="course name"  required>
					      <div class="valid-tooltip">
					        Looks good!
					      </div>
					    </div>
					    <div class="col-md-6 mb-3">
					    	<div class="form-row">
					    		<div class="col-md-6">
					    			<label for="validationTooltip03">选择视频</label>
								      	<select id="3_video" name="videoName3" class="custom-select" required>
									      	
									    </select>
					    		</div>
					    		<div class="col-md-6">
					    			<label for="validationTooltip01">课程所属类别</label>
								      	<div class="input-group">
									        <select id="3_videoGroup" class="custom-select" onchange="getVideo(3)" required>
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
			      </td>
			    </tr>
			    <tr>
			      <td>
			      	<div class="form-row" id="4">
						<div class="col-md-6 mb-3">
					      <label for="validationTooltip02">第四集课程名称</label>
					      <input type="text" name="courseName4" class="form-control" id="courseName4" placeholder="course name"  required>
					      <div class="valid-tooltip">
					        Looks good!
					      </div>
					    </div>
					    <div class="col-md-6 mb-3">
					    	<div class="form-row">
					    		<div class="col-md-6">
					    			<label for="validationTooltip03">选择视频</label>
								      	<select id="4_video" name="videoName4" class="custom-select" required>
									      	
									    </select>
					    		</div>
					    		<div class="col-md-6">
					    			<label for="validationTooltip01">课程所属类别</label>
								      	<div class="input-group">
									        <select id="4_videoGroup" class="custom-select" onchange="getVideo(4)" required>
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
			      </td>
			    </tr>
			    <tr>
			      <td>
			      	<div class="form-row" id="5">
						<div class="col-md-6 mb-3">
					      <label for="validationTooltip02">第五集课程名称</label>
					      <input type="text" name="courseName5" class="form-control" id="courseName5" placeholder="course name"  required>
					      <div class="valid-tooltip">
					        Looks good!
					      </div>
					    </div>
					    <div class="col-md-6 mb-3">
					    	<div class="form-row">
					    		<div class="col-md-6">
					    			<label for="validationTooltip03">选择视频</label>
								      	<select id="5_video" name="videoName5" class="custom-select" required>
									      	
									    </select>
					    		</div>
					    		<div class="col-md-6">
					    			<label for="validationTooltip01">课程所属类别</label>
								      	<div class="input-group">
									        <select id="5_videoGroup" class="custom-select" onchange="getVideo(5)" required>
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
			      </td>
			    </tr>
			</tbody>
		</table>
		
    </div>
    
    
    
    
    <#include "../footer.ftl">
    
  </body>
</html>
<script>
//执行一个laydate实例
laydate.render({
  elem: '#endTime1' //指定元素
  ,type: 'datetime'
});
laydate.render({
  elem: '#endTime2' //指定元素
  ,type: 'datetime'
});
laydate.render({
  elem: '#endTime3' //指定元素
  ,type: 'datetime'
});
laydate.render({
  elem: '#endTime4' //指定元素
  ,type: 'datetime'
});
</script>
<script>
	function compileCourse(){
		$.ajax({
		   	type: "POST",
		   	url: "${indexpath}/teacher/compileCourse.action",
		   	data: {"id":$("#courseID").val(),"intro":$("#courseIntro").val(),"courseTypeID":$("#courseTypeID").val(),
		   			"courseName1":$("#courseName1").val(),"courseName2":$("#courseName2").val(),
		   			"courseName3":$("#courseName3").val(),"courseName4":$("#courseName4").val(),
		   			"courseName5":$("#courseName5").val(),"videoName1":$("#1_video").val(),
		   			"videoName2":$("#2_video").val(),"videoName3":$("#3_video").val(),
		   			"videoName4":$("#4_video").val(),"videoName5":$("#5_video").val()},
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
	
	
	function delLink(linkID){
		$.ajax({
		   	type: "POST",
		   	url: "${indexpath}/teacher/delLink.action",
		   	data: {"courseID":$("#courseID").val(),"id":linkID},
		   	dataType: 'json',
		   	success: function(data){
		   		alert(decodeURIComponent(data.msg));
		   	}
		});
	}
	
	function ajaxFileUpload(msg){
		var data = eval("("+msg+")");
		alert(decodeURIComponent(data.msg));
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
	
</script>