<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>看好医</title>
    <meta http-equiv="keywords" content="看好医,医学讲解,医学视频">
	<meta http-equiv="description" content="看好医从事疾病的讲解，让用户了解病情的发生发展过程，从而帮助疾病的预防">
    <link rel="stylesheet" href="${csspath}/boots/bootstrap.min.css">
    <link rel="stylesheet" href="${csspath}/boots/blog.css">
    <link rel="stylesheet" href="${csspath}/kanhaoyi-index.css">
    <script src="${jspath}/jquery.min.js"></script>
	<script src="${jspath}/popper.min.js"></script>
	<script src="${jspath}/boots/bootstrap.min.js"></script>
  </head>
  
  <body>
  	<header class="pb-3">
      <div class="bg-dark collapse show img-fluid k-top-of-page" id="navbarHeader" >
        <nav class="navbar navbar-expand-md navbar-dark absolute-top k-nav-back">
        	<div class="container" >
	            <div class="btn-group" role="group">
				  <a class="btn btn-link text-dark">首页</a>
				</div>
				<div class="btn-group" role="group" aria-label="Basic example">
				  <a id="people" href="${indexpath}/loginPage.action" class="btn btn-link text-dark">
				  	未登录
				  </a>
				  <a id="signUp" href="${indexpath}/signUpPage.action" class="btn btn-link text-dark">
				  	注册
				  </a>
				  <a id="logout" href="${indexpath}/logout.action" class="btn btn-link text-dark" style="display: none;">
				  	退出
				  </a>
				  <a class="btn btn-link text-dark" href="${indexpath}/info-list.html">
				  	消息<span class="text-danger" id="infoNum"></span>
				  </a>
				</div>
        	</div>
	    </nav>
      </div>
    </header>
    
	<div class="container">
	 <div class="row">
	    <div class="col-xl-3 col-lg-8 col-md-6 col-sm-12 pb-3">
	      	<nav aria-label="breadcrumb ">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}/internal.html" target="_blank">内科</a></li>
			    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}/surgery.html" target="_blank">外科</a></li>
			  </ol>
			</nav>
			
			<nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}/ENT.html" target="_blank">耳鼻喉</a></li>
			    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}/ophthalmology.html" target="_blank">眼科</a></li>
			    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}/stomatology.html" target="_blank">口腔科</a></li>
			  </ol>
			</nav>
			
			<nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}/urinary.html" target="_blank">泌尿科</a></li>
			    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}/andrology.html" target="_blank">男科</a></li>
			  </ol>
			</nav>
			
			<nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}/gynaecology.html" target="_blank">妇产科</a></li>
			    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}/dermatology.html" target="_blank">皮肤科</a></li>
			  </ol>
			</nav>
			
			<nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}/TCM.html" target="_blank">中医科</a></li>
			    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}/beautify.html" target="_blank">养生科</a></li>
			  </ol>
			</nav>
			
			<nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}/heredity.html" target="_blank">遗传科</a></li>
			    <li class="breadcrumb-item"><a class="k-col-black" href="${indexpath}/pediatric.html" target="_blank">儿科</a></li>
			  </ol>
			</nav>
	    </div>
	    <div class="col-xl-6 col-sm-12 d-none d-xl-block pb-3">
				      
			<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
			    <ol class="carousel-indicators">
			      <li data-target="#carouselExampleCaptions" data-slide-to="0" class=""></li>
			      <li data-target="#carouselExampleCaptions" data-slide-to="1" class="active"></li>
			      <li data-target="#carouselExampleCaptions" data-slide-to="2" class=""></li>
			    </ol>
			    <div class="carousel-inner">
			      <div class="carousel-item">
			        <img class="d-block w-100" data-src="" alt="第一张幻灯片[570x400]" src="${imgpath}/5.png" data-holder-rendered="true">
			        <div class="carousel-caption d-none d-md-block">
			          <h5><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">首张幻灯片标签</font></font></h5>
			          <p><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Nulla vitae elit libero，pharetra augue mollis interdum。</font></font></p>
			        </div>
			      </div>
			      <div class="carousel-item active">
			        <img class="d-block w-100" data-src="" alt="第二张幻灯片[570x400]" src="${imgpath}/5.png" data-holder-rendered="true">
			        <div class="carousel-caption d-none d-md-block">
			          <h5><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">第二张幻灯片标签</font></font></h5>
			          <p><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Lorem ipsum dolor坐在amet，consectetur adipiscing elit。</font></font></p>
			        </div>
			      </div>
			      <div class="carousel-item">
			        <img class="d-block w-100" data-src="" alt="第三张幻灯片[570x400]" src="${imgpath}/5.png" data-holder-rendered="true">
			        <div class="carousel-caption d-none d-md-block">
			          <h5><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">第三张幻灯片标签</font></font></h5>
			          <p><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Praesent commodo cursus magna，vel scelerisque nisl consectetur。</font></font></p>
			        </div>
			      </div>
			    </div>
			    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
			      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			      <span class="sr-only"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">以前</font></font></span>
			    </a>
			    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
			      <span class="carousel-control-next-icon" aria-hidden="true"></span>
			      <span class="sr-only"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">下一个</font></font></span>
			    </a>
			  </div>
	    </div>
	    
	    <div class="col-xl-3 col-lg-4 col-md-6 col-12 mb-2">
	      
	      <table width="286" height="399" cellspacing="0" cellpadding="0" border="0">
				<tbody>
					<tr>
						<td rowspan="6">
							<a>
								<img src="${imgpath}/people-template/people_01.png" alt="" width="52" height="399">
							</a>
						</td>
						<td>
							<a href="www.baidu.com">
								<img src="${imgpath}/people-template/people_02.png" alt="" width="55" height="58">
							</a>
						</td>
						<td>
							<a data-toggle="tooltip" data-placement="right" title="头痛点这里" href="${indexpath}/head.html" target="_blank">
								<img src="${imgpath}/people-template/people_03.png" alt="" width="73" height="58">
							</a>
						</td>
						<td>
							<a href="www.baidu.com">
								<img src="${imgpath}/people-template/people_04.png" alt="" width="56" height="58">
							</a>
						</td>
						<td rowspan="6">
							<a href="www.baidu.com">
								<img src="${imgpath}/people-template/people_05.png" alt="" width="49" height="399">
							</a>
						</td>
					</tr>
					<tr>
						<td rowspan="4">
							<a data-toggle="tooltip" data-placement="right" title="上肢痛点这里" href="${indexpath}/hand.html" target="_blank">
								<img src="${imgpath}/people-template/people_06.png" alt="" width="55" height="180">
							</a>
						</td>
						<td>
							<a data-toggle="tooltip" data-placement="right" title="胸口痛点这里" href="${indexpath}/chest.html" target="_blank">
								<img src="${imgpath}/people-template/people_07.png" alt="" width="73" height="65">
							</a>
						</td>
						<td rowspan="4">
							<a data-toggle="tooltip" data-placement="right" title="上肢痛点这里" href="${indexpath}/hand.html" target="_blank">
								<img src="${imgpath}/people-template/people_08.png" alt="" width="56" height="180">
							</a>
						</td>
					</tr>
					<tr>
						<td>
							<a data-toggle="tooltip" data-placement="right" title="肚子痛点这里" href="${indexpath}/abdomen.html" target="_blank">
								<img src="${imgpath}/people-template/people_09.png" alt="" width="73" height="48">
							</a>
						</td>
					</tr>
					<tr>
						<td>
							<a data-toggle="tooltip" data-placement="right" title="阴部痛点这里" href="${indexpath}/perineum.html" target="_blank">
								<img src="${imgpath}/people-template/people_10.png" alt="" width="73" height="40">
							</a>
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							<a data-toggle="tooltip" data-placement="right" title="下肢痛点这里" href="${indexpath}/leg.html" target="_blank">
								<img src="${imgpath}/people-template/people_11.png" alt="" width="73" height="188">
							</a>
						</td>
					</tr>
					<tr>
						<td>
							<a>
								<img src="${imgpath}/people-template/people_12.png" alt="" width="55" height="161">
							</a>
						</td>
						<td>
							<a>
								<img src="${imgpath}/people-template/people_13.png" alt="" width="56" height="161">
							</a>
						</td>
					</tr>
				</tbody>
			</table>

	    </div>
	  </div>
	  <#if (maxClickList?size > 0)>
		  <div class="row">
		  	<div class="col">
		  		<h3>最热榜单</h3>
		  	</div>
		  </div>
		  <div class="row">
		  	<#list maxClickList as course>
			    <div class="col-xl-2 col-lg-3 col-md-3 col-sm-4 col-6">
			    	<a href="${indexpath}${course.coursePath}">
			    		<img width="100%;" src="${imgpath}${course.picturePath}"></img>
			      		<p style="word-wrap:keep-all;white-space:nowrap;overflow: hidden;">${course.courseName}</p>
			    	</a>
			    </div>
		    </#list>
		  </div>
	  </#if>
	  
	  <#if (newTimeList?size > 0)>
		  <div class="row">
		  	<div class="col">
		  		<h3>最新发布</h3>
		  	</div>
		  </div>
		  <div class="row">
		  	<#list newTimeList as course>
			    <div class="col-xl-2 col-lg-3 col-md-3 col-sm-4 col-6">
			    	<a href="${indexpath}${course.coursePath}">
			    		<img width="100%;" src="${imgpath}${course.picturePath}"></img>
			      		<p style="word-wrap:keep-all;white-space:nowrap;overflow: hidden;">${course.courseName}</p>
			    	</a>
			    </div>
		    </#list>
		  </div>
	  </#if>
	</div>

	<#include "../footer.ftl">
	
  </body>
</html>
<script>
	/* 人体图片提示功能启用  */
	$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})
</script>
<script src="${jspath}/kanhaoyi.js"></script>