<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>Error Message</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/web.css" type="text/css">
</head>
<body>
	<div class="body">
		<p class="title">
			Error
		</p>
		<p class="content">
		#foreach( $ErrorMessage in $ErrorModel )
   			$ErrorMessage.getDefaultMessage() <br>
		#end
		</p>
		<a href="#springMessage("homeURL")"> home </a>
	</div>
</body>
</html>