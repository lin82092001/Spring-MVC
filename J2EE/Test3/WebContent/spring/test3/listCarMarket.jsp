<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
<title>List</title>
</head>
<body>
	<div class="body">
		<p class="title">車市列表</p>
		<p class="content">
		<center>
			<table border="1px" cellspacing="0px" cellpadding="6px" valign="middle">
				<tr>
					<th>序號</th>
					<th>汽車</th>
					<th>價格</th>
					<th>車商序號</th>
				</tr>
				#foreach($CarMarketModel in $CarListModel)
				<tr>
					<td>$CarMarketModel.id</td>
					<td>$CarMarketModel.name</td>
					<td>$CarMarketModel.price</td>
					<td>$CarMarketModel.ownerId</td>
				</tr>
				#end
			</table>
		</center><br>
		</p>
		<a href="#springMessage("homeURL")">home</a> 
		<!--<a href="../../index.html">home</a>-->
	</div>
</body>
</html>