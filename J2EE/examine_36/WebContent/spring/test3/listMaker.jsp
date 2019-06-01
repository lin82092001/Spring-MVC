<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>車商列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../../css/web.css" type="text/css">
</head>

<body>
	<div class="body">
			<p class="title">車商列表</p>
			<p class="content">
			<center>
				<table border="1px" cellspacing="0px" cellpadding="6px"
					valign="middle">
					<tr style="text-align: center;">
						<th>全體車商總數</th>
						<td>$Model.AllModel.id家</td>
					</tr>
					<tr style="text-align: center;">
						<th>全體車商現金總額</th>
						<td>$Model.AllModel.cash元</td>
					</tr>
					<tr style="text-align: center;">
						<th>全體車商資產總額</th>
						<td>$Model.AllModel.asset元</td>
					</tr>
					<tr style="text-align: center;">
						<th>所有汽車總數</th>
						<td>$Model.AllModel.count輛</td>
					</tr>
			</table>
			</center><br>
			
			<center>
				<table border="1px" cellspacing="0px" cellpadding="6px"
					valign="middle">
					<tr style="text-align: center;">
						<th>序號</th>
						<th>姓名</th>
						<th>現金</th>
						<th>資產</th>
						<th>車數</th>
					</tr>
					#foreach( $OwnerModel in $Model.OwnerListModel )
					<tr style="text-align: center;">
						<td>$OwnerModel.id</td>
						<td>$OwnerModel.name</td>
						<td>$OwnerModel.cash</td>
						<td>$OwnerModel.asset</td>
						<td>$OwnerModel.count</td>
					</tr>
					#end
				</table>
			</center><br>	
		<a href="#springMessage("homeURL")">#springMessage("home")</a>	
	</div>
</body>
</html>