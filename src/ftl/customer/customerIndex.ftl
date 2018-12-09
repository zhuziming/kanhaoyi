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
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">客服中心</a>
      
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="#">退出</a>
        </li>
      </ul>
    </nav>

    <div class="container-fluid">
        <#if (listMap?size=0)>
	  		暂时还没有课程
	  	<#else>
	  		<div class="row">
				<#list listMap as courseMap>
		            <div class="col-xl-2 col-lg-3 col-md-3 col-sm-4 col-6">
				    	<a href="${indexpath}/customer/customerSocket.action?courseID=${courseMap.course_id}" target="_blank">
				    		<img width="100%;" src="${imgpath}/courseImg${courseMap.picture_path}"></img>
				      		<p class="k-course-sort">${courseMap.course_name}</p>
				      		<p>开始时间：${courseMap.begin_time}</p>
				      		<p>到期时间：${courseMap.end_time}</p>
				    	</a>
				    </div>
		        </#list>
			</div>
		</#if>
      
    </div>
  </body>
</html>
