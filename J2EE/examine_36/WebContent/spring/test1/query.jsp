<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>庫存查詢</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
</head>
<body>
	<div class="body">
		<p class="title">庫存查詢</p>
		
		<form id="form" action="doQuery" method="POST">
		
			<p class="content">
				新車
				<input type="text" name="name" size="10"/>
				數量
				<input type="submit" value="?" class="QuestionMark"/>
		   </p>
		
		</form>
		<a href="#springMessage("homeURL")">home</a> 
		<!--<a href="../../index.html">home</a>-->	
	</div>
</body>
</html>