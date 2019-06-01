<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>車市列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../../css/web.css" type="text/css">
</head>

<body>
	<div class="body">
			<p class="title">車市列表</p>
			<p class="content">
			<center>
				<table border="1px" cellspacing="0px" cellpadding="6px"
					valign="middle">
					<tr style="text-align: center">
						<th>序號</th>
						<th>汽車</th>
						<th>價格</th>
						<th>車商序號</th>
					</tr>
					#foreach( $CarModel in $CarListModel )
					<tr style="text-align: center">
						<td>$CarModel.id</td>
						<td>$CarModel.name</td>
						<td>$CarModel.price</td>
						<td>$CarModel.ownerId</td>
					</tr>
					#end
				</table>
			</center><br>
		<a href="#springMessage("homeURL")">#springMessage("home")</a>	
	</div>
</body>
</html>