<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>看好医后台</title>
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
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link" href="${indexpath}/manage/index.action">
                 	 首页
                </a>
              </li>
              <li class="nav-item bg-dark">
                <a class="nav-link text-light" href="${indexpath}/manage/systemList.action">
                  	系统页列表
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${indexpath}/manage/courseList.action">
                  	课程列表
                </a>
              </li>
            </ul>
          </div>
        </nav>

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
         	
         	<div class="table-responsive">
	            <table class="table table-striped table-sm">
	              <thead>
	                <tr>
	                  <th>级别</th>
	                  <th>名称</th>
	                  <th>操作</th>
	                </tr>
	              </thead>
	              <tbody>
	                <tr>
	                  <td>一级</td>
	                  <td>首页</td>
	                  <td>
	                  	<button type="button" onclick="createIndex()" class="btn btn-info btn-sm">生成页面</button>
	                  </td>
	                </tr>
	                <#list courseTypeList as courseType>
		                <tr>
		                  <td>二级</td>
		                  <td>${courseType.name}</td>
		                  <td >
		                  	<button type="button" onclick="createCourseTypeNav(${courseType.id})" class="btn btn-info btn-sm">生成页面</button>
		                  </td>
		                </tr>
	                </#list>
					<#list peoplePartList as peoplePart>
		                <tr>
		                  <td>二级</td>
		                  <td>${peoplePart.partName}</td>
		                  <td >
		                  	<button type="button" onclick="createPeoplePartNav(${peoplePart.id})" class="btn btn-info btn-sm">生成页面</button>
		                  </td>
		                </tr>
	                </#list>
	              </tbody>
	            </table>
	          </div>
        </main>
      </div>
    </div>
  </body>
</html>
<script>

	/* 创建人体部位导航
	   peoplePartID: 人体部位id
	*/
	function createPeoplePartNav(peoplePartID){
		$.ajax({
			type: "POST",
			url: "${indexpath}/manage/createPeoplePartNav.action",
			data:{peoplePartID:peoplePartID},
			success: function(msg){
				var info = eval("("+msg+")");
				alert(info.msg);
			}
		});
	}

	/* 创建课程类型导航 
	   courseTypeID: 课程类型id
	 */
	function createCourseTypeNav(courseTypeID){
		$.ajax({
			type: "POST",
			url: "${indexpath}/manage/createCourseTypeNav.action",
			data:{courseTypeID:courseTypeID},
			success: function(msg){
				var info = eval("("+msg+")");
				alert(info.msg);
			}
		});
	}
	/* 创建首页 */
	function createIndex(){
		$.ajax({
			type: "POST",
			url: "${indexpath}/manage/createIndex.action",
			data:{},
			success: function(msg){
				var info = eval("("+msg+")");
				alert(info.msg);
			}
		});
	}
</script>
