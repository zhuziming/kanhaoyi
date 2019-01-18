<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>看好一-网站地图</title>
    <link rel="icon" href="${imgpath}/favicon.ico" type="image/x-icon"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="${csspath}/boots/bootstrap.min.css">
    <link rel="stylesheet" href="${csspath}/kanhaoyi-index.css">
  </head>
  <body>
  	<div class="container-fluid">
  		<#list courseTypeList as courseType>
  			<#if map[courseType.name]??&&map[courseType.name]?size gt 0>
  				<h3>${courseType.name}</h3>
				<div class="row">
					<#list map[courseType.name] as course>
					  	<div class="col-xl-1 col-lg-2 col-md-3 col-sm-4 col-6">
					    	<a href="${indexpath}${course.coursePath}">
					      		<p class="k-course-sort">${course.courseName}</p>
					    	</a>
					    </div>
					</#list>
				</div>
			</#if>
		</#list>
	  	<#list peoplePartList as peoplePart>
	  		<#if map[peoplePart.partName]??&&map[peoplePart.partName]?size gt 0>
  				<h3>${peoplePart.partName}</h3>
				<div class="row">
					<#list map[peoplePart.partName] as course>
					  	<div class="col-xl-1 col-lg-2 col-md-3 col-sm-4 col-6">
					    	<a href="${indexpath}${course.coursePath}">
					      		<p class="k-course-sort">${course.courseName}</p>
					    	</a>
					    </div>
					</#list>
				</div>
			</#if>
	  	</#list>
	</div>
  </body>
</html>