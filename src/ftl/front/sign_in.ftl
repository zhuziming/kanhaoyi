<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <title>看好医</title>
	    <link rel="icon" href="${imgpath}/favicon.ico" type="image/x-icon"/>
	    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="${indexpath}/css/boots/bootstrap.min.css">
		<link rel="stylesheet" href="${indexpath}/css/signin.css">
	</head>
	<body class="text-center">
		
		<form class="form-signin" action="${indexpath}/loginUrl.action" method="POST">
	      <img class="mb-4" src="${indexpath}/image/logo.png" alt="" width="150">
	      <h1 class="h3 mb-3 font-weight-normal">欢迎您回来</h1>
	      <label for="inputEmail" class="sr-only">账号</label>
	      <input type="text" id="account" name="account" class="form-control" placeholder="账号" required>
	      <label for="inputPassword" class="sr-only">密码</label>
		  <input type="password" id="password" name="password" class="form-control" placeholder="密码" required>
		  <div class="media">
			  <div class="media-body">
			    <input type="text" id="code" name="code" class="form-control" placeholder="验证码" required>
			  </div>
			  <img onclick="this.src='${indexpath}/getCode.action?'+new Date().getTime()" class="ml-3" src="${indexpath}/getCode.action" alt="Generic placeholder image">
		  </div>
		
	      <div class="checkbox mb-3">
	        <label>
	          <input type="checkbox" name="rememberMe" value="true">下次免登录
	        </label>
	      </div>
	      <#if bugMsg??>
	      	<div class="alert alert-danger" role="alert">
			  ${bugMsg!''}
		  	</div>
		  <#else>
	      
	      </#if>
	      
	      <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
	      <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
	    </form>	
		
	</body>
</html>