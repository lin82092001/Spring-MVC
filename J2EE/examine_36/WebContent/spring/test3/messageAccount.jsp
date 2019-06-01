<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>帳戶資料</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
</head>

<body>
	<div class="body">

		<p class="title">帳戶資料</p>

		<p class="content">
		<center>
			<table border="1px" cellspacing="0px" cellpadding="6px"
				valign="middle">
				<tr style="text-align: center;">
					<th>車商序號</th>
					<td>$MessageModel.id</td>
				</tr>
				<tr style="text-align: center;">
					<th>車商姓名</th>
					<td>$MessageModel.name</td>
				</tr>

				<tr style="text-align: center;">
					<th>帳戶現金</th>
					<td>$MessageModel.cash元</td>
				</tr>
				<tr style="text-align: center;">
					<th>汽車資產</th>
					<td>$MessageModel.asset元</td>
				</tr>
				<tr style="text-align: center;">
					<th>汽車數量</th>
					<td>$MessageModel.count輛</td>
				</tr>
			</table>
		</center><br>

		<a href="#springMessage("homeURL")">#springMessage("home")</a>

	</div>
</body>
</html>