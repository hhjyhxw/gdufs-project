<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>用户列表</title>
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
			<form id="form">
				（工/学）号：<div class="layui-input-inline"><input type="text" name="uid" class="layui-input" value="${(uid)!}" id="uid"></div>
				 姓名：<div class="layui-input-inline"> <input type="text" name="realName" class="layui-input" value="${(realName)!}" id="realName"></div>
				 状态：<div class="layui-input-inline">
				 <select name="status" class="layui-input" id="status">
				     <option value="">全部</option>
				     <option value="0">未绑定</option>
				     <option value="1">绑定</option>
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
							    <th>头像</th>
								<th>账号</th>
								<th>姓名</th>
								<th>状态</th>
								<th>院系</th>
								<th>年级</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<#list page.list as user>
						  <tr>
						     <td><img src="${user.headUrl!''}" ></td>
						     <td>${user.uid!''}</td> 
						      <td>${user.realName!''}</td> 
						     <td style="text-align:center;"><#if user.status=="1"><i class="layui-icon" style="color:red;">ဇ</i></#if><#if user.status=="0"><i class="layui-icon" style="color:green;"></i></#if></td>
						     <td>${user.university!''}</td>
						     <td>${user.grades!''}</td>
						      <td>
						       <a href="javascript:void(0)"  class="layui-btn layui-btn-mini" onclick="javascript:openDetail('${user.id}')">其他信息</a>
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
				           url:'/admin/user_getlist',
				           type:'post',
				           data:{"pageNo":obj.curr,"uid":$('#uid').val(),"realName":$('#realName').val(),"status":$('#status').val()},
				           success:function(data) { 
				            str = ''; 
				            if(data.errCode==='0001'){
				                $('.layui-elem-field').find('tbody').html(str)
				            }
                            $.each(data.list,function(i,val){
                                
                                str += '<tr>'
                                  
								str += '<td><img src="'+val.headUrl+' "/></td>'
								str += '<td>'+(val.hasOwnProperty('uid')?val.uid:'')+'</td>'
								str += '<td>'+(val.hasOwnProperty('realName')?val.realName:'')+'</td>'
								str += '<td style="text-align:center;">'
								str += val.status=='0'?'<i class="layui-icon" style="color:green;"></i>':'<i class="layui-icon" style="color:red;">ဇ</i>'
								str += '</td>'
								str += '<td>'+(val.hasOwnProperty('university')?val.university:'')+'</td>'
								str += '<td>'+(val.hasOwnProperty('grade')?val.grade:'')+'</td>'
								str += '<td>'
								
								str += '<a href="javascript:void(0)"  class="layui-btn layui-btn-mini" onclick="javascript:openDetail(\''+val.id+'\')">其他信息</a>'
								
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
				           url:'../admin/user_getlist',
				           type:'post',
				           data:{"uid":$('#uid').val(),"realName":$('#realName').val(),"status":$('#status').val()},
				           success:function(data) { 
				            str = ''; 
				            if(data.errCode==='0001'){
				                $('.layui-elem-field').find('tbody').html(str)
				            }
                            $.each(data.list,function(i,val){
                                str += '<tr>'
                                  
								str += '<td><img src="'+val.headUrl+' "/></td>'
								str += '<td>'+(val.hasOwnProperty('uid')?val.uid:'')+'</td>'
								str += '<td>'+(val.hasOwnProperty('realName')?val.realName:'')+'</td>'
								str += '<td style="text-align:center;">'
								str += val.status=='0'?'<i class="layui-icon" style="color:green;"></i>':'<i class="layui-icon" style="color:red;">ဇ</i>'
								str += '</td>'
								str += '<td>'+(val.hasOwnProperty('university')?val.university:'')+'</td>'
								str += '<td>'+(val.hasOwnProperty('grade')?val.grade:'')+'</td>'
								str += '<td>'
								
								str += '<a href="javascript:void(0)"  class="layui-btn layui-btn-mini" onclick="javascript:openDetail(\''+val.id+'\')">其他信息</a>'
								
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
			 function openDetail(id){
                  layer.open({
                     type: 2, 
                     area: ['500px', '300px'],
                      content: '/admin/user_input?id='+id 
                      }); 
                   
                }
		</script>
	</body>

</html>
		      