<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>汽車入庫</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
</head>
<body>
	<div class="body">
		<p class="title">汽車入庫</p>
		
		<form id="form" action="doMessage" method="POST">		
			<p class="content">
					新車
					<input type="text" name="name" size="10"/>
					，
					<input type="text" name="count" size="10"/>
					輛
					<input type="submit" value="!" class="QuestionMark"/>
		   </p>		   
		</form>
		<a href="#springMessage("homeURL")">home</a> 
		<!--<a href="../../index.html">home</a>-->
	</div>
</body>
</html>