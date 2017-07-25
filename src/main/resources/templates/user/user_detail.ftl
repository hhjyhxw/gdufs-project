<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>用户信息详情</title>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../css/global.css" media="all">
		<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../css/table.css" />
	</head>
	<body>
	

	
	 <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
     <legend>用户其他信息</legend>
    
     <div class="layui-field-box">
					爱好：${user.hobbies!''}
	   <hr>
	                   积分:${user.remainScore!''}
	  
	</div>
	 </fieldset>
	</body>
</html>	
	