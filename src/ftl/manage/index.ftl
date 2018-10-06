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
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item bg-dark">
                <a class="nav-link text-light" href="${indexpath}/manage/indexPage.action">
                 	 首页
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${indexpath}/manage/systemListPage.action">
                  	系统页列表
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${indexpath}/manage/courseListPage.action">
                  	课程列表
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${indexpath}/manage/userListPage.action">
                  	用户列表
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${indexpath}/manage/userListPage.action">
                  	老师列表
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
	                  <th>id</th>
	                  <th>名称</th>
	                  <th>作者</th>
	                  <th>科室</th>
	                  <th>点击量</th>
	                  <th>操作</th>
	                </tr>
	              </thead>
	              <tbody>
	                <tr>
	                  <td>Lorem</td>
	                  <td>Lorem</td>
	                  <td>ipsum</td>
	                  <td>dolor</td>
	                  <td>ipsum</td>
	                  <td>生成页面</td>
	                </tr>
	              </tbody>
	            </table>
	          </div>
        </main>
      </div>
    </div>
  </body>
</html>
