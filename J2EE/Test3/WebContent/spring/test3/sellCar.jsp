<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
<title>我要造車</title>
</head>
<body>
	<div class="body">
		<p class="title">我要造車</p>
		<form id="form" action="doSellCar" method="POST">
			<p class="content">
			車商序號： 
				<input type="text" name="ownerId" size="10"/>
				<br>
			汽車名稱：
				<input type="text" name="Carname" size="10"/>
				<br>
			汽車價格：
				<input type="text" name="price" size="10"/>
				<br>
				<input type="submit" value="造車" class="QuestionMark"/>
			</p>
		</form>
		<a href="#springMessage("homeURL")">home</a> 
		<!--<a href="../../index.html">home</a>-->
	</div>
</body>
</html>