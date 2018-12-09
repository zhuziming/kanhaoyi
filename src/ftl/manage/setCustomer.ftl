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
	<script src="${jspath}/laydate/laydate.js"></script>
	<style>
	  .date-input{padding-left: 10px; height: 38px; min-width: 262px; line-height: 38px; border: 1px solid #e6e6e6;  background-color: #fff;  border-radius: 2px;}
	</style>
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
	              	<#if (courseMap)??>
	              		<tr>
		                  <td>${courseMap.id}</td>
		                  <td>${courseMap.course_name}</td>
		                  <td>${courseMap.account}</td>
		                  <td>${courseMap.name}</td>
		                  <td>${courseMap.click_volume}</td>
		                </tr>
	              	<#else>
						暂时还没有课程
					</#if>
	              	
	              </tbody>
	            </table>
	            
	            <hr class="my-4">
	            
	            <table class="table table-striped table-sm">
	              <thead>
	                <tr>
	                  <th>用户id</th>
	                  <th>开始时间</th>
	                  <th>结束时间</th>
	                  <th>操作</th>
	                </tr>
	              </thead>
	              <tbody  id="contentList">
	              	<tr>
	              		<td><input type="text" value="${(customer.userID)!''}" class="date-input" id="userID"/></td>
	              		<td><input type="text" value="${(customer.beginTime)!''}" readonly class="date-input" placeholder="请选择日期" id="beginTime"></td>
	              		<td><input type="text" value="${(customer.endTime)!''}" readonly class="date-input" placeholder="请选择日期" id="endTime"></td>
	              		<td>
	              			<button type="button" onclick="addCustomer()" class="btn btn-primary">保存</button>
	              			<button type="button" onclick="delCustomer()" class="btn btn-danger">删除</button>
	              		</td>
	              	</tr>
	              	
	              </tbody>
	            </table>
	          </div>
        </main>
      </div>
    </div>
  </body>
</html>
<script>
//执行一个laydate实例
laydate.render({
  elem: '#beginTime' //指定元素
  ,type: 'datetime'
});
laydate.render({
  elem: '#endTime' //指定元素
  ,type: 'datetime'
});
</script>
<script>
	function addCustomer(){
		var userID = $("#userID").val();
		var beginTime = $("#beginTime").val();
		var endTime = $("#endTime").val();
		$.ajax({
		   	type: "POST",
		   	url: "${indexpath}/manage/addCustomer.action",
		   	data: {"userID":userID,"courseID":${courseMap.id},"beginTime":beginTime,"endTime":endTime},
		   	dataType: "json",
		   	success: function(msg){
		   		alert(msg.msg);
		   		if(msg.success==1){
		   			location.reload();
		   		}
		   	}
		});
	}
	
	function delCustomer(){
		$.ajax({
		   	type: "POST",
		   	url: "${indexpath}/manage/delCustomer.action",
		   	data: {"customerID":"${(customer.id)!''}"},
		   	dataType: "json",
		   	success: function(msg){
		   		alert(msg.msg);
		   		if(msg.success==1){
		   			location.reload();
		   		}
		   	}
		});
	}
</script>