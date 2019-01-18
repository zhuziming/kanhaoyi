<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <title>看好一</title>
	    <link rel="icon" href="${imgpath}/favicon.ico" type="image/x-icon"/>
	    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="${csspath}/boots/bootstrap.min.css" rel="stylesheet" >
		<link href="${csspath}/form-validation.css" rel="stylesheet">
		<link href="${csspath}/boots/blog.css" rel="stylesheet" >
	</head>
	<body>
		<div class="container">
	      <div class="py-5 text-center">
	        <img class="d-block mx-auto mb-4" src="${imgpath}/logo.png" alt="" width="150">
	        <h2>看好一欢迎您</h2>
	        <p class="lead">
	        	以下信息为基本信息，请尽量填写完整，注册成功后，可以前往个人中心完善个人信息
	        </p>
	      </div>
	    <div class="row">
		    <div class="col-md-12 order-md-1">
		    	<form method="POST" id="formOne" action="${indexpath}/signUp.action" class="needs-validation" novalidate>
		    		<div class="mb-3">
		              <label for="user">账号</label>
		              <input type="text" value="${(user.account)!''}" class="form-control" name="account" id="account" placeholder="建议使用手机号" required>
		              <div class="invalid-feedback" style="width: 100%;">
		              	账号不能为空
		              </div>
		            </div>
		            
		            <div class="mb-3">
		              <label for="user">昵称</label>
		              <input type="text" value="${(user.nickname)!''}" class="form-control" name="nickname" id="account" placeholder="比如：张三" required>
		              <div class="invalid-feedback" style="width: 100%;">
		              	昵称不能为空
		              </div>
		            </div>
		            
		            <div class="mb-3">
		              <label for="user">手机号</label>
		              <input type="text" value="${(user.phone)!''}" class="form-control" name="phone" id="phone" required>
		              <div class="invalid-feedback">
		               	手机号不能为空
		              </div>
		            </div>
		            
		            <div class="mb-3">
		              <label for="email">Email</label>
		              <div class="input-group">
		                <div class="input-group-prepend">
		                  <span class="input-group-text">@</span>
		                </div>
		                 <input type="email" value="${(user.email)!''}" class="form-control" name="email" id="email">
		                <div class="invalid-feedback" style="width: 100%;">
			              	Email格式错误
			            </div>
		              </div>
		            </div>
		            
		            
		            
		            <div class="mb-3">
		                <label for="state">性别</label>
		                <select class="custom-select d-block w-100" name="sex" id="sex" required>
		                  <option value="">--/--</option>
		                  <option value="男">男</option>
		                  <option value="女">女</option>
		                </select>
		                <div class="invalid-feedback">
		               	性别不能为空
		              	</div>
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
						  <img onclick="this.src='${indexpath}/getCode.action?'+new Date().getTime()" class="ml-3" src="${indexpath}/getCode.action" alt="Generic placeholder image">
					 </div>
					 <#if bugMsg??>
				      	<div class="alert alert-danger" role="alert">
						  ${bugMsg!''}
					  	</div>
					 <#else>
				      
				     </#if>
		            <button class="btn btn-primary btn-lg btn-block"  type="submit">注册</button>
		     	</form>       
		    </div>
	    	
	    </div>
	    
	    </div>
		<footer class="blog-footer">
	      <p>看好一www.kanhaoyi.com专注于各种病情的讲解，提高全民医疗常识，如有不足的地方请在以下邮箱留言kanhaoyi@sina.cn</p>
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
      
      
    </script>