<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
<title>我的車庫</title>
</head>
<body>
	<div class="body">
		<p class="title">我的車庫</p>
		<p class="content">
			車商序號 = $CarMarketModel.ownerId 
			<br><br>
			汽車數量 = $CarMarketModel.count			
			<br><br>
			
			<center>
			<table border="1px" cellspacing="0px" cellpadding="6px" valign="middle">
				<tr>
					<th>序號</th>
					<th>汽車</th>
					<th>價格</th>
				</tr>
				#foreach($feeModel in $CarListModel)
				<tr>
					<td>$feeModel.id</td>
					<td>$feeModel.Carname</td>
					<td>$feeModel.price</td>
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