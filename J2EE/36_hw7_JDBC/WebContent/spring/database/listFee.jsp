<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/web.css" type="text/css">
<title>List Fee</title>
</head>
<body>
	<div class="body">
		<p class="title">List Fee</p>
		<p class="content">
		<center>
			<table border="1px" cellspacing="0px" cellpadding="6px" valign="middle">
				<tr>
					<th>ID</th>
					<th>NAME</th>
					<th>COUNT</th>
				</tr>
				#foreach($feeModel in $FeeListModel)
				<tr>
					<td>$feeModel.id</td>
					<td>$feeModel.name</td>
					<td>$feeModel.count</td>
				</tr>
				#end
			</table>
		</center><br>
		</p>
		<!--<a href="#springMessage("homeURL")">home</a>--> 
		<a href="../../index.html">home</a>
	</div>
</body>
</html>