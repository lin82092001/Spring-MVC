<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>Hello JSP</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../css/snow.css" rel="stylesheet" type="text/css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.js"></script>
	<script src="../javascript/jquery.snow.min.1.0.js"></script>
	
	<script>
		$(document).ready(function(){$.fn.snow();})
	</script>
</head>

<body class="snow">
	<div class="body">
		<p class="title"><font size="6">#stext('name=message.title')</font></p>

			<p class="message">
				<font size="6">
					$helloForm.result
				</font>
			</p>
			
			<p class="stamp">
				<font size="6">
					$helloForm.stamp
				</font>
			</p>
			<a href="../index.html"><font size="6">#stext('name=home')</font></a>

	</div>
</body>
</html>