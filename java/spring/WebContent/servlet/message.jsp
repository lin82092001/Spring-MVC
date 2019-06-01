<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>Hello JSP</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../css/web.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="body">
		<p class="title">Message</p>

			<p class="message">
			${requestScope.result}
			</p>
			<p class="stamp">
			${requestScope.stamp}
			</p>
			<a href="../index.html">home</a>

	</div>
</body>
</html>