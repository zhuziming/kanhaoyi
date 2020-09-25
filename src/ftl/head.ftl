<div class="bg-dark collapse show " id="navbarHeader" >
    <nav class="navbar navbar-expand-md absolute-top k-ma-nav-back">
    	<div class="container text-white" >
            <div class="btn-group" role="group" aria-label="Basic example">
			   <a href="${indexpath}" class="btn btn-link text-white">首页</a>
			</div>
			<div class="btn-group" role="group" aria-label="Basic example">
			  	<a id="people" href="${indexpath}/back/index.action" class="btn btn-link text-white">
			  		${(user.nickname)!''}
			  	</a>
			  	<a id="logout" href="${indexpath}/logout.action" class="btn btn-link text-white">
				  	退出
				</a>
				<a id="people" href="${indexpath}/loginPage.action" class="btn btn-link text-white">
				  	未登录
				</a>
				<a id="signUp" href="${indexpath}/signUpPage.action" class="btn btn-link text-white">
				  	注册
				</a>
			</div>
    	</div>
    </nav>
</div>