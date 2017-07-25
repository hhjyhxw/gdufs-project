<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>活动列表</title>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../css/global.css" media="all">
		<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../css/table.css" />
		<style>
		 img {
		    display: inline-block;
		    border: none;
		    width: 26px;
		    height: 25px;
        }
		</style>
	</head>
	<body>
		<div class="admin-main">
			<blockquote class="layui-elem-quote">
			
				 标题：<div class="layui-input-inline"><input type="text" name="name" class="layui-input" value="${(name)!}" id="name"></div>
				状态：<div class="layui-input-inline">
				 <select name="status" class="layui-input" id="status">
				     <option value="">全部</option>
				     <option value="0">不显示</option>
				     <option value="1">显示</option>
				    </select>
				   </div> 
				 是否banner：<div class="layui-input-inline">
				 <select name="banner" class="layui-input" id="banner">
				     <option value="">全部</option>
				     <option value="0">不是</option>
				     <option value="1">是</option>
				    </select>
				   </div>
		        <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> 搜索
				</a>
				</form>	
			</blockquote>
				
			<fieldset class="layui-elem-field">
				<legend>列表</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
							    <th>宣传图</th>
								<th>名称</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<#list page.list as ac>
						  <tr>
						     <td><img src="${ac.icon!''}" ></td>
						     <td>${ac.name!''}</td> 
						    
						     <td style="text-align:center;"><#if ac.status=="0"><i class="layui-icon" style="color:red;">ဇ</i></#if><#if ac.status=="1"><i class="layui-icon" style="color:green;"></i></#if></td>
						      <td>
						       <a href="/admin/activities_to_input?id=${ac.id}"  class="layui-btn layui-btn-mini" >修改</a>
						       <a href="javascript:void(0);"  class="layui-btn layui-btn-mini" onclick="javascript:del('${ac.id}')">删除</a>
						       
						      </td> 
						  </tr>
						
						</#list>
						</tbody>
					</table>

				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="page" class="page">
				</div>
			</div>
		</div>
		<script type="text/javascript" src="../plugins/layui/layui.js"></script>
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script>
			layui.config({
				base: '../plugins/layui/modules/'
			});

			layui.use(['icheck', 'laypage','layer'], function() {
				var $ = layui.jquery,
					laypage = layui.laypage,
					layer = parent.layer === undefined ? layui.layer : parent.layer;
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-green'
				});
               
				//page
				laypage({
					cont: 'page',
					pages: ${page.pages} //总页数
						,
					groups: 10 //连续显示分页数
						,
					first:true,
					last:true,
					jump: function(obj, first) {
						//得到了当前页，用于向服务端请求对应数据
						var curr = obj.curr;
						if(!first) {
						 $.ajax({
				           url:'/admin/activities_getList',
				           type:'post',
				           data:{"pageNo":obj.curr,"name":$('#name').val(),"banner":$('#banner').val(),"status":$('#status').val()},
				           success:function(data) { 
				            str = ''; 
				            if(data.errCode==='0001'){
				                $('.layui-elem-field').find('tbody').html(str)
				            }
                            $.each(data.list,function(i,val){
                               str += '<tr>'
                                  
								str += '<td><img src="'+val.icon+' "/></td>'
								str += '<td>'+(val.hasOwnProperty('name')?val.name:'')+'</td>'

								str += '<td style="text-align:center;">'
								str += val.status=='1'?'<i class="layui-icon" style="color:green;"></i>':'<i class="layui-icon" style="color:red;">ဇ</i>'
								str += '</td>'
								
								str += '<td>'
								
								str += '<a href="/admin/activities_to_input?id='+val.id+'"  class="layui-btn layui-btn-mini" >修改</a>'
								str += '<a href="javascript:del(\''+val.id+'\');" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini" id='+ val.id+'>删除</a>'
                                str += '</tr>'
                            
                            
                            })
                            $('.layui-elem-field').find('tbody').html(str)
                         },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				 })
				 	}
					}
				});

				$('#search').on('click', function() {
						 $.ajax({
				           url:'/admin/activities_getList',
				           type:'post',
				           data:{"name":$('#name').val(),"banner":$('#banner').val(),"status":$('#status').val()},
				           success:function(data) { 
				            str = ''; 
				            if(data.errCode==='0001'){
				                $('.layui-elem-field').find('tbody').html(str)
				            }
                            $.each(data.list,function(i,val){
                                str += '<tr>'
                                  
								str += '<td><img src="'+val.icon+' "/></td>'
								str += '<td>'+(val.hasOwnProperty('name')?val.name:'')+'</td>'

								str += '<td style="text-align:center;">'
								str += val.status=='1'?'<i class="layui-icon" style="color:green;"></i>':'<i class="layui-icon" style="color:red;">ဇ</i>'
								str += '</td>'
								
								str += '<td>'
								
								str += '<a href="/admin/activities_to_input?id='+val.id+'"   class="layui-btn layui-btn-mini" >修改</a>'
								str += '<a href="javascript:del(\''+val.id+'\');" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini" id='+ val.id+'>删除</a>'
                                str += '</tr>'
                            
                            })
                            $('.layui-elem-field').find('tbody').html(str)
                         },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				 })
				});
				
				
				$('.site-table tbody tr').on('click', function(event) {
					var $this = $(this);
					var $input = $this.children('td').eq(0).find('input');
					$input.on('ifChecked', function(e) {
						$this.css('background-color', '#EEEEEE');
					});
					$input.on('ifUnchecked', function(e) {
						$this.removeAttr('style');
					});
					$input.iCheck('toggle');
				}).find('input').each(function() {
					var $this = $(this);
					$this.on('ifChecked', function(e) {
						$this.parents('tr').css('background-color', '#EEEEEE');
					});
					$this.on('ifUnchecked', function(e) {
						$this.parents('tr').removeAttr('style');
					});
				});
						
			});
			function del(id){
			$.ajax({
				           url:'../admin/activities_del',
				           type:'post',
				           data:{"id":id},
				           success:function(data) { 
				              layer.msg("删除成功",{time:2000});
				              setTimeout(function(){window.location.href="../admin/activities_list";},1000);
				              
                         },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				      })
			
			}
			
		</script>
	</body>

</html>
		      