<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
<title>帳戶資料</title>
</head>
<body>
	<div class="body">
		<p class="title">帳戶資料</p>
		<p class="content">
			
			<center>
			<table border="1px" cellspacing="0px" cellpadding="6px" valign="middle">
				<tr>
					<th>車商序號</th>
					<td>$CarMarketModel.id</td>
				</tr>
				<tr>
					<th>車商姓名</th>
					<td>$CarMarketModel.name</td>
				</tr>
				<tr>
					<th>帳戶現金</th>
					<td>$CarMarketModel.cash元</td>
				</tr>
				<tr>
					<th>汽車資產</th>
					<td>$CarMarketModel.asset元</td>
				</tr>
				<tr>
					<th>汽車數量</th>
					<td>$CarMarketModel.count輛</td>
				</tr>
			</table>
		</center><br>
		
		</p>
		<a href="#springMessage("homeURL")">home</a> 
		<!--<a href="../../index.html">home</a>-->
	</div>
</body>
</html>