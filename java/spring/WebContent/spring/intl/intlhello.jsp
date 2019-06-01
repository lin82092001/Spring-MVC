<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>hello</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../css/web.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="body">
		<p class="title"><font size="6">#stext('name=hello.title')</font></p>
		
		<form action="doHello" method="POST">
			<p class="context">
				<font size="6">
					#stext('name=hello.question')
						<input type="text" name="helloForm.name" size="12"/>
						<input type="submit" value="?"/>
				</font>
			</p>

		</form>
			<a href="../index.html"><font size="6">#stext('name=home')</font></a>

	</div>
</body>
</html>