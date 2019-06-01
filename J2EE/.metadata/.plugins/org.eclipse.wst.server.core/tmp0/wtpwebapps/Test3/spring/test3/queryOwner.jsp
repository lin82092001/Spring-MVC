<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的帳戶</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
</head>
<body>
	<div class="body">
		<p class="title">我的帳戶</p>
		
		<form id="form" action="doQueryOwner" method="POST">
		
			<p class="content">
				
				車商序號：
				<input type="text" name="ownerId" size="10"/>
				<br><br>
				<input type="submit" value="查詢" class="QuestionMark"/>
		   </p>
		
		</form>
	<a href="#springMessage("homeURL")">home</a> 
		<!--<a href="../../index.html">home</a>-->	
	</div>
</body>
</html>