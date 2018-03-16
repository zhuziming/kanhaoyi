<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>在Servlet中使用FreeMarker</title>
	</head>
	<body>
		${name}<br />
		<#list names as name>
			${name}<br />
		</#list>
	</body>
</html>