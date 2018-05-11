<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <title>看好医</title>
	    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="http://172.16.1.175:8080/webWork/css/boots/bootstrap.min.css">
		<link href="http://172.16.1.175:8080/webWork/css/form-validation.css" rel="stylesheet">
		<link rel="stylesheet" href="http://172.16.1.175:8080/webWork/css/boots/blog.css">
	</head>
	<body>
		<div class="container">
	      <div class="py-5 text-center">
	        <img class="d-block mx-auto mb-4" src="http://172.16.1.175:8080/webWork/image/logo.png" alt="" width="150">
	        <h2>看好医欢迎您</h2>
	        <p class="lead">
	        	以下信息为基本信息，请尽量填写完整，注册成功后，可以前往个人中心完善个人信息
	        </p>
	      </div>
	    <div class="row">
		    <div class="col-md-12 order-md-1">
		    	<iframe name="ajaxIframe" hidden></iframe>
		    	<form id="formOne" target="ajaxIframe" action="http://172.16.1.175:8080/webWork/signUp.action" class="needs-validation" novalidate>
		    		<div class="mb-3">
		              <label for="user">账号</label>
		              <input type="text" class="form-control" name="account" id="account" placeholder="建议使用手机号" required>
		              <div class="invalid-feedback" style="width: 100%;">
		              	账号不能为空
		              </div>
		            </div>
		            
		            <div class="mb-3">
		              <label for="user">昵称</label>
		              <input type="text" class="form-control" name="nickname" id="account" placeholder="比如：张三" required>
		              <div class="invalid-feedback" style="width: 100%;">
		              	昵称不能为空
		              </div>
		            </div>
		            
		            <div class="mb-3">
		              <label for="user">手机号</label>
		              <input type="text" class="form-control" name="phone" id="phone">
		            </div>
		            
		            <div class="mb-3">
		              <label for="email">Email</label>
		              <div class="input-group">
		                <div class="input-group-prepend">
		                  <span class="input-group-text">@</span>
		                </div>
		                 <input type="email" class="form-control" name="email" id="email">
		                <div class="invalid-feedback" style="width: 100%;">
			              	Email格式错误
			              </div>
		              </div>
		            </div>
		            
		            
		            
		            <div class="mb-3">
		                <label for="state">性别</label>
		                <select class="custom-select d-block w-100" name="sex" id="sex">
		                  <option>--/--</option>
		                  <option value="男">男</option>
		                  <option value="女">女</option>
		                </select>
		              </div>
		            
		            
		            <div class="mb-3">
		              <label for="nickname">密码</label>
		              <input type="password" class="form-control" name="password" id="password" placeholder="" required>
		              <div class="invalid-feedback">
		               	密码不能为空
		              </div>
		            </div>
		            <label for="nickname">验证码</label>
		            <div class="media mb-3">
						  <div class="media-body">
						    <input type="text" name="code" class="form-control" placeholder="验证码" required>
						    <div class="invalid-feedback">
				               	请输入验证码
				            </div>
						  </div>
						  <img onclick="this.src='http://172.16.1.175:8080/webWork/getCode.action?'+new Date().getTime()" class="ml-3" src="http://172.16.1.175:8080/webWork/getCode.action" alt="Generic placeholder image">
					 </div>
		            <button class="btn btn-primary btn-lg btn-block"  type="submit">注册</button>
		     	</form>       
		    </div>
	    	
	    </div>
	    
	    </div>
		<footer class="blog-footer">
	      <p>看好医www.kanhaoyi.com专注于各种病情的讲解，提高全民医疗常识，如有不足的地方请在以下邮箱留言kanhaoyi@sina.cn</p>
	      <p>
	        <a href="#">返回顶部</a>
	      </p>
	    </footer>
	</body>
</html>

<script>
      // Example starter JavaScript for disabling form submissions if there are invalid fields
      (function() {
        'use strict';

        window.addEventListener('load', function() {
          // Fetch all the forms we want to apply custom Bootstrap validation styles to
          var forms = document.getElementsByClassName('needs-validation');

          // Loop over them and prevent submission
          var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
              if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
              }
              form.classList.add('was-validated');
            }, false);
          });
        }, false);
      })();
      
      function submitTar(msg){
    	  var jo = eval("("+decodeURIComponent(msg)+")");
    	  if(jo.success==1){
    		  window.location.href='http://172.16.1.175:8080/webWork/sign-up-success.html';
    	  }else if(jo.success==2){
    		  alert(jo.msg);
    	  }else if(jo.success==3){
    		  alert(jo.msg);
    	  }
      }
    </script>