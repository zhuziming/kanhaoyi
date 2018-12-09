<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>客服聊天</title>

    <link href="${csspath}/boots/bootstrap.min.css" rel="stylesheet">
    <link href="${csspath}/dashboard.css" rel="stylesheet" >
    <script src="${jspath}/jquery.min.js"></script>
	<script src="${jspath}/popper.min.js"></script>
	<script src="${jspath}/boots/bootstrap.min.js"></script>
	<script src="${jspath}/socketServer.js"></script>
  </head>

  <body>
    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">看好医客服后台</a>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <nav class="col-2  sidebar">
          <div class="sidebar-sticky">
            <ul id="ulContent" class="nav flex-column">
            </ul>
          </div>
        </nav>

        <main role="main" class="col-10 ml-auto pt-3 px-4">
			<div id="sayContent" class="jumbotron" style="height:500px; overflow-y: auto;">
				<h6>欢迎来到看好医，请稍等。</h6>
				<div id="sayBottom"></div>
			</div>
        </main>
		<div id="contentBottom"></div>
      </div>
    </div>
  </body>
</html>
<script>
ws.onopen = function() {        
	var content = getContent("server","","${courseID}","","signUp");
	ws.send(JSON.stringify(content));
};

</script>


