<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>管理员</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">

		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
	    <style>
	      .layui-textarea {
		    height: 230px;
          }
	    </style>
	 
	</head>

	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>活动<#if ac??>修改<#else>添加</#if></legend>
			</fieldset>

			<form class="layui-form" action="" id="form">
			<#if ac??><input type="hidden" name="id" value="${ac.id}"/></#if>
			 
				<div class="layui-form-item">
					<label class="layui-form-label">活动名称：</label>
					<div class="layui-input-block">
						<input type="text" name="name" <#if ac??>value="${ac.name}"</#if> lay-verify="name" style="width:190px;" autocomplete="off" placeholder="请活动名称" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">状态</label>
					<div class="layui-input-block">
						<input type="checkbox" name="status" value="1" lay-skin="switch" title="开关" <#if ac??><#if ac.status=="1">checked</#if></#if>  lay-text="显示|不显示">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">是否banner</label>
					<div class="layui-input-block">
						<input type="checkbox" name="banner" value="1" lay-skin="switch" title="开关" <#if ac??><#if ac.banner=="1">checked</#if></#if>  lay-text="是|不是">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">排序</label>
					<div class="layui-input-block">
						<input type="number" name="order" class="layui-input" <#if ac??>value=${ac.orders}<#else>value=0</#if> style="width:190px;" lay-verify="order" >
					</div>
				</div>
				<div class="layui-form-item">
				<label class="layui-form-label">活动预览图</label>
				<div class="site-demo-upload">
                  <img id="LAY_demo_upload" <#if ac??>src="${ac.icon}"<#else>src="../images/t01b7922a0172c31452.jpg"</#if> width="172px">
                  <input type="hidden" name="icon" <#if ac??>value="${ac.icon}"</#if> id="LAY_demo_upload_VAL" >
               </div>
               </div>
               <div class="layui-form-item">
				<label class="layui-form-label">图片上传</label>
              <div class="site-demo-upload">
                  <div class="site-demo-upbar">
                    <input type="file" name="file" class="layui-upload-file" lay-type="images" id="test">
                  </div>
               </div>
               </div>
               <textarea  name="introduction" required lay-verify="required" placeholder="请输入" class="layui-textarea">
                <#if ac??>${ac.introduction}</#if>
               </textarea>
			
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					   <#if ac??><button type="button" class="layui-btn layui-btn-primary" onclick="javascript:window.location.href='/admin/activities_list'">返回</button></#if>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="../plugins/layui/layui.js"></script>
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script>
			layui.use(['form','layedit','upload'], function() {
				var form = layui.form(),
				layer = parent.layer === undefined ? layui.layer : parent.layer;
                  layui.upload({
                          url: '/upload'
                          ,elem: '#test' 
                         ,method: 'post' 
                         ,before: function(input){
                              layer.msg('图片上传中...',{time:2000})
                          },
                        success: function(res){
                         LAY_demo_upload.src = res.data.src;
                         LAY_demo_upload_VAL.value = res.data.src;
                              }
                        });
                 
				//自定义验证规则
				form.verify({
					name: function(value) {
						if(value.length < 1) {
							return '活动名称不能为空';
						}
						
					},order: function(value) {
						if(value.length < 1) {
							return '排序不能为空';
						}
						
					}
					
					
				});

				//监听提交
				form.on('submit(demo1)', function(data) {
				    	$.ajax({
				           url:'/admin/activities_input',
				           type:'post',
				           data:$('#form').serialize(),
				           success:function(data) {
				             if("0000"==data){
				                layer.msg('添加成功',{time:2000})
				                  setTimeout(function(){window.location.href="/admin/activities_list";},1000);
				              }else if("0001"==data){
				                  layer.msg("添加失败！", {time:2000 })
				                
				              }else{
				                 layer.msg('修改成功',{time:2000})
				                 setTimeout(function(){window.location.href="/admin/activities_list";},1000);
				               }
                         },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				 })
				 return false;
				});
			});
		</script>
	</body>

</html>