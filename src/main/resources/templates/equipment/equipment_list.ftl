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
				状态：<div class="layui-input-inline">
				 <select name="status" class="layui-input" id="status">
				     <option value="">全部</option>
				     <option value="0">未响应</option>
				     <option value="1">维修中</option>
				     <option value="2">已解决</option>
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
							    <th>保障类型</th>
								<th>保障者</th>
								<th>状态</th>
								<th>保障时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<#list page.list as ee>
						  <tr>
						     <td>${ee.mark}</td>
						     <td>${ee.nickName!''}</td> 
						     <td>
						      <#if ee.status=="0">
						      未响应
						      </#if>
						      <#if ee.status=="1">
						      处理中
						      </#if>
						      <#if ee.status=="2">
						       已处理
						      </#if> 
						     </td>
						     <td>
						      ${ee.createTime?string("yyyy-MM-dd HH:mm:ss")}
						     </td>
						      <td>
						      <#if ee.status=="0">
						      <a href="javascript:deal('${ee.id}','0');"  class="layui-btn layui-btn-mini">响应</a>
						      </#if>
						       <#if ee.status=="1">
						      <a href="javascript:deal('${ee.id}','1');"  class="layui-btn layui-btn-mini" >处理完成</a>
						      </#if>
						       
						       <a href="javascript:openView('${ee.id}');"  class="layui-btn layui-btn-mini" >查看</a>
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
				           url:'/admin/equipment_getList',
				           type:'post',
				           data:{"pageNo":obj.curr,"status":$('#status').val()},
				           success:function(data) { 
				            str = ''; 
				            if(data.errCode==='0001'){
				                $('.layui-elem-field').find('tbody').html(str)
				            }
                            $.each(data.list,function(i,val){
                               str += '<tr>'
								str += '<td>'+val.mark+'</td>'
								str += '<td>'+(val.hasOwnProperty('nick')?val.nick:'')+'</td>'
								str += '<td>'
								str += val.status=='0'?'未响应':(val.status=='1'?'处理中':'已处理');
								str += '</td>'
								str += '<td>'+val.time+'</td>'
								str += '<td>'
								
								str += '<a href="javascript:deal(\''+val.id+ '\',\''+val.status+'\');"  class="layui-btn layui-btn-mini">'+val.status=='0'?'响应':(val.status=='1'?'处理完成':'')+'</a>'
								
									str += '<a href="javascript:openView(\''+val.id+'\')" class="layui-btn layui-btn-mini" >查看</a>'
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
				           url:'/admin/equipment_getList',
				           type:'post',
				           data:{"status":$('#status').val()},
				           success:function(data) { 
				            str = ''; 
				            if(data.errCode==='0001'){
				                $('.layui-elem-field').find('tbody').html(str)
				            }
                            $.each(data.list,function(i,val){
                               str += '<tr>'
								str += '<td>'+(val.hasOwnProperty('mark')?val.mark:'')+'</td>'
								str += '<td>'+(val.hasOwnProperty('nick')?val.nick:'')+'</td>'
								str += '<td>'
								str += val.status=='0'?'未响应':(val.status=='1'?'处理中':'已处理');
								str += '</td>'
								str += '<td>'+(val.hasOwnProperty('time')?val.time:'')+'</td>'
								str += '<td>'
								str += '<a href="javascript:deal(\''+val.id+ '\',\''+val.status+'\');"  class="layui-btn layui-btn-mini">'+val.status=='0'?'响应':(val.status=='1'?'处理完成':'')+'</a>'
								str += '<a href="javascript:openView(\''+val.id+'\')" class="layui-btn layui-btn-mini" >查看</a>'
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
			
			
			function openView(id){
			   layer.open({
                     type: 2, 
                     area: ['400px', '450px'],
                     content: '/admin/equipment_to_iput?id='+id 
               }); 
			}
			function deal(id,type){
			 $.ajax({
				           url:'/admin/equipment_input',
				           type:'post',
				           data:{"id":id,"type":type},
				           success:function(data) { 
				            if(data==='0000'){
				                 layer.msg('处理成功',{time:2000})
				                  setTimeout(function(){window.location.href="/admin/equipment_list";},1000);
				            }
                            
                         },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				 })
			
			}
			
			
		</script>
	</body>

</html>
		      