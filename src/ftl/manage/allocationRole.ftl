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
	                  <th>账号</th>
	                  <th>昵称</th>
	                  <th>手机号</th>
	                  <th>性别</th>
	                  <th>操作</th>
	                </tr>
	              </thead>
	              <tbody>
	                <tr>
	                  <td>${user.id}</td>
	                  <td>${user.account}</td>
	                  <td>${user.nickname}</td>
	                  <td>${user.phone}</td>
	                  <td>${user.sex}</td>
	                  <td>
	                  	<button type="button" onclick="updateRole()" class="btn btn-info btn-sm">
	                  		赋予角色
	                  	</button>
	                  </td>
	                </tr>
	              </tbody>
	            </table>
	          </div>
        </main>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
        	<#list roleList as role>
		        <div style="width: 20%; display: inline-block; border: 1px solid #eee;padding: 5px">
					<label class="checkbox">
				      <input ${(role.flag=='1')?string("checked='checked'","")} name="roles" type="checkbox" value="${role.id}"> ${role.title}
				    </label>
				</div>
	        </#list>
        </main>
      </div>
    </div>
  </body>
</html>

<script>
	/* 更新用户角色 */
	function updateRole(){
		var checkID = []; // 定义一个空数组
		$("input[name='roles']:checked").each(function(i){ // 把所有被选中的复选框的值存入数组
        	checkID[i] =$(this).val();
    	});
		$.ajax({
			type: "POST",
			url: "${indexpath}/manage/updateRole.action",
			data:{userID:${user.id},roles:checkID},
			success: function(data){
				var info = eval("("+data+")");
				alert(info.msg);
			}
		});
	}
</script>
