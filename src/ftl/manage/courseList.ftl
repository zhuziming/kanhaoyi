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
				<div id="padingHTML">
					${padingHTML}
				</div>
         	</form>

         	<div class="table-responsive">
	            <table class="table table-striped table-sm">
	              <thead>
	                <tr>
	                  <th>id</th>
	                  <th>课程名字</th>
	                  <th>作者</th>
	                  <th>科室</th>
	                  <th>点击量</th>
	                </tr>
	              </thead>
	              <tbody  id="contentList">
	              	<#if (courseListMap?size=0)>
	              		暂时还没有课程
	              	<#else>
	              		<#list courseListMap as courseMap>
			                <tr>
			                  <td>${courseMap.id}</td>
			                  <td>${courseMap.course_name}</td>
			                  <td>${courseMap.account}</td>
			                  <td>${courseMap.name}</td>
			                  <td>${courseMap.click_volume}</td>
			                </tr>
		                </#list>
					</#if>
	              	
	              </tbody>
	            </table>
	          </div>
        </main>
      </div>
    </div>
  </body>
</html>
<script>
	function ajaxPaging(pageIndex){
		$.ajax({
		   	type: "POST",
		   	url: "${indexpath}/manage/ajaxGetCourseList.action",
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