<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <title>看好医</title>
	    <link rel="icon" href="${imgpath}/favicon.ico" type="image/x-icon"/>
	    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="${csspath}/boots/bootstrap.min.css" rel="stylesheet">
		<link href="${csspath}/form-validation.css" rel="stylesheet">
		<link href="${csspath}/boots/blog.css" rel="stylesheet">
	</head>
	<body>
	
		<header style="padding-bottom:15px;">
	      <div style="background-position:top center;height:170px; background-image:url('${imgpath}/0ac04c23af3b3297bf02dca163474326898d211d.png');" class="bg-dark collapse show img-fluid" id="navbarHeader" >
	        <nav style="height:42px; background-color: hsla(0,0%,100%,.4);box-shadow: 0 1px 2px rgba(0,0,0,.1);" class="navbar navbar-expand-md navbar-dark absolute-top">
	        	<div class="container">
		            <div class="btn-group" role="group" aria-label="Basic example">
					  
					</div>
					<div class="btn-group" role="group" aria-label="Basic example">
					  
					</div>
	        	</div>
		    </nav>
	      </div>
	    </header>
	
		<div class="container">
			<div class="jumbotron">
			  <h1 class="display-4">注册成功</h1>
			  <p class="lead">${user.nickname} 感谢您对我们的支持</p>
			  <hr class="my-4">
			  <p>如果使用中您有想看的内容是我们没有的，请在kanhaoyi@sina.cn 或 留言板块给我们留言</p>
			  <p class="lead">
			    <a class="btn btn-primary btn-lg" href="${indexpath}" role="button">去首页</a>
			  </p>
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
</script>