<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>报障详情</title>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../css/global.css" media="all">
		<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../css/table.css" />
	</head>
	<body>
	 <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
     <legend>报障信息</legend>
     </fieldset>
      <div class="layui-form-item">
					类型:
					${e.mark!''}
					<hr>

 
					保障者:${(e.nickName)!''}
					
	<hr>
					描述:
			
						${e.descbic}
	<hr>
					状态:
					
					    <#if e.status=="0">
					    未响应
					    </#if>
					    <#if e.status=="1">
					   处理中
					    </#if>
					    <#if e.status=="2">
					   已处理
					    </#if>
					    
						

<hr>
					
					 
					    <#if e.status=="1">
			响应时间:
					  ${e.modifyDate?string("yyyy-MM-dd HH:mm:ss")}
					    </#if>
					    <#if e.status=="2">
					   处理时间:
				
					    ${e.modifyDate?string("yyyy-MM-dd HH:mm:ss")}
				
					    </#if>
					   
					    
						
					
	</div>
	</body>
</html>	
	